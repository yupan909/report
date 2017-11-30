package com.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.report.bean.Msg;
import com.report.dao.IMsgDao;
import com.report.service.IMsgService;
@Service
public class MsgServiceImpl implements IMsgService {
	
	@Autowired
	IMsgDao dao;

	@Override
	public void sendMsg(String userName, String msgDetail) throws Exception {
		// TODO Auto-generated method stub
		Msg msg = new Msg();
		msg.setUserName(userName);
		msg.setMsgDetail(msgDetail);
		dao.insertMsg(msg);

	}

	@Override
	public JSONObject getMsgs(String userName, int begin, int end)
			throws Exception {
		// TODO Auto-generated method stub
		List<Msg> msgList = dao.selectMsg(userName, begin, end);
		int count = dao.selectMsgCount(userName);
		JSONArray msgs = JSONArray.fromObject(msgList);
		JSONObject result = new JSONObject();
		result.put("msgs", msgs);
		result.put("cnt", count);
		return result;
	}

	@Override
	public void setIsRead(String id) throws Exception {
		// TODO Auto-generated method stub
        dao.setIsRead(id);
	}
	
	@Override
	public Integer getMsgCount(String userName) throws Exception{
		return dao.getMsgCount(userName);
	}

}
