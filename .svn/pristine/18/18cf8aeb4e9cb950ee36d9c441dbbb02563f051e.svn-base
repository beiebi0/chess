package org.markanderson.hw2_5;

import java.util.ArrayList;
import java.util.Set;

import org.markanderson.hw2.StateChangerImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
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
		int rows = 8;
		int cols = 8;
		
		Set<Move> possibleMoves = Sets.newHashSet();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Position fromPos = new Position(i, j);
				possibleMoves.addAll(getPossibleMovesFromPosition(state, fromPos));
			}
		}
		return possibleMoves;
	}

	@Override
	public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
		
		Set<Move> possibleMoves = Sets.newHashSet();

		if (start == null) {
			return possibleMoves;
		}
		if (state.getPiece(start) == null) {
			return possibleMoves;
		}
		if (state.getGameResult() != null) {
			return possibleMoves;
		}
		if (state.getNumberOfMovesWithoutCaptureNorPawnMoved() > 99) {
			state.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));
			return possibleMoves;
		}
		
		int rows = 8;
		int cols = 8;
		
		Color color = state.getPiece(start).getColor();
		Piece piece = new Piece(color, state.getPiece(start).getKind());
		StateChangerImpl stateChanger = new StateChangerImpl();
		Move move;
		
		if (!state.getTurn().equals(color)) {
			// not the correct turn. no possible moves for piece
			return possibleMoves;
		}
		switch (piece.getKind()) {
		case PAWN:
			int numPawnMoves = 4;
			ArrayList<Position> pawnArray = new ArrayList<Position>();
			ArrayList<PieceKind> pieceKindArray = new ArrayList<PieceKind>();

			int twoUp = color.isWhite() ? 2 : -2;
			int oneUp = color.isWhite() ? 1 : -1;
			int colLeft = -1;
			int colRight = 1;

			pawnArray.add(new Position(start.getRow() + oneUp, start.getCol() + colRight));
			pawnArray.add(new Position(start.getRow() + oneUp, start.getCol() + colLeft));
			pawnArray.add(new Position(start.getRow() + oneUp, start.getCol()));
			pawnArray.add(new Position(start.getRow() + twoUp, start.getCol()));

			pieceKindArray.add(PieceKind.BISHOP);
			pieceKindArray.add(PieceKind.ROOK);
			pieceKindArray.add(PieceKind.QUEEN);
			pieceKindArray.add(PieceKind.KNIGHT);
			
			int promotionRow = color.isWhite() ? 7 : 0;

			for (int i = 0; i < numPawnMoves; i++) {
				for (int j = 0; j < numPawnMoves; j++) {
					// set a flag for promotion, promotion row being 7 or 0 depending on the color
					boolean shouldPromote = pawnArray.get(i).getRow() == promotionRow ? true : false;
					PieceKind kind = shouldPromote ? pieceKindArray.get(j) : null;
					
					// make the move
					move = new Move(start, pawnArray.get(i), kind);
					if (!stateChanger.isOutsideBounds(move.getTo()) &&
							!stateChanger.doesPieceMovementJeopardizeKing(state, color, start, pawnArray.get(i)) &&
							stateChanger.isLegalMovePawn(color, state, start, pawnArray.get(i), move)) {
						// add moves if we are !outside bounds, movement will not put our king in
						// danger, and if it is in fact a legal move
						possibleMoves.add(move);
					}
				}
			}
			break;
		case ROOK:
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					Position toPos = new Position(i, j);
					if (!stateChanger.isOutsideBounds(toPos) &&
							stateChanger.isLegalMoveRook(color, state, start, toPos) &&
							!start.equals(toPos) &&
							!stateChanger.doesPieceMovementJeopardizeKing(state, color, start, toPos)) {
						// add moves by iterating over the board. if it is a legal move, if it is
						// not the same to position as from position, and if piece movement doesn't
						// put our king in danger.
						move = new Move(start, toPos, null);
						possibleMoves.add(move);
					}
				}
			}

			break;
		case KNIGHT:
			int numKnightMoves = 8;
			ArrayList<Position> knightArray = new ArrayList<Position>();
			
			// make an array of possible knight moves.
			knightArray.add(new Position(start.getRow() + 2, start.getCol() - 1));
			knightArray.add(new Position(start.getRow() + 2, start.getCol() + 1));
			knightArray.add(new Position(start.getRow() + 1, start.getCol() - 2));
			knightArray.add(new Position(start.getRow() + 1, start.getCol() + 2));
			knightArray.add(new Position(start.getRow() - 2, start.getCol() - 1));
			knightArray.add(new Position(start.getRow() - 2, start.getCol() + 1));
			knightArray.add(new Position(start.getRow() - 1, start.getCol() - 2));
			knightArray.add(new Position(start.getRow() - 1, start.getCol() + 2));

			for (int i = 0; i < numKnightMoves; i++) {
				move = new Move(start, knightArray.get(i), null);
				if (!stateChanger.isOutsideBounds(move.getTo()) && 
						stateChanger.isLegalMoveKnight(color, state, start, knightArray.get(i)) &&
						!stateChanger.doesPieceMovementJeopardizeKing(state, color, start, knightArray.get(i))) {
					// check each move from the array and be sure that we can make the move
					possibleMoves.add(move);
				}
			}
			break;
		case BISHOP:
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					Position toPos = new Position(i, j);
					if (!stateChanger.isOutsideBounds(toPos) &&
							stateChanger.isLegalMoveBishop(color, state, start, toPos) &&
							!start.equals(toPos) &&
							!stateChanger.doesPieceMovementJeopardizeKing(state, color, start, toPos)) {
						move = new Move(start, toPos, null);
						possibleMoves.add(move);
					}
				}
			}
			
			break;
		case QUEEN:
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						Position toPos = new Position(i, j);
						if (!stateChanger.isOutsideBounds(new Position(i, j)) &&
								(stateChanger.isLegalMoveBishop(color, state, start, toPos) ||
								stateChanger.isLegalMoveRook(color, state, start, toPos)) &&
								!start.equals(toPos) &&
								!stateChanger.doesPieceMovementJeopardizeKing(state, color, start, toPos)) {
							move = new Move(start, toPos, null);
							possibleMoves.add(move);
						}
					}
				}
			break;
		case KING:
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					Position toPos = new Position(i, j);
					
					if (!stateChanger.isOutsideBounds(toPos) && 
							stateChanger.isLegalMoveKing(color, state, start, toPos) &&
							!stateChanger.doesPieceMovementJeopardizeKing(state, color, start, toPos) &&
							!start.equals(toPos)) {
						move = new Move(start, toPos, null);
						possibleMoves.add(move);
					}
				}
			}
			break;
		}		
		return possibleMoves;
	}

	@Override
	public Set<Position> getPossibleStartPositions(State state) {

		Set<Position> startPositions = Sets.newHashSet();
		Set<Move> possibleMoves = getPossibleMoves(state);
		
		for (Move m : possibleMoves) {
			startPositions.add(m.getFrom());
		}
		
		return startPositions;
	}
}