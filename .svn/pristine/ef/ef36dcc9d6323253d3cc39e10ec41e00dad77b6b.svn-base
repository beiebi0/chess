package org.haoxiangzuo.hw7;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.haoxiangzuo.hw6.client.ChessService;
import org.haoxiangzuo.hw6.client.ChessServiceAsync;
import org.haoxiangzuo.hw7.Match;
import org.haoxiangzuo.hw7.Player;

import com.google.appengine.api.channel.ChannelPresence;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.objectify.ObjectifyService;

public class DisconnectServlet  extends HttpServlet {
		static {
			ObjectifyService.register(Match.class);
			ObjectifyService.register(Player.class);
		}
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
			ChannelService channelService = ChannelServiceFactory.getChannelService();
			ChannelPresence presence = channelService.parsePresence(request);
			
			Player player = ofy().load().type(Player.class).id(presence.clientId()).get();
			
			ChessServiceAsync chessService = GWT.create(ChessService.class);
			
			if (player == null) {
				throw new IllegalArgumentException("Error Player");
			}

			if(!presence.isConnected()) {
				player.setInGame(true);
				chessService.deleteUserFromQueue(player.getEmail(), new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						
					}});
			} 
			ofy().save().entity(player).now();
		}
}
