/**
 * 
 */
package com.yangguangyu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.yangguangyu.entity.Article;
import com.yangguangyu.entity.Category;
import com.yangguangyu.entity.Channel;
import com.yangguangyu.entity.Link;
import com.yangguangyu.service.ArticleService;
import com.yangguangyu.service.CategoryService;
import com.yangguangyu.service.ChannelService;
import com.yangguangyu.service.LinkService;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
@Controller
public class IndexController {
	@Autowired
	ChannelService channelService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ArticleService articleService;
	@Autowired
	LinkService linkService;
	@RequestMapping("channel")
	public String channel(HttpServletRequest request,
			@RequestParam(defaultValue="1")int chnId,
			@RequestParam(defaultValue="0")int categoryId,
			@RequestParam(defaultValue="1")int page){
		//回传参数数值
		request.setAttribute("chnId", chnId);
		request.setAttribute("categoryId", categoryId);
		//获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
		//获取这个频道下的所有分类
		List<Category> categories=categoryService.listByChannelId(chnId);
		request.setAttribute("categories", categories);
		
		PageInfo<Article> articles=articleService.listByCat(chnId,categoryId,page);
		request.setAttribute("articles", articles);
		
		List<Article> newArticles=articleService.getNewArticles(5);
		request.setAttribute("newArticles", newArticles);
		//友情连接
		List<Link> linkList=linkService.getLinkLists();
		request.setAttribute("linkList", linkList);
		return "channelindex";
	}
	@RequestMapping(value={"index","/"})
	public String index(HttpServletRequest request,@RequestParam(defaultValue="1")int page){
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
		
		PageInfo<Article> hotList=articleService.hotList(page);
		
		List<Article> newArticles=articleService.getNewArticles(5);
		
		request.setAttribute("hotList", hotList);
		request.setAttribute("newArticles", newArticles);
		List<Link> linkList=linkService.getLinkLists();
		request.setAttribute("linkList", linkList);
		return "index";
	}
}
