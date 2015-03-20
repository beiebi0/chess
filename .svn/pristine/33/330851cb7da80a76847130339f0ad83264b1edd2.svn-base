package org.shared.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StateTest {
  State start;
  State startEq;

  @Before
  public void setup() {
    start = new State();
    startEq = new State();
  }
  
  @Test
  public void testEquals() {
    assertFalse(start.equals(null));
    assertFalse(start.equals(42));
    assertEquals(start, startEq);
    startEq.setGameResult(new GameResult(Color.WHITE, GameResultReason.CHECKMATE));
    assertFalse(start.equals(startEq));
    State differentState1 = new State();
    differentState1.setCanCastleKingSide(Color.WHITE, false);
    assertFalse(start.equals(differentState1));
    State differentState2 = new State();
    differentState2.setCanCastleQueenSide(Color.WHITE, false);
    assertFalse(start.equals(differentState2));
    State differentState3 = new State();
    differentState3.setPiece(0, 0, null);
    assertFalse(start.equals(differentState3));
  }

  @Test
  public void testHashCode() {
    assertEquals(start.hashCode(), startEq.hashCode());
    startEq.setGameResult(new GameResult(Color.WHITE, GameResultReason.CHECKMATE));
    assertTrue(start.hashCode() != startEq.hashCode());
  }

  @Test
  public void testCopy() {
    start.setPiece(5, 5, new Piece(Color.WHITE, PieceKind.KNIGHT));
    State startCopy = start.copy();
    assertEquals(start, startCopy);
    startCopy.setPiece(0, 0, null);
    assertFalse(start.equals(startCopy));
  }

  @Test
  public void testCopyOfCanCastleKingSide() {
    State startCopy = start.copy();
    startCopy.setCanCastleKingSide(Color.WHITE, false);
    assertFalse(start.equals(startCopy));
  }
  
  @Test
  public void testToString() {
    assertEquals("State [turn=W, board=[" +
		"[(W ROOK), (W KNIGHT), (W BISHOP), (W QUEEN), (W KING), (W BISHOP), (W KNIGHT), (W ROOK)], " +
        "[(W PAWN), (W PAWN), (W PAWN), (W PAWN), (W PAWN), (W PAWN), (W PAWN), (W PAWN)], " +
		"[null, null, null, null, null, null, null, null], " +
		"[null, null, null, null, null, null, null, null], " +
		"[null, null, null, null, null, null, null, null], " +
		"[null, null, null, null, null, null, null, null], " +
        "[(B PAWN), (B PAWN), (B PAWN), (B PAWN), (B PAWN), (B PAWN), (B PAWN), (B PAWN)], " +
		"[(B ROOK), (B KNIGHT), (B BISHOP), (B QUEEN), (B KING), (B BISHOP), (B KNIGHT), (B ROOK)]], " +
		"canCastleKingSide=[true, true], canCastleQueenSide=[true, true], " +
		"numberOfMovesWithoutCaptureNorPawnMoved=0]", start.toString());
    start.setGameResult(new GameResult(Color.WHITE, GameResultReason.CHECKMATE));
    assertEquals("State [turn=W, board=[" +
        "[(W ROOK), (W KNIGHT), (W BISHOP), (W QUEEN), (W KING), (W BISHOP), (W KNIGHT), (W ROOK)], " +
        "[(W PAWN), (W PAWN), (W PAWN), (W PAWN), (W PAWN), (W PAWN), (W PAWN), (W PAWN)], " +
        "[null, null, null, null, null, null, null, null], " +
        "[null, null, null, null, null, null, null, null], " +
        "[null, null, null, null, null, null, null, null], " +
        "[null, null, null, null, null, null, null, null], " +
        "[(B PAWN), (B PAWN), (B PAWN), (B PAWN), (B PAWN), (B PAWN), (B PAWN), (B PAWN)], " +
        "[(B ROOK), (B KNIGHT), (B BISHOP), (B QUEEN), (B KING), (B BISHOP), (B KNIGHT), (B ROOK)]], " +
        "canCastleKingSide=[true, true], canCastleQueenSide=[true, true], " +
        "gameResult=GameResult [winner=W, gameResultReason=CHECKMATE], " +
        "numberOfMovesWithoutCaptureNorPawnMoved=0]", start.toString());
  }
}
