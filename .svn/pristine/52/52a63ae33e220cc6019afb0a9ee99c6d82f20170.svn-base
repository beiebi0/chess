package org.leozis.hw2_5;

import static org.shared.chess.PieceKind.BISHOP;
import static org.shared.chess.PieceKind.KNIGHT;
import static org.shared.chess.PieceKind.QUEEN;
import static org.shared.chess.PieceKind.ROOK;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.leozis.hw2.StateChangerImpl;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

public class StateExplorerImpl implements StateExplorer{
	@Override
	public Set<Move> getPossibleMoves(State state) {
		HashSet<Move> allPossibleMoves = new HashSet<Move>();

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Set<Move> possibleMovesFromThisPosition = this.getPossibleMovesFromPosition(state, new Position(i,j));

				if(!possibleMovesFromThisPosition.isEmpty()){
					Iterator<Move> itr = possibleMovesFromThisPosition.iterator();

					while(itr.hasNext()){
						allPossibleMoves.add(itr.next());
					}
				}

				possibleMovesFromThisPosition.clear();
			}

		}

		return allPossibleMoves;
	}

	@Override
	public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
		StateChangerImpl stateChanger = new StateChangerImpl();
		HashSet<Move> possibleMoves = new HashSet<Move>();

		if (start == null) {
			return possibleMoves;
		}
		if (state.getPiece(start) == null) {
			return possibleMoves;
		}

		Piece piece = state.getPiece(start);
		Color color = piece.getColor();
		Boolean isWhite = color.equals(Color.WHITE);
		PieceKind kinds[] = {QUEEN, ROOK, BISHOP, KNIGHT};

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Move move = new Move(start, new Position(i,j), null);
				if(piece.getKind() == PieceKind.PAWN){
					for (int k = 0; k < 4; k++) {
						if(i == (isWhite ? 7 : 0)){
							move = new Move(start, new Position(i,j), kinds[k]);
							if(stateChanger.isPossibleMove(state,move)){
								possibleMoves.add(move);
							}
						}
					}
				}

				if(stateChanger.isPossibleMove(state,move)){
					possibleMoves.add(move);
				}
			}
		}
		return possibleMoves;
	}

	@Override
	public Set<Position> getPossibleStartPositions(State state) {
		Set<Move> moves = getPossibleMoves(state);
		HashSet<Position> startPositions = new HashSet<Position>();
		for (Move move : moves) {
			startPositions.add(move.getFrom());
		}
		return startPositions;
	}
}
