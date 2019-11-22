/**
 * 
 */
package com.yangguangyu.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yangguangyu.entity.Company;
import com.yangguangyu.utils.DateUtils;
import com.yangguangyu.utils.NumberUtil;
import com.yangguangyu.utils.StringUtils;

/**
 * @author 杨光宇
 * 2019年11月18日
 */
public class TestAdd extends TestBase{
	@Autowired
	ComService service;
	@Test
	public void add(){
		Random random = new Random();
		StringUtils stringUtils = new StringUtils();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		 Calendar cal = Calendar.getInstance();
		NumberUtil numberUtil = new NumberUtil();
		DateUtils dateUtils = new DateUtils();
		
			//公司名称
			String str1[]={"有限公司","股份有限公司","集团有限公司"};
			String name="北京"+stringUtils.getRandonCnStr(numberUtil.getMamMinNum(2, 4))+str1[random.nextInt(3)];
			//法人代表姓名
			String corporation = stringUtils.getRandonCnStr(numberUtil.getMamMinNum(2,3));
			//注册地址
			String address="北京市"+stringUtils.getRandonCnStr(numberUtil.getMamMinNum(20,40));
			//注册资本
			String capital=String.valueOf(numberUtil.getMamMinNum(100000,1000000000));
			//营业执照号
			String regist_no="1101"+String.valueOf(stringUtils.getRandomNumtoStr(11));
			//公司经济类型
			int type=numberUtil.getMamMinNum(0, 11);
			//成立时间
			String today = sdf.format(date);
			Date randomDate = dateUtils.randomDate("1980-01-01", today);
			String created = sdf.format(randomDate);
			cal.setTime(randomDate);
			cal.add(Calendar.YEAR, 50);
			String period=sdf.format(cal.getTime());
			
		
	}
}
