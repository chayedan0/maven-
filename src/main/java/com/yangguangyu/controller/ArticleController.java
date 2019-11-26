/**
 * 
 */
package com.yangguangyu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.yangguangyu.common.CmsAssert;
import com.yangguangyu.common.ConstantClass;
import com.yangguangyu.common.MsgResult;
import com.yangguangyu.dao.ArticleMapper;
import com.yangguangyu.entity.Article;
import com.yangguangyu.entity.Category;
import com.yangguangyu.entity.User;
import com.yangguangyu.service.ArticleService;
import com.yangguangyu.service.CategoryService;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
@RequestMapping("article")
@Controller
public class ArticleController {
	@Autowired
	ArticleService articleService;
	@Autowired
	CategoryService catService;
	@RequestMapping("showdetail")
	public String showDetail(HttpServletRequest request,Integer id){
		Article article=articleService.getById(id);
		CmsAssert.AssertTrueHtml(article!=null, "文章不存在");
		request.setAttribute("article", article);
		return "article/detail";
	}
	@RequestMapping("getCategoryByChannel")
	@ResponseBody
	public MsgResult getCategoryByChannel(int chnId){
		List<Category> categories = catService.listByChannelId(chnId);
		return new MsgResult(1,"",categories);
	}
	@RequestMapping("addCollection")
	@ResponseBody
	public Object addCollection(HttpServletRequest request,Integer id){
		Article article=articleService.getById(id);
		User loginUser  = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrueHtml(article!=null, "文章不存在");
		Article collectionArticle=articleService.collectionArticle(id,loginUser.getId());
		if(collectionArticle!=null){
			return new MsgResult(0,"您已经收藏过了",null);
		}
		try {
			articleService.addCollection(id,loginUser.getId());
			return new MsgResult(1,"收藏成功",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new MsgResult(2,"收藏失败",null);
		}
	}
}
