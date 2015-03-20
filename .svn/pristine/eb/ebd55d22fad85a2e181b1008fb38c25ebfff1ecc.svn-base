package org.yuanjia.hw9;

import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;
import org.yuanjia.hw2_5.StateExplorerImpl;

public class SimpleHeuristic implements Heuristic {
    
	StateExplorer stateExplorer = new StateExplorerImpl();
    
    public final int pawnValue = 100;
    public final int knightValue = 300;
    public final int bishopValue = 300;
    public final int rookValue = 500;
    public final int queenValue = 900;
    public final int kingValue = 2000;
    
	@Override
	public int getStateValue(State state){
		
		int sum = 0;
		
	    if (state.getGameResult() != null) {
            if (state.getGameResult().getWinner().isWhite())
                    return Integer.MAX_VALUE;
            if (state.getGameResult().getWinner().isBlack())
                    return Integer.MIN_VALUE;
            return 0;
	    }
	    
	    for(int r = 0; r < 8; r++){
	    	for(int c = 0; c < 8; c++){
	    		Piece piece = state.getPiece(r, c);	
	    		if(piece == null) continue;
	    		int PN = piece.getColor().isWhite()?1:-1;
	    		
	    		switch(piece.getKind()){
	    		case PAWN:
	    			sum += pawnValue*PN;
	    			break;
	    		case ROOK:
	    			sum += rookValue*PN;
	    			break;
	    		case KNIGHT:
	    			sum += knightValue*PN;
	    		case QUEEN:
	    			sum += queenValue*PN;
	    		case BISHOP:
	    			sum += bishopValue*PN;
	    		case KING:
	    			sum += kingValue*PN;
	    		}
	    	}
	    }
	    return sum;
	}
	
	@Override
	public Iterable<Move> getOrderedMoves(State state) {
		return stateExplorer.getPossibleMoves(state);
	}
}
