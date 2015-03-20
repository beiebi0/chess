package org.paulsultan.hw9;

import org.paulsultan.hw2_5.StateExplorerImpl;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.State;

public class Heuristic {
    StateExplorerImpl stateExplorer;
    public Heuristic(){
        stateExplorer = new StateExplorerImpl();       
    }
    
    int getStateValue(State state){
        if (state.getGameResult()!=null && state.getGameResult().getWinner()==null){
            //draw
            return 0;
        }
        else if(state.getGameResult()!=null && state.getGameResult().getWinner()!=null){
            //there is a winner
            if(state.getGameResult().getWinner().isWhite())
                return Integer.MAX_VALUE;
            else
                return Integer.MIN_VALUE;
        }
        else{
            //sum up pieces
            int sum=0;
            for (int row=0; row<8; row++){
                for (int col=0; col<8; col++){
                    Piece piece = state.getPiece(row, col);
                    if (piece!=null){
                        int pieceValue=getPieceValue(piece.getKind());
                        if (piece.getColor().isWhite())
                            sum+=pieceValue;
                        else
                            sum-=pieceValue;

                    }
                }
            }
            return sum;
        }
    }
    
    public Iterable<Move> getOrderedMoves(State state){
        return stateExplorer.getPossibleMoves(state);
    }
    
    int getPieceValue(PieceKind kind){
        int value;
        switch(kind){
           case PAWN:
              value=1;
              break;
           case KNIGHT:
              value= 3;
              break;
           case BISHOP:
              value= 3;
              break;
           case ROOK:
              value= 5;
              break;
           case QUEEN:
              value= 9;
              break;
           case KING:
              value= 3;
              break;
           default:
              value= 0;
              break;
        }
        return value;
    }
}
