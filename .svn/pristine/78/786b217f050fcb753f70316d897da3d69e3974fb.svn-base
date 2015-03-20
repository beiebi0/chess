package org.kuangchelee.hw9;

import java.util.Collections;
import java.util.List;
import com.google.common.collect.Lists;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.State;
import org.shared.chess.Move;

import org.kuangchelee.hw2.StateChangerImpl;
import org.kuangchelee.hw2_5.StateExplorerImpl;

public class AlphaBetaPruning 
{
	StateChangerImpl stateChanger = new StateChangerImpl();
	StateExplorerImpl stateExplorer = new StateExplorerImpl();
	static class TimeoutException extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}

	@SuppressWarnings("hiding")
	static class MoveScore<Move> implements Comparable<MoveScore<Move>> {
		Move move;
		double score;

		@Override
		public int compareTo(MoveScore<Move> o) {
			return (int)(o.score - score); // sort DESC (best score first)
		}
	}

	private Heuristic heuristic = new Heuristic();

	public AlphaBetaPruning() { }
	public AlphaBetaPruning(Heuristic heuristic) {
		this.heuristic = heuristic;
	}

	public Move findBestMove(State state, int depth, Timer timer) {
		boolean isWhite = state.getTurn().isWhite();
		// Do iterative deepening (A*), and slow get better heuristic values for the states.
		List<MoveScore<Move>> scores = Lists.newArrayList();
		Iterable<Move> possibleMoves = stateExplorer.getPossibleMoves(state);
		for (Move move : possibleMoves) {
			MoveScore<Move> score = new MoveScore<Move>();
			score.move = move;
			score.score = Integer.MIN_VALUE;
			scores.add(score);
		}

		try {
			for (int i = 0; i < depth; i++) {
				for (MoveScore<Move> moveScore : scores) {
					Move move = moveScore.move;
					State tmpState = state.copy();
					stateChanger.makeMove(tmpState, move);
					double score =
							findMoveScore(tmpState, i, Integer.MIN_VALUE, Integer.MAX_VALUE, timer);
					if (!isWhite) {
						// the scores are from the point of view of the white, so for black we need to switch.
						score = -score;
					}
					moveScore.score = score;
				}
				Collections.sort(scores); // This will give better pruning on the next iteration.
			}
		} catch (TimeoutException e) {
			// OK, it should happen
		}

		Collections.sort(scores);
		//Window.alert(scores.get(0).score + "");
		return scores.get(0).move;
	}

	/**
	 * If we get a timeout, then the score is invalid.
	 */
	private double findMoveScore(State state, int depth, double alpha, double beta, Timer timer)
			throws TimeoutException {
		if (timer.didTimeout()) {
			throw new TimeoutException();
		}
		GameResult over = state.getGameResult();

		if (depth == 0 || over != null) {
			return heuristic.getStateValue(state);
		}
		Color color = state.getTurn();
		int scoreSum = 0;
		int count = 0;
		Iterable<Move> possibleMoves = stateExplorer.getPossibleMoves(state);
		for (Move move : possibleMoves) {
			count++;
			State tmpState = state.copy();
			stateChanger.makeMove(tmpState, move);
			double childScore = findMoveScore(tmpState, depth - 1, alpha, beta, timer);
			if (color == null) {
				scoreSum += childScore;
			} else if (color.isWhite()) {
				alpha = Math.max(alpha, childScore);
				if (beta <= alpha) {
					break;
				}
			} else {
				beta = Math.min(beta, childScore);
				if (beta <= alpha) {
					break;
				}
			}
		}
		return color == null ? scoreSum / count : color.isWhite() ? alpha : beta;
	}
}