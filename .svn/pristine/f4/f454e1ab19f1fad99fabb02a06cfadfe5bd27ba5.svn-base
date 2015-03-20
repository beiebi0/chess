package org.chenji.hw6.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.chenji.hw6.client.GameService;
import org.chenji.hw6.client.LoginInfo;
import org.chenji.hw6.client.Match;
import org.chenji.hw6.client.Player;
import org.shared.chess.Color;
import org.shared.chess.State;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GameServiceImpl extends RemoteServiceServlet implements GameService {

	/**
   * 
   */
  private static final long serialVersionUID = -1138122470165028454L;
  private static int num = 0;
  private ChannelService channelService = ChannelServiceFactory.getChannelService();
	private Map<String, Player> players = new HashMap<String, Player>();
	private Map<String, Match> matches = new HashMap<String, Match>();
	private Match newMatch = null;
	Logger logger = Logger.getLogger(GameServiceImpl.class.toString());
	@Override
	public Player connect(LoginInfo loginInfo) {
		Player player = new Player();
		player.setPlayerId(loginInfo.getEmailAddress() + num++);
		player.setNickname(loginInfo.getNickname());
		String token = channelService.createChannel(player.getPlayerId());
		player.setToken(token);
		players.put(player.getPlayerId(), player);
		if (newMatch == null) {
		  newMatch = new Match();
			player.setColor(Color.WHITE);
			player.setWating(true);
			newMatch.setPlayer(0, player.getPlayerId());
      matches.put(player.getPlayerId(), newMatch);
		} else {
		  player.setColor(Color.BLACK);
		  player.setWating(false);
      newMatch.setPlayer(1, player.getPlayerId());
      matches.put(player.getPlayerId(), newMatch);
      newMatch = null;
    }
		logger.log(Level.INFO, "server login: " + player.getPlayerId());
		return player;
	}

	@Override
	public void updateState(String clientId, String state) {
		Match match = matches.get(clientId);
		logger.log(Level.INFO, "match: " + match.getPlayer(0));
		channelService.sendMessage(new ChannelMessage(match.getPlayer(0), state));
		channelService.sendMessage(new ChannelMessage(match.getPlayer(1), state));
	}
}
