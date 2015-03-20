package org.simongellis.hw7.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.condition.IfTrue;

@Entity
public class Player {
	@Id String email;
	String name;
	int rank;
	Set<String> connections;
	Set<Key<Match>> matches;
	
	@Index(IfTrue.class) boolean automatch; 
	
	public Player() {
		this.email = "";
		this.name = "";
		this.rank = 1500;
		connections = new HashSet<String>();
		matches = new HashSet<Key<Match>>();
		automatch = false;
	}
	
	public Player(String email, String name) {
		this.email = email;
		this.name = name;
		this.rank = 1500;
		connections = new HashSet<String>();
		matches = new HashSet<Key<Match>>();
		automatch = false;
	}
	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	public int numberOfConnections() {
		return connections.size();
	}
	public void addConnection(String token) {
		connections.add(token);
	}
	public void removeConnection(String token) {
		connections.remove(token);
	}
	public Set<String> getConnections() {
		return Collections.unmodifiableSet(connections);
	}
	
	public int numberOfMatches() {
		return matches.size();
	}
	public boolean containsMatch(Key<Match> match) {
		return matches.contains(match);
	}
	public void addMatch(Key<Match> match) {
		matches.add(match);
	}
	public void removeMatch(Key<Match> match) {
		matches.remove(match);
	}
	public Set<Key<Match>> getMatches() {
		return Collections.unmodifiableSet(matches);
	}
	
	public void setAutomatch(boolean value) {
		automatch = value;
	}
}
