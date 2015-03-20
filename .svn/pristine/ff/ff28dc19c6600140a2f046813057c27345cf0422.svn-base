//https://developers.google.com/web-toolkit/doc/latest/tutorial/appengine
package org.paulsultan.hw6;

import java.io.Serializable;

public class LoginInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean loggedIn = false;
    private String loginUrl;
    private String logoutUrl;
    private String emailAddress;
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

	public String getChannelToken() {
		return token;
	}
	public void setChannelToken(String token) {
		this.token = token;
	}
	
	@Override
	public boolean equals(Object obj) {
		LoginInfo test = (LoginInfo)obj;
		if (this.emailAddress.equals(test.emailAddress))
			return true;
		else
			return false;
	}
}
