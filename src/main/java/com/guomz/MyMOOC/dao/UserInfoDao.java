package com.guomz.MyMOOC.dao;

import com.guomz.MyMOOC.entity.UserInfo;

public interface UserInfoDao {

	public UserInfo queryUserInfoByOpenId(String openid);
	
	public int insertUserInfo(UserInfo userInfo);
}
