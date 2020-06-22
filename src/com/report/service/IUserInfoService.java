package com.report.service;

import net.sf.json.JSONObject;

import com.report.bean.UserInfo;

public interface IUserInfoService {
	/**
	 * 用户登陆
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public UserInfo userLogin(String userName,String password) throws Exception;
	
	/**
	 * 用户注册
	 * @param userInfo
	 * @throws Exception
	 */
	public void userRegister(UserInfo userInfo) throws Exception;
	
	/**
	 * 判断该用户名是否已经被注册
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean chackUserIsRegisted(String userName) throws Exception;
	
	/**
	 * 获取用户信息列表
	 * @param userName
	 * @param realName
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUserInfoList(String userName,String realName,int begin,int end) throws Exception;
	
	/**
	 * 根据主键获取用户的详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserInfo getUserInfoByPk(String id) throws Exception;
	
	/**
	 * 获取用户的问题
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public String getQuestionByUserName(String userName) throws Exception;
	/**
	 * 检查问题答案是否正确
	 * @param userName
	 * @param answer
	 * @return
	 * @throws Exception
	 */
	public UserInfo checkIsQuestionRigth(String userName,String answer) throws Exception;
	
	/**
	 * 修改密码
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void modifyPassword(String userName,String password) throws Exception;
	
	/**
	 * 修改个人信息
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public int updateUserMySelf(UserInfo userInfo) throws Exception;
	

}
