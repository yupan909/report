package com.report.bean;
/**
 * 
 * @author Administrator
 *
 */
public class Msg {
	private String id;
	private String userName;
	private String msgDetail;
	private String createTime;
	private String isRead;
	
	
	public Msg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Msg(String userName, String msgDetail, String isRead) {
		super();
		this.userName = userName;
		this.msgDetail = msgDetail;
		this.isRead = isRead;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMsgDetail() {
		return msgDetail;
	}
	public void setMsgDetail(String msgDetail) {
		this.msgDetail = msgDetail;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	

}
