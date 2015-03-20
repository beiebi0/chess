package org.chenji.hw2_5;

import java.util.Set;

import org.chenji.hw2.MoveCheck;
import org.chenji.hw2.MoveCheckImpl;
import org.shared.chess.Color;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import static org.shared.chess.PieceKind.*;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;

public class StateExplorerImpl implements StateExplorer {

  @Override
  public Set<Move> getPossibleMoves(State state) {
    Set<Move> moves = Sets.newHashSet();
    for (int r = 0; r < State.ROWS; r ++)
      for (int c = 0; c < State.COLS; c ++) {
        Piece piece = state.getPiece(r, c);
        if (piece != null && piece.getColor() == state.getTurn()) {
          moves.addAll(getPossibleMovesFromPosition(state, new Position(r, c)));
        }
      }
    return moves;
  }

  @Override
  public Set<Move> getPossibleMovesFromPosition(State state, Position start) {
    if (start == null) {
      return null;
    }
    if (state.getPiece(start) == null) {
      return null;
    }
    Set<Move> moves = Sets.newHashSet();
    Piece piece = state.getPiece(start);
    Color color = piece.getColor();
    MoveCheck moveCheck = new MoveCheckImpl();
    switch (piece.getKind()) {
    case QUEEN:
      int queen_r[] = { 1, 1, 0, -1, -1, -1, 0, 1 }; // start from 12:00, C.W.
      int queen_c[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
      for (int i = 0; i < 8; i++)
        for (int r = start.getRow() + queen_r[i], c = start.getCol()
            + queen_c[i]; (r >= 0 && r < State.ROWS)
            && (c >= 0 && c < State.COLS); r = r + queen_r[i], c = c
            + queen_c[i]) {
          Move move = new Move(start, new Position(r, c), null);
          if (moveCheck.isCanMakeMove(state, move)) {
            moves.add(move);
          }
        }
      break;
    case ROOK:
      int rook_r[] = { 1, 0, -1, 0}; 
      int rook_c[] = { 0, 1, 0, -1};
      for (int i = 0; i < 4; i++)
        for (int r = start.getRow() + rook_r[i], c = start.getCol()
            + rook_c[i]; (r >= 0 && r < State.ROWS)
            && (c >= 0 && c < State.COLS); r = r + rook_r[i], c = c
            + rook_c[i]) {
          Move move = new Move(start, new Position(r, c), null);
          if (moveCheck.isCanMakeMove(state, move)) {
            moves.add(move);
          }
        }
      break;
    case BISHOP:
      int bishop_r[] = {1, -1, -1, 1};
      int bishop_c[] = {1, 1, -1,-1};
      for (int i = 0; i < 4; i++)
        for (int r = start.getRow() + bishop_r[i], c = start.getCol()
            + bishop_c[i]; (r >= 0 && r < State.ROWS)
            && (c >= 0 && c < State.COLS); r = r + bishop_r[i], c = c
            + bishop_c[i]) {
          Move move = new Move(start, new Position(r, c), null);
          if (moveCheck.isCanMakeMove(state, move)) {
            moves.add(move);
          }
        }
      break;
    case KING:
      int king_r[] = { 1, 1, 0, -1, -1, -1, 0, 1, 0, 0 };
      int king_c[] = { 0, 1, 1, 1, 0, -1, -1, -1, 2, -2 };
      for (int i = 0; i < 10; i++) {
        int r = start.getRow() + king_r[i];
        int c = start.getCol() + king_c[i];
        if ((r >= 0 && r < State.ROWS) && (c >= 0 && c < State.COLS)) {
          Move move = new Move(start, new Position(r, c), null);
          if (moveCheck.isCanMakeMove(state, move)) {
            moves.add(move);
          }
        }
      }
      break;
    case PAWN:
      int pawn_r[] = { 1, 2, 1, 1};
      int pawn_c[] = { 0, 0, 1, -1};
      PieceKind kinds[] = {QUEEN, ROOK, BISHOP, KNIGHT};
      for (int i = 0; i < 4; i++) {
        int r = start.getRow() + pawn_r[i] * (color.isWhite() ? 1 : -1);
        int c = start.getCol() + pawn_c[i];
        if ((r >= 0 && r < State.ROWS) && (c >= 0 && c < State.COLS)) {
          if (r == (color.isWhite() ? State.ROWS - 1 : 0)) {
            for (int j = 0; j < 4; j ++) {
              Move move = new Move(start, new Position(r, c), kinds[j]);
              if (moveCheck.isCanMakeMove(state, move)) {
                moves.add(move);
              }
            }
          }
          else {
            Move move = new Move(start, new Position(r, c), null);
            if (moveCheck.isCanMakeMove(state, move)) {
              moves.add(move);
            }
          }
        }
      }
      break;
    case KNIGHT:
      int knight_r[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
      int knight_c[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
      for (int i = 0; i < 8; i++) {
        int r = start.getRow() + knight_r[i];
        int c = start.getCol() + knight_c[i];
        if ((r >= 0 && r < State.ROWS) && (c >= 0 && c < State.COLS)) {
          Move move = new Move(start, new Position(r, c), null);
          if (moveCheck.isCanMakeMove(state, move)) {
            moves.add(move);
          }
        }
      }
      break;
    default:
      break;
    }
    return moves;
  }

  @Override
  public Set<Position> getPossibleStartPositions(State state) {
    Set<Move> moves = getPossibleMoves(state);
    Set<Position> positions = Sets.newHashSet();
    for (Move move:moves) {
      positions.add(move.getFrom());
    }
    return positions;
  }
}
