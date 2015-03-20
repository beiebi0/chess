package org.simongellis.hw2_5;

import java.util.HashSet;
import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

public class StateExplorerImpl implements StateExplorer {

	@Override
	public Set<Move> getPossibleMoves(State state) {
		Set<Move> moves = new HashSet<Move>();
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col) {
				moves.addAll(getPossibleMovesFromPosition(state, new Position(row, col)));
			}
		}
		return moves;
	}

	@Override
	public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
		Set<Move> moves = new HashSet<Move>();
		if (state.getGameResult() != null)
			return moves;
		if (state.getPiece(start) == null || state.getPiece(start).getColor() != state.getTurn())
			return moves;
		int row = start.getRow(), col = start.getCol();
		Position testPos;
		switch(state.getPiece(start).getKind()) {
		case BISHOP:
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, -1, -1));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, -1, 1));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, 1, -1));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, 1, 1));
			break;
		case KING:
			for (int dr = -1; dr < 2; ++dr) {
				for (int dc = -1; dc < 2; ++dc) {
					if (!(dr == 0 && dc == 0)) {
						testPos = new Position(row + dr, col + dc);
						if (isMoveSafe(state, start, testPos))
							moves.add(new Move(start, testPos, null));
					}
				}
			}
			if (state.isCanCastleKingSide(state.getTurn())
					&& row == (state.getPiece(start).getColor().isWhite() ? 0 : 7)
					&& col == 4
					&& state.getPiece(row, 5) == null
					&& state.getPiece(row, 6) == null
					&& state.getPiece(row, 7) != null
					&& state.getPiece(row, 7).getColor() == state.getPiece(start).getColor()
					&& state.getPiece(row, 7).getKind() == PieceKind.ROOK) {
				testPos = new Position(row, col + 2);
				if (!doesMoveCauseCheck(state, start, testPos))
					moves.add(new Move(start, testPos, null));
			}
			if (state.isCanCastleQueenSide(state.getTurn())
					&& row == (state.getPiece(start).getColor().isWhite() ? 0 : 7)
					&& col == 4
					&& state.getPiece(row, 3) == null
					&& state.getPiece(row, 2) == null
					&& state.getPiece(row, 1) == null
					&& state.getPiece(row, 0) != null
					&& state.getPiece(row, 0).getColor() == state.getPiece(start).getColor()
					&& state.getPiece(row, 0).getKind() == PieceKind.ROOK) {
				testPos = new Position(row, col - 2);
				if (!doesMoveCauseCheck(state, start, testPos))
					moves.add(new Move(start, testPos, null));
			}
			break;
		case KNIGHT:
			testPos = new Position(row - 2, col - 1);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			testPos = new Position(row - 2, col + 1);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			testPos = new Position(row - 1, col - 2);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			testPos = new Position(row - 1, col + 2);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			testPos = new Position(row + 1, col - 2);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			testPos = new Position(row + 1, col + 2);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			testPos = new Position(row + 2, col - 1);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			testPos = new Position(row + 2, col + 1);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			break;
		case PAWN:
			int wayForward = state.getTurn().isWhite() ? 1 : -1;
			int initialRow = state.getTurn().isWhite() ? 1 : 6;
			int endRow = state.getTurn().isWhite() ? 7 : 0;
			testPos = new Position(row + wayForward, col);
			if (state.getPiece(testPos) == null && !doesMoveCauseCheck(state, start, testPos)) {
				if (testPos.getRow() == endRow) {
					moves.add(new Move(start, testPos, PieceKind.BISHOP));				
					moves.add(new Move(start, testPos, PieceKind.KNIGHT));				
					moves.add(new Move(start, testPos, PieceKind.QUEEN));				
					moves.add(new Move(start, testPos, PieceKind.ROOK));				
				}
				else
					moves.add(new Move(start, testPos, null));
			}
			testPos = new Position(row + 2 * wayForward, col);
			if (row == initialRow
					&& state.getPiece(row + wayForward, col) == null
					&& state.getPiece(testPos) == null
					&& !doesMoveCauseCheck(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			if (col > 0) {
				testPos = new Position(row + wayForward, col - 1);
				if (state.getPiece(testPos) != null
						&& state.getPiece(testPos).getColor() != state.getTurn()
						&& !doesMoveCauseCheck(state, start, testPos)) {
					if (testPos.getRow() == endRow) {
						moves.add(new Move(start, testPos, PieceKind.BISHOP));				
						moves.add(new Move(start, testPos, PieceKind.KNIGHT));				
						moves.add(new Move(start, testPos, PieceKind.QUEEN));				
						moves.add(new Move(start, testPos, PieceKind.ROOK));				
					}
					else
						moves.add(new Move(start, testPos, null));
				}
				if (state.getEnpassantPosition() != null
						&& state.getEnpassantPosition().getRow() == row
						&& state.getEnpassantPosition().getCol() == col - 1
						&& !doesMoveCauseCheck(state, start,testPos)) {
					if (testPos.getRow() == endRow) {
						moves.add(new Move(start, testPos, PieceKind.BISHOP));				
						moves.add(new Move(start, testPos, PieceKind.KNIGHT));				
						moves.add(new Move(start, testPos, PieceKind.QUEEN));				
						moves.add(new Move(start, testPos, PieceKind.ROOK));				
					}
					else
						moves.add(new Move(start, testPos, null));
				}
			}
			if (col < State.COLS - 1) {
				testPos = new Position(row + wayForward, col + 1);
				if (state.getPiece(testPos) != null
						&& state.getPiece(testPos).getColor() != state.getTurn()
						&& !doesMoveCauseCheck(state, start, testPos)) {
					if (testPos.getRow() == endRow) {
						moves.add(new Move(start, testPos, PieceKind.BISHOP));				
						moves.add(new Move(start, testPos, PieceKind.KNIGHT));				
						moves.add(new Move(start, testPos, PieceKind.QUEEN));				
						moves.add(new Move(start, testPos, PieceKind.ROOK));				
					}
					else
						moves.add(new Move(start, testPos, null));
				}
				if (state.getEnpassantPosition() != null
						&& state.getEnpassantPosition().getRow() == row
						&& state.getEnpassantPosition().getCol() == col + 1
						&& !doesMoveCauseCheck(state, start,testPos)) {
					if (testPos.getRow() == endRow) {
						moves.add(new Move(start, testPos, PieceKind.BISHOP));				
						moves.add(new Move(start, testPos, PieceKind.KNIGHT));				
						moves.add(new Move(start, testPos, PieceKind.QUEEN));				
						moves.add(new Move(start, testPos, PieceKind.ROOK));				
					}
					else
						moves.add(new Move(start, testPos, null));
				}
			}
			break;
		case QUEEN:
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, -1, -1));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, -1, 1));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, 1, -1));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, 1, 1));			
		case ROOK:
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, -1, 0));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, 1, 0));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, 0, -1));
			moves.addAll(getPossibleMovesFromPositionInDirection(state, start, 0, 1));
			break;
		default:
			break;
		}
		return moves;
	}

	@Override
	public Set<Position> getPossibleStartPositions(State state) {
		Set<Position> startPositions = new HashSet<Position>();
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col) {
				Position currentPos = new Position(row, col);
				if (!getPossibleMovesFromPosition(state, currentPos).isEmpty())
					startPositions.add(currentPos);
			}
		}
		return startPositions;
	}
	
	//Returns a list of Moves to all possible positions in a given direction.
	//Used to find the possible moves of queens, bishops, and rooks.
	private Set<Move> getPossibleMovesFromPositionInDirection(State state, Position start, int dr, int dc) {
		Set<Move> moves = new HashSet<Move>();
		int testRow = start.getRow() + dr, testCol = start.getCol() + dc;
		while (isPositionOnBoard(testRow, testCol)) {
			Position testPos = new Position(testRow, testCol);
			if (isMoveSafe(state, start, testPos))
				moves.add(new Move(start, testPos, null));
			if (state.getPiece(testRow, testCol) != null)
				break;
			testRow += dr;
			testCol += dc;
		}
		return moves;
	}

	//Makes a copy of the board, executes a move on that copy, and calls isKingInCheck on the copy.
	//The move has already been confirmed to be otherwise legal.
	//This is identical to the function of the same name in my StateChangerImpl.
	//I copied it to avoid writing any public methods that weren't in the StateExplorer interface.
	private boolean doesMoveCauseCheck(State state, Position startPos, Position endPos) {
		int startRow = startPos.getRow(), startCol = startPos.getCol();
		int endCol = endPos.getCol();		
		Piece movingPiece = state.getPiece(startPos);
		Color currentTurn = state.getTurn();
		
		//Kingside castling
		if (movingPiece.getKind() == PieceKind.KING
				&& startCol + 2 == endCol) {
			if (isKingInCheck(state, currentTurn, startPos))
				return true;
			State test = state.copy();
			test.setPiece(startRow, 4, null);
			test.setPiece(startRow, 5, movingPiece);
			if (isKingInCheck(test, currentTurn, new Position(startRow, 5)))
				return true;
			test.setPiece(startRow, 5, new Piece(currentTurn, PieceKind.ROOK));
			test.setPiece(startRow, 6, movingPiece);
			test.setPiece(startRow, 7, null);
			return isKingInCheck(test, currentTurn, endPos);
		}
		
		//Queenside castling
		if (movingPiece.getKind() == PieceKind.KING
				&& startCol - 2 == endCol) {
			if (isKingInCheck(state, currentTurn, startPos))
				return true;
			State test = state.copy();
			test.setPiece(startRow, 4, null);
			test.setPiece(startRow, 3, movingPiece);
			if (isKingInCheck(test, currentTurn, new Position(startRow, 3)))
				return true;
			test.setPiece(startRow, 3, new Piece(currentTurn, PieceKind.ROOK));
			test.setPiece(startRow, 2, movingPiece);
			test.setPiece(startRow, 0, null);
			return isKingInCheck(test, currentTurn, endPos);
		}
		
		//Normal movement
		State test = state.copy();
		test.setPiece(startPos, null);
		test.setPiece(endPos, movingPiece);
		return isKingInCheck(test, currentTurn, findPositionOfKing(test, currentTurn));
	}

	private Position findPositionOfKing(State state, Color color) {
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col) {
				if (state.getPiece(row, col) != null
						&& state.getPiece(row, col).getKind() == PieceKind.KING
						&& state.getPiece(row, col).getColor() == color)
					return new Position(row, col);
			}
		}
		return null;
	}

	//Tests whether the king is in check in some given state.
	//This is identical to the function of the same name in my StateChangerImpl.
	//I copied it to avoid writing any public methods that weren't in the StateExplorer interface.
	private boolean isKingInCheck(State state, Color kingColor, Position kingPos) {
		int kingRow = kingPos.getRow(), kingCol = kingPos.getCol();
		if (isPieceEnemyKnight(state, kingColor, kingRow - 2, kingCol - 1)
				|| isPieceEnemyKnight(state, kingColor, kingRow - 2, kingCol + 1)
				|| isPieceEnemyKnight(state, kingColor, kingRow - 1, kingCol - 2)
				|| isPieceEnemyKnight(state, kingColor, kingRow - 1, kingCol + 2)
				|| isPieceEnemyKnight(state, kingColor, kingRow + 1, kingCol - 2)
				|| isPieceEnemyKnight(state, kingColor, kingRow + 1, kingCol + 2)
				|| isPieceEnemyKnight(state, kingColor, kingRow + 2, kingCol - 1)
				|| isPieceEnemyKnight(state, kingColor, kingRow + 2, kingCol + 1))
			return true;
		
		for (int rowDir = -1; rowDir < 2; ++rowDir) {
			if (testForCheckInDirection(state, kingColor, kingPos, rowDir, -1))
				return true;
			if (rowDir != 0 && testForCheckInDirection(state, kingColor, kingPos, rowDir, 0))
				return true;
			if (testForCheckInDirection(state, kingColor, kingPos, rowDir, 1))
				return true;
		}
		return false;
	}

	//Tests if a piece is an enemy knight.
	//This is only here to make isKingInCheck shorter and neater.
	//This is identical to the function of the same name in my StateChangerImpl.
	//I copied it to avoid writing any public methods that weren't in the StateExplorer interface.
	private boolean isPieceEnemyKnight(State state, Color kingColor, int row, int col) {
		if (row < 0 || row >= State.ROWS || col < 0 || col >= State.ROWS)
			return false;
		Piece target = state.getPiece(row, col);
		return (target != null && target.getColor() != kingColor && target.getKind() == PieceKind.KNIGHT);
	}
		
	//Tests if the king is threatened by a piece in some cardinal direction.
	//This is identical to the function of the same name in my StateChangerImpl.
	//I copied it to avoid writing any public methods that weren't in the StateExplorer interface.
	private boolean testForCheckInDirection(State state, Color kingColor, Position kingPos, int dr, int dc) {
		int testRow = kingPos.getRow() + dr;
		int testCol = kingPos.getCol() + dc;
		if (!isPositionOnBoard(testRow, testCol))
			return false;
		Piece testPiece = state.getPiece(testRow, testCol);
		if (testPiece != null && testPiece.getColor() != kingColor) {
			if (testPiece.getKind() == PieceKind.KING)
				return true;
			if (testPiece.getKind() == PieceKind.PAWN
					&& dc != 0
					&& ((kingColor == Color.BLACK && dr == -1) || (kingColor == Color.WHITE && dr == 1)))
				return true;
		}
		while (isPositionOnBoard(testRow, testCol)) {
			testPiece = state.getPiece(testRow, testCol);
			if (testPiece != null) {
				if (testPiece.getColor() == kingColor)
					return false;
				if (testPiece.getKind() == PieceKind.QUEEN)
					return true;
				if ((dr == 0 || dc == 0) && testPiece.getKind() == PieceKind.ROOK)
					return true;
				if (dr != 0 && dc != 0 && testPiece.getKind() == PieceKind.BISHOP)
					return true;
				return false;
			}
			testRow += dr;
			testCol += dc;
		}
		return false;
	}

	private boolean isPositionOnBoard(int row, int col) {
		return (row >= 0 && row < State.ROWS && col >= 0 && col < State.ROWS);
	}
	
	//Tests that a move is on the board, doesn't destroy friendly pieces, and doesn't cause check.
	private boolean isMoveSafe(State state, Position start, Position end) {
		if (!isPositionOnBoard(end.getRow(), end.getCol()))
			return false;
		if (state.getPiece(end) == null || state.getPiece(end).getColor() != state.getTurn())
			return !doesMoveCauseCheck(state, start, end);
		else
			return false;
	}
}
