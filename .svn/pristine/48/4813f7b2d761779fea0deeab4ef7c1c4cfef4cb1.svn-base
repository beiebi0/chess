package org.chenji.hw7;

import org.shared.chess.Move;
import org.shared.chess.Position;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.SimpleDropController;

/**
 * DropController which allows a widget to be dropped on a SimplePanel drop
 * target when the drop target does not yet have a child widget.
 */
public class SetWidgetDropController extends SimpleDropController {

  private final SimplePanel dropTarget;
  private final int row;
  private final int col;
  private final Presenter presenter;
  private Image[][] board;

  public SetWidgetDropController(SimplePanel dropTarget, int row, int col,
      Image[][] board, Presenter presenter) {
    super(dropTarget);
    this.dropTarget = dropTarget;
    this.row = row;
    this.col = col;
    this.board = board;
    this.presenter = presenter;
  }

  @Override
  public void onDrop(DragContext context) {
    // dropTarget.setWidget(context.draggable);
    presenter.onClick(row, col);
    super.onDrop(context);
  }

  @Override
  public void onPreviewDrop(DragContext context) throws VetoDragException {
    Image img = (Image) context.draggable;
    int r = 0;
    int c = 0;
    for (int i = 0; i < 8; i++)
      for (int j = 0; j < 8; j++) {
        if (board[i][j].equals(img)) {
          // System.out.println(r + " " + c);
          r = i;
          c = j;
        }
      }
    Move move = new Move(new Position(r, c), new Position(row, col), null);
    if (move == null || !presenter.canMakeMove(move)) {
      throw new VetoDragException();
    } else
      presenter.onClick(r, c);
    super.onPreviewDrop(context);
  }
}