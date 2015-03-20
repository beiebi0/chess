package org.longjuntan.hw7;

import java.io.Serializable;
import java.util.Date;

import org.longjuntan.hw2.StateChangerImpl;
import org.longjuntan.hw3.Utils;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.State;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Match implements Serializable {

	public Date getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Date initialTime) {
		this.initialTime = initialTime;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// match id (key), the keys of the two players, the game state, which player
	// has the current turn, and whether the match is over (and who won).

	@Id
	String matchId;

	@Load Ref<Player> white;
	@Load Ref<Player> black;
	String state;
	Date initialTime;
	
	public Match(){}

	public Match(Date current) {
		initialTime = current;
		this.matchId = Long.toString(initialTime.getTime());
		state = Utils.getHistory(new State());
	}

	// public Match(String matchId) {
	// initialTime = new Date();
	// // this.matchId = matchId+initialTime.toString();
	// this.matchId = Long.toString(initialTime.getTime());
	// }

	public String getMatchId() {
		return this.matchId;
	}

	public Player getWhite() {
		return white.get();
	}

	public void setWhite(Player player) {
		white = Ref.create(player);
	}

	public Player getBlack() {
		return black.get();
	}

	public void setBlack(Player player) {
		black = Ref.create(player);
	}

	public void setPlayer(Player white, Player black) {
		setWhite(white);
		setBlack(black);
	}

	public Player getOpponent(String player) {
		return player.equals(getBlack().email) ? getWhite()
				: getBlack();
	}

	public String getState() {
		return state;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
/*	public void makeMove(String moveString){
		State s = Utils.setStateByHistory(state);
		StateChangerImpl sc = new StateChangerImpl();
		Move move = Utils.getMoveFromString(moveString);
		System.out.println(move.toString());
		sc.makeMove(s, move);
		setState(Utils.getHistory(s));	
	}*/
	
	public Color getColor(String email){
		System.out.println("getColor():" + email);
		System.out.println("W: "+ white.get().toString());
		System.out.println("B: "+ black.get().toString());
		if(email.equals(white.get().toString())){		
//			return "W";
			return Color.WHITE;
		}else if(email.equals(black.get().toString())){
//			return "B";
			return Color.BLACK;
		}
		else return null;
	}
	
	public String getTurn(String email){
		State s = Utils.setStateByHistory(state);
		if(s.getTurn() == getColor(email))
			return "My Turn!";
		else
			return "Other's turn.";
	}
}
