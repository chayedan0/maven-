/**
 * 
 */
package com.yangguangyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangguangyu.dao.ChannelMapper;
import com.yangguangyu.entity.Channel;
import com.yangguangyu.service.ChannelService;

/**
 * @author 杨光宇
 * 2019年11月21日
 */
@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	ChannelMapper channelMapper;
	/* (non-Javadoc)
	 * @see com.yangguangyu.service.ChannelService#list()
	 */
	@Override
	public List<Channel> list() {
		// TODO Auto-generated method stub
		return channelMapper.list();
	}

}
