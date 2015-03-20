package org.paulsultan.hw6.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paulsultan.hw6.LoginInfo;
import org.paulsultan.hw6.ServerOffException;
import org.paulsultan.hw6.client.ChessService;
import org.paulsultan.hw7.Match;
import org.paulsultan.hw7.Player;
import org.paulsultan.hw3.StateSerializer;
import org.shared.chess.Color;
import org.shared.chess.State;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ChessServiceImpl extends RemoteServiceServlet implements ChessService {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ChessServiceImpl.class.getName());
	
	public static boolean serverStatus = true;
	public static ArrayList<LoginInfo> loginList = new ArrayList<LoginInfo>();
	public static ChannelService channelService = ChannelServiceFactory.getChannelService();
	
	static {
		ObjectifyService.register(Player.class);
		ObjectifyService.register(Match.class);
	}
	
	static public void addUser(LoginInfo login){
		log.info("adding to queue");
		//add player when he logs in
		boolean inList=false;
		if (loginList.size()>0){
			for (LoginInfo eachUser: loginList){
				if (eachUser.getEmailAddress().equals(login.getEmailAddress()))
					inList=true;
			}
		}
		if(!inList)
			loginList.add(login);
		
		//alert all other users
		for (LoginInfo eachUser: loginList){
			channelService.sendMessage(new ChannelMessage(eachUser.getEmailAddress(), "3"+login.getEmailAddress()));
		}

	}
	static public void deleteUser(String email){
		log.info("removing from queue");
		//remove player when he logs out
		int num = -1;
		for (int i=0; i<loginList.size(); i++){
			if (loginList.get(i).getEmailAddress().equals(email))
				num = i;
		}
		if (num!=-1)
			loginList.remove(num);
		
		//alert all other users
		for (LoginInfo eachUser: loginList){
			channelService.sendMessage(new ChannelMessage(eachUser.getEmailAddress(), "4"+email));
		}
	}
    public static String getDate(){
        Calendar cal = Calendar.getInstance();
        String year =Integer.toString(cal.get(Calendar.YEAR));
        String month = Integer.toString(cal.get(Calendar.MONTH)+1);
        String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        month = (month.length()==1) ? "0"+month : month;
        day = (day.length()==1) ? "0"+day : day;
        return  year+month+day;
    }
	
	@Override
	public ArrayList<String> getOtherPlayers(LoginInfo currentUser){
		//alert all other users
		ArrayList<String> players = new ArrayList<String>();
		for (LoginInfo eachUser: loginList){
			if(!(eachUser.equals(currentUser)))
				players.add(eachUser.getEmailAddress());
		}
		return players;
	}	
	@Override
	public LoginInfo login(String email){
	    LoginInfo login = new LoginInfo();

		//user is logged in
		String token = channelService.createChannel(email);
		login.setChannelToken(token);
		login.setEmailAddress(email);
		login.setLoggedIn(true);
		
		//add player to datastore
		Player player = ofy().load().type(Player.class).id(login.getEmailAddress()).get();
		if (player==null)
			player = new Player(login.getEmailAddress());
		player.setInGame(false);
		ofy().save().entity(player).now();		

		ChessServiceImpl.addUser(login);	
		
		return login;
	}

	@Override
	public String makeMove(String stringState, Long matchId) throws ServerOffException{
		if(!serverStatus){
			throw new ServerOffException();
		}
		
		//get match and update state
		Key<Match> matchKey = Key.create(Match.class, matchId);
		Match match = ofy().load().key(matchKey).get();
		match.setState(stringState);
		ofy().save().entity(match).now();
		
		//find opponent
		Key<Player> opponentKey;
		State state = StateSerializer.parse(stringState);
		if (state.getTurn().equals(Color.BLACK))
			opponentKey = match.getBlackPlayer();
		else
			opponentKey = match.getWhitePlayer();
						
		if(opponentKey!=null){
			//opponent may have deleted the game
			Player opponentPlayer = ofy().load().key(opponentKey).get();
			Player currentPlayer=ofy().load().key(match.getOpponent(opponentKey)).get();
			if(state.getGameResult()!=null){
				opponentPlayer.setInGame(false);
				currentPlayer.setInGame(false);
				
				Player blackPlayer;
				Player whitePlayer;
				//state turn color is updated so the current color is reversed 
				if(state.getTurn().equals(Color.BLACK)){
					whitePlayer = currentPlayer;
					blackPlayer = opponentPlayer;
				}
				else{
					whitePlayer = opponentPlayer;
					blackPlayer = currentPlayer;
				}
				
				double[] ranks = new double[2];
				if (state.getGameResult().getWinner().equals(Color.WHITE)){
					ranks=computeElo(whitePlayer.getRank(), 1, blackPlayer.getRank(), 0);
				}
				else if (state.getGameResult().getWinner().equals(Color.BLACK)){
					ranks=computeElo(whitePlayer.getRank(), 0, blackPlayer.getRank(), 1);
				}
				else{
					ranks=computeElo(whitePlayer.getRank(), .5, blackPlayer.getRank(), .5);
				}
				whitePlayer.setRank(ranks[0]);
				blackPlayer.setRank(ranks[1]);
				
				ofy().save().entities(opponentPlayer,currentPlayer).now();
			}
		    channelService.sendMessage(new ChannelMessage(opponentPlayer.getEmail(), "0"+stringState+"#"+matchId+"#"+opponentPlayer.getRank()));
		    return currentPlayer.getRank()+"#"+matchId;
		}
		return null;
	}
	
	@Override
	public String autoMatch(LoginInfo currentUser) throws ServerOffException{
		if(!serverStatus){
			throw new ServerOffException();
		}
		
		//make white player
		String currentEmail = currentUser.getEmailAddress();
		Key<Player> currentPlayerKey = Key.create(Player.class, currentEmail);
		Player whitePlayer = ofy().load().key(currentPlayerKey).get();
		
		List<Player> freePlayers = ofy().load().type(Player.class).filter("isInGame", false).list();
		if(freePlayers.isEmpty()){
			//no one else is free
			whitePlayer.setInGame(false);
			ofy().save().entity(whitePlayer).now();
			return "0No free players online.";
		}
		else if (freePlayers.size()==1 && freePlayers.get(0).getEmail().equals(currentEmail)){
			//no one else is free
			whitePlayer.setInGame(false);
			ofy().save().entity(whitePlayer).now();
			return "0You are the only free player.";
		}
		else{
			//start a match with a random opponent
			//find random player
			Random r = new Random();
			Player opponentPlayer = freePlayers.remove(r.nextInt(freePlayers.size()));
			if (opponentPlayer.getEmail().equals(currentEmail)){
				//picked myself
				r = new Random();
				opponentPlayer = freePlayers.remove(r.nextInt(freePlayers.size()));
			}
			Key<Player> opponentKey = Key.create(Player.class, opponentPlayer.getEmail());
			
			//build match
			String date = getDate();
			Match match = new Match(currentPlayerKey, opponentKey, StateSerializer.dump(new State()), date);
			ofy().save().entity(match).now();

			//add match to players
			Long newMatchId = match.getMatchId();
			Key<Match> matchKey = Key.create(Match.class,newMatchId);
			whitePlayer.addMatch(matchKey);
			opponentPlayer.addMatch(matchKey);
			ofy().save().entities(whitePlayer,opponentPlayer).now();
			
			channelService.sendMessage(new ChannelMessage(opponentPlayer.getEmail(), "1"+date+"#"+currentUser.getEmailAddress()+"#"+newMatchId+"#"+"b"));
			
			return date+"#"+opponentPlayer.getEmail()+"#"+newMatchId+"#"+"w";
		}
	}
	@Override
	public String startMatchWith(String newEmail, String currentUserEmail) throws ServerOffException{
		if(!serverStatus){
			throw new ServerOffException();
		}
		
		//make players
		Player whitePlayer = ofy().load().type(Player.class).id(currentUserEmail).get();
		Player blackPlayer = ofy().load().type(Player.class).id(newEmail).get();
		if (blackPlayer==null)
			blackPlayer = new Player(newEmail);
		whitePlayer.setInGame(true);
		blackPlayer.setInGame(true);
		ofy().save().entities(blackPlayer,whitePlayer).now();
		
		//make match
		Key<Player> whiteKey = Key.create(Player.class, whitePlayer.getEmail());
		Key<Player> blackKey = Key.create(Player.class, blackPlayer.getEmail());
		String date=getDate();
		Match match = new Match(whiteKey, blackKey, StateSerializer.dump(new State()), date);
		ofy().save().entity(match).now();
		
		//add players to match
		Long matchId = match.getMatchId();
		Key<Match> matchKey = Key.create(Match.class,matchId);
		blackPlayer.addMatch(matchKey);
		whitePlayer.addMatch(matchKey);
		ofy().save().entities(whitePlayer,blackPlayer).now();
		
		channelService.sendMessage(new ChannelMessage(blackPlayer.getEmail(), "2"+date+"#"+currentUserEmail+"#"+matchId+"#"+"b"));
		
		return date+"#"+blackPlayer.getEmail()+"#"+matchId+"#"+"w";
	}
	
	@Override
	public ArrayList<String> getMatches(String currentUserEmail){
		Query<Match> matches = ofy().load().type(Match.class);
		ArrayList<String> matchesList = new ArrayList<String>();
		
		for (Match eachMatch : matches){
			if (eachMatch.getBlackPlayer()==null && eachMatch.getWhitePlayer()!=null){
				//black player deleted this match
				Player remainingPlayer = ofy().load().key(eachMatch.getWhitePlayer()).get();
				if (remainingPlayer.getEmail().equals(currentUserEmail))
					matchesList.add("Deleted#w#"+eachMatch.getMatchId()+"#"+eachMatch.getState());
			}
			else if (eachMatch.getBlackPlayer()!=null && eachMatch.getWhitePlayer()==null){
				//white player deleted this match
				Player remainingPlayer = ofy().load().key(eachMatch.getBlackPlayer()).get();
				if (remainingPlayer.getEmail().equals(currentUserEmail))
					matchesList.add("Deleted#b#"+eachMatch.getMatchId()+"#"+eachMatch.getState());
			}
			else{
				//both players are still in match
				Player whitePlayer = ofy().load().key(eachMatch.getWhitePlayer()).get();
				Player blackPlayer = ofy().load().key(eachMatch.getBlackPlayer()).get();
				if (whitePlayer.getEmail().equals(currentUserEmail))
					matchesList.add(eachMatch.getStartDate()+"#"+blackPlayer.getEmail()+"#w#"+eachMatch.getMatchId()+"#"+eachMatch.getState());
				else if (blackPlayer.getEmail().equals(currentUserEmail))
					matchesList.add(eachMatch.getStartDate()+"#"+whitePlayer.getEmail()+"#b#"+eachMatch.getMatchId()+"#"+eachMatch.getState());
			}
		}
		return matchesList;
	}
	
	@Override
	public String loadMatch(String currentUserEmail, String matchId) throws ServerOffException{
		if(!serverStatus){
			throw new ServerOffException();
		}
		
		Long id = Long.valueOf(matchId);
		Match match = ofy().load().type(Match.class).id(id).get();
		if(match==null){
			//TODO: determine why this is null sometimes.  error will be shown
		}
		String state = match.getState();
		String startDate = match.getStartDate();
		
		String myColor;
		String opponent;
		Key<Player> whiteKey = match.getWhitePlayer();
		Key<Player> blackKey = match.getBlackPlayer();
		if(whiteKey==null||blackKey==null){
			//1 player deleted the match
			if(whiteKey==null){
				Player black = ofy().load().key(blackKey).get();
				black.setInGame(false);
				ofy().save().entities(black).now();
				myColor="b";
				opponent="w Deleted this Match";
			}
			else{
				Player white = ofy().load().key(whiteKey).get();
				white.setInGame(false);
				ofy().save().entities(white).now();
				myColor="w";
				opponent="b Deleted this Match";
			}
		}
		else{
			Player white = ofy().load().key(whiteKey).get();
			Player black = ofy().load().key(blackKey).get();
			if(currentUserEmail.equals(white.getEmail())){
				white.setInGame(true);
				ofy().save().entities(white).now();
				opponent=black.getEmail();
				myColor="w";
			}
			else{
				black.setInGame(true);
				ofy().save().entities(black).now();
				opponent=white.getEmail();
				myColor="b";
			}
		}
		return startDate+"#"+state+"#"+myColor+"#"+id+"#"+opponent;
	}
	@Override
	public void deleteMatch(String currentUserEmail, String deleteGameId, Boolean isInGame) throws ServerOffException{	
		if(!serverStatus){
			throw new ServerOffException();
		}
		
		//update deleting player status
		Player deletingPlayer = ofy().load().type(Player.class).id(currentUserEmail).get();
		Key<Match> matchKey = Key.create(Match.class,Long.valueOf(deleteGameId));
		deletingPlayer.removeMatch(matchKey);
		if(isInGame||deletingPlayer.getMatches().isEmpty())
			deletingPlayer.setInGame(false);
		ofy().save().entity(deletingPlayer).now();
		
		//update the match status
		Match match = ofy().load().key(matchKey).get();
		if(match==null){
			//TODO: determine why this is null sometimes.  error will be shown
		}
		
		Key<Player> blackKey = match.getBlackPlayer();
		Key<Player> whiteKey = match.getWhitePlayer();
		if (blackKey!=null&&whiteKey!=null){
            //current player is deleting the match first
            Player white = ofy().load().key(whiteKey).get();
            if (white.equals(deletingPlayer))
                match.setWhitePlayer(null);
            else
                match.setBlackPlayer(null);
            ofy().save().entity(match).now();
        }
		else if (blackKey!=null&&whiteKey==null)
			//white deleted it first, black is deleting it now
			ofy().delete().entity(match).now();
		else if (blackKey==null&&whiteKey!=null)
			//black deleted it first, white is deleting it now
			ofy().delete().entity(match).now();
		
	}
	
	@Override
	public Double getRank(String email){
		Key<Player> playerKey = Key.create(Player.class, email);
		Player player = ofy().load().key(playerKey).get();
		double rank = player.getRank();
		return rank;
	}	
	public double[] computeElo(double rankA, double resultPointsForA, double rankB, double resultPointsForB){
		double[] newRanks = new double[2];
		
		double expectedScoreForA = 1 / (1+Math.pow(10, (rankB-rankA)/400));
		newRanks[0] = rankA + 15*(resultPointsForA-expectedScoreForA);
		
		double expectedScoreForB = 1 / (1+Math.pow(10, (rankA-rankB)/400));
		newRanks[1] = rankB + 15*(resultPointsForB-expectedScoreForB);
		
		return newRanks;
	}
	
	public void setServerStatus(boolean status){
		serverStatus=status;
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
