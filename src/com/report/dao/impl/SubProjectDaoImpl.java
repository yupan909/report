package com.report.dao.impl;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.report.bean.SubProject;
import com.report.bean.SubProjectAchievements;
import com.report.bean.SubProjectParty;
import com.report.dao.ISubProjectDao;
import com.report.utils.JStringUtil;

@Service
public class SubProjectDaoImpl implements ISubProjectDao{

	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public void insertSubProject(SubProject subProject) throws Exception {
		// TODO Auto-generated method stub
		String createTime = JStringUtil.getNowDate();
		subProject.setCreateTime(createTime);
		sqlMapClient.insert("insertSubProject", subProject);
		
		
	}

	@Override
	public void updateSubProject(SubProject subProject) throws Exception {
		// TODO Auto-generated method stub
		sqlMapClient.update("updateSubProject",subProject);
		
	}

	@Override
	public List<SubProject> selectSubProjectByUserName(String userName,String status,String ktmc, 
			String ztc,int begin,int end) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("userName", userName);
		condition.put("status", status);
		condition.put("begin", begin);
		condition.put("end", end);
		if(!JStringUtil.isEmpty(ktmc)){
			condition.put("ktmc", ktmc);
		}
		if(!JStringUtil.isEmpty(ztc)){
			condition.put("ztc", ztc);
		}
		List<SubProject> result = sqlMapClient.queryForList("selectSubProjectByUserName", condition);
		return result;
	}
	@Override
	public Integer selectCountByUserId(String userName,String status,String ktmc, String ztc) throws Exception{
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("userName", userName);
		condition.put("status", status);
		if(!JStringUtil.isEmpty(ktmc)){
			condition.put("ktmc", ktmc);
		}
		if(!JStringUtil.isEmpty(ztc)){
			condition.put("ztc", ztc);
		}
		Integer count = (Integer) sqlMapClient.queryForObject("selectCountByUserId", condition);
		return count;
	}

	@Override
	public SubProject selectSubProjectById(String id) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("id", id);
		SubProject subProject = (SubProject) sqlMapClient.queryForObject("selectSubProjectById", condition);
		return subProject;
	}
	
	@Override
	public void updateStaus(String id,String status) throws Exception{
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("id", id);
		condition.put("status", status);
		sqlMapClient.update("updateStaus", condition);
	}

	@Override
	public void deleteSubProjectById(String id) throws Exception {
		// TODO Auto-generated method stub
		sqlMapClient.delete("deleteSubProjectById", id);
		
	}

	@Override
	public void insertSubProjectAchievements(
			SubProjectAchievements subProjectAchievements) throws Exception {
		// TODO Auto-generated method stub
		String id = creatId();
		subProjectAchievements.setId(id);
		sqlMapClient.insert("insertSubProjectAchievements", subProjectAchievements);
		
	}

	@Override
	public List<SubProjectAchievements> selectSubProjectAchievementsByPid(
			String pid, String achType) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("pid", pid);
		condition.put("achType", achType);
		List<SubProjectAchievements> result = sqlMapClient.queryForList("selectSubProjectAchievementsByPid", condition);
		return result;
	}

	@Override
	public void deleteSubProjectAchievementsByPid(String pid) throws Exception {
		// TODO Auto-generated method stub
		sqlMapClient.delete("deleteSubProjectAchievementsByPid", pid);
		
	}

	@Override
	public void insertSubProjectParty(SubProjectParty subProjectParty)
			throws Exception {
		// TODO Auto-generated method stub
		String id = creatId();
		subProjectParty.setId(id);
		sqlMapClient.insert("insertSubProjectParty", subProjectParty);
	}

	@Override
	public List<SubProjectParty> selectSubProjectPartyByPid(String pid)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("pid", pid);
		List<SubProjectParty> result = sqlMapClient.queryForList("selectSubProjectPartyByPid", condition);
		return result;
	}

	@Override
	public void deleteSubProjectPartyByPid(String pid) throws Exception {
		// TODO Auto-generated method stub
		sqlMapClient.delete("deleteSubProjectPartyByPid", pid);
		
	}
	
	
	private String creatId(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replace("-", "");
		return id;
		
	}

	@Override
	public List<SubProject> selectSubProjectForAdmin(String ktmc, String ztc,
			String realName,String startDate,String endDate, String status, int begin,
			int end) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("status", status);
		condition.put("begin", begin);
		condition.put("end", end);
		if(!JStringUtil.isEmpty(ktmc)){
			condition.put("ktmc", ktmc);
		}
		if(!JStringUtil.isEmpty(ztc)){
			condition.put("ztc", ztc);
		}
		if(!JStringUtil.isEmpty(realName)){
			condition.put("realName", realName);
		}
		if(!JStringUtil.isEmpty(startDate)){
			condition.put("startDate", startDate);
		}
		
		if(!JStringUtil.isEmpty(endDate)){
			condition.put("startDate", endDate);
		}
		List<SubProject> result = sqlMapClient.queryForList("selectSubProjectForAdmin", condition);
		return result;
	}

	@Override
	public Integer selectCountForAdmin(String ktmc, String ztc,
			String realName, String startDate,String endDate, String status) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("status", status);
		if(!JStringUtil.isEmpty(ktmc)){
			condition.put("ktmc", ktmc);
		}
		if(!JStringUtil.isEmpty(ztc)){
			condition.put("ztc", ztc);
		}
		if(!JStringUtil.isEmpty(realName)){
			condition.put("realName", realName);
		}
		if(!JStringUtil.isEmpty(startDate)){
			condition.put("startDate", startDate);
		}
		
		if(!JStringUtil.isEmpty(endDate)){
			condition.put("startDate", endDate);
		}
		Integer count = (Integer) sqlMapClient.queryForObject("selectCountForAdmin", condition);
		return count;
	}

}
