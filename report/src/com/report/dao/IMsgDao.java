package com.report.dao;

import java.util.List;

import com.report.bean.Msg;

public interface IMsgDao {
	/**
	 * 插入消息
	 * @param msg
	 * @throws Exception
	 */
	public void insertMsg(Msg msg) throws Exception;
	
	/**
	 * 获取消息列表
	 * @param userName
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Msg> selectMsg(String userName,int begin,int end) throws Exception;
	
	/**
	 * 获取消息条数
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Integer selectMsgCount(String userName) throws Exception;
	
	/**
	 * 设置消息已读
	 * @param id
	 * @throws Exception
	 */
	public void setIsRead(String id) throws Exception;
	
	/**
	 * 获取当前用户未读的消息总条数
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Integer getMsgCount(String userName) throws Exception;

}
