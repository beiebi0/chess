package org.yuanjia.hw9;

import org.shared.chess.Move;
import org.shared.chess.State;

public class AI {
	private static int maxMilliSec = 3000;
	private static int maxLevel = 3;
	private static final AI AI_instance = new AI(maxMilliSec, maxLevel);

	
	private AI(int maxMilliSec, int maxLevel){
		AI.maxMilliSec = maxMilliSec;
		AI.maxLevel = maxLevel;
	}
	
	public static AI get_AI(){
		return AI_instance;
	}
	
	public static Move get_move(State state){
		AlphaBetaPruning abp = new AlphaBetaPruning(new SimpleHeuristic());
        return abp.findBestMove(state, maxLevel, new DateTimer(maxMilliSec));
	}
	
//	public static PieceKind get_promotion(){
//		
//	}
}
