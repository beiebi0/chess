package org.longjuntan.hw2;

import org.longjuntan.hw2_5.StateExplorerImpl;
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

public class StateChangerImpl implements StateChanger {
	public void makeMove(State state, Move move) throws IllegalMove {
		if (state.getGameResult() != null) {
			// Game already ended!
			throw new IllegalMove();
		}

		Color turn = move(state, move);
		
		StateExplorerImpl se = new StateExplorerImpl();

		// End of game
		if (state.getNumberOfMovesWithoutCaptureNorPawnMoved() == 100) {
			state.setGameResult(new GameResult(null,
					GameResultReason.FIFTY_MOVE_RULE));
		} else if (se.getPossibleMoves(state).size()==0) {
			if (undercheck(turn, findingKing(turn, state), state)) {
				state.setGameResult(new GameResult(turn.getOpposite(),
						GameResultReason.CHECKMATE));

			} else {
				state.setGameResult(new GameResult(null,
						GameResultReason.STALEMATE));
			}
		}

	}

	/**
	 * To check whether there is something else between two position 
	 * 	vertically or horizontally
	 * @param state Current state
	 * @param from The position from which the move is
	 * @param to The position to which the move is
	 * @return true if exist something between the two positions, false for nothing
	 */
	private boolean isSthInTheWay_Cross(State state, Position from, Position to) {
		if (from.getCol() == to.getCol()) {
			//Vertically
			int diff = (to.getRow() - from.getRow())
					/ Math.abs(from.getRow() - to.getRow());
			if (Math.abs(from.getRow() - to.getRow()) == 1) {
				return false;
			}
			for (int i = from.getRow() + diff;; i += diff) {
				if (i == to.getRow())
					break;
				if (state.getPiece(i, from.getCol()) != null) {
					return true;
				}
			}
		} else if (from.getRow() == to.getRow()) {
			//Horizontally
			if (Math.abs(from.getCol() - to.getCol()) == 1) {
				return false;
			}
			int diff = (to.getCol() - from.getCol())
					/ Math.abs(from.getCol() - to.getCol());
			for (int i = from.getCol() + diff;; i += diff) {
				if (i == to.getCol())
					break;
				if (state.getPiece(from.getRow(), i) != null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * To check whether there is something else between two position diagonally
	 * @param state Current state
	 * @param from The position from which the move is
	 * @param to The position to which the move is
	 * @return true if exist something between the two positions, false for nothing
	 */
	private boolean isSthInTheWay_Diagonal(State state, Position from,
			Position to) {
		int hDiff = (to.getCol() - from.getCol())
				/ Math.abs(from.getCol() - to.getCol());
		int vDiff = (to.getRow() - from.getRow())
				/ Math.abs(from.getRow() - to.getRow());
		for (int i = from.getCol() + hDiff, j = from.getRow() + vDiff;; i += hDiff, j += vDiff) {
			if (i == to.getCol()) {
				break;
			}
			if (state.getPiece(j, i) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The helper function for setting "canCastle" when something happens to ROOK
	 * @param state Current state
	 * @param position The position for the rook changed
	 * @param color The color for that changed rook
	 */
	private void setCastling(State state, Position position, Color color) {
		int col = position.getCol();
		int row = position.getRow();

		if (row == (color.isWhite() ? 0 : 7)) {
			if (col == 0 && state.isCanCastleQueenSide(color)) {
				state.setCanCastleQueenSide(color, false);
			} else if (col == 7 && state.isCanCastleKingSide(color)) {
				state.setCanCastleKingSide(color, false);
			}
		}
	}

	/**
	 * The helper function to make a legal move, setting other relevant variables
	 * @param state Current state
	 * @param from The position from which the move is
	 * @param to The position to which the move is
	 * @param addition the flag using for Castling or Promotion
	 */
	private void makeMoveHelper(State state, Position from, Position to, PieceKind addition) {
		Piece pieceFrom = state.getPiece(from);
		if (state.getPiece(to) != null) {
			//Capture happens
			Piece pieceTo = state.getPiece(to);
			if (pieceFrom.getColor() == pieceTo.getColor()) {
				throw new IllegalMove();
			} else {
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
				if (pieceTo.getKind() == PieceKind.ROOK) {
					//when a rook is captured, set related "canCastle" to false
					setCastling(state, to, pieceTo.getColor());
				}
			}
		} else {
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
					.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		}

		//By default,clear enpassant position in every move
		state.setEnpassantPosition(null);

		Color color = pieceFrom.getColor();
		switch (pieceFrom.getKind()) {
		case ROOK:
			//when a rook moves, set related "canCastle" to false
			setCastling(state, from, color);
			break;
		case KING:
			//when a king moves, set related "canCastle" to false
			state.setCanCastleKingSide(color, false);
			state.setCanCastleQueenSide(color, false);
			if(addition == PieceKind.ROOK){
				if(to.equals(new Position(color.isWhite()?0:7,2))){
					state.setPiece(new Position(color.isWhite()?0:7,0), null);
					state.setPiece(new Position(color.isWhite()?0:7,3), new Piece(color,PieceKind.ROOK));
				}else{
					state.setPiece(new Position(color.isWhite()?0:7,7), null);
					state.setPiece(new Position(color.isWhite()?0:7,5), new Piece(color,PieceKind.ROOK));
				}
			}
			break;
		case PAWN:
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			if (Math.abs(to.getRow() - from.getRow()) == 2) {
				//When enpassant happens, set the enpassant position
				state.setEnpassantPosition(to);
			}
			if(addition != null){
				pieceFrom = new Piece(color,addition);
			}
			break;
		default:
			break;
		}

		state.setPiece(from, null);
		state.setPiece(to, pieceFrom);

		state.setTurn(pieceFrom.getColor().getOpposite());
	}

	/**
	 * To check whether this move is Enpassant or not
	 * @param state Current state
	 * @param from The position from which the move is
	 * @param to The position to which the move is
	 * @return true if it's an Enpassant move, otherwise false
	 */
	private boolean isEnpassant(State state, Position from, Position to) {
		Color color = state.getPiece(state.getEnpassantPosition()).getColor();
		Piece pieceFrom = state.getPiece(from);
		int col = state.getEnpassantPosition().getCol();
		int row = state.getEnpassantPosition().getRow();
		return (state.getPiece(to) == null)
				&& (color != pieceFrom.getColor()
				&& (to.equals(new Position(row + (color.isWhite() ? -1 : 1), col))) 
				&& (from.equals(new Position(row, col + 1)) 
				|| from.equals(new Position(row, col - 1))));
	}
	
	/**
	 * To check whether current situation satisfy castling
	 * @param side 2 if King side, otherwise Queen side
	 * @param state Current state
	 * @param from The position from which the move is
	 * @param to The position to which the move is
	 * @return true if it's an Castling move, otherwise false
	 */
	private boolean isCastling(int side, State state, Position from, Position to){
		Color color = state.getPiece(from).getColor();

		if (side == 2) {// King side
			return from.getRow() == to.getRow()
					&& from.equals(new Position(color.isWhite() ? 0 : 7, 4))
					&& !isSthInTheWay_Cross(state, from,
							new Position(color.isWhite() ? 0 : 7, 7))
					&& state.isCanCastleKingSide(color)
					&& !undercheck(color, new Position(color.isWhite() ? 0 : 7,
							5), state);
		} else {// Queen side
			return from.getRow() == to.getRow()
					&& from.equals(new Position(color.isWhite() ? 0 : 7, 4))
					&& !isSthInTheWay_Cross(state, from,
							new Position(color.isWhite() ? 0 : 7, 0))
					&& state.isCanCastleQueenSide(color)
					&& !undercheck(color, new Position(color.isWhite() ? 0 : 7,
							3), state);
		}
	}
	
	/**
	 * To find out the position of the king
	 * @param color The color of the side
	 * @param state Current state
	 * @return the position of that king
	 */
	private Position findingKing(Color color, State state){
		Position king = null;
		loop:
		for(int row =0;row<State.ROWS; row++){
			for(int col = 0; col<State.COLS;col++){
				if(state.getPiece(row, col)!=null){
					if(state.getPiece(row, col).equals(new Piece(color,PieceKind.KING))){
						king = new Position(row,col);
						break loop;
					}
				}
			}
		}
		return king;
	}
	
	/**
	 * To check if one side is under check
	 * @param color The color of side
	 * @param position The position for the king
	 * @param state Current state
	 * @return true if under check, false if not
	 */
	private boolean undercheck(Color color, Position position, State state) {
		for (int row = 0; row < State.ROWS; row++) {
			for (int col = 0; col < State.COLS; col++) {
				Piece other = state.getPiece(row, col);
				if (other != null) {
					if (other.getColor() == color)
						continue;
					if (check(state, position, new Position(row, col))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * To check if the position for the king is in check by certain piece
	 * @param state Current state
	 * @param king The position for king
	 * @param other The position for other certain piece
	 * @return true if the king is in check, false if not
	 */
	private boolean check(State state, Position king, Position other) {

		int dCol = king.getCol() - other.getCol();
		int dRow = king.getRow() - other.getRow();

		Piece piece = state.getPiece(other);

		switch (piece.getKind()) {
		case PAWN:
			if ((dCol == 1 || dCol == -1)
					&& ((piece.getColor().isWhite() && (dRow == 1)) || (piece
							.getColor().isBlack() && (dRow == -1)))) {
				return true;
			}
			break;
		case BISHOP:
			if (Math.abs(dCol) == Math.abs(dRow)
					&& !isSthInTheWay_Diagonal(state, king, other)) {
				return true;
			}
			break;
		case KING:
			if (Math.abs(dCol) <= 1 && Math.abs(dRow) <= 1) {
				return true;
			}
			break;
		case KNIGHT:
			if ((Math.abs(dCol) == 1 && Math.abs(dRow) == 2)
					|| (Math.abs(dCol) == 2 && Math.abs(dRow) == 1)) {
				return true;
			}
			break;
		case QUEEN:
			if ((Math.abs(dCol) == Math.abs(dRow) && !isSthInTheWay_Diagonal(
					state, king, other))
					|| ((Math.abs(dCol) == 0 || Math.abs(dRow) == 0) && !isSthInTheWay_Cross(
							state, king, other))) {
				return true;
			}
			break;
		case ROOK:
			if ((Math.abs(dCol) == 0 || Math.abs(dRow) == 0)
					&& !isSthInTheWay_Cross(state, king, other)) {
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	

	/**
	 * To make sure whether the move is valid 
	 * @param state Current state
	 * @param move The move to be made
	 * @return If the move is valid, return the color for next turn.
	 * @throws IllegalMove
	 */
	public Color move(State state, Move move) throws IllegalMove {

		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		if (piece == null) {
			// Nothing to move!
			throw new IllegalMove();
		}
		Color color = piece.getColor();
		Position to = move.getTo();

		if (color != state.getTurn()) {
			// Wrong player moves!
			throw new IllegalMove();
		}

		if (from.equals(to)) {
			throw new IllegalMove();
		}

		// hMove for horizontal and vMove for vertical
		int hMove = to.getCol() - from.getCol();
		int vMove = to.getRow() - from.getRow();

		// make sure only pawn can promote
		if (move.getPromoteToPiece() != null
				&& !piece.getKind().equals(PieceKind.PAWN)) {
			throw new IllegalMove();
		}

		State original = state.copy();

		switch (piece.getKind()) {
		case PAWN:
			if (move.getPromoteToPiece() != null
					&& !(to.getRow() == (color.isWhite() ? 7 : 0))) {
				throw new IllegalMove();
			}
			if (to.getRow() == (color.isWhite() ? 7 : 0)
					&& (move.getPromoteToPiece() == null
							|| move.getPromoteToPiece().equals(PieceKind.KING) || move
							.getPromoteToPiece().equals(PieceKind.PAWN))) {
				throw new IllegalMove();
			}
			if ((state.getEnpassantPosition() != null)
					&& isEnpassant(state, from, to)) {
				// Enpassant happens
				Position enpassant = state.getEnpassantPosition();
				makeMoveHelper(state, from, to, null);
				state.setPiece(enpassant, null);
			} else {
				if (Math.abs(hMove) > 1) {
					throw new IllegalMove();
				} else if (Math.abs(hMove) == 1) {
					if (vMove != (color.isWhite() ? 1 : -1)
							|| state.getPiece(to) == null) {
						throw new IllegalMove();
					} else {// move straight 1 step without capture
						makeMoveHelper(state, from, to,
								move.getPromoteToPiece());
					}
				} else {
					if (vMove == (color.isWhite() ? 1 : -1)
							&& (state.getPiece(to) == null)) {
						makeMoveHelper(state, from, to,
								move.getPromoteToPiece());
					} else if (((vMove == (color.isWhite() ? 2 : -2))
							&& (state.getPiece(to) == null) && (state.getPiece(
							from.getRow() + (color.isWhite() ? 1 : -1),
							to.getCol()) == null))) {
						if (from.getRow() == (color.isWhite() ? 1 : 6)) {
							makeMoveHelper(state, from, to,
									move.getPromoteToPiece());
						} else {
							throw new IllegalMove();
						}
					} else {
						throw new IllegalMove();
					}
				}
			}
			break;

		case ROOK:
			if ((vMove != 0) && (hMove != 0)) {
				throw new IllegalMove();
			} else {
				if (isSthInTheWay_Cross(state, from, to)) {
					throw new IllegalMove();
				} else {
					makeMoveHelper(state, from, to, null);
				}
			}
			break;

		case KNIGHT:
			if ((Math.abs(vMove) == 1 && Math.abs(hMove) == 2)
					|| (Math.abs(vMove) == 2 && Math.abs(hMove) == 1)) {
				makeMoveHelper(state, from, to, null);
			} else {
				throw new IllegalMove();
			}
			break;

		case BISHOP:
			if (Math.abs(vMove) != Math.abs(hMove)) {
				throw new IllegalMove();
			} else {
				if (isSthInTheWay_Diagonal(state, from, to)) {
					throw new IllegalMove();
				} else {
					makeMoveHelper(state, from, to, null);
				}
			}
			break;

		case QUEEN:
			if (Math.abs(vMove) == Math.abs(hMove)) {
				if (isSthInTheWay_Diagonal(state, from, to)) {
					throw new IllegalMove();
				} else {
					makeMoveHelper(state, from, to, null);
				}
			} else if (hMove == 0 || vMove == 0) {
				if (isSthInTheWay_Cross(state, from, to)) {
					throw new IllegalMove();
				} else {
					makeMoveHelper(state, from, to, null);
				}
			} else {
				throw new IllegalMove();
			}
			break;

		case KING:
			if (Math.abs(vMove) >= 2 || Math.abs(hMove) > 2) {
				throw new IllegalMove();
			} else if (Math.abs(hMove) == 2) {
				if (isCastling(hMove, state, from, to)
						&& !undercheck(color, findingKing(color, state), state)) {
					makeMoveHelper(state, from, to, PieceKind.ROOK);
				} else {
					throw new IllegalMove();
				}
			} else {
				makeMoveHelper(state, from, to, null);
			}
			break;

		default:
			break;
		}

		if (undercheck(color, findingKing(color, state), state)) {
			state = original;
			throw new IllegalMove();
		}

		return state.getTurn();
	}
}
