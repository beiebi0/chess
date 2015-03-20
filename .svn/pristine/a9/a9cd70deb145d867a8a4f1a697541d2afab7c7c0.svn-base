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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public abstract class AbstractStateExplorerAllTest extends
		AbstractStateExplorerTest {

	/*
	 * Begin Tests by Yoav Zibin <yoav.zibin@gmail.com>
	 */
	@Test
	public void testGetPossibleStartPositions_InitState() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		for (int c = 0; c < 8; c++)
			expectedPositions.add(new Position(1, c));
		// knight positions
		expectedPositions.add(new Position(0, 1));
		expectedPositions.add(new Position(0, 6));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMoves_InitState() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// pawn moves
		for (int c = 0; c < 8; c++) {
			expectedMoves.add(new Move(new Position(1, c), new Position(2, c),
					null));
			expectedMoves.add(new Move(new Position(1, c), new Position(3, c),
					null));
		}
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_InitStateForLeftKnight() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 1)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_Promotion() {
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(6, 0), new Piece(Color.WHITE,
				PieceKind.PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();
		// promotion moves
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 0)));
	}

	/*
	 * End Tests by Yoav Zibin <yoav.zibin@gmail.com>
	 */

	/*
	 * Begin Tests by Chen Ji <ji.chen1990@gmail.com>
	 */

	/*
	 * @Test public void testGetPossibleMovesFromPosition_NoPiece_cj() {
	 * assertEquals(null, stateExplorer.getPossibleMovesFromPosition(start, new
	 * Position(3, 3))); }
	 */

	@Test
	// 1
	public void testGetPossibleMovesFromPosition_WhitePawnMove_cj() {
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(1, 3), new Position(2, 3), null));
		expectedMoves
				.add(new Move(new Position(1, 3), new Position(3, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 3)));
	}

	@Test
	// 2
	public void testGetPossibleMovesFromPosition_BlackPawnMove_cj() {
		start.setTurn(Color.BLACK);
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(6, 3), new Position(4, 3), null));
		expectedMoves
				.add(new Move(new Position(6, 3), new Position(5, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 3)));
	}

	@Test
	// 3
	public void testGetPossibleMovesFromPosition_WhitePawnCapture_cj() {
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(4, 3), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4, 4));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 3), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 3)));
	}

	@Test
	// 4
	public void testGetPossibleMovesFromPosition_WhitePawnPromotion_cj() {
		start.setPiece(new Position(1, 1), null);
		start.setPiece(new Position(7, 1), null);
		start.setPiece(new Position(6, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 0),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 0),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 0),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 0),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 1),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 1),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 1),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 1),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 2),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 2),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 2),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 1), new Position(7, 2),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 1)));
	}

	@Test
	// 5
	public void testGetPossibleMovesFromPosition_WhitePawnCannotCaptureIfWillExposeKing_cj() {
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(4, 3), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4, 4));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 3), null);
		start.setPiece(new Position(0, 4), null);
		start.setPiece(new Position(0, 3), new Piece(Color.WHITE,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 3)));
	}

	@Test
	// 6
	public void testGetPossibleMovesFromPosition_WhitePawnMoveUnderCheck_cj() {
		start.setPiece(new Position(6, 1), null);
		start.setPiece(new Position(7, 2), null);
		start.setPiece(new Position(5, 0), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(0, 4), null);
		start.setPiece(new Position(1, 4), new Piece(Color.WHITE,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(1, 3), new Position(2, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 3)));
	}

	@Test
	// 7
	public void testGetPossibleMovesFromPosition_WhitePawnCannotMoveUnderCheck_cj() {
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(4, 0), new Piece(Color.BLACK,
				PieceKind.QUEEN));

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(1, 3)).isEmpty());
	}

	@Test
	// 8
	public void testGetPossibleMovesFromPosition_WhiteKingMove_cj() {
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(0, 4), null);
		start.setPiece(new Position(1, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(1, 4), new Position(2, 3), null));
		expectedMoves
				.add(new Move(new Position(1, 4), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(1, 4), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(1, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(1, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(1, 4), new Position(0, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 4)));
	}

	@Test
	// 9
	public void testGetPossibleMovesFromPosition_WhiteKingCapture_cj() {
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(2, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(1, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(0, 4), null);
		start.setPiece(new Position(1, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(1, 4), new Position(2, 3), null));
		expectedMoves
				.add(new Move(new Position(1, 4), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(1, 4), new Position(2, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 4)));
	}

	@Test
	// 10
	public void testGetPossibleMovesFromPosition_WhiteKingCannotMove_cj() {
		start.setPiece(new Position(6, 3), null);
		start.setPiece(new Position(1, 3), null);

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(0, 4)).isEmpty());
	}

	@Test
	// 11
	public void testGetPossibleMovesFromPosition_WhiteKingCannotMoveUnderCheckByKnightAndPawn_cj() {
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(2, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(1, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(7, 1), null);
		start.setPiece(new Position(2, 6), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(1, 4), null);
		start.setPiece(new Position(1, 5), null);
		start.setPiece(new Position(0, 5), null);

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(0, 4)).isEmpty());
	}

	@Test
	// 12
	public void testGetPossibleMovesFromPosition_WhiteKingMoveUnderCheckByRook_cj() {
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(7, 0), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(1, 4), null);
		start.setPiece(new Position(1, 5), null);
		start.setPiece(new Position(1, 3), null);

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 4)));
	}

	@Test
	// 13
	public void testGetPossibleMovesFromPosition_WhiteKingCalstleBothSides_cj() {
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(0, 6), null);

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 4)));
	}

	@Test
	// 14
	public void testGetPossibleMovesFromPosition_WhiteKingCalstleQueenSides_cj() {
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(7, 0), null);
		start.setPiece(new Position(4, 1), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(1, 1), null);
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(0, 6), null);
		start.setCanCastleKingSide(Color.WHITE, false);

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 4)));
	}

	@Test
	// 15
	public void testGetPossibleMovesFromPosition_WhiteKingCannotCalstleQueenSidesUnderCheck_cj() {
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(7, 0), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(1, 4), null);
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(0, 6), null);
		start.setCanCastleKingSide(Color.WHITE, false);

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 4)));
	}

	@Test
	// 16
	public void testGetPossibleMovesFromPosition_WhiteKingCannotCalstleQueenSidesWhenPathUnderAttack_cj() {
		start.setPiece(new Position(6, 3), null);
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(0, 3), null);

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(0, 4)).isEmpty());
	}

	@Test
	// 17
	public void testGetPossibleMovesFromPosition_WhiteRookMove_cj() {
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(0, 2), null);

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(3, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(4, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(6, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 0)));
	}

	@Test
	// 18
	public void testGetPossibleMovesFromPosition_WhiteRookCannotMoveIfWillExposeKing_cj() {
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(0, 0), null);
		start.setPiece(new Position(2, 2), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(4, 0), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		start.setPiece(new Position(1, 3), null);

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(2, 2)).isEmpty());
	}

	@Test
	// 19
	public void testGetPossibleMovesFromPosition_WhiteRookMoveUnderCheck_cj() {
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(0, 0), null);
		start.setPiece(new Position(2, 0), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(4, 0), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		start.setPiece(new Position(1, 3), null);

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(2, 0), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(2, 0), new Position(4, 0), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(2, 0)));
	}

	@Test
	// 20
	public void testGetPossibleMovesFromPosition_WhiteRookCannotMoveUnderCheck_cj() {
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(0, 0), null);
		start.setPiece(new Position(2, 0), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(1, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(2, 0)).isEmpty());
	}

	@Test
	// 21
	public void testGetPossibleMovesFromPosition_WhiteQueenMove_cj() {
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(4, 3), new Piece(Color.WHITE,
				PieceKind.QUEEN));

		Set<Move> expectedMoves = Sets.newHashSet();

		for (int i = 0; i < State.COLS; i++) {
			if (i != 3)
				expectedMoves.add(new Move(new Position(4, 3), new Position(4,
						i), null));
		}
		for (int i = 0; i < State.ROWS - 1; i++) {
			if (i != 4)
				expectedMoves.add(new Move(new Position(4, 3), new Position(i,
						3), null));
		}
		for (int i = 2; i < State.ROWS - 1; i++) {
			if (i != 4) {
				expectedMoves.add(new Move(new Position(4, 3), new Position(i,
						3), null));
				expectedMoves.add(new Move(new Position(4, 3), new Position(i,
						7 - i), null));
				expectedMoves.add(new Move(new Position(4, 3), new Position(i,
						i - 1), null));
			}
		}
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 3)));
	}

	@Test
	// 22
	public void testGetPossibleMovesFromPosition_WhiteQueenMoveUnderCheck_cj() {
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(2, 0), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(4, 0), new Piece(Color.BLACK,
				PieceKind.QUEEN));

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(2, 0), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(2, 0), new Position(4, 0), null));
		expectedMoves
				.add(new Move(new Position(2, 0), new Position(3, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(2, 0)));
	}

	@Test
	// 23
	public void testGetPossibleMovesFromPosition_WhiteQueenCannotMoveUnderCheck_cj() {
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(2, 0), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(1, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(2, 0)).isEmpty());
	}

	@Test
	// 24
	public void testGetPossibleMovesFromPosition_WhiteBishopMove_cj() {
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(3, 3), new Piece(Color.WHITE,
				PieceKind.BISHOP));

		Set<Move> expectedMoves = Sets.newHashSet();

		for (int i = 2; i < State.ROWS - 1; i++) {
			if (i != 3) {
				expectedMoves.add(new Move(new Position(3, 3), new Position(i,
						6 - i), null));
				expectedMoves.add(new Move(new Position(3, 3), new Position(i,
						i), null));
			}
		}
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(3, 3)));
	}

	@Test
	// 25
	public void testGetPossibleMovesFromPosition_WhiteBishopCannotMoveIfWillExposeKing_cj() {
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(2, 4), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(7, 0), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(1, 4), null);

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(2, 4)).isEmpty());
	}

	@Test
	// 26
	public void testGetPossibleMovesFromPosition_WhiteBishopMoveUnderCheck_cj() {
		start.setPiece(new Position(1, 3), null);
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(3, 5), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(7, 0), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(1, 4), null);

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(3, 5), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(3, 5), new Position(4, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(3, 5)));
	}

	@Test
	// 27
	public void testGetPossibleMovesFromPosition_WhiteBishopCannotMoveUnderCheck_cj() {
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(3, 3), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(1, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(3, 3)).isEmpty());
	}

	@Test
	// 28
	public void testGetPossibleMovesFromPosition_WhiteKnightMove_cj() {
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(5, 6), new Piece(Color.WHITE,
				PieceKind.KNIGHT));

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(5, 6), new Position(6, 4), null));
		expectedMoves
				.add(new Move(new Position(5, 6), new Position(4, 4), null));
		expectedMoves
				.add(new Move(new Position(5, 6), new Position(3, 5), null));
		expectedMoves
				.add(new Move(new Position(5, 6), new Position(7, 5), null));
		expectedMoves
				.add(new Move(new Position(5, 6), new Position(3, 7), null));
		expectedMoves
				.add(new Move(new Position(5, 6), new Position(7, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(5, 6)));
	}

	@Test
	// 29
	public void testGetPossibleMovesFromPosition_WhiteKnightCannotMoveIfWillExposeKing_cj() {
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(1, 3), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(4, 0), new Piece(Color.BLACK,
				PieceKind.QUEEN));

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(1, 3)).isEmpty());
	}

	@Test
	// 30
	public void testGetPossibleMovesFromPosition_WhiteKnightMoveUnderCheck_cj() {
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(3, 2), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(7, 0), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(1, 4), null);

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(3, 2), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(3, 2), new Position(4, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(3, 2)));
	}

	@Test
	// 31
	public void testGetPossibleMovesFromPosition_WhiteKnightCannotMoveUnderCheck_cj() {
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(2, 2), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(1, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));

		assertTrue(stateExplorer.getPossibleMovesFromPosition(start,
				new Position(2, 2)).isEmpty());
	}

	@Test
	// 32
	public void testGetPossibleStartPositions_1_cj() {
		start = new State(Color.WHITE, new Piece[State.ROWS][State.COLS],
				new boolean[] { false, false }, new boolean[] { false, false },
				null, 0, null);
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(1, 4, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(2, 4, new Piece(Color.BLACK, PieceKind.KING));

		assertTrue(stateExplorer.getPossibleStartPositions(start).isEmpty());
	}

	@Test
	// 33
	public void testGetPossibleStartPositions_2_cj() {
		start = new State(Color.WHITE, new Piece[State.ROWS][State.COLS],
				new boolean[] { false, false }, new boolean[] { false, false },
				null, 0, null);
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(1, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(3, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));

		Set<Position> expectedPositions = Sets.newHashSet();
		expectedPositions.add(new Position(0, 4));
		expectedPositions.add(new Position(3, 4));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	// 34
	public void testGetPossibleStartPositions_3_cj() {
		start = new State(Color.WHITE, new Piece[State.ROWS][State.COLS],
				new boolean[] { false, false }, new boolean[] { true, false },
				null, 0, null);
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(1, 0, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(2, 4, new Piece(Color.BLACK, PieceKind.KING));

		Set<Position> expectedPositions = Sets.newHashSet();

		expectedPositions.add(new Position(0, 4));
		expectedPositions.add(new Position(0, 0));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));

	}

	@Test
	// 35
	public void testGetPossibleMoves_1_cj() {
		start = new State(Color.WHITE, new Piece[State.ROWS][State.COLS],
				new boolean[] { false, false }, new boolean[] { false, false },
				null, 0, null);
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(1, 4, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(2, 4, new Piece(Color.BLACK, PieceKind.KING));

		assertTrue(stateExplorer.getPossibleMoves(start).isEmpty());
	}

	@Test
	// 36
	public void testGetPossibleMoves_2_cj() {
		start = new State(Color.WHITE, new Piece[State.ROWS][State.COLS],
				new boolean[] { false, false }, new boolean[] { false, false },
				null, 0, null);
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(1, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(3, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 5), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(4, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	// 37
	public void testGetPossibleMoves_3_cj() {
		start = new State(Color.WHITE, new Piece[State.ROWS][State.COLS],
				new boolean[] { false, false }, new boolean[] { true, false },
				null, 0, null);
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(1, 0, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(2, 4, new Piece(Color.BLACK, PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	/*
	 * End Tests by Chen Ji <ji.chen1990@gmail.com>
	 */

	/*
	 * Begin Tests by Yueh-Lin Chung <felixjon2000@gmail.com>
	 */
	void init_ylc() {
		for (int col = 0; col < 8; ++col) {
			for (int row = 0; row < 8; ++row) {
				start.setPiece(row, col, null);
			}
		}
	}

	@Test
	public void testGetPossibleMoves_BlackKingUnderCheck_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(4, 5), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 7), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(2, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));
		// Bishop move
		expectedMoves
				.add(new Move(new Position(4, 6), new Position(3, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleStartPositions_BlackKingUnderCheck_ylc() {
		Set<Position> expectedPositions = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(4, 5), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 7), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(2, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));
		// Bishop move
		expectedPositions.add(new Position(4, 6));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackKingUnderCheck_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(4, 5), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 7), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(2, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));
		// Bishop move
		expectedMoves
				.add(new Move(new Position(4, 6), new Position(3, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 6)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackBishopUnderBlackKingCheckmate_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(4, 5), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 6)));
	}

	@Test
	public void testGetPossibleMoves_ScholarsMate_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(7, 1), null);
		start.setPiece(new Position(7, 6), null);
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(1, 4), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(3, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(3, 2), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(5, 5), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(6, 5), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_ScholarsMatePreviousStep_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(7, 1), null);
		start.setPiece(new Position(7, 6), null);
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(1, 4), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(3, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(3, 2), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(5, 5), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(4, 7), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		// Queen moves
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(4, 6), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(4, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(4, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(5, 7), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(6, 7), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(5, 6), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(6, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(3, 7), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(2, 7), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(3, 6), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(1, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 7), new Position(0, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 7)));
	}

	@Test
	public void testGetPossibleMoves_FoolsMate_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(1, 5), null);
		start.setPiece(new Position(1, 6), null);
		start.setPiece(new Position(2, 5), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 7), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_BlackKingUnderCheckByWhiteQueen_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(0, 2), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 5), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(7, 6), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 6), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(5, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		// King move
		expectedMoves
				.add(new Move(new Position(6, 6), new Position(5, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackKingMoveWhenWhiteBlackKingUnderCheckByWhiteQueen_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(0, 2), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 5), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(7, 6), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 6), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(5, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		// King move
		expectedMoves
				.add(new Move(new Position(6, 6), new Position(5, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 6)));
	}

	@Test
	public void testGetPossibleStartPositions_BlackKingUnderCheckByWhiteQueen_ylc() {
		Set<Position> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(0, 2), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 5), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(7, 6), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 6), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(5, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		// King move
		expectedMoves.add(new Position(6, 6));
		assertEquals(expectedMoves,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMoves_WhiteKingUnderCheckByBlackRook_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(3, 1), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 2), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(7, 6), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 1), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 1), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(2, 3), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		// King move
		expectedMoves
				.add(new Move(new Position(3, 1), new Position(3, 0), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteKingMoveWhenWhiteKingUnderCheckByBlackRook_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(3, 1), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 2), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(7, 6), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 1), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 1), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(2, 3), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		// King move
		expectedMoves
				.add(new Move(new Position(3, 1), new Position(3, 0), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(3, 1)));
	}

	@Test
	public void testGetPossibleStartPositions_WhiteKingUnderCheckByBlackRook_ylc() {
		Set<Position> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(3, 1), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 2), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(7, 6), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 1), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 1), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(2, 3), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		// King move
		expectedMoves.add(new Position(3, 1));
		assertEquals(expectedMoves,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMoves_WhiteKingUnderCheckByTwoBlackRook_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(2, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 7), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(1, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(1, 5), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(2, 5), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(4, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));
		// Pawn move
		expectedMoves
				.add(new Move(new Position(1, 6), new Position(2, 6), null));
		expectedMoves
				.add(new Move(new Position(1, 6), new Position(2, 5), null));
		// King move
		expectedMoves
				.add(new Move(new Position(2, 7), new Position(1, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhitePawnMoveWhenWhiteKingUnderCheckByTwoBlackRook_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(2, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 7), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(1, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(1, 5), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(2, 5), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(4, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));
		// Pawn move
		expectedMoves
				.add(new Move(new Position(1, 6), new Position(2, 6), null));
		expectedMoves
				.add(new Move(new Position(1, 6), new Position(2, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 6)));
	}

	@Test
	public void testGetPossibleStartPositions_WhiteKingUnderCheckByTwoBlackRook_ylc() {
		Set<Position> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(2, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 7), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(1, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(1, 5), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(2, 5), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(4, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));
		// Pawn move
		expectedMoves.add(new Position(1, 6));
		// King move
		expectedMoves.add(new Position(2, 7));
		assertEquals(expectedMoves,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMoves_WhiteKnightGoingToCheck_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(7, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(6, 4), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.ROOK));
		// Knight move
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(7, 2), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(7, 6), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(5, 2), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(5, 6), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(4, 5), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(4, 3), null));
		// King move
		expectedMoves
				.add(new Move(new Position(7, 7), new Position(7, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteKnightGoingToCheck_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(7, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(6, 4), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.ROOK));
		// Knight move
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(7, 2), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(7, 6), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(5, 2), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(5, 6), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(4, 5), null));
		expectedMoves
				.add(new Move(new Position(6, 4), new Position(4, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 4)));
	}

	@Test
	public void testGetPossibleMoves_BlackKingUnderCheckByWhiteKnight_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(7, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(7, 6), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.ROOK));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackKingUnderCheckByWhiteKnight_ylc() {
		Set<Move> expectedMoves = Sets.newHashSet();
		init_ylc();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(7, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(7, 6), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.ROOK));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(5, 7)));
	}

	/*
	 * End Tests by Yueh-Lin Chung <felixjon2000@gmail.com>
	 */
	/*
	 * Begin Tests by Haoxiang Zuo <haoxiangzuo@gmail.com>
	 */
	public void initialForHz() {
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++)
				start.setPiece(row, col, null);
	}

	@Test
	public void testGetPossibleStartPositions_BlackSide() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		start.setTurn(Color.BLACK);
		for (int c = 0; c < 8; c++)
			expectedPositions.add(new Position(6, c));
		// knight positions
		expectedPositions.add(new Position(7, 1));
		expectedPositions.add(new Position(7, 6));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_OnePawnMove() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		start.setPiece(new Position(1, 0), null);
		start.setPiece(3, 0, new Piece(start.getTurn(), PieceKind.PAWN));
		for (int c = 1; c < 8; c++)
			expectedPositions.add(new Position(1, c));
		// knight positions
		expectedPositions.add(new Position(3, 0));
		expectedPositions.add(new Position(0, 1));
		expectedPositions.add(new Position(0, 6));
		expectedPositions.add(new Position(0, 0));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_OnlyKingMove() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		initialForHz();
		start.setPiece(0, 4, new Piece(start.getTurn(), PieceKind.KING));
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		// knight positions
		expectedPositions.add(new Position(0, 4));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_QueenCanMove() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		start.setPiece(1, 2, null);
		for (int c = 0; c < 2; c++)
			expectedPositions.add(new Position(1, c));
		for (int c = 3; c < 8; c++)
			expectedPositions.add(new Position(1, c));
		// knight positions
		expectedPositions.add(new Position(0, 1));
		expectedPositions.add(new Position(0, 6));
		expectedPositions.add(new Position(0, 3));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_NolegalMove() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		initialForHz();
		start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(2, 1, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(1, 2, new Piece(Color.BLACK, PieceKind.QUEEN));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_EnpassantCap() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		initialForHz();
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(4, 3, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(4, 4, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4, 4));
		expectedPositions.add(new Position(0, 4));
		expectedPositions.add(new Position(4, 3));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMoves_InitStateForBlack() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// pawn moves
		start.setTurn(Color.BLACK);
		for (int c = 0; c < 8; c++) {
			expectedMoves.add(new Move(new Position(6, c), new Position(5, c),
					null));
			expectedMoves.add(new Move(new Position(6, c), new Position(4, c),
					null));
		}
		// knight moves
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 2), null));
		expectedMoves
				.add(new Move(new Position(7, 6), new Position(5, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 6), new Position(5, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_Enpassant() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// pawn moves
		initialForHz();
		start.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(4, 3, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(4, 4, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4, 4));

		// knight moves
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 3), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 0), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_InitStateForLeftKnightForBlack() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// knight moves
		start.setTurn(Color.BLACK);
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(7, 1)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteKingCanMoveRight() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// knight moves
		initialForHz();
		start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(2, 1, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(2, 2, new Piece(Color.BLACK, PieceKind.QUEEN));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 0)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_PromotionForBlack() {
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(1, 0), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setTurn(Color.BLACK);
		Set<Move> expectedMoves = Sets.newHashSet();
		// promotion moves
		expectedMoves.add(new Move(new Position(1, 0), new Position(0, 1),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(1, 0), new Position(0, 1),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(1, 0), new Position(0, 1),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(1, 0), new Position(0, 1),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 0)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_PromotionForWhite() {
		start.setPiece(new Position(1, 0), null);
		start.setPiece(7, 0, null);
		start.setPiece(new Position(6, 0), new Piece(Color.WHITE,
				PieceKind.PAWN));
		Set<Move> expectedMoves = Sets.newHashSet();
		// promotion moves
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 0)));
	}

	/*
	 * End Tests by Haoxiang Zuo <haoxiangzuo@gmail.com>
	 */

	/*
	 * start test by bo huang <fantasyblake1213@gmail.com>
	 */

	public void clearPiece(State state) {

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				state.setPiece(i, j, null);
			}
		}

		state.setCanCastleKingSide(Color.WHITE, false);
		state.setCanCastleKingSide(Color.BLACK, false);
		state.setCanCastleQueenSide(Color.WHITE, false);
		state.setCanCastleQueenSide(Color.BLACK, false);

	}

	@Test
	public void testGetPossibleMoves_InitStateBlack() {
		Set<Move> expectedMoves = Sets.newHashSet();
		for (int i = 0; i <= 7; i++) {
			expectedMoves.add(new Move(new Position(6, i), new Position(5, i),
					null));
			expectedMoves.add(new Move(new Position(6, i), new Position(4, i),
					null));
		}
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 2), null));
		expectedMoves
				.add(new Move(new Position(7, 6), new Position(5, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 6), new Position(5, 7), null));

		State state = start.copy();
		state.setTurn(Color.BLACK);

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleMoves_PawnCannotMoveWhite() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		state.setPiece(3, 3, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(4, 3, new Piece(Color.BLACK, PieceKind.PAWN));

		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 0), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleMoves_PawnCaptureEnpassWhite() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		state.setPiece(4, 3, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(4, 4, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setEnpassantPosition(new Position(4, 4));

		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 0), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 3), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleMoves_CheckMateWhite() {
		Set<Position> expectedPositions = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(0, 2, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(1, 2, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleMoves_CheckMateBlack() {
		Set<Position> expectedPositions = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 2, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(6, 2, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		state.setTurn(Color.BLACK);

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleMoves_StaleMateWhite() {
		Set<Position> expectedPositions = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(2, 1, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(1, 2, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleMoves_StaleMateBlack() {
		Set<Position> expectedPositions = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(5, 6, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		state.setTurn(Color.BLACK);

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleStartPositions_InitStateBlack() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		for (int c = 0; c < 8; c++)
			expectedPositions.add(new Position(6, c));
		// knight positions
		expectedPositions.add(new Position(7, 1));
		expectedPositions.add(new Position(7, 6));

		State state = start.copy();
		state.setTurn(Color.BLACK);

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(state));
	}

	@Test
	public void testGetPossibleStartPositions_CheckMateWhite() {
		Set<Position> expectedPositions = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(0, 2, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(1, 2, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(state));
	}

	@Test
	public void testGetPossibleStartPositions_CheckMateBlack() {
		Set<Position> expectedPositions = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 2, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(6, 2, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		state.setTurn(Color.BLACK);

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(state));
	}

	@Test
	public void testGetPossibleStartPositions_StaleMateWhite() {
		Set<Position> expectedPositions = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(2, 1, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(1, 2, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(state));
	}

	@Test
	public void testGetPossibleStartPositions_StaleMateBlack() {
		Set<Position> expectedPositions = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(5, 6, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		state.setTurn(Color.BLACK);

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(state));
	}

	@Test
	public void testGetPossibleMovesFromPosition_Rook() {
		Set<Move> expectedMoves = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(6, 2, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(4, 0, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		expectedMoves
				.add(new Move(new Position(6, 0), new Position(7, 0), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(4, 0), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(6, 1), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(6, 0)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_Knight() {
		Set<Move> expectedMoves = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(3, 3, new Piece(Color.WHITE, PieceKind.KNIGHT));
		state.setPiece(1, 2, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(2, 1, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(1, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(2, 5, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(4, 0, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		expectedMoves
				.add(new Move(new Position(3, 3), new Position(5, 2), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(4, 1), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(4, 5), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(5, 4), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(3, 3)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_Bishop() {
		Set<Move> expectedMoves = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(3, 3, new Piece(Color.WHITE, PieceKind.BISHOP));
		state.setPiece(5, 5, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(2, 2, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(5, 1, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(2, 4, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		expectedMoves
				.add(new Move(new Position(3, 3), new Position(4, 4), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(4, 2), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(5, 1), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(2, 4), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(3, 3)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_Queen() {
		Set<Move> expectedMoves = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(3, 3, new Piece(Color.WHITE, PieceKind.QUEEN));
		state.setPiece(5, 5, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(5, 3, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(3, 2, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(3, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(2, 3, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(2, 2, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(5, 1, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(2, 4, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		expectedMoves
				.add(new Move(new Position(3, 3), new Position(4, 4), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(4, 2), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(5, 1), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(3, 3), new Position(4, 3), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(3, 3)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_KingCanCastling() {
		Set<Move> expectedMoves = Sets.newHashSet();

		State state = start.copy();

		state.setPiece(0, 1, null);
		state.setPiece(0, 2, null);
		state.setPiece(0, 3, null);
		state.setPiece(0, 5, null);
		state.setPiece(0, 6, null);

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(0, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_KingCannotCastling() {
		Set<Move> expectedMoves = Sets.newHashSet();

		State state = start.copy();
		state.setCanCastleKingSide(Color.WHITE, false);
		state.setCanCastleQueenSide(Color.WHITE, false);

		state.setPiece(0, 1, null);
		state.setPiece(0, 2, null);
		state.setPiece(0, 3, null);
		state.setPiece(0, 5, null);
		state.setPiece(0, 6, null);

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(0, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_PawnCanEnp() {
		Set<Move> expectedMoves = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(4, 3, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(5, 5, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		state.setEnpassantPosition(new Position(4, 3));

		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 3), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 5), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(4, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_PawnCannotEnp() {
		Set<Move> expectedMoves = Sets.newHashSet();

		State state = start.copy();
		clearPiece(state);

		state.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(4, 3, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(5, 5, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 5), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(4, 4)));
	}

	/*
	 * end test by bo huang <fantasyblake1213@gmail.com>
	 */

	/*
	 * Begin Tests by Shih-Wei Huang <loptyc@gmail.com>
	 */
	private State getStateEnpassantSituation() {
		State state = new State(Color.BLACK, new Piece[8][8], new boolean[] {
				false, false }, new boolean[] { false, false }, null, 0, null);
		state.setPiece(3, 3, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(3, 2, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		state.setEnpassantPosition(new Position(3, 3));
		return state;
	}

	private State getStateUnderCheckSituation() {
		State state = new State(Color.BLACK, new Piece[8][8], new boolean[] {
				false, false }, new boolean[] { false, false }, null, 0, null);
		state.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KNIGHT));
		state.setPiece(6, 7, new Piece(Color.BLACK, PieceKind.BISHOP));
		state.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(6, 0, new Piece(Color.BLACK, PieceKind.KING));
		return state;
	}

	@Test
	public void testGetPossibleMoves_BlackCanEnpassant_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = getStateEnpassantSituation();
		expectedMoves
				.add(new Move(new Position(3, 2), new Position(2, 3), null));
		expectedMoves
				.add(new Move(new Position(3, 2), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(7, 0), new Position(7, 1), null));
		expectedMoves
				.add(new Move(new Position(7, 0), new Position(6, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 0), new Position(6, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleStartPositions_BlackCanEnpassant_swh() {
		Set<Position> expectedPositions = Sets.newHashSet();
		State state = getStateEnpassantSituation();
		expectedPositions.add(new Position(3, 2));
		expectedPositions.add(new Position(7, 0));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(state));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackCanEnpassant_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = getStateEnpassantSituation();
		expectedMoves
				.add(new Move(new Position(3, 2), new Position(2, 3), null));
		expectedMoves
				.add(new Move(new Position(3, 2), new Position(2, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(3, 2)));
	}

	@Test
	public void testGetPossibleMoves_BlackKingUnderCheck_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = getStateUnderCheckSituation();
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 4), null));
		expectedMoves
				.add(new Move(new Position(7, 7), new Position(6, 5), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(7, 0), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(7, 1), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(5, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleStartPositions_BlackKingUnderCheck_swh() {
		Set<Position> expectedPositions = Sets.newHashSet();
		State state = getStateUnderCheckSituation();
		expectedPositions.add(new Position(7, 4));
		expectedPositions.add(new Position(7, 7));
		expectedPositions.add(new Position(6, 0));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(state));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackKingUnderCheck_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = getStateUnderCheckSituation();
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(7, 0), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(7, 1), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(5, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(6, 0)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackPawnPromotion_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = new State(Color.BLACK, new Piece[8][8], new boolean[] {
				false, false }, new boolean[] { false, false }, null, 0, null);
		state.setPiece(0, 6, new Piece(Color.WHITE, PieceKind.KNIGHT));
		state.setPiece(1, 5, new Piece(Color.BLACK, PieceKind.PAWN));
		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		expectedMoves.add(new Move(new Position(1, 5), new Position(0, 5),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(1, 5), new Position(0, 5),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(1, 5), new Position(0, 5),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(1, 5), new Position(0, 5),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(1, 5), new Position(0, 6),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(1, 5), new Position(0, 6),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(1, 5), new Position(0, 6),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(1, 5), new Position(0, 6),
				PieceKind.ROOK));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(1, 5)));
	}

	@Test
	public void testGetPossibleMoves_WhiteNormalMoves_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = new State(Color.WHITE, new Piece[8][8], new boolean[] {
				false, false }, new boolean[] { false, false }, null, 0, null);
		state.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.KNIGHT));
		state.setPiece(3, 6, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(1, 1, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(3, 2), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 6), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 2), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(6, 3), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(2, 3), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(6, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(3, 6), new Position(4, 6), null));
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if (!(i == 0 && j == 0))
					expectedMoves.add(new Move(new Position(1, 1),
							new Position(1 + i, 1 + j), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleStartPositions_WhiteNormalMoves_swh() {
		Set<Position> expectedPositions = Sets.newHashSet();
		State state = new State(Color.WHITE, new Piece[8][8], new boolean[] {
				false, false }, new boolean[] { false, false }, null, 0, null);
		state.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.KNIGHT));
		state.setPiece(3, 6, new Piece(Color.WHITE, PieceKind.PAWN));
		state.setPiece(1, 1, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		state.setPiece(2, 4, new Piece(Color.WHITE, PieceKind.QUEEN));
		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(0, 7, new Piece(Color.WHITE, PieceKind.BISHOP));

		expectedPositions.add(new Position(4, 4));
		expectedPositions.add(new Position(3, 6));
		expectedPositions.add(new Position(1, 1));
		expectedPositions.add(new Position(2, 4));
		expectedPositions.add(new Position(0, 0));
		expectedPositions.add(new Position(0, 7));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(state));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteRook_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = new State(Color.WHITE, new Piece[8][8], new boolean[] {
				false, false }, new boolean[] { false, false }, null, 0, null);
		state.setPiece(3, 5, new Piece(Color.WHITE, PieceKind.KNIGHT));
		state.setPiece(3, 0, new Piece(Color.WHITE, PieceKind.QUEEN));
		state.setPiece(3, 6, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(6, 0, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(5, 6, new Piece(Color.BLACK, PieceKind.KNIGHT));
		state.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		expectedMoves
				.add(new Move(new Position(3, 6), new Position(3, 7), null));
		expectedMoves
				.add(new Move(new Position(3, 6), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(3, 6), new Position(1, 6), null));
		expectedMoves
				.add(new Move(new Position(3, 6), new Position(2, 6), null));
		expectedMoves
				.add(new Move(new Position(3, 6), new Position(4, 6), null));
		expectedMoves
				.add(new Move(new Position(3, 6), new Position(5, 6), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(3, 6)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteKingCannotCastlePathUnderAttack_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = new State(Color.WHITE, new Piece[8][8], new boolean[] {
				true, false }, new boolean[] { false, false }, null, 0, null);
		state.setPiece(3, 0, new Piece(Color.WHITE, PieceKind.QUEEN));
		state.setPiece(0, 7, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(6, 5, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(5, 6, new Piece(Color.BLACK, PieceKind.KNIGHT));
		state.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(0, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteKingCannotCastleUnderAttack_swh() {
		Set<Move> expectedMoves = Sets.newHashSet();
		State state = new State(Color.WHITE, new Piece[8][8], new boolean[] {
				true, false }, new boolean[] { false, false }, null, 0, null);
		state.setPiece(3, 0, new Piece(Color.WHITE, PieceKind.QUEEN));
		state.setPiece(0, 7, new Piece(Color.WHITE, PieceKind.ROOK));
		state.setPiece(6, 4, new Piece(Color.BLACK, PieceKind.ROOK));
		state.setPiece(5, 6, new Piece(Color.BLACK, PieceKind.KNIGHT));
		state.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		state.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.KING));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(0, 4)));
	}

	/*
	 * End Tests by Shih-Wei Huang <loptyc@gmail.com>
	 */
	/*
	 * Begin Tests by Kuang-Che Lee <qfoxer@gmail.com>
	 */
	void clearAllPiecesbyKCL() {
		for (int i = 0; i < State.ROWS; i++) {
			for (int j = 0; j < State.COLS; j++) {
				start.setPiece(i, j, null);
			}
		}
		start.setCanCastleKingSide(Color.WHITE, false);
		start.setCanCastleKingSide(Color.BLACK, false);
		start.setCanCastleQueenSide(Color.WHITE, false);
		start.setCanCastleQueenSide(Color.BLACK, false);
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteKingCastling() {
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(0, 2), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(0, 6), null);

		Set<Move> expectedMoves = Sets.newHashSet();
		// white king moves
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackKingCastling() {
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(2, 0), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(7, 1), null);
		start.setPiece(new Position(7, 2), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(7, 5), null);
		start.setPiece(new Position(7, 6), null);

		Set<Move> expectedMoves = Sets.newHashSet();
		// black king moves
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 2), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(7, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteRightBishop() {
		start.setPiece(new Position(1, 6), null);
		start.setPiece(new Position(2, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();
		// white right bishop moves
		expectedMoves
				.add(new Move(new Position(0, 5), new Position(1, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 5), new Position(2, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 5)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhitePawnCanEnpassant() {
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(4, 0), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 1), null);
		start.setPiece(new Position(4, 1), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4, 1));

		Set<Move> expectedMoves = Sets.newHashSet();
		// white pawn moves
		expectedMoves
				.add(new Move(new Position(4, 0), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(4, 0), new Position(5, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 0)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackPawnCanEnpassant() {
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(3, 0), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(1, 1), null);
		start.setPiece(new Position(3, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setEnpassantPosition(new Position(3, 1));

		Set<Move> expectedMoves = Sets.newHashSet();
		// black pawn moves
		expectedMoves
				.add(new Move(new Position(3, 0), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(3, 0), new Position(2, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(3, 0)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackKingHasToCaptureWhitePawn() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(7, 1), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(6, 0), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 2), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		// black king captures
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(6, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(6, 1), null));
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(6, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(7, 1)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackKingCheckByWhiteRook() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(1, 2), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		// black king moves
		expectedMoves
				.add(new Move(new Position(5, 2), new Position(4, 1), null));
		expectedMoves
				.add(new Move(new Position(5, 2), new Position(5, 1), null));
		expectedMoves
				.add(new Move(new Position(5, 2), new Position(6, 1), null));
		expectedMoves
				.add(new Move(new Position(5, 2), new Position(4, 3), null));
		expectedMoves
				.add(new Move(new Position(5, 2), new Position(5, 3), null));
		expectedMoves
				.add(new Move(new Position(5, 2), new Position(6, 3), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(5, 2)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteRightKnightMoves() {
		start.setPiece(new Position(6, 0), null);
		start.setPiece(new Position(6, 1), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(0, 6), null);
		start.setPiece(new Position(2, 7), new Piece(Color.WHITE,
				PieceKind.KNIGHT));

		Set<Move> expectedMoves = Sets.newHashSet();
		// white right knight moves
		expectedMoves
				.add(new Move(new Position(2, 7), new Position(3, 5), null));
		expectedMoves
				.add(new Move(new Position(2, 7), new Position(4, 6), null));
		expectedMoves
				.add(new Move(new Position(2, 7), new Position(0, 6), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(2, 7)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhitePawnCanPromote() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(7, 6), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 7), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		// white pawn promote
		PieceKind piecekind[] = { PieceKind.QUEEN, PieceKind.BISHOP,
				PieceKind.KNIGHT, PieceKind.ROOK };
		for (int i = 0; i < piecekind.length; i++) {
			expectedMoves.add(new Move(new Position(6, 7), new Position(7, 7),
					piecekind[i]));
			expectedMoves.add(new Move(new Position(6, 7), new Position(7, 6),
					piecekind[i]));
		}

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 7)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteLeftBishopMovesFromInitState() {
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(1, 3), null);

		Set<Move> expectedMoves = Sets.newHashSet();
		// white bishop moves
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(3, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(4, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(5, 7), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 2)));
	}

	@Test
	public void testGetPossibleMoves_WhiteKingInCheckmate() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(2, 4), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(2, 5), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(2, 7), new Piece(Color.BLACK,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		// white king loses
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_StalemateWhenBlackTurn() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(3, 2), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 2), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 0), new Piece(Color.BLACK,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		// stalemate when black king's turn
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_BlackKingHasDisadvantage() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(5, 3), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(6, 2), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(7, 2), new Piece(Color.BLACK,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		// black king moves
		expectedMoves
				.add(new Move(new Position(7, 2), new Position(6, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_CanOnlyCaptureBlackRookWithWhiteQueen() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(5, 0), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 7), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(1, 5), new Piece(Color.BLACK,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		// white queen has to capture
		expectedMoves
				.add(new Move(new Position(5, 0), new Position(5, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_CanOnlyCaptureOrBlockBlackQueenWithWhiteKnight() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(4, 3), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(6, 4), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		start.setPiece(new Position(1, 2), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(2, 7), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(1, 7), new Piece(Color.BLACK,
				PieceKind.ROOK));

		Set<Move> expectedMoves = Sets.newHashSet();
		// white knight can only block or capture black queen
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(6, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(2, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_BlackKingCanOnlyMoveDownOrRight() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(5, 2), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
				PieceKind.KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		// black king can only move down or right
		expectedMoves
				.add(new Move(new Position(7, 0), new Position(6, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 0), new Position(7, 1), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleStartPositions_InitStateWithNoPawns() {
		for (int i = 0; i < State.COLS; i++) {
			start.setPiece(new Position(6, i), null);
			start.setPiece(new Position(1, i), null);
		}
		Set<Position> expectedPositions = Sets.newHashSet();
		// all pieces can be moved but pawns
		for (int i = 0; i < State.COLS; i++) {
			expectedPositions.add(new Position(0, i));
		}
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_OnlyKingsLeft() {
		clearAllPiecesbyKCL();
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));

		Set<Position> expectedPositions = Sets.newHashSet();
		// can only move king
		expectedPositions.add(new Position(0, 4));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_OnlyKingAndQueenLeft() {
		clearAllPiecesbyKCL();
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(0, 3), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));

		Set<Position> expectedPositions = Sets.newHashSet();
		// can only move king
		expectedPositions.add(new Position(0, 3));
		expectedPositions.add(new Position(0, 4));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_PawnsShallNotPass() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.WHITE);
		start.setPiece(new Position(3, 0), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 2), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(4, 0), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 2), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));

		Set<Position> expectedPositions = Sets.newHashSet();
		// can only move king
		expectedPositions.add(new Position(0, 4));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_OneBlackKnightHasNoWayToJump() {
		clearAllPiecesbyKCL();
		start.setTurn(Color.BLACK);
		// this knight cannot move
		start.setPiece(new Position(7, 1), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		// other pieces can
		start.setPiece(new Position(5, 0), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(6, 3), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));

		Set<Position> expectedPositions = Sets.newHashSet();
		expectedPositions.add(new Position(6, 3));
		expectedPositions.add(new Position(5, 0));
		expectedPositions.add(new Position(5, 2));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	/*
	 * End Tests by Kuang-Che Lee <qfoxer@gmail.com>
	 */
	/*
	 * Begin Tests by Ashish Manral <ashish.manral09@gmail.com>
	 */
	@Test
	public void testGetPossibleStartPositionsFromInitialState() {
		Set<Position> expectedPositions = Sets.newHashSet();
		for (int c = 0; c < 8; c++)
			expectedPositions.add(new Position(1, c));
		expectedPositions.add(new Position(0, 1));
		expectedPositions.add(new Position(0, 6));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMovesFromInitialState() {
		Set<Move> expectedMoves = Sets.newHashSet();
		for (int c = 0; c < 8; c++) {
			expectedMoves.add(new Move(new Position(1, c), new Position(2, c),
					null));
			expectedMoves.add(new Move(new Position(1, c), new Position(3, c),
					null));
		}
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPositionQueenSideBlackKnight() {
		Set<Move> expectedMoves = Sets.newHashSet();
		start.setPiece(1, 0, null);
		start.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setTurn(Color.BLACK);
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(7, 1)));
	}

	@Test
	public void testGetPossibleMovesFromPositionKingSideBlackKnight() {
		Set<Move> expectedMoves = Sets.newHashSet();
		start.setPiece(1, 0, null);
		start.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setTurn(Color.BLACK);
		expectedMoves
				.add(new Move(new Position(7, 6), new Position(5, 7), null));
		expectedMoves
				.add(new Move(new Position(7, 6), new Position(5, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(7, 6)));
	}

	@Test
	public void testGetPossibleMovesFromWhiteKingWhichCanCastle() {
		Set<Move> expectedMoves = Sets.newHashSet();
		start.setPiece(0, 1, null);
		start.setPiece(0, 2, null);
		start.setPiece(0, 3, null);
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPawnWhichCanDoEnpassantCapture() {
		Set<Move> expectedMoves = Sets.newHashSet();
		start.setPiece(1, 0, null);
		start.setPiece(3, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setEnpassantPosition(new Position(3, 0));
		start.setPiece(6, 1, null);
		start.setPiece(3, 1, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setTurn(Color.BLACK);
		expectedMoves
				.add(new Move(new Position(3, 1), new Position(2, 1), null));
		expectedMoves
				.add(new Move(new Position(3, 1), new Position(2, 0), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(3, 1)));
	}

	@Test
	public void testGetPossibleMovesWhenKingUnderCheck() {
		Set<Move> expectedMoves = Sets.newHashSet();
		boolean[] canCastleKingSide = new boolean[] { false, false };
		boolean[] canCastleQueenSide = new boolean[] { false, false };
		start = new State(Color.WHITE, new Piece[8][8], canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(2, 6, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(2, 7, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(3, 4, new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(3, 5, new Piece(Color.BLACK, PieceKind.BISHOP));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetStartPositionsWhenKingUnderCheck() {
		Set<Position> expectedPositions = Sets.newHashSet();
		boolean[] canCastleKingSide = new boolean[] { false, false };
		boolean[] canCastleQueenSide = new boolean[] { false, false };
		start = new State(Color.WHITE, new Piece[8][8], canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(2, 6, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(2, 7, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(3, 4, new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(3, 5, new Piece(Color.BLACK, PieceKind.BISHOP));
		start.setPiece(3, 6, new Piece(Color.BLACK, PieceKind.BISHOP));
		start.setPiece(6, 6, new Piece(Color.WHITE, PieceKind.BISHOP));
		expectedPositions.add(new Position(2, 0));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetStartPositionsWhenKingUnderCheckAdvanced() {
		Set<Position> expectedPositions = Sets.newHashSet();
		boolean[] canCastleKingSide = new boolean[] { false, false };
		boolean[] canCastleQueenSide = new boolean[] { false, false };
		start = new State(Color.WHITE, new Piece[8][8], canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(2, 6, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(2, 7, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(3, 4, new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(3, 5, new Piece(Color.BLACK, PieceKind.BISHOP));
		start.setPiece(3, 6, new Piece(Color.BLACK, PieceKind.BISHOP));
		start.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.BISHOP));
		expectedPositions.add(new Position(2, 0));
		expectedPositions.add(new Position(6, 0));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testMovesWhenKingUnderCheck() {
		Set<Move> expectedMoves = Sets.newHashSet();
		boolean[] canCastleKingSide = new boolean[] { false, false };
		boolean[] canCastleQueenSide = new boolean[] { false, false };
		start = new State(Color.WHITE, new Piece[8][8], canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(2, 6, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(2, 7, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(3, 4, new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(3, 5, new Piece(Color.BLACK, PieceKind.BISHOP));
		start.setPiece(3, 6, new Piece(Color.BLACK, PieceKind.BISHOP));
		start.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.BISHOP));
		expectedMoves
				.add(new Move(new Position(6, 0), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(2, 0), new Position(2, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testPromotionWithoutCapture() {
		Set<Move> expectedMoves = Sets.newHashSet();
		boolean[] canCastleKingSide = new boolean[] { false, false };
		boolean[] canCastleQueenSide = new boolean[] { false, false };
		start = new State(Color.WHITE, new Piece[8][8], canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		start.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 0)));
	}

	@Test
	public void testPromotionWithCapture() {
		Set<Move> expectedMoves = Sets.newHashSet();
		boolean[] canCastleKingSide = new boolean[] { false, false };
		boolean[] canCastleQueenSide = new boolean[] { false, false };
		start = new State(Color.WHITE, new Piece[8][8], canCastleKingSide,
				canCastleQueenSide, null, 0, null);
		start.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(7, 1, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 0)));
	}

	@Test
	public void testMoveAfterGameEnded() {
		Set<Move> expectedMoves = Sets.newHashSet();
		start.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));
		assertEquals(expectedMoves,
				stateExplorer.getPossibleStartPositions(start));
	}

	/*
	 * End Tests by Ashish Manral <ashish.manral09@gmail.com>
	 */
	/*
	 * Start Test by Yuan Jia <jiayuan6311@gmail.com>
	 */
	@Test
	public void testGetPossibleStartPositions_RemoveOnePawn() {
		Set<Position> expectedPositions = Sets.newHashSet();
		start.setPiece(1, 1, null);

		// pawn positions
		expectedPositions.add(new Position(1, 0));
		for (int c = 2; c < 8; c++)
			expectedPositions.add(new Position(1, c));
		// knight positions
		expectedPositions.add(new Position(0, 1));
		expectedPositions.add(new Position(0, 6));
		// bishop positions
		expectedPositions.add(new Position(0, 2));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_RemoveTwoPawn() {
		Set<Position> expectedPositions = Sets.newHashSet();
		start.setPiece(1, 1, null);
		start.setPiece(1, 0, null);

		// pawn positions
		for (int c = 2; c < 8; c++)
			expectedPositions.add(new Position(1, c));
		// knight positions
		expectedPositions.add(new Position(0, 1));
		expectedPositions.add(new Position(0, 6));
		// rook position
		expectedPositions.add(new Position(0, 0));
		// bishop position
		expectedPositions.add(new Position(0, 2));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleStartPositions_NoPawn() {
		Set<Position> expectedPositions = Sets.newHashSet();
		for (int c = 0; c < 8; c++) {
			start.setPiece(1, c, null);
		}
		// 0 column
		for (int c = 0; c < 8; c++)
			expectedPositions.add(new Position(0, c));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_InitStateForRightKnight() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 6)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_RemoveOnePawnForLeftBishop() {
		State state = start.copy();
		Set<Move> expectedMoves = Sets.newHashSet();

		state.setPiece(1, 1, null);
		state.setPiece(2, 1, new Piece(Color.WHITE, PieceKind.BISHOP));
		// bishop moves
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(1, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(2, 0), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(0, 2)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_RemoveOnePawnForQueen() {
		State state = start.copy();
		Set<Move> expectedMoves = Sets.newHashSet();

		state.setPiece(1, 3, null);
		// queen moves
		for (int c = 1; c < 7; c++) {
			expectedMoves.add(new Move(new Position(0, 3), new Position(c, 3),
					null));
		}
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				state, new Position(0, 3)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_Enpassant() {
		Set<Move> expectedMoves = Sets.newHashSet();

		// pawn moves
		expectedMoves
				.add(new Move(new Position(1, 0), new Position(3, 0), null));
		expectedMoves
				.add(new Move(new Position(1, 0), new Position(2, 0), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 0)));
	}

	@Test
	public void testGetPossibleMoves_SmallDifferentFromStartState() {
		State state = start.copy();
		Set<Move> expectedMoves = Sets.newHashSet();

		state.setPiece(0, 1, null);
		// pawn moves
		for (int c = 0; c < 8; c++) {
			expectedMoves.add(new Move(new Position(1, c), new Position(2, c),
					null));
			expectedMoves.add(new Move(new Position(1, c), new Position(3, c),
					null));
		}
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		// rook moves
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 1), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleMoves_RemoveOnePawn() {
		State state = start.copy();
		Set<Move> expectedMoves = Sets.newHashSet();

		state.setPiece(1, 7, null);
		// pawn moves
		for (int c = 0; c < 7; c++) {
			expectedMoves.add(new Move(new Position(1, c), new Position(2, c),
					null));
			expectedMoves.add(new Move(new Position(1, c), new Position(3, c),
					null));
		}
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		// rook moves
		for (int r = 1; r < 7; r++) {
			expectedMoves.add(new Move(new Position(0, 7), new Position(r, 7),
					null));
		}

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	@Test
	public void testGetPossibleMoves_RemoveTwoPawn() {
		State state = start.copy();
		Set<Move> expectedMoves = Sets.newHashSet();

		state.setPiece(1, 7, null);
		state.setPiece(1, 0, null);
		// pawn moves
		for (int c = 1; c < 7; c++) {
			expectedMoves.add(new Move(new Position(1, c), new Position(2, c),
					null));
			expectedMoves.add(new Move(new Position(1, c), new Position(3, c),
					null));
		}
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		// rook moves
		for (int r = 1; r < 7; r++) {
			expectedMoves.add(new Move(new Position(0, 0), new Position(r, 0),
					null));
			expectedMoves.add(new Move(new Position(0, 7), new Position(r, 7),
					null));
		}

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(state));
	}

	/*
	 * End Test by Yuan Jia <jiayuan6311@gmail.com>
	 */
	/*
	 * Begin Tests by Jiangfeng Chen <kanppa@gmail.com>
	 */
	public State InitJFC() {
		boolean[] CastleBool = { false, false };
		Piece[][] board = new Piece[ROWS][COLS];
		State original = new State(BLACK, board, CastleBool, CastleBool, null,
				7, null);
		original.setPiece(new Position(7, 4), new Piece(BLACK, KING));
		original.setPiece(new Position(0, 4), new Piece(WHITE, KING));
		return original;
	}

	@Test
	// 1
	public void testPawnCJF() {
		State original = InitJFC();
		original.setPiece(new Position(6, 7), new Piece(BLACK, PAWN));
		original.setPiece(new Position(6, 4), new Piece(BLACK, PAWN));
		original.setPiece(new Position(6, 0), new Piece(BLACK, PAWN));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(6, 7), new Position(4, 7), null));
		expectedMoves
				.add(new Move(new Position(6, 7), new Position(5, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				original, new Position(6, 7)));
	}

	@Test
	// 2
	public void testKightCJF() {
		State original = InitJFC();
		Set<Move> expectedMoves = Sets.newHashSet();
		original.setPiece(new Position(3, 4), new Piece(BLACK, KNIGHT));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(5, 5), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(5, 3), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(4, 6), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(4, 2), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(1, 5), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(2, 6), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(2, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				original, new Position(3, 4)));
	}

	@Test
	// 3
	public void testRookCJF() {
		State original = InitJFC();
		Set<Move> expectedMoves = Sets.newHashSet();
		original.setPiece(new Position(3, 5), new Piece(BLACK, ROOK));
		for (int i = 0; i < 8; i++) {
			if (i != 5)
				expectedMoves.add(new Move(new Position(3, 5), new Position(3,
						i), null));
			if (i != 3)
				expectedMoves.add(new Move(new Position(3, 5), new Position(i,
						5), null));
		}
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				original, new Position(3, 5)));
	}

	@Test
	// 4
	public void testPromoteCJF() {
		State original = InitJFC();
		Set<Move> expectedMoves = Sets.newHashSet();
		original.setPiece(new Position(1, 2), new Piece(BLACK, PAWN));
		original.setPiece(new Position(0, 1), new Piece(WHITE, ROOK));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 2),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 2),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 2),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 2),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 1),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 1),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 1),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 1),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				original, new Position(1, 2)));
	}

	@Test
	// 5
	public void testCheckCJF() {
		State original = InitJFC();
		Set<Move> expectedMoves = Sets.newHashSet();
		original.setTurn(WHITE);
		original.setPiece(new Position(2, 6), new Piece(BLACK, QUEEN));
		original.setPiece(new Position(1, 4), new Piece(WHITE, KNIGHT));
		expectedMoves
				.add(new Move(new Position(1, 4), new Position(2, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				original, new Position(1, 4)));
	}

	@Test
	// 6
	public void CheckMateCJF() {
		State original = InitJFC();
		original.setTurn(WHITE);
		Set<Move> expectedMoves = Sets.newHashSet();
		original.setPiece(new Position(0, 0), new Piece(BLACK, ROOK));
		original.setPiece(new Position(0, 7), new Piece(BLACK, ROOK));
		original.setPiece(new Position(2, 4), new Piece(BLACK, QUEEN));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(original));
	}

	@Test
	// 7
	public void testPawnAndQueenAndKingCJF() {
		State original = InitJFC();
		original.setTurn(WHITE);
		Set<Move> expectedMoves = Sets.newHashSet();
		original.setPiece(new Position(2, 2), new Piece(WHITE, QUEEN));
		original.setPiece(new Position(6, 7), new Piece(WHITE, PAWN));
		for (int i = 0; i < 8; i++) {
			if (i != 2) {
				expectedMoves.add(new Move(new Position(2, 2), new Position(i,
						i), null));
				if (i <= 4 && i > 0)
					expectedMoves.add(new Move(new Position(2, 2),
							new Position(i, 4 - i), null));
				expectedMoves.add(new Move(new Position(2, 2), new Position(2,
						i), null));
				expectedMoves.add(new Move(new Position(2, 2), new Position(i,
						2), null));
			}
		}
		expectedMoves.add(new Move(new Position(6, 7), new Position(7, 7),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 7), new Position(7, 7),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 7), new Position(7, 7),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 7), new Position(7, 7),
				PieceKind.QUEEN));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(original));
	}

	@Test
	// 8
	public void testBishopCJF() {
		State original = InitJFC();
		original.setPiece(new Position(6, 6), new Piece(BLACK, BISHOP));
		Set<Move> expectedMoves = Sets.newHashSet();

		for (int i = 0; i < 8; i++) {
			if (i != 6) {
				expectedMoves.add(new Move(new Position(6, 6), new Position(i,
						i), null));
				if (i >= 5)
					expectedMoves.add(new Move(new Position(6, 6),
							new Position(i, 12 - i), null));
			}
		}
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				original, new Position(6, 6)));
	}

	@Test
	// 9
	public void StartKnightTestCJF() {
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 6)));
	}

	@Test
	// 10
	public void KingMoveCJF() {
		State original = InitJFC();
		original.setTurn(WHITE);
		original.setCanCastleKingSide(WHITE, true);
		original.setPiece(new Position(0, 7), new Piece(WHITE, ROOK));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				original, new Position(0, 4)));
	}

	/*
	 * End Tests by Jiangfeng Chen <kanppa@gmail.com>
	 */

	/*
	 * Begin Tests by Mengyan Huang <aimeehwang90@gmail.com>
	 */
	@Test
	// 1
	public void testPossibleMovesFromPosition_WhiteRookMoves() {
		start.setPiece(0, 0, null);
		start.setPiece(0, 1, null);
		start.setPiece(1, 1, null);
		start.setPiece(3, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(3, 1, new Piece(Color.WHITE, PieceKind.PAWN));

		Set<Move> expect = Sets.newHashSet();
		expect.add(new Move(new Position(3, 0), new Position(2, 0), null));
		expect.add(new Move(new Position(3, 0), new Position(4, 0), null));
		expect.add(new Move(new Position(3, 0), new Position(5, 0), null));
		expect.add(new Move(new Position(3, 0), new Position(6, 0), null));
		assertEquals(expect, stateExplorer.getPossibleMovesFromPosition(start,
				new Position(3, 0)));
	}

	@Test
	// 2
	public void testPossibleMovesFromPosition_WhiteKnightMoves() {
		start.setPiece(0, 6, null);
		start.setPiece(1, 4, null);
		start.setPiece(1, 5, null);
		start.setPiece(1, 7, null);
		start.setPiece(4, 6, new Piece(Color.WHITE, PieceKind.KNIGHT));
		start.setPiece(3, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 5, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 7, new Piece(Color.WHITE, PieceKind.PAWN));

		Set<Move> expect = Sets.newHashSet();
		expect.add(new Move(new Position(4, 6), new Position(5, 4), null));
		expect.add(new Move(new Position(4, 6), new Position(6, 5), null));
		expect.add(new Move(new Position(4, 6), new Position(6, 7), null));
		assertEquals(expect, stateExplorer.getPossibleMovesFromPosition(start,
				new Position(4, 6)));
	}

	@Test
	// 3
	public void testPossibleMovesFromPosition_WhiteKingMoves() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				start.setPiece(i, j, null);
			}
		start.setPiece(4, 5, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(3, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(5, 4, new Piece(Color.BLACK, PieceKind.QUEEN));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		Set<Move> expect = Sets.newHashSet();
		expect.add(new Move(new Position(4, 5), new Position(3, 5), null));
		expect.add(new Move(new Position(4, 5), new Position(5, 4), null));
		expect.add(new Move(new Position(4, 5), new Position(4, 6), null));
		assertEquals(expect, stateExplorer.getPossibleMovesFromPosition(start,
				new Position(4, 5)));
	}

	@Test
	// 4
	public void testPossibleMovesFromPosition_WhitePawnMoves() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				start.setPiece(i, j, null);
			}
		start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(1, 1, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 2, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 0, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		Set<Move> expect = Sets.newHashSet();
		expect.add(new Move(new Position(1, 1), new Position(2, 0), null));
		expect.add(new Move(new Position(1, 1), new Position(2, 1), null));
		expect.add(new Move(new Position(1, 1), new Position(3, 1), null));
		assertEquals(expect, stateExplorer.getPossibleMovesFromPosition(start,
				new Position(1, 1)));
	}

	@Test
	// 5
	public void testPossibleMovesFromPosition_WhiteQueenMovesWhileKingInCheck() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				start.setPiece(i, j, null);
			}
		start.setPiece(3, 7, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(3, 6, new Piece(Color.WHITE, PieceKind.QUEEN));
		start.setPiece(5, 6, new Piece(Color.BLACK, PieceKind.KNIGHT));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		Set<Move> expect = Sets.newHashSet();

		expect.add(new Move(new Position(3, 6), new Position(5, 6), null));
		assertEquals(expect, stateExplorer.getPossibleMovesFromPosition(start,
				new Position(3, 6)));
	}

	@Test
	// 6
	public void testgetPossibleMoves_WhiteKingInCheck() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				start.setPiece(i, j, null);
			}
		start.setTurn(Color.WHITE);
		start.setPiece(3, 7, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(2, 6, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 7, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(4, 6, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		Set<Move> expect = Sets.newHashSet();

		expect.add(new Move(new Position(4, 4), new Position(4, 6), null));
		expect.add(new Move(new Position(3, 7), new Position(4, 7), null));
		expect.add(new Move(new Position(3, 7), new Position(3, 6), null));
		expect.add(new Move(new Position(3, 7), new Position(4, 6), null));
		assertEquals(expect, stateExplorer.getPossibleMoves(start));
	}

	@Test
	// 7
	public void testgetPossibleMoves_RandomState() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				start.setPiece(i, j, null);
			}
		start.setTurn(Color.WHITE);

		start.setPiece(0, 7, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(2, 4, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 7, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		Set<Move> expect = Sets.newHashSet();

		expect.add(new Move(new Position(2, 4), new Position(3, 4), null));
		expect.add(new Move(new Position(2, 7), new Position(3, 7), null));
		expect.add(new Move(new Position(0, 7), new Position(1, 7), null));
		expect.add(new Move(new Position(0, 7), new Position(0, 6), null));
		expect.add(new Move(new Position(0, 7), new Position(1, 6), null));
		assertEquals(expect, stateExplorer.getPossibleMoves(start));
	}

	@Test
	// 8
	public void testgetPossibleMoves_CheckMate() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				start.setPiece(i, j, null);
			}
		start.setTurn(Color.WHITE);

		start.setPiece(3, 7, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(4, 5, new Piece(Color.BLACK, PieceKind.QUEEN));
		start.setPiece(3, 5, new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(1, 5, new Piece(Color.BLACK, PieceKind.BISHOP));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		Set<Move> expect = Sets.newHashSet();

		assertEquals(expect, stateExplorer.getPossibleMoves(start));
	}

	@Test
	// 9
	public void testgetPossibleStartPositions_CheckMate() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				start.setPiece(i, j, null);
			}
		start.setTurn(Color.WHITE);

		start.setPiece(3, 7, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(4, 5, new Piece(Color.BLACK, PieceKind.QUEEN));
		start.setPiece(3, 5, new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(1, 5, new Piece(Color.BLACK, PieceKind.BISHOP));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));

		Set<Position> expect = Sets.newHashSet();

		assertEquals(expect, stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	// 10
	public void testgetPossibleStartPositions_Enpassant() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				start.setPiece(i, j, null);
			}
		start.setTurn(Color.WHITE);
		start.setEnpassantPosition(new Position(4, 6));
		start.setPiece(0, 7, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(4, 5, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(4, 6, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(5, 5, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		start.setEnpassantPosition(new Position(4, 6));

		Set<Position> expect = Sets.newHashSet();

		expect.add(new Position(4, 5));
		expect.add(new Position(0, 7));
		assertEquals(expect, stateExplorer.getPossibleStartPositions(start));
	}

	/*
	 * End Tests by Mengyan Huang <aimeehwang90@gmail.com>
	 */

	/*
	 * Begin Tests by Shitian Ren <renshitian@gmail.com>
	 */
	@Test
	public void testGetAllPossibleMoves() {
		State before = new State(BLACK, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(0, 4, new Piece(WHITE, KING));
		before.setPiece(7, 4, new Piece(BLACK, KING));
		before.setPiece(4, 3, new Piece(BLACK, KNIGHT));
		before.setPiece(3, 3, new Piece(WHITE, PAWN));
		before.setPiece(3, 4, new Piece(BLACK, PAWN));
		before.setPiece(4, 4, new Piece(WHITE, PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 4), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(6, 2), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 1), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(3, 1), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(3, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(6, 4), null));
		expectedMoves
				.add(new Move(new Position(3, 4), new Position(2, 4), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(before));

	}

	@Test
	public void testGetAllPossibleMoves_MovementLeads2Check() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(0, 3, new Piece(WHITE, KING));
		before.setPiece(0, 4, new Piece(WHITE, ROOK));
		before.setPiece(1, 5, new Piece(BLACK, ROOK));
		before.setPiece(2, 4, new Piece(BLACK, PAWN));
		before.setPiece(7, 4, new Piece(BLACK, KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 3), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 7), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(before));
	}

	@Test
	public void testGetAllPossibleMoves_UnderCheck_Move2Defend() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(0, 0, new Piece(WHITE, KING));
		before.setPiece(1, 1, new Piece(BLACK, QUEEN));
		before.setPiece(1, 5, new Piece(WHITE, QUEEN));
		before.setPiece(7, 4, new Piece(BLACK, KING));

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(1, 5), new Position(1, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 1), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(before));

	}

	@Test
	public void testGetAllPossibleMoves_CastleKingSide() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(1, 0, new Piece(BLACK, ROOK));
		before.setPiece(0, 4, new Piece(WHITE, KING));
		before.setPiece(0, 7, new Piece(WHITE, ROOK));
		before.setPiece(2, 7, new Piece(BLACK, PAWN));
		before.setPiece(7, 3, new Piece(BLACK, KING));

		before.setCanCastleKingSide(WHITE, true);
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(1, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(2, 7), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(before));
		Set<Move> expectedMoves1 = Sets.newHashSet();

		before.setPiece(5, 6, new Piece(BLACK, ROOK));
		expectedMoves1.add(new Move(new Position(0, 4), new Position(0, 3),
				null));
		expectedMoves1.add(new Move(new Position(0, 4), new Position(0, 5),
				null));
		expectedMoves1.add(new Move(new Position(0, 7), new Position(0, 6),
				null));
		expectedMoves1.add(new Move(new Position(0, 7), new Position(0, 5),
				null));
		expectedMoves1.add(new Move(new Position(0, 7), new Position(1, 7),
				null));
		expectedMoves1.add(new Move(new Position(0, 7), new Position(2, 7),
				null));
		assertEquals(expectedMoves1, stateExplorer.getPossibleMoves(before));

	}

	@Test
	public void testGetAllPossibleMoves_CastleQueenSide() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(1, 0, new Piece(BLACK, ROOK));
		before.setPiece(0, 4, new Piece(WHITE, KING));
		before.setPiece(0, 0, new Piece(WHITE, ROOK));
		before.setPiece(2, 5, new Piece(BLACK, ROOK));
		before.setPiece(7, 3, new Piece(BLACK, KING));

		before.setCanCastleQueenSide(WHITE, true);
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 0), new Position(1, 0), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(before));

		before.setPiece(3, 2, new Piece(BLACK, QUEEN));

		Set<Move> expectedMoves1 = Sets.newHashSet();

		expectedMoves1.add(new Move(new Position(0, 4), new Position(0, 3),
				null));
		expectedMoves1.add(new Move(new Position(0, 0), new Position(0, 1),
				null));
		expectedMoves1.add(new Move(new Position(0, 0), new Position(0, 2),
				null));
		expectedMoves1.add(new Move(new Position(0, 0), new Position(0, 3),
				null));
		expectedMoves1.add(new Move(new Position(0, 0), new Position(1, 0),
				null));
		assertEquals(expectedMoves1, stateExplorer.getPossibleMoves(before));

	}

	@Test
	public void testGetPossibleStartPositions_NoMovement() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(0, 4, new Piece(WHITE, KING));
		before.setPiece(7, 4, new Piece(BLACK, KING));
		before.setPiece(4, 3, new Piece(BLACK, KNIGHT));
		before.setPiece(3, 3, new Piece(WHITE, PAWN));
		before.setPiece(3, 4, new Piece(BLACK, PAWN));
		before.setPiece(4, 4, new Piece(WHITE, PAWN));
		Set<Position> expectedPositions = Sets.newHashSet();

		expectedPositions.add(new Position(0, 4));
		expectedPositions.add(new Position(4, 4));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(before));

	}

	@Test
	public void testGetPossibleStartPositions_MoveLeads2Check() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(0, 4, new Piece(WHITE, KING));
		before.setPiece(7, 4, new Piece(BLACK, KING));
		before.setPiece(2, 4, new Piece(WHITE, BISHOP));
		before.setPiece(4, 4, new Piece(BLACK, QUEEN));

		Set<Position> expectedPositions = Sets.newHashSet();

		expectedPositions.add(new Position(0, 4));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(before));
	}

	@Test
	public void testGetPossibleMovesFromPosition_MovesLeads2Check() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(0, 4, new Piece(WHITE, KING));
		before.setPiece(7, 4, new Piece(BLACK, KING));
		before.setPiece(2, 4, new Piece(WHITE, BISHOP));
		before.setPiece(4, 4, new Piece(BLACK, QUEEN));
		Set<Position> expectedPositions = Sets.newHashSet();

		assertEquals(expectedPositions,
				stateExplorer.getPossibleMovesFromPosition(before,
						new Position(2, 4)));

	}

	@Test
	public void testGetPosssibleMovesFromPosition_QueenSideCastle() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(1, 0, new Piece(BLACK, ROOK));
		before.setPiece(0, 4, new Piece(WHITE, KING));
		before.setPiece(0, 0, new Piece(WHITE, ROOK));
		before.setPiece(2, 5, new Piece(BLACK, ROOK));
		before.setPiece(7, 3, new Piece(BLACK, KING));

		before.setCanCastleQueenSide(WHITE, true);
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 2), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				before, new Position(0, 4)));

		before.setPiece(3, 2, new Piece(BLACK, QUEEN));

		Set<Move> expectedMoves1 = Sets.newHashSet();

		expectedMoves1.add(new Move(new Position(0, 4), new Position(0, 3),
				null));
	}

	@Test
	public void testGetPosssibleMovesFromPosition_KingSideCastle() {
		State before = new State(WHITE, new Piece[8][8], new boolean[] { false,
				false }, new boolean[] { false, false }, null, 0, null);

		before.setPiece(1, 0, new Piece(BLACK, ROOK));
		before.setPiece(0, 4, new Piece(WHITE, KING));
		before.setPiece(0, 7, new Piece(WHITE, ROOK));
		before.setPiece(2, 7, new Piece(BLACK, PAWN));
		before.setPiece(7, 3, new Piece(BLACK, KING));

		before.setCanCastleKingSide(WHITE, true);
		Set<Move> expectedMoves = Sets.newHashSet();

		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				before, new Position(0, 4)));

		Set<Move> expectedMoves1 = Sets.newHashSet();
		before.setPiece(5, 6, new Piece(BLACK, ROOK));
		expectedMoves1.add(new Move(new Position(0, 4), new Position(0, 3),
				null));
		expectedMoves1.add(new Move(new Position(0, 4), new Position(0, 5),
				null));

		assertEquals(expectedMoves1,
				stateExplorer.getPossibleMovesFromPosition(before,
						new Position(0, 4)));

	}

	/*
	 * End Tests by Shitian Ren <renshitian@gmail.com>
	 */

	/*
	 * Begin Tests by Leo Zis <leozis@gmail.com>
	 */
	void init_lz() {
		for (int col = 0; col < 8; ++col) {
			for (int row = 0; row < 8; ++row) {
				start.setPiece(row, col, null);
			}
		}
	}

	@Test
	public void testGetPossibleStartPositions_ForBlack_KingRookPawn() {
		Set<Position> expectedPositions = Sets.newHashSet();
		init_lz();
		start.setTurn(Color.BLACK);
		start.setPiece(new Position(2, 3), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));

		expectedPositions.add(new Position(3, 2));
		expectedPositions.add(new Position(4, 4));
		expectedPositions.add(new Position(5, 6));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossiblePositions_ForWhite_KingPawn() {
		Set<Move> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(0, 3), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 3), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 2), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(7, 3), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));

		expectedPositions.add(new Move(new Position(0, 3), new Position(1, 3),
				null));
		expectedPositions.add(new Move(new Position(0, 3), new Position(1, 4),
				null));
		expectedPositions.add(new Move(new Position(0, 3), new Position(0, 4),
				null));
		expectedPositions.add(new Move(new Position(3, 3), new Position(4, 2),
				null));
		expectedPositions.add(new Move(new Position(3, 3), new Position(4, 3),
				null));
		expectedPositions.add(new Move(new Position(3, 3), new Position(4, 4),
				null));

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossiblePositions_ForBlack_BlackKingChecked() {
		Set<Move> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(7, 0), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(2, 2), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(0, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(6, 4), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setTurn(Color.BLACK);
		// set canCastQueenSide, canCastKingSide for black to false
		start.setCanCastleKingSide(Color.BLACK, false);
		start.setCanCastleQueenSide(Color.BLACK, false);

		expectedPositions.add(new Move(new Position(0, 4), new Position(0, 3),
				null));
		expectedPositions.add(new Move(new Position(0, 4), new Position(0, 5),
				null));

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_ForWhiteKnight() {
		Set<Move> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(7, 0), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(1, 1), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(0, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(6, 5), new Piece(Color.WHITE,
				PieceKind.ROOK));

		expectedPositions.add(new Move(new Position(0, 7), new Position(1, 5),
				null));
		expectedPositions.add(new Move(new Position(0, 7), new Position(2, 6),
				null));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						0, 7)));
	}

	@Test
	public void testGetPossibleStartPositions_ForBlack_PawnsInSingleFile() {
		Set<Position> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(7, 0), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(2, 3), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(0, 0), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(1, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(2, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setTurn(Color.BLACK);

		expectedPositions.add(new Position(2, 3));
		expectedPositions.add(new Position(1, 6));
		expectedPositions.add(new Position(0, 0));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossiblePositions_ForBlack_PawnsPromotion() {
		Set<Move> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(1, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(0, 5), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setTurn(Color.BLACK);

		expectedPositions.add(new Move(new Position(1, 6), new Position(0, 5),
				PieceKind.ROOK));
		expectedPositions.add(new Move(new Position(1, 6), new Position(0, 5),
				PieceKind.QUEEN));
		expectedPositions.add(new Move(new Position(1, 6), new Position(0, 5),
				PieceKind.BISHOP));
		expectedPositions.add(new Move(new Position(1, 6), new Position(0, 5),
				PieceKind.KNIGHT));
		expectedPositions.add(new Move(new Position(1, 6), new Position(0, 6),
				PieceKind.ROOK));
		expectedPositions.add(new Move(new Position(1, 6), new Position(0, 6),
				PieceKind.QUEEN));
		expectedPositions.add(new Move(new Position(1, 6), new Position(0, 6),
				PieceKind.BISHOP));
		expectedPositions.add(new Move(new Position(1, 6), new Position(0, 6),
				PieceKind.KNIGHT));

		expectedPositions.add(new Move(new Position(7, 0), new Position(7, 1),
				null));
		expectedPositions.add(new Move(new Position(7, 0), new Position(6, 0),
				null));
		expectedPositions.add(new Move(new Position(7, 0), new Position(6, 1),
				null));

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_ForWhite_Enpassant() {
		Set<Move> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(4, 1), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 2), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4, 1));

		expectedPositions.add(new Move(new Position(4, 2), new Position(5, 1),
				null));
		expectedPositions.add(new Move(new Position(4, 2), new Position(5, 2),
				null));
		expectedPositions.add(new Move(new Position(4, 2), new Position(5, 3),
				null));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						4, 2)));
	}

	@Test
	public void testGetPossibleMoves_ForBlack_Enpassant() {
		Set<Move> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(3, 1), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 2), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setEnpassantPosition(new Position(3, 2));
		start.setTurn(Color.BLACK);

		expectedPositions.add(new Move(new Position(3, 1), new Position(2, 2),
				null));
		expectedPositions.add(new Move(new Position(3, 1), new Position(2, 1),
				null));
		expectedPositions.add(new Move(new Position(5, 3), new Position(4, 3),
				null));
		// black king moves
		expectedPositions.add(new Move(new Position(7, 0), new Position(6, 0),
				null));
		expectedPositions.add(new Move(new Position(7, 0), new Position(7, 1),
				null));
		expectedPositions.add(new Move(new Position(7, 0), new Position(6, 1),
				null));

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_ForBlack_Castle() {
		Set<Move> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(7, 7), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 0), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 7), new Piece(Color.WHITE,
				PieceKind.ROOK));

		start.setTurn(Color.BLACK);

		// king moves
		expectedPositions.add(new Move(new Position(7, 4), new Position(7, 2),
				null));
		expectedPositions.add(new Move(new Position(7, 4), new Position(7, 3),
				null));
		expectedPositions.add(new Move(new Position(7, 4), new Position(7, 5),
				null));
		expectedPositions.add(new Move(new Position(7, 4), new Position(7, 6),
				null));

		// rook moves
		expectedPositions.add(new Move(new Position(7, 0), new Position(6, 0),
				null));
		expectedPositions.add(new Move(new Position(7, 0), new Position(7, 1),
				null));
		expectedPositions.add(new Move(new Position(7, 0), new Position(7, 2),
				null));
		expectedPositions.add(new Move(new Position(7, 0), new Position(7, 3),
				null));
		expectedPositions.add(new Move(new Position(7, 7), new Position(6, 7),
				null));
		expectedPositions.add(new Move(new Position(7, 7), new Position(7, 6),
				null));
		expectedPositions.add(new Move(new Position(7, 7), new Position(7, 5),
				null));

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_ForWhite_CastleQueenSide() {
		Set<Move> expectedPositions = Sets.newHashSet();
		init_lz();

		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(1, 0), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(1, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));

		// king moves
		expectedPositions.add(new Move(new Position(0, 4), new Position(0, 2),
				null));
		expectedPositions.add(new Move(new Position(0, 4), new Position(0, 3),
				null));
		expectedPositions.add(new Move(new Position(0, 4), new Position(0, 5),
				null));
		expectedPositions.add(new Move(new Position(0, 4), new Position(1, 3),
				null));
		expectedPositions.add(new Move(new Position(0, 4), new Position(1, 4),
				null));
		expectedPositions.add(new Move(new Position(0, 4), new Position(1, 5),
				null));

		// rook moves
		expectedPositions.add(new Move(new Position(0, 0), new Position(1, 0),
				null));
		expectedPositions.add(new Move(new Position(0, 0), new Position(0, 1),
				null));
		expectedPositions.add(new Move(new Position(0, 0), new Position(0, 2),
				null));
		expectedPositions.add(new Move(new Position(0, 0), new Position(0, 3),
				null));
		expectedPositions.add(new Move(new Position(0, 7), new Position(1, 7),
				null));
		expectedPositions.add(new Move(new Position(0, 7), new Position(0, 6),
				null));
		expectedPositions.add(new Move(new Position(0, 7), new Position(0, 5),
				null));

		assertEquals(expectedPositions, stateExplorer.getPossibleMoves(start));
	}

	/*
	 * End Tests by Leo Zis <leozis@gmail.com>
	 */

	/*
	 * Begin Tests by Longjun Tan <longjuntan@gmail.com>
	 */

	private void initForTLJ() {
		for (int row : new int[] { 0, 1, 6, 7 }) {
			for (int col = 0; col <= 7; col++) {
				start.setPiece(row, col, null);
			}
		}
		start.setCanCastleKingSide(WHITE, false);
		start.setCanCastleKingSide(BLACK, false);
		start.setCanCastleQueenSide(WHITE, false);
		start.setCanCastleQueenSide(BLACK, false);
	}

	private void whiteKingCheckByBlackQueenForTLJ() {
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		start.setPiece(new Position(1, 2), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(7, 6), new Piece(Color.BLACK,
				PieceKind.KING));
	}

	private void blackKingCheckByMultipleForTLJ() {
		start.setPiece(new Position(7, 3), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(7, 5), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(5, 3), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 3), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		start.setPiece(new Position(6, 4), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setTurn(BLACK);
	}

	private void canCastlingAndPromotionForTLJ() {
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setCanCastleKingSide(WHITE, true);
		start.setPiece(new Position(5, 0), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(1, 2), new Piece(Color.BLACK,
				PieceKind.PAWN));
	}

	@Test
	public void testGetPossibleMoves_WhiteKingUnderCheck() {
		initForTLJ();
		whiteKingCheckByBlackQueenForTLJ();

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(1, 2), new Position(5, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(1, 7), null));
		expectedMoves
				.add(new Move(new Position(1, 2), new Position(1, 6), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleStartPositions_WhiteKingUnderCheck() {
		initForTLJ();
		whiteKingCheckByBlackQueenForTLJ();

		Set<Position> expectedPositions = Sets.newHashSet();
		expectedPositions.add(new Position(1, 2));
		expectedPositions.add(new Position(0, 7));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMoves_BlackKingCheckByMultiple_KingMove() {
		initForTLJ();
		blackKingCheckByMultipleForTLJ();
		start.setPiece(new Position(6, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(7, 3), new Position(6, 2), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_BlackKingCheckByMultiple_withCheckmate() {
		initForTLJ();
		blackKingCheckByMultipleForTLJ();
		start.setPiece(new Position(6, 1), new Piece(Color.WHITE,
				PieceKind.QUEEN));

		Set<Move> expectedMoves = Sets.newHashSet();

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleStartPositions_BlackKingCheckByMultiple_withCheckmate() {
		initForTLJ();
		blackKingCheckByMultipleForTLJ();
		start.setPiece(new Position(6, 1), new Piece(Color.WHITE,
				PieceKind.QUEEN));

		Set<Position> expectedPositions = Sets.newHashSet();

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMoves_BlackKingCheckByMultiple_canCapture() {
		initForTLJ();
		blackKingCheckByMultipleForTLJ();
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.KNIGHT));

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(7, 3), new Position(7, 2), null));
		expectedMoves
				.add(new Move(new Position(7, 3), new Position(6, 2), null));
		expectedMoves
				.add(new Move(new Position(5, 2), new Position(6, 4), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleStartPositions_BlackKingCheckByMultiple_canCapture() {
		initForTLJ();
		blackKingCheckByMultipleForTLJ();
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.KNIGHT));

		Set<Position> expectedPositions = Sets.newHashSet();
		expectedPositions.add(new Position(7, 3));
		expectedPositions.add(new Position(5, 2));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackKingCheckByMultiple_canCapture() {
		initForTLJ();
		blackKingCheckByMultipleForTLJ();
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.KNIGHT));

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(7, 3), new Position(7, 2), null));
		expectedMoves
				.add(new Move(new Position(7, 3), new Position(6, 2), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(7, 3)));
	}

	@Test
	public void testGetPossibleMoves_castling() {
		initForTLJ();
		canCastlingAndPromotionForTLJ();

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		for (int col : new int[] { 3, 4, 5 }) {
			expectedMoves.add(new Move(new Position(0, 4),
					new Position(1, col), null));
		}
		for (int row = 1; row < State.COLS; row++) {
			expectedMoves.add(new Move(new Position(0, 7),
					new Position(row, 7), null));
		}

		for (int col : new int[] { 5, 6 }) {
			expectedMoves.add(new Move(new Position(0, 7),
					new Position(0, col), null));
		}
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_promotion() {
		initForTLJ();
		canCastlingAndPromotionForTLJ();
		start.setTurn(BLACK);

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(5, 0), new Position(4, 0), null));
		expectedMoves
				.add(new Move(new Position(5, 0), new Position(6, 0), null));
		for (int row : new int[] { 4, 5, 6 }) {
			expectedMoves.add(new Move(new Position(5, 0),
					new Position(row, 1), null));
		}

		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 2),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 2),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 2),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(1, 2), new Position(0, 2),
				PieceKind.ROOK));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_promotion() {
		initForTLJ();
		canCastlingAndPromotionForTLJ();
		start.setPiece(new Position(0, 1), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setTurn(BLACK);

		Set<Move> expectedMoves = Sets.newHashSet();
		Position p = new Position(1, 2);

		for (int col : new int[] { 1, 2 }) {

			expectedMoves.add(new Move(p, new Position(0, col),
					PieceKind.BISHOP));
			expectedMoves.add(new Move(p, new Position(0, col),
					PieceKind.KNIGHT));
			expectedMoves
					.add(new Move(p, new Position(0, col), PieceKind.QUEEN));
			expectedMoves
					.add(new Move(p, new Position(0, col), PieceKind.ROOK));
		}

		assertEquals(expectedMoves,
				stateExplorer.getPossibleMovesFromPosition(start, p));
	}

	@Test
	public void testGetPossibleStartPositions_canCastlingAndPromotion() {
		initForTLJ();
		canCastlingAndPromotionForTLJ();
		start.setPiece(new Position(0, 1), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setTurn(BLACK);

		Set<Position> expectedPositions = Sets.newHashSet();
		expectedPositions.add(new Position(1, 2));
		expectedPositions.add(new Position(5, 0));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_canCastlingAndPromotion() {
		initForTLJ();
		canCastlingAndPromotionForTLJ();
		start.setPiece(new Position(0, 1), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setTurn(BLACK);

		Set<Move> expectedMoves = Sets.newHashSet();

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 7)));
	}

	/*
	 * End Tests by Longjun Tan <longjuntan@gmail.com>
	 */

	/*
	 * Begin Tests by Mark Anderson <markmakingmusic@gmail.com>
	 */

	@Test
	public void testGetPossibleStartPositions_WhitePawnsStaggeredRemovalFromBoard_mea() {

		Set<Position> expectedPositions = Sets.newHashSet();

		for (int i = 0; i < 8; i += 2) {
			// staggered removal of pawns
			start.setPiece(new Position(1, i), null);

			// pawns expected to move
			expectedPositions.add(new Position(1, i + 1));
		}
		// rook 1
		expectedPositions.add(new Position(0, 0));
		// knight 1
		expectedPositions.add(new Position(0, 1));
		// queen
		expectedPositions.add(new Position(0, 3));
		// king
		expectedPositions.add(new Position(0, 4));
		// bishop 2
		expectedPositions.add(new Position(0, 5));
		// knight 2
		expectedPositions.add(new Position(0, 6));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	public void testGetPossibleStartPositions_BlackPawnsStaggeredRemovalFromBoard_mea() {

		Set<Position> expectedPositions = Sets.newHashSet();

		start.setTurn(Color.BLACK);

		for (int i = 0; i < 8; i += 2) {
			// staggered removal of pawns
			start.setPiece(new Position(6, i), null);

			// pawns expected to move
			expectedPositions.add(new Position(6, i + 1));
		}
		// rook 1
		expectedPositions.add(new Position(7, 0));
		// knight 1
		expectedPositions.add(new Position(7, 1));
		// queen
		expectedPositions.add(new Position(7, 3));
		// king
		expectedPositions.add(new Position(7, 4));
		// bishop 2
		expectedPositions.add(new Position(7, 5));
		// knight 2
		expectedPositions.add(new Position(7, 6));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackEnpassantPositionNotNull_mea() {
		initForManderson();

		Set<Move> expectedMoves = Sets.newHashSet();

		// black pawn
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4, 4));

		// white pawn to capture
		start.setPiece(new Position(4, 5), new Piece(Color.WHITE,
				PieceKind.PAWN));

		expectedMoves
				.add(new Move(new Position(4, 5), new Position(5, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 5), new Position(5, 5), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 5)));
	}

	@Test
	public void testGetPossibleMoves_BlackKingInCheckWithNoOtherPossiblePieceMovement_mea() {
		initForManderson();

		Set<Move> expectedMoves = Sets.newHashSet();

		start.setTurn(Color.BLACK);

		start.setPiece(new Position(5, 3), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 7), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(1, 4), new Piece(Color.WHITE,
				PieceKind.QUEEN));

		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 5), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_KingMustMoveOrBeBlockedBySeveralPieces_mea() {
		initForManderson();

		Set<Move> expectedMoves = Sets.newHashSet();

		start.setTurn(Color.BLACK);

		// move king
		start.setPiece(new Position(7, 4), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.KING));

		start.setPiece(new Position(3, 0), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.BISHOP));
		start.setPiece(new Position(1, 5), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(1, 4), new Piece(Color.WHITE,
				PieceKind.QUEEN));

		// for the block
		expectedMoves
				.add(new Move(new Position(5, 6), new Position(3, 4), null));
		expectedMoves
				.add(new Move(new Position(3, 0), new Position(3, 4), null));
		expectedMoves
				.add(new Move(new Position(1, 5), new Position(3, 4), null));

		// for the move
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(3, 3), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(3, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(4, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(5, 3), null));
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(4, 3), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_Stalemate_mea() {
		initForManderson();

		Set<Move> expectedMoves = Sets.newHashSet();

		start.setTurn(Color.BLACK);

		// move black king
		start.setPiece(new Position(7, 4), null);
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
				PieceKind.KING));

		// add pieces
		start.setPiece(new Position(5, 1), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(5, 2), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 2), new Piece(Color.WHITE,
				PieceKind.QUEEN));

		// stalemate
		start.setGameResult(new GameResult(null, GameResultReason.STALEMATE));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_Checkmate_mea() {
		initForManderson();

		Set<Move> expectedMoves = Sets.newHashSet();

		start.setTurn(Color.BLACK);

		// move black king
		start.setPiece(new Position(7, 4), null);
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
				PieceKind.KING));

		// add pieces
		start.setPiece(new Position(5, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(5, 2), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(6, 2), new Piece(Color.WHITE,
				PieceKind.QUEEN));

		// stalemate
		start.setGameResult(new GameResult(null, GameResultReason.CHECKMATE));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_FiftyMoveRule_mea() {
		initForManderson();

		Set<Move> expectedMoves = Sets.newHashSet();

		start.setTurn(Color.WHITE);
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		start.setGameResult(new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE));

		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMovesFromPosition_QueenCanCaptureWhiteRook_mea() {
		Set<Move> expectedMoves = Sets.newHashSet();

		start.setTurn(Color.BLACK);

		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(1, 7), null);
		start.setPiece(new Position(0, 7), null);
		start.setPiece(new Position(3, 7), new Piece(Color.WHITE,
				PieceKind.ROOK));

		expectedMoves
				.add(new Move(new Position(7, 3), new Position(6, 4), null));
		expectedMoves
				.add(new Move(new Position(7, 3), new Position(5, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 3), new Position(4, 6), null));
		expectedMoves
				.add(new Move(new Position(7, 3), new Position(3, 7), null));

		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(7, 3)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_PromotionCaptureOrNot_mea() {
		Set<Move> expectedMoves = Sets.newHashSet();

		start.setTurn(Color.WHITE);

		start.setPiece(new Position(7, 1), null);
		start.setPiece(new Position(6, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 2), null);
		start.setPiece(new Position(5, 2), new Piece(Color.BLACK,
				PieceKind.KNIGHT));
		start.setPiece(new Position(3, 1), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(2, 3), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(1, 1), null);
		start.setPiece(new Position(1, 2), null);
		start.setPiece(new Position(1, 3), null);

		for (int i = 0; i < 3; i++) {
			expectedMoves.add(new Move(new Position(6, 1), new Position(7, i),
					PieceKind.BISHOP));
			expectedMoves.add(new Move(new Position(6, 1), new Position(7, i),
					PieceKind.QUEEN));
			expectedMoves.add(new Move(new Position(6, 1), new Position(7, i),
					PieceKind.KNIGHT));
			expectedMoves.add(new Move(new Position(6, 1), new Position(7, i),
					PieceKind.ROOK));

		}
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 1)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackQueenMovesWithNoPawnsOnBoard_mea() {
		Set<Move> expectedMoves = Sets.newHashSet();
		initForManderson_noPawns();

		start.setTurn(Color.BLACK);

		int startPos = 1;
		int endPos = 7;
		int cornerPos = 4;

		// one blocking piece for the queen
		start.setPiece(new Position(3, 7), new Piece(Color.BLACK,
				PieceKind.PAWN));

		for (int i = 0; i < endPos; i++) {
			expectedMoves.add(new Move(new Position(7, 3), new Position(i, 3),
					null));
		}
		for (int i = startPos; i < cornerPos; i++) {
			expectedMoves.add(new Move(new Position(7, 3), new Position(7 - i,
					3 + i), null));
			expectedMoves.add(new Move(new Position(7, 3), new Position(7 - i,
					3 - i), null));
		}
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(7, 3)));
	}

	@Test
	public void testGetPossibleMoves_KingMustMoveOrBeBlockedByRookWhileInCheck_mea() {
		Set<Move> expectedMoves = Sets.newHashSet();
		initForManderson();

		start.setTurn(Color.BLACK);

		start.setPiece(new Position(3, 4), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(6, 7), new Piece(Color.BLACK,
				PieceKind.ROOK));

		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 5), null));

		// the rook can also move to block the king from being in check
		expectedMoves
				.add(new Move(new Position(6, 7), new Position(6, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testGetPossibleMoves_WhiteBishopCannotExposeKing_mea() {
		Set<Move> expectedMoves = Sets.newHashSet();
		initForManderson();

		start.setTurn(Color.BLACK);

		start.setPiece(new Position(7, 3), new Piece(Color.BLACK,
				PieceKind.BISHOP));

		start.setPiece(new Position(7, 2), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(7, 7), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		start.setPiece(new Position(5, 5), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));

		// king can only move one space, otherwise check
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	public void initForManderson_noPawns() {
		for (int i = 0; i < 8; i++) {
			// die pawns!
			start.setPiece(new Position(1, i), null);
			start.setPiece(new Position(6, i), null);
		}
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));
	}

	public void initForManderson() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				start.setPiece(new Position(i, j), null);
			}
		}
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));

		start.setCanCastleQueenSide(Color.WHITE, false);
		start.setCanCastleQueenSide(Color.BLACK, false);

		start.setCanCastleKingSide(Color.WHITE, false);
		start.setCanCastleKingSide(Color.BLACK, false);
	}

	/*
	 * End Tests by Mark Anderson <markmakingmusic@gmail.com>
	 */

	/*
	 * Start Tests by Zhihan Li <lizhihan1211@gmail.com>
	 */
	public void initZHL(Color color) {
		Piece[][] board = new Piece[State.ROWS][State.COLS];
		start = new State(color, board, new boolean[2], new boolean[2], null,
				0, null);
	}

	@Test
	public void testStateExplorerGetPossibleStartPositionsBlackInit() {
		Set<Position> expectedStartPos = Sets.newHashSet();
		start.setTurn(BLACK);
		start.setPiece(1, 0, null);
		start.setPiece(3, 0, new Piece(WHITE, PAWN));

		for (int col = 0; col < 8; col++) {
			expectedStartPos.add(new Position(6, col));
		}
		expectedStartPos.add(new Position(7, 1));
		expectedStartPos.add(new Position(7, 6));
		assertEquals(expectedStartPos,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testStateExplorerGetPossibleStartPositionBlackNoPawn() {
		Set<Position> expectedStartPos = Sets.newHashSet();
		start.setPiece(0, 1, null);
		start.setPiece(3, 0, new Piece(WHITE, KNIGHT));
		start.setTurn(BLACK);

		for (int col = 0; col < 8; col++) {
			start.setPiece(6, col, null);
		}

		for (int col = 0; col < 8; col++) {
			expectedStartPos.add(new Position(7, col));
		}
		assertEquals(expectedStartPos,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testStateExplorerGetPossibleStartPositionWhiteKingUnderCheck() {
		Set<Position> expectedStartPos = Sets.newHashSet();
		initZHL(WHITE);
		start.setPiece(3, 0, new Piece(BLACK, ROOK));
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(4, 4, new Piece(WHITE, KNIGHT));
		start.setPiece(0, 0, new Piece(WHITE, KING));

		expectedStartPos.add(new Position(0, 0));

		assertEquals(expectedStartPos,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testStateExplorerGetPossibleMovesBlackKingAndRook() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();

		initZHL(BLACK);
		start.setPiece(0, 0, new Piece(WHITE, KING));
		start.setPiece(4, 4, new Piece(WHITE, KNIGHT));
		start.setPiece(7, 6, new Piece(BLACK, ROOK));
		start.setPiece(7, 4, new Piece(BLACK, KING));

		// Black king
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(7, 3), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(7, 5), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(6, 4), null));

		// Black rook
		expectedPossibleMove.add(new Move(new Position(7, 6),
				new Position(7, 5), null));
		expectedPossibleMove.add(new Move(new Position(7, 6),
				new Position(7, 7), null));
		for (int row = 6; row >= 0; row--)
			expectedPossibleMove.add(new Move(new Position(7, 6), new Position(
					row, 6), null));

		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testStateExplorerGetPossibleMovesWhiteKingAndKnight() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();
		initZHL(WHITE);
		start.setPiece(0, 0, new Piece(WHITE, KING));
		start.setPiece(4, 4, new Piece(WHITE, KNIGHT));
		start.setPiece(7, 6, new Piece(BLACK, ROOK));
		start.setPiece(7, 4, new Piece(BLACK, KING));

		// WHITE King
		expectedPossibleMove.add(new Move(new Position(0, 0),
				new Position(1, 0), null));
		expectedPossibleMove.add(new Move(new Position(0, 0),
				new Position(1, 1), null));
		expectedPossibleMove.add(new Move(new Position(0, 0),
				new Position(0, 1), null));

		// White Knight
		expectedPossibleMove.add(new Move(new Position(4, 4),
				new Position(6, 3), null));
		expectedPossibleMove.add(new Move(new Position(4, 4),
				new Position(6, 5), null));
		expectedPossibleMove.add(new Move(new Position(4, 4),
				new Position(5, 2), null));
		expectedPossibleMove.add(new Move(new Position(4, 4),
				new Position(5, 6), null));
		expectedPossibleMove.add(new Move(new Position(4, 4),
				new Position(3, 2), null));
		expectedPossibleMove.add(new Move(new Position(4, 4),
				new Position(3, 6), null));
		expectedPossibleMove.add(new Move(new Position(4, 4),
				new Position(2, 3), null));
		expectedPossibleMove.add(new Move(new Position(4, 4),
				new Position(2, 5), null));

		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testStateExplorerGetPossibleMovesWhiteKingUnderCheck() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();
		initZHL(WHITE);
		start.setPiece(3, 0, new Piece(BLACK, ROOK));
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(4, 4, new Piece(WHITE, KNIGHT));
		start.setPiece(0, 0, new Piece(WHITE, KING));

		expectedPossibleMove.add(new Move(new Position(0, 0),
				new Position(0, 1), null));
		expectedPossibleMove.add(new Move(new Position(0, 0),
				new Position(1, 1), null));

		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testStateExplorerGetPossibleMovesInStaleMate() {
		initZHL(WHITE);
		start.setPiece(7, 7, new Piece(WHITE, KING));
		start.setPiece(5, 6, new Piece(BLACK, QUEEN));
		start.setPiece(6, 5, new Piece(BLACK, KING));
		Set<Move> expectedPossibleMove = Sets.newHashSet();
		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMoves(start));

	}

	@Test
	public void testStateExplorerGetPossibleMovesBlackKingCanCastle() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();

		initZHL(BLACK);
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(7, 7, new Piece(BLACK, ROOK));
		start.setPiece(4, 4, new Piece(WHITE, KNIGHT));
		start.setPiece(0, 0, new Piece(WHITE, KING));
		start.setCanCastleKingSide(BLACK, true);

		// Black rook
		expectedPossibleMove.add(new Move(new Position(7, 7),
				new Position(7, 5), null));
		expectedPossibleMove.add(new Move(new Position(7, 7),
				new Position(7, 6), null));
		for (int row = 6; row >= 0; row--) {
			expectedPossibleMove.add(new Move(new Position(7, 7), new Position(
					row, 7), null));
		}

		// Black King
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(7, 3), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(7, 5), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(6, 4), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(7, 6), null));

		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMoves(start));

	}

	@Test
	public void testStateExplorerGetPossibleMovesFromPositionWhiteKingInit() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();

		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						0, 4)));
	}

	@Test
	public void testStateExplorerGetPossibleMovesFromPositionFromSquareWithNoPiece() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();
		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						4, 4)));
	}

	@Test
	public void testStateExplorerGetPossibleMovesFromPositionWhiteQueen() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();
		initZHL(WHITE);
		start.setPiece(0, 0, new Piece(WHITE, KING));
		start.setPiece(1, 2, new Piece(WHITE, PAWN));
		start.setPiece(1, 3, new Piece(WHITE, PAWN));
		start.setPiece(0, 4, new Piece(WHITE, QUEEN));
		start.setPiece(7, 5, new Piece(BLACK, KING));
		start.setPiece(7, 7, new Piece(BLACK, ROOK));

		// White queen
		for (int row = 1; row < 8; row++) {
			expectedPossibleMove.add(new Move(new Position(0, 4), new Position(
					row, 4), null));
		}
		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(1, 5), null));
		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(2, 6), null));
		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(3, 7), null));

		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(0, 1), null));
		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(0, 2), null));
		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(0, 3), null));
		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(0, 5), null));
		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(0, 6), null));
		expectedPossibleMove.add(new Move(new Position(0, 4),
				new Position(0, 7), null));
		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						0, 4)));
	}

	@Test
	public void testStateExplorerGetPossibleMovesFromPositionBlackBishop() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();
		initZHL(BLACK);
		start.setPiece(0, 0, new Piece(WHITE, KING));
		start.setPiece(5, 2, new Piece(WHITE, ROOK));
		start.setPiece(7, 4, new Piece(BLACK, BISHOP));
		start.setPiece(7, 5, new Piece(BLACK, KING));

		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(5, 2), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(6, 3), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(6, 5), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(5, 6), null));
		expectedPossibleMove.add(new Move(new Position(7, 4),
				new Position(4, 7), null));

		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						7, 4)));

	}

	@Test
	public void testStateExplorerGetPossibleMovesFromPositionWhitePawnEnpassantCapture() {
		Set<Move> expectedPossibleMove = Sets.newHashSet();
		start.setPiece(6, 1, null);
		start.setPiece(4, 1, new Piece(BLACK, PAWN));
		start.setPiece(1, 2, null);
		start.setPiece(4, 2, new Piece(WHITE, PAWN));
		start.setPiece(6, 6, null);
		start.setPiece(5, 6, new Piece(BLACK, PAWN));
		start.setEnpassantPosition(new Position(4, 1));

		expectedPossibleMove.add(new Move(new Position(4, 2),
				new Position(5, 2), null));
		expectedPossibleMove.add(new Move(new Position(4, 2),
				new Position(5, 1), null));
		assertEquals(expectedPossibleMove,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						4, 2)));

	}

	/*
	 * End Tests by Zhihan Li <lizhihan1211@gmail.com>
	 */
	/*
	 * Begin Tests by Peigen You <fusubacon@gmail.com>
	 */

	public void initForPeigenYou(State state) {
		start = new State();
		for (int c = 0; c < 8; c++) {
			for (int r = 0; r < 8; r++) {
				start.setPiece(r, c, null);
			}
		}
		start.setPiece(0, 4, new Piece(WHITE, KING));
		start.setTurn(BLACK);
	}

	@Test
	public void testForOnlyKingAndKnightNoCheck() {
		Set<Position> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(7, 2, new Piece(BLACK, KNIGHT));
		expectedPositions.add(new Position(7, 4));
		expectedPositions.add(new Position(7, 2));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testForOnlyKingAndRookSolveCheck() {
		Set<Position> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(6, 3, new Piece(BLACK, ROOK));
		start.setPiece(6, 4, new Piece(WHITE, QUEEN));
		expectedPositions.add(new Position(7, 4));
		expectedPositions.add(new Position(6, 3));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testForBishopSolveCheck() {
		Set<Position> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(6, 3, new Piece(BLACK, BISHOP));
		start.setPiece(4, 4, new Piece(WHITE, QUEEN));
		expectedPositions.add(new Position(7, 4));
		expectedPositions.add(new Position(6, 3));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testPawnSolveCheck() {
		Set<Position> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(6, 4, new Piece(BLACK, PAWN));
		start.setPiece(5, 3, new Piece(WHITE, KNIGHT));
		expectedPositions.add(new Position(7, 4));
		expectedPositions.add(new Position(6, 4));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testPawnSolveCheck2() {
		Set<Position> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		start.setPiece(7, 5, new Piece(BLACK, KING));
		start.setPiece(6, 2, new Piece(BLACK, PAWN));
		start.setPiece(3, 1, new Piece(WHITE, QUEEN));
		expectedPositions.add(new Position(7, 5));
		expectedPositions.add(new Position(6, 2));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testStalemate() {
		Set<Position> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		start.setPiece(7, 7, new Piece(BLACK, KING));
		start.setPiece(6, 6, new Piece(WHITE, ROOK));
		start.setPiece(5, 5, new Piece(WHITE, QUEEN));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testCheckmate() {
		Set<Position> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		start.setPiece(7, 7, new Piece(BLACK, KING));
		start.setPiece(6, 6, new Piece(WHITE, QUEEN));
		start.setPiece(5, 5, new Piece(WHITE, PAWN));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testStalemate2() {
		Set<Position> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		start.setPiece(7, 7, new Piece(BLACK, KING));
		start.setPiece(7, 0, new Piece(WHITE, ROOK));
		start.setPiece(5, 6, new Piece(WHITE, QUEEN));
		start.setPiece(7, 6, new Piece(BLACK, BISHOP));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testCastle() {
		Set<Move> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		Position startPos = new Position(7, 4);
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(7, 0, new Piece(BLACK, ROOK));
		start.setPiece(6, 0, new Piece(BLACK, PAWN));
		start.setPiece(6, 3, new Piece(BLACK, PAWN));
		start.setPiece(6, 5, new Piece(BLACK, PAWN));

		start.setPiece(6, 4, new Piece(BLACK, PAWN));
		start.setCanCastleKingSide(BLACK, false);
		expectedPositions.add(new Move(startPos, new Position(7, 3), null));
		expectedPositions.add(new Move(startPos, new Position(7, 2), null));
		expectedPositions.add(new Move(startPos, new Position(7, 5), null));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						7, 4)));
	}

	@Test
	public void testcantCastle() {
		Set<Move> expectedPositions = Sets.newHashSet();
		initForPeigenYou(start);
		Position startPos = new Position(7, 4);
		start.setPiece(7, 4, new Piece(BLACK, KING));
		start.setPiece(7, 0, new Piece(BLACK, ROOK));
		start.setPiece(6, 0, new Piece(BLACK, PAWN));
		start.setPiece(6, 5, new Piece(BLACK, PAWN));
		start.setPiece(4, 3, new Piece(WHITE, ROOK));

		start.setPiece(6, 4, new Piece(BLACK, PAWN));
		// should be setCanCastleQueenSide to false;
		start.setCanCastleKingSide(BLACK, false);
		start.setCanCastleQueenSide(BLACK, false);
		expectedPositions.add(new Move(startPos, new Position(7, 5), null));

		assertEquals(expectedPositions,
				stateExplorer.getPossibleMovesFromPosition(start, new Position(
						7, 4)));
	}
	/**
	 * end test of Peigen You <fusubacon@gmail.com>
	 */
	/*
	 * Begin Tests by Kan Wang <kerrywang881122@gmail.com>
	 */
	/**
	 * test for general case
	 */

	State initStateKanWang1() {
		Piece[][] board = new Piece[8][8];
		board[7][7] = new Piece(Color.BLACK, PieceKind.KING);
		board[7][6] = new Piece(Color.BLACK, PieceKind.ROOK);
		board[6][5] = new Piece(Color.BLACK, PieceKind.KNIGHT);
		board[6][1] = new Piece(Color.WHITE, PieceKind.ROOK);
		board[5][6] = new Piece(Color.WHITE, PieceKind.PAWN);
		board[4][5] = new Piece(Color.WHITE, PieceKind.KING);

		State state = new State(Color.WHITE, board, new boolean[] { false, false },
				new boolean[] { false, false }, null, 0, null);
		return state;
	}

	@Test
	public void testGetPossibleStartPositions_state1() {
		Set<Position> e = Sets.newHashSet();
		State begin = initStateKanWang1();

		e.add(new Position(6, 1));
		e.add(new Position(4, 5));
		e.add(new Position(5, 6));
		assertEquals(e, stateExplorer.getPossibleStartPositions(begin));
	}

	@Test
	public void testGetPossibleMoveFromPosition_state1p1() {
		Set<Move> e = Sets.newHashSet();
		State begin = initStateKanWang1();

		e.add(new Move(new Position(5, 6), new Position(6, 6), null));
		e.add(new Move(new Position(5, 6), new Position(6, 5), null));

		assertEquals(e, stateExplorer.getPossibleMovesFromPosition(begin,
				new Position(5, 6)));
	}

	@Test
	public void testGetPossibleMoveFromPosition_state1p2() {
		Set<Move> e = Sets.newHashSet();
		State begin = initStateKanWang1();

		e.add(new Move(new Position(4, 5), new Position(5, 5), null));
		e.add(new Move(new Position(4, 5), new Position(3, 5), null));
		e.add(new Move(new Position(4, 5), new Position(5, 4), null));
		e.add(new Move(new Position(4, 5), new Position(3, 4), null));
		e.add(new Move(new Position(4, 5), new Position(3, 6), null));

		assertEquals(e, stateExplorer.getPossibleMovesFromPosition(begin,
				new Position(4, 5)));
	}

	@Test
	public void testGetPossibleMoveFromPosition_state1p3() {
		Set<Move> e = Sets.newHashSet();
		State begin = initStateKanWang1();

		e.add(new Move(new Position(6, 1), new Position(0, 1), null));
		e.add(new Move(new Position(6, 1), new Position(1, 1), null));
		e.add(new Move(new Position(6, 1), new Position(2, 1), null));
		e.add(new Move(new Position(6, 1), new Position(3, 1), null));
		e.add(new Move(new Position(6, 1), new Position(4, 1), null));
		e.add(new Move(new Position(6, 1), new Position(5, 1), null));
		e.add(new Move(new Position(6, 1), new Position(7, 1), null));

		e.add(new Move(new Position(6, 1), new Position(6, 0), null));
		e.add(new Move(new Position(6, 1), new Position(6, 2), null));
		e.add(new Move(new Position(6, 1), new Position(6, 3), null));
		e.add(new Move(new Position(6, 1), new Position(6, 4), null));
		e.add(new Move(new Position(6, 1), new Position(6, 5), null));

		assertEquals(e, stateExplorer.getPossibleMovesFromPosition(begin,
				new Position(6, 1)));
	}

	@Test
	public void testGetPossibleMoves_state1() {
		Set<Move> e = Sets.newHashSet();
		State begin = initStateKanWang1();
		begin.setTurn(Color.BLACK);

		e.add(new Move(new Position(7, 6), new Position(7, 0), null));
		e.add(new Move(new Position(7, 6), new Position(7, 1), null));
		e.add(new Move(new Position(7, 6), new Position(7, 2), null));
		e.add(new Move(new Position(7, 6), new Position(7, 3), null));
		e.add(new Move(new Position(7, 6), new Position(7, 4), null));
		e.add(new Move(new Position(7, 6), new Position(7, 5), null));
		e.add(new Move(new Position(7, 6), new Position(6, 6), null));
		e.add(new Move(new Position(7, 6), new Position(5, 6), null));

		e.add(new Move(new Position(6, 5), new Position(7, 3), null));
		e.add(new Move(new Position(6, 5), new Position(5, 3), null));
		e.add(new Move(new Position(6, 5), new Position(4, 4), null));
		e.add(new Move(new Position(6, 5), new Position(4, 6), null));
		e.add(new Move(new Position(6, 5), new Position(5, 7), null));

		e.add(new Move(new Position(7, 7), new Position(6, 6), null));
		assertEquals(e, stateExplorer.getPossibleMoves(begin));
	}

	/**
	 * test for promotion
	 */

	State initStateKanWang2() {
		Piece[][] board = new Piece[8][8];
		board[6][0] = new Piece(Color.BLACK, PieceKind.KING);
		board[1][0] = new Piece(Color.WHITE, PieceKind.KING);
		board[6][4] = new Piece(Color.WHITE, PieceKind.PAWN);
		board[7][3] = new Piece(Color.BLACK, PieceKind.ROOK);

		State state = new State(Color.WHITE, board, new boolean[] { false, false },
				new boolean[] { false, false }, null, 0, null);
		return state;
	}

	@Test
	public void testGetPossibleStartPosition_Promotion() {
		Set<Position> e = Sets.newHashSet();
		State begin = initStateKanWang2();

		e.add(new Position(1, 0));
		e.add(new Position(6, 4));

		assertEquals(e, stateExplorer.getPossibleStartPositions(begin));

	}

	@Test
	public void testKanWangGetPossibleMovesFromPosition_Promotion() {
		Set<Move> e = Sets.newHashSet();
		State begin = initStateKanWang2();

		e.add(new Move(new Position(6, 4), new Position(7, 4), PieceKind.BISHOP));
		e.add(new Move(new Position(6, 4), new Position(7, 4), PieceKind.QUEEN));
		e.add(new Move(new Position(6, 4), new Position(7, 4), PieceKind.ROOK));
		e.add(new Move(new Position(6, 4), new Position(7, 4), PieceKind.KNIGHT));

		e.add(new Move(new Position(6, 4), new Position(7, 3), PieceKind.BISHOP));
		e.add(new Move(new Position(6, 4), new Position(7, 3), PieceKind.QUEEN));
		e.add(new Move(new Position(6, 4), new Position(7, 3), PieceKind.ROOK));
		e.add(new Move(new Position(6, 4), new Position(7, 3), PieceKind.KNIGHT));

		assertEquals(e, stateExplorer.getPossibleMovesFromPosition(begin,
				new Position(6, 4)));
	}

	/**
	 * test for enpassant
	 */

	State initStateKanWang3() {
		Piece[][] board = new Piece[8][8];
		board[6][0] = new Piece(Color.BLACK, PieceKind.KING);
		board[1][0] = new Piece(Color.WHITE, PieceKind.KING);
		board[3][6] = new Piece(Color.WHITE, PieceKind.PAWN);
		board[3][5] = new Piece(Color.BLACK, PieceKind.PAWN);

		return new State(Color.BLACK, board, new boolean[] { false, false },
				new boolean[] { false, false }, new Position(3, 6), 0, null);
	}

	@Test
	public void testGetPossibleStartPosition_Enpassant() {
		Set<Position> e = Sets.newHashSet();
		State begin = initStateKanWang3();

		e.add(new Position(3, 5));
		e.add(new Position(6, 0));

		assertEquals(e, stateExplorer.getPossibleStartPositions(begin));

	}

	@Test
	public void testKanWangGetPossibleMovesFromPosition_Enpassant() {
		Set<Move> e = Sets.newHashSet();
		State begin = initStateKanWang3();

		e.add(new Move(new Position(3, 5), new Position(2, 6), null));
		e.add(new Move(new Position(3, 5), new Position(2, 5), null));

		assertEquals(e, stateExplorer.getPossibleMovesFromPosition(begin,
				new Position(3, 5)));
	}

	/**
	 * test for castling
	 */

	State initStateKanWang4() {
		Piece[][] board = new Piece[8][8];
		board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
		board[1][0] = new Piece(Color.WHITE, PieceKind.KING);
		board[7][0] = new Piece(Color.BLACK, PieceKind.ROOK);
		board[7][7] = new Piece(Color.BLACK, PieceKind.ROOK);
		board[6][6] = new Piece(Color.WHITE, PieceKind.PAWN);

		return new State(Color.BLACK, board, new boolean[] { false, true },
				new boolean[] { false, true }, new Position(3, 6), 0, null);
	}

	@Test
	public void testGetPossibleStartPosition_Castling() {
		Set<Position> e = Sets.newHashSet();
		State begin = initStateKanWang4();

		e.add(new Position(7, 4));
		e.add(new Position(7, 0));
		e.add(new Position(7, 7));

		assertEquals(e, stateExplorer.getPossibleStartPositions(begin));

	}

	@Test
	public void testGetPossibleMovesFromPosition_Castling() {
		Set<Move> e = Sets.newHashSet();
		State begin = initStateKanWang4();
		
		e.add(new Move(new Position(7, 4), new Position(7, 2), null));
		e.add(new Move(new Position(7, 4), new Position(7, 3), null));
		e.add(new Move(new Position(7, 4), new Position(6, 3), null));
		e.add(new Move(new Position(7, 4), new Position(6, 4), null));
		e.add(new Move(new Position(7, 4), new Position(6, 5), null));
		assertEquals(e, stateExplorer.getPossibleMovesFromPosition(begin,
				new Position(7, 4)));
	}
	
	/*
	 * end test of Kan Wang <kerrywang881122@gmail.com>
	 */
	
	
	/*
	 * Begin Tests by Bohou Li <bohoulee@gmail.com>
	 */

	@Test
	public void testGetPossibleStartPositions_BohouInitState() {
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		for (int i = 0; i < 8; i++)
			expectedPositions.add(new Position(1, i));
		// knight positions
		expectedPositions.add(new Position(0, 1));
		expectedPositions.add(new Position(0, 6));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testGetPossibleMoves_BohouInitState() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// pawn moves
		for (int i = 0; i < 8; i++) {
			expectedMoves.add(new Move(new Position(1, i), new Position(2, i),
					null));
			expectedMoves.add(new Move(new Position(1, i), new Position(3, i),
					null));
		}
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void testKnightPossibleMoves_BohouInitState() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 1)));
	}

	@Test
	public void testPossiblePromotionMoves_BohouPromotionState() {
		start.setPiece(new Position(1, 0), null);
		start.setPiece(new Position(6, 0), new Piece(Color.WHITE,
				PieceKind.PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();
		// promotion moves
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 0)));
	}

	private void setBohouState() {
		start.setPiece(new Position(7, 7), null);
		start.setPiece(new Position(6, 7), null);
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(2, 7), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(2, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(2, 3), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(1, 1), null);
		for (int i = 3; i < 8; i++) {
			start.setPiece(new Position(1, i), null);
		}
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(0, 2), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		start.setPiece(new Position(0, 3), new Piece(Color.WHITE,
				PieceKind.QUEEN));
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(0, 6), null);
		start.setCanCastleKingSide(Color.BLACK, false);
	}

	@Test
	public void testStartPositions_BohouSetState() {
		boolean[] castleBooleans = new boolean[] { false, false };
		start = new State(Color.WHITE, new Piece[8][8], castleBooleans,
				castleBooleans, null, 0, null);
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
				PieceKind.KING));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
				PieceKind.ROOK));
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
				PieceKind.KING));
		start.setPiece(new Position(7, 7), new Piece(Color.BLACK,
				PieceKind.ROOK));

		Set<Position> expectedPositions = Sets.newHashSet();
		expectedPositions.add(new Position(0, 0));
		expectedPositions.add(new Position(0, 4));
		expectedPositions.add(new Position(0, 7));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void testKingPossibleMoves_BohouSetState() {
		setBohouState();
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 4)));
	}

	@Test
	public void testQueenPossibleMoves_BohouSetState() {
		setBohouState();
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 3), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 3), new Position(1, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 3), new Position(2, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 3)));
	}

	@Test
	public void testBishopPossibleMoves_BohouSetState() {
		setBohouState();
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(1, 3), null));
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(1, 1), null));
		expectedMoves
				.add(new Move(new Position(0, 2), new Position(2, 0), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 2)));
	}

	@Test
	public void testRookPossibleMoves_BohouSetState() {
		setBohouState();
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(1, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(2, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(0, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 7)));
	}

	@Test
	public void testPawnPossibleMoves_BohouSetState() {
		setBohouState();
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(1, 0), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(1, 0), new Position(3, 0), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 0)));
	}

	@Test
	public void testEnpassentMoves_BohouSetState() {
		start.setPiece(new Position(7, 7), null);
		start.setPiece(new Position(6, 7), null);
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(4, 3), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(3, 6), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(2, 7), new Piece(Color.BLACK,
				PieceKind.ROOK));
		start.setPiece(new Position(2, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(1, 1), null);
		for (int i = 3; i < 8; i++) {
			start.setPiece(new Position(1, i), null);
		}
		start.setPiece(new Position(0, 1), null);
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(0, 6), null);
		start.setEnpassantPosition(new Position(4, 4));
		start.setCanCastleKingSide(Color.BLACK, false);

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 3), null));
		expectedMoves
				.add(new Move(new Position(4, 3), new Position(5, 4), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 3)));
	}
	/*
	 * End Tests by Bohou Li <Bohoulee@gmail.com>
	 */
	
  
  /**
   * Begin Tests by Simon Gellis <simongellis@gmail.com>
   */
	public void startForSimon(Color turn, boolean placeKings) {
		start = new State(turn, new Piece[State.ROWS][State.COLS], new boolean[]{true, true}, new boolean[]{true, true}, null, 0, null);
		if (placeKings) {
			start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(7, 4, new Piece(Color.BLACK, PieceKind.KING));
		}
	}
	
	@Test
	public void testNoPossibleMovesInCheckmate() {
		startForSimon(Color.BLACK, true);
		start.setPiece(6, 4, new Piece(Color.WHITE, PieceKind.QUEEN));
		start.setPiece(5, 5, new Piece(Color.WHITE, PieceKind.BISHOP));
		start.setGameResult(new GameResult(Color.WHITE, GameResultReason.CHECKMATE));
		Set<Move> expectedMoves = new HashSet<Move>();
		Set<Move> actualMoves = stateExplorer.getPossibleMoves(start);
		assertEquals(expectedMoves, actualMoves);
	}
	
	@Test
	public void testNoPossibleMovesInStalemate() {
		startForSimon(Color.BLACK, false);
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(6, 5, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(5, 6, new Piece(Color.WHITE, PieceKind.QUEEN));
		start.setGameResult(new GameResult(null, GameResultReason.STALEMATE));
		Set<Move> expectedMoves = new HashSet<Move>();
		Set<Move> actualMoves = stateExplorer.getPossibleMoves(start);
		assertEquals(expectedMoves, actualMoves);
	}

	@Test
	public void testNoPossibleMovesAfterFiftyMoveRule() {
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		start.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));
		Set<Move> expectedMoves = new HashSet<Move>();
		Set<Move> actualMoves = new HashSet<Move>();
		assertEquals(expectedMoves, actualMoves);
	}

	@Test
	public void testOnlyLegalMovesOutOfCheck() {
		startForSimon(Color.BLACK, false);
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(1, 2, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(5, 2, new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.ROOK));
		Set<Move> expectedMoves = new HashSet<Move>();
		expectedMoves.add(new Move(new Position(5, 2), new Position(4, 1), null));
		expectedMoves.add(new Move(new Position(5, 2), new Position(5, 1), null));
		expectedMoves.add(new Move(new Position(5, 2), new Position(6, 1), null));
		expectedMoves.add(new Move(new Position(5, 2), new Position(4, 3), null));
		expectedMoves.add(new Move(new Position(5, 2), new Position(5, 3), null));
		expectedMoves.add(new Move(new Position(5, 2), new Position(6, 3), null));
		Set<Move> actualMoves = stateExplorer.getPossibleMoves(start);
		assertEquals(expectedMoves, actualMoves);
	}
	
	@Test
	public void testPawnMustBePromoted() {
		// change the turn to WHITE
		startForSimon(Color.WHITE, true);
		start.setPiece(6, 7, new Piece(Color.WHITE, PieceKind.PAWN));
		Set<Move> moves = stateExplorer.getPossibleMovesFromPosition(start, new Position(6, 7));
		Move shouldBeIllegal = new Move(new Position(6, 7), new Position(7, 7), null);
		assertFalse(moves.contains(shouldBeIllegal));
	}
	
	@Test
	public void testKingMayNotCastleWhenWayIsBlocked() {
		start.setPiece(0, 1, null);
		start.setPiece(0, 2, null);
		Set<Move> moves = stateExplorer.getPossibleMovesFromPosition(start, new Position(0, 4));
		Move shouldBeIllegal = new Move(new Position(0, 4), new Position(0, 2), null);
		assertFalse(moves.contains(shouldBeIllegal));
	}
	
	@Test
	public void testEnpassantCaptureIsLegalButNotRequired() {
		startForSimon(Color.BLACK, true);
		start.setPiece(3, 2, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(3, 1, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setEnpassantPosition(new Position(3, 2));
		Set<Move> moves = stateExplorer.getPossibleMovesFromPosition(start, new Position(3, 1));
		assertTrue(moves.contains(new Move(new Position(3, 1), new Position(2, 2), null)));
		assertTrue(moves.contains(new Move(new Position(3, 1), new Position(2, 1), null)));
	}
	
	@Test
	public void testPossibleMovesOfKing() {
		startForSimon(Color.BLACK, true);
		start.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.ROOK));
		Position startPosition = new Position(7, 4);
		Set<Move> expectedMoves = new HashSet<Move>();
		expectedMoves.add(new Move(startPosition, new Position(7, 2), null));
		expectedMoves.add(new Move(startPosition, new Position(7, 3), null));
		expectedMoves.add(new Move(startPosition, new Position(6, 3), null));
		expectedMoves.add(new Move(startPosition, new Position(6, 4), null));
		expectedMoves.add(new Move(startPosition, new Position(6, 5), null));
		expectedMoves.add(new Move(startPosition, new Position(7, 5), null));
		expectedMoves.add(new Move(startPosition, new Position(7, 6), null));
		Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
		assertEquals(expectedMoves, actualMoves);
	}
	
	@Test
	public void testPossibleMovesOfRook() {
		startForSimon(Color.BLACK, true);
		start.setPiece(7, 0, new Piece(Color.BLACK, PieceKind.ROOK));
		Position startPosition = new Position(7, 0);
		Set<Move> expectedMoves = new HashSet<Move>();
		for (int row = 6; row >= 0; --row) {
			expectedMoves.add(new Move(startPosition, new Position(row, 0), null));
		}
		for (int col = 1; col < 4; ++col) {
			expectedMoves.add(new Move(startPosition, new Position(7, col), null));
		}
		Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
		assertEquals(expectedMoves, actualMoves);
	}
	
	@Test
	public void testPossibleMovesOfKnight() {
		startForSimon(Color.BLACK, true);
		start.setPiece(3, 3, new Piece(Color.BLACK, PieceKind.KNIGHT));
		Position startPosition = new Position(3, 3);
		Set<Move> expectedMoves = new HashSet<Move>();
		expectedMoves.add(new Move(startPosition, new Position(1, 2), null));
		expectedMoves.add(new Move(startPosition, new Position(2, 1), null));
		expectedMoves.add(new Move(startPosition, new Position(5, 2), null));
		expectedMoves.add(new Move(startPosition, new Position(4, 1), null));
		expectedMoves.add(new Move(startPosition, new Position(1, 4), null));
		expectedMoves.add(new Move(startPosition, new Position(2, 5), null));
		expectedMoves.add(new Move(startPosition, new Position(5, 4), null));
		expectedMoves.add(new Move(startPosition, new Position(4, 5), null));
		Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
		assertEquals(expectedMoves, actualMoves);
	}
	
	/*
	 * End Tests by Simon Gellis <simongellis@gmail.com>
	 */
	
	
	
	/*
	 * Begin Tests by Alexander Oskotsky <alex.oskotsky@gmail.com>
	 */
	public void setInitialStateAlex() {
		for (int i = 0; i < State.ROWS; i++) {
			for (int j = 0; j < State.COLS; j++) {
				start.setPiece(i, j, null);
			}
		}
		start.setCanCastleKingSide(Color.WHITE, false);
		start.setCanCastleKingSide(Color.BLACK, false);
		start.setCanCastleQueenSide(Color.WHITE, false);
		start.setCanCastleQueenSide(Color.BLACK, false);

		start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(7, 7, new Piece(Color.BLACK, PieceKind.KING));
	}

	@Test
	public void getPossibleStartPositions_kingMovement() {
		setInitialStateAlex();

		Set<Position> expected = new HashSet<Position>();
		expected.add(new Position(0, 0));

		assertEquals(expected, stateExplorer.getPossibleStartPositions(start));
	}

	@Test
	public void getPossibleMoves_kingMovement() {
		setInitialStateAlex();

		Set<Move> expected = new HashSet<Move>();

		expected.add(new Move(new Position(0, 0), new Position(1, 0), null));
		expected.add(new Move(new Position(0, 0), new Position(1, 1), null));
		expected.add(new Move(new Position(0, 0), new Position(0, 1), null));

		assertEquals(expected, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void getPossibleMoves_knightMovement() {
		setInitialStateAlex();

		start.setPiece(4, 4, new Piece(Color.WHITE, PieceKind.KNIGHT));

		Set<Move> expected = new HashSet<Move>();

		// white king moves
		expected.add(new Move(new Position(0, 0), new Position(1, 0), null));
		expected.add(new Move(new Position(0, 0), new Position(1, 1), null));
		expected.add(new Move(new Position(0, 0), new Position(0, 1), null));

		// white knight moves
		expected.add(new Move(new Position(4, 4), new Position(5, 2), null));
		expected.add(new Move(new Position(4, 4), new Position(5, 6), null));
		expected.add(new Move(new Position(4, 4), new Position(6, 5), null));
		expected.add(new Move(new Position(4, 4), new Position(6, 3), null));

		expected.add(new Move(new Position(4, 4), new Position(2, 5), null));
		expected.add(new Move(new Position(4, 4), new Position(2, 3), null));
		expected.add(new Move(new Position(4, 4), new Position(3, 2), null));
		expected.add(new Move(new Position(4, 4), new Position(3, 6), null));

		assertEquals(expected, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void getPossibleMoves_enpassant() {
		setInitialStateAlex();

		start.setPiece(4, 0, new Piece(Color.BLACK, PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4, 0));

		start.setPiece(4, 1, new Piece(Color.WHITE, PieceKind.PAWN));

		Set<Move> expected = new HashSet<Move>();

		// white king moves
		expected.add(new Move(new Position(0, 0), new Position(1, 0), null));
		expected.add(new Move(new Position(0, 0), new Position(1, 1), null));
		expected.add(new Move(new Position(0, 0), new Position(0, 1), null));

		// white pawn moves
		expected.add(new Move(new Position(4, 1), new Position(5, 1), null));
		expected.add(new Move(new Position(4, 1), new Position(5, 0), null));

		assertEquals(expected, stateExplorer.getPossibleMoves(start));
	}

	@Test
	public void getMovesFromPosition_queenSideCastle() {
		setInitialStateAlex();

		start.setPiece(0, 0, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setCanCastleQueenSide(Color.WHITE, true);

		Set<Move> expected = new HashSet<Move>();

		expected.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expected.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expected.add(new Move(new Position(0, 4), new Position(0, 2), null));
		expected.add(new Move(new Position(0, 4), new Position(1, 4), null));
		expected.add(new Move(new Position(0, 4), new Position(1, 5), null));
		expected.add(new Move(new Position(0, 4), new Position(1, 3), null));
		
		assertEquals(expected, stateExplorer.getPossibleMovesFromPosition(start, new Position(0, 4)));
	}
	
	@Test
	public void getMovesFromPosition_kingSideCastle() {
		setInitialStateAlex();

		start.setPiece(0, 0, null);
		start.setPiece(0, 4, new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(0, 7, new Piece(Color.WHITE, PieceKind.ROOK));
		start.setCanCastleKingSide(Color.WHITE, true);

		Set<Move> expected = new HashSet<Move>();

		expected.add(new Move(new Position(0, 4), new Position(0, 5), null));
		expected.add(new Move(new Position(0, 4), new Position(0, 3), null));
		expected.add(new Move(new Position(0, 4), new Position(0, 6), null));
		expected.add(new Move(new Position(0, 4), new Position(1, 4), null));
		expected.add(new Move(new Position(0, 4), new Position(1, 5), null));
		expected.add(new Move(new Position(0, 4), new Position(1, 3), null));
		
		assertEquals(expected, stateExplorer.getPossibleMovesFromPosition(start, new Position(0, 4)));
	}
	
	@Test
	public void getMovesFromPosition_promotion() {
		setInitialStateAlex();

		start.setPiece(6, 0, new Piece(Color.WHITE, PieceKind.PAWN));

		Set<Move> expected = new HashSet<Move>();

		expected.add(new Move(new Position(6, 0), new Position(7, 0), PieceKind.KNIGHT));
		expected.add(new Move(new Position(6, 0), new Position(7, 0), PieceKind.ROOK));
		expected.add(new Move(new Position(6, 0), new Position(7, 0), PieceKind.QUEEN));
		expected.add(new Move(new Position(6, 0), new Position(7, 0), PieceKind.BISHOP));
		
		
		assertEquals(expected, stateExplorer.getPossibleMovesFromPosition(start, new Position(6, 0)));
	}
	
	@Test
	public void getPossibleMoves_kingInCheck() {
		setInitialStateAlex();

		start.setPiece(6, 1, new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(2, 0, new Piece(Color.BLACK, PieceKind.QUEEN));

		Set<Move> expected = new HashSet<Move>();

		expected.add(new Move(new Position(0, 0), new Position(0, 1), null));
		
		
		assertEquals(expected, stateExplorer.getPossibleMoves(start));
	}
	
	@Test
	public void getPossibleMoves_pawnCaptureRook() {
		setInitialStateAlex();

		start.setPiece(6, 2, new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(5, 1, new Piece(Color.WHITE, PieceKind.PAWN));

		Set<Move> expected = new HashSet<Move>();

		expected.add(new Move(new Position(5, 1), new Position(6, 1), null));
		expected.add(new Move(new Position(5, 1), new Position(6, 2), null));
		
		
		assertEquals(expected, stateExplorer.getPossibleMovesFromPosition(start, new Position(5, 1)));
	}
	
	@Test
	public void getMovesFromPosition_pawnMovement() {
		setInitialStateAlex();

		start.setPiece(1, 0, new Piece(Color.WHITE, PieceKind.PAWN));

		Set<Move> expected = new HashSet<Move>();

		expected.add(new Move(new Position(1, 0), new Position(2, 0), null));
		expected.add(new Move(new Position(1, 0), new Position(3, 0), null));
		
		
		assertEquals(expected, stateExplorer.getPossibleMovesFromPosition(start, new Position(1, 0)));
	}

	/*
	 * End Tests by Alexander Oskotsky <alex.oskotsky@gmail.com>
	 */
	
	/*
	 * Begin Tests by Zhaohui Zhang <bravezhaohui@gmail.com>
	 */
	@Test
	public void testGetPossibleStartPositions_BlackInitial() {
		State former = start.copy();
		former.setTurn(Color.BLACK);
		former.setPiece(1, 0, null);
		former.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.PAWN));
		Set<Position> expectedPositions = Sets.newHashSet();
		// pawn positions
		for (int c = 0; c < 8; c++)
			expectedPositions.add(new Position(6, c));
		// knight positions
		expectedPositions.add(new Position(7, 1));
		expectedPositions.add(new Position(7, 6));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(former));
	}

	@Test
	public void testGetPossibleStartPositions_WhiteNormal() {
		Piece[][] board = new Piece[8][8];
		board[0][3] = new Piece(WHITE, KING);
		board[0][7] = new Piece(WHITE, QUEEN);
		board[1][6] = new Piece(WHITE, BISHOP);
		board[7][4] = new Piece(BLACK, KING);
		State former = new State(WHITE, board, new boolean[] { false, false },
				new boolean[] { false, false }, null, 0, null);
		Set<Position> expectedPositions = Sets.newHashSet();
		expectedPositions.add(new Position(0, 3));
		expectedPositions.add(new Position(0, 7));
		expectedPositions.add(new Position(1, 6));
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(former));
	}

	@Test
	public void testGetPossibleStartPositions_GameIsOver() {
		Piece[][] board = new Piece[8][8];
		board[0][3] = new Piece(WHITE, KING);
		board[7][4] = new Piece(BLACK, KING);
		GameResult gameResult = new GameResult(null,
				GameResultReason.FIFTY_MOVE_RULE);
		State former = new State(WHITE, board, new boolean[] { false, false },
				new boolean[] { false, false }, null, 0, gameResult);
		Set<Position> expectedPositions = Sets.newHashSet();
		assertEquals(expectedPositions,
				stateExplorer.getPossibleStartPositions(former));
	}

	@Test
	public void testGetPossibleMovesFromPosition_InitStateForRightKnightAnother() {
		Set<Move> expectedMoves = Sets.newHashSet();
		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 6)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_NoPiece() {
		Set<Move> expectedMoves = Sets.newHashSet();
		Piece[][] board = new Piece[8][8];
		board[0][3] = new Piece(WHITE, KING);
		board[7][4] = new Piece(BLACK, KING);
		State former = new State(BLACK, board, new boolean[] { false, false },
				new boolean[] { false, false }, null, 0, null);
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(former,
				new Position(4, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPositions_CastlingKingside() {
		Piece[][] board = new Piece[8][8];
		board[0][3] = new Piece(WHITE, KING);
		board[7][4] = new Piece(BLACK, KING);
		board[7][0] = new Piece(BLACK, ROOK);
		board[7][7] = new Piece(BLACK, ROOK);
		State former = new State(BLACK, board, new boolean[] { false, true },
				new boolean[] { false, false }, null, 0, null);
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 3), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 4), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(6, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 4), new Position(7, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				former, new Position(7, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackLeftKnightInit() {
		State former = start.copy();
		former.setTurn(Color.BLACK);
		former.setPiece(1, 0, null);
		former.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 2), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				former, new Position(7, 1)));

	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteQueen() {
		Piece[][] board = new Piece[8][8];
		board[0][3] = new Piece(WHITE, KING);
		board[0][7] = new Piece(WHITE, QUEEN);
		board[1][6] = new Piece(BLACK, BISHOP);
		board[7][4] = new Piece(BLACK, KING);
		State former = new State(WHITE, board, new boolean[] { false, false },
				new boolean[] { false, false }, null, 0, null);
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(1, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(0, 6), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(0, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(0, 4), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(1, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(2, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(3, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(4, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(5, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(6, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 7), new Position(7, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				former, new Position(0, 7)));
	}

	@Test
	public void testGetPossibleMoves_BlackInit() {
		State former = start.copy();
		former.setTurn(Color.BLACK);
		former.setPiece(1, 0, null);
		former.setPiece(2, 0, new Piece(Color.WHITE, PieceKind.PAWN));

		Set<Move> expectedMoves = Sets.newHashSet();
		// pawn moves
		for (int c = 0; c < 8; c++) {
			expectedMoves.add(new Move(new Position(6, c), new Position(5, c),
					null));
			expectedMoves.add(new Move(new Position(6, c), new Position(4, c),
					null));
		}
		// knight moves
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 0), null));
		expectedMoves
				.add(new Move(new Position(7, 1), new Position(5, 2), null));
		expectedMoves
				.add(new Move(new Position(7, 6), new Position(5, 5), null));
		expectedMoves
				.add(new Move(new Position(7, 6), new Position(5, 7), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(former));
	}

	@Test
	public void testGetPossibleMovesPawnEnpassant() {
		State former = start.copy();
		former.setTurn(WHITE);
		former.setPiece(1, 5, null);
		former.setPiece(4, 5, new Piece(WHITE, PAWN));
		former.setPiece(6, 0, null);
		former.setPiece(5, 0, new Piece(BLACK, PAWN));
		former.setPiece(6, 6, null);
		former.setPiece(4, 6, new Piece(BLACK, PAWN));
		former.setEnpassantPosition(new Position(4, 6));

		Set<Move> expectedMoves = Sets.newHashSet();
		// pawn moves
		for (int c = 0; c < 8; c++) {
			if (c != 5) {
				expectedMoves.add(new Move(new Position(1, c), new Position(2,
						c), null));
				expectedMoves.add(new Move(new Position(1, c), new Position(3,
						c), null));
			}
		}
		expectedMoves
				.add(new Move(new Position(4, 5), new Position(5, 5), null));
		expectedMoves
				.add(new Move(new Position(4, 5), new Position(5, 6), null));

		// knight moves
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 0), null));
		expectedMoves
				.add(new Move(new Position(0, 1), new Position(2, 2), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(0, 6), new Position(2, 7), null));
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(1, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMoves(former));
	}

	/*
	 * End Tests by Zhaohui Zhang <bravezhaohui@gmail.com>
	 */
	

	  
	  /*
	   * Begin Tests by Corinne Taylor <corinnetaylor858@gmail.com>
	   */
	  
	  private void clearBoard(){
		  for (int rows = 0; rows < 8; rows++){
			  for (int cols = 0; cols < 8; cols++){
				  start.setPiece(rows, cols, null);
			  }
		  }
			start.setCanCastleKingSide(WHITE, false);
			start.setCanCastleKingSide(BLACK, false);
			start.setCanCastleQueenSide(WHITE, false);
			start.setCanCastleQueenSide(BLACK, false);

	  }
	  
	  private void setUpPawnPromotion(){
			start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
					PieceKind.KING));
			start.setPiece(new Position(7, 7), new Piece(Color.BLACK,
					PieceKind.KING));
			start.setPiece(new Position(6, 0), new Piece(Color.WHITE,
					PieceKind.PAWN));
			start.setPiece(new Position(1, 0), new Piece(Color.BLACK,
					PieceKind.PAWN));
	  }
	  
	  private void setUpCastling(){
			start.setPiece(new Position(0, 4), new Piece(Color.WHITE,
					PieceKind.KING));
			start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
					PieceKind.ROOK));
			start.setCanCastleKingSide(WHITE, true);
			start.setPiece(new Position(7, 4), new Piece(Color.BLACK,
					PieceKind.KING));
	  }
	  
	  @Test
	  public void testGetPossibleStartPositions_MissingWhitePawn() {
		  start.setPiece(1, 0, null);
	    Set<Position> expectedPositions = Sets.newHashSet();
	    //pawn positions
	    for (int i = 1; i < 8; i++)
	      expectedPositions.add(new Position(1, i));
	    //knight positions
	    expectedPositions.add(new Position(0, 1));
	    expectedPositions.add(new Position(0, 6));
	    //rook position
	    expectedPositions.add(new Position(0, 0));
	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleStartPositions(start));
	    }
	  
	  @Test
	  public void testGetPossibleMoves_MissingWhitePawn() {
		  start.setPiece(1, 0, null);
	    Set<Move> expectedMoves = Sets.newHashSet();
	    // pawn moves
	    for (int i = 1; i < 8; i++) {
	      expectedMoves.add(new Move(new Position(1, i), new Position(2, i), null));
	      expectedMoves.add(new Move(new Position(1, i), new Position(3, i), null));
	    }
	    // knight moves
	    expectedMoves.add(new Move(new Position(0, 1), new Position(2, 0), null));
	    expectedMoves.add(new Move(new Position(0, 1), new Position(2, 2), null));
	    expectedMoves.add(new Move(new Position(0, 6), new Position(2, 5), null));
	    expectedMoves.add(new Move(new Position(0, 6), new Position(2, 7), null));
	    //rook moves
	    for (int i = 1; i < 7; i++){
	    	expectedMoves.add(new Move(new Position(0, 0), new Position(i, 0), null));
	    }
	    assertEquals(expectedMoves,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleStartPositions_PawnPromotion() {
		  clearBoard();
		  setUpPawnPromotion();
	    Set<Position> expectedPositions = Sets.newHashSet();
	    //pawn positions
	    expectedPositions.add(new Position(6, 0));
	    //king positions
	    expectedPositions.add(new Position(0, 7));
	    
	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleStartPositions(start));
	    }
	  
	  @Test
	  public void testGetPossibleMoves_WhitePromotion() {
		  clearBoard();
		  setUpPawnPromotion();
	    Set<Move> expectedMoves = Sets.newHashSet();
	    // pawn moves
	    expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0), ROOK));
	    expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0), BISHOP));
	    expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0), KNIGHT));
	    expectedMoves.add(new Move(new Position(6, 0), new Position(7, 0), QUEEN));
	    // king moves
	    expectedMoves.add(new Move(new Position(0, 7), new Position(0, 6), null));
	    expectedMoves.add(new Move(new Position(0, 7), new Position(1, 7), null));
	    expectedMoves.add(new Move(new Position(0, 7), new Position(1, 6), null));
	    
	    assertEquals(expectedMoves,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleMoves_BlackPromotion() {
		  clearBoard();
		  setUpPawnPromotion();
		  start.setTurn(BLACK);
	    Set<Move> expectedMoves = Sets.newHashSet();
	    // pawn moves
	    expectedMoves.add(new Move(new Position(1, 0), new Position(0, 0), ROOK));
	    expectedMoves.add(new Move(new Position(1, 0), new Position(0, 0), BISHOP));
	    expectedMoves.add(new Move(new Position(1, 0), new Position(0, 0), KNIGHT));
	    expectedMoves.add(new Move(new Position(1, 0), new Position(0, 0), QUEEN));
	    // king moves
	    expectedMoves.add(new Move(new Position(7, 7), new Position(7, 6), null));
	    expectedMoves.add(new Move(new Position(7, 7), new Position(6, 7), null));
	    expectedMoves.add(new Move(new Position(7, 7), new Position(6, 6), null));
	    
	    assertEquals(expectedMoves,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleStartPositions_Castling() {
		  clearBoard();
		  setUpCastling();
	    Set<Position> expectedPositions = Sets.newHashSet();
	    //rook positions
	    expectedPositions.add(new Position(0, 7));
	    //king positions
	    expectedPositions.add(new Position(0, 4));
	    
	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleStartPositions(start));
	    }
	  
	  @Test
	  public void testGetPossibleMoves_WhiteCastleKingSide() {
		  clearBoard();
		  setUpCastling();
	    Set<Move> expectedMoves = Sets.newHashSet();
	    // Rook moves
	    for (int i = 5; i < 7; i++){
	    	expectedMoves.add(new Move(new Position(0, 7), new Position(0, i), null));
	    }
	    for (int i = 1; i < 8; i++){
	    	expectedMoves.add(new Move(new Position(0, 7), new Position(i, 7), null));
	    }
	    
	    // king moves
	    expectedMoves.add(new Move(new Position(0, 4), new Position(0, 3), null));
	    expectedMoves.add(new Move(new Position(0, 4), new Position(0, 5), null));
	    expectedMoves.add(new Move(new Position(0, 4), new Position(1, 3), null));
	    expectedMoves.add(new Move(new Position(0, 4), new Position(1, 4), null));
	    expectedMoves.add(new Move(new Position(0, 4), new Position(1, 5), null));
	    expectedMoves.add(new Move(new Position(0, 4), new Position(0, 6), null));
	    
	    
	    assertEquals(expectedMoves,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleMoves_WhiteBishop() {
		  clearBoard();
		  start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
					PieceKind.BISHOP));
		  start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
					PieceKind.KING));
		  start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
					PieceKind.KING));
	    Set<Move> expectedMoves = Sets.newHashSet();
	    // Bishop moves
	    for (int i = 1; i < 8; i++){
	    	expectedMoves.add(new Move(new Position(0, 0), new Position(i, i), null));
	    }

	    // king moves
	    expectedMoves.add(new Move(new Position(0, 7), new Position(0, 6), null));
	    expectedMoves.add(new Move(new Position(0, 7), new Position(1, 7), null));
	    expectedMoves.add(new Move(new Position(0, 7), new Position(1, 6), null));
	    
	    
	    assertEquals(expectedMoves,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleMoves_WhiteKnight() {
		  clearBoard();
		  start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
					PieceKind.KNIGHT));
		  start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
					PieceKind.KING));
		  start.setPiece(new Position(7, 0), new Piece(Color.BLACK,
					PieceKind.KING));
	    Set<Move> expectedMoves = Sets.newHashSet();
	    // Knight moves
	    expectedMoves.add(new Move(new Position(0, 0), new Position(2, 1), null));
	    expectedMoves.add(new Move(new Position(0, 0), new Position(1, 2), null));

	    // king moves
	    expectedMoves.add(new Move(new Position(0, 7), new Position(0, 6), null));
	    expectedMoves.add(new Move(new Position(0, 7), new Position(1, 7), null));
	    expectedMoves.add(new Move(new Position(0, 7), new Position(1, 6), null));
	    
	    
	    assertEquals(expectedMoves,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleMoves_WhiteQueen() {
		  clearBoard();
		  start.setPiece(new Position(0, 0), new Piece(Color.WHITE,
					PieceKind.QUEEN));
		  start.setPiece(new Position(0, 7), new Piece(Color.WHITE,
					PieceKind.KING));
		  start.setPiece(new Position(7, 1), new Piece(Color.BLACK,
					PieceKind.KING));
	    Set<Move> expectedMoves = Sets.newHashSet();
	    // Queen moves
	    for (int i = 1; i < 8; i++){
	    	expectedMoves.add(new Move(new Position(0, 0), new Position(i, 0), null));
	    	expectedMoves.add(new Move(new Position(0, 0), new Position(i, i), null));
	    }
	    for (int i = 1; i < 7; i++){
	    	expectedMoves.add(new Move(new Position(0, 0), new Position(0, i), null));
	    }
	    // king moves
	    expectedMoves.add(new Move(new Position(0, 7), new Position(0, 6), null));
	    expectedMoves.add(new Move(new Position(0, 7), new Position(1, 7), null));
	    expectedMoves.add(new Move(new Position(0, 7), new Position(1, 6), null));
	    
	    
	    assertEquals(expectedMoves,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  /*
	   * end tests by Corinne Taylor <corinnetaylor858@gmail.com>
	   */
	
		/*
		 * Begin tests by Sanjana Agarwal <coolbarbie2004@gmail.com>
		 * 
		 */
		

		void initforsanjana()
		{
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


		@Test
		public void testMovesForKnight()
		{
			initforsanjana();
			start.setPiece(2,2, new Piece(WHITE,KNIGHT));
			Position startPosition = new Position(2,2);
			Set<Move> expectedMoves = new HashSet<Move>();
			expectedMoves.add(new Move(startPosition, new Position(3,4), null));
			expectedMoves.add(new Move(startPosition, new Position(3,0), null));
			expectedMoves.add(new Move(startPosition, new Position(1,4), null));
			expectedMoves.add(new Move(startPosition, new Position(1,0), null));
			expectedMoves.add(new Move(startPosition, new Position(4,3), null));
			expectedMoves.add(new Move(startPosition, new Position(4,1), null));
			expectedMoves.add(new Move(startPosition, new Position(0,3), null));
			expectedMoves.add(new Move(startPosition, new Position(0,1), null));
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}
		
		@Test
		public void testMovesForRook()
		{
			initforsanjana();
			start.setPiece(4,5, new Piece(WHITE,ROOK));
			Position startPosition = new Position(4,5);
			Set<Move> expectedMoves = new HashSet<Move>();
			for(int i=0;i<5;i++)
				expectedMoves.add(new Move(startPosition, new Position(4,i), null));
			
			for(int i=6;i<8;i++)
				expectedMoves.add(new Move(startPosition, new Position(4,i), null));
			
			for(int i=0;i<4;i++)
				expectedMoves.add(new Move(startPosition, new Position(i,5), null));
			
			for(int i=5;i<8;i++)
				expectedMoves.add(new Move(startPosition, new Position(i,5), null));
			
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}

		@Test
		public void testMovesForBishop()
		{
			initforsanjana();
			start.setPiece(0,0, new Piece(WHITE,BISHOP));
			Position startPosition = new Position(0,0);
			Set<Move> expectedMoves = new HashSet<Move>();
			for(int i=1;i<8;i++)
				expectedMoves.add(new Move(startPosition, new Position(0+i,0+i), null));
				
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}
		
		@Test
		public void testMovesForQueen()
		{
			initforsanjana();
			start.setPiece(3,3, new Piece(WHITE,QUEEN));
			Position startPosition = new Position(3,3);
			Set<Move> expectedMoves = new HashSet<Move>();
			for(int i=0;i<3;i++)
				expectedMoves.add(new Move(startPosition, new Position(3,i), null));
			
			for(int i=4;i<8;i++)
				expectedMoves.add(new Move(startPosition, new Position(3,i), null));
			
			for(int i=0;i<3;i++)
				expectedMoves.add(new Move(startPosition, new Position(i,3), null));
			
			for(int i=4;i<8;i++)
				expectedMoves.add(new Move(startPosition, new Position(i,3), null));
			
			for(int i=1;i<5;i++)
				expectedMoves.add(new Move(startPosition, new Position(3+i,3+i), null));
			
			for(int i=1;i<4;i++)
				expectedMoves.add(new Move(startPosition, new Position(3-i,3-i), null));
			
			for(int i=1;i<4;i++)
				expectedMoves.add(new Move(startPosition, new Position(3-i,3+i), null));
			
			for(int i=1;i<4;i++)
				expectedMoves.add(new Move(startPosition, new Position(3+i,3-i), null));
				
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}
		
		@Test
		public void testAfter50Rule()
		{
			initforsanjana();
			start.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
			start.setGameResult(new GameResult(null, GameResultReason.FIFTY_MOVE_RULE));
			Set<Move> expectedMoves = new HashSet<Move>();
			Set<Move> actualMoves = new HashSet<Move>();
			assertEquals(expectedMoves, actualMoves);
		}
		
		@Test
		public void testAfterCheckmate()
		{
			initforsanjana();
			start.setGameResult(new GameResult(null, GameResultReason.CHECKMATE));
			Set<Move> expectedMoves = new HashSet<Move>();
			Set<Move> actualMoves = new HashSet<Move>();
			assertEquals(expectedMoves, actualMoves);
		}
		
		@Test
		public void testMovesForKingCannotCastle()
		{
			initforsanjana();
			Position startPosition = new Position(0,4);
			Set<Move> expectedMoves = new HashSet<Move>();

			start.setPiece(1, 6, new Piece(BLACK, ROOK));
			start.setPiece(1, 3, new Piece(WHITE, PAWN));
			start.setPiece(6, 2, new Piece(BLACK, QUEEN));
			
			expectedMoves.add(new Move(startPosition, new Position(0, 3), null));
			expectedMoves.add(new Move(startPosition, new Position(0, 5), null));
			
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}	
		
		@Test
		public void testIllegalNOPromotionMoveForPawn()
		{
			initforsanjana();
			start.setPiece(6,3, new Piece(WHITE,PieceKind.PAWN));
			Position startPosition = new Position(6,3);
			Set<Move> expectedMoves = new HashSet<Move>();

			expectedMoves.add(new Move(startPosition, new Position(7, 3), null));
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertFalse(expectedMoves.equals(actualMoves));
		}	
		
		@Test
		public void testMovesForPawnPromotion()
		{
			initforsanjana();
			start.setPiece(6,0, new Piece(WHITE,PAWN));
			Position startPosition = new Position(6,0);
			Set<Move> expectedMoves = new HashSet<Move>();

			expectedMoves.add(new Move(startPosition, new Position(7, 0), ROOK));
			expectedMoves.add(new Move(startPosition, new Position(7, 0), KNIGHT));
			expectedMoves.add(new Move(startPosition, new Position(7, 0), BISHOP));
			expectedMoves.add(new Move(startPosition, new Position(7, 0), QUEEN));	

			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}
		
		@Test
		public void testMovesForKing()
		{
			initforsanjana();
			start.setPiece(0,4,null);
			start.setPiece(2,3,new Piece(WHITE,KING));
			Position startPosition = new Position(2, 3);
			Set<Move> expectedMoves = new HashSet<Move>();

			expectedMoves.add(new Move(startPosition, new Position(2, 4), null));
			expectedMoves.add(new Move(startPosition, new Position(2, 2), null));
			expectedMoves.add(new Move(startPosition, new Position(1, 2), null));
			expectedMoves.add(new Move(startPosition, new Position(1, 3), null));
			expectedMoves.add(new Move(startPosition, new Position(1, 4), null));
			expectedMoves.add(new Move(startPosition, new Position(3, 2), null));
			expectedMoves.add(new Move(startPosition, new Position(3, 3), null));
			expectedMoves.add(new Move(startPosition, new Position(3, 4), null));

			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}
		
		@Test
		public void testIllegalNOCastling()
		{
			initforsanjana();
			start.setPiece(4,5, new Piece(BLACK,QUEEN));
			start.setPiece(4,2, new Piece(BLACK,BISHOP));
			Position startPosition = new Position(0,4);
			Set<Move> expectedMoves = new HashSet<Move>();

			expectedMoves.add(new Move(startPosition, new Position(0,6), null));
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertFalse(expectedMoves.equals(actualMoves));
		}	
		
		@Test
		public void testMovesForPawnEnpassant()
		{
			initforsanjana();
			start.setPiece(1,3, new Piece(WHITE,PAWN));
			Position startPosition = new Position(1,3);
			Set<Move> expectedMoves = new HashSet<Move>();
			
			expectedMoves.add(new Move(startPosition, new Position(3,3),null));
			expectedMoves.add(new Move(startPosition, new Position(2,3),null));
			
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}
		
		@Test
		public void testMovesForPawnCannotEnpassant()
		{
			initforsanjana();
			start.setPiece(1,3, new Piece(WHITE,PAWN));
			start.setPiece(3,3, new Piece(BLACK,PAWN));
			start.setPiece(2,4, new Piece(BLACK,PAWN));
			start.setPiece(2,2, new Piece(BLACK,PAWN));
			Position startPosition = new Position(1,3);
			Set<Move> expectedMoves = new HashSet<Move>();
			
			expectedMoves.add(new Move(startPosition, new Position(2,2),null));
			expectedMoves.add(new Move(startPosition, new Position(2,3),null));
			expectedMoves.add(new Move(startPosition, new Position(2,4),null));
			
			Set<Move> actualMoves = stateExplorer.getPossibleMovesFromPosition(start, startPosition);
			assertEquals(expectedMoves, actualMoves);
		}
		
		/*
		 * End tests by Sanjana Agarwal <coolbarbie2004@gmail.com>
		 * 
		 */
		
		/*
		 * Begin tests by Karthik Mahadevan <karthikjey@gmail.com>
		 */
		public void initKarthik() {
			for (int row = 0; row < 8; ++row) {
				for (int col = 0; col < 8; ++col) {
					start.setPiece(new Position(row, col), null);
				}
			}
		}
		
		@Test
		public void testStartingPosition_KingUnderAttack_KM() {
			initKarthik();
			start.setPiece(new Position(2,2), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(3,0), new Piece(Color.BLACK, PieceKind.BISHOP));
			start.setPiece(new Position(4,0), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(5,0), new Piece(Color.BLACK, PieceKind.BISHOP));
			start.setPiece(new Position(1,1), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(6,5), new Piece(Color.WHITE, PieceKind.ROOK));
			start.setPiece(new Position(7,4), new Piece(Color.BLACK, PieceKind.KING));
			
			Set<Position> expectedPositions = Sets.newHashSet();
			expectedPositions.add(new Position(1,1));
			expectedPositions.add(new Position(2,2));
			assertEquals(expectedPositions,
					stateExplorer.getPossibleStartPositions(start));
		}
		
		@Test
		public void testPossibleMovesFromPosition_KingCanCastle_KM() {
			initKarthik();
			start.setPiece(new Position(0,4), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(0,0), new Piece(Color.WHITE, PieceKind.ROOK));
			start.setPiece(new Position(6,3), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(6,5), new Piece(Color.BLACK, PieceKind.ROOK));
			start.setPiece(new Position(1,7), new Piece(Color.BLACK, PieceKind.ROOK));
			start.setPiece(new Position(7,4), new Piece(Color.BLACK, PieceKind.KING));
			start.setPiece(new Position(1,3), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(2,3), new Piece(Color.BLACK, PieceKind.PAWN));
			
			Set<Move> expectedMoves = Sets.newHashSet();
			expectedMoves.add(new Move(new Position(0,4),new Position(0,3),null));
			expectedMoves.add(new Move(new Position(0,4),new Position(0,2),null));
			assertEquals(expectedMoves,
					stateExplorer.getPossibleMovesFromPosition(start,new Position(0,4)));
		}
		
		@Test
		public void testStartingPosition_KingCannotMove_KM() {
			initKarthik();
			start.setPiece(new Position(0,0), new Piece(Color.WHITE, PieceKind.ROOK));
			start.setPiece(new Position(0,4), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(6,3), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(6,5), new Piece(Color.BLACK, PieceKind.ROOK));
			start.setPiece(new Position(7,4), new Piece(Color.BLACK, PieceKind.KING));
			start.setPiece(new Position(1,7), new Piece(Color.BLACK, PieceKind.ROOK));
			//manage the castle boolean
			start.setCanCastleKingSide(WHITE, false);
			start.setCanCastleQueenSide(WHITE, false);
			Set<Position> expectedPositions = Sets.newHashSet();
			expectedPositions.add(new Position(0,0));
			assertEquals(expectedPositions,
					stateExplorer.getPossibleStartPositions(start));
		}
		
		@Test
		public void testPossibleMovesFromPosition_QueenMovements_KM() {
			initKarthik();
			start.setPiece(new Position(7,4), new Piece(Color.BLACK, PieceKind.KING));
			start.setPiece(new Position(7,5), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(5,5), new Piece(Color.WHITE, PieceKind.KNIGHT));
			start.setPiece(new Position(4,7), new Piece(Color.BLACK, PieceKind.PAWN));
			start.setPiece(new Position(4,4), new Piece(Color.WHITE, PieceKind.QUEEN));
			start.setPiece(new Position(4,3), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(0,4), new Piece(Color.WHITE, PieceKind.KING));
			
			Set<Move> expectedMoves = Sets.newHashSet();
			expectedMoves.add(new Move(new Position(4,4),new Position(5,3),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(6,2),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(7,1),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(5,4),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(6,4),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(7,4),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(4,5),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(4,6),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(4,7),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(3,4),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(2,4),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(1,4),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(3,3),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(2,2),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(1,1),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(0,0),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(3,5),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(2,6),null));
			expectedMoves.add(new Move(new Position(4,4),new Position(1,7),null));
			
			assertEquals(expectedMoves,
					stateExplorer.getPossibleMovesFromPosition(start,new Position(4,4)));
		}
		
		@Test
		public void testPossibleMovesFromPosition_QueenMovesCauseCheck_KM() {
			initKarthik();
			start.setPiece(new Position(0,4), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(4,0), new Piece(Color.BLACK, PieceKind.BISHOP));
			start.setPiece(new Position(2,2), new Piece(Color.WHITE, PieceKind.QUEEN));
			start.setPiece(new Position(7,2), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(7,3), new Piece(Color.BLACK, PieceKind.KING));
			
			
			Set<Move> expectedMoves = Sets.newHashSet();
			expectedMoves.add(new Move(new Position(2,2),new Position(3,1),null));
			expectedMoves.add(new Move(new Position(2,2),new Position(4,0),null));
			expectedMoves.add(new Move(new Position(2,2),new Position(1,3),null));
			assertEquals(expectedMoves,
					stateExplorer.getPossibleMovesFromPosition(start,new Position(2,2)));
		}
		
		@Test
		public void testPossibleMovesFromPosition_KingMovements_KM() {
			initKarthik();
			start.setPiece(new Position(0,4), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(0,0), new Piece(Color.WHITE, PieceKind.ROOK));
			start.setPiece(new Position(0,7), new Piece(Color.WHITE, PieceKind.ROOK));
			start.setPiece(new Position(1,2), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(1,3), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(1,5), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(1,6), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(7,3), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(7,3), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(7,4), new Piece(Color.BLACK, PieceKind.KING));
			
			start.setCanCastleKingSide(Color.WHITE, false);
			
			Set<Move> expectedMoves = Sets.newHashSet();
			expectedMoves.add(new Move(new Position(0,4),new Position(1,4),null));
			expectedMoves.add(new Move(new Position(0,4),new Position(0,5),null));
			expectedMoves.add(new Move(new Position(0,4),new Position(0,3),null));
			expectedMoves.add(new Move(new Position(0,4),new Position(0,2),null));
			assertEquals(expectedMoves,
					stateExplorer.getPossibleMovesFromPosition(start,new Position(0,4)));
		}
		
		@Test
		public void testAllPossibleMoves_KingInCheck_KM() {
			initKarthik();
			start.setPiece(new Position(0,0), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(2,0), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(7,1), new Piece(Color.BLACK, PieceKind.ROOK));
			start.setPiece(new Position(4,3), new Piece(Color.WHITE, PieceKind.BISHOP));
			start.setPiece(new Position(7,3), new Piece(Color.BLACK, PieceKind.KING));
			start.setPiece(new Position(0,3), new Piece(Color.WHITE, PieceKind.ROOK));
			
			Set<Move> expectedMoves = Sets.newHashSet();
			expectedMoves.add(new Move(new Position(4,3),new Position(1,0),null));
			assertEquals(expectedMoves,
					stateExplorer.getPossibleMoves(start));
		}
		
		@Test
		public void testAllPossibleMoves_KingInCheckCanMove_KM() {
			initKarthik();
			start.setPiece(new Position(0,0), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(3,0), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(3,1), new Piece(Color.WHITE, PieceKind.KNIGHT));
			start.setPiece(new Position(0,3), new Piece(Color.WHITE, PieceKind.BISHOP));
			start.setPiece(new Position(7,3), new Piece(Color.BLACK, PieceKind.KING));
			
			Set<Move> expectedMoves = Sets.newHashSet();
			expectedMoves.add(new Move(new Position(0,0),new Position(1,1),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(0,1),null));
			expectedMoves.add(new Move(new Position(0,3),new Position(3,0),null));
			expectedMoves.add(new Move(new Position(3,1),new Position(1,0),null));
			assertEquals(expectedMoves,
					stateExplorer.getPossibleMoves(start));
		}
		
		@Test
		public void testAllPossibleMoves_KingCannotCastle_KM() {
			initKarthik();
			start.setPiece(new Position(0,4), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(0,0), new Piece(Color.WHITE, PieceKind.ROOK));
			start.setPiece(new Position(4,1), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(7,4), new Piece(Color.BLACK, PieceKind.KING));
			start.setPiece(new Position(4,4), new Piece(Color.BLACK, PieceKind.PAWN));
			start.setPiece(new Position(4,3), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(4,5), new Piece(Color.WHITE, PieceKind.PAWN));
			
			start.setEnpassantPosition(new Position(4,4));
			start.setCanCastleKingSide(Color.WHITE, false);
			
			Set<Move> expectedMoves = Sets.newHashSet();
			expectedMoves.add(new Move(new Position(4,3),new Position(5,4),null));
			expectedMoves.add(new Move(new Position(4,5),new Position(5,4),null));
			expectedMoves.add(new Move(new Position(4,3),new Position(5,3),null));
			expectedMoves.add(new Move(new Position(4,5),new Position(5,5),null));
			expectedMoves.add(new Move(new Position(0,4),new Position(1,3),null));
			expectedMoves.add(new Move(new Position(0,4),new Position(1,5),null));
			expectedMoves.add(new Move(new Position(0,4),new Position(0,2),null));
			expectedMoves.add(new Move(new Position(0,4),new Position(0,3),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(0,1),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(0,2),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(0,3),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(1,0),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(2,0),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(3,0),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(4,0),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(5,0),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(6,0),null));
			expectedMoves.add(new Move(new Position(0,0),new Position(7,0),null));
			assertEquals(expectedMoves,
					stateExplorer.getPossibleMoves(start));
		}
		
		@Test
		public void testPossibleMovesFromPosition_PawnCannotCapture_KM() {
			initKarthik();
			start.setPiece(new Position(0,4), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(7,5), new Piece(Color.BLACK, PieceKind.KING));
			start.setPiece(new Position(7,4), new Piece(Color.BLACK, PieceKind.QUEEN));
			start.setPiece(new Position(4,4), new Piece(Color.WHITE, PieceKind.PAWN));
			start.setPiece(new Position(4,5), new Piece(Color.BLACK, PieceKind.PAWN));
			start.setPiece(new Position(5,3), new Piece(Color.BLACK, PieceKind.PAWN));
			
			start.setEnpassantPosition(new Position(4,5));
			start.setCanCastleKingSide(Color.WHITE, false);
			
			Set<Move> expectedMoves = Sets.newHashSet();
			expectedMoves.add(new Move(new Position(4,4),new Position(5,4),null));
			
			assertEquals(expectedMoves,
					stateExplorer.getPossibleMovesFromPosition(start,new Position(4,4)));
		}
		
		
		/*
		 * End tests by karthik Mahadevan <karthikjey@gmail.com>
		 */
		
		//Start Tests Paul Sultan

		  
		  void clearBoard_PS() {
			  for (int col = 0; col < 8; ++col) {
					for (int row = 0; row < 8; ++row) {
						start.setPiece(row, col, null);
					}
				}
		  }
		  
		  @Test
		  public void testGetPossibleMovesForKings_PS(){
		     Set<Move> expectedMoves = Sets.newHashSet();
		     clearBoard_PS();
		     
		     start.setPiece(new Position(0, 4), new Piece(Color.WHITE, PieceKind.KING));
			 start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
			 start.setCanCastleKingSide(WHITE, false);
			 start.setCanCastleQueenSide(WHITE, false);
		     
			 expectedMoves.add(new Move(new Position(0,4),new Position(0,3),null));
			 expectedMoves.add(new Move(new Position(0,4),new Position(1,3),null));
			 expectedMoves.add(new Move(new Position(0,4),new Position(1,4),null));
			 expectedMoves.add(new Move(new Position(0,4),new Position(1,5),null));
			 expectedMoves.add(new Move(new Position(0,4),new Position(0,5),null));
			 
			 assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			 
		  }
		  
		  @Test
		  public void testGetPossibleMovesFromPositionWhiteKnight_PS(){
			  Set<Move> expectedMoves = Sets.newHashSet();
			  
			  expectedMoves.add(new Move(new Position(0,6),new Position(2,5),null));
			  expectedMoves.add(new Move(new Position(0,6),new Position(2,7),null));
			  
			  assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(start, new Position(0,6)));
		  }

		  @Test
		  public void testGetPossibleMovesFromPositionWhiteKingCanCastle_PS(){
			  Set<Move> expectedMoves = Sets.newHashSet();
			  clearBoard_PS();
			  
			  start.setPiece(new Position(0, 4), new Piece(Color.WHITE, PieceKind.KING));
			  start.setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceKind.ROOK));
			  start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
			  start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.ROOK));
			  
			  start.setCanCastleKingSide(WHITE, true);
			  start.setCanCastleQueenSide(WHITE, true);
			  
			  expectedMoves.add(new Move(new Position(0,4),new Position(0,3),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(1,3),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(1,4),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(1,5),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(0,5),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(0,2),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(0,6),null));
			  
			  assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(start, new Position(0,4)));
		  }
		  
		  @Test
		  public void testGetPossibleMovesFromPositionWhiteKingCannotCastle_PS(){
			  Set<Move> expectedMoves = Sets.newHashSet();
			  clearBoard_PS();
			  
			  start.setPiece(new Position(0, 4), new Piece(Color.WHITE, PieceKind.KING));
			  start.setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceKind.ROOK));
			  start.setPiece(new Position(0, 1), new Piece(Color.WHITE, PieceKind.KNIGHT));
			  start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
			  start.setCanCastleKingSide(WHITE, false);
			  start.setCanCastleQueenSide(WHITE, false);
			  
			  expectedMoves.add(new Move(new Position(0,4),new Position(0,3),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(1,3),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(1,4),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(1,5),null));
			  expectedMoves.add(new Move(new Position(0,4),new Position(0,5),null));
			  
			  assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(start, new Position(0,4)));
		  }
		  
		  @Test
		  public void testGetPossibleMovesWhiteKingCheckMate_PS() {
			  Set<Move> expectedMoves = Sets.newHashSet();

			  clearBoard_PS();
			  start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.KING));
			  start.setPiece(new Position(2, 4), new Piece(Color.BLACK, PieceKind.BISHOP));
			  start.setPiece(new Position(2, 5), new Piece(Color.BLACK, PieceKind.BISHOP));
			  start.setPiece(new Position(2, 7), new Piece(Color.BLACK, PieceKind.KING));
				
			  assertEquals(expectedMoves,stateExplorer.getPossibleMoves(start));
		}

		  @Test
		  public void testGetPossibleMovesWithCheck_PS(){
		     Set<Move> expectedMoves = Sets.newHashSet();
		     clearBoard_PS();
		     
		     start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.KING));
			 start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
			 start.setPiece(new Position(2, 5), new Piece(Color.BLACK, PieceKind.PAWN));
		     
			 expectedMoves.add(new Move(new Position(0,7),new Position(1,7),null));
			 expectedMoves.add(new Move(new Position(0,7),new Position(0,6),null));
			 
			 assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
		  }
		  
		  @Test
		  public void testGetPossibleMovesFromPositionPawnCanCapture_PS(){
		     Set<Move> expectedMoves = Sets.newHashSet();
		     clearBoard_PS();
		     
		     start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.KING));
			 start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
			 start.setPiece(new Position(3, 2), new Piece(Color.WHITE, PieceKind.PAWN));
			 start.setPiece(new Position(4, 3), new Piece(Color.BLACK, PieceKind.PAWN));
		     
			 expectedMoves.add(new Move(new Position(3,2),new Position(4,2),null));
			 expectedMoves.add(new Move(new Position(3,2),new Position(4,3),null));
			 
			  assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(start, new Position(3,2)));
		  }
		 
		  @Test
		  public void testGetPossibleMovesFromPositionKnight_PS(){
		     Set<Move> expectedMoves = Sets.newHashSet();
		     clearBoard();
		     
		     start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.KING));
			 start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
			 start.setPiece(new Position(3, 5), new Piece(Color.WHITE, PieceKind.KNIGHT));
		     
			 expectedMoves.add(new Move(new Position(3,5),new Position(5,4),null));
			 expectedMoves.add(new Move(new Position(3,5),new Position(5,6),null));
			 expectedMoves.add(new Move(new Position(3,5),new Position(4,3),null));
			 expectedMoves.add(new Move(new Position(3,5),new Position(4,7),null));
			 expectedMoves.add(new Move(new Position(3,5),new Position(2,3),null));
			 expectedMoves.add(new Move(new Position(3,5),new Position(2,7),null));
			 expectedMoves.add(new Move(new Position(3,5),new Position(1,4),null));
			 expectedMoves.add(new Move(new Position(3,5),new Position(1,6),null));
			 
			 assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(start, new Position(3,5)));
		  }
		  
		  @Test
		  public void testGetPossibleMovesFromPositionPawnPromotion_PS() {
			Set<Move> expectedMoves = Sets.newHashSet();
			clearBoard_PS();

			start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.KING));
			start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
			start.setPiece(new Position(7, 1), null);
			start.setPiece(new Position(7, 2), new Piece(Color.WHITE, PieceKind.BISHOP));
		    start.setPiece(new Position(6, 1), new Piece(Color.WHITE, PieceKind.PAWN));
		    
		    expectedMoves.add(new Move(new Position(6, 1), new Position(7, 1), PieceKind.BISHOP));
		    expectedMoves.add(new Move(new Position(6, 1), new Position(7, 1), PieceKind.KNIGHT));
		    expectedMoves.add(new Move(new Position(6, 1), new Position(7, 1), PieceKind.ROOK));
		    expectedMoves.add(new Move(new Position(6, 1), new Position(7, 1), PieceKind.QUEEN));
		    
		    assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(start, new Position(6, 1)));
		  }

		  @Test
		  public void testGetPossibleStartPositionsAlmostStaleMate_PS() {
		     Set<Position> expectedPositions = Sets.newHashSet();
		     clearBoard_PS();
		     
		     start.setPiece(new Position(0, 6), new Piece(Color.WHITE, PieceKind.KING));
		     start.setPiece(new Position(1, 0), new Piece(Color.WHITE, PieceKind.PAWN));
		     start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
			 start.setPiece(new Position(2, 5), new Piece(Color.BLACK, PieceKind.ROOK));
			 start.setPiece(new Position(2, 7), new Piece(Color.BLACK, PieceKind.ROOK));
			 start.setPiece(new Position(1, 3), new Piece(Color.BLACK, PieceKind.ROOK));
		     
			 expectedPositions.add(new Position(1, 0));
			 
			 assertEquals(expectedPositions, stateExplorer.getPossibleStartPositions(start));
		  }

		  @Test
		  public void testGetPossibleMovesFromPawnStart_PS() {
			Set<Move> expectedMoves = Sets.newHashSet();

		    expectedMoves.add(new Move(new Position(1, 5), new Position(2, 5), null));
		    expectedMoves.add(new Move(new Position(1, 5), new Position(3, 5), null));
		    
		    assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(start, new Position(1, 5)));
		  }

		  
		  
	
		//End Tests Paul Sultan

		  
			

			/*
			 * Start Tests by Sahil Vora <vora.sahil@gmail.com>
			 */
			@Test
			public void testGetPossibleMovesAfterStaleMate() {
				Piece[][] board = new Piece[8][8];
				board[7][7] = new Piece(Color.BLACK, PieceKind.KING);
				board[6][5] = new Piece(Color.WHITE, PieceKind.KING);
				board[5][6] = new Piece(Color.WHITE, PieceKind.QUEEN);
				start = new State(Color.BLACK, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, new GameResult(null,
								GameResultReason.STALEMATE));
				Set<Move> expectedMoves = Sets.newHashSet(); // Since it is a stalemate,
																// no moves should be
																// possible

				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			@Test
			public void testGetPossibleMovesFromEmptyPositions() {
				Set<Position> expectedPositions = Sets.newHashSet();
				assertEquals(expectedPositions,
						stateExplorer.getPossibleMovesFromPosition(start, new Position(
								4, 4)));
			}

			@Test
			public void testGetPossibleStartPositions_PawnRemovedFromFrontOfKing() {
				Set<Position> expectedPositions = Sets.newHashSet();
				start.setPiece(new Position(1, 4), null);
				// pawn positions
				for (int c = 0; c < 8; c++) {
					if (c != 4) {
						expectedPositions.add(new Position(1, c));
					}
				}// knight positions
				expectedPositions.add(new Position(0, 1));
				expectedPositions.add(new Position(0, 6));
				expectedPositions.add(new Position(0, 4));
				expectedPositions.add(new Position(0, 3));
				expectedPositions.add(new Position(0, 5));
				assertEquals(expectedPositions,
						stateExplorer.getPossibleStartPositions(start));
			}

			@Test
			public void testGetPossibleStartPositions_KingSideKnighAndBishopRemoved() {
				Set<Position> expectedPositions = Sets.newHashSet();
				start.setPiece(new Position(0, 5), null);
				start.setPiece(new Position(0, 6), null);
				// pawn positions
				for (int c = 0; c < 8; c++) {
					expectedPositions.add(new Position(1, c));
				}// knight positions
				expectedPositions.add(new Position(0, 1));
				expectedPositions.add(new Position(0, 4));
				expectedPositions.add(new Position(0, 7));
				assertEquals(expectedPositions,
						stateExplorer.getPossibleStartPositions(start));
			}

			@Test
			public void testGetPossibleMovesFromPosition_KingSideCastle() {
				start.setPiece(new Position(0, 5), null);
				start.setPiece(new Position(0, 6), null);
				Set<Move> expectedMoves = Sets.newHashSet();
				// promotion moves
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(0, 5), null));
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(0, 6), null));
				assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
						start, new Position(0, 4)));
			}

			@Test
			public void testGetPossibleMovesFromPosition_EnPassantPawnMove() {
				start.setPiece(new Position(1, 0), null);
				start.setPiece(new Position(4, 2), new Piece(WHITE, PAWN));
				start.setPiece(new Position(6, 3), null);
				start.setPiece(new Position(4, 3), new Piece(BLACK, PAWN));
				start.setEnpassantPosition(new Position(4, 3));
				Set<Move> expectedMoves = Sets.newHashSet();
				// promotion moves
				expectedMoves
						.add(new Move(new Position(4, 2), new Position(5, 3), null));
				expectedMoves
						.add(new Move(new Position(4, 2), new Position(5, 2), null));
				assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
						start, new Position(4, 2)));
			}

			@Test
			public void testGetPossibleMovesAfterCheckMate() {
				Set<Move> expectedMoves = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
				board[7][4] = new Piece(Color.BLACK, PieceKind.KING);
				board[6][4] = new Piece(Color.WHITE, PieceKind.QUEEN);
				board[5][5] = new Piece(Color.WHITE, PieceKind.BISHOP);
				start = new State(Color.BLACK, board, new boolean[] { true, true },
						new boolean[] { true, true }, null, 0, new GameResult(
								Color.WHITE, GameResultReason.CHECKMATE));
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			@Test
			public void testGetPossibleMovesAfterFiftyMoves() {
				Set<Move> expectedMoves = Sets.newHashSet();
				start = new State();
				start.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
				start.setGameResult(new GameResult(null,
						GameResultReason.FIFTY_MOVE_RULE));
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			@Test
			public void testGetPossibleStartPositionsAfterCheck() {
				Set<Position> expectedPositions = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
				board[7][5] = new Piece(Color.BLACK, PieceKind.KING);
				board[7][4] = new Piece(Color.BLACK, PieceKind.QUEEN);
				board[0][3] = new Piece(Color.WHITE, PieceKind.BISHOP);
				board[0][2] = new Piece(Color.WHITE, PieceKind.QUEEN);
				board[0][6] = new Piece(Color.WHITE, PieceKind.KNIGHT);
				// can block the check
				expectedPositions.add(new Position(0, 3));
				expectedPositions.add(new Position(0, 6));
				expectedPositions.add(new Position(0, 2));
				// can escape the check
				expectedPositions.add(new Position(0, 4));
				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				assertEquals(expectedPositions,
						stateExplorer.getPossibleStartPositions(start));
			}

			@Test
			public void testGetPossibleStartPositionsRookBlocksCheck() {
				Set<Position> expectedPositions = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
				board[1][3] = new Piece(Color.WHITE, PieceKind.ROOK);// ROOK CANNOT MOVE
				board[2][2] = new Piece(Color.BLACK, PieceKind.QUEEN);
				board[7][7] = new Piece(Color.BLACK, PieceKind.KING);
				// can escape the check
				expectedPositions.add(new Position(0, 4));
				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				assertEquals(expectedPositions,
						stateExplorer.getPossibleStartPositions(start));
			}

			@Test
			public void testGetPossibleMovesRookBlocksCheck() {
				Set<Move> expectedMoves = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, KING);
				board[1][3] = new Piece(Color.WHITE, ROOK);// ROOK CANNOT MOVE
				board[2][2] = new Piece(Color.BLACK, QUEEN);
				board[7][7] = new Piece(Color.BLACK, KING);
				// can escape the check
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(0, 3), null));
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(1, 4), null));
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(1, 5), null));
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(0, 5), null));

				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			private void kingMovesHelper(Set<Move> expectedMoves) {
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(0, 3), null));
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(0, 5), null));
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(1, 3), null));
				expectedMoves
						.add(new Move(new Position(0, 4), new Position(1, 5), null));
			}

			@Test
			public void testGetPossibleMovesRookMoves() {
				Set<Move> expectedMoves = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
				board[1][4] = new Piece(Color.WHITE, PieceKind.ROOK);
				board[7][3] = new Piece(Color.BLACK, PieceKind.KING);
				for (int i = 2; i < 8; i++) {
					expectedMoves.add(new Move(new Position(1, 4), new Position(i, 4),
							null));
				}
				for (int i = 0; i < 8; i++) {
					if (i != 4) {
						expectedMoves.add(new Move(new Position(1, 4), new Position(1,
								i), null));
					}
				}
				kingMovesHelper(expectedMoves);
				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			@Test
			public void testGetPossibleMovesKnightMoves() {
				Set<Move> expectedMoves = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, KING);
				board[1][4] = new Piece(Color.WHITE, KNIGHT);
				board[7][3] = new Piece(Color.BLACK, KING);

				expectedMoves
						.add(new Move(new Position(1, 4), new Position(0, 2), null));
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(0, 6), null));
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(2, 2), null));
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(2, 6), null));
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(3, 3), null));
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(3, 5), null));
				kingMovesHelper(expectedMoves);

				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			@Test
			public void testGetPossibleMovesPawnMoves() {
				Set<Move> expectedMoves = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, KING);
				board[1][4] = new Piece(Color.WHITE, PAWN);
				board[7][3] = new Piece(Color.BLACK, KING);

				expectedMoves
						.add(new Move(new Position(1, 4), new Position(2, 4), null));
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(3, 4), null));
				kingMovesHelper(expectedMoves);

				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			@Test
			public void testGetPossibleMovesBishopMoves() {
				Set<Move> expectedMoves = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
				board[1][4] = new Piece(Color.WHITE, BISHOP);
				board[7][3] = new Piece(Color.BLACK, PieceKind.KING);
				int c = 1;
				int i = 2;
				while (c + 4 <= 7) {
					expectedMoves.add(new Move(new Position(1, 4), new Position(i,
							4 + c), null));
					i++;
					c++;
				}
				i = 2;
				c = 1;
				while (4 - c >= 0) {
					expectedMoves.add(new Move(new Position(1, 4), new Position(i,
							4 - c), null));
					i++;
					c++;
				}
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(0, 5), null));
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(0, 3), null));
				kingMovesHelper(expectedMoves);

				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			@Test
			public void testGetPossibleMovesQueenMoves() {
				Set<Move> expectedMoves = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
				board[1][4] = new Piece(Color.WHITE, QUEEN);
				board[7][3] = new Piece(Color.BLACK, PieceKind.KING);
				for (int i = 2; i < 8; i++) {
					expectedMoves.add(new Move(new Position(1, 4), new Position(i, 4),
							null));
				}
				for (int i = 0; i < 8; i++) {
					if (i != 4) {
						expectedMoves.add(new Move(new Position(1, 4), new Position(1,
								i), null));
					}
				}

				int c = 1;
				int i = 2;
				while (c + 4 <= 7) {
					expectedMoves.add(new Move(new Position(1, 4), new Position(i,
							4 + c), null));
					i++;
					c++;
				}
				i = 2;
				c = 1;
				while (4 - c >= 0) {
					expectedMoves.add(new Move(new Position(1, 4), new Position(i,
							4 - c), null));
					i++;
					c++;
				}
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(0, 5), null));
				expectedMoves
						.add(new Move(new Position(1, 4), new Position(0, 3), null));

				kingMovesHelper(expectedMoves);

				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}

			@Test
			public void testGetPossibleMovesIsUnionOfGetStartPositionAndGetPossibleMovesFromPosition() {
				Set<Move> expectedMoves = Sets.newHashSet();
				Piece[][] board = new Piece[8][8];
				board[0][4] = new Piece(Color.WHITE, PieceKind.KING);
				board[1][4] = new Piece(Color.WHITE, PieceKind.QUEEN);
				board[7][3] = new Piece(Color.BLACK, PieceKind.KING);
				start = new State(Color.WHITE, board, new boolean[] { false, false },
						new boolean[] { false, false }, null, 0, null);
				Set<Position> positions = stateExplorer
						.getPossibleStartPositions(start);
				for (Position pos : positions) {
					expectedMoves.addAll(stateExplorer.getPossibleMovesFromPosition(
							start, pos));
				}
				assertEquals(expectedMoves, stateExplorer.getPossibleMoves(start));
			}
			
			
			/*
			 * End Tests by Sahil Vora <vora.sahil@gmail.com>
			 */

}
