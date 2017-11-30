package com.report.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.report.exception.NotLoginException;
import com.report.bean.UserInfo;

/**
 * 登陆用户过滤器
 * @author hujunhui
 *
 */
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = Logger.getLogger(AuthInterceptor.class);
    	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
    	//从登陆session中获取用户信息 
	    UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
	    String uri = request.getRequestURI();
	    String contextPath = request.getContextPath();
	    String funcUri = uri.replaceFirst(contextPath, "");
	    if (funcUri.equals("/userLogin.do")|| funcUri.equals("/register.do")|| funcUri.equals("/loginOut.do")|| funcUri.equals("/checkUserNameBeforeRegister.do")
	    		||funcUri.equals("/getQuestionByUserName.do") ||funcUri.equals("/chackAnswer.do") ||funcUri.equals("/modifyPassword.do")){
	    	LOGGER.info("用户登录，不用拦截");
	    	return true;
	    }
	    //验证是否登录
		if (user != null) {
			LOGGER.info("用户发送请求，进行拦截");
		    //用户被禁用
//		    if (user.getUserView().getStatus() == ADMIN_STATUS.DISABLE.ordinal()){
//		    	throw new AuthException("用户被禁用");
//		    }
		    
//             contextPath = request.getContextPath();
//             funcUri = uri.replaceFirst(contextPath, "");
		   response.setHeader("sessionstatus","ok");//在响应头设置session状态
           return true;
//            if (expandAction.contains(funcUri)){
//                return true;
//            }else{
//            	 // 验证权限
//                if (user.authAccess(funcUri)) {
//                    return true;
//                }else{
//
//                    //没有权限
//                	throw new AuthException("权限不足");
//                }
//              
//            }

		}else{
			if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ //如果是ajax请求响应头会有x-requested-with  
				LOGGER.info("用户登录过期");
				response.setHeader("sessionstatus","timeout");//在响应头设置session状态
				return false;
	        }else{
	        	throw new NotLoginException("未登录");
	        }
		}

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    

    }

}
