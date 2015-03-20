package org.yoavzibin.hw2;

import org.shared.chess.Color;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;

public class StateChangerImpl implements StateChanger {
  public void makeMove(State state, Move move) throws IllegalMove {
    if (state.getGameResult() != null) {
      // Game already ended!
      throw new IllegalMove();
    }
    Position from = move.getFrom();
    Piece piece = state.getPiece(from);
    if (piece == null) {
      // Nothing to move!
      throw new IllegalMove();
    }
    Color color = piece.getColor();
    if (color != state.getTurn()) {
      // Wrong player moves!
      throw new IllegalMove();
    }
    /*ChessBoard chessBoard = new ChessBoard();
    chessBoard.makeMove(state, move);*/
  }
}
