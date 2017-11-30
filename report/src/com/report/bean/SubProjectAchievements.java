package com.report.bean;
/**
 * 子课题申请预期成就
 * @author Administrator
 *
 */
public class SubProjectAchievements {
	private String id;
	private String pid;
	private String achType;
	private Integer achRank;
	private String achYjjd;
	private String achCgmc;
	private String achCgxs;
	private String achCdr;
	
	public SubProjectAchievements() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SubProjectAchievements(String achType, Integer achRank,
			String achYjjd, String achCgmc, String achCgxs, String achCdr) {
		super();
		this.achType = achType;
		this.achRank = achRank;
		this.achYjjd = achYjjd;
		this.achCgmc = achCgmc;
		this.achCgxs = achCgxs;
		this.achCdr = achCdr;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getAchType() {
		return achType;
	}
	public void setAchType(String achType) {
		this.achType = achType;
	}
	public Integer getAchRank() {
		return achRank;
	}
	public void setAchRank(Integer achRank) {
		this.achRank = achRank;
	}
	public String getAchYjjd() {
		return achYjjd;
	}
	public void setAchYjjd(String achYjjd) {
		this.achYjjd = achYjjd;
	}
	public String getAchCgmc() {
		return achCgmc;
	}
	public void setAchCgmc(String achCgmc) {
		this.achCgmc = achCgmc;
	}
	public String getAchCgxs() {
		return achCgxs;
	}
	public void setAchCgxs(String achCgxs) {
		this.achCgxs = achCgxs;
	}
	public String getAchCdr() {
		return achCdr;
	}
	public void setAchCdr(String achCdr) {
		this.achCdr = achCdr;
	}
	
	public boolean isEnputy(){

		if(("".equals(this.achYjjd) || this.achYjjd ==null)
			&& ("".equals(this.achCdr) || this.achCdr ==null)
			&& ("".equals(this.achCgmc) || this.achCgmc ==null)
			&& ("".equals(this.achCgxs) || this.achCgxs ==null)){
			return true;	
		}

		return false;
	} 
	


}
