package org.longjuntan.hw7;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.VoidWork;

public class objectifyDAO implements DAO {

	@Override
	public Match getMatch(String matchId) {
		Match match = ofy().load().type(Match.class).id(matchId).get();
		return match;
	}

	@Override
	public void savePlayer(Player player) {
		ofy().save().entities(player).now();
	}
	

	@Override
	public Player getPlayer(String email) {
		Player player = ofy().load().type(Player.class).id(email).get();
		return player;
	}

	@Override
	public void createMatchTransction(final Match match, final Player white,
			final Player black) {
		ofy().transact(new VoidWork() {

			@Override
			public void vrun() {
				match.setBlack(black);
				match.setWhite(white);
	

				white.matches.add(Key.create(match));
				black.matches.add(Key.create(match));
				
//				white.channels.put(match.matchId, whiteToken);
//				black.channels.put(match.matchId, blackToken);
				
				System.out.println("createMatchTransction:");
				System.out.println(white.email);
				System.out.println(black.email);
				

				ofy().save().entities(white,black).now();
				ofy().save().entity(match).now();
//				ofy().save().entity(black).now();
			}
		});

	}

	@Override
	public List<Player> getPlayer() {
//		ofy().load().type(Player.class).list();
		// TODO Auto-generated method stub
		return ofy().load().type(Player.class).list();
	}

	@Override
	public void clearChannels(String email) {
		Player player = getPlayer(email);
		player.getChannels().clear();
		savePlayer(player);
		// TODO Auto-generated method stub		
	}

	@Override
	public void saveMatch(Match current) {
		ofy().save().entity(current).now();
		// TODO Auto-generated method stub
		
	}

/*	@Override
	public List<Match> getMatch(Key<Match> key) {
		ofy().load().type(Match.class).list();
		// TODO Auto-generated method stub
		return null;
	}*/

}
