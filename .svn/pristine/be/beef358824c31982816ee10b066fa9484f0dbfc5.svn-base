package org.simongellis.hw6.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("multiplayer")
public interface MultiplayerService extends RemoteService {
	String connectUser();
	String getLoginStatus(String href);
	String[] loadAllMatches();
	void aiMatch();
	void autoMatch();
	void cancelAutoMatch();
	Boolean emailMatch(String email);
	String switchToMatch(Long matchId);
	void deleteMatch(Long matchId);
	String makeMove(Long matchId, String serializedMove);
	String makeAIMove(Long matchId);
	
	void storeEmails(Long id, String emails);
	String getEmails(Long id);
}
