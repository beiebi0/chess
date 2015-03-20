package org.vorasahil.hw9;

//Copyright 2012 Google Inc.
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
////////////////////////////////////////////////////////////////////////////////

import java.util.Collections;
import java.util.List;

import org.vorasahil.hw2.StateChangerImpl;
import org.shared.chess.*;

import com.google.common.collect.Lists;

/**
 * http://en.wikipedia.org/wiki/Alpha-beta_pruning<br>
 * This algorithm performs both A* and alpha-beta pruning.<br>
 * The set of possible moves is maintained ordered by the current heuristic
 * value of each move. We first use depth=1, and update the heuristic value of
 * each move, then use depth=2, and so on until we get a timeout or reach
 * maximum depth. <br>
 * If a state has {@link TurnBasedState#whoseTurn} null (which happens in
 * backgammon when we should roll the dice), then I treat all the possible moves
 * with equal probabilities. <br>
 * 
 * @author yzibin@google.com (Yoav Zibin)
 */
public class AlphaBetaPruning {
	static class TimeoutException extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}

	static class MoveScore<Move> implements Comparable<MoveScore<Move>> {
		Move move;
		int score;

		@Override
		public int compareTo(MoveScore<Move> o) {
			return o.score - score; // sort DESC (best score first)
		}
	}

	private Heuristic heuristic;
	private StateChangerImpl stateChangerImpl;

	public AlphaBetaPruning(Heuristic heuristic) {
		this.heuristic = heuristic;
		stateChangerImpl = new StateChangerImpl();
	}

	public Move findBestMove(State state, int depth, Timer timer) {
		boolean isWhite = state.getTurn().isWhite();
		// Do iterative deepening (A*), and slow get better heuristic values for
		// the states.
		List<MoveScore<Move>> scores = Lists.newArrayList();
		{
			Iterable<Move> possibleMoves = heuristic.getOrderedMoves(state);
			for (Move move : possibleMoves) {
				MoveScore<Move> score = new MoveScore<Move>();
				score.move = move;
				score.score = Integer.MIN_VALUE;
				scores.add(score);
			}
		}

		try {
			for (int i = 0; i < depth; i++) {
				for (MoveScore<Move> moveScore : scores) {
					Move move = moveScore.move;
					State thisState = state.copy();
					stateChangerImpl.makeMove(thisState, move);
					int score = findMoveScore(thisState, i, Integer.MIN_VALUE,
							Integer.MAX_VALUE, timer);
					if (!isWhite) {
						// the scores are from the point of view of the white,
						// so for black we need to switch.
						score = -score;
					}
					moveScore.score = score;
				}
				Collections.sort(scores); // This will give better pruning on
											// the next iteration.
			}
		} catch (TimeoutException e) {
			// OK, it should happen
			Collections.sort(scores);
			return scores.get(0).move;
		}

		Collections.sort(scores);
		return scores.get(0).move;
	}

	/**
	 * If we get a timeout, then the score is invalid.
	 */
	private int findMoveScore(State state, int depth, int alpha, int beta,
			Timer timer) throws TimeoutException {
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
			State thisState = state.copy();
			stateChangerImpl.makeMove(thisState, move);
			int childScore = findMoveScore(thisState, depth - 1, alpha, beta,
					timer);
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
		return color == null ? scoreSum / count : color.isWhite() ? alpha
				: beta;
	}
	
	public static void main(String arg[]){
		Heuristic h = new Heuristic();
		AlphaBetaPruning abp = new AlphaBetaPruning(h);
		State state = new State();
		
		state.setPiece(1, 0, null);
		state.setPiece(3, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(6, 1, null);
		state.setPiece(4, 1, new Piece(Color.BLACK, PieceKind.PAWN));
		
		Move move = abp.findBestMove(state, 50, new Timer(40000));
		System.out.println(move);
	}
}
