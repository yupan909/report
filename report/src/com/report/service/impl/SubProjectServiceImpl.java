package com.report.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.report.bean.SubProject;
import com.report.bean.SubProjectAchievements;
import com.report.bean.SubProjectParty;
import com.report.dao.ISubProjectDao;
import com.report.service.ISubProjectService;

@Service
public class SubProjectServiceImpl implements ISubProjectService {
	
    @Autowired
	ISubProjectDao dao;
	
	@Override
	public List<SubProject> getSubProjectList(String userName,String status,String ktmc, 
			String ztc,int begin,int end)
			throws Exception {
		// TODO Auto-generated method stub
		List<SubProject> result = dao.selectSubProjectByUserName(userName, status, ktmc, ztc, begin, end);
		return result;
	}

	@Override
	public void changeStatus(String id, String status) throws Exception {
		// TODO Auto-generated method stub
		dao.updateStaus(id, status);

	}

	@Override
	public JSONObject getSubProjectDetail(String id) throws Exception {
		// TODO Auto-generated method stub
		SubProject subProject = dao.selectSubProjectById(id);
		List<SubProjectParty> subProjectParties = dao.selectSubProjectPartyByPid(id);
		List<SubProjectAchievements> subProjectAchievementsExpect = dao.selectSubProjectAchievementsByPid(id, "1");
		List<SubProjectAchievements> subProjectAchievementsReal = dao.selectSubProjectAchievementsByPid(id, "2");
		JSONObject result = new JSONObject();
		JSONObject subProjectInfo = JSONObject.fromObject(subProject);
		JSONArray partiesInfo = JSONArray.fromObject(subProjectParties);
		JSONArray expectAchievements = JSONArray.fromObject(subProjectAchievementsExpect);
		JSONArray realAchievements = JSONArray.fromObject(subProjectAchievementsReal);
		result.put("subProject", subProjectInfo);
		result.put("partiesInfo", partiesInfo);
		result.put("expectAchievements", expectAchievements);
		result.put("realAchievements", realAchievements);
		return result;
	}
    @Transactional(rollbackFor = Exception.class)
	@Override
	public String saveSubProject(SubProject subProject,
			List<SubProjectAchievements> subProjectAchievements,
			List<SubProjectParty> subProjectParties) throws Exception {
		// TODO Auto-generated method stub
    	UUID uuid = UUID.randomUUID();
    	String id = uuid.toString().replace("-", "");
    	subProject.setId(id);
    	dao.insertSubProject(subProject);
    	for (SubProjectParty subProjectParty : subProjectParties) {
    		subProjectParty.setPid(id);
    		dao.insertSubProjectParty(subProjectParty);
		}
    	for (SubProjectAchievements subProjectAchievement : subProjectAchievements) {
    		subProjectAchievement.setPid(id);
    		dao.insertSubProjectAchievements(subProjectAchievement);
		}
    	return id;
	}

    @Transactional(rollbackFor = Exception.class)
	@Override
	public void updateSubProject(SubProject subProject,
			List<SubProjectAchievements> subProjectAchievements,
			List<SubProjectParty> subProjectParties) throws Exception {
		// TODO Auto-generated method stub
    	String pid = subProject.getId();
    	dao.updateSubProject(subProject);
    	dao.deleteSubProjectAchievementsByPid(pid);
    	dao.deleteSubProjectPartyByPid(pid);
    	for (SubProjectParty subProjectParty : subProjectParties) {
    		subProjectParty.setPid(pid);
    		dao.insertSubProjectParty(subProjectParty);
		}
    	
    	for (SubProjectAchievements subProjectAchievement : subProjectAchievements) {
    		subProjectAchievement.setPid(pid);
    		dao.insertSubProjectAchievements(subProjectAchievement);
		}
	}

	@Override
	public int selectCountByUserId(String userName, String status, String ktmc,
			String ztc) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCountByUserId(userName, status, ktmc, ztc);
	}

	@Override
	public List<SubProject> selectSubProjectForAdmin(String ktmc, String ztc,
			String realName, String startDate,String endDate, String status, int begin,
			int end) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectSubProjectForAdmin(ktmc, ztc, realName, startDate,endDate, status, begin, end);
	}

	@Override
	public Integer selectCountForAdmin(String ktmc, String ztc,
			String realName, String startDate,String endDate, String status) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCountForAdmin(ktmc, ztc, realName, startDate,endDate, status);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteSubProject(String id) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteSubProjectById(id);
		dao.deleteSubProjectAchievementsByPid(id);
		dao.deleteSubProjectPartyByPid(id);
		
	}

}
