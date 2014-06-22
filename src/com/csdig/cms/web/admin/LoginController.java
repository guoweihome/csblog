package com.csdig.cms.web.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.model.CmsUser;
import com.csdig.cms.service.LoginService;
import com.csdig.cms.web.AdminContextInterceptor;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = ConstantDefine.AdminUrl.LOGIN, method = { RequestMethod.GET })
	public String getFirstPage(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(ConstantDefine.AdminUrl.BASE + "/main")
	public String main() throws Exception {
		return "main";
	}
	
	@RequestMapping(ConstantDefine.AdminUrl.BASE + "/welcome")
	public String welcome() throws Exception {
		return "welcome";
	}

	@RequestMapping(value = "/cms_admin/sendMessage")
	@ResponseBody
	public Map<String, String> sendMessage(String telNum) throws Exception {
		loginService.generatePassword(telNum);
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "200");
		return map;
	}

	@RequestMapping(value = "/cms_admin/dologin")
	@ResponseBody
	public Map<String, String> doLogin(HttpServletRequest request, String telNum, String pwd) throws Exception {
		CmsUser user = loginService.doLogin(telNum, pwd);
		request.getSession().setAttribute(AdminContextInterceptor.SESSION_USER_INFO, user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("STATUS", "200");
		return map;
	}

	@RequestMapping(value = ConstantDefine.AdminUrl.LOGOUT, method = { RequestMethod.GET })
	public String logout(HttpServletRequest request) throws Exception {
		CmsUser user = (CmsUser) request.getSession().getAttribute(AdminContextInterceptor.SESSION_USER_INFO);
		if (user != null && user.getTelNum() != null) {
			loginService.invalidatePwd(user);
		}
		request.getSession().invalidate();
		return "redirect:" + ConstantDefine.AdminUrl.LOGIN;
	}

}
