package org.bohouli.hw2;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import java.lang.Math;
import java.util.Set;


import com.google.common.collect.Sets;

public class StateChangerImpl implements StateChanger {
	public void makeMove(State state, Move move) throws IllegalMove {
		if (state.getGameResult() != null) {
			// Game already ended!
			throw new IllegalMove();
		}
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		if (piece == null) {
			// Nothing to move!
			throw new IllegalMove();
		}
		Color color = piece.getColor();
		if (color != state.getTurn()) {
			// Wrong player moves!
			throw new IllegalMove();
		}
		if (from.equals(move.getTo())) {
			// From and To are the same!
			throw new IllegalMove();
		}
		if(move.getPromoteToPiece() != null && piece.getKind() != PieceKind.PAWN) {
			// Illegal promotion!
			throw new IllegalMove();
		}

		// TODO: implement chess logic in HW2.
		switch (piece.getKind()) {
		case KING:
			kingMove(state, move);
			break;
		case QUEEN:
			queenMove(state, move);
			break;
		case ROOK:
			rookMove(state, move);
			break;
		case BISHOP:
			bishopMove(state, move);
			break;
		case KNIGHT:
			knightMove(state, move);
			break;
		case PAWN:
			pawnMove(state, move);
			break;
		}
		
		Color turn = (piece.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE;
		state.setTurn(turn);
		
		setCastlingBooleans(state, move);
		
		if (isUnderCheck(state, getKingPosition(state, color), color))
			throw new IllegalMove();

		if (state.getNumberOfMovesWithoutCaptureNorPawnMoved() == 100)
			state.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));
		
		//check stalemate or checkmate
		if (cannotEscapeCheck(state)) { 
			if(isUnderCheck(state, getKingPosition(state, turn), turn))
				state.setGameResult(new GameResult(color, GameResultReason.CHECKMATE));
			else 
				state.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		}
		
		/*
		if (isThreeFold()) {
			//three fold
		}*/
	}

	//If to or from are associated with rook positions, castling booleans should be set.
	private void setCastlingBooleans(State state, Move move) {
		Position from = move.getFrom();
		Position to = move.getTo();

		if (from.getRow() == 0 && from.getCol() == 0)
			state.setCanCastleQueenSide(Color.WHITE, false);
		else if (from.getRow() == 0 && from.getCol() == 7)
			state.setCanCastleKingSide(Color.WHITE, false);
		else if (from.getRow() == 7 && from.getCol() == 0)
			state.setCanCastleQueenSide(Color.BLACK, false);
		else if (from.getRow() == 7 && from.getCol() == 7)
			state.setCanCastleKingSide(Color.BLACK, false);

		if (to.getRow() == 0 && to.getCol() == 0)
			state.setCanCastleQueenSide(Color.WHITE, false);
		else if (to.getRow() == 0 && to.getCol() == 7)
			state.setCanCastleKingSide(Color.WHITE, false);
		else if (to.getRow() == 7 && to.getCol() == 0)
			state.setCanCastleQueenSide(Color.BLACK, false);
		else if (to.getRow() == 7 && to.getCol() == 7)
			state.setCanCastleKingSide(Color.BLACK, false);
	}

	private boolean isOnBoard(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		if(row > -1 && row < 8 && col > -1 && col < 8)
			return true;
		else
			return false;
	}
	
	//Check if it is dead
	private boolean cannotEscapeCheck(State state) {
		Set<Move> moves = Sets.newHashSet();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Position start = new Position(i, j);
				Piece cur = state.getPiece(start);
				Color color = state.getTurn();
				if (cur != null && cur.getColor() == color) {
					Set<Move> move = getPossibleMoves(state, start);
					moves.addAll(move);
				}
			}
		}

		if (moves.isEmpty())
			return true;
		else
			return false;
	}
	
	//Get possible moves from start position
	public Set<Move> getPossibleMoves(State state, Position start) {
		Set<Move> moves = Sets.newHashSet();
		Piece cur = state.getPiece(start);
		Color color = state.getTurn();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Position to = new Position(i, j);
				if (!start.equals(to)) {
					State probeState = state.copy();
					Move move = new Move(start, to, null);
					try {
						switch (cur.getKind()) {
						case KING:
							kingMove(probeState, move);
							break;
						case QUEEN:
							queenMove(probeState, move);
							break;
						case ROOK:
							rookMove(probeState, move);
							break;
						case BISHOP:
							bishopMove(probeState, move);
							break;
						case KNIGHT:
							knightMove(probeState, move);
							break;
						case PAWN: {
							if(to.getRow() == 7 || to.getRow() == 0)
								move = new Move(start, to, PieceKind.QUEEN);
							pawnMove(probeState, move);
							break;
						}
						}
					} catch (IllegalMove e) {
						continue;
					}
					
					if (!isUnderCheck(probeState,
							getKingPosition(probeState, color), color)) {
						if(move.getPromoteToPiece() == null)
							moves.add(move);
						else {
							moves.add(new Move(start, to, PieceKind.QUEEN));
							moves.add(new Move(start, to, PieceKind.BISHOP));
							moves.add(new Move(start, to, PieceKind.KNIGHT));
							moves.add(new Move(start, to, PieceKind.ROOK));
						}
					}
				}
			}
		}
		return moves;
	}

	private Position getKingPosition(State state, Color color) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Piece cur = state.getPiece(i, j);
				if(cur != null && cur.getKind() == PieceKind.KING && cur.getColor() == color)
					return new Position(i, j);
			}
		}
		return null;
	}
	
	private boolean isRowUnderCheck(State state, Position from, Position to) {
		int row = from.getRow();
		int increment = (to.getCol() - from.getCol() > 0) ? 1 : -1;
		for(int i = from.getCol(); i != to.getCol(); i += increment) {
			if(isUnderCheck(state, new Position(row, i), state.getTurn()))
				return true;
		}
		
		return false;
	}
	
	//examine piece in position to, and with color is under check
	private boolean isUnderCheck(State state, Position to, Color color) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Position from = new Position(i, j);
				Piece cur = state.getPiece(from);
				if (cur != null && cur.getColor() != color && !from.equals(to))
					if (canCapture(state, from, to))
						return true;
			}
		}

		return false;
	}
	
	//Test if a piece can be captured in to
	private boolean canCapture(State state, Position from, Position to) {
		switch (state.getPiece(from).getKind()) {
		case KING:
			return kingCapture(state, from, to);
		case QUEEN:
			return queenCapture(state, from, to);
		case ROOK:
			return rookCapture(state, from, to);
		case BISHOP:
			return bishopCapture(state, from, to);
		case KNIGHT:
			return knightCapture(state, from, to);
		case PAWN:
			return pawnCapture(state, from, to);
		default:
			return false;
		}
	}
	
	private boolean isRouteOccupied(State state, Position from, Position to) {
		int rowOffset = to.getRow() - from.getRow();
		int colOffset = to.getCol() - from.getCol();
		int offset = Math.max(Math.abs(rowOffset), Math.abs(colOffset));
		int rowIncre = rowOffset / offset;
		int colIncre = colOffset / offset;
		int i = from.getRow() + rowIncre;
		int j = from.getCol() + colIncre;

		while (i != to.getRow() || j != to.getCol()) {
			if (state.getPiece(i, j) != null)
				return true;
			i += rowIncre;
			j += colIncre;
		}
		
		return false;
	}
	
	private boolean kingCapture(State state, Position from, Position to) {
		if (Math.abs(from.getCol() - to.getCol()) <= 1
				&& Math.abs(from.getRow() - to.getRow()) <= 1)
			return true;
		else
			return false;
	}
	
	private boolean queenCapture(State state, Position from, Position to) {
		if (Math.abs(from.getCol() - to.getCol()) == Math.abs(from.getRow() - to.getRow())) {
			if (isRouteOccupied(state, from, to))
				return false;
		} else if (from.getRow() == to.getRow()) {	// Row
			if (isRouteOccupied(state, from, to))
				return false;
		} else if (from.getCol() == to.getCol()) {	//Column
			if (isRouteOccupied(state, from, to))
				return false;
		} else
			return false;
		
		return true;
	}
	
	private boolean rookCapture(State state, Position from, Position to) {
		if (from.getRow() == to.getRow()) {
			if (isRouteOccupied(state, from, to))
				return false;
		} else if (from.getCol() == to.getCol()) {
			if (isRouteOccupied(state, from, to))
				return false;
		} else
			return false;
		
		return true;
	}
	
	private boolean bishopCapture(State state, Position from, Position to) {
		if (Math.abs(from.getCol() - to.getCol()) == Math.abs(from.getRow() - to.getRow())) {
			if (isRouteOccupied(state, from, to))
				return false;
			else
				return true;
		} else
			return false;
	}
	
	private boolean knightCapture(State state, Position from, Position to) {
		if ((Math.abs(from.getCol() - to.getCol()) == 1 
				&& Math.abs(from.getRow() - to.getRow()) == 2)
				|| (Math.abs(from.getCol() - to.getCol()) == 2 
				&& Math.abs(from.getRow() - to.getRow()) == 1)) {
			return true;
		} else
			return false;
	}
	
	private boolean pawnCapture(State state, Position from, Position to) {
		int direction = (state.getPiece(from).getColor() == Color.WHITE) ? 1 : -1;

		if (from.getRow() + direction == to.getRow()
				&& Math.abs(from.getCol() - to.getCol()) == 1) {
			/*
			if (state.getPiece(to) == null 
					|| state.getPiece(to).getColor() != state.getPiece(from).getColor())
				return true;
			else
				return false;*/
			return true;
		} else
			return false;
	}
	
	private void kingMove(State state, Move move) throws IllegalMove {
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Position to = move.getTo();
		if(!isOnBoard(to))
			throw new IllegalMove();

		if (Math.abs(from.getCol() - to.getCol()) <= 1
				&& Math.abs(from.getRow() - to.getRow()) <= 1) {
			if (state.getPiece(to) == null)
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
						.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
			else if (state.getPiece(to).getColor() != piece.getColor())
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			else
				throw new IllegalMove();
		}// castling
		else if (piece.getColor() == Color.WHITE && from.equals(new Position(0, 4))
				&& to.equals(new Position(0, 2))) {
			if (state.isCanCastleQueenSide(Color.WHITE)
					&& !isRouteOccupied(state, new Position(0, 4), new Position(0, 0))
					&& !isRowUnderCheck(state, new Position(0, 4), new Position(0, 1))) {
				state.setPiece(new Position(0, 3), new Piece(Color.WHITE,
						PieceKind.ROOK));
				state.setPiece(new Position(0, 0), null);
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
						.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
			} else
				throw new IllegalMove();
		} else if (piece.getColor() == Color.WHITE
				&& from.equals(new Position(0, 4)) && to.equals(new Position(0, 6))) {
			if (state.isCanCastleKingSide(Color.WHITE)
					&& !isRouteOccupied(state, new Position(0, 4), new Position(0, 7))
					&& !isRowUnderCheck(state, new Position(0, 4), new Position(0, 7))) {
				state.setPiece(new Position(0, 5), new Piece(Color.WHITE,
						PieceKind.ROOK));
				state.setPiece(new Position(0, 7), null);
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
						.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
			} else
				throw new IllegalMove();
		} else if (piece.getColor() == Color.BLACK
				&& from.equals(new Position(7, 4)) && to.equals(new Position(7, 2))) {
			if (state.isCanCastleQueenSide(Color.BLACK)
					&& !isRouteOccupied(state, new Position(7, 4), new Position(7, 0))
					&& !isRowUnderCheck(state, new Position(7, 4), new Position(7, 1))) {
				state.setPiece(new Position(7, 3), new Piece(Color.BLACK,
						PieceKind.ROOK));
				state.setPiece(new Position(7, 0), null);
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
						.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
			} else
				throw new IllegalMove();
		} else if (piece.getColor() == Color.BLACK
				&& from.equals( new Position(7, 4)) && to.equals(new Position(7, 6))) {
			if (state.isCanCastleKingSide(Color.BLACK)
					&& !isRouteOccupied(state, new Position(7, 4), new Position(7, 7))
					&& !isRowUnderCheck(state, new Position(7, 4), new Position(7, 7))) {
				state.setPiece(new Position(7, 5), new Piece(Color.BLACK,
						PieceKind.ROOK));
				state.setPiece(new Position(7, 7), null);
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
						.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
			} else
				throw new IllegalMove();
		} else
			throw new IllegalMove();

		state.setPiece(from, null);
		state.setPiece(to, new Piece(piece.getColor(), piece.getKind()));
		state.setEnpassantPosition(null);
		state.setCanCastleQueenSide(piece.getColor(), false);
		state.setCanCastleKingSide(piece.getColor(), false);
	}

	private void queenMove(State state, Move move) throws IllegalMove {
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Position to = move.getTo();
		if(!isOnBoard(to))
			throw new IllegalMove();
		
		//Diagnal
		if (Math.abs(from.getCol() - to.getCol()) == Math.abs(from.getRow() - to.getRow())) {
			if(isRouteOccupied(state, from, to))
				throw new IllegalMove();
		} else if (from.getRow() == to.getRow()) {	// Row
			if(isRouteOccupied(state, from, to))
				throw new IllegalMove();
		} else if (from.getCol() == to.getCol()) {	//Column
			if(isRouteOccupied(state, from, to))
				throw new IllegalMove();
		} else
			throw new IllegalMove();
		
		if (state.getPiece(to) == null)
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(
					state.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		else if (state.getPiece(to).getColor() != piece.getColor())
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		else
			throw new IllegalMove();

		state.setPiece(from, null);
		state.setPiece(to, new Piece(piece.getColor(), piece.getKind()));
		state.setEnpassantPosition(null);
	}

	private void rookMove(State state, Move move) throws IllegalMove {
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Position to = move.getTo();
		if(!isOnBoard(to))
			throw new IllegalMove();

		if (from.getRow() == to.getRow()) {
			if(isRouteOccupied(state, from, to))
				throw new IllegalMove();
		} else if (from.getCol() == to.getCol()) {
			if(isRouteOccupied(state, from, to))
				throw new IllegalMove();
		} else
			throw new IllegalMove();
		
		if (state.getPiece(to) == null)
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
					.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		else if (state.getPiece(to).getColor() != piece.getColor())
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		else
			throw new IllegalMove();

		state.setPiece(from, null);
		state.setPiece(to, new Piece(piece.getColor(), piece.getKind()));
		state.setEnpassantPosition(null);
	}

	private void bishopMove(State state, Move move) throws IllegalMove {
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Position to = move.getTo();
		if(!isOnBoard(to))
			throw new IllegalMove();

		if (Math.abs(from.getCol() - to.getCol()) == Math.abs(from.getRow()
				- to.getRow())) {
			if(isRouteOccupied(state, from, to))
				throw new IllegalMove();

			if (state.getPiece(to) == null)
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
						.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
			else if (state.getPiece(to).getColor() != piece.getColor())
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			else
				throw new IllegalMove();
		} else
			throw new IllegalMove();

		state.setPiece(from, null);
		state.setPiece(to, new Piece(piece.getColor(), piece.getKind()));
		state.setEnpassantPosition(null);
	}

	private void knightMove(State state, Move move) throws IllegalMove {
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Position to = move.getTo();
		if(!isOnBoard(to))
			throw new IllegalMove();

		if ((Math.abs(from.getCol() - to.getCol()) == 1 
				&& Math.abs(from.getRow() - to.getRow()) == 2)
				|| (Math.abs(from.getCol() - to.getCol()) == 2 
				&& Math.abs(from.getRow() - to.getRow()) == 1)) {
			if (state.getPiece(to) == null)
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(
						state.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
			else if (state.getPiece(to).getColor() != state.getPiece(from)
					.getColor())
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			else
				throw new IllegalMove();
		} else
			throw new IllegalMove();

		state.setPiece(from, null);
		state.setPiece(to, new Piece(piece.getColor(), piece.getKind()));
		state.setEnpassantPosition(null);
	}

	private void pawnMove(State state, Move move) throws IllegalMove {
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Position to = move.getTo();
		if(!isOnBoard(to))
			throw new IllegalMove();
		int direction = (piece.getColor() == Color.WHITE) ? 1 : -1;
		
		if (move.getPromoteToPiece() == null) {
			if ((piece.getColor() == Color.BLACK && from.getRow() == 1)
			|| (piece.getColor() == Color.WHITE && from.getRow() == 6))
				throw new IllegalMove();
			
			if (from.getRow() + direction == to.getRow() && from.getCol() == to.getCol()) {
				if (state.getPiece(to) == null) {
					state.setEnpassantPosition(null);
				} else
					throw new IllegalMove();
			} else if (from.getRow() + direction * 2 == to.getRow()
					&& from.getCol() == to.getCol()) {
				if ((piece.getColor() == Color.WHITE && from.getRow() == 1)
						|| (piece.getColor() == Color.BLACK && from.getRow() == 6)) {
					if ((state.getPiece(from.getRow() + direction, from.getCol()) == null)
							&& state.getPiece(to) == null && move.getPromoteToPiece() == null)
						state.setEnpassantPosition(to);
					else
						throw new IllegalMove();
				} else
					throw new IllegalMove();
			} else if (from.getRow() + direction == to.getRow()
					&& Math.abs(from.getCol() - to.getCol()) == 1) {
				if (state.getPiece(to) == null) { /* enpassent */
					if (state.getEnpassantPosition() == null)
						throw new IllegalMove();
					else if (from.getRow() == state.getEnpassantPosition().getRow()
							&& to.getCol() == state.getEnpassantPosition().getCol()) {
						state.setPiece(state.getEnpassantPosition(), null);
						state.setEnpassantPosition(null);
					} else
						throw new IllegalMove();
				} else if (state.getPiece(to).getColor() != piece.getColor()) {
					state.setEnpassantPosition(null);
				} else
					throw new IllegalMove();
			} else
				throw new IllegalMove();
		} else {	// Promotion
			if ((Math.abs(from.getCol() - to.getCol()) == 1 && state.getPiece(to) != null &&
					state.getPiece(to).getColor() != state.getPiece(from).getColor())
					|| (from.getCol() == to.getCol() && state.getPiece(to) == null)) {
				if (piece.getColor() == Color.WHITE && from.getRow() == 6 && to.getRow() == 7) {
					if (move.getPromoteToPiece() == PieceKind.KING
							|| move.getPromoteToPiece() == PieceKind.PAWN)
						throw new IllegalMove();
					else
						piece = new Piece(piece.getColor(),
								move.getPromoteToPiece());
				} else if (piece.getColor() == Color.BLACK 
						&& from.getRow() == 1 && to.getRow() == 0) {
					if (move.getPromoteToPiece() == PieceKind.KING
							|| move.getPromoteToPiece() == PieceKind.PAWN)
						throw new IllegalMove();
					else
						piece = new Piece(piece.getColor(),
								move.getPromoteToPiece());
				} else
					throw new IllegalMove();
			} else
				throw new IllegalMove();
		}

		state.setPiece(from, null);
		state.setPiece(to, new Piece(piece.getColor(), piece.getKind()));
		state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	}
}
