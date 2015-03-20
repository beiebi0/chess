package org.wenjiechen.hw2_5;

import java.util.*;

import org.shared.chess.Color;
import org.shared.chess.PieceKind;
import org.shared.chess.Move;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;

public class StateExplorerImpl implements StateExplorer {

	// remove unnecessary state.copy()	
	@Override
	public Set<Move> getPossibleMoves(State state) {
		Set<Position> selfPieces = getPieces(state, state.getTurn());
		Set<Move> allMoves = Sets.newHashSet();
		for (Position piece : selfPieces) {
			allMoves.addAll(getPossibleMovesFromPosition(state,piece));
		}
	return allMoves;
	}

	@Override
	public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
		Set<Move> moves = new HashSet<Move>();
		MoveChecker moveChecker = new MoveChecker();
		if (state.getTurn() == state.getPiece(start).getColor()) {
			for (int row = 0; row < 8; ++row) {
				for (int col = 0; col < 8; ++col) {
					Position possibleTo = new Position(row, col);
					if (moveChecker.isMoveLegal(state, start, possibleTo, null) == true) {
						if (moveChecker.mustPromotion(state, start, possibleTo) == false) {
							moves.add(new Move(start, possibleTo, null));
						} else {
							moves.add(new Move(start, possibleTo,
									PieceKind.BISHOP));
							moves.add(new Move(start, possibleTo,
									PieceKind.KNIGHT));
							moves.add(new Move(start, possibleTo,
									PieceKind.QUEEN));
							moves.add(new Move(start, possibleTo,
									PieceKind.ROOK));
						}
					}// outer if
				}
			}// outer for
		}
		return moves;
	}

	@Override
	public Set<Position> getPossibleStartPositions(State state) {
		Set<Move> moves = getPossibleMoves(state);
		Set<Position> startPos = Sets.newHashSet();
		for (Move m : moves) {
			startPos.add(m.getFrom());
		}
		return startPos;
	}
	
	/**
	 * save "side" side pieces or opponent piece
	 */
	private Set<Position> getPieces(State state, Color side){
		Set<Position> pieces = Sets.newHashSet();
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				if (state.getPiece(row, col) != null
						&& state.getPiece(row, col).getColor() == side) {
					pieces.add(new Position(row, col));
				}
			}
		}// outer for
		return pieces;
	}
	
	public static void main(String[] args) {
		State state = new State();
		StateExplorerImpl stateExplorer = new StateExplorerImpl();
		stateExplorer.getPossibleMoves(state);
	}
}
