package org.yuanjia.hw7;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelPresence;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ConnectServlet extends HttpServlet {
	static {
		ObjectifyService.register(Match.class);
		ObjectifyService.register(Player.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		ChannelPresence presence = channelService.parsePresence(req);
		
		String clientId = presence.clientId();
		String userEmail = clientId.split("$r=")[0]; //remove the random part
		
		Player player = ofy().load().type(Player.class).id(userEmail).get();
		
		if(presence.isConnected()){
			player.addToken(channelService.createChannel(clientId));
		}else{
			player.getTokens().remove(channelService.createChannel(clientId));
		}
	
		ofy().save().entity(player).now();
	}

}
