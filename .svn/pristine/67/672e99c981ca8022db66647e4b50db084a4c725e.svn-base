package org.chenji.hw7.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chenji.hw7.client.GameService;
import org.chenji.hw7.client.LoginInfo;
import org.chenji.hw7.client.Match;
import org.chenji.hw7.client.Player;
import org.chenji.hw7.StateSerializer;
import org.chenji.hw8.Glicko_Eval;
import org.shared.chess.State;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GameServiceImpl extends RemoteServiceServlet implements GameService {

	/**
   * 
   */
  private static final long serialVersionUID = -1138122470165028454L;
  private ChannelService channelService = ChannelServiceFactory.getChannelService();
	private Map<String, Player> players = new HashMap<String, Player>();
	private Map<String, List<Match>> matchList = new HashMap<String, List<Match>>();
  private List<Match> matches = new ArrayList<Match>();
  private Map<Integer, String> contactEmails = new HashMap<Integer, String>();
  private Match autoMatch = null;
  public static int TotalNumberOfMatches = 1;
	@Override
	public Player connect(LoginInfo loginInfo) {
	  //State state = new State();
		Player player = new Player();
		player.setPlayerId(loginInfo.getEmailAddress());
		player.setNickname(loginInfo.getNickname());
		String token = channelService.createChannel(player.getPlayerId());
		player.setToken(token);
		players.put(player.getPlayerId(), player);
		return player;
	}
	@Override 
	public List<Match> getMatches(String clientId) {
	  return matchList.get(clientId);
	}
  @Override
  public void autoMatch(String clientId) {
    if (autoMatch == null) {
      autoMatch = new Match(TotalNumberOfMatches++);
      autoMatch.setPlayer(0, clientId);
    } else {
      if (autoMatch.getPlayer(0).equals(clientId))
        return;
      autoMatch.setPlayer(1, clientId);
      setTitle(autoMatch);
      updateMatchLists(autoMatch);
      channelService.sendMessage(new ChannelMessage(autoMatch.getPlayer(0), "newMatch:" + autoMatch.toString()));
      channelService.sendMessage(new ChannelMessage(autoMatch.getPlayer(1), "newMatch:" + autoMatch.toString()));
      autoMatch = null;
    }
  }
  private void setTitle(Match match) {
    Player player1 = players.get(match.getPlayer(0));
    Player player2 = players.get(match.getPlayer(1));
    match.setTitle("White: " + player1.getNickname() + "(" + player1.getRating() + ") vs Black: " + player2.getNickname() + "(" + player2.getRating() + ")");
  }
  private void updateMatchLists(Match match) {
    String clientId = match.getPlayer(0);
    String opponentId = match.getPlayer(1);
    matches.add(match);
    if (!matchList.containsKey(clientId)) {
      matchList.put(clientId, new ArrayList<Match>());
    }
    if (!matchList.containsKey(opponentId)) {
      matchList.put(opponentId, new ArrayList<Match>());
    }
    matchList.get(clientId).add(match);
    matchList.get(opponentId).add(match);
  }
	@Override
	public void invite(String clientId, String opponentId) {
	  Match match = new Match(TotalNumberOfMatches++, players.get(clientId), players.get(opponentId));
	  updateMatchLists(match);
	  if (players.containsKey(opponentId)) {
	    channelService.sendMessage(new ChannelMessage(opponentId, "newMatch:" + match.toString()));
	  }
	  channelService.sendMessage(new ChannelMessage(clientId, "newMatch:" + match.toString()));
	}
	@Override
	public void delete(int matchId) {
	  for (Match match : matches) {
	    if (match.getMatchId() == matchId) {
	      match.setDeleted(true);
	      channelService.sendMessage(new ChannelMessage(match.getPlayer(0), "deleteMatch:" + match.getMatchId()));
        channelService.sendMessage(new ChannelMessage(match.getPlayer(1), "deleteMatch:" + match.getMatchId()));
	    }
	  }
	}
	@Override
  public Match load(int matchId) {
    for (Match match : matches) {
      if (match.getMatchId() == matchId) {
        return match;
      }
    }
    return null;
  }
	@Override
	public void updateState(int matchId, int turn, String state) {
	  State curState = StateSerializer.getStateFromString(state);
	  for (Match match : matches) {
      if (match.getMatchId() == matchId) {
        match.setState(state);
        match.setTurn(turn);
        if (curState.getGameResult() != null) {
          Glicko_Eval.rate(players.get(match.getPlayer(0)), players.get(match.getPlayer(1)), curState);
        }
        channelService.sendMessage(new ChannelMessage(match.getPlayer(0), "updateMatch:" + match.toString()));
        channelService.sendMessage(new ChannelMessage(match.getPlayer(1), "updateMatch:" + match.toString()));
        return;
      }
    }
	}
	@Override
	public Player getPlayer(String playerId) {
	  return players.get(playerId);
	}
	@Override
	public void sendEmails(int id, String emails) {
	  contactEmails.put(id, emails);
	}
	
	@Override
	public String getEmails(int id) {
	  return contactEmails.get(id);
	}
	
	public static void addHeadersForCORS(HttpServletRequest req, HttpServletResponse resp) {
    resp.setHeader("Access-Control-Allow-Methods", "POST"); // "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS");
    resp.setHeader("Access-Control-Allow-Origin", "*");
    resp.setHeader("Access-Control-Allow-Headers", "X-GWT-Module-Base, X-GWT-Permutation, Content-Type"); 
  }

  @Override
  protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
    addHeadersForCORS(req, resp);
  }

  @Override
  protected void onAfterResponseSerialized(String serializedResponse) {
    super.onAfterResponseSerialized(serializedResponse);
    addHeadersForCORS(getThreadLocalRequest(), getThreadLocalResponse());
  }
}
