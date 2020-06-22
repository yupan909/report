package com.report.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.report.bean.Msg;
import com.report.dao.IMsgDao;
import com.report.utils.JStringUtil;

@Service
public class MsgDaoImpl implements IMsgDao {

	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public void insertMsg(Msg msg) throws Exception {
		// TODO Auto-generated method stub
		String id =  JStringUtil.creatId();
		String createTime = JStringUtil.getNowDate();
		msg.setId(id);
		msg.setCreateTime(createTime);
		msg.setIsRead("N");
		sqlMapClient.insert("insertMsg", msg);

	}

	@Override
	public List<Msg> selectMsg(String userName, int begin, int end)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("userName", userName);
		condition.put("begin", begin);
		condition.put("end", end);
		List<Msg> msgs = sqlMapClient.queryForList("selectMsg", condition);
		return msgs;
	}

	@Override
	public Integer selectMsgCount(String userName) throws Exception {
		// TODO Auto-generated method stub
		Integer count = (Integer) sqlMapClient.queryForObject("selectMsgCount", userName);
		return count;
	}

	@Override
	public void setIsRead(String id) throws Exception {
		// TODO Auto-generated method stub
		sqlMapClient.update("setIsRead", id);

	}

	@Override
	public Integer getMsgCount(String userName) throws Exception{
		Integer count = (Integer) sqlMapClient.queryForObject("getMsgCount", userName);
		return count;
	}

}
