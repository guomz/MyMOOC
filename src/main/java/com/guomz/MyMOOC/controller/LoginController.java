package com.guomz.MyMOOC.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guomz.MyMOOC.dto.LoginResult;
import com.guomz.MyMOOC.dto.WechatInfo;
import com.guomz.MyMOOC.entity.UserInfo;
import com.guomz.MyMOOC.service.UserInfoService;
import com.guomz.MyMOOC.util.weixin.LoginUtil;

@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 用户登陆
	 * @param code
	 * @param userInfoStr
	 * @return
	 */
	@RequestMapping(value="/logincheck",method=RequestMethod.GET)
	public Map<String ,Object> loginCheck(@RequestParam(value="code",required=false)String code,@RequestParam(value="userInfoStr",required=false)String userInfoStr)
	{
		Map<String ,Object> modelMap=new HashMap<String ,Object>();
		ObjectMapper objectMapper=new ObjectMapper();
		UserInfo userInfo=null;
		WechatInfo wechatInfo=null;
		try {
			wechatInfo = objectMapper.readValue(userInfoStr, WechatInfo.class);
			LoginResult loginResult = LoginUtil.getLoginResult(code);
			userInfo = LoginUtil.generateUserInfo(wechatInfo, loginResult.getOpenid());
			userInfo = userInfoService.doLogin(userInfo);
			modelMap.put("success", true);
			modelMap.put("userId", userInfo.getUserId());
			modelMap.put("openid", userInfo.getOpenid());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		
		
		return modelMap;
	}
	
	
}
