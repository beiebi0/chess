package org.longjuntan.hw9;

import org.longjuntan.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

public class GameHeuristic {

	public int getStateValue(State state) {
		GameResult result = state.getGameResult();
		if (result != null) {
			Color winner = result.getWinner();
			if (winner == Color.WHITE) {
				return Integer.MAX_VALUE;
			}
			if (winner == Color.BLACK) {
				return Integer.MIN_VALUE;
			}
			return 0;
		}

		int stateValue = 0;
		for (int i = 0; i < State.ROWS; i++) {
			for (int j = 0; j < State.COLS; j++) {
				Piece p = state.getPiece(i, j);

				if (p != null) {
					int weight = p.getColor().isWhite() ? 1 : -1;
					stateValue += weight * getPieceValue(p.getKind());
				}
			}
		}

		return stateValue;
	}

	public Iterable<Move> getOrderedMoves(State t) {
		StateExplorer se = new StateExplorerImpl();
		return se.getPossibleMoves(t);
	}

	public int getPieceValue(PieceKind kind) {
		switch (kind) {
		case PAWN:
			return 1;
		case KNIGHT:
			return 3;
		case BISHOP:
			return 3;
		case ROOK:
			return 5;
		case QUEEN:
			return 9;
		case KING:
			return 0;
		default:
			return 0;
		}
	}
}
