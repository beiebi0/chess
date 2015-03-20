package org.chenji.hw2;

import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

public class MoveCheckImpl implements MoveCheck {
  public State stateChanger(State state, Move move) {
    State newState = state.copy();
    Piece piece = state.getPiece(move.getFrom());
    Color color = piece.getColor();
    // make move
    newState.setPiece(move.getFrom(), null);
    newState.setPiece(move.getTo(), (move.getPromoteToPiece() == null ? piece
        : new Piece(color, move.getPromoteToPiece())));
    switch (piece.getKind()) {
    case PAWN:
      if (state.getEnpassantPosition() != null
          && state.getEnpassantPosition().equals(
              new Position(move.getFrom().getRow(), move.getTo().getCol()))
          && state.getPiece(state.getEnpassantPosition()).getColor() == color
              .getOpposite()) {
        newState.setPiece(state.getEnpassantPosition(), null);
      }
      break;
    case KING:
      if (move.getFrom().equals(
          new Position((color.isWhite() ? 0 : State.ROWS - 1), 4))
          && (move.getTo().equals(
              new Position((color.isWhite() ? 0 : State.ROWS - 1), 2)) || move
              .getTo().equals(
                  new Position((color.isWhite() ? 0 : State.ROWS - 1), 6)))) {
        newState.setPiece((color.isWhite() ? 0 : State.ROWS - 1), (move.getTo()
            .getCol() == 2 ? 0 : 7), null);
        newState.setPiece((color.isWhite() ? 0 : State.ROWS - 1), (move.getTo()
            .getCol() == 2 ? 3 : 5), new Piece(color, PieceKind.ROOK));
      }
      break;
    case ROOK:
      break;
    case QUEEN:
      break;
    case BISHOP:
      break;
    case KNIGHT:
      break;
    default:
      break;
    }

    // set enpassant position and numberOfMovesWithoutCaptureNorPawnMoved
    /*
     * switch (piece.getKind()) { case PAWN: if (move.getTo().getRow() -
     * move.getFrom().getRow() == (color.isWhite() ? 2 : -2)) {
     * newState.setEnpassantPosition(move.getTo()); } else {
     * newState.setEnpassantPosition(null); }
     * newState.setNumberOfMovesWithoutCaptureNorPawnMoved(0); break; case
     * BISHOP: case KING: case KNIGHT: case QUEEN: case ROOK: default:
     * newState.setEnpassantPosition(null);
     * newState.setNumberOfMovesWithoutCaptureNorPawnMoved((state.getPiece(move
     * .getTo()) == null ? state .getNumberOfMovesWithoutCaptureNorPawnMoved() +
     * 1 : 0)); break; }
     */

    // set canCastle
    /*
     * switch (piece.getKind()) { case KING:
     * newState.setCanCastleKingSide(color, false);
     * newState.setCanCastleQueenSide(color, false); break; case ROOK:
     * newState.setCanCastleKingSide( color, move.getFrom().getRow() ==
     * (color.isWhite() ? 0 : State.ROWS - 1) && move.getFrom().getCol() == 7 ?
     * false : state .isCanCastleKingSide(color));
     * newState.setCanCastleQueenSide( color, move.getFrom().getRow() ==
     * (color.isWhite() ? 0 : State.ROWS - 1) && move.getFrom().getCol() == 0 ?
     * false : state .isCanCastleQueenSide(color)); break; case PAWN: case
     * BISHOP: case KNIGHT: case QUEEN: default:
     * newState.setCanCastleKingSide(color, state.isCanCastleKingSide(color));
     * newState.setCanCastleQueenSide(color, state.isCanCastleQueenSide(color));
     * break; } if (state.getPiece(move.getTo()) != null &&
     * state.getPiece(move.getTo()).equals( new Piece(color.getOpposite(),
     * PieceKind.ROOK))) { if (move.getTo().equals( new
     * Position((color.isBlack() ? 0 : State.ROWS - 1), 7))) {
     * newState.setCanCastleKingSide(color.getOpposite(), false); } if
     * (move.getTo().equals( new Position((color.isBlack() ? 0 : State.ROWS -
     * 1), 0))) { newState.setCanCastleQueenSide(color.getOpposite(), false); }
     * }
     */
    newState.setTurn(color.getOpposite());
    state = newState.copy();
    return newState;
  }

  public boolean isUnderCheckAfterMove(State state, Move move) {
    return ResultCheck.isUnderCheck(stateChanger(state, move), state.getTurn());
  }


  public boolean isCanPawnMove(State state, Move move) {
    Position from = move.getFrom();
    Position to = move.getTo();
    Piece piece = state.getPiece(from);
    Color color = piece.getColor();
    if (to.getRow() == from.getRow()) {
      // no movement
      return false;
    }
    if (move.getPromoteToPiece() != null) {
      if (move.getTo().getRow() != (color.isWhite() ? State.ROWS - 1 : 0)) {
        return false;
      }
      if (move.getPromoteToPiece() == PieceKind.KING
          || move.getPromoteToPiece() == PieceKind.PAWN) {
        return false;
      }
    }
    // non-capturing
    if (to.getCol() == from.getCol()) {
      if (to.getRow() - from.getRow() == (color.isWhite() ? 2 : -2)) {
        // move two squares
        if (from.getRow() != (color.isWhite() ? 1 : State.ROWS - 2)) {
          // The pawn must have not moved
          return false;
        }
        if (state.getPiece(from.getRow() + (color.isWhite() ? 1 : -1),
            from.getCol()) != null
            || state.getPiece(to) != null) {
          // square occupied
          return false;
        }
        return !isUnderCheckAfterMove(state, move);
      } else if (to.getRow() - from.getRow() == (color.isWhite() ? 1 : -1)) {
        // move one square
        if (state.getPiece(to) != null) {
          // square occupied
          return false;
        }
        return !isUnderCheckAfterMove(state, move);
      } else {
        return false;
      }
    }
    // capturing
    else {
      if (to.getRow() - from.getRow() != (color.isWhite() ? 1 : -1)) {
        // Not move over one row
        return false;
      }
      if (to.getCol() - from.getCol() != 1 && to.getCol() - from.getCol() != -1) {
        // Not move over one column
        return false;
      }
      if (state.getPiece(to) != null && state.getPiece(to).getColor() == color) {
        // Capturing piece of the same color
        return false;
      }
      if (state.getPiece(to) == null) {
        if (state.getEnpassantPosition() == null) {
          // Nothing to capture
          return false;
        } else if (state.getEnpassantPosition().getRow() != from.getRow()
            || state.getEnpassantPosition().getCol() != to.getCol()) {
          // Wrong enpassant position
          return false;
        } else {
          // can capture enpassant
        }
      }
      return !isUnderCheckAfterMove(state, move);
    }
  }

  public boolean isCanKingMove(State state, Move move) {
    Position from = move.getFrom();
    Position to = move.getTo();
    Piece piece = state.getPiece(from);
    Color color = piece.getColor();
    if (move.getPromoteToPiece() != null) {
      // illegal promotion
      return false;
    }
    if (to.getRow() == from.getRow() && to.getCol() == from.getCol()) {
      // no movement
      return false;
    }

    if (move.getFrom().equals(
        new Position((color.isWhite() ? 0 : State.ROWS - 1), 4))
        && (move.getTo().equals(
            new Position((color.isWhite() ? 0 : State.ROWS - 1), 2)) || move
            .getTo().equals(
                new Position((color.isWhite() ? 0 : State.ROWS - 1), 6)))) {
      // castling
      if (!(move.getTo().getCol() == 2 ? state.isCanCastleQueenSide(color)
          : state.isCanCastleKingSide(color))) {
        // cannot castling
        return false;
      }
      int queenSide[] = { 3, 2, 1 };
      int kingSide[] = { 5, 6 };
      for (int i = 0; i < (move.getTo().getCol() == 2 ? 3 : 2); i++) {
        if (state.getPiece((color.isWhite() ? 0 : State.ROWS - 1), (move
            .getTo().getCol() == 2 ? queenSide[i] : kingSide[i])) != null) {
          // path blocked
          return false;
        }
      }
      int p[] = { 4, (4 + move.getTo().getCol()) / 2, move.getTo().getCol() };
      for (int i = 0; i < 3; i++) {
        if (ResultCheck.isUnderCheck(state, new Position((color.isWhite() ? 0
            : State.ROWS - 1), p[i]), color)) {
          // path under check
          return false;
        }
      }
    } else {
      if (to.getRow() - from.getRow() > 1 || to.getRow() - from.getRow() < -1
          || to.getCol() - from.getCol() > 1
          || to.getCol() - from.getCol() < -1) {
        // more than one square
        return false;
      }
      if (state.getPiece(to) != null && state.getPiece(to).getColor() == color) {
        // Occupied
        return false;
      }
    }
    return !isUnderCheckAfterMove(state, move);
  }

  public boolean isCanRookMove(State state, Move move) {
    Position from = move.getFrom();
    Position to = move.getTo();
    Piece piece = state.getPiece(from);
    Color color = piece.getColor();
    if (move.getPromoteToPiece() != null) {
      // illegal promotion
      return false;
    }
    if (to.getRow() == from.getRow() && to.getCol() == from.getCol()) {
      // no movement
      return false;
    }
    if (to.getRow() - from.getRow() != 0 && to.getCol() - from.getCol() != 0) {
      // Both row and column changed
      return false;
    }
    int itr_r = (to.getRow() > from.getRow() ? 1 : (to.getRow() == from
        .getRow() ? 0 : -1));
    int itr_c = (to.getCol() > from.getCol() ? 1 : (to.getCol() == from
        .getCol() ? 0 : -1));
    for (int i = from.getRow() + itr_r, j = from.getCol() + itr_c; i != to
        .getRow() || j != to.getCol(); i = i + itr_r, j = j + itr_c) {
      if (state.getPiece(i, j) != null) {
        // Square occupied in the way
        return false;
      }
    }
    if (state.getPiece(to) != null && state.getPiece(to).getColor() == color) {
      // Occupied
      return false;
    }
    return !isUnderCheckAfterMove(state, move);
  }

  public boolean isCanBishopMove(State state, Move move) {
    Position from = move.getFrom();
    Position to = move.getTo();
    Piece piece = state.getPiece(from);
    Color color = piece.getColor();
    if (move.getPromoteToPiece() != null) {
      // illegal promotion
      return false;
    }
    if (to.getRow() == from.getRow() && to.getCol() == from.getCol()) {
      // no movement
      return false;
    }
    if (to.getRow() - from.getRow() != to.getCol() - from.getCol()
        && to.getRow() - from.getRow() != from.getCol() - to.getCol()) {
      // not diagonal
      return false;
    }
    int itr_r = (to.getRow() > from.getRow() ? 1 : (to.getRow() == from
        .getRow() ? 0 : -1));
    int itr_c = (to.getCol() > from.getCol() ? 1 : (to.getCol() == from
        .getCol() ? 0 : -1));
    for (int i = from.getRow() + itr_r, j = from.getCol() + itr_c; i != to
        .getRow() || j != to.getCol(); i = i + itr_r, j = j + itr_c) {
      if (state.getPiece(i, j) != null) {
        // Square occupied in the way
        return false;
      }
    }
    if (state.getPiece(to) != null && state.getPiece(to).getColor() == color) {
      // Occupied
      return false;
    }
    return !isUnderCheckAfterMove(state, move);
  }

  public boolean isCanQueenMove(State state, Move move) {
    Position from = move.getFrom();
    Position to = move.getTo();
    Piece piece = state.getPiece(from);
    Color color = piece.getColor();
    if (move.getPromoteToPiece() != null) {
      // illegal promotion
      return false;
    }
    if (to.getRow() == from.getRow() && to.getCol() == from.getCol()) {
      // no movement
      return false;
    }
    if (to.getRow() - from.getRow() != to.getCol() - from.getCol()
        && to.getRow() - from.getRow() != from.getCol() - to.getCol()
        && to.getRow() - from.getRow() != 0 && to.getCol() - from.getCol() != 0) {
      // both rook and bishop cases
      return false;
    }
    int itr_r = (to.getRow() > from.getRow() ? 1 : (to.getRow() == from
        .getRow() ? 0 : -1));
    int itr_c = (to.getCol() > from.getCol() ? 1 : (to.getCol() == from
        .getCol() ? 0 : -1));
    for (int i = from.getRow() + itr_r, j = from.getCol() + itr_c; i != to
        .getRow() || j != to.getCol(); i = i + itr_r, j = j + itr_c) {
      if (state.getPiece(i, j) != null) {
        // Square occupied in the way
        return false;
      }
    }
    if (state.getPiece(to) != null && state.getPiece(to).getColor() == color) {
      // Occupied
      return false;
    }
    return !isUnderCheckAfterMove(state, move);
  }

  public boolean isCanKnightMove(State state, Move move) {
    Position from = move.getFrom();
    Position to = move.getTo();
    Piece piece = state.getPiece(from);
    Color color = piece.getColor();
    if (move.getPromoteToPiece() != null) {
      // illegal promotion
      return false;
    }
    if (to.getRow() == from.getRow() && to.getCol() == from.getCol()) {
      // no movement
      return false;
    }
    if (to.getRow() - from.getRow() == 1 || to.getRow() - from.getRow() == -1) {
      if (to.getCol() - from.getCol() != 2 && to.getCol() - from.getCol() != -2) {
        return false;
      }
    } else if (to.getRow() - from.getRow() == 2
        || to.getRow() - from.getRow() == -2) {
      if (to.getCol() - from.getCol() != 1 && to.getCol() - from.getCol() != -1) {
        return false;
      }
    } else {
      return false;
    }
    if (state.getPiece(to) != null && state.getPiece(to).getColor() == color) {
      // Occupied
      return false;
    }
    return !isUnderCheckAfterMove(state, move);
  }

  public boolean isCanMakeMove(State state, Move move) {
    if (state.getGameResult() != null)
      return false;
    Piece piece = state.getPiece(move.getFrom());
    switch (piece.getKind()) {
    case PAWN:
      return isCanPawnMove(state, move);
    case KING:
      return isCanKingMove(state, move);
    case ROOK:
      return isCanRookMove(state, move);
    case BISHOP:
      return isCanBishopMove(state, move);
    case QUEEN:
      return isCanQueenMove(state, move);
    case KNIGHT:
      return isCanKnightMove(state, move);
    default:
      return false;
    }
  }

}
