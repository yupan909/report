package com.report.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.report.bean.UserInfo;
import com.report.dao.IUserInfoDao;
import com.report.utils.JStringUtil;

@Service
public class UserInfoDaoImpl implements IUserInfoDao {
	
	@Resource
	private SqlMapClient sqlMapClient;

	@Override
	public void insertUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replace("-", "");
		userInfo.setId(id);
		sqlMapClient.insert("insertUser", userInfo);

	}

	@Override
	public UserInfo selectUserByUserNameAndPassword(String userName,
			String password) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("userName", userName);
		condition.put("password", password);
		UserInfo userInfo =(UserInfo) sqlMapClient.queryForObject("selectUserByUserNameAndPassword", condition);
		return userInfo;
	}

	@Override
	public UserInfo selectUserByUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("userName", userName);
		UserInfo userInfo =(UserInfo) sqlMapClient.queryForObject("selectUserByUserName", condition);
		return userInfo;
	}

	@Override
	public void updateUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		sqlMapClient.update("updateUser", userInfo);

	}

	@Override
	public List<UserInfo> selectUserInfoForList(String userName,
			String realName, int begin, int end) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("begin", begin);
		condition.put("end", end);
		if(!JStringUtil.isEmpty(userName)){
			condition.put("userName", userName);
		}
		if(!JStringUtil.isEmpty(realName)){
			condition.put("realName", realName);
		}
		List<UserInfo> userInfos = sqlMapClient.queryForList("selectUserInfoForList", condition);
		return userInfos;
	}

	@Override
	public Integer selectUserInfoCount(String userName, String realName)
			throws Exception {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> condition = new HashMap<>();
		if(!JStringUtil.isEmpty(userName)){
			condition.put("userName", userName);
		}
		if(!JStringUtil.isEmpty(realName)){
			condition.put("realName", realName);
		}
		
		Integer count = (Integer) sqlMapClient.queryForObject("selectUserInfoCount", condition);
		return count;
	}

	@Override
	public UserInfo selectUserInfoByPK(String id) throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = (UserInfo) sqlMapClient.queryForObject("selectUserInfoByPK", id);
		return userInfo;
	}

	@Override
	public UserInfo selectUserInfoByUserNameAndAnswer(String userName,
			String answer) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("userName", userName);
		condition.put("answer", answer);
		UserInfo userInfo = (UserInfo) sqlMapClient.queryForObject("selectUserInfoByUserNameAndAnswer", condition);
		return userInfo;
	}

	@Override
	public int updateUserMySelf(UserInfo userInfo) throws Exception {
		return sqlMapClient.update("updateUserMySelf", userInfo);
	}

}
