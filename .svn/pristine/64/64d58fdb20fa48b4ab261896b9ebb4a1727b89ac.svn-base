package org.vorasahil.hw9;

import org.vorasahil.hw2.StateChangerImpl;
import org.vorasahil.hw2_5.StateExplorerImpl;
import static org.shared.chess.Color.*;
import org.shared.chess.*;

public class Heuristic {
	StateChangerImpl stateChangerImpl;
	StateExplorerImpl stateExplorerImpl;

	public Heuristic() {
		stateChangerImpl = new StateChangerImpl();
		stateExplorerImpl = new StateExplorerImpl();
	}

	int getStateValue(State state) {
		double score = 0;
		GameResult gr = state.getGameResult();
		if (gr != null) {
			Color winner = gr.getWinner();
			if (winner == null) {
				return 0;
			} else if (winner.isWhite()) {
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}
		} else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					Piece piece = state.getPiece(i, j);
					if (piece != null) {
						int l = 0;
						Color color = piece.getColor();
						int t = color.equals(WHITE) ? 1 : -1;
						switch (piece.getKind()) {
						case BISHOP:
							l = stateExplorerImpl.getPossibleMovesFromPosition(
									state.copy(), new Position(i, j)).size();
							score += t * (3 + (l * 0.5));
							break;
						case KING:
							if (stateChangerImpl.checkPositionForCheck(
									new Position(i, j), color, state.copy())) {
								score += -1 * t * 10;
							}
							break;
						case KNIGHT:
							l = stateExplorerImpl.getPossibleMovesFromPosition(
									state.copy(), new Position(i, j)).size();
							score += t * (3 + (l * 0.5));
							break;
						case PAWN:
							l = stateExplorerImpl.getPossibleMovesFromPosition(
									state.copy(), new Position(i, j)).size();
							score += t * (1 + (l * 0.2));
							break;
						case QUEEN:
							if (stateChangerImpl.checkPositionForCheck(
									new Position(i, j), color, state.copy())) {
								score += -1 * t * 2;
							}
							score += t * 9;
							break;
						case ROOK:
							score += t * 5;
							break;
						default:
							break;
						}
					}
				}
			}
		}
		return (int) score;
	}

	Iterable<Move> getOrderedMoves(State state) {
		return stateExplorerImpl.getPossibleMoves(state);
	}
}
