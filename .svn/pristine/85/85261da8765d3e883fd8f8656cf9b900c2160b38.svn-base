package org.bohouli.hw3;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Piece;
import org.bohouli.hw3.Presenter.View;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Graphics extends Composite implements View {
	private static GameImages gameImages = GWT.create(GameImages.class);
	private static GraphicsUiBinder uiBinder = GWT
			.create(GraphicsUiBinder.class);

	interface GraphicsUiBinder extends UiBinder<Widget, Graphics> {
	}

	@UiField
	GameCss css;
	@UiField
	Label signInName;
	@UiField
	Label userRank;
	@UiField
	Label signInEmail;
	@UiField
	Anchor signOut;
	@UiField
	Label opponent;
	@UiField
	Button autoMatch;
	@UiField
	Button aiMatch;
	@UiField
	Button selectMatch;
	@UiField
	TextBox opponentBox;
	@UiField
	Button loadMatch;
	@UiField
	ListBox matchBox;
	@UiField
	Label matchTime;
	@UiField
	Label gameStatus;
	@UiField
	Grid gameGrid;
	@UiField
	Button gameSave;
	@UiField
	Button gameLoad;
	@UiField
	Button gameRestart;
	@UiField
	Button gameDelete;
	private Image[][] board = new Image[8][8];

	public Graphics() {
		initWidget(uiBinder.createAndBindUi(this));
		gameGrid.resize(8, 8);
		gameGrid.setCellPadding(0);
		gameGrid.setCellSpacing(0);
		gameGrid.setBorderWidth(1);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				final Image image = new Image();
				board[row][col] = image;
				image.setWidth("100%");

				if (row % 2 == 0 && col % 2 == 1 || row % 2 == 1
						&& col % 2 == 0) {
					image.setResource(gameImages.blackTile());
				} else {
					image.setResource(gameImages.whiteTile());
				}
				image.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				gameGrid.setWidget(row, col, image);
			}
		}
	}

	@Override
	public void setPiece(int row, int col, Piece piece) {
		if (piece != null) {
			switch (piece.getKind()) {
			case KING:
				if (piece.getColor().isWhite())
					board[row][col].setResource(gameImages.whiteKing());
				else
					board[row][col].setResource(gameImages.blackKing());
				break;
			case QUEEN:
				if (piece.getColor().isWhite())
					board[row][col].setResource(gameImages.whiteQueen());
				else
					board[row][col].setResource(gameImages.blackQueen());
				break;
			case ROOK:
				if (piece.getColor().isWhite())
					board[row][col].setResource(gameImages.whiteRook());
				else
					board[row][col].setResource(gameImages.blackRook());
				break;
			case BISHOP:
				if (piece.getColor().isWhite())
					board[row][col].setResource(gameImages.whiteBishop());
				else
					board[row][col].setResource(gameImages.blackBishop());
				break;
			case KNIGHT:
				if (piece.getColor().isWhite())
					board[row][col].setResource(gameImages.whiteKnight());
				else
					board[row][col].setResource(gameImages.blackKnight());
				break;
			case PAWN:
				if (piece.getColor().isWhite())
					board[row][col].setResource(gameImages.whitePawn());
				else
					board[row][col].setResource(gameImages.blackPawn());
				break;
			default:
				break;
			}
		} else {
			if (row % 2 == 0 && col % 2 == 1 || row % 2 == 1 && col % 2 == 0) {
				board[row][col].setResource(gameImages.blackTile());
			} else {
				board[row][col].setResource(gameImages.whiteTile());
			}
		}
	}
	
	@Override
	public void setPromotionPieces() {
		gameStatus.setText("Promote Pawn To Which Piece?");
		board[3][2].setResource(gameImages.greenRook());
		board[3][3].setResource(gameImages.greenKnight());
		board[3][4].setResource(gameImages.greenBishop());
		board[3][5].setResource(gameImages.greenQueen());
	}

	@Override
	public void setHighlighted(int row, int col, boolean highlighted) {
		Element element = board[row][col].getElement();
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
		} else {
			gameStatus.setText("White's Turn");
		}
	}

	@Override
	public void setGameResult(GameResult gameResult) {
		if (gameResult != null) {
			if (gameResult.getWinner() == null) {
				gameStatus.setText("It's A Tie, Game Result Reason: "
						+ gameResult.getGameResultReason());
			} else {
				String winner = gameResult.getWinner() == Color.BLACK ? "Black" : "White";
				gameStatus.setText("Winner: " + winner
						+ ", Game Result Reason: "
						+ gameResult.getGameResultReason());
			}
		}
	}
	
	
	@Override
	public Image getImage(int row, int col) {
		return board[row][col];
	}
	
	@Override
	public void setMatchTime(String time) {
		matchTime.setText(time);
	}
	
	@Override
	public HasClickHandlers getLoadMatchButton() {
		return loadMatch;
	}
	
	@Override
	public HasClickHandlers getRestartButton() {
		return gameRestart;
	}
	
	@Override
	public HasClickHandlers getSaveButton() {
		return gameSave;
	}
	
	@Override
	public HasClickHandlers getLoadButton() {
		return gameLoad;
	}
	
	@Override
	public HasClickHandlers getAutoButton() {
		return autoMatch;
	}
	
	@Override
	public HasClickHandlers getAIButton() {
		return aiMatch;
	}
	
	@Override
	public HasClickHandlers getDeleteButton() {
		return gameDelete;
	}
	
	@Override
	public HasClickHandlers getSelectMatchButton() {
		return selectMatch;
	}
	
	@Override
	public String getOpponentBox() {
		return opponentBox.getText();
	}
	
	@Override
	public void addMatch(String match) {
		matchBox.addItem(match);
	}
	@Override
	public void removeMatch() {
		matchBox.clear();
	}
	
	@Override
    public String getSelectedMatch() {
            return matchBox.getValue(matchBox.getSelectedIndex());
    }
	
	@Override
	public HasChangeHandlers getMatchBox() {
		return matchBox;
	}
	
	@Override
	public void setSignInName(String name) {
		signInName.setText(name);
	}
	
	@Override
	public void setSignInEmail(String email) {
		signInEmail.setText(email);
	}
	
	@Override
	public void setOpponent(String name) {
		opponent.setText(name);
	}
	
	@Override
	public void setSignOutLink(String href) {
		signOut.setHref(href);
	}
	
	@Override
	public void setSignOutText(String text) {
		signOut.setText(text);
	}
	
	@Override
	public void setAutoMatchString(String text) {
		autoMatch.setText(text);
	}
	
	@Override
	public void setAIMatchString(String text) {
		aiMatch.setText(text);
	}
	
	@Override
	public void setSelectMatchString(String text) {
		selectMatch.setText(text);
	}
	@Override
	public void setLoadMatchString(String text) {
		loadMatch.setText(text);
	}
	@Override
	public void setSaveString(String text) {
		gameSave.setText(text);
	}
	@Override
	public void setLoadString(String text) {
		gameLoad.setText(text);
	}
	@Override
	public void setRestartString(String text) {
		gameRestart.setText(text);
	}
	@Override
	public void setDeleteString(String text) {
		gameDelete.setText(text);
	}	
	@Override
	public void setUserRank(String text) {
		userRank.setText(text);
	}
}
