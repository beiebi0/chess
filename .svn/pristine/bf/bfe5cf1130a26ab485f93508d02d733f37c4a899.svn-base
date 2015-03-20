package org.zhihanli.hw9;

import org.shared.chess.Move;
import org.shared.chess.State;

public class AI {
	
	private int timeOutMilliSec;
	private int maxDepth;
	
	public AI(int timeOutMilliSec,int maxDepth){
		this.timeOutMilliSec=timeOutMilliSec;
		this.maxDepth=maxDepth;
	}
	
	public Move getMove(State state) {
		AlphaBetaPruning abp = new AlphaBetaPruning(new HeuristicImpl());
		return abp.findBestMove(state, maxDepth, new DateTimer(timeOutMilliSec));
	}
}
