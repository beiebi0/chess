package org.longjuntan.hw2_5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

public class Copy_3_of_StateExplorerImpl implements StateExplorer {

	@Override
	public Set<Move> getPossibleMoves(State state) {
		if (state.getGameResult() != null) {
			return Sets.newHashSet();
		}
		
		Set<Move> possibleMoves = new HashSet<Move>();
		Color color = state.getTurn();

		State copy = state.copy();
		StateChangerImpl sc = new StateChangerImpl();

		for (int row = 0; row < State.ROWS; row++) {
			for (int col = 0; col < State.COLS; col++) {
				Piece piece = copy.getPiece(row, col);
				if (piece != null) {
					if (piece.getColor() != color)
						continue;
					else {
						// list for PieceKind to possible promote
						List<PieceKind> list = new ArrayList<PieceKind>();
						list.add(null);
						if (piece.getKind() == PieceKind.PAWN) {
							list.add(PieceKind.QUEEN);
							list.add(PieceKind.BISHOP);
							list.add(PieceKind.KNIGHT);
							list.add(PieceKind.ROOK);
						} else if (piece.getKind() == PieceKind.KING) {
							list.add(PieceKind.ROOK);
						}
						for (PieceKind pk : list) {
							for (int i = 0; i < State.ROWS; i++) {
								for (int j = 0; j < State.COLS; j++) {
									try {
										sc.move(copy, new Move(new Position(
												row, col), new Position(i, j), pk));
									} catch (IllegalMove e) {
										copy = state.copy();
										continue;
									}
									possibleMoves.add(new Move(new Position(
											row, col), new Position(i, j), pk));
									copy = state.copy();
								}
							}
						}
					}
				}
			}
		}
		return possibleMoves;
	}

	@Override
	public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
		Set<Move> possibleMovesFromPosition = new HashSet<Move>();
		for (Move move : getPossibleMoves(state)) {
			if (start.equals(move.getFrom())) {
				possibleMovesFromPosition.add(move);
			}
		}
		return possibleMovesFromPosition;
	}

	@Override
	public Set<Position> getPossibleStartPositions(State state) {
		Set<Position> startPositions = new HashSet<Position>();
		for (Move move : getPossibleMoves(state)) {
			startPositions.add(move.getFrom());
		}
		return startPositions;
	}

}
