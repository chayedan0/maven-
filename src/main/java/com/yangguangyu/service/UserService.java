/**
 * 
 */
package com.yangguangyu.service;

import com.github.pagehelper.PageInfo;
import com.yangguangyu.entity.User;

/**
 * @author 杨光宇
 * 2019年11月19日
 */
public interface UserService {

	/**注册
	 * @param user
	 * @return
	 */
	int register(User user);
	/**验证
	 * @param user
	 * @return
	 */
	User findByName(String username);
	/**
	 * @param user
	 * @return
	 */
	User login(User user);
	/**
	 * @param name
	 * @param page
	 * @return
	 */
	PageInfo<User> getPageList(String name, Integer page);
	/**
	 * @param userId
	 * @return
	 */
	User getUserById(Integer userId);
	/**
	 * @param userId
	 * @param status
	 * @return
	 */
	int updateStatus(Integer userId, int status);
	
}
