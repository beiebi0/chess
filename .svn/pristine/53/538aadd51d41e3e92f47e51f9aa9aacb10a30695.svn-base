package org.paulsultan.hw6.client;

import java.util.ArrayList;

import org.paulsultan.hw6.LoginInfo;
import org.paulsultan.hw6.ServerOffException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface ChessService extends RemoteService {
	LoginInfo login(String href);
	ArrayList<String> getMatches(String currentUserEmail);
	ArrayList<String> getOtherPlayers(LoginInfo currentUser);
	Double getRank(String email);

	String loadMatch(String currentUserEmail, String matchId) throws ServerOffException;
	
	String autoMatch(LoginInfo currentUser) throws ServerOffException;
	String startMatchWith(String newEmail, String currentUserEmail) throws ServerOffException;
	void deleteMatch(String cureUserEmail, String deleteGameId, Boolean isInGame) throws ServerOffException;

	String makeMove(String state, Long id) throws ServerOffException;	
	
	void setServerStatus(boolean status);	

}
