package com.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.report.bean.UserInfo;
import com.report.service.IUserInfoService;

/**
 * 用户信息控制器
 * @author Administrator
 *
 */
@Controller
public class UserInfoController {
	@Autowired
	IUserInfoService service;
	
	@RequestMapping(value="/checkUserNameBeforeRegister",method=RequestMethod.POST)
	@ResponseBody
	public String checkUserNameBeforeRegister(HttpServletRequest request) throws Exception{
		JSONObject result = new JSONObject();
		String userName = request.getParameter("userName");
		boolean status = service.chackUserIsRegisted(userName);
		if(status){
			result.put("valid", false);
		}else{
			result.put("valid", true); //可用
		}
		
		return result.toString();
		
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public String register(HttpServletRequest request) throws Exception{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String qq = request.getParameter("qq");
		String realName = request.getParameter("realName");
		String cid = request.getParameter("cid");
		String companyName = request.getParameter("companyName");
		String address = request.getParameter("address");
		String endTime = request.getParameter("endTime");
		UserInfo userInfo = new UserInfo(userName, password, question, answer, email, phoneNumber, qq, realName, cid, companyName, address, endTime);
		service.userRegister(userInfo);
		JSONObject result = new JSONObject();
		result.put("status", "ok");
		return result.toString();
		
	}
	
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	@ResponseBody
	public String userLogin(HttpServletRequest request) throws Exception{
		JSONObject result = new JSONObject();
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserInfo userInfo = service.userLogin(userName, password);
		if(userInfo != null){
			session.setAttribute("userInfo", userInfo);
			result.put("status", "ok");
		}else{
			result.put("status", "error");
		}
		return result.toString();
	}
	
	@RequestMapping(value="/loginOut",method=RequestMethod.POST)
	@ResponseBody
	public String loginOut(HttpServletRequest request) throws Exception{
		JSONObject result = new JSONObject();
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		result.put("status", "ok");
		return result.toString();
	}
	
	
	@RequestMapping(value="/authority",method=RequestMethod.POST)
	@ResponseBody
	public String authority(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		JSONObject result = new JSONObject();
		if("admin".equals(userInfo.getUserName())){
			result.put("isAdmin", "Y");
			result.put("realName", userInfo.getRealName());
		}else{
			result.put("isAdmin", "N");
			result.put("realName", userInfo.getRealName());
		}
		return result.toString();
	}
	
	/**
	 * 获取用户信息列表并分页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserInfos",method=RequestMethod.POST)
	@ResponseBody
	public String getUserInfos(HttpServletRequest request) throws Exception{
		String cnt = request.getParameter("cnt");
		int begin = 10*(Integer.parseInt(cnt)-1);
		int end = 10;
		String userName = request.getParameter("userName");
		String realName = request.getParameter("realName");
		JSONObject result = service.getUserInfoList(userName, realName, begin, end);
		return result.toString();
	}
	/**
	 * 主键获取用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserInfoDetail",method=RequestMethod.POST)
	@ResponseBody
	public String getUserInfoDetail(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		UserInfo userInfo = service.getUserInfoByPk(id);
		JSONObject userInfoObject = JSONObject.fromObject(userInfo);
		JSONObject result = new JSONObject();
		result.put("userInfo", userInfoObject);
		return result.toString();
		
	}
	
	@RequestMapping(value="/getQuestionByUserName",method=RequestMethod.POST)
	@ResponseBody
	public String getQuestionByUserName(HttpServletRequest request) throws Exception{
		String userName = request.getParameter("userName");
		String question = service.getQuestionByUserName(userName);
		JSONObject result = new JSONObject();
		if(question !=null){
			result.put("status", "ok");
			result.put("question", question);
		}else{
			result.put("status", "error");
		}
		return result.toString();
	}
	
	@RequestMapping(value="/chackAnswer",method=RequestMethod.POST)
	@ResponseBody
	public String chackAnswer(HttpServletRequest request) throws Exception{
		String userName = request.getParameter("userName");
		String answer = request.getParameter("answer");
		 UserInfo userInfo =  service.checkIsQuestionRigth(userName, answer);
		 JSONObject result = new JSONObject();
		 if(userInfo !=null){
			 result.put("status", "ok");
		 }else{
			 result.put("status", "error");
		 }
		 return result.toString();
	}
	
	@RequestMapping(value="/modifyPassword",method=RequestMethod.POST)
	@ResponseBody
	public String modifyPassword(HttpServletRequest request) throws Exception{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		service.modifyPassword(userName, password);
		JSONObject result = new JSONObject();
		result.put("status", "ok");
		return result.toString();
	}
	
	/**
	 * 获取当前自己的个人信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserInfoMyself",method=RequestMethod.POST)
	@ResponseBody
	public String getUserInfoMyself(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		JSONObject userInfoObject = JSONObject.fromObject(userInfo);
		return userInfoObject.toString();
	}
	
	/**
	 * 修改个人信息，并重新保存至session中
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateUserInfoMyself",method=RequestMethod.POST)
	@ResponseBody
	public String updateUserInfoMyself(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		String userName = userInfo.getUserName();
		String password = request.getParameter("password");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String qq = request.getParameter("qq");
		String realName = request.getParameter("realName");
		String cid = request.getParameter("cid");
		String companyName = request.getParameter("companyName");
		String address = request.getParameter("address");
		String endTime = request.getParameter("endTime");
		userInfo = new UserInfo(userName, password, question, answer, email, phoneNumber, qq, realName, cid, companyName, address, endTime);
		
		int i = service.updateUserMySelf(userInfo);
		JSONObject result = new JSONObject();
		if(i==1){ //成功
			result.put("status", "ok");
			session.setAttribute("userInfo", userInfo);
		}else{
			result.put("status", "error");
		}
		return result.toString();
	}
	
}
