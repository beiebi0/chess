package org.markanderson.hw7;

import java.io.Serializable;
import java.util.Date;

import org.markanderson.hw8.ManderRatingImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;


@Entity
public class Match implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id public Long matchID;
	
	@Load public Ref<Player> player1;
	@Load public Ref<Player> player2;

	public String bPlayerEmail;
	public String wPlayerEmail;
	public String state;
	public Color turn;
	public GameResult gameResult;
	public Color winner;
	public Date startDate;
	public ManderRatingImpl whiteRank;
	public ManderRatingImpl blackRank;

	// no arg constructor
	public Match() {}

	// auto generated getters/ setters
	public Long getMatchID() {
		return matchID;
	}

	public void setMatchID(Long matchID) {
		this.matchID = matchID;
	}

	public Player getPlayer1() {
		return player1.getValue();
	}

	public void setPlayer1(Player player1) {
		this.player1 = Ref.create(player1);
	}

	public Player getPlayer2() {
		return player2.getValue();
	}

	public void setPlayer2(Player player2) {
		this.player2 = Ref.create(player2);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Color getTurn() {
		return turn;
	}

	public void setTurn(Color turn) {
		this.turn = turn;
	}

	public GameResult getGameResult() {
		return gameResult;
	}

	public void setGameResult(GameResult gameResult) {
		this.gameResult = gameResult;
	}

	public Color getWinner() {
		return winner;
	}

	public void setWinner(Color winner) {
		this.winner = winner;
	}

	public String getbPlayerEmail() {
		return bPlayerEmail;
	}

	public void setbPlayerEmail(String bPlayerEmail) {
		this.bPlayerEmail = bPlayerEmail;
	}

	public String getwPlayerEmail() {
		return wPlayerEmail;
	}

	public void setwPlayerEmail(String wPlayerEmail) {
		this.wPlayerEmail = wPlayerEmail;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public ManderRatingImpl getWhiteRank() {
		return whiteRank;
	}

	public void setWhiteRank(ManderRatingImpl whiteRank) {
		this.whiteRank = whiteRank;
	}

	public ManderRatingImpl getBlackRank() {
		return blackRank;
	}

	public void setBlackRank(ManderRatingImpl blackRank) {
		this.blackRank = blackRank;
	}
}
