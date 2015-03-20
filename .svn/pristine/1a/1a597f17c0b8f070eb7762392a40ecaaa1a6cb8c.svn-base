package org.simongellis.hw6.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MultiplayerServiceAsync {
	void connectUser(AsyncCallback<String> callback);
	void getLoginStatus(String href, AsyncCallback<String> callback);
	void loadAllMatches(AsyncCallback<String[]> callback);
	void aiMatch(AsyncCallback<Void> callback);
	void autoMatch(AsyncCallback<Void> callback);
	void cancelAutoMatch(AsyncCallback<Void> callback);
	void emailMatch(String email, AsyncCallback<Boolean> callback);
	void switchToMatch(Long matchId, AsyncCallback<String> callback);
	void deleteMatch(Long matchId, AsyncCallback<Void> callback);
	void makeMove(Long matchId, String serializedMove, AsyncCallback<String> callback);
	void makeAIMove(Long matchId, AsyncCallback<String> callback);
	
	void storeEmails(Long id, String emails, AsyncCallback<Void> callback);
	void getEmails(Long id, AsyncCallback<String> callback);
}
