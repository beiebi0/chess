package org.longjuntan.hw3;

import java.util.Date;
import java.util.List;

import org.longjuntan.hw3.Presenter.View;
import org.longjuntan.hw5.MoveAnimation;
import org.longjuntan.hw8.GameConstants;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.media.client.Audio;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Graphics extends Composite implements View {
        private static GameImages gameImages = GWT.create(GameImages.class);
        private static GraphicsUiBinder uiBinder = GWT
                        .create(GraphicsUiBinder.class);
        private static GameConstants constants = GWT.create(GameConstants.class);
        private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
        private SuggestBox contactsBox = new SuggestBox(oracle);
        
        interface GraphicsUiBinder extends UiBinder<Widget, Graphics> {
        }

        @UiField
        GameCss css;
        @UiField
        Label gameStatus;
        @UiField
        Label colorStatus;
        @UiField
        Grid gameGrid;

     
        @UiField
        Button inviteButton;
        @UiField
        ListBox matchList;
        @UiField
        Button autoMatch;
        // @UiField
        // Button restart;
        @UiField
        Button delete;
        // @UiField
        // Button save;
        // @UiField
        // Button load;
        // @UiField
        // VerticalPanel contactsPanel;
        @UiField
        FlowPanel suggestPanel;
        @UiField
        Grid promoteGrid;
        @UiField
        VerticalPanel myInfo;
        @UiField
        VerticalPanel gameInfo;
        @UiField
        VerticalPanel opponentInfo;
        @UiField
        Button aiPlay;

        private final int DURATION = 2000;

        private Image[][] board = new Image[8][8];
        private Presenter presenter;
        private Image[] promote = new Image[4];
        private Audio move;
        private Audio gameover;
//        private Storage storage = Storage.getLocalStorageIfSupported();



        public Graphics(List<String> list) {
                oracle.addAll(list);
//                      oracle = oracle2;
                initWidget(uiBinder.createAndBindUi(this));

                move = GameAudioFactory.createAudio("move");
                gameover = GameAudioFactory.createAudio("gameover");

                gameGrid.resize(8, 8);
                gameGrid.setCellPadding(0);
                gameGrid.setCellSpacing(0);
                gameGrid.setBorderWidth(0);
                promoteGrid.resize(0, 0);

             
                gameStatus.setText(constants.status());
                colorStatus.setText("");

                for (int row = 0; row < 8; row++) {
                        for (int col = 0; col < 8; col++) {
                                final Image image = new Image();
                                board[row][col] = image;

                                image.setWidth("100%");
                                image.setResource(gameImages.blank());
                                image.addDragOverHandler(new DragOverHandler() {

                                        @Override
                                        public void onDragOver(DragOverEvent event) {
                                                image.getElement().getStyle().clearBackgroundColor();
                                        }
                                });
                                if (row % 2 == 0 && col % 2 == 1 || row % 2 == 1
                                                && col % 2 == 0) {
                                        gameGrid.getCellFormatter().addStyleName(row, col,
                                                        css.blacktile());

                                } else {
                                        gameGrid.getCellFormatter().addStyleName(row, col,
                                                        css.whitetile());
                                }

                                final int i = State.ROWS - 1 - row;
                                final int j = col;

                                image.addClickHandler(new ClickHandler() {
                                        @Override
                                        public void onClick(ClickEvent event) {
                                                for (int m = 0; m <= 50; m++) {
                                                        image.setPixelSize(m, m);
                                                }
                                                promoteGrid.resize(0, 0);
                                                presenter.selectCell(i, j);
                                        }
                                });

                                gameGrid.setWidget(row, col, image);
                        }
                }
                

                History.addValueChangeHandler(new ValueChangeHandler<String>() {
                        @Override
                        public void onValueChange(ValueChangeEvent<String> event) {
                                String token = event.getValue();
                                if (token.isEmpty()) {
                                        presenter.setState(new State());
                                } else {
                                        presenter.setState(Utils.setStateByHistory(token));
                                }
                        }
                });

                inviteButton.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                                final String opponent = contactsBox.getText();
                                presenter.createMatch(opponent);

                        }
                });
                // contactsPanel.add(contactsBox);
                aiPlay.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                                presenter.createMatch("AIPlayer");

                        }
                });
                aiPlay.setText(constants.aiButton());
                
                suggestPanel.add(contactsBox);

                matchList.addChangeHandler(new ChangeHandler() {

                        @Override
                        public void onChange(ChangeEvent event) {
                                Window.alert("Select change");
                                System.out.println(getMatchIdFromList());
                                presenter.load(getMatchIdFromList());

                        }
                });
                delete.setText(constants.deleteButton());
                delete.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                                Window.alert("Click delete");
                                presenter.deleteCurrentMatch();
                                // History.newItem(presenter.getHistory());
                        }
                });
                
                inviteButton.setText(constants.inviteButton());
                autoMatch.setText(constants.autoMatchButton());
                myInfo.add(new Label(constants.myInfo()));

        } 

        @Override
        public void setMyInfo(String name, int rank) {
                myInfo.clear();

                myInfo.add(new Label(constants.myInfo()));
                myInfo.add(new Label(constants.name() + name));
                myInfo.add(new Label(constants.ranking() + rank));
        }

        @Override
        public void setOppoInfo(String name, int rank) {
                opponentInfo.clear();
                opponentInfo.add(new Label(constants.oppoInfo()));
                opponentInfo.add(new Label(constants.name() + name));
                opponentInfo.add(new Label(constants.ranking() + rank));

        }

        @Override
        public void setGameInfo(String id, Date date) {
                gameInfo.clear();
                gameInfo.add(new Label(constants.gameInfo()));
                gameInfo.add(new Label(constants.gameId() + id));
                gameInfo.add(new Label(constants.startTime()
                                + DateTimeFormat.getFormat(
                                                DateTimeFormat.PredefinedFormat.DATE_TIME_SHORT)
                                                .format(date)));

        }

        @Override
        public void clearGameNOppoInfo() {
                gameInfo.clear();
                opponentInfo.clear();
        }

        public String getMatchIdFromList() {
                return matchList.getValue(matchList.getSelectedIndex());
        }

        @Override
        public void setPiece(int row, int col, Piece piece) {
                final int i = row;
                final int j = col;
                final Piece p = piece;
                if (piece == null) {
                        board[row][col].setResource(gameImages.blank());
                        return;
                }
                final Image image = board[row][col];
                image.getElement().setDraggable(Element.DRAGGABLE_TRUE);

                // Add a DragStartHandler.
                image.addDragStartHandler(new DragStartHandler() {
                        public void onDragStart(DragStartEvent event) {
                                promoteGrid.resize(0, 0);
                                presenter.selectCell(State.ROWS - 1 - i, j);
                                // Required: set data for the event.
                                event.setData("text", p.toString());

                                int offsetX = event.getNativeEvent().getClientX()
                                                - image.getAbsoluteLeft();
                                int offsetY = event.getNativeEvent().getClientY()
                                                - image.getAbsoluteTop();

                                Element e = image.getElement();
                                // Optional: show a copy of the widget under cursor.
                                event.getDataTransfer().setDragImage(e, offsetX, offsetY);
                        }
                });

                if (piece.getColor().isWhite()) {
                        switch (piece.getKind()) {
                        case PAWN:
                                image.setResource(gameImages.whitePawn());
                                break;
                        case BISHOP:
                                image.setResource(gameImages.whiteBishop());
                                break;
                        case KING:
                                image.setResource(gameImages.whiteKing());
                                break;
                        case KNIGHT:
                                image.setResource(gameImages.whiteKnight());
                                break;
                        case QUEEN:
                                image.setResource(gameImages.whiteQueen());
                                break;
                        case ROOK:
                                image.setResource(gameImages.whiteRook());
                                break;
                        default:
                                break;
                        }
                } else {
                        switch (piece.getKind()) {
                        case PAWN:
                                image.setResource(gameImages.blackPawn());
                                break;
                        case BISHOP:
                                image.setResource(gameImages.blackBishop());
                                break;
                        case KING:
                                image.setResource(gameImages.blackKing());
                                break;
                        case KNIGHT:
                                image.setResource(gameImages.blackKnight());
                                break;
                        case QUEEN:
                                image.setResource(gameImages.blackQueen());
                                break;
                        case ROOK:
                                image.setResource(gameImages.blackRook());
                                break;
                        default:
                                break;
                        }
                }
        }

        /**
         * Set highlight for the selected position
         */
        @Override
        public void setHighlighted(int row, int col, boolean highlighted) {
                Element element = board[State.ROWS - 1 - row][col].getElement();
                if (highlighted) {
                        element.setClassName(css.highlighted());
                } else {
                        element.removeClassName(css.highlighted());
                }
        }

        /**
         * Highlight the possible moves for selected position
         */
        @Override
        public void setPossibleMoves(Position p, boolean possible) {
                final int row = State.ROWS - 1 - p.getRow();
                final int col = p.getCol();
                final Image image = board[row][col];

                image.addDropHandler(new DropHandler() {
                        @Override
                        public void onDrop(DropEvent event) {
                                // Prevent the native text drop.
                                event.preventDefault();
                                presenter.selectCell(State.ROWS - 1 - row, col);
                        }
                });

                Element element = board[row][col].getElement();
                if (possible) {
                        element.setClassName(css.possible());
                } else {
                        element.removeClassName(css.possible());
                }
        }

   
        @Override
        public void setColor(Color color) {
                colorStatus.setText("You are " + (color.isWhite() ? "White" : "Black")
                                + "!");
        }

        @Override
        public void setWhoseTurn(Boolean bool) {
                gameStatus.setText((bool ? constants.yourTurn() : constants
                                .othersTurn()));
        }

        @Override
        public void setGameResult(GameResult gameResult) {
                if (gameResult != null) {
                        gameover.play();
                        if (gameResult.getWinner() == null) {
                                gameStatus.setText(constants.drawBy()
                                                + gameResult.getGameResultReason() + "!");
                        } else {
                                gameStatus.setText((gameResult.getWinner().isWhite() ? "White"
                                                : "Black")
                                                + constants.winBy()
                                                + gameResult.getGameResultReason() + "!");
                        }
                }
        }

        @Override
        public void updateHistory() {
                History.newItem(presenter.getHistory());
                move.play();
        }

        @Override
        public void animation(Position p) {
                Image i = board[State.ROWS - 1 - p.getRow()][p.getCol()];
                MoveAnimation ma = new MoveAnimation(i);
                ma.run(DURATION);
        }

        public void setHistory(String state) {
                presenter.setState(Utils.setStateByHistory(state));
        }

        /**
         * Show the promotion grid
         */
        @Override
        public void setPromotion(Color c) {
                promoteGrid.resize(2, 4);
                promoteGrid.setText(0, 0, constants.promoteTo());

                int col = 0;
                for (final PieceKind k : new PieceKind[] { PieceKind.QUEEN,
                                PieceKind.BISHOP, PieceKind.KNIGHT, PieceKind.ROOK }) {
                        final Image image = new Image();
                        promote[col] = image;
                        image.setWidth("100%");
                        if (c == Color.BLACK) {
                                switch (k) {
                                case QUEEN:
                                        image.setResource(gameImages.blackQueen());
                                        break;
                                case KNIGHT:
                                        image.setResource(gameImages.blackKnight());
                                        break;
                                case BISHOP:
                                        image.setResource(gameImages.blackBishop());
                                        break;
                                case ROOK:
                                        image.setResource(gameImages.blackRook());
                                        break;
                                default:
                                        image.setResource(gameImages.blank());
                                        break;
                                }
                        } else {
                                switch (k) {
                                case QUEEN:
                                        image.setResource(gameImages.whiteQueen());
                                        break;
                                case KNIGHT:
                                        image.setResource(gameImages.whiteKnight());
                                        break;
                                case BISHOP:
                                        image.setResource(gameImages.whiteBishop());
                                        break;
                                case ROOK:
                                        image.setResource(gameImages.whiteRook());
                                        break;
                                default:
                                        image.setResource(gameImages.blank());
                                        break;
                                }
                        }

                        /**
                         * Set click handler for promotion selection
                         */
                        image.addClickHandler(new ClickHandler() {
                                @Override
                                public void onClick(ClickEvent event) {
                                        presenter.setPromotionKind(k);
                                        presenter.promote();
                                        promoteGrid.resize(0, 0);
                                }
                        });
                        promoteGrid.setWidget(1, col, image);
                        col++;
                }
        }

        public void setPresenter(Presenter p) {
                this.presenter = p;
        }

        @Override
        public void setMatchList(List<String> list, String matchId) {

                matchList.clear();
                for (String s : list) {
                        String[] entries = s.split(">");
                        matchList.addItem(entries[1], entries[0]);
                }
                // TODO Auto-generated method stub
        }

}