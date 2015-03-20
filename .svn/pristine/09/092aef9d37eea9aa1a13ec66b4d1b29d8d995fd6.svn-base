package org.chenji.hw6;

import java.util.Arrays;
import java.util.Set;
import java.util.Vector;

import org.chenji.hw2.MoveCheck;
import org.chenji.hw2.MoveCheckImpl;
import org.chenji.hw2.StateChangerImpl;
import org.chenji.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;

public class Presenter {
  public interface View {
    /**
     * Renders the piece at this position. If piece is null then the position is
     * empty.
     */
    void setPiece(int row, int col, Piece piece);

    /**
     * Turns the highlighting on or off at this cell. Cells that can be clicked
     * should be highlighted.
     */
    void setHighlighted(int row, int col, boolean highlighted);

    /**
     * Indicate whose turn it is.
     */
    void setWhoseTurn(Color color);

    /**
     * Indicate whether the game is in progress or over.
     */
    void setGameResult(GameResult gameResult);

    void setPromotion(Color color);

    void setSelected(int row, int col, boolean selected);
    
    void dragTo(int x, int y, Piece piece);
    
    void animation(Move move, Piece piece);
    
    void updateState(String state);
  }

  private View view;
  private State state;
  private Position selected;
  private Position currentMousePosition;
  public Set<Position> possibleMoves = Sets.newHashSet();
  private StateExplorer stateExplorer = new StateExplorerImpl();
  private StateChanger stateChanger = new StateChangerImpl();
  private MoveCheck moveCheck = new MoveCheckImpl();
  private PieceKind promoteTo = null;
  private Move promotionMove = null;
  public boolean waitingForPromotion = false;

  public void setView(View view) {
    this.view = view;
  }

  public void init() {
    state = new State();
  }

  public State getState() {
    return state;
  }

  public void setState() {
    view.setWhoseTurn(state.getTurn());
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        view.setPiece(r, c, state.getPiece(r, c));
      }
    }
    view.setGameResult(state.getGameResult());
    clearBoard();
    highlightPossibleMoves();
  }

  public void changeState(State state) {
    this.state = state;
  }

  public void setState(State state) {
    this.state = state;
    setState();
  }

  public void setPromoteTo(int col) {
    if (col == 0)
      promoteTo = PieceKind.QUEEN;
    else if (col == 1)
      promoteTo = PieceKind.KNIGHT;
    else if (col == 2)
      promoteTo = PieceKind.ROOK;
    else if (col == 3)
      promoteTo = PieceKind.BISHOP;
    else
      promoteTo = null;
  }

  public void restart() {
    init();
    setState();
  }

  private void highlightPossibleMoves() {
    if (selected != null) {
      for (Move move : stateExplorer.getPossibleMovesFromPosition(state,
          selected)) {
        Position position = move.getTo();
        if (!possibleMoves.contains(position)) {
          view.setHighlighted(position.getRow(), position.getCol(), true);
          possibleMoves.add(position);
        }
      }
    } else {
      for (Position position : stateExplorer.getPossibleStartPositions(state)) {
        view.setHighlighted(position.getRow(), position.getCol(), true);
        possibleMoves.add(position);
      }
    }
  }

  public State getStateFromString(String s) {
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

  private void clearHighlight() {
    for (Position position : possibleMoves) {
      view.setHighlighted(position.getRow(), position.getCol(), false);
    }
    possibleMoves.clear();
    if (selected != null) {
      view.setSelected(selected.getRow(), selected.getCol(), false);
    }
    if (currentMousePosition != null) {
      view.setSelected(currentMousePosition.getRow(), currentMousePosition.getCol(), false);
    }
  }

  public void clearBoard() {
    clearPromotion();
    clearHighlight();
    selected = null;
    currentMousePosition = null;
  }

  private void selectPiece(Position position) {
    if (state.getPiece(position) != null
        && state.getPiece(position).getColor() == state.getTurn() && possibleMoves.contains(position)) {
      clearHighlight();
      selected = position;
      view.setSelected(selected.getRow(), selected.getCol(), true);
      highlightPossibleMoves();
    }
  }

  private boolean isPawnPromotion(Move move) {
    return state.getPiece(move.getFrom()) != null
        && state.getPiece(move.getFrom()).getKind() == PieceKind.PAWN
        && move.getTo().getRow() == (state.getTurn().isWhite() ? State.ROWS - 1
            : 0) && promoteTo == null;
  }

  private void setPromoteOption(Color color) {
    view.setPromotion(color);
  }

  public void promote(int i) {
    switch (i) {
    case 0:
      promoteTo = PieceKind.QUEEN;
      break;
    case 1:
      promoteTo = PieceKind.KNIGHT;
      break;
    case 2:
      promoteTo = PieceKind.ROOK;
      break;
    case 3:
      promoteTo = PieceKind.BISHOP;
      break;
    default:
      break;
    }
    Move move = new Move(promotionMove.getFrom(), promotionMove.getTo(),
        promoteTo);
    stateChanger.makeMove(state, move);
    //clearBoard();
    setState();
  }

  private void clearPromotion() {
    waitingForPromotion = false;
    promoteTo = null;
    promotionMove = null;
    view.setPromotion(null);
  }

  public Color getTurn() {
    return state.getTurn();
  }

  public void animationClick(int row, int col) {
    if (selected == null) {
      selectPiece(new Position(row, col));
    } else {
      Move move = new Move(selected, new Position(row, col), promoteTo);
      if (moveCheck.isCanMakeMove(state, move)) {
        view.setPiece(selected.getRow(), selected.getCol(), null);
        view.animation(move, state.getPiece(selected));
      }
      else {
        clearBoard();
        highlightPossibleMoves();
        view.animation(move, null);
      }
    }
  }
  public void onClick(int row, int col) {
    if (selected == null) {
      selectPiece(new Position(row, col));
    } else {
      Move move = new Move(selected, new Position(row, col), promoteTo);
      if (moveCheck.isCanMakeMove(state, move)) {
        if (isPawnPromotion(move)) {
          waitingForPromotion = true;
          promotionMove = move;
          setPromoteOption(state.getTurn());
          return;
        }
        stateChanger.makeMove(state, move);
        view.updateState(state.toString());
        setState();
      }
      else {
        clearBoard();
        highlightPossibleMoves();
      }
    }
  }

  public void mouseOver(int row, int col) {
    view.setSelected(row, col, true);
  }

  public void mouseOut(int row, int col) {
    view.setSelected(row, col, false);
  }
  public void mouseDown(int row, int col) {
    onClick(row, col);
  }
  public void mouseMove(int x, int y) {
    if (selected == null)
      return;
    Piece piece = state.getPiece(selected);
    view.dragTo(x, y, piece);
    Position moveTo = new Position(y/50, x/50);
    if (currentMousePosition != null && (currentMousePosition.getRow() != y/50 || currentMousePosition.getCol() != x/50)) {
      view.setSelected(currentMousePosition.getRow(), currentMousePosition.getCol(), false);
      view.setHighlighted(currentMousePosition.getRow(), currentMousePosition.getCol(), true);
    }
    if (possibleMoves.contains(moveTo)) {
      view.setSelected(y/50, x/50, true);
      currentMousePosition = moveTo;
    }
  }
}
