package com.report.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.report.bean.Region;
import com.report.dao.IRegionDao;
import com.report.utils.JStringUtil;

@Repository
public class RegionDaoImpl implements IRegionDao {

	@Resource
	private SqlMapClient sqlMapClient;
	
	@Override
	public void insertRegion(Region region) throws Exception {
		sqlMapClient.insert("insertRegion", region);
	}

	@Override
	public int updateRegion(Region region) throws Exception {
		return sqlMapClient.update("updateRegion", region);
	}
	
	@Override
	public int updateRegionStatus(HashMap<String, Object> map) throws Exception {
		return sqlMapClient.update("updateRegionStatus", map);
	}

	@Override
	public HashMap<String, Object> selectRegionById(String id) throws Exception {
		HashMap<String, Object> map = (HashMap<String, Object>)sqlMapClient.queryForObject("selectRegionById", id);
		return map;
	}

	@Override
	public int deleteRegionById(String id) throws Exception {
		return sqlMapClient.delete("deleteRegionById", id);
	}

	@Override
	public List<Region> selectRegionByUserName(HashMap<String, Object> map)
			throws Exception {
		List<Region> list = sqlMapClient.queryForList("selectRegionByUserName", map);
		return list;
	}

	@Override
	public Integer selectCountByRegion(HashMap<String, Object> map)
			throws Exception {
		Integer count = (Integer)sqlMapClient.queryForObject("selectCountByRegion", map);
		return count;
	}

	@Override
	public List<Region> selectRegionForAdmin(HashMap<String, Object> map)
			throws Exception {
		List<Region> list = sqlMapClient.queryForList("selectRegionForAdmin", map);
		return list;
	}

	@Override
	public Integer selectCountForRegionAdmin(HashMap<String, Object> map)
			throws Exception {
		Integer count = (Integer)sqlMapClient.queryForObject("selectCountForRegionAdmin", map);
		return count;
	}

}
