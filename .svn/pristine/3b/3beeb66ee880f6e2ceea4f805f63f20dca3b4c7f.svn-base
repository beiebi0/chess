package org.mengyanhuang.hw7;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Match implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//match id (key), the keys of the two players, the game state, 
	//which player has the current turn, and whether the match is over (and who won).
	@Id long matchID;
    String blackemail = "";
    String whiteemail = "";
    String state;
    boolean isWhiteTurn = true;
    String result = "";
    Date startDate;
    
    boolean isWdeleteMatch = false;
    boolean isBdeleteMatch = false;
    
    public Match() {
	}
    
    public Match(long matchId, String playerAemail, String playerBemail, String state,
    		boolean isWhiteTurn) {
		this.matchID = matchId;
		this.whiteemail = playerAemail;
		this.blackemail = playerBemail;
		this.state = state;
		this.isWhiteTurn = isWhiteTurn;
		this.startDate = new Date();
	}
    
    public long getMatchid() {
		return matchID;
	}
    
	public void setMatchid(long matchid) {
		this.matchID = matchid;
	}
    
	public String getWhite(){
		return whiteemail;
	}
	
	public void setWhite(String useremail){
		this.whiteemail = useremail;
	}
	
	public String getBlack(){
		return blackemail;
	}
	
	public void setBlack(String useremail){
		this.blackemail = useremail;
	}
	
    public String getTurn(){
    	if(isWhiteTurn) return "White";
    	else return "Black";
    }
    
    public boolean isWhiteTurn(){
    	return isWhiteTurn;
    }
    
    public void setWhiteTurn(boolean a){
    	this.isWhiteTurn = a;
    }
    
    public void setState(String state){
    	this.state = state;
    }
    
    public String getState() {
		return state;
	}
    
    public void setResult(String result){
    	this.result = result;
    }
    
    public String getResult() {
		return result;
	}
    
    public boolean isWdeleteMatch() {
		return isWdeleteMatch;
	}
    
	public void setWdeleteMatch(boolean deletepA) {
		this.isWdeleteMatch = deletepA;
	}
	
	public boolean isBdeleteMatch() {
		return isBdeleteMatch;
	}
	
	public void setBDeleteMatch(boolean deletepB) {
		this.isBdeleteMatch = deletepB;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date date){
		this.startDate = date;
	}
    
}
