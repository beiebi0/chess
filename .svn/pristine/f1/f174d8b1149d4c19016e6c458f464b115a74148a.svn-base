package org.wenjiechen.hw2_5;

import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

import com.google.common.collect.Sets;

public class MoveChecker {

	/**
	 * Is move "from" to "to" legal.
	 * If legal return true; else return false 
	 */
	public boolean isMoveLegal(State state, Position from,
									Position to, PieceKind promotionKind) {
		if (from.equals(to)) {
			return false;
		}

		if (state.getPiece(to) != null
				&& state.getPiece(to).getColor() == state.getPiece(from)
						.getColor()) {
			// can't capture self
			return false;
		}
		
		if (state.getGameResult() != null) {
			// Game already ended!
			return false;
		}
		
		if (state.getNumberOfMovesWithoutCaptureNorPawnMoved() >= 100) {
			//break fifty move rule
			return false;
		}

		Position kingPos = getKingPosition(state);		
		switch (state.getPiece(from).getKind()) {
		case KING:
			if (checkKingsRule(state, from, to) == true
					&& isKingUnderAttackAfterMove(state,from,to,kingPos) == false) {
				return true;
			}
			break;

		case ROOK:
			if (checkRooksRule(state, from, to) == true
					&& ExistPieceBetweenFromAndToInCross(state, from, to) == false
					&& isKingUnderAttackAfterMove(state,from,to,kingPos) == false) {
				return true;
			}
			break;

		case BISHOP:
			if (checkBishopsRule(state, from, to) == true
					&& ExistPieceBetweenFromAndToInDiagonal(state, from, to) == false
					&& isKingUnderAttackAfterMove(state,from,to,kingPos) == false) {
				return true;
			}
			break;

		case QUEEN:
			if (checkQueensRule(state, from, to) == true
					&& ExistPieceBetweenFromAndToInCrossOrDiagonal(state, from,to) == false
					&& isKingUnderAttackAfterMove(state,from,to,kingPos) == false) {
				return true;
			}
			break;

		case KNIGHT:
			if (checkKnightsRule(state, from, to) == true
					&& isKingUnderAttackAfterMove(state,from,to,kingPos) == false) {
				return true;
			}
			break;

		case PAWN:
			if (isLegalMoveForPawn(state, from, to,promotionKind) == true
					&& isKingUnderAttackAfterMove(state,from,to,kingPos) == false) {
				return true;
			}
			break;
		}// switch end
		return false;
	}
		
	/**
	 * if moving piece is pawn and can do promotion return true; else return false; 
	 * @param state
	 * @param from
	 * @param to
	 * @return
	 */	
	public boolean mustPromotion(State state, Position from, Position to) {
		if (state.getPiece(from).getKind() == PieceKind.PAWN 
				&& checkOnlyMoveForwardForPawn(from,to,state.getPiece(from).getColor()) == true) {
			switch (state.getPiece(from).getColor()) {
			case WHITE:
				if (from.getRow() == 6) {
					return true;
				}
				break;
			case BLACK:
				if (from.getRow() == 1) {
					return true;
				}
				break;
			}// switch
			return false;
		}
		return false;
	}
	
	/**
	 * save all current side pieces and opponent piece
	 */
	private Set<Position> getPieces(State state, Color color){
		Set<Position> pieces = Sets.newHashSet();
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				if (state.getPiece(row, col) != null
						&& state.getPiece(row, col).getColor() == color) {
					pieces.add(new Position(row, col));
				}
			}
		}// outer for
		return pieces;
	}
	
	/**
	 * check after a piece move from "from" to "to",  is the king under attack.
	 * if king's under attack, return ture, else false.
	 * have copied "state", don't change it.
	 */
	private boolean isKingUnderAttackAfterMove(State state, Position from, Position to, Position kingPos){
		State stateExplorer = state.copy();
		stateExplorer.setPiece(to, stateExplorer.getPiece(from));		
		stateExplorer.setPiece(from, null);

		if(state.getPiece(from).getKind() == PieceKind.KING){
			return isUnderAttack(stateExplorer,to); 
		}
		else if(checkEnpassant(state, from, to) == true){
			stateExplorer.setPiece(stateExplorer.getEnpassantPosition(), null);
			stateExplorer.setEnpassantPosition(null);
			return isUnderAttack(stateExplorer,kingPos);			
		}
		else{
			return isUnderAttack(stateExplorer,kingPos);
		}
	}
		
	/**
	 * if the position is under attack by opponent, return true, else false e.g.
	 * when it's white's turn, check position is under attack by black piece
	 */
	private boolean isUnderAttack(State state, Position position) {
		Set<Position> opponentPieces = getPieces(state,
				(state.getTurn() == Color.WHITE)? Color.BLACK:Color.WHITE);
		
		for (Position opponentPiece : opponentPieces) {
			switch (state.getPiece(opponentPiece).getKind()) {
			case KING:
				if (checkKingsRule(state, opponentPiece, position) == true) {
					return true;
				}
				break;

			case ROOK:
				if (checkRooksRule(state, opponentPiece, position) == true
						&& ExistPieceBetweenFromAndToInCross(state, opponentPiece,
								position) == false) {
					return true;
				}
				break;

			case BISHOP:
				if (checkBishopsRule(state, opponentPiece, position) == true
						&& ExistPieceBetweenFromAndToInDiagonal(state,
								opponentPiece, position) == false) {
					return true;
				}
				break;

			case QUEEN:
				if (checkQueensRule(state, opponentPiece, position) == true
						&& ExistPieceBetweenFromAndToInCrossOrDiagonal(state,
								opponentPiece, position) == false) {
					return true;
				}
				break;

			case KNIGHT:
				if (checkKnightsRule(state, opponentPiece, position) == true) {
					return true;
				}
				break;

			case PAWN:
				if (isUnderAttackByPawn(state, opponentPiece, position) == true) {
					return true;
				}
				break;
			} // End of switch
		}
		return false;
	}
	
	/**
	 * Position posCheck is a Pawn. 
	 * if it can capture Position pos, return true, or false
	 */
	private boolean isUnderAttackByPawn(State state, Position posCheck, Position pos) {
		if (checkOnlyMoveForwardForPawn(posCheck, pos, state.getPiece(posCheck)
				.getColor()) == true) {
			if (Math.abs(pos.getCol() - posCheck.getCol()) == 1
					&& Math.abs(pos.getRow() - posCheck.getRow()) == 1) {
				return true;
			}
			//check enapssant
			else if(state.getEnpassantPosition() != null 
					&& state.getEnpassantPosition() == pos
					&& pos.getRow() == posCheck.getRow()
					&& Math.abs(pos.getCol() - posCheck.getCol()) == 1){
				return true;
			}
		}
		return false;
	}

	private Position getKingPosition(State state) {
		Position kingPos = null;
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				if (state.getPiece(row, col) != null
						&&state.getPiece(row, col).getKind() == PieceKind.KING
						&& state.getPiece(row, col).getColor() == state
								.getTurn()) {
					kingPos = new Position(row, col);
				}
			}
		}//outer for
		return kingPos;
	}
	
	/**check king's castling rule. if pass, return true; else return false
	 * @param state
	 * @param from
	 * @param to
	 * @return
	 */
	private boolean checkCastling(State state, Position from, Position to, Color pieceColor) {
		//White king do castle
		switch (pieceColor){
		case WHITE:
			if(from.equals(new Position(0,4))){
				//do king side
				if(to.equals(new Position(0,6)) && state.isCanCastleKingSide(Color.WHITE) == true){
					//check no piece in path and path is not under attack
					boolean isUnderAttack = isUnderAttack(state,new Position(0,4)) 
												|| isUnderAttack(state,new Position(0,5))
													|| isUnderAttack(state,new Position(0,6));
					boolean existPieceInPath = ExistPieceBetweenFromAndToInCross(state,
														new Position(0,4),new Position(0,7));
					if(isUnderAttack == false && existPieceInPath == false){
						return true;
					}
				} // end of king side
				//do queen side 
				else if(to.equals(new Position(0,2)) && state.isCanCastleQueenSide(Color.WHITE) == true){
					boolean isUnderAttack = isUnderAttack(state,new Position(0,4)) 
												|| isUnderAttack(state,new Position(0,3))
													|| isUnderAttack(state,new Position(0,2));					
					boolean existPieceInPath = ExistPieceBetweenFromAndToInCross(state,
															new Position(0,4), new Position(0,0));
					//move king and rook
					if(isUnderAttack == false && existPieceInPath == false){				
						return true;
					}
				} // end of queen side
			}
			break; // king in form is not at (0,4)
		case BLACK:
			if(from.equals(new Position(7,4))){
				//do king side
				if(to.equals(new Position(7,6)) && state.isCanCastleKingSide(Color.BLACK) == true){
					//check no piece in path and path is not under attack
					boolean isUnderAttack = isUnderAttack(state,new Position(7,4)) 
												|| isUnderAttack(state,new Position(7,5))
													|| isUnderAttack(state,new Position(7,6));					
					boolean existPieceInPath = ExistPieceBetweenFromAndToInCross(state,
														new Position(7,4),new Position(7,7));
					//move king and rook
					if(isUnderAttack == false && existPieceInPath == false){					
						return true;
					}
				} // end of king side
				//do queen side 
				else if(to.equals(new Position(7,2)) && state.isCanCastleQueenSide(Color.BLACK) == true){
					//check no piece in path and path is not under attack
					boolean isUnderAttack = isUnderAttack(state,new Position(7,4)) 
												|| isUnderAttack(state,new Position(7,3))
													|| isUnderAttack(state,new Position(7,2));					
					boolean existPieceInPath = ExistPieceBetweenFromAndToInCross(state,
														new Position(7,4), new Position(7,0));
					//move king and rook
					if(isUnderAttack == false && existPieceInPath == false){				
						return true;
					}
				} // end of queen side			
			}
			break; // king in form is not at (7,4)
		}// end of switch		
		return false;
	}
	
	
	private boolean checkKingsRule(State state, Position from, Position to) {
		if(Math.abs(to.getCol()-from.getCol()) == 2){
			return checkCastling(state,from,to,state.getPiece(from).getColor());
		}
		else if (Math.abs(from.getRow() - to.getRow()) > 1
				|| Math.abs(from.getCol() - to.getCol()) > 1) {
			return false;
		}
		return true;
	}
	
	private boolean checkRooksRule(State state, Position from, Position to) {
		if (from.getRow() != to.getRow() && from.getCol() != to.getCol()) {
			return false;
		}
		return true;
	}

	private boolean ExistPieceBetweenFromAndToInCross(State state,
			Position from, Position to) {
		int fromRow = from.getRow(), fromCol = from.getCol();
		int toRow = to.getRow(), toCol = to.getCol();
		if (fromRow == toRow) { // move horizontally
			int i = fromCol < toCol ? fromCol : toCol;
			int j = fromCol > toCol ? fromCol : toCol;
			for (++i; i < j; ++i) {
				if (state.getPiece(fromRow, i) != null) {
					return true;
				}
			}
		} else { // move vertically
			int i = fromRow < toRow ? fromRow : toRow;
			int j = fromRow > toRow ? fromRow : toRow;
			for (++i; i < j; ++i) {
				if (state.getPiece(i, fromCol) != null) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkBishopsRule(State state, Position from, Position to) {
		if (Math.abs(to.getRow() - from.getRow()) != Math.abs(to.getCol()
				- from.getCol())) {
			return false;
		}
		return true;
	}

	private boolean ExistPieceBetweenFromAndToInDiagonal(State state,
			Position from, Position to) {
		int fromRow = from.getRow(), fromCol = from.getCol();
		int toRow = to.getRow(), toCol = to.getCol();
		int colStart = fromCol < toCol ? fromCol : toCol;
		int rowStart = fromCol < toCol ? fromRow : toRow;
		int colEnd = fromCol > toCol ? fromCol : toCol;
		if ((toRow - fromRow) == (toCol - fromCol)) {
			while (colStart < (colEnd - 1)) {
				if (state.getPiece(++rowStart, ++colStart) != null) {
					return true;
				}
			}
		} else { // slop = -1
			while (colStart < (colEnd - 1)) {
				if (state.getPiece(--rowStart, ++colStart) != null) {
					return true;
				}
			}
		}
		return false;
	}

	// check queen's rule, pass return true, or false
	private boolean checkQueensRule(State state, Position from, Position to) {
		if (!checkRooksRule(state, from, to)
				&& !checkBishopsRule(state, from, to)) {
			return false;
		}
		return true;
	}

	// if leap over other piece, return true, or false
	private boolean ExistPieceBetweenFromAndToInCrossOrDiagonal(State state,
			Position from, Position to) {
		if (to.getRow() == from.getRow() || to.getCol() == from.getCol()) {
			if (ExistPieceBetweenFromAndToInCross(state, from, to) == true)
				return true;
		} else {
			if (ExistPieceBetweenFromAndToInDiagonal(state, from, to) == true)
				return true;
		}
		return false;
	}

	// check knight's rule, pass return true, or false
	private boolean checkKnightsRule(State state, Position from, Position to) {
		if (!(Math.abs(to.getRow() - from.getRow()) == 2
				&& Math.abs(to.getCol() - from.getCol()) == 1 || Math.abs(to
				.getCol() - from.getCol()) == 2
				&& Math.abs(to.getRow() - from.getRow()) == 1)) {
			return false;
		}
		return true;
	}

	/**
	 * check if the move(from,to) comply pawn's rule.
	 * if it does return true, else return false. 
	 */
	private boolean isLegalMoveForPawn(State state, Position from,
											Position to,PieceKind promotionKind){
		
		Color fromColor = state.getPiece(from).getColor();
		// pawn only can move forward
		if(checkOnlyMoveForwardForPawn(from,to,fromColor) == false){
			return false;
		}
		
		if(checkPromotion(state,from,to,promotionKind) == false){
			return false;
		}

		// check move diagonally and enpassant rule
		if (to.getCol() != from.getCol()) {
			if (!(Math.abs(to.getCol() - from.getCol()) == 1 
					&& Math.abs(to.getRow() - from.getRow()) == 1)) {
				return false;
			} else if (state.getPiece(to) == null 
						&& checkEnpassant(state, from, to) == false) {
				return false;
			}
		}
		
		// move forward at the first step
		else if (state.getPiece(from).getColor() == Color.WHITE
				&& from.getRow() == 1
				|| state.getPiece(from).getColor() == Color.BLACK
				&& from.getRow() == 6) {
			if (checkFirstStepForPawn(state, from, to,
					state.getPiece(from).getColor()) == false) {
				return false;
			}
		}
		// move one step forward in the same column
		else {
			if (checkMoveOneStepForwardForPawn(state, from, to,
					state.getPiece(from).getColor()) == false) {
				return false;
			}
		}
		return true;
	}
	
	/** if move backward return false
	*/
	private boolean checkOnlyMoveForwardForPawn(Position from, Position to,
			Color color) {
		switch (color) {
		case WHITE:
			if (to.getRow() - from.getRow() <= 0) { // move backward
				return false;
			}
			break; // need break, because when "if condition" fails, control
					// flow should break out.
		case BLACK:
			if (from.getRow() - to.getRow() <= 0) {
				return false;
			}
			break;
		}
		return true;
	}
	
	/**
	 * check Pawn's enpassant rule.If it holds return true, else return false. 
	 * 1.enpassant position isn't null and two pawns belong to different side.
	 * 2.capturing piece only can move to the passed square of captured piece

	 */
	private boolean checkEnpassant(State state, Position from, Position to) {
		if(state.getPiece(to) != null){
			return false;
		}
		if(state.getEnpassantPosition() == null){
			return false;
		}
		
		Color colorOfMovePiece = state.getPiece(from).getColor();		
		if (state.getEnpassantPosition() != null
				&& colorOfMovePiece != state.getPiece(state.getEnpassantPosition()).getColor()) {
				Position enpassantPos = state.getEnpassantPosition();
				boolean isMoveValid = from.getRow() == enpassantPos.getRow()
										&& to.getCol() == enpassantPos.getCol()
										&& Math.abs(from.getRow() - to.getRow()) == 1
										&& Math.abs(from.getCol() - to.getCol()) == 1;
				if ( isMoveValid == true) {
					return true;
				}
		}
		return false;
	}
	
	
	/**
	 * check when pawn move forward only one step. 
	 * it rule passes return true, fail return false.
	 * can't move to different column, can't move beyond 1 step,
	 * destination can't be occupied 
	 */
	private boolean checkMoveOneStepForwardForPawn(State state, Position from,
			Position to, Color color) {

		if (to.getCol() != from.getCol()) {
			return false;
		} else if (Math.abs(to.getRow() - from.getRow()) > 1) {
			// move beyond one step
			return false;
		} else if (checkOccupiedForPawn(state, from, to, color) == true) {
			return false;
		}
		return true;
	}
	
	/**
	 * check first step is legal or not. if legal return true, else return false
	 * first step can be one step or two step, and the destination isn't occupied.
	 * can't move to different column
	 */
	private boolean checkFirstStepForPawn(State state, Position from,
			Position to, Color color) {
		if (to.getCol() != from.getCol()) {
			return false;
		}
		else if (Math.abs(from.getRow() - to.getRow()) > 2) {
			return false;
		}
		else if (checkOccupiedForPawn(state, from, to, color) == true) {
			return false;
		}
		return true;
	}
	
	/**
	 *  if forward squares are occupied returns true, otherwise false
	 */
	private boolean checkOccupiedForPawn(State state, Position from,
			Position to, Color color) {
		int i = 0, j = 0;
		if (color == Color.WHITE) {
			i = from.getRow() + 1;
			j = to.getRow();
			for (; i <= j; ++i) {
				if (state.getPiece(i, from.getCol()) != null) {
					return true;
				}
			}
		} else {
			i = from.getRow() - 1;
			j = to.getRow();
			for (; i >= j; --i) {
				if (state.getPiece(i, from.getCol()) != null) {
					return true; // occupied
				}
			}
		}
		return false;
	}
		
	/**
	 * check promotion's validation. 
	 * If valid or pomotionkind = null return true, else return false 
	 */
	private boolean checkPromotion(State state, Position from, 
										Position to,PieceKind promotionKind) {
		if(promotionKind == null)
			return true;		
		if (promotionKind == PieceKind.BISHOP
				|| promotionKind == PieceKind.KNIGHT
				|| promotionKind == PieceKind.QUEEN
				|| promotionKind == PieceKind.ROOK) {
			switch (state.getPiece(from).getColor()) {
			case WHITE:
				if (from.getRow() == 6) {
					return true;
				}
				break;
			case BLACK:
				if (from.getRow() == 1) {
					return true;
				}
				break;
			}// switch
		}// if
		return false;
	}	
}
