package org.mengyanhuang.hw9;

import org.mengyanhuang.hw2.StateChangerImpl;
import org.mengyanhuang.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;

public class HeuristicImpl implements Heuristic {
	StateChangerImpl stateChangerImpl = new StateChangerImpl();
	StateExplorerImpl stateExplorerImpl = new StateExplorerImpl();
	
	 /**
	  * piece values are given by Hans Berliner's system
	  */
	 private static final int KING_VALUE = 6000;
	 private static final int QUEEN_VALUE = 880;
	 private static final int BISHOP_VALUE = 333;
	 private static final int KNIGHT_VALUE = 320;
	 private static final int ROOK_VALUE = 510;
	 private static final int PAWN_VALUE = 100;
	
	@Override
	public int getStateValue(State state) {
		// TODO Auto-generated method stub
		GameResult gameover = state.getGameResult();
	    if (gameover != null) {
	      //if game result is a draw
	      if (gameover.getWinner() == null){
	    	  return 0;
	      } else if (gameover.getWinner().equals(Color.WHITE)) {
	          return 10000;
	      } else if (gameover.getWinner().equals(Color.BLACK)) {
	    	  return -10000;
	      }
	    }
	    
	    int blackScore = 0, whiteScore = 0;
	    for (int i = 0; i < 8; ++i) {
	        for (int j = 0; j < 8; ++j) {
	          Piece p = state.getPiece(new Position(i, j));
	          if (p != null) {
	            switch (p.getKind()) {
	              case KING:
	                if (p.getColor().isWhite()) {
	                	whiteScore += KING_VALUE;
	                } else {
	                	blackScore += KING_VALUE;
	                }
	                break;
	              
	              case QUEEN:
	                if (p.getColor().isWhite()) {
	                	whiteScore += QUEEN_VALUE;
	                } else {
	                	blackScore += QUEEN_VALUE;
	                }
	                break;
	                
	              case BISHOP:
	                if (p.getColor().isWhite()) {
	                	whiteScore += BISHOP_VALUE;
	                } else {
	                	blackScore += BISHOP_VALUE;
	                }
	                break;
	             
	              case KNIGHT:
	                if (p.getColor().isWhite()) {
	                	whiteScore += KNIGHT_VALUE;
	                } else {
	                	blackScore += KNIGHT_VALUE;
	                }
	                break;
	              
	              case ROOK:
	                if (p.getColor().isWhite()) {
	                	whiteScore += ROOK_VALUE;
	                } else {
	                	blackScore += ROOK_VALUE;
	                }
	                break;
	              
	              case PAWN:
	                if (p.getColor().isWhite()) {
	                	whiteScore += PAWN_VALUE;
	                } else {
	                	blackScore += PAWN_VALUE;
	                }
	                break;
	              default:
	                break;
	            }
	          }
	        }
	      }
		return whiteScore - blackScore;
	}
	
	@Override
	public Iterable<Move> getOrderedMoves(State state) {
		// TODO Auto-generated method stub
		Iterable<Move> possiblemoves = stateExplorerImpl.getPossibleMoves(state);
		return possiblemoves;
	}
}
