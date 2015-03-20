package org.chenji.hw3;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.chenji.hw3.Presenter.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Graphics extends Composite implements View {
  private static GameImages gameImages = GWT.create(GameImages.class);
  private static GraphicsUiBinder uiBinder = GWT.create(GraphicsUiBinder.class);

  interface GraphicsUiBinder extends UiBinder<Widget, Graphics> {
  }

  @UiField
  GameCss css;
  @UiField
  Grid boardGrid;
  @UiField
  Label gameStatus;
  @UiField
  AbsolutePanel gamePanel;
  @UiField
  Button restart;
  private Image[][] background = new Image[14][8];
  private final Image[][] board = new Image[14][8];
  private int currentHistory = 0;
  private int promoteCol = -1;
  private Grid gameGrid;
  private Presenter presenter;

  public Graphics(Presenter p) {
    initWidget(uiBinder.createAndBindUi(this));
    presenter = p;
    presenter.setView(this);
    boardGrid.resize(14, 8);
    boardGrid.setCellPadding(0);
    boardGrid.setCellSpacing(0);
    boardGrid.setBorderWidth(0);
    for (int row : new int[] { 0, 1, 2, 11, 12, 13 })
      for (int col = 0; col < 8; col++) {
        final Image image = new Image();
        background[row][col] = image;
        image.setWidth("100%");
        image.setResource(gameImages.empty());
        boardGrid.setWidget(row, col, image);
      }
    for (int row = 3; row < 11; row++) {
      for (int col = 0; col < 8; col++) {
        final Image image = new Image();
        background[row][col] = image;
        image.setWidth("100%");
        if (row % 2 == 0 && col % 2 == 1 || row % 2 == 1 && col % 2 == 0) {
          image.setResource(gameImages.blackTile());
        } else {
          image.setResource(gameImages.whiteTile());
        }
        boardGrid.setWidget(row, col, image);
      }
    }
    restart.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        presenter.restart();
        if (presenter.currentState > currentHistory) {
          History.newItem("page" + currentHistory++);
        }
      }
    });

    gameGrid = new Grid();
    gameGrid.resize(14, 8);
    gameGrid.setCellPadding(0);
    gameGrid.setCellSpacing(0);
    gameGrid.setBorderWidth(0);

    for (int row : new int[] { 0, 1, 2, 11, 12, 13 })
      for (int col = 0; col < 8; col++) {
        final Image image = new Image();
        board[row][col] = image;
        image.setWidth("100%");
        image.setResource(gameImages.empty());
        final int r = row;
        final int c = col;
        image.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            if (presenter.waitingForPromotion && c == promoteCol
                && (presenter.getTurn().isWhite() ? (r > 9) : (r < 4))) {
              presenter.setPromoteTo(r);
              presenter.onClick(r, c);
              if (presenter.currentState > currentHistory) {
                History.newItem("page" + currentHistory++);
              }
            }
          }
        });
        gameGrid.setWidget(row, col, image);
      }

    for (int row = 3; row < 11; row++) {
      for (int col = 0; col < 8; col++) {
        final Image image = new Image();
        board[row][col] = image;
        image.setWidth("100%");
        image.setResource(gameImages.empty());
        final int r = row;
        final int c = col;
        image.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            if (presenter.waitingForPromotion && c == promoteCol
                && (presenter.getTurn().isWhite() ? (r > 9) : (r < 4))) {
              presenter.setPromoteTo(r);
            }
            presenter.onClick(r, c);
            if (presenter.currentState > currentHistory) {
              History.newItem("page" + currentHistory++);
            }
          }
        });
        gameGrid.setWidget(row, col, image);
      }
    }

    gamePanel.add(gameGrid, 0, 0);
    presenter.setState();
    if (presenter.currentState > currentHistory) {
      History.newItem("page" + currentHistory++);
    }
  }

  public void setPromotion(Color color, int col) {
    init();
    promoteCol = col;
    int queenRow = (color.isWhite() ? 10 : 3);
    int knightRow = (color.isWhite() ? 11 : 2);
    int rookRow = (color.isWhite() ? 12 : 1);
    int bishopRow = (color.isWhite() ? 13 : 0);
    for (int row = (color.isWhite() ? 10 : 0); row < (color.isWhite() ? 14 : 4); row++) {
      background[row][col].setResource((row > 9 && col % 2 == 0 || row < 4
          && col % 2 == 1) ? gameImages.whiteTile() : gameImages.blackTile());
    }
    board[queenRow][col].setResource(color.isWhite() ? gameImages.whiteQueen()
        : gameImages.blackQueen());
    board[knightRow][col].setResource(color.isWhite() ? gameImages
        .whiteKnight() : gameImages.blackKnight());
    board[rookRow][col].setResource(color.isWhite() ? gameImages.whiteRook()
        : gameImages.blackRook());
    board[bishopRow][col].setResource(color.isWhite() ? gameImages
        .whiteBishop() : gameImages.blackBishop());
  }

  public void init() {
    promoteCol = -1;
    for (int row : new int[] { 0, 1, 2, 11, 12, 13 })
      for (int col = 0; col < 8; col++) {
        background[row][col].setResource(gameImages.empty());
        board[row][col].setResource(gameImages.empty());
      }
  }

  @Override
  public void setPiece(int r, int c, Piece piece) {
    init();
    int row = r + 3;
    int col = c;
    if (piece == null) {
      board[row][col].setResource(gameImages.empty());
      return;
    }
    switch (piece.getKind()) {
    case PAWN:
      board[row][col].setResource(piece.getColor().isWhite() ? gameImages
          .whitePawn() : gameImages.blackPawn());
      break;
    case ROOK:
      board[row][col].setResource(piece.getColor().isWhite() ? gameImages
          .whiteRook() : gameImages.blackRook());
      break;
    case KNIGHT:
      board[row][col].setResource(piece.getColor().isWhite() ? gameImages
          .whiteKnight() : gameImages.blackKnight());
      break;
    case BISHOP:
      board[row][col].setResource(piece.getColor().isWhite() ? gameImages
          .whiteBishop() : gameImages.blackBishop());
      break;
    case QUEEN:
      board[row][col].setResource(piece.getColor().isWhite() ? gameImages
          .whiteQueen() : gameImages.blackQueen());
      break;
    case KING:
      board[row][col].setResource(piece.getColor().isWhite() ? gameImages
          .whiteKing() : gameImages.blackKing());
      break;
    default:
      break;
    }
  }

  @Override
  public void setHighlighted(int row, int col, boolean highlighted) {
    Element element = board[row + 3][col].getElement();
    if (highlighted) {
      element.setClassName(css.highlighted());
    } else {
      element.removeClassName(css.highlighted());
    }
  }

  @Override
  public void setWhoseTurn(Color color) {
    if (color == Color.BLACK) {
      gameStatus.setText("Black's Turn");
    } else if (color == Color.WHITE) {
      gameStatus.setText("White's Turn");
    } else {
      gameStatus.setText("Draw");
    }
  }

  @Override
  public void setGameResult(GameResult gameResult) {
    if (gameResult != null) {
      if (gameResult.isDraw()) {
        gameStatus.setText("Draw: " + gameResult.getGameResultReason());
      } else if (gameResult.getWinner() == Color.BLACK) {
        gameStatus.setText("Black Win");
      } else if (gameResult.getWinner() == Color.WHITE) {
        gameStatus.setText("White Win");
      } else {
      }
    }
  }
}
