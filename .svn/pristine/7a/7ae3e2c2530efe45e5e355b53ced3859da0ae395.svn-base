package org.chenji.hw7.client;

import java.io.Serializable;
import java.util.Date;

import org.shared.chess.Color;
import org.shared.chess.State;

public class Match implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 2811557183395350224L;
  private int matchId;
  private boolean deleted = false;
  private String state;
  private String title;
  private int turn;
  private String[] players = new String[] {"", ""};
  private Date startTime;
  public Match() {}
  public Match(int id) {
    matchId = id;
    turn = 0;
    state = new State().toString();
    setStartTime(new Date());
  }
  public Match(int id, Player player1, Player player2) {
    matchId = id;
    players[0] = player1.getPlayerId();
    players[1] = player2.getPlayerId();
    title = "White: " + player1.getNickname() + "(" + player1.getRating() + ") vs Black: " + player2.getNickname() + "(" + player2.getRating() + ")";
    turn = 0;
    state = new State().toString();
    setStartTime(new Date());
  }
  public Match(int id, boolean deleted, String title, String player1, String player2, int turn, String state, String time) {
    matchId = id;
    this.deleted = deleted;
    this.title = title;
    players[0] = player1;
    players[1] = player2;
    this.turn = turn;
    this.state = state;
    startTime = new Date(time);
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getPlayer(int i) {
    return players[i];
  }
  public void setPlayer(int i, String player) {
    this.players[i] = player;
  }
  public int getMatchId() {
    return matchId;
  }
  public void setMatchId(int matchId) {
    this.matchId = matchId;
  }
  public boolean isDeleted() {
    return deleted;
  }
  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
  public boolean isMatch(String player1, String player2) {
    return (players[0].equals(player1) && players[1].equals(player2)) || (players[0].equals(player2) && players[1].equals(player1));
  }
  public String getOpponentOf(String player) {
    assert (player.equals(players[0]) || player.equals(players[1]));
    return players[0].equals(player) ? players[1] : players[0];
  }
  public Color getColorOf(String player) {
    assert (player.equals(players[0]) || player.equals(players[1]));
    return players[0].equals(player) ? Color.WHITE : Color.BLACK;
  }
  @Override
  public String toString() {
    return matchId + "&" + deleted + "&" + title + "&" + players[0] + "&" + players[1] + "&" + turn + "&" + state + "&" + startTime.toString() ;
  }
  public static Match deserializeFrom(String value) {
    String keys[] = value.split("&");
    return new Match(Integer.parseInt(keys[0]), Boolean.parseBoolean(keys[1]), keys[2], keys[3], keys[4], Integer.parseInt(keys[5]), keys[6], keys[7]);
  }
  public int getTurn() {
    return turn;
  }
  public void setTurn(int turn) {
    this.turn = turn;
  }
  public Date getStartTime() {
    return startTime;
  }
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
}
