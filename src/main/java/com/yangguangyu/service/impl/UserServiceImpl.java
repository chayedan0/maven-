/**
 * 
 */
package com.yangguangyu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangguangyu.common.CmsAssert;
import com.yangguangyu.common.ConstantClass;
import com.yangguangyu.common.Md5;
import com.yangguangyu.dao.UserMapper;
import com.yangguangyu.entity.User;
import com.yangguangyu.service.UserService;

/**
 * @author 杨光宇
 * 2019年11月19日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.UserService#register(com.yangguangyu.entity.User)
	 */
	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		User existUser = findByName(user.getUsername());
		CmsAssert.AssertTrue(existUser==null, "该用户已存在");
		//加盐
		user.setPassword(Md5.password(user.getPassword(), user.getUsername().substring(0,2)));
		
		return userMapper.add(user);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.UserService#findByName(java.lang.String)
	 */
	@Override
	public User findByName(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByUserName(username);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.UserService#login(com.yangguangyu.entity.User)
	 * 登录
	 */
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		/*通过姓名查询用户*/
		User loginUser = findByName(user.getUsername());
		if(loginUser==null)
		return null;
		//计算加盐
		String pwdSaltMd5 = Md5.password(user.getPassword(), user.getUsername().substring(0,2));
		//判断加盐是否与loginUser密码相同
		if(pwdSaltMd5.equals(loginUser.getPassword())){
			return loginUser;
		}else{
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.UserService#getPageList(java.lang.String, java.lang.Integer)
	 */
	@Override
	public PageInfo<User> getPageList(String name, Integer page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, ConstantClass.PAGE_SIZE);
		return new PageInfo<User>(userMapper.list(name));
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.UserService#getUserById(java.lang.Integer)
	 */
	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getById(userId);
	}
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.UserService#updateStatus(java.lang.Integer, int)
	 */
	@Override
	public int updateStatus(Integer userId, int status) {
		// TODO Auto-generated method stub
		return  userMapper.updateStatus(userId,status);
	}
	
}
