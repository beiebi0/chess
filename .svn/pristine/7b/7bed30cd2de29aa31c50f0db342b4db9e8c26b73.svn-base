package org.yuanjia.hw7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Match implements IsSerializable {

	@Id
	String matchId;

	String blackplayer = "";   //userEmail
	String whiteplayer = "";
	String stateStr = "Castle=TTTT&Turn=W&numberOfMovesWithoutCaptureNorPawnMoved=0&Board=00:WR,01:WN,02:WB,03:WQ,04:WK,05:WB,06:WN,07:WR,10:WP,11:WP,12:WP,13:WP,14:WP,15:WP,16:WP,17:WP,60:BP,61:BP,62:BP,63:BP,64:BP,65:BP,66:BP,67:BP,70:BR,71:BN,72:BB,73:BQ,74:BK,75:BB,76:BN,77:BR";
	String turn = "white";
	String winner = "";
	Date startDate = new Date();
	public List<Boolean> deleted = new ArrayList<Boolean>(Arrays.asList(false,false));

	public Match() {
	}

	public Match(String matchId) {
		this.matchId = matchId;
	}

	public void setPlayer(String player, String color) {
		if (color.equals("black")||color.equals("B")) {
			blackplayer = player;
		} else if (color.equals("white")||color.equals("W")) {
			whiteplayer = player;
		}
	}

	public String getMatchId() {
		return matchId;
	}
	
	public String getPlayer(String color){
		if(color.equals("black")||color.equals("B")){
			return blackplayer;
		}else if (color.equals("white")||color.equals("W")){
			return whiteplayer;
		}
		return null;
	}
	
	public String getStateStr(){
		return stateStr;
	}
	
	public void setStateStr(String stateStr){
		this.stateStr = stateStr;
	}
	
	public Date getStartDate(){
		return startDate;
	}
}
