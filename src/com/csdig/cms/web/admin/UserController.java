package com.csdig.cms.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csdig.cms.common.ConstantDefine;
import com.csdig.cms.model.CmsUser;
import com.csdig.cms.service.UserService;

@Controller
@RequestMapping(value = ConstantDefine.AdminUrl.BASE + "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request) throws Exception {
		 return "user/list";
	}
	
	@RequestMapping(value = "getUsers")
	@ResponseBody
	public Map<String, Object> getUsers() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<CmsUser> list = userService.getUsers();
		result.put("total", list.size());
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value = "add")
	@ResponseBody
	public Map<String, Object> add(String telNum) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		CmsUser user = new CmsUser();
		user.setTelNum(telNum);
		userService.add(user);
		return result;
	}
	
	@RequestMapping(value = "edit")
	@ResponseBody
	public Map<String, Object> edit(int id,String newTelNum) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		CmsUser user = new CmsUser();
		user.setId(id);
		user.setTelNum(newTelNum);
		userService.update(user);
		return result;
	}
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(int id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		CmsUser user = new CmsUser();
		user.setId(id);
		userService.delete(user);
		return result;
	}
	
	
}
