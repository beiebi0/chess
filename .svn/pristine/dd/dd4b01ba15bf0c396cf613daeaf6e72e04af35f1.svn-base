package org.shared.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.shared.chess.Color.BLACK;
import static org.shared.chess.Color.WHITE;
import static org.shared.chess.PieceKind.BISHOP;
import static org.shared.chess.PieceKind.KING;
import static org.shared.chess.PieceKind.KNIGHT;
import static org.shared.chess.PieceKind.PAWN;
import static org.shared.chess.PieceKind.QUEEN;
import static org.shared.chess.PieceKind.ROOK;
import static org.shared.chess.State.COLS;
import static org.shared.chess.State.ROWS;

import org.junit.Test;

public abstract class AbstractStateChangerAllTest extends
AbstractStateChangerTest {



	/*
	 * Begin Tests by Yoav Zibin <yoav.zibin@gmail.com>
	 */


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
	public void testIllegalCastling() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		stateChanger.makeMove(start, move);
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

	/*
	 * End Tests by Yoav Zibin <yoav.zibin@gmail.com>
	 */

	/*
	 * Begin Tests by Simon Gellis <simongellis@gmail.com>
	 */
	@Test(expected = IllegalMove.class)
	public void testWrongPlayerMoving() {
		start.setTurn(Color.BLACK);
		Move move = new Move(new Position(1, 1), new Position(2, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testMovingNonexistantPiece() {
		start.setTurn(Color.BLACK);
		Move move = new Move(new Position(4, 4), new Position(3, 4), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalBlackPawnMovement() {
		start.setTurn(Color.BLACK);
		Move move = new Move(new Position(6, 1), new Position(5, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalRookMovement() {
		start.setTurn(Color.BLACK);
		start.setPiece(6, 5, null);
		Move move = new Move(new Position(7, 7), new Position(5, 5), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalBishopMovement() {
		start.setTurn(Color.BLACK);
		start.setPiece(6, 5, null);
		Move move = new Move(new Position(7, 5), new Position(5, 5), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKnightMovement() {
		start.setTurn(Color.BLACK);
		Move move = new Move(new Position(7, 6), new Position(4, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalQueenMovement() {
		start.setTurn(Color.BLACK);
		start.setPiece(6, 3, null);
		start.setPiece(6, 2, null);
		start.setPiece(6, 1, null);
		start.setPiece(7, 2, null);
		start.setPiece(7, 1, null);
		Move move = new Move(new Position(7, 3), new Position(6, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKingMovement() {
		start.setTurn(Color.BLACK);
		start.setPiece(6, 4, null);
		Move move = new Move(new Position(7, 4), new Position(5, 4), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalMoveThroughSameColorPiece() {
		start.setTurn(Color.BLACK);
		Move move = new Move(new Position(7, 7), new Position(5, 7), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testMovingAfterFiftyMoveRule() {
		start.setTurn(Color.BLACK);
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		start.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));
		stateChanger.makeMove(start, new Move(new Position(7, 1), new Position(
				5, 2), null));
	}

	@Test(expected = IllegalMove.class)
	public void testMovingAfterCheckmate() {
		Piece[][] board = new Piece[8][8];
		board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
		board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
		board[6][4] = new Piece(Color.WHITE, PieceKind.QUEEN);
		board[5][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
		start = new State(Color.BLACK, board, new boolean[] { true, true },
				new boolean[] { true, true }, null, 0, new GameResult(
						Color.WHITE, GameResultReason.CHECKMATE));
		stateChanger.makeMove(start, new Move(new Position(7, 4), new Position(
				7, 3), null));
	}

	@Test(expected = IllegalMove.class)
	public void testMovingAfterStalemate() {
		Piece[][] board = new Piece[8][8];
		board[7][7] = new Piece(Color.BLACK, PieceKind.KING);
		board[6][5] = new Piece(Color.WHITE, PieceKind.KING);
		board[5][6] = new Piece(Color.WHITE, PieceKind.QUEEN);
		start = new State(Color.BLACK, board, new boolean[] { true, true },
				new boolean[] { true, true }, null, 0, new GameResult(null,
						GameResultReason.STALEMATE));
		stateChanger.makeMove(start, new Move(new Position(7, 4), new Position(
				7, 3), null));
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastlingWhenPiecesAreInTheWay() {
		start.setTurn(Color.BLACK);
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastlingWhenRookHasBeenMoved() {
		Piece[][] board = new Piece[8][8];
		board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
		board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
		board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
		start = new State(Color.BLACK, board, new boolean[] { true, true },
				new boolean[] { true, true }, null, 0, null);
		stateChanger.makeMove(start, new Move(new Position(7, 7), new Position(
				6, 7), null));
		stateChanger.makeMove(start, new Move(new Position(0, 4), new Position(
				1, 4), null));
		stateChanger.makeMove(start, new Move(new Position(6, 7), new Position(
				7, 7), null));
		stateChanger.makeMove(start, new Move(new Position(1, 4), new Position(
				0, 4), null));
		assertEquals(start.isCanCastleKingSide(Color.BLACK), false);
		stateChanger.makeMove(start, new Move(new Position(7, 4), new Position(
				7, 6), null));
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastlingWhenKingHasBeenMoved() {
		Piece[][] board = new Piece[8][8];
		board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
		board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
		board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
		start = new State(Color.BLACK, board, new boolean[] { true, true },
				new boolean[] { true, true }, null, 0, null);
		stateChanger.makeMove(start, new Move(new Position(7, 4), new Position(
				6, 4), null));
		stateChanger.makeMove(start, new Move(new Position(0, 4), new Position(
				1, 4), null));
		stateChanger.makeMove(start, new Move(new Position(6, 4), new Position(
				7, 4), null));
		stateChanger.makeMove(start, new Move(new Position(1, 4), new Position(
				0, 4), null));
		assertEquals(start.isCanCastleQueenSide(Color.BLACK), false);
		assertEquals(start.isCanCastleKingSide(Color.BLACK), false);
		stateChanger.makeMove(start, new Move(new Position(7, 4), new Position(
				7, 6), null));
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPawnMovingTwoSquaresForwardAfterLeavingStartPosition() {
		start.setTurn(Color.BLACK);
		stateChanger.makeMove(start, new Move(new Position(7, 0), new Position(6, 0), null));
		stateChanger.makeMove(start, new Move(new Position(1, 0), new Position(2, 0), null));
		stateChanger.makeMove(start, new Move(new Position(6, 0), new Position(4, 0), null));
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCaptureOfSameColorPiece() {
		start.setTurn(Color.BLACK);
		start.setPiece(6, 2, null);
		start.setPiece(5, 2, new Piece(Color.BLACK, PieceKind.PAWN));
		Move move = new Move(new Position(6, 1), new Position(5, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionWhenNotAtEighthRank() {
		start.setTurn(Color.BLACK);
		Move move = new Move(new Position(6, 1), new Position(5, 1),
				PieceKind.QUEEN);
		stateChanger.makeMove(start, move);
		assertFalse(start.getPiece(5, 1).getKind() == PieceKind.QUEEN);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionToKing() {
		Piece[][] board = new Piece[8][8];
		board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
		board[6][4] = new Piece(Color.BLACK, PieceKind.KING);
		board[1][7] = new Piece(Color.BLACK, PieceKind.PAWN);
		start = new State(Color.BLACK, board, new boolean[] { true, true },
				new boolean[] { true, true }, null, 0, null);
		Move move = new Move(new Position(1, 7), new Position(0, 7),
				PieceKind.KING);
		stateChanger.makeMove(start, move);
		assertFalse(start.getPiece(0, 7).getKind() == PieceKind.KING);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionOfRook() {
		Piece[][] board = new Piece[8][8];
		board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
		board[6][4] = new Piece(Color.BLACK, PieceKind.KING);
		board[1][7] = new Piece(Color.BLACK, PieceKind.ROOK);
		start = new State(Color.BLACK, board, new boolean[] { true, true },
				new boolean[] { true, true }, null, 0, null);
		Move move = new Move(new Position(1, 7), new Position(0, 7),
				PieceKind.QUEEN);
		stateChanger.makeMove(start, move);
		assertFalse(start.getPiece(0, 7).getKind() == PieceKind.QUEEN);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalMoveIntoCheck() {
		Piece[][] board = new Piece[8][8];
		board[0][0] = new Piece(Color.WHITE, PieceKind.QUEEN);
		board[7][4] = new Piece(Color.WHITE, PieceKind.KING);
		board[1][2] = new Piece(Color.BLACK, PieceKind.KING);
		start = new State(Color.BLACK, board, new boolean[] { true, true },
				new boolean[] { true, true }, null, 0, null);
		Move move = new Move(new Position(1, 2), new Position(1, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalIrrelevantMoveWhileInCheck() {
		Piece[][] board = new Piece[8][8];
		board[0][0] = new Piece(Color.WHITE, PieceKind.QUEEN);
		board[7][4] = new Piece(Color.WHITE, PieceKind.KING);
		board[1][1] = new Piece(Color.BLACK, PieceKind.KING);
		board[7][6] = new Piece(Color.BLACK, PieceKind.PAWN);
		start = new State(Color.BLACK, board, new boolean[] { true, true },
				new boolean[] { true, true }, null, 0, null);
		Move move = new Move(new Position(7, 6), new Position(6, 6), null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * End Tests by Simon Gellis <simongellis@gmail.com>
	 */
	/*
	 * Begin Tests by Kuang-Che Lee <qfoxer@gmail.com>
	 */
	@Test
	public void TestWhitePawnMoveTwoSquaresAtStart() {
		Move move = new Move(new Position(1, 1), new Position(3, 1), null);
		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(1, 1, null);
		expected.setPiece(3, 1, new Piece(WHITE, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setEnpassantPosition(new Position(3, 1));
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhitePawnMoveOneSquareAtStart() {
		Move move = new Move(new Position(1, 2), new Position(2, 2), null);
		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(1, 2, null);
		expected.setPiece(2, 2, new Piece(WHITE, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setEnpassantPosition(null);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestPawnMoveOneSquare() {
		start.setTurn(WHITE);
		Move move = new Move(new Position(1, 4), new Position(2, 4), null);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(1, 4, null);
		expected.setPiece(2, 4, new Piece(WHITE, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setEnpassantPosition(null);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveUp() {
		start.setPiece(1, 4, null);
		Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		State expected = start.copy();
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setTurn(BLACK);
		expected.setPiece(0, 4, null);
		expected.setPiece(1, 4, new Piece(WHITE, KING));
		int CurNumberOfMovesWithoutCaptureNorPawnMoved = expected
				.getNumberOfMovesWithoutCaptureNorPawnMoved();
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(CurNumberOfMovesWithoutCaptureNorPawnMoved + 1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveUpRight() {
		start.setPiece(1, 5, null);
		Move move = new Move(new Position(0, 4), new Position(1, 5), null);
		State expected = start.copy();
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setTurn(BLACK);
		expected.setPiece(0, 4, null);
		expected.setPiece(1, 5, new Piece(WHITE, KING));
		int CurNumberOfMovesWithoutCaptureNorPawnMoved = expected
				.getNumberOfMovesWithoutCaptureNorPawnMoved();
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(CurNumberOfMovesWithoutCaptureNorPawnMoved + 1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveUpLeft() {
		start.setPiece(1, 3, null);
		Move move = new Move(new Position(0, 4), new Position(1, 3), null);
		State expected = start.copy();
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setTurn(BLACK);
		expected.setPiece(0, 4, null);
		expected.setPiece(1, 3, new Piece(WHITE, KING));
		int CurNumberOfMovesWithoutCaptureNorPawnMoved = expected
				.getNumberOfMovesWithoutCaptureNorPawnMoved();
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(CurNumberOfMovesWithoutCaptureNorPawnMoved + 1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveRight() {
		start.setPiece(0, 5, null);
		Move move = new Move(new Position(0, 4), new Position(0, 5), null);
		State expected = start.copy();
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setTurn(BLACK);
		expected.setPiece(0, 4, null);
		expected.setPiece(0, 5, new Piece(WHITE, KING));
		int CurNumberOfMovesWithoutCaptureNorPawnMoved = expected
				.getNumberOfMovesWithoutCaptureNorPawnMoved();
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(CurNumberOfMovesWithoutCaptureNorPawnMoved + 1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveLeft() {
		start.setPiece(0, 3, null);
		Move move = new Move(new Position(0, 4), new Position(0, 3), null);
		State expected = start.copy();
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setTurn(BLACK);
		expected.setPiece(0, 4, null);
		expected.setPiece(0, 3, new Piece(WHITE, KING));
		int CurNumberOfMovesWithoutCaptureNorPawnMoved = expected
				.getNumberOfMovesWithoutCaptureNorPawnMoved();
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(CurNumberOfMovesWithoutCaptureNorPawnMoved + 1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveDown() {
		Move move = new Move(new Position(3, 3), new Position(2, 3), null);

		Piece whiteking = new Piece(WHITE, KING);
		start.setPiece(3, 3, whiteking);
		start.setPiece(2, 3, null);
		start.setPiece(0, 4, null);
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleQueenSide(WHITE, false);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(3, 3, null);
		expected.setPiece(2, 3, new Piece(WHITE, KING));
		int CurNumberOfMovesWithoutCaptureNorPawnMoved = expected
				.getNumberOfMovesWithoutCaptureNorPawnMoved();
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(CurNumberOfMovesWithoutCaptureNorPawnMoved + 1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveDownRight() {
		Move move = new Move(new Position(3, 3), new Position(2, 4), null);

		Piece whiteking = new Piece(WHITE, KING);
		start.setPiece(0, 4, null);
		start.setPiece(3, 3, whiteking);
		start.setPiece(2, 4, null);
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleQueenSide(WHITE, false);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(3, 3, null);
		expected.setPiece(2, 4, new Piece(WHITE, KING));
		int CurNumberOfMovesWithoutCaptureNorPawnMoved = expected
				.getNumberOfMovesWithoutCaptureNorPawnMoved();
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(CurNumberOfMovesWithoutCaptureNorPawnMoved + 1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveDownLeft() {
		Move move = new Move(new Position(3, 3), new Position(2, 2), null);

		Piece whiteking = new Piece(WHITE, KING);
		start.setPiece(3, 3, whiteking);
		start.setPiece(2, 2, null);
		start.setPiece(0, 4, null);
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleQueenSide(WHITE, false);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(3, 3, null);
		expected.setPiece(2, 2, new Piece(WHITE, KING));
		int CurNumberOfMovesWithoutCaptureNorPawnMoved = expected
				.getNumberOfMovesWithoutCaptureNorPawnMoved();
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(CurNumberOfMovesWithoutCaptureNorPawnMoved + 1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveUpAndCapturePiece() {
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);

		Piece whiteking = new Piece(WHITE, KING);
		Piece blackpiece = new Piece(BLACK, PAWN);
		start.setPiece(0, 4, null);
		start.setPiece(6, 2, null);
		start.setPiece(3, 4, whiteking);
		start.setPiece(4, 4, blackpiece);
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleQueenSide(WHITE, false);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(3, 4, null);
		expected.setPiece(4, 4, new Piece(WHITE, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveLeftAndCapturePiece() {
		Move move = new Move(new Position(2, 3), new Position(2, 2), null);

		Piece whiteking = new Piece(WHITE, KING);
		start.setPiece(2, 3, whiteking);
		Piece blackpiece = new Piece(BLACK, PAWN);
		start.setPiece(2, 2, blackpiece);
		start.setPiece(0, 4, null);
		start.setPiece(6, 1, null);
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleQueenSide(WHITE, false);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(2, 3, null);
		expected.setPiece(2, 2, new Piece(WHITE, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void TestWhiteKingMoveUpRightAndCapturePiece() {
		Move move = new Move(new Position(2, 4), new Position(3, 5), null);

		Piece whiteking = new Piece(WHITE, KING);
		start.setPiece(2, 4, whiteking);
		Piece blackpiece = new Piece(BLACK, PAWN);
		start.setPiece(3, 5, blackpiece);
		start.setPiece(0, 4, null);
		start.setPiece(6, 1, null);
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleQueenSide(WHITE, false);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(2, 4, null);
		expected.setPiece(3, 5, new Piece(WHITE, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhitePawnMoveRight() {
		Piece whitepawn = new Piece(WHITE, PAWN);
		start.setPiece(1, 0, whitepawn);
		Move move = new Move(new Position(1, 0), new Position(1, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhitePawnMoveUpThreeSquares() {
		Piece whitepawn = new Piece(WHITE, PAWN);
		start.setPiece(1, 0, whitepawn);
		Move move = new Move(new Position(1, 0), new Position(4, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhitePawnMoveBack() {
		Piece whitepawn = new Piece(WHITE, PAWN);
		start.setPiece(1, 0, whitepawn);
		Move move = new Move(new Position(1, 0), new Position(0, 0), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhitePawnMoveUpToWhiteOccupiedSquare() {
		Piece whitepawn = new Piece(WHITE, PAWN);
		Piece whitepiece = new Piece(WHITE, PAWN);
		start.setPiece(3, 0, whitepawn);
		start.setPiece(4, 0, whitepiece);
		start.setPiece(1, 0, null);
		start.setPiece(1, 1, null);
		Move move = new Move(new Position(3, 0), new Position(4, 0), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhitePawnMoveUpToBlackOccupiedSquare() {
		Piece whitepawn = new Piece(WHITE, PAWN);
		Piece blackpawn = new Piece(BLACK, PAWN);
		start.setPiece(4, 1, whitepawn);
		start.setPiece(5, 1, blackpawn);
		start.setPiece(1, 1, null);
		start.setPiece(6, 1, null);
		Move move = new Move(new Position(4, 1), new Position(5, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveTwoSquaresRight() {
		Piece whiteking = new Piece(WHITE, KING);
		start.setPiece(0, 4, whiteking);
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveTwoSquaresTop() {
		Piece whiteking = new Piece(WHITE, KING);
		start.setPiece(2, 4, whiteking);
		start.setPiece(0, 4, null);
		Move move = new Move(new Position(2, 4), new Position(4, 4), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveToWhiteOccupiedSquare() {
		Piece whiteking = new Piece(WHITE, KING);
		Piece whitepawn = new Piece(WHITE, PAWN);
		start.setPiece(0, 4, whiteking);
		start.setPiece(1, 4, whitepawn);
		Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveToSquareEatenByBlackQueenDiagonal() {
		Piece whiteking = new Piece(WHITE, KING);
		Piece blackqueen = new Piece(BLACK, QUEEN);
		start.setPiece(0, 4, null);
		start.setPiece(7, 3, null);
		start.setPiece(5, 3, blackqueen);
		start.setPiece(4, 4, null);
		start.setPiece(3, 5, null);
		start.setPiece(2, 5, whiteking);
		Move move = new Move(new Position(2, 5), new Position(2, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveToSquareEatenByBlackQueenFile() {
		Piece whiteking = new Piece(WHITE, KING);
		Piece blackqueen = new Piece(BLACK, QUEEN);
		start.setPiece(7, 3, null);
		start.setPiece(5, 3, blackqueen);
		start.setPiece(4, 3, null);
		start.setPiece(3, 3, null);
		start.setPiece(0, 4, null);
		start.setPiece(2, 4, whiteking);
		Move move = new Move(new Position(2, 4), new Position(3, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveToSquareEatenByBlackRook() {
		Piece whiteking = new Piece(WHITE, KING);
		Piece blackrook = new Piece(BLACK, ROOK);
		start.setPiece(7, 0, null);
		start.setPiece(3, 3, blackrook);
		start.setPiece(2, 3, null);
		start.setPiece(2, 4, whiteking);
		start.setPiece(0, 4, null);
		Move move = new Move(new Position(2, 4), new Position(2, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveToSquareEatenByBlackBishop() {
		Piece whiteking = new Piece(WHITE, KING);
		Piece blackbishop = new Piece(BLACK, BISHOP);
		start.setPiece(7, 2, null);
		start.setPiece(3, 2, blackbishop);
		start.setPiece(2, 3, null);
		start.setPiece(2, 4, whiteking);
		start.setPiece(0, 4, null);
		Move move = new Move(new Position(2, 4), new Position(2, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveToSquareEatenByBlackKnight() {
		Piece whiteking = new Piece(WHITE, KING);
		Piece blackknight = new Piece(BLACK, KNIGHT);
		start.setPiece(2, 3, blackknight);
		start.setPiece(7, 1, null);
		start.setPiece(0, 5, whiteking);
		start.setPiece(0, 4, null);
		Move move = new Move(new Position(0, 5), new Position(0, 4), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveToSquareEatenByBlackPawn() {
		Piece whiteking = new Piece(WHITE, KING);
		Piece blackpawn = new Piece(BLACK, PAWN);
		start.setPiece(3, 4, blackpawn);
		start.setPiece(6, 4, null);
		start.setPiece(0, 4, null);
		start.setPiece(2, 4, whiteking);
		Move move = new Move(new Position(2, 4), new Position(2, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestIllegalWhiteKingMoveToSquareEatenByBlackKing() {
		Piece whiteking = new Piece(WHITE, KING);
		Piece blackking = new Piece(BLACK, KING);
		start.setPiece(7, 4, null);
		start.setPiece(0, 4, null);
		start.setPiece(4, 3, blackking);
		start.setPiece(2, 4, whiteking);
		Move move = new Move(new Position(2, 4), new Position(3, 4), null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * End Tests by Kuang-Che Lee <qfoxer@gmail.com>
	 */

	/*
	 * Begin Tests by Harsh Mehta <hamehta3@gmail.com>
	 */
	public void initForHarshMehta() {
		start.setTurn(BLACK);
	}

	/**
	 * Helper functions to clear pieces on King / Queen side
	 */
	private void clearQueenSidePieces() {
		start.setPiece(7, 3, null);
		start.setPiece(7, 2, null);
		start.setPiece(7, 1, null);
	}

	private void clearKingSidePieces() {
		start.setPiece(7, 5, null);
		start.setPiece(7, 6, null);
	}

	/**
	 * Tests to check if the canCastleKingSide and canCastleQueenSide booleans
	 * work as expected
	 */
	@Test
	public void testMoveRookKingSideSetsBooleanFalse() {
		initForHarshMehta();
		start.setPiece(6, 7, null); // Remove pawn in front of rook
		Move move = new Move(new Position(7, 7), new Position(6, 7), null); // Move
		// rook
		stateChanger.makeMove(start, move);
		assertFalse(start.isCanCastleKingSide(BLACK));
	}

	@Test
	public void testMoveRookQueenSideSetsBooleanFalse() {
		initForHarshMehta();
		start.setPiece(6, 0, null); // Remove pawn in front of rook
		Move move = new Move(new Position(7, 0), new Position(6, 0), null); // Move
		// rook
		stateChanger.makeMove(start, move);
		assertFalse(start.isCanCastleQueenSide(BLACK));
	}

	@Test
	public void testMoveKingSetsCanCastleKingSideBooleanFalse() {
		initForHarshMehta();
		start.setPiece(6, 4, null); // Remove pawn in front of king
		Move move = new Move(new Position(7, 4), new Position(6, 4), null); // Move
		// king
		stateChanger.makeMove(start, move);
		assertFalse(start.isCanCastleKingSide(BLACK));
	}

	@Test
	public void testMoveKingSetsCanCastleQueenSideBooleanFalse() {
		initForHarshMehta();
		start.setPiece(6, 4, null); // Remove pawn in front of king
		Move move = new Move(new Position(7, 4), new Position(6, 4), null); // Move
		// king
		stateChanger.makeMove(start, move);
		assertFalse(start.isCanCastleQueenSide(BLACK));
	}

	/**
	 * Tests for Illegal moves
	 */
	@Test(expected = IllegalMove.class)
	public void testIllegalCastleKingSideKingMoved() {
		initForHarshMehta();
		start.setCanCastleKingSide(BLACK, false);
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleQueenSideKingMoved() {
		initForHarshMehta();
		start.setCanCastleQueenSide(BLACK, false);
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleKingSideRookMoved() {
		initForHarshMehta();
		start.setCanCastleKingSide(BLACK, false);
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleQueenSideRookMoved() {
		initForHarshMehta();
		start.setCanCastleQueenSide(BLACK, false);
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleKingSidePiecesBetween() {
		initForHarshMehta();
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleQueenSidePiecesBetween() {
		initForHarshMehta();
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleQueenSideKingInCheck() {
		initForHarshMehta();
		start.setPiece(6, 4, null);
		clearQueenSidePieces();
		start.setPiece(0, 3, null);
		// Set White Queen to new position in direct check with Black King
		start.setPiece(3, 4, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleKingSideKingInCheck() {
		initForHarshMehta();
		start.setPiece(6, 4, null);
		clearKingSidePieces();
		start.setPiece(0, 0, null);
		// Set White Rook to new position in direct check with Black King
		start.setPiece(3, 4, new Piece(WHITE, ROOK));
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleQueenSideKingPassThroughCheck() {
		initForHarshMehta();
		start.setPiece(6, 3, null);
		clearQueenSidePieces();
		start.setPiece(0, 3, null);
		// Set White Queen to new position attacking the castling path of Black
		// King
		start.setPiece(3, 3, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleKingSideKingPassThroughCheck() {
		initForHarshMehta();
		start.setPiece(6, 5, null);
		clearKingSidePieces();
		start.setPiece(0, 3, null);
		// Set White Queen to new position attacking the castling path of Black
		// King
		start.setPiece(3, 5, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleQueenSideKingEndsUpInCheck() {
		initForHarshMehta();
		start.setPiece(6, 2, null);
		clearQueenSidePieces();
		start.setPiece(0, 3, null);
		// Set White Queen to new position attacking the castling end path of
		// Black King
		start.setPiece(3, 2, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalCastleKingSideKingEndsUpInCheck() {
		initForHarshMehta();
		start.setPiece(6, 6, null);
		clearKingSidePieces();
		start.setPiece(0, 3, null);
		// Set White Queen to new position attacking the castling end path of
		// Black King
		start.setPiece(3, 6, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		stateChanger.makeMove(start, move);
	}

	// See http://de.wikipedia.org/wiki/Pam-Krabb%C3%A9-Rochade
	@Test(expected = IllegalMove.class)
	public void testIllegalCastleDifferentRanks() {
		initForHarshMehta();
		start.setPiece(6, 4, null);
		start.setPiece(1, 4, null);
		start.setPiece(0, 4, null);
		start.setPiece(4, 0, new Piece(WHITE, KING)); // Set White King
		// somewhere else
		start.setPiece(0, 4, new Piece(BLACK, ROOK));
		Move move = new Move(new Position(7, 4), new Position(5, 4), null);
		stateChanger.makeMove(start, move);
	}

	/**
	 * Tests for Legal moves: A powerset of 3 basic test scenarios: 1.
	 * Queen/King Side 2. Sparse/Dense Rank - Presence of other pieces on the
	 * same rank 3. Other rook moved/not moved
	 */

	/**
	 * Helper functions for legal tests
	 */


	private void setExpectedStateAfterKingSideCastle(State expected) {
		expected.setTurn(WHITE);
		expected.setPiece(7, 4, null);
		expected.setPiece(7, 6, new Piece(BLACK, KING));
		expected.setPiece(7, 7, null);
		expected.setPiece(7, 5, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setCanCastleKingSide(BLACK, false);
		expected.setCanCastleQueenSide(BLACK, false);
		expected.setEnpassantPosition(null);
	}

	private void setExpectedStateAfterQueenSideCastle(State expected) {
		expected.setTurn(WHITE);
		expected.setPiece(7, 4, null);
		expected.setPiece(7, 2, new Piece(BLACK, KING));
		expected.setPiece(7, 0, null);
		expected.setPiece(7, 3, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setCanCastleKingSide(BLACK, false);
		expected.setCanCastleQueenSide(BLACK, false);
		expected.setEnpassantPosition(null);
	}

	@Test
	public void testLegalCastleKingSideDenseRankOtherRookNotMoved() {
		initForHarshMehta();
		clearKingSidePieces();
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterKingSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleKingSideDenseRankOtherRookMoved() {
		initForHarshMehta();
		clearKingSidePieces();
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		start.setCanCastleQueenSide(BLACK, false); // Other rook moved earlier
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterKingSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleKingSideSparseRankOtherRookNotMoved() {
		initForHarshMehta();
		clearKingSidePieces();
		clearQueenSidePieces();
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterKingSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleKingSideSparseRankOtherRookMoved() {
		initForHarshMehta();
		clearKingSidePieces();
		clearQueenSidePieces();
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		start.setCanCastleQueenSide(BLACK, false); // Other rook moved earlier
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterKingSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleQueenSideDenseRankOtherRookNotMoved() {
		initForHarshMehta();
		clearQueenSidePieces();
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterQueenSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleQueenSideDenseRankOtherRookMoved() {
		initForHarshMehta();
		clearQueenSidePieces();
		start.setCanCastleKingSide(BLACK, false); // Other rook moved earlier
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterQueenSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleQueenSideSparseRankOtherRookNotMoved() {
		initForHarshMehta();
		clearKingSidePieces();
		clearQueenSidePieces();
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterQueenSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleQueenSideSparseRankOtherRookMoved() {
		initForHarshMehta();
		clearKingSidePieces();
		clearQueenSidePieces();
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		start.setCanCastleKingSide(BLACK, false); // Other rook moved earlier
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterQueenSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	/**
	 * Additionally, 1. The rook involved in castling may be under attack. 2. In
	 * queenside castling, the square next to the rook involved may be under
	 * attack. These are legal castles
	 * 
	 */
	@Test
	public void testLegalCastleKingSideRookUnderAttack() {
		initForHarshMehta();
		clearKingSidePieces();
		start.setPiece(6, 7, null); // Remove Black Pawn in front of Black Rook
		start.setPiece(0, 3, null); // Remove White Queen
		// Set White Queen to new position attacking Black Rook
		start.setPiece(3, 7, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterKingSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleQueenSideRookUnderAttack() {
		initForHarshMehta();
		clearQueenSidePieces();
		start.setPiece(6, 0, null); // Remove Black Pawn in front of Black Rook
		start.setPiece(0, 3, null); // Remove White Queen
		// Set White Queen to new position attacking Black Rook
		start.setPiece(3, 0, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterQueenSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleQueenSideSquareNextToRookUnderAttack() {
		initForHarshMehta();
		clearQueenSidePieces();
		start.setPiece(6, 1, null); // Remove Black Pawn in front of square next
		// to rook under attack
		start.setPiece(0, 3, null); // Remove White Queen
		// Set White Queen to new position attacking square next to Black Rook
		start.setPiece(3, 1, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterQueenSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	/**
	 * Random legal castles: Moving pawns arbitrarily.
	 */
	@Test
	public void testLegalCastleKingSideRandomPawnMoves() {
		initForHarshMehta();
		clearKingSidePieces();
		start.setPiece(6, 1, null); // Remove random Black Pawn
		start.setPiece(4, 1, new Piece(BLACK, PAWN)); // Set Black Pawn two
		// squares ahead
		start.setEnpassantPosition(new Position(4, 1));  // Set its enpassant position
		Move move = new Move(new Position(7, 4), new Position(7, 6), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterKingSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testLegalCastleQueenSideRandomPawnMoves() {
		initForHarshMehta();
		clearQueenSidePieces();
		start.setPiece(6, 1, null); // Remove random Black Pawn
		start.setPiece(4, 1, new Piece(BLACK, PAWN)); // Set Black Pawn two
		// squares ahead
		start.setEnpassantPosition(new Position(4, 1));  // Set its enpassant position
		Move move = new Move(new Position(7, 4), new Position(7, 2), null);
		State expected = start.copy(); // Copy the current (start) state
		setExpectedStateAfterQueenSideCastle(expected);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	/*
	 * End Tests by Harsh Mehta <hamehta3@gmail.com>
	 */
	/*
	 * Begin Tests by Shih-Wei Huang <loptyc@gmail.com>
	 */
	@Test
	public void testPawnMoveOneSquare() {
		start.setTurn(BLACK);
		Move move = new Move(new Position(6, 7), new Position(5, 7), null);
		State expected = start.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 7, null);
		expected.setPiece(5, 7, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setEnpassantPosition(null);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testPawnMoveTwoSquare() {
		start.setTurn(BLACK);
		Move move = new Move(new Position(6, 1), new Position(4, 1), null);
		State expected = start.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 1, null);
		expected.setPiece(4, 1, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setEnpassantPosition(new Position(4, 1));
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testPawnEnpassantRight() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 0, null);
		initialState.setPiece(3, 3, new Piece(WHITE, PAWN));
		initialState.setPiece(3, 2, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(7, 0, new Piece(BLACK, KING));
		initialState.setEnpassantPosition(new Position(3, 3));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 2, null);
		expected.setPiece(3, 3, null);
		expected.setPiece(2, 3, new Piece(BLACK, PAWN));
		expected.setEnpassantPosition(null);
		Move move = new Move(new Position(3, 2), new Position(2, 3), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testPawnEnpassantLeft() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 0, null);
		initialState.setPiece(3, 3, new Piece(WHITE, PAWN));
		initialState.setPiece(3, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(7, 0, new Piece(BLACK, KING));
		initialState.setEnpassantPosition(new Position(3, 3));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(3, 3, null);
		expected.setPiece(2, 3, new Piece(BLACK, PAWN));
		expected.setEnpassantPosition(null);
		Move move = new Move(new Position(3, 4), new Position(2, 3), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testPawnCaptureRight() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 0, null);
		initialState.setPiece(4, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 2, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(7, 0, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(5, 2, null);
		expected.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(5, 2), new Position(4, 3), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testPawnCaptureLeft() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 0, null);
		initialState.setPiece(4, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(7, 0, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(5, 4, null);
		expected.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(5, 4), new Position(4, 3), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testPawnMoveOneSquareAtAnotherPlace() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 0, null);
		initialState.setPiece(4, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(7, 0, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(5, 4, null);
		expected.setPiece(4, 4, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(5, 4), new Position(4, 4), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testPawnMoveResetNumberOfMovesWithoutCaptureNorPawnMoved() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 40, null);
		initialState.setPiece(4, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(7, 0, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(5, 4, null);
		expected.setPiece(4, 4, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		Move move = new Move(new Position(5, 4), new Position(4, 4), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testKingMoveForward() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 40, null);
		initialState.setPiece(4, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(7, 0, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(7, 0, null);
		expected.setPiece(6, 0, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(41);
		Move move = new Move(new Position(7, 0), new Position(6, 0), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testKingMoveLeft() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(4, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 2, null);
		expected.setPiece(6, 1, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(21);
		Move move = new Move(new Position(6, 2), new Position(6, 1), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testKingMoveBackward() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(4, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 2, null);
		expected.setPiece(7, 2, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(21);
		Move move = new Move(new Position(6, 2), new Position(7, 2), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testKingMoveLeftAndForward() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(4, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 2, null);
		expected.setPiece(5, 1, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(21);
		Move move = new Move(new Position(6, 2), new Position(5, 1), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testKingMoveRightAndBackward() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(4, 5, new Piece(WHITE, ROOK));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 2, null);
		expected.setPiece(7, 3, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(21);
		Move move = new Move(new Position(6, 2), new Position(7, 3), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testKingCaptureRight() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(4, 5, new Piece(WHITE, ROOK));
		initialState.setPiece(6, 3, new Piece(WHITE, PAWN));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 2, null);
		expected.setPiece(6, 3, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		Move move = new Move(new Position(6, 2), new Position(6, 3), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testKingCaptureBackward() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(7, 2, new Piece(WHITE, ROOK));
		initialState.setPiece(6, 5, new Piece(WHITE, PAWN));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 2, null);
		expected.setPiece(7, 2, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		Move move = new Move(new Position(6, 2), new Position(7, 2), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test
	public void testKingCaptureLeftAndForward() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(7, 1, new Piece(WHITE, ROOK));
		initialState.setPiece(6, 3, new Piece(WHITE, PAWN));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		State expected = initialState.copy();
		expected.setTurn(WHITE);
		expected.setPiece(6, 2, null);
		expected.setPiece(7, 1, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		Move move = new Move(new Position(6, 2), new Position(7, 1), null);
		stateChanger.makeMove(initialState, move);
		assertEquals(expected, initialState);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPawnMoveBackward() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(7, 1, new Piece(WHITE, ROOK));
		initialState.setPiece(6, 3, new Piece(WHITE, PAWN));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		Move move = new Move(new Position(5, 4), new Position(6, 4), null);
		stateChanger.makeMove(initialState, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPawnCapture() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(7, 1, new Piece(WHITE, ROOK));
		initialState.setPiece(6, 3, new Piece(WHITE, PAWN));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		Move move = new Move(new Position(5, 4), new Position(4, 3), null);
		stateChanger.makeMove(initialState, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIlleaalKingMove() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(0, 7, new Piece(WHITE, ROOK));
		initialState.setPiece(2, 3, new Piece(WHITE, PAWN));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		Move move = new Move(new Position(6, 2), new Position(6, 4), null);
		stateChanger.makeMove(initialState, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKingMoveAnother() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(0, 7, new Piece(WHITE, ROOK));
		initialState.setPiece(1, 3, new Piece(WHITE, PAWN));
		initialState.setPiece(6, 3, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		Move move = new Move(new Position(6, 2), new Position(6, 3), null);
		stateChanger.makeMove(initialState, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKingMoveToSquareUnderDirectAttack() {
		boolean canCastleArray[] = { false, false };
		State initialState = new State(BLACK, new Piece[8][8], canCastleArray,
				canCastleArray, null, 20, null);
		initialState.setPiece(0, 3, new Piece(WHITE, ROOK));
		initialState.setPiece(6, 7, new Piece(WHITE, PAWN));
		initialState.setPiece(5, 4, new Piece(BLACK, PAWN));
		initialState.setPiece(0, 0, new Piece(WHITE, KING));
		initialState.setPiece(6, 2, new Piece(BLACK, KING));
		Move move = new Move(new Position(6, 2), new Position(6, 3), null);
		stateChanger.makeMove(initialState, move);
	}

	/*
	 * End Tests by Shih-Wei Huang <loptyc@gmail.com>
	 */


	/*
	 * 	Begin tests by Sanjana Agarwal
	 * 
	 * */

	void initforsanjana()
	{
		//	  Piece[][] board = new Piece[ROWS][COLS];
		//	  State copied = new State(Color.WHITE, board, new boolean[]{true, true}, new boolean[]{true, true}, null, 0, null);
		start=new State();
		for (int row = 0; row<ROWS; ++row) 
			for (int col = 0; col<COLS; ++col) 
				start.setPiece(row,col,null);
		start.setPiece(new Position(7,4),new Piece(BLACK,KING));
		start.setPiece(new Position(0,4),new Piece(WHITE,KING));
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleKingSide(BLACK, false);
		start.setCanCastleQueenSide(WHITE, false);
		start.setCanCastleQueenSide(BLACK, false);
	}

	//If White bishop tries to capture a black knight i.e a legal capture
	@Test()
	public void testBishopLegalCapture() {
		initforsanjana();
		Move move = new Move(new Position(3,0), new Position(5,2), null);

		Piece whitebishop=new Piece(WHITE,PieceKind.BISHOP);
		start.setPiece(3,0,whitebishop);
		Piece blackknight= new Piece(BLACK, PieceKind.KNIGHT);
		start.setPiece(5,2,blackknight);
		State expected = start.copy();

		assertEquals(blackknight.getColor(),BLACK);
		expected.setTurn(BLACK);
		expected.setPiece(3,0, null);
		expected.setPiece(5,2, whitebishop);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);		   		   
	}

	//If white bishop tries to capture a black queen but there's another piece in the path
	// that is an illegal capture
	@Test(expected = IllegalMove.class)
	public void testBishopIllegalCapture() {
		initforsanjana(); 	
		Piece whitebishop=new Piece(WHITE,BISHOP);
		start.setPiece(5,2,whitebishop);
		Piece blackbishop=new Piece(BLACK,BISHOP);
		start.setPiece(6,1,blackbishop);
		Piece blackqueen=new Piece(BLACK,QUEEN);
		start.setPiece(7,0,blackqueen);
		Move move = new Move(new Position(5, 2), new Position(7,0), null);
		stateChanger.makeMove(start, move);		    
	}

	//If white knight tries to capture the black bishop this will expose the white king
	//to check from black rook, making this an illegal capture but still a legal move as in
	//no exception will be thrown

	@Test()
	public void testIllegalCaptureLegalMoveSituationalPin() {

		initforsanjana();
		Move move = new Move(new Position(3,1), new Position(4,3), null);
		Piece whiteknight=new Piece(WHITE,PieceKind.KNIGHT);
		start.setPiece(3,1,whiteknight);
		Piece blackrook=new Piece(BLACK,PieceKind.ROOK);
		start.setPiece(7,1,blackrook);
		Piece blackbishop=new Piece(BLACK,PieceKind.BISHOP);
		start.setPiece(4,3,blackbishop);
		State expected = start.copy();

		expected.setTurn(BLACK);
		expected.setPiece(3,1, null);
		expected.setPiece(4,3,whiteknight);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleKingSide(BLACK, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setCanCastleQueenSide(BLACK, false);
		stateChanger.makeMove(start, move);		 
		assertEquals(expected, start);		
	}

	//A king tries to capture a black piece, which is on a square that is attacked by a piece of the opponent;
	//regardless whether this piece is `pinned' (would cause check to its own king) when moved or not.
	//Such a move would still be regarded as moving the king into check. hence illegal capture

	@Test(expected = IllegalMove.class)
	public void testIllegalCaptureKingExposedTOCheck() {

		initforsanjana();
		start.setPiece(0,4,null); //setting old white king to null first
		Piece blackrook=new Piece(BLACK,PieceKind.ROOK);
		start.setPiece(4, 3,blackrook);
		Piece blackbishop=new Piece(BLACK,BISHOP);
		start.setPiece(4,6,blackbishop);
		Piece whitequeen=new Piece(WHITE,PieceKind.QUEEN);
		start.setPiece(0,3,whitequeen);
		Piece whiteking=new Piece(WHITE,KING);
		start.setPiece(3,6,whiteking);

		//if king tries to capture the bishop it could expose itself to check from black rook
		//even though the rook is pinned to its' king. It will still be an illegal capture

		Move move = new Move(new Position(3,6), new Position(4,6), null);
		stateChanger.makeMove(start, move);		    
	}

	//If white king, black knight and black king are in one row next to each other;
	// and the white king tries to capture the knight standing next to its' black king
	//i.e an illegal capture

	@Test(expected = IllegalMove.class)
	public void testKingIllegalCheckCapture() {

		initforsanjana();
		start.setPiece(0,4,null);		//set old king positions to null
		start.setPiece(7,4,null);
		Piece whiteking=new Piece(WHITE,KING);
		start.setPiece(1,4,whiteking);
		Piece blackking=new Piece(BLACK,KING);
		start.setPiece(1,6,blackking);
		Piece blackknight=new Piece(BLACK,PieceKind.KNIGHT);
		start.setPiece(1,5,blackknight);

		//white king will come in check if it moves to capture black knight.
		Move move = new Move(new Position(1,4), new Position(1,5), null);
		stateChanger.makeMove(start, move);		    
	}

	//If White king tries to capture a black piece i.e a legal capture
	@Test()
	public void testKingLegalCapturesPiece() {
		initforsanjana();
		Move move = new Move(new Position(0,4), new Position(1,4), null);

		Piece whiteking=start.getPiece(0,4);
		Piece blackrook=new Piece(BLACK,PieceKind.ROOK);
		start.setPiece(1,4,blackrook);
		State expected = start.copy();

		assertEquals(blackrook.getColor(),BLACK);
		expected.setTurn(BLACK);
		expected.setPiece(0,4, null);
		expected.setPiece(1,4,whiteking);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setCanCastleKingSide(WHITE, false);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);		   		   
	}

	//If white king tries to capture a black piece exposing itself to check i.e. an illegal capture
	@Test(expected = IllegalMove.class)
	public void testKingMovesInCheckIllegalCapture() {
		initforsanjana();
		start.setPiece(0,4,null);
		Piece whiteking=new Piece(WHITE,KING);
		start.setPiece(1,4,whiteking);
		Piece blackbishop=new Piece(BLACK,BISHOP);
		start.setPiece(2,5,blackbishop);
		Piece blackknight =new Piece(BLACK,KNIGHT);
		start.setPiece(4,6,blackknight);

		//If king captures bishop it exposes itself to check, making this an illegal capture
		Move move = new Move(new Position(1,4), new Position(2,5), null);
		stateChanger.makeMove(start, move);		    
	}

	//If white knight tries to capture a piece by an illegal move i.e. an illegal capture
	@Test(expected = IllegalMove.class)
	public void testKnightIllegalMoveCapture() {
		initforsanjana();  

		Piece whiteknight=new Piece(WHITE,KNIGHT);
		start.setPiece(4,6,whiteknight);
		Move move = new Move(new Position(4,6), new Position(7,4), null);
		stateChanger.makeMove(start, move);		    
	}

	//If White knight tries to capture a black piece i.e a legal capture
	@Test()
	public void testKnightLegalCapturesPiece() {
		initforsanjana();
		Move move = new Move(new Position(4,7), new Position(6,6), null);

		Piece whiteknight=new Piece(WHITE,KNIGHT);
		start.setPiece(4,7,whiteknight);
		Piece whitepawn=new Piece(WHITE,PAWN);
		start.setPiece(5,7,whitepawn);
		Piece blackqueen=new Piece(BLACK,QUEEN);
		start.setPiece(6,7,blackqueen);
		Piece blackrook=new Piece(BLACK,ROOK);
		start.setPiece(6,6,blackrook);
		State expected = start.copy();

		assertEquals(blackrook.getColor(),BLACK);
		expected.setTurn(BLACK);
		expected.setPiece(4,7, null);
		expected.setPiece(6,6,whiteknight);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);		   		   
	}

	//If white pawn tries to capture a black piece backwards i.e illegal capture
	@Test(expected = IllegalMove.class)
	public void testPawnIllegalCaptureWhitePieceBackwards() 
	{
		initforsanjana();
		start.setPiece(0, 3,new Piece(BLACK,QUEEN));
		start.setPiece(1,4,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(1, 4), new Position(0,3), null);
		stateChanger.makeMove(start, move); 
	}

	//If white pawn tries to capture another white piece i.e an illegal capture
	@Test(expected = IllegalMove.class)
	public void testPawnIllegalCaptureWhitePiece() {

		initforsanjana();
		Piece whiteknight=new Piece(WHITE,KNIGHT);
		start.setPiece(2,5,whiteknight);
		start.setPiece(1,4,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(1, 4), new Position(2, 5), null);
		stateChanger.makeMove(start, move); 
	}
	//If white pawn tries to capture a black piece in forward direction i.e an illegal capture
	@Test(expected = IllegalMove.class)
	public void testPawnIllegalForwardCapture() {
		initforsanjana();
		Piece whitepawn=new Piece(WHITE,PieceKind.PAWN);
		start.setPiece(3,5,whitepawn);
		Piece blackbishop=new Piece(BLACK,PieceKind.BISHOP);
		start.setPiece(4,5,blackbishop);
		Move move = new Move(new Position(3, 5), new Position(4, 5), null);
		stateChanger.makeMove(start, move); 
	}

	//If White pawn tries to capture a black piece, in a legal capture
	@Test()
	public void testPawnLegalCapturesPiece() {

		initforsanjana();
		Move move = new Move(new Position(1, 1), new Position(2, 2), null);

		Piece whitepawn=new Piece(WHITE, PieceKind.PAWN);
		start.setPiece(1,1,whitepawn);
		Piece blackpawn= new Piece(BLACK,PieceKind.PAWN);
		start.setPiece(2,2,blackpawn);
		State expected = start.copy();

		assertEquals(blackpawn.getColor(),BLACK);
		expected.setTurn(BLACK);
		expected.setPiece(1, 1, null);
		expected.setPiece(2, 2,whitepawn);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);	    	   
	}

	//If a white pawn captures a black piece legally but stands in-front of another
	//white pawn. It is still a legal capture and is called doubling or doubled pawns.
	@Test()
	public void testPawnLegalCapturePieceDoubling() {
		initforsanjana();
		Move move = new Move(new Position(1, 1), new Position(2, 2), null);

		Piece whitepawn=new Piece(WHITE, PieceKind.PAWN);
		start.setPiece(1,1,whitepawn);
		Piece blackpawn= new Piece(BLACK,PieceKind.PAWN);
		start.setPiece(2,2,blackpawn);
		start.setPiece(1,2,new Piece(WHITE, PieceKind.PAWN));
		State expected = start.copy();

		assertEquals(blackpawn.getColor(),BLACK);
		expected.setTurn(BLACK);
		expected.setPiece(1, 1, null);
		expected.setPiece(2, 2,whitepawn);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);	    	
	}

	//If white pawn tries to capture a black piece in forward direction i.e an illegal capture
	@Test(expected = IllegalMove.class)
	public void testPawnIllegalSidewaysCapture() {
		initforsanjana();
		Piece whitepawn=new Piece(WHITE,PieceKind.PAWN);
		start.setPiece(3,5,whitepawn);
		Piece blackpawn=new Piece(BLACK,PieceKind.PAWN);
		start.setPiece(3,6,blackpawn);
		Move move = new Move(new Position(3, 5), new Position(3,6), null);
		stateChanger.makeMove(start, move); 
	}

	//If white Queen tries to capture a black piece,but its path is blocked 
	//by another white piece i.e an illegal capture
	@Test(expected = IllegalMove.class)
	public void testQueenIllegalBlockedCapture() {

		initforsanjana();
		Piece whitequeen=new Piece(WHITE,PieceKind.QUEEN);
		start.setPiece(0,3,whitequeen);
		Piece whitepawn=new Piece(WHITE,PieceKind.PAWN); 
		start.setPiece(4,3,whitepawn);
		Piece blackrook=new Piece(BLACK,PieceKind.ROOK);
		start.setPiece(7,3,blackrook);

		Move move = new Move(new Position(0,3), new Position(7,3), null);
		stateChanger.makeMove(start, move);		    
	}

	//If white Queen tries to capture a its own king i.e. an illegal capture
	@Test(expected = IllegalMove.class)
	public void testQueenIllegalCapture() {

		initforsanjana();
		Piece whitequeen=new Piece(WHITE,PieceKind.QUEEN);
		start.setPiece(0,6,whitequeen);
		Move move = new Move(new Position(0,6), new Position(0,4), null);
		stateChanger.makeMove(start, move);		    
	}

	//If white Queen tries to capture a piece when it is not her turn i.e an illegal capture
	@Test(expected = IllegalMove.class)
	public void testQueenIllegalTurnCapture() {

		initforsanjana();
		start.setTurn(BLACK);
		Piece whitequeen=new Piece(WHITE,PieceKind.QUEEN);
		start.setPiece(0,3,whitequeen);
		Move move = new Move(new Position(0,3), new Position(3,6), null);
		stateChanger.makeMove(start, move);		    
	}

	//If White queen tries to capture a black piece i.e a legal capture
	@Test()
	public void testQueenLegalCapturesPiece() {

		initforsanjana();
		Move move = new Move(new Position(0,3), new Position(7,3), null);

		Piece whitequeen=new Piece(WHITE,PieceKind.QUEEN);
		start.setPiece(0,3,whitequeen);
		Piece blackrook=new Piece(BLACK,PieceKind.ROOK);
		start.setPiece(7,3,blackrook);
		State expected = start.copy();

		assertEquals(blackrook.getColor(),BLACK);
		expected.setTurn(BLACK);
		expected.setPiece(0,3, null);
		expected.setPiece(7,3,whitequeen);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);		   		   
	}
	//If white rook tries to capture a black knight but there's another piece in the path
	//i.e an illegal capture
	@Test(expected = IllegalMove.class)
	public void testRookIllegalCapturePiece() {

		initforsanjana();
		Piece whiterook=new Piece(WHITE,PieceKind.ROOK);
		start.setPiece(5,4,whiterook);
		Piece blackknight=new Piece(BLACK,PieceKind.KNIGHT);
		start.setPiece(5,6,blackknight);
		Piece whitepawn=new Piece(WHITE,PieceKind.PAWN);
		start.setPiece(5,5,whitepawn);
		Move move = new Move(new Position(5, 4), new Position(5,6), null);
		stateChanger.makeMove(start, move);		    
	}

	//If White rook tries to capture a black piece i.e a legal capture
	@Test()
	public void testRookLegalCapturesPiece() {

		initforsanjana();
		Move move = new Move(new Position(4,4), new Position(4,7), null);

		Piece whiterook=new Piece(WHITE,PieceKind.ROOK);
		start.setPiece(4,4,whiterook);
		Piece blackbishop= new Piece(BLACK, PieceKind.BISHOP);
		start.setPiece(4,7,blackbishop);
		State expected = start.copy();

		assertEquals(blackbishop.getColor(),BLACK);
		expected.setTurn(BLACK);
		expected.setPiece(4,4, null);
		expected.setPiece(4,7, whiterook);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);		   		   
	}
	
	 @Test
	    public void test() 
	    {
	    	initforsanjana();
	    	State state = start.copy();
	    	state.setPiece(7, 4,null);
	    	state.setPiece(0,2,new Piece(BLACK,KING));
	    	state.setPiece(2,0,new Piece(WHITE,KNIGHT));
	    	state.setPiece(1,1,new Piece(BLACK,BISHOP));
	    	state.setNumberOfMovesWithoutCaptureNorPawnMoved(41);
	    	Move move = new Move(new Position(0, 4), new Position(1,4), null);
	    	State expected = state.copy();
	    	expected.setPiece(0,4,null);
	    	expected.setPiece(1,4,new Piece(WHITE,KING));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(42);
	    	expected.setTurn(BLACK);
			stateChanger.makeMove(state,move);
			assertEquals(expected, state);
	    }

	/* 
	 *  End tests by Sanjana Agarwal
	 */


	/*
	 * Begin Tests by Peigen You <fusubacon@gmail.com>
	 */

	public void initForPeigenYou() {
		for (int c = 0; c < 8; c++) {
			for (int r = 0; r < 8; r++) {
				start.setPiece(r, c, null);
			}
		}
		start.setPiece(0, 4,new Piece(WHITE,KING));
		start.setTurn(BLACK);
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleKingSide(BLACK, false);
		start.setCanCastleQueenSide(WHITE, false);
		start.setCanCastleQueenSide(BLACK, false);
	}

	@Test(expected = IllegalMove.class)
	public void testCaseForPawnCheck() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(7, 2), null);

		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(6, 3), new Piece(WHITE, PAWN));
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testCaseForPawnCheck1() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(7, 4), null);

		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(6, 3), new Piece(WHITE, PAWN));
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testCaseForPawnCheck2() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(7, 4), null);
		State expected = start.copy();

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(5, 3), new Piece(WHITE, PAWN));
		stateChanger.makeMove(start, move);
		expected.setTurn(WHITE);
		expected.setPiece(new Position(7, 4), new Piece(BLACK, KING));
		expected.setPiece(new Position(5, 3), new Piece(WHITE, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected, start);
	}

	@Test(expected = IllegalMove.class)
	public void testCaseForPawnAndKingCheck() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(6, 2), null);
		start.setPiece(0,4, null);
		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(6, 2), new Piece(WHITE, PAWN));
		start.setPiece(5, 3, new Piece(WHITE, KING));
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testCaseForQueenCheck() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(7, 2), null);

		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(6, 3), new Piece(WHITE, QUEEN));
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testCaseForQueenCheck1() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(7, 2), null);
		State expected = start.copy();

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(4, 1), new Piece(WHITE, QUEEN));
		stateChanger.makeMove(start, move);
		expected.setTurn(WHITE);
		expected.setPiece(new Position(7, 2), new Piece(BLACK, KING));
		expected.setPiece(new Position(4, 1), new Piece(WHITE, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected, start);
	}

	@Test
	public void testCaseForQueenCheck2() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(6, 2), null);
		State expected = start.copy();

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(4, 1), new Piece(WHITE, QUEEN));
		stateChanger.makeMove(start, move);
		expected.setTurn(WHITE);
		expected.setPiece(new Position(6, 2), new Piece(BLACK, KING));
		expected.setPiece(new Position(4, 1), new Piece(WHITE, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected, start);
	}

	@Test(expected = IllegalMove.class)
	public void testCaseForQueenCheck3() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(6, 2), null);

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(5, 2), new Piece(WHITE, QUEEN));
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testCaseForBishopCheck() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(7, 2), null);

		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(6, 3), new Piece(WHITE, BISHOP));
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testCaseForBISHOPCheck1() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(7, 2), null);
		State expected = start.copy();

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(4, 1), new Piece(WHITE, BISHOP));
		stateChanger.makeMove(start, move);
		expected.setTurn(WHITE);
		expected.setPiece(new Position(7, 2), new Piece(BLACK, KING));
		expected.setPiece(new Position(4, 1), new Piece(WHITE, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected, start);
	}

	@Test
	public void testCaseForBISHOPCheck2() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(6, 2), null);
		State expected = start.copy();

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(4, 1), new Piece(WHITE, BISHOP));
		stateChanger.makeMove(start, move);
		expected.setTurn(WHITE);
		expected.setPiece(new Position(6, 2), new Piece(BLACK, KING));
		expected.setPiece(new Position(4, 1), new Piece(WHITE, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected, start);
	}

	@Test
	public void testCaseForBISHOPCheck3() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(6, 2), null);
		State expected = start.copy();

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(5, 2), new Piece(WHITE, BISHOP));
		stateChanger.makeMove(start, move);
		expected.setTurn(WHITE);
		expected.setPiece(new Position(6, 2), new Piece(BLACK, KING));
		expected.setPiece(new Position(5, 2), new Piece(WHITE, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected, start);
	}

	@Test(expected = IllegalMove.class)
	public void testCaseForRookCheck() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(6, 3), null);

		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(3, 3), new Piece(WHITE, ROOK));
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testCaseForRookCheck1() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(7, 3), null);

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(3, 3), new Piece(WHITE, ROOK));
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testCaseForRookCheck2() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(6, 2), null);
		State expected = start.copy();

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(3, 3), new Piece(WHITE, ROOK));
		stateChanger.makeMove(start, move);
		expected.setTurn(WHITE);
		expected.setPiece(new Position(6, 2), new Piece(BLACK, KING));
		expected.setPiece(new Position(3, 3), new Piece(WHITE, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected, start);
	}

	@Test
	public void testCaseForRookCheck3() {
		initForPeigenYou();
		Move move = new Move(new Position(6, 3), new Position(6, 4), null);
		State expected = start.copy();

		start.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(3, 3), new Piece(WHITE, ROOK));
		stateChanger.makeMove(start, move);
		expected.setTurn(WHITE);
		expected.setPiece(new Position(6, 4), new Piece(BLACK, KING));
		expected.setPiece(new Position(3, 3), new Piece(WHITE, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected, start);
	}

	@Test
	public void testCaseForKnightCheck() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(7, 2), null);
		State expected = start.copy();

		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(5, 2), new Piece(WHITE, KNIGHT));
		expected.setTurn(WHITE);
		expected.setPiece(new Position(7, 2), new Piece(BLACK, KING));
		expected.setPiece(new Position(5, 2), new Piece(WHITE, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testCaseForKnightCheck1() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(6, 2), null);
		State expected = start.copy();

		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(5, 2), new Piece(WHITE, KNIGHT));
		expected.setTurn(WHITE);
		expected.setPiece(new Position(6, 2), new Piece(BLACK, KING));
		expected.setPiece(new Position(5, 2), new Piece(WHITE, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testCaseForKnightCheck2() {
		initForPeigenYou();
		Move move = new Move(new Position(7, 3), new Position(6, 3), null);
		State expected = start.copy();

		start.setPiece(new Position(7, 3), new Piece(BLACK, KING));
		start.setPiece(new Position(5, 2), new Piece(WHITE, KNIGHT));
		expected.setTurn(WHITE);
		expected.setPiece(new Position(6, 3), new Piece(BLACK, KING));
		expected.setPiece(new Position(5, 2), new Piece(WHITE, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testCaseForKnightCheck3() {
		initForPeigenYou();
		Move move = new Move(new Position(3, 2), new Position(4, 3), null);
		State expected = start.copy();

		start.setPiece(new Position(3, 2), new Piece(BLACK, KING));
		start.setPiece(new Position(5, 3), new Piece(WHITE, KNIGHT));
		expected.setTurn(WHITE);
		expected.setPiece(new Position(4, 3), new Piece(BLACK, KING));
		expected.setPiece(new Position(5, 3), new Piece(WHITE, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	/*
	 * End Tests by Peigen You <fusubacon@gmail.com>
	 */

	/*
	 * Begin Tests by Jiangfeng Chen <kanppa@gmail.com>
	 */

	@Test
	public void testPawnCaptureBishop() {
		boolean[] CastleBool = { false, false  };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(5, 5), new Position(4, 6), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				7, null);
		original.setPiece(5, 5, new Piece(BLACK, PAWN));
		original.setPiece(4, 6, new Piece(WHITE, BISHOP));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(5, 5, null);
		expected.setPiece(4, 6, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testPawnCapturePawn() {
		boolean[] CastleBool = { false, false  };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 6), new Position(3, 7), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				7, null);
		original.setPiece(4, 6, new Piece(BLACK, PAWN));
		original.setPiece(3, 7, new Piece(WHITE, PAWN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(4, 6, null);
		expected.setPiece(3, 7, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testPawnEnPassant() {
		boolean[] CastleBool = { false, false  };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 2), new Position(2, 1), null);
		State original = new State(BLACK, board, CastleBool, CastleBool,
				new Position(3, 1), 7, null);
		original.setPiece(3, 2, new Piece(BLACK, PAWN));
		original.setPiece(3, 1, new Piece(WHITE, PAWN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 2, null);
		expected.setPiece(3, 1, null);
		expected.setEnpassantPosition(null);
		expected.setPiece(2, 1, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testPawnCaptureThenPromote() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(1, 2), new Position(0, 1), QUEEN);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				7, null);
		original.setPiece(1, 2, new Piece(BLACK, PAWN));
		original.setPiece(0, 1, new Piece(WHITE, ROOK));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(1, 2, null);
		expected.setPiece(0, 1, new Piece(BLACK, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testPawnCaptureRook() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 4), new Position(3, 3), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				3, null);
		original.setPiece(4, 4, new Piece(BLACK, PAWN));
		original.setPiece(3, 3, new Piece(WHITE, ROOK));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(4, 4, null);
		expected.setPiece(3, 3, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testPawnCaptureKnight() {
		boolean[] CastleBool = { false, false  };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 6), new Position(2, 7), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 6, new Piece(BLACK, PAWN));
		original.setPiece(2, 7, new Piece(WHITE, KNIGHT));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 6, null);
		expected.setPiece(2, 7, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testPawnCaptureQueen() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 4), new Position(3, 3), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				3, null);
		original.setPiece(4, 4, new Piece(BLACK, PAWN));
		original.setPiece(3, 3, new Piece(WHITE, QUEEN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(4, 4, null);
		expected.setPiece(3, 3, new Piece(BLACK, PAWN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testQueenCapturePawn() {
		boolean[] CastleBool = { false, false  };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 4), new Position(3, 3), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				2, null);
		original.setPiece(4, 4, new Piece(BLACK, QUEEN));
		original.setPiece(3, 3, new Piece(WHITE, PAWN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(4, 4, null);
		expected.setPiece(3, 3, new Piece(BLACK, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testQueenCaptureKnight() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 6), new Position(2, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				2, null);
		original.setPiece(4, 6, new Piece(BLACK, QUEEN));
		original.setPiece(2, 4, new Piece(WHITE, KNIGHT));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(4, 6, null);
		expected.setPiece(2, 4, new Piece(BLACK, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testQueenCaptureRook() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(5, 2), new Position(5, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				3, null);
		original.setPiece(5, 2, new Piece(BLACK, QUEEN));
		original.setPiece(5, 4, new Piece(WHITE, ROOK));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(5, 2, null);
		expected.setPiece(5, 4, new Piece(BLACK, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testQueenCaptureBishop() {
		boolean[] CastleBool = { false, false  };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(7, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				5, null);
		original.setPiece(3, 4, new Piece(BLACK, QUEEN));
		original.setPiece(7, 4, new Piece(WHITE, BISHOP));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(7, 4, new Piece(BLACK, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testQueenCaptureQueen() {
		boolean[] CastleBool = {false, false  };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(6, 7), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				2, null);
		original.setPiece(3, 4, new Piece(BLACK, QUEEN));
		original.setPiece(6, 7, new Piece(WHITE, QUEEN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(6, 7, new Piece(BLACK, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testRookCaptureQueen() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(6, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				2, null);
		original.setPiece(3, 4, new Piece(BLACK, ROOK));
		original.setPiece(6, 4, new Piece(WHITE, QUEEN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(6, 4, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testRookCaptureKnight() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(1, 3), new Position(6, 3), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				2, null);
		original.setPiece(1, 3, new Piece(BLACK, ROOK));
		original.setPiece(6, 3, new Piece(WHITE, KNIGHT));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(1, 3, null);
		expected.setPiece(6, 3, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testRookCapturePawn() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(3, 7), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				3, null);
		original.setPiece(3, 4, new Piece(BLACK, ROOK));
		original.setPiece(3, 7, new Piece(WHITE, PAWN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(3, 7, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testRookCaptureBishop() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(2, 6), new Position(4, 6), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				8, null);
		original.setPiece(2, 6, new Piece(BLACK, ROOK));
		original.setPiece(4, 6, new Piece(WHITE, BISHOP));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(2, 6, null);
		expected.setPiece(4, 6, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testRookCaptureRook() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, ROOK));
		original.setPiece(4, 4, new Piece(WHITE, ROOK));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(4, 4, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testBishopCaptureRook() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(2, 4), new Position(3, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(2, 4, new Piece(BLACK, BISHOP));
		original.setPiece(3, 5, new Piece(WHITE, ROOK));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(2, 4, null);
		expected.setPiece(3, 5, new Piece(BLACK, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testBishopCapturePawn() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(2, 2), new Position(5, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(2, 2, new Piece(BLACK, BISHOP));
		original.setPiece(5, 5, new Piece(WHITE, PAWN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(2, 2, null);
		expected.setPiece(5, 5, new Piece(BLACK, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testBishopCaptureQueen() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(1, 1), new Position(4, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(1, 1, new Piece(BLACK, BISHOP));
		original.setPiece(4, 4, new Piece(WHITE, QUEEN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(1, 1, null);
		expected.setPiece(4, 4, new Piece(BLACK, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testBishopCaptureBishop() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(5, 6), new Position(2, 3), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(5, 6, new Piece(BLACK, BISHOP));
		original.setPiece(2, 3, new Piece(WHITE, BISHOP));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(5, 6, null);
		expected.setPiece(2, 3, new Piece(BLACK, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testBishopCaptureKnight() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 6), new Position(1, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 6, new Piece(BLACK, BISHOP));
		original.setPiece(1, 4, new Piece(WHITE, KNIGHT));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 6, null);
		expected.setPiece(1, 4, new Piece(BLACK, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKnightCapturePawn() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(4, 6), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KNIGHT));
		original.setPiece(4, 6, new Piece(WHITE, PAWN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(4, 6, new Piece(BLACK, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKnightCaptureQUEEN() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(5, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KNIGHT));
		original.setPiece(5, 5, new Piece(WHITE, QUEEN));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(5, 5, new Piece(BLACK, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKnightCaptureBishop() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(2, 6), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KNIGHT));
		original.setPiece(2, 6, new Piece(WHITE, BISHOP));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(2, 6, new Piece(BLACK, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKnightCaptureRook() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(1, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KNIGHT));
		original.setPiece(1, 5, new Piece(WHITE, ROOK));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(1, 5, new Piece(BLACK, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKnightCaptureKnight() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(4, 2), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KNIGHT));
		original.setPiece(4, 2, new Piece(WHITE, KNIGHT));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(4, 2, new Piece(BLACK, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKingCaptureKnight() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(3, 3), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KING));
		original.setPiece(3, 3, new Piece(WHITE, KNIGHT));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(3, 3, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKingCaptureQueen() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(4, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KING));
		original.setPiece(4, 5, new Piece(WHITE, QUEEN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(4, 5, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKingCapturePawn() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(2, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KING));
		original.setPiece(2, 4, new Piece(WHITE, PAWN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(2, 4, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKingCaptureBishop() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(3, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KING));
		original.setPiece(3, 5, new Piece(WHITE, BISHOP));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(3, 5, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test
	public void testKingCaptureRook() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(2, 3), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KING));
		original.setPiece(2, 3, new Piece(WHITE, ROOK));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		State expected = original.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 4, null);
		expected.setPiece(2, 3, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(original, move);
		assertEquals(expected, original);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPawnCaptureJ() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 5), new Position(3, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				7, null);
		original.setPiece(4, 5, new Piece(BLACK, PAWN));
		original.setPiece(3, 5, new Piece(WHITE, PAWN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalEnPassantJ() {
		boolean[] CastleBool = { false, false  };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 2), new Position(2, 1), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 2, new Piece(BLACK, PAWN));
		original.setPiece(3, 1, new Piece(WHITE, PAWN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalQueenCaptureVertical() {
		boolean[] CastleBool = { false,false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 5), new Position(2, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(4, 5, new Piece(BLACK, QUEEN));
		original.setPiece(3, 5, new Piece(BLACK, KNIGHT));
		original.setPiece(2, 5, new Piece(WHITE, PAWN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalQueenCaptureDiagonal() {
		boolean[] CastleBool = { false,false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(2, 3), new Position(5, 6), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(2, 3, new Piece(BLACK, QUEEN));
		original.setPiece(4, 5, new Piece(WHITE, ROOK));
		original.setPiece(5, 6, new Piece(WHITE, PAWN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalBishopCaptureVertical() {
		boolean[] CastleBool = { false,false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 5), new Position(2, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(4, 5, new Piece(BLACK, BISHOP));
		original.setPiece(2, 5, new Piece(WHITE, PAWN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalBishopCaptureDiagonal() {
		boolean[] CastleBool = { false,false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(2, 3), new Position(5, 6), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(2, 3, new Piece(BLACK, BISHOP));
		original.setPiece(4, 5, new Piece(BLACK, KNIGHT));
		original.setPiece(5, 6, new Piece(WHITE, ROOK));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKnightCapture() {
		boolean[] CastleBool = { false,false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(1, 2), new Position(3, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(1, 2, new Piece(BLACK, KNIGHT));
		original.setPiece(3, 4, new Piece(WHITE, QUEEN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalRookCaptureSkew() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(2, 4), new Position(3, 1), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(2, 4, new Piece(BLACK, ROOK));
		original.setPiece(3, 1, new Piece(WHITE, KNIGHT));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalRookCaptureVertical() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(4, 5), new Position(2, 5), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(4, 5, new Piece(BLACK, ROOK));
		original.setPiece(3, 5, new Piece(BLACK, KNIGHT));
		original.setPiece(2, 5, new Piece(WHITE, PAWN));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		original.setPiece(0, 0, new Piece(BLACK, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKingCaptureTooFar() {
		boolean[] CastleBool = { false,false };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(1, 2), new Position(3, 2), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(1, 2, new Piece(BLACK, KING));
		original.setPiece(3, 2, new Piece(WHITE, BISHOP));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		stateChanger.makeMove(original, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKingCaptureGetDangerous() {
		boolean[] CastleBool = { true, true };
		Piece[][] board = new Piece[ROWS][COLS];
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				0, null);
		original.setPiece(3, 4, new Piece(BLACK, KING));
		original.setPiece(6, 6, new Piece(WHITE, BISHOP));
		original.setPiece(4, 4, new Piece(WHITE, KNIGHT));
		original.setPiece(7, 7, new Piece(WHITE, KING));
		stateChanger.makeMove(original, move);
	}


	/*
	 * End Tests by Jiangfeng Chen <kanppa@gmail.com>
	 */

	/*
	 * Begin Tests by Bo Huang <fantasyblake1213@gmail.com>
	 */

	public State clearPiece(State s) {
		for (int row = 0; row <= 7; row++) {
			for (int col = 0; col <= 7; col++) {
				s.setPiece(row, col, null);
			}			
		}
		s.setCanCastleKingSide(WHITE, false);
		s.setCanCastleKingSide(BLACK, false);
		s.setCanCastleQueenSide(WHITE, false);
		s.setCanCastleQueenSide(BLACK, false);
		return s;

	}

	@Test(expected = IllegalMove.class)
	public void TestNotEscapeFromPawnBlack() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(6, 3, null);
		checked.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(1, 0), new Position(2, 0), null);
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestNotEscapeFromRookBlack() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(7, 0, null);
		checked.setPiece(3, 6, new Piece(BLACK, ROOK));
		Move move = new Move(new Position(1, 0), new Position(2, 0), null);
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestNotEscapeFromKnightBlack() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(7, 1, null);
		checked.setPiece(5, 3, new Piece(BLACK, KNIGHT));
		Move move = new Move(new Position(1, 0), new Position(2, 0), null);
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestNotEscapeFromBishopBlack() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(7, 2, null);
		checked.setPiece(5, 2, new Piece(BLACK, BISHOP));
		Move move = new Move(new Position(1, 0), new Position(2, 0), null);
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestNotEscapeFromQueenBlack() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(7, 3, null);
		checked.setPiece(3, 0, new Piece(BLACK, QUEEN));
		Move move = new Move(new Position(1, 0), new Position(2, 0), null);
		stateChanger.makeMove(checked, move);

	}


	@Test(expected = IllegalMove.class)
	public void TestStillCheckByRookBlackAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(7, 0, null);
		checked.setPiece(3, 6, new Piece(BLACK, ROOK));
		Move move = new Move(new Position(3, 4), new Position(3, 3), null);
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestStillCheckByKnightBlackAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(7, 1, null);
		checked.setPiece(5, 3, new Piece(BLACK, KNIGHT));
		Move move = new Move(new Position(3, 4), new Position(4, 5), null);
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestStillCheckByBishopBlackAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(7, 2, null);
		checked.setPiece(5, 2, new Piece(BLACK, BISHOP));
		Move move = new Move(new Position(3, 4), new Position(4, 3), null);
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestStillCheckByQueenBlackAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(7, 3, null);
		checked.setPiece(3, 0, new Piece(BLACK, QUEEN));
		Move move = new Move(new Position(3, 4), new Position(3, 5), null);
		stateChanger.makeMove(checked, move);
	}


	@Test(expected = IllegalMove.class)
	public void TestEndangerByPawnAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(6, 3, null);
		checked.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);
		checked.setPiece(6, 5, null);
		checked.setPiece(5, 5, new Piece(BLACK, PAWN));
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestEndangerByRookAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setPiece(6, 3, null);
		checked.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);
		checked.setPiece(7, 7, null);
		checked.setPiece(6, 7, null);
		checked.setPiece(1, 4, null);
		checked.setPiece(4, 7, new Piece(BLACK, ROOK));
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestEndangerByKnightAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setPiece(6, 3, null);
		checked.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);
		checked.setPiece(7, 6, null);
		checked.setPiece(5, 6, new Piece(BLACK, KNIGHT));
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestEndangerByBishopAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setPiece(6, 3, null);
		checked.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);
		checked.setPiece(7, 5, null);
		checked.setPiece(5, 5, new Piece(BLACK, BISHOP));
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestEndangerByQueenAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setPiece(6, 3, null);
		checked.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);
		checked.setPiece(7, 3, null);
		checked.setPiece(5, 4, new Piece(BLACK, QUEEN));
		stateChanger.makeMove(checked, move);
	}

	@Test(expected = IllegalMove.class)
	public void TestEndangerByKingAfterKingMove() {
		State checked = start.copy();
		checked.setTurn(WHITE);
		checked.setPiece(0, 4, null);
		checked.setPiece(3, 4, new Piece(WHITE, KING));
		checked.setPiece(6, 3, null);
		checked.setPiece(4, 3, new Piece(BLACK, PAWN));
		Move move = new Move(new Position(3, 4), new Position(4, 4), null);
		checked.setPiece(7, 4, null);
		checked.setPiece(5, 4, new Piece(BLACK, KING));
		stateChanger.makeMove(checked, move);
	}

	@Test
	public void TestEscapeFromPawnBlack() {

		State checked = start.copy();
		checked = clearPiece(checked).copy();

		checked.setPiece(3, 3, new Piece(WHITE, KING));
		checked.setPiece(4, 4, new Piece(BLACK, PAWN));
		checked.setPiece(7, 7, new Piece(BLACK, KING));
		checked.setTurn(WHITE);
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		State escaped = checked.copy();
		escaped.setPiece(3, 3, null);
		escaped.setPiece(2, 3, new Piece(WHITE, KING));
		escaped.setTurn(BLACK);
		escaped.setCanCastleKingSide(WHITE, false);
		escaped.setCanCastleQueenSide(WHITE, false);
		escaped.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

		Move move = new Move(new Position(3, 3), new Position(2, 3), null);
		stateChanger.makeMove(checked, move);

		assertEquals(checked, escaped);
	}

	@Test
	public void TestEscapeFromRookBlackCaptureKnightBlack() {

		State checked = start.copy();
		checked = clearPiece(checked).copy();

		checked.setPiece(3, 3, new Piece(WHITE, KING));
		checked.setPiece(3, 0, new Piece(BLACK, ROOK));
		checked.setPiece(2, 3, new Piece(BLACK, KNIGHT));
		checked.setPiece(7, 7, new Piece(BLACK, KING));
		checked.setTurn(WHITE);
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setCanCastleKingSide(BLACK, false);
		checked.setCanCastleQueenSide(BLACK, false);
		checked.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		State escaped = checked.copy();
		escaped.setPiece(3, 3, null);
		escaped.setPiece(2, 3, new Piece(WHITE, KING));
		escaped.setTurn(BLACK);
		escaped.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		Move move = new Move(new Position(3, 3), new Position(2, 3), null);
		stateChanger.makeMove(checked, move);

		assertEquals(checked, escaped);
	}

	@Test(expected = IllegalMove.class)
	public void TestTryToEscapeFromKnightBlackByCastlingQueenSide() {

		State checked = start.copy();
		checked = clearPiece(checked).copy();

		checked.setPiece(0, 4, new Piece(WHITE, KING));
		checked.setPiece(0, 0, new Piece(WHITE, ROOK));
		checked.setPiece(1, 2, new Piece(BLACK, KNIGHT));
		checked.setPiece(7, 7, new Piece(BLACK, KING));
		checked.setTurn(WHITE);
		checked.setCanCastleKingSide(WHITE, true);
		checked.setCanCastleQueenSide(WHITE, true);
		checked.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		/*
		 * State escaped = checked.copy(); escaped.setPiece(0, 4, null);
		 * escaped.setPiece(0, 2, new Piece(WHITE, KING)); escaped.setPiece(0,
		 * 0, null); escaped.setPiece(0, 3, new Piece(WHITE, ROOK));
		 * escaped.setTurn(BLACK); escaped.setCanCastleKingSide(false);
		 * escaped.setCanCastleQueenSide(false);
		 * escaped.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		 */

		Move moveKing = new Move(new Position(0, 4), new Position(0, 2), null);
		stateChanger.makeMove(checked, moveKing);

	}

	@Test(expected = IllegalMove.class)
	public void TestTryToEscapeFromBishopBlackByCastlingKingSide() {

		State checked = start.copy();
		checked = clearPiece(checked).copy();

		checked.setPiece(0, 4, new Piece(WHITE, KING));
		checked.setPiece(0, 7, new Piece(WHITE, ROOK));
		checked.setPiece(2, 6, new Piece(BLACK, BISHOP));
		checked.setPiece(7, 7, new Piece(BLACK, KING));
		checked.setTurn(WHITE);
		checked.setCanCastleKingSide(WHITE, true);
		checked.setCanCastleQueenSide(WHITE, true);
		checked.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		/*
		 * State escaped = checked.copy(); escaped.setPiece(0, 4, null);
		 * escaped.setPiece(0, 6, new Piece(WHITE, KING)); escaped.setPiece(0,
		 * 7, null); escaped.setPiece(0, 5, new Piece(WHITE, ROOK));
		 * escaped.setTurn(BLACK); escaped.setCanCastleKingSide(false);
		 * escaped.setCanCastleQueenSide(false);
		 * escaped.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		 */

		Move moveKing = new Move(new Position(0, 4), new Position(0, 6), null);
		stateChanger.makeMove(checked, moveKing);

	}

	@Test
	public void TestEscapeFromQueenBlackByCaptureIt() {

		State checked = start.copy();
		checked = clearPiece(checked).copy();

		checked.setPiece(3, 3, new Piece(WHITE, KING));
		checked.setPiece(3, 0, new Piece(BLACK, QUEEN));
		checked.setPiece(0, 0, new Piece(WHITE, ROOK));
		checked.setPiece(7, 7, new Piece(BLACK, KING));
		checked.setTurn(WHITE);
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		State escaped = checked.copy();
		escaped.setPiece(0, 0, null);
		escaped.setPiece(3, 0, new Piece(WHITE, ROOK));
		escaped.setTurn(BLACK);
		escaped.setCanCastleKingSide(WHITE, false);
		escaped.setCanCastleQueenSide(WHITE, false);
		escaped.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		Move move = new Move(new Position(0, 0), new Position(3, 0), null);
		stateChanger.makeMove(checked, move);

		assertEquals(checked, escaped);
	}

	@Test
	public void TestEscapeFromQueenBlackByMoveAPiceInTheCheckWay() {

		State checked = start.copy();
		checked = clearPiece(checked).copy();

		checked.setPiece(3, 3, new Piece(WHITE, KING));
		checked.setPiece(3, 0, new Piece(BLACK, QUEEN));
		checked.setPiece(0, 5, new Piece(WHITE, BISHOP));
		checked.setPiece(7, 7, new Piece(BLACK, KING));
		checked.setTurn(WHITE);
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		State escaped = checked.copy();
		escaped.setPiece(0, 5, null);
		escaped.setPiece(3, 2, new Piece(WHITE, BISHOP));
		escaped.setTurn(BLACK);
		escaped.setCanCastleKingSide(WHITE, false);
		escaped.setCanCastleQueenSide(WHITE, false);
		escaped.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

		Move move = new Move(new Position(0, 5), new Position(3, 2), null);
		stateChanger.makeMove(checked, move);

		assertEquals(checked, escaped);
	}

	@Test(expected = IllegalMove.class)
	public void TestTryToEscapeFromQueenBlackByMoveAPiceInTheCheckWayButEndangerByOther() {

		State checked = start.copy();
		checked = clearPiece(checked).copy();

		checked.setPiece(3, 3, new Piece(WHITE, KING));
		checked.setPiece(3, 0, new Piece(BLACK, QUEEN));
		checked.setPiece(4, 3, new Piece(WHITE, KNIGHT));
		checked.setPiece(5, 3, new Piece(BLACK, ROOK));
		checked.setPiece(7, 7, new Piece(BLACK, KING));
		checked.setTurn(WHITE);
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setCanCastleKingSide(BLACK, false);
		checked.setCanCastleQueenSide(BLACK, false);
		checked.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		/*
		 * State escaped = checked.copy(); escaped.setPiece(4, 3, null);
		 * escaped.setPiece(3, 1, new Piece(WHITE, KNIGHT));
		 * escaped.setTurn(BLACK);
		 * escaped.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		 */

		Move move = new Move(new Position(4, 3), new Position(3, 1), null);
		stateChanger.makeMove(checked, move);

	}

	@Test
	public void TestEscapeFromQueenBlackByMoveAPiceInTheCheckWayAndPromoteToROOK() {

		State checked = start.copy();
		checked = clearPiece(checked).copy();

		checked.setPiece(7, 3, new Piece(WHITE, KING));
		checked.setPiece(7, 0, new Piece(BLACK, QUEEN));
		checked.setPiece(6, 2, new Piece(WHITE, PAWN));
		checked.setPiece(7, 7, new Piece(BLACK, KING));
		checked.setTurn(WHITE);
		checked.setCanCastleKingSide(WHITE, false);
		checked.setCanCastleQueenSide(WHITE, false);
		checked.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		State escaped = checked.copy();
		escaped.setPiece(6, 2, null);
		escaped.setPiece(7, 2, new Piece(WHITE, ROOK));
		escaped.setTurn(BLACK);
		escaped.setCanCastleKingSide(WHITE, false);
		escaped.setCanCastleQueenSide(WHITE, false);
		escaped.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		Move move = new Move(new Position(6, 2), new Position(7, 2), ROOK);
		stateChanger.makeMove(checked, move);

		assertEquals(checked, escaped);
	}

	/*
	 * End Tests by Bo Huang <fantasyblake1213@gmail.com>
	 */

	/*
	 * Begin Tests by Kan Wang <kerrywang881122@gmail.com>
	 */
	/**
	 * legal promotion test: move to empty piece
	 */
	@Test
	public void testPromotionToQueenEmptyPiece() {
		Move move = new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.QUEEN);
		start.setPiece(7, 0, null);
		start.setPiece(6, 0, null);
		start.setPiece(1, 0, null);
		start.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setCanCastleQueenSide(Color.BLACK, false);

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 0, new Piece(Color.WHITE, PieceKind.QUEEN));
		expected.setPiece(6, 0, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
		start = new State();
	}

	@Test
	public void testPromotionToBishopEmptyPiece() {
		Move move = new Move(new Position(6, 1), new Position(7, 1),
				PieceKind.BISHOP);
		start.setPiece(7, 1, null);
		start.setPiece(6, 1, null);
		start.setPiece(1, 1, null);
		start.setPiece(6, 1, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 1, new Piece(Color.WHITE, PieceKind.BISHOP));
		expected.setPiece(6, 1, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
		start = new State();
	}

	@Test
	public void testPromotionToKnightEmptyPiece() {
		Move move = new Move(new Position(6, 2), new Position(7, 2),
				PieceKind.KNIGHT);
		start.setPiece(7, 2, null);
		start.setPiece(6, 2, null);
		start.setPiece(1, 2, null);
		start.setPiece(6, 2, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 2, new Piece(Color.WHITE, PieceKind.KNIGHT));
		expected.setPiece(6, 2, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
		start = new State();
	}

	@Test
	public void testPromotionToRookEmptyPiece() {
		Move move = new Move(new Position(6, 3), new Position(7, 3),
				PieceKind.ROOK);
		start.setPiece(7, 3, null);
		start.setPiece(6, 3, null);
		start.setPiece(1, 3, null);
		start.setPiece(6, 4, null);
		start.setPiece(6, 3, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 3, new Piece(Color.WHITE, PieceKind.ROOK));
		expected.setPiece(6, 3, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
		start = new State();
	}

	/**
	 * test for promotion: promotion with a capture
	 */

	@Test
	public void testPromotionToQueenWithCapture() {
		Move move = new Move(new Position(6, 4), new Position(7, 5),
				PieceKind.QUEEN);
		start.setPiece(6, 4, null);
		start.setPiece(1, 4, null);
		start.setPiece(6, 4, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 5, new Piece(Color.WHITE, PieceKind.QUEEN));
		expected.setPiece(6, 4, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);

		// make sure start is the beginning state
		start = new State();
	}

	@Test
	public void testPromotionToBishopWithCapture() {
		Move move = new Move(new Position(6, 5), new Position(7, 6),
				PieceKind.BISHOP);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 6, new Piece(Color.WHITE, PieceKind.BISHOP));
		expected.setPiece(6, 5, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);

		// make sure start is the beginning state
		start = new State();
	}

	@Test
	public void testPromotionToKnightWithCapture() {
		Move move = new Move(new Position(6, 6), new Position(7, 7),
				PieceKind.KNIGHT);
		start.setPiece(6, 6, null);
		start.setPiece(1, 6, null);
		start.setPiece(6, 6, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 7, new Piece(Color.WHITE, PieceKind.KNIGHT));
		expected.setPiece(6, 6, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);
		expected.setCanCastleKingSide(Color.BLACK, false);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);

		// make sure start is the beginning state
		start = new State();
	}

	@Test
	public void testPromotionToRookWithCapture() {
		Move move = new Move(new Position(6, 7), new Position(7, 6),
				PieceKind.ROOK);
		start.setPiece(6, 7, null);
		start.setPiece(1, 7, null);
		start.setPiece(6, 7, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 6, new Piece(Color.WHITE, PieceKind.ROOK));
		expected.setPiece(6, 7, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);

		// make sure start is the beginning state
		start = new State();
	}

	// some random test of promotion to queen
	@Test
	public void testPromotionToQueenRandom1() {
		Move move = new Move(new Position(6, 3), new Position(7, 3),
				PieceKind.QUEEN);
		start.setPiece(7, 3, null);
		start.setPiece(6, 3, null);
		start.setPiece(1, 3, null);
		start.setPiece(6, 4, null);
		start.setPiece(6, 5, null);
		start.setPiece(6, 3, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 3, new Piece(Color.WHITE, PieceKind.QUEEN));
		expected.setPiece(6, 3, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
		start = new State();
	}

	// some random test of promotion to queen
	@Test
	public void testPromotionToQueenRandom2() {
		Move move = new Move(new Position(6, 5), new Position(7, 6),
				PieceKind.QUEEN);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.PAWN));

		State expected = start.copy();

		// set pieces
		expected.setPiece(7, 6, new Piece(Color.WHITE, PieceKind.QUEEN));
		expected.setPiece(6, 5, null);

		// other states do not need to be set
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setTurn(Color.BLACK);

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
		start = new State();
	}

	/**
	 * test for illegal promotion: not in (6, x)
	 */

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionWrongPosition1() {
		Move move = new Move(new Position(1, 3), new Position(3, 3),
				PieceKind.QUEEN);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionWrongPosition2() {
		start.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(1, 4, null);
		Move move = new Move(new Position(4, 4), new Position(5, 4),
				PieceKind.QUEEN);
		stateChanger.makeMove(start, move);
		start = new State();
	}

	/**
	 * test for illegal promotion: move is not possible
	 */
	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionIllegalMove1() {
		Move move = new Move(new Position(6, 5), new Position(7, 6),
				PieceKind.QUEEN);
		start.setPiece(7, 6, null);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.PAWN));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionIllegalMove2() {
		Move move = new Move(new Position(6, 5), new Position(7, 5),
				PieceKind.QUEEN);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(0, 3, null);
		start.setPiece(7, 5, new Piece(Color.WHITE, PieceKind.QUEEN));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	/**
	 * test for promote: do not promote when a pawn get (7, x)
	 */
	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionNotPromote1() {
		Move move = new Move(new Position(6, 5), new Position(7, 5),
				null);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.PAWN));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionNotPromote2() {
		Move move = new Move(new Position(6, 1), new Position(7, 1),
				null);
		start.setPiece(6, 1, null);
		start.setPiece(1, 1, null);
		start.setPiece(6, 1, new Piece(Color.WHITE, PieceKind.PAWN));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	/**
	 * test for promotion: promote to a king or to a pawn
	 */

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionWrongPromotionKind1() {
		Move move = new Move(new Position(6, 1), new Position(7, 1),
				PieceKind.PAWN);
		start.setPiece(6, 1, null);
		start.setPiece(1, 1, null);
		start.setPiece(6, 1, new Piece(Color.WHITE, PieceKind.PAWN));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionWrongPromotionKind2() {
		Move move = new Move(new Position(6, 5), new Position(7, 5),
				PieceKind.KING);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.PAWN));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	/**
	 * test for promotion: promotion when king is under check
	 */
	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionUnderCheck1() {
		Move move = new Move(new Position(6, 5), new Position(7, 5),
				PieceKind.QUEEN);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.PAWN));

		start.setPiece(1, 4, new Piece(Color.BLACK, PieceKind.ROOK));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionUnderCheck2() {
		Move move = new Move(new Position(6, 5), new Position(7, 5),
				PieceKind.QUEEN);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.PAWN));

		start.setPiece(2, 6, new Piece(Color.BLACK, PieceKind.QUEEN));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	/**
	 * test for promotion: try to promote a not-pawn piece
	 */
	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionPromoteOtherPiece1() {
		Move move = new Move(new Position(6, 5), new Position(7, 5),
				PieceKind.QUEEN);
		start.setPiece(6, 5, null);
		start.setPiece(1, 5, null);
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.ROOK));

		stateChanger.makeMove(start, move);
		start = new State();
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalPromotionPromoteOtherPiece2() {
		Move move = new Move(new Position(6, 1), new Position(7, 1),
				PieceKind.ROOK);
		start.setPiece(6, 1, null);
		start.setPiece(1, 1, null);
		start.setPiece(6, 1, new Piece(Color.WHITE, PieceKind.QUEEN));

		stateChanger.makeMove(start, move);
		start = new State();
	}


	/*
	 * End Tests by Kan Wang <kerrywang881122@gmail.com>
	 */

	/*
	 * Begin Tests by Bohou Li <bohoulee@gmail.com>
	 */

	/*
	 * Test castle when canCastleKingside is set true for white
	 */
	@Test
	public void testCanCastleKingSide() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { true, false };
		boolean[] canCastleQueenSide = new boolean[] { false, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][3] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][5] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[3][2] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[5][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[5][5] = new Piece(Color.BLACK, PieceKind.KNIGHT);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(0, 4, null);
		expected.setPiece(0, 6, new Piece(WHITE, PieceKind.KING));
		expected.setPiece(0, 7, null);
		expected.setPiece(0, 5, new Piece(WHITE, PieceKind.ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setCanCastleKingSide(Color.WHITE, false);
		expected.setCanCastleKingSide(Color.BLACK, false);
		expected.setCanCastleQueenSide(Color.WHITE, false);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	/*
	 * Test castle when canCastleQueenside is set true for white
	 */
	@Test
	public void testCanCastleQueenSide() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { false, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][1] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[2][2] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[2][4] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[5][1] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[5][2] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[5][4] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(0, 4, null);
		expected.setPiece(0, 2, new Piece(WHITE, PieceKind.KING));
		expected.setPiece(0, 0, null);
		expected.setPiece(0, 3, new Piece(WHITE, PieceKind.ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setCanCastleKingSide(Color.WHITE, false);
		expected.setCanCastleQueenSide(Color.WHITE, false);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	/*
	 * Test castle when canCastleKingside and canCastleQueenside are both set
	 * true for white
	 */
	@Test
	public void testCanCastleBothSide() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][1] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[2][2] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[2][4] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[5][1] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[5][2] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[5][4] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setPiece(0, 4, null);
		expected.setPiece(0, 2, new Piece(WHITE, PieceKind.KING));
		expected.setPiece(0, 0, null);
		expected.setPiece(0, 3, new Piece(WHITE, PieceKind.ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setCanCastleKingSide(Color.WHITE, false);
		expected.setCanCastleQueenSide(Color.WHITE, false);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	/*
	 * Test castle when canCastleKingSide is set false for white
	 */
	@Test(expected = IllegalMove.class)
	public void testCannotCastleKingSide() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { false, false };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][3] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][5] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[3][2] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[5][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[5][5] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[4][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test Castle when canCastleQueenside is set false for white
	 */
	@Test(expected = IllegalMove.class)
	public void testCannotCastleQueenSide() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { false, false };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][1] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[2][2] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[2][4] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[5][1] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[5][2] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[5][4] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test Castle when canCastleKingside and canCastleQueenside are both set
	 * false for white
	 */
	@Test(expected = IllegalMove.class)
	public void testCannotCastleBothSide() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { false, true };
		boolean[] canCastleQueenSide = new boolean[] { false, false };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][1] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[2][2] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[2][4] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[5][1] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[5][2] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[5][4] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white king side Castle when the sixth square is occupied by white
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleKingSideSixthSquareOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][3] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[3][2] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[4][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white queen side Castle when the second square is occupied by white
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideSecondSquareOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][0] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[2][1] = new Piece(Color.WHITE, PieceKind.QUEEN);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[3][6] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white king side Castle when the fifth square is occupied by white
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleKingSideFifthSquareOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][3] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][5] = new Piece(Color.WHITE, PieceKind.KNIGHT);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[4][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white queen side Castle when the first square is occupied by white
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideFirstSquareOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][1] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[4][6] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[3][6] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white queen side Castle when the third square is occupied by white
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideThirdSquareOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][3] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][0] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[4][6] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[3][6] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white king side Castle when king is under check
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleKingSideBeingChecked() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][3] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][5] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[5][0] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[6][4] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[4][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[2][2] = new Piece(Color.BLACK, PieceKind.QUEEN);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white queen side Castle when king is under check
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideBeingChecked() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[2][0] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[2][1] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[4][6] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[6][4] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[4][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[3][6] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[2][2] = new Piece(Color.BLACK, PieceKind.QUEEN);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white king side Castle when the king will be under check if it
	 * castle
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleKingSideTargetChecked() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][3] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[5][0] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[6][4] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[4][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[3][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white queen side Castle when the king will be under check if it
	 * castle
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideTargetChecked() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[1][4] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[2][0] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[4][6] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[5][5] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[4][2] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[3][6] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white king side Castle when the square in the middle is under check
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleKingSideFifthSquareChecked() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
			board[0][3] = new Piece(Color.WHITE, PieceKind.QUEEN);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[2][5] = new Piece(Color.BLACK, PieceKind.QUEEN);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white queen side Castle when the square in the middle is under check
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideThirdSquareChecked() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[5][5] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[4][2] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[3][6] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white king side Castle when the fifth square is occupied by black
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleKingSideFifthSquareBlackOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[5][0] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[0][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white king side Castle when the fifth square is occupied by black
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideThirdSquareBlackOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[5][0] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[0][3] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white king side Castle when the sixth square is occupied by black
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleKingSideSixthSquareBlackOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][1] = new Piece(Color.WHITE, PieceKind.KNIGHT);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[5][0] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[0][6] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white queen side Castle when the second square is occupied by black
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideSecondSquareBlackOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[5][0] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[0][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * Test white queen side Castle when the first square is occupied by black
	 */
	@Test(expected = IllegalMove.class)
	public void testCastleQueenSideFirstSquareBlackOccupied() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };

		Piece[][] board = new Piece[8][8];
		{
			board[0][0] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
			board[0][7] = new Piece(Color.WHITE, PieceKind.ROOK);
			board[5][0] = new Piece(Color.WHITE, PieceKind.BISHOP);

			board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
			board[7][1] = new Piece(Color.BLACK, PieceKind.KNIGHT);
			board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
			board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
			board[0][1] = new Piece(Color.BLACK, PieceKind.BISHOP);
		}

		start = new State(Color.WHITE, board, canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * End Tests by Bohou Li <bohoulee@gmail.com>
	 */


	/* Begin Tests by Wenjie Chen <cwjcourage@gmail.com> Test StateChanger
	 * Castling For White
	 */
	public void initForWenjieChen() {
		start.setTurn(WHITE);
		// removing all pieces, it's convenient for testcase
		for (int col = 0; col < 8; ++col) {
			for (int row = 0; row < 8; ++row) {
				start.setPiece(row, col, null);
			}
		}
		// put KING and ROOKs in original positions
		start.setPiece(0, 4, new Piece(WHITE, KING));
		start.setPiece(0, 7, new Piece(WHITE, ROOK));
		start.setPiece(0, 0, new Piece(WHITE, ROOK));
	}

	@Test
	public void testBothSidesRookCanCastleExpectTrue() {
		super.setup();
		State expectedState = start.copy();
		expectedState.setCanCastleKingSide(WHITE, true);
		expectedState.setCanCastleQueenSide(WHITE, true);
		assertEquals(expectedState, start);
	}

	/*
	 * End Tests by Wenjie Chen <cwjcourage@gmail.com>
	 */

	/*
	 * Begin Tests by Ashish Manral <ashish.manral09@gmail.com>
	 */

	@Test(expected = IllegalMove.class)
	public void testRookIllegalMovement() {
		start.setPiece(1, 1, null);
		Move move = new Move(new Position(0, 0), new Position(1, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalRookJumpingOverOtherPiece() {
		Move move = new Move(new Position(0, 0), new Position(3, 0), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testRookMovesToSquareOccupiedBySameColor() {
		Move move = new Move(new Position(0, 0), new Position(1, 0), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testRookMovesAfterGameAlreadyOver() {
		start.setPiece(1, 0, null);
		start.setGameResult(new GameResult(WHITE, GameResultReason.CHECKMATE));
		Move move = new Move(new Position(0, 0), new Position(3, 0), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalRookMoveOnCheck() {
		Piece whiteRook = start.getPiece(0, 0);
		Piece blackRook = start.getPiece(7, 0);
		start.setPiece(0, 0, null);
		start.setPiece(0, 1, null);
		start.setPiece(0, 2, null);
		start.setPiece(0, 3, null);
		start.setPiece(1, 0, null);
		start.setPiece(1, 2, null);
		start.setPiece(7, 0, null);
		start.setPiece(6, 0, null);
		start.setPiece(0, 0, blackRook);
		start.setPiece(1, 2, whiteRook);
		Move move = new Move(new Position(1, 2), new Position(2, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testRookCapturesPiece() {
		State someState = start.copy();
		someState.setPiece(1, 0, null);
		Piece whiteRook = someState.getPiece(0, 0);
		State expected = someState.copy();
		expected.setPiece(6, 0, whiteRook);
		expected.setPiece(0, 0, null);
		expected.setTurn(BLACK);
		expected.setCanCastleQueenSide(WHITE, false);
		Move move = new Move(new Position(0, 0), new Position(6, 0), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testRookMovement() {
		State someState = start.copy();
		Piece whiteRook = someState.getPiece(0, 0);
		someState.setPiece(1, 0, null);
		State expected = someState.copy();
		expected.setPiece(0, 0, null);
		expected.setPiece(4, 0, whiteRook);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setTurn(BLACK);
		expected.setCanCastleQueenSide(WHITE, false);
		Move move = new Move(new Position(0, 0), new Position(4, 0), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testRookMovesAndGameDraws() {
		State someState = start.copy();
		Piece whiteRook = someState.getPiece(0, 0);
		someState.setPiece(1, 0, null);
		someState.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		State expected = someState.copy();
		expected.setPiece(0, 0, null);
		expected.setPiece(4, 0, whiteRook);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));
		expected.setTurn(BLACK);
		Move move = new Move(new Position(0, 0), new Position(4, 0), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test(expected = IllegalMove.class)
	public void testKnightIllegalMovement() {
		Move move = new Move(new Position(0, 1), new Position(3, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testKnightMovesToSquareOccupiedBySameColor() {
		Move move = new Move(new Position(0, 1), new Position(1, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testKnightMovesAfterGameAlreadyOver() {
		start.setGameResult(new GameResult(WHITE, GameResultReason.CHECKMATE));
		Move move = new Move(new Position(0, 1), new Position(2, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKnightMoveOnCheck() {
		Piece whiteKnight = start.getPiece(0, 1);
		Piece blackRook = start.getPiece(7, 0);
		start.setPiece(0, 0, null);
		start.setPiece(0, 1, null);
		start.setPiece(0, 2, null);
		start.setPiece(0, 3, null);
		start.setPiece(1, 0, null);
		start.setPiece(7, 0, null);
		start.setPiece(6, 0, null);
		start.setPiece(0, 0, blackRook);
		start.setPiece(1, 1, whiteKnight);
		Move move = new Move(new Position(1, 1), new Position(3, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testKnightCapturesPiece() {
		State someState = start.copy();
		Piece whiteKnight = someState.getPiece(0, 1);
		Piece blackPawn = someState.getPiece(6, 3);
		someState.setPiece(0, 1, null);
		someState.setPiece(6, 3, null);
		someState.setPiece(2, 2, whiteKnight);
		someState.setPiece(4, 3, blackPawn);
		State expected = someState.copy();
		expected.setPiece(2, 2, null);
		expected.setPiece(4, 3, whiteKnight);
		expected.setTurn(BLACK);
		Move move = new Move(new Position(2, 2), new Position(4, 3), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testKnightMovement() {
		State someState = start.copy();
		Piece whiteKnight = someState.getPiece(0, 1);
		State expected = someState.copy();
		expected.setPiece(0, 1, null);
		expected.setPiece(2, 0, whiteKnight);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setTurn(BLACK);
		Move move = new Move(new Position(0, 1), new Position(2, 0), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testKnightMovesAndGameDraws() {
		State someState = start.copy();
		Piece whiteKnight = someState.getPiece(0, 1);
		someState.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		State expected = someState.copy();
		expected.setPiece(0, 1, null);
		expected.setPiece(2, 0, whiteKnight);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));
		expected.setTurn(BLACK);
		Move move = new Move(new Position(0, 1), new Position(2, 0), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test(expected = IllegalMove.class)
	public void testBishopIllegalMovement() {
		start.setPiece(1, 3, null);
		Move move = new Move(new Position(0, 2), new Position(3, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testBishopMovesToSquareOccupiedBySameColor() {
		Move move = new Move(new Position(0, 2), new Position(1, 1), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testBishopMovesAfterGameAlreadyOver() {
		start.setGameResult(new GameResult(WHITE, GameResultReason.CHECKMATE));
		start.setPiece(1, 3, null);
		Move move = new Move(new Position(0, 2), new Position(1, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalBishopMoveOnCheck() {
		Piece whiteBishop = start.getPiece(0, 2);
		Piece blackRook = start.getPiece(7, 0);
		start.setPiece(0, 0, null);
		start.setPiece(0, 1, null);
		start.setPiece(0, 2, null);
		start.setPiece(0, 3, null);
		start.setPiece(1, 0, null);
		start.setPiece(7, 0, null);
		start.setPiece(6, 0, null);
		start.setPiece(0, 0, blackRook);
		start.setPiece(1, 1, whiteBishop);
		Move move = new Move(new Position(1, 1), new Position(2, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testBishopCapturesPiece() {
		State someState = start.copy();
		Piece whiteBishop = someState.getPiece(0, 2);
		Piece blackPawn = someState.getPiece(6, 3);
		someState.setPiece(1, 1, null);
		someState.setPiece(6, 3, null);
		someState.setPiece(0, 2, null);
		someState.setPiece(5, 3, blackPawn);
		someState.setPiece(2, 0, whiteBishop);
		State expected = someState.copy();
		expected.setPiece(2, 0, null);
		expected.setPiece(5, 3, whiteBishop);
		expected.setTurn(BLACK);
		Move move = new Move(new Position(2, 0), new Position(5, 3), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testBishopMovement() {
		State someState = start.copy();
		Piece whiteBishop = someState.getPiece(0, 2);
		someState.setPiece(1, 1, null);
		State expected = someState.copy();
		expected.setPiece(0, 2, null);
		expected.setPiece(2, 0, whiteBishop);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setTurn(BLACK);
		Move move = new Move(new Position(0, 2), new Position(2, 0), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testBishopMovesAndGameDraws() {
		State someState = start.copy();
		Piece whiteBishop = someState.getPiece(0, 2);
		someState.setPiece(1, 1, null);
		someState.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		State expected = someState.copy();
		expected.setPiece(0, 2, null);
		expected.setPiece(2, 0, whiteBishop);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));
		expected.setTurn(BLACK);
		Move move = new Move(new Position(0, 2), new Position(2, 0), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test(expected = IllegalMove.class)
	public void testQueenIllegalsMovement() {
		start.setPiece(1, 3, null);
		Move move = new Move(new Position(0, 3), new Position(3, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testQueenMovesToSquareOccupiedBySameColor() {
		Move move = new Move(new Position(0, 3), new Position(1, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testQueenMovesAfterGameAlreadyOver() {
		start.setGameResult(new GameResult(WHITE, GameResultReason.CHECKMATE));
		start.setPiece(1, 3, null);
		Move move = new Move(new Position(0, 3), new Position(1, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalQueenMoveOnCheck() {
		Piece whiteQueen = start.getPiece(0, 3);
		Piece blackRook = start.getPiece(7, 0);
		start.setPiece(0, 0, null);
		start.setPiece(0, 1, null);
		start.setPiece(0, 2, null);
		start.setPiece(0, 3, null);
		start.setPiece(1, 0, null);
		start.setPiece(7, 0, null);
		start.setPiece(6, 0, null);
		start.setPiece(0, 0, blackRook);
		start.setPiece(1, 3, whiteQueen);
		Move move = new Move(new Position(1, 3), new Position(2, 3), null);
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testQueenCapturesPiece() {
		State someState = start.copy();
		Piece whiteQueen = someState.getPiece(0, 3);
		someState.setPiece(1, 3, null);
		State expected = someState.copy();
		expected.setPiece(0, 3, null);
		expected.setPiece(6, 3, whiteQueen);
		expected.setTurn(BLACK);
		Move move = new Move(new Position(0, 3), new Position(6, 3), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testQueenMovement() {
		State someState = start.copy();
		Piece whiteQueen = someState.getPiece(0, 3);
		someState.setPiece(1, 3, null);
		State expected = someState.copy();
		expected.setPiece(0, 3, null);
		expected.setPiece(2, 3, whiteQueen);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setTurn(BLACK);
		Move move = new Move(new Position(0, 3), new Position(2, 3), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testQueenMovesAndGameEnds() {
		State someState = start.copy();
		Piece whiteQueen = someState.getPiece(0, 3);
		someState.setPiece(1, 3, null);
		someState.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		State expected = someState.copy();
		expected.setPiece(0, 3, null);
		expected.setPiece(2, 3, whiteQueen);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));
		expected.setTurn(BLACK);
		Move move = new Move(new Position(0, 3), new Position(2, 3), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test(expected = IllegalMove.class)
	public void testKingIllegalMovement() {
		start.setPiece(1, 4, null);
		Move move = new Move(new Position(0, 4), new Position(2, 2), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testKingMovesToSquareOccupiedBySameColor() {
		Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testKingMovesAfterGameAlreadyOver() {
		start.setGameResult(new GameResult(WHITE, GameResultReason.CHECKMATE));
		start.setPiece(1, 4, null);
		Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testKingCapturesPiece() {
		State someState = start.copy();
		Piece whiteKing = someState.getPiece(0, 4);
		Piece blackPawn = someState.getPiece(6, 4);
		someState.setPiece(6, 4, null);
		someState.setPiece(1, 4, blackPawn);
		State expected = someState.copy();
		expected.setPiece(0, 4, null);
		expected.setPiece(1, 4, whiteKing);
		expected.setTurn(BLACK);
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testKingMovement() {
		State someState = start.copy();
		Piece whiteKing = someState.getPiece(0, 4);
		someState.setPiece(1, 4, null);
		State expected = someState.copy();
		expected.setPiece(0, 4, null);
		expected.setPiece(1, 4, whiteKing);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setTurn(BLACK);
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	@Test
	public void testKingMovesAndGameEnds() {
		State someState = start.copy();
		Piece whiteKing = someState.getPiece(0, 4);
		someState.setPiece(1, 4, null);
		someState.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		State expected = someState.copy();
		expected.setPiece(0, 4, null);
		expected.setPiece(1, 4, whiteKing);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));
		expected.setTurn(BLACK);
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		stateChanger.makeMove(someState, move);
		assertEquals(expected, someState);
	}

	/*
	 * End Tests by Ashish Manral <ashish.manral09@gmail.com>
	 */

	/*
	 * Begin Tests by Longjun Tan <longjuntan@gmail.com>
	 */

	private void clearBoardForLT(){
		for (int row : new int[] { 0, 1, 6, 7 }) {
			for (int col = 0; col < State.COLS; col++) {
				start.setPiece(row, col, null);
			}
		}

		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleKingSide(BLACK, false);
		start.setCanCastleQueenSide(WHITE, false);
		start.setCanCastleQueenSide(BLACK, false);
	}

	@Test
	public void testInitialValueShouldBeNull() {
		assertEquals(null, start.getEnpassantPosition());
	}

	@Test
	public void testPositionWhenMoveBlackPawnOneSquare() {
		// First white movement, helping to change turn to black
		stateChanger.makeMove(start, new Move(new Position(1, 0), new Position(
				2, 0), null));

		Move move = new Move(new Position(6, 0), new Position(5, 0), null);
		stateChanger.makeMove(start, move);
		assertEquals(null, start.getEnpassantPosition());
	}

	@Test
	public void testPositionWhenMoveBlackPawnTwoSquares() {
		stateChanger.makeMove(start, new Move(new Position(1, 0), new Position(
				2, 0), null));

		Move move = new Move(new Position(6, 0), new Position(4, 0), null);
		stateChanger.makeMove(start, move);
		assertEquals(move.getTo(), start.getEnpassantPosition());
	}

	@Test
	public void testRowWhenMoveBlackPawnTwoSquares() {
		start.setTurn(BLACK);

		Move move = new Move(new Position(6, 2), new Position(4, 2), null);
		stateChanger.makeMove(start, move);

		assertEquals(4, start.getEnpassantPosition().getRow());
	}

	@Test
	public void testPieceKindWhenMoveBlackPawnTwoSquares() {
		stateChanger.makeMove(start, new Move(new Position(1, 0), new Position(
				2, 0), null));

		Move move = new Move(new Position(6, 1), new Position(4, 1), null);
		stateChanger.makeMove(start, move);
		assertEquals(PAWN, start.getPiece(start.getEnpassantPosition())
				.getKind());
	}

	public void testColorWhenMoveBlackPawnTwoSquares() {
		stateChanger.makeMove(start, new Move(new Position(1, 0), new Position(
				2, 0), null));

		Move move = new Move(new Position(6, 5), new Position(4, 5), null);
		stateChanger.makeMove(start, move);
		assertEquals(BLACK, start.getPiece(start.getEnpassantPosition())
				.getColor());
	}

	@Test
	public void testEnpassantForfeitWithoutCapture() {
		start.setTurn(BLACK);

		Move move = new Move(new Position(6, 5), new Position(4, 5), null);
		stateChanger.makeMove(start, move);

		assertEquals(move.getTo(), start.getEnpassantPosition());

		// Didn't capture that black pawn which just moved two squares,
		// so it forfeit after this non-enpassant movement
		move = new Move(new Position(1, 1), new Position(2, 1), null);
		stateChanger.makeMove(start, move);

		assertEquals(null, start.getEnpassantPosition());
	}

	@Test
	public void testEnpassantPositionChanges() {
		Move move = new Move(new Position(1, 1), new Position(3, 1), null);
		stateChanger.makeMove(start, move);

		assertEquals(move.getTo(), start.getEnpassantPosition());

		move = new Move(new Position(6, 5), new Position(4, 5), null);
		stateChanger.makeMove(start, move);

		assertEquals(move.getTo(), start.getEnpassantPosition());
	}

	@Test
	public void testEnpassantFromLeftSide() {
		// Black pawn captures white pawn by enpassant from left side
		start.setPiece(6, 5, null);
		start.setPiece(3, 5, new Piece(BLACK, PAWN));
		State expected = start.copy();

		expected.setPiece(1, 6, null);
		expected.setPiece(3, 5, null);
		expected.setPiece(2, 6, new Piece(BLACK, PAWN));

		Move move = new Move(new Position(1, 6), new Position(3, 6), null);
		stateChanger.makeMove(start, move);

		assertEquals(move.getTo(), start.getEnpassantPosition());

		move = new Move(new Position(3, 5), new Position(2, 6), null);
		stateChanger.makeMove(start, move);

		assertEquals(expected, start);
	}

	@Test
	public void testNumberOfMovesWithoutCaptureNorPawnMovedWhenEnpassantHappens() {
		start.setPiece(6, 5, null);
		start.setPiece(3, 5, new Piece(BLACK, PAWN));
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(42);

		Move move = new Move(new Position(1, 4), new Position(3, 4), null);
		stateChanger.makeMove(start, move);
		assertEquals(0, start.getNumberOfMovesWithoutCaptureNorPawnMoved());

		move = new Move(new Position(3, 5), new Position(2, 4), null);
		stateChanger.makeMove(start, move);
		assertEquals(0, start.getNumberOfMovesWithoutCaptureNorPawnMoved());
	}

	@Test(expected = IllegalMove.class)
	public void testEnpassantForfeitWithCapture() {
		start.setPiece(6, 5, null);
		start.setPiece(3, 5, new Piece(BLACK, PAWN));
		start.setPiece(1, 4, null);
		start.setPiece(3, 4, new Piece(WHITE, PAWN));

		start.setEnpassantPosition(null);

		Move move = new Move(new Position(3, 5), new Position(2, 4), null);
		stateChanger.makeMove(start, move);
	}

	@Test
	public void testOtherMovementsWontAffectEnpassantPosition_Rook() {
		start.setPiece(6, 7, null);
		start.setPiece(4, 7, new Piece(BLACK, PAWN));

		start.setTurn(BLACK);
		stateChanger.makeMove(start, new Move(new Position(7, 7), new Position(
				5, 7), null));
		assertEquals(null, start.getEnpassantPosition());
	}

	@Test
	public void testOtherMovementsWontAffectEnpassantPosition_Knight() {
		start.setTurn(BLACK);
		stateChanger.makeMove(start, new Move(new Position(7, 1), new Position(
				5, 0), null));
		assertEquals(null, start.getEnpassantPosition());
	}

	@Test
	public void testOtherMovementsWontAffectEnpassantPosition_Bishop() {
		start.setPiece(6, 1, null);
		start.setPiece(5, 1, new Piece(BLACK, PAWN));

		start.setTurn(BLACK);
		stateChanger.makeMove(start, new Move(new Position(7, 2), new Position(
				6, 1), null));
		assertEquals(null, start.getEnpassantPosition());
	}

	@Test
	public void testOtherMovementsWontAffectEnpassantPosition_Queen() {
		start.setPiece(6, 3, null);
		start.setPiece(4, 3, new Piece(BLACK, PAWN));

		start.setTurn(BLACK);
		stateChanger.makeMove(start, new Move(new Position(7, 3), new Position(
				6, 3), null));
		assertEquals(null, start.getEnpassantPosition());
	}

	@Test
	public void testOtherMovementsWontAffectEnpassantPosition_King() {
		start.setPiece(6, 5, null);
		start.setPiece(4, 5, new Piece(BLACK, PAWN));

		start.setTurn(BLACK);
		stateChanger.makeMove(start, new Move(new Position(7, 4), new Position(
				6, 5), null));
		assertEquals(null, start.getEnpassantPosition());
	}

	@Test(expected = IllegalMove.class)
	public void testCannotEnpassant_CannotCaptureKnight() {
		start.setPiece(0, 6, null);
		start.setPiece(3, 5, new Piece(WHITE, KNIGHT));

		start.setPiece(6, 6, null);
		start.setPiece(3, 6, new Piece(BLACK, PAWN));

		Move move = new Move(new Position(1, 7), new Position(3, 7), null);
		stateChanger.makeMove(start, move);

		assertEquals(move.getTo(), start.getEnpassantPosition());

		// Black pawn tried to enpassant a white knight, but failed
		move = new Move(new Position(3, 6), new Position(2, 5), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testCannotEnpassant_ExposeKing() {
		clearBoardForLT();

		start.setPiece(0, 3, new Piece(WHITE, KING));
		start.setPiece(3, 5, new Piece(WHITE, PAWN));
		start.setEnpassantPosition(new Position(3, 5));
		start.setPiece(2, 4, new Piece(WHITE, ROOK));
		start.setPiece(3, 4, new Piece(BLACK, PAWN));
		start.setPiece(7, 4, new Piece(BLACK, KING));

		start.setTurn(BLACK);

		// This enpassant would expose black King to white Rook.
		Move move = new Move(new Position(3, 4), new Position(2, 5), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testCannotEnpassant_UnderCheck() {
		clearBoardForLT();

		start.setPiece(0, 3, new Piece(WHITE, KING));
		start.setPiece(3, 5, new Piece(WHITE, PAWN));
		start.setEnpassantPosition(new Position(3, 5));
		start.setPiece(5, 4, new Piece(WHITE, ROOK));
		start.setPiece(3, 4, new Piece(BLACK, PAWN));
		start.setPiece(7, 4, new Piece(BLACK, KING));

		start.setTurn(BLACK);

		// The black King is under check by white rook,
		// so this enpassant is illegal.
		Move move = new Move(new Position(3, 4), new Position(2, 5), null);
		stateChanger.makeMove(start, move);
	}

	/*
	 * End Tests by Longjun Tan <longjuntan@gmail.com>
	 */

	/*
	 * Begin Tests by Shitian Ren <renshitian@gmail.com>
	 */

	/*@Test
	public void testBlackKnightLegalMovement()  {
		//create an initial board with several Pieces
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,KNIGHT));
		before.setPiece(3, 3, new Piece(WHITE,PAWN));
		before.setPiece(3, 4, new Piece(BLACK,PAWN));
		before.setPiece(4,4,new Piece(WHITE,PAWN));

		Move move = new Move(new Position(4,3),new Position(2,4),null);

		//change the position of several Pieces to make it as expected
		State before1 = before.copy();
		State expected1 = before1.copy();

		expected1.setTurn(WHITE);
		expected1.setPiece(4, 3, null);
		expected1.setPiece(2,4,new Piece(BLACK,KNIGHT));
	    expected1.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    stateChanger.makeMove(before1, move);
	    assertEquals(before1,expected1);

	    //test for movement of black knight to position occupied by one white pawn
	    State before2 = before.copy();	    
	    before2.setPiece(3, 3, null);
	    before2.setPiece(2,4,new Piece(WHITE,PAWN));
	    State expected2 = before2.copy();

	    expected2.setTurn(WHITE);
		expected2.setPiece(4, 3, null);
		expected2.setPiece(2,4,new Piece(BLACK,KNIGHT));
	    expected2.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    stateChanger.makeMove(before2, move);
	    assertEquals(before2,expected2);

	    //test for movement of black knight to unoccupied position
		State before3 = before.copy();
		State expected3 = before3.copy();
		Move move2 = new Move(new Position(4,3),new Position(3,1),null);
		expected3.setTurn(WHITE);
		expected3.setPiece(4, 3, null);
		expected3.setPiece(3,1,new Piece(BLACK,KNIGHT));
	    expected3.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    stateChanger.makeMove(before3, move2);
	    assertEquals(before3,expected3);

	}


	//test for illegal movement of black knight to position occupied with same color piece 
	@Test(expected = IllegalMove.class)
	public void testBlackKnightMoveToBlackPiece() {
		Move move = new Move(new Position(4,3),new Position(2,4),null);
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,KNIGHT));
		before.setPiece(3, 3, new Piece(WHITE,PAWN));
		before.setPiece(2, 4, new Piece(BLACK,PAWN));
		before.setPiece(4,4,new Piece(WHITE,PAWN));


		stateChanger.makeMove(before, move);
	}

	//test for illegal movement of black knight to a square unoccupied position
	@Test(expected = IllegalMove.class)
	public void testBlackKnightMoveSquare() {
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,KNIGHT));
		before.setPiece(3, 3, new Piece(WHITE,PAWN));
		before.setPiece(3, 4, new Piece(BLACK,PAWN));
		before.setPiece(4,4,new Piece(WHITE,PAWN));

		Move move = new Move(new Position(4,3),new Position(5,2),null);

		stateChanger.makeMove(before, move);
	}


	//test for movement of black knight to a diagonal position
	@Test(expected = IllegalMove.class)
	public void testBlackKnightMoveDiagonal() {
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,KNIGHT));
		before.setPiece(3, 3, new Piece(WHITE,PAWN));
		before.setPiece(3, 4, new Piece(BLACK,PAWN));
		before.setPiece(4,4,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(4,3),new Position(2,1),null);

		stateChanger.makeMove(before, move);
	}


	//test for movement of black knight to a vertical position
	@Test(expected = IllegalMove.class)
	public void testBlackKnightMoveVertical() {
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,KNIGHT));
		before.setPiece(3, 3, new Piece(WHITE,PAWN));
		before.setPiece(3, 4, new Piece(BLACK,PAWN));
		before.setPiece(4,4,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(4,3),new Position(6,3),null);

		stateChanger.makeMove(before, move);
	}

	//test for movement of black knight to a horizontal position
	@Test(expected = IllegalMove.class)
	public void testBlackKnightMoveHorizontal() {
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,KNIGHT));
		before.setPiece(3, 3, new Piece(WHITE,PAWN));
		before.setPiece(3, 4, new Piece(BLACK,PAWN));
		before.setPiece(4,4,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(4,3),new Position(4,0),null);

		stateChanger.makeMove(before, move);
	}


	//test for movement of black bishop
	@Test
	public void testBlackBishopLegalMove() {
		//setup the state board
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0,4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,BISHOP));
		before.setPiece(2,1, new Piece(WHITE,PAWN));
		before.setPiece(7,6, new Piece(BLACK,KNIGHT));

		//test for movement of black bishop to unoccupied position
		State before1 = before.copy();
		State expected1 = before1.copy();
		Move move1 = new Move(new Position(4,3),new Position(6,1),null);


		expected1.setTurn(WHITE);
		expected1.setPiece(4,3, null);
		expected1.setPiece(6,1,new Piece(BLACK,BISHOP));
	    expected1.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    stateChanger.makeMove(before1, move1);
	    assertEquals(before1,expected1);

	    //test for movement of black bishop to occupied position by white piece
		State before2 = before.copy();
		State expected2 = before2.copy();
		Move move2 = new Move(new Position(4,3),new Position(2,1),null);

		expected2.setTurn(WHITE);
		expected2.setPiece(4,3, null);
		expected2.setPiece(2,1,new Piece(BLACK,BISHOP));
	    expected2.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    stateChanger.makeMove(before2, move2);
	    assertEquals(before2,expected2);

	    //test for movement of black biship to unoccupied position
		State before3 = before.copy();
		State expected3 = before3.copy();
		Move move3 = new Move(new Position(4,3),new Position(6,5),null);

		expected3.setTurn(WHITE);
		expected3.setPiece(4,3, null);
		expected3.setPiece(6,5,new Piece(BLACK,BISHOP));
	    expected3.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    stateChanger.makeMove(before3, move3);
	    assertEquals(before3,expected3);
	}

	//test for movement of black bishop to vertical position
	@Test(expected = IllegalMove.class)
	public void testBlackBishopMoveVertical(){
		Move move = new Move(new Position(4,3),new Position(2,3),null);
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0,4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,BISHOP));
		before.setPiece(2,1, new Piece(WHITE,PAWN));
		before.setPiece(7,6, new Piece(BLACK,KNIGHT));

		stateChanger.makeMove(before, move);		
	}

	//test for movement of black bishop to horizontal position
	@Test(expected = IllegalMove.class)
	public void testBlackBishopMoveHorizontal(){
		Move move = new Move(new Position(4,3),new Position(4,6),null);
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0,4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,BISHOP));
		before.setPiece(2,1, new Piece(WHITE,PAWN));
		before.setPiece(7,6, new Piece(BLACK,KNIGHT));

		stateChanger.makeMove(before, move);		
	}

	//test for movement of black bishop to non-diagonal position
	@Test(expected = IllegalMove.class)
	public void testBlackBishopMoveNotDiagonal(){
		Move move = new Move(new Position(4,3),new Position(6,6),null);
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0,4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,BISHOP));
		before.setPiece(2,1, new Piece(WHITE,PAWN));
		before.setPiece(7,6, new Piece(BLACK,KNIGHT));

		stateChanger.makeMove(before, move);		
	}

	//test for movement of black bishop over piece
	@Test(expected = IllegalMove.class)
	public void testBlackBishopMoveOverPiece(){
		Move move = new Move(new Position(4,3),new Position(1,0),null);
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0,4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,BISHOP));
		before.setPiece(2,1, new Piece(WHITE,PAWN));
		before.setPiece(7,6, new Piece(BLACK,KNIGHT));

		stateChanger.makeMove(before, move);		
	}


	//test for movement of black bishop to position occupied by piece with same color
	@Test(expected = IllegalMove.class)
	public void testBlackBishopMoveToPositionOfSameColor(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0,4, new Piece(WHITE,KING));
		before.setPiece(7,4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,BISHOP));
		before.setPiece(2,1, new Piece(WHITE,PAWN));
		before.setPiece(7,6, new Piece(BLACK,KNIGHT));
		Move move = new Move(new Position(4,3),new Position(7,6),null);

		stateChanger.makeMove(before, move);		
	}


	//test for legal movement of black rook
	@Test
	public void testBlackRookLegalMove(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);
		before.setPiece(0,4,new Piece(WHITE,KING));
		before.setPiece(7, 4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,ROOK));
		before.setPiece(3, 3, new Piece(BLACK,KNIGHT));
		before.setPiece(4,6,new Piece(WHITE,PAWN));


		//test for movement of black rook to unoccupied position
		State before1 = before.copy();
		State except1 = before1.copy();
		Move move1 = new Move(new Position(4,3),new Position(4,0),null);

		stateChanger.makeMove(before1, move1);
		except1.setPiece(4, 3, null);
		except1.setPiece(4, 0, new Piece(BLACK,ROOK));
		except1.setTurn(WHITE);
		except1.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(except1,before1);

		//test for movement of black rook to position occupied by op piece
		State before2 = before.copy();
		State except2 = before2.copy();
		Move move2 = new Move(new Position(4,3),new Position(4,6),null);

		stateChanger.makeMove(before2, move2);
		except2.setPiece(4, 3, null);
		except2.setPiece(4, 6, new Piece(BLACK,ROOK));
		except2.setTurn(WHITE);
		except2.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		assertEquals(except2,before2);


	}

	//test for movement of black rook to position occupied by piece with same color
	@Test(expected = IllegalMove.class)
	public void testBlackRookMoveToPositionOfSameColor(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);
		before.setPiece(0,4,new Piece(WHITE,KING));
		before.setPiece(7, 4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,ROOK));
		before.setPiece(3, 3, new Piece(BLACK,KNIGHT));
		before.setPiece(4,6,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(4,3),new Position(3,3),null);

		stateChanger.makeMove(before, move);		
	}

	//test for movement of black rook to non-vertical and non-horizontal position
	@Test(expected = IllegalMove.class)
	public void testBlackRookNotMoveVerticalNorHorizontal(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);
		before.setPiece(0,4,new Piece(WHITE,KING));
		before.setPiece(7, 4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,ROOK));
		before.setPiece(3, 3, new Piece(BLACK,KNIGHT));
		before.setPiece(4,6,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(4,3),new Position(2,2),null);


		stateChanger.makeMove(before, move);		
	}

	//test for movement of black rook over op piece
	@Test(expected = IllegalMove.class)
	public void testBlackRookMoveOverOpPiece(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);
		before.setPiece(0,4,new Piece(WHITE,KING));
		before.setPiece(7, 4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,ROOK));
		before.setPiece(3, 3, new Piece(BLACK,KNIGHT));
		before.setPiece(4,6,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(4,3),new Position(4,7),null);

		stateChanger.makeMove(before, move);		
	}

	//test for movement of black rook over piece with same color
	@Test(expected = IllegalMove.class)
	public void testBlackRookMoveOverSamePiece(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);
		before.setPiece(0,4,new Piece(WHITE,KING));
		before.setPiece(7, 4, new Piece(BLACK,KING));
		before.setPiece(4,3, new Piece(BLACK,ROOK));
		before.setPiece(3, 3, new Piece(BLACK,KNIGHT));
		before.setPiece(4,6,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(4,3),new Position(1,3),null);


		stateChanger.makeMove(before, move);		
	}



	//test for legal movement of black queen 
	@Test
	public void testBlackQueenLegalMove(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7, 4,new Piece(BLACK,KING));
		before.setPiece(3, 3,new Piece(BLACK,QUEEN));
		before.setPiece(5, 1,new Piece(BLACK,PAWN));
		before.setPiece(3, 7,new Piece(WHITE,PAWN));		
		//test for movement of black queen to unoccupied vertical position
		State before1 = before.copy();
		State expected1 = before1.copy();
		Move move1 = new Move(new Position(3,3),new Position(6,3),null);

		expected1.setPiece(3, 3,null);
		expected1.setPiece(6, 3, new Piece(BLACK,QUEEN));
		expected1.setTurn(WHITE);
		expected1.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(before1, move1);
		assertEquals(before1,expected1);


		//test for movement of black queen to unoccupied horizontal position
		State before2 = before.copy();
		State expected2 = before2.copy();
		Move move2 = new Move(new Position(3,3),new Position(3,0),null);

		expected2.setPiece(3, 3,null);
		expected2.setPiece(3, 0, new Piece(BLACK,QUEEN));
		expected2.setTurn(WHITE);
		expected2.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(before2, move2);
		assertEquals(before2,expected2);


		//test for movement of black queen to unoccupied diagonal position
		State before3 = before.copy();
		State expected3 = before3.copy();
		Move move3 = new Move(new Position(3,3),new Position(4,2),null);

		expected3.setPiece(3, 3,null);
		expected3.setPiece(4, 2, new Piece(BLACK,QUEEN));
		expected3.setTurn(WHITE);
		expected3.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(before3, move3);
		assertEquals(before3,expected3);


		//test for movement of black queen to unoccupied diagonal position
		State before4 = before.copy();
		State expected4 = before4.copy();
		Move move4 = new Move(new Position(3,3),new Position(1,1),null);

		expected4.setPiece(3, 3,null);
		expected4.setPiece(1, 1, new Piece(BLACK,QUEEN));
		expected4.setTurn(WHITE);
		expected4.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(before4, move4);
		assertEquals(before4,expected4);


		//test for movement of black queen to occupied position with op piece
		State before5 = before.copy();
		State expected5 = before5.copy();
		Move move5 = new Move(new Position(3,3),new Position(3,7),null);

		expected5.setPiece(3, 3,null);
		expected5.setPiece(3, 7, new Piece(BLACK,QUEEN));
		expected5.setTurn(WHITE);
		expected5.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		stateChanger.makeMove(before5, move5);
		assertEquals(before5,expected5);
	}


	//test for movement of black queen over piece
	@Test(expected = IllegalMove.class)
	public void testBlackQueenMoveOverPiece(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7, 4,new Piece(BLACK,KING));
		before.setPiece(3, 3,new Piece(BLACK,QUEEN));
		before.setPiece(5, 1,new Piece(BLACK,PAWN));
		before.setPiece(3, 7,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(3,3),new Position(6,0),null);

		stateChanger.makeMove(before, move);		
	}	

	//test for movement of black queen to illegal direction
	@Test(expected = IllegalMove.class)
	public void testBlackQueenMoveWrongDirection(){
		State before = new State(BLACK, new Piece[8][8], new boolean[]{false, false}, new boolean[]{false, false}, null,0, null);

		before.setPiece(0, 4, new Piece(WHITE,KING));
		before.setPiece(7, 4,new Piece(BLACK,KING));
		before.setPiece(3, 3,new Piece(BLACK,QUEEN));
		before.setPiece(5, 1,new Piece(BLACK,PAWN));
		before.setPiece(3, 7,new Piece(WHITE,PAWN));
		Move move = new Move(new Position(3,3),new Position(4,7),null);

		stateChanger.makeMove(before, move);		
	}
	 */

	/*
	 * End Tests by Shitian Ren <renshitian@gmail.com>
	 */

	/*
	 * Begin Tests by Mark Anderson <markmakingmusic@gmail.com>
	 */

	@Test
	// KNIGHT:
	public void atStartKnightOneCanMoveUpLeft() {
		Move move = new Move(new Position(0, 1), new Position(2, 0), null);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setPiece(0, 1, null);
		expected.setPiece(2, 0, new Piece(WHITE, KNIGHT));
		stateChanger.makeMove(start, move);

		assertEquals(expected, start);
	}

	@Test
	public void atStartKnightOneCanMoveUpRight() {
		Move move = new Move(new Position(0, 1), new Position(2, 2), null);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setPiece(0, 1, null);
		expected.setPiece(2, 2, new Piece(WHITE, KNIGHT));
		stateChanger.makeMove(start, move);

		assertEquals(expected, start);
	}

	@Test
	public void atStartKnightTwoCanMoveUpLeft() {
		Move move = new Move(new Position(0, 6), new Position(2, 5), null);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setPiece(0, 6, null);
		expected.setPiece(2, 5, new Piece(WHITE, KNIGHT));
		stateChanger.makeMove(start, move);

		assertEquals(expected, start);
	}

	@Test
	public void atStartKnightTwoCanMoveUpRight() {
		Move move = new Move(new Position(0, 6), new Position(2, 7), null);

		State expected = start.copy();
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setPiece(0, 6, null);
		expected.setPiece(2, 7, new Piece(WHITE, KNIGHT));
		stateChanger.makeMove(start, move);

		assertEquals(expected, start);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightOneCannotMoveLeft() {
		Move move = new Move(new Position(0, 1), new Position(0, 0), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightOneCannotMoveRight() {
		Move move = new Move(new Position(0, 1), new Position(0, 2), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightOneCannotMoveUpMiddle() {
		Move move = new Move(new Position(0, 1), new Position(2, 1), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightOneCannotMoveDiagLeft() {
		Move move = new Move(new Position(0, 1), new Position(1, 0), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightOneCannotMoveDiagRight() {
		Move move = new Move(new Position(0, 1), new Position(1, 2), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightTwoCannotMoveLeft() {
		Move move = new Move(new Position(0, 6), new Position(0, 5), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightTwoCannotMoveRight() {
		Move move = new Move(new Position(0, 6), new Position(0, 7), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightTwoCannotMoveUpMiddle() {
		Move move = new Move(new Position(0, 6), new Position(2, 6), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightTwoCannotMoveDiagLeft() {
		Move move = new Move(new Position(0, 6), new Position(1, 5), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void atStartKnightTwoCannotMoveDiagRight() {
		Move move = new Move(new Position(0, 6), new Position(1, 7), null);

		stateChanger.makeMove(start, move);
	}

	// ROOK
	@Test
	public void rookOneLegalVerticalMoveUp() {
		Move move = new Move(new Position(0, 0), new Position(3, 0), null);

		// white
		State newState = start.copy();
		newState.setPiece(1, 0, null);
		// black
		newState.setPiece(6, 1, null);
		newState.setPiece(5, 0, new Piece(BLACK, PAWN));
		newState.setPiece(6, 7, null);
		newState.setPiece(4, 7, new Piece(BLACK, PAWN));
		newState.setTurn(WHITE);

		State expected = newState.copy();
		expected.setPiece(0, 0, null);
		expected.setPiece(3, 0, new Piece(WHITE, ROOK));
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(start
				.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		expected.setCanCastleQueenSide(WHITE, false);

		stateChanger.makeMove(newState, move);
		assertEquals(expected, newState);
	}

	@Test(expected = IllegalMove.class)
	public void rookOneIllegalMoveDiagDownRight() {
		Move move = new Move(new Position(2, 0), new Position(0, 2), null);

		State newState = start.copy();
		// white
		newState.setPiece(0, 0, null);
		newState.setPiece(1, 0, null);
		newState.setPiece(3, 0, new Piece(WHITE, PAWN));
		newState.setPiece(2, 0, new Piece(WHITE, ROOK));
		newState.setTurn(WHITE);
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(5, 7, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test(expected = IllegalMove.class)
	public void rookOneIllegalMoveDiagUpRight() {
		Move move = new Move(new Position(2, 0), new Position(4, 2), null);

		State newState = start.copy();
		// white
		newState.setPiece(0, 0, null);
		newState.setPiece(1, 0, null);
		newState.setPiece(3, 0, new Piece(WHITE, PAWN));
		newState.setPiece(2, 0, new Piece(WHITE, ROOK));
		newState.setTurn(WHITE);
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(5, 7, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test(expected = IllegalMove.class)
	public void rookOneIllegalMoveJump() {
		Move move = new Move(new Position(2, 0), new Position(4, 0), null);

		State newState = start.copy();
		// white
		newState.setPiece(0, 0, null);
		newState.setPiece(1, 0, null);
		newState.setPiece(3, 0, new Piece(WHITE, PAWN));
		newState.setPiece(2, 0, new Piece(WHITE, ROOK));
		newState.setTurn(WHITE);
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(5, 7, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test
	public void rookTwoLegalVerticalMoveUp() {
		Move move = new Move(new Position(0, 7), new Position(3, 7), null);

		// white
		State newState = start.copy();
		newState.setPiece(1, 7, null);
		// black
		newState.setPiece(6, 0, null);
		newState.setPiece(4, 0, new Piece(BLACK, PAWN));
		newState.setPiece(6, 6, null);
		newState.setPiece(5, 7, new Piece(BLACK, PAWN));
		newState.setTurn(WHITE);

		State expected = newState.copy();
		expected.setPiece(0, 7, null);
		expected.setPiece(3, 7, new Piece(WHITE, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(start
				.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		expected.setTurn(BLACK);
		expected.setCanCastleKingSide(WHITE, false);

		stateChanger.makeMove(newState, move);
		assertEquals(expected, newState);
	}


	@Test(expected = IllegalMove.class)
	public void rookTwoIllegalMoveDiagDownLeft() {
		Move move = new Move(new Position(2, 7), new Position(0, 5), null);

		State newState = start.copy();
		// white
		newState.setPiece(0, 7, null);
		newState.setPiece(1, 7, null);
		newState.setPiece(3, 7, new Piece(WHITE, PAWN));
		newState.setPiece(2, 7, new Piece(WHITE, ROOK));
		newState.setTurn(WHITE);
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(4, 7, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test(expected = IllegalMove.class)
	public void rookTwoIllegalMoveDiagUpLeft() {
		Move move = new Move(new Position(2, 7), new Position(4, 5), null);

		State newState = start.copy();
		// white
		newState.setPiece(0, 7, null);
		newState.setPiece(1, 7, null);
		newState.setPiece(3, 7, new Piece(WHITE, PAWN));
		newState.setPiece(2, 7, new Piece(WHITE, ROOK));
		newState.setTurn(WHITE);
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(4, 7, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test(expected = IllegalMove.class)
	public void rookTwoIllegalMoveJump() {
		Move move = new Move(new Position(2, 7), new Position(5, 7), null);

		State newState = start.copy();
		// white
		newState.setPiece(0, 7, null);
		newState.setPiece(1, 7, null);
		newState.setPiece(3, 7, new Piece(WHITE, PAWN));
		newState.setPiece(2, 7, new Piece(WHITE, ROOK));
		newState.setTurn(WHITE);
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(4, 7, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	// BISHOP
	@Test
	public void bishopOneLegalMoveDiagUpRight() {
		Move move = new Move(new Position(0, 2), new Position(5, 7), null);

		State newState = start.copy();
		// white
		newState.setPiece(1, 3, null);
		newState.setPiece(3, 3, new Piece(WHITE, PAWN));
		// black
		newState.setPiece(6, 0, null);
		newState.setPiece(5, 0, new Piece(BLACK, PAWN));
		newState.setTurn(WHITE);

		State expected = newState.copy();
		expected.setPiece(0, 2, null);
		expected.setPiece(5, 7, new Piece(WHITE, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(start
				.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		expected.setTurn(BLACK);

		stateChanger.makeMove(newState, move);
		assertEquals(expected, newState);
	}

	@Test
	public void bishopOneLegalMoveDiagUpLeft() {
		Move move = new Move(new Position(0, 2), new Position(2, 0), null);

		State newState = start.copy();
		// white
		newState.setPiece(1, 1, null);
		newState.setPiece(3, 1, new Piece(WHITE, PAWN));
		// black
		newState.setPiece(6, 0, null);
		newState.setPiece(5, 0, new Piece(BLACK, PAWN));
		newState.setTurn(WHITE);

		State expected = newState.copy();
		expected.setPiece(0, 2, null);
		expected.setPiece(2, 0, new Piece(WHITE, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(start
				.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		expected.setTurn(BLACK);

		stateChanger.makeMove(newState, move);
		assertEquals(expected, newState);
	}

	@Test(expected = IllegalMove.class)
	public void bishopOneIllegalMoveFromStart() {
		Move move = new Move(new Position(0, 2), new Position(2, 4), null);

		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void bishopOneIllegalJumpUpRight() {
		Move move = new Move(new Position(0, 2), new Position(3, 5), null);

		State newState = start.copy();
		// white
		newState.setPiece(1, 3, null);
		newState.setPiece(1, 4, null);
		newState.setPiece(2, 3, new Piece(WHITE, PAWN));
		newState.setPiece(2, 4, new Piece(WHITE, PAWN));
		// black
		newState.setPiece(6, 0, null);
		newState.setPiece(4, 0, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test(expected = IllegalMove.class)
	public void bishopOneIllegalMoveStraightUp() {
		Move move = new Move(new Position(0, 2), new Position(3, 2), null);

		State newState = start.copy();
		// white
		newState.setPiece(1, 2, null);
		newState.setPiece(4, 2, new Piece(WHITE, PAWN));
		// black
		newState.setPiece(6, 0, null);
		newState.setPiece(4, 0, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test
	public void bishopTwoLegalMoveDiagUpLeft() {
		Move move = new Move(new Position(0, 5), new Position(5, 0), null);

		State newState = start.copy();
		// white
		newState.setPiece(1, 4, null);
		newState.setPiece(3, 4, new Piece(WHITE, PAWN));
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(5, 7, new Piece(BLACK, PAWN));
		newState.setTurn(WHITE);

		State expected = newState.copy();
		expected.setPiece(0, 5, null);
		expected.setPiece(5, 0, new Piece(WHITE, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(start
				.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		expected.setTurn(BLACK);

		stateChanger.makeMove(newState, move);
		assertEquals(expected, newState);
	}

	@Test
	public void bishopTwoLegalMoveDiagUpRight() {
		Move move = new Move(new Position(0, 5), new Position(2, 7), null);

		State newState = start.copy();
		// white
		newState.setPiece(1, 6, null);
		newState.setPiece(2, 6, new Piece(WHITE, PAWN));
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(5, 7, new Piece(BLACK, PAWN));
		newState.setTurn(WHITE);

		State expected = newState.copy();
		expected.setPiece(0, 5, null);
		expected.setPiece(2, 7, new Piece(WHITE, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(start
				.getNumberOfMovesWithoutCaptureNorPawnMoved() + 1);
		expected.setTurn(BLACK);

		stateChanger.makeMove(newState, move);
		assertEquals(expected, newState);
	}

	@Test(expected = IllegalMove.class)
	public void bishopTwoIllegalJumpUpLeft() {
		Move move = new Move(new Position(0, 5), new Position(3, 2), null);

		State newState = start.copy();
		// white
		newState.setPiece(1, 3, null);
		newState.setPiece(1, 4, null);
		newState.setPiece(2, 3, new Piece(WHITE, PAWN));
		newState.setPiece(2, 4, new Piece(WHITE, PAWN));
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(4, 7, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test(expected = IllegalMove.class)
	public void bishopTwoIllegalMoveStraightUp() {
		Move move = new Move(new Position(0, 5), new Position(3, 5), null);

		State newState = start.copy();
		// white
		newState.setPiece(1, 5, null);
		newState.setPiece(4, 5, new Piece(WHITE, PAWN));
		// black
		newState.setPiece(6, 7, null);
		newState.setPiece(4, 7, new Piece(BLACK, PAWN));

		stateChanger.makeMove(newState, move);
	}

	@Test(expected = IllegalMove.class)
	public void bishopTwoIllegalMoveFromStart() {
		Move move = new Move(new Position(0, 5), new Position(2, 3), null);

		stateChanger.makeMove(start, move);
	}

	/*
	 * End Tests by Mark Anderson <markmakingmusic@gmail.com>
	 */



	/*
	 * Begin Tests by Zhihan Li <lizhihan1211@gmail.com>
	 */

	public void init() {
		Piece[][] board = new Piece[State.ROWS][State.COLS];
		start = new State(BLACK, board, new boolean[2], new boolean[2], null,
				0, null);
	}

	@Test
	public void testEndOfGameEndByCheckMateByOneRookOneBishopOnePawn() {

		init();

		start.setPiece(7, 3, new Piece(BLACK, KING));
		start.setPiece(2, 4, new Piece(BLACK, BISHOP));
		start.setPiece(2, 5, new Piece(BLACK, PAWN));
		start.setPiece(3, 6, new Piece(BLACK, ROOK));
		start.setPiece(0, 7, new Piece(WHITE, KING));

		State expected = start.copy();

		Move move = new Move(new Position(3, 6), new Position(3, 7), null);

		expected.setPiece(3, 6, null);
		expected.setPiece(3, 7, new Piece(BLACK, ROOK));
		expected.setTurn(WHITE);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setGameResult(new GameResult(BLACK, GameResultReason.CHECKMATE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByCheckMateByTwoBishop() {
		init();
		start.setPiece(4, 7, new Piece(BLACK, BISHOP));
		start.setPiece(2, 4, new Piece(BLACK, BISHOP));
		start.setPiece(2, 7, new Piece(BLACK, KING));
		start.setPiece(0, 7, new Piece(WHITE, KING));

		State expected = start.copy();

		Move move = new Move(new Position(4, 7), new Position(2, 5), null);

		expected.setPiece(4, 7, null);
		expected.setPiece(2, 5, new Piece(BLACK, BISHOP));
		expected.setTurn(WHITE);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setGameResult(new GameResult(BLACK, GameResultReason.CHECKMATE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByCheckMateByTwoRook() {
		init();
		start.setPiece(7, 0, new Piece(BLACK, ROOK));
		start.setPiece(7, 3, new Piece(BLACK, KING));
		start.setPiece(1, 7, new Piece(BLACK, ROOK));
		start.setPiece(0, 3, new Piece(WHITE, KING));

		State expected = start.copy();

		Move move = new Move(new Position(7, 0), new Position(0, 0), null);

		expected.setPiece(7, 0, null);
		expected.setPiece(0, 0, new Piece(BLACK, ROOK));
		expected.setTurn(WHITE);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setGameResult(new GameResult(BLACK, GameResultReason.CHECKMATE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByCheckMateByFivePawn() {
		System.out.println("look");
		init();
		start.setPiece(1, 1, new Piece(BLACK, PAWN));
		start.setPiece(2, 2, new Piece(BLACK, PAWN));
		start.setPiece(2, 3, new Piece(BLACK, PAWN));
		start.setPiece(2, 4, new Piece(BLACK, PAWN));
		start.setPiece(1, 5, new Piece(BLACK, PAWN));
		start.setPiece(0, 3, new Piece(WHITE, KING));
		start.setPiece(7, 3, new Piece(BLACK, KING));

		State expected = start.copy();

		Move move = new Move(new Position(2, 4), new Position(1, 4), null);

		expected.setPiece(2, 4, null);
		expected.setPiece(1, 4, new Piece(BLACK, PAWN));
		expected.setTurn(WHITE);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		expected.setGameResult(new GameResult(BLACK, GameResultReason.CHECKMATE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByCheckMateByRookVsThreePawn() {
		init();
		start.setPiece(1, 2, new Piece(WHITE, PAWN));
		start.setPiece(1, 3, new Piece(WHITE, PAWN));
		start.setPiece(1, 4, new Piece(WHITE, PAWN));
		start.setPiece(0, 3, new Piece(WHITE, KING));
		start.setPiece(7, 3, new Piece(BLACK, KING));
		start.setPiece(7, 0, new Piece(BLACK, ROOK));

		State expected = start.copy();

		Move move = new Move(new Position(7, 0), new Position(0, 0), null);

		expected.setPiece(7, 0, null);
		expected.setPiece(0, 0, new Piece(BLACK, ROOK));
		expected.setTurn(WHITE);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setGameResult(new GameResult(BLACK, GameResultReason.CHECKMATE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByCheckMateByOneRook() {
		init();
		start.setPiece(2, 3, new Piece(BLACK, KING));
		start.setPiece(7, 6, new Piece(BLACK, ROOK));
		start.setPiece(0, 3, new Piece(WHITE, KING));

		State expected = start.copy();

		Move move = new Move(new Position(7, 6), new Position(0, 6), null);

		expected.setPiece(7, 6, null);
		expected.setPiece(0, 6, new Piece(BLACK, ROOK));
		expected.setTurn(WHITE);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setGameResult(new GameResult(BLACK, GameResultReason.CHECKMATE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByCheckMateByRookAndQueen() {
		init();
		start.setPiece(0, 7, new Piece(BLACK, KING));
		start.setPiece(1, 5, new Piece(BLACK, ROOK));
		start.setPiece(1, 6, new Piece(BLACK, QUEEN));
		start.setPiece(0, 3, new Piece(WHITE, KING));

		State expected = start.copy();

		Move move = new Move(new Position(1, 6), new Position(0, 6), null);

		expected.setPiece(1, 6, null);
		expected.setPiece(0, 6, new Piece(BLACK, QUEEN));
		expected.setTurn(WHITE);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setGameResult(new GameResult(BLACK, GameResultReason.CHECKMATE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByCheckMateByTwoQueen() {
		init();
		start.setPiece(3, 5, new Piece(BLACK, KING));
		start.setPiece(2, 4, new Piece(BLACK, QUEEN));
		start.setPiece(1, 1, new Piece(BLACK, QUEEN));
		start.setPiece(0, 3, new Piece(WHITE, KING));

		State expected = start.copy();

		Move move = new Move(new Position(2, 4), new Position(1, 3), null);

		expected.setPiece(2, 4, null);
		expected.setPiece(1, 3, new Piece(BLACK, QUEEN));
		expected.setTurn(WHITE);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setGameResult(new GameResult(BLACK, GameResultReason.CHECKMATE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleBishopAndKnightVsBishop() {
		init();
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(6, 4, new Piece(BLACK, KNIGHT));
		start.setPiece(6, 2, new Piece(BLACK, KING));
		start.setPiece(7, 4, new Piece(BLACK, BISHOP));
		start.setPiece(1, 5, new Piece(WHITE, KING));
		start.setPiece(1, 6, new Piece(WHITE, BISHOP));

		State expected = start.copy();
		Move move = new Move(new Position(7, 4), new Position(4, 7), null);

		expected.setTurn(WHITE);
		expected.setPiece(7, 4, null);
		expected.setPiece(4, 7, new Piece(BLACK, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleRookAndBishopVsRook() {
		init();

		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(2, 1, new Piece(BLACK, ROOK));
		start.setPiece(4, 1, new Piece(BLACK, KING));
		start.setPiece(4, 2, new Piece(BLACK, BISHOP));
		start.setPiece(5, 4, new Piece(WHITE, KING));
		start.setPiece(3, 7, new Piece(WHITE, ROOK));

		State expected = start.copy();
		Move move = new Move(new Position(2, 1), new Position(2, 5), null);

		expected.setTurn(WHITE);
		expected.setPiece(2, 1, null);
		expected.setPiece(2, 5, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleRookVsRook() {
		init();

		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(4, 0, new Piece(BLACK, ROOK));
		start.setPiece(4, 1, new Piece(BLACK, KING));
		start.setPiece(5, 4, new Piece(WHITE, KING));
		start.setPiece(3, 7, new Piece(WHITE, ROOK));

		State expected = start.copy();
		Move move = new Move(new Position(4, 0), new Position(2, 0), null);

		expected.setTurn(WHITE);
		expected.setPiece(4, 0, null);
		expected.setPiece(2, 0, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleRookVsRookAndBishop() {
		init();

		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(2, 1, new Piece(BLACK, ROOK));
		start.setPiece(4, 1, new Piece(BLACK, KING));
		start.setPiece(4, 4, new Piece(WHITE, BISHOP));
		start.setPiece(5, 4, new Piece(WHITE, KING));
		start.setPiece(3, 7, new Piece(WHITE, ROOK));

		State expected = start.copy();

		Move move = new Move(new Position(2, 1), new Position(2, 4), null);
		expected.setTurn(WHITE);
		expected.setPiece(2, 1, null);
		expected.setPiece(2, 4, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleRookVsTwoKnight() {
		init();

		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(7, 4, new Piece(BLACK, ROOK));
		start.setPiece(4, 5, new Piece(BLACK, KING));
		start.setPiece(2, 5, new Piece(WHITE, KNIGHT));
		start.setPiece(2, 3, new Piece(WHITE, KNIGHT));
		start.setPiece(3, 7, new Piece(WHITE, KING));

		State expected = start.copy();
		Move move = new Move(new Position(7, 4), new Position(7, 3), null);

		expected.setTurn(WHITE);
		expected.setPiece(7, 4, null);
		expected.setPiece(7, 3, new Piece(BLACK, ROOK));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleQueenAndBishopVsTwoKnight() {
		init();

		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(4, 2, new Piece(BLACK, BISHOP));
		start.setPiece(5, 5, new Piece(BLACK, KING));
		start.setPiece(5, 1, new Piece(BLACK, QUEEN));
		start.setPiece(2, 3, new Piece(WHITE, KNIGHT));
		start.setPiece(3, 7, new Piece(WHITE, KNIGHT));
		start.setPiece(2, 7, new Piece(WHITE, KING));

		State expected = start.copy();

		Move move = new Move(new Position(5, 1), new Position(3, 1), null);
		expected.setTurn(WHITE);
		expected.setPiece(5, 1, null);
		expected.setPiece(3, 1, new Piece(BLACK, QUEEN));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleBishopVsTwoKnight() {
		init();
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(7, 2, new Piece(BLACK, BISHOP));
		start.setPiece(4, 5, new Piece(BLACK, KING));
		start.setPiece(2, 3, new Piece(WHITE, KNIGHT));
		start.setPiece(2, 7, new Piece(WHITE, KING));
		start.setPiece(1, 7, new Piece(WHITE, KNIGHT));

		State expected = start.copy();

		Move move = new Move(new Position(7, 2), new Position(5, 4), null);
		expected.setTurn(WHITE);
		expected.setPiece(7, 2, null);
		expected.setPiece(5, 4, new Piece(BLACK, BISHOP));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleKnightVsTwoKnight() {
		init();
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(6, 4, new Piece(BLACK, KNIGHT));
		start.setPiece(4, 5, new Piece(BLACK, KING));
		start.setPiece(2, 3, new Piece(WHITE, KNIGHT));
		start.setPiece(2, 7, new Piece(WHITE, KNIGHT));
		start.setPiece(1, 5, new Piece(WHITE, KING));

		State expected = start.copy();

		Move move = new Move(new Position(6, 4), new Position(5, 6), null);
		expected.setTurn(WHITE);
		expected.setPiece(6, 4, null);
		expected.setPiece(5, 6, new Piece(BLACK, KNIGHT));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByFiftyMoveRuleTwoKingLeft() {
		init();
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		start.setPiece(0, 0, new Piece(BLACK, KING));
		start.setPiece(7, 7, new Piece(WHITE, KING));

		Move move = new Move(new Position(0, 0), new Position(0, 1), null);
		State expected = start.copy();

		expected.setTurn(WHITE);
		expected.setPiece(0, 0, null);
		expected.setPiece(0, 1, new Piece(BLACK, KING));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		expected.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByStalemateOneQueen() {
		init();

		start.setPiece(7, 7, new Piece(WHITE, KING));
		start.setPiece(5, 6, new Piece(BLACK, QUEEN));
		start.setPiece(6, 4, new Piece(BLACK, KING));

		Move move = new Move(new Position(6, 4), new Position(6, 5), null);
		State expected = start.copy();

		expected.setTurn(WHITE);
		expected.setPiece(6, 4, null);
		expected.setPiece(6, 5, new Piece(BLACK, KING));
		expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);

	}

	@Test
	public void testEndOfGameEndByStalemateTwoRook() {
		init();
		start.setPiece(7, 4, new Piece(WHITE, KING));
		start.setPiece(5, 5, new Piece(BLACK, ROOK));
		start.setPiece(6, 3, new Piece(BLACK, ROOK));
		start.setPiece(5, 4, new Piece(BLACK, KING));

		Move move = new Move(new Position(5, 5), new Position(6, 5), null);
		State expected = start.copy();
		expected.setTurn(WHITE);
		expected.setPiece(5, 5, null);
		expected.setPiece(6, 5, new Piece(BLACK, ROOK));
		expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByStalemateRookVsBishop() {
		init();
		start.setPiece(7, 0, new Piece(WHITE, KING));
		start.setPiece(7, 1, new Piece(WHITE, BISHOP));
		start.setPiece(0, 7, new Piece(BLACK, ROOK));
		start.setPiece(5, 1, new Piece(BLACK, KING));

		Move move = new Move(new Position(0, 7), new Position(7, 7), null);
		State expected = start.copy();

		expected.setTurn(WHITE);
		expected.setPiece(0, 7, null);
		expected.setPiece(7, 7, new Piece(BLACK, ROOK));
		expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByStalemateOneRook() {
		init();
		start.setPiece(0, 0, new Piece(WHITE, KING));
		start.setPiece(1, 1, new Piece(BLACK, ROOK));
		start.setPiece(3, 2, new Piece(BLACK, KING));

		Move move = new Move(new Position(3, 2), new Position(2, 2), null);
		State expected = start.copy();
		expected.setTurn(WHITE);
		expected.setPiece(3, 2, null);
		expected.setPiece(2, 2, new Piece(BLACK, KING));
		expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	@Test
	public void testEndOfGameEndByStalemateOneKnight() {
		init();
		start.setPiece(7, 0, new Piece(WHITE, KING));
		start.setPiece(5, 2, new Piece(BLACK, KNIGHT));
		start.setPiece(4, 1, new Piece(BLACK, KING));

		Move move = new Move(new Position(4, 1), new Position(5, 1), null);
		State expected = start.copy();

		expected.setTurn(WHITE);
		expected.setPiece(4, 1, null);
		expected.setPiece(5, 1, new Piece(BLACK, KING));
		expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		stateChanger.makeMove(start, move);
		assertEquals(expected, start);
	}

	/*
	 * End Tests by Zhihan Li <lizhihan1211@gmail.com>	
	 */

	/*
	 * Start Tests by Sahil Vora <vora.sahil@gmail.com>
	 */

	/**
	 * Helper method that sets all the other pieces on the rank to be null.
	 * @param state
	 */
	private void clearOtherPieces(State state){
		state.setPiece(new Position(0,5), null);
		state.setPiece(new Position(0,6), null);
		state.setPiece(new Position(0,1), null);
		state.setPiece(new Position(0,2), null);
		state.setPiece(new Position(0,3), null);
	}



	/**
	 * Testing if initially boolean variables are set true.
	 */
	@Test
	public void testKingSideCastlingEnabledAtStart() {
		State start=new State();
		assertTrue(start.isCanCastleKingSide(WHITE));
	}

	@Test
	public void testQueenSideCastlingEnabledAtStart() {
		assertTrue(start.isCanCastleQueenSide(WHITE));
	}

	@Test
	public void testKingSideCastlingEnabledAfterKRookMoved() {
		State expected=new State();
		expected.setPiece(new Position(1,7), null);
		Move move = new Move(new Position(0,7),new Position(1,7),null);
		stateChanger.makeMove(expected, move);
		assertFalse(expected.isCanCastleKingSide(WHITE));
	}

	@Test
	public void testQueenSideCastlingEnabledAfterKRookMoved() {
		State expected=new State();
		expected.setPiece(new Position(1,7), null);
		Move move = new Move(new Position(0,7),new Position(1,7),null);
		stateChanger.makeMove(expected, move);
		assertTrue(expected.isCanCastleQueenSide(WHITE));
	}

	@Test
	public void testKingSideCastlingEnabledAfterQRookMoved() {
		State expected=new State();
		expected.setPiece(new Position(1,0), null);
		Move move = new Move(new Position(0,0),new Position(1,0),null);
		stateChanger.makeMove(expected, move);
		assertTrue(expected.isCanCastleKingSide(WHITE));
	}

	@Test
	public void testQueenSideCastlingEnabledAfterQRookMoved() {
		State expected=new State();
		expected.setPiece(new Position(1,0), null);
		Move move = new Move(new Position(0,0),new Position(1,0),null);
		stateChanger.makeMove(expected, move);
		assertFalse(expected.isCanCastleQueenSide(WHITE));
	}

	@Test
	public void testKingSideCastlingEnabledAfterKingMoved() {
		State expected=new State();
		expected.setPiece(new Position(1,4), null);
		Move move = new Move(new Position(0,4),new Position(1,4),null);
		stateChanger.makeMove(expected, move);
		assertFalse(expected.isCanCastleKingSide(WHITE));
	}

	@Test
	public void testQueenSideCastlingEnabledAfterKingMoved() {
		State expected=new State();
		expected.setPiece(new Position(1,4), null);
		Move move = new Move(new Position(0,4),new Position(1,4),null);
		stateChanger.makeMove(expected, move);
		assertFalse(expected.isCanCastleQueenSide(WHITE));
	}

	@Test(expected=IllegalMove.class)
	public void testKingSideCastlingWithBlockingBishop(){
		State expected=new State();
		expected.setPiece(new Position(0,6), null);
		Move move = new Move(new Position(0,4),new Position(0,6),null);
		stateChanger.makeMove(expected, move);
	}

	@Test(expected=IllegalMove.class)
	public void testQueenSideCastlingWithBlockingBishop(){
		State expected=new State();
		expected.setPiece(new Position(0,1), null);
		expected.setPiece(new Position(0,3), null);
		Move move = new Move(new Position(0,4),new Position(0,2),null);
		stateChanger.makeMove(expected, move);
		assertTrue(expected.isCanCastleQueenSide(WHITE));
	}

	@Test(expected=IllegalMove.class)
	public void testCastlingKingSideWithBlockingKnight(){
		State expected=new State();
		expected.setPiece(new Position(0,5), null);
		Move move = new Move(new Position(0,4),new Position(0,6),null);
		stateChanger.makeMove(expected, move);
	}

	@Test(expected=IllegalMove.class)
	public void testCastlingQueenSideWithBlockingKnight(){
		State expected=new State();
		expected.setPiece(new Position(0,2), null);
		expected.setPiece(new Position(0,3), null);
		Move move = new Move(new Position(0,4),new Position(0,2),null);
		stateChanger.makeMove(expected, move);
	}

	@Test(expected=IllegalMove.class)
	public void testCastlingQueenSideWithBlockingQueen(){
		State expected=new State();
		expected.setPiece(new Position(0,2), null);
		expected.setPiece(new Position(0,1), null);
		Move move = new Move(new Position(0,4),new Position(0,2),null);
		stateChanger.makeMove(expected, move);
	}

	@Test(expected = IllegalMove.class)
	public void testIllegalKingSideCastling() {
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		stateChanger.makeMove(start, move);
	}


	@Test(expected = IllegalMove.class)
	public void testIllegalQueenSideCastling() {
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		stateChanger.makeMove(start, move);
	}


	@Test(expected = IllegalMove.class)
	public void testKCastlingWithCanCastleKingSideSetToFalse() {
		State expected=new State();
		clearOtherPieces(expected);
		expected.setCanCastleKingSide(WHITE, false);
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		stateChanger.makeMove(expected, move);
	}


	@Test
	public void testQueenSideCastlingWithCanCastleKingSideSetToFalse() {
		State state=new State();
		clearOtherPieces(state);
		state.setCanCastleKingSide(WHITE, false);
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		State expected=state.copy();
		stateChanger.makeMove(state, move);
		expected.setPiece(new Position(0,0), null);
		expected.setPiece(new Position(0,3), new Piece(WHITE,PieceKind.ROOK));
		expected.setPiece(new Position(0,4), null);
		expected.setPiece(new Position(0,2), new Piece(WHITE,PieceKind.KING));
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected,state);
	}

	@Test
	public void testKingSideCastlingWithCanCastleQueenSideSetToFalse() {
		State state=new State();
		clearOtherPieces(state);
		state.setCanCastleQueenSide(WHITE, false);
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		State expected=state.copy();
		stateChanger.makeMove(state, move);
		expected.setPiece(new Position(0,7), null);
		expected.setPiece(new Position(0,5), new Piece(WHITE,PieceKind.ROOK));
		expected.setPiece(new Position(0,4), null);
		expected.setPiece(new Position(0,6), new Piece(WHITE,PieceKind.KING));
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected,state);
	}


	@Test(expected = IllegalMove.class)
	public void testQCastlingWithCanCastleQueenSideSetToFalse() {
		State expected=new State();
		clearOtherPieces(expected);
		expected.setCanCastleQueenSide(WHITE, false);
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);
		stateChanger.makeMove(expected, move);
	}


	@Test
	public void testCastleKingSide(){
		State state=new State();
		state.setPiece(new Position(0,5), null);
		state.setPiece(new Position(0,6), null);
		Move move = new Move(new Position(0,4),new Position(0,6),null);
		State expected=state.copy();
		stateChanger.makeMove(state, move);
		expected.setPiece(new Position(0,7), null);
		expected.setPiece(new Position(0,5), new Piece(WHITE,PieceKind.ROOK));
		expected.setPiece(new Position(0,4), null);
		expected.setPiece(new Position(0,6), new Piece(WHITE,PieceKind.KING));
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		assertEquals(expected,state);
	}

	public void testCastleQueenSide(){
		State state=new State();
		state.setPiece(new Position(0,1), null);
		state.setPiece(new Position(0,2), null);
		state.setPiece(new Position(0,3), null);
		Move move = new Move(new Position(0,4),new Position(0,2),null);
		State expected=state.copy();
		stateChanger.makeMove(state, move);
		expected.setPiece(new Position(0,0), null);
		expected.setPiece(new Position(0,3), new Piece(WHITE,PieceKind.ROOK));
		expected.setPiece(new Position(0,4), null);
		expected.setPiece(new Position(0,2), new Piece(WHITE,PieceKind.KING));
		expected.setCanCastleKingSide(WHITE, false);
		expected.setCanCastleQueenSide(WHITE, false);
		expected.setTurn(BLACK);
		expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		assertEquals(expected,state);
	}


	@Test(expected=IllegalMove.class)
	public void testCanCastleKingSideWhenInCheck(){
		State expected=new State();
		expected.setPiece(new Position(0,5), null);
		expected.setPiece(new Position(0,6), null);
		expected.setPiece(new Position(1,4), null);
		expected.setPiece(new Position(7,3),null);
		expected.setPiece(new Position(5,4), new Piece(BLACK,PieceKind.QUEEN));
		Move move = new Move(new Position(0,4),new Position(0,6),null);
		stateChanger.makeMove(expected, move);
	}

	@Test(expected=IllegalMove.class)
	public void testCanCastleQueenSideWhenInCheck(){
		State expected=new State();
		expected.setPiece(new Position(0,1), null);
		expected.setPiece(new Position(0,2), null);
		expected.setPiece(new Position(0,3), null);
		expected.setPiece(new Position(1,4), null);
		expected.setPiece(new Position(7,3),null);
		expected.setPiece(new Position(5,4), new Piece(BLACK,PieceKind.QUEEN));
		Move move = new Move(new Position(0,4),new Position(0,2),null);
		stateChanger.makeMove(expected, move);
	}

	@Test(expected = IllegalMove.class)
	public void testCanCastleKingSideWhenThroughCheck(){
		State expected=new State();
		expected.setPiece(new Position(0,5), null);
		expected.setPiece(new Position(0,6), null);
		expected.setPiece(new Position(1,5), null);
		expected.setPiece(new Position(7,3),null);
		expected.setPiece(new Position(5,5), new Piece(BLACK,PieceKind.QUEEN));
		Move move = new Move(new Position(0,4),new Position(0,6),null);
		stateChanger.makeMove(expected, move);
	}

	@Test(expected=IllegalMove.class)
	public void testCanCastleQueenSideWhenThroughCheck(){
		State expected=new State();
		expected.setPiece(new Position(0,1), null);
		expected.setPiece(new Position(0,2), null);
		expected.setPiece(new Position(0,3), null);
		expected.setPiece(new Position(1,3), null);
		expected.setPiece(new Position(7,3),null);
		expected.setPiece(new Position(5,3), new Piece(BLACK,PieceKind.QUEEN));
		Move move = new Move(new Position(0,4),new Position(0,2),null);
		stateChanger.makeMove(expected, move);
	}

	@Test(expected=IllegalMove.class)
	public void testCanCastleKingSideWhenToCheck(){
		State expected=new State();
		expected.setPiece(new Position(0,5), null);
		expected.setPiece(new Position(0,6), null);
		expected.setPiece(new Position(1,6), null);
		expected.setPiece(new Position(7,3),null);
		expected.setPiece(new Position(5,6), new Piece(BLACK,PieceKind.QUEEN));
		Move move = new Move(new Position(0,4),new Position(0,6),null);
		stateChanger.makeMove(expected, move);
	}

	@Test (expected=IllegalMove.class)
	public void testCanCastleQueenSideWhenToCheck(){
		State expected=new State();
		expected.setPiece(new Position(0,1), null);
		expected.setPiece(new Position(0,2), null);
		expected.setPiece(new Position(0,3), null);
		expected.setPiece(new Position(1,2), null);
		expected.setPiece(new Position(7,3),null);
		expected.setPiece(new Position(5,2), new Piece(BLACK,PieceKind.QUEEN));
		Move move = new Move(new Position(0,4),new Position(0,2),null);
		stateChanger.makeMove(expected, move);
	}


	/*
	 * End Tests by Sahil Vora <vora.sahil@gmail.com>
	 */

	 /*
	  * Begin Tests by Chen Ji <ji.chen1990@gmail.com>
	  */
	  private State beforeCaptureEnpassant;  // For capture tests
	  private State initialState;  // For update tests
	  private State expectedState;

	  public void setup(Position blackOldPosition, Position blackNewPosition, Position whiteOldPosition, Position whiteNewPosition){
		  beforeCaptureEnpassant = start.copy();
		  beforeCaptureEnpassant.setTurn(WHITE);
		  beforeCaptureEnpassant.setPiece(whiteOldPosition,null);
		  beforeCaptureEnpassant.setPiece(whiteNewPosition,new Piece(WHITE, PAWN));
		  beforeCaptureEnpassant.setPiece(blackOldPosition,null);
		  beforeCaptureEnpassant.setPiece(blackNewPosition,new Piece(BLACK, PAWN));
		  beforeCaptureEnpassant.setEnpassantPosition(blackNewPosition);
	  }

	  // used in capture cases...
	  public void captureEnpassant(Position blackPosition, Position whitePosition, Position whitePositionAfterCapture){
		  expectedState = beforeCaptureEnpassant.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(whitePosition, null);
		  expectedState.setPiece(whitePositionAfterCapture, new Piece(WHITE,PAWN));
		  expectedState.setPiece(blackPosition, null);
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expectedState.setEnpassantPosition(null);
	  }

	  // 14 cases of capture;
	  @Test
	  public void testWhiteA5CaptureBlackB5(){
		  Position blackOldPosition = new Position(6,1); //
		  Position blackNewPosition = new Position(4,1); //
		  Position whiteOldPosition = new Position(1,0); //
		  Position whiteNewPosition = new Position(4,0); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,1); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteB5CaptureBlackA5(){
		  Position blackOldPosition = new Position(6,0); //
		  Position blackNewPosition = new Position(4,0); //
		  Position whiteOldPosition = new Position(1,1); //
		  Position whiteNewPosition = new Position(4,1); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,0); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteB5CaptureBlackC5(){
		  Position blackOldPosition = new Position(6,2); //
		  Position blackNewPosition = new Position(4,2); //
		  Position whiteOldPosition = new Position(1,1); //
		  Position whiteNewPosition = new Position(4,1); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,2); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteC5CaptureBlackB5(){
		  Position blackOldPosition = new Position(6,1); //
		  Position blackNewPosition = new Position(4,1); //
		  Position whiteOldPosition = new Position(1,2); //
		  Position whiteNewPosition = new Position(4,2); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,1); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteC5CaptureBlackD5(){
		  Position blackOldPosition = new Position(6,3); //
		  Position blackNewPosition = new Position(4,3); //
		  Position whiteOldPosition = new Position(1,2); //
		  Position whiteNewPosition = new Position(4,2); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,3); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteD5CaptureBlackC5(){
		  Position blackOldPosition = new Position(6,2); //
		  Position blackNewPosition = new Position(4,2); //
		  Position whiteOldPosition = new Position(1,3); //
		  Position whiteNewPosition = new Position(4,3); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,2); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteD5CaptureBlackE5(){
		  Position blackOldPosition = new Position(6,4); //
		  Position blackNewPosition = new Position(4,4); //
		  Position whiteOldPosition = new Position(1,3); //
		  Position whiteNewPosition = new Position(4,3); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,4); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteE5CaptureBlackD5(){
		  Position blackOldPosition = new Position(6,3); //
		  Position blackNewPosition = new Position(4,3); //
		  Position whiteOldPosition = new Position(1,4); //
		  Position whiteNewPosition = new Position(4,4); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,3); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteE5CaptureBlackF5(){
		  Position blackOldPosition = new Position(6,5); //
		  Position blackNewPosition = new Position(4,5); //
		  Position whiteOldPosition = new Position(1,4); //
		  Position whiteNewPosition = new Position(4,4); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,5); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteF5CaptureBlackE5(){
		  Position blackOldPosition = new Position(6,4); //
		  Position blackNewPosition = new Position(4,4); //
		  Position whiteOldPosition = new Position(1,5); //
		  Position whiteNewPosition = new Position(4,5); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,4); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteF5CaptureBlackG5(){
		  Position blackOldPosition = new Position(6,6); //
		  Position blackNewPosition = new Position(4,6); //
		  Position whiteOldPosition = new Position(1,5); //
		  Position whiteNewPosition = new Position(4,5); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,6); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteG5CaptureBlackF5(){
		  Position blackOldPosition = new Position(6,5); //
		  Position blackNewPosition = new Position(4,5); //
		  Position whiteOldPosition = new Position(1,6); //
		  Position whiteNewPosition = new Position(4,6); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,5); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteG5CaptureBlackH5(){
		  Position blackOldPosition = new Position(6,7); //
		  Position blackNewPosition = new Position(4,7); //
		  Position whiteOldPosition = new Position(1,6); //
		  Position whiteNewPosition = new Position(4,6); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,7); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  @Test
	  public void testWhiteH5CaptureBlackG5(){
		  Position blackOldPosition = new Position(6,6); //
		  Position blackNewPosition = new Position(4,6); //
		  Position whiteOldPosition = new Position(1,7); //
		  Position whiteNewPosition = new Position(4,7); //
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,6); //
		  captureEnpassant(blackNewPosition, whiteNewPosition, whitePositionAfterCaptureEnpassant);

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
		  assertEquals(expectedState, beforeCaptureEnpassant);
	  }

	  // used for cases of enpassant position update
	  public void setupInitialState(){
		  initialState = start.copy();
		  initialState.setTurn(WHITE);
		  initialState.setPiece(6, 1, null); 
		  initialState.setPiece(4, 1, new Piece(BLACK, PAWN)); // Black B7 move to B5
		  for(int i = 1; i < State.COLS; i ++){
			  initialState.setPiece(1, i, null); // Set all white pawns null except the first one.
		  }
		  initialState.setEnpassantPosition(new Position(4,1));
		  initialState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	  }

	  // 7 cases of update
	  @Test
	  public void testEnpassantPositionUpdate_PawnMoveTwoSquares(){
		  Move move = new Move(new Position(1, 0), new Position(3, 0), null);
		  setupInitialState();
		  expectedState = initialState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(1, 0, null);
		  expectedState.setPiece(3, 0, new Piece(WHITE, PAWN));
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expectedState.setEnpassantPosition(new Position(3, 0));
		  stateChanger.makeMove(initialState, move);
		  assertEquals(expectedState, initialState);
	  }

	  @Test
	  public void testEnpassantPositionUpdate_PawnMoveOneSquare(){
		  Move move = new Move(new Position(1, 0), new Position(2, 0), null);
		  setupInitialState();
		  expectedState = initialState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(1, 0, null);
		  expectedState.setPiece(2, 0, new Piece(WHITE, PAWN));
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(initialState, move);
		  assertEquals(expectedState, initialState);
	  }

	  @Test
	  public void testEnpassantPositionUpdate_RookMove(){
		  Move move = new Move(new Position(0, 7), new Position(1, 7), null);
		  setupInitialState();
		  expectedState = initialState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(0, 7, null);
		  expectedState.setPiece(1, 7, new Piece(WHITE, ROOK));
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expectedState.setCanCastleKingSide(WHITE, false);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(initialState, move);
		  assertEquals(expectedState, initialState);
	  }

	  @Test
	  public void testEnpassantPositionUpdate_KnightMove(){
		  Move move = new Move(new Position(0, 6), new Position(2, 5), null);
		  setupInitialState();
		  expectedState = initialState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(0, 6, null);
		  expectedState.setPiece(2, 5, new Piece(WHITE, KNIGHT));
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(initialState, move);
		  assertEquals(expectedState, initialState);
	  }

	  @Test
	  public void testEnpassantPositionUpdate_BishopMove(){
		  Move move = new Move(new Position(0, 5), new Position(2, 7), null);
		  setupInitialState();
		  expectedState = initialState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(0, 5, null);
		  expectedState.setPiece(2, 7, new Piece(WHITE, BISHOP));
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(initialState, move);
		  assertEquals(expectedState, initialState);
	  }

	  @Test
	  public void testEnpassantPositionUpdate_KingMove(){
		  Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		  setupInitialState();
		  expectedState = initialState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(0, 4, null);
		  expectedState.setPiece(1, 4, new Piece(WHITE, KING));
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expectedState.setCanCastleKingSide(WHITE, false);
		  expectedState.setCanCastleQueenSide(WHITE, false);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(initialState, move);
		  assertEquals(expectedState, initialState);
	  }

	  @Test
	  public void testEnpassantPositionUpdate_QueenMove(){
		  Move move = new Move(new Position(0, 3), new Position(1, 3), null);
		  setupInitialState();
		  expectedState = initialState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(0, 3, null);
		  expectedState.setPiece(1, 3, new Piece(WHITE, QUEEN));
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(initialState, move);
		  assertEquals(expectedState, initialState);
	  }

	  // 3 cases of illegal move
	  @Test(expected = IllegalMove.class)
	  public void testCannotCaptureWhenEnpassantPossitionIsNull() {
		  Position blackOldPosition = new Position(6,0); //A7
		  Position blackNewPosition = new Position(4,0); //A5
		  Position whiteOldPosition = new Position(1,1); //B2
		  Position whiteNewPosition = new Position(4,1); //B5
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);
		  beforeCaptureEnpassant.setEnpassantPosition(null);
		  Position whitePositionAfterCaptureEnpassant = new Position(5,0); //A6

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
	  }

	  @Test(expected = IllegalMove.class)
	  public void testCannotCaptureWhenEnpassantPossitionIsDifferent() {
		  Position blackOldPosition = new Position(6,0); //A7
		  Position blackNewPosition = new Position(4,0); //A5
		  Position whiteOldPosition = new Position(1,1); //B2
		  Position whiteNewPosition = new Position(4,1); //B5
		  setup(blackOldPosition, blackNewPosition, whiteOldPosition, whiteNewPosition);

		  Position whitePositionAfterCaptureEnpassant = new Position(5,2); //C6

		  Move move = new Move(whiteNewPosition, whitePositionAfterCaptureEnpassant, null);
		  stateChanger.makeMove(beforeCaptureEnpassant, move);
	  }

	  @Test(expected = IllegalMove.class)
	  public void testCannotCaptureEnpassantIfWillExploseKing() {
		  Piece[][] board = new Piece[State.ROWS][State.COLS];
		  board[7][0] = new Piece(BLACK, KING);
		  board[7][4] = new Piece(BLACK, QUEEN);
		  board[4][5] = new Piece(BLACK, PAWN);
		  board[4][4] = new Piece(WHITE, PAWN);
		  board[0][4] = new Piece(WHITE, KING); // Move pawn at 4,4 will explose white king at 0,4 to black queen at 7,4
		  State newState = new State(WHITE, board, 
				  new boolean[] {false, false}, new boolean[] {false, false}, 
				  new Position(4, 5), 0, null);
		  Move move = new Move(new Position(4, 4), new Position(5, 5), null);
		  stateChanger.makeMove(newState, move);
	  }

	  // 4 under check tests
	  @Test(expected = IllegalMove.class)
	  public void testCannotCaptureEnpassantIfIsUnderCheck() {
		  Piece[][] board = new Piece[State.ROWS][State.COLS];
		  board[7][0] = new Piece(BLACK, KING);
		  board[7][1] = new Piece(BLACK, QUEEN);
		  board[4][5] = new Piece(BLACK, PAWN);
		  board[4][4] = new Piece(WHITE, PAWN);
		  board[0][1] = new Piece(WHITE, KING); // White king is under check by queen at 7,1
		  State newState = new State(WHITE, board, 
				  new boolean[] {false, false}, new boolean[] {false, false}, 
				  new Position(4, 5), 0, null);
		  Move move = new Move(new Position(4, 4), new Position(5, 5), null);
		  stateChanger.makeMove(newState, move);
	  }


	  @Test
	  public void testCaptureEnpassantUnderCheck_ByPawn() {
		  Piece[][] board = new Piece[State.ROWS][State.COLS];
		  board[7][0] = new Piece(BLACK, KING);
		  board[4][5] = new Piece(BLACK, PAWN);
		  board[4][4] = new Piece(WHITE, PAWN);
		  board[3][4] = new Piece(WHITE, KING); // White King is under chech by pawn at 4,5
		  State newState = new State(WHITE, board, 
				  new boolean[] {false, false}, new boolean[] {false, false}, 
				  new Position(4, 5), 0, null);
		  Move move = new Move(new Position(4, 4), new Position(5, 5), null);
		  expectedState = newState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(4, 4, null);
		  expectedState.setPiece(5, 5, new Piece(WHITE, PAWN));
		  expectedState.setPiece(4, 5, null);
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(newState, move);
		  assertEquals(expectedState, newState);
	  }

	  @Test
	  public void testCaptureEnpassantUnderCheck_ByQueen() {
		  Piece[][] board = new Piece[State.ROWS][State.COLS];
		  board[7][0] = new Piece(BLACK, KING);
		  board[5][0] = new Piece(BLACK, QUEEN);
		  board[4][5] = new Piece(BLACK, PAWN);
		  board[4][4] = new Piece(WHITE, PAWN);
		  board[5][6] = new Piece(WHITE, KING); // White king is under check by queen at 5,0
		  State newState = new State(WHITE, board, 
				  new boolean[] {false, false}, new boolean[] {false, false}, 
				  new Position(4, 5), 0, null);
		  Move move = new Move(new Position(4, 4), new Position(5, 5), null);
		  expectedState = newState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(4, 4, null);
		  expectedState.setPiece(5, 5, new Piece(WHITE, PAWN));
		  expectedState.setPiece(4, 5, null);
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(newState, move);
		  assertEquals(expectedState, newState);
	  }

	  @Test
	  public void testCaptureEnpassantUnderCheck_ByRook() {
		  Piece[][] board = new Piece[State.ROWS][State.COLS];
		  board[7][0] = new Piece(BLACK, KING);
		  board[5][0] = new Piece(BLACK, ROOK);
		  board[4][5] = new Piece(BLACK, PAWN);
		  board[4][4] = new Piece(WHITE, PAWN);
		  board[5][6] = new Piece(WHITE, KING); // White king is under check by rook at 5,0
		  State newState = new State(WHITE, board, 
				  new boolean[] {false, false}, new boolean[] {false, false}, 
				  new Position(4, 5), 0, null);
		  Move move = new Move(new Position(4, 4), new Position(5, 5), null);
		  expectedState = newState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(4, 4, null);
		  expectedState.setPiece(5, 5, new Piece(WHITE, PAWN));
		  expectedState.setPiece(4, 5, null);
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(newState, move);
		  assertEquals(expectedState, newState);
	  }

	  public void testCaptureEnpassantUnderCheck_ByBishop() {
		  Piece[][] board = new Piece[State.ROWS][State.COLS];
		  board[7][0] = new Piece(BLACK, KING);
		  board[7][3] = new Piece(BLACK, BISHOP);
		  board[4][5] = new Piece(BLACK, PAWN);
		  board[4][4] = new Piece(WHITE, PAWN);
		  board[4][6] = new Piece(WHITE, KING);
		  State newState = new State(WHITE, board, 
				  new boolean[] {false, false}, new boolean[] {false, false}, 
				  new Position(4, 5), 0, null);
		  Move move = new Move(new Position(4, 4), new Position(5, 5), null);
		  expectedState = newState.copy();
		  expectedState.setTurn(BLACK);
		  expectedState.setPiece(4, 4, null);
		  expectedState.setPiece(5, 5, new Piece(WHITE, PAWN));
		  expectedState.setPiece(4, 5, null);
		  expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expectedState.setEnpassantPosition(null);
		  stateChanger.makeMove(newState, move);
		  assertEquals(expectedState, newState);
	  }
	  /*
	   * End Tests by Chen Ji <ji.chen1990@gmail.com>
	   */

	  /*
	   * Start Tests by Haoxiang Zuo <haoxiangzuo@gmail.com>
	   */
	  private void clearAllPieces() {
		  for (int row = 0; row<=7; row++)
			  for (int col = 0; col<=7; col++)
				  start.setPiece(row, col, null);
	  }
	  @Test
	  public void testBlackKingBeingCheckMateByOneWhiteRookAndOneWhiteQueen(){
		  clearAllPieces();
		  Piece blackKing = new Piece(Color.BLACK,PieceKind.KING);
		  Piece whiteKing = new Piece(Color.WHITE,PieceKind.KING);
		  Piece whiteQueen = new Piece(Color.WHITE,PieceKind.QUEEN);
		  Piece whiteRook = new Piece(Color.WHITE,PieceKind.ROOK);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 4, blackKing);
		  start.setPiece(0, 4, whiteKing);
		  start.setPiece(6, 7, whiteRook);
		  start.setPiece(0, 0, whiteQueen);
		  start.setTurn(Color.WHITE);

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(0, 0, null);
		  expected.setPiece(7, 0,	whiteQueen);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);

		  Move moveWhiteQueen = new Move(new Position(0,0), new Position(7,0), null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testBlackKingBeingCheckMateByOneWhiteQueen()
	  {
		  start.setTurn(Color.WHITE);
		  start.setPiece(6, 5, null);
		  start.setPiece(6, 6, null);
		  start.setPiece(1, 4, null);

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(0, 3, null);
		  expected.setPiece(4, 7, new Piece(Color.WHITE, PieceKind.QUEEN));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);

		  Move moveWhiteQueen = new Move(new Position(0,3), new Position(4,7),null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected,start);
	  }
	  @Test
	  public void testBlackKingBeingCheckMateByOneWhiteKingAndOneWhiteRook()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setPiece(7, 3, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(5, 3, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(0, 6, new Piece(Color.WHITE, PieceKind.ROOK));
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(0, 6, null);
		  expected.setPiece(7, 6, new Piece(Color.WHITE, PieceKind.ROOK));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);

		  Move moveWhiteRook = new Move(new Position(0,6), new Position(7,6), null);
		  stateChanger.makeMove(start, moveWhiteRook);
		  assertEquals(expected,start);
	  }
	  @Test
	  public void testBlackKingBeingCheckmateByOneWhiteQueeWhenWhiteQueenDoingCaptrue()
	  {
		  start.setTurn(Color.WHITE);
		  start.setPiece(6, 5, null);
		  start.setPiece(6, 6, null);
		  start.setPiece(1, 4, null);
		  start.setPiece(4, 7, new Piece(Color.BLACK, PieceKind.PAWN));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(0, 3, null);
		  expected.setPiece(4, 7, new Piece(Color.WHITE, PieceKind.QUEEN));
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);

		  Move moveWhiteQueen = new Move(new Position(0,3), new Position(4,7),null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected,start);
	  }
	  @Test
	  public void testBlackKingBeingCheckmateByOneWhiteQueenAndOneWhiteRookInTheCorner()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(6, 4, new Piece(Color.WHITE, PieceKind.ROOK));
		  start.setPiece(0, 2, new Piece(Color.WHITE, PieceKind.QUEEN));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.BISHOP));
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(0, 2, null);
		  expected.setPiece(5, 2, new Piece(Color.WHITE, PieceKind.QUEEN));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);

		  Move moveWhiteQueen = new Move(new Position(0,2), new Position(5,2), null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testBlackKingBeingCheckmateByTwoWhiteRooksAndOneWhiteQueen()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(new Position(7,4), new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(new Position(0,3), new Piece(Color.WHITE, PieceKind.ROOK));
		  start.setPiece(new Position(0,5), new Piece(Color.WHITE, PieceKind.ROOK));
		  start.setPiece(new Position(0,4), new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(new Position(1,1), new Piece(Color.WHITE, PieceKind.QUEEN));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(new Position(1,1), null);
		  expected.setPiece(new Position(1,4), new Piece(Color.WHITE, PieceKind.QUEEN));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);

		  Move moveWhiteQueen = new Move(new Position(1,1), new Position(1,4), null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testBlackKingBeingCheckmateByOneWhiteKingAndTwoWhiteBishops()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(6, 2, new Piece(Color.WHITE, PieceKind.BISHOP));
		  start.setPiece(4, 1, new Piece(Color.WHITE, PieceKind.BISHOP));
		  start.setPiece(5, 1, new Piece(Color.WHITE, PieceKind.KING));
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(4, 1, null);
		  expected.setPiece(5, 2, new Piece(Color.WHITE, PieceKind.BISHOP));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);

		  Move moveWhiteBishop = new Move(new Position(4,1), new Position(5,2), null);
		  stateChanger.makeMove(start, moveWhiteBishop);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testBlackKingBeingCheckmateByOneWhiteKingAndTwoKnights()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(5, 5, new Piece(Color.WHITE, PieceKind.KNIGHT));
		  start.setPiece(7, 5, new Piece(Color.WHITE, PieceKind.KNIGHT));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(7, 5, null);
		  expected.setPiece(5, 6, new Piece(Color.WHITE, PieceKind.KNIGHT));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);

		  Move moveWhiteKnight = new Move(new Position(7,5), new Position(5,6), null);
		  stateChanger.makeMove(start, moveWhiteKnight);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testBlackKingBeingCheckmateByOneWhiteKingAndTwoWhiteRooks()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(1, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(3, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(4, 1, new Piece(Color.WHITE, PieceKind.ROOK));
		  start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.ROOK));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);
		  expected.setPiece(0, 0, null);
		  expected.setPiece(3, 0, new Piece(Color.WHITE, PieceKind.ROOK));

		  Move moveWhiteKnight = new Move(new Position(0 , 0), new Position(3 , 0), null);
		  stateChanger.makeMove(start, moveWhiteKnight);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testBlackKingBeingCheckmateByOneWhiteKingAndOneWhiteQueen(){
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7,0,new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(5,1, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(6,7, new Piece(Color.WHITE, PieceKind.QUEEN));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  GameResult result = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
		  expected.setGameResult(result);
		  expected.setPiece(6, 7, null);
		  expected.setPiece(7, 7, new Piece(Color.WHITE, PieceKind.QUEEN));

		  Move moveWhiteKnight = new Move(new Position(6,7), new Position(7,7), null);
		  stateChanger.makeMove(start, moveWhiteKnight);
		  assertEquals(expected, start);		
	  }
	  @Test
	  public void testMoveOneWhiteQueenAndBlackKingDidntUnderCheckAndTheGameDidntEnd()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(5, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.QUEEN));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expected.setPiece(0, 0, null);
		  expected.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.QUEEN));

		  Move moveWhiteQueen = new Move(new Position(0,0), new Position(6,0), null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveOneWhiteKingAndBlackKingDindntUnderCheckTheGameDidntEnd()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(5, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.QUEEN));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expected.setPiece(5, 4, null);
		  expected.setPiece(5, 3, new Piece(Color.WHITE, PieceKind.KING));

		  Move moveWhiteQueen = new Move(new Position(5,4), new Position(5,3), null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveOneWhiteQueenAndReachFiftyMoveRule()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(0, 3, new Piece(Color.WHITE, PieceKind.QUEEN));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		  expected.setPiece(0, 3, null);
		  expected.setPiece(1, 3, new Piece(Color.WHITE, PieceKind.QUEEN));
		  expected.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));

		  Move moveWhiteQueen = new Move(new Position(0,3), new Position(1,3), null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveOneWhiteKingAndReachFiftyMoveRule()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		  expected.setPiece(0, 4, null);
		  expected.setPiece(1, 4, new Piece(Color.WHITE, PieceKind.KING));
		  expected.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));

		  Move moveWhiteKing = new Move(new Position(0,4), new Position(1,4), null);
		  stateChanger.makeMove(start, moveWhiteKing);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveOneWhiteRookAndReachFiftyMoveRule()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(0, 1, new Piece(Color.WHITE, PieceKind.ROOK));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		  expected.setPiece(0, 1, null);
		  expected.setPiece(0, 2, new Piece(Color.WHITE, PieceKind.ROOK));
		  expected.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));

		  Move moveWhiteRook = new Move(new Position(0,1), new Position(0, 2), null);
		  stateChanger.makeMove(start, moveWhiteRook);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveOneWhiteBishopAndReachFiftyMoveRule()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(0, 1, new Piece(Color.WHITE, PieceKind.BISHOP));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		  expected.setPiece(0, 1, null);
		  expected.setPiece(1, 0, new Piece(Color.WHITE, PieceKind.BISHOP));
		  expected.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));

		  Move moveWhiteBishop = new Move(new Position(0, 1), new Position(1, 0), null);
		  stateChanger.makeMove(start, moveWhiteBishop);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void MoveOneWhiteKnightAndReachFiftyMoveRule()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(0, 1, new Piece(Color.WHITE, PieceKind.KNIGHT));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		  expected.setPiece(0, 1, null);
		  expected.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.KNIGHT));
		  expected.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));

		  Move moveWhiteKnight = new Move(new Position(0, 1), new Position(2, 0), null);
		  stateChanger.makeMove(start, moveWhiteKnight);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveOneWhitePawnAndGameDidntEnd(){
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(1, 1, new Piece(Color.WHITE, PieceKind.PAWN));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expected.setPiece(1, 1, null);
		  expected.setPiece(2, 1, new Piece(Color.WHITE, PieceKind.PAWN));

		  Move moveWhitePawn = new Move(new Position(1, 1), new Position(2, 1), null);
		  stateChanger.makeMove(start, moveWhitePawn);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testWhiteQueenDoOneCaptureAndGameDidntEnd()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(6, 7, new Piece(Color.BLACK, PieceKind.QUEEN));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(0, 1, new Piece(Color.WHITE, PieceKind.QUEEN));

		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expected.setPiece(0, 1, null);
		  expected.setPiece(6, 7, new Piece(Color.WHITE, PieceKind.QUEEN));

		  Move moveWhiteQueen = new Move(new Position(0, 1), new Position(6, 7), null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testWhiteKingDoOneCaptureAndGameDidntEnd()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setNumberOfMovesWithoutCaptureNorPawnMoved(99);
		  start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(1, 3, new Piece(Color.BLACK, PieceKind.QUEEN));
		  start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));


		  State expected = start.copy();
		  expected.setTurn(Color.BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
		  expected.setPiece(0, 4, null);
		  expected.setPiece(1, 3, new Piece(Color.WHITE, PieceKind.KING));

		  Move moveWhiteKing = new Move(new Position(0, 4), new Position(1, 3), null);
		  stateChanger.makeMove(start, moveWhiteKing);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveWhiteQueenAndReachStaleMate()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(0, 6, new Piece(Color.WHITE, PieceKind.QUEEN));

		  State expected = start.copy();
		  expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(0, 6, null);
		  expected.setPiece(5, 6, new Piece(Color.WHITE, PieceKind.QUEEN));


		  Move moveWhiteQueen = new Move(new Position(0, 6), new Position(5, 6), null);
		  stateChanger.makeMove(start, moveWhiteQueen);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveWhiteRookAndReachStaleMate()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(7, 1, new Piece(Color.BLACK, PieceKind.BISHOP));
		  start.setPiece(5, 1, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(1, 7, new Piece(Color.WHITE, PieceKind.ROOK));

		  State expected = start.copy();
		  expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(1, 7, null);
		  expected.setPiece(7, 7, new Piece(Color.WHITE, PieceKind.ROOK));

		  Move moveWhiteRook = new Move(new Position(1,7), new Position(7,7), null);
		  stateChanger.makeMove(start, moveWhiteRook);
		  assertEquals(expected, start);	
	  }
	  @Test
	  public void testMoveWhiteKingAndReachStaleMate(){
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 2, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(6, 2, new Piece(Color.WHITE, PieceKind.PAWN));
		  start.setPiece(4, 2, new Piece(Color.WHITE, PieceKind.KING));

		  State expected = start.copy();
		  expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(4, 2, null);
		  expected.setPiece(5, 2, new Piece(Color.WHITE, PieceKind.KING));

		  Move moveWhiteKing = new Move(new Position(4, 2), new Position(5,2), null);
		  stateChanger.makeMove(start, moveWhiteKing);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveWhiteBishopAndReachStaleMate()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		  start.setPiece(5, 1, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(4, 2, new Piece(Color.WHITE, PieceKind.BISHOP));

		  State expected = start.copy();
		  expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(4, 2, null);
		  expected.setPiece(5, 3, new Piece(Color.WHITE, PieceKind.BISHOP));

		  Move moveWhiteBishop = new Move(new Position(4, 2), new Position(5,3), null);
		  stateChanger.makeMove(start, moveWhiteBishop);
		  assertEquals(expected, start);
	  }
	  @Test
	  public void testMoveWhiteKnightAndReachStaleMate()
	  {
		  clearAllPieces();
		  start.setTurn(Color.WHITE);
		  start.setCanCastleKingSide(Color.BLACK, false);
		  start.setCanCastleQueenSide(Color.BLACK, false);
		  start.setCanCastleKingSide(Color.WHITE, false);
		  start.setCanCastleQueenSide(Color.WHITE, false);
		  start.setPiece(7, 3, new Piece(Color.BLACK, PieceKind.KING));
		  start.setPiece(5, 3, new Piece(Color.WHITE, PieceKind.KING));
		  start.setPiece(7, 4, new Piece(Color.WHITE, PieceKind.BISHOP));
		  start.setPiece(5, 1, new Piece(Color.WHITE, PieceKind.KNIGHT));
		  start.setPiece(5, 0, new Piece(Color.WHITE, PieceKind.KNIGHT));

		  State expected = start.copy();
		  expected.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expected.setTurn(Color.BLACK);
		  expected.setPiece(5, 0, null);
		  expected.setPiece(6, 2, new Piece(Color.WHITE, PieceKind.KNIGHT));

		  Move moveWhiteKnight = new Move(new Position(5,0), new Position(6,2), null);
		  stateChanger.makeMove(start, moveWhiteKnight);
		  assertEquals(expected, start);
	  }
	  /*
	   * End Tests by Haoxiang Zuo <haoxiangzuo@gmail.com>
	   */

	  /*
	   * Start Tests by Karthik Mahadevan <karthikjey@gmail.com>
	   */

	  @Test
	  public void testKnightJumpLegal() {
		  Move move2 = new Move(new Position(7, 1), new Position(5, 2), null);

		  start.setPiece(1, 0, null);
		  start.setPiece(2, 0, new Piece(WHITE, PAWN));
		  State expected = start.copy();
		  expected.setPiece(7, 1, null);
		  expected.setPiece(5, 2, new Piece(BLACK, KNIGHT));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

		  start.setTurn(BLACK);

		  stateChanger.makeMove(start, move2);
		  assertEquals(expected, start);
	  }

	  @Test(expected = IllegalMove.class)
	  public void testKnightJumpIllegal() {
		  start.setPiece(1, 0, null);
		  start.setPiece(3, 0, new Piece(WHITE, PAWN));
		  start.setPiece(6, 2, null);
		  start.setPiece(5, 2, new Piece(BLACK, PAWN));
		  start.setTurn(BLACK);

		  Move move = new Move(new Position(7, 1), new Position(5, 2), null);

		  stateChanger.makeMove(start, move);
	  }

	  @Test
	  public void testKnightJumpCapture() {
		  State expected = start.copy();
		  expected.setPiece(1, 2, null);
		  expected.setPiece(6, 1, null);
		  expected.setPiece(3, 1, new Piece(BLACK, PAWN));
		  expected.setPiece(7,1,null);
		  expected.setPiece(5,2,new Piece(BLACK, KNIGHT));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

		  start.setPiece(1, 2, null);
		  start.setPiece(6, 1, null);
		  start.setPiece(3, 1, new Piece(BLACK, PAWN));
		  start.setPiece(new Position(5,2),new Piece(WHITE,PAWN));
		  start.setTurn(BLACK);
		  Move move = new Move(new Position(7,1), new Position(5, 2), null);
		  stateChanger.makeMove(start, move);

		  assertEquals(start,expected);
	  }

	  @Test(expected = IllegalMove.class) 
	  public void testKnightJumpIllegalSquare() {
		  start.setPiece(1, 0, null);
		  start.setPiece(2, 0, new Piece(WHITE, PAWN));
		  Move move = new Move(new Position(7, 1), new Position(5, 1), null);
		  start.setTurn(BLACK);
		  stateChanger.makeMove(start, move);
	  }

	  @Test(expected = IllegalMove.class) 
	  public void testKnightJumpIllegal1() {
		  start.setPiece(1, 0, null);
		  start.setPiece(2, 0, new Piece(WHITE, PAWN));
		  Move move = new Move(new Position(7, 1), new Position(6, 3), null);
		  start.setTurn(BLACK);
		  stateChanger.makeMove(start, move);
	  }

	  @Test
	  public void testBishopMovement1() {
		  start.setPiece(1, 4, null);
		  start.setPiece(2, 4, new Piece(WHITE, PAWN));
		  start.setPiece(6, 1, null);
		  start.setPiece(5, 1, new Piece(BLACK, PAWN));
		  Move move = new Move(new Position(0, 5), new Position(5, 0), null);
		  State expected = start.copy();

		  expected.setPiece(0, 5, null);
		  expected.setPiece(5, 0, new Piece(WHITE, BISHOP));
		  expected.setTurn(BLACK);
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

		  stateChanger.makeMove(start, move);
		  assertEquals(start,expected);
	  }

	  @Test(expected = IllegalMove.class)
	  public void testIllegalBishopJump() {
		  start.setTurn(BLACK);
		  Move move = new Move(new Position(7, 5), new Position(2, 0), null);
		  stateChanger.makeMove(start, move);
	  }


	  @Test(expected = IllegalMove.class)
	  public void testBishopIllegalMoveStraight() {
		  start.setPiece(1, 6, null);
		  start.setPiece(3, 6, new Piece(WHITE, PAWN));
		  start.setPiece(6, 5, null);
		  start.setPiece(5, 5, new Piece(BLACK, PAWN));
		  start.setTurn(BLACK);
		  Move move = new Move(new Position(7,5), new Position(6, 5), null);

		  stateChanger.makeMove(start,move);
	  }


	  @Test(expected = IllegalMove.class)
	  public void testRookIllegalJump() {
		  start.setPiece(1, 0, null);
		  start.setPiece(2, 0, new Piece(WHITE, PAWN));
		  start.setTurn(BLACK);

		  Move move = new Move(new Position(7, 0), new Position(5, 0), null);
		  stateChanger.makeMove(start, move);
	  }

	  @Test
	  public void testRookForwardMovement5() {
		  start.setPiece(1, 1, null);
		  start.setPiece(4, 1, new Piece(WHITE, PAWN));
		  start.setPiece(6, 0, null);
		  start.setPiece(4, 0, new Piece(BLACK, PAWN));

		  Move move = new Move(new Position(7, 0), new Position(5, 0), null);

		  State expected = start.copy();

		  start.setTurn(BLACK);

		  expected.setPiece(7, 0, null);
		  expected.setPiece(5, 0, new Piece(BLACK, ROOK));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  expected.setCanCastleQueenSide(BLACK, false);

		  stateChanger.makeMove(start, move);

		  assertEquals(start,expected);
	  }


	  @Test
	  public void testRookAcrossMovement5() {
		  start.setPiece(1, 7, null);
		  start.setPiece(5, 7, new Piece(WHITE, PAWN));
		  start.setPiece(6, 0, null);
		  start.setPiece(4, 0, new Piece(BLACK, PAWN));
		  start.setPiece(7, 0, null);
		  start.setPiece(5, 0, new Piece(BLACK, ROOK));
		  start.setCanCastleQueenSide(BLACK, false);

		  Move move = new Move(new Position(5, 0), new Position(5, 6), null);

		  State expected = start.copy();

		  start.setTurn(BLACK);

		  expected.setPiece(5, 0, null);
		  expected.setPiece(5, 6, new Piece(BLACK, ROOK));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

		  stateChanger.makeMove(start, move);

		  assertEquals(start,expected);
	  }


	  @Test
	  public void testRookCapture5() {
		  start.setPiece(1, 7, null);
		  start.setPiece(5, 7, new Piece(WHITE, PAWN));
		  start.setPiece(6, 0, null);
		  start.setPiece(4, 0, new Piece(BLACK, PAWN));
		  start.setPiece(7, 0, null);
		  start.setPiece(5, 0, new Piece(BLACK, ROOK));
		  start.setCanCastleQueenSide(BLACK, false);

		  Move move = new Move(new Position(5, 0), new Position(5, 7), null);

		  State expected = start.copy();

		  start.setTurn(BLACK);

		  expected.setPiece(5, 0, null);
		  expected.setPiece(5, 7, new Piece(BLACK, ROOK));


		  stateChanger.makeMove(start, move);

		  assertEquals(start,expected);
	  }

	  @Test(expected = IllegalMove.class)
	  public void testRookCapturesOwnPawn() {
		  start.setPiece(1, 7, null);
		  start.setPiece(2, 7, new Piece(WHITE, PAWN));
		  start.setTurn(BLACK);
		  Move move = new Move(new Position(7, 0), new Position(6, 0), null);
		  stateChanger.makeMove(start, move);
	  }


	  @Test(expected = IllegalMove.class)
	  public void testIllegalRookMovementDiagonal() {
		  start.setPiece(1, 0, null);
		  start.setPiece(3, 0, new Piece(WHITE, PAWN));
		  start.setPiece(6, 1, null);
		  start.setPiece(5, 1, new Piece(BLACK, PAWN));
		  Move move = new Move(new Position(7, 0), new Position(6, 1), null);
		  start.setTurn(BLACK);
		  stateChanger.makeMove(start, move);
	  }

	  @Test
	  public void testQueenForwardMovement() {
		  start.setPiece(6, 3, null);
		  start.setPiece(6, 4, null);
		  start.setPiece(1, 3, null);
		  start.setPiece(1, 4, null);
		  start.setPiece(7, 3, null);
		  start.setPiece(5, 5, new Piece(BLACK, QUEEN));
		  State expected = start.copy();
		  expected.setPiece(5, 5, null);
		  expected.setPiece(2, 5, new Piece(BLACK, QUEEN));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

		  start.setTurn(BLACK);

		  Move move = new Move(new Position(5,5), new Position(2,5), null);
		  stateChanger.makeMove(start, move);
		  assertEquals(start,expected);
	  }

	  @Test
	  public void testQueenAcrossMovement() {
		  start.setPiece(6, 3, null);
		  start.setPiece(6, 4, null);
		  start.setPiece(1, 3, null);
		  start.setPiece(1, 4, null);
		  start.setPiece(7, 3, null);
		  start.setPiece(5, 5, new Piece(BLACK, QUEEN));
		  State expected = start.copy();
		  expected.setPiece(5, 5, null);
		  expected.setPiece(5, 0, new Piece(BLACK, QUEEN));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		  start.setTurn(BLACK);
		  Move move = new Move(new Position(5,5), new Position(5,0), null);
		  stateChanger.makeMove(start, move);
		  assertEquals(start,expected);
	  }

	  @Test
	  public void testQueenDiagonalMovement() {
		  start.setPiece(6, 3, null);
		  start.setPiece(6, 4, null);
		  start.setPiece(1, 3, null);
		  start.setPiece(1, 4, null);
		  start.setPiece(7, 3, null);
		  start.setPiece(5, 5, new Piece(BLACK, QUEEN));
		  State expected = start.copy();
		  expected.setPiece(5, 5, null);
		  expected.setPiece(4, 4, new Piece(BLACK, QUEEN));
		  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

		  start.setTurn(BLACK);

		  Move move = new Move(new Position(5,5), new Position(4,4), null);
		  stateChanger.makeMove(start, move);
		  assertEquals(start,expected);
	  }

	  @Test
	  public void testQueenCapture() {
		  start.setPiece(6, 3, null);
		  start.setPiece(6, 4, null);
		  start.setPiece(1, 3, null);
		  start.setPiece(1, 4, null);
		  start.setPiece(7, 3, null);
		  start.setPiece(5, 3, new Piece(WHITE, QUEEN));
		  start.setPiece(5, 5, new Piece(BLACK, QUEEN));
		  State expected = start.copy();
		  expected.setPiece(5, 5, null);
		  expected.setPiece(5, 3, new Piece(BLACK, QUEEN));
		  start.setTurn(BLACK);
		  Move move = new Move(new Position(5,5), new Position(5,3), null);
		  stateChanger.makeMove(start, move);
	  }

	  @Test(expected = IllegalMove.class)
	  public void testQueenIllegalJump() {
		  start.setPiece(6, 3, null);
		  start.setPiece(6, 4, null);
		  start.setPiece(1, 3, null);
		  start.setPiece(1, 4, null);
		  start.setPiece(7, 3, null);
		  start.setPiece(5, 3, new Piece(WHITE, QUEEN));
		  start.setPiece(5, 5, new Piece(BLACK, QUEEN));
		  start.setTurn(BLACK);
		  Move move = new Move(new Position(5,5), new Position(5,2), null);
		  stateChanger.makeMove(start, move);
	  }

	  @Test(expected = IllegalMove.class)
	  public void testQueenIllegalMovement() {
		  start.setPiece(6, 3, null);
		  start.setPiece(6, 4, null);
		  start.setPiece(1, 3, null);
		  start.setPiece(1, 4, null);
		  start.setPiece(7, 3, null);
		  start.setPiece(5, 5, new Piece(BLACK, QUEEN));
		  start.setTurn(BLACK);
		  Move move = new Move(new Position(5,5), new Position(4,2), null);
		  stateChanger.makeMove(start, move);
	  }


	  /*
	   * End Tests by Karthik Mahadevan <karthikjey@gmail.com>
	   */

	   /*
	    * Begin tests by Ali Shah <thealishah@gmail.com>
	    * 
	    */
	    @Test
	    public void testPawnCanMoveOneSquare() {
	    	int blackPawnRowSpawnPosition = ROWS - 2;
	    	int moveBlackPawnUpOne = -1; 
	    	Move move = new Move(new Position(blackPawnRowSpawnPosition, 0), 
	    			new Position(blackPawnRowSpawnPosition + moveBlackPawnUpOne, 0), null);
	    	State expected = start.copy();
	    	expected.setTurn(WHITE);
	    	expected.setPiece(blackPawnRowSpawnPosition, 0, null);
	    	expected.setPiece(blackPawnRowSpawnPosition + moveBlackPawnUpOne, 0, new Piece(BLACK, PAWN));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);

	    	start.setTurn(BLACK);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    /*
	     * End tests by Ali Shah <thealishah@gmail.com>
	     */

	    /*
	     * Begin Tests by Leo Zis <leozis@gmail.com>
	     */
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

	    /*
	     * End Tests by Leo Zis <leozis@gmail.com>
	     */

	    /*

	     * Start Test by Yuan Jia <jiayuan6311@gmail.com>
	     */
	    @Test
	    public void testBlackCanCastleKingSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	State a_state = b_state.copy();
	    	a_state.setCanCastleKingSide(Color.BLACK, false);
	    	a_state.setTurn(Color.WHITE);
	    	a_state.setPiece(7, 4, null);
	    	a_state.setPiece(7, 7, null);
	    	a_state.setPiece(7, 6, new Piece(Color.BLACK, PieceKind.KING));
	    	a_state.setPiece(7, 5, new Piece(Color.BLACK, PieceKind.ROOK));
	    	a_state.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	stateChanger.makeMove(b_state, move);
	    	assertEquals(b_state, a_state);
	    }


	    @Test
	    public void testBlackCanCastleKingSide2() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	State a_state = b_state.copy();
	    	a_state.setCanCastleKingSide(Color.BLACK, false);
	    	a_state.setTurn(Color.WHITE);
	    	a_state.setPiece(7, 4, null);
	    	a_state.setPiece(7, 7, null);
	    	a_state.setPiece(7, 6, new Piece(Color.BLACK, PieceKind.KING));
	    	a_state.setPiece(7, 5, new Piece(Color.BLACK, PieceKind.ROOK));
	    	a_state.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	stateChanger.makeMove(b_state, move);
	    	assertEquals(b_state, a_state);
	    }


	    @Test
	    public void testBlackCanCastleQueenSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	State a_state = b_state.copy();
	    	a_state.setCanCastleQueenSide(Color.BLACK, false);
	    	a_state.setTurn(Color.WHITE);
	    	a_state.setPiece(7, 4, null);
	    	a_state.setPiece(7, 0, null);
	    	a_state.setPiece(7, 2, new Piece(Color.BLACK, PieceKind.KING));
	    	a_state.setPiece(7, 3, new Piece(Color.BLACK, PieceKind.ROOK));
	    	a_state.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	stateChanger.makeMove(b_state, move);
	    	assertEquals(b_state, a_state);
	    }


	    @Test
	    public void testBlackCanCastleQueenSide2() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	State a_state = b_state.copy();
	    	a_state.setCanCastleQueenSide(Color.BLACK, false);
	    	a_state.setTurn(Color.WHITE);
	    	a_state.setPiece(7, 4, null);
	    	a_state.setPiece(7, 0, null);
	    	a_state.setPiece(7, 2, new Piece(Color.BLACK, PieceKind.KING));
	    	a_state.setPiece(7, 3, new Piece(Color.BLACK, PieceKind.ROOK));
	    	a_state.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	stateChanger.makeMove(b_state, move);
	    	assertEquals(b_state, a_state);
	    }


	    @Test
	    public void testBlackCanCastleRookUnderAttack() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[1][7] = new Piece(Color.WHITE, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	State a_state = b_state.copy();
	    	a_state.setCanCastleKingSide(Color.BLACK, false);
	    	a_state.setTurn(Color.WHITE);
	    	a_state.setPiece(7, 4, null);
	    	a_state.setPiece(7, 7, null);
	    	a_state.setPiece(7, 6, new Piece(Color.BLACK, PieceKind.KING));
	    	a_state.setPiece(7, 5, new Piece(Color.BLACK, PieceKind.ROOK));
	    	a_state.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	stateChanger.makeMove(b_state, move);
	    	assertEquals(b_state, a_state);
	    }


	    @Test
	    public void testBlackCanCastleNextRookUnderAttackQueenSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[1][1] = new Piece(Color.WHITE, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	State a_state = b_state.copy();
	    	a_state.setCanCastleQueenSide(Color.BLACK, false);
	    	a_state.setTurn(Color.WHITE);
	    	a_state.setPiece(7, 4, null);
	    	a_state.setPiece(7, 0, null);
	    	a_state.setPiece(7, 2, new Piece(Color.BLACK, PieceKind.KING));
	    	a_state.setPiece(7, 3, new Piece(Color.BLACK, PieceKind.ROOK));
	    	a_state.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	stateChanger.makeMove(b_state, move);
	    	assertEquals(b_state, a_state);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleOverPiecesKingSide1() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleOverPiecesKingSide2() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
	    	b_board[7][5] = new Piece(Color.BLACK, PieceKind.BISHOP);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleOverPiecesQueenSide1() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[7][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleOverPiecesQueenSide2() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[7][3] = new Piece(Color.BLACK, PieceKind.QUEEN);
	    	b_board[7][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleAfterMovedKingSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleAfterMovedQueenSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleWhenCheckedKingSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[1][4] = new Piece(Color.WHITE, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleWhenCheckedQueenSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[1][4] = new Piece(Color.WHITE, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleOverAttackedKingSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[1][5] = new Piece(Color.WHITE, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleOverAttackedQueenSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[1][3] = new Piece(Color.WHITE, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, false };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleToCheckedKingSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[1][6] = new Piece(Color.WHITE, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, false };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleToCheckedQueenSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[1][2] = new Piece(Color.WHITE, PieceKind.ROOK);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleToBlackKingSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[7][6] = new Piece(Color.BLACK, PieceKind.KNIGHT);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleToBlackQueenSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[7][2] = new Piece(Color.BLACK, PieceKind.BISHOP);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleToWhiteKingSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[7][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackCanNotCastleToWhiteQueenSide() {
	    	Move move = new Move(new Position(7, 4), new Position(7, 2), null);
	    	Piece[][] b_board = new Piece[8][8];
	    	b_board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
	    	b_board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
	    	b_board[7][2] = new Piece(Color.WHITE, PieceKind.BISHOP);
	    	b_board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
	    	boolean[] b_canCastleKingSide = { false, true };
	    	boolean[] b_canCastleQueenSide = { false, true };
	    	State b_state = new State(Color.BLACK, b_board, b_canCastleKingSide,
	    			b_canCastleQueenSide, null, 0, null);
	    	stateChanger.makeMove(b_state, move);
	    }


	    /*
	     * End Tests by Yuan Jia <jiayuan6311@gmail.com>
	     */ 
	    /*

		/*
	     * Start Test by Mengyan Huang <aimeehwang90@gmail.com>
	     */
	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnMovements() {
	    	Move move = new Move(new Position(6, 0), new Position(4, 1), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalRookMovements() {
	    	Move move = new Move(new Position(7, 0), new Position(5, 1), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalKnightMovements() {
	    	Move move = new Move(new Position(7, 1), new Position(6, 0), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalBishopMovements() {
	    	Move move = new Move(new Position(7, 2), new Position(6, 2), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalQueenMovements(){
	    	Move move = new Move(new Position(7, 3), new Position(5, 2), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalKingMovements(){
	    	Move move = new Move(new Position(7, 4), new Position(5, 3), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnLeapOverOtherPieceMovements() {
	    	//Trying to move a black pawn for 2 squares while another piece is right in front of it
	    	start.setPiece(6, 2, new Piece(Color.BLACK, PieceKind.PAWN));
	    	start.setPiece(5, 2, new Piece(Color.WHITE, PieceKind.PAWN));
	    	start.setPiece(1, 2, null);
	    	Move move = new Move(new Position(6, 2), new Position(4, 2), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalRookLeapOverOtherPieceMovements() {
	    	start.setPiece(4, 4, new Piece(Color.BLACK, PieceKind.ROOK));
	    	start.setPiece(4, 5, new Piece(Color.WHITE, PieceKind.PAWN));
	    	start.setPiece(1, 5, null);
	    	start.setPiece(7, 7, null);
	    	start.setPiece(6, 7, null);
	    	Move move = new Move(new Position(4, 4), new Position(4, 6), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalBishopLeapOverOtherPieceMovements() {
	    	start.setPiece(5, 4, new Piece(BLACK, PieceKind.BISHOP));
	    	start.setPiece(4, 3, new Piece(WHITE, PieceKind.PAWN));
	    	start.setPiece(7, 2, null);
	    	start.setPiece(1, 3, null);
	    	start.setPiece(6, 3, null);
	    	Move move = new Move(new Position(5, 4), new Position(3, 2), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalQueenLeapOverOtherPieceMovements() {
	    	start.setPiece(5, 5, new Piece(Color.BLACK, PieceKind.QUEEN));
	    	start.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.PAWN));
	    	start.setPiece(7, 3, null);
	    	start.setPiece(6, 4, null);
	    	start.setPiece(1, 4, null);
	    	Move move = new Move(new Position(5, 4), new Position(3, 2), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalCastling_KingAlreadyMoved() {
	    	start.setCanCastleKingSide(Color.BLACK, false);
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalCastling_KingInCheck() {
	    	//Trying to do castle while king is in check by the bishop
	    	start.setCanCastleKingSide(Color.BLACK, true);
	    	start.setPiece(5, 2, new Piece(Color.WHITE, PieceKind.BISHOP));
	    	start.setPiece(6, 3, null);
	    	start.setPiece(0, 5, null);
	    	start.setPiece(1, 6, null);
	    	start.setPiece(7, 5, null);
	    	start.setPiece(7, 6, null);
	    	Move move = new Move(new Position(7, 4), new Position(7, 6), null);
	    	stateChanger.makeMove(start, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testIllegalEnpassant_WrongEndSquare() {
	    	//Move a black pawn to the wrong end-square after performed an Enpassant
	    	start.setPiece(3, 5, new Piece(Color.WHITE, PieceKind.PAWN));
	    	start.setEnpassantPosition(new Position(3,5));
	    	start.setPiece(3, 6, new Piece(Color.BLACK, PieceKind.PAWN));
	    	start.setPiece(1, 5, null);
	    	start.setPiece(6, 6, null);
	    	Move move = new Move(new Position(3,6), new Position(3,5), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalCapture_CaptureItsOwnPiece() {
	    	//Trying to capture a piece of its own color
	    	start.setPiece(4, 4, new Piece(Color.BLACK,PieceKind.PAWN));
	    	start.setPiece(5, 4, new Piece(Color.BLACK,PieceKind.QUEEN));
	    	start.setPiece(6, 4, null);
	    	start.setPiece(6, 3, null);
	    	start.setPiece(7, 3, null);
	    	Move move = new Move(new Position(5,4), new Position(4,4), null);
	    	stateChanger.makeMove(start, move);	    
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalCapture_CausedByIllegalRandomMoves() {
	    	//Trying to capture a white piece using random illegal moves
	    	Move move = new Move(new Position(6,0), new Position(1,0), null);
	    	stateChanger.makeMove(start, move);	    
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPromote_NotReach8thRank() {
	    	//Trying to promote a black pawn while it cannot reach the 8th rank
	    	start.setPiece(2, 2, new Piece(Color.BLACK, PieceKind.PAWN));
	    	start.setPiece(6, 2, null);
	    	start.setPiece(1, 2, null);
	    	//start.setPiece(0, 2, null);
	    	Move move = new Move(new Position(2,2), new Position(1,2), PieceKind.KNIGHT);
	    	stateChanger.makeMove(start, move);	
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPromote_NotPawn() {
	    	//Trying to promote a black piece while it is not a pawn
	    	start.setPiece(1, 2, new Piece(Color.BLACK, PieceKind.KNIGHT));
	    	start.setPiece(1, 2, null);
	    	start.setPiece(6, 2, null);
	    	start.setPiece(0, 2, null);
	    	Move move = new Move(new Position(1,2), new Position(0,2), PieceKind.QUEEN);
	    	stateChanger.makeMove(start, move);	
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalResponseToCheck() {
	    	start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
	    	start.setPiece(5, 2, new Piece(Color.WHITE, PieceKind.QUEEN));
	    	start.setPiece(6, 3, null);
	    	start.setPiece(0, 3, null);
	    	start.setPiece(1, 3, null);
	    	Move move = new Move(new Position(7,4), new Position(6,3), null);
	    	stateChanger.makeMove(start, move);	
	    }

	    @Test(expected = IllegalMove.class)
	    public void testWhenIllegalTurn() {
	    	start.setTurn(Color.WHITE);
	    	// Trying to move a BLACK pawn when it is the WHITE's turn.
	    	Move move = new Move(new Position(6, 0), new Position(4, 0), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testMoveStartsFromEmptySquare() {
	    	Move move = new Move(new Position(4, 0), new Position(3, 0), null);
	    	stateChanger.makeMove(start, move);
	    }	

	    @Test(expected = IllegalMove.class)
	    public void testWhenGameAlreadyOver() {
	    	start.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
	    	start.setGameResult(new GameResult(Color.WHITE, GameResultReason.FIFTY_MOVE_RULE));
	    	Move move = new Move(new Position(6, 0), new Position(4, 0), null);
	    	stateChanger.makeMove(start, move);
	    }
	    /*
	     * End Tests by Mengyan Huang <aimeehwang90@gmail.com>
	     */	


	    /*
	     * Start Tests by Zhaohui Zhang <bravezhaohui@gmail.com>
	     */
	    @Test
	    public void testStartStatusHasNoEnpassantPosition() {
	    	assertEquals(null, start.getEnpassantPosition());
	    }

	    @Test
	    public void testWhitePawnCanMoveEnpassantPosition() {
	    	Move move = new Move(new Position(1, 0), new Position(3, 0), null);
	    	Position expectedEnpassantPosition = new Position(3, 0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(start.getEnpassantPosition(), expectedEnpassantPosition);
	    }

	    @Test
	    public void testPawnOneSquareMoveWillNotChangeEnpassantPosition() {
	    	Move move = new Move(new Position(1, 0), new Position(2, 0), null);
	    	State expected = start.copy();
	    	expected.setTurn(BLACK);
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(2, 0, new Piece(WHITE, PAWN));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	expected.setEnpassantPosition(null);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test
	    public void testPawnTwoSquaresMoveCanChangeEnpassantPosition() {
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

	    @Test
	    public void testBlackEnpassantPositionBeforeWhitePawnCapture() {
	    	Move move = new Move(new Position(6, 6), new Position(4, 6), null);
	    	State former = start.copy();
	    	former.setTurn(BLACK);
	    	former.setPiece(1, 5, null);
	    	former.setPiece(2, 5, new Piece(WHITE, PAWN));
	    	former.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	former.setEnpassantPosition(null);
	    	State expected = former.copy();
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 6, null);
	    	expected.setPiece(4, 6, new Piece(BLACK, PAWN));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	expected.setEnpassantPosition(new Position(4, 6));
	    	stateChanger.makeMove(former, move);
	    	assertEquals(expected, former);
	    }

	    @Test
	    public void testWhitePawnCanCaptureByEnpassant() {
	    	Move move = new Move(new Position(4, 5), new Position(5, 6), null);
	    	Position enpassantPositionOfBlack = new Position(4, 6);
	    	State former = start.copy();
	    	former.setTurn(WHITE);
	    	former.setPiece(1, 5, null);
	    	former.setPiece(4, 5, new Piece(WHITE, PAWN));
	    	former.setPiece(6, 0, null);
	    	former.setPiece(5, 0, new Piece(BLACK, PAWN));
	    	former.setPiece(6, 6, null);
	    	former.setPiece(4, 6, new Piece(BLACK, PAWN));
	    	former.setEnpassantPosition(enpassantPositionOfBlack);
	    	State expected = former.copy();
	    	expected.setTurn(BLACK);
	    	expected.setPiece(4, 5, null);
	    	expected.setPiece(5, 6, new Piece(WHITE, PAWN));
	    	expected.setPiece(4, 6, null);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	expected.setEnpassantPosition(null);
	    	stateChanger.makeMove(former, move);
	    	assertEquals(expected, former);
	    }

	    @Test
	    public void testEnpassantPositionSetNullIfWhitePawnNotCapture() {
	    	Move move = new Move(new Position(1, 7), new Position(2, 7), null);
	    	Position enpassantPositionOfBlack = new Position(4, 6);
	    	State former = start.copy();
	    	former.setTurn(WHITE);
	    	former.setPiece(1, 5, null);
	    	former.setPiece(4, 5, new Piece(WHITE, PAWN));
	    	former.setPiece(6, 0, null);
	    	former.setPiece(5, 0, new Piece(BLACK, PAWN));
	    	former.setPiece(6, 6, null);
	    	former.setPiece(4, 6, new Piece(BLACK, PAWN));
	    	former.setEnpassantPosition(enpassantPositionOfBlack);
	    	State expected = former.copy();
	    	expected.setTurn(BLACK);
	    	expected.setPiece(1, 7, null);
	    	expected.setPiece(2, 7, new Piece(WHITE, PAWN));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	expected.setEnpassantPosition(null);
	    	stateChanger.makeMove(former, move);
	    	assertEquals(expected, former);
	    }

	    @Test
	    public void testWhitePawnCanBeCapturedIfInEnpassantPosition() {
	    	Move move = new Move(new Position(3, 1), new Position(2, 0), null);
	    	Position enpassantPositionOfWhite = new Position(3, 0);
	    	State former = start.copy();
	    	former.setTurn(BLACK);
	    	former.setPiece(1, 0, null);
	    	former.setPiece(3, 0, new Piece(WHITE, PAWN));
	    	former.setPiece(6, 1, null);
	    	former.setPiece(3, 1, new Piece(BLACK, PAWN));
	    	former.setPiece(1, 2, null);
	    	former.setPiece(3, 2, new Piece(WHITE, PAWN));
	    	former.setEnpassantPosition(enpassantPositionOfWhite);
	    	former.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	State expected = former.copy();
	    	expected.setTurn(WHITE);
	    	expected.setPiece(3, 0, null);
	    	expected.setPiece(3, 1, null);
	    	expected.setPiece(2, 0, new Piece(BLACK, PAWN));
	    	expected.setEnpassantPosition(null);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(former, move);
	    	assertEquals(expected, former);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testWhitePawnCanNotBeCapturedIfNotInEnpassantPosition() {
	    	Move move = new Move(new Position(3, 1), new Position(2, 0), null);
	    	State former = start.copy();
	    	former.setTurn(BLACK);
	    	former.setPiece(1, 0, null);
	    	former.setPiece(3, 0, new Piece(WHITE, PAWN));
	    	former.setPiece(6, 1, null);
	    	former.setPiece(3, 1, new Piece(BLACK, PAWN));
	    	former.setPiece(1, 2, null);
	    	former.setPiece(3, 2, new Piece(WHITE, PAWN));
	    	former.setEnpassantPosition(null);
	    	former.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(former, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnCanNotCrossNonEnpassantPosition() {
	    	Move move = new Move(new Position(3, 1), new Position(2, 2), null);
	    	Position enpassantPositionOfWhite = new Position(3, 0);
	    	State former = start.copy();
	    	former.setTurn(BLACK);
	    	former.setPiece(1, 0, null);
	    	former.setPiece(3, 0, new Piece(WHITE, PAWN));
	    	former.setPiece(6, 1, null);
	    	former.setPiece(3, 1, new Piece(BLACK, PAWN));
	    	former.setPiece(1, 2, null);
	    	former.setPiece(3, 2, new Piece(WHITE, PAWN));
	    	former.setEnpassantPosition(enpassantPositionOfWhite);
	    	former.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(former, move);
	    }

	    @Test
	    public void testWhitePawnCanNotBeCapturedIfBlackPawnNotCrossEnpassantPosition() {
	    	Move move = new Move(new Position(3, 1), new Position(2, 1), null);
	    	Position enpassantPositionOfWhite = new Position(3, 0);
	    	State former = start.copy();
	    	former.setTurn(BLACK);
	    	former.setPiece(1, 0, null);
	    	former.setPiece(3, 0, new Piece(WHITE, PAWN));
	    	former.setPiece(6, 1, null);
	    	former.setPiece(3, 1, new Piece(BLACK, PAWN));
	    	former.setPiece(1, 2, null);
	    	former.setPiece(3, 2, new Piece(WHITE, PAWN));
	    	former.setEnpassantPosition(enpassantPositionOfWhite);
	    	former.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	State expected = former.copy();
	    	expected.setTurn(WHITE);
	    	expected.setPiece(3, 1, null);
	    	expected.setPiece(2, 1, new Piece(BLACK, PAWN));
	    	expected.setEnpassantPosition(null);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(former, move);
	    	assertEquals(expected, former);

	    }

	    /*This unit test is not logical since black pawn cannot move over rook. So I deleted it.
	     * @Test
		//wrong
		public void testWhitePawnCanEnpassantAlsoCaptureAnotherPiece() {
			Move move = new Move(new Position(4, 1), new Position(5, 0), null);
			Piece[][] board = new Piece[8][8];
			board[4][0] = new Piece(BLACK, PAWN);
			board[5][0] = new Piece(BLACK, ROOK);
			board[4][1] = new Piece(WHITE, PAWN);
			board[0][3] = new Piece(WHITE, KING);
			board[7][4] = new Piece(BLACK, KING);
			State former = new State(WHITE, board, new boolean[] { true, true },
					new boolean[] { true, true }, new Position(4, 0), 0, null);
			State expected = former.copy();
			expected.setTurn(BLACK);
			expected.setPiece(4, 0, null);
			expected.setPiece(5, 0, new Piece(WHITE, PAWN));
			expected.setPiece(4, 1, null);
			expected.setEnpassantPosition(null);
			expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
			stateChanger.makeMove(former, move);
			assertEquals(expected, former);

		}*/

	    @Test(expected = IllegalMove.class)
	    public void testWhitePawnCanNotCaptureIfBlackPawnNotInEnpassantPosition() {
	    	Move move = new Move(new Position(4, 5), new Position(5, 6), null);
	    	State former = start.copy();
	    	former.setTurn(WHITE);
	    	former.setPiece(1, 5, null);
	    	former.setPiece(4, 5, new Piece(WHITE, PAWN));
	    	former.setPiece(6, 0, null);
	    	former.setPiece(5, 0, new Piece(BLACK, PAWN));
	    	former.setPiece(6, 6, null);
	    	former.setPiece(4, 6, new Piece(BLACK, PAWN));
	    	former.setEnpassantPosition(null);
	    	stateChanger.makeMove(former, move);
	    }

	    @Test
	    //fixed the bug of illegal position before move
	    public void testEnpassantPositionIfGameOver() {
	    	Piece[][] board = new Piece[8][8];
	    	board[4][0] = new Piece(BLACK, PAWN);
	    	board[4][1] = new Piece(WHITE, PAWN);
	    	board[0][3] = new Piece(WHITE, KING);
	    	board[7][3] = new Piece(BLACK, KING);
	    	board[6][1] = new Piece(WHITE, ROOK);
	    	board[6][5] = new Piece(WHITE, QUEEN);
	    	State former = new State(WHITE, board, new boolean[] { false, false },
	    			new boolean[] { false, false }, new Position(4, 0), 0, null);
	    	Move move = new Move(new Position(6, 1), new Position(7, 1), null);
	    	State expected = former.copy();
	    	GameResult gameResult = new GameResult(WHITE,
	    			GameResultReason.CHECKMATE);
	    	expected.setTurn(BLACK);
	    	expected.setGameResult(gameResult);
	    	expected.setPiece(6, 1, null);
	    	expected.setPiece(7, 1, new Piece(WHITE, ROOK));
	    	expected.setEnpassantPosition(null);
	    	expected.setCanCastleKingSide(WHITE, false);
	    	expected.setCanCastleQueenSide(WHITE, false);
	    	expected.setCanCastleKingSide(BLACK, false);
	    	expected.setCanCastleQueenSide(BLACK, false);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	stateChanger.makeMove(former, move);
	    	assertEquals(expected, former);
	    }

	    /*@Test
		//fixed bug for illegal states which is already a checkmate result
		public void testEnpassantPositionCanNotBeAnywhere() {
			Piece[][] board = new Piece[8][8];
			board[4][0] = new Piece(BLACK, PAWN);
			board[4][1] = new Piece(WHITE, PAWN);
			board[0][3] = new Piece(WHITE, KING);
			board[7][4] = new Piece(BLACK, KING);
			State former = new State(WHITE, board, new boolean[] { true, true },
					new boolean[] { true, true }, new Position(4, 0), 0, null);
			assertTrue(former.getEnpassantPosition() != null);
			assertTrue(former.getEnpassantPosition().getCol() == 3
					|| former.getEnpassantPosition().getCol() == 4);
		}*/

	    @Test
	    public void testEnpassantPositionCanNotPlaceOtherPieceExceptPawn() {
	    	Piece[][] board = new Piece[8][8];
	    	board[4][0] = new Piece(BLACK, PAWN);
	    	board[4][1] = new Piece(WHITE, PAWN);
	    	board[0][3] = new Piece(WHITE, KING);
	    	board[7][4] = new Piece(BLACK, KING);
	    	State former = new State(WHITE, board, new boolean[] { true, true },
	    			new boolean[] { true, true }, new Position(4, 0), 0, null);
	    	assertTrue(former.getEnpassantPosition() != null);
	    	int row = former.getEnpassantPosition().getRow();
	    	int collum = former.getEnpassantPosition().getCol();
	    	assertTrue(board[row][collum].getKind() == PAWN);
	    }

	    // test for other cases while related to en passant
	    @Test(expected = IllegalMove.class)
	    public void testPawnCanNotMoveDiagonallyWithoutEnpassantOrCapture() {
	    	Move move = new Move(new Position(6, 1), new Position(5, 0), null);
	    	State former = start.copy();
	    	former.setTurn(BLACK);
	    	former.setPiece(1, 0, null);
	    	former.setPiece(3, 0, new Piece(WHITE, PAWN));
	    	former.setEnpassantPosition(new Position(3, 0));
	    	former.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(former, move);
	    }

	    @Test
	    public void testWhitePawnCanMoveDiagonallyWithoutEnpassant() {
	    	Move move = new Move(new Position(1, 7), new Position(2, 6), null);
	    	Piece[][] board = new Piece[8][8];
	    	board[4][0] = new Piece(BLACK, PAWN);
	    	board[2][6] = new Piece(BLACK, PAWN);
	    	board[4][1] = new Piece(WHITE, PAWN);
	    	board[1][7] = new Piece(WHITE, PAWN);
	    	board[0][3] = new Piece(WHITE, KING);
	    	board[7][4] = new Piece(BLACK, KING);
	    	State former = new State(WHITE, board, new boolean[] { true, true },
	    			new boolean[] { true, true }, new Position(4, 0), 0, null);
	    	State expected = former.copy();
	    	expected.setTurn(BLACK);
	    	expected.setPiece(1, 7, null);
	    	expected.setPiece(2, 6, new Piece(WHITE, PAWN));
	    	expected.setEnpassantPosition(null);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(former, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testWhitePawnCanNotMoveTwoSquaresIfNotFromInitialPosition() {
	    	Piece[][] board = new Piece[8][8];
	    	board[4][0] = new Piece(BLACK, PAWN);
	    	board[5][0] = new Piece(BLACK, ROOK);
	    	board[4][1] = new Piece(WHITE, PAWN);
	    	board[0][3] = new Piece(WHITE, KING);
	    	board[7][4] = new Piece(BLACK, KING);
	    	State former = new State(WHITE, board, new boolean[] { true, true },
	    			new boolean[] { true, true }, null, 0, null);
	    	Move move = new Move(new Position(4, 1), new Position(6, 1), null);
	    	stateChanger.makeMove(former, move);
	    }
	    //add 4 rooks to ensure the castling state
	    @Test
	    public void testWhitePawnMoveOneSquareNotFromInitialPositionNotChangeEnpassantPosition() {
	    	Piece[][] board = new Piece[8][8];
	    	board[4][0] = new Piece(BLACK, PAWN);
	    	board[5][0] = new Piece(BLACK, ROOK);
	    	board[4][1] = new Piece(WHITE, PAWN);
	    	board[0][4] = new Piece(WHITE, KING);
	    	board[7][4] = new Piece(BLACK, KING);
	    	board[0][0] = new Piece(WHITE, ROOK);
	    	board[0][7] = new Piece(WHITE, ROOK);
	    	board[7][0] = new Piece(BLACK, ROOK);
	    	board[7][7] = new Piece(BLACK, ROOK);
	    	State former = new State(WHITE, board, new boolean[] { true, true },
	    			new boolean[] { true, true }, new Position(4, 0), 0, null);
	    	Move move = new Move(new Position(4, 1), new Position(5, 1), null);
	    	State expected = former.copy();
	    	expected.setTurn(BLACK);
	    	expected.setPiece(4, 1, null);
	    	expected.setPiece(5, 1, new Piece(WHITE, PAWN));
	    	expected.setEnpassantPosition(null);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(former, move);
	    	assertEquals(expected, former);
	    }
	    //set castling condition for Black to false	
	    @Test
	    public void testEnpassantPositionCanChangeFromBlackPawnToWhitePawnContinuously(){
	    	Piece[][] board = new Piece[8][8];
	    	board[4][0] = new Piece(BLACK, PAWN);
	    	board[1][1] = new Piece(WHITE, PAWN);
	    	board[0][3] = new Piece(WHITE, KING);
	    	board[7][4] = new Piece(BLACK, KING);
	    	State former = new State(WHITE, board, new boolean[] { false, false },
	    			new boolean[] { false, false }, new Position(4, 0), 0, null);
	    	Move move = new Move(new Position(1, 1), new Position(3, 1), null);
	    	State expected = former.copy();
	    	expected.setTurn(BLACK);
	    	expected.setPiece(1, 1, null);
	    	expected.setPiece(3, 1, new Piece(WHITE, PAWN));
	    	expected.setEnpassantPosition(new Position(3, 1));
	    	expected.setCanCastleKingSide(WHITE, false);
	    	expected.setCanCastleKingSide(BLACK, false);
	    	expected.setCanCastleQueenSide(WHITE, false);
	    	expected.setCanCastleQueenSide(BLACK, false);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(former, move);
	    	assertEquals(expected, former);
	    }

	    /*
	     * End Tests by Zhaohui Zhang <bravezhaohui@gmail.com>
	     */

	    /*Start Paul Sultan Tests <Paul.Sultan@gmail.com>*/
	    @Test
	    public void testLegalKingCanMoveOneSquare(){
	    	State expected = start.copy();

	    	start.setPiece(1, 4, null);
	    	Move move = new Move(new Position(0, 4), new Position(1, 4), null);
	    	stateChanger.makeMove(start, move);

	    	expected.setTurn(BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	expected.setPiece(0, 4, null);
	    	expected.setPiece(1, 4, new Piece(WHITE, KING));
	    	expected.setCanCastleKingSide(WHITE, false);
	    	expected.setCanCastleQueenSide(WHITE, false);
	    	assertEquals(expected, start);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalKingCanMoveOneSquare(){
	    	//King cannot move on same color piece  	  
	    	Move move = new Move(new Position(4, 0), new Position(3, 0), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test
	    public void testLegalKingCanMoveDiagnol(){
	    	State expected = start.copy();

	    	start.setPiece(1, 3, null);
	    	Move move = new Move(new Position(0, 4), new Position(1, 3), null);
	    	stateChanger.makeMove(start, move);

	    	expected.setTurn(BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	expected.setCanCastleKingSide(WHITE, false);
	    	expected.setCanCastleQueenSide(WHITE, false);
	    	expected.setPiece(1, 3, new Piece(WHITE, KING));
	    	expected.setPiece(0, 4, null);
	    	assertEquals(expected, start);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalKingMoveIntoCheck(){
	    	//King cannot move to space that causes check
	    	start.setPiece(0, 0, null);
	    	start.setPiece(1, 0, null);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(0, 4, null);
	    	start.setPiece(0, 1, new Piece(WHITE, KING));

	    	Move move = new Move(new Position(0, 1), new Position(0, 0), null);	  	  
	    	stateChanger.makeMove(start, move);
	    }


	  @Test
	  public void testLegalQueenCastling(){	  
	  	  start.setPiece(0,1, null);
	  	  start.setPiece(0,2, null);
	  	  start.setPiece(0,3, null);

	  	  State expected = start.copy();

	  	  Move kingMove = new Move(new Position(0, 4), new Position(0, 2), null);
	  	  stateChanger.makeMove(start, kingMove);

	  	  expected.setTurn(BLACK);
	  	  expected.setCanCastleQueenSide(WHITE, false);
	  	  expected.setCanCastleKingSide(WHITE, false);
	  	  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	  	  expected.setPiece(0, 3, new Piece(WHITE, ROOK));
	  	  expected.setPiece(0, 2, new Piece(WHITE, KING));
	  	  expected.setPiece(0, 4, null);
	  	  expected.setPiece(0, 0, null);
	  	  assertEquals(expected, start);
	  }



	  @Test
	  public void testLegalKingCastling(){
	  	  start.setPiece(0,5, null);
	  	  start.setPiece(0,6, null);

	  	  State expected = start.copy();

	  	  Move kingMove = new Move(new Position(0, 4), new Position(0, 6), null);
	  	  stateChanger.makeMove(start, kingMove);

	  	  expected.setCanCastleQueenSide(WHITE, false);
	  	  expected.setCanCastleKingSide(WHITE, false);
	  	  expected.setTurn(BLACK);
	  	  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	  	  expected.setPiece(0, 5, new Piece(WHITE, ROOK));
	  	  expected.setPiece(0, 6, new Piece(WHITE, KING));
	  	  expected.setPiece(0, 7, null);
	  	  expected.setPiece(0, 4, null);
	  	  assertEquals(expected, start);
	  }


	    @Test(expected = IllegalMove.class)
	    public void testIllegalQueenCastling(){
	    	//piece in the way of castling
	    	start.setPiece(0, 1, null);
	    	start.setPiece(0, 2, null);

	    	Move kingMove = new Move(new Position(4, 0), new Position(2, 0), null);
	    	stateChanger.makeMove(start, kingMove);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnTwoSquares(){
	    	//can only move two in first position
	    	start.setPiece(1, 0, null);
	    	start.setPiece(2, 0, new Piece(WHITE, PAWN));
	    	Move move = new Move(new Position(2, 0), new Position(4, 0), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnTwoSquaresBlock(){
	    	//cannot move two when blocked
	    	start.setPiece(6, 0, null);
	    	start.setPiece(2, 0, new Piece(BLACK, PAWN));
	    	Move move = new Move(new Position(1, 0), new Position(3, 0), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test
	    public void testLegalPawnDiagnolCapture(){
	    	start.setPiece(1, 0, null);
	    	start.setPiece(6, 1, null);

	    	State expected = start.copy();

	    	start.setPiece(4, 1, new Piece(BLACK, PAWN));
	    	start.setPiece(3, 0, new Piece(WHITE, PAWN));
	    	Move move = new Move(new Position(3, 0), new Position(4, 1), null);
	    	stateChanger.makeMove(start, move);

	    	expected.setTurn(BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	expected.setPiece(4, 1, new Piece(WHITE, PAWN));
	    	expected.setPiece(3, 0, null);
	    	assertEquals(expected, start);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnBack(){
	    	//Pawns can only move forward
	    	start.setPiece(0, 0, null);
	    	Move move = new Move(new Position(0, 1), new Position(0, 0), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnDiagnol(){
	    	//pawn must capture in order to go diagonal
	    	Move move = new Move(new Position(0, 1), new Position(1, 2), null);
	    	stateChanger.makeMove(start, move);
	    }


	    @Test
	    public void testLegalPawnRookPromote(){
	    	start.setPiece(7, 0, null);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, null);

	    	start.setCanCastleQueenSide(Color.BLACK, false);

	    	State expected = start.copy();

	    	start.setPiece(6, 0, new Piece(WHITE, PAWN));
	    	Move move = new Move(new Position(6, 0), new Position(7, 0), ROOK);
	    	stateChanger.makeMove(start, move);

	    	expected.setTurn(BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	expected.setPiece(7, 0, new Piece(WHITE, ROOK));
	    	assertEquals(expected, start);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnMaxMove(){
	    	start.setNumberOfMovesWithoutCaptureNorPawnMoved(50);
	    	Move move = new Move(new Position(0, 1), new Position(0, 2), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalRookMovedCastle(){
	    	start.setPiece(0, 0, null);
	    	start.setPiece(0, 1, new Piece(WHITE, ROOK));
	    	start.setPiece(0, 2, null);
	    	start.setPiece(0, 3, null);
	    	start.setCanCastleQueenSide(WHITE, false);
	    	Move move = new Move(new Position(0, 4), new Position(0, 2), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnOneBlocked(){
	    	start.setPiece(2, 4, new Piece(WHITE, KING));
	    	start.setPiece(0, 4, null);
	    	Move move = new Move(new Position(1, 4), new Position(2, 4), null);
	    	stateChanger.makeMove(start, move);
	    }


	    @Test
	    public void testLegalKingBackwards(){
		  State expected = start.copy();

		  start.setPiece(0, 4, null);
		  start.setPiece(1, 4, new Piece(WHITE, KING));

	  	  Move move = new Move(new Position(1, 4), new Position(0, 4), null);
	  	  stateChanger.makeMove(start, move);

	  	  expected.setTurn(BLACK);
	  	  expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	  	  expected.setCanCastleKingSide(WHITE, false);
	  	  expected.setCanCastleQueenSide(WHITE, false);
	  	  expected.setPiece(1, 4, null);
	  	  assertEquals(expected, start);
	  }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnBackwards(){  	  
	    	start.setPiece(0, 3, null);
	    	Move move = new Move(new Position(1, 3), new Position(0, 3), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test
	    public void testLegalPawnTwoSquares(){
	    	State expected = start.copy();
	    	Move move = new Move(new Position(1, 0), new Position(3, 0), null);
	    	stateChanger.makeMove(start, move);

	    	expected.setTurn(BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	expected.setEnpassantPosition(new Position(3,0));
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(3, 0, new Piece(WHITE, PAWN));
	    	assertEquals(expected, start);
	    }	  

	    @Test(expected = IllegalMove.class)
	    public void testIllegalPawnKingPromote(){
	    	start.setPiece(7, 0, null);
	    	start.setPiece(1, 0, null);
	    	start.setPiece(6, 0, new Piece(WHITE, PAWN));

	    	Move move = new Move(new Position(6, 0), new Position(7, 0), KING);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalKingMove2() {
	    	start.setPiece(1, 4, null);

	    	Move move = new Move(new Position(0, 4), new Position(2, 4), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = RuntimeException.class)
	    public void testIllegalKingOffBoard() {
	    	start.setPiece(0, 4, null);
	    	start.setPiece(1, 7, new Piece(WHITE, KING));

	    	Move move = new Move(new Position(1, 7), new Position(1, 8), null);
	    	stateChanger.makeMove(start, move);
	    }

	    /*End Paul Sultan Tests*/
	    /*
	     * Begin Tests by Yueh-Lin Chung <felixjon2000@gmail.com>
	     */
	    @Test
	    public void testBlackPawnPromoteToQueen() {


	    	start.setCanCastleQueenSide(Color.WHITE, false);


	    	State expected = start.copy();
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 0, null);

	    	Move move = new Move(new Position(1, 0), new Position(0, 0), PieceKind.QUEEN);
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 0, null);
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(0, 0, new Piece(BLACK, PieceKind.QUEEN));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test
	    public void testBlackPawnPromoteToRook() {

	    	start.setCanCastleQueenSide(Color.WHITE, false);

	    	State expected = start.copy();
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 0, null);		

	    	Move move = new Move(new Position(1, 0), new Position(0, 0), PieceKind.ROOK);
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 0, null);
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(0, 0, new Piece(BLACK, PieceKind.ROOK));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test
	    public void testBlackPawnPromoteToBishop() {

	    	start.setCanCastleQueenSide(Color.WHITE, false);

	    	State expected = start.copy();
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 0, null);

	    	Move move = new Move(new Position(1, 0), new Position(0, 0), PieceKind.BISHOP);
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 0, null);
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(0, 0, new Piece(BLACK, PieceKind.BISHOP));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test
	    public void testBlackPawnPromoteToKnight() {
	    	start.setCanCastleQueenSide(Color.WHITE, false);


	    	State expected = start.copy();
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 0, null);
	    	Move move = new Move(new Position(1, 0), new Position(0, 0), PieceKind.KNIGHT);
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 0, null);
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(0, 0, new Piece(BLACK, PieceKind.KNIGHT));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToKing() {
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	Piece BlackPawn = new Piece(BLACK, PieceKind.PAWN);
	    	start.setPiece(1, 0, BlackPawn);
	    	Move move = new Move(new Position(1, 0), new Position(0, 0), PieceKind.KING);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToPawn() {
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	Piece BlackPawn = new Piece(BLACK, PieceKind.PAWN);
	    	start.setPiece(1, 0, BlackPawn);
	    	Move move = new Move(new Position(1, 0), new Position(0, 0), PieceKind.PAWN);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test
	    public void testBlackPawnPromoteToQueenAfterCapture() {
	    	State expected = start.copy();
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	Move move = new Move(new Position(1, 0), new Position(0, 1), PieceKind.QUEEN);
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 0, null);
	    	expected.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(0, 1, new Piece(BLACK, PieceKind.QUEEN));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test
	    public void testBlackPawnPromoteToRookAfterCapture() {
	    	State expected = start.copy();
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	Move move = new Move(new Position(1, 0), new Position(0, 1), PieceKind.ROOK);
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 0, null);
	    	expected.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(0, 1, new Piece(BLACK, PieceKind.ROOK));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test
	    public void testBlackPawnPromoteToBishopAfterCapture() {
	    	State expected = start.copy();
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	Move move = new Move(new Position(1, 0), new Position(0, 1), PieceKind.BISHOP);
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 0, null);
	    	expected.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(0, 1, new Piece(BLACK, PieceKind.BISHOP));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test
	    public void testBlackPawnPromoteToKnightAfterCapture() {
	    	State expected = start.copy();
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	Move move = new Move(new Position(1, 0), new Position(0, 1), PieceKind.KNIGHT);
	    	expected.setTurn(WHITE);
	    	expected.setPiece(6, 0, null);
	    	expected.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	expected.setPiece(1, 0, null);
	    	expected.setPiece(0, 1, new Piece(BLACK, PieceKind.KNIGHT));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
	    	stateChanger.makeMove(start, move);
	    	assertEquals(expected, start);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToKingAfterCaptureAtEdge() {
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	Move move = new Move(new Position(1, 0), new Position(0, 1), PieceKind.KING);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToPawnAfterCaptureAtEdge() {
	    	start.setTurn(BLACK);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(1, 0, new Piece(BLACK, PieceKind.PAWN));
	    	start.setPiece(0, 1, new Piece(WHITE, PieceKind.BISHOP));
	    	Move move = new Move(new Position(1, 0), new Position(0, 1), PieceKind.PAWN);
	    	stateChanger.makeMove(start, move);
	    }


	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToQueenWithOneMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(5, 0), PieceKind.QUEEN);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToRookWithOneMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(5, 0), PieceKind.ROOK);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToBishopWithOneMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(5, 0), PieceKind.BISHOP);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToKnightWithOneMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(5, 0), PieceKind.KNIGHT);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToKingWithOneMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(5, 0), PieceKind.KING);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToPawnWithOneMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(5, 0), PieceKind.PAWN);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToQueenWithTwoMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(4, 0), PieceKind.QUEEN);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToRookWithTwoMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(4, 0), PieceKind.ROOK);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToBishopWithTwoMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(4, 0), PieceKind.BISHOP);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToKnightWithTwoMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(4, 0), PieceKind.KNIGHT);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToKingWithTwoMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(4, 0), PieceKind.KING);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToPawnWithTwoMove() {
	    	start.setTurn(BLACK);
	    	Move move = new Move(new Position(6, 0), new Position(4, 0), PieceKind.PAWN);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToQueenAfterCapture() {
	    	start.setTurn(BLACK);
	    	Piece Enemy = new Piece(WHITE, PieceKind.PAWN);
	    	start.setPiece(1, 1, null);
	    	start.setPiece(3, 1, Enemy);
	    	Piece BlackPawn = new Piece(BLACK, PieceKind.PAWN);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(4, 0, BlackPawn);
	    	Move move = new Move(new Position(4, 0), new Position(3, 1), PieceKind.QUEEN);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToRookAfterCapture() {
	    	start.setTurn(BLACK);
	    	Piece Enemy = new Piece(WHITE, PieceKind.PAWN);
	    	start.setPiece(1, 1, null);
	    	start.setPiece(3, 1, Enemy);
	    	Piece BlackPawn = new Piece(BLACK, PieceKind.PAWN);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(4, 0, BlackPawn);
	    	Move move = new Move(new Position(4, 0), new Position(3, 1), PieceKind.ROOK);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToBishopAfterCapture() {
	    	start.setTurn(BLACK);
	    	Piece Enemy = new Piece(WHITE, PieceKind.PAWN);
	    	start.setPiece(1, 1, null);
	    	start.setPiece(3, 1, Enemy);
	    	Piece BlackPawn = new Piece(BLACK, PieceKind.PAWN);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(4, 0, BlackPawn);
	    	Move move = new Move(new Position(4, 0), new Position(3, 1), PieceKind.BISHOP);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToKnightAfterCapture() {
	    	start.setTurn(BLACK);
	    	Piece Enemy = new Piece(WHITE, PieceKind.PAWN);
	    	start.setPiece(1, 1, null);
	    	start.setPiece(3, 1, Enemy);
	    	Piece BlackPawn = new Piece(BLACK, PieceKind.PAWN);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(4, 0, BlackPawn);
	    	Move move = new Move(new Position(4, 0), new Position(3, 1), PieceKind.KNIGHT);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToKingAfterCapture() {
	    	start.setTurn(BLACK);
	    	Piece Enemy = new Piece(WHITE, PieceKind.PAWN);
	    	start.setPiece(1, 1, null);
	    	start.setPiece(3, 1, Enemy);
	    	Piece BlackPawn = new Piece(BLACK, PieceKind.PAWN);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(4, 0, BlackPawn);
	    	Move move = new Move(new Position(4, 0), new Position(3, 1), PieceKind.KING);
	    	stateChanger.makeMove(start, move);		
	    }

	    @Test(expected = IllegalMove.class)
	    public void testBlackPawnIllegalPromoteToPawnAfterCapture() {
	    	start.setTurn(BLACK);
	    	Piece Enemy = new Piece(WHITE, PieceKind.PAWN);
	    	start.setPiece(1, 1, null);
	    	start.setPiece(3, 1, Enemy);
	    	Piece BlackPawn = new Piece(BLACK, PieceKind.PAWN);
	    	start.setPiece(6, 0, null);
	    	start.setPiece(4, 0, BlackPawn);
	    	Move move = new Move(new Position(4, 0), new Position(3, 1), PieceKind.PAWN);
	    	stateChanger.makeMove(start, move);		
	    }
	    /*
	     * End Tests by Yueh-Lin Chung <felixjon2000@gmail.com> 
	     */

	    /*
	     * Begin Tests by Alexander Oskotsky <alex.oskotsky@gmail.com>
	     */			
	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveKnightOnWhitePiece() {
	    	Move move = new Move(new Position(0, 6), new Position(1, 6), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveBishopOnWhitePiece() {
	    	Move move = new Move(new Position(0, 5), new Position(1, 6), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveRookOnWhitePiece() {
	    	Move move = new Move(new Position(0, 7), new Position(1, 7), null);
	    	stateChanger.makeMove(start, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveKnightWhenGameOver() {
	    	Move move = new Move(new Position(0, 6), new Position(1, 6), null);
	    	State gameOverState = start.copy();
	    	gameOverState.setGameResult(new GameResult(Color.BLACK, GameResultReason.CHECKMATE));
	    	stateChanger.makeMove(gameOverState, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveBishopWhenGameOver() {
	    	State gameOverState = start.copy();
	    	gameOverState.setGameResult(new GameResult(Color.BLACK, GameResultReason.CHECKMATE));
	    	gameOverState.setPiece(4, 4, new Piece(WHITE, BISHOP));
	    	gameOverState.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 5), null);

	    	stateChanger.makeMove(gameOverState, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveRookWhenGameOver() {
	    	State gameOverState = start.copy();
	    	gameOverState.setGameResult(new GameResult(Color.BLACK, GameResultReason.CHECKMATE));
	    	gameOverState.setPiece(4, 4, new Piece(WHITE, ROOK));
	    	gameOverState.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(4, 5), null);

	    	stateChanger.makeMove(gameOverState, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveQueenWhenGameOver() {
	    	State gameOverState = start.copy();
	    	gameOverState.setGameResult(new GameResult(Color.BLACK, GameResultReason.CHECKMATE));
	    	gameOverState.setPiece(4, 4, new Piece(WHITE, QUEEN));
	    	gameOverState.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(4, 5), null);

	    	stateChanger.makeMove(gameOverState, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveKnightForwardOneSquare() {
	    	State knightMiddleOfBoardState = start.copy();
	    	knightMiddleOfBoardState.setPiece(4, 4, new Piece(WHITE, KNIGHT));
	    	knightMiddleOfBoardState.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 4), null);

	    	stateChanger.makeMove(knightMiddleOfBoardState, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveBishipForwardOneSquare() {
	    	State bishopMiddleOfBoardState = start.copy();
	    	bishopMiddleOfBoardState.setPiece(4, 4, new Piece(WHITE, BISHOP));
	    	bishopMiddleOfBoardState.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 4), null);

	    	stateChanger.makeMove(bishopMiddleOfBoardState, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveRookDiagonally() {
	    	State rookMiddleOfBoardState = start.copy();
	    	rookMiddleOfBoardState.setPiece(4, 4, new Piece(WHITE, ROOK));
	    	rookMiddleOfBoardState.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 5), null);

	    	stateChanger.makeMove(rookMiddleOfBoardState, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveKnightDiagonally() {
	    	State knightMiddleOfBoardState = start.copy();
	    	knightMiddleOfBoardState.setPiece(4, 4, new Piece(WHITE, KNIGHT));
	    	knightMiddleOfBoardState.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 5), null);

	    	stateChanger.makeMove(knightMiddleOfBoardState, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveBishopOverBlackPiece() {
	    	State state = start.copy();

	    	state.setPiece(4, 4, new Piece(WHITE, BISHOP));
	    	state.setPiece(5, 5, new Piece(BLACK, PAWN));
	    	state.setPiece(1, 0, null);
	    	state.setPiece(6, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(6, 6), null);

	    	stateChanger.makeMove(state, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveRookOverBlackPiece() {
	    	State state = start.copy();

	    	state.setPiece(4, 4, new Piece(WHITE, ROOK));
	    	state.setPiece(4, 5, new Piece(BLACK, PAWN));
	    	state.setPiece(1, 0, null);
	    	state.setPiece(6, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(4, 6), null);

	    	stateChanger.makeMove(state, move);
	    }

	    @Test(expected = IllegalMove.class)
	    public void testIllegalMoveQueenOverBlackPiece() {
	    	State state = start.copy();

	    	state.setPiece(4, 4, new Piece(WHITE, QUEEN));
	    	state.setPiece(4, 5, new Piece(BLACK, PAWN));
	    	state.setPiece(1, 0, null);
	    	state.setPiece(6, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(4, 6), null);

	    	stateChanger.makeMove(state, move);
	    }	

	    @Test
	    public void testMoveKnightLShape() {
	    	Move move = new Move(new Position(0, 6), new Position(2, 5), null);

	    	State expected = start.copy();
	    	expected.setPiece(0,  6, null);
	    	expected.setPiece(2,  5, new Piece(WHITE, KNIGHT));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	expected.setTurn(Color.BLACK);

	    	stateChanger.makeMove(start, move);

	    	assertEquals(expected, start);
	    }	

	    @Test
	    public void testMoveKnightLShape2() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, KNIGHT));
	    	state.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(6, 5), null);

	    	State expected = state.copy();
	    	expected.setPiece(4,  4, null);
	    	expected.setPiece(6,  5, new Piece(WHITE, KNIGHT));
	    	expected.setTurn(Color.BLACK);

	    	stateChanger.makeMove(state, move);

	    	assertEquals(expected, state);
	    }	

	    @Test
	    public void testMoveBishopDiagnolly() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, BISHOP));
	    	state.setPiece(1, 0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 5), null);

	    	State expected = state.copy();
	    	expected.setPiece(4,  4, null);
	    	expected.setPiece(5,  5, new Piece(WHITE, BISHOP));
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
	    	expected.setTurn(Color.BLACK);

	    	stateChanger.makeMove(state, move);

	    	assertEquals(expected, state);
	    }	

	    @Test
	    public void testMoveRookHorizontally() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, ROOK));
	    	state.setPiece(1,  0, null);

	    	Move move = new Move(new Position(4, 4), new Position(4, 5), null);

	    	State expected = state.copy();
	    	expected.setPiece(4,  4, null);
	    	expected.setPiece(4,  5, new Piece(WHITE, ROOK));
	    	expected.setTurn(Color.BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

	    	stateChanger.makeMove(state, move);

	    	assertEquals(expected, state);
	    }	

	    @Test
	    public void testMoveRookVertically() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, ROOK));
	    	state.setPiece(1,  0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 4), null);

	    	State expected = state.copy();
	    	expected.setPiece(4,  4, null);
	    	expected.setPiece(5,  4, new Piece(WHITE, ROOK));
	    	expected.setTurn(Color.BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

	    	stateChanger.makeMove(state, move);

	    	assertEquals(expected, state);
	    }	

	    @Test
	    public void testMoveQueenVertically() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, QUEEN));
	    	state.setPiece(1,  0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 4), null);

	    	State expected = state.copy();
	    	expected.setPiece(4,  4, null);
	    	expected.setPiece(5,  4, new Piece(WHITE, QUEEN));
	    	expected.setTurn(Color.BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

	    	stateChanger.makeMove(state, move);

	    	assertEquals(expected, state);
	    }	

	    @Test
	    public void testMoveQueenHorizontally() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, QUEEN));
	    	state.setPiece(1,  0, null);

	    	Move move = new Move(new Position(4, 4), new Position(4, 5), null);

	    	State expected = state.copy();
	    	expected.setPiece(4,  4, null);
	    	expected.setPiece(4,  5, new Piece(WHITE, QUEEN));
	    	expected.setTurn(Color.BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

	    	stateChanger.makeMove(state, move);

	    	assertEquals(expected, state);
	    }	

	    @Test
	    public void testMoveQueenDiagnolly() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, QUEEN));
	    	state.setPiece(1,  0, null);

	    	Move move = new Move(new Position(4, 4), new Position(5, 5), null);

	    	State expected = state.copy();
	    	expected.setPiece(4,  4, null);
	    	expected.setPiece(5,  5, new Piece(WHITE, QUEEN));
	    	expected.setTurn(Color.BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

	    	stateChanger.makeMove(state, move);

	    	assertEquals(expected, state);
	    }

	    @Test(expected=IllegalMove.class)
	    public void testIllegalMoveQueenLShape() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, QUEEN));
	    	state.setPiece(1,  0, null);

	    	Move move = new Move(new Position(4, 4), new Position(2, 5), null);


	    	stateChanger.makeMove(state, move);
	    }

	    @Test
	    public void testMoveKnightOverBlackPiece() {
	    	State state = start.copy();
	    	state.setPiece(4, 4, new Piece(WHITE, KNIGHT));
	    	state.setPiece(5, 5, new Piece(BLACK, PAWN));
	    	state.setPiece(1,  0, null);
	    	state.setPiece(6,  0, null);

	    	Move move = new Move(new Position(4, 4), new Position(2, 5), null);

	    	State expected = state.copy();
	    	expected.setPiece(4, 4, null);
	    	expected.setPiece(2, 5, new Piece(WHITE, KNIGHT));
	    	expected.setTurn(Color.BLACK);
	    	expected.setNumberOfMovesWithoutCaptureNorPawnMoved(1);

	    	stateChanger.makeMove(state, move);

	    	assertEquals(expected, state);
	    }	
	    /*
	     * End Tests by Alexander Oskotsky <alex.oskotsky@gmail.com> 
	     */		
}
