package org.leozis.hw2;

import java.util.Set;

import org.leozis.hw2_5.*;
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

/**
 * @author Leo
 *
 */
public class StateChangerImpl implements StateChanger {

	public void makeMove(State state, Move move) throws IllegalMove {
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

		if(this.isPossibleMove(state, move)){

			this.makeChange(state,  move);

			if(this.isInCheckMate(state)){
				state.setGameResult(new GameResult(piece.getColor(),
						GameResultReason.CHECKMATE));
			}else if(this.isInStaleMate(state)){
				state.setGameResult(new GameResult(null,
						GameResultReason.STALEMATE));
			}else{
				//50 move rule
				if(state.getNumberOfMovesWithoutCaptureNorPawnMoved() > 99){
					state.setGameResult(new GameResult(null,
							GameResultReason.FIFTY_MOVE_RULE));
				}
			}

		}else{
			throw new IllegalMove();
		}	
	}


	/**
	 * @param state
	 * @param move
	 * @return true if it is a possible move and false if it is not
	 */
	public boolean isPossibleMove(State state, Move move){
		if (state.getGameResult() != null) {
			// Game already ended!
			return false;
		}

		Position from = move.getFrom();
		Position to = move.getTo();

		Piece piece = state.getPiece(from);
		if (piece == null) {
			// Nothing to move!
			return false;
		}
		Color color = piece.getColor();
		if (color != state.getTurn()) {
			// Wrong player moves!
			return false;
		}
		if(from == to){
			// moved to same position
			return false;
		}
		if(to.getRow() < 0 || to.getRow() > 7 || to.getCol() < 0 || to.getCol() > 7){
			//out of bounds
			return false;
		}

		if (state.getPiece(to) != null && state.getPiece(to).getColor().equals(piece.getColor())) {
			// capture own color
			return false;
		}

		//check for illegal promotion of pawn
		if(color.equals(Color.WHITE) && piece.getKind().equals(PieceKind.PAWN)){
			if(to.getRow() != 7 && move.getPromoteToPiece() != null){
				return false;
			}
		}else if(color.equals(Color.BLACK) && piece.getKind().equals(PieceKind.PAWN)){
			if(to.getRow() != 0 && move.getPromoteToPiece() != null){
				return false;
			}
		}

		//only a pawn can be promoted
		if(move.getPromoteToPiece() != null && !piece.getKind().equals(PieceKind.PAWN)){
			return false;
		}

		if(move.getPromoteToPiece() != null 
				&& !move.getPromoteToPiece().equals(PieceKind.ROOK)
				&& !move.getPromoteToPiece().equals(PieceKind.QUEEN)
				&& !move.getPromoteToPiece().equals(PieceKind.KNIGHT)
				&& !move.getPromoteToPiece().equals(PieceKind.BISHOP)){
			return false;

		}


		/* If the player is in check: 
	    He must make a move (a legal move) to bring him out of check or else it is an illegal move

		If player is NOT in check, then make a valid move that does not cause a check */
		if(isInCheck(state)){
			//if this is a castle, then you cannot get out of check this way
			if(this.isLegalCastleMove(state, from, to)){
				return false;
			}
			if(this.isMoveSafeFromCheck(state, move)){
				return true;
			}else{
				return false;
			}
		}else{
			if(this.isValidMove(state, from, to, piece) && this.isMoveSafeFromCheck(state, move)){
				return true;
			}else{
				return false;
			}
		}
	}

	private void makeChange(State state, Move move){
		Piece piece = state.getPiece(move.getFrom());
		Position from = move.getFrom();
		Position to = move.getTo();

		if(move.getPromoteToPiece() == null){
			if(this.isLegalCastleMove(state, from, to)){
				this.makeCastleChange(state, from, to, piece);
			}else if(this.isLegalEnpassant(state, from, to)){
				this.makeEnpassantChange(state, from, to, piece);
			}else{
				this.makeNormalChange(state, from, to, piece);
			}
		}else{
			this.makePromotionChange(state, move, piece);
		}

	}

	private void makeNormalChange(State state, Position from, Position to, Piece piece){
		Boolean isWhite = piece.getColor().equals(Color.WHITE);

		// 50 moves counter
		if (state.getPiece(to) == null && !piece.getKind().equals(PieceKind.PAWN)) {
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
					.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		} else {
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		}

		state.setPiece(from.getRow(), from.getCol(), null);
		state.setPiece(to.getRow(), to.getCol(), piece);
		state.setTurn(isWhite ? Color.BLACK : Color.WHITE);


		setIfEnpassant(state, from, to, piece);

		setCastleFlags(state, from, to, piece);
	}

	private void makePromotionChange(State state, Move move, Piece piece){
		Position from = move.getFrom();
		Position to = move.getTo();
		Boolean isWhite = piece.getColor().equals(Color.WHITE);

		// 50 moves counter
		state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		state.setPiece(from.getRow(), from.getCol(), null);
		state.setPiece(to.getRow(), to.getCol(), new Piece((isWhite ? Color.WHITE: Color.BLACK),move.getPromoteToPiece()));
		state.setTurn(isWhite ? Color.BLACK : Color.WHITE);

		setIfEnpassant(state, from, to, piece);

		setCastleFlags(state, from, to, piece);
	}

	private void makeCastleChange(State state, Position from, Position to, Piece piece){


		state.setNumberOfMovesWithoutCaptureNorPawnMoved(state
				.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);

		state.setPiece(from.getRow(), from.getCol(), null);
		state.setPiece(to.getRow(), to.getCol(), piece);
		if(to.equals(new Position(0,2))){
			state.setPiece(0, 0, null);
			state.setPiece(0, 3, new Piece(piece.getColor(), PieceKind.ROOK));
		}else if(to.equals(new Position(0,6))){
			state.setPiece(0, 7, null);
			state.setPiece(0, 5, new Piece(piece.getColor(), PieceKind.ROOK));
		}else if(to.equals(new Position(7,2))){
			state.setPiece(7, 0, null);
			state.setPiece(7, 3, new Piece(piece.getColor(), PieceKind.ROOK));
		}else if(to.equals(new Position(7,6))){
			state.setPiece(7, 7, null);
			state.setPiece(7, 5, new Piece(piece.getColor(), PieceKind.ROOK));
		}

		state.setTurn(state.getTurn().equals(Color.WHITE) ? Color.BLACK : Color.WHITE);

		setIfEnpassant(state, from, to, piece);

		setCastleFlags(state, from, to, piece);
	}

	private void makeEnpassantChange(State state, Position from, Position to, Piece piece){
		Boolean isWhite = piece.getColor().equals(Color.WHITE);

		// 50 moves counter
		state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		state.setPiece(from.getRow(), from.getCol(), null);
		state.setPiece(to.getRow(), to.getCol(), piece);
		state.setPiece(from.getRow(),to.getCol(), null); //piece being captured is set to null
		state.setTurn(isWhite ? Color.BLACK : Color.WHITE);


		setIfEnpassant(state, from, to, piece);

		setCastleFlags(state, from, to, piece);
	}

	//uses StateExplorer to see if player is in check mate. he is in check mate if there are 0 possible moves
	//and he is also in check at the time. 
	private boolean isInCheckMate(State state) {
		StateExplorerImpl stateExplorer = new StateExplorerImpl();

		Set<Move> allPossibleMoves = stateExplorer.getPossibleMoves(state);

		if(this.isInCheck(state)){
			if(allPossibleMoves.isEmpty()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	private boolean isInStaleMate(State state) {
		StateExplorerImpl stateExplorer = new StateExplorerImpl();

		Set<Move> allPossibleMoves = stateExplorer.getPossibleMoves(state);

		if(!this.isInCheck(state)){
			if(allPossibleMoves.isEmpty()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}



	//if the move is valid (if the piece moves according to its rules) then return true
	//this does not take "check" into consideration
	//Returns true if it successfully moved or false if it was an illegal move
	private Boolean isValidMove(State state, Position from, Position to, Piece piece){
		switch (piece.getKind()) {
		case PAWN:  
			if(this.isValidPawnMove(state, from, to, piece)){
				return true;
			}
			return false;
		case ROOK:  
			if(this.isValidRookMove(state, from, to, piece)){
				return true;
			}else{
				return false;
			}
		case KNIGHT: 
			if(this.isValidKnightMove(state, from, to, piece)){
				return true;
			}else{
				return false;
			}
		case BISHOP: 
			if(this.isValidBishopMove(state, from, to, piece)){
				return true;
			}else{
				return false;
			}
		case QUEEN:  
			if(this.isValidQueenMove(state, from, to, piece)){
				return true;
			}else{
				return false;
			}
		case KING:  
			if(this.isValidKingMove(state, from, to, piece)){
				return true;
			}else{
				return false;
			}
		default: return false;
		}
	}

	//Make the move in a fake state and see if the player is in check. if he is not in check, then return true
	private boolean isMoveSafeFromCheck(State state, Move move) {
		Piece piece = state.getPiece(move.getFrom());
		Color color = piece.getColor();
		Position from = move.getFrom();
		Position to = move.getTo();

		State fakeState = state.copy();

		//make the move in a fake state if its valid
		//if it is NOT a valid move then return false
		if(this.isValidMove(fakeState, from, to, piece)){
			this.makeChange(fakeState,move);
		}else{
			return false;
		}

		//Keep the turn set on the player so we can find out if he is still in check
		fakeState.setTurn(color);

		if(this.isInCheck(fakeState)){
			return false;
		}else{
			return true;
		}
	}


	//returns true if the color is in check (if it is possible for the opposite color to capture the king on the next move)
	private Boolean isInCheck(State state){
		Color color = state.getTurn();
		Boolean isWhite = color.equals(Color.WHITE);
		Color oppositeColor = isWhite ? Color.BLACK : Color.WHITE;

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Piece currentPiece = state.getPiece(i,j);
				if(currentPiece != null){
					if(currentPiece.getColor().equals(oppositeColor)){
						switch (currentPiece.getKind()) {
						case PAWN:  
							if(this.isLegalPawnCapture(state, this.kingPosition(state,color),new Position(i,j))){
								return true; 
							}
							break;
						case ROOK:
							if(this.isValidRookMove(state, new Position(i,j), this.kingPosition(state,color),currentPiece)){
								return true;
							}
							break;
						case KNIGHT: 
							if(this.isValidKnightMove(state, new Position(i,j), this.kingPosition(state,color),currentPiece)){
								return true; 
							}
							break;
						case BISHOP: 
							if(this.isValidBishopMove(state, new Position(i,j), this.kingPosition(state,color),currentPiece)){
								return true;
							}
							break;
						case QUEEN: 
							if(this.isValidQueenMove(state, new Position(i,j), this.kingPosition(state,color),currentPiece)){
								return true; 
							}
							break;
						case KING:
							if(this.isValidKingMove(state, new Position(i,j), this.kingPosition(state,color),currentPiece)){
								return true; 
							}
							break;
						default:
							break;
						}
					}
				}
			}
		}

		return false;
	}

	//gives the position of the king whose color is passed in as a parameter
	private Position kingPosition(State state, Color color){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Piece currentPiece = state.getPiece(i,j);
				if(currentPiece != null && currentPiece.getColor().equals(color) && currentPiece.getKind().equals(PieceKind.KING)){
					return new Position(i,j);
				}
			}
		}
		return null;
	}



	//PAWN MOVES
	private Boolean isValidPawnMove(State state, Position from, Position to, Piece piece){
		Boolean isWhite = piece.getColor().equals(Color.WHITE);

		//Moving two squares
		//CONDITIONS: TO square must be null, and there cannot be pieces in the way, and must be in the 3rd row if white and same column
		if(state.getPiece(to) == null &&
				state.getPiece(new Position(isWhite ? 2 : 5, from.getCol())) == null &&
				from.getRow() == (isWhite ? 1: 6) && 
				to.getRow() == (isWhite ? 3 : 4) && 
				from.getCol() == to.getCol()){

			return true;

			//Moving pawn one square
			//CONDITIONS: TO square must be null, and must be in the next row and same column
		}else if(state.getPiece(to) == null && 
				(to.getRow() == (isWhite ? from.getRow()+1 : from.getRow()-1)) && 
				(to.getCol() == from.getCol())){

			return true;

			//Capturing
			//CONDITIONS: TO square must be opposite color, and must be in the next row and same previous / next column
		}else if(isLegalPawnCapture(state,to,from)){

			return true;

		}else if(this.isLegalEnpassant(state, from, to)){
			return true;
		}
		else return false;
	}

	//ROOK MOVES
	private Boolean isValidRookMove(State state, Position from, Position to, Piece piece){
		if(isOppositeColorOrEmpty(state, from, to, piece)  &&
				(isLegalHorizontalMove(state, to, from) || isLegalVerticalMove(state, to, from))){

			return true;

		}
		else return false;
	}

	//BISHOP MOVES
	private Boolean isValidBishopMove(State state, Position from, Position to, Piece piece){		
		if(isOppositeColorOrEmpty(state, from, to, piece)  &&
				(isLegalDiagonalMove(state, to, from))){

			return true;

		}
		else return false;
	}

	//KNIGHT MOVES
	private Boolean isValidKnightMove(State state, Position from, Position to, Piece piece){		
		if(isOppositeColorOrEmpty(state, from, to, piece)  &&
				(isLegalKnightMove(state, to, from))){

			return true;

		}
		else return false;
	}

	//QUEEN MOVES
	private Boolean isValidQueenMove(State state, Position from, Position to, Piece piece){
		if(isOppositeColorOrEmpty(state, from, to, piece)  &&
				(isLegalHorizontalMove(state, to, from) || isLegalVerticalMove(state, to, from) || isLegalDiagonalMove(state, to, from))){

			return true;

		}
		else return false;
	}

	//KING MOVES
	private Boolean isValidKingMove(State state, Position from, Position to, Piece piece){
		if(isOppositeColorOrEmpty(state, from, to, piece)  &&
				(Math.abs(to.getCol() - from.getCol()) <= 1 && Math.abs(to.getRow() - from.getRow()) <= 1) &&
				(isLegalHorizontalMove(state, to, from) || isLegalVerticalMove(state, to, from) || isLegalDiagonalMove(state, to, from))){

			return true;

			//checks for castling
		}else if(isLegalCastleMove(state,from,to)){

			return true;

		}
		else return false;
	}


	private Boolean isOppositeColorOrEmpty(State state, Position from, Position to, Piece piece){
		Boolean isWhite = piece.getColor().equals(Color.WHITE);

		if(state.getPiece(to) == null){
			return true;
		}else if(state.getPiece(to).getColor() == (isWhite ? Color.BLACK : Color.WHITE)){
			return true;
		}else return false;
	}

	private Boolean isEmpty(State state, Position to){

		if(state.getPiece(to) == null){
			return true;
		}else return false;
	}

	private Boolean isLegalPawnCapture(State state, Position to, Position from){
		Boolean isWhite = state.getPiece(from).getColor().equals(Color.WHITE);

		if(state.getPiece(to) != null){
			if(state.getPiece(to).getColor() == (isWhite ? Color.BLACK : Color.WHITE) && 
					(to.getRow() == (isWhite ? from.getRow()+1 : from.getRow()-1)) && 
					(Math.abs(to.getCol() - from.getCol()) == 1)){
				return true;
			}
			else{ 
				return false;
			}
		}else{
			return false;
		}
	}

	private Boolean isLegalEnpassant(State state, Position from, Position to){
		Boolean isWhite = state.getPiece(from).getColor().equals(Color.WHITE);

		if(state.getPiece(to) == null &&
				(to.getRow() == (isWhite ? from.getRow()+1 : from.getRow()-1)) && 
				(Math.abs(to.getCol() - from.getCol()) == 1)){
			//if there is an enpassant available
			if(state.getEnpassantPosition() != null){
				int enpassantCol = state.getEnpassantPosition().getCol();
				int enpassantRow = state.getEnpassantPosition().getRow();
				//if the capture location is a legal enpassant, return true
				if(to.equals(new Position(isWhite ? enpassantRow+1 : enpassantRow-1,enpassantCol))){
					return true;
				}
			}
		}

		return false;
	}

	private Boolean isLegalCastleMove(State state, Position from, Position to){

		if(from.equals(new Position(0,4))){
			if(to.equals(new Position(0,2))){
				if(state.isCanCastleQueenSide(Color.WHITE) == false){
					return false;
				}else{
					return this.isEmptyAndNotInCheck(state,to);
				}
			}else if(to.equals(new Position(0,6))){
				if(state.isCanCastleKingSide(Color.WHITE) == false){
					return false;
				}else{
					return this.isEmptyAndNotInCheck(state, to);
				}
			}else{
				return false; //return false if king is placed in any other spot
			}
		}else if(from.equals(new Position(7,4))){ //else it is the black king
			if(to.equals(new Position(7,2))){
				if(state.isCanCastleQueenSide(Color.BLACK) == false){
					return false;
				}else{
					return this.isEmptyAndNotInCheck(state,to);
				}
			}else if(to.equals(new Position(7,6))){
				if(state.isCanCastleKingSide(Color.BLACK) == false){
					return false;
				}else{
					return this.isEmptyAndNotInCheck(state, to);
				}
			}else{
				return false; //return false if king is placed in any other spot
			}
		}
		return false;
	}

	//attempts to move the king into castling position. if no pieces are in the way, and if he will not
	//be under check, then it will return true
	private Boolean isEmptyAndNotInCheck(State state,Position to){
		Color currColor = state.getTurn();

		if(to.equals(new Position(0,6))){
			if(this.isEmpty(state, new Position(0,5)) && this.isEmpty(state, new Position(0,6))){
				State fakeState1 = state.copy();
				State fakeState2 = state.copy();
				this.makeNormalChange(fakeState1, new Position(0,4), new Position(0,5), state.getPiece(0, 4));
				this.makeNormalChange(fakeState2, new Position(0,4), new Position(0,6), state.getPiece(0, 4));

				//Keep the turn set on the player so we can find out if he is still in check
				fakeState1.setTurn(currColor);
				fakeState2.setTurn(currColor);

				if(!this.isInCheck(fakeState1) && !this.isInCheck(fakeState2)){
					return true;
				}
			}
		}else if(to.equals(new Position(0,2))){
			if(this.isEmpty(state, new Position(0,3)) && this.isEmpty(state, new Position(0,2)) && this.isEmpty(state, new Position(0,1))){
				State fakeState1 = state.copy();
				State fakeState2 = state.copy();
				this.makeNormalChange(fakeState1, new Position(0,4), new Position(0,3), state.getPiece(0, 4));
				this.makeNormalChange(fakeState2, new Position(0,4), new Position(0,2), state.getPiece(0, 4));

				//Keep the turn set on the player so we can find out if he is still in check
				fakeState1.setTurn(currColor);
				fakeState2.setTurn(currColor);

				if(!this.isInCheck(fakeState1) && !this.isInCheck(fakeState2)){
					return true;
				}
			}
		}else if(to.equals(new Position(7,6))){
			if(this.isEmpty(state, new Position(7,5)) && this.isEmpty(state, new Position(7,6))){
				State fakeState1 = state.copy();
				State fakeState2 = state.copy();
				this.makeNormalChange(fakeState1, new Position(7,4), new Position(7,5), state.getPiece(7, 4));
				this.makeNormalChange(fakeState2, new Position(7,4), new Position(7,6), state.getPiece(7, 4));

				//Keep the turn set on the player so we can find out if he is still in check
				fakeState1.setTurn(currColor);
				fakeState2.setTurn(currColor);

				if(!this.isInCheck(fakeState1) && !this.isInCheck(fakeState2)){
					return true;
				}
			}
		}else if(to.equals(new Position(7,2))){
			if(this.isEmpty(state, new Position(7,3)) && this.isEmpty(state, new Position(7,2)) && this.isEmpty(state, new Position(7,1))){
				State fakeState1 = state.copy();
				State fakeState2 = state.copy();
				this.makeNormalChange(fakeState1, new Position(7,4), new Position(7,3), state.getPiece(7, 4));
				this.makeNormalChange(fakeState2, new Position(7,4), new Position(7,2), state.getPiece(7, 4));

				//Keep the turn set on the player so we can find out if he is still in check
				fakeState1.setTurn(currColor);
				fakeState2.setTurn(currColor);

				if(!this.isInCheck(fakeState1) && !this.isInCheck(fakeState2)){
					return true;
				}
			}
		}
		return false;

	}

	//If there is a piece in the way between the to and from position horizontally
	//OR it is not a horizontal move, returns false
	//Otherwise true
	private Boolean isLegalHorizontalMove(State state, Position to, Position from){
		Boolean isGoingLeft = (to.getCol() < from.getCol()) ? true : false;

		//illegal move if not moving horizontally
		if(to.getRow() == from.getRow()){
			//step left if it is going left, and step right if it is going right
			if(isGoingLeft){
				for(int i = from.getCol() - 1; i >= to.getCol() + 1; i--){
					if(state.getPiece(to.getRow(), i) != null){
						return false;
					}
				}
			}else{
				for(int i = from.getCol() + 1; i <= to.getCol() - 1; i++){
					if(state.getPiece(to.getRow(), i) != null){
						return false;
					}
				}
			}
		}else{
			return false;
		}


		return true;
	}

	//If there is a piece in the way between the to and from position vertically
	//OR it is not a vertical move, returns false
	//Otherwise true
	private Boolean isLegalVerticalMove(State state, Position to, Position from){
		Boolean isGoingDown = (to.getRow() < from.getRow()) ? true : false;

		//illegal move if not moving vertically
		if(to.getCol() == from.getCol()){
			//step down if it is going down, and step up if it is going up
			if(isGoingDown){
				for(int i = from.getRow() - 1; i >= to.getRow() + 1; i--){
					if(state.getPiece(i, to.getCol()) != null){
						return false;
					}
				}
			}else{
				for(int i = from.getRow() + 1; i <= to.getRow() - 1; i++){
					if(state.getPiece(i, to.getCol()) != null){
						return false;
					}
				}
			}
		}else{
			return false;
		}


		return true;
	}

	private Boolean isLegalDiagonalMove(State state, Position to, Position from){
		//if it is a legal diagonal movement
		if(Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getCol() - to.getCol())){

			int fromRow = from.getRow();
			int fromCol = from.getCol();
			int toRow = to.getRow();
			int toCol = to.getCol();
			int rowDir = 0;
			int colDir = 0;

			if (fromRow != toRow) {
				rowDir = toRow > fromRow ? 1 : -1;
			}

			if (fromCol != toCol) {
				colDir = toCol > fromCol ? 1 : -1;
			}

			int currRow = fromRow + rowDir;
			int currCol = fromCol + colDir;

			while (currRow != toRow || currCol != toCol) {
				Piece currentPiece = state.getPiece(currRow, currCol);

				if (currentPiece != null) {
					return false;
				}

				currRow += rowDir;
				currCol += colDir;
			}

			return true;
		}else{
			return false;
		}
	}

	private Boolean isLegalKnightMove(State state, Position to, Position from) {
		if(Math.abs(to.getRow() - from.getRow()) == 2 && Math.abs(to.getCol() - from.getCol()) == 1 ){
			return true;
		}else if(Math.abs(to.getRow() - from.getRow()) == 1 && Math.abs(to.getCol() - from.getCol()) == 2){
			return true;
		}else{
			return false;
		}
	}

	//can't castle if rooks or kings have moved.
	private void setCastleFlags(State state, Position from, Position to,
			Piece piece) {

		if(state.getPiece(0, 0) == null){
			state.setCanCastleQueenSide(Color.WHITE, false);
		}else if(!state.getPiece(0, 0).getKind().equals(PieceKind.ROOK) || !state.getPiece(0, 0).getColor().equals(Color.WHITE)){
			state.setCanCastleQueenSide(Color.WHITE, false);
		}

		if(state.getPiece(0, 7) == null){
			state.setCanCastleKingSide(Color.WHITE, false);
		}else if(!state.getPiece(0, 7).getKind().equals(PieceKind.ROOK) || !state.getPiece(0, 7).getColor().equals(Color.WHITE)){
			state.setCanCastleKingSide(Color.WHITE, false);
		}

		if(state.getPiece(7, 0) == null){
			state.setCanCastleQueenSide(Color.BLACK, false);
		}else if(!state.getPiece(7, 0).getKind().equals(PieceKind.ROOK) || !state.getPiece(7, 0).getColor().equals(Color.BLACK)){
			state.setCanCastleQueenSide(Color.BLACK, false);
		}

		if(state.getPiece(7, 7) == null){
			state.setCanCastleKingSide(Color.BLACK, false);
		}else if(!state.getPiece(7, 7).getKind().equals(PieceKind.ROOK) || !state.getPiece(7, 7).getColor().equals(Color.BLACK)){
			state.setCanCastleKingSide(Color.BLACK, false);
		}

		if (from.equals(new Position(0, 4)) && piece.getKind().equals(PieceKind.KING) && piece.getColor().equals(Color.WHITE)) {
			state.setCanCastleKingSide(Color.WHITE, false);
			state.setCanCastleQueenSide(Color.WHITE, false);
		}

		if (from.equals(new Position(7, 4)) && piece.getKind().equals(PieceKind.KING) && piece.getColor().equals(Color.BLACK)) {
			state.setCanCastleKingSide(Color.BLACK, false);
			state.setCanCastleQueenSide(Color.BLACK, false);
		}
	}

	private void setIfEnpassant(State state, Position from, Position to,
			Piece piece) {

		if (piece.getKind().equals(PieceKind.PAWN)) {
			if (piece.getColor().equals(Color.WHITE)) {
				if (from.getRow() == 1 && to.getRow() == 3) {
					state.setEnpassantPosition(new Position(3, from.getCol()));
					return;
				}
			} else {
				if (from.getRow() == 6 && to.getRow() == 4) {
					state.setEnpassantPosition(new Position(4, from.getCol()));
					return;
				}
			}
		}

		state.setEnpassantPosition(null);

	}

}