package org.alexanderoskotsky.hw6;

import java.util.List;

import org.alexanderoskotsky.hw7.MatchInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface ChessService extends RemoteService {

	PlayerInfo connect(String myEmail);

	void sendState(String myEmail, String matchId, String state, int turn);

	MatchInfo createMatch(String myEmail, String email);

	List<MatchInfo> getMatches(String myEmail);

	MatchInfo getMatchState(String myEmail, String matchId);
	
	void deleteMatch(String myEmail, String matchId);
	
	void autoMatch(String myEmail);
	
	PlayerInfo getPlayerInfo(String myEmail);
}
