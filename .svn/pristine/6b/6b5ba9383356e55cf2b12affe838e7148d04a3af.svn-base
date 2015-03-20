package org.bohouli.hw9;

import java.util.Collections;
import java.util.List;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.bohouli.hw2.StateChangerImpl;

import static org.shared.chess.Color.*;
import com.google.common.collect.Lists;

public class AlphaBetaPruning {
	private StateChanger stateChanger = new StateChangerImpl();

	static class TimeoutException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	static class MoveScore implements Comparable<MoveScore> {
		Move move;
		int score;

		@Override
		public int compareTo(MoveScore o) {
			return o.score - score; // sort DESC (best score first)
		}
	}

	private BohouHeuristic heuristic;

	public AlphaBetaPruning(BohouHeuristic heuristic) {
		this.heuristic = heuristic;
	}

	public Move findBestMove(State state, int depth, BohouTimer timer) {
		boolean isWhite = state.getTurn() == WHITE;

		// Do iterative deepening (A*), and slow get better heuristic values for
		// the states.
		List<MoveScore> scores = Lists.newArrayList();
		{
			Iterable<Move> possibleMoves = heuristic.getOrderedMoves(state);
			for (Move move : possibleMoves) {
				MoveScore score = new MoveScore();
				score.move = move;
				score.score = Integer.MIN_VALUE;
				scores.add(score);
			}
		}

		try {
			for (int i = 0; i < depth; i++) {
				for (MoveScore moveScore : scores) {
					Move move = moveScore.move;
					State tmp = state.copy();
					stateChanger.makeMove(tmp, move);
					int score = findMoveScore(tmp, i, Integer.MIN_VALUE, Integer.MAX_VALUE, timer);

					if (!isWhite) {
						score = -score;
					}
					moveScore.score = score;
				}
				Collections.sort(scores);
			}
		} catch (TimeoutException e) {

		}

		Collections.sort(scores);
		return scores.get(0).move;
	}

	/**
	 * If we get a timeout, then the score is invalid.
	 */
	private int findMoveScore(State state, int depth, int alpha, int beta,
			BohouTimer timer) throws TimeoutException {

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
		Iterable<Move> possibleMoves = heuristic.getOrderedMoves(state);
		
		for (Move move : possibleMoves) {
			count++;
			State tmp = state.copy();
			stateChanger.makeMove(tmp, move);
			int childScore = findMoveScore(tmp, depth - 1, alpha, beta, timer);
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