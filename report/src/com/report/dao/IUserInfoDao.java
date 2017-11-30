package com.report.dao;


import java.util.List;

import com.report.bean.UserInfo;
/**
 * 用户信息dao
 * @author Administrator
 *
 */
public interface IUserInfoDao {
	
	/**
	 * 插入用户信息（注册）
	 * @param userInfo 用户信息
	 * @throws Exception
	 */
	public void insertUser(UserInfo userInfo) throws Exception;
	
	/**
	 * 根据用户的用户名和密码获取用户信息（登陆）
	 * @param userName 用户名
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	public UserInfo selectUserByUserNameAndPassword(String userName,String password) throws Exception;
	
	/**
	 * 根据用户名获取用户信息（用户验证）
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public UserInfo selectUserByUserName(String userName) throws Exception;
	
	/**
	 * 根据用户名和答案获取用户信息
	 * @param userName
	 * @param answer
	 * @return
	 * @throws Exception
	 */
	public UserInfo selectUserInfoByUserNameAndAnswer(String userName,String answer) throws Exception;
	
	
	/**
	 * 更新用户信息
	 * @param userInfo
	 * @throws Exception
	 */
	public void updateUser(UserInfo userInfo) throws Exception;
	
	/**
	 * 
	 * 根据查询条件查询用户信息并分页
	 * @param userName
	 * @param realName
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> selectUserInfoForList(String userName,String realName,int begin,int end) throws Exception;
	
	/**
	 * 根据查询条件获取用户总数
	 * @param userName
	 * @param realName
	 * @return
	 * @throws Exception
	 */
	public Integer selectUserInfoCount(String userName,String realName)  throws Exception;
	
	/**
	 * 根据主键获取用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserInfo selectUserInfoByPK(String id) throws Exception;
	
	
	/**
	 * 更新用户信息---个人信息
	 * @param userInfo
	 * @throws Exception
	 */
	public int updateUserMySelf(UserInfo userInfo) throws Exception;

   
}
