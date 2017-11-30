package com.report.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.report.bean.Region;

/**
 * 实验区dao
 * @author Administrator
 *
 */
public interface IRegionDao {
	
	/**
	 * 新增实验区申请单
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void insertRegion(Region region) throws Exception; 
	
	/**
	 * 更新实验区申请单
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public int updateRegion(Region region) throws Exception;
	
	/**
	 * 更新实验区申请单状态
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public int updateRegionStatus(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 根据主键获取当前实验区申请单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> selectRegionById(String id) throws Exception;
	
	/**
	 * 删除实验区申请单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteRegionById(String id) throws Exception;
	
	/**
	/**
	 * 根据用户名获取实验区并分页
	 * @param userName
	 * @param begin  起始页
	 * @param end 本页长度
	 * @return
	 * @throws Exception
	 */
	public List<Region> selectRegionByUserName(HashMap<String,Object> map) throws Exception;
	
	/**
	/**
	 * 根据用户名获取实验区总长度
	 * @param userName
	 * @param begin  起始页
	 * @param end 本页长度
	 * @return
	 * @throws Exception
	 */
	public Integer selectCountByRegion(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 根据用户名获取实验区并分页 --管理员
	 * @param userName
	 * @param begin  起始页
	 * @param end 本页长度
	 * @return
	 * @throws Exception
	 */
	public List<Region> selectRegionForAdmin(HashMap<String,Object> map) throws Exception;
	
	/**
	 * 根据用户名获取实验区总长度--管理员
	 * @param userName
	 * @param begin  起始页
	 * @param end 本页长度
	 * @return
	 * @throws Exception
	 */
	public Integer selectCountForRegionAdmin(HashMap<String,Object> map) throws Exception;
	

}
