package org.chenji.hw3;

import java.util.Set;
import java.util.Vector;

import org.chenji.hw2.MoveCheck;
import org.chenji.hw2.MoveCheckImpl;
import org.chenji.hw2.StateChangerImpl;
import org.chenji.hw2_5.StateExplorerImpl;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;

import com.google.common.collect.Sets;
import com.google.gwt.user.client.History;

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

    void setPromotion(Color color, int cor);
  }

  private View view;
  private State state;
  private Position selected;
  private Vector<State> history = new Vector<State>();
  public int currentState = 0;
  private Set<Position> possibleMoves = Sets.newHashSet();
  private StateExplorer stateExplorer = new StateExplorerImpl();
  private StateChanger stateChanger = new StateChangerImpl();
  private MoveCheck moveCheck = new MoveCheckImpl();
  private PieceKind promoteTo = null;
  public boolean waitingForPromotion = false;

  public void setView(View view) {
    this.view = view;
  }

  public void init() {
    state = new State();
  }

  public Presenter() {
    init();
    addHistory();
  }

  public void setState() {
    setState(state);
  }

  public void changeState(State state) {
    this.state = state;
  }
  public void setState(State state) {
    view.setWhoseTurn(state.getTurn());
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        view.setPiece(r, c, state.getPiece(r, c));
      }
    }
    view.setGameResult(state.getGameResult());
  }
  public void setPromoteTo(int row) {
    if (row == 3 || row == 10)
      promoteTo = PieceKind.QUEEN;
    else if(row == 2 || row == 11)
      promoteTo = PieceKind.KNIGHT;
    else if(row == 1 || row == 12)
      promoteTo = PieceKind.ROOK;
    else if(row == 0 || row == 13)
      promoteTo = PieceKind.BISHOP;
    else
      promoteTo = null;
  }

  public void restart() {
    init();
    setState();
    addHistory();
  }

  private void highlightPossibleMoves() {
    for (Move move : stateExplorer
        .getPossibleMovesFromPosition(state, selected)) {
      Position position = move.getTo();
      if (!possibleMoves.contains(position)) {
        view.setHighlighted(position.getRow(), position.getCol(), true);
        possibleMoves.add(position);
      }
    }
  }

  private void clearHighlight() {
    view.setHighlighted(selected.getRow(), selected.getCol(), false);
    for (Position position : possibleMoves) {
      view.setHighlighted(position.getRow(), position.getCol(), false);
    }
    possibleMoves.clear();
    selected = null;
  }

  private void selectPiece(Position position) {
    if (state.getPiece(position) != null
        && state.getPiece(position).getColor() == state.getTurn()) {
      selected = position;
      highlightPossibleMoves();
    }
  }

  public void setStateAt(int index) {
    state = history.elementAt(index).copy();
    setState();
  }

  public void addHistory() {
    history.addElement(state.copy());
    currentState ++;
  }

  private boolean isPawnPromotion(Move move) {
    return state.getPiece(move.getFrom()) != null
        && state.getPiece(move.getFrom()).getKind() == PieceKind.PAWN
        && move.getTo().getRow() == (state.getTurn().isWhite() ? State.ROWS - 1
            : 0) && promoteTo == null;
  }

  private void setPromoteOption(Color color, int col) {
    view.setPromotion(color, col);
  }

  public void promote() {
  }
  public Color getTurn() {
    return state.getTurn();
  }
  public void onClick(int row, int col) {
      row = row - 3;
      row = (row >= 0 ? row : 0);
      row = (row <= 7 ? row : 7);
      if (selected == null) {
        selectPiece(new Position(row, col));
      } else {
        Move move = new Move(selected, new Position(row, col), promoteTo);
        if (moveCheck.isCanMakeMove(state, move)) {
          if (isPawnPromotion(move)) {
            waitingForPromotion = true;
            setState();
            setPromoteOption(state.getTurn(), col);
            return;
          }
          stateChanger.makeMove(state, move);
          promoteTo = null;
          waitingForPromotion = false;
          addHistory();
          setState();
        }
        else {
          setState();
        }
        clearHighlight();
      }
    }
  
}
