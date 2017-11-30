package com.report.bean;

/**
 * 用户实体
 * @author Administrator
 *
 */
public class UserInfo {
	/**
	 * 主键	
	 */
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码问题
     */
    private String question;
    /**
     * 问题回答
     */
    private String answer;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * qq
     */
    private String qq;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String cid;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 预计节课时间
     */
    private String endTime;
    
    public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	public UserInfo(String userName, String password, String question,
			String answer, String email, String phoneNumber, String qq,
			String realName, String cid, String companyName, String address,
			String endTime) {
		super();
		this.userName = userName;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.qq = qq;
		this.realName = realName;
		this.cid = cid;
		this.companyName = companyName;
		this.address = address;
		this.endTime = endTime;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
