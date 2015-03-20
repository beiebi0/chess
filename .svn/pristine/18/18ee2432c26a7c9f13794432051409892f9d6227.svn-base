package org.shared.chess;

import java.util.Set;

import org.junit.Before;

public abstract class AbstractStateExplorerTest {
  protected State start;
  protected StateExplorer stateExplorer;

  public abstract StateExplorer getStateExplorer();

  @Before
  public void setup() {
    start = new State();
    final StateExplorer impl = getStateExplorer();
    stateExplorer = new StateExplorer() {
      @Override
      public Set<Move> getPossibleMoves(State state) {
        AbstractStateChangerTest.assertStatePossible(state);
        return impl.getPossibleMoves(state);
      }

      @Override
      public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
        AbstractStateChangerTest.assertStatePossible(state);
        return impl.getPossibleMovesFromPosition(state, start);
      }

      @Override
      public Set<Position> getPossibleStartPositions(State state) {
        AbstractStateChangerTest.assertStatePossible(state);
        return impl.getPossibleStartPositions(state);
      }
    };
  }
}
