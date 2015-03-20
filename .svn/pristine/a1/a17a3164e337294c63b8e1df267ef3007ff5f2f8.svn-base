package org.wenjiechen.hw2_5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.shared.chess.Color.WHITE;

import java.util.Set;

import org.junit.Test;
import org.shared.chess.AbstractStateExplorerTest;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;

public class StateExplorerImplTest extends AbstractStateExplorerTest {
	@Override
	public StateExplorer getStateExplorer() {
		return new StateExplorerImpl();
	}

	@Test
	public void testGetPossibleMovesFromPosition_GameEndCanntMoveAnyPiece() {
		start.setGameResult(new GameResult(Color.BLACK,
				GameResultReason.CHECKMATE));
		assertTrue(stateExplorer.getPossibleMoves(start).isEmpty());
	}

	@Test
	public void testGetPossibleMovesFromPosition_StepCountEquals100CanntMoveAnyPiece() {
		start.setNumberOfMovesWithoutCaptureNorPawnMoved(100);
		assertTrue(stateExplorer.getPossibleMoves(start).isEmpty());
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteKingMoveKingIsUnderAttack() {
		start.setPiece(new Position(1, 4), null);
		start.setPiece(new Position(0, 3), null);
		start.setPiece(new Position(7, 7), null);
		start.setPiece(new Position(6, 7), null);
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK,
				PieceKind.ROOK));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(0, 4), new Position(0, 3), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(0, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteKnightMoveKingIsUnderAttack() {
		start.setPiece(new Position(1, 5), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(6, 3), null);
		start.setPiece(new Position(6, 7), null);
		start.setPiece(new Position(0, 6), null);
		start.setPiece(new Position(2, 6), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 5), new Piece(Color.WHITE,
				PieceKind.KNIGHT));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(4, 5), new Position(2, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 5)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteBishopMoveKingIsUnderAttack() {
		start.setPiece(new Position(1, 5), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(6, 7), null);
		start.setPiece(new Position(0, 5), null);
		start.setPiece(new Position(2, 6), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		start.setPiece(new Position(4, 4), new Piece(Color.WHITE,
				PieceKind.BISHOP));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(4, 4), new Position(2, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhiteRookMoveKingIsUnderAttack() {
		start.setPiece(new Position(1, 5), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(0, 7), null);
		start.setPiece(new Position(2, 6), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		start.setPiece(new Position(2, 5), new Piece(Color.WHITE,
				PieceKind.ROOK));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(2, 5), new Position(2, 6), null));
		expectedMoves
				.add(new Move(new Position(2, 5), new Position(1, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(2, 5)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhitePawnMoveKingIsUnderAttack() {
		start.setPiece(new Position(1, 5), null);
		start.setPiece(new Position(7, 3), null);
		start.setPiece(new Position(2, 6), new Piece(Color.BLACK,
				PieceKind.QUEEN));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(1, 7), new Position(2, 6), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 7)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_MoveWhitePawn() {
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(1, 5), new Position(2, 5), null));
		expectedMoves
				.add(new Move(new Position(1, 5), new Position(3, 5), null));
		start.setTurn(Color.WHITE);
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(1, 5)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_MoveWhitePawnDiagonal() {
		start.setPiece(new Position(1, 5), null);
		start.setPiece(new Position(4, 5), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 6), null);
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 4), null);
		start.setPiece(new Position(5, 4), new Piece(Color.BLACK,
				PieceKind.PAWN));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(4, 5), new Position(5, 6), null));
		expectedMoves
				.add(new Move(new Position(4, 5), new Position(5, 4), null));
		expectedMoves
				.add(new Move(new Position(4, 5), new Position(5, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(4, 5)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_WhitePawnDoPromotion() {
		start.setPiece(new Position(1, 4), null);
		start.setPiece(new Position(6, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves.add(new Move(new Position(6, 4), new Position(7, 5),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 4), new Position(7, 5),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 4), new Position(7, 5),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 4), new Position(7, 5),
				PieceKind.QUEEN));
		expectedMoves.add(new Move(new Position(6, 4), new Position(7, 3),
				PieceKind.BISHOP));
		expectedMoves.add(new Move(new Position(6, 4), new Position(7, 3),
				PieceKind.KNIGHT));
		expectedMoves.add(new Move(new Position(6, 4), new Position(7, 3),
				PieceKind.ROOK));
		expectedMoves.add(new Move(new Position(6, 4), new Position(7, 3),
				PieceKind.QUEEN));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(6, 4)));
	}

	@Test
	public void testGetPossibleMovesFromPosition_BlackPawnDoEnpassant() {
		start.setPiece(new Position(1, 4), null);
		start.setPiece(new Position(3, 4), new Piece(Color.WHITE,
				PieceKind.PAWN));
		start.setPiece(new Position(6, 5), null);
		start.setPiece(new Position(3, 5), new Piece(Color.BLACK,
				PieceKind.PAWN));
		start.setEnpassantPosition(new Position(3, 4));
		start.setTurn(Color.BLACK);
		Set<Move> expectedMoves = Sets.newHashSet();
		expectedMoves
				.add(new Move(new Position(3, 5), new Position(2, 4), null));
		expectedMoves
				.add(new Move(new Position(3, 5), new Position(2, 5), null));
		assertEquals(expectedMoves, stateExplorer.getPossibleMovesFromPosition(
				start, new Position(3, 5)));
	}
}
