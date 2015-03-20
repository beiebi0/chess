package org.kuangchelee.hw9;

import org.kuangchelee.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.Piece;
import org.shared.chess.State;

public class Heuristic{
	StateExplorerImpl stateExplorer = new StateExplorerImpl();
	double getStateValue(State state){
		double score = 0;
		for(int i = 0; i < State.ROWS; i++){
			for(int j = 0; j < State.COLS; j++){
				Piece piece = state.getPiece(i, j);
				if(piece != null){
					score += getPieceValue(piece);
				}
			}
		}
		score += getMobilityValue(state);
		return score;
	}
	int getPieceValue(Piece piece){
		int val = (piece.getColor() == Color.WHITE) ? 1 : -1;
		switch(piece.getKind()){
		case KING:
			val *= 200;
			break;
		case QUEEN:
			val *= 9;
			break;
		case BISHOP:
			val *= 3;
			break;
		case KNIGHT:
			val *= 3;
			break;
		case ROOK:
			val *= 5;
			break;
		case PAWN:
			val *= 1;
			break;
		default:
			val *= 1;
		}
		return val;
	}
	double getMobilityValue(State state){
		State whiteTurn = state.copy();
		State blackTurn = state.copy();
		whiteTurn.setTurn(Color.WHITE);
		blackTurn.setTurn(Color.BLACK);
		
		return 0.1 * (stateExplorer.getPossibleMoves(whiteTurn).size() - stateExplorer.getPossibleMoves(blackTurn).size()); 
	}
}