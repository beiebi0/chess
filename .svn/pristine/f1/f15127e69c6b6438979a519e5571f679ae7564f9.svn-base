package org.yoavzibin.hw2_5;

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
      expectedMoves.add(new Move(new Position(1, c), new Position(2, c), null));
      expectedMoves.add(new Move(new Position(1, c), new Position(3, c), null));
    }
    // knight moves
    expectedMoves.add(new Move(new Position(0, 1), new Position(2, 0), null));
    expectedMoves.add(new Move(new Position(0, 1), new Position(2, 2), null));
    expectedMoves.add(new Move(new Position(0, 6), new Position(2, 5), null));
    expectedMoves.add(new Move(new Position(0, 6), new Position(2, 7), null));
    assertEquals(expectedMoves,
        stateExplorer.getPossibleMoves(start));
  }
  
  @Test
  public void testGetPossibleMovesFromPosition_InitStateForLeftKnight() {
    Set<Move> expectedMoves = Sets.newHashSet();
    // knight moves
    expectedMoves.add(new Move(new Position(0, 1), new Position(2, 0), null));
    expectedMoves.add(new Move(new Position(0, 1), new Position(2, 2), null));
    assertEquals(expectedMoves,
        stateExplorer.getPossibleMovesFromPosition(start, new Position(0, 1)));
  }

  @Test
  public void testGetPossibleMovesFromPosition_Promotion() {
    start.setPiece(new Position(1, 0), null);
    start.setPiece(new Position(6, 0), new Piece(Color.WHITE, PieceKind.PAWN));
    
    Set<Move> expectedMoves = Sets.newHashSet();
    // promotion moves
    expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1), PieceKind.BISHOP));
    expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1), PieceKind.KNIGHT));
    expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1), PieceKind.ROOK));
    expectedMoves.add(new Move(new Position(6, 0), new Position(7, 1), PieceKind.QUEEN));
    assertEquals(expectedMoves,
        stateExplorer.getPossibleMovesFromPosition(start, new Position(6, 0)));
  }
  /*
   * End Tests by Yoav Zibin <yoav.zibin@gmail.com>
   */
}
