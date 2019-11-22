/**
 * 
 */
package com.yangguangyu.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yangguangyu.entity.Article;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
public interface ArticleService {

	/**
	 * @param id
	 * @return
	 */
	Article getDetailById(int id);

	/**
	 * @param article
	 * @return
	 */
	int add(Article article);

	/**
	 * @param page
	 * @param id
	 * @return
	 */
	PageInfo<Article> listByUser(int page, Integer id);

	/**
	 * @param id
	 * @return
	 */
	Article checkExist(int id);

	/**
	 * @param id
	 * @return
	 */
	int delete(int id);

	/**
	 * @param chnId
	 * @param categoryId
	 * @param page
	 * @return
	 */
	PageInfo<Article> listByCat(int chnId, int categoryId, int page);

	/**
	 * @param page
	 * @return
	 */
	PageInfo<Article> hotList(int page);

	/**
	 * @param i
	 * @return
	 */
	List<Article> getNewArticles(int i);

	/**
	 * @param status
	 * @param page
	 * @return
	 */
	PageInfo<Article> getPageList(int status, Integer page);

	/**
	 * @param id
	 * @param status
	 * @return
	 */
	int apply(int id, int status);

	/**
	 * @param id
	 * @param status
	 * @return
	 */
	int setHot(int id, int status);

	/**
	 * @param id
	 * @return
	 */
	Article getById(Integer id);

	/**
	 * @param article
	 * @return
	 */
	int update(Article article);

}
