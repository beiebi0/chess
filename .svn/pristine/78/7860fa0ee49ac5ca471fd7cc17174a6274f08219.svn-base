package org.longjuntan.hw2_5;

import static org.shared.chess.PieceKind.BISHOP;
import static org.shared.chess.PieceKind.KNIGHT;
import static org.shared.chess.PieceKind.QUEEN;
import static org.shared.chess.PieceKind.ROOK;

import java.util.HashSet;
import java.util.Set;

import org.longjuntan.hw2.StateChangerImpl;
import org.shared.chess.Color;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;

public class StateExplorerImpl implements StateExplorer {

	@Override
	public Set<Move> getPossibleMoves(State state) {
		Set<Move> possibleMoves = new HashSet<Move>();

		for (Position p : getPossibleStartPositions(state)) {
			possibleMoves.addAll(getPossibleMovesFromPosition(state, p));
		}

		return possibleMoves;
	}

	@Override
	public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
		if (state.getGameResult() != null || start == null) {
			return Sets.newHashSet();
		}

		Set<Move> possibleMovesFromPosition = new HashSet<Move>();
		Color color = state.getTurn();

		Piece piece = state.getPiece(start);
		if (piece != null) {
			if (piece.getColor() != color)
				return possibleMovesFromPosition;
			else {
				switch (piece.getKind()) {
				case QUEEN:
					possibleMovesFromPosition
							.addAll(possibleMoveFromPosition_Cross(state, start));
					possibleMovesFromPosition
							.addAll(possibleMoveFromPosition_Diagonal(state,
									start));
					break;
				case ROOK:
					possibleMovesFromPosition
							.addAll(possibleMoveFromPosition_Cross(state, start));
					break;
				case BISHOP:
					possibleMovesFromPosition
							.addAll(possibleMoveFromPosition_Diagonal(state,
									start));
					break;
				case KING:
					int vDiff_king[] = { 1, 1, 0, -1, -1, -1, 0, 1, 0, 0 };
					int hDiff_king[] = { 0, 1, 1, 1, 0, -1, -1, -1, 2, -2 };
					possibleMovesFromPosition
							.addAll(possibleMoveFromPosition_Circle(state,
									start, vDiff_king, hDiff_king));
					break;
				case PAWN:
					possibleMovesFromPosition
							.addAll(possibleMoveFromPosition_Pawn(state, start));
					break;
				case KNIGHT:
					int vDiff_knight[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
					int hDiff_knight[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
					possibleMovesFromPosition
							.addAll(possibleMoveFromPosition_Circle(state,
									start, vDiff_knight, hDiff_knight));
					break;
				default:
					break;
				}
			}
		}
		return possibleMovesFromPosition;
	}

	@Override
	public Set<Position> getPossibleStartPositions(State state) {
		Set<Position> startPositions = new HashSet<Position>();
		for (int row = 0; row < State.ROWS; row++) {
			for (int col = 0; col < State.COLS; col++) {
				if (getPossibleMovesFromPosition(state, new Position(row, col))
						.size() != 0) {
					startPositions.add(new Position(row, col));
				}
			}
		}
		return startPositions;
	}

	private Set<Move> possibleMoveFromPosition_Cross(State state, Position start) {
		Set<Move> possibleMovesFromPosition = new HashSet<Move>();
		StateChangerImpl sc = new StateChangerImpl();
		State copy = state.copy();

		int row = start.getRow();
		int col = start.getCol();

		for (int c = 0; c < State.COLS; c++) {
			Move move = new Move(start, new Position(row, c), null);
			try {
				sc.move(copy, move);
			} catch (IllegalMove e) {
				copy = state.copy();
				continue;
			}
			possibleMovesFromPosition.add(move);
			copy = state.copy();
		}

		for (int r = 0; r < State.ROWS; r++) {
			Move move = new Move(start, new Position(r, col), null);
			try {
				sc.move(copy, move);
			} catch (IllegalMove e) {
				copy = state.copy();
				continue;
			}
			possibleMovesFromPosition.add(move);
			copy = state.copy();
		}

		return possibleMovesFromPosition;
	}

	private Set<Move> possibleMoveFromPosition_Diagonal(State state,
			Position start) {
		Set<Move> possibleMovesFromPosition = new HashSet<Move>();
		StateChangerImpl sc = new StateChangerImpl();
		State copy = state.copy();

		int row = start.getRow();
		int col = start.getCol();

		for (int c = 0; c < State.COLS; c++) {
			int r = row + col - c;
			if (r < 0 || r >= State.ROWS) {
				continue;
			}
			Move move = new Move(start, new Position(r, c), null);
			try {
				sc.move(copy, move);
			} catch (IllegalMove e) {
				copy = state.copy();
				continue;
			}
			possibleMovesFromPosition.add(move);
			copy = state.copy();
		}

		for (int c = 0; c < State.COLS; c++) {
			int r = row - col + c;
			if (r < 0 || r >= State.ROWS) {
				continue;
			}
			Move move = new Move(start, new Position(r, c), null);
			try {
				sc.move(copy, move);
			} catch (IllegalMove e) {
				copy = state.copy();
				continue;
			}
			possibleMovesFromPosition.add(move);
			copy = state.copy();
		}

		return possibleMovesFromPosition;
	}

	private Set<Move> possibleMoveFromPosition_Circle(State state,
			Position start, int[] vDiff, int[] hDiff) {
		Set<Move> possibleMovesFromPosition = new HashSet<Move>();
		StateChangerImpl sc = new StateChangerImpl();
		State copy = state.copy();

		for (int i = 0; i < hDiff.length; i++) {
			int col = start.getCol() + hDiff[i];
			int row = start.getRow() + vDiff[i];

			if ((row >= 0 && row < State.ROWS) && (col >= 0 && col < State.COLS)) {
				Move move = new Move(start, new Position(row, col), null);
				try {
					sc.move(copy, move);
				} catch (IllegalMove e) {
					copy = state.copy();
					continue;
				}
				possibleMovesFromPosition.add(move);
				copy = state.copy();
			}
		}
		return possibleMovesFromPosition;
	}

	private Set<Move> possibleMoveFromPosition_Pawn(State state, Position start) {
		Set<Move> possibleMovesFromPosition = new HashSet<Move>();
		StateChangerImpl sc = new StateChangerImpl();
		State copy = state.copy();

		int vDiff[] = { 1, 2, 1, 1 };
		int hDiff[] = { 0, 0, 1, -1 };
		PieceKind promoteTo[] = { QUEEN, ROOK, BISHOP, KNIGHT };
		for (int i = 0; i < vDiff.length; i++) {
			int r = start.getRow() + vDiff[i]
					* (state.getTurn().isWhite() ? 1 : -1);
			int c = start.getCol() + hDiff[i];
			if ((r >= 0 && r < State.ROWS) && (c >= 0 && c < State.COLS)) {
				if (r == (state.getTurn().isWhite() ? State.ROWS - 1 : 0)) {
					for (PieceKind pk:promoteTo) {
						Move move = new Move(start, new Position(r, c),
								pk);
						try {
							sc.move(copy, move);
						} catch (IllegalMove e) {
							copy = state.copy();
							continue;
						}
						possibleMovesFromPosition.add(move);
						copy = state.copy();
					}
				} else {
					Move move = new Move(start, new Position(r, c), null);
					try {
						sc.move(copy, move);
					} catch (IllegalMove e) {
						copy = state.copy();
						continue;
					}
					possibleMovesFromPosition.add(move);
					copy = state.copy();
				}
			}
		}
		return possibleMovesFromPosition;
	}

}
