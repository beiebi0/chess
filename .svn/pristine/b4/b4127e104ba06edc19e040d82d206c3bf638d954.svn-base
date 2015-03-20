package org.haoxiangzuo.hw7;

import java.util.HashSet;
import java.util.Set;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.condition.IfFalse;


@Entity
public class Player {
	@Id String email;
	String name;
	String channel;
	Set<Key<Match>> matches;
	int rank;
	@Index(IfFalse.class) boolean inGame;
	public Player()
	{
		email="";
		name="";
		channel = "";
		matches = new HashSet<Key<Match>>();
		inGame = false;
		rank = 1500;
	}
	public boolean isInGame() {
		return inGame;
	}
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	public Player(String email, String name)
	{
		this.email = email;
		this.name = name;
		channel = "";
		matches = new HashSet<Key<Match>>();
		inGame = false;
		rank = 1500;
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
	public String getChannel() {
		return channel;
	}
	public void setChannel (String channels) {
		this.channel = channels;
	}
	public Set<Key<Match>> getMatches() {
		return matches;
	}
	public void setMatches(Set<Key<Match>> matches) {
		this.matches = matches;
	}
	public void addMatch(Key<Match> match)
	{
		matches.add(match);
	}
	public void deleteMatch(Key<Match> match)
	{
		matches.remove(match);
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
