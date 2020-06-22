package com.report.service;

import net.sf.json.JSONObject;

public interface IMsgService {
	
	/**
	 * 发送消息
	 * @param userName
	 * @param msgDetail
	 * @throws Exception
	 */
	public void sendMsg(String userName,String msgDetail) throws Exception;
	
	/**
	 * 获取消息列表
	 * @param userName
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public JSONObject getMsgs(String userName,int begin,int end) throws Exception;
	
	/**
	 * 设置消息已读
	 * @param id
	 * @throws Exception
	 */
	public void setIsRead(String id) throws Exception;
	

	/**
	 * 获取当前用户未读的消息总条数
	 * @param userName
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public Integer getMsgCount(String userName) throws Exception;
	
}
