/**
 * 
 */
package com.yangguangyu.dao;

import java.util.List;

import com.yangguangyu.entity.Category;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
public interface CategoryMapper {

	/**
	 * @param chnId
	 * @return
	 */
	List<Category> listByChannelId(int chnId);

}
