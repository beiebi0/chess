package org.ashishmanral.hw9;

import org.ashishmanral.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.State;

import com.google.gwt.user.client.Window;

public class Heuristic {
	
	private StateExplorerImpl stateExplorer;
	private int moveCount;
	private static final int ENDGAME=75;   // Start Endgame tactics after 75 moves
	private static final int MIDDLEGAME=50;  // Start Middlegame tactics after 50 moves.
	
	public Heuristic(){
		stateExplorer = new StateExplorerImpl();
	}
	
	public Iterable<Move> getOrderedMoves(State state){
		
		return stateExplorer.getPossibleMoves(state);
		
	}
	
	public int getValueOfState(State state){
		if(state.getGameResult()!=null) return calculateGameEndedValue(state.getGameResult());
		int valueOfState=0;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Piece currentPiece = state.getPiece(i,j);
				if(currentPiece!=null) {
					valueOfState += getValueOfPiece(currentPiece);
					if(moveCount>=ENDGAME) {
						valueOfState += PieceSquareTable.getValueForEndgame(i, j, currentPiece);
					}
					else if(moveCount>=MIDDLEGAME){
						valueOfState += PieceSquareTable.getValueForMiddlegame(i, j, currentPiece);
					}
				}
			}
		}
		return valueOfState;
	}
	
	public int calculateGameEndedValue(GameResult gameResult){
		if(gameResult.getWinner()==null) return 0;
		else if (gameResult.getWinner().equals(Color.WHITE)) return 1000000; 
		else return -1000000;
	}
	
	public int getValueOfPiece(Piece piece){
		int multiplier = (piece.getColor().equals(Color.WHITE))?1:-1;
		switch(piece.getKind()){
			case BISHOP:return 320*multiplier;
			case KNIGHT:return 320*multiplier;
			case PAWN:return 100*multiplier;
			case QUEEN:return 900*multiplier;
			case ROOK:return 500*multiplier;
			case KING:return 20000*multiplier;
			default:return 0;
		}
	}
	
	public void increaseMoveCount(){
		++moveCount;
	}
	
}
