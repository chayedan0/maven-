/**
 * 
 */
package com.yangguangyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangguangyu.dao.CategoryMapper;
import com.yangguangyu.entity.Category;
import com.yangguangyu.service.CategoryService;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.CategoryService#listByChannelId(int)
	 */
	@Override
	public List<Category> listByChannelId(int chnId) {
		// TODO Auto-generated method stub
		return categoryMapper.listByChannelId(chnId);
	}

}
