package org.chenji.hw7;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.chenji.hw7.Presenter.View;
import org.chenji.hw7.client.GameService;
import org.chenji.hw7.client.GameServiceAsync;
import org.chenji.hw7.client.LoginInfo;
import org.chenji.hw7.client.Match;
import org.chenji.hw7.client.Player;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.animation.client.Animation;
import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.ChannelError;
import com.google.gwt.appengine.channel.client.ChannelFactoryImpl;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Timer;

public class Graphics extends Composite implements View {
  private static GameImages gameImages = GWT.create(GameImages.class);
  private static GraphicsUiBinder uiBinder = GWT.create(GraphicsUiBinder.class);

  interface GraphicsUiBinder extends UiBinder<Widget, Graphics> {
  }

  private GameServiceAsync gameService;
  private Channel channel;
  private SocketListener socketListener;
  private ChessConstants constants = new ChessConstants();
  private ChessMessages messages = new ChessMessages();
  private LoginInfo loginInfo;

  @UiField
  GameCss css;
  @UiField
  Grid boardGrid;
  @UiField
  Label playerStatus;
  @UiField
  Label gameInfo;
  @UiField
  Label gameStatus;
  @UiField
  AbsolutePanel gamePanel;
  @UiField
  Button restart;
  // @UiField
  // Button save;
  @UiField
  Button AI;
  @UiField
  Button load;
  @UiField
  Button delete;
  @UiField
  ListBox stateList;
  @UiField
  Button invite;
  @UiField
  Button autoMatch;
  @UiField
  SuggestBox opponentEmail;
  @UiField
  Grid promotion;
  private Image[][] background = new Image[8][8];
  private final Image[][] board = new Image[8][8];
  private Image[] promotionImgs = new Image[4];
  private Grid gameGrid;
  private Presenter presenter;
  private boolean isMouseDown = false;
  private int reletiveX = 0;
  private int reletiveY = 0;
  private Image animationImage = new Image();
  private final int DURATION = 1000;
  private boolean animationOn = false;
  private Player player = null;
  // private List<Match> matchList = new ArrayList<Match>();
  public int currentMatchId = 0;
  private Match currentMatch = null;
  private boolean isWaiting = true;
  private boolean playingWithAI = false;
  private Audio sound;
  private PickupDragController dragController;
  private Image draggingImage = new Image();
  private Storage storage = Storage.getLocalStorageIfSupported();
  private Timer timer;
  private boolean isConnectionLost = false;

  public class ChangePositionAnimation extends Animation {
    private Position start;
    private Position end;
    private Move move;

    public ChangePositionAnimation(Position start, Position end, Move move) {
      this.start = new Position(start.getRow() * 50, start.getCol() * 50);
      this.end = new Position(end.getRow() * 50, end.getCol() * 50);
      this.move = move;
    }

    @Override
    protected void onUpdate(double progress) {
      Position position = extractProportionalLength(progress);
      animationImage.removeFromParent();
      // System.out.println(position.getCol() + " " + position.getRow());
      gamePanel.add(animationImage, position.getCol(), position.getRow());
    }

    @Override
    protected void onComplete() {
      super.onComplete();
      presenter.makeMove(move);
      animationImage.removeFromParent();
      animationOn = false;
    }

    private Position extractProportionalLength(double progress) {
      int x = (int) (start.getCol() - (start.getCol() - end.getCol())
          * progress);
      int y = (int) (start.getRow() - (start.getRow() - end.getRow())
          * progress);
      return new Position(y, x);
    }
  }

  public Graphics(Presenter p, LoginInfo loginInfo) {
    initWidget(uiBinder.createAndBindUi(this));
    this.loginInfo = loginInfo;
    dragController = new PickupDragController(gamePanel, false);
    // this.dragController = dragController1;
    dragController.setBehaviorMultipleSelection(false);
    dragController.setBehaviorDragStartSensitivity(5);
    dragController.setBehaviorBoundaryPanelDrop(false);
    presenter = p;
    presenter.setView(this);
    boardGrid.resize(8, 8);
    boardGrid.setCellPadding(0);
    boardGrid.setCellSpacing(0);
    boardGrid.setBorderWidth(0);
    for (int row = 0; row < 8; row++) {
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

    gameGrid = new Grid();
    gameGrid.resize(8, 8);
    gameGrid.setCellPadding(0);
    gameGrid.setCellSpacing(0);
    gameGrid.setBorderWidth(0);
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        final Image image = new Image();
        board[row][col] = image;
        image.setWidth("100%");
        image.setResource(gameImages.empty());
        final int r = row;
        final int c = col;
        SimplePanel simplePanel = new SimplePanel();
        simplePanel.setPixelSize(50, 50);
        simplePanel.setWidget(image);

        // instantiate a drop controller of the panel in the current cell
        SetWidgetDropController dropController = new SetWidgetDropController(
            simplePanel, r, c, board, presenter);
        dragController.registerDropController(dropController);
        dragController.makeDraggable(image);

        gameGrid.setWidget(row, col, simplePanel);

      }
    }
    gameGrid.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        // TODO Auto-generated method stub
        if (!isTurn())
          return;
        animationOn = true;
        Cell cell = gameGrid.getCellForEvent(event);
        presenter.animationClick(cell.getRowIndex(), cell.getCellIndex());
        // System.out.println("mouseclick");

      }
    });
    gamePanel.add(gameGrid, 0, 0);

    promotion.resize(1, 4);
    promotion.setCellPadding(0);
    promotion.setCellSpacing(0);
    promotion.setBorderWidth(0);

    for (int i = 0; i < 4; i++) {
      final int c = i;
      promotionImgs[i] = new Image();
      promotionImgs[i].setWidth("100%");
      promotionImgs[i].addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          presenter.promote(c);
          promotion.setVisible(false);
        }
      });
      promotion.setWidget(0, i, promotionImgs[i]);
    }

    // button handlers
    restart.setText(constants.restart());
    restart.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        presenter.restart();
        History.newItem(presenter.getState().toString());
      }
    });

    /*
     * save.setText(constants.save()); save.addClickHandler(new ClickHandler() {
     * public void onClick(ClickEvent event) { String time = new
     * Date().toString(); stateStore.setItem(time,
     * presenter.getState().toString()); stateList.addItem(time); } });
     */

    AI.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        // TODO Auto-generated method stub
        playingWithAI = true;
        currentMatchId = 0;
        gameInfo.setText("Playing with AI");
        presenter.setState(new State());
      }

    });
    delete.setText(constants.delete());
    delete.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int index = stateList.getSelectedIndex();
        String key = stateList.getItemText(index);
        // stateStore.removeItem(key);
        // stateList.removeItem(index);
        // TODO Delete from server
        final int matchId = Integer.parseInt(key.substring(
            key.indexOf(':') + 2, key.indexOf(';')));
        gameService.delete(matchId, new AsyncCallback<Void>() {

          @Override
          public void onFailure(Throwable caught) {
            // TODO Auto-generated method stub

          }

          @Override
          public void onSuccess(Void result) {
            // TODO Auto-generated method stub
            Window.alert("Match " + matchId + " Deleted");
            for (int i = 0; i < stateList.getItemCount(); i++) {
              String key = stateList.getItemText(i);
              int id = Integer.parseInt(key.substring(key.indexOf(':') + 2,
                  key.indexOf(';')));
              if (id == matchId) {
                stateList.removeItem(i);
                break;
              }
            }
          }
        });
      }
    });

    load.setText(constants.load());
    load.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        isWaiting = false;
        int index = stateList.getSelectedIndex();
        String key = stateList.getItemText(index);
        // String loadState = stateStore.getItem(key);
        // State state = StateParser.parse(loadState);
        // presenter.setState(state);
        // TODO Load from server
        final int matchId = Integer.parseInt(key.substring(key.lastIndexOf(':') + 2));
        gameService.load(matchId, new AsyncCallback<Match>() {

          @Override
          public void onFailure(Throwable caught) {
            // TODO Auto-generated method stub
            Window.alert("cannot connect to service, load from local storage!");
            Match result = Match.deserializeFrom(storage.getItem("match"
                + matchId));
            player.setColor(result.getColorOf(player.getPlayerId()));
            currentMatchId = result.getMatchId();
            currentMatch = result;
            gameInfo.setText(currentMatch.getTitle() + "  "
                + messages.startTime(currentMatch.getStartTime().toString()));
            presenter.setState(presenter.getStateFromString(currentMatch
                .getState()));
          }

          @Override
          public void onSuccess(Match result) {
            // TODO Auto-generated method stub
            player.setColor(result.getColorOf(player.getPlayerId()));
            currentMatchId = result.getMatchId();
            currentMatch = result;
            gameInfo.setText(currentMatch.getTitle() + "  "
                + messages.startTime(currentMatch.getStartTime().toString()));
            presenter.setState(presenter.getStateFromString(currentMatch
                .getState()));

          }
        });
      }
    });

    invite.setText(constants.invite());
    invite.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Window.alert(opponentEmail.getText() + " Invited!");
        String key = opponentEmail.getText();
        // TODO Invite
        gameService.invite(player.getPlayerId(), key,
            new AsyncCallback<Void>() {

              @Override
              public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub
                Window.alert("cannot connect to server, please invite later");
              }

              @Override
              public void onSuccess(Void result) {
                // TODO Auto-generated method stub

              }
            });
      }
    });

    autoMatch.setText(constants.autoMatch());
    autoMatch.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        // TODO AutoMatch
        isWaiting = false;
        gameInfo.setText(constants.waitingToLoadAGame());
        presenter.setState(new State());
        gameService.autoMatch(player.getPlayerId(), new AsyncCallback<Void>() {

          @Override
          public void onFailure(Throwable caught) {
            // TODO Auto-generated method stub

          }

          @Override
          public void onSuccess(Void result) {
            // TODO Auto-generated method stub
          }
        });
      }
    });

    timer = new Timer() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        if (isConnectionLost) {
          //Window.alert("Connection lost!");
          connect();
        } else {
          if (storage != null) {
            for (int i = 0; i < storage.getLength(); i++) {
              String key = storage.key(i);
              if (key.startsWith("state")) {
                final int id = Integer.parseInt(key.substring(key.length() - 1));
                Window.alert("Updating match " + id);
                int turn = Integer.parseInt(storage.getItem(key)
                    .substring(0, 1));
                String state = storage.getItem(key).substring(1);
                gameService.updateState(id, turn, state,
                    new AsyncCallback<Void>() {

                      @Override
                      public void onFailure(Throwable caught) {
                        // TODO Auto-generated method stub
                      }

                      @Override
                      public void onSuccess(Void result) {
                        // TODO Auto-generated method stub
                        System.out.println("succeed!");
                        storage.removeItem("state" + id);
                        if (playingWithAI && presenter.getTurn().isBlack())
                          presenter.AIMove();
                      }
                    });
              }
            }
          }
        }
      }
    };
    timer.scheduleRepeating(1000);

    socketListener = new SocketListener() {
      @Override
      public void onOpen() {
        isConnectionLost = false;
        gameService.getMatches(player.getPlayerId(),
            new AsyncCallback<List<Match>>() {

              @Override
              public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub
                List<Match> list = getMatchesFromLocalStorage();
                for (Match match : list) {
                  if (!match.isDeleted()) {
                    stateList.addItem(messages.newItem(match.getStartTime()
                        .toString(), match.getOpponentOf(player.getPlayerId()),
                        match.getPlayer(match.getTurn()), String.valueOf(match
                            .getMatchId())));
                  }
                }
              }

              @Override
              public void onSuccess(List<Match> result) {
                // TODO Auto-generated method stub
                // matchList = result;
                if (result == null)
                  return;
                for (Match match : result) {
                  if (!match.isDeleted()) {
                    stateList.addItem(messages.newItem(match.getStartTime()
                        .toString(), match.getOpponentOf(player.getPlayerId()),
                        match.getPlayer(match.getTurn()), String.valueOf(match
                            .getMatchId())));
                    storage.setItem("match" + match.getMatchId(),
                        match.toString());
                    if (match.getMatchId() == currentMatchId) {
                      player.setColor(match.getColorOf(player.getPlayerId()));
                    }
                  }
                }
              }
            });
      }

      @Override
      public void onMessage(String message) {
        // Window.alert(message);
        if (message.startsWith("newMatch:")) {
          // Window.alert("new match");
          String value = message.substring(message.indexOf(':') + 1);
          Match match = Match.deserializeFrom(value);
          // Window.alert(match.toString());
          // matchList.add(match);
          stateList.addItem(messages.newItem((match.getStartTime().toString()),
              match.getOpponentOf(player.getPlayerId()),
              match.getPlayer(match.getTurn()),
              String.valueOf(match.getMatchId())));
          storage.setItem("match" + match.getMatchId(), match.toString());

        }
        if (message.startsWith("updateMatch:")) {
          // Window.alert("update match");
          String value = message.substring(message.indexOf(':') + 1);
          Match update = Match.deserializeFrom(value);
          final State state = presenter.getStateFromString(update.getState());
          // Window.alert(state.getGameResult().toString());
          if (state.getGameResult() != null) {
            if (state.getGameResult().isDraw()) {
              Window
                  .alert(messages.draw(Integer.toString(update.getMatchId())));
            } else if (state.getGameResult().getWinner().isWhite()) {
              if (update.getPlayer(0).equals(player.getPlayerId())) {
                Window
                    .alert(messages.win(Integer.toString(update.getMatchId())));
              } else {
                Window
                    .alert(messages.lose(Integer.toString(update.getMatchId()),
                        update.getPlayer(0)));
              }
            } else if (state.getGameResult().getWinner().isBlack()) {
              if (update.getPlayer(1).equals(player.getPlayerId())) {
                Window
                    .alert(messages.win(Integer.toString(update.getMatchId())));
              } else {
                Window
                    .alert(messages.lose(Integer.toString(update.getMatchId()),
                        update.getPlayer(1)));
              }
            }
            gameService.getPlayer(player.getPlayerId(),
                new AsyncCallback<Player>() {

                  @Override
                  public void onFailure(Throwable caught) {
                    // TODO Auto-generated method stub
                  }

                  @Override
                  public void onSuccess(Player result) {
                    // TODO Auto-generated method stub
                    player.setRating(result.getRating());
                    player.setRD(result.getRD());
                    playerStatus.setText(constants.welcome() + ", "
                        + player.getPlayerId() + "(" + player.getRating() + ")");
                  }

                });
          }
          for (int i = 0; i < stateList.getItemCount(); i++) {
            stateList.getItemText(i);
            String key = stateList.getItemText(i);
            int matchId = Integer
                .parseInt(key.substring(key.lastIndexOf(':') + 2));
            if (matchId == update.getMatchId()) {
              String turn = "";
              if (state.getGameResult() != null) {
                if (state.getGameResult().isDraw()) {
                  turn = constants.draw();
                } else if (state.getGameResult().getWinner().isWhite()) {
                  if (update.getPlayer(0).equals(player.getPlayerId())) {
                    turn = constants.win();
                  } else {
                    turn = constants.lose();
                  }
                } else if (state.getGameResult().getWinner().isBlack()) {
                  if (update.getPlayer(1).equals(player.getPlayerId())) {
                    turn = constants.win();
                  } else {
                    turn = constants.lose();
                  }
                }
              } else {
                turn = update.getPlayer(update.getTurn());
              }
              stateList.setItemText(
                  i,
                  messages.newItem((update.getStartTime().toString()),
                      update.getOpponentOf(player.getPlayerId()), turn,
                      String.valueOf(update.getMatchId())));
              storage.setItem("match" + matchId, update.toString());
              break;
            }
          }
          if (currentMatchId == update.getMatchId()) {
            // Window.alert("current: " + currentMatchId);

            gameInfo.setText(currentMatch.getTitle() + "  "
                + messages.startTime((currentMatch.getStartTime().toString())));
            presenter.setState(state);
          }
        }
        if (message.startsWith("deleteMatch:")) {
          // Window.alert("delete match");
          String value = message.substring(message.indexOf(':') + 1);
          int matchId = Integer.parseInt(value);
          Window.alert(constants.match() + " " + matchId + " "
              + constants.deleted());
          for (int i = 0; i < stateList.getItemCount(); i++) {
            String key = stateList.getItemText(i);
            int id = Integer.parseInt(key.substring(key.indexOf(':') + 2,
                key.indexOf(';')));
            if (id == matchId) {
              stateList.removeItem(i);
              break;
            }
          }
        }
      }

      @Override
      public void onError(ChannelError error) {
        // TODO Auto-generated method stub
        Window.alert("Cannot connect to service!  Error: " + error.getCode());
        isConnectionLost = true;
      }

      @Override
      public void onClose() {
        // TODO Auto-generated method stub
        isConnectionLost = true;
      }
    };

    sound = Audio.createIfSupported();

    if (sound != null) {
      sound.addSource("chenji_audio/click.wav");
    }


    History.addValueChangeHandler(new ValueChangeHandler<String>() {

      public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();
        if (token.isEmpty()) {
          presenter.setState(new State());
        } else {
          presenter.setState(presenter.getStateFromString(token));
        }
      }
    });
    promotion.setVisible(false);
  }

  public void setDragController(PickupDragController dragController) {
    this.dragController = dragController;
  }

  private List<Match> getMatchesFromLocalStorage() {
    if (storage == null)
      return null;
    List<Match> list = new ArrayList<Match>();
    for (int i = 0; i < storage.getLength(); i++) {
      if (storage.key(i).startsWith("match"))
        list.add(Match.deserializeFrom(storage.getItem(storage.key(i))));
    }
    return list;
  }
  public void setService(GameServiceAsync gameService) {
    this.gameService = gameService;
  }
  public void getEmails() {
    gameService.getEmails(0, new AsyncCallback<String>(){

      @Override
      public void onFailure(Throwable caught) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onSuccess(String result) {
        // TODO Auto-generated method stub
        Window.alert(result);
      }});
  }
  public void connect() {
    gameService.connect(loginInfo, new AsyncCallback<Player>() {

      @Override
      public void onFailure(Throwable caught) {
        isConnectionLost = true;
      }

      @Override
      public void onSuccess(Player result) {
        isConnectionLost = false;
        Window.alert("Connected!");
        getEmails();
        if (player == null)
          player = result;
        playerStatus.setText(constants.welcome() + ", " + player.getPlayerId()
            + "(" + player.getRating() + ")");
        channel = new ChannelFactoryImpl().createChannel(player.getToken());
        channel.open(socketListener);
      }
    });
  }

  @Override
  public void updateState(final State state) {
    gameService.updateState(currentMatchId,
        (state.getTurn().isWhite() ? 0 : 1), state.toString(),
        new AsyncCallback<Void>() {

          @Override
          public void onFailure(Throwable caught) {
            // TODO Auto-generated method stub
            Window
                .alert("Cannot connect to service, your move will be sent immediately after reconnection");
            storage.setItem("state" + currentMatchId, (state.getTurn()
                .isWhite() ? 0 : 1) + state.toString());
            Match match = Match.deserializeFrom(storage.getItem("match" + currentMatchId));
            match.setState(state.toString());
            storage.setItem("match" + currentMatchId, match.toString());
            isConnectionLost = true;
          }

          @Override
          public void onSuccess(Void result) {
            // TODO Auto-generated method stub
            System.out.println("succeed!");
            if (playingWithAI && presenter.getTurn().isBlack())
              presenter.AIMove();
          }
        });
  }

  private boolean isTurn() {
    return isWaiting
        || (player != null && player.getColor() == presenter.getTurn());
  }

  public void setResource(Image image, Piece piece) {
    if (piece != null) {
      switch (piece.getKind()) {
      case BISHOP:
        image.setResource(piece.getColor() == Color.WHITE ? gameImages
            .whiteBishop() : gameImages.blackBishop());
        break;
      case KING:
        image.setResource(piece.getColor() == Color.WHITE ? gameImages
            .whiteKing() : gameImages.blackKing());
        break;
      case KNIGHT:
        image.setResource(piece.getColor() == Color.WHITE ? gameImages
            .whiteKnight() : gameImages.blackKnight());
        break;
      case PAWN:
        image.setResource(piece.getColor() == Color.WHITE ? gameImages
            .whitePawn() : gameImages.blackPawn());
        break;
      case QUEEN:
        image.setResource(piece.getColor() == Color.WHITE ? gameImages
            .whiteQueen() : gameImages.blackQueen());
        break;
      case ROOK:
        image.setResource(piece.getColor() == Color.WHITE ? gameImages
            .whiteRook() : gameImages.blackRook());
        break;
      default:
        break;
      }
    }
    image.setWidth("100%");
  }

  public void dragTo(int x, int y, Piece piece) {
    setResource(draggingImage, piece);
    draggingImage.removeFromParent();
    gamePanel.add(draggingImage, x - reletiveX, y - reletiveY);
  }

  public void setPromotion(Color color) {
    if (color == null) {
      promotion.setVisible(false);
      return;
    }
    if (color.isWhite()) {
      promotionImgs[0].setResource(gameImages.whiteQueen());
      promotionImgs[1].setResource(gameImages.whiteKnight());
      promotionImgs[2].setResource(gameImages.whiteRook());
      promotionImgs[3].setResource(gameImages.whiteBishop());
    } else {
      promotionImgs[0].setResource(gameImages.blackQueen());
      promotionImgs[1].setResource(gameImages.blackKnight());
      promotionImgs[2].setResource(gameImages.blackRook());
      promotionImgs[3].setResource(gameImages.blackBishop());
    }
    promotion.setVisible(true);
  }

  @Override
  public void setPiece(int row, int col, Piece piece) {
    ((SimplePanel) gameGrid.getWidget(row, col)).clear();
    ((SimplePanel) gameGrid.getWidget(row, col)).setWidget(board[row][col]);
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
    Element element = board[row][col].getElement();
    if (highlighted) {
      element.setClassName(css.highlighted());
    } else {
      element.removeClassName(css.highlighted());
    }
  }

  @Override
  public void setSelected(int row, int col, boolean selected) {
    Element element = board[row][col].getElement();
    if (selected) {
      element.setClassName(css.selected());
    } else {
      element.removeClassName(css.selected());
    }
  }

  @Override
  public void setWhoseTurn(Color color) {
    if (currentMatch != null) {
      if (player.getColor() == color) {
        gameStatus.setText(messages.userTurn(color.isWhite() ? constants
            .white() : constants.black()));
      } else {
        gameStatus.setText(messages.opponentTurn(
            (color.isWhite() ? constants.white() : constants.black()),
            currentMatch.getOpponentOf(player.getPlayerId())));
      }
    } else {
      if (color == Color.BLACK) {
        gameStatus.setText(constants.blackTurn());
      } else if (color == Color.WHITE) {
        gameStatus.setText(constants.whiteTurn());
      } else {
        gameStatus.setText(constants.draw());
      }
    }
  }

  @Override
  public void setGameResult(GameResult gameResult) {
    if (gameResult != null) {
      if (gameResult.isDraw()) {
        gameStatus.setText(constants.draw() + ": "
            + getGameResultReason(gameResult.getGameResultReason()));
      } else if (gameResult.getWinner() == Color.BLACK) {
        gameStatus.setText(constants.black() + " " + constants.win());
      } else if (gameResult.getWinner() == Color.WHITE) {
        gameStatus.setText(constants.white() + " " + constants.win());
      } else {
      }
    }
  }

  private String getGameResultReason(GameResultReason reason) {
    if (reason == GameResultReason.CHECKMATE) {
      return constants.checkmate();
    } else if (reason == GameResultReason.FIFTY_MOVE_RULE) {
      return constants.fiftyMoveRule();
    } else if (reason == GameResultReason.STALEMATE) {
      return constants.stalemate();
    } else {
      return "";
    }
  }

  @Override
  public void animation(Move move, Piece piece) {
    // TODO Auto-generated method stub
    if (piece == null) {
      animationOn = false;
      return;
    }
    setResource(animationImage, piece);
    ChangePositionAnimation cpa = new ChangePositionAnimation(move.getFrom(),
        move.getTo(), move);
    cpa.run(DURATION);
  }

  @Override
  public boolean isPlayingWithAI() {
    return playingWithAI;
  }

  @Override
  public void clickSound() {
    // TODO Auto-generated method stub
    sound.play();
  }

  @Override
  public void addHistory(String state) {
    History.newItem(state);
  }

}
