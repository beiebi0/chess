package org.paulsultan.hw7;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paulsultan.hw6.server.ChessServiceImpl;

import com.google.appengine.api.channel.ChannelPresence;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class ChannelConnectionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static{
		ObjectifyService.register(Player.class);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		ChannelPresence presence = channelService.parsePresence(request);
		
		Player player = ofy().load().type(Player.class).id(presence.clientId()).get();
		if(player == null){
			throw new IllegalArgumentException("error getting Player in channel");
		}
		if(presence.isConnected()){
			System.out.println(presence.clientId() + " connected");
		}
		else{
			ChessServiceImpl.deleteUser(player.getEmail());
			System.out.println(presence.clientId() + " disconncted");
		}
		ofy().save().entity(player).now();
	}
}