package org.longjuntan.hw7;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.longjuntan.hw6.server.GameServiceImpl;

import com.google.appengine.api.channel.ChannelPresence;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

public class PresenceServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Handle request and write response
		ChannelService channelService = ChannelServiceFactory
				.getChannelService();
		ChannelPresence presence = channelService.parsePresence(req);
		String email = presence.clientId().split(" ")[0];
		
		if(presence.isConnected()) {
//			player.getChannels().add(presence.clientId());
//			GameServiceImpl.addChannel(email);
			System.out.println(presence.clientId() + " connected");
			
		} else {
//			player.getChannels().remove(presence.clientId());
			System.out.println(email);
//			GameServiceImpl.disconnect(email);
			GameServiceImpl.removeChannel(email,presence.clientId());
			System.out.println(presence.clientId() + " disconncted");
		}

//		DataOperation.delWaitingPlayer(email);
	}
}
