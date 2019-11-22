/**
 * 
 */
package com.yangguangyu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.yangguangyu.common.ConstantClass;
import com.yangguangyu.entity.User;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
public class UserInterceptor implements HandlerInterceptor{
	public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
			User longUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
			
			if(longUser==null){
				response.sendRedirect("/user/login");
				return false;
			}
			if(request.getServletPath().contains("/admin/")&&longUser.getRole()==ConstantClass.USER_ROLE_GENERAL){
				request.setAttribute("errorMsg", "只有管理员才能访问这个页面");
				request.getRequestDispatcher("/user/login").forward(request, response);
				return false;
			}
		return true;
	}
}
