package org.bohouli.hw6.server;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class BohouMatch implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	String matchKey = null;
	String whitePlayer = null;
	String blackPlayer = null;
	String state = null;
	String turn = null;
	Date date = null;
	boolean gameOver = false;
	String winner = null;
	boolean isDeleted = false;

	public BohouMatch() {}
	
	public BohouMatch(String matchKey) {
		this.matchKey = matchKey;
	}
	
	public String getOpponent(String player) {
		if(player.equals(whitePlayer))
			return blackPlayer;
		else
			return whitePlayer;
	}
	
	public String getMatchKey() {
		return matchKey;
	}

	public void setMatchKey(String matchKey) {
		this.matchKey = matchKey;
	}

	public String getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(String whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public String getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(String blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

