package org.haoxiangzuo.hw6.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChessServiceAsync {
	void login(String x, AsyncCallback<UserInfos> callback);
	void autoMatch(UserInfos currentUser, AsyncCallback<String> callback);
	void makeMove(String move, AsyncCallback<Void> callback);
	void findOpponent(String email, UserInfos currentUser, AsyncCallback<String> callback);
	void getMatches(UserInfos currentUser, AsyncCallback<ArrayList<String>> callback);
	void loadMatch(String infos, AsyncCallback<String> callback);
	void deleteMatch(String info, AsyncCallback<Void> callback);
	void showRank(String email, AsyncCallback<String> callback);
	void deleteUserFromQueue(String id, AsyncCallback<Void> callback);
	void fakeLogin(String x, AsyncCallback<UserInfos> callback);
//	void saveContacts(String x, ArrayList<String> contacts, AsyncCallback<Void> callback);
//	void findContacts(String x, AsyncCallback<String> callback);
}
