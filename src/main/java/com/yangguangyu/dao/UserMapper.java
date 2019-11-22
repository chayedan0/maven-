/**
 * 
 */
package com.yangguangyu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangguangyu.entity.User;

/**
 * @author 杨光宇
 * 2019年11月19日
 */
public interface UserMapper {

	/**
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM cms_user "
			+ " WHERE username = #{value} limit 1 ")
	User findByUserName(String username);

	/**添加
	 * @param user
	 * @return
	 */
	int add(User user);

	/**
	 * @param name
	 * @return
	 */
	List<User> list(String name);

	/**
	 * @param userId
	 * @return
	 */
	User getById(Integer userId);

	/**
	 * @param userId
	 * @param status
	 * @return
	 */
	@Update("UPDATE cms_user SET locked=${status} WHERE id=${userId}")
	int updateStatus(@Param("userId") Integer userId, 
			@Param("status") int status);
	
}
