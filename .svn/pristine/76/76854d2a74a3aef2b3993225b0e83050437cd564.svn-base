package org.haoxiangzuo.hw9;


import org.haoxiangzuo.hw2.StateChangerImpl;
import org.haoxiangzuo.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;

public class Heuristic {
	StateChangerImpl stateChangerImpl;
	StateExplorerImpl stateExplorerImpl;
	public Heuristic ()
	{
		stateChangerImpl = new StateChangerImpl();
		stateExplorerImpl = new StateExplorerImpl();
	}
	
	int getStateValue(State state)
	{
		int score = 0;
		Position whiteKing = new Position(0,0);
		Position blackKing = new Position(0,0);
		if (state.getGameResult()!=null&&state.getGameResult().getWinner()!=null)
			return state.getGameResult().getWinner().equals(Color.WHITE) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		else
		{
			for (int row = 0; row<8; row++)
				for (int col = 0; col<8; col++)
				{
					Piece piece = state.getPiece(row, col);
					if (piece!=null&&piece.getColor().equals(Color.WHITE))
					{
						switch(piece.getKind())
						{
						case PAWN:
							score+=row;
							break;
						case BISHOP:
							score = score + 10;
							break;
						case ROOK:
							score = score + 10;
							break;
						case KNIGHT:
							score+= 6;
							break;
						case QUEEN:
							score+= 15;
							break;
						default:
							whiteKing = new Position(row,col);
							break;
						}
					}
					else if (piece!=null&&piece.getColor().equals(Color.BLACK))
					{
						switch(piece.getKind())
						{
						case PAWN:
							score-=(7-row);
							break;
						case BISHOP:
							score = score - 10;
							break;
						case ROOK:
							score = score - 10;
							break;
						case KNIGHT:
							score -= 6;
							break;
						case QUEEN:
							score -= 15;
							break;
						default:
							blackKing = new Position(row,col);
							break;
						}
					}
				}
			if (state.getTurn().equals(Color.WHITE))
			{
				boolean blackCheck = stateChangerImpl
						.willThisPositionUnderCheck(state, whiteKing);
				if (blackCheck)
					score -= 10;
			}
			else
			{
				boolean whiteCheck = stateChangerImpl.willThisPositionUnderCheck(state, blackKing);
				if (whiteCheck)
					score += 10;
			}
		}
		return score;
	}
	
	Iterable<Move> getOrderedMoves(State state)
	{
		return stateExplorerImpl.getPossibleMoves(state);
	}
}
