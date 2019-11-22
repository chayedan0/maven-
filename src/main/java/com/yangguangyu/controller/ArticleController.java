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

import com.yangguangyu.common.CmsAssert;
import com.yangguangyu.common.MsgResult;
import com.yangguangyu.dao.ArticleMapper;
import com.yangguangyu.entity.Article;
import com.yangguangyu.entity.Category;
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
}
