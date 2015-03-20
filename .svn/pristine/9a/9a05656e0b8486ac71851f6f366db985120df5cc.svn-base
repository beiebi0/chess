package org.simongellis.hw7.client;

import java.util.Date;

import org.shared.chess.Color;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Match {

	@Id Long id;
	Key<Player> white;
	Key<Player> black;
	Date startDate;
	String state;
	
	public Match() {}
	
	public Match(Key<Player> white, Key<Player> black, Date startDate, String state) {
		this.white = white;
		this.black = black;
		this.startDate = startDate;
		this.state = state;
	}
	
	public Long getId() {
		return id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getStartDate() {
		return startDate;
	}
	
	public Key<Player> getOpponent(Key<Player> player) {
		if (player.equals(white))
			return black;
		else
			return white;
	}
	
	public Color getPlayerColor(Key<Player> player) {
		if (player.equals(white))
			return Color.WHITE;
		else
			return Color.BLACK;
	}
}
