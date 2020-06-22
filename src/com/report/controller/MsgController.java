package com.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.report.bean.UserInfo;
import com.report.service.IMsgService;

@Controller
public class MsgController {
	@Autowired
	IMsgService service;
	
	@RequestMapping("/getMsg")
	@ResponseBody
	public String getMsg(HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession();
		UserInfo userInfo =  (UserInfo) session.getAttribute("userInfo");
		String userName = userInfo.getUserName();
		String cnt = request.getParameter("cnt");
		int end = 10;
		int begin = end * (Integer.parseInt(cnt)-1);
		JSONObject result = service.getMsgs(userName, begin, end);
		return result.toString();
	}
	
	@RequestMapping("/setIsRead")
	@ResponseBody
	public String setIsRead(HttpServletRequest request)throws Exception{
		String id = request.getParameter("id");
		service.setIsRead(id);
		JSONObject result = new JSONObject();
		result.put("status", "ok");
		return result.toString();
	}
	
	
	@RequestMapping("/getMsgCount")
	@ResponseBody
	public String getMsgCount(HttpServletRequest request)throws Exception{
		
		HttpSession session = request.getSession();
		UserInfo userInfo =  (UserInfo) session.getAttribute("userInfo");
		String userName = userInfo.getUserName();
		int count = service.getMsgCount(userName); //获取当前用户未读的消息总条数
		
		JSONObject result = new JSONObject();
		result.put("count", count);
		return result.toString();
	}

}
