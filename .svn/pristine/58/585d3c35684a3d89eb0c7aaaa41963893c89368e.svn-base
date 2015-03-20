package org.markanderson.hw2;

import org.markanderson.hw2_5.StateExplorerImpl;
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
	// so that we don't have to set any pieces inside the isLegalMove...
	// methods,
	// which gets messy
	public boolean doPromotePawn;
	public boolean doEnpassantCapture;

	public void makeMove(State state, Move move) throws IllegalMove {
		Position from = move.getFrom();
		Position to = move.getTo();
		
		doPromotePawn = false;
		doEnpassantCapture = false;
		if (from.equals(to)) {
			// if player moves piece to same location
			// we are instructed to throw illegal move
			throw new IllegalMove();
		}
		if (isOutsideBounds(to)) {
			// outside of bounds
			throw new IllegalMove();
		}

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

		if (state.getGameResult() != null) {
			// Game already ended!
			throw new IllegalMove();
		}

		if (move.getPromoteToPiece() != null) {
			// trying to promote piece that is not a pawn
			if (!piece.equals(new Piece(color, PieceKind.PAWN))) {
				throw new IllegalMove();
			}
		}

		switch (piece.getKind()) {
		// check the legality for each piece movement
		case PAWN:
			if (!isLegalMovePawn(color, state, from, to, move)) {
				throw new IllegalMove();
			}
			break;
		case ROOK:
			if (!isLegalMoveRook(color, state, from, to)) {
				throw new IllegalMove();
			}
			break;
		case KNIGHT:
			if (!isLegalMoveKnight(color, state, from, to)) {
				throw new IllegalMove();
			}
			break;
		case BISHOP:
			if (!isLegalMoveBishop(color, state, from, to)) {
				throw new IllegalMove();
			}
			break;
		case QUEEN:
			if (!isLegalMoveQueen(color, state, from, to)) {
				throw new IllegalMove();
			}
			break;
		case KING:
			if (!isLegalMoveKing(color, state, from, to)) {
				throw new IllegalMove();
			}
			break;
		default:
			break;
		}

		if (doesPieceMovementJeopardizeKing(state, color, from, to)) {
			// check if the movement of the current piece puts our king in
			// jeopardy
			throw new IllegalMove();
		}

		if (canCapture(color, state.getPiece(to))
				|| piece.getKind().equals(PieceKind.PAWN)
				|| (move.getPromoteToPiece() != null)) {
			// keep track of the number of moves without capture
			// nor pawn moved...if the piece is a pawn, or will capture
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		} else {
			// otherwise increment
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
					.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		}

		if (doEnpassantCapture) {
			// if we flipped the flag inside the isLegalMovePawn() method,
			// we need to actually set the captured piece to null here.
			doEnpassantCapture = false;
			state.setPiece(state.getEnpassantPosition(), null);
		}

		if (piece.equals(new Piece(color, PieceKind.PAWN))
				&& to.getCol() == from.getCol()
				&& Math.abs(to.getRow() - from.getRow()) == 2) {
			// whether or not the enpassant capture was made,
			// we need to set the enpassantPosition to null unless
			// the current piece is a pawn is in position
			state.setEnpassantPosition(to);
		} else {
			state.setEnpassantPosition(null);
		}

		if (doPromotePawn) {
			doPromotePawn = false;
			// a pawn was promoted, set the piece, something other than
			// just the piece that is moving
			state.setPiece(to, new Piece(color, move.getPromoteToPiece()));
		} else {
			// here we check for a castling scenario. This is a case where we
			// need
			// to set more than one piece (king and rook)
			if (piece.equals(new Piece(color, PieceKind.KING))
					&& from.getRow() == to.getRow()
					&& (from.getCol() - 2 == to.getCol() || from.getCol() + 2 == to
							.getCol())) {
				// we castled...set the rook now
				if (from.getCol() > to.getCol()) {
					// queenside
					// set the rook
					state.setPiece(from.getRow(), 0, null);
					state.setPiece(new Position(from.getRow(), 3), new Piece(
							color, PieceKind.ROOK));
				} else {
					// kingside
					// set the rook
					state.setPiece(from.getRow(), 7, null);
					state.setPiece(new Position(from.getRow(), 5), new Piece(
							color, PieceKind.ROOK));
				}

			}
			// set the king
			state.setPiece(to, piece);
		}
		// set the from position to null
		state.setPiece(from, null);

		if (!positionForKing(Color.WHITE, state).equals(new Position(0, 4))) {
			// castling scenarios...if the king's current position is not
			// in the original position, set both bools to false
			state.setCanCastleKingSide(Color.WHITE, false);
			state.setCanCastleQueenSide(Color.WHITE, false);
		}

		if (!positionForKing(Color.BLACK, state).equals(new Position(7, 4))) {
			// same thing for black king
			state.setCanCastleKingSide(Color.BLACK, false);
			state.setCanCastleQueenSide(Color.BLACK, false);
		}

		// castling rook WHITE
		Piece whiteRook = new Piece(Color.WHITE, PieceKind.ROOK);
		if (!whiteRook.equals(state.getPiece(0, 0))) {
			// if white rook is not in its original position,
			// set queenside castling to false
			state.setCanCastleQueenSide(Color.WHITE, false);
		} else if (piece.equals(whiteRook) && to.equals(new Position(0, 0))) {
			// check to make sure it wasn't an promoted piece that is now a rook
			state.setCanCastleQueenSide(Color.WHITE, false);
		}

		if (!whiteRook.equals(state.getPiece(0, 7))) {
			// same thing for second white rook
			state.setCanCastleKingSide(Color.WHITE, false);
		} else if (piece.equals(whiteRook) && to.equals(new Position(0, 7))) {
			state.setCanCastleKingSide(Color.WHITE, false);
		}

		// castling rook BLACK
		Piece blackRook = new Piece(Color.BLACK, PieceKind.ROOK);
		if (!blackRook.equals(state.getPiece(7, 0))) {
			// same thing for first black rook
			state.setCanCastleQueenSide(Color.BLACK, false);
		} else if (piece.equals(blackRook) && to.equals(new Position(7, 0))) {
			state.setCanCastleQueenSide(Color.BLACK, false);
		}

		if (!blackRook.equals(state.getPiece(7, 7))) {
			// same thing for second black rook
			state.setCanCastleKingSide(Color.BLACK, false);
		} else if (piece.equals(blackRook) && to.equals(new Position(7, 7))) {
			state.setCanCastleKingSide(Color.BLACK, false);
		}

		// CHECKMATE / STALEMATE
		if (color.getOpposite().isWhite()) {
			StateExplorerImpl explorer = new StateExplorerImpl();
			State fakeState = state.copy();
			fakeState.setTurn(color.getOpposite());

			if (isKingInCheck(positionForKing(Color.WHITE, state), Color.WHITE,
					state)) {
				// if king is in check already and all of his moves will result
				// in check, then we have checkmate
				if (allPossibleMovesForWhiteKingResultInCheck(state)
						&& explorer.getPossibleMoves(fakeState).size() == 0) {
					// set game result accordingly for black as the winner
					state.setGameResult(new GameResult(Color.BLACK,
							GameResultReason.CHECKMATE));
				}
			} else {
				// the king is NOT in check...possible stalemate scenario
				if (!areStillPossibleMovesForPawns(Color.WHITE, state, move)
						&& allPossibleMovesForWhiteKingResultInCheck(state)
						&& explorer.getPossibleMoves(fakeState).size() == 0) {
					// we have a stalemate if no pawns can move, and
					// king's moves will result in check
					state.setGameResult(new GameResult(null,
							GameResultReason.STALEMATE));
				}
			}
		} else {
			// now check the scenarios for black king
			StateExplorerImpl explorer = new StateExplorerImpl();
			State fakeState = state.copy();
			fakeState.setTurn(color.getOpposite());

			// black king in possible check
			if (isKingInCheck(positionForKing(Color.BLACK, state), Color.BLACK,
					state)) {

				if (allPossibleMovesForBlackKingResultInCheck(state)
						&& explorer.getPossibleMoves(fakeState).size() == 0) {
					state.setGameResult(new GameResult(Color.WHITE,
							GameResultReason.CHECKMATE));
				}
			} else {
				if (!areStillPossibleMovesForPawns(Color.BLACK, state, move)
						&& allPossibleMovesForBlackKingResultInCheck(state)
						&& explorer.getPossibleMoves(fakeState).size() == 0) {
					// king is not in check; STALEMATE
					state.setGameResult(new GameResult(null,
							GameResultReason.STALEMATE));
				}
			}
		}

		if (state.getNumberOfMovesWithoutCaptureNorPawnMoved() > 99) {
			// if we have reached 100 moves, we have a draw
			state.setGameResult(new GameResult(null,
					GameResultReason.FIFTY_MOVE_RULE));
		}
		// set the next turn
		state.setTurn(color.getOpposite());
	}

	public boolean doesPieceMovementJeopardizeKing(State state, Color color,
			Position from, Position to) {
		Piece piece = state.getPiece(from);
		State tmpState = state.copy();
		tmpState.setPiece(from, null);
		tmpState.setPiece(to, piece);

		// corner case where this doesn't cover enpassant capture!
		if (state.getEnpassantPosition() != null
				&& piece.equals(new Piece(color, PieceKind.PAWN))) {
			Position enpassCapturePos = state.getEnpassantPosition();
			Position thisPawnCapturePos = new Position(from.getRow(),
					to.getCol());

			if (enpassCapturePos != null
					&& enpassCapturePos.equals(thisPawnCapturePos)) {
				// if we enpassant captured, check to see if that move
				// jeopardized the king
				tmpState.setPiece(enpassCapturePos, null);
			}

		}
		if (isKingInCheck(positionForKing(color, tmpState), color, tmpState)) {
			// check the tmpState that we set up, is king in check?
			return true;
		}
		return false;
	}

	public boolean isLegalMoveKing(Color color, State state, Position from,
			Position to) {
		int fromRow = from.getRow();
		int fromCol = from.getCol();
		int toRow = to.getRow();
		int toCol = to.getCol();

		// we are castling either king or queen side (2 squares right or left)
		if (fromRow == toRow && (fromCol - toCol == 2 || toCol - fromCol == 2)
				&& !isKingInCheck(from, color, state)) {

			if (fromCol - toCol == 2
					&& state.isCanCastleQueenSide(color)
					&& !isOccupied(new Position(color.equals(Color.WHITE) ? 0 : 7, 3), state)
					&& !isOccupied(new Position(color.equals(Color.WHITE) ? 0 : 7, 2), state)
					&& !isOccupied(new Position(color.equals(Color.WHITE) ? 0 : 7, 1), state)
					&& !isKingInCheck(fromRow, fromCol - 1, color, state)
					&& !isKingInCheck(fromRow, fromCol - 2, color, state)) {
				// can we castle? are the movements occupied by other pieces? are the movement
				// squares putting the king in jeopardy? these must pass to castle
				// queen side
				Piece rook = new Piece(color, PieceKind.ROOK);
				if (!rook.equals(state.getPiece(color.equals(Color.WHITE) ? 0 : 7, 0))) {
					// no rook where it is supposed to be, not a legal castle
					return false;
				}
				return true;
			} else if (toCol - fromCol == 2
					&& state.isCanCastleKingSide(color)
					&& !isEnemyPieceWithinMovement(state.getPiece(from), from, to, state) 
					&& !isOccupied(to, state)
					&& !isKingInCheck(fromRow, fromCol + 1, color, state)
					&& !isKingInCheck(fromRow, fromCol + 2, color, state)) {
				Piece rook = new Piece(color, PieceKind.ROOK);
				if (!rook.equals(state.getPiece(color.equals(Color.WHITE) ? 0
						: 7, 7))) {
					// no rook where it is supposed to be!
					return false;
				}
				// king side
				return true;
			} else {
				// must be an illegal move
				return false;
			}
		}
		// basic king movement: depending on direction of movement,
		// do some simple math to determine which row/ col we are moving to
		int canMoveRows = fromRow > toRow ? (fromRow - toRow) : (toRow - fromRow);
		int canMoveCols = fromCol > toCol ? (fromCol - toCol) : (toCol - fromCol);
		
		if ((canMoveRows == 0 || canMoveRows == 1)
				&& (canMoveCols == 0 || canMoveCols == 1)
				&& !isOccupiedBySameColor(color, state.getPiece(to))
				&& !isKingInCheck(to, color, state)) {
			// if we are moving by one square (row, col) and not in check after movement
			return true;
		}
		return false;
	}

	public boolean isLegalMoveQueen(Color color, State state, Position from,
			Position to) {
		// only need to check whether her moves are legal for rook and bishop
		if (isLegalMoveRook(color, state, from, to)
				|| isLegalMoveBishop(color, state, from, to)) {
			return true;
		}
		return false;
	}

	public boolean isLegalMoveKnight(Color color, State state, Position from,
			Position to) {
		int fromRow = from.getRow();
		int fromCol = from.getCol();
		
		// get the legal moves, and check if one of them is the move we are making 
		Position legalUpLeft = new Position(fromRow + 2, fromCol - 1);
		Position legalUpLeft2 = new Position(fromRow + 1, fromCol - 2);

		Position legalUpRight = new Position(fromRow + 2, fromCol + 1);
		Position legalUpRight2 = new Position(fromRow + 1, fromCol + 2);

		Position legalDownLeft = new Position(fromRow - 2, fromCol - 1);
		Position legalDownLeft2 = new Position(fromRow - 1, fromCol - 2);

		Position legalDownRight = new Position(fromRow - 2, fromCol + 1);
		Position legalDownRight2 = new Position(fromRow - 1, fromCol + 2);

		if ((to.equals(legalUpLeft) || to.equals(legalUpRight)
				|| to.equals(legalDownLeft) || to.equals(legalDownRight)
				|| to.equals(legalUpLeft2) || to.equals(legalUpRight2)
				|| to.equals(legalDownLeft2) || to.equals(legalDownRight2))
				&& !isOccupiedBySameColor(color, state.getPiece(to))) {
			return true;
		}
		return false;
	}

	public boolean isLegalMoveBishop(Color color, State state, Position from,
			Position to) {
		int fromRow = from.getRow();
		int fromCol = from.getCol();
		int toRow = to.getRow();
		int toCol = to.getCol();

		int numLoopsRows = toRow > fromRow ? (toRow - fromRow) : (fromRow - toRow);
		int numLoopsCols = toCol > fromCol ? (toCol - fromCol) : (fromCol - toCol);
		
		if (numLoopsRows != numLoopsCols) {
			// these are equal if we are moving diagonally
			return false;
		}

		if (!isEnemyPieceWithinMovement(state.getPiece(from), from, to, state)
				&& !isOccupiedBySameColor(color, state.getPiece(to))) {
			// no piece in the movement path or on the square we are moving to
			return true;
		} else {
			return false;
		}
	}

	public boolean isLegalMoveRook(Color color, State state, Position from,
			Position to) {
		int fromRow = from.getRow();
		int fromCol = from.getCol();
		int toRow = to.getRow();
		int toCol = to.getCol();

		// not moving horizontal or vertical, illegal
		if (toRow != fromRow && toCol != fromCol) {
			return false;
		}
		// piece is not in the movement path and not occupied by same color on the 'to' square
		if (!isEnemyPieceWithinMovement(state.getPiece(from), from, to, state)
				&& !isOccupiedBySameColor(color, state.getPiece(to))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isLegalMovePawn(Color color, State state, Position from,
			Position to, Move move) {
		// these primitives help for both black/ white movement so we don't need to repeat code
		int fromRow = from.getRow();
		int fromCol = from.getCol();
		int toRow = to.getRow();
		int toCol = to.getCol();
		int twoUp = color.isWhite() ? 2 : -2;
		int oneUp = color.isWhite() ? 1 : -1;
		int colLeft = -1;
		int colRight = 1;
		int startRowPos = color.isWhite() ? 1 : 6;
		int promoteRow = color.isWhite() ? 7 : 0;
		int enpassCaptureRow = color.isWhite() ? 4 : 3;

		if (Math.abs(fromRow - toRow) != 1 && Math.abs(fromRow - toRow) != 2
				&& Math.abs(fromCol - toCol) != 1) {
			// not moving one or two squares
			return false;
		}

		if (move.getPromoteToPiece() != null) {
			// check to make sure we are not promoting a pawn or king
			if (move.getPromoteToPiece().equals(PieceKind.KING)
					|| move.getPromoteToPiece().equals(PieceKind.PAWN)) {
				throw new IllegalMove();
			}
			if (toRow != promoteRow) {
				// not row 7 or 0
				throw new IllegalMove();
			}
		}

		// first start pos
		if (fromRow == startRowPos
				&& (fromRow + twoUp) == toRow
				&& (fromCol == toCol)
				&& !isEnemyPieceWithinMovement(state.getPiece(from), from, to,
						state) && !isOccupied(to, state)) {
			// moving two squares from start. legal
			return true;
		}
		if (toRow == promoteRow) {
			doPromotePawn = true;
		}
		Position upLeftPos = new Position(fromRow + oneUp, fromCol + colLeft);
		Position upRightPos = new Position(fromRow + oneUp, fromCol + colRight);
		Position upMidPos = new Position(fromRow + oneUp, fromCol);

		if (to.equals(upLeftPos) || to.equals(upRightPos)) {
			// capture scenario
			if (canCapture(color, state.getPiece(new Position(toRow, toCol)))) {
				return true;
			} else if (!isOccupied(to, state)) {
				// diagonal move is not occupied, possible enpassant scenario
				if (fromRow == enpassCaptureRow) {
					Position enpassCapturePos = state.getEnpassantPosition();
					Position thisPawnCapturePos = new Position(fromRow, toCol);

					if (enpassCapturePos != null
							&& enpassCapturePos.equals(thisPawnCapturePos)) {
						// set the flag and change the state later
						doEnpassantCapture = true;
						return true;
					}
				}
			}
		}
		if (to.equals(upMidPos) && !isOccupied(to, state)) {
			// we can move forward one space too.
			return true;
		}
		return false;
	}

	public Position positionForKing(Color color, State state) {
		// convenience method for finding the position of the king on the board
		Piece king = new Piece(color, PieceKind.KING);

		int row = -1;
		int col = -1;
		boolean found = false;
		for (int i = 0; i <= 7; i++) {
			if (found) {
				break;
			}
			for (int j = 0; j <= 7; j++) {
				if (king.equals(state.getPiece(i, j))) {
					row = i;
					col = j;
					found = true;
					break;
				}
			}
		}
		return new Position(row, col);
	}

	public boolean areStillPossibleMovesForPawns(Color color, State state, Move move) {
		// convenience method for seeing whether or not there are legal moves left
		// for pawns...this is for the case of stalemate
		Piece pawn = new Piece(color, PieceKind.PAWN);

		int rowOffset = color.equals(Color.WHITE) ? 1 : -1;
		int firstMoveOffset = color.equals(Color.WHITE) ? 2 : -2;
		int pawnStartPosRow = color.equals(Color.WHITE) ? 1 : 6;

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if (pawn.equals(state.getPiece(i, j))) {
					
					Position currPawnPos = new Position(i, j);
					// moving middle. 1 or 2 moves. IE are we in the starting
					// position?
					if (i == pawnStartPosRow
							&& !isEnemyPieceWithinMovement(pawn, currPawnPos,
									new Position(i + firstMoveOffset, j), state)
							&& !isOccupiedBySameColor(color,
									state.getPiece(new Position(i
											+ firstMoveOffset, j)))) {
						// we can move two spaces
						return true;
					}
					// does this pawn have moves?
					for (int k = -1; k < 2; k++) {
						if (!isOutsideBounds(i + rowOffset, j + k)) {
							if (k == -1 || k == 1) {
								// attacking diagonal
								Piece enemy = state.getPiece(i + rowOffset, j
										+ k);
								if (enemy != null
										&& !isOccupiedBySameColor(color, enemy)) {
									// enemy piece is here, we can move
									return true;
								}
							} else if (k == 0) {
								if (state.getPiece(i + rowOffset, j + k) == null) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		// no possible moves for pawns on board
		return false;
	}

	public boolean allPossibleMovesForWhiteKingResultInCheck(State state) {
		Position whiteKingPos = positionForKing(Color.WHITE, state);
		int row = whiteKingPos.getRow();
		int col = whiteKingPos.getCol();

		// we check all the squares around the king. a case where the square is not
		// outside the bounds and is a legal move will return true for this.
		if ((isOutsideBounds(row + 1, col) || !isLegalMoveKing(Color.WHITE,
				state, whiteKingPos, new Position(row + 1, col)))
				&& (isOutsideBounds(row + 1, col + 1) || !isLegalMoveKing(
						Color.WHITE, state, whiteKingPos, new Position(row + 1,
								col + 1)))
				&& (isOutsideBounds(row, col + 1) || !isLegalMoveKing(
						Color.WHITE, state, whiteKingPos, new Position(row,
								col + 1)))
				&& (isOutsideBounds(row - 1, col + 1) || !isLegalMoveKing(
						Color.WHITE, state, whiteKingPos, new Position(row - 1,
								col + 1)))
				&& (isOutsideBounds(row - 1, col) || !isLegalMoveKing(
						Color.WHITE, state, whiteKingPos, new Position(row - 1,
								col)))
				&& (isOutsideBounds(row - 1, col - 1) || !isLegalMoveKing(
						Color.WHITE, state, whiteKingPos, new Position(row - 1,
								col - 1)))
				&& (isOutsideBounds(row, col - 1) || !isLegalMoveKing(
						Color.WHITE, state, whiteKingPos, new Position(row,
								col - 1)))
				&& (isOutsideBounds(row + 1, col - 1) || !isLegalMoveKing(
						Color.WHITE, state, whiteKingPos, new Position(row + 1,
								col - 1)))) {
			return true;
		}
		return false;
	}

	public boolean allPossibleMovesForBlackKingResultInCheck(State state) {
		Position blackKingPos = positionForKing(Color.BLACK, state);
		int row = blackKingPos.getRow();
		int col = blackKingPos.getCol();

		// same as above method, for black king
		if ((isOutsideBounds(row + 1, col) || !isLegalMoveKing(Color.BLACK,
				state, blackKingPos, new Position(row + 1, col)))
				&& (isOutsideBounds(row + 1, col + 1) || !isLegalMoveKing(
						Color.BLACK, state, blackKingPos, new Position(row + 1,
								col + 1)))
				&& (isOutsideBounds(row, col + 1) || !isLegalMoveKing(
						Color.BLACK, state, blackKingPos, new Position(row,
								col + 1)))
				&& (isOutsideBounds(row - 1, col + 1) || !isLegalMoveKing(
						Color.BLACK, state, blackKingPos, new Position(row - 1,
								col + 1)))
				&& (isOutsideBounds(row - 1, col) || !isLegalMoveKing(
						Color.BLACK, state, blackKingPos, new Position(row - 1,
								col)))
				&& (isOutsideBounds(row - 1, col - 1) || !isLegalMoveKing(
						Color.BLACK, state, blackKingPos, new Position(row - 1,
								col - 1)))
				&& (isOutsideBounds(row, col - 1) || !isLegalMoveKing(
						Color.BLACK, state, blackKingPos, new Position(row,
								col - 1)))
				&& (isOutsideBounds(row + 1, col - 1) || !isLegalMoveKing(
						Color.BLACK, state, blackKingPos, new Position(row + 1,
								col - 1)))) {
			return true;
		}
		return false;
	}

	public boolean isKingInCheck(Position kingPos, Color kingColor, State state) {
		// this is convenience method to call all of the other isKingInCheck methods
		return isKingInCheckByPawn(kingPos, kingColor, state)
				|| isKingInCheckByKnight(kingPos, kingColor, state)
				|| isKingInCheckByKing(kingPos, kingColor, state)
				|| isKingInCheckByRook(kingPos, kingColor, state)
				|| isKingInCheckByBishop(kingPos, kingColor, state)
				|| isKingInCheckByQueen(kingPos, kingColor, state);
	}

	public boolean isKingInCheck(int kingRow, int kingCol, Color kingColor,
			State state) {
		// this is overloaded method in case we want to use 2 ints instead of position for input
		return isKingInCheck(new Position(kingRow, kingCol), kingColor, state);
	}

	public boolean isKingInCheckByKnight(Position kingPos, Color kingColor,
			State state) {
		int row = kingPos.getRow();
		int col = kingPos.getCol();
		Piece knight = new Piece(kingColor.getOpposite(), PieceKind.KNIGHT);

		// check each point around king where knight would place him in check
		if (!isOutsideBounds(row + 2, col + 1)
				&& knight.equals(state.getPiece(row + 2, col + 1))) {
			return true;
		}
		if (!isOutsideBounds(row + 1, col + 2)
				&& knight.equals(state.getPiece(row + 1, col + 2))) {
			return true;
		}
		if (!isOutsideBounds(row - 1, col + 2)
				&& knight.equals(state.getPiece(row - 1, col + 2))) {
			return true;
		}
		if (!isOutsideBounds(row - 2, col + 1)
				&& knight.equals(state.getPiece(row - 2, col + 1))) {
			return true;
		}
		if (!isOutsideBounds(row - 2, col - 1)
				&& knight.equals(state.getPiece(row - 2, col - 1))) {
			return true;
		}
		if (!isOutsideBounds(row - 1, col - 2)
				&& knight.equals(state.getPiece(row - 1, col - 2))) {
			return true;
		}
		if (!isOutsideBounds(row + 1, col - 2)
				&& knight.equals(state.getPiece(row + 1, col - 2))) {
			return true;
		}
		if (!isOutsideBounds(row + 2, col - 1)
				&& knight.equals(state.getPiece(row + 2, col - 1))) {
			return true;
		}
		return false;
	}

	public boolean isKingInCheckByQueen(Position kingPos, Color kingColor,
			State state) {

		// check each point around king where queen would place him in check
		if (isKingInCheckByRook(kingPos, kingColor, state)
				|| isKingInCheckByBishop(kingPos, kingColor, state)) {
			System.out.println(kingColor + " king is in check by Queen");
			return true;
		} else {
			return false;
		}
	}

	public boolean isKingInCheckByBishop(Position kingPos, Color kingColor,
			State state) {
		// check each point around king where bishop would place him in check
		int row = kingPos.getRow();
		int col = kingPos.getCol();
		boolean pieceFound = false;

		Piece bishop = new Piece(kingColor.getOpposite(), PieceKind.BISHOP);
		Piece queen = new Piece(kingColor.getOpposite(), PieceKind.QUEEN);
		Piece self = new Piece(kingColor, PieceKind.KING);

		int iteratorRow = 1;
		int iteratorCol = -1;
		// 1. check diag up left. trace each row and column in diagonal way
		while (!isOutsideBounds(row + iteratorRow, col + iteratorCol) && !pieceFound) {
			if (bishop.equals(state.getPiece(new Position(row + iteratorRow, col + iteratorCol)))
					|| queen.equals(state.getPiece(new Position(row
							+ iteratorRow, col + iteratorCol)))) {
				System.out.println(kingColor + " king is in check by Bishop");

				return true;
			} else if (state.getPiece(new Position(row + iteratorRow, col + iteratorCol)) != null
					&& !self.equals(state.getPiece(new Position(row
							+ iteratorRow, col + iteratorCol)))) {
				// 1b. another piece other than a bishop or queen is there. stop
				// looking and break out of the loop
				pieceFound = true;
			}
			iteratorRow = iteratorRow + 1;
			iteratorCol = iteratorCol - 1;
		}
		pieceFound = false;

		iteratorRow = 1;
		iteratorCol = 1;
		// 1. check diag up right. do the same as before, but up to the right
		while (!isOutsideBounds(row + iteratorRow, col + iteratorCol)
				&& !pieceFound) {
			if (bishop.equals(state.getPiece(new Position(row + iteratorRow,
					col + iteratorCol)))
					|| queen.equals(state.getPiece(new Position(row
							+ iteratorRow, col + iteratorCol)))) {
				return true;
			} else if (state.getPiece(new Position(row + iteratorRow, col
					+ iteratorCol)) != null
					&& !self.equals(state.getPiece(new Position(row
							+ iteratorRow, col + iteratorCol)))) {
				// 1b. another piece other than a bishop or queen is there. stop
				// looking
				pieceFound = true;
			}
			iteratorRow = iteratorRow + 1;
			iteratorCol = iteratorCol + 1;
		}
		pieceFound = false;

		iteratorRow = -1;
		iteratorCol = -1;
		// 1. check diag down left
		while (!isOutsideBounds(row + iteratorRow, col + iteratorCol)
				&& !pieceFound) {
			if (bishop.equals(state.getPiece(new Position(row + iteratorRow,
					col + iteratorCol)))
					|| queen.equals(state.getPiece(new Position(row
							+ iteratorRow, col + iteratorCol)))) {
				System.out.println(kingColor + " king is in check by Bishop");
				return true;
			} else if (state.getPiece(new Position(row + iteratorRow, col
					+ iteratorCol)) != null
					&& !self.equals(state.getPiece(new Position(row
							+ iteratorRow, col + iteratorCol)))) {
				// 1b. another piece other than a bishop or queen is there. stop
				// looking
				pieceFound = true;
			}
			iteratorRow = iteratorRow - 1;
			iteratorCol = iteratorCol - 1;
		}
		pieceFound = false;

		iteratorRow = -1;
		iteratorCol = 1;
		// 1. check diag down right
		while (!isOutsideBounds(row + iteratorRow, col + iteratorCol)
				&& !pieceFound) {
			if (bishop.equals(state.getPiece(new Position(row + iteratorRow,
					col + iteratorCol)))
					|| queen.equals(state.getPiece(new Position(row
							+ iteratorRow, col + iteratorCol)))) {
				System.out.println(kingColor + " king is in check by Bishop");
				return true;
			} else if (state.getPiece(new Position(row + iteratorRow, col
					+ iteratorCol)) != null
					&& !self.equals(state.getPiece(new Position(row
							+ iteratorRow, col + iteratorCol)))) {
				// 1b. another piece other than a bishop or queen is there. stop
				// looking
				pieceFound = true;
			}
			iteratorRow = iteratorRow - 1;
			iteratorCol = iteratorCol + 1;
		}
		return false;
	}

	public boolean isKingInCheckByPawn(Position kingPos, Color kingColor,
			State state) {
		int kingRow = kingPos.getRow();
		int kingCol = kingPos.getCol();
		// find the side that the attacking piece is coming from
		int checkAttackerSide = kingColor == Color.WHITE ? kingRow + 1 : kingRow - 1;

		if (isOutsideBounds(checkAttackerSide, kingCol)) {
			// make sure we are not outside bounds
			return false;
		}
		Piece pawn = new Piece(kingColor.getOpposite(), PieceKind.PAWN);
		// handle if we are out of bounds when we check left or right.
		if (kingCol != 0) {
			if (pawn.equals(state.getPiece(new Position(checkAttackerSide,
					kingCol - 1)))) {
				System.out.println(kingColor + " king is in check by Pawn");
				return true;
			}
		}
		if (kingCol != 7) {
			if (pawn.equals(state.getPiece(new Position(checkAttackerSide,
					kingCol + 1)))) {
				System.out.println(kingColor + " king is in check by Pawn");
				return true;
			}
		}
		return false;
	}

	public boolean isKingInCheckByKing(Position kingPos, Color kingColor,
			State state) {
		int startRow = kingPos.getRow();
		int startCol = kingPos.getCol();

		Piece enemyKing = new Piece(kingColor.getOpposite(), PieceKind.KING);

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				Position toPos = new Position(startRow + i, startCol + j);
				if (!isOutsideBounds(startRow + i, startCol + j)) {
					if (enemyKing.equals(state.getPiece(toPos))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isKingInCheckByRook(Position kingPos, Color kingColor,
			State state) {
		int row = kingPos.getRow();
		int col = kingPos.getCol();
		boolean otherFound = false;

		Piece rook = new Piece(kingColor.getOpposite(), PieceKind.ROOK);
		Piece queen = new Piece(kingColor.getOpposite(), PieceKind.QUEEN);
		Piece self = new Piece(kingColor, PieceKind.KING);

		while (!isOutsideBounds(row, col) && !otherFound) {
			if (!isOutsideBounds(row, col - 1)) {
				if (rook.equals(state.getPiece(new Position(row, col - 1)))
						|| queen.equals(state.getPiece(new Position(row,
								col - 1)))) {
					return true;
				} else if (state.getPiece(new Position(row, col - 1)) != null
						&& !self.equals(state.getPiece(new Position(row,
								col - 1)))) {
					// 1b. another piece other than a rook or queen is there.
					// stop looking
					otherFound = true;
				}
			}
			col--;
		}
		col = kingPos.getCol();
		otherFound = false;

		// 2. check right
		while (!isOutsideBounds(row, col) && !otherFound) {
			if (!isOutsideBounds(row, col + 1)) {
				if (rook.equals(state.getPiece(new Position(row, col + 1)))
						|| queen.equals(state.getPiece(new Position(row,
								col + 1)))) {
					System.out.println(kingColor + " king is in check by Rook");
					return true;
				} else if (state.getPiece(new Position(row, col + 1)) != null
						&& !self.equals(state.getPiece(new Position(row,
								col + 1)))) {
					// 1b. another piece other than a rook or queen is there.
					// stop looking
					otherFound = true;
				}
			}
			col++;
		}
		col = kingPos.getCol();
		otherFound = false;

		// 2. check up
		while (!isOutsideBounds(row, col) && !otherFound) {
			if (!isOutsideBounds(row + 1, col)) {
				if (rook.equals(state.getPiece(new Position(row + 1, col)))
						|| queen.equals(state.getPiece(new Position(row + 1,
								col)))) {
					return true;
				} else if (state.getPiece(new Position(row + 1, col)) != null
						&& !self.equals(state.getPiece(new Position(row + 1,
								col)))) {
					// 1b. another piece other than a rook is there. stop
					// looking
					otherFound = true;
				}
			}
			row++;
		}
		row = kingPos.getRow();
		otherFound = false;

		// 2. check down
		while (!isOutsideBounds(row, col) && !otherFound) {
			if (!isOutsideBounds(row - 1, col)) {
				if (rook.equals(state.getPiece(new Position(row - 1, col)))
						|| queen.equals(state.getPiece(new Position(row - 1,
								col)))) {
					return true;
				} else if (state.getPiece(new Position(row - 1, col)) != null
						&& !self.equals(state.getPiece(new Position(row - 1,
								col)))) {
					// 1b. another piece other than a rook is there. stop
					// looking
					otherFound = true;
				}
			}
			row--;
		}
		// no check found
		return false;
	}

	public boolean isOutsideBounds(int toRow, int toCol) {
		// convenience method for outside bounds of game board check
		if (toRow > 7 || toRow < 0 || toCol > 7 || toCol < 0) {
			// outside bounds
			return true;
		}
		return false;
	}

	public boolean isOutsideBounds(Position to) {
		// convenience overloaded method for outside bounds of game board
		return isOutsideBounds(to.getRow(), to.getCol());
	}

	public boolean isEnemyPieceWithinMovement(Piece movingPiece, Position from,
			Position to, State state) {
		// knight is hopping, we don't need to check for him.
		if (movingPiece.equals(new Piece(movingPiece.getColor(),
				PieceKind.KNIGHT))) {
			return false;
		}

		int fromRow = from.getRow();
		int fromCol = from.getCol();
		int toRow = to.getRow();
		int toCol = to.getCol();

		int iteratorRow = 0;
		int iteratorCol = 0;
		int count = 0;
		
		// get the direction for the iterator (loop)
		if (fromRow < toRow && fromCol == toCol) {
			// 1. up
			iteratorRow = 1;
			iteratorCol = 0;
			count = Math.abs(fromRow - toRow);
		} else if (fromRow > toRow && fromCol == toCol) {
			// 2. down
			iteratorRow = -1;
			iteratorCol = 0;
			count = Math.abs(fromRow - toRow);
		} else if (fromRow == toRow && fromCol > toCol) {
			// 3. left
			iteratorRow = 0;
			iteratorCol = -1;
			count = Math.abs(fromCol - toCol);
		} else if (fromRow == toRow && fromCol < toCol) {
			// 4. right
			iteratorRow = 0;
			iteratorCol = 1;
			count = Math.abs(fromCol - toCol);
		} else if (fromRow < toRow && fromCol > toCol) {
			// 5. diag up left
			iteratorRow = 1;
			iteratorCol = -1;
			count = Math.abs(fromRow - toRow);
		} else if (fromRow < toRow && fromCol < toCol) {
			// 6. diag up right
			iteratorRow = 1;
			iteratorCol = 1;
			count = Math.abs(fromRow - toRow);
		} else if (fromRow > toRow && fromCol > toCol) {
			// 7. diag down left
			iteratorRow = -1;
			iteratorCol = -1;
			count = Math.abs(fromRow - toRow);
		} else if (fromRow > toRow && fromCol < toCol) {
			// 8. diag down right
			iteratorRow = -1;
			iteratorCol = 1;
			count = Math.abs(fromRow - toRow);
		}

		for (int i = 0; i < count - 1; i++) {
			// based on the direction we are checking,
			// iterate and look for another occupying piece
			if (state.getPiece(fromRow + iteratorRow, fromCol + iteratorCol) != null) {
				return true;
			}
			// increment
			fromRow += iteratorRow;
			fromCol += iteratorCol;
		}
		return false;
	}

	public boolean isOccupied(Position to, State state) {
		// sometimes we want to check if is occupied
		return state.getPiece(to) != null;
	}

	public boolean isOccupiedBySameColor(Color color, Piece piece) {
		// sometimes we need to check whether the position is occupied by the same color
		if (piece == null) {
			return false;
		}
		if (piece.getColor().equals(color)) {
			return true;
		}
		return false;
	}

	public boolean canCapture(Color color, Piece piece) {
		// sometimes we need to check if the piece can capture
		if (piece != null && !piece.getColor().equals(color)) {
			return true;
		}
		return false;
	}
}