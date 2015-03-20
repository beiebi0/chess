package org.paulsultan.hw7;

import org.paulsultan.hw7.Player;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Match {
	@Id Long matchId;
	Key<Player> whitePlayer;
	Key<Player> blackPlayer;
	String state;
	String startDate; //YYYYMMDD
	
	public Match(){}
	public Match(Key<Player> white, Key<Player> black, String state, String startDate){
		this.whitePlayer = white;
		this.blackPlayer = black;
		this.state = state;
		this.startDate = startDate;
	}
		
	public void setMatchId(Long matchId){
		this.matchId = matchId;
	}
	public Long getMatchId(){
		return matchId;
	}
	
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}
	
	public void setWhitePlayer(Key<Player> white){
		this.whitePlayer = white;
	}
	public Key<Player> getWhitePlayer(){
		return whitePlayer;
	}
	public void setBlackPlayer(Key<Player> black){
		this.blackPlayer = black;
	}
	public Key<Player> getBlackPlayer(){
		return blackPlayer;
	}
	
	public Key<Player> getOpponent(Key<Player> player){
		if (player.equals(whitePlayer))
			return blackPlayer;
		else
			return whitePlayer;
	}
	
	public String getStartDate(){
		return startDate;
	}
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}
}
