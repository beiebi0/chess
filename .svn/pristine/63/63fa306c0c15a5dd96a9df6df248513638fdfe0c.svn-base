package org.simongellis.hw6.server;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shared.chess.Color;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.simongellis.hw11.EmailList;
import org.simongellis.hw2.StateChangerImpl;
import org.simongellis.hw6.client.MultiplayerService;
import org.simongellis.hw7.client.Match;
import org.simongellis.hw7.client.Player;
import org.simongellis.hw7.client.Serializer;
import org.simongellis.hw9.AlphaBetaPruning;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class MultiplayerServiceImpl extends RemoteServiceServlet implements MultiplayerService {

	private static final long serialVersionUID = 1L;

	static {
		ObjectifyService.register(Player.class);
		ObjectifyService.register(Match.class);
		ObjectifyService.register(EmailList.class);
	}

	public static void addHeadersForCORS(HttpServletRequest req, HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Methods", "POST"); // "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers",
				"X-GWT-Module-Base, X-GWT-Permutation, Content-Type");
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

	private static final AlphaBetaPruning ai = new AlphaBetaPruning();
	private static final Key<Player> aiKey = Key.create(Player.class, "AI");

	UserService userService = UserServiceFactory.getUserService();
	ChannelService channelService = ChannelServiceFactory.getChannelService();
	StateChanger stateChanger = new StateChangerImpl();
	Serializer serializer = new Serializer();

	@Override
	public String connectUser() {
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			String channelID = user.getEmail();
			Key<Player> playerKey = Key.create(Player.class, user.getEmail());
			Player player = ofy().load().key(playerKey).get();
			if (player == null) {
				player = new Player(user.getEmail(), user.getNickname());
				ofy().save().entity(player);
			}
			channelID += "_" + String.valueOf(player.numberOfConnections());
			String token = channelService.createChannel(channelID);
			return token;
		} else
			return null;
	}

	@Override
	public String getLoginStatus(String href) {
		String result;
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			Player player = ofy().load().key(Key.create(Player.class, user.getEmail())).get();
			if (player != null) {
				result = "loggedin " + player.getName() + " " + player.getEmail() + " "
						+ player.getRank() + " " + userService.createLogoutURL(href);
			} else {
				result = "loggedin " + user.getNickname() + " " + user.getEmail() + " 1500 "
						+ userService.createLogoutURL(href);
			}
		} else {
			result = "loggedout " + userService.createLoginURL(href);
		}
		return result;
	}

	@Override
	public String[] loadAllMatches() {
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			Key<Player> playerKey = Key.create(Player.class, user.getEmail());
			Player player = ofy().load().key(playerKey).get();
			String[] results = new String[player.numberOfMatches()];
			int index = 0;
			for (Match match : ofy().load().keys(player.getMatches()).values()) {
				Key<Player> opponentKey = match.getOpponent(playerKey);
				if (opponentKey.equals(aiKey)) {
					String message = match.getId().toString();
					message += " W noname AI 1500 ";
					message += match.getStartDate().getTime() + " ";
					message += match.getState();
					results[index++] = message;
				} else {
					Player opponent = ofy().load().key(match.getOpponent(playerKey)).get();
					String message = match.getId().toString();
					if (match.getPlayerColor(playerKey).isWhite())
						message += " W ";
					else
						message += " B ";
					message += opponent.getName() + " ";
					message += opponent.getEmail() + " ";
					message += opponent.getRank() + " ";
					message += match.getStartDate().getTime() + " ";
					message += match.getState();
					results[index++] = message;
				}
			}
			return results;
		}
		return new String[0];
	}

	@Override
	public void aiMatch() {
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			Key<Player> playerKey = Key.create(Player.class, user.getEmail());
			Player player = ofy().load().key(playerKey).get();
			Date startDate = new Date();
			Match match = new Match(playerKey, aiKey, startDate, "");
			Key<Match> matchKey = ofy().save().entity(match).now();
			player.addMatch(matchKey);
			ofy().save().entity(player);
			String message = "aimatch " + match.getId() + " " + startDate.getTime() + " ";
			for (String connection : player.getConnections()) {
				channelService.sendMessage(new ChannelMessage(connection, message));
			}
		}
	}

	@Override
	public void autoMatch() {
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			Key<Player> playerKey = Key.create(Player.class, user.getEmail());
			Player player = ofy().load().key(playerKey).get();
			List<Player> otherPlayers = ofy().load().type(Player.class).filter("automatch", true)
					.list();
			if (otherPlayers.isEmpty()) {
				player.setAutomatch(true);
				ofy().save().entity(player);
			} else {
				Player otherPlayer = otherPlayers.remove(0);
				otherPlayer.setAutomatch(false);
				Key<Player> otherPlayerKey = Key.create(Player.class, otherPlayer.getEmail());
				Date startDate = new Date();
				Match match = new Match(otherPlayerKey, playerKey, startDate, "");
				Key<Match> matchKey = ofy().save().entity(match).now();
				player.addMatch(matchKey);
				otherPlayer.addMatch(matchKey);
				ofy().save().entities(player, otherPlayer, match).now();
				String message = "newmatch " + match.getId() + " B " + otherPlayer.getName() + " "
						+ otherPlayer.getEmail() + " " + otherPlayer.getRank() + " "
						+ startDate.getTime() + " ";
				for (String connection : player.getConnections()) {
					channelService.sendMessage(new ChannelMessage(connection, message));
				}
				message = "newmatch " + match.getId() + " W " + player.getName() + " "
						+ player.getEmail() + " " + player.getRank() + " " + startDate.getTime()
						+ " ";
				for (String connection : otherPlayer.getConnections()) {
					channelService.sendMessage(new ChannelMessage(connection, message));
				}
			}
		}
	}

	@Override
	public void cancelAutoMatch() {
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			Player player = ofy().load().key(Key.create(Player.class, user.getEmail())).get();
			player.setAutomatch(false);
			ofy().save().entity(player);
		}
	}

	@Override
	public Boolean emailMatch(String email) {
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			Key<Player> playerKey = Key.create(Player.class, user.getEmail());
			Player player = ofy().load().key(playerKey).get();
			Key<Player> otherPlayerKey = Key.create(Player.class, email);
			Player otherPlayer = ofy().load().key(otherPlayerKey).get();
			if (otherPlayer == null) {
				return Boolean.FALSE;
			}

			Date startDate = new Date();
			Match match = new Match(playerKey, otherPlayerKey, startDate, "");
			Key<Match> matchKey = ofy().save().entity(match).now();
			player.addMatch(matchKey);
			otherPlayer.addMatch(matchKey);

			ofy().save().entities(player, otherPlayer);
			String message = "newmatch " + match.getId() + " W " + otherPlayer.getName() + " "
					+ otherPlayer.getEmail() + " " + otherPlayer.getRank() + " "
					+ startDate.getTime() + " ";
			for (String connection : player.getConnections()) {
				channelService.sendMessage(new ChannelMessage(connection, message));
			}
			message = "newmatch " + match.getId() + " B " + player.getName() + " "
					+ player.getEmail() + " " + player.getRank() + " " + startDate.getTime() + " ";
			for (String connection : otherPlayer.getConnections()) {
				channelService.sendMessage(new ChannelMessage(connection, message));
			}

			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public String switchToMatch(Long matchId) {
		if (userService.isUserLoggedIn()) {
			String message;
			if (matchId != null) {
				User user = userService.getCurrentUser();
				Key<Player> playerKey = Key.create(Player.class, user.getEmail());
				Key<Match> matchKey = Key.create(Match.class, matchId);
				Match match = ofy().load().key(matchKey).get();

				message = match.getId().toString() + " ";
				if (match.getPlayerColor(playerKey).isWhite())
					message += "W ";
				else
					message += "B ";
				message += match.getState();
				return message;
			}
		}
		return "none";
	}

	@Override
	public void deleteMatch(Long matchId) {
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			Key<Player> playerKey = Key.create(Player.class, user.getEmail());
			Player player = ofy().load().key(playerKey).get();
			Key<Match> matchKey = Key.create(Match.class, matchId);
			player.removeMatch(matchKey);
			ofy().save().entity(player);

			Match match = ofy().load().key(matchKey).get();
			Key<Player> otherPlayerKey = match.getOpponent(playerKey);
			if (otherPlayerKey.equals(aiKey)) {
				ofy().delete().entity(match);
			} else {
				Player otherPlayer = ofy().load().key(match.getOpponent(playerKey)).get();
				if (!otherPlayer.containsMatch(matchKey)) {
					ofy().delete().entity(match);
				}
			}
		}
	}

	@Override
	public String makeMove(Long matchId, String serializedMove) {
		if (userService.isUserLoggedIn()) {
			Key<Match> matchKey = Key.create(Match.class, matchId);
			Match match = ofy().load().key(matchKey).get();

			State state = serializer.unserializeState(match.getState());
			Move move = serializer.unserializeMove(serializedMove);
			try {
				stateChanger.makeMove(state, move);
			} catch (IllegalMove e) {
				return match.getState();
			}
			match.setState(serializer.serializeState(state));
			ofy().save().entity(match);

			User user = userService.getCurrentUser();
			Key<Player> playerKey = Key.create(Player.class, user.getEmail());
			Key<Player> opponentKey = match.getOpponent(playerKey);
			String message = "move " + matchId.toString() + " " + serializer.serializeState(state)
					+ " " + serializedMove;

			if (!opponentKey.equals(aiKey)) {
				Player opponent = ofy().load().key(opponentKey).get();
				for (String connection : opponent.getConnections()) {
					channelService.sendMessage(new ChannelMessage(connection, message));
				}

				// ELO
				if (state.getGameResult() != null) {
					Player player = ofy().load().key(playerKey).get();
					Color winnerColor = state.getGameResult().getWinner();

					int playerRank = player.getRank();
					int opponentRank = opponent.getRank();

					double aTerm = Math.pow(10, (double) playerRank / 400);
					double bTerm = Math.pow(10, (double) opponentRank / 400);

					// 1 for win, 0 for loss, 0.5 for draw
					double expectedOutcomeForPlayer = aTerm / (aTerm + bTerm);
					double expectedOutcomeForOpponent = bTerm / (aTerm + bTerm);

					double actualOutcome;
					if (match.getPlayerColor(playerKey).equals(winnerColor)) {
						actualOutcome = 1; // win for player
					} else if (match.getPlayerColor(opponentKey).equals(winnerColor)) {
						actualOutcome = 0; // win for opponent
					} else {
						actualOutcome = 0.5; // draw for both
					}

					playerRank += Math.round(15 * (actualOutcome - expectedOutcomeForPlayer));
					opponentRank += Math
							.round(15 * (1.0 - actualOutcome - expectedOutcomeForOpponent));

					player.setRank(playerRank);
					opponent.setRank(opponentRank);
					ofy().save().entities(player, opponent);

					String updateRank = "rank me " + playerRank;
					for (String connection : player.getConnections()) {
						channelService.sendMessage(new ChannelMessage(connection, updateRank));
					}
					for (Match otherMatch : ofy().load().keys(player.getMatches()).values()) {
						updateRank = "rank " + otherMatch.getId() + " " + playerRank;
						Player otherPlayer = ofy().load().key(otherMatch.getOpponent(playerKey))
								.get();
						for (String connection : otherPlayer.getConnections()) {
							channelService.sendMessage(new ChannelMessage(connection, updateRank));
						}
					}

					updateRank = "rank me " + opponentRank;
					for (String connection : opponent.getConnections()) {
						channelService.sendMessage(new ChannelMessage(connection, updateRank));
					}
					for (Match otherMatch : ofy().load().keys(opponent.getMatches()).values()) {
						updateRank = "rank " + otherMatch.getId() + " " + opponentRank;
						Player otherPlayer = ofy().load().key(otherMatch.getOpponent(opponentKey))
								.get();
						for (String connection : otherPlayer.getConnections()) {
							channelService.sendMessage(new ChannelMessage(connection, updateRank));
						}
					}
				}
			}

			return serializer.serializeState(state);
		}
		return serializer.serializeState(new State());
	}

	public String makeAIMove(Long matchId) {
		if (userService.isUserLoggedIn()) {
			Key<Match> matchKey = Key.create(Match.class, matchId);
			Match match = ofy().load().key(matchKey).get();
			State state = serializer.unserializeState(match.getState());
			Move move = null;
			try {
				move = ai.bestMove(state);
				stateChanger.makeMove(state, move);
			} catch (IllegalMove e) {
				return match.getState() + " ";
			}
			String stateAfterMove = serializer.serializeState(state);
			match.setState(stateAfterMove);
			ofy().save().entity(match);
			return stateAfterMove + " " + serializer.serializeMove(move);
		}
		return "  ";
	}

	@Override
	public void storeEmails(Long id, String emails) {
		if (emails == null)
			return;
		Set<String> allEmails = new HashSet<String>(Arrays.asList(emails.split("[ ]")));
		Key<EmailList> emailKey = Key.create(EmailList.class, id);
		EmailList list = ofy().load().key(emailKey).get();
		if (list == null)
			list = new EmailList(id, allEmails);
		else
			list.addEmails(allEmails);
		ofy().save().entity(list);
	}
	
	@Override
	public String getEmails(Long id) {
		Key<EmailList> emailKey = Key.create(EmailList.class, id);
		EmailList list = ofy().load().key(emailKey).get();
		Set<String> allEmails = list.getEmails();
		String emails = null;
		for (String email : allEmails) {
			if (emails == null)
				emails = email;
			else
				emails += " " + email;
		}
		return emails;
	}
}
