package org.paulsultan.hw6.client;

import java.util.ArrayList;

import org.paulsultan.hw6.LoginInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChessServiceAsync {
	void login(String x, AsyncCallback<LoginInfo> callback);
	void getMatches(String currentUserEmail, AsyncCallback<ArrayList<String>> callback);
	void getOtherPlayers(LoginInfo currentUser, AsyncCallback<ArrayList<String>> callback);
	void getRank(String email, AsyncCallback<Double> callback);

	void loadMatch(String currentUserEmail, String matchId, AsyncCallback<String> callback);
	
	void autoMatch(LoginInfo currentUser, AsyncCallback<String> callback);
	void startMatchWith(String newEmail, String currentUserEmail, AsyncCallback<String> callback);
	void deleteMatch(String cureUserEmail, String deleteGameId, Boolean isInsGame, AsyncCallback<Void> callback);

	void makeMove(String state, Long id, AsyncCallback<String> callback);
	
	void setServerStatus(boolean status, AsyncCallback<Void> callback);
}
