package com.report.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.report.bean.Region;
import com.report.bean.UserInfo;
import com.report.common.ReportConstant;
import com.report.dao.IRegionDao;
import com.report.service.IRegionService;
import com.report.utils.JStringUtil;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Resource	
	private IRegionDao regionDao;
	
	@Override
	public HashMap<String, Object> selectRegion(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!JStringUtil.isEmpty(id)){
			map = regionDao.selectRegionById(id);
		}
		return map;
	}

	
	@Override
	public JSONObject saveOrUpdateRegion(HttpServletRequest request,
			Region region) throws Exception {
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		String userName = userInfo.getUserName();
		
		String id = request.getParameter("id");
		if(JStringUtil.isEmpty(id)){
			id = JStringUtil.creatId();
			region.setId(id);
			region.setCreatetime(JStringUtil.getNowDate());
			region.setStatus(ReportConstant.STATUS_CG);
			region.setUserName(userName);
			regionDao.insertRegion(region);
		}else{
			regionDao.updateRegion(region);
		}
		JSONObject result = new JSONObject();
		result.put("msg", "1");
		result.put("id", id);
		return result;
	}

	@Override
	public JSONObject deleteRegion(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");

		JSONObject result = new JSONObject();
		if(!JStringUtil.isEmpty(id)){
			int i = regionDao.deleteRegionById(id);
			if(i==1){
				result.put("msg", "1"); //删除成功
			}else{
				result.put("msg", "0");
			}
		}else{
			result.put("msg", "0");
		}
		return result;
	}

	@Override
	public JSONObject updateRegionStatus(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		String status = request.getParameter("status");

		JSONObject result = new JSONObject();
		if(!JStringUtil.isEmpty(id)){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("status", status);
			int i = regionDao.updateRegionStatus(map);
			if(i==1){
				result.put("msg", "1"); //删除成功
			}else{
				result.put("msg", "0");
			}
		}else{
			result.put("msg", "0");
		}
		return result;
	}
	
	@Override
	public JSONObject selectRegionList(HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		String userName = userInfo.getUserName();
		
		String cnt = request.getParameter("cnt"); //总记录数
		String status = request.getParameter("status");
		
		String sqdw = request.getParameter("sqdw"); //申请单位
		String lxr = request.getParameter("lxr"); //联系人
		String syqfzr = request.getParameter("syqfzr"); //实验区负责人
		String startDate = request.getParameter("startDate"); //开始时间
		String endDate = request.getParameter("endDate"); //结束时间

		int end = 10;  
		int begin = 10 * (Integer.parseInt(cnt)-1);
		
		JSONObject result = new JSONObject();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("status", status);
		map.put("begin", begin);
		map.put("end", end);
		if(!JStringUtil.isEmpty(sqdw)){
			map.put("sqdw", sqdw);
		}
		if(!JStringUtil.isEmpty(lxr)){
			map.put("lxr", lxr);
		}
		if(!JStringUtil.isEmpty(syqfzr)){
			map.put("syqfzr", syqfzr);
		}
		if(!JStringUtil.isEmpty(startDate)){
			map.put("startDate", startDate);
		}
		if(!JStringUtil.isEmpty(endDate)){
			map.put("endDate", endDate);
		}
		
		List<Region> list = regionDao.selectRegionByUserName(map);
		int count = regionDao.selectCountByRegion(map);
		
		result.put("status", "ok");
		result.put("cnt", count);
		result.put("list", JSONArray.fromObject(list));
		return result;
	}
	
	@Override
	public JSONObject selectRegionListAdmin(HttpServletRequest request)
			throws Exception {
		String cnt = request.getParameter("cnt"); //总记录数
		String status = request.getParameter("status");
		
		String sqdw = request.getParameter("sqdw"); //申请单位
		String lxr = request.getParameter("lxr"); //联系人
		String realName = request.getParameter("realName"); //申请人姓名
		String startDate = request.getParameter("startDate"); //开始时间
		String endDate = request.getParameter("endDate"); //结束时间
		
		int end = 10;  
		int begin = 10 * (Integer.parseInt(cnt)-1);
		
		JSONObject result = new JSONObject();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("begin", begin);
		map.put("end", end);
		if(!JStringUtil.isEmpty(sqdw)){
			map.put("sqdw", sqdw);
		}
		if(!JStringUtil.isEmpty(lxr)){
			map.put("lxr", lxr);
		}
		if(!JStringUtil.isEmpty(realName)){
			map.put("realName", realName);
		}
		if(!JStringUtil.isEmpty(startDate)){
			map.put("startDate", startDate);
		}
		if(!JStringUtil.isEmpty(endDate)){
			map.put("endDate", endDate);
		}
		
		List<Region> list = regionDao.selectRegionForAdmin(map);
		int count = regionDao.selectCountForRegionAdmin(map);
		
		result.put("status", "ok");
		result.put("cnt", count);
		result.put("list", JSONArray.fromObject(list));
		return result;
	}
	
}
