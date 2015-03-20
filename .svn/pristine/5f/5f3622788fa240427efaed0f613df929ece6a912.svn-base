package org.shihweihuang.hw6.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shihweihuang.hw2.StateChangerImpl;
import org.shihweihuang.hw3.StateParser;
import org.shihweihuang.hw5.MyCalender;
import org.shihweihuang.hw6.client.ChessPlatformService;
import org.shihweihuang.hw6.client.LoginInfo;
import org.shihweihuang.hw6.client.MoveSerializer;
import org.shihweihuang.hw7.Match;
import org.shihweihuang.hw7.Player;
import org.shihweihuang.hw8.server.ELOCalculator;
import org.shihweihuang.hw8.server.ResultType;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;

import com.googlecode.objectify.Key;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class ChessPlatformServiceImpl extends RemoteServiceServlet implements
		ChessPlatformService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		ObjectifyService.register(Player.class);
		ObjectifyService.register(Match.class);
	}
	// private static HashMap<String, String> tokenMap = new HashMap<String,
	// String>();
	//
	// @Override
	// public String getToken(String userId) {
	// if (tokenMap.get(userId) == null) {
	// tokenMap.put(userId, ChannelServiceFactory.getChannelService()
	// .createChannel(userId));
	// }
	// return tokenMap.get(userId);
	// }

	// String id;
	//
	// int i = 0;

	private List<LoginInfo> waitingList = new ArrayList<LoginInfo>();

	
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
	
	
	
	private void pushToClient(String userId, String message) {
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		channelService.sendMessage(new ChannelMessage(userId, message));
	}

	private void matchPlayer() {
		LoginInfo loginInfo1 = waitingList.get(0);
		LoginInfo loginInfo2 = waitingList.get(1);

		String matchID = loginInfo1.getEmailAddress() + "+"
				+ loginInfo2.getEmailAddress() + "+" + (int) (Math.random() * 10000000);
		Match match = new Match();
		match.setMatchID(matchID);
		State state = new State();
		match.setState(state.toString());
		match.setwPlayer(loginInfo1.getEmailAddress());
		match.setbPlayer(loginInfo2.getEmailAddress());
		Key<Player> player1Key = Key.create(Player.class,
				loginInfo1.getEmailAddress());
		Player player1 = ofy().load().key(player1Key).get();
		player1.getMatches().add(matchID);
		ofy().save().entities(player1).now();
		Key<Player> player2Key = Key.create(Player.class,
				loginInfo2.getEmailAddress());
		Player player2 = ofy().load().key(player2Key).get();
		player2.getMatches().add(matchID);
		ofy().save().entities(player2).now();
		ofy().save().entities(match).now();
		Set<String> tokens = player1.getTokens();
		for (String token : tokens) {
			pushToClient(token, "NEWMATCH:" + matchID);
		}
		tokens = player2.getTokens();
		for (String token : tokens) {
			pushToClient(token, "NEWMATCH:" + matchID);
		}
		waitingList.remove(0);
		waitingList.remove(0);
	}

	@Override
	public String getToken(LoginInfo loginInfo) {
		Key<Player> playerKey = Key.create(Player.class,
				loginInfo.getEmailAddress());
		Player player = ofy().load().key(playerKey).get();
		if (player != null) {
			player.getTokens().add(
					loginInfo.getEmailAddress() + "*" + loginInfo.getAppendix());
			player.setName(loginInfo.getNickname());
			ofy().save().entities(player).now();
		} else {
			player = new Player();
			player.setEmail(loginInfo.getEmailAddress());
			player.setName(loginInfo.getNickname());
		}
		return ChannelServiceFactory.getChannelService().createChannel(
				loginInfo.getEmailAddress() + "*" + loginInfo.getAppendix());
	}

	@Override
	public void matchPlayer(LoginInfo loginInfo, String matchPlayer) {
		if (matchPlayer.equals("")) {
			waitingList.add(loginInfo);
			if (waitingList.size() >= 2) {
				matchPlayer();
			}
		} else {
			Key<Player> playerKey = Key.create(Player.class, matchPlayer);
			Player opponent = ofy().load().key(playerKey).get();
			if (opponent == null) {
				opponent = new Player();
				opponent.setEmail(matchPlayer);
				ofy().save().entities(opponent).now();
			}
			String matchID = loginInfo.getEmailAddress() + "+" + matchPlayer + "+"
					+ (int) (Math.random() * 10000000);
			Match match = new Match();
			match.setMatchID(matchID);
			State state = new State();
			match.setState(state.toString());
			match.setwPlayer(loginInfo.getEmailAddress());
			match.setbPlayer(matchPlayer);

			Key<Player> player1Key = Key.create(Player.class, matchPlayer);
			Player player1 = ofy().load().key(player1Key).get();

			player1.getMatches().add(matchID);
			ofy().save().entities(player1).now();
			Key<Player> player2Key = Key.create(Player.class,
					loginInfo.getEmailAddress());
			Player player2 = ofy().load().key(player2Key).get();
			player2.getMatches().add(matchID);
			ofy().save().entities(player2).now();
			ofy().save().entities(match).now();
			Set<String> tokens = player1.getTokens();
			for (String token : tokens) {
				pushToClient(token, "NEWMATCH:" + matchID);
			}
			tokens = player2.getTokens();
			for (String token : tokens) {
				pushToClient(token, "NEWMATCH:" + matchID);
			}
		}
	}

	@Override
	public void publishMove(String matchID, String moveString) {
		Key<Match> matchKey = Key.create(Match.class, matchID);
		Match match = null;
		while(match == null){
		match = ofy().load().key(matchKey).get();
		}
		State state = StateParser.parse(match.getState());
		Move move = MoveSerializer.deserialize(moveString);
		StateChanger stateChanger = new StateChangerImpl();
		try {
			stateChanger.makeMove(state, move);
		} catch (Exception e) {

		}
		match.setState(state.toString());
		ofy().save().entities(match).now();
		Key<Player> player1Key = Key.create(Player.class, match.getwPlayer());
		Player player1 = ofy().load().key(player1Key).get();
		Key<Player> player2Key = Key.create(Player.class, match.getbPlayer());
		Player player2 = ofy().load().key(player2Key).get();
		
		GameResult gameResult = state.getGameResult();
		if (gameResult != null) {
			Color winColor = gameResult.getWinner();
			ResultType type = null;
			if (winColor.isWhite()) {
				type = ResultType.WIN;
			} else if (winColor.isBlack()) {
				type = ResultType.LOSE;
			} else {
				type = ResultType.DRAW;
			}
			double rank1 = player1.getRanking();
			double rank2 = player2.getRanking();
			double[] newRanks = ELOCalculator.newRank(rank1, type, rank2);
			player1.setRanking(newRanks[0]);
			player2.setRanking(newRanks[1]);
			ofy().save().entities(player1).now();
			ofy().save().entities(player2).now();
		}

		for (String token : player1.getTokens()) {
			pushToClient(token, "NEWMOVEON@@" + matchID + "@@" + state);
		}
		for (String token : player2.getTokens()) {
			pushToClient(token, "NEWMOVEON@@" + matchID + "@@" + state);
		}
	}

	@Override
	public Match getMatch(String matchID) {
		Key<Match> matchKey = Key.create(Match.class, matchID);
		Match match = ofy().load().key(matchKey).get();
		return match;
	}

	@Override
	public Set<String> getMatchList(LoginInfo loginInfo) {
		Player player = null;
		while (player == null) {
			Key<Player> playerKey = Key.create(Player.class,
					loginInfo.getEmailAddress());
			player = ofy().load().key(playerKey).get();
		}

		return player.getMatches();
	}

	@Override
	public void removeMatch(LoginInfo loginInfo, String matchID) {
		Key<Player> playerKey = Key.create(Player.class,
				loginInfo.getEmailAddress());
		Player player = ofy().load().key(playerKey).get();
		if (player.getMatches().remove(matchID)) {
			ofy().save().entities(player).now();
		}
		Key<Match> matchKey = Key.create(Match.class, matchID);
		Match match = ofy().load().key(matchKey).get();
		if (match.getbPlayer().equals(loginInfo.getEmailAddress())) {
			match.setbPlayer("");
		}
		if (match.getwPlayer().equals(loginInfo.getEmailAddress())) {
			match.setwPlayer("");
		}
		if (match.getbPlayer().equals("") && match.getwPlayer().equals("")) {
			ofy().delete().entity(match);
		}
	}

	@Override
	public void removeFromWaitList(LoginInfo loginInfo) {
		waitingList.remove(loginInfo);
	}

	@Override
	public double getPlayerRanking(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		Player player = null;
		Key<Player> playerKey = Key.create(Player.class,
				loginInfo.getEmailAddress());
		while (player == null) {
			player = ofy().load().key(playerKey).get();
		}
		return player.getRanking();
	}

	
}
