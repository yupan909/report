package com.report.bean;

/**
 * 子课题参与者实体
 * @author Administrator
 *
 */
public class SubProjectParty {
	
	private String id;
	private String pid;
	private String partyXm;
	private String partyXb;
	private String partyCsny;
	private String partyZyzw;
	private String partyYjzc;
	private String partyXl;
	private String partyXw;
	private String partyGzdw;
	private Integer partyRank;
	public SubProjectParty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubProjectParty(String partyXm, String partyXb, String partyCsny,
			String partyZyzw, String partyYjzc, String partyXl, String partyXw,
			String partyGzdw, Integer partyRank) {
		super();
		this.partyXm = partyXm;
		this.partyXb = partyXb;
		this.partyCsny = partyCsny;
		this.partyZyzw = partyZyzw;
		this.partyYjzc = partyYjzc;
		this.partyXl = partyXl;
		this.partyXw = partyXw;
		this.partyGzdw = partyGzdw;
		this.partyRank = partyRank;
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
	public String getPartyXm() {
		return partyXm;
	}
	public void setPartyXm(String partyXm) {
		this.partyXm = partyXm;
	}
	public String getPartyXb() {
		return partyXb;
	}
	public void setPartyXb(String partyXb) {
		this.partyXb = partyXb;
	}
	public String getPartyCsny() {
		return partyCsny;
	}
	public void setPartyCsny(String partyCsny) {
		this.partyCsny = partyCsny;
	}
	public String getPartyZyzw() {
		return partyZyzw;
	}
	public void setPartyZyzw(String partyZyzw) {
		this.partyZyzw = partyZyzw;
	}
	public String getPartyYjzc() {
		return partyYjzc;
	}
	public void setPartyYjzc(String partyYjzc) {
		this.partyYjzc = partyYjzc;
	}
	public String getPartyXl() {
		return partyXl;
	}
	public void setPartyXl(String partyXl) {
		this.partyXl = partyXl;
	}
	public String getPartyXw() {
		return partyXw;
	}
	public void setPartyXw(String partyXw) {
		this.partyXw = partyXw;
	}
	public String getPartyGzdw() {
		return partyGzdw;
	}
	public void setPartyGzdw(String partyGzdw) {
		this.partyGzdw = partyGzdw;
	}
	public Integer getPartyRank() {
		return partyRank;
	}
	public void setPartyRank(Integer partyRank) {
		this.partyRank = partyRank;
	}
	
	
	public boolean isEnputy(){
		if(("".equals(this.partyCsny) || this.partyCsny ==null)
			&& ("".equals(this.partyGzdw) || this.partyGzdw ==null)
			&& ("".equals(this.partyXb) || this.partyXb ==null)
			&& ("".equals(this.partyXl) || this.partyXl ==null)
			&& ("".equals(this.partyXm) || this.partyXm ==null)
			&& ("".equals(this.partyXw) || this.partyXw ==null)
			&& ("".equals(this.partyYjzc) || this.partyYjzc ==null)
			&& ("".equals(this.partyZyzw) || this.partyZyzw ==null)){
			
			return true;	
		}
		return false;
	}

}
