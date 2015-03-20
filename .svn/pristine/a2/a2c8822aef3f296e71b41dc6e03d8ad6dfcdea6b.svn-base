package org.haoxiangzuo.hw6.client;

import java.io.Serializable;

public class UserInfos implements Serializable {
	  private static final long serialVersionUID=1L;
	
	  private boolean loggedIn = false;
	  private String loginUrl;
	  private String logoutUrl;
	  private String emailAddress;
	  private String nickname;
	  private String token;
	  public boolean isLoggedIn() {
	    return loggedIn;
	  }

	  public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	  }

	  public String getLoginUrl() {
	    return loginUrl;
	  }

	  public void setLoginUrl(String loginUrl) {
	    this.loginUrl = loginUrl;
	  }

	  public String getLogoutUrl() {
	    return logoutUrl;
	  }

	  public void setLogoutUrl(String logoutUrl) {
	    this.logoutUrl = logoutUrl;
	  }

	  public String getEmailAddress() {
	    return emailAddress;
	  }

	  public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	  }

	  public String getNickname() {
	    return nickname;
	  }


	  public void setNickname(String nickname) {
	    this.nickname = nickname;
	  }

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		UserInfos that = (UserInfos) obj;
		if (this.emailAddress.equals(that.emailAddress))
			return true;
		else
			return false;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	  
	}
