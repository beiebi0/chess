package org.wenjiechen.hw3;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.wenjiechen.hw2.StateChangerImpl;

/**
 * Tests for the Presenter class.
 */
public class PresenterTest {
	Presenter presenter;
	Presenter.View view;
	State start;
	StateChanger stateChanger = new StateChangerImpl();

	@Before
	public void setup() {
		presenter = new Presenter();
		view = Mockito.mock(Presenter.View.class);
		presenter.setView(view);
		start = new State();
		presenter.setState(start);
	}

	@Test
	public void testView() {
		Mockito.verify(view).setWhoseTurn(Color.WHITE);
		Mockito.verify(view).setGameResult(null);
		Mockito.verify(view).setPiece(0, 0,
				new Piece(Color.WHITE, PieceKind.ROOK));
		Mockito.verify(view).setPiece(4, 4, null);
	}

	@Test
	public void testMovePawn() {
		presenter.dealWithClick(1, 2);
		presenter.cleanHighlight();
		Mockito.verify(view).setHighlighted(2, 2, true);
		Mockito.verify(view).setHighlighted(3, 2, true);
	}
	
	@Test
	public void testSecondClickForSameKindPiece() {
		presenter.dealWithClick(1, 2);
		presenter.cleanHighlight();
		Mockito.verify(view).setHighlighted(2, 2, true);
		Mockito.verify(view).setHighlighted(3, 2, true);
		presenter.dealWithClick(1, 3);
		presenter.cleanHighlight();
	}
	
	@Test
	public void testSecondClickForPromotion() {
		start.setPiece(0, 0,null);
		start.setPiece(1, 0,null);
		start.setPiece(6, 0,new Piece(Color.WHITE,PieceKind.PAWN));
		presenter.dealWithClick(6, 0);
		Mockito.verify(view).setPromotionChoice(Color.WHITE);
	}
	
	@Test
	public void testMoveKnight() {
		presenter.dealWithClick(0, 1);
		presenter.cleanHighlight();
		Mockito.verify(view).setHighlighted(2, 0, true);
		Mockito.verify(view).setHighlighted(2, 2, true);
	}
	
	@Test
	public void testPossibleMoves() {
		for (int i = 0; i < 8; i++) {
			Mockito.verify(view).setHighlighted(1, i, true);
		}
		Mockito.verify(view).setHighlighted(0, 1, true);
		Mockito.verify(view).setHighlighted(0, 6, true);
	}

	@Test
	public void testWhiteRookCaptureBlackPawn() {
		start.setPiece(0, 0,null);
		start.setPiece(1, 0,null);
		start.setPiece(5, 0, new Piece(Color.WHITE,PieceKind.ROOK));
		stateChanger.makeMove(start, new Move(new Position(5,0), new Position(6,0), null));
		presenter.setState(start);
		Mockito.verify(view).setPiece(6, 0,
				new Piece(Color.WHITE, PieceKind.ROOK));				
	}
	
	@Test
	public void testHighlightForRook() {
		start.setPiece(0, 0,null);
		start.setPiece(1, 0,null);
		start.setPiece(5, 0, new Piece(Color.WHITE,PieceKind.ROOK));
		presenter.dealWithClick(5, 0);
		Mockito.verify(view).setHighlighted(6, 0, true);				
		Mockito.verify(view).setHighlighted(5, 1, true);				
		Mockito.verify(view).setHighlighted(5, 2, true);				
		Mockito.verify(view).setHighlighted(5, 3, true);				
		Mockito.verify(view).setHighlighted(5, 4, true);				
		Mockito.verify(view).setHighlighted(5, 5, true);				
		Mockito.verify(view).setHighlighted(5, 6, true);				
		Mockito.verify(view).setHighlighted(5, 7, true);				
	}
	
	@Test
	public void testHighlightForCanntMovePiece() {
		presenter.dealWithClick(0, 4);
		for (int r = 0; r < 8; ++r) {
			for (int c = 0; c < 8; ++c) {
				Mockito.verify(view).setHighlighted(r, c, false);
			}
		}		
	}
	
	@Test
	public void testWhiteKingCastleKingSide() {
		start.setPiece(0, 5,null);
		start.setPiece(0, 6,null);
		stateChanger.makeMove(start, new Move(new Position(0,4), new Position(0,6), null));
		presenter.setState(start);
		Mockito.verify(view).setPiece(0, 6,
				new Piece(Color.WHITE, PieceKind.KING));				
		Mockito.verify(view).setPiece(0, 5,
				new Piece(Color.WHITE, PieceKind.ROOK));				
	}
	
}