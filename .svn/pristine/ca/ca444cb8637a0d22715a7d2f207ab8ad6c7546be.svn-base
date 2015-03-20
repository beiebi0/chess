package org.sanjana.hw9;

import static org.shared.chess.Color.WHITE;
import static org.shared.chess.PieceKind.PAWN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.sanjana.hw2.StateChangerImpl;
import org.sanjana.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

public class Heuristic {

	private final static int QUEEN_VALUE = 9;
	private final static int BISHOP_VALUE = 3;
	private final static int KNIGHT_VALUE = 3;
	private final static int ROOK_VALUE = 5;
	private final static int PAWN_VALUE = 1;
	private StateExplorerImpl stateExplorer;
	private StateChangerImpl stateChanger;
	Move move;

	public Heuristic() {
		stateExplorer = new StateExplorerImpl();
		stateChanger = new StateChangerImpl();
	}

	int getStateValue(State state) 
	{
		GameResult gameResult = state.getGameResult();
		if (gameResult != null) {
			Color winner = gameResult.getWinner();
			if (winner == null)
				return 0;
			else if (winner.isWhite()) 
				return Integer.MAX_VALUE;
			else
				return Integer.MIN_VALUE;

		}

		int whiteScore = 0;
		int blackScore = 0;

		for (int r = 0; r < State.ROWS; r++)
		{
			for (int c = 0; c < State.COLS; c++)
			{
				Piece piece = state.getPiece(r, c);
				if (piece != null) 
				{
					PieceKind kind = piece.getKind();
					if (piece.getColor() == WHITE) 
						whiteScore = whiteScore + pieceValue(kind);
					else
						blackScore = blackScore + pieceValue(kind);
				}
			}
		}
		
//		if(stateChanger.IsKingInCheck(state,state.getTurn(),stateChanger.WhereIsKing(state,state.getTurn()))){
//			if(state.getTurn()==WHITE)
//				whiteScore+=50;
//			else
//				blackScore+=50;
//		}
		
		return whiteScore - blackScore;
	}
	
	private int pieceValue(PieceKind kind) {
		switch(kind)
		{
		case QUEEN:
			return QUEEN_VALUE;

		case BISHOP:
			return BISHOP_VALUE;

		case KNIGHT:
			return KNIGHT_VALUE;

		case ROOK:
			return ROOK_VALUE;

		case PAWN:
			return PAWN_VALUE;

		default:
			return 0;
		}
	}
/*
 * Adds Score for a player if pawn is close to promotion
 */
	private int helper(State state)
	{
		int whiteScore = 0;
		int blackScore = 0;

		for (int r = 0; r < State.ROWS; r++)
		{
			for (int c = 0; c < State.COLS; c++)
			{
				Piece piece = state.getPiece(r, c);
				if (piece != null) 
				{
					PieceKind kind = piece.getKind();
					if(kind==PAWN){
						if (piece.getColor() == Color.WHITE && r==6) {
							move=new Move(new Position(r,c), new Position(r+1,c), PieceKind.QUEEN);
							if(tryPromotion(state,move))
								whiteScore+=10;

							move=new Move(new Position(r,c), new Position(r+1,c-1), PieceKind.QUEEN);
							if(tryPromotion(state,move))
								whiteScore+=10;

							move=new Move(new Position(r,c), new Position(r+1,c+1), PieceKind.QUEEN);
							if(tryPromotion(state,move))
								whiteScore+=10;
						}

						if (piece.getColor() == Color.BLACK && r==1) {
							move=new Move(new Position(r,c), new Position(r-1,c), PieceKind.QUEEN);
							if(tryPromotion(state,move))
								blackScore+=10;

							move=new Move(new Position(r,c), new Position(r-1,c-1), PieceKind.QUEEN);
							if(tryPromotion(state,move))
								blackScore+=10;

							move=new Move(new Position(r,c), new Position(r-1,c+1), PieceKind.QUEEN);
							if(tryPromotion(state,move))
								blackScore+=10;
						}
					}
				}
			}
		}

		return whiteScore - blackScore;
	}

	@SuppressWarnings("hiding")
	static class MoveScore<Move> implements Comparable<MoveScore<Move>> {
		Move move;
		int score;

		public MoveScore(Move move,int score){
			this.move=move;
			this.score=score;
		}
		
		@Override
		public int compareTo(MoveScore<Move> o) {
			return o.score - score; 
		}
	}

	Iterable<Move> getOrderedMoves(State state) 
	{	
		List<MoveScore<Move>> moveScoreList = new ArrayList<MoveScore<Move>>();
		Set<Move> possibleMoves = stateExplorer.getPossibleMoves(state);

		for (Move move : possibleMoves)
		{
			State copy=tryMove(state, move);
			int value = getStateValue(copy)+helper(copy);
			if (state.getTurn()!=Color.WHITE)
				value = -value;

			MoveScore<Move> score = new MoveScore<Move>(move,value);
//			score.move = move;
//			score.score = value;
			moveScoreList.add(score);
		}

		Collections.sort(moveScoreList);
		List<Move> orderedMoves = new ArrayList<Move>();
		for (MoveScore<Move> moveScore : moveScoreList)
			orderedMoves.add(moveScore.move);

		return orderedMoves;
	}

	private State tryMove(State state,Move move){
		State newState=state.copy();
		stateChanger.makeMove(newState, move);
		return newState;
	}

	private boolean tryPromotion(State state, Move move){
		State copy=state.copy();
		try{
			stateChanger.makeMove(copy, move);
			return true;
		} catch (IllegalMove e) {
			return false;
		}
	}
}
