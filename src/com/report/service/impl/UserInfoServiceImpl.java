package com.report.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.bean.UserInfo;
import com.report.dao.IUserInfoDao;
import com.report.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
	
    @Autowired
	IUserInfoDao dao;
	
	@Override
	public UserInfo userLogin(String userName, String password)
			throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = dao.selectUserByUserNameAndPassword(userName, password);
		return userInfo;
	}

	@Override
	public void userRegister(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		dao.insertUser(userInfo);

	}

	@Override
	public boolean chackUserIsRegisted(String userName) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		UserInfo userInfo = dao.selectUserByUserName(userName);
		if(userInfo !=null){
			result = true;
		}
		return result;
	}

	@Override
	public JSONObject getUserInfoList(String userName, String realName,
			int begin, int end) throws Exception {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		List<UserInfo> userInfos = dao.selectUserInfoForList(userName, realName, begin, end);
		int count = dao.selectUserInfoCount(userName, realName);
		JSONArray userInfosArray = JSONArray.fromObject(userInfos);
		result.put("userInfos", userInfosArray);
		result.put("cnt", count);
		result.put("status", "ok");
				
		return result;
	}

	@Override
	public UserInfo getUserInfoByPk(String id) throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = dao.selectUserInfoByPK(id);
		return userInfo;
	}

	@Override
	public String getQuestionByUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = dao.selectUserByUserName(userName);
		String question = null;
		if(userInfo !=null){
			question = userInfo.getQuestion();
		}
		return question;
	}

	@Override
	public UserInfo checkIsQuestionRigth(String userName, String answer)
			throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = dao.selectUserInfoByUserNameAndAnswer(userName, answer);
		return userInfo;
	}

	@Override
	public void modifyPassword(String userName, String password)
			throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userName);
		userInfo.setPassword(password);
		dao.updateUser(userInfo);
		
	}

	@Override
	public int updateUserMySelf(UserInfo userInfo) throws Exception {
		return dao.updateUserMySelf(userInfo);
	}

}
