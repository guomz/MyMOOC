package com.guomz.MyMOOC.dto;

/**
 * 获取微信服务器返回的信息
 * @author 12587
 *
 */
public class LoginResult {

	private String openid;
	private String session_key;
	private String unionid;
	private String errcode;
	private String errmsg;
	@Override
	public String toString() {
		return "LoginResult [openid=" + openid + ", session_key=" + session_key + ", unionid=" + unionid + ", errcode="
				+ errcode + ", errmsg=" + errmsg + "]";
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
