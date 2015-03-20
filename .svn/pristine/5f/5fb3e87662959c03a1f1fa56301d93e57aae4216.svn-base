package org.kanwang.hw9;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import org.kanwang.hw2.StateChangerImpl;
import org.kanwang.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.PieceKind;
import org.shared.chess.State;

public class Heuristic {
	StateChangerImpl sc = new StateChangerImpl();
	StateExplorerImpl se = new StateExplorerImpl();

	public Iterable<Move> getOrderedMoves(State s) {
		LinkedList<Move> result = new LinkedList<Move>();
		result.addAll(se.getPossibleMoves(s));
		final State s1 = s;
		Collections.sort(result, new Comparator<Move>() {
			@Override
			public int compare(Move arg0, Move arg1) {
				return getMoveScore(arg1, s1) - getMoveScore(arg0, s1);
			}
		});
		return result;
	}

	private int getMoveScore(Move m, State s) {
		int result = 0;
		if (m.getPromoteToPiece() != null) {
			result += this.pieceScore(m.getPromoteToPiece()) * 2;
		}
		if (s.getEnpassantPosition() != null
				&& sc.checkUseEnpassant(m.getFrom(), m.getTo(), s
						.getEnpassantPosition(), s.getPiece(m.getFrom())
						.getColor())) {
			result += 20;
		}

		if (s.getPiece(m.getTo()) != null) {
			result += this.pieceScore(s.getPiece(m.getTo()).getKind());
		}

		result += this.pieceScore(s.getPiece(m.getFrom()).getKind())/2;
		
		if(s.getPiece(m.getFrom()).getColor() == Color.WHITE){
			result += m.getTo().getRow() - m.getFrom().getRow();
		}
		else{
			result += m.getFrom().getRow() - m.getTo().getRow();
		}
		return result;
	}

	private int pieceScore(PieceKind p) {
		switch (p) {
		case BISHOP:
			return 40;
		case KING:
			return 30;
		case KNIGHT:
			return 50;
		case PAWN:
			return 20;
		case QUEEN:
			return 100;
		case ROOK:
			return 60;
		default:
			return 0;
		}
	}

	private int getNumofPieces(State s, Color c) {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (s.getPiece(i, j) != null && s.getPiece(i, j).getColor() == c) {
					count++;
				}
			}
		}
		return count;
	}

	public int getStateValue(State s) {
		int result = 0;
		if(s.getGameResult() != null){
			if(s.getGameResult().isDraw()){
				result = 0;
			}
			else if(s.getGameResult().getWinner() == Color.WHITE)
				result = 10000;
			else 
				result = -10000;
			
			return result;
		}
		
		for(int i = 0 ; i < 8 ; i ++){
			for(int j = 0 ; j < 8 ; j ++){
				if(s.getPiece(i, j) != null){
					if(s.getPiece(i, j).getColor() == Color.WHITE){
						result += this.pieceScore(s.getPiece(i, j).getKind());
					}
					else{
						result -= this.pieceScore(s.getPiece(i, j).getKind());
					}
				}
			}
		}
		//open game
		if(this.getNumofPieces(s, Color.WHITE) + this.getNumofPieces(s, Color.BLACK) > 22){
			for(int i = 0 ; i < 8 ; i ++){
				if(s.getPiece(1, i) == null){
					result += 20;
				}
				if(s.getPiece(6, 1) == null){
					result -= 20;
				}
			}
		}
		//mid game
		else if(this.getNumofPieces(s, Color.WHITE) + this.getNumofPieces(s, Color.BLACK) > 12){
			for(int i = 0 ; i < 8 ; i++){
				for(int j = 0 ; j < 8 ; j++){
					if(s.getPiece(i, j) != null && s.getPiece(i, j).getKind() == PieceKind.PAWN){
						if(s.getPiece(i, j).getColor() == Color.WHITE)
							result += 50;
						else
							result -= 50;
					}
				}
			}
		}
		
		return result;
	}
}
