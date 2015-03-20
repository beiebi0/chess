package org.simongellis.hw2;

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
import org.shared.chess.StateExplorer;
import org.simongellis.hw2_5.StateExplorerImpl;

public class StateChangerImpl implements StateChanger {

	@Override
	public void makeMove(State state, Move move) throws IllegalMove {
		if (state.getGameResult() != null)
			throw new IllegalMove();
		
		if (move.getFrom().getRow() < 0 || move.getFrom().getRow() >= State.ROWS
				|| move.getFrom().getCol() < 0 || move.getFrom().getCol() >= State.COLS)
			throw new IllegalMove();
		if (move.getTo().getRow() < 0 || move.getTo().getRow() >= State.ROWS
				|| move.getTo().getCol() < 0 || move.getTo().getCol() >= State.COLS)
			throw new IllegalMove();
		
		Piece movingPiece = state.getPiece(move.getFrom());
		if (movingPiece == null)
			throw new IllegalMove();
		if (movingPiece.getColor() != state.getTurn())
			throw new IllegalMove();
		
		if (move.getPromoteToPiece() != null) {
			if (move.getTo().getRow() != (state.getTurn().isBlack() ? 0 : State.ROWS - 1))
				throw new IllegalMove();
			if (movingPiece.getKind() != PieceKind.PAWN)
				throw new IllegalMove();
			if (move.getPromoteToPiece() == PieceKind.KING || move.getPromoteToPiece() == PieceKind.PAWN)
				throw new IllegalMove();
		}
		else {
			if (move.getTo().getRow() == (state.getTurn().isBlack() ? 0 : State.ROWS - 1)
					&& movingPiece.getKind() == PieceKind.PAWN)
				throw new IllegalMove();
		}
		

		Piece attackedPiece = state.getPiece(move.getTo());
		if (attackedPiece != null && attackedPiece.getColor() == state.getTurn())
			throw new IllegalMove();

		if (isMoveLegalDisregardingCheck(state, move.getFrom(), move.getTo())
				&& !doesMoveCauseCheck(state, move.getFrom(), move.getTo())) 
			executeMove(state, move);		
		else
			throw new IllegalMove();
	}

	//Alters the state of the board to perform a move.
	//By the time this function is called, the move has been verified to be legal.
	private void executeMove(State state, Move move) {
		Position startPos = move.getFrom(), endPos = move.getTo();
		if (state.getPiece(endPos) == null && state.getPiece(startPos).getKind() != PieceKind.PAWN)
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		else
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		
		Piece movingPiece = state.getPiece(startPos);
		Piece destroyedPiece = state.getPiece(endPos);
		PieceKind movingKind = movingPiece.getKind();
		state.setPiece(startPos, null);
		state.setPiece(endPos, movingPiece);
		
		//En Passant
		if (movingKind == PieceKind.PAWN && state.getEnpassantPosition() != null) {
			int targetRow = (state.getTurn().isBlack() ? 2 : 5);
			if (state.getEnpassantPosition().getCol() == endPos.getCol()
					&& endPos.getRow() == targetRow)
				state.setPiece(state.getEnpassantPosition(), null);
		}
		
		if (movingKind == PieceKind.PAWN && Math.abs(endPos.getRow() - startPos.getRow()) == 2)
			state.setEnpassantPosition(endPos);
		else
			state.setEnpassantPosition(null);
		
		//Promotion
		if (move.getPromoteToPiece() != null)
			state.setPiece(endPos, new Piece(state.getTurn(), move.getPromoteToPiece()));

		//Sets the CanCastle booleans
		if (move.getFrom().getRow() == 0) {
			int col = move.getFrom().getCol();
			if (col == 0 && movingKind == PieceKind.ROOK)
				state.setCanCastleQueenSide(Color.WHITE, false);
			if (col == 7 && movingKind == PieceKind.ROOK)
				state.setCanCastleKingSide(Color.WHITE, false);
		}
		if (move.getFrom().getRow() ==7) {
			int col = move.getFrom().getCol();
			if (col == 0 && movingKind == PieceKind.ROOK)
				state.setCanCastleQueenSide(Color.BLACK, false);
			if (col == 7 && movingKind == PieceKind.ROOK)
				state.setCanCastleKingSide(Color.BLACK, false);
		}
		if (move.getTo().getRow() == 0 && destroyedPiece != null) {
			int col = move.getTo().getCol();
			if (col == 0 && destroyedPiece.getKind() == PieceKind.ROOK)
				state.setCanCastleQueenSide(Color.WHITE, false);
			if (col == 7 && destroyedPiece.getKind() == PieceKind.ROOK)
				state.setCanCastleKingSide(Color.WHITE, false);
		}
		if (move.getTo().getRow() == 7 && destroyedPiece != null) {
			int col = move.getTo().getCol();
			if (col == 0 && destroyedPiece.getKind() == PieceKind.ROOK)
				state.setCanCastleQueenSide(Color.BLACK, false);
			if (col == 7 && destroyedPiece.getKind() == PieceKind.ROOK)
				state.setCanCastleKingSide(Color.BLACK, false);
		}
		if (movingKind == PieceKind.KING) {
			state.setCanCastleKingSide(state.getTurn(), false);
			state.setCanCastleQueenSide(state.getTurn(), false);
			if (move.getFrom().getCol() - 2 == move.getTo().getCol()) {
				state.setPiece(move.getFrom().getRow(), 0, null);
				state.setPiece(move.getFrom().getRow(), 3, new Piece(state.getTurn(), PieceKind.ROOK));
			}
			if (move.getFrom().getCol() + 2 == move.getTo().getCol()) {
				state.setPiece(move.getFrom().getRow(), 7, null);
				state.setPiece(move.getFrom().getRow(), 5, new Piece(state.getTurn(), PieceKind.ROOK));
			}
		}
		state.setTurn(state.getTurn().getOpposite());
		
		//Sets the game result
		StateExplorer explorer = new StateExplorerImpl();
		Position kingPos = findPositionOfKing(state, state.getTurn());
		if (explorer.getPossibleMoves(state).isEmpty()) {
			if (isKingInCheck(state, state.getTurn(), kingPos)) {
				state.setGameResult(new GameResult(state.getTurn().getOpposite(), GameResultReason.CHECKMATE));
			}
			else {
				state.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
			}
		}
		else if (state.getNumberOfMovesWithoutCaptureNorPawnMoved() > 99)
			state.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));
	}
	
	//Tests whether the movement specified in the piece is legal.
	//Check is tested for in doesMoveCauseCheck.
	private boolean isMoveLegalDisregardingCheck(State state, Position mover, Position target) {
		if (mover.equals(target))
			return false;
		if (state.getPiece(target) != null && state.getPiece(mover).getColor() == state.getPiece(target).getColor())
			return false;
		int startRow = mover.getRow(), startCol = mover.getCol();
		int endRow = target.getRow(), endCol = target.getCol();		
		switch (state.getPiece(mover).getKind()) {
		case BISHOP:
			if (Math.abs(endRow - startRow) != Math.abs(endCol - startCol))
				return false;
			int testingRow = (startRow < endRow ? startRow + 1 : startRow - 1);
			int testingCol = (startCol < endCol ? startCol + 1 : startCol - 1);
			while (testingRow != endRow) {
				if (state.getPiece(testingRow, testingCol) != null)
					return false;
				if (testingRow < endRow)
					testingRow++;
				else
					testingRow--;
				if (testingCol < endCol)
					testingCol++;
				else
					testingCol--;
			}
			return true;
		case KING:
			if (state.isCanCastleKingSide(state.getTurn())
					&& endRow == startRow
					&& endCol == 6
					&& state.getPiece(startRow, 5) == null
					&& state.getPiece(startRow, 6) == null) {
				return true;
			}
			if (state.isCanCastleQueenSide(state.getTurn())
					&& startRow == endRow
					&& endCol == 2
					&& state.getPiece(startRow, 3) == null
					&& state.getPiece(startRow, 2) == null
					&& state.getPiece(startRow, 1) == null) {
				return true;
			}
			return (Math.abs(endRow - startRow) <= 1 && Math.abs(endCol - startCol) <= 1);
		case KNIGHT:
			return ((Math.abs(endRow - startRow) == 2 && Math.abs(endCol - startCol) == 1)
					|| Math.abs(endRow - startRow) == 1 && Math.abs(endCol - startCol) == 2);
		case PAWN:
			int wayForward = state.getTurn().isWhite() ? 1 : -1;
			int initialRow = state.getTurn().isWhite() ? 1 : 6;
			if (startCol == endCol) {
				if (state.getPiece(target) != null)
					return false;
				if (startRow + wayForward == endRow)
					return true;
				return (startRow == initialRow
						&& startRow + (wayForward * 2) == endRow
						&& state.getPiece(startRow + wayForward, startCol) == null);
			}
			if (Math.abs(endCol - startCol) == 1 && startRow + wayForward == endRow) {
				if (state.getPiece(target) != null)
					return true;
				if (state.getEnpassantPosition() != null
						&& endCol == state.getEnpassantPosition().getCol()
						&& endRow - wayForward == state.getEnpassantPosition().getRow())
					return true;
			}
			return false;
		case QUEEN:
			if (Math.abs(endRow - startRow) == Math.abs(endCol - startCol)) {
				int testRow = (startRow < endRow ? startRow + 1 : startRow - 1);
				int testCol = (startCol < endCol ? startCol + 1 : startCol - 1);
				while (true) {
					if (testCol == endCol)
						return true;
					if (state.getPiece(testRow, testCol) != null)
						break;
					if (testRow < endRow)
						testRow++;
					else
						testRow--;
					if (testCol < endCol)
						testCol++;
					else
						testCol--;
				}			
			}
		case ROOK:
			if (startRow == endRow) {
				int testCol = (startCol < endCol ? startCol + 1 : startCol - 1);
				while (testCol != endCol) {
					if (state.getPiece(startRow, testCol) != null)
						return false;
					if (testCol < endCol)
						testCol++;
					else
						testCol--;
				}
				return true;
			}
			if (startCol == endCol) {
				int testRow = (startRow < endRow ? startRow + 1 : startRow - 1);
				while (testRow != endRow) {
					if (state.getPiece(testRow, startCol) != null)
						return false;
					if (testRow < endRow)
						testRow++;
					else
						testRow--;
				}
				return true;
			}
			return false;
		default:
			break;
		}
		return true;
	}

	//Makes a copy of the board, executes a move on that copy, and calls isKingInCheck on the copy.
	//The move has already been confirmed to be otherwise legal.
	private boolean doesMoveCauseCheck(State state, Position startPos, Position endPos) {
		int startRow = startPos.getRow(), startCol = startPos.getCol();
		int endRow = endPos.getRow(), endCol = endPos.getCol();		
		Piece movingPiece = state.getPiece(startPos);
		Color currentTurn = state.getTurn();
		
		//Kingside castling
		if (movingPiece.getKind() == PieceKind.KING
				&& startCol + 2 == endCol) {
			if (isKingInCheck(state, currentTurn, startPos))
				return true;
			State test = state.copy();
			test.setEnpassantPosition(null);
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
			test.setEnpassantPosition(null);
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
		int wayForward = (state.getTurn().isWhite() ? 1 : -1);
		State test = state.copy();
		test.setPiece(startPos, null);
		test.setPiece(endPos, movingPiece);
		if (test.getEnpassantPosition() != null
				&& test.getEnpassantPosition().getRow() + wayForward== endRow
				&& test.getEnpassantPosition().getCol() == endCol)
			test.setPiece(test.getEnpassantPosition(), null);
		if (movingPiece.getKind() == PieceKind.PAWN && Math.abs(endRow - startRow) == 2)
			test.setEnpassantPosition(endPos);
		else
			test.setEnpassantPosition(null);
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
	private boolean isPieceEnemyKnight(State state, Color kingColor, int row, int col) {
		if (row < 0 || row >= State.ROWS || col < 0 || col >= State.ROWS)
			return false;
		Piece target = state.getPiece(row, col);
		return (target != null && target.getColor() != kingColor && target.getKind() == PieceKind.KNIGHT);
	}
		
	//Tests if the king is threatened by a piece in some cardinal direction.
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

}


