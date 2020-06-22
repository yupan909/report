package com.report.dao;

import java.util.List;

import com.report.bean.SubProject;
import com.report.bean.SubProjectAchievements;
import com.report.bean.SubProjectParty;

/**
 * 子课题dao
 * @author Administrator
 *
 */
public interface ISubProjectDao {
	/**
	 * 保存子课题的单头
	 * @param subProject
	 * @throws Exception
	 */
	public void insertSubProject (SubProject subProject) throws Exception;
	
	/**
	 * 更新子课题单头
	 * @param subProject
	 * @throws Exception
	 */
	public void updateSubProject (SubProject subProject) throws Exception;
	
	/**
	 * 根据用户名获取子课题表头并分页
	 * @param userName
	 * @param begin  起始页
	 * @param end 本页长度
	 * @return
	 * @throws Exception
	 */
	public List<SubProject> selectSubProjectByUserName(String userName,String status,String ktmc, String ztc,int begin,int end) throws Exception;
	
	
	/**
	 * 根据查询条件获取单据总数
	 * @param userName
	 * @param status
	 * @param ktmc
	 * @param ztc
	 * @return
	 * @throws Exception
	 */
	public Integer selectCountByUserId(String userName,String status,String ktmc, String ztc) throws Exception;
	/**
	 * 根据主键获取子课题单头信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SubProject selectSubProjectById (String id) throws Exception;
	
	/**
	 * 更贴心子课题的单据状态（审批，提交，撤回等功能）
	 * @param id
	 * @param status
	 * @throws Exception
	 */
	public void updateStaus(String id,String status) throws Exception;
	
	/**
	 * 根据子课题的主键删除子课题单头
	 * @param id
	 * @throws Exception
	 */
	public void deleteSubProjectById(String id) throws Exception;
	
	/**
	 * 保存子课题预期成就表体
	 * @param subProjectAchievements
	 * @throws Exception
	 */
	public void insertSubProjectAchievements(SubProjectAchievements subProjectAchievements) throws Exception;
	
	/**
	 * 
	 * 根据外键获取子课题预期成就表体
	 * @param pid
	 * @throws Exception
	 */
	public List<SubProjectAchievements> selectSubProjectAchievementsByPid(String pid,String achType)throws Exception;
	
	/**
	 * 根据外键删除表体数据
	 * @param pid
	 * @throws Exception
	 */
	public void deleteSubProjectAchievementsByPid (String pid) throws Exception;
	
	/**
	 * 插入子课题 参与者表体
	 * @param subProjectParty
	 * @throws Exception
	 */
	public void insertSubProjectParty(SubProjectParty subProjectParty) throws Exception;
	
	/**
	 * 根据外键获取子课题参与者信息
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<SubProjectParty> selectSubProjectPartyByPid (String pid) throws Exception;
	
	/**
	 * 根据外键删除子课题参与者信息
	 * @param pid
	 * @throws Exception
	 */
	public void deleteSubProjectPartyByPid(String pid) throws Exception;
	
	/**
	 * 管理员根据不用的状态获取子课题列表
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
	public List<SubProject> selectSubProjectForAdmin(String ktmc, String ztc,String realName,String startDate,String endDate,String status,int begin,int end) throws Exception;
	
	/**
	 * 管理员根据不通透的状态获取子课题列表的总条数
	 * @param ktmc
	 * @param ztc
	 * @param realName
	 * @param createTime
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public Integer selectCountForAdmin(String ktmc, String ztc,String realName,String startDate,String endDate,String status) throws Exception;

}
