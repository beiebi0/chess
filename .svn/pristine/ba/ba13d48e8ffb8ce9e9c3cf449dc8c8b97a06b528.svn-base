package org.shihweihuang.hw9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;
import org.shihweihuang.hw2.StateChangerImpl;
import org.shihweihuang.hw2_5.StateExplorerImpl;
import org.shihweihuang.hw9.AlphaBetaPruning.MoveScore;

public class Heuristic {
	private final static int QUEEN_VALUE = 900;
	private final static int BISHOP_VALUE = 300;
	private final static int KNIGHT_VALUE = 300;
	private final static int ROOK_VALUE = 500;
	private final static int PAWN_VALUE = 100;
	StateExplorer stateExplorer;
	private StateChanger stateChanger;

	public Heuristic() {
		stateExplorer = new StateExplorerImpl();
		stateChanger = new StateChangerImpl();
	}

	int getStateValue(State state) {
		GameResult gameResult = state.getGameResult();
		if (gameResult != null) {
			Color winner = gameResult.getWinner();
			if (winner == null) {
				return 0;
			} else if (winner.isWhite()) {
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}
		}
		int whiteScore = 0;
		int blackScore = 0;
		for (int r = 0; r < State.ROWS; r++) {
			for (int c = 0; c < State.COLS; c++) {
				Piece piece = state.getPiece(r, c);
				if (piece != null) {
					PieceKind kind = piece.getKind();
					if (piece.getColor() == Color.WHITE) {
						whiteScore = whiteScore + getKindValue(kind);
					} else {
						blackScore = blackScore + getKindValue(kind);
					}
				}
			}
		}
		return whiteScore - blackScore;
	}

	private int getKindValue(PieceKind kind) {
		if (kind.equals(PieceKind.QUEEN)) {
			return QUEEN_VALUE;
		} else if (kind.equals(PieceKind.BISHOP)) {
			return BISHOP_VALUE;
		} else if (kind.equals(PieceKind.KNIGHT)) {
			return KNIGHT_VALUE;
		} else if (kind.equals(PieceKind.ROOK)) {
			return ROOK_VALUE;
		} else if (kind.equals(PieceKind.PAWN)) {
			return PAWN_VALUE;
		} else {
			return 0;
		}

	}

	
	static class MoveScore<T> implements Comparable<MoveScore<T>> {
		T move;
		int score;

		@Override
		public int compareTo(MoveScore<T> o) {
			return o.score - score; // sort DESC (best score first)
		}
	}
	Iterable<Move> getOrderedMoves(State state) {
		Color color = state.getTurn();
		boolean isWhite = color.isWhite();
		List<MoveScore<Move>> moveScoreList = new ArrayList<MoveScore<Move>>();
		Set<Move> possibleMoves = stateExplorer.getPossibleMoves(state);
		for (Move move : possibleMoves){
			State nextState = state.copy();
			stateChanger.makeMove(nextState, move);
			int value = getStateValue(nextState);
			if (!isWhite){
				value = -value;
			}
			MoveScore<Move> score = new MoveScore<Move>();
			score.move = move;
			score.score = value;
			moveScoreList.add(score);
		}
		Collections.sort(moveScoreList);
		List<Move> orderedMoves = new ArrayList<Move>();
		for (MoveScore<Move> moveScore : moveScoreList){
			orderedMoves.add(moveScore.move);
		}
		return orderedMoves;
	}
}
