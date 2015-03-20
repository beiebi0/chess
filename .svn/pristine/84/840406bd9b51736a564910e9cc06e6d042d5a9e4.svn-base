package org.wenjiechen.hw1;

import static org.junit.Assert.assertEquals;
import static org.shared.chess.Color.BLACK;
import static org.shared.chess.Color.WHITE;
import static org.shared.chess.PieceKind.QUEEN;
import static org.shared.chess.PieceKind.KING;
import static org.shared.chess.PieceKind.ROOK;
import static org.shared.chess.PieceKind.PAWN;

import org.junit.Test;
import org.shared.chess.AbstractStateChangerTest;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.State;
import org.shared.chess.Position;

public abstract class AbstractStateChangerCastlingForWhiteTest extends
		AbstractStateChangerTest {

	public void initForWenjieChen() {
		start.setTurn(WHITE);
		// removing all pieces, it's convenient for testcase
		for (int col = 0; col < 8; ++col) {
			for (int row = 0; row < 8; ++row) {
				start.setPiece(row, col, null);
			}
		}
		// put KINGs and ROOKs in original positions
		start.setPiece(0, 4, new Piece(WHITE, KING));
		start.setPiece(0, 7, new Piece(WHITE, ROOK));
		start.setPiece(0, 0, new Piece(WHITE, ROOK));
		start.setPiece(7, 4, new Piece(BLACK,KING));
	}

	@Test
	public void testBothSidesRookCanCastleExpectTrue() {
		super.setup();
		State expectedState = start.copy();
		expectedState.setCanCastleKingSide(WHITE, true);
		expectedState.setCanCastleQueenSide(WHITE, true);
		assertEquals(expectedState, start);
	}

	@Test
	public void testMoveKingSideRookExpectFalse() {
		initForWenjieChen();
		State expectedState = start.copy();
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expectedState.setPiece(2, 7, new Piece(WHITE, ROOK));
		expectedState.setPiece(0, 7, null);
		expectedState.setTurn(BLACK); // alternative
		Move move = new Move(new Position(0, 7), new Position(2, 7), null);
		stateChanger.makeMove(start, move);
		assertEquals(expectedState, start);
	}

	@Test
	public void testMoveQueenSideRookExpectFalse() {
		initForWenjieChen();
		State expectedState = start.copy();
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expectedState.setPiece(3, 0, new Piece(WHITE, ROOK));
		expectedState.setPiece(0, 0, null);
		expectedState.setTurn(BLACK);
		Move move = new Move(new Position(0, 0), new Position(3, 0), null);
		stateChanger.makeMove(start, move);
		assertEquals(expectedState, start);
	}

	@Test
	public void testMoveKingForwardExpectFalse() {
		initForWenjieChen();
		State expectedState = start.copy();
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expectedState.setPiece(0, 4, null);
		expectedState.setPiece(1, 4, new Piece(WHITE, KING));
		expectedState.setTurn(BLACK);
		Move move = new Move(new Position(0, 4), new Position(1, 4), null);
		stateChanger.makeMove(start, move);
		assertEquals(expectedState, start);
	}

	@Test
	public void testMoveKingSideRookForwardAndBackExpectFalse() {
		initForWenjieChen();
		State expectedState = start.copy();
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(2);
		expectedState.setTurn(BLACK);
		Move move1 = new Move(new Position(0, 7), new Position(2, 7), null);
		stateChanger.makeMove(start, move1);
		start.setTurn(WHITE);
		Move move2 = new Move(new Position(2, 7), new Position(0, 7), null);
		stateChanger.makeMove(start, move2);
		assertEquals(expectedState, start);
	}

	@Test
	public void testMoveQueenSideRookForwardAndBackExpectFalse() {
		initForWenjieChen();
		State expectedState = start.copy();
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(2);
		expectedState.setTurn(BLACK);
		Move move1 = new Move(new Position(0, 0), new Position(3, 0), null);
		stateChanger.makeMove(start, move1);
		start.setTurn(WHITE);
		Move move2 = new Move(new Position(3, 0), new Position(0, 0), null);
		stateChanger.makeMove(start, move2);
		assertEquals(expectedState, start);
	}

	//castle is one move involving two piece.
	//when move king 2 steps to right, rook's move should be done automatically
	@Test
	public void testKingCastleWithKingSideRook() {
		initForWenjieChen();
		State expectedState = start.copy();
		// when castle finish, all flags are false
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1); //castle regards as 1 step 
		expectedState.setPiece(0, 4, null);
		expectedState.setPiece(0, 6, new Piece(WHITE, KING));// king move 2 steps to right
		expectedState.setPiece(0, 7, null);
		expectedState.setPiece(0, 5, new Piece(WHITE, ROOK));// right rook move to left to King
		expectedState.setTurn(BLACK);
		//move king 2 steps to right, and rook's move should be done automatically
		Move move1 = new Move(new Position(0, 4), new Position(0, 6), null);
		stateChanger.makeMove(start, move1);
		assertEquals(expectedState, start);
	}

	//white king do castle with king side rook. then move black pawn
	@Test
	public void testBlackPieceMoveAfterKingCastleWithKingSideRook(){
		initForWenjieChen();
		State expectedState = start.copy();
		// when castle finish, all flags are false
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);//move pawn
		expectedState.setPiece(0, 4, null);
		expectedState.setPiece(0, 6, new Piece(WHITE, KING));// king move 2 steps to right
		expectedState.setPiece(0, 7, null);
		expectedState.setPiece(0, 5, new Piece(WHITE, ROOK));// right rook move to left of King
		expectedState.setPiece(5, 3, new Piece(BLACK,PAWN));
		expectedState.setTurn(WHITE);
		
		start.setPiece(6, 3, new Piece(BLACK, PAWN));
		Move move1 = new Move(new Position(0, 4), new Position(0, 6), null);
		Move move2 = new Move(new Position(6,3), new Position(5,3),null);
		stateChanger.makeMove(start, move1);
		stateChanger.makeMove(start, move2);
		assertEquals(expectedState, start);
	}

	//rook move to (0,5), and then king move to (0,6).
	//It's illegalmove, because king can't move 2 steps and leap over rooks 
	//if do castle, king must move first
	@Test(expected = IllegalMove.class)
	public void testKingCastleWithKingSideRookButRookMoveFirst() {
		initForWenjieChen();
		Move move1 = new Move(new Position(0, 7), new Position(0, 5), null);
		Move move2 = new Move(new Position(0,4), new Position(0,6),null);
		stateChanger.makeMove(start, move1);
		stateChanger.makeMove(start, move2);
	}
	
	//rook move to (0,3), and then king move to (0,2).
	//It's illegalmove, because if do castle, king must move first
	@Test(expected = IllegalMove.class)
	public void testKingCastleWithQueenSideRookButRookMoveFirst() {
		initForWenjieChen();
		Move move1 = new Move(new Position(0,0), new Position(0, 3), null);
		Move move2 = new Move(new Position(0,4), new Position(0, 2),null);
		stateChanger.makeMove(start, move1);
		stateChanger.makeMove(start, move2);
	}
	
	//if it throw illegalMove, it maybe the Trun = black after first move,
	//but it is supposed to be white
	@Test
	public void testKingCastleWithQueenSideRook() {
		initForWenjieChen();
		State expectedState = start.copy();
		// when castle finish, all flags are false
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expectedState.setPiece(0, 4, null);
		expectedState.setPiece(0, 2, new Piece(WHITE, KING));// king move 2 steps to left
		expectedState.setPiece(0, 0, null);
		expectedState.setPiece(0, 3, new Piece(WHITE, ROOK));// right rook move to right of King
		expectedState.setTurn(BLACK);

		Move move1 = new Move(new Position(0, 4), new Position(0, 2), null);
		stateChanger.makeMove(start, move1);
		assertEquals(expectedState, start);
	}
	
	//move black piece, after white king do castle with queen side rook
	@Test
	public void testBlackPieceMoveAfterKingCastleWithQueenSideRook(){
		initForWenjieChen();
		State expectedState = start.copy();
		// when castle finish, all flags are false
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(0);//move pawn
		expectedState.setPiece(0, 4, null);
		expectedState.setPiece(0, 2, new Piece(WHITE, KING));// king move 2 steps to right
		expectedState.setPiece(0, 0, null);
		expectedState.setPiece(0, 3, new Piece(WHITE, ROOK));// right rook move to left of King
		expectedState.setPiece(5, 3, new Piece(BLACK,PAWN));
		expectedState.setTurn(WHITE);
		
		start.setPiece(6, 3, new Piece(BLACK, PAWN));
		Move move1 = new Move(new Position(0, 4), new Position(0, 2), null);
		Move move2 = new Move(new Position(6,3), new Position(5,3),null);
		stateChanger.makeMove(start, move1);
		stateChanger.makeMove(start, move2);
		assertEquals(expectedState, start);
	}
	
	@Test
	public void testMoveKingTowardQueenSideRookExpectFalse() {
		initForWenjieChen();
		State expectedState = start.copy();
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expectedState.setPiece(0, 4, null);
		expectedState.setPiece(0, 3, new Piece(WHITE, KING));
		expectedState.setTurn(BLACK);
		Move move = new Move(new Position(0, 4), new Position(0, 3), null);
		stateChanger.makeMove(start, move);
		assertEquals(expectedState, start);
	}

	@Test(expected = IllegalMove.class)
	public void testMovePieceBetweenKingAndKingSideRookExpectFalse() {
		initForWenjieChen();
		start.setPiece(0, 5, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(0,4), new Position(0, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testMovePieceBetweenKingAndKingSideRookExpectFalse2() {
		initForWenjieChen();
		start.setPiece(0, 6, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);
		stateChanger.makeMove(start, move);
	}

	@Test(expected = IllegalMove.class)
	public void testMovePieceBetweenKingAndQueenSideRookExpectFalse() {
		initForWenjieChen();		
		start.setPiece(0, 3, new Piece(WHITE, QUEEN));
		Move move = new Move(new Position(0,4), new Position(0, 2), null);
		stateChanger.makeMove(start, move);
	}

	// White King to move cannot castle king side because the black queen (6,6) is covering
	// (0,6). However, White can castle queen side, even though the rook on (0,0) is under attack
	@Test(expected = IllegalMove.class)
	public void testKingSidePathIsInCheckButQueenSideIsFreeCastleKingSide() {
		initForWenjieChen();		
		start.setPiece(6, 6, new Piece(BLACK, QUEEN));
		//white king wants to castle with king side rook, expect illegalmove
		Move move = new Move(new Position(0,4), new Position(0,6), null);
		stateChanger.makeMove(start, move);
	}

	// White King to move cannot castle king side because the black queen (6,6) is covering
	// (0,6). However, White can castle queen side, even though the rook on (0,0) is under attack
	@Test
	public void testKingSidePathIsInCheckButQueenSideIsFreeCastleQueenSide() {
		initForWenjieChen();		
		State expectedState = start.copy();
		expectedState.setCanCastleQueenSide(WHITE, false);
		expectedState.setCanCastleKingSide(WHITE, false);
		expectedState.setNumberOfMovesWithoutCaptureNorPawnMoved(1);
		expectedState.setPiece(0,4, null);
		expectedState.setPiece(0,2, new Piece(WHITE,KING));
		expectedState.setPiece(0,0, null);
		expectedState.setPiece(0,3, new Piece(WHITE,ROOK));
		expectedState.setPiece(6,6, new Piece(BLACK,QUEEN));
		expectedState.setTurn(BLACK);			
		
		start.setPiece(6, 6, new Piece(BLACK, QUEEN));
		//move king, rook should be moved automatically
		Move move = new Move(new Position(0,4), new Position(0,2), null);
		stateChanger.makeMove(start, move);
		assertEquals(expectedState, start);
	}
	
	
	// When Black Queen is at (2,2), White King cannot castle on either side 
	//because he is in check from the black queen.
	@Test(expected = IllegalMove.class)
	public void testKingIsInCheckAndCanntCastleOnEitherSide() {
		initForWenjieChen();
		start.setPiece(2, 2, new Piece(BLACK, QUEEN)); //check white king
		Move move = new Move(new Position(0, 4), new Position(0, 2), null);//want castle to left
		stateChanger.makeMove(start, move);
	}
	
	// When Black Queen is at (2,2), White King cannot castle on either side 
	//because he is in check from the black queen.
	@Test(expected = IllegalMove.class)
	public void testKingIsInCheckAndCanntCastleOnEitherSide2() {
		initForWenjieChen();
		start.setPiece(2, 2, new Piece(BLACK, QUEEN)); //check white king
		Move move = new Move(new Position(0, 4), new Position(0, 6), null);//want castle to right
		stateChanger.makeMove(start, move);
	}
}
