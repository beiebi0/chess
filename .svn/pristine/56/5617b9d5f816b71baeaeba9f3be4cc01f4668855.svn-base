package org.haoxiangzuo.hw2_5;

import java.util.ArrayList;
import java.util.Set;

import org.haoxiangzuo.hw2.StateChangerImpl;
import org.shared.chess.Color;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;

public class StateExplorerImpl implements StateExplorer {

	protected StateChangerImpl stateChangerImpl = new StateChangerImpl();
	@Override
	public Set<Move> getPossibleMoves(State state) {
		// TODO Auto-generated method stub
		Set<Move> sets = Sets.newHashSet();
		Set<Move> empty = Sets.newHashSet();
		sets = getPossibleMove(state,stateChangerImpl.findKingPosition(state, state.getTurn()));
		if (state.getGameResult()==null)
			return sets;
		else
			return empty;
	}

	@Override
	public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
		// TODO Auto-generated method stub
		Set<Move> empty = Sets.newHashSet();
		if (state.getGameResult()==null&&state.getPiece(start)!=null&&state.getTurn()==state.getPiece(start).getColor())
			return getPossibleMoveFrom(state,start);
		else
			return empty;
	}

	@Override
	public Set<Position> getPossibleStartPositions(State state) {
		// TODO Auto-generated method stub
		Set<Position> empty = Sets.newHashSet();
		if (state.getGameResult()==null)
			return getPossiblePosition(state,stateChangerImpl.findKingPosition(state, state.getTurn()));
		else
			return empty;
	}
	/**
	 * ifCanMove: to check in the given state, if the piece at the specific position 
	 * can move to board[row][col], it will return true if it can move to the position
	 * 
	 * @param state: the given state
	 * @param piece: the given piece
	 * @param row: the given row of destination position
	 * @param col: the given col of destination position
	 * @return boolean
	 */
	public  boolean ifCanMove(State state, Position piece, int row, int col)
	{
		
		boolean flag = false;
		switch (state.getPiece(piece).getKind())
	    {
	   		case PAWN:
	   		{
	   			if (row!=0&&row!=7)
	   			{
	   				flag = stateChangerImpl.makePawnMove(state, new Move(piece,new Position(row,col),null));
	   				break;
	   			}
	   			else
	   			{
	   				flag = stateChangerImpl.makePawnMove(state, new Move(piece,new Position(row,col),PieceKind.QUEEN));
	   				if (flag)
	   					state.setPiece(new Position(row,col), new Piece(state.getTurn(),PieceKind.PAWN));
	   				break;
	   			}
	   		}
	    	case QUEEN:
	    		flag = stateChangerImpl.makeQueenMove(state, new Move(piece,new Position(row,col),null));
	   			break;
	    	case KING:
	    		flag = stateChangerImpl.makeKingMove(state, new Move(piece, new Position(row, col), null));
	    		break;
	   		case BISHOP:
	   			flag = stateChangerImpl.makeBishopMove(state, new Move(piece,new Position(row,col),null));
	   			break;
    		case ROOK:
    			flag = stateChangerImpl.makeRookMove(state, new Move(piece,new Position(row,col),null));
	    		break;
	    	case KNIGHT:
	    		flag = stateChangerImpl.makeKnightMove(state, new Move(piece,new Position(row,col),null));
	   			break;
	    	default:
	    		break;	   		
	   	} 
		return flag;
	}
	/**
	 * addInMoveSet: Add the Move to the Set<Move> if the move is legal
	 * @param state: the given state
	 * @param posMove: the set of moves
	 * @param destination: the destination of the move
	 * @param flag: if the move is legal
	 * @param piece: the original position of the move
	 * @param kingPos: the king Position
	 */
	public void addInMoveSet(State state, Set<Move> posMove, Position destination, boolean flag, Position piece, Position kingPos)
	{		
		if (flag)
		{
			if (state.getPiece(destination).getKind()==PieceKind.KING)
			{
				if (!stateChangerImpl.willThisPositionUnderCheck(state, destination))
					posMove.add(new Move(piece,destination,null));
			}
			else if (state.getPiece(destination).getKind()==PieceKind.PAWN)
			{
				if (!stateChangerImpl.willThisPositionUnderCheck(state, kingPos)&&destination.getRow()!=7&&destination.getRow()!=0)
					posMove.add(new Move(piece,destination,null));
				else if ((destination.getRow()==0||destination.getRow()==7)&&!stateChangerImpl.willThisPositionUnderCheck(state, kingPos))
				{
					posMove.add(new Move(piece,destination,PieceKind.QUEEN));
					posMove.add(new Move(piece,destination,PieceKind.ROOK));
					posMove.add(new Move(piece,destination,PieceKind.KNIGHT));
					posMove.add(new Move(piece,destination,PieceKind.BISHOP));
				}
			}
			else
			{
				if (!stateChangerImpl.willThisPositionUnderCheck(state, kingPos))
					posMove.add(new Move(piece,destination,null));
			}
		}
	}
	/**
	 * MoveFromPosition: to move from the given position to all the positions it can move to
	 * 					 find those positions and add them into the set.
	 * @param originalState
	 * @param state
	 * @param posMove
	 * @param p
	 * @param kingPos
	 */
	public void MoveFromPosition(State originalState, State state, Set<Move> posMove, Position p, Position kingPos)
	{
		for (int row=0;row<=7;row++)
		{
			for(int col=0;col<=7;col++)
			{
				 boolean flag = false;
				    //Get PieceKind
				 state=originalState.copy();
				 
				 if(state.getPiece(p)!=null)
				 {
					
					 flag = ifCanMove(state, p, row, col);
					 if (flag)
						 addInMoveSet(state, posMove, new Position(row,col), flag, p, kingPos);
				 }
				 
				 state=originalState.copy();
			}
		}
	}
	/**
	 * getPossibleMoveFrom: get all the possible moves from one specific position
	 * @param originalState
	 * @param p
	 * @return set of moves
	 */
	public Set<Move> getPossibleMoveFrom(State originalState, Position p)
	{
		State state = originalState.copy();
		Set<Move> posMove = Sets.newHashSet();
		Position kingPos = stateChangerImpl.findKingPosition(state, state.getTurn());
		MoveFromPosition(originalState, state, posMove, p, kingPos);
		return posMove;
	}
	/**
	 * getPossibleMove: get all the possible moves from all position that can move in this turn
	 * @param originalState
	 * @param kingPos
	 * @return
	 */
	public Set<Move> getPossibleMove(State originalState, Position kingPos)
	{
		State state = originalState.copy();
		Set<Move> posMove = Sets.newHashSet();
		ArrayList<Position> allMyPosition = new ArrayList<Position>();
		for (int row=0;row<=7;row++)
			for(int col=0;col<=7;col++)
			{
				if (state.getPiece(row, col)!=null&&state.getPiece(row, col).getColor()==state.getTurn())
					allMyPosition.add(new Position(row,col));
			}
		for (int i=0; i<allMyPosition.size();i++)
		{
			MoveFromPosition(originalState, state, posMove, allMyPosition.get(i), kingPos);
			state=originalState.copy();
		}
		return posMove;
	}
	/**
	 * getPossiblePosition: get all the possible positions that can move in this turn
	 * @param originalState
	 * @param kingPos
	 * @return
	 */
	public Set<Position> getPossiblePosition(State originalState, Position kingPos)
	{
		State state = originalState.copy();
		Set<Position> posPos = Sets.newHashSet();
		ArrayList<Position> allMyPosition = new ArrayList<Position>();
		for (int row=0;row<=7;row++)
			for(int col=0;col<=7;col++)
			{
				if (state.getPiece(row, col)!=null&&state.getPiece(row, col).getColor()==state.getTurn())
					allMyPosition.add(new Position(row,col));
			}
		for (Position p:allMyPosition)
		{
			for (int row=0;row<=7;row++)
				for(int col=0;col<=7;col++)
				{
					 boolean flag = false;
					    //Get PieceKind
					 state=originalState.copy();
					 if(state.getPiece(p)!=null)
					 {
						 flag = ifCanMove(state, p, row, col);
						 if (flag
								 &&(state.getPiece(new Position(row,col)).getKind()==PieceKind.KING
								 	&&!stateChangerImpl.willThisPositionUnderCheck(state, new Position(row,col))
								 ||(state.getPiece(new Position(row,col)).getKind()!=PieceKind.KING
									&&!stateChangerImpl.willThisPositionUnderCheck(state, kingPos))))
							 posPos.add(p);				
					 }
					 
					 state=originalState.copy();
				}
		}
		return posPos;
	}
}
