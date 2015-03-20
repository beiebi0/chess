package org.longjuntan.hw6.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.longjuntan.hw2.StateChangerImpl;
import org.longjuntan.hw3.Utils;
import org.longjuntan.hw6.client.GameService;
import org.longjuntan.hw7.DAO;
import org.longjuntan.hw7.Match;
import org.longjuntan.hw7.Player;
import org.longjuntan.hw7.objectifyDAO;
import org.longjuntan.hw8.GameInfo;
import org.longjuntan.hw8.PageInfo;
import org.longjuntan.hw8.PlayerInfo;
import org.longjuntan.hw9.AlphaBetaPruning;
import org.longjuntan.hw9.GameHeuristic;
import org.longjuntan.hw9.GameTimer;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.State;
import org.shared.chess.StateChanger;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

@SuppressWarnings("serial")
public class GameServiceImpl extends RemoteServiceServlet implements
		GameService {

	private static DAO dao = new objectifyDAO();

	private StateChanger sc = new StateChangerImpl();

	static {
		ObjectifyService.register(Match.class);
		ObjectifyService.register(Player.class);
	}

	@Override
	// public String sendMove(String matchId, String move) { for HW11
	public String sendMove(String email, String matchId, String move,String state) {
		ChannelService channelService = ChannelServiceFactory
				.getChannelService();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		Match current = dao.getMatch(matchId);

//		Move m = Utils.getMoveFromString(move);
//
//		State s = Utils.setStateByHistory(current.getState());
//		sc.makeMove(s, m);

//		current.setState(Utils.getHistory(s));
		current.setState(state);
		String AIMove = "";
		if (current.getOpponent(email).getEmail().equals("AIPlayer")) {
			AIMove = "|";
		} else {
			for (String channel : current.getOpponent(email).getChannels()) {
				channelService.sendMessage(new ChannelMessage(channel, "MOVE"
						+ current.getMatchId() + " " + move));
			}
		}

		dao.saveMatch(current);
		return AIMove + move;
	}

	@Override
	public String callAIPlayer(String matchId, String stateString) {
		Match current = dao.getMatch(matchId);
		State s = Utils.setStateByHistory(stateString);

		AlphaBetaPruning abp = new AlphaBetaPruning(new GameHeuristic());
		Move aiMove = abp.findBestMove(s, 7, new GameTimer(3000));

		sc.makeMove(s, aiMove);
		current.setState(Utils.getHistory(s));
		dao.saveMatch(current);
		return aiMove.toString();
	}

	@Override
	public PlayerInfo join(String email) {
		ChannelService channelService = ChannelServiceFactory
				.getChannelService();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		PlayerInfo info = new PlayerInfo();
		Player player = getOrCreatePlayer(email);

		String clientId = email + " " + Long.toString(new Date().getTime());
		String token = channelService.createChannel(clientId);

		System.out.println("Before: Add channel to " + player.toString()
				+ player.getChannels().size());
		player.addChannels(clientId);
		dao.savePlayer(player);

		System.out.println("After: Add channel to " + player.toString()
				+ player.getChannels().size());
		System.out.println("Token: " + token);

		info.setToken(token);
		info.setEmail(email);
		info.setNickname(email);
		info.setRank(player.getRank());

		return info;
	}

	@Override
	// public PageInfo load(String matchId) {For HW11
	public PageInfo load(String email, String matchId) {
		matchId = matchId.trim();
		Match match = dao.getMatch(matchId);
		ChannelService channelService = ChannelServiceFactory
				.getChannelService();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		GameInfo game = new GameInfo();

		PageInfo info = new PageInfo();
		PlayerInfo opponentInfo = new PlayerInfo();

		// String email = user.getEmail();
		StringBuilder sb = new StringBuilder();
		
		// Player player = dao.getPlayer(email);
		// sb.append(matchId).append(">");

		Player opponent = match.getOpponent(email);

		sb.append(match.getColor(email));
		sb.append(match.getState());

		

		game.setGameId(matchId);
		game.setInitDate(match.getInitialTime());
		game.setState(match.getState());

		

		opponentInfo.setEmail(opponent.getEmail());
		opponentInfo.setMatchId(matchId);
		opponentInfo.setNickname(opponent.getName());
		opponentInfo.setRank(opponent.getRank());

		info.setGame(game);
		info.setOpponent(opponentInfo);
		info.setColor(match.getColor(email));

		// TODO Auto-generated method stub
		return info;
	}


	@Override
	// public PageInfo createMatch(String opponent) {for HW11
	public PageInfo createMatch(String email, String opponent) {
		ChannelService channelService = ChannelServiceFactory
				.getChannelService();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Player white = getOrCreatePlayer(email);
		Player black = getOrCreatePlayer(opponent);

		Match match = new Match(new Date());
		dao.createMatchTransction(match, white, black);

		String matchId = match.getMatchId();
		GameInfo game = new GameInfo();
		game.setGameId(matchId);
		game.setInitDate(match.getInitialTime());
		game.setState(match.getState());

		PlayerInfo opponentInfo = new PlayerInfo();
		opponentInfo.setNickname(black.getName());
		opponentInfo.setEmail(black.getEmail());
		opponentInfo.setRank(black.getRank());

		PageInfo info = new PageInfo();
		info.setGame(game);
		info.setOpponent(opponentInfo);
		info.setColor(Color.WHITE);
		info.setListInfo(matchId + ">" + "vs."
				+ black.getName() + " "
				+ match.getTurn(email));

		System.out.println("Create match");
		System.out.println(match.getBlack());
		for (String channel : match.getBlack().getChannels()) {
			if (channel == null) {
				break;
			}
			// channelService.sendMessage(new ChannelMessage(channel,
			// "NB"+match.getMatchId()+"@"+match.getState()));
			channelService.sendMessage(new ChannelMessage(channel, match
					.getMatchId()));
		}

		return info;
	}

	private Player getOrCreatePlayer(String email) {
		Player player;
		if ((player = dao.getPlayer(email)) == null) {
			System.out.println("Player Not Found");
			player = new Player(email);
			dao.savePlayer(player);
		}
		System.out.println(player.toString());
		return player;
	}

	
	public static void removeChannel(String email, String channel) {
		Player player = dao.getPlayer(email);
		player.getChannels().remove(channel);
		dao.savePlayer(player);
		System.out.println(player.getChannels().size());
	}

	@Override
	public List<String> loadMatchList(String email) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		Player player = dao.getPlayer(email);
		if (player == null)
			return null;
		System.out.println("# of Match: " + player.getMatches().size());
		list.add(">[select one]");
		for(Match match : ofy().cache(false).load().keys(player.getMatches()).values()){
//		for (Key<Match> m : player.getMatches()) {
////			Match match = ofy().load().key(m).get();
//			Match match = ofy().cache(false).load().key(m).getValue();
			if (match != null) {
				State s = Utils.setStateByHistory(match.getState());
				if (s.getGameResult() == null)
					list.add(match.getMatchId() + ">" + "vs."
							+ match.getOpponent(email).getName() + " "
							+ match.getTurn(email));
				else
					list.add(match.getMatchId() + ">" + "vs."
							+ match.getOpponent(email).toString() + " "
							+ s.getGameResult().toString());
			}
		}
		return list;
	}

	@Override
	public void deleteMatch(final String email, final String matchId) {
//		Player p = dao.getPlayer(email);
//		Match m = dao.getMatch(matchId);
//		p.getMatches().remove(Key.create(m));
//		dao.savePlayer(p);
//
//		List<String> list = new ArrayList<String>();
//		for (Key<Match> matchKey : p.getMatches()) {
//			Match match = ofy().cache(false).load().key(matchKey).getValue();
//			if (match != null) {
//				State s = Utils.setStateByHistory(match.getState());
//				if (s.getGameResult() == null)
//					list.add(match.getMatchId() + ">" + "vs."
//							+ match.getOpponent(email).toString() + " "
//							+ match.getTurn(email));
//				else
//					list.add(match.getMatchId() + ">" + "vs."
//							+ match.getOpponent(email).toString() + " "
//							+ s.getGameResult().toString());
//			}
//		}
//		
////		return list;

     ofy().transact(new VoidWork() {
             @Override
             public void vrun() {
                     Player player = dao.getPlayer(email);
                     if (player != null) {
                             player.getMatches().remove(Key.create(Match.class, matchId));
                            dao.savePlayer(player);

                             Match match = dao.getMatch(matchId);
                             if (match == null)
                                     return;

                             if (match.isDeleted())
                                     dao.deleteMatch(match);
                             else {
                                     match.setDeleted(true);
                                     dao.saveMatch(match);
                             }
                     }
             }
     });
	}
}