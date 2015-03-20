package org.yuanjia.hw2_5;

import java.util.HashSet;
import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;
import org.yuanjia.hw2.StateChangerImpl;

public class StateExplorerImpl implements StateExplorer {

  StateChangerImpl statechanger = new StateChangerImpl();
  
  @Override
  public Set<Move> getPossibleMoves(State state) {
    Set<Position> possible_pos = getPossibleStartPositions(state);
    Set<Move> possible_move = new HashSet<Move>();
    
    for(Position from_p : possible_pos){
    	//possible_move.addAll(getPossibleMovesFromPosition(state,p));
    	for(Move moves : getPossibleMovesFromPosition(state,from_p)){
    		try{
    			State state_test = state.copy();
    			statechanger.makeMove(state_test,moves);
    			possible_move.add(moves);
    		}catch(IllegalMove e){
    		
    		}
    	}
    }
    return possible_move;
  }

  @Override
  public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
	  Color turncolor = state.getTurn();
	  Piece blackpawn = new Piece(Color.BLACK,PieceKind.PAWN);
	  Piece whitepawn = new Piece(Color.WHITE,PieceKind.PAWN);
	  
	  Set<Move> possible_move_singlestart = new HashSet<Move>();
	  Set<Position> possible_des = reachablePositions(state,start);
    
	  for(Position p : possible_des){
		  State state_test = state.copy();
		  
		  state_test.setPiece(p, state.getPiece(start));
		  state_test.setPiece(start, null);
		  if(!statechanger.isColorKingUnderCheck(state_test, turncolor)){
			  if(turncolor==Color.BLACK&&state.getPiece(start).equals(blackpawn)&&p.getRow()==0){
				  possible_move_singlestart.add(new Move(start,p,PieceKind.BISHOP));
				  possible_move_singlestart.add(new Move(start,p,PieceKind.ROOK));
				  possible_move_singlestart.add(new Move(start,p,PieceKind.QUEEN));
				  possible_move_singlestart.add(new Move(start,p,PieceKind.KNIGHT));
			  }else if(turncolor==Color.WHITE&&state.getPiece(start).equals(whitepawn)&&p.getRow()==7){
				  possible_move_singlestart.add(new Move(start,p,PieceKind.BISHOP));
				  possible_move_singlestart.add(new Move(start,p,PieceKind.ROOK));
				  possible_move_singlestart.add(new Move(start,p,PieceKind.QUEEN));
				  possible_move_singlestart.add(new Move(start,p,PieceKind.KNIGHT));
			  }else{
				  possible_move_singlestart.add(new Move(start,p,null));
			  }
		  }
	  }
    
	  
    return possible_move_singlestart;
  }

  @Override
  public Set<Position> getPossibleStartPositions(State state) {
	  Color turncolor = state.getTurn();
	  Set<Position> piece_pos = new HashSet<Position>();
	  Set<Position> possible_pos = new HashSet<Position>();
	  
	  for(int i = 0; i<8; i++){
		  for(int j = 0; j <8 ; j++){
			  if(state.getPiece(i, j)!=null&&state.getPiece(i, j).getColor()==turncolor){
				  piece_pos.add(new Position(i,j));
			  }
		  }
	  }
	  for(Position p: piece_pos){
		  
		  for(Position to_p : reachablePositions(state,p)){
			  State state1 = state.copy();
			  state1.setPiece(to_p, state.getPiece(p));
			  state1.setPiece(p, null);
			  if(!statechanger.isColorKingUnderCheck(state1, turncolor)){
				  possible_pos.add(p);
			  }
		  }
		  
	  }

		return possible_pos;
	
  }

  public Set<Position> attackablePositions(State state, Position position){
		Set<Position> r_pos = new HashSet<Position>();
		if(state.getPiece(position)==null) return new HashSet<Position>();
		
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
		Set<Position> r_pos_inbound = new HashSet<Position>();
		
		for(Position p:r_pos){
			if(p.getCol()>=0&&p.getCol()<=7&&p.getRow()>=0&&p.getRow()<=7){
				r_pos_inbound.add(p);
			}
		}
		
		return r_pos_inbound;
	}
  
  public Set<Position> reachablePositions(State state, Position position){
		Set<Position> reachables = new HashSet<Position>();
		
		if(state.getPiece(position)==null) return reachables;
		Piece thispiece = state.getPiece(position);
		Piece blackking = new Piece(Color.BLACK,PieceKind.KING);
		Piece whiteking = new Piece(Color.WHITE,PieceKind.KING);
		Color thiscolor = thispiece.getColor();
		Color enemy_color;
		if(thiscolor.equals(Color.WHITE)){
			enemy_color = Color.BLACK;
		}else{
			enemy_color = Color.WHITE;
		}
		int thiscol = position.getCol();
		int thisrow = position.getRow();
		Position enp = state.getEnpassantPosition();
		
		if(thispiece.getKind()==PieceKind.PAWN){
			if(thiscolor.equals(Color.BLACK)){
				if(thisrow-1>=0&&thiscol+1<=7&&state.getPiece(thisrow-1, thiscol+1)!=null) reachables.add(new Position(thisrow-1,thiscol+1));
				if(thisrow-1>=0&&thiscol+1<=7&&enp!=null&&enp.equals(new Position(thisrow,thiscol+1))){
					reachables.add(new Position(thisrow-1,thiscol+1));
				}
				if(thisrow-1>=0&&thiscol-1>=0&&state.getPiece(thisrow-1, thiscol-1)!=null)reachables.add(new Position(thisrow-1,thiscol-1));
				if(thisrow-1>=0&&thiscol-1>=0&&enp!=null&&enp.equals(new Position(thisrow,thiscol-1))){
					reachables.add(new Position(thisrow-1,thiscol-1));
				}
				
				if(thisrow-1>=0&&state.getPiece(thisrow-1, thiscol)==null) reachables.add(new Position(thisrow-1,thiscol));
				
				if(thisrow==6&&state.getPiece(5, thiscol)==null&&state.getPiece(4, thiscol)==null) reachables.add(new Position(thisrow-2,thiscol));
			}else if(thiscolor.equals(Color.WHITE)){
				if(thisrow+1<=7&&thiscol+1<=7&&state.getPiece(thisrow+1, thiscol+1)!=null) reachables.add(new Position(thisrow+1,thiscol+1));
				if(thisrow+1<=7&&thiscol+1<=7&&enp!=null&&enp.equals(new Position(thisrow,thiscol+1))){
					reachables.add(new Position(thisrow+1,thiscol+1));
				}
				if(thisrow+1<=7&&thiscol-1>=0&&state.getPiece(thisrow+1, thiscol-1)!=null) reachables.add(new Position(thisrow+1,thiscol-1));
				if(thisrow+1<=7&&thiscol-1>=0&&enp!=null&&enp.equals(new Position(thisrow,thiscol-1))){
					reachables.add(new Position(thisrow+1,thiscol-1));
				}
				
				
				if(thisrow+1<=7&&state.getPiece(thisrow+1, thiscol)==null) reachables.add(new Position(thisrow+1,thiscol));
				
				if(thisrow==1&&state.getPiece(2, thiscol)==null&&state.getPiece(3, thiscol)==null) reachables.add(new Position(thisrow+2,thiscol));
			}
		}else{ reachables=attackablePositions(state,position);}
		
		if(thispiece.equals(blackking)){
			if(position.equals(new Position(7,4))&&state.isCanCastleKingSide(thiscolor)){
				if(!statechanger.isColorKingUnderCheck(state, thiscolor)&&!statechanger.isUnderColorAttack(state, enemy_color, new Position(7,5))){
					if(state.getPiece(7, 5)==null&&state.getPiece(7, 6)==null)reachables.add(new Position(7,6));
				}
			}
			if(position.equals(new Position(7,4))&&state.isCanCastleQueenSide(thiscolor)){
				if(!statechanger.isColorKingUnderCheck(state, thiscolor)&&!statechanger.isUnderColorAttack(state, enemy_color, new Position(7,3))){
					if(state.getPiece(7, 1)==null&&state.getPiece(7, 2)==null&&state.getPiece(7,3)==null)reachables.add(new Position(7,2));
				}
			}
		}else if(thispiece.equals(whiteking)){
			if(position.equals(new Position(0,4))&&state.isCanCastleKingSide(thiscolor)){
				if(!statechanger.isColorKingUnderCheck(state, thiscolor)&&!statechanger.isUnderColorAttack(state, enemy_color, new Position(0,5))){
					if(state.getPiece(0, 5)==null&&state.getPiece(0, 6)==null)reachables.add(new Position(0,6));
				}
			}
			if(position.equals(new Position(0,4))&&state.isCanCastleQueenSide(thiscolor)){
				if(!statechanger.isColorKingUnderCheck(state, thiscolor)&&!statechanger.isUnderColorAttack(state, enemy_color, new Position(0,3))){
					if(state.getPiece(0, 1)==null&&state.getPiece(0, 2)==null&&state.getPiece(0,3)==null)reachables.add(new Position(0,2));
				}
			}
		}
		
		//need to delete the out bound positions and occupied by same color
		Set<Position> reachables_inbound = new HashSet<Position>();
		Set<Position> reachables_last = new HashSet<Position>();
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
}
