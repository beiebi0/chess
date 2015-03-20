package org.yoavzibin.hw2_5;

import java.util.Set;

import org.shared.chess.Move;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;

public class StateExplorerImpl implements StateExplorer {

  @Override
  public Set<Move> getPossibleMoves(State state) {
    Set<Move> res = Sets.newHashSet();
    Set<Position> start = getPossibleStartPositions(state);
    for (Position p : start) {
      res.addAll(getPossibleMovesFromPosition(state, p));
    }
    return res;
  }

  @Override
  public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
    /*ChessBoard chessBoard = new ChessBoard();
    return chessBoard.getPossibleMovesFromPosition(state, start);*/
    return null;
  }

  @Override
  public Set<Position> getPossibleStartPositions(State state) {
    /*ChessBoard chessBoard = new ChessBoard();
    return chessBoard.getPossibleStartPositions(state);*/
    return null;
  }
}
