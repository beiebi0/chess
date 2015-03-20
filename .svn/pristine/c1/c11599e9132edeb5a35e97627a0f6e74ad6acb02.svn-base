package org.yoavzibin.hw1;

import static org.junit.Assert.assertEquals;
import static org.shared.chess.Color.BLACK;
import static org.shared.chess.Color.WHITE;
import static org.shared.chess.PieceKind.PAWN;

import org.junit.Test;
import org.shared.chess.AbstractStateChangerTest;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;

public abstract class AbstractStateChangerPawnAndKingTest extends AbstractStateChangerTest {
  @Test
  public void testPawnCanMoveTwoSquares() {
    Move move = new Move(new Position(1, 0), new Position(3, 0), null);
    State expected = start.copy();
    expected.setTurn(BLACK);
    expected.setPiece(1, 0, null);
    expected.setPiece(3, 0, new Piece(WHITE, PAWN));
    expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
    expected.setEnpassantPosition(new Position(3, 0));
    stateChanger.makeMove(start, move);
    assertEquals(expected, start);
  }
}
