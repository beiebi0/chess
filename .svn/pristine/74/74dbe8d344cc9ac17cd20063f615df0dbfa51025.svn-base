package org.chenji.hw7.client;

import java.io.Serializable;
import java.util.Date;

import org.shared.chess.Color;

public class Player implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 7160509653910266755L;
  private String PlayerId;
  private String nickname;
  private String token;
  private Color color;
  private double RD;
  private int rating;
  private Date lastGame;
  public Player() {
    RD = 350;
    rating = 1500;
    lastGame = new Date();
  }
  public String getPlayerId() {
    return PlayerId;
  }
  public void setPlayerId(String playerId) {
    PlayerId = playerId;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public Color getColor() {
    return color;
  }
  public void setColor(Color color) {
    this.color = color;
  }
  public Date getLastGame() {
    return lastGame;
  }
  public void setLastGame(Date lastGame) {
    this.lastGame = lastGame;
  }
  public double getRD() {
    return RD;
  }
  public void setRD(double rD) {
    RD = rD;
  }
  public int getRating() {
    return rating;
  }
  public void setRating(int rating) {
    this.rating = rating;
  }
}
