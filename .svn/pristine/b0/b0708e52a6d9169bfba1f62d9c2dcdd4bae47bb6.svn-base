package org.kanwang.hw9;

import org.kanwang.hw9.AlphaBetaPruning;
import org.kanwang.hw9.DateTimer;
import org.kanwang.hw9.Heuristic;
import org.shared.chess.Move;
import org.shared.chess.State;

public class AIPlayer {
	static AlphaBetaPruning searcher = new AlphaBetaPruning(new Heuristic());
	public Move getMove(State s){
		Move m = searcher.findBestMove(s, 3, new DateTimer(3000));
		return m;
	}

}
