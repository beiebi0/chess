package org.leozis.hw1;

import static org.junit.Assert.*;
import static org.shared.chess.Color.BLACK;
import static org.shared.chess.Color.WHITE;
import static org.shared.chess.PieceKind.*;

import org.junit.Test;
import org.shared.chess.AbstractStateChangerTest;
import org.shared.chess.Color;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;

public abstract class AbstractStateChangerCapturingTest extends AbstractStateChangerTest {
	@Test
	  public void testPawnCapturePawn_Black() {
	     Move move = new Move(new Position(4, 2), new Position(3, 1), null);
	     start.setPiece(1,2,null);
	     start.setPiece(6,2,null);
	     start.setPiece(3,1,new Piece(WHITE, PAWN));
	     start.setPiece(4,2,new Piece(BLACK, PAWN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(4, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, PAWN));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }
	  @Test
	  public void testPawnCaptureRook_Black() {
	     Move move = new Move(new Position(4, 2), new Position(3, 1), null);
	     start.setPiece(0,0,null);
	     start.setPiece(6,2,null);
	     start.setPiece(3,1,new Piece(WHITE, ROOK));
	     start.setPiece(4,2,new Piece(BLACK, PAWN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(4, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, PAWN));
	     expected.setCanCastleQueenSide(Color.WHITE, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }
	  @Test
	  public void testPawnCaptureBishop_Black() {
	     Move move = new Move(new Position(4, 2), new Position(3, 1), null);
	     start.setPiece(0,2,null);
	     start.setPiece(6,2,null);
	     start.setPiece(3,1,new Piece(WHITE, BISHOP));
	     start.setPiece(4,2,new Piece(BLACK, PAWN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(4, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, PAWN));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }
	  @Test
	  public void testPawnCaptureKnight_Black() {
	     Move move = new Move(new Position(4, 2), new Position(3, 1), null);
	     start.setPiece(0,1,null);
	     start.setPiece(6,2,null);
	     start.setPiece(3,1,new Piece(WHITE, KNIGHT));
	     start.setPiece(4,2,new Piece(BLACK, PAWN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(4, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, PAWN));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }
	  @Test
	  public void testPawnCaptureQueen_Black() {
	     Move move = new Move(new Position(4, 2), new Position(3, 1), null);
	     start.setPiece(0,3,null);
	     start.setPiece(6,2,null);
	     start.setPiece(3,1,new Piece(WHITE, QUEEN));
	     start.setPiece(4,2,new Piece(BLACK, PAWN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(4, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, PAWN));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }
	  @Test
	  public void testRookCapturePawn_Black() {
	     Move move = new Move(new Position(4, 1), new Position(3, 1), null);
	     start.setPiece(1,0,null);
	     start.setPiece(7,0,null);
	     start.setPiece(3,1,new Piece(WHITE, PAWN));
	     start.setPiece(4,1,new Piece(BLACK, ROOK));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(4, 1, null);
	     expected.setPiece(3, 1, new Piece(BLACK, ROOK));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testRookCaptureRook_Black() {
	     Move move = new Move(new Position(3, 0), new Position(3, 1), null);
	     start.setPiece(0,0,null);
	     start.setPiece(7,0,null);
	     start.setPiece(3,1,new Piece(WHITE, ROOK));
	     start.setPiece(3,0,new Piece(BLACK, ROOK));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(3, 0, null);
	     expected.setPiece(3, 1, new Piece(BLACK, ROOK));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     expected.setCanCastleQueenSide(Color.WHITE, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testRookCaptureKnight_Black() {
	     Move move = new Move(new Position(4, 1), new Position(3, 1), null);
	     start.setPiece(0,1,null);
	     start.setPiece(7,0,null);
	     start.setPiece(3,1,new Piece(WHITE, KNIGHT));
	     start.setPiece(4,1,new Piece(BLACK, ROOK));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(4, 1, null);
	     expected.setPiece(3, 1, new Piece(BLACK, ROOK));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }
	  @Test
	  public void testRookCaptureBishop_Black() {
	     Move move = new Move(new Position(3, 3), new Position(3, 1), null);
	     start.setPiece(0,2,null);
	     start.setPiece(7,0,null);
	     start.setPiece(3,1,new Piece(WHITE, BISHOP));
	     start.setPiece(3,3,new Piece(BLACK, ROOK));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(3, 3, null);
	     expected.setPiece(3, 1, new Piece(BLACK, ROOK));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }
	  @Test
	  public void testRookCaptureQueen_Black() {
	     Move move = new Move(new Position(3, 3), new Position(3, 1), null);
	     start.setPiece(0,3,null);
	     start.setPiece(7,0,null);
	     start.setPiece(3,1,new Piece(WHITE, QUEEN));
	     start.setPiece(3,3,new Piece(BLACK, ROOK));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(3, 3, null);
	     expected.setPiece(3, 1, new Piece(BLACK, ROOK));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testKnightCapturePawn_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 1), null);
	     start.setPiece(1,1,null);
	     start.setPiece(7,1,null);
	     start.setPiece(3,1,new Piece(WHITE, PAWN));
	     start.setPiece(5,2,new Piece(BLACK, KNIGHT));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, KNIGHT));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testKnightCaptureKnight_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 1), null);
	     start.setPiece(0,1,null);
	     start.setPiece(7,1,null);
	     start.setPiece(3,1,new Piece(WHITE, KNIGHT));
	     start.setPiece(5,2,new Piece(BLACK, KNIGHT));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, KNIGHT));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testKnightCaptureBishop_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 1), null);
	     start.setPiece(0,2,null);
	     start.setPiece(7,1,null);
	     start.setPiece(3,1,new Piece(WHITE, BISHOP));
	     start.setPiece(5,2,new Piece(BLACK, KNIGHT));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, KNIGHT));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testKnightCaptureQueen_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 1), null);
	     start.setPiece(0,3,null);
	     start.setPiece(7,1,null);
	     start.setPiece(3,1,new Piece(WHITE, QUEEN));
	     start.setPiece(5,2,new Piece(BLACK, KNIGHT));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 1, new Piece(BLACK, KNIGHT));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testBishopCapturePawn_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 0), null);
	     start.setPiece(1,0,null);
	     start.setPiece(7,2,null);
	     start.setPiece(3,0,new Piece(WHITE, PAWN));
	     start.setPiece(5,2,new Piece(BLACK, BISHOP));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 0, new Piece(BLACK, BISHOP));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testBishopCaptureBishop_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 0), null);
	     start.setPiece(0,2,null);
	     start.setPiece(7,2,null);
	     start.setPiece(3,0,new Piece(WHITE, BISHOP));
	     start.setPiece(5,2,new Piece(BLACK, BISHOP));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 0, new Piece(BLACK, BISHOP));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testBishopCaptureKnight_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 4), null);
	     start.setPiece(0,1,null);
	     start.setPiece(7,2,null);
	     start.setPiece(3,4,new Piece(WHITE, KNIGHT));
	     start.setPiece(5,2,new Piece(BLACK, BISHOP));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 4, new Piece(BLACK, BISHOP));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testBishopCaptureRook_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 4), null);
	     start.setPiece(0,0,null);
	     start.setPiece(7,2,null);
	     start.setPiece(3,4,new Piece(WHITE, ROOK));
	     start.setPiece(5,2,new Piece(BLACK, BISHOP));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 4, new Piece(BLACK, BISHOP));
	     expected.setCanCastleQueenSide(Color.WHITE, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testBishopCaptureQueen_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 4), null);
	     start.setPiece(0,3,null);
	     start.setPiece(7,2,null);
	     start.setPiece(3,4,new Piece(WHITE, QUEEN));
	     start.setPiece(5,2,new Piece(BLACK, BISHOP));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 4, new Piece(BLACK, BISHOP));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testQueenCapturePawn_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 0), null);
	     start.setPiece(1,1,null);
	     start.setPiece(7,3,null);
	     start.setPiece(3,0,new Piece(WHITE, PAWN));
	     start.setPiece(5,2,new Piece(BLACK, QUEEN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 0, new Piece(BLACK, QUEEN));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testQueenCaptureRook_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 4), null);
	     start.setPiece(0,0,null);
	     start.setPiece(7,3,null);
	     start.setPiece(3,4,new Piece(WHITE, ROOK));
	     start.setPiece(5,2,new Piece(BLACK, QUEEN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 4, new Piece(BLACK, QUEEN));
	     expected.setCanCastleQueenSide(Color.WHITE, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testQueenCaptureKnight_Black() {
	     Move move = new Move(new Position(5, 2), new Position(3, 4), null);
	     start.setPiece(0,1,null);
	     start.setPiece(7,3,null);
	     start.setPiece(3,4,new Piece(WHITE, KNIGHT));
	     start.setPiece(5,2,new Piece(BLACK, QUEEN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(3, 4, new Piece(BLACK, QUEEN));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testQueenCaptureBishop_Black() {
	     Move move = new Move(new Position(5, 2), new Position(2, 2), null);
	     start.setPiece(0,2,null);
	     start.setPiece(7,3,null);
	     start.setPiece(2,2,new Piece(WHITE, BISHOP));
	     start.setPiece(5,2,new Piece(BLACK, QUEEN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(2, 2, new Piece(BLACK, QUEEN));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testQueenCaptureQueen_Black() {
	     Move move = new Move(new Position(5, 2), new Position(2, 2), null);
	     start.setPiece(0,3,null);
	     start.setPiece(7,3,null);
	     start.setPiece(2,2,new Piece(WHITE, QUEEN));
	     start.setPiece(5,2,new Piece(BLACK, QUEEN));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(2, 2, new Piece(BLACK, QUEEN));
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testKingCapturePawn_Black() {
	     Move move = new Move(new Position(5, 2), new Position(4, 2), null);
	     start.setPiece(1,1,null);
	     start.setPiece(7,4,null);
	     start.setPiece(4,2,new Piece(WHITE, PAWN));
	     start.setPiece(5,2,new Piece(BLACK, KING));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(4, 2, new Piece(BLACK, KING));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     expected.setCanCastleKingSide(Color.BLACK, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testKingCaptureRook_Black() {
	     Move move = new Move(new Position(5, 2), new Position(4, 1), null);
	     start.setPiece(0,0,null);
	     start.setPiece(7,4,null);
	     start.setPiece(4,1,new Piece(WHITE, ROOK));
	     start.setPiece(5,2,new Piece(BLACK, KING));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(4, 1, new Piece(BLACK, KING));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     expected.setCanCastleKingSide(Color.BLACK, false);
	     expected.setCanCastleQueenSide(Color.WHITE, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testKingCaptureKnight_Black() {
	     Move move = new Move(new Position(5, 2), new Position(4, 2), null);
	     start.setPiece(0,1,null);
	     start.setPiece(7,4,null);
	     start.setPiece(4,2,new Piece(WHITE, KNIGHT));
	     start.setPiece(5,2,new Piece(BLACK, KING));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(4, 2, new Piece(BLACK, KING));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     expected.setCanCastleKingSide(Color.BLACK, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }

	  @Test
	  public void testKingCaptureBishop_Black() {
	     Move move = new Move(new Position(5, 2), new Position(5, 1), null);
	     start.setPiece(0,2,null);
	     start.setPiece(7,4,null);
	     start.setPiece(5,1,new Piece(WHITE, BISHOP));
	     start.setPiece(5,2,new Piece(BLACK, KING));
	     start.setTurn(BLACK);
	     State expected = start.copy();
	     expected.setTurn(WHITE);
	     expected.setPiece(5, 2, null);
	     expected.setPiece(5, 1, new Piece(BLACK, KING));
	     expected.setCanCastleQueenSide(Color.BLACK, false);
	     expected.setCanCastleKingSide(Color.BLACK, false);
	     stateChanger.makeMove(start, move);
	     assertEquals(expected, start);
	  }
}