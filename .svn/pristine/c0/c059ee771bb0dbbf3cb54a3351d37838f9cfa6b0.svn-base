package org.bohouli.hw2_5;

import java.util.Set;

import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;
import org.shared.chess.Color;
import org.bohouli.hw2.*;

import com.google.common.collect.Sets;

public class StateExplorerImpl implements StateExplorer {

	@Override
	public Set<Move> getPossibleMoves(State state) {
		// TODO Auto-generated method stub
		Set<Move> moves = Sets.newHashSet();
		Set<Position> positions = getPossibleStartPositions(state);
		for(Position pos : positions) {
			moves.addAll(getPossibleMovesFromPosition(state, pos));
		}
		
		return moves;
	}

	@Override
	public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
		// TODO Auto-generated method stub
		StateChangerImpl stateChanger = new StateChangerImpl();
		if(state.getPiece(start) == null ||
				state.getPiece(start).getColor() != state.getTurn())
			return Sets.newHashSet();
		return stateChanger.getPossibleMoves(state, start);
	}

	@Override
	public Set<Position> getPossibleStartPositions(State state) {
		// TODO Auto-generated method stub
		StateChangerImpl stateChanger = new StateChangerImpl();
		Set<Position> positions = Sets.newHashSet();
		if(state.getGameResult() != null)
			return positions;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Position start = new Position(i, j);
				Piece cur = state.getPiece(start);
				Color color = state.getTurn();
				if (cur != null && cur.getColor() == color) {
					Set<Move> move = stateChanger.getPossibleMoves(state, start);
					if(!move.isEmpty())
						positions.add(start);
				}
			}
		}
		return positions;
	}
}
