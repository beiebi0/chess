package org.longjuntan.hw7;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;

import org.longjuntan.hw8.Ranking;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	String email;

	String name;

	int rank = Ranking.getDefaultRanking();

	HashSet<String> channels = new HashSet<String>();

	@Load
	LinkedList<Key<Match>> matches = new LinkedList<Key<Match>>();

	public Player() {
	}

	public Player(String email) {
		this.email = email;
		this.name = email.split("@")[0];
	}

	@Override
	public String toString() {
		return email;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof Player))
			return false;
		Player other = (Player) o;
		return this.email.equals(other.email);
	}

	public void addChannels(String clientId) {
		channels.add(clientId);
	}

	@Override
	public int hashCode() {
		return email.hashCode() * 17 + name.hashCode();
	}

	public HashSet<String> getChannels() {
		return channels;
	}

	public LinkedList<Key<Match>> getMatches() {
		return matches;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
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

	public void setChannels(HashSet<String> channels) {
		this.channels = channels;
	}

	public void setMatches(LinkedList<Key<Match>> matches) {
		this.matches = matches;
	}

}
