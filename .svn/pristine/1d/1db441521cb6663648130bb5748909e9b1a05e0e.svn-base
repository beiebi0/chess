package org.markanderson.hw9;

import org.markanderson.hw2.StateChangerImpl;
import org.markanderson.hw2_5.StateExplorerImpl;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

public class ManderHeuristic {
	
	StateExplorerImpl se = new StateExplorerImpl();
	StateChangerImpl sc = new StateChangerImpl();
	
	public Iterable<Move> getOrderedMoves(State state) {
		return se.getPossibleMoves(state);
	}
	
	public int getPieceVal(PieceKind pk, org.shared.chess.Color color) {
		switch(pk) {
			case PAWN: {
				return color.equals(org.shared.chess.Color.WHITE) ? 1 : -1;
			}
			case ROOK: {
				return color.equals(org.shared.chess.Color.WHITE) ? 3 : -3;
			}
			case BISHOP: {
				return color.equals(org.shared.chess.Color.WHITE) ? 5 : -5;
			}
			case KNIGHT: {
				return color.equals(org.shared.chess.Color.WHITE) ? 7 : -7;
			}
			case QUEEN: {
				return color.equals(org.shared.chess.Color.WHITE) ? 9 : -9;
			}
			case KING: {
				return color.equals(org.shared.chess.Color.WHITE) ? 10 : -10;
			}
			default: {
				return 0;
			}
		}
	}
	public int getStateValue(State state) {
		if (state.getGameResult() != null && state.getGameResult().isDraw()) {
			// draw, keep at zero
			return 0;
		} 
		if (state.getGameResult() != null && state.getGameResult().getWinner().isWhite()) {
			// white piece, want to lean towards the positive
			return 1000;
		} else if (state.getGameResult() != null && state.getGameResult().getWinner().isBlack()) {
			// black piece, want to lean towards the negative
			return -1000;
		}
		int stateSum = 0;
		// get the statesum (negative for black pieces, positive for white pieces)
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = state.getPiece(new Position(i,j));
				if (piece != null) {
					stateSum += getPieceVal(piece.getKind(), piece.getColor());
				}
			}
		}
		return stateSum;
	}
}
