package org.markanderson.hw7;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id String playerEmail;

	public Map<String, String> tokens = new HashMap<String, String>();
	public Set<Key<Match>> playerMatches = new TreeSet<Key<Match>>();
	public double rating;
	
	// no arg constructor
	public Player() {}

	// constructor
	public Player(String userEmail, List<Match> matches) {
		this.playerEmail = userEmail;
	}

	public Set<Key<Match>> getPlayerMatches() {
		return playerMatches;
	}

	public void setPlayerMatches(Set<Key<Match>> playerMatches) {
		this.playerMatches = playerMatches;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public double getRank() {
		return rating;
	}

	public void setRank(double rank) {
		this.rating = rank;
	}

	public Map<String, String> getTokens() {
		return tokens;
	}

	public void setTokens(Map<String, String> tokens) {
		this.tokens = tokens;
	}

}
