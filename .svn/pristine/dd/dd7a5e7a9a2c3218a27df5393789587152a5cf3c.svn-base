package org.zhaohuizhang.hw9;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;
import org.zhaohuizhang.hw2.StateChangerImpl;
import org.zhaohuizhang.hw2_5.StateExplorerImpl;

// need to be changed
public class HeuristicImpl implements Heuristic {

	@Override
	public int getStateValue(State state) {
		int stateValue = 0;

		if (state.getGameResult() != null
				&& state.getGameResult().getWinner() == null) {
			return 0;
		}

		if (state.getGameResult() != null
				&& state.getGameResult().getWinner() != null) {
			Color winner = state.getGameResult().getWinner();

			if (winner.isWhite()) {
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}
		}

		for (int i = 0; i < State.ROWS; i++) {
			for (int j = 0; j < State.COLS; j++) {
				Piece piece = state.getPiece(i, j);
				stateValue += PieceValue.getPieceValue(piece, i, j);
			}
		}

		return stateValue;
	}

	@Override
	public Iterable<Move> getOrderedMoves(State state) {
		StateExplorer stateExplorer = new StateExplorerImpl();
		StateChanger stateChanger = new StateChangerImpl();

		Set<Move> possibleMoves = stateExplorer.getPossibleMoves(state);
		if(possibleMoves.size() == 0){
			return null;
		}
		LinkedList<Move> orderedMoves = new LinkedList<Move>();
		Map<Move, Integer> tmpMap = new HashMap<Move, Integer>();

		for(Move move:possibleMoves){
			State fakeState = state.copy();
			stateChanger.makeMove(fakeState, move);
			int stateValue = getStateValue(fakeState);
			tmpMap.put(move, stateValue);
		}
		
		Map<Move, Integer> sortedMap = sortByComparator(tmpMap);
		
		for (Map.Entry<Move, Integer> entry : sortedMap.entrySet()) {
			orderedMoves.add(entry.getKey());
		}
		return orderedMoves;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map<Move, Integer> sortByComparator(Map<Move, Integer> unsortMap){
		List list = new LinkedList(unsortMap.entrySet());
		 
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		
		Map sortedMap = new LinkedHashMap();
		
		for (
		Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}


	

}
