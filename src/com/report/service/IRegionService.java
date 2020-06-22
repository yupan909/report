package com.report.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.report.bean.Region;

public interface IRegionService {
	
	/**
	 * 查询当前的实验区申请单内容
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> selectRegion(HttpServletRequest request) throws Exception;
	
	/**
	 * 保存实验区申请单
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public JSONObject saveOrUpdateRegion(HttpServletRequest request, Region region) throws Exception;
	
	/**
	 * 删除实验区申请单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject deleteRegion(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询实验区申请单列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject selectRegionList(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询实验区申请单列表 --管理员
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject selectRegionListAdmin(HttpServletRequest request) throws Exception;
	
	/**
	 * 修改实验区申请单状态
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject updateRegionStatus(HttpServletRequest request) throws Exception;
	
}
