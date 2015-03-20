package org.chenji.hw2;

import org.shared.chess.Color;
import org.shared.chess.IllegalMove;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;

public class StateChangerImpl implements StateChanger {

  public void changeState(State state, Move move) {
    State prevState = state.copy();
    Piece piece = state.getPiece(move.getFrom());
    Color color = piece.getColor();
    // make move
    state.setPiece(move.getFrom(), null);
    state.setPiece(move.getTo(), (move.getPromoteToPiece() == null ? piece
        : new Piece(color, move.getPromoteToPiece())));
    switch (piece.getKind()) {
    case PAWN:
      if (prevState.getEnpassantPosition() != null
          && prevState.getEnpassantPosition().equals(
              new Position(move.getFrom().getRow(), move.getTo().getCol()))
          && prevState.getPiece(prevState.getEnpassantPosition()).getColor() == color
              .getOpposite()) {
        state.setPiece(prevState.getEnpassantPosition(), null);
      }
      break;
    case KING:
      if (move.getFrom().equals(
          new Position((color.isWhite() ? 0 : State.ROWS - 1), 4))
          && (move.getTo().equals(
              new Position((color.isWhite() ? 0 : State.ROWS - 1), 2)) || move
              .getTo().equals(
                  new Position((color.isWhite() ? 0 : State.ROWS - 1), 6)))) {
        state.setPiece((color.isWhite() ? 0 : State.ROWS - 1), (move.getTo()
            .getCol() == 2 ? 0 : 7), null);
        state.setPiece((color.isWhite() ? 0 : State.ROWS - 1), (move.getTo()
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
    switch (piece.getKind()) {
    case PAWN:
      if (move.getTo().getRow() - move.getFrom().getRow() == (color.isWhite() ? 2
          : -2)) {
        state.setEnpassantPosition(move.getTo());
      } else {
        state.setEnpassantPosition(null);
      }
      state.setNumberOfMovesWithoutCaptureNorPawnMoved(0);
      break;
    case BISHOP:
    case KING:
    case KNIGHT:
    case QUEEN:
    case ROOK:
    default:
      state.setEnpassantPosition(null);
      state.setNumberOfMovesWithoutCaptureNorPawnMoved((prevState.getPiece(move
          .getTo()) == null ? prevState
          .getNumberOfMovesWithoutCaptureNorPawnMoved() + 1 : 0));
      break;
    }

    // set canCastle
    switch (piece.getKind()) {
    case KING:
      state.setCanCastleKingSide(color, false);
      state.setCanCastleQueenSide(color, false);
      break;
    case ROOK:
      state.setCanCastleKingSide(
          color,
          move.getFrom().getRow() == (color.isWhite() ? 0 : State.ROWS - 1)
              && move.getFrom().getCol() == 7 ? false : state
              .isCanCastleKingSide(color));
      state.setCanCastleQueenSide(
          color,
          move.getFrom().getRow() == (color.isWhite() ? 0 : State.ROWS - 1)
              && move.getFrom().getCol() == 0 ? false : state
              .isCanCastleQueenSide(color));
      break;
    case PAWN:
    case BISHOP:
    case KNIGHT:
    case QUEEN:
    default:
      state.setCanCastleKingSide(color, prevState.isCanCastleKingSide(color));
      state.setCanCastleQueenSide(color, prevState.isCanCastleQueenSide(color));
      break;
    }
    if (prevState.getPiece(move.getTo()) != null
        && prevState.getPiece(move.getTo()).equals(
            new Piece(color.getOpposite(), PieceKind.ROOK))) {
      if (move.getTo().equals(
          new Position((color.isBlack() ? 0 : State.ROWS - 1), 7))) {
        state.setCanCastleKingSide(color.getOpposite(), false);
      }
      if (move.getTo().equals(
          new Position((color.isBlack() ? 0 : State.ROWS - 1), 0))) {
        state.setCanCastleQueenSide(color.getOpposite(), false);
      }
    }
    state.setTurn(color.getOpposite());
    state.setGameResult(ResultCheck.getGameResult(state));
  }

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
    Position to = move.getTo();
    if (from.getCol() < 0 || from.getCol() >= State.COLS || from.getRow() < 0
        || from.getRow() >= State.ROWS) {
      // From position outside the board
      throw new IllegalMove();
    }
    if (to.getCol() < 0 || to.getCol() >= State.COLS || to.getRow() < 0
        || to.getRow() >= State.ROWS) {
      // To position outside the board
      throw new IllegalMove();
    }
    MoveCheck moveCheck = new MoveCheckImpl();
    if (!moveCheck.isCanMakeMove(state, move)) {
      throw new IllegalMove();
    }
    changeState(state, move);
  }
}
