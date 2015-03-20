package org.haoxiangzuo.hw6.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface ChessService extends RemoteService {
	UserInfos login(String href);
	String autoMatch(UserInfos currentUser);
	void makeMove(String move);
	String findOpponent(String email, UserInfos currentUser);
	ArrayList<String> getMatches(UserInfos currentUser);
	String loadMatch(String infos);
	void deleteMatch(String info);
	String showRank(String email);
	void deleteUserFromQueue(String id);
	UserInfos fakeLogin(String email);
//	void saveContacts(String id, ArrayList<String> contacts);
//	String findContacts(String id);
}
