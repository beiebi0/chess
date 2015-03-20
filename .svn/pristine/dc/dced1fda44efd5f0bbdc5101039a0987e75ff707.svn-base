package org.yuanjia.hw2;

import java.util.ArrayList;

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
		// TODO: implement chess logic in HW2.
		Position to = move.getTo();
		Piece topiece = state.getPiece(to);
		if (topiece != null && topiece.getColor() == piece.getColor()) {
			// Can't move to occupied position.
			throw new IllegalMove();
		}
		// out of bound
		if (to.getCol() > 7 || to.getRow() > 7 || to.getCol() < 0
				|| to.getRow() < 0) {
			throw new IllegalMove();
		}
		// Can't move to origin position
		if (to.getCol() == from.getCol() && to.getRow() == from.getRow()) {
			throw new IllegalMove();
		}
		// Can't promote to king or pawn
		if (move.getPromoteToPiece() == PieceKind.KING
				|| move.getPromoteToPiece() == PieceKind.PAWN) {
			throw new IllegalMove();
		}
		// Only pawn can be promoted
		if (move.getPromoteToPiece() != null
				&& piece.getKind() != PieceKind.PAWN) {
			throw new IllegalMove();
		}
		// NumberOfMovesWithoutCaptureNorPawnMoved set
		if (state.getPiece(to) != null && piece.getKind() != PieceKind.PAWN) {
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		} else if (piece.getKind() != PieceKind.PAWN) {
			int o_numofmoves = state
					.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1;
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(o_numofmoves);
		} else if (piece.getKind() == PieceKind.PAWN) {
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		}
		// turn set!
		if (color == Color.BLACK) {
			state.setTurn(Color.WHITE);
		} else {
			state.setTurn(Color.BLACK);
		}
		// make moves
		
		switch(piece.getKind()){
		case PAWN:
			pawnMove(state,move);
			break;
		case KNIGHT:
			knightMove(state,move);
			break;
		case BISHOP:
			bishopMove(state,move);
			break;
		case ROOK:
			rookMove(state,move);
			break;
		case QUEEN:
			queenMove(state,move);
			break;
		case KING:
			kingMove(state,move);
			break;
		
		}
	//can not don't move king out of check	
	if(isColorKingUnderCheck(state,color)) throw new IllegalMove();
	//setCastlebool
	setCastlebool(state,move);
	//checkmate
	if(color==Color.BLACK&&isCheckmate(state,Color.WHITE)){
		state.setGameResult(new GameResult(Color.BLACK,GameResultReason.CHECKMATE));
	}else if(color==Color.WHITE&&isCheckmate(state,Color.BLACK)){
		state.setGameResult(new GameResult(Color.WHITE,GameResultReason.CHECKMATE));
	}
	//stalemate
	if(color==Color.BLACK&&isStalemate(state,Color.WHITE)){
		state.setGameResult(new GameResult(null,GameResultReason.STALEMATE));
	}else if(color==Color.WHITE&&isStalemate(state,Color.BLACK)){
		state.setGameResult(new GameResult(null,GameResultReason.STALEMATE));
	}
	//Fifty_move_rule
	if(state.getNumberOfMovesWithoutCaptureNorPawnMoved()==100){
		state.setGameResult(new GameResult(null,GameResultReason.FIFTY_MOVE_RULE));
	}
	
	
	}

	public void pawnMove(State state, Move move) {
		Position to = move.getTo();
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Color color = piece.getColor();
		
		//promote only when reach last row
		if(color.equals(Color.BLACK)&&to.getRow()!=0&&move.getPromoteToPiece()!=null){
			throw new IllegalMove();
		}else if(color.equals(Color.WHITE)&&to.getRow()!=7&&move.getPromoteToPiece()!=null){
			throw new IllegalMove();
		}

		if (color == Color.BLACK && from.getRow() - to.getRow() == 1) {
			if (from.getCol() == to.getCol() && state.getPiece(to) == null) {
				// no capture
				Piece promoted_p = piece;
				if (to.getRow() == 0)
					promoted_p = new Piece(color, move.getPromoteToPiece());
				if (to.getRow() != 0 && move.getPromoteToPiece() != null)
					throw new IllegalMove();
				state.setPiece(from, null);
				state.setPiece(to, promoted_p);
				state.setEnpassantPosition(null);
			} else if (Math.abs(from.getCol() - to.getCol()) == 1
					&& state.getPiece(to) != null) {
				// capture
				Piece promoted_p = piece;
				if (to.getRow() == 0)
					promoted_p = new Piece(color, move.getPromoteToPiece());
				if (to.getRow() != 0 && move.getPromoteToPiece() != null)
					throw new IllegalMove();
				state.setPiece(from, null);
				state.setPiece(to, promoted_p);
				state.setEnpassantPosition(null);
			//en passant
			}else if(Math.abs(from.getCol()-to.getCol())==1&&state.getEnpassantPosition()!=null&&state.getEnpassantPosition().equals(new Position(to.getRow()+1,to.getCol()))){
				state.setPiece(from, null);
				state.setPiece(state.getEnpassantPosition(), null);
				state.setPiece(to, piece);
				state.setEnpassantPosition(null);
			}else {
				throw new IllegalMove();
			}

		} else if (color == Color.BLACK && to.getRow() == 4
				&& from.getRow() == 6) {
			if (from.getCol() == to.getCol() && state.getPiece(to) == null
					&& state.getPiece(5, from.getCol()) == null) {
				state.setPiece(from, null);
				state.setPiece(to, piece);
				state.setEnpassantPosition(to);
			} else {
				throw new IllegalMove();
			}

		} else if (color == Color.WHITE && to.getRow() - from.getRow() == 1) {
			if (from.getCol() == to.getCol() && state.getPiece(to) == null) {
				// no capture
				Piece promoted_p = piece;
				if (to.getRow() == 7)
					promoted_p = new Piece(color, move.getPromoteToPiece());
				if (to.getRow() != 7 && move.getPromoteToPiece() != null)
					throw new IllegalMove();
				state.setPiece(from, null);
				state.setPiece(to, promoted_p);
				state.setEnpassantPosition(null);
			} else if (Math.abs(from.getCol() - to.getCol()) == 1
					&& state.getPiece(to) != null) {
				// capture
				Piece promoted_p = piece;
				if (to.getRow() == 7)
					promoted_p = new Piece(color, move.getPromoteToPiece());
				if (to.getRow() != 7 && move.getPromoteToPiece() != null)
					throw new IllegalMove();
				state.setPiece(from, null);
				state.setPiece(to, promoted_p);
				state.setEnpassantPosition(null);
			} else if(Math.abs(from.getCol()-to.getCol())==1&&state.getEnpassantPosition()!=null&&state.getEnpassantPosition().equals(new Position(to.getRow()-1,to.getCol()))){
				state.setPiece(from, null);
				state.setPiece(state.getEnpassantPosition(), null);
				state.setPiece(to, piece);
				state.setEnpassantPosition(null);
			}else {
				throw new IllegalMove();
			}

		} else if (color == Color.WHITE && to.getRow() == 3
				&& from.getRow() == 1) {
			if (from.getCol() == to.getCol() && state.getPiece(to) == null
					&& state.getPiece(2, from.getCol()) == null) {
				state.setPiece(from, null);
				state.setPiece(to, piece);
				state.setEnpassantPosition(to);
			} else {
				throw new IllegalMove();
			}
		} else {
			throw new IllegalMove();
		}

	}

	public void knightMove(State state, Move move) {
		Position to = move.getTo();
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		
		state.setEnpassantPosition(null);
		
		if ((Math.abs(from.getCol() - to.getCol()) == 2)
				&& (Math.abs(from.getRow() - to.getRow()) == 1)) {
			// 4 positions
			state.setPiece(from, null);
			state.setPiece(to, piece);
		} else if ((Math.abs(from.getCol() - to.getCol()) == 1)
				&& (Math.abs(from.getRow() - to.getRow()) == 2)) {
			// other 4 positions
			state.setPiece(from, null);
			state.setPiece(to, piece);
		} else {
			throw new IllegalMove();
		}
	}

	public void bishopMove(State state, Move move) {
		Position to = move.getTo();
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		
		state.setEnpassantPosition(null);

		if (Math.abs(from.getCol() - to.getCol()) == Math.abs(from.getRow()
				- to.getRow())) {
			// check whether over pieces
			for (int i = 1; i < Math.abs(from.getCol() - to.getCol()); i++) {
				if (to.getCol() > from.getCol() && to.getRow() > from.getRow()) {
					if (state.getPiece(from.getRow() + i, from.getCol() + i) != null) {
						throw new IllegalMove();
					}
				} else if (to.getCol() > from.getCol()
						&& to.getRow() < from.getRow()) {
					if (state.getPiece(from.getRow() - i, from.getCol() + i) != null) {
						throw new IllegalMove();
					}
				} else if (to.getCol() < from.getCol()
						&& to.getRow() < from.getRow()) {
					if (state.getPiece(from.getRow() - i, from.getCol() - i) != null) {
						throw new IllegalMove();
					}
				} else if (to.getCol() < from.getCol()
						&& to.getRow() > from.getRow()) {
					if (state.getPiece(from.getRow() + i, from.getCol() - i) != null) {
						throw new IllegalMove();
					}
				}
			}
			state.setPiece(from, null);
			state.setPiece(to, piece);
		} else {
			throw new IllegalMove();
		}
	}

	public void rookMove(State state, Move move) {
		Position to = move.getTo();
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Color color = piece.getColor();
		
		state.setEnpassantPosition(null);
	
		if (from.getCol() == to.getCol() || from.getRow() == to.getRow()) {
			//when move from start point, set castle to false
			if(from.equals(new Position(7, 7))||from.equals(new Position(0, 7))){
				state.setCanCastleKingSide(color, false);
			}
			if(from.equals(new Position(7, 0))||from.equals(new Position(0, 0))){
				state.setCanCastleQueenSide(color, false);
			}
			//deal with promoted pawn move back, and original rook was captured without moved
			if(to.equals(new Position(7, 7))||to.equals(new Position(0, 7))){
				state.setCanCastleKingSide(color, false);
			}
			if(to.equals(new Position(7, 0))||to.equals(new Position(0, 0))){
				state.setCanCastleQueenSide(color, false);
			}
			//actual moves
			if (from.getRow() < to.getRow()) {
				// whether over pieces
				for (int i = 1; i < to.getRow() - from.getRow(); i++) {
					if (state.getPiece(from.getRow() + i, from.getCol()) != null) {
						throw new IllegalMove();
					}
				}
				// make move
				state.setPiece(from, null);
				state.setPiece(to, piece);
			} else if (from.getRow() > to.getRow()) {
				// whether over
				for (int i = 1; i < from.getRow() - to.getRow(); i++) {
					if (state.getPiece(to.getRow() + i, from.getCol()) != null) {
						throw new IllegalMove();
					}
				}
				// make move
				state.setPiece(from, null);
				state.setPiece(to, piece);
			} else if (from.getCol() > to.getCol()) {
				// whether over
				for (int i = 1; i < from.getCol() - to.getCol(); i++) {
					if (state.getPiece(to.getRow(), to.getCol() + i) != null) {
						throw new IllegalMove();
					}
				}
				// make move
				state.setPiece(from, null);
				state.setPiece(to, piece);
			} else if (from.getCol() < to.getCol()) {
				// whether over
				for (int i = 1; i < to.getCol() - from.getCol(); i++) {
					if (state.getPiece(to.getRow(), from.getCol() + i) != null) {
						throw new IllegalMove();
					}
				}
				// make move
				state.setPiece(from, null);
				state.setPiece(to, piece);
			}
		} else {
			throw new IllegalMove();
		}
	}

	public void queenMove(State state, Move move) {
		Position to = move.getTo();
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Color color = piece.getColor();
		
		state.setEnpassantPosition(null);
		
		//like bishop
		if (Math.abs(from.getCol() - to.getCol()) == Math.abs(from.getRow()
				- to.getRow())) {
			// check whether over pieces
			for (int i = 1; i < Math.abs(from.getCol() - to.getCol()); i++) {
				if (to.getCol() > from.getCol() && to.getRow() > from.getRow()) {
					if (state.getPiece(from.getRow() + i, from.getCol() + i) != null) {
						throw new IllegalMove();
					}
				} else if (to.getCol() > from.getCol()
						&& to.getRow() < from.getRow()) {
					if (state.getPiece(from.getRow() - i, from.getCol() + i) != null) {
						throw new IllegalMove();
					}
				} else if (to.getCol() < from.getCol()
						&& to.getRow() < from.getRow()) {
					if (state.getPiece(from.getRow() - i, from.getCol() - i) != null) {
						throw new IllegalMove();
					}
				} else if (to.getCol() < from.getCol()
						&& to.getRow() > from.getRow()) {
					if (state.getPiece(from.getRow() + i, from.getCol() - i) != null) {
						throw new IllegalMove();
					}
				}
			}
			state.setPiece(from, null);
			state.setPiece(to, piece);
		//like rook
		} else if (from.getCol() == to.getCol() || from.getRow() == to.getRow()) {
			//set canCastle
			if(from.equals(new Position(7, 7))||from.equals(new Position(0, 7))){
				state.setCanCastleKingSide(color, false);
			}
			if(from.equals(new Position(7, 0))||from.equals(new Position(0, 0))){
				state.setCanCastleQueenSide(color, false);
			}
			if (from.getRow() < to.getRow()) {
				// whether over pieces
				for (int i = 1; i < to.getRow() - from.getRow(); i++) {
					if (state.getPiece(from.getRow() + i, from.getCol()) != null) {
						throw new IllegalMove();
					}
				}
				// make move
				state.setPiece(from, null);
				state.setPiece(to, piece);
			} else if (from.getRow() > to.getRow()) {
				// whether over
				for (int i = 1; i < from.getRow() - to.getRow(); i++) {
					if (state.getPiece(to.getRow() + i, from.getCol()) != null) {
						throw new IllegalMove();
					}
				}
				// make move
				state.setPiece(from, null);
				state.setPiece(to, piece);
			} else if (from.getCol() > to.getCol()) {
				// whether over
				for (int i = 1; i < from.getCol() - to.getCol(); i++) {
					if (state.getPiece(to.getRow(), to.getCol() + i) != null) {
						throw new IllegalMove();
					}
				}
				// make move
				state.setPiece(from, null);
				state.setPiece(to, piece);
			} else if (from.getCol() < to.getCol()) {
				// whether over
				for (int i = 1; i < to.getCol() - from.getCol(); i++) {
					if (state.getPiece(to.getRow(), from.getCol() + i) != null) {
						throw new IllegalMove();
					}
				}
				// make move
				state.setPiece(from, null);
				state.setPiece(to, piece);
			}
		} else {
			throw new IllegalMove();
		}
	}

	public void kingMove(State state, Move move) {
		Position to = move.getTo();
		Position from = move.getFrom();
		Piece piece = state.getPiece(from);
		Color color = piece.getColor();
		Piece blackrook = new Piece(Color.BLACK,PieceKind.ROOK);
		Piece whiterook = new Piece(Color.WHITE,PieceKind.ROOK);
		
		state.setEnpassantPosition(null);
		
		//normal moves
    	if(from.getCol()==to.getCol()&&Math.abs(from.getRow()-to.getRow())==1){
    		//make move
    		state.setPiece(from, null);
    		state.setPiece(to, piece);
    	}else if(Math.abs(from.getCol()-to.getCol())==1&&from.getRow()==to.getRow()){
    		//make move
    		state.setPiece(from, null);
    		state.setPiece(to, piece);
   
    	}else if(Math.abs(from.getCol()-to.getCol())==1&&Math.abs(from.getRow()-to.getRow())==1){
    		//make move
    		state.setPiece(from, null);
    		state.setPiece(to, piece);
    	
    	//castle moves	
    	}else if(from.equals(new Position(7,4))&&to.equals(new Position(7,6))){
    		if(state.isCanCastleKingSide(Color.BLACK)&&state.getPiece(7, 5)==null&&state.getPiece(7, 6)==null){
    			if(state.getPiece(7, 7)==null||!state.getPiece(7,7).equals(blackrook)) {
    				throw new IllegalMove();
    			}
    			if(isUnderColorAttack(state,Color.WHITE,from)) throw new IllegalMove();
    			if(isUnderColorAttack(state,Color.WHITE,new Position(7,5))) throw new IllegalMove();
    			state.setPiece(from, null);
    			state.setPiece(new Position(7,7), null);
    			state.setPiece(to, piece);
    			state.setPiece(new Position(7,5), new Piece(Color.BLACK,PieceKind.ROOK));
    		}else{throw new IllegalMove();}
    		
    	}else if(from.equals(new Position(7,4))&&to.equals(new Position(7,2))){
    		if(state.isCanCastleQueenSide(Color.BLACK)&&state.getPiece(7, 1)==null&&state.getPiece(7, 2)==null&&state.getPiece(7, 3)==null){
    			if(state.getPiece(7, 0)==null||!state.getPiece(7,0).equals(blackrook)) throw new IllegalMove();
    			if(isUnderColorAttack(state,Color.WHITE,from)) throw new IllegalMove();
    			if(isUnderColorAttack(state,Color.WHITE,new Position(7,3))) throw new IllegalMove();
    			
    			state.setPiece(from, null);
    			state.setPiece(new Position(7,0), null);
    			state.setPiece(to, piece);
    			state.setPiece(new Position(7,3), new Piece(Color.BLACK,PieceKind.ROOK));
    		}else{throw new IllegalMove();}
    		
    	}else if(from.equals(new Position(0,4))&&to.equals(new Position(0,6))){
    		if(state.isCanCastleKingSide(Color.WHITE)&&state.getPiece(0, 5)==null&&state.getPiece(0, 6)==null){
    			if(state.getPiece(0, 7)==null||!state.getPiece(0,7).equals(whiterook)) throw new IllegalMove();
    			if(isUnderColorAttack(state,Color.BLACK,from)) throw new IllegalMove();
    			if(isUnderColorAttack(state,Color.BLACK,new Position(0,5))) throw new IllegalMove();
    			
    			state.setPiece(from, null);
    			state.setPiece(new Position(0,7), null);
    			state.setPiece(to, piece);
    			state.setPiece(new Position(0,5), new Piece(Color.WHITE,PieceKind.ROOK));
    		}else{throw new IllegalMove();}
    		
    	}else if(from.equals(new Position(0,4))&&to.equals(new Position(0,2))){
    		if(state.isCanCastleQueenSide(Color.WHITE)&&state.getPiece(0, 1)==null&&state.getPiece(0, 2)==null&&state.getPiece(0, 3)==null){
    			if(state.getPiece(0, 0)==null||!state.getPiece(0,0).equals(whiterook)) throw new IllegalMove();
    			if(isUnderColorAttack(state,Color.BLACK,from)) throw new IllegalMove();
    			if(isUnderColorAttack(state,Color.BLACK,new Position(0,3))) throw new IllegalMove();
    			
    			state.setPiece(from, null);
    			state.setPiece(new Position(0,0), null);
    			state.setPiece(to, piece);
    			state.setPiece(new Position(0,3), new Piece(Color.WHITE,PieceKind.ROOK));
    		}else{throw new IllegalMove();}
    		
    	}else{
    		throw new IllegalMove();
    	}
		state.setCanCastleKingSide(color, false);
		state.setCanCastleQueenSide(color, false);
    
		//can not move to check
		if(color.equals(Color.WHITE)&&isUnderColorAttack(state,Color.BLACK,to)) throw new IllegalMove();
		if(color.equals(Color.BLACK)&&isUnderColorAttack(state,Color.WHITE,to)) throw new IllegalMove();
	}
	
	public ArrayList<Position> attackablePositions(State state, Position position){
		ArrayList<Position> r_pos = new ArrayList<Position>();
		if(state.getPiece(position)==null) return new ArrayList<Position>();
		
		Piece thispiece = state.getPiece(position);
		Color thiscolor = thispiece.getColor();
		int thiscol = position.getCol();
		int thisrow = position.getRow();
		
		switch(thispiece.getKind()){
		case PAWN:
			if(thispiece.getColor()==Color.BLACK){
				r_pos.add(new Position(thisrow-1,thiscol-1));
				r_pos.add(new Position(thisrow-1,thiscol+1));
			}else if(thispiece.getColor()==Color.WHITE){
				r_pos.add(new Position(thisrow+1,thiscol-1));
				r_pos.add(new Position(thisrow+1,thiscol+1));
			}
			break;
			
		case KNIGHT:
			r_pos.add(new Position(thisrow+1,thiscol+2));
			r_pos.add(new Position(thisrow+1,thiscol-2));
			r_pos.add(new Position(thisrow-1,thiscol+2));
			r_pos.add(new Position(thisrow-1,thiscol-2));
			r_pos.add(new Position(thisrow+2,thiscol+1));
			r_pos.add(new Position(thisrow+2,thiscol-1));
			r_pos.add(new Position(thisrow-2,thiscol+1));
			r_pos.add(new Position(thisrow-2,thiscol-1));
			break;
			
		case BISHOP:
			for(int i = 1; i < 8; i++){
				if(thisrow+i>7||thiscol+i>7||state.getPiece(thisrow+i, thiscol+i)!=null){
					if(thisrow+i<=7&&thiscol+i<=7&&!state.getPiece(thisrow+i, thiscol+i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow+i, thiscol+i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow+i, thiscol+i));
				}
			}
			for(int i = 1; i < 8; i++){	
				if(thisrow+i>7||thiscol-i<0||state.getPiece(thisrow+i, thiscol-i)!=null){
					if(thisrow+i<=7&&thiscol-i>=0&&!state.getPiece(thisrow+i, thiscol-i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow+i, thiscol-i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow+i, thiscol-i));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thisrow-i<0||thiscol+i>7||state.getPiece(thisrow-i, thiscol+i)!=null){
					if(thisrow-i>=0&&thiscol+i<=7&&!state.getPiece(thisrow-i, thiscol+i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow-i, thiscol+i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow-i, thiscol+i));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thisrow-i<0||thiscol-i<0||state.getPiece(thisrow-i, thiscol-i)!=null){
					if(thisrow-i>=0&&thiscol-i>=0&&!state.getPiece(thisrow-i, thiscol-i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow-i, thiscol-i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow-i, thiscol-i));
				}
			}
			break;
		case ROOK:
			for(int i = 1; i < 8; i++){
				if(thisrow+i>7||state.getPiece(thisrow+i, thiscol)!=null){
					if(thisrow+i<=7&&!state.getPiece(thisrow+i, thiscol).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow+i, thiscol));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow+i, thiscol));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thisrow-i<0||state.getPiece(thisrow-i, thiscol)!=null){
					if(thisrow-i>=0&&!state.getPiece(thisrow-i, thiscol).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow-i, thiscol));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow-i, thiscol));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thiscol+i>7||state.getPiece(thisrow, thiscol+i)!=null){
					if(thiscol+i<=7&&!state.getPiece(thisrow, thiscol+i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow, thiscol+i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow, thiscol+i));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thiscol-i<0||state.getPiece(thisrow, thiscol-i)!=null){
					if(thiscol-i>=0&&!state.getPiece(thisrow, thiscol-i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow, thiscol-i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow, thiscol-i));
				}
			}
			break;
			
		case QUEEN:
			//like bishop
			for(int i = 1; i < 8; i++){
				if(thisrow+i>7||thiscol+i>7||state.getPiece(thisrow+i, thiscol+i)!=null){
					if(thisrow+i<=7&&thiscol+i<=7&&!state.getPiece(thisrow+i, thiscol+i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow+i, thiscol+i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow+i, thiscol+i));
				}
			}
			for(int i = 1; i < 8; i++){	
				if(thisrow+i>7||thiscol-i<0||state.getPiece(thisrow+i, thiscol-i)!=null){
					if(thisrow+i<=7&&thiscol-i>=0&&!state.getPiece(thisrow+i, thiscol-i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow+i, thiscol-i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow+i, thiscol-i));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thisrow-i<0||thiscol+i>7||state.getPiece(thisrow-i, thiscol+i)!=null){
					if(thisrow-i>=0&&thiscol+i<=7&&!state.getPiece(thisrow-i, thiscol+i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow-i, thiscol+i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow-i, thiscol+i));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thisrow-i<0||thiscol-i<0||state.getPiece(thisrow-i, thiscol-i)!=null){
					if(thisrow-i>=0&&thiscol-i>=0&&!state.getPiece(thisrow-i, thiscol-i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow-i, thiscol-i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow-i, thiscol-i));
				}
			}
			//like rook
			for(int i = 1; i < 8; i++){
				if(thisrow+i>7||state.getPiece(thisrow+i, thiscol)!=null){
					if(thisrow+i<=7&&!state.getPiece(thisrow+i, thiscol).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow+i, thiscol));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow+i, thiscol));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thisrow-i<0||state.getPiece(thisrow-i, thiscol)!=null){
					if(thisrow-i>=0&&!state.getPiece(thisrow-i, thiscol).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow-i, thiscol));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow-i, thiscol));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thiscol+i>7||state.getPiece(thisrow, thiscol+i)!=null){
					if(thiscol+i<=7&&!state.getPiece(thisrow, thiscol+i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow, thiscol+i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow, thiscol+i));
				}
			}
			for(int i = 1; i < 8; i++){
				if(thiscol-i<0||state.getPiece(thisrow, thiscol-i)!=null){
					if(thiscol-i>=0&&!state.getPiece(thisrow, thiscol-i).getColor().equals(thiscolor)){
						r_pos.add(new Position(thisrow, thiscol-i));
					}
					break;
				}else{
					r_pos.add(new Position(thisrow, thiscol-i));
				}
			}
			break;
		case KING:
			r_pos.add(new Position(thisrow-1, thiscol-1));
			r_pos.add(new Position(thisrow-1, thiscol));
			r_pos.add(new Position(thisrow-1, thiscol+1));
			r_pos.add(new Position(thisrow, thiscol-1));
			r_pos.add(new Position(thisrow, thiscol+1));
			r_pos.add(new Position(thisrow+1, thiscol-1));
			r_pos.add(new Position(thisrow+1, thiscol));
			r_pos.add(new Position(thisrow+1, thiscol+1));
			break;
		}
		//need to delete the out bound positions
		ArrayList<Position> r_pos_inbound = new ArrayList<Position>();
		
		for(Position p:r_pos){
			if(p.getCol()>=0&&p.getCol()<=7&&p.getRow()>=0&&p.getRow()<=7){
				r_pos_inbound.add(p);
			}
		}
		
		return r_pos_inbound;
	}

	public boolean isUnderColorAttack(State state, Color color, Position position){
		ArrayList<Position> underAttackPos = new ArrayList<Position>();
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(state.getPiece(i, j)!=null&&state.getPiece(i, j).getColor()==color){
					ArrayList<Position> temppos = attackablePositions(state,new Position(i,j));
					underAttackPos.addAll(temppos);
				}
			}
		}
		
		for(Position p:underAttackPos){
			if(position.equals(p)) return true;
		}
		
		return false;
	}
	
	public boolean isColorKingUnderCheck(State state, Color color){
		Piece colorKing = new Piece(color,PieceKind.KING);
		Position pos = null;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(state.getPiece(i,j)!=null&&state.getPiece(i, j).equals(colorKing)){
					pos = new Position(i,j);
				}
			}
		}
		
		if(color.equals(Color.BLACK)){
			return isUnderColorAttack(state, Color.WHITE, pos);
		}else{
			return isUnderColorAttack(state, Color.BLACK, pos);
		}
	}

	public void setCastlebool(State state, Move move){
		Piece blackrook = new Piece(Color.BLACK,PieceKind.ROOK);
		Piece whiterook = new Piece(Color.WHITE,PieceKind.ROOK);
		Position to = move.getTo();
		Position from = move.getFrom();
		Position p77 = new Position(7,7);
		Position p70 = new Position(7,0);
		Position p74 = new Position(7,4);
		Position p07 = new Position(0,7);
		Position p00 = new Position(0,0);
		Position p04 = new Position(0,4);
		
		if(from.equals(p77)||to.equals(p77)){
			if(state.getPiece(7, 7)==null||!state.getPiece(7, 7).equals(blackrook)) state.setCanCastleKingSide(Color.BLACK, false);
		}else if(from.equals(p70)||to.equals(p70)){
			if(state.getPiece(7, 0)==null||!state.getPiece(7, 0).equals(blackrook)) state.setCanCastleQueenSide(Color.BLACK, false);
		}else if(from.equals(p07)||to.equals(p07)){
			if(state.getPiece(0, 7)==null||!state.getPiece(0, 7).equals(whiterook)) state.setCanCastleKingSide(Color.WHITE, false);
		}else if(from.equals(p00)||to.equals(p00)){
			if(state.getPiece(0, 0)==null||!state.getPiece(0, 0).equals(whiterook)) state.setCanCastleQueenSide(Color.WHITE, false);
		}
		
		
		
		
		
	}
	//exclude castle
	public ArrayList<Position> reachablePositions(State state, Position position){
		ArrayList<Position> reachables = new ArrayList<Position>();
		
		if(state.getPiece(position)==null) return reachables;
		Piece thispiece = state.getPiece(position);
		Color thiscolor = thispiece.getColor();
		int thiscol = position.getCol();
		int thisrow = position.getRow();
		
		if(thispiece.getKind()==PieceKind.PAWN){
			if(thiscolor.equals(Color.BLACK)){
				if(thisrow-1>=0&&thiscol+1<=7&&state.getPiece(thisrow-1, thiscol+1)!=null) reachables.add(new Position(thisrow-1,thiscol+1));
				if(thisrow-1>=0&&thiscol-1>=0&&state.getPiece(thisrow-1, thiscol-1)!=null){
					
					reachables.add(new Position(thisrow-1,thiscol-1));
				}
				if(thisrow-1>=0&&state.getPiece(thisrow-1, thiscol)==null) reachables.add(new Position(thisrow-1,thiscol));
				
				if(thisrow==6&&state.getPiece(5, thiscol)==null&&state.getPiece(4, thiscol)==null) reachables.add(new Position(thisrow-2,thiscol));
			}else if(thiscolor.equals(Color.WHITE)){
				if(thisrow+1<=7&&thiscol+1<=7&&state.getPiece(thisrow+1, thiscol+1)!=null) reachables.add(new Position(thisrow+1,thiscol+1));
				if(thisrow+1<=7&&thiscol-1>=0&&state.getPiece(thisrow+1, thiscol-1)!=null) reachables.add(new Position(thisrow+1,thiscol-1));
				if(thisrow+1<=7&&state.getPiece(thisrow+1, thiscol)==null) reachables.add(new Position(thisrow+1,thiscol));
				
				if(thisrow==1&&state.getPiece(2, thiscol)==null&&state.getPiece(3, thiscol)==null) reachables.add(new Position(thisrow+2,thiscol));
			}
		}else{ reachables=attackablePositions(state,position);}
		
		//need to delete the out bound positions and occupied by same color
		ArrayList<Position> reachables_inbound = new ArrayList<Position>();
		ArrayList<Position> reachables_last = new ArrayList<Position>();
		for(Position p:reachables){
			if(p.getCol()>=0&&p.getCol()<=7&&p.getRow()>=0&&p.getRow()<=7){
				reachables_inbound.add(p);
			}
		}	
		
		for(Position p:reachables_inbound){
			if(state.getPiece(p)!=null&&state.getPiece(p).getColor()!=thiscolor) {
				reachables_last.add(p);
			}else if(state.getPiece(p)==null){
				reachables_last.add(p);
			}
			
		}
		
		return reachables_last;
		
	}

	public boolean isCheckmate(State state, Color color){
	
		//original position in check
		if(!isColorKingUnderCheck(state,color)) {
			return false;
		}

		//possible other pieces' move and king's move
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Position poss = new Position(i,j);
				Piece moved_piece = null;
				if(state.getPiece(poss)!=null) moved_piece = state.getPiece(poss);
				if(moved_piece!=null&&moved_piece.getColor()==color){
					for(Position p:reachablePositions(state,poss)){
						State state1 = state.copy();
						state1.setPiece(p, moved_piece);
						state1.setPiece(poss, null);
						if(!isColorKingUnderCheck(state1,color)) return false;
					}
				}	
			}
		}
		return true;			
	}

	public boolean isStalemate(State state, Color color){

		//original position not in check
		if(isColorKingUnderCheck(state,color)) {
			return false;
		}

		//possible other pieces' move and king's move
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Position poss = new Position(i,j);
				Piece moved_piece = null;
				if(state.getPiece(poss)!=null) moved_piece = state.getPiece(poss);
				if(moved_piece!=null&&moved_piece.getColor()==color){
					for(Position p:reachablePositions(state,poss)){
						State state1 = state.copy();
						state1.setPiece(p, moved_piece);
						state1.setPiece(poss, null);
						if(!isColorKingUnderCheck(state1,color)) return false;
					}
				}	
			}
		}
		return true;		
	}
}
