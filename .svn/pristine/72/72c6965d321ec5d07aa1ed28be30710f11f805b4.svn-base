package org.haoxiangzuo.hw6.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.haoxiangzuo.hw2.StateChangerImpl;
import org.haoxiangzuo.hw6.client.ChessService;
import org.haoxiangzuo.hw6.client.UserInfos;
import org.haoxiangzuo.hw7.Match;
import org.haoxiangzuo.hw7.Player;
import org.haoxiangzuo.hw7.StateChanger;
import org.haoxiangzuo.hw8.EloRating;
import org.shared.chess.Color;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.Move;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ChessServiceImpl extends RemoteServiceServlet implements
		ChessService {

	static {
		ObjectifyService.register(Player.class);
		ObjectifyService.register(Match.class);
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<UserInfos> queue = new ArrayList<UserInfos>();
	private ChannelService channelService = ChannelServiceFactory.getChannelService();
	private HashMap<String, String> matchedContacts = new HashMap<String, String>(); 
	private StateChangerImpl stateChangerImpl = new StateChangerImpl();
	public boolean searchQueue(UserInfos user)
	{
		if (queue.size()>0)
		{
			for (UserInfos u: queue)
			{
				if (u.getEmailAddress().equals(user.getEmailAddress()))
					return true;
			}
		}
		return false;
	}
	@Override
	public UserInfos login(String href) {
		// TODO Auto-generated method stub
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		UserInfos thisUser = new UserInfos();
		if (user == null)
			{
				thisUser.setLoggedIn(false);
				thisUser.setLoginUrl(userService.createLoginURL(href));
			}
		else
			{
				thisUser.setLoggedIn(true);
				thisUser.setLogoutUrl(userService.createLogoutURL(href));
				thisUser.setEmailAddress(user.getEmail());
				thisUser.setNickname(user.getNickname());
				String token = channelService.createChannel(user.getEmail());
				Player thisPlayer = ofy().load().type(Player.class).id(thisUser.getEmailAddress()).get();
				if (thisPlayer==null)
					thisPlayer = new Player(thisUser.getEmailAddress(),thisUser.getNickname());
				thisPlayer.setChannel(token);
				thisPlayer.setInGame(false);
				thisUser.setToken(token);
				if (!searchQueue(thisUser))
					queue.add(thisUser);
				ofy().save().entity(thisPlayer).now();
			}
		return thisUser;
	}
	@Override
	public String autoMatch(UserInfos currentUser) {
		// TODO Auto-generated method stub
		String name = currentUser.getEmailAddress();
		Key<Player> thisKey = Key.create(Player.class, name);
		Player white = ofy().load().key(thisKey).get();
		if (white.isInGame())
		{
			return "0Your are in Game! You can not to automatch now, please try Find this User to start a new game";
		}
		else
		{
			white.setInGame(true);
			ofy().save().entity(white).now();
			List<Player> others = ofy().load().type(Player.class).filter("inGame", false).list();
			if (others.isEmpty())
			{
				white.setInGame(false);
				ofy().save().entity(white).now();
				return "1No others online or they are in games! To create Game while in game, please user Find this User";
			}
			else
			{
				Random r = new Random();
				Player black = others.remove(r.nextInt(others.size()));
				Key<Player> thatKey = Key.create(Player.class, black.getEmail());
				black.setInGame(true);
				Date startDate = new Date();
				Match match = new Match(thisKey,thatKey,startDate);
				State state = new State();
				String thisState = StateChanger.stateToString(state);
				match.setState(thisState);
				ofy().save().entity(match).now();
				Long id = match.getId();
				Key<Match> matchKey = Key.create(Match.class,id);
				black.addMatch(matchKey);
				white.addMatch(matchKey);
				ofy().save().entities(white,black).now();
				String otherToken = "";
				for (UserInfos u : queue)
				{
					if (u.getEmailAddress().equals(black.getEmail()))
						otherToken = u.getToken();			
				}
				if (!otherToken.equals(""))
					channelService.sendMessage(new ChannelMessage(otherToken, "1Opponent: "+currentUser.getNickname()+" You are black Match id#"+id+"#"+"b"+"#"+match.getStartDate().getTime()+"#"+match.getState()));
				return "Opponent: "+black.getEmail()+" You are white Match id#"+id+"#"+"w"+"#"+match.getStartDate().getTime()+"#"+match.getState();
			}
		}
	}
	@Override
	public void makeMove(String move) {
		// TODO Auto-generated method stub
		String[] infos = move.split(",");
		Long id = Long.valueOf(infos[5]);
		Key<Match> matchKey = Key.create(Match.class, id);
		Match match = ofy().load().key(matchKey).get();
		String stringState = match.getState();
		State state = StateChanger.stringToState(stringState);
		Position from = new Position(Integer.valueOf(infos[0]),Integer.valueOf(infos[1]));
		Position to = new Position(Integer.valueOf(infos[2]),Integer.valueOf(infos[3]));
		Move thisMove;
		switch (Integer.valueOf(infos[4])) {
		case 0:
			thisMove = new Move(from, to, PieceKind.QUEEN);
			break;
		case 1:
			thisMove = new Move(from, to, PieceKind.KNIGHT);
			break;
		case 2:
			thisMove = new Move(from, to, PieceKind.ROOK);
			break;
		case 3:
			thisMove = new Move(from, to, PieceKind.BISHOP);
			break;
		default:
			thisMove = new Move(from, to, null);
			break;
		}
		stateChangerImpl.makeMove(state, thisMove);
		System.out.println(state.getTurn());
		stringState = StateChanger.stateToString(state);
		if (state.getGameResult()!=null)
		{
			match.setOver(true);
			Key<Player> whiteKey = match.getWhite();
			Key<Player> blackKey = match.getBlack();
			Player white = ofy().load().key(whiteKey).get();
			Player black = ofy().load().key(blackKey).get();
			white.setInGame(false);
			black.setInGame(false);
			if (state.getGameResult().getWinner().equals(Color.WHITE))
			{
				int rankWhite = EloRating.computeElo(white.getRank(), black.getRank(), 1);
				int rankBlack = EloRating.computeElo(black.getRank(), white.getRank(), 0);
				white.setRank(rankWhite);
				black.setRank(rankBlack);
			}
			else if (state.getGameResult().getWinner().equals(Color.BLACK))
			{
				int rankWhite = EloRating.computeElo(white.getRank(), black.getRank(), 0);
				int rankBlack = EloRating.computeElo(black.getRank(), white.getRank(), 1);
				white.setRank(rankWhite);
				black.setRank(rankBlack);
			}
			else
			{
				int rankWhite = EloRating.computeElo(white.getRank(), black.getRank(), 0.5);
				int rankBlack = EloRating.computeElo(black.getRank(), white.getRank(), 0.5);
				white.setRank(rankWhite);
				black.setRank(rankBlack);
			}
			ofy().save().entities(white,black);
		}
		match.setState(stringState);
		Key<Player> opponent;
		if (state.getTurn().equals(Color.BLACK))
			opponent = match.getBlack();
		else
			opponent = match.getWhite();
		ofy().save().entity(match).now();
		Player otherPlayer = ofy().load().key(opponent).get();
		String otherToken = "";
		for (UserInfos u : queue)
		{
			if (u.getEmailAddress().equals(otherPlayer.getEmail()))
				otherToken = u.getToken();			
		}
		if (!otherToken.equals(""))
			channelService.sendMessage(new ChannelMessage(otherToken, "0"+infos[0]+","+infos[1]+","+infos[2]+","+infos[3]+","+infos[4]+","+id));
	}
	@Override
	public String findOpponent(String email, UserInfos currentUser) {
		// TODO Auto-generated method stub
		Player black = ofy().load().type(Player.class).id(email).get();
		String[] name = email.split("@");
		if (black==null)
			black = new Player(email,name[0]);
		black.setInGame(true);
		Player white = ofy().load().type(Player.class).id(currentUser.getEmailAddress()).get();
		white.setInGame(true);
		ofy().save().entities(black,white).now();
		Key<Player> thisKey = Key.create(Player.class, white.getEmail());
		Key<Player> thatKey = Key.create(Player.class, black.getEmail());
		Date startDate = new Date();
		Match match = new Match(thisKey,thatKey,startDate);
		State state = new State();
		String thisState = StateChanger.stateToString(state);
		match.setState(thisState);
		ofy().save().entity(match).now();
		Long id = match.getId();
		Key<Match> matchKey = Key.create(Match.class,id);
		black.addMatch(matchKey);
		white.addMatch(matchKey);
		ofy().save().entities(white,black).now();
		String otherToken = "";
		for (UserInfos u : queue)
		{
			if (u.getEmailAddress().equals(black.getEmail()))
				otherToken = u.getToken();			
		}
		if (!otherToken.equals(""))
			channelService.sendMessage(new ChannelMessage(otherToken, "2Opponent: "+currentUser.getNickname()+" You are black Match id#"+id+"#"+"b"+"#"+match.getStartDate().getTime()+"#"+match.getState()));
		return "Opponent: "+black.getEmail()+" You are white Match id#"+id+"#"+"w"+"#"+match.getStartDate().getTime()+"#"+match.getState();
	}
	@Override
	public ArrayList<String> getMatches(UserInfos currentUser) {
		// TODO Auto-generated method stub
		String email = currentUser.getEmailAddress();
		Query<Match> matches = ofy().load().type(Match.class);
		ArrayList<String> matchesList = new ArrayList<String>();
		for (Match m : matches)
		{
			if (m.getBlack()!=null&&m.getWhite()!=null)
			{
				Player white = ofy().load().key(m.getWhite()).get();
				Player black = ofy().load().key(m.getBlack()).get();
				if (white.getEmail().equals(email))
					matchesList.add("Opponent: "+black.getEmail()+" You are white Match id#"+m.getId()+"#"+m.getStartDate().getTime()+"#"+m.getState());
				else if (black.getEmail().equals(email))
					matchesList.add("Opponent: "+white.getEmail()+" You are black Match id#"+m.getId()+"#"+m.getStartDate().getTime()+"#"+m.getState());
			}
			else if (m.getBlack()==null||m.getWhite()==null)
			{
				matchesList.add("Opponent deleted the match! You are white Match id#"+m.getId()+"#"+m.getStartDate().getTime()+"#"+m.getState());
			}
		}
		return matchesList;
	}
	@Override
	public String loadMatch(String infos) {
		// TODO Auto-generated method stub
		String[] messages = infos.split("#");
		String thisUserEmail = messages[0];
		Long matchId = Long.valueOf(messages[1]);
		Match match = ofy().load().type(Match.class).id(matchId).get();
		String state = match.getState();
		String color = "";
		Key<Player> whiteKey = match.getWhite();
		Key<Player> blackKey = match.getBlack();
		if (whiteKey!=null&&blackKey!=null)
		{
		Player white = ofy().load().key(whiteKey).get();
		Player black = ofy().load().key(blackKey).get();
		String opponent="";
		if (thisUserEmail.equals(white.getEmail()))
		{
			color="w";
			opponent = black.getEmail();
		}
		else if (thisUserEmail.equals(black.getEmail()))
		{
			color="b";
			opponent = white.getEmail();
		}
		String result = state+"#"+color+"#"+matchId+"#"+opponent+"#"+match.getStartDate().getTime();
		return result;
		}
		else
		{
			if (whiteKey!=null)
			{
				color="w";
			}
			else
			{
				color="b";
			}
			String result = state+"#"+color+"#"+matchId+"#"+"delete this Match"+"#"+match.getStartDate().getTime();
			return result;
		}
	}
	@Override
	public void deleteMatch(String info) {
		// TODO Auto-generated method stub
		String[] messages = info.split("#");
		Player thisPlayer = ofy().load().type(Player.class).id(messages[0]).get();
		Key<Match> matchKey = Key.create(Match.class,Long.valueOf(messages[1]));
		thisPlayer.deleteMatch(matchKey);
		if (thisPlayer.getMatches().isEmpty())
			thisPlayer.setInGame(false);
		ofy().save().entity(thisPlayer).now();
		Match match = ofy().load().key(matchKey).get();
		Key<Player> whiteKey = match.getWhite();
		Key<Player> blackKey = match.getBlack();
		if ((whiteKey==null&&blackKey!=null)||(whiteKey!=null&&blackKey==null))
		{
			ofy().delete().entity(match).now();
		}
		else
		{
			Player white = ofy().load().key(whiteKey).get();
			if (white.getEmail().equals(messages[0]))
				match.setWhite(null);
			else
				match.setBlack(null);
			ofy().save().entity(match).now();
		}
	}
	@Override
	public String showRank(String email) {
		// TODO Auto-generated method stub
		Player thisPlayer = ofy().load().type(Player.class).id(email).get();
		return thisPlayer.getRank()+"";
	}
	@Override
	public void deleteUserFromQueue(String id) {
		// TODO Auto-generated method stub
		int num = -1;
		for (int i=0; i<queue.size(); i++)
		{
			if (queue.get(i).getEmailAddress().equals(id))
				num = i;
		}
		if (num!=-1)
			queue.remove(num);
	}
	@Override
	public UserInfos fakeLogin(String email) {
		// TODO Auto-generated method stub
		UserInfos thisUser = new UserInfos();
		thisUser.setLoggedIn(true);
		thisUser.setLogoutUrl("");
		thisUser.setEmailAddress(email);
		thisUser.setNickname(email);
		String token = channelService.createChannel(email);
		Player thisPlayer = ofy().load().type(Player.class).id(thisUser.getEmailAddress()).get();
		if (thisPlayer==null)
			thisPlayer = new Player(thisUser.getEmailAddress(),thisUser.getNickname());
		thisPlayer.setChannel(token);
		thisPlayer.setInGame(false);
		thisUser.setToken(token);
		if (!searchQueue(thisUser))
			queue.add(thisUser);
		ofy().save().entity(thisPlayer).now();
		return thisUser;
	}
//	@Override
//	public void saveContacts(String id, ArrayList<String> contacts) {
//		// TODO Auto-generated method stub
//		String contactsNew = "";
//		for (String s : contacts)
//		{
//			contactsNew += s+",";
//		}
//		matchedContacts.put(id, contactsNew);
//		Contacts contact = new Contacts(id, contactsNew);
//		ofy().save().entity(contact).now();
//	}
//	@Override
//	public String findContacts(String id) {
//		// TODO Auto-generated method stub
//		String contacts = matchedContacts.get(id);
//		Contacts contact = ofy().load().type(Contacts.class).id(id).get();
//		if (!contact.getContacts().equals(""))
//			return contact.getContacts();
//		return contacts;
//	}
}
