package org.chenji.hw7.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("game")
public interface GameService extends RemoteService {

	public Player connect(LoginInfo loginInfo); // connect and return token created by server
	public List<Match> getMatches(String clientId); // get current matches
	public void autoMatch(String clientId);
	public void invite(String clientId, String opponentId);
	public Match load(int matchId);
	public void delete(int matchId);
	public void updateState(int matchId, int turn, String state); // send new state to server
	public Player getPlayer(String playerId);
	public void sendEmails(int id, String emails);
	public String getEmails(int id);
}
