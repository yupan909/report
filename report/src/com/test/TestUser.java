package com.test;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.report.bean.UserInfo;
import com.report.dao.IUserInfoDao;

public class TestUser {
	private static IUserInfoDao userDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext cxt=new ClassPathXmlApplicationContext("/config/beans.xml");
		userDao=  (IUserInfoDao) cxt.getBean("UserInfoDaoImpl");
	}

	@Test
	public void test() {
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName("胡俊辉");
			userInfo.setPassword("hujunhui");
			userDao.selectUserByUserNameAndPassword("admin","admin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
