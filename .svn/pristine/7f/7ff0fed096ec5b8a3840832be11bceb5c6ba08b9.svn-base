package org.haoxiangzuo.hw7;

import java.util.Date;

import org.shared.chess.Color;
import org.haoxiangzuo.hw7.Player;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Match {
	@Id Long matchId;
	Key<Player> white;
	Key<Player> black;
	String state;
	boolean over;
	Date startDate;
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Key<Player> getWhite() {
		return white;
	}

	public void setWhite(Key<Player> white) {
		this.white = white;
	}

	public Key<Player> getBlack() {
		return black;
	}

	public void setBlack(Key<Player> black) {
		this.black = black;
	}

	public Match() {}
	
	public Match(Key<Player> white, Key<Player> black, Date startDate) {
		this.white = white;
		this.black = black;
		this.state = "";
		this.over = false;
		this.startDate = startDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getId() {
		return matchId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public Key<Player> getOpponent(Key<Player> player) {
		
		return player.equals(white)?black:white;
	}
	
	public Color getColor(Key<Player> player) {		
		return player.equals(white)?Color.WHITE:Color.BLACK;
	}
}
