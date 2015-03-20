package org.simongellis.hw9;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;

import org.shared.chess.Move;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;
import org.simongellis.hw2.StateChangerImpl;
import org.simongellis.hw2_5.StateExplorerImpl;

public class AlphaBetaPruning {
	private class Node {
		Node(State state, Move move, int alpha, int beta) {
			this.state = state;
			this.move = move;
			this.utility = moveRater.heuristic(state);
			this.alpha = alpha;
			this.beta = beta;
		}

		Node(Move move, int utility, int alpha, int beta) {
			this.state = null;
			this.move = move;
			this.utility = utility;
			this.alpha = alpha;
			this.beta = beta;
		}

		State state;
		Move move;
		int utility;
		int alpha, beta;
	}

	private class MoveRater implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.utility < o2.utility) {
				return 1;
			} else if (o1.utility > o2.utility) {
				return -1;
			} else {
				int firstRating = Math.min(o1.move.getTo().getCol(), 7 - o1.move.getTo().getCol());
				int secondRating = Math.min(o2.move.getTo().getCol(), 7 - o2.move.getTo().getCol());
				if (o1.state.getTurn().isWhite())
					return (firstRating - secondRating);
				else
					return (secondRating - firstRating);
			}
		}

		private int[] pieceValues = { 0, 9, 5, 3, 3, 1 };

		public int heuristic(State state) {
			if (state.getGameResult() != null) {
				if (state.getGameResult().isDraw())
					return 0;
				else if (state.getGameResult().getWinner().isWhite())
					return Integer.MAX_VALUE - 1;
				else
					return Integer.MIN_VALUE + 1;
			}
			int result = 0;
			for (int row = 0; row < State.ROWS; ++row) {
				for (int col = 0; col < State.COLS; ++col) {
					if (state.getPiece(row, col) != null) {
						int value = pieceValues[state.getPiece(row, col).getKind().ordinal()];
						if (state.getPiece(row, col).getColor().isWhite())
							result += value;
						else
							result -= value;
					}
				}
			}
			return result;
		}
	}

	private StateChanger stateChanger = new StateChangerImpl();
	private StateExplorer stateExplorer = new StateExplorerImpl();
	private MoveRater moveRater = new MoveRater();
	private static final int MAX_DEPTH = 4;

	public Move bestMove(State state) {
		Move move = null;
		LinkedList<Move[]> killerMoves = new LinkedList<Move[]>();
		for (int i = 1; i <= MAX_DEPTH; ++i) {
			killerMoves.addFirst(new Move[2]);
			//System.out.println("Minimax search to depth " + i + " begun.");
			int debugValues[] = new int[] { 0, 0 };
			move = bestMove(state, 1, i, Integer.MIN_VALUE, Integer.MAX_VALUE, debugValues,
					killerMoves).move;
			//System.out.println("Move: " + move);
			//System.out.println("Visited " + debugValues[0] + ", pruned " + debugValues[1]);
		}
		return move;
	}

	private Node bestMove(State state, int depth, int targetDepth, int alpha, int beta,
			int debugValues[], LinkedList<Move[]> killerMoves) {
		++debugValues[0];
		
		if (state.getGameResult() != null || depth >= targetDepth) {
			return new Node(null, moveRater.heuristic(state), alpha, beta);
		}
		
		boolean playingForWhite = state.getTurn().isWhite();
		Set<Move> moves = stateExplorer.getPossibleMoves(state);
		Move[] killers = killerMoves.get(depth - 1);
		LinkedList<Node> killerMovesForThisState = new LinkedList<Node>();
		LinkedList<Node> information = new LinkedList<Node>();
		
		for (Move move : moves) {
			State newState = state.copy();
			stateChanger.makeMove(newState, move);
			if (move.equals(killers[0]))
				killerMovesForThisState.addFirst(new Node(newState, move, alpha, beta));
			else if (move.equals(killers[1]))
				killerMovesForThisState.addLast(new Node(newState, move, alpha, beta));
			else
				information.add(new Node(newState, move, alpha, beta));
		}
		
		Node bestSoFar;
		
		if (playingForWhite) {
			Collections.sort(information, moveRater);
			information.addAll(0, killerMovesForThisState);
			bestSoFar = new Node(null, Integer.MIN_VALUE, alpha, beta);
			
			for (Node moveInfo : information) {
				Node bestInThisBranch = bestMove(moveInfo.state, depth + 1, targetDepth, alpha,
						beta, debugValues, killerMoves);
				alpha = bestInThisBranch.alpha;
				beta = bestInThisBranch.beta;
				
				if (bestInThisBranch.utility > bestSoFar.utility) {
					bestSoFar = moveInfo;
					bestSoFar.utility = bestInThisBranch.utility;
				}
				
				if (bestSoFar.utility > beta || bestSoFar.utility == Integer.MAX_VALUE - 1) {
					++debugValues[1];
					if (killers[0] == null)
						killers[0] = bestSoFar.move;
					else if (killers[1] == null && !(bestSoFar.move.equals(killers[0])))
						killers[1] = bestSoFar.move;
					return bestSoFar;
				}
				
				if (bestSoFar.utility > alpha)
					alpha = bestSoFar.utility;
			}
		} else {
			Collections.sort(information, Collections.reverseOrder(moveRater));
			information.addAll(0, killerMovesForThisState);
			bestSoFar = new Node(null, Integer.MAX_VALUE, alpha, beta);
			
			for (Node moveInfo : information) {
				Node bestInThisBranch = bestMove(moveInfo.state, depth + 1, targetDepth, alpha,
						beta, debugValues, killerMoves);
				alpha = bestInThisBranch.alpha;
				beta = bestInThisBranch.beta;

				if (bestInThisBranch.utility < bestSoFar.utility) {
					bestSoFar = moveInfo;
					bestSoFar.utility = bestInThisBranch.utility;
				}
				
				if (bestSoFar.utility < alpha || bestSoFar.utility == Integer.MIN_VALUE + 1) {
					++debugValues[1];
					if (killers[0] != null)
						killers[0] = bestSoFar.move;
					else if (killers[1] != null)
						killers[1] = bestSoFar.move;
					return bestSoFar;
				}
				
				if (bestSoFar.utility < beta)
					beta = bestSoFar.utility;
			}
		}

		return bestSoFar;
	}
}
