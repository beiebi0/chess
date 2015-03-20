package org.karthikmahadevan.hw9;

import org.karthikmahadevan.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;

public class Heuristic {
	StateExplorerImpl stateExplorer = new StateExplorerImpl();
	int scores [] = {1000, 9, 5, 3, 3, 1};
	
	public Iterable<Move> getOrderedMoves(State state) {
		return stateExplorer.getPossibleMoves(state);
	}

	//gives the value of the state from white's perspective
	public int getStateValue(State state) {
		int score = 0;
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				Piece piece = state.getPiece(new Position(row, col));
				if (piece != null) {
					if (piece.getColor().equals(Color.WHITE)) {
						score += scores[piece.getKind().ordinal()];
					}
					else {
						score -= scores[piece.getKind().ordinal()];
					}
				}
			}
		}
		return score;
	}

}
