package org.simongellis.hw7.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simongellis.hw7.client.Player;

import com.google.appengine.api.channel.ChannelPresence;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.googlecode.objectify.Key;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	ChannelService channelService = ChannelServiceFactory.getChannelService();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ChannelPresence channelPresence = channelService.parsePresence(req);
			String channelName = channelPresence.clientId();
			String userID = channelName.substring(0, channelName.lastIndexOf('_'));

			Key<Player> playerKey = Key.create(Player.class, userID);
			Player player = ofy().load().key(playerKey).get();

			if (channelPresence.isConnected())
				player.addConnection(channelName);
			else
				player.removeConnection(channelName);
			
			ofy().save().entity(player);
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
