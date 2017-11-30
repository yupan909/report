package com.report.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.report.bean.SubProject;
import com.report.bean.SubProjectAchievements;
import com.report.bean.SubProjectParty;

/**
 * 子课题 服务层
 * @author Administrator
 *
 */
public interface ISubProjectService {
	
	/**
	 * 根据用户信息获取子课题列表数据
	 * @param userName  用户名
	 * @param status   状态，草稿箱（0）？待提交（1）？待通过（2）？已通过（3）
	 * @return
	 */
	public List<SubProject> getSubProjectList(String userName,String status,String ktmc, 
			String ztc,int begin,int end) throws Exception;
	
	/**
	 * 根据条件获取单据的总条数
	 * @param userName
	 * @param status
	 * @param ktmc
	 * @param ztc
	 * @return
	 * @throws Exception
	 */
	public int selectCountByUserId(String userName,String status,String ktmc, String ztc) throws Exception;
	
	/**
	 * 修改单据状态
	 * @param id
	 * @param status
	 * @throws Exception
	 */
	public void changeStatus(String id,String status) throws Exception;
	/**
	 * 获取子课题详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JSONObject getSubProjectDetail(String id) throws Exception;
	
	/**
	 * 更新子课题信息
	 * @param subProject
	 * @param subProjectAchievements
	 * @param subProjectParties
	 * @throws Exception
	 */
	public void updateSubProject(SubProject subProject ,List<SubProjectAchievements> subProjectAchievements,List<SubProjectParty> subProjectParties) throws Exception;
	
	/**
	 * 保存子课题详细信息
	 */
	public String saveSubProject(SubProject subProject ,List<SubProjectAchievements> subProjectAchievements,List<SubProjectParty> subProjectParties) throws Exception;
	
	public void deleteSubProject(String id) throws Exception;
	
	/**
	 * 管理员获取子课题信息
	 * @param ktmc
	 * @param ztc
	 * @param realName
	 * @param createTime
	 * @param status
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<SubProject> selectSubProjectForAdmin(String ktmc, String ztc,String realName, String startDate,String endDate, String status, int begin,int end) throws Exception;
	
	/**
	 * 管理员获取子课题的条数
	 * @param ktmc
	 * @param ztc
	 * @param realName
	 * @param createTime
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public Integer selectCountForAdmin(String ktmc, String ztc,String realName, String startDate,String endDate, String status) throws Exception;

}
