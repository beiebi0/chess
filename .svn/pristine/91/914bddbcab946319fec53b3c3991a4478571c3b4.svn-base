package org.bohouli.hw6.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bohouli.hw6.UserInformation;
import org.bohouli.hw6.client.BohouChessService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class BohouChessServiceImpl extends RemoteServiceServlet implements
		BohouChessService {

	private static final long serialVersionUID = 1L;
	UserService userService = UserServiceFactory.getUserService();
	ChannelService channelService = ChannelServiceFactory.getChannelService();

	private static Queue<String> waitingPlayers = new LinkedList<String>();
	private static Map<Long, ArrayList<String>> contacts = new HashMap<Long, ArrayList<String>>();

	static {
		ObjectifyService.register(BohouMatch.class);
		ObjectifyService.register(BohouPlayer.class);
	}
	
	@Override
	public void sendContacts(long key, ArrayList<String> emails) {
		contacts.put(key, emails);
	}

	//rpc call this function and it returns user information
	@Override
	public UserInformation login(String requestUri) {
		User user = userService.getCurrentUser();
		UserInformation loginInfo = new UserInformation();

		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(user.getEmail());
			loginInfo.setNickname(user.getNickname());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
			loginInfo.setToken(channelService.createChannel(user.getEmail()));

			BohouPlayer player = ofy().load().type(BohouPlayer.class).id(user.getEmail()).get();
			if (player == null) {
				player = new BohouPlayer();
			}
			player.setEmail(loginInfo.getEmailAddress());
			player.setName(loginInfo.getNickname());
			player.setLoggedIn(true);
			ofy().save().entity(player).now();
			loginInfo.setRanking(player.getRating());
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}
	
	@Override
	public void makeAutoMatch(final String state) {
		if (!userService.isUserLoggedIn())
			return;

		final String email = userService.getCurrentUser().getEmail();
		if (waitingPlayers.contains(email))
			waitingPlayers.remove(email);

		if (!waitingPlayers.isEmpty()) {
			final String otherEmail = waitingPlayers.poll();
			
			ofy().transact(new VoidWork() {
				@Override
				public void vrun() {
					//create a match
					BohouMatch match = new BohouMatch();
					match.setMatchKey(UUID.randomUUID().toString());
					match.setState(state);
					match.setWhitePlayer(email);
					match.setBlackPlayer(otherEmail);
					Date date = new Date();
					match.setDate(date);

					//update players
					BohouPlayer player = ofy().load().type(BohouPlayer.class).id(email).get();
					player.addMatch(Key.create(match));		
					BohouPlayer otherPlayer = ofy().load().type(BohouPlayer.class).id(otherEmail).get();
					otherPlayer.addMatch(Key.create(match));
					
					//save match and players
					ofy().save().entity(match).now();
					ofy().save().entities(player, otherPlayer).now();

					channelService.sendMessage(new ChannelMessage(player.getEmail(), 
							"2#W#" + otherPlayer.getEmail() + "#" + match.getMatchKey() + "#"));
					channelService.sendMessage(new ChannelMessage(otherPlayer.getEmail(), 
							"2#B#" + player.getEmail() + "#" + match.getMatchKey() + "#"));
				}
			});				
		} else {
			waitingPlayers.add(email);
		}
	}
	
	@Override
	public void makeSelectMatch(final String otherEmail, final String state) {
		if (!userService.isUserLoggedIn())
			return;
		final String email = userService.getCurrentUser().getEmail();
		
		ofy().transact(new VoidWork() {
			@Override
			public void vrun() {
				BohouPlayer player = ofy().load().type(BohouPlayer.class).id(email).get();
				if (player == null)
					player = new BohouPlayer(email, userService.getCurrentUser().getNickname());
				
				BohouPlayer otherPlayer = ofy().load().type(BohouPlayer.class).id(otherEmail).get();
				if (otherPlayer == null) {
					otherPlayer = new BohouPlayer();
					otherPlayer.setEmail(otherEmail);
				}

				//Create match
				BohouMatch match = new BohouMatch();
				match.setWhitePlayer(email);
				match.setBlackPlayer(otherEmail);
				match.setMatchKey(UUID.randomUUID().toString());
				match.setState(state);
				Date date = new Date();
				match.setDate(date);
				
				player.addMatch(Key.create(match));
				otherPlayer.addMatch(Key.create(match));
				
				ofy().save().entity(match).now();
				ofy().save().entities(player, otherPlayer).now();

				if (player.isLoggedIn())
					channelService.sendMessage(new ChannelMessage(player.getEmail(), 
							"2#W#" + otherPlayer.getEmail() + "#" + match.getMatchKey() + "#"));
				if (otherPlayer.isLoggedIn())
					channelService.sendMessage(new ChannelMessage(otherPlayer.getEmail(), 
							"2#B#" + player.getEmail() + "#" + match.getMatchKey() + "#"));
			}
		});
		
				
	}

	//When player make a move, he sends it to server
	@Override
	public void sendState(final String matchKey, final String state, final String win) {
		if (userService.isUserLoggedIn()) {
			ofy().transact(new VoidWork() {
				@Override
				public void vrun() {
					BohouMatch match = ofy().load().type(BohouMatch.class).id(matchKey).get();

					if (match != null) {
						match.setState(state);
						ofy().save().entity(match).now();

						String email = userService.getCurrentUser().getEmail();
						String otherEmail = match.getOpponent(email);				
						BohouPlayer otherPlayer = ofy().load().type(BohouPlayer.class).id(otherEmail).get();
						
						if(win == null) {
							if (otherPlayer.isLoggedIn()) {
								String message = "1#" + match.getMatchKey() + "#" + state + "#";
								channelService.sendMessage(new ChannelMessage(otherEmail, message));
							}	
						} else {
							String winner;
							String loser;
							double win_s = 1;
							double lose_s = 0;
							
							if(win.equals("W")) {
								winner = match.getWhitePlayer();
								loser = match.getBlackPlayer();
							} else {
								if(win.equals("D")) {
									win_s = 0.5;
									lose_s = 0.5;
								}
								winner = match.getBlackPlayer();
								loser = match.getWhitePlayer();
							} 
							
							BohouPlayer winPlayer = ofy().load().type(BohouPlayer.class).id(winner).get();
							BohouPlayer losePlayer = ofy().load().type(BohouPlayer.class).id(loser).get();
							System.out.println(winner);
							System.out.println(loser);
							if(winPlayer == null)
								System.out.println("win null");
							if(losePlayer == null) 
								System.out.println("lose null");
							
							Date now = new Date();
		                    int win_dur = (int)((winPlayer.getTime().getTime() - now.getTime()) / 
		                    		(24 * 60 * 60 * 1000));
		                    int lose_dur = (int)((losePlayer.getTime().getTime() - now.getTime()) / 
		                    		(24 * 60 * 60 * 1000));
		                    
		                    double win_rate = BohouRank.Calculate(winPlayer.getRating(), 
		                    		losePlayer.getRating(), winPlayer.getRD(), win_dur, win_s);
		                    double lose_rate = BohouRank.Calculate(losePlayer.getRating(), 
		                    		winPlayer.getRating(), losePlayer.getRD(),lose_dur, lose_s);
		                    winPlayer.setRating(win_rate);
		                    losePlayer.setRating(lose_rate);
		                    
		                    winPlayer.setTime(now);
		                    losePlayer.setTime(now);
		                    winPlayer.setRD(BohouRank.getRD(win_dur, winPlayer.getRD()));
		                    losePlayer.setRD(BohouRank.getRD(lose_dur, losePlayer.getRD()));
		                    ofy().save().entity(winPlayer).now();
		                    ofy().save().entity(losePlayer).now();
							
		                    if(winPlayer.isLoggedIn()) {
		                    	String win_message = "5#" + match.getMatchKey() + "#" + state + "#"
		    							+ win_rate + "#";
		                    	channelService.sendMessage(new ChannelMessage(winner, win_message));
		                    }
							if(losePlayer.isLoggedIn()) {
								String lose_message = "5#" + match.getMatchKey() + "#" + state + "#"
										+ lose_rate + "#";
								channelService.sendMessage(new ChannelMessage(loser, lose_message));
							}
						}
					}
				}
			});	
		}
	}

	//Player load demand match state from the server
	@Override
	public void getState(String matchKey) {
		if (!userService.isUserLoggedIn())
			return;
		String email = userService.getCurrentUser().getEmail();
		
		BohouMatch match = ofy().load().type(BohouMatch.class).id(matchKey).getValue();
		if(match == null)
			return;
		
		String otherEmail = match.getOpponent(email);
		
		String message = "3#";
		if(match.getWhitePlayer().equals(email))
			message += "W#";
		else
			message += "B#";
		message += otherEmail + "#";
		message += matchKey + "#";
		message += match.getState() + "#";
		channelService.sendMessage(new ChannelMessage(email, message));
	}
	
	//Load all the matches belongs to this player
	@Override
	public void loadMatches() {
		if (!userService.isUserLoggedIn())
			return;
		System.out.println("load matches");
		String email = userService.getCurrentUser().getEmail();
		BohouPlayer player = ofy().load().type(BohouPlayer.class).id(email).get();
		if(player == null)
			return;
		String message = "4";
		for(Key<BohouMatch> matchKey : player.getMatches()) {
			BohouMatch match = ofy().load().key(matchKey).get();
			if(match != null) {
				message += "#" + match.getMatchKey() + " " + match.getOpponent(email);
			}
		}
		message += "#";
		System.out.println(message);
		System.out.println(email);
		channelService.sendMessage(new ChannelMessage(email, message));
	}
	
	//Delete a match from the player information, if both players delete then delete match
	@Override
	public void deleteMatch(final String matchKey) {
		if (!userService.isUserLoggedIn())
			return;
		final String email = userService.getCurrentUser().getEmail();

		ofy().transact(new VoidWork() {
			@Override
			public void vrun() {
				BohouPlayer player = ofy().load().type(BohouPlayer.class).id(email).get();
				if (player != null) {
					player.getMatches().remove(Key.create(BohouMatch.class, matchKey));
					ofy().save().entity(player);

					BohouMatch match = ofy().load().type(BohouMatch.class).id(matchKey).get();
					if (match == null)
						return;

					if (match.isDeleted())
						ofy().delete().entity(match);
					else {
						match.setDeleted(true);
						ofy().save().entity(match);
					}
				}
			}
		});
		
	}
	
	//Set off line when player logs out or disconnects
	public static void setOffline(String email) {
		System.out.println("we log out...");
        BohouPlayer player = ofy().load().type(BohouPlayer.class).id(email).get();
        if(player == null)
        	return;
        player.setLoggedIn(false);
        player.setTokens(null);
        ofy().save().entity(player).now();
        if (waitingPlayers.contains(email)) {
        	waitingPlayers.remove(email);
        }
	}
	
	@Override
	public Date getDate(String matchKey) {
		BohouMatch match = ofy().load().type(BohouMatch.class).id(matchKey).get();
		if (match == null)
			return null;
		else
			return match.getDate();
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
}
