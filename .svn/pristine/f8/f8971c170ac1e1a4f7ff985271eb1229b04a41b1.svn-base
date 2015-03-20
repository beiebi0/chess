package org.bohouli.hw9;

import org.bohouli.hw2_5.StateExplorerImpl;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;
import org.shared.chess.Color;

public class BohouHeuristic {
	public int getStateValue(State state) {
		if (state.getGameResult() != null) {
			if (state.getGameResult().getWinner() == null)
				return 0;
			else {
				if (state.getGameResult().getWinner() == Color.WHITE)
					return 100000;
				else
					return -100000;
			}
		}

		int sum = 0;
		for (int row = 0; row < State.ROWS; row++) {
			for (int col = 0; col < State.COLS; col++) {
				Piece piece = state.getPiece(row, col);

				if (piece != null) {
					int base = piece.getColor().isWhite() ? 1 : -1;
					int delta = 0;
					switch (piece.getKind()) {
					case PAWN:
						delta = 1;
					case ROOK:
						delta = 5;
					case KNIGHT:
						delta = 3;
					case BISHOP:
						delta = 3;
					case QUEEN:
						delta = 9;
					case KING:
						delta = 0;
					default:
						delta = 0;
					}
					sum += base * delta;
				}
			}
		}
		
		return sum;
	}

	public Iterable<Move> getOrderedMoves(State t) {
		StateExplorer explorer = new StateExplorerImpl();

		return explorer.getPossibleMoves(t);
	}
}
