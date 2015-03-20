package org.chenji.hw9;

import org.chenji.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.State;

public class Heuristic {
  public Iterable<Move> getOrderedMoves(State t) {
    return new StateExplorerImpl().getPossibleMoves(t);
  }
  
  public int getStateValue(State state) {
    int score = 0;
    if (state.getGameResult() != null) {
      if (state.getGameResult().getWinner() != null) {
        Color winner = state.getGameResult().getWinner();
        return winner.isWhite() ? 1000 : -1000;
      }
    }
    else {
      for (int r = 0; r < State.ROWS; r ++)
        for (int c = 0; c < State.COLS; c ++) {
          Piece piece = state.getPiece(r, c);
          if (piece != null)
            score += getScoreOf(piece) * (piece.getColor().isWhite() ? 1 : -1);
        }
    }
    return score;
  }
  
  private int getScoreOf(Piece piece) {
    switch (piece.getKind()) {
    case BISHOP: return 30;
    case KING: return 100;
    case KNIGHT: return 30;
    case PAWN: return 9;
    case QUEEN: return 100;
    case ROOK: return 55;
    default: return 0;
    }
  }

}
