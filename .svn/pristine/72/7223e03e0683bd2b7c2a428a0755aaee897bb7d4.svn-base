package org.alexanderoskotsky.hw9;

import org.alexanderoskotsky.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

public class ChessHeuristic {

	public int getStateValue(State t) {
		int score = 0;

		if (t.getGameResult() != null && t.getGameResult().getWinner() == null) {
			return 0;
		}

		if (t.getGameResult() != null && t.getGameResult().getWinner() != null) {
			Color winner = t.getGameResult().getWinner();

			if (winner.isWhite()) {
				return 1000000;
			} else {
				return -1000000;
			}
		}

		for (int i = 0; i < State.ROWS; i++) {
			for (int j = 0; j < State.COLS; j++) {
				Piece piece = t.getPiece(i, j);

				if (piece != null) {
					int multiple = piece.getColor().isWhite() ? 1 : -1;
					score += getScoreForPiece(piece.getKind()) * multiple;
				}
			}
		}

		return score;
	}

	public Iterable<Move> getOrderedMoves(State t) {
		StateExplorer explorer = new StateExplorerImpl();

		return explorer.getPossibleMoves(t);
	}

	private int getScoreForPiece(PieceKind kind) {
		switch (kind) {
		case BISHOP:
			return 3;
		case KING:
			return 0;
		case KNIGHT:
			return 3;
		case PAWN:
			return 1;
		case QUEEN:
			return 9;
		case ROOK:
			return 5;
		default:
			return 0;
		}
	}

}
