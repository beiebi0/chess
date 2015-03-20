package org.zhihanli.hw9;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;
import org.zhihanli.hw2.StateChangerImpl;
import org.zhihanli.hw2_5.StateExplorerImpl;

import static org.shared.chess.Color.*;
import static org.shared.chess.PieceKind.*;

public class HeuristicImpl implements Heuristic {

	@Override
	public int getStateValue(State state) {
		if (state.getGameResult() != null) {
			if (state.getGameResult().getWinner() == WHITE)
				return 20000;
			if (state.getGameResult().getWinner() == BLACK)
				return -20000;
			return 0;
		}

		int sum = 0;
		StateExplorer stateExplorer = new StateExplorerImpl();

		/**
		 * calculate material score
		 */
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Piece piece = state.getPiece(row, col);

				if (piece == null)
					continue;

				/**
				 * mobility score
				 */
				Color color = piece.getColor();
				int moveNum = 0;

				moveNum = stateExplorer.getPossibleMovesFromPosition(state,
						new Position(row, col)).size();
				if (color == WHITE) {
					sum += moveNum;
				} else {
					sum -= moveNum;
				}

				switch (piece.getKind()) {
				case PAWN:
					if (color == WHITE) {
						sum += PieceValue.pawnValue;
						sum += pawnValueAdjustment(state, row, col);
					}
					if (color == BLACK) {
						sum -= PieceValue.pawnValue;
						sum -= pawnValueAdjustment(state, row, col);

					}
					break;
				case ROOK:
					if (color == WHITE)
						sum += PieceValue.rookValue;
					if (color == BLACK)
						sum -= PieceValue.rookValue;
					break;
				case KNIGHT:
					if (color == WHITE)
						sum += PieceValue.knightValue;
					if (color == BLACK)
						sum -= PieceValue.knightValue;
					break;
				case QUEEN:
					if (color == WHITE)
						sum += PieceValue.queenValue;
					if (color == BLACK)
						sum -= PieceValue.queenValue;
					break;
				case BISHOP:
					if (color == WHITE)
						sum += PieceValue.bishopValue;
					if (color == BLACK)
						sum -= PieceValue.bishopValue;
					break;
				case KING:
					if (color == WHITE)
						sum += PieceValue.kingValue;
					if (color == BLACK)
						sum -= PieceValue.kingValue;
					break;
				}
				int positionValue = PieceValue.getPieceSquareValue(row, col,
						piece.getKind());
				sum += (color == WHITE ? positionValue : -positionValue);
			}
		}

		return sum;
	}

	@Override
	public Iterable<Move> getOrderedMoves(State state) {
		List<moveValue> sortedMoves = new LinkedList<moveValue>();
		int sum = 0;
		StateExplorer stateExplorer = new StateExplorerImpl();
		Set<Move> moveSet = stateExplorer.getPossibleMoves(state);

		for (Move move : moveSet) {
			State s = getStateAfterMove(state, move);
			int value = getStateValue(s);
			sum += value;
			sortedMoves.add(new moveValue(move, value));
		}

		Collections.sort(sortedMoves, new moveValueComparator(state.getTurn()));
		int average = sum / sortedMoves.size();

		List<Move> result = new LinkedList<Move>();
		for (moveValue mV : sortedMoves) {
			if (mV.getValue() < average)
				continue;
			result.add(mV.getMove());
		}
		return result;
	}

	private class moveValue {
		private Move move;
		private int value;

		public moveValue(Move move, int value) {
			this.move = move;
			this.value = value;
		}

		public Move getMove() {
			return move;
		}

		public int getValue() {
			return value;
		}

		@Override
		public String toString() {
			return move.toString() + " " + value;
		}

	}

	private class moveValueComparator implements Comparator {
		Color color;

		public moveValueComparator(Color color) {
			this.color = color;
		}

		@Override
		public int compare(Object o1, Object o2) {
			moveValue moveValue1 = (moveValue) o1;
			moveValue moveValue2 = (moveValue) o2;

			return color == WHITE ? moveValue2.getValue()
					- moveValue1.getValue() : moveValue1.getValue()
					- moveValue2.getValue();
		}

	}

	private State getStateAfterMove(State state, Move move) {
		State stateCopy = state.copy();
		StateChanger stateChanger = new StateChangerImpl();
		stateChanger.makeMove(stateCopy, move);
		return stateCopy;
	}

	private int pawnValueAdjustment(State state, int row, int col) {
		int sum = 0;
		Piece piece = state.getPiece(row, col);

		/**
		 * check whether this pawn is doubled
		 */
		if (piece.getKind() != PAWN)
			return 0;
		for (int r = 0; r < 8; r++) {
			if (r == row)
				continue;
			Piece p = state.getPiece(r, col);
			if (p != null) {

				/**
				 * doubled
				 */
				if (p.getKind() == PAWN && p.getColor() == piece.getColor())
					sum -= 5;
			}
		}

		/**
		 * check whether the pawn is isolated
		 * 
		 */
		boolean isIsolated = true;
		for (int r = 0; r < 8; r++) {
			Piece p1, p2;
			if (col + 1 < 8 & col + 1 >= 0) {
				p1 = state.getPiece(r, col + 1);
				if (p1 != null && p1.getColor() == piece.getColor()
						&& p1.getKind() == PAWN) {
					isIsolated = false;
					break;
				}

			}
			if (col - 1 < 8 & col - 1 >= 0) {
				p2 = state.getPiece(r, col - 1);
				if (p2 != null && p2.getColor() == piece.getColor()
						&& p2.getKind() == PAWN) {
					isIsolated = false;
					break;
				}
			}
		}
		if (isIsolated)
			sum += -5;

		/**
		 * check whether the pawn is blocked, i.e. cannot move forward
		 */
		int rowOffset = 0;
		if (piece.getColor() == WHITE) {
			rowOffset = 1;
		} else {
			rowOffset = -1;
		}

		if (onBoard(row + rowOffset, col)
				&& state.getPiece(row + rowOffset, col) != null) {
			if (onBoard(row + rowOffset, col + 1)
					&& (state.getPiece(row + rowOffset, col + 1) == null || state
							.getPiece(row + rowOffset, col + 1).getColor() == piece
							.getColor())) {
				if (onBoard(row + rowOffset, col - 1)
						&& (state.getPiece(row + rowOffset, col - 1) == null || state
								.getPiece(row + rowOffset, col - 1).getColor() == piece
								.getColor())) {
					sum += -5;
				}
			}
		}

		return sum;
	}

	private boolean onBoard(int row, int col) {
		return row >= 0 && row < 8 & col >= 0 && col < 8;
	}
}
