package org.markanderson.hw6.server;

import org.markanderson.hw6.client.ManderLoginService;
import org.markanderson.hw6.helper.ManderChessUserSessionInfo;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ManderLoginServiceImpl extends RemoteServiceServlet implements
    ManderLoginService {

	private static final long serialVersionUID = 1L;

public ManderChessUserSessionInfo login(String requestUri) {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    ManderChessUserSessionInfo loginInfo = new ManderChessUserSessionInfo();

    if (user != null) {
      loginInfo.setLoggedIn(true);
      loginInfo.setEmailAddress(user.getEmail());
      loginInfo.setNickname(user.getNickname());
      loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
    } else {
      loginInfo.setLoggedIn(false);
      loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
    }
    return loginInfo;
  }
}