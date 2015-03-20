package org.yoavzibinga.hw1;

import static org.shared.chess.Color.BLACK;
import static org.shared.chess.Color.WHITE;

import org.junit.Test;
import org.shared.chess.AbstractStateChangerTest;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Position;

public abstract class AbstractStateChangerIllegalMovesTest extends AbstractStateChangerTest {
  @Test(expected = IllegalMove.class)
  public void testNoPieceToMove() {
    Move move = new Move(new Position(2, 0), new Position(3, 0), null);
    stateChanger.makeMove(start, move);
  }
  
  @Test(expected = IllegalMove.class)
  public void testIllegalPawnMovement() {
    Move move = new Move(new Position(1, 0), new Position(2, 1), null);
    stateChanger.makeMove(start, move);
  }

  @Test(expected = IllegalMove.class)
  public void testIllegalRookMovement() {
    // TODO: create many more tests for all kind of illegal movement
  }

  @Test(expected = IllegalMove.class)
  public void testIllegalCastling() {
    Move move = new Move(new Position(0, 4), new Position(0, 6), null);
    stateChanger.makeMove(start, move);
  }

  @Test(expected = IllegalMove.class)
  public void testIllegalEnpassant() {
    // TODO
  }

  @Test(expected = IllegalMove.class)
  public void testIllegalCapture() {
    // TODO
  }

  @Test(expected = IllegalMove.class)
  public void testIllegalPromote() {
    // TODO
  }

  @Test(expected = IllegalMove.class)
  public void testIllegalResponseToCheck() {
    // TODO
  }

  @Test(expected = IllegalMove.class)
  public void testGameAlreadyOver() {
    start.setGameResult(new GameResult(WHITE, GameResultReason.CHECKMATE));
    Move move = new Move(new Position(1, 0), new Position(3, 0), null);
    stateChanger.makeMove(start, move);
  }

  @Test(expected = IllegalMove.class)
  public void testIllegalTurn() {
    start.setTurn(BLACK);
    // Trying to move a WHITE pawn when it is the BLACK's turn.
    Move move = new Move(new Position(1, 0), new Position(3, 0), null);
    stateChanger.makeMove(start, move);
  }
}
