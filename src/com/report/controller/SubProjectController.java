package com.report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.report.bean.SubProject;
import com.report.bean.SubProjectAchievements;
import com.report.bean.SubProjectParty;
import com.report.bean.UserInfo;
import com.report.service.IMsgService;
import com.report.service.ISubProjectService;
import com.report.utils.JStringUtil;
import com.report.utils.MakeWordUtil;

@Controller
public class SubProjectController {
	
	@Autowired
	ISubProjectService service;
	
	@Autowired
	IMsgService msgService;
	
	
	@RequestMapping("/saveSubProject")
	@ResponseBody
	public String saveSubProject(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		String userName = userInfo.getUserName();
		String ktmc = request.getParameter("ktmc");
		String ztc = request.getParameter("ztc");
		String fzrxm = request.getParameter("fzrxm");
		String xb = request.getParameter("xb");
		String mz = request.getParameter("mz");
		String csrq = request.getParameter("csrq");
		String xzzz = request.getParameter("xzzz");
		String zyzw = request.getParameter("zyzw");
		String zhxl = request.getParameter("zhxl");
		String ssxk = request.getParameter("ssxk");
		String szs = request.getParameter("szs");
		String gzdw = request.getParameter("gzdw");
		String dzyx = request.getParameter("dzyx");
		String txdz = request.getParameter("txdz");
		String yzbm = request.getParameter("yzbm");
		String lxdh_qh = request.getParameter("lxdh_qh");
		String lxdh_dw = request.getParameter("lxdh_dw");
		String lxdh_jt = request.getParameter("lxdh_jt");
		String lxdh_sj = request.getParameter("lxdh_sj");
		String[] yqcgs = request.getParameterValues("yqcg");
		String yqcg = "";
		if(yqcgs !=null && yqcgs.length>0){
			for (String string : yqcgs) {
				if(!"".equals(yqcg)){
					yqcg += "|"+string;
				}else{
					yqcg = string;
				}
			}
		}
		String jflyjse = request.getParameter("jflyjse");
		String yjwcsj = request.getParameter("yjwcsj");
		String ktlz = request.getParameter("ktlz");
		String sshwctjdlz = request.getParameter("sshwctjdlz");
		String status = request.getParameter("status");
		String year = request.getParameter("year");
		SubProject subProject = new SubProject(ktmc, ztc, fzrxm, xb, mz, csrq, xzzz, zyzw, zhxl, szs, ssxk, gzdw, dzyx, txdz, yzbm, lxdh_qh, lxdh_dw, lxdh_jt, lxdh_sj, yqcg, jflyjse, yjwcsj, ktlz, sshwctjdlz, status, userName, year);
		
		String[] partyXms = request.getParameterValues("partyXm");
		String[] partyXbs = request.getParameterValues("partyXb");
		String[] partyCsnys = request.getParameterValues("partyCsny");
		String[] partyZyzws = request.getParameterValues("partyZyzw");
		String[] partyYjzcs = request.getParameterValues("partyYjzc");
		String[] partyXls = request.getParameterValues("partyXl");
		String[] partyXws = request.getParameterValues("partyXw");
		String[] partyGzdws = request.getParameterValues("partyGzdw");
		List<SubProjectParty> subProjectParties = new ArrayList<>();
		for(int i =0;i<partyXms.length;i++){
			String partyXm = partyXms[i];
			String partyXb = partyXbs[i];
			String partyCsny = partyCsnys[i];
			String partyZyzw = partyZyzws[i];
			String partyYjzc = partyYjzcs[i];
			String partyXl = partyXls[i];
			String partyXw = partyXws[i];
			String partyGzdw = partyGzdws[i];
			SubProjectParty subProjectParty = new SubProjectParty(partyXm, partyXb, partyCsny, partyZyzw, partyYjzc, partyXl, partyXw, partyGzdw, i+1);
			if(!subProjectParty.isEnputy()){
				subProjectParties.add(subProjectParty);
			}
		}
		
		String[] achTypes = request.getParameterValues("achType");
		String[] achYjjds = request.getParameterValues("achYjjd");
		String[] achCgmcs = request.getParameterValues("achCgmc");
		String[] achCgxss = request.getParameterValues("achCgxs");
		String[] achCdrs = request.getParameterValues("achCdr");
		
		List<SubProjectAchievements> subProjectAchievements = new ArrayList<>();
		for(int i=0;i<achTypes.length;i++){
			String achType = achTypes[i];
			String achYjjd = achYjjds[i];
			String achCgmc = achCgmcs[i];
			String achCgxs = achCgxss[i];
			String achCdr = achCdrs[i];
			SubProjectAchievements achievement = new SubProjectAchievements(achType, i+1, achYjjd, achCgmc, achCgxs, achCdr);
			if(!achievement.isEnputy()){
				subProjectAchievements.add(achievement);
			}	
		}

		if(JStringUtil.isEmpty(id)){
			id = service.saveSubProject(subProject, subProjectAchievements, subProjectParties);
		}else{
			subProject.setId(id);
			service.updateSubProject(subProject, subProjectAchievements, subProjectParties);
		}
		if("1".equals(status)){
			//String msgDetail = "您有一条《"+ktmc+"》的待办！";
			String msgDetail = "您有一份《子课题申报单》待审核，请前往“申报子课题 > 待审核列表”查看！";
			try {
				msgService.sendMsg("admin", msgDetail);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("消息发送失败");
			}
			
		}
        JSONObject result = new JSONObject();
        result.put("status", "ok");
        result.put("id", id);
        result.put("msg", "成功");
		return result.toString();
		
	}
	
	@RequestMapping("/deleteSubProject")
	@ResponseBody
	public String deleteSubProject(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		service.deleteSubProject(id);
		JSONObject result = new JSONObject();
		result.put("status", "ok");
		return result.toString();
		
	}
	
	@RequestMapping("/getSubProjectList")
	@ResponseBody
	public String getSubProjectList(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		String userName = userInfo.getUserName();
		String cnt = request.getParameter("cnt");
		String status = request.getParameter("status");
		String ktmc = request.getParameter("ktmc");
		String ztc = request.getParameter("ztc");
		int end = 10;
		int begin = 10 * (Integer.parseInt(cnt)-1);
		List<SubProject> subProjects = service.getSubProjectList(userName, status, ktmc, ztc, begin, end);
		int count = service.selectCountByUserId(userName, status, ktmc, ztc);
		JSONObject result = new JSONObject();
		result.put("status", "ok");
		result.put("cnt", count);
		JSONArray inner = JSONArray.fromObject(subProjects);
		result.put("subProjects", inner);
		return result.toString();
		
		
	}
	
	@RequestMapping("/getSubProjectForAdmin")
	@ResponseBody
	public String getSubProjectForAdmin(HttpServletRequest request) throws Exception{
		String cnt = request.getParameter("cnt");
		String status = request.getParameter("status");
		String ktmc = request.getParameter("ktmc");
		String ztc = request.getParameter("ztc");
		String realName = request.getParameter("realName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int end = 10;
		int begin = 10 * (Integer.parseInt(cnt)-1);
		List<SubProject> subProjects = service.selectSubProjectForAdmin(ktmc, ztc, realName, startDate,endDate, status, begin, end);
		int count = service.selectCountForAdmin(ktmc, ztc, realName, startDate,endDate, status);
		JSONObject result = new JSONObject();
		result.put("status", "ok");
		result.put("cnt", count);
		JSONArray inner = JSONArray.fromObject(subProjects);
		result.put("subProjects", inner);
		return result.toString();
	}
	
	@RequestMapping("/getSubDetail")
	@ResponseBody
	public String getSubDetail(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		JSONObject result = service.getSubProjectDetail(id);
		return result.toString();
		
	}
	
	@RequestMapping("/changeStatus")
	@ResponseBody
	public String changeStatus(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		service.changeStatus(id, status);
		JSONObject detail = service.getSubProjectDetail(id);
		String userName = detail.getJSONObject("subProject").getString("userName");
		String ktmc = detail.getJSONObject("subProject").getString("ktmc");
		String msgDetail = "";
		if("2".equals(status)){
			//msgDetail = "您的《"+ktmc+"》子课题申请被驳回！";
			msgDetail = "您有一份《子课题申报单》审核不通过，请前往“申报子课题 > 审核不通过”查看！";
		}else if("3".equals(status)){
			//msgDetail = "您的《"+ktmc+"》子课题申请已经通过审核！";
			msgDetail = "您有一份《子课题申报单》审核通过，请前往“申报子课题 > 已通过列表”查看！";
		}
		try {
			msgService.sendMsg(userName, msgDetail);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("消息发送失败");
		}
		JSONObject result = new JSONObject();
		result.put("status", "ok");
		return result.toString();
	}
	
	/**
	 * 下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadSubProject")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
    	String id = request.getParameter("id");
		
    	//文件保存路径
		String filePath=session.getServletContext().getRealPath(File.separator+"wordtemp"+File.separator);
		
		//文件名称
		String fileName="子课题申请•评审表";
		//文件对应的模板名称
		String fileType="subProject.ftl";
		
		//获取数据
		Map<String, Object> dataMap = new HashMap<String, Object>();
		JSONObject result = service.getSubProjectDetail(id);
		Iterator it = result.keys();
		while (it.hasNext()){  
	         String key = String.valueOf(it.next());  
	         Object obj = result.get(key);  
	         dataMap.put(key, obj);  
	    }  
		
		MakeWordUtil.createWord(dataMap, fileType, filePath, fileName);
		
		response.setContentType("application/msword");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".doc");
		ServletOutputStream servletout = response.getOutputStream();
		File outFile = new File(filePath + File.separator + fileName+ ".doc");
		try {
			if (outFile.exists()) {
				FileInputStream in = new FileInputStream(outFile);
				byte bs[] = new byte[in.available()];
				int x = 0;
				int j = 0;
				while ((x = in.read()) != -1) {
					bs[j++] = (byte) x;
				}
				servletout.write(bs);
				in.close();
				MakeWordUtil.deleteFile(new File(filePath));
			}	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			servletout.flush();
			servletout.close();
			response.flushBuffer();
		}
	}

}
