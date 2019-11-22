/**
 * 
 */
package com.yangguangyu.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.yangguangyu.common.CmsAssert;
import com.yangguangyu.common.ConstantClass;
import com.yangguangyu.common.MsgResult;
import com.yangguangyu.entity.Article;
import com.yangguangyu.entity.Channel;
import com.yangguangyu.entity.User;
import com.yangguangyu.service.ArticleService;
import com.yangguangyu.service.ChannelService;
import com.yangguangyu.service.UserService;

/**
 * @author 杨光宇
 * 2019年11月19日
 */
@Controller
@RequestMapping("user")
public class UserController {
	Logger log = Logger.getLogger(UserController.class);
	@Value("${upload.path}")
	String updloadPath;
	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ChannelService channelService;
	private SimpleDateFormat dateFormat;
	/*判断请求方式*/
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(HttpServletRequest request){
		return "user/register";
	}
	/*注册*/
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(HttpServletRequest request,User user){
		int result=userService.register(user);
		System.out.println(result);
		/*抛错误*/
		CmsAssert.AssertTrue(result>0, "用户注册失败，请稍后重试");
		return "redirect:/user/login";
	}
	/*判断登录请求*/
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request){
		return "user/login";
	}
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,User user){
		/*登录用户*/
		User loginUser=userService.login(user);
		if(loginUser!=null){
			/*用户为存在*/
			request.getSession().setAttribute(ConstantClass.USER_KEY, loginUser);
			/*判断用户的role是否是admin管理元*/
			return loginUser.getRole()==ConstantClass.USER_ROLE_ADMIN?"redirect:/admin/index":"redirect:/user/home";
		}else{
			/*用户不存在*/
			request.setAttribute("errorMsg", "用户名或密码错误");
			request.setAttribute("user", user);
			return "user/login";
		}
	}
	//退出登陆
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		//清楚user key
		request.getSession().removeAttribute(ConstantClass.USER_KEY);
		return "redirect:/";
	}
	@RequestMapping("checkname")
	@ResponseBody
	public boolean checkname(String username){
		return null==userService.findByName(username);
	}
	@RequestMapping("home")
	public String home(HttpServletRequest request){
		return "/user/home";
	}
	
	@GetMapping("updateArticle")
	public String updateArticle(HttpServletRequest request,int id){
		// 获取文章的详情 用于回显
		Article article=articleService.getDetailById(id);
		request.setAttribute("article", article);
		request.setAttribute("content1", htmlspecialchars(article.getContent()));
		System.out.println("将要修改的文章"+article);
		//获取所有的频道
		List<Channel> channels=channelService.list();
		request.setAttribute("channels", channels);
		return "article/update";
	}
	/*替换字符串*/
	private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
	@RequestMapping("updateArticle")
	@ResponseBody
	public MsgResult updateArticle(HttpServletRequest request,MultipartFile file,Article article) throws IllegalStateException, IOException{
		if(!file.isEmpty()){
			String picUrl=processFile(file);
			article.setPicture(picUrl);
		}
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		article.setUserId(loginUser.getId());
		
		int result=articleService.update(article);
		if(result>0){
			return new MsgResult(1,"处理成功",null);
			
		}else{
			return new MsgResult(2, "添加失败，稍后重试", null);
		}
	}
	private String processFile(MultipartFile file) throws IllegalStateException, IOException{
		log.info("updloadPath is "+updloadPath);
		//0求扩展名
		String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		String fileNamePre =UUID.randomUUID().toString();
		//新名称
		String fileName =fileNamePre+suffixName;
		
		dateFormat=new SimpleDateFormat("yyyyMMdd");
		String path = dateFormat.format(new Date());
		File pathFile = new File(updloadPath+"/"+path);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		//最后名称
		String newFileName=updloadPath+"/"+path+"/"+fileName;
		
		file.transferTo(new File(newFileName));
		return path+"/"+fileName;
	}
	@RequestMapping("myarticles")
	public String mybaticles(HttpServletRequest request,@RequestParam(defaultValue="1")int page){
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		//用户获取文章
		PageInfo<Article> pageInfo=articleService.listByUser(page,loginUser.getId());
		request.setAttribute("pageInfo", pageInfo);
		return "user/myarticles";
	}
	/*删除文章*/
	@RequestMapping("delArticle")
	@ResponseBody
	public MsgResult delArticle(HttpServletRequest request,int id){
		//判断文章索引
		CmsAssert.AssertTrue(id>0, "文章必须大于0");
		//获取文章
		Article article=articleService.checkExist(id);
		//当前登录对象
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(loginUser.getRole()==ConstantClass.USER_ROLE_ADMIN||loginUser.getId()==article.getUserId(), "只有管理员和文章的作者能删除文章");
		int result=articleService.delete(id);
		CmsAssert.AssertTrue(result>0, "文章删除失败");
		return new MsgResult(1,"删除成功",null);
	}
	/*点击发布文章触发*/
	/**
	 * 进入发表文章的界面
	 * @param request
	 * @return
	 */
	@GetMapping("postArticle")
	public String postArticle(HttpServletRequest request) {
		
		// 获取所有的频道
		List<Channel> channels =  channelService.list();
		request.setAttribute("channels", channels);
		return "article/publish";
	}
	@PostMapping("postArticle")
	@ResponseBody
	public MsgResult postArticle(HttpServletRequest request, MultipartFile file,Article article) throws IllegalStateException, IOException{
		
		if(!file.isEmpty()) {
			String fileUrl = processFile(file);
			article.setPicture(fileUrl);
		}
		User loginUser  = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		System.out.println(loginUser.getId()+"userid显示出来");
		article.setUserId(loginUser.getId());
		
		int result = articleService.add(article);
		if(result>0) {
			return new MsgResult(1, "处理成功",null);
		}else {
			return new MsgResult(2, "添加失败，请稍后再试试！",null);
		}
	}
	@RequestMapping("channel")
	public String channel(){
		return "redirect:/";
	}
	@RequestMapping("user/home")
	public String home(){
		return "redirect:/user/home";
	}
	@RequestMapping("admin/index")
	public String admin(){
		return "redirect:/admin/index";
	}
}
