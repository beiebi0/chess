package org.markanderson.hw6.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.markanderson.hw3.HistorySupport;
import org.markanderson.hw6.client.ManderChessService;
import org.markanderson.hw6.helper.ManderChessGameSessionInfo;
import org.markanderson.hw6.helper.ManderChessUserSessionInfo;
import org.markanderson.hw7.Match;
import org.markanderson.hw7.Player;
import org.markanderson.hw8.ManderRatingImpl;
import org.shared.chess.Color;
import org.shared.chess.State;

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

public class ManderChessServiceImpl extends RemoteServiceServlet implements
		ManderChessService {

//	public static void addHeadersForCORS(HttpServletRequest req,
//			HttpServletResponse resp) {
//		resp.setHeader("Access-Control-Allow-Methods", "POST");
//		resp.setHeader("Access-Control-Allow-Origin", "*");
//		resp.setHeader("Access-Control-Allow-Headers",
//				"X-GWT-Module-Base, X-GWT-Permutation, Content-Type");
//	}
//
//	@Override
//	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
//		addHeadersForCORS(req, resp);
//	}
//
//	@Override
//	protected void onAfterResponseSerialized(String serializedResponse) {
//		super.onAfterResponseSerialized(serializedResponse);
//		addHeadersForCORS(getThreadLocalRequest(), getThreadLocalResponse());
//	}

	ManderChessGameSessionInfo gameInfo;
	// Player player;
	Match match;
	// Objectify ofy = ObjectifyService.factory().begin();
	ChannelService chanService = ChannelServiceFactory.getChannelService();

	public Map<String, ManderChessGameSessionInfo> gameMap = new HashMap<String, ManderChessGameSessionInfo>();

	private static final long serialVersionUID = 1L;

	@Override
	public void updateStateForMove(String clientID, final String stateStr) {
		log("State being sent via message to Channel API: " + stateStr);

		String[] newStateStrSplit = stateStr.split("#");
		final String stStr = newStateStrSplit[0];
		String idAndRank = newStateStrSplit[1];
		String gameIDStr = newStateStrSplit[1];

		final ManderChessGameSessionInfo gameID = gameMap.get(clientID);

		log("Client IDs: White: " + gameID.getwClientID() + "Black: "
				+ gameID.getbClientID());
		if (gameID != null && gameID.getbClientID() != null
				&& gameID.getwClientID() != null) {
			chanService.sendMessage(new ChannelMessage(gameID.getwClientID(),
					stateStr));
			chanService.sendMessage(new ChannelMessage(gameID.getbClientID(),
					stateStr));
			log("User " + gameID + " sent message to clients: "
					+ gameID.getwClientID() + " &&& " + gameID.getbClientID());
		}

		// now make a transaction that changes the state of the persistent
		// entities
		final Match match = ofy().load().type(Match.class)
				.id(Integer.valueOf(gameIDStr)).getValue();

		final State state = HistorySupport.deserializeHistoryToken(stStr);

		// set the ratings for each player if player has won
		if (state.getGameResult() != null
				&& state.getGameResult().getWinner() != null) {
			final Player wPlayer = ofy().load().type(Player.class)
					.id(gameID.getwPlayerEmail()).get();
			final Player bPlayer = ofy().load().type(Player.class)
					.id(gameID.getbPlayerEmail()).get();

			// get the rating for each player and save it via a transaction
			final ManderRatingImpl whiteRating = new ManderRatingImpl(
					wPlayer.getRank());
			final ManderRatingImpl blackRating = new ManderRatingImpl(
					bPlayer.getRank());

			ManderRatingImpl[] ratings = { whiteRating, blackRating };

			ManderRatingImpl.calculateRatings(ratings);

			wPlayer.setRank(whiteRating.getRating());
			bPlayer.setRank(blackRating.getRating());

			ofy().transact(new VoidWork() {
				@Override
				public void vrun() {
					match.setBlackRank(blackRating);
					match.setWhiteRank(whiteRating);
					ofy().save().entity(match).now();
					ofy().save().entity(wPlayer).now();
					ofy().save().entity(bPlayer).now();
				}
			});
		}
		// make the transaction
		ofy().transact(new VoidWork() {
			@Override
			public void vrun() {
				match.setTurn(state.getTurn());
				match.setState(stStr);
				ofy().save().entity(match).now();
				log("Match saved! " + match.toString());
			}
		});
	}

	@Override
	public ManderChessUserSessionInfo createUserChannel() {
		// register the entities with objectify persistence model
		ObjectifyService.register(Player.class);
		ObjectifyService.register(Match.class);

		ManderChessUserSessionInfo userInfo = new ManderChessUserSessionInfo();

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Player player = ofy().load().type(Player.class).id(user.getEmail())
				.get();

		if (player == null) {
			// if new player, set email and rank
			player.setPlayerEmail(user.getEmail());
			player.setRank(1500);
			player.setPlayerMatches(new TreeSet<Key<Match>>());
		}

		// set all the user information
		if (user.getNickname().contains("@")) {
			// weird case where the nickname still contained the full email
			// address (such as with @nyu.edu email addresses. This causes an
			// Exception to occur
			// within Objectify and crashes.
			String[] parts = user.getNickname().split("@");
			String nickname = parts[0];

			userInfo.setNickname(nickname);
		} else {
			userInfo.setNickname(user.getNickname());
		}

		// randomize the clientID along with the nickname of the user to use as
		// the token
		String clientID = userInfo.getNickname();

		String token = chanService.createChannel(clientID);

		userInfo.setChannelID(token);
		userInfo.setEmailAddress(player.getPlayerEmail());
		userInfo.setClientID(clientID);
		// already set nickname earlier...
		// set the rating
		double rating = player.getRank();
		userInfo.setRating(rating);

		if (gameInfo == null) {
			// set the white client
			gameInfo = new ManderChessGameSessionInfo();
			gameInfo.setwClientID(userInfo.getClientID());
			gameInfo.setwPlayerEmail(userInfo.getEmailAddress());
			userInfo.setColor(Color.WHITE);
			gameMap.put(clientID, gameInfo);
			gameInfo.setwRank(player.getRank());

			log("User joined: " + userInfo.getEmailAddress());
		} else {
			// set the black client
			gameInfo.setbClientID(userInfo.getClientID());
			gameInfo.setbPlayerEmail(userInfo.getEmailAddress());
			gameInfo.setbRank(player.getRank());
			userInfo.setColor(Color.BLACK);
			gameMap.put(clientID, gameInfo);
			log("User joined: " + userInfo.getEmailAddress());
		}

		ofy().save().entity(player).now();

		return userInfo;
	}

	@Override
	public ManderChessGameSessionInfo sendInvitationToUser(String emailTo)
			throws UnsupportedEncodingException {

		// get the nickname from the emailTo. This seems to be a bug where
		// the User.getNickname method returns the entire email address, which
		// throws an exception when trying to save that as the key, because it
		// has a period in it.
		String[] parts = emailTo.split("@");
		String nickname = parts[0];

		// otherwise, a second user has joined so we need to set the black
		// player accordingly
		String bClientID = nickname;

		log("User invited to game: " + emailTo);

		// look for the player with the emailTo id. If none, create a new one,
		// if one with the same id exists, just set the token and save the new
		// entity
		Player p = ofy().load().type(Player.class).id(emailTo).get();

		if (p == null || emailTo == null) {
			p = new Player();
			p.setPlayerEmail(emailTo);
		}
		String token = chanService.createChannel(bClientID);
		p.tokens.put(bClientID, token);

		log("Player 2 for Invitation is: " + p.getPlayerEmail());

		// save the player entity
		ofy().save().entity(p).now();

		// set up the match for both players, because second player has
		// now joined the game
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String wClientID = user.getNickname();

		ManderChessGameSessionInfo gInfo = new ManderChessGameSessionInfo();

		Match m = new Match();
		Player wPlayer = ofy().load().type(Player.class).id(user.getEmail())
				.get();
		Player bPlayer = ofy().load().type(Player.class).id(p.getPlayerEmail())
				.get();

		m.setwPlayerEmail(wPlayer.getPlayerEmail());
		m.setbPlayerEmail(bPlayer.getPlayerEmail());
		m.setTurn(Color.WHITE);

		// increment the match id if other matches exist, otherwise, just set it
		// to 1
		m.setMatchID(ofy().load().type(Match.class).order("-matchID").first()
				.get() != null ? ofy().load().type(Match.class)
				.order("-matchID").first().get().getMatchID() + 1 : 1);
		m.setState(HistorySupport.serializeHistory(new State()));
		m.setPlayer1(wPlayer);
		m.setPlayer2(bPlayer);

		// set the date for the current time
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		m.setStartDate(date);

		// save the match entity
		ofy().save().entity(m).now();

		gInfo.setwPlayerEmail(user.getEmail());
		gInfo.setbPlayerEmail(p.getPlayerEmail());
		gInfo.setCurrentGameID(String.valueOf(m.getMatchID()));
		gInfo.setCurrentTurn("White");
		gInfo.setbClientID(bClientID);
		gInfo.setwClientID(wClientID);
		gInfo.setwRank(wPlayer.getRank());
		gInfo.setbRank(bPlayer.getRank());

		chanService.sendMessage(new ChannelMessage(bClientID, "setGameTime="
				+ ":" + date.toString()));
		chanService.sendMessage(new ChannelMessage(wClientID, "setGameTime="
				+ ":" + date.toString()));
		// get the match set from the wPlayer and add the new match into the
		// set
		Set<Key<Match>> wPlayerMatches = wPlayer.getPlayerMatches();
		wPlayerMatches.add(Key.create(Match.class, m.getMatchID()));
		wPlayer.setPlayerMatches(wPlayerMatches);

		// add the newly created match to both player's match list
		ofy().save().entity(wPlayer).now();

		// case where user is playing against himself. Don't persist
		// another match
		// because it will be the same user
		Set<Key<Match>> bPlayerMatches = bPlayer.getPlayerMatches();
		bPlayerMatches.add(Key.create(Match.class, m.getMatchID()));
		bPlayer.setPlayerMatches(bPlayerMatches);

		ofy().save().entity(bPlayer).now();
		log("Match is between [W]: " + m.getwPlayerEmail() + " & [B]: "
				+ m.getbPlayerEmail());

		// update the bPlayer's dropdown as well.
		chanService.sendMessage(new ChannelMessage(wClientID, "addNewGame="
				+ ":" + serializeChessGameInfo(gInfo)));
		chanService.sendMessage(new ChannelMessage(bClientID, "addNewGame="
				+ ":" + serializeChessGameInfo(gInfo)));

		return gInfo;
	}

	@Override
	public List<ManderChessGameSessionInfo> retrieveMatchesForCurrentPlayer() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		List<ManderChessGameSessionInfo> matchesForUser = new ArrayList<ManderChessGameSessionInfo>();

		Player currentPlayer = ofy().load().type(Player.class)
				.id(user.getEmail()).get();

		Set<Key<Match>> keysForPlayerMatches = currentPlayer.getPlayerMatches();

		Iterator<Key<Match>> iterator = keysForPlayerMatches.iterator();

		// iterate over the player matches and add them to the array list. This
		// will be used
		// to populate the drop down menu for the player
		while (iterator.hasNext()) {
			Key<Match> currKey = iterator.next();
			Match currMatch = ofy().load().type(Match.class)
					.id(currKey.getId()).getValue();
			ManderChessGameSessionInfo gInfo = new ManderChessGameSessionInfo();

			gInfo.setwPlayerEmail(currMatch.getwPlayerEmail());
			gInfo.setbPlayerEmail(currMatch.getbPlayerEmail());
			gInfo.setCurrentGameID(String.valueOf(currMatch.getMatchID()));
			gInfo.setCurrentTurn(currMatch.getTurn().equals(Color.WHITE) ? "White"
					: "Black");
			gInfo.setGameDate(currMatch.getStartDate());
			matchesForUser.add(gInfo);
		}
		return matchesForUser;
	}

	@Override
	public String loadGameFromDropDown(String textStr) {
		// get the game id from the text string
		String[] splitStr = textStr.split("GameID:");
		String selectedGameID = splitStr[1];

		Match selectedMatch = ofy().load().type(Match.class)
				.id(Integer.valueOf(selectedGameID)).getValue();

		// return the state string so that we can deserialize it and load it
		return selectedMatch.getState() + "date="
				+ selectedMatch.getStartDate();
	}

	// used for serializing the game info.
	public String serializeChessGameInfo(ManderChessGameSessionInfo gInfo) {
		StringBuilder sb = new StringBuilder();

		sb.append("bClientID=" + gInfo.getbClientID());
		sb.append(":");
		sb.append("wClientID=" + gInfo.getwClientID());
		sb.append(":");
		sb.append("bEmail=" + gInfo.getbPlayerEmail());
		sb.append(":");
		sb.append("wEmail=" + gInfo.getwPlayerEmail());
		sb.append(":");
		sb.append("gameID=" + gInfo.getCurrentGameID());
		sb.append(":");
		sb.append("turn=" + gInfo.getCurrentTurn());

		return sb.toString();
	}

}