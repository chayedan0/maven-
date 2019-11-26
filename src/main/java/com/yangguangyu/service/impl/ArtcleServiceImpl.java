/**
 * 
 */
package com.yangguangyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangguangyu.common.ConstantClass;
import com.yangguangyu.dao.ArticleMapper;
import com.yangguangyu.entity.Article;
import com.yangguangyu.service.ArticleService;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
@Service
public class ArtcleServiceImpl implements ArticleService {
	@Autowired
	ArticleMapper articleMapper;
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#getDetailById(int)
	 */
	@Override
	public Article getDetailById(int id) {
		// TODO Auto-generated method stub
		return articleMapper.getDetailById(id);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#add(com.yangguangyu.entity.Article)
	 */
	@Override
	public int add(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.add(article);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#listByUser(int, java.lang.Integer)
	 */
	@Override
	public PageInfo<Article> listByUser(int page, Integer userId) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		
		return new PageInfo<Article>(articleMapper.listByUser(userId));
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#checkExist(int)
	 */
	@Override
	public Article checkExist(int id) {
		// TODO Auto-generated method stub
		return articleMapper.checkExist(id);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#delete(int)
	 */
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return articleMapper.delete(id);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#listByCat(int, int, int)
	 */
	@Override
	public PageInfo<Article> listByCat(int chnId, int categoryId, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		
		return new PageInfo<Article>(articleMapper.listByCat(chnId,categoryId));
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#hotList(int)
	 */
	@Override
	public PageInfo<Article> hotList(int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		
		return	new PageInfo<Article>(articleMapper.hotList());
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#getNewArticles(int)
	 */
	@Override
	public List<Article> getNewArticles(int i) {
		// TODO Auto-generated method stub
		return articleMapper.newList(i);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#getPageList(int, java.lang.Integer)
	 */
	@Override
	public PageInfo<Article> getPageList(int status, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.listByStatus(status));
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#apply(int, int)
	 */
	@Override
	public int apply(int id, int status) {
		// TODO Auto-generated method stub
		return articleMapper.apply(id,status);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#setHot(int, int)
	 */
	@Override
	public int setHot(int id, int status) {
		// TODO Auto-generated method stub
		return articleMapper.setHot(id,status);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#getById(java.lang.Integer)
	 */
	@Override
	public Article getById(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.getById(id);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#update(com.yangguangyu.entity.Article)
	 */
	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#collections(int, java.lang.Integer)
	 */
	@Override
	public PageInfo<Article> collections(int page, Integer id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.collections(id));
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#addCollection(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void addCollection(Integer aid, Integer uid) {
		// TODO Auto-generated method stub
		articleMapper.addCollection(aid,uid);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#collectionArticle(java.lang.Integer)
	 */
	@Override
	public Article collectionArticle(Integer aid,Integer uid) {
		// TODO Auto-generated method stub
		return articleMapper.collectionArticle(aid,uid);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ArticleService#delCollection(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void delCollection(Integer aid, Integer uid) {
		// TODO Auto-generated method stub
		articleMapper.delCollection(aid,uid);
	}
	
}
