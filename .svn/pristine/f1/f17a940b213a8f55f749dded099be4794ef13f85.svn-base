package org.shared.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.shared.chess.Color;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;

public class PieceTest {
  Piece piece1 = new Piece(Color.WHITE, PieceKind.BISHOP);
  Piece piece1Eq = new Piece(Color.WHITE, PieceKind.BISHOP);
  Piece piece2Color = new Piece(Color.BLACK, PieceKind.BISHOP);
  Piece piece3Kind = new Piece(Color.WHITE, PieceKind.KING);
  
  @Test
  public void testEquals() {
    assertFalse(piece1.equals(null));
    assertFalse(piece1.equals(42));
    assertEquals(piece1, piece1Eq);
    assertFalse(piece1.equals(piece2Color));
    assertFalse(piece1.equals(piece3Kind));
    assertFalse(piece3Kind.equals(piece2Color));
  }

  @Test
  public void testHashCode() {
    assertEquals(piece1.hashCode(), piece1Eq.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("(W BISHOP)", piece1.toString());
  }
}
