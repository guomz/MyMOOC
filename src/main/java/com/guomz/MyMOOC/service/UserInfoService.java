package com.guomz.MyMOOC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.MyMOOC.dao.UserInfoDao;
import com.guomz.MyMOOC.entity.UserInfo;
/**
 * 通用，登陆使用
 * @author 12587
 *
 */
@Service
public class UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Transactional
	public UserInfo doLogin(UserInfo userInfo)
	{
		UserInfo temp = userInfoDao.queryUserInfoByOpenId(userInfo.getOpenid());
		if(temp==null)
		{
			int result = userInfoDao.insertUserInfo(userInfo);
			if(result<=0)
			{
				throw new RuntimeException("数据库操作失败");
			}
			return userInfo;
		}
		else
		{
			return temp;
		}
	}
	
	public UserInfo getUserInfoByOpenId(String openid)
	{
		return userInfoDao.queryUserInfoByOpenId(openid);
	}
}
