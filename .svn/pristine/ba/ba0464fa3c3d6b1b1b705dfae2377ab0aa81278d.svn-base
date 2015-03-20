package org.chenji.hw6.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("game")
public interface GameService extends RemoteService {

	public Player connect(LoginInfo loginInfo); // connect and return token created by server
	public void updateState(String clientId, String state); // send new state to server
}
