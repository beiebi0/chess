package org.mengyanhuang.hw7;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

@Entity
public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id String email;
	String name;
    //String token;
    String state;
    boolean isWaiting;
    boolean isWhite;
    List<String> tokens = new LinkedList<String>();
    @Serialize List<Match> matches = new LinkedList<Match>();
    int rank = 1500;
    int RD = 350;
    long lastplayedtime = 0;
    
    public Player() {}
    
    public Player(String userEmail, String username) {
		this.email = userEmail;
		this.name = username;	
	}
    
    public List<Match> getMatchList() {
		return matches;
	}
    
    public void addMatch(Match match){
    	matches.add(match);
    }

    public void addToken(String token){
    	tokens.add(token);
    }
    
	public void deleteMatch(Match match) {
		matches.remove(match);
	}
	
	public void deletebyMatchid(long matchid){
		Iterator<Match> iterator = matches.iterator();
		while(iterator.hasNext()){
			Match match = iterator.next();
			if(match.getMatchid() == matchid)
				matches.remove(match);
		}
	}
	
	public void deleteToken(String token) {
		tokens.remove(token);
	}
	
	public int getRank(){
		return rank;
	}
	
	public void setRank(int r){
		this.rank = r;
	}
	
	public void setRD(int r){
		this.RD =r;
	}
	
	public int getRD(){
		return RD;
	}
	
	public long getLastPlayedTime(){
		return lastplayedtime;
	}
	
	public void setLastPlayedTIme(long time){
		this.lastplayedtime = time;
	}
        
}
