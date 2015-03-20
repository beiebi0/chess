package org.shihweihuang.hw6.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shihweihuang.hw6.client.LoginInfo;
import org.shihweihuang.hw6.client.LoginService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.com.google.common.math.IntMath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements
    LoginService {

	public static void addHeadersForCORS(HttpServletRequest req, HttpServletResponse resp) {
    resp.setHeader("Access-Control-Allow-Methods", "POST"); // "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS");
    resp.setHeader("Access-Control-Allow-Origin", "*");
    resp.setHeader("Access-Control-Allow-Headers", "X-GWT-Module-Base, X-GWT-Permutation, Content-Type"); 
  }

  @Override
  protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
    addHeadersForCORS(req, resp);
  }

  @Override
  protected void onAfterResponseSerialized(String serializedResponse) {
    super.onAfterResponseSerialized(serializedResponse);
    addHeadersForCORS(getThreadLocalRequest(), getThreadLocalResponse());
  }
	
	
	
  public LoginInfo login(String requestUri) {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    LoginInfo loginInfo = new LoginInfo();

    if (user != null) {
      loginInfo.setLoggedIn(true);
      loginInfo.setEmailAddress(user.getEmail());
      loginInfo.setNickname(user.getNickname());
      loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
      loginInfo.setAppendix((int)(Math.random()*10000000) + "");
    } else {
      loginInfo.setLoggedIn(false);
      loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
    }
    return loginInfo;
  }

}
