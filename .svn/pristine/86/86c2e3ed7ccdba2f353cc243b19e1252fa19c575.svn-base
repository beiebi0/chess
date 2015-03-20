package org.alexanderoskotsky.hw6;

import java.util.List;

import org.alexanderoskotsky.hw7.MatchInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChessServiceAsync {

	void connect(String myEmail, AsyncCallback<PlayerInfo> callback);

	void sendState(String myEmail, String matchId, String state, int turnNumber,
			AsyncCallback<Void> callback);

	void createMatch(String myEmail, String email, AsyncCallback<MatchInfo> callback);

	void getMatches(String myEmail, AsyncCallback<List<MatchInfo>> callback);

	void getMatchState(String myEmail, String matchId, AsyncCallback<MatchInfo> callback);

	void deleteMatch(String myEmail, String matchId, AsyncCallback<Void> callback);

	void autoMatch(String myEmail, AsyncCallback<Void> callback);

	void getPlayerInfo(String myEmail, AsyncCallback<PlayerInfo> playerInfo);
}
