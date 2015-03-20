package org.longjuntan.hw8;

import java.io.Serializable;

import org.shared.chess.Color;

public class PageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PlayerInfo opponent;
	private GameInfo game;
	private Color color;
	private String listInfo;
	
	public String getListInfo() {
		return listInfo;
	}

	public void setListInfo(String listInfo) {
		this.listInfo = listInfo;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public GameInfo getGame() {
		return game;
	}

	public void setGame(GameInfo game) {
		this.game = game;
	}

	public PlayerInfo getOpponent() {
		return opponent;
	}

	public void setOpponent(PlayerInfo opponent) {
		this.opponent = opponent;
	}
}
