package org.haoxiangzuo.hw2;

import java.util.ArrayList;

import org.shared.chess.*;


public class StateChangerImpl implements StateChanger {

	@Override
	public void makeMove(State state, Move move) throws IllegalMove {
	    if (state.getGameResult() != null) {
	      // Game already ended!
	      throw new IllegalMove();
	    }
	    Position from = move.getFrom();
	    Piece piece = state.getPiece(from);
	    Position enpassantPosition = state.getEnpassantPosition();
	    if (piece == null) {
	      // Nothing to move!
	      throw new IllegalMove();
	    }
	    Color color = piece.getColor();
	    if (color != state.getTurn()) {
	      // Wrong player moves!
	      throw new IllegalMove();
	    }
	    // TODO: implement chess logic in HW2
	    //Out of bound
	    if (!inBound(move.getTo())||!inBound(move.getFrom()))
	    	throw new IllegalMove();
	    
	    //check unpawn promotion
	    if (move.getPromoteToPiece()!=null
	    		&&(state.getPiece(move.getFrom()).getKind()!=PieceKind.PAWN
	    		||(piece.getColor()==Color.WHITE&&move.getTo().getRow()!=7)
	    		||(piece.getColor()==Color.BLACK&&move.getTo().getRow()!=0))
	    		||move.getPromoteToPiece()==PieceKind.PAWN)
	    	throw new IllegalMove();
	    
	    boolean exception = true;
	    //Get PieceKind
	    switch (piece.getKind())
	    {
	   		case PAWN:
	   			exception = makePawnMove(state, move);
	   			break;
	   		case KING:
	   			exception = makeKingMove(state, move);
    			break;
	    	case QUEEN:
	    		exception = makeQueenMove(state, move);
	   			break;
	   		case BISHOP:
	   			exception = makeBishopMove(state, move);
	   			break;
    		case ROOK:
    			exception = makeRookMove(state, move);
	    		break;
	    	case KNIGHT:
	    		exception = makeKnightMove(state, move);
	   			break;
	   	}
	    //check if legal move
	    if(!exception)
	    	throw new IllegalMove();
	    	
	    
	    //check Enpassant Position
	    if (state.getEnpassantPosition()==enpassantPosition)
	    	state.setEnpassantPosition(null);
	    
	    //check is my king under direct attack
	 	if (willThisPositionUnderCheck(state, findKingPosition(state, state.getTurn())))
	 			throw new IllegalMove();
	 		 	
	    //Change Turn
	    state.setTurn(color==Color.WHITE?Color.BLACK:Color.WHITE);
	    
	    Position kingPos = findKingPosition(state, state.getTurn());
	    //End of Game Set of CheckMate
	    //now findKingPosition will return enemyKing position
	    boolean kingUnderCheck = willThisPositionUnderCheck(state, kingPos);
	    boolean canKingMoveAway = canKingMoveAwayFromCheck(state, kingPos);
	    boolean LegalMove = hasLegalMove(state, kingPos);
	    if (kingUnderCheck&&!(canKingMoveAway||LegalMove))
	    	state.setGameResult(new GameResult(color, GameResultReason.CHECKMATE));
	    //End of Game Set of StaleMate
	    if (!kingUnderCheck&&!canKingMoveAway&&!LegalMove)
	    	state.setGameResult(new GameResult(null, GameResultReason.STALEMATE));	
	    //End of Game Set of FIFTY_MOVE_RULE
	    if (state.getNumberOfMovesWithoutCaptureNorPawnMoved()==100)
	    	state.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));
	   
	  }

	public boolean hasLegalMove(State originalState, Position kingPos)
	{
		State state = originalState.copy();
	
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
					 state=originalState.copy();
					    //Get PieceKind
					    switch (state.getPiece(p).getKind())
					    {
					   		case PAWN:
					   		{
					   			if (row!=0&&row!=7)
					   			{
					   				flag = makePawnMove(state, new Move(p,new Position(row,col),null));
					   				break;
					   			}
					   			else
					   			{
					   				flag = makePawnMove(state, new Move(p,new Position(row,col),PieceKind.QUEEN));
					   				break;
					   			}
					   		}
					    	case QUEEN:
					    		flag = makeQueenMove(state, new Move(p,new Position(row,col),null));
					   			break;
					   		case BISHOP:
					   			flag = makeBishopMove(state, new Move(p,new Position(row,col),null));
					   			break;
				    		case ROOK:
				    			flag = makeRookMove(state, new Move(p,new Position(row,col),null));
					    		break;
					    	case KNIGHT:
					    		flag = makeKnightMove(state, new Move(p,new Position(row,col),null));
					   			break;
					    	default:
					    		break;	   		
					   	}
					    
					if (flag&&!willThisPositionUnderCheck(state, kingPos))
						return true;
					
					state=originalState.copy();
				}
		}
		
		
		return false;
	}
	public boolean canKingMoveAwayFromCheck(State state2, Position position)
	{	
		State state = state2.copy();
		state.setPiece(position, null);
		Color enemyColor = state.getTurn()==Color.WHITE?Color.BLACK:Color.WHITE;
		ArrayList<Position> possiblePositionForKing = new ArrayList<Position>();
		// checkKnight
		possiblePositionForKing.add(new Position(position.getRow()-1,position.getCol()+1));
		possiblePositionForKing.add(new Position(position.getRow()-1,position.getCol()-1));
		possiblePositionForKing.add(new Position(position.getRow()-1,position.getCol()));
		possiblePositionForKing.add(new Position(position.getRow(),position.getCol()-1));
		possiblePositionForKing.add(new Position(position.getRow(),position.getCol()+1));
		possiblePositionForKing.add(new Position(position.getRow()+1,position.getCol()-1));
		possiblePositionForKing.add(new Position(position.getRow()+1,position.getCol()+1));
		possiblePositionForKing.add(new Position(position.getRow()+1,position.getCol()));
		
		for (int i=0; i<possiblePositionForKing.size(); i++)
		{
			if(inBound(possiblePositionForKing.get(i))
					&&(state.getPiece(possiblePositionForKing.get(i))==null
					||state.getPiece(possiblePositionForKing.get(i)).getColor()==enemyColor)
					&&!willThisPositionUnderCheck(state, possiblePositionForKing.get(i)))
				return true;
		}
		
		return false;
	}
	public boolean makePawnMove(State state, Move move)
	{
		Piece piece = state.getPiece(move.getFrom());
		// from initial position move two squares ahead 
		if (move.getFrom().getRow()==(piece.getColor()==Color.WHITE?1:6)
				&&move.getTo().getCol()==move.getFrom().getCol()
				&&move.getTo().getRow()==(piece.getColor()==Color.WHITE?3:4)
				&&state.getPiece(move.getFrom().getRow()+(piece.getColor()==Color.WHITE?1:-1), move.getFrom().getCol())==null
				&&state.getPiece(move.getFrom().getRow()+(piece.getColor()==Color.WHITE?2:-2), move.getFrom().getCol())==null)
		{
			
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), piece);
			state.setEnpassantPosition(move.getTo());
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			return true;
		}
		
		// from one position move one squares ahead
		if (move.getFrom().getCol()==move.getTo().getCol()
				&&move.getFrom().getRow()+(piece.getColor()==Color.WHITE?1:-1)==move.getTo().getRow()
				&&state.getPiece(move.getTo())==null)
		{
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), piece);
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			promotion(state, move);
			if ((move.getTo().getRow()==7||move.getTo().getRow()==0)&&move.getPromoteToPiece()==null)
				return false;
			return true;
		}
		
		//from one position do capture 
		if ((move.getFrom().getCol()==move.getTo().getCol()-1||
				move.getFrom().getCol()==move.getTo().getCol()+1
				)&&move.getFrom().getRow()==move.getTo().getRow()-(piece.getColor()==Color.WHITE?1:-1)
				&&state.getPiece(move.getTo())!=null
				&&state.getPiece(move.getTo()).getColor()!=piece.getColor())
		{
			setCastlingCapture(state,move);
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), piece);
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			promotion(state, move);
			if ((move.getTo().getRow()==7||move.getTo().getRow()==0)&&move.getPromoteToPiece()==null)
				return false;
			return true;
		}
		
		//en passant
		if ((move.getFrom().getCol()==move.getTo().getCol()-1||
				move.getFrom().getCol()==move.getTo().getCol()+1
				)&&move.getFrom().getRow()==move.getTo().getRow()-(piece.getColor()==Color.WHITE?1:-1)
				&&state.getEnpassantPosition()!=null&&state.getEnpassantPosition().getCol()==move.getTo().getCol()
				&&state.getEnpassantPosition().getRow()+(piece.getColor()==Color.WHITE?1:-1)==move.getTo().getRow())
		{
			state.setPiece(move.getTo(), piece);
			state.setPiece(move.getFrom(), null);
			state.setPiece(state.getEnpassantPosition(), null);
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			return true;
		}
			
		return false;
	}

	public void promotion (State state, Move move)
	{
		if (state.getPiece(move.getTo()).getColor()==Color.WHITE
				&&move.getPromoteToPiece()!=null&&move.getTo().getRow()==7)
		{
			state.setPiece(move.getTo(), new Piece(Color.WHITE, move.getPromoteToPiece()));
		}
		if (state.getPiece(move.getTo()).getColor()==Color.BLACK
				&&move.getPromoteToPiece()!=null&&move.getTo().getRow()==0)
		{
			state.setPiece(move.getTo(), new Piece(Color.BLACK, move.getPromoteToPiece()));
		}
		if (move.getPromoteToPiece()!=null&&move.getTo().getRow()!=0&&move.getTo().getRow()!=7)
			throw new IllegalMove();
		if (move.getPromoteToPiece()==PieceKind.KING)
			throw new IllegalMove();
	}
	public boolean makeKnightMove(State state, Move move)  {
		// TODO Auto-generated method stub
		Piece fromPiece = state.getPiece(move.getFrom());
		Piece toPiece = state.getPiece(move.getTo());
		// Knight moving or doing capture
		if (canKnightMove(state, move)&&(toPiece==null||toPiece.getColor()!=fromPiece.getColor()))
		{
			if (toPiece!=null)
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			else
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved()+1);
			setCastlingCapture(state,move);
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), fromPiece);
			return true;
		}
		
		return false;
	}
	public boolean canKnightMove(State state, Move move)
	{
		int rowOffset = Math.abs(move.getTo().getRow()-move.getFrom().getRow());
		int colOffset = Math.abs(move.getTo().getCol()-move.getFrom().getCol());
		if ((rowOffset==2&&colOffset==1)||(rowOffset==1&&colOffset==2))
		{
			return true;
		}
		else
			return false;
	}
	public boolean makeRookMove(State state, Move move)  {
		// TODO Auto-generated method stub
		Piece fromPiece = state.getPiece(move.getFrom());
		Piece toPiece = state.getPiece(move.getTo());
		
		//Rook move in file or rank or do capture in file or rank
		if (canMoveInRandAndFile(state, move)
				&&(toPiece==null||toPiece.getColor()!=fromPiece.getColor()))
		{
			if (toPiece!=null)
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			else
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved()+1);
			setCastlingCapture(state,move);
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), fromPiece);
			if (move.getFrom().equals(new Position(7,0))||move.getFrom().equals(new Position(0,0)))
				state.setCanCastleQueenSide(fromPiece.getColor(), false);
			if (move.getFrom().equals(new Position(7,7))||move.getFrom().equals(new Position(0,7)))
				state.setCanCastleKingSide(fromPiece.getColor(), false);
			return true;
		}
		
		
		return false;
	}
	public boolean canMoveInRandAndFile(State state, Move move)
	{
		if (move.getFrom().getCol()!=move.getTo().getCol()
				&&move.getFrom().getRow()!=move.getTo().getRow())
			return false;
		if (move.getFrom().getRow()==move.getTo().getRow())
		{
			int row = move.getFrom().getRow();
			for (int i=
					move.getFrom().getCol()>move.getTo().getCol()
					?move.getTo().getCol()+1:move.getFrom().getCol()+1;
				 i<(move.getFrom().getCol()>move.getTo().getCol()
							?move.getFrom().getCol():move.getTo().getCol());
				 i++)
			{
				if (state.getPiece(row, i)!=null)
					return false;
			}
		}
		if (move.getFrom().getCol()==move.getTo().getCol())
		{
			int col = move.getFrom().getCol();
			for (int i=
					move.getFrom().getRow()>move.getTo().getRow()
					?move.getTo().getRow()+1:move.getFrom().getRow()+1;
				 i<(move.getFrom().getRow()>move.getTo().getRow()
							?move.getFrom().getRow():move.getTo().getRow());
				 i++)
			{
				if (state.getPiece(i, col)!=null)
					return false;
			}
		}
		return true;
	}
	public boolean makeBishopMove(State state, Move move)  {
		// TODO Auto-generated method stub
		Piece fromPiece = state.getPiece(move.getFrom());
		Piece toPiece = state.getPiece(move.getTo());
		// Bishop moving or doing capture
		if (canMoveDiagonally(state, move)
				&&(toPiece==null||toPiece.getColor()!=fromPiece.getColor()))
		{
			if (toPiece!=null)
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			else
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved()+1);
			setCastlingCapture(state,move);
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), fromPiece);
			return true;
		}
		
		return false;
	}
	public boolean canMoveDiagonally (State state, Move move){
		
		if (Math.abs(move.getFrom().getCol()-move.getTo().getCol())!=Math.abs(move.getFrom().getRow()-move.getTo().getRow()))
			return false;

		int flag1 = move.getFrom().getCol()<move.getTo().getCol()?1:-1;
		int flag2 = move.getFrom().getRow()<move.getTo().getRow()?1:-1;
		for (int i=1; i<Math.abs(move.getFrom().getCol()-move.getTo().getCol());i++)
		{
			int row = move.getFrom().getRow()+i*flag2;
			int col = move.getFrom().getCol()+i*flag1;
			if (state.getPiece(row, col)!=null)
				return false;
		}
		return true;
	}
	public boolean makeQueenMove(State state, Move move)  {
		// TODO Auto-generated method stub
		Piece fromPiece = state.getPiece(move.getFrom());
		Piece toPiece = state.getPiece(move.getTo());
		
		//move queen
		if ((canMoveInRandAndFile(state, move)||canMoveDiagonally(state, move))
				&&(toPiece==null||toPiece.getColor()!=fromPiece.getColor()))
		{
			if (toPiece!=null)
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			else
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved()+1);
			setCastlingCapture(state,move);
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), fromPiece);
			return true;
		}
		
		return false;
	}
	public void setCastlingCapture(State state, Move move)
	{
		Color enemyColor = state.getTurn()==Color.WHITE?Color.BLACK:Color.WHITE;
		if (state.getPiece(move.getTo())!=null){
		if (state.getPiece(move.getTo()).equals(new Piece(enemyColor, PieceKind.ROOK))
				&&(move.getTo().equals(new Position(7,7))||move.getTo().equals(new Position(0,7))))
			state.setCanCastleKingSide(enemyColor, false);
		if (state.getPiece(move.getTo()).equals(new Piece(enemyColor, PieceKind.ROOK))
				&&(move.getTo().equals(new Position(7,0))||move.getTo().equals(new Position(0,0))))
			state.setCanCastleQueenSide(enemyColor, false);
		}
	}
	public boolean makeKingMove(State state, Move move)  {
		// TODO Auto-generated method stub
		Piece fromPiece = state.getPiece(move.getFrom());
		Piece toPiece = state.getPiece(move.getTo());
		
		// if won't make king under check
		if (((Math.abs(move.getTo().getCol()-move.getFrom().getCol())==1&&Math.abs(move.getFrom().getRow()-move.getTo().getRow())==1)
				||(Math.abs(move.getTo().getCol()-move.getFrom().getCol())==0&&Math.abs(move.getFrom().getRow()-move.getTo().getRow())==1)
				||(Math.abs(move.getTo().getCol()-move.getFrom().getCol())==1&&Math.abs(move.getFrom().getRow()-move.getTo().getRow())==0))
				&&(toPiece==null||toPiece.getColor()!=fromPiece.getColor()))
		{
			if (toPiece!=null)
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			else
				state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved()+1);
			setCastlingCapture(state,move);
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), fromPiece);
			state.setCanCastleKingSide(fromPiece.getColor(), false);
			state.setCanCastleQueenSide(fromPiece.getColor(), false);
			return true;
		}
		
		//castling (move king)
		//king side castling
		if (state.isCanCastleKingSide(fromPiece.getColor())
				&&move.getFrom().getRow()==(fromPiece.getColor()==Color.WHITE?0:7)&&move.getFrom().getCol()==4
				&&move.getTo().getRow()==(fromPiece.getColor()==Color.WHITE?0:7)&&move.getTo().getCol()==6
				&&state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 5)==null
				&&state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 6)==null)
		{
			if (willThisPositionUnderCheck(state, move.getFrom())
					||willThisPositionUnderCheck(state,new Position((fromPiece.getColor()==Color.WHITE?0:7), 5)))
					return false;
			if (state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 7)==null
					||(state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 7)!=null)
						&&!state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 7).equals(new Piece(fromPiece.getColor(), PieceKind.ROOK)))
				{
					state.setCanCastleKingSide(fromPiece.getColor(), false);
					return false;
				}
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved()+1);
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), fromPiece);
			state.setPiece((fromPiece.getColor()==Color.WHITE?0:7), 7, null);
			state.setPiece((fromPiece.getColor()==Color.WHITE?0:7), 5, new Piece(fromPiece.getColor(), PieceKind.ROOK));
			state.setCanCastleKingSide(fromPiece.getColor(), false);
			state.setCanCastleQueenSide(fromPiece.getColor(), false);
			return true;
		}
		//queen side castling
		if (state.isCanCastleQueenSide(fromPiece.getColor())
				&&move.getFrom().getRow()==(fromPiece.getColor()==Color.WHITE?0:7)&&move.getFrom().getCol()==4
				&&move.getTo().getRow()==(fromPiece.getColor()==Color.WHITE?0:7)&&move.getTo().getCol()==2
				&&state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 1)==null
				&&state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 2)==null
				&&state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 3)==null)
		{
			if (willThisPositionUnderCheck(state, move.getFrom())
					||willThisPositionUnderCheck(state, new Position((fromPiece.getColor()==Color.WHITE?0:7), 3)))
				return false;
			if (state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 0)==null
					||(state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 0)!=null)
						&&!state.getPiece((fromPiece.getColor()==Color.WHITE?0:7), 0).equals(new Piece(fromPiece.getColor(), PieceKind.ROOK)))
				{
					state.setCanCastleQueenSide(fromPiece.getColor(), false);
					return false;
				}
			state.setNumberOfMovesWithoutCaptureNorPawnMoved(state.getNumberOfMovesWithoutCaptureNorPawnMoved()+1);
			state.setPiece(move.getFrom(), null);
			state.setPiece(move.getTo(), fromPiece);
			state.setPiece((fromPiece.getColor()==Color.WHITE?0:7), 0, null);
			state.setPiece((fromPiece.getColor()==Color.WHITE?0:7), 3, new Piece(fromPiece.getColor(), PieceKind.ROOK));
			state.setCanCastleKingSide(fromPiece.getColor(), false);
			state.setCanCastleQueenSide(fromPiece.getColor(), false);
			return true;
		}
		
		return false;
	}
	public Position findKingPosition(State state, Color color)
	{
		Position kingPos = new Position(-1,-1);
		for (int row=0; row<=7; row++)
			for (int col=0; col<=7; col++)
			{
				if (state.getPiece(row, col)!=null&&state.getPiece(row, col).equals(new Piece(color, PieceKind.KING)))
					kingPos = new Position(row, col);
			}
		return kingPos;
	}
	public boolean willThisPositionUnderCheck(State state, Position position){
		return isUnderPawnAttackCheck(state,position)
				||isUnderKnightAttackCheck(state, position)
				||isUnderFileOrRankAttackCheck(state, position)
				||isUnderDiagnalAttack(state, position)
				||isUnderKingAttack(state, position);
	}
	public boolean isUnderKingAttack(State state, Position position)
	{
		Color enemyColor = state.getTurn()==Color.WHITE?Color.BLACK:Color.WHITE;
		
		ArrayList<Position> possiblePositionForKning = new ArrayList<Position>();
		// checkKnight
		possiblePositionForKning.add(new Position(position.getRow()-1,position.getCol()+1));
		possiblePositionForKning.add(new Position(position.getRow()-1,position.getCol()-1));
		possiblePositionForKning.add(new Position(position.getRow()-1,position.getCol()));
		possiblePositionForKning.add(new Position(position.getRow(),position.getCol()-1));
		possiblePositionForKning.add(new Position(position.getRow(),position.getCol()+1));
		possiblePositionForKning.add(new Position(position.getRow()+1,position.getCol()-1));
		possiblePositionForKning.add(new Position(position.getRow()+1,position.getCol()+1));
		possiblePositionForKning.add(new Position(position.getRow()+1,position.getCol()));
		
		for (int i=0; i<possiblePositionForKning.size(); i++)
		{
			if(inBound(possiblePositionForKning.get(i))
					&&state.getPiece(possiblePositionForKning.get(i))!=null
					&&state.getPiece(possiblePositionForKning.get(i)).equals(new Piece(enemyColor, PieceKind.KING)))
				return true;
		}
		
		
		return false;
	}
	public boolean isUnderFileOrRankAttackCheck(State state, Position position)
	{
		Color enemyColor = state.getTurn()==Color.WHITE?Color.BLACK:Color.WHITE;
		int[] meetFlag = {0,0,0,0};
		int[] offset = {1,0,-1,0,0,1,0,-1};
		for (int i=1; i<8; i++)
		{
			for (int j=0; j<4; j++)
			{
				Position now = new Position(position.getRow()+i*offset[j*2],position.getCol()+i*offset[j*2+1]);
				if(inBound(now)&&meetFlag[j]==0)
				{
					if(state.getPiece(now)!=null
						&&(state.getPiece(now).equals(new Piece(enemyColor, PieceKind.ROOK))
								||state.getPiece(now).equals(new Piece(enemyColor, PieceKind.QUEEN))))
						return true;
					else if (state.getPiece(now)!=null)
						meetFlag[j] = 1;
				}
			}
		}
		
		return false;
	}
	public boolean isUnderDiagnalAttack(State state, Position position)
	{
		Color enemyColor = state.getTurn()==Color.WHITE?Color.BLACK:Color.WHITE;
		int[] meetFlag = {0,0,0,0};
		int[] offset = {1,1,-1,1,1,-1,-1,-1};
		for (int i=1; i<8; i++)
		{
			for (int j=0; j<4; j++)
			{
				Position now = new Position(position.getRow()+i*offset[j*2],position.getCol()+i*offset[j*2+1]);
				if (inBound(now)&&meetFlag[j]==0)
				{
						if(state.getPiece(now)!=null
							&&(state.getPiece(now).equals(new Piece(enemyColor, PieceKind.BISHOP))
							||state.getPiece(now).equals(new Piece(enemyColor, PieceKind.QUEEN))))
							return true;
						else if (state.getPiece(now)!=null)
							meetFlag[j] = 1;
				}
			}
		}
		return false;
	}
	public boolean isUnderPawnAttackCheck(State state, Position position)
	{
		Color enemyColor = state.getTurn()==Color.WHITE?Color.BLACK:Color.WHITE;
		Color myColor = state.getTurn();
		
		// checkPawn
		if ((position.getCol()>=1&&(myColor==Color.WHITE?position.getRow()<=6:position.getRow()>=1)
				&&state.getPiece(position.getRow()+(myColor==Color.WHITE?1:-1), position.getCol()-1)!=null
				&&state.getPiece( position.getRow()+(myColor==Color.WHITE?1:-1), position.getCol()-1).equals(new Piece(enemyColor, PieceKind.PAWN)))
			||
			(position.getCol()<=6&&(myColor==Color.WHITE?position.getRow()<=6:position.getRow()>=1)
				&&state.getPiece(position.getRow()+(myColor==Color.WHITE?1:-1), position.getCol()+1)!=null
				&&state.getPiece(position.getRow()+(myColor==Color.WHITE?1:-1), position.getCol()+1).equals(new Piece(enemyColor, PieceKind.PAWN))))
			return true;
		
		
		return false;
	}
	public boolean isUnderKnightAttackCheck(State state, Position position)
	{
		Color enemyColor = state.getTurn()==Color.WHITE?Color.BLACK:Color.WHITE;
		
		ArrayList<Position> possiblePositionForKnight = new ArrayList<Position>();
		// checkKnight
		possiblePositionForKnight.add(new Position(position.getRow()-2,position.getCol()+1));
		possiblePositionForKnight.add(new Position(position.getRow()-2,position.getCol()-1));
		possiblePositionForKnight.add(new Position(position.getRow()-1,position.getCol()+2));
		possiblePositionForKnight.add(new Position(position.getRow()-1,position.getCol()-2));
		possiblePositionForKnight.add(new Position(position.getRow()+2,position.getCol()+1));
		possiblePositionForKnight.add(new Position(position.getRow()+2,position.getCol()-1));
		possiblePositionForKnight.add(new Position(position.getRow()+1,position.getCol()+2));
		possiblePositionForKnight.add(new Position(position.getRow()+1,position.getCol()-2));
		
		for (int i=0; i<possiblePositionForKnight.size(); i++)
		{
			if(inBound(possiblePositionForKnight.get(i))
					&&state.getPiece(possiblePositionForKnight.get(i))!=null&&state.getPiece(possiblePositionForKnight.get(i)).equals(new Piece(enemyColor, PieceKind.KNIGHT)))
				return true;
		}
		
		
		return false;
	}
	public boolean inBound(Position position)
	{
		return position.getCol()>=0&&position.getCol()<=7&&position.getRow()>=0&&position.getRow()<=7;
	}
	
}
