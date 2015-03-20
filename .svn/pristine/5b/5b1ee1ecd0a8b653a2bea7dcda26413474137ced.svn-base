package org.chenji.hw2;


import org.chenji.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

public class ResultCheck {
  public static boolean isUnderCheck(State state, Position position, Color color) {
    if (position == null) {
      // king not found
      System.out.println("King position should not be null");
      return false;
    }
    int delt_r[] = { 1, 1, 0, -1, -1, -1, 0, 1 }; // start from 12:00, C.W.
    int delt_c[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
    for (int i = 0; i < 8; i++) {
      int n = 0;
      for (int r = position.getRow() + delt_r[i], c = position.getCol()
          + delt_c[i]; (r >= 0 && r < State.ROWS) && (c >= 0 && c < State.COLS); r = r
          + delt_r[i], c = c + delt_c[i], n++) {
        if (state.getPiece(r, c) != null) {
          Piece piece = state.getPiece(r, c);
          if (piece.getColor() == color.getOpposite()) {
            switch (piece.getKind()) {
            case KING:
              if (n > 0)
                break;
            case QUEEN:
              return true;
            case ROOK:
              if (i % 2 == 0) {
                return true;
              }
              break;
            case PAWN:
              if (n > 0 || delt_r[i] != (color.isWhite() ? 1 : -1))
                break;
            case BISHOP:
              if (i % 2 == 1) {
                return true;
              }
            case KNIGHT:
              break;
            default:
              break;
            }
          }
          break;
        }
      }
    }
    int knight_r[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int knight_c[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    for (int i = 0; i < 8; i++) {
      int r = position.getRow() + knight_r[i];
      int c = position.getCol() + knight_c[i];
      if ((r >= 0 && r < State.ROWS) && (c >= 0 && c < State.COLS)) {
        Piece piece = state.getPiece(position.getRow() + knight_r[i],
            position.getCol() + knight_c[i]);
        if (piece != null
            && piece.equals(new Piece(color.getOpposite(), PieceKind.KNIGHT))) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean isUnderCheck(State state, Color color) {
    Position kingPosition = null;
    for (int r = 0; r < State.ROWS; r++)
      for (int c = 0; c < State.COLS; c++) {
        if (state.getPiece(r, c) != null
            && state.getPiece(r, c).equals(new Piece(color, PieceKind.KING))) {
          kingPosition = new Position(r, c);
        }
      }
    return isUnderCheck(state, kingPosition, color);
  }

  public static GameResult getGameResult(State state) {
    if (state.getNumberOfMovesWithoutCaptureNorPawnMoved() >= 100) {
      return new GameResult(null, GameResultReason.FIFTY_MOVE_RULE);
    }
    StateExplorer stateExplorer = new StateExplorerImpl();
    if (stateExplorer.getPossibleMoves(state).isEmpty()) {
      if (isUnderCheck(state, state.getTurn())) {
        return new GameResult(state.getTurn().getOpposite(), GameResultReason.CHECKMATE);
      }
      else {
        return new GameResult(null, GameResultReason.STALEMATE);
      }
    }
    return null;
  }
}