package org.alexanderoskotsky.hw6;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alexanderoskotsky.hw3.StateSerializer;
import org.alexanderoskotsky.hw7.AutoMatch;
import org.alexanderoskotsky.hw7.Match;
import org.alexanderoskotsky.hw7.MatchInfo;
import org.alexanderoskotsky.hw7.Player;
import org.alexanderoskotsky.hw8.ELOCalc;
import org.alexanderoskotsky.hw8.ELOCalc.Outcome;
import org.shared.chess.Color;
import org.shared.chess.State;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.googlecode.objectify.Work;

public class ChessServiceImpl extends RemoteServiceServlet implements ChessService {
	private static final long serialVersionUID = 1L;

	private ChannelService channelService = ChannelServiceFactory.getChannelService();

	private Logger logger = Logger.getLogger(ChessServiceImpl.class.toString());

	static {
		ObjectifyService.register(Match.class);
		ObjectifyService.register(Player.class);
		ObjectifyService.register(AutoMatch.class);
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

	@Override
	public synchronized PlayerInfo connect(String myEmail) {
		PlayerInfo info = new PlayerInfo();

		String longId = myEmail + ">" + UUID.randomUUID().toString();

		String clientId = longId.substring(0, Math.min(63, longId.length() - 1));

		String token = channelService.createChannel(clientId);

		Player player = getOrCreatePlayer(myEmail);

		player.setName(myEmail);

		ofy().save().entity(player).now();

		info.setChannelId(token);
		info.setClientId(clientId);
		info.setUsername(myEmail);
		info.setEmail(myEmail);
		info.setRank(player.getRank());

		return info;
	}

	@Override
	public void sendState(final String myEmail, final String matchId, final String stateString,
			final int turn) {
		logger.log(Level.INFO, "state received " + stateString);

		ofy().transact(new VoidWork() {

			@Override
			public void vrun() {
				Match match = ofy().load().type(Match.class).id(Integer.valueOf(matchId)).get();

				if (match == null) {
					throw new IllegalArgumentException("Invalid match");
				}

				// if(match.getTurnNumber() >= turn) {
				// throw new IllegalArgumentException("Invalid turn number");
				// }

				State newState = StateSerializer.deserialize(stateString);
				State oldState = StateSerializer.deserialize(match.getState());

				if (newState.equals(oldState)) {
					throw new IllegalArgumentException("Invalid state");
				}

				match.setState(stateString);
				match.setTurnNumber(turn);

				ofy().save().entity(match).now();

				if (newState.getGameResult() != null) {
					Outcome whiteOutcome = Outcome.DRAW;
					Outcome blackOutcome = Outcome.DRAW;

					if (newState.getGameResult().getWinner() != null) {
						if (newState.getGameResult().getWinner().equals(Color.WHITE)) {
							whiteOutcome = Outcome.WIN;
							blackOutcome = Outcome.LOSS;
						} else {
							whiteOutcome = Outcome.LOSS;
							blackOutcome = Outcome.WIN;
						}
					}

					int whiteScore = ELOCalc.getNewScore(match.getWhitePlayer().getRank(), match
							.getBlackPlayer().getRank(), whiteOutcome);
					int blackScore = ELOCalc.getNewScore(match.getBlackPlayer().getRank(), match
							.getWhitePlayer().getRank(), blackOutcome);

					match.getWhitePlayer().setRank(whiteScore);
					match.getBlackPlayer().setRank(blackScore);

					ofy().save().entities(match.getWhitePlayer(), match.getBlackPlayer()).now();
				}

				Player player = getCurrentPlayer(myEmail);

				if (player.getEmail().equals(match.getWhitePlayer().getEmail())) {
					for (String channel : match.getBlackPlayer().getChannels()) {
						channelService.sendMessage(new ChannelMessage(channel, matchId + "#"
								+ stateString));
					}
				} else {
					for (String channel : match.getWhitePlayer().getChannels()) {
						channelService.sendMessage(new ChannelMessage(channel, matchId + "#"
								+ stateString));
					}
				}

			}
		});

	}

	@Override
	public MatchInfo createMatch(String myEmail, final String email) {
		final Player currentPlayer = getCurrentPlayer(myEmail);

		final Match match = new Match();

		match.setState(StateSerializer.serialize(new State()));
		match.setName(UUID.randomUUID().toString());
		Match lastMatch = ofy().load().type(Match.class).order("-id").first().get();
		final Long id = lastMatch == null ? 1 : lastMatch.getId() + 1;

		ofy().transact(new VoidWork() {
			@Override
			public void vrun() {
				match.setId(id);

				Player white = currentPlayer;
				Player black = getOrCreatePlayer(email);

				match.setWhitePlayer(white);

				Date startDate = new Date();

				match.setStartDate(startDate);

				Key<Match> key = Key.create(match);

				white.getMatches().add(key);

				match.setBlackPlayer(black);
				black.getMatches().add(key);
				ofy().save().entity(black).now();

				ofy().save().entity(match).now();
				ofy().save().entity(white).now();
			}
		});

		// TODO if the black player has any open channels then we should be
		// sending him a message at this point

		return getMatchInfoFromMatch(match, currentPlayer);
	}

	/**
	 * Load a Player by their email or create a new Player if not found
	 * 
	 * @param email
	 * @return
	 */
	private Player getOrCreatePlayer(String email) {
		Player player = ofy().load().type(Player.class).id(email).get();

		if (player == null) {
			player = new Player();
			player.setEmail(email);
			player.setRank(1500);
			player.setMatches(new ArrayList<Key<Match>>());
		}

		return player;
	}

	/**
	 * Create a MatchInfo bean from a Match entity thats relative to the
	 * currentPlayer. This means setting the correct myColor and opponent fields
	 * depending on which the current player is
	 * 
	 * @param match
	 * @param currentPlayer
	 * @return
	 */
	private MatchInfo getMatchInfoFromMatch(Match match, Player currentPlayer) {
		MatchInfo info = new MatchInfo();
		info.setMatchId(String.valueOf(match.getId()));

		if (match.getWhitePlayer().getEmail().equals(currentPlayer.getEmail())) {
			info.setMyColor(Color.WHITE);
			info.setOpponentEmail(match.getBlackPlayer().getEmail());
			info.setOpponentRank(match.getBlackPlayer().getRank());
		} else {
			info.setMyColor(Color.BLACK);
			info.setOpponentEmail(match.getWhitePlayer().getEmail());
			info.setOpponentRank(match.getWhitePlayer().getRank());
		}

		info.setStartDate(match.getStartDate());

		State state = StateSerializer.deserialize(match.getState());
		info.setTurn(state.getTurn());
		if (state.getGameResult() != null) {
			info.setReason(state.getGameResult().getGameResultReason());
			info.setWinner(state.getGameResult().getWinner());
		}

		info.setStateString(match.getState());
		info.setTurnNumber(match.getTurnNumber());

		return info;
	}

	@Override
	public List<MatchInfo> getMatches(String myEmail) {
		final List<MatchInfo> data = new ArrayList<MatchInfo>();
		final Player currentPlayer = getCurrentPlayer(myEmail);

		for (Key<Match> matchKey : currentPlayer.getMatches()) {
			Match match = ofy().cache(false).load().key(matchKey).getValue();

			if (match == null) {
				continue;
			}

			data.add(getMatchInfoFromMatch(match, currentPlayer));
		}

		return data;
	}

	@Override
	public MatchInfo getMatchState(String myEmail, final String matchId) {
		final Player currentPlayer = getCurrentPlayer(myEmail);

		return ofy().transact(new Work<MatchInfo>() {

			@Override
			public MatchInfo run() {
				Match match = ofy().load().type(Match.class).id(Integer.valueOf(matchId)).get();

				if (match == null) {
					throw new IllegalArgumentException("Match not found");
				}

				return getMatchInfoFromMatch(match, currentPlayer);
			}
		});
	}

	@Override
	public void deleteMatch(String myEmail, String matchId) {
		Player player = ofy().load().type(Player.class).id(myEmail).get();

		if (player == null) {
			throw new IllegalStateException("Invalid player");
		}

		player.getMatches().remove(Key.create(Match.class, Integer.valueOf(matchId)));

		ofy().save().entity(player).now();
	}

	@Override
	public void autoMatch(String myEmail) {
		Player player = getCurrentPlayer(myEmail);

		boolean found = false;
		for (AutoMatch match : ofy().load().type(AutoMatch.class).list()) {
			if (!match.getPlayer().equals(player)) {
				found = true;

				Player otherPlayer = match.getPlayer();

				String otherEmail = otherPlayer.getEmail();

				ofy().delete().entity(match).now();

				MatchInfo info = createMatch(myEmail, otherEmail);

				for (String channel : player.getChannels()) {
					channelService.sendMessage(new ChannelMessage(channel, "NewMatch="
							+ info.getMatchId()));
				}

				for (String channel : otherPlayer.getChannels()) {
					channelService.sendMessage(new ChannelMessage(channel, "NewMatch="
							+ info.getMatchId()));
				}

				break;
			}
		}

		if (!found) {
			AutoMatch newMatch = new AutoMatch();
			newMatch.setPlayer(player);
			ofy().save().entity(newMatch).now();
		}
	}

	private Player getCurrentPlayer(String myEmail) {
		Player player = ofy().load().type(Player.class).id(myEmail).get();

		if (player == null) {
			throw new IllegalArgumentException("Player not found");
		}

		return player;
	}

	@Override
	public PlayerInfo getPlayerInfo(String myEmail) {

		Player player = getCurrentPlayer(myEmail);

		player.setName(myEmail);

		PlayerInfo info = new PlayerInfo();
		info.setUsername(myEmail);
		info.setEmail(myEmail);
		info.setRank(player.getRank());

		return info;
	}
}
