package org.chenji.hw7;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

public class StateSerializer {
  public static State getStateFromString(String s) {
    String[] keys = s
        .substring(s.indexOf("["), s.lastIndexOf("]"))
        .split(
            "[\\]\\)]?(\\[|, )(turn|board|canCastleKingSide|canCastleQueenSide|numberOfMovesWithoutCaptureNorPawnMoved|enpassantPosition|gameResult)=[\\[\\(]?");
    Color color = (keys[1].equals("W") ? Color.WHITE : Color.BLACK);
    Piece[][] board = new Piece[State.ROWS][State.COLS];
    keys[2] = keys[2].substring(keys[2].indexOf("[") + 2,
        keys[2].lastIndexOf("]") - 1);
    int i = 0;
    for (String row : keys[2].split("[\\)\\]]*, [\\[\\(]*")) {
      PieceKind pk = null;
      if (row.startsWith("ROOK", 2)) {
        pk = PieceKind.ROOK;
      } else if (row.startsWith("KNIGHT", 2)) {
        pk = PieceKind.KNIGHT;
      } else if (row.startsWith("BISHOP", 2)) {
        pk = PieceKind.BISHOP;
      } else if (row.startsWith("QUEEN", 2)) {
        pk = PieceKind.QUEEN;
      } else if (row.startsWith("KING", 2)) {
        pk = PieceKind.KING;
      } else if (row.startsWith("PAWN", 2)) {
        pk = PieceKind.PAWN;
      } else {
      }
      board[i / 8][i % 8] = pk == null ? null : new Piece(
          (row.startsWith("W") ? Color.WHITE : Color.BLACK), pk);
      i++;
    }
    String[] castle = keys[3].split(", ");
    boolean[] canCastleKingSide = new boolean[] { Boolean.valueOf(castle[0]),
        Boolean.valueOf(castle[1]) };
    castle = keys[4].split(", ");
    boolean[] canCastleQueenSide = new boolean[] { Boolean.valueOf(castle[0]),
        Boolean.valueOf(castle[1]) };
    Position enpassantPosition = null;
    GameResult gameResult = null;
    int numberOfMovesWithoutCaptureNorPawnMoved = 0;
    if (keys.length == 8) {
      String[] enpassant = keys[5].split(",");
      enpassantPosition = new Position(Integer.valueOf(enpassant[0]),
          Integer.valueOf(enpassant[1]));
      String[] result = keys[6].split("=");
      GameResultReason reason = null;
      if (result[2].equals("CHECKMATE")) {
        reason = GameResultReason.CHECKMATE;
      } else if (result[3].equals("FIFTY_MOVE_RULE")) {
        reason = GameResultReason.FIFTY_MOVE_RULE;
      } else if (result[3].equals("THREEFOLD_REPETITION_RULE")) {
        reason = GameResultReason.THREEFOLD_REPETITION_RULE;
      } else if (result[3].equals("STALEMATE")) {
        reason = GameResultReason.STALEMATE;
      } else {
      }
      gameResult = new GameResult(result[1].startsWith("null") ? null
          : (result[1].startsWith("W") ? Color.WHITE : Color.BLACK), reason);
      numberOfMovesWithoutCaptureNorPawnMoved = Integer.parseInt(keys[7]);
    } else if (keys.length == 7) {
      if (keys[5].startsWith("GameResult")) {
        String[] result = keys[5].split("=");
        GameResultReason reason = null;
        if (result[2].equals("CHECKMATE")) {
          reason = GameResultReason.CHECKMATE;
        } else if (result[3].equals("FIFTY_MOVE_RULE")) {
          reason = GameResultReason.FIFTY_MOVE_RULE;
        } else if (result[3].equals("THREEFOLD_REPETITION_RULE")) {
          reason = GameResultReason.THREEFOLD_REPETITION_RULE;
        } else if (result[3].equals("STALEMATE")) {
          reason = GameResultReason.STALEMATE;
        } else {
        }
        gameResult = new GameResult(result[1].startsWith("null") ? null
            : (result[1].startsWith("W") ? Color.WHITE : Color.BLACK), reason);
      } else {
        String[] enpassant = keys[5].split(",");
        enpassantPosition = new Position(Integer.valueOf(enpassant[0]),
            Integer.valueOf(enpassant[1]));
      }
      numberOfMovesWithoutCaptureNorPawnMoved = Integer.parseInt(keys[6]);
    } else if (keys.length == 6) {
      numberOfMovesWithoutCaptureNorPawnMoved = Integer.parseInt(keys[5]);
    } else {
    }
    return new State(color, board, canCastleKingSide, canCastleQueenSide,
        enpassantPosition, numberOfMovesWithoutCaptureNorPawnMoved, gameResult);
  }
}
