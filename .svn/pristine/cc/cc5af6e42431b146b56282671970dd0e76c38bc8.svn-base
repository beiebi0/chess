package org.wenjiechen.hw2;

//import static org.junit.Assert.assertEquals;

import java.lang.Math;
import org.shared.chess.Color;
import org.shared.chess.GameResultReason;
import org.shared.chess.GameResult;
import org.shared.chess.PieceKind;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;

public class StateChangerImpl implements StateChanger {

	//can optimize 1: find king position and pass it
	
	
	private boolean promotionValidation = false; 

	public void makeMove(State state, Move move) throws IllegalMove {
		if (state.getGameResult() != null) {
			// Game already ended!
			throw new IllegalMove();
		}
		
		if (state.getNumberOfMovesWithoutCaptureNorPawnMoved() > 100) {
			//break fifty move rule
			throw new IllegalMove();
		}
		
		Position from = move.getFrom();
		Piece pieceOfFrom = state.getPiece(from);
		if (pieceOfFrom == null) {
			// Nothing to move!
			throw new IllegalMove();
		}
		
		if (state.getPiece(from).getKind() != PieceKind.PAWN 
				&& move.getPromoteToPiece() != null){
			//can't do promotion for other piece
			throw new IllegalMove();
		}

		Color colorOfFrom = pieceOfFrom.getColor();
		if (colorOfFrom != state.getTurn()) {
			// Wrong player moves!
			throw new IllegalMove();
		}

		PieceKind PieceKindOfFrom = pieceOfFrom.getKind();
		Position to = move.getTo();
		int fromRow = from.getRow(), fromCol = from.getCol();
		int toRow = to.getRow(), toCol = to.getCol();
		if (toRow < 0 || toRow > 7 || toCol < 0 || toCol > 7 || fromRow < 0
				|| fromRow > 7 || fromCol < 0 || fromCol > 7) {
			// move out of the board
			throw new IllegalMove();
		}

		if (from.equals(to)) {
			// don't move actually
			throw new IllegalMove();
		}

		Piece pieceOfTo = state.getPiece(to);
		if (pieceOfTo != null && pieceOfTo.getColor() == colorOfFrom) {
			// capture yourself
			throw new IllegalMove();
		}
		// check move rule for different kinds of piece
		// change the state;
		// if it's illegal throw IllegalMove exception
		switch (PieceKindOfFrom) {
		case KING:
			// check king's normal move. if fails, check king castle rule.
			// cann't move king to a position which is under attack
			// if fail again, throw exception
			//after move, king can't be under attack
			if (checkKingsRule(state, from, to) == true 
					&& isKingUnderAttackAfterKingMove(state,from,to) == false){
				//can't castle
				state.setCanCastleKingSide(colorOfFrom, false);
				state.setCanCastleQueenSide(colorOfFrom, false);
				movePieceAndSetStepCount(state,from,to, null);
			}
			else if(checkAndDoCastling(state, from, to, colorOfFrom) == true) {
//				System.out.println("King does castling");
			}
			else {
				throw new IllegalMove();
			}
			break;

		case ROOK:
			// check rook's rule, pass return true, or false
			// don't check leap in checkRooksRule for avoiding coupling
			// can't leap over other piece, check is there piece between "from" to "to"
			//after move, king can't be under attack
			if (checkRooksRule(state, from, to) == false 
					|| ExistPieceBetweenFromAndToInCross(state, from, to) == true
					|| isKingUnderAttackAfterMove(state,from,to) == true) {
				throw new IllegalMove();
			}

			//move rook can't castling
			if(from.equals(new Position(0,0)) == true){
				state.setCanCastleQueenSide(Color.WHITE, false);
			}
			else if(from.equals(new Position(0,7)) == true){
				state.setCanCastleKingSide(Color.WHITE, false);
			}
			else if(from.equals(new Position(7,0)) == true){
				state.setCanCastleQueenSide(Color.BLACK, false);
			}
			else if(from.equals(new Position(7,7)) == true){
				state.setCanCastleKingSide(Color.BLACK, false);
			}
			movePieceAndSetStepCount(state,from,to, null);
//			System.out.println("Rook's rule succeeds");
			break;

		case BISHOP:
			// check bishop's rule, pass return true, or false
			// leap over other piece,check is there piece between "from" to "to"
			//after move, king can't be under attack
			if (checkBishopsRule(state, from, to) == false 
					||ExistPieceBetweenFromAndToInDiagonal(state, from, to) == true
					||isKingUnderAttackAfterMove(state,from,to) == true) {
				throw new IllegalMove();
			}
			movePieceAndSetStepCount(state,from,to, null);
			//System.out.println("Bishop's rule succeeds");
			break;

		case QUEEN:
			// check queen's rule, pass return true, or false
			// leap over other pieces
			//after move, king can't be under attack
			if (checkQueensRule(state, from, to) == false
					||ExistPieceBetweenFromAndToInCrossOrDiagonal(state, from, to) == true
					||isKingUnderAttackAfterMove(state,from,to) == true) {
				throw new IllegalMove();
			}
			movePieceAndSetStepCount(state,from,to, null);			
//			System.out.println("Queen's rule succeeds");
			break;

		case KNIGHT:
			// don't need to check "cann't leap other pieces"
			//after move, king can't be under attack
			if (checkKnightsRule(state, from, to) == false
					||isKingUnderAttackAfterMove(state,from,to) == true) {
				throw new IllegalMove();
			}
			movePieceAndSetStepCount(state,from,to, null);			
//			System.out.println("Knight's rule succeeds");
			break;

		case PAWN:
			if (checkPawnsRule(state, from, to, move.getPromoteToPiece()) == true
					&& isKingUnderAttackAfterMove(state,from,to) == false) {
				movePieceAndSetStepCount(state,from,to, move.getPromoteToPiece());			
			}
			else if(checkAndDoEnpassant(state,from,to) == true){
//				System.out.println("do enpassant");
			}
			else{
				throw new IllegalMove();
			}
//			System.out.println("Pawn's rule succeeds");
			break;
		} // End of switch

		// change turn
		if (colorOfFrom == Color.WHITE) {
			state.setTurn(Color.BLACK);
		} else {
			state.setTurn(Color.WHITE);
		}
		// change enpassant state
		if(state.getEnpassantPosition() != null 
				&& colorOfFrom != state.getPiece(state.getEnpassantPosition()).getColor()){
			state.setEnpassantPosition(null);
		}
		else if(state.getEnpassantPosition() != null
				&& PieceKindOfFrom != PieceKind.PAWN){
			state.setEnpassantPosition(null);			
		}
		//check the conditions of end game, and predict if the opponent is checkmate
		checkGameEnd(state);
	}
	
	/**
	 * after complete current move, check can one of the three conditions of end game hold.
	 * and predict if opponent's king is checkmate. 
	 */
	private void checkGameEnd(State state){
		if(state.getNumberOfMovesWithoutCaptureNorPawnMoved() == 100){
			state.setGameResult(new GameResult(null,GameResultReason.FIFTY_MOVE_RULE));
		}
		if(isStalemate(state) == true){
			state.setGameResult(new GameResult(null,GameResultReason.STALEMATE));
		}
		else if (isCheckMate(state) == true)
		{
			Color winner;
			if(state.getTurn() == Color.WHITE){
				winner = Color.BLACK;
			}
			else{
				winner = Color.WHITE;
			}
			state.setGameResult(new GameResult(winner,GameResultReason.CHECKMATE));
		}		
	}
		
	/**
	 * whatever move the try piece anywhere, will result in king is under attack.
	 * if that's true, return true, else return false
	 * @param state
	 * @param tryPiecePos
	 * @param checkedSide
	 * @return
	 */
	private boolean willAnyMoveResultInKingUnderAttack(State state,Position tryPiecePos,
										Position kingPos, Color checkedSide){
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				State stateExplorer = state.copy();
				Position tryMoveTo = new Position(row, col);
				if (isMoveLegal(stateExplorer, tryPiecePos, tryMoveTo) == true
						&& isKingUnderAttackAfterMove(stateExplorer,
								tryPiecePos, tryMoveTo) == false) {
					// if try piece can move to tryMoveTo position
					// and after move king is not under attack
					return false;
				}
			}
		}// outer for
		//any move of tryPiecePos will result in king is under attack
		return true;
	}
	
	/**
	 * Stalemate: king isn't under attack, but move any piece will result in king is under attack
	 * if it's stalemate, return true, 	else return false
	 * @param state
	 * @return
	 */
	
	private boolean isStalemate(State state){
		Color checkedSide = state.getTurn();
		Position kingPos = null;
		//find king's position
		for(int row =0; row < 8; ++row){
			for(int col=0;col<8;++col){
				if(state.getPiece(row, col) != null 
						&& state.getPiece(row,col).getColor() == checkedSide
						&& state.getPiece(row,col).getKind() == PieceKind.KING){
					kingPos = new Position(row,col);
				}
			}
		}// end of outer for

		if (kingPos != null && isUnderAttack(state, kingPos) == false) {
			//if checked side's king isn't under attack
			for (int row = 0; row < 8; ++row) {
				for (int col = 0; col < 8; ++col) {
					if (state.getPiece(row, col) != null
							&& state.getPiece(row, col).getColor() == checkedSide) {
						// find a piece belongs to checkedSide
						Position tryPiece = new Position(row, col);
						// check if whatever move a piece will result in its king is under attack
						if (willAnyMoveResultInKingUnderAttack(state, tryPiece,kingPos,checkedSide) == false) {
							// checked side is not in stalemate
							return false;
						}// inner if
					}// outer if
				}// inner for
			}// outer for
			// can't find a piece move will result in king isn't under attack
			// so it's stalemate
			return true;	
		}
		else{
			//king is under attack,so it's not stalemate
			return false;			
		}	
	}
	
	/**
	 * check if move the tryPiece can help its king escape checkmate.
	 * if it can do, return true; else return false
	 * @param state
	 * @param tryPiece
	 * @return
	 */
	
	private boolean canEscapeCheckMate(State state, Position tryPiecePosition, Color checkSide){		
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				State stateExplorer = state.copy();
				Position tryMoveTo = new Position(row, col);
				if (isMoveLegal(stateExplorer,tryPiecePosition,tryMoveTo) == true
					&& isKingUnderAttackAfterMove(stateExplorer, 
							tryPiecePosition,tryMoveTo) == false) {
					// if try piece can move to tryMoveTo position 
					// and after move king is not under attack
					return true;
				}
/*				else if(stateExplorer.getPiece(tryPiecePosition).getKind() == PieceKind.PAWN
						&& stateExplorer.getEnpassantPosition() != null
						&& checkAndDoEnpassant(stateExplorer,tryPiecePosition,tryMoveTo) == true){
					//the above condition doesn't cover pawn do enpassant
					return true;
				}  */
			}
		}// outer for
		return false;
	}
	
	/**
	 * check if move(from, to) is legal move for the piece at from
	 * if it's legal move return true, else return false
	 * @param state
	 * @param from
	 * @param to
	 * @return
	 */
	
	private boolean isMoveLegal(State state, Position from, Position to){

		if (from.equals(to)) {
			// don't move actually
			return false;
		}
		
		if(state.getPiece(to) != null
				&&state.getPiece(to).getColor() == state.getPiece(from).getColor()){
			//can't capture self
			return false;
		}
		
		switch(state.getPiece(from).getKind()){

		case KING:
			// pass king's rule
			if (checkKingsRule(state, from, to) == true) {
				return true;
			}
			break;

		case ROOK:
			// pass rook's rule and no piece between from
			// and to
			if (checkRooksRule(state, from, to) == true
					&& ExistPieceBetweenFromAndToInCross(state,from, to) == false) {
				return true;
			}
			break;

		case BISHOP:
			// pass bishop's rule and no piece between from
			// and
			// to
			if (checkBishopsRule(state, from, to) == true
					&& ExistPieceBetweenFromAndToInDiagonal(state, from, to) == false) {
				return true;
			}
			break;

		case QUEEN:
			// pass queen's rule and no piece between from
			// and
			// to
			if (checkQueensRule(state, from, to) == true
					&& ExistPieceBetweenFromAndToInCrossOrDiagonal(state, from, to) == false) {
				return true;
			}
			break;

		case KNIGHT:
			// pass knight's rule
			if (checkKnightsRule(state, from, to) == true) {
				return true;
			}
			break;

		case PAWN:
			// pass pawn's rule
			if (isLegalMoveForPawn(state, from, to) == true) {
				return true;
			}
			break;
		}// switch end
		return false;
	}
	
	/**
	 * check if the move(from,to) comply pawn's rule(except enpassant).
	 * if it does return true, else return false. 
	 * @param state
	 * @param from
	 * @param to
	 * @return
	 */
	private boolean isLegalMoveForPawn(State state, Position from, Position to){		// can only move forward
		
		if (checkOnlyMoveForwardForPawn(from, to, state.getPiece(from).getColor()) == false) {
			return false;
		}
		// check move diagonally
		if (to.getCol() != from.getCol()) {
			if (checkDiagonalMoveForPawn(state, from, to,
					state.getPiece(from).getColor(), null) == false) {
				return false;
			}
		}// move forward in the first step and set enpassant validation
		else if (state.getPiece(from).getColor() == Color.WHITE
				&& from.getRow() == 1
				|| state.getPiece(from).getColor() == Color.BLACK
				&& from.getRow() == 6) {
			if (checkFirstStepForPawn(state, from, to,
					state.getPiece(from).getColor(),null) == false) {
				return false;
			}
		}
		// move one step forward in the same column
		else {
			if (checkMoveOneStepForwardForPawn(state, from, to,
					state.getPiece(from).getColor(), null) == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * the mothed is in the checkGameEnd mothed, and the turn has been changed in Last move.
	 * So it predicts if in the turn king is checkmate. 
	 * If checkmate return true; else return false
	 * @param state
	 * @return
	 */	
	private boolean isCheckMate(State state) {
		//predict in this turn
		Color checkedSide = state.getTurn();
		// find king of checked side
		Position kingPos = null;
		for(int row =0; row < 8; ++row){
			for(int col=0;col<8;++col){
				if(state.getPiece(row, col) != null 
						&& state.getPiece(row,col).getColor() == checkedSide
						&& state.getPiece(row,col).getKind() == PieceKind.KING){
					kingPos = new Position(row,col);
				}
			}
		}// end of outer for
		if (kingPos != null && isUnderAttack(state, kingPos) == true) {
			//if checked side's king is under attack
			for (int row = 0; row < 8; ++row) {
				for (int col = 0; col < 8; ++col) {
					if (state.getPiece(row, col) != null
							&& state.getPiece(row, col).getColor() == checkedSide) {
						// find a piece belongs to checkedSide
//						State stateExplorer = state.copy();
						Position tryMovePiece = new Position(row, col);
						// check if the try move piece can help its king escape under attack
						if (canEscapeCheckMate(state, tryMovePiece,checkedSide) == true) {
							// checked side king is not checkmate
							return false;
						}// inner if
					}// outer if
				}// inner for
			}// outer for
				// king of current turn is checkmate
			return true;
		}
		else{// king is not under attack
			return false;			
		}
	}
		
	/**
	 * when normal pawn's rule fails, check does the enpassant condition hold.
	 * If hold, chechk after move, is king under attack.
	 * if not, do enpassant return true, else return false. 
	 */
	private boolean checkAndDoEnpassant(State state, Position from, Position to) {
		Color colorOfMovePiece = state.getPiece(from).getColor();
		// check enpassant position isn't null and two pawns belong to different
		// side.
		if (state.getEnpassantPosition() != null
				&& colorOfMovePiece != state.getPiece(
						state.getEnpassantPosition()).getColor()) {
			// after enpassant, is king under attack
			Position kingPos = null;
			Color kingColor = state.getTurn();
			// find the king
			for (int row = 0; row < 8; ++row) {
				for (int col = 0; col < 8; ++col) {
					if (state.getPiece(row, col) != null
							&& state.getPiece(row, col).getColor() == kingColor
							&& state.getPiece(row, col).getKind() == PieceKind.KING) {
						kingPos = new Position(row, col);
					}
				}
			}// end of outer for
			Piece pieceOfFrom = new Piece(state.getPiece(from).getColor(),
					state.getPiece(from).getKind());
			boolean isUnderAttack = false;
			State stateExplorer = state.copy();
			stateExplorer.setPiece(from, null);
			stateExplorer.setPiece(to, pieceOfFrom);
			//do enpassant, captured piece
			stateExplorer.setPiece(state.getEnpassantPosition(), null);
			isUnderAttack = isUnderAttack(stateExplorer, kingPos);
			if (isUnderAttack == false) {
				// check move of enpassant is valid
				Position epsPos = state.getEnpassantPosition();
				// capturing piece only can move to the passed square of
				// captured piece
				boolean isMoveValid = from.getRow() == epsPos.getRow()
						&& to.getCol() == epsPos.getCol()
						&& Math.abs(from.getRow() - to.getRow()) == 1
						&& Math.abs(from.getCol() - to.getCol()) == 1;
				if (checkOnlyMoveForwardForPawn(from, to, colorOfMovePiece) == true
						&& isMoveValid == true) {
					// do enpassant
					state.setPiece(state.getEnpassantPosition(), null);
					state.setEnpassantPosition(null);
					movePieceAndSetStepCount(state, from, to, null);
					return true;
				}
				else { //after enpassant, king is under attack
					return false;
				}
			}
		}
		return false;
	}
	
	/**check king's castling rule. if pass, do castling and return true; else return false
	 * @param state
	 * @param from
	 * @param to
	 * @return
	 */
	private boolean checkAndDoCastling(State state, Position from, Position to, Color pieceColor) {
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
					//move king and rook
					if(isUnderAttack == false && existPieceInPath == false){
//						System.out.println("do castling: "+ from + "->" + to);
						state.setPiece(0,4,null);
						state.setPiece(0,6,new Piece(Color.WHITE,PieceKind.KING));
						state.setPiece(0,7,null);
						state.setPiece(0,5,new Piece(Color.WHITE,PieceKind.ROOK));
						state.setCanCastleKingSide(Color.WHITE, false);
						state.setCanCastleQueenSide(Color.WHITE, false);
						state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.
								getNumberOfMovesWithoutCaptureNorPawnMoved()+1);
						return true;
					}
				} // end of king side
				//do queen side 
				else if(to.equals(new Position(0,2)) && state.isCanCastleQueenSide(Color.WHITE) == true){
					//check no piece in path and path is not under attack
					boolean isUnderAttack = isUnderAttack(state,new Position(0,4)) 
												|| isUnderAttack(state,new Position(0,3))
													|| isUnderAttack(state,new Position(0,2));
					
					boolean existPieceInPath = ExistPieceBetweenFromAndToInCross(state,
														new Position(0,4), new Position(0,0));
					//move king and rook
					if(isUnderAttack == false && existPieceInPath == false){
//						System.out.println("do castling: "+ from + "->" + to);
						state.setPiece(0,4,null);
						state.setPiece(0,2,new Piece(Color.WHITE,PieceKind.KING));
						state.setPiece(0,0,null);
						state.setPiece(0,3,new Piece(Color.WHITE,PieceKind.ROOK));
						state.setCanCastleKingSide(Color.WHITE, false);
						state.setCanCastleQueenSide(Color.WHITE, false);
						state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.
								getNumberOfMovesWithoutCaptureNorPawnMoved()+1);					
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
//						System.out.println("do castling: "+ from + "->" + to);
						state.setPiece(7,4,null);
						state.setPiece(7,6,new Piece(Color.BLACK,PieceKind.KING));
						state.setPiece(7,7,null);
						state.setPiece(7,5,new Piece(Color.BLACK,PieceKind.ROOK));
						state.setCanCastleKingSide(Color.BLACK, false);
						state.setCanCastleQueenSide(Color.BLACK, false);
						state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.
								getNumberOfMovesWithoutCaptureNorPawnMoved()+1);					
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
//						System.out.println("do castling: "+ from + "->" + to);
						state.setPiece(7,4,null);
						state.setPiece(7,2,new Piece(Color.BLACK,PieceKind.KING));
						state.setPiece(7,0,null);
						state.setPiece(7,3,new Piece(Color.BLACK,PieceKind.ROOK));
						state.setCanCastleKingSide(Color.BLACK, false);
						state.setCanCastleQueenSide(Color.BLACK, false);
						state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.
								getNumberOfMovesWithoutCaptureNorPawnMoved()+1);					
						return true;
					}
				} // end of queen side			
			}
			break; // king in form is not at (7,4)
		}// end of switch		
		return false;
	}

	/**
	 * check after king move from "from" to "to", it's under attack.
	 * if it's under attack, return ture, else false 
	 */
	private boolean isKingUnderAttackAfterKingMove(State state, Position from, Position to){
		State stateExplorer = state.copy();
		stateExplorer.setPiece(from, null);
		boolean isUnderAttack = isUnderAttack(stateExplorer,to);
		return isUnderAttack;		
	}
	
	/**
	 * check after a piece move from "from" to "to",  is the king under attack.
	 * if king's under attack, return ture, else false 
	 */
	private boolean isKingUnderAttackAfterMove(State state, Position from, Position to){
		Position kingPos = null;
		Color  kingColor = state.getTurn();
		//find the king
		for(int row =0; row < 8; ++row){
			for(int col=0;col<8;++col){
				if(state.getPiece(row, col) != null 
						&& state.getPiece(row,col).getColor() == kingColor
						&& state.getPiece(row,col).getKind() == PieceKind.KING){
					kingPos = new Position(row,col);
				}
			}
		}// end of outer for
		Piece pieceOfFrom = new Piece(state.getPiece(from).getColor(),
										state.getPiece(from).getKind());
		boolean isUnderAttack = false;
		if(state.getPiece(from).getKind() == PieceKind.KING){
			isUnderAttack = isKingUnderAttackAfterKingMove(state,from,to);
		}
		else{
			State stateExplorer = state.copy();
			stateExplorer.setPiece(from, null);
			stateExplorer.setPiece(to, pieceOfFrom);
			isUnderAttack = isUnderAttack(stateExplorer,kingPos);
		}
		return isUnderAttack;		
	}
	
	/**
	 * Position posCheck is a Pawn. 
	 * if it can capture Position pos, return true, or false
	 */
	private boolean isUnderAttackByPawn(State state, Position posCheck, Position pos){
		if(checkOnlyMoveForwardForPawn(posCheck,pos, state.getPiece(posCheck).getColor()) == true){
			if (Math.abs(pos.getCol() - posCheck.getCol()) == 1 && Math.abs(pos
					.getRow() - posCheck.getRow()) == 1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 *if the position is under attack by opponent, return true, else false
	 *e.g. when it's white's turn, check position is under attack by black piece
	 */
	private boolean isUnderAttack(State state, Position position) {
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				Position posCheck = new Position(row, col);
				// don't check itself
				if (position.equals(posCheck) == false) {
					//posCheck isn't null and is opponent piece 
					if ( (state.getPiece(posCheck) != null
							&& state.getPiece(posCheck).getColor() != state.getTurn())) {
						PieceKind pieceKind = state.getPiece(posCheck).getKind();
						// can posCheck attack position
						switch (pieceKind) {

						case KING:
							// pass king's rule
							if (checkKingsRule(state, posCheck, position) == true) {
//								System.out.println("isUnderAttack(): attacked by King"+ posCheck);
								return true;
							}
							break;

						case ROOK:
							// pass rook's rule and no piece between posCheck
							// and position
							if (checkRooksRule(state, posCheck, position) == true
									&& ExistPieceBetweenFromAndToInCross(state,posCheck, position) == false) {
	//							System.out.println("isUnderAttack(): attacked by Rook at"+ posCheck);
								return true;
							}
							break;

						case BISHOP:
							// pass bishop's rule and no piece between posCheck
							// and
							// position
							if (checkBishopsRule(state, posCheck, position) == true
									&& ExistPieceBetweenFromAndToInDiagonal(state, posCheck, position) == false) {
		//						System.out.println("isUnderAttack(): attacked by Bishop"+ posCheck);
								return true;
							}
							break;

						case QUEEN:
							// pass queen's rule and no piece between posCheck
							// and
							// position
							if (checkQueensRule(state, posCheck, position) == true
									&& ExistPieceBetweenFromAndToInCrossOrDiagonal(state, posCheck, position) == false) {
	//					System.out.println("isUnderAttack(): attacked by Queen"+ posCheck);
								return true;
							}
							break;

						case KNIGHT:
							// pass knight's rule
							if (checkKnightsRule(state, posCheck, position) == true) {
			//					System.out.println("isUnderAttack(): attacked by Knight"+ posCheck);
								return true;
							}
							break;

						case PAWN:
							// pass pawn's rule
							if (isUnderAttackByPawn(state, posCheck, position) == true) {
				//				System.out.println("isUnderAttack(): attacked by pawn"+ posCheck);
								return true;
							}
							break;
						} // End of switch
					} // end of inner if
				}// end of outer if
			}// end of inner for
		}// end of outer for
//		System.out.println(position + "is not under attack");
		return false;
	}

	/** 1.move piece "from" to "to". if there is opponent piece at 'to', capture it.
	 *  2. set setNumberOfMovesWithoutCaptureNorPawnMoved
	*/
	private void movePieceAndSetStepCount(State state, Position from, Position to, PieceKind promotionKind){
		//capture piece in 'to'
		if (state.getPiece(to) != null) {
//			System.out.println(state.getPiece(from) + " Capture Piece " + state.getPiece(to));
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			//if rooks didn't move and is captured, update canCastle
			if (state.getPiece(to).getKind() == PieceKind.ROOK) {
				if (to.equals(new Position(0, 0)) == true) {
					state.setCanCastleQueenSide(Color.WHITE, false);
				}
				if (to.equals(new Position(0, 7)) == true) {
					state.setCanCastleKingSide(Color.WHITE, false);
				}
				if (to.equals(new Position(7, 0)) == true) {
					state.setCanCastleQueenSide(Color.BLACK, false);
				}
				if (to.equals(new Position(7, 7)) == true) {
					state.setCanCastleKingSide(Color.BLACK, false);
				}
			}	
		}
		// move pawn
		else if (state.getPiece(from).getKind() == PieceKind.PAWN) { 
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		}
		// set Number Of Moves Without Capture
		else{
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		}
		//do promotion for pawn
		if (promotionValidation == true && promotionKind != null) {
			Piece promotionPiece = new Piece(state.getPiece(from).getColor(),
													promotionKind);
			state.setPiece(to, promotionPiece);
		} else {
			state.setPiece(to, state.getPiece(from));
		}
		state.setPiece(from, null);
	}

	// check King's rule,pass return true, otherwise false
	// don't forgive check the castling's rule
	private boolean checkKingsRule(State state, Position from, Position to) {
		if (Math.abs(from.getRow() - to.getRow()) > 1
				|| Math.abs(from.getCol() - to.getCol()) > 1) {
	//		System.out.println("checkKingsRule(): King's rule false");
			return false;
		}
		return true;
	}

	// check rook's rule, pass return true, or throw IllegalMove
	// don't check leap in checkRooksRule for avoiding coupling
	private boolean checkRooksRule(State state, Position from, Position to) {
		// don't move in cross
		if (from.getRow() != to.getRow() && from.getCol() != to.getCol()) {
//			System.out.println("checkRooksRule(): Rook's rule false");
			return false;
		}
		return true;
	}

	/**
	 * in horizontal or vertical, if there is piece between "from" to "to"
	 * return true, or false
	 */
	private boolean ExistPieceBetweenFromAndToInCross(State state,
			Position from, Position to) {
		int fromRow = from.getRow(), fromCol = from.getCol();
		int toRow = to.getRow(), toCol = to.getCol();
		if (fromRow == toRow) { // move horizontally
			int i = fromCol < toCol ? fromCol : toCol;
			int j = fromCol > toCol ? fromCol : toCol;
			// check (fromrow,i+1) to (fromrow, j-1)
			for (++i; i < j; ++i) {
				if (state.getPiece(fromRow, i) != null) {
					// other piece in it's path
//					System.out
//							.println("ExistPieceBetweenFromAndToInCross(): exist other piece between from to to, return true");
					// System.out.println(fromRow + i);
					return true;
				}
			}
		} else { // move vertically
			int i = fromRow < toRow ? fromRow : toRow;
			int j = fromRow > toRow ? fromRow : toRow;
			for (++i; i < j; ++i) {
				if (state.getPiece(i, fromCol) != null) {
//					System.out
//							.println("ExistPieceBetweenFromAndToInCross(): exist other piece between from to to, return true");
					return true;
				}
			}
		}
		return false;
	}

	// move diagonally, that is |slop| = 1. pass rule return true, or false
	private boolean checkBishopsRule(State state, Position from, Position to) {
		// |slope| != 1
		if (Math.abs(to.getRow() - from.getRow()) != Math.abs(to.getCol()
				- from.getCol())) {
//			System.out
//					.println("checkBishopsRule(): Bishop's rule false, slope != 1");
			return false;
		}
		return true;
	}

	// in diagonal, if there is piece between "from" to "to" return true, or
	// false
	private boolean ExistPieceBetweenFromAndToInDiagonal(State state,
			Position from, Position to) {
		int fromRow = from.getRow(), fromCol = from.getCol();
		int toRow = to.getRow(), toCol = to.getCol();
		// check is there any piece in the diagonal of From to To?
		// check from (rowStart,colStart) to (rowEnd-1,x)
		int colStart = fromCol < toCol ? fromCol : toCol;
		int rowStart = fromCol < toCol ? fromRow : toRow;
		int colEnd = fromCol > toCol ? fromCol : toCol;
		// (y2-y1) == (x2-x1) = slope = 1
		if ((toRow - fromRow) == (toCol - fromCol)) {
			while (colStart < (colEnd - 1)) {
				if (state.getPiece(++rowStart, ++colStart) != null) {
//					System.out
//							.println("ExistPieceBetweenFromAndToInDiagonal(): exist other piece, return true");
					return true;
				}
			}
		} else { // slop = -1
			while (colStart < (colEnd - 1)) {
				if (state.getPiece(--rowStart, ++colStart) != null) {
//					System.out
//							.println("ExistPieceBetweenFromAndToInDiagonal(): exist other piece, return true");
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
//			System.out.println("checkQueensRule(): queen's rule false");
			return false;
		}
		return true;
	}

	// if leap over other piece, return true, or false
	private boolean ExistPieceBetweenFromAndToInCrossOrDiagonal(State state,
			Position from, Position to) {
		if (to.getRow() == from.getRow() || to.getCol() == from.getCol()) {
			// move in cross
			if (ExistPieceBetweenFromAndToInCross(state, from, to) == true)
				return true;
		} else {
			// move in diagonal
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
//			System.out.println("checkKnightsRule(): Knight's rule false");
			return false;
		}
		return true;
	}

	/**
	 * if Pawn do promotion, check its validation. if valid return true, else return false 
	 * @param state
	 * @param from
	 * @param to
	 * @param pieceKind
	 * @return
	 */
	private boolean checkPromotion(State state, Position from, Position to,
											PieceKind pieceKindOfPromotion) {
		if (pieceKindOfPromotion == PieceKind.BISHOP
				|| pieceKindOfPromotion == PieceKind.KNIGHT
				|| pieceKindOfPromotion == PieceKind.QUEEN
				|| pieceKindOfPromotion == PieceKind.ROOK) {
			switch (state.getPiece(from).getColor()) {
			case WHITE:
				if (from.getRow() == 6) {
					promotionValidation = true;
					return true;
				}
				break;
			case BLACK:
				if (from.getRow() == 1) {
					promotionValidation = true;
					return true;
				}
				break;
			}// switch
		}// if
		promotionValidation = false;
		return false;
	}
	
	/**
	 * check pawn's rule, pass return true, or false
	 */
	private boolean checkPawnsRule(State state, Position from, Position to, PieceKind pieceKindOfPromotion) {
		// can only move forward
		if (checkOnlyMoveForwardForPawn(from, to, state.getPiece(from)
				.getColor()) == false) {
//			System.out
//					.println("checkOnlyMoveForwardForPawn(): move backward, return false");
			return false;
		}
		// check move diagonally
		if (to.getCol() != from.getCol()) {
			if (checkDiagonalMoveForPawn(state, from, to,
					state.getPiece(from).getColor(),pieceKindOfPromotion) == false) {
	//			System.out
		//				.println("checkDiagonalMoveForPawn(): move diagonally fails, return false");
				return false;
			}
		}// move forward in the first step and set enpassant validation
		else if (state.getPiece(from).getColor() == Color.WHITE
				&& from.getRow() == 1
				|| state.getPiece(from).getColor() == Color.BLACK
				&& from.getRow() == 6) {
			if (checkFirstStepForPawn(state, from, to, state.getPiece(from)
					.getColor(),pieceKindOfPromotion) == false) {
//				System.out
//						.println("checkFirstStepForPawn(): first step move fails, return false");
				return false;
			}
		}
		// move one step forward in the same column
		else {
			if (checkMoveOneStepForwardForPawn(state, from, to,
					state.getPiece(from).getColor(), pieceKindOfPromotion) == false) {
//				System.out
//						.println("checkMoveOneStepForwardForPawn(): move one step forward fails, return false");
				return false;
			}
		}
		return true;
	}

	// check first step and set enpassant position
	private boolean checkFirstStepForPawn(State state, Position from,
			Position to, Color color, PieceKind pieceKindOfPromotion) {
		// first step can be one step or two step, and the destination isn't
		// occupied.
		// can't move to different column
		if (to.getCol() != from.getCol()) {
//			System.out
//					.println("checkFirstStepForPawn(): move to different colum, return false");
			return false;
		}
		// move beyond 2 steps
		else if (Math.abs(from.getRow() - to.getRow()) > 2) {
//			System.out
//					.println("checkFirstStepForPawn(): more than 2 step, return false");
			return false;
		}
		// exist other piece in path
		else if (checkOccupiedForPawn(state, from, to, color) == true) {// implicit: toCol = fromCol
	//		System.out
	//				.println("checkFirstStepForPawn(): exist other piece in path, return fale");
			return false;
		}
		//
		else if(pieceKindOfPromotion != null){
				return false;
		}
		// set Enpassant vaildation
		if (Math.abs(to.getRow() - from.getRow()) == 2) {
			state.setEnpassantPosition(to);
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

	// if forward square is occupied returns true, otherwise false
	private boolean checkOccupiedForPawn(State state, Position from,
			Position to, Color color) {
		int i = 0, j = 0;
		if (color == Color.WHITE) {
			i = from.getRow() + 1;
			j = to.getRow();
			for (; i <= j; ++i) {
//				System.out.println(new Position(i, from.getCol()));
//				System.out.println(state.getPiece(i, from.getCol()));
				if (state.getPiece(i, from.getCol()) != null) {
					return true; // occupied
				}
			}
		} else { // color = black
			i = from.getRow() - 1;
			j = to.getRow();
			for (; i >= j; --i) {
//				System.out.println(new Position(i, from.getCol()));
//				System.out.println(state.getPiece(i, from.getCol()));
				if (state.getPiece(i, from.getCol()) != null) {
					return true; // occupied
				}
			}
		}
		return false;
	}

	private boolean checkDiagonalMoveForPawn(State state, Position from,
			Position to, Color color, PieceKind pieceKindOfPromotion) {
		if (checkOnlyMoveForwardForPawn(from, to, color) == false) {
			// it's not necessary to check
			return false;
		}
		// check
		else if (!(Math.abs(to.getCol() - from.getCol()) == 1 && Math.abs(to
				.getRow() - from.getRow()) == 1)) {
			return false;
		} else if (state.getPiece(to) == null) {
			// no piece in "to" position
			return false;
		}
		// "to" isn't opponent piece
		else if (color == Color.WHITE
				&& state.getPiece(to).getColor() != Color.BLACK) {
			return false;
		} else if (color == Color.BLACK
				&& state.getPiece(to).getColor() != Color.WHITE) {
			return false;
		}
		else if(pieceKindOfPromotion != null ){
			if(checkPromotion(state, from, to,pieceKindOfPromotion) == false){
				return false;
			}
		}
		return true;
	}

	private boolean checkMoveOneStepForwardForPawn(State state, Position from,
			Position to, Color color, PieceKind pieceKindOfPromotion) {
		if (checkOnlyMoveForwardForPawn(from, to, color) == false) {
			// it's not necessary to check actually
			return false;
		}
		// move to different column
		else if (to.getCol() != from.getCol()) {
			return false;
		} else if (Math.abs(to.getRow() - from.getRow()) > 1) {
			// move beyond one step
			return false;
		} else if (checkOccupiedForPawn(state, from, to, color) == true) {
			// destination is occupied returns true
			return false;
		}
		else if(pieceKindOfPromotion != null){
			if(checkPromotion(state,from, to, pieceKindOfPromotion) == false){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

//		System.out.println("test message: ");
/*		StateChangerImpl stateChanger = new StateChangerImpl();
		//black turn, white pawn position(2,2), null(3,2) are under attack by black rook(4,2) 
		State start = new State();

		start.setTurn(Color.WHITE);
		start.setPiece(6, 5, null);
		start.setPiece(6, 6, null);
		start.setPiece(1, 4, null);
		
		Move moveWhiteQueen = new Move(new Position(0,3), new Position(4,7),null);
		stateChanger.makeMove(start, moveWhiteQueen);
	*/	
		
		/*start.setTurn(Color.BLACK);
//		stateChanger.isUnderAttack(start, new Position(3,2));
		start.setPiece(1, 0,null);
//		stateChanger.isUnderAttack(start, new Position(6,0));
//		stateChanger.isUnderAttack(start, new Position(2,2));		
//		start.setPiece(1, 3,null);		
//		stateChanger.isUnderAttack(start, new Position(5,7));
		start.setPiece(3, 3,new Piece(Color.WHITE,PieceKind.PAWN));		
//		stateChanger.isUnderAttack(start, new Position(4,3));		
	*/
/*		start.setPiece(0, 5,null);		
		start.setPiece(0, 6,null);
		Move move  = new Move(new Position(0,4), new Position(0,6), null);
		stateChanger.makeMove(start, move);
*/		
		
		//		stateChanger.isUnderAttack(start, new Position(6,3));		
//		start.setPiece(0, 2,null);		
//		start.setPiece(0, 3,null);		
//		start.setPiece(0, 1,null);		
//		stateChanger.isUnderAttack(start, new Position(1,3));		
			
		/*
 		Position pos1 = new Position(3, 4);
		Position pos2 = new Position(3, 4);
		System.out.println(pos1.equals(pos2));
		 * //move1 white pawn capture black rook start.setTurn(Color.BLACK);
		 * System.out.println("move1: white pawn capture black rook"); //
		 * start.setPiece(2, 2, new Piece(Color.BLACK,PieceKind.ROOK));
		 * start.setPiece(5, 2, new Piece(Color.WHITE,PieceKind.ROOK)); //
		 * start.setPiece(6, 4, null); // start.setPiece(1, 2, null); Move move
		 * = new Move(new Position(6,3), new Position(5,2), null);
		 * stateChanger.makeMove(start, move);
		 * 
		 * 
		 * //move2: move queen capture black pawn /*
		 * System.out.println("move2: move bishop"); start.setPiece(3, 3, new
		 * Piece(Color.WHITE, PieceKind.BISHOP)); start.setPiece(1, 4,null); //
		 * start.setPiece(5, 5, new Piece(Color.WHITE, PieceKind.BISHOP)); Move
		 * move2 = new Move(new Position(3,3), new Position(6,6),null);
		 * start.setTurn(Color.WHITE); stateChanger.makeMove(start, move2);
		 * 
		 * 
		 * System.out.println("move2: move rook capture black pawn");
		 * start.setPiece(0, 3, new Piece(Color.WHITE, PieceKind.ROOK));
		 * start.setPiece(1, 3,null); // start.setPiece(3, 3, new
		 * Piece(Color.BLACK,PieceKind.ROOK)); //set piece in queen's path Move
		 * move5 = new Move(new Position(0,3), new Position(6,3),null);
		 * start.setTurn(Color.WHITE); stateChanger.makeMove(start, move5);
		 * 
		 * 
		 * System.out.println("move2: move queen capture black pawn");
		 * start.setPiece(0, 3, new Piece(Color.WHITE, PieceKind.QUEEN));
		 * start.setPiece(3, 6, null); // start.setPiece(3, 3, new
		 * Piece(Color.BLACK,PieceKind.ROOK)); //set piece in queen's path
		 * start.setPiece(1, 4,null); Move move6 = new Move(new Position(0,3),
		 * new Position(3,6),null); start.setTurn(Color.WHITE);
		 * stateChanger.makeMove(start, move6);
		 * 
		 * //move4: move white knight (0,1) to (2,2)
		 * System.out.println("move4: move white knight (0,1) to (2,2)"); Move
		 * move4 = new Move(new Position(0,1), new Position(2,2),null);
		 * stateChanger.makeMove(start, move4); //move3: move black knight to
		 * white queen in (6,3) start.setPiece(6, 3, null);
		 * System.out.println("move3: move black knight to white queen in (6,3)"
		 * ); Move move3 = new Move(new Position(7,1), new Position(6,3),null);
		 * stateChanger.makeMove(start, move3);
		 * 
		 * Move move7 = new Move(new Position(1, 0), new Position(3, 0), null);
		 * System.out.println(start.getPiece(3, 0));
		 * stateChanger.makeMove(start, move7);
		 */
		/*
		 * start.setPiece(3, 3, new Piece(Color.WHITE, PieceKind.BISHOP));
		 * boolean havepiece = ExistPieceBetweenFromAndToInCross(start,new
		 * Position(1,3),new Position(2,3)); boolean havepiece2
		 * =ExistPieceBetweenFromAndToInDiagonal(start,new Position(3,3),new
		 * Position(1,1)); System.out.println("=========\n"+havepiece2); //
		 * System.out.println(start);
		 */
	}
}