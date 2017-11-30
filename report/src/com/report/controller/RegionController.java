package com.report.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.report.bean.Region;
import com.report.service.IMsgService;
import com.report.service.IRegionService;
import com.report.utils.MakeWordUtil;
/**
 * 实验区申请 Controller
 */
@Controller
public class RegionController {
	
	@Resource
	IRegionService regionService;
	
	@Resource
	IMsgService msgService;
	
	/**
	 * 查询当前实验区申请表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectRegion")
	@ResponseBody
	public String selectRegion(HttpServletRequest request) throws Exception{
		HashMap<String, Object> map = regionService.selectRegion(request);
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 保存
	 * @param request
	 * @param region
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveRegion")
	@ResponseBody
	public String saveRegion(HttpServletRequest request,Region region) throws Exception{
		JSONObject result = regionService.saveOrUpdateRegion(request, region);
		
		if("1".equals(region.getStatus())){ //提交发消息
			String msgDetail = "您有一份《实验区申报单》待审核，请前往“申报实验区 > 待审核列表”查看！";
			try {
				msgService.sendMsg("admin", msgDetail);
			} catch (Exception e) {
				System.out.println("消息发送失败");
			}
		}
		
		return result.toString();
		
	}
	
	/**
	 * 更新状态
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateRegionStatus")
	@ResponseBody
	public String updateRegionStatus(HttpServletRequest request) throws Exception{
		JSONObject result = regionService.updateRegionStatus(request);
		
		String status = request.getParameter("status");
		String msgDetail = "";
		if("2".equals(status)){ //不通过
			msgDetail = "您有一份《实验区申报单》审核不通过，请前往“申报实验区 > 审核不通过”查看！";
		}else if("3".equals(status)){ //通过
			msgDetail = "您有一份《实验区申报单》审核通过，请前往“申报实验区 > 已通过列表”查看！";
		}
		
		try {
			HashMap<String, Object> map = regionService.selectRegion(request);
			msgService.sendMsg((String)map.get("userName"), msgDetail);
		} catch (Exception e) {
			System.out.println("消息发送失败");
		}
		
		return result.toString();
	}
	
	/**
	 * 删除
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteRegion")
	@ResponseBody
	public String deleteRegion(HttpServletRequest request) throws Exception{
		JSONObject result = regionService.deleteRegion(request);
		return result.toString();
	}
	
	/**
	 * 列表查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectRegionList")
	@ResponseBody
	public String selectRegionList(HttpServletRequest request) throws Exception{
		JSONObject result = regionService.selectRegionList(request);
		return result.toString();
	}
	
	/**
	 * 列表查询 --管理员
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectRegionListAdmin")
	@ResponseBody
	public String selectRegionListAdmin(HttpServletRequest request) throws Exception{
		JSONObject result = regionService.selectRegionListAdmin(request);
		return result.toString();
	}
	
	/**
	 * 下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadRegion")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
    	String id = request.getParameter("id");
		
    	//文件保存路径
		String filePath=session.getServletContext().getRealPath(File.separator+"wordtemp"+File.separator);
		
		//文件名称
		String fileName="实验区申请表";
		//文件对应的模板名称
		String fileType="region.ftl";
		
		//获取数据
		Map<String, Object> dataMap = regionService.selectRegion(request);
		
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
