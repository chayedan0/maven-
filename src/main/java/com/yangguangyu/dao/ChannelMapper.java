/**
 * 
 */
package com.yangguangyu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yangguangyu.entity.Channel;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
public interface ChannelMapper {

	/**
	 * @return
	 */
	@Select("SELECT * FROM cms_channel ORDER BY id")
	List<Channel> list();

}
