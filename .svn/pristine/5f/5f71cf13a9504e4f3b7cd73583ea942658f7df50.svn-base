package org.leozis.hw2_5;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.shared.chess.AbstractStateExplorerTest;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;

public class StateExplorerImplTest extends AbstractStateExplorerTest {
	  @Override
	  public StateExplorer getStateExplorer() {
	    return new StateExplorerImpl();
	  }
	  
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
		start.setPiece(new Position(2, 3), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(new Position(3, 2), new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(5, 6), new Piece(Color.BLACK, PieceKind.PAWN));
		
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
	    
		start.setPiece(new Position(0, 3), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(3, 3), new Piece(Color.WHITE, PieceKind.PAWN));
		start.setPiece(new Position(4, 2), new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(new Position(7, 3), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(4, 4), new Piece(Color.BLACK, PieceKind.PAWN));
		
	    expectedPositions.add(new Move(new Position(0,3),new Position(1,3),null));
	    expectedPositions.add(new Move(new Position(0,3),new Position(1,4),null));
	    expectedPositions.add(new Move(new Position(0,3),new Position(0,4),null));
	    expectedPositions.add(new Move(new Position(3,3),new Position(4,2),null));
	    expectedPositions.add(new Move(new Position(3,3),new Position(4,3),null));
	    expectedPositions.add(new Move(new Position(3,3),new Position(4,4),null));

	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossiblePositions_ForBlack_BlackKingChecked() {
	    Set<Move> expectedPositions = Sets.newHashSet();
	    init_lz();
	    
		start.setPiece(new Position(7, 0), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(2, 2), new Piece(Color.WHITE, PieceKind.BISHOP));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.KNIGHT));
		start.setPiece(new Position(0, 4), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(6, 4), new Piece(Color.WHITE, PieceKind.ROOK));
		start.setTurn(Color.BLACK);
		
	    expectedPositions.add(new Move(new Position(0,4),new Position(0,3),null));
	    expectedPositions.add(new Move(new Position(0,4),new Position(0,5),null));


	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleMovesFromPosition_ForWhiteKnight() {
	    Set<Move> expectedPositions = Sets.newHashSet();
	    init_lz();
	    
		start.setPiece(new Position(7, 0), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(1, 1), new Piece(Color.WHITE, PieceKind.BISHOP));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.KNIGHT));
		start.setPiece(new Position(0, 4), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(6, 5), new Piece(Color.WHITE, PieceKind.ROOK));
		
	    expectedPositions.add(new Move(new Position(0,7),new Position(1,5),null));
	    expectedPositions.add(new Move(new Position(0,7),new Position(2,6),null));


	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleMovesFromPosition(start, new Position(0,7)));
	  }
	  
	  @Test
	  public void testGetPossibleStartPositions_ForBlack_PawnsInSingleFile() {
	    Set<Position> expectedPositions = Sets.newHashSet();
	    init_lz();
	    
		start.setPiece(new Position(7, 0), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(2, 3), new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.KNIGHT));
		start.setPiece(new Position(0, 0), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(1, 6), new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(new Position(2, 6), new Piece(Color.BLACK, PieceKind.PAWN));
		start.setTurn(Color.BLACK);
		
	    expectedPositions.add(new Position(2,3));
	    expectedPositions.add(new Position(1,6));
	    expectedPositions.add(new Position(0,0));



	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleStartPositions(start));
	  }
	  
	  @Test
	  public void testGetPossiblePositions_ForBlack_PawnsPromotion() {
	    Set<Move> expectedPositions = Sets.newHashSet();
	    init_lz();
	    
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(1, 6), new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(0, 5), new Piece(Color.WHITE, PieceKind.KNIGHT));
		start.setTurn(Color.BLACK);
		
	    expectedPositions.add(new Move(new Position(1,6),new Position(0,5),PieceKind.ROOK));
	    expectedPositions.add(new Move(new Position(1,6),new Position(0,5),PieceKind.QUEEN));
	    expectedPositions.add(new Move(new Position(1,6),new Position(0,5),PieceKind.BISHOP));
	    expectedPositions.add(new Move(new Position(1,6),new Position(0,5),PieceKind.KNIGHT));
	    expectedPositions.add(new Move(new Position(1,6),new Position(0,6),PieceKind.ROOK));
	    expectedPositions.add(new Move(new Position(1,6),new Position(0,6),PieceKind.QUEEN));
	    expectedPositions.add(new Move(new Position(1,6),new Position(0,6),PieceKind.BISHOP));
	    expectedPositions.add(new Move(new Position(1,6),new Position(0,6),PieceKind.KNIGHT));
	    
	    expectedPositions.add(new Move(new Position(7,0),new Position(7,1),null));
	    expectedPositions.add(new Move(new Position(7,0),new Position(6,0),null));
	    expectedPositions.add(new Move(new Position(7,0),new Position(6,1),null));



	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleMovesFromPosition_ForWhite_Enpassant() {
	    Set<Move> expectedPositions = Sets.newHashSet();
	    init_lz();
	    
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(4, 1), new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(new Position(5, 3), new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(new Position(4, 2), new Piece(Color.WHITE, PieceKind.PAWN));
		start.setEnpassantPosition(new Position(4,1));
		
	    expectedPositions.add(new Move(new Position(4,2),new Position(5,1),null));
	    expectedPositions.add(new Move(new Position(4,2),new Position(5,2),null));
	    expectedPositions.add(new Move(new Position(4,2),new Position(5,3),null));



	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleMovesFromPosition(start,new Position(4,2)));
	  }
	  
	  @Test
	  public void testGetPossibleMoves_ForBlack_Enpassant() {
	    Set<Move> expectedPositions = Sets.newHashSet();
	    init_lz();
	    
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(3, 1), new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(new Position(5, 3), new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(new Position(3, 2), new Piece(Color.WHITE, PieceKind.PAWN));
		start.setEnpassantPosition(new Position(3,2));
		start.setTurn(Color.BLACK);
		
	    expectedPositions.add(new Move(new Position(3,1),new Position(2,2),null));
	    expectedPositions.add(new Move(new Position(3,1),new Position(2,1),null));
	    expectedPositions.add(new Move(new Position(5,3),new Position(4,3),null));
	    //black king moves
	    expectedPositions.add(new Move(new Position(7,0),new Position(6,0),null));
	    expectedPositions.add(new Move(new Position(7,0),new Position(7,1),null));
	    expectedPositions.add(new Move(new Position(7,0),new Position(6,1),null));

	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleMoves_ForBlack_Castle() {
	    Set<Move> expectedPositions = Sets.newHashSet();
	    init_lz();
	    
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(7, 0), new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(new Position(7, 7), new Piece(Color.BLACK, PieceKind.ROOK));
		start.setPiece(new Position(6, 0), new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(new Position(6, 7), new Piece(Color.WHITE, PieceKind.ROOK));

		start.setTurn(Color.BLACK);
		
		//king moves
	    expectedPositions.add(new Move(new Position(7,4),new Position(7,2),null));
	    expectedPositions.add(new Move(new Position(7,4),new Position(7,3),null));
	    expectedPositions.add(new Move(new Position(7,4),new Position(7,5),null));
	    expectedPositions.add(new Move(new Position(7,4),new Position(7,6),null));
	    
	    //rook moves
	    expectedPositions.add(new Move(new Position(7,0),new Position(6,0),null));
	    expectedPositions.add(new Move(new Position(7,0),new Position(7,1),null));
	    expectedPositions.add(new Move(new Position(7,0),new Position(7,2),null));
	    expectedPositions.add(new Move(new Position(7,0),new Position(7,3),null));
	    expectedPositions.add(new Move(new Position(7,7),new Position(6,7),null));
	    expectedPositions.add(new Move(new Position(7,7),new Position(7,6),null));
	    expectedPositions.add(new Move(new Position(7,7),new Position(7,5),null));

	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  @Test
	  public void testGetPossibleMoves_ForWhite_CastleQueenSide() {
	    Set<Move> expectedPositions = Sets.newHashSet();
	    init_lz();
	    
		start.setPiece(new Position(7, 4), new Piece(Color.BLACK, PieceKind.KING));
		start.setPiece(new Position(0, 4), new Piece(Color.WHITE, PieceKind.KING));
		start.setPiece(new Position(0, 0), new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(new Position(0, 7), new Piece(Color.WHITE, PieceKind.ROOK));
		start.setPiece(new Position(1, 0), new Piece(Color.BLACK, PieceKind.PAWN));
		start.setPiece(new Position(1, 7), new Piece(Color.BLACK, PieceKind.PAWN));
		
		//king moves
	    expectedPositions.add(new Move(new Position(0,4),new Position(0,2),null));
	    expectedPositions.add(new Move(new Position(0,4),new Position(0,3),null));
	    expectedPositions.add(new Move(new Position(0,4),new Position(0,5),null));
	    expectedPositions.add(new Move(new Position(0,4),new Position(1,3),null));
	    expectedPositions.add(new Move(new Position(0,4),new Position(1,4),null));
	    expectedPositions.add(new Move(new Position(0,4),new Position(1,5),null));
	    
	    //rook moves
	    expectedPositions.add(new Move(new Position(0,0),new Position(1,0),null));
	    expectedPositions.add(new Move(new Position(0,0),new Position(0,1),null));
	    expectedPositions.add(new Move(new Position(0,0),new Position(0,2),null));
	    expectedPositions.add(new Move(new Position(0,0),new Position(0,3),null));
	    expectedPositions.add(new Move(new Position(0,7),new Position(1,7),null));
	    expectedPositions.add(new Move(new Position(0,7),new Position(0,6),null));
	    expectedPositions.add(new Move(new Position(0,7),new Position(0,5),null));

	    assertEquals(expectedPositions,
	        stateExplorer.getPossibleMoves(start));
	  }
	  
	  /*
	   * End Tests by Leo Zis <leozis@gmail.com>
	   */
}
