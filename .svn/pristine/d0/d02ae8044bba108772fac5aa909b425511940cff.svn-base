package org.ashishmanral.hw9;

import java.util.Collections;
import java.util.List;

import org.ashishmanral.hw2.StateChangerImpl;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.State;

import com.google.common.collect.Lists;

/**
 * AlphaBetaPruning.java 
 * @author Ashish
 */
public class AlphaBetaPruning {
  
  static class TimeoutException extends RuntimeException {
    private static final long serialVersionUID = 1L;
  }

  private Heuristic heuristic;
  private StateChangerImpl stateChanger;

  /**
   * Constructor
   */
  public AlphaBetaPruning() {
    this.heuristic = new Heuristic();
    this.stateChanger = new StateChangerImpl();
  }

  /**
   * Finds the best move for AI.
   * @param state
   * @param depth
   * @param timer
   * @return
   */
  public Move findBestMove(State state, int depth, Timer timer) {
    boolean isAIWhite = state.getTurn().isWhite();
    // Do iterative deepening (A*), and slow get better heuristic values for the states.
    List<MoveScore> scores = Lists.newArrayList();
    {
      Iterable<Move> possibleMoves = heuristic.getOrderedMoves(state);
      for (Move move : possibleMoves) {
        MoveScore score = new MoveScore(move, Integer.MIN_VALUE);
        scores.add(score);
      }
    }

    try {
      for (int i = 0; i < depth; i++) {
        for (MoveScore moveScore : scores) {
          State stateCopy = state.copy();
          stateChanger.makeMove(stateCopy, moveScore.getMove());
          int score = findMoveScore(stateCopy, i, Integer.MIN_VALUE, Integer.MAX_VALUE, timer);
          if (!isAIWhite) {
            // the scores are from the point of view of the white, so for black we need to switch.
            score = -score;
          }
          moveScore.setScore(score);
        }
        Collections.sort(scores); // This will give better pruning on the next iteration.
      }
    } catch (TimeoutException e) {
    	Collections.sort(scores);
    	ChosenMove chosenMove = new ChosenMove();
    	for(MoveScore moveScore : scores){
        	chosenMove.populate(moveScore);
        }
    	heuristic.increaseMoveCount();
		return chosenMove.getChosenMove().getMove();
    }
    Collections.sort(scores);
    ChosenMove chosenMove = new ChosenMove();
	for(MoveScore moveScore : scores){
    	chosenMove.populate(moveScore);
    }
	return chosenMove.getChosenMove().getMove();
  }

  /**
   * Finds out the score for a move.
   * @param state
   * @param depth
   * @param alpha
   * @param beta
   * @param timer
   * @return
   * @throws TimeoutException
   */
  private int findMoveScore(State state, int depth, int alpha, int beta, Timer timer)
      throws TimeoutException {
	if (timer.didTimeout()) {
      throw new TimeoutException();
    }
    if (depth == 0 || state.getGameResult() != null) {
      return heuristic.getValueOfState(state);
    }
    Color playerColor = state.getTurn();
    int scoreSum = 0;
    int count = 0;
    Iterable<Move> possibleMoves = heuristic.getOrderedMoves(state);
    for (Move move : possibleMoves) {
      count++;
      State stateCopy = state.copy();
      stateChanger.makeMove(stateCopy, move);
      int childScore = findMoveScore(stateCopy, depth - 1, alpha, beta, timer);
      if (playerColor == null) {
        scoreSum += childScore;
      } else if (playerColor.isWhite()) {
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
    return playerColor == null ? scoreSum / count : playerColor.isWhite() ? alpha : beta;
  }
}
