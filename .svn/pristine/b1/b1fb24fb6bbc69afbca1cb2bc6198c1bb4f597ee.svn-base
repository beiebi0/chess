package org.shared.chess;

import org.junit.Before;

public abstract class AbstractStateChangerTest {
  protected State start;
  protected StateChanger stateChanger;

  public abstract StateChanger getStateChanger();
  
  @Before
  public void setup() {
    start = new State();
    final StateChanger impl = getStateChanger();
    stateChanger = new StateChanger() {
      @Override
      public void makeMove(State state, Move move) throws IllegalMove {
        assertStatePossible(state);
        impl.makeMove(state, move);
      }
    };
  }

  public static void assertStatePossible(State state) {
    int[][] piecesCount = new int[2][PieceKind.values().length];
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        Piece piece = state.getPiece(r, c);
        if (piece == null) {
          continue;
        }
        piecesCount[piece.getColor().ordinal()][piece.getKind().ordinal()]++;
      }
    }
    for (Color color : Color.values()) {
      check(piecesCount[color.ordinal()][PieceKind.KING.ordinal()] == 1, "Yoav: You must have exactly one king");
      int promotedCount = 0;
      promotedCount += Math.max(0, piecesCount[color.ordinal()][PieceKind.QUEEN.ordinal()] - 1);
      promotedCount += Math.max(0, piecesCount[color.ordinal()][PieceKind.ROOK.ordinal()] - 2);
      promotedCount += Math.max(0, piecesCount[color.ordinal()][PieceKind.KNIGHT.ordinal()] - 2);
      promotedCount += Math.max(0, piecesCount[color.ordinal()][PieceKind.BISHOP.ordinal()] - 2);
      check(piecesCount[color.ordinal()][PieceKind.PAWN.ordinal()] + promotedCount <= 8, "Yoav: You promoted too many pieces, you need to remove some pawns");      
    }
  }
  
  private static void check(boolean condition, String message) {
    if (!condition) {
      throw new RuntimeException(message);
    }
  }
}
