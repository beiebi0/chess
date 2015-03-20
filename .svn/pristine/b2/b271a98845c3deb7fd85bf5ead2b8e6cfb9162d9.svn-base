package org.bohouli.hw6.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class BohouPlayer {
	@Id
	String email = null;
	String name = null;
	List<String> tokens = new ArrayList<String>();
	private boolean loggedIn = false;
	double RD = 350;
	double Rating = 1500;
	Date time = new Date();
	List<Key<BohouMatch>> matches = new ArrayList<Key<BohouMatch>>();
	
	public BohouPlayer() {}
	
	public BohouPlayer(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public List<Key<BohouMatch>> getMatches() {
		return matches;
	}

	public void addMatch(Key<BohouMatch> match) {
		this.matches.add(match);
	}
	
	public void removeMatch() {
		
	}

	public double getRD() {
		return RD;
	}

	public void setRD(double rD) {
		RD = rD;
	}

	public double getRating() {
		return Rating;
	}

	public void setRating(double rating) {
		Rating = rating;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
