package org.paulsultan.hw7;

import java.util.HashSet;
import java.util.Set;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Player {
	@Id String email;
	@Index boolean isInGame;
	double rank;
	Set<Key<Match>> matches = new HashSet<Key<Match>>();
	
	public Player(){}
	public Player(String email){
		this.email = email;
		matches = new HashSet<Key<Match>>();
		isInGame = false;
		rank = 1500;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
		
	public boolean getIsInGame(){
		return isInGame;
	}
	public void setInGame(boolean isInGame){
		this.isInGame = isInGame;
	}
			
	public Set<Key<Match>> getMatches(){
		return matches;
	}
	public void setMatches(Set<Key<Match>> matches){
		this.matches = matches;
	}
	
	public void addMatch(Key<Match> match){
		matches.add(match);
	}
	public void removeMatch(Key<Match> match){
		matches.remove(match);
	}
	
	public double getRank(){
		return rank;
	}
	public void setRank(double rank){
		this.rank = rank;
	}
}
