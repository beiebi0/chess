package org.haoxiangzuo.hw9;

import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.State;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heuristic thisH = new Heuristic();
		AlphaBetaPruning thisA = new AlphaBetaPruning(thisH);
		Timer t = new Timer(40000);
		State start = new State();
		Move move = thisA.findBestMove(start, 3, t);
		System.out.print(move.getFrom().getRow()+" "+move.getFrom().getCol()+" "+move.getTo().getRow()+" "+move.getTo().getCol());
	}

}
