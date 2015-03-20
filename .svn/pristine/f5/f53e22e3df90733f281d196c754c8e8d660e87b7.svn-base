package org.bohouli.hw3;

import java.util.Date;
import java.util.Set;

import org.bohouli.hw2.StateChangerImpl;
import org.bohouli.hw2_5.StateExplorerImpl;
import org.bohouli.hw6.UserInformation;
import org.bohouli.hw6.client.BohouChessService;
import org.bohouli.hw6.client.BohouChessServiceAsync;
import org.bohouli.hw6.client.BohouChessConstants;
import org.bohouli.hw6.client.BohouChessMessages;
import org.bohouli.hw9.AlphaBetaPruning;
import org.bohouli.hw9.BohouHeuristic;
import org.bohouli.hw9.Timer;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.ChannelError;
import com.google.gwt.appengine.channel.client.ChannelFactory;
import com.google.gwt.appengine.channel.client.ChannelFactoryImpl;
import com.google.gwt.appengine.channel.client.Socket;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.media.client.Audio;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class Presenter {
	public interface View {
		/**
		 * Renders the piece at this position. If piece is null then the
		 * position is empty.
		 */
		void setPiece(int row, int col, Piece piece);

		/**
		 * Renders the piece when promotion is needed.
		 */
		void setPromotionPieces();

		/**
		 * Turns the highlighting on or off at this cell. Cells that can be
		 * clicked should be highlighted.
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

		/**
		 * Get an image from the view.
		 */
		Image getImage(int row, int col);
		
		void setMatchTime(String time);
		
		void removeMatch();
		
		HasClickHandlers getLoadMatchButton();

		/**
		 * Get restart button.
		 */
		HasClickHandlers getRestartButton();

		/**
		 * Get save button.
		 */
		HasClickHandlers getSaveButton();

		/**
		 * Get load button.
		 */
		HasClickHandlers getLoadButton();

		/**
		 * Get auto match button.
		 */
		HasClickHandlers getAutoButton();
		
		HasClickHandlers getAIButton();
		
		/**
		 * Get auto delete button.
		 */
		HasClickHandlers getDeleteButton();
		
		/**
		 * Get find opponent button.
		 */
		HasClickHandlers getSelectMatchButton();
		
		String getOpponentBox();
		void addMatch(String match);
		
		String getSelectedMatch();
		HasChangeHandlers getMatchBox();
		
		void setSignInName(String name);
		void setSignInEmail(String email);
		void setOpponent(String name);
		void setSignOutLink(String href);
		
		void setSignOutText(String text);
		void setAutoMatchString(String text);
		void setAIMatchString(String text);
		void setSelectMatchString(String text);
		void setLoadMatchString(String text);
		void setSaveString(String text);
		void setLoadString(String text);
		void setRestartString(String text);
		void setDeleteString(String text);
		void setUserRank(String text);
	}

	enum AUDIO_KIND {
		DRAG, CLICK
	};

	private View view;
	private State state;
	private StateChanger stateChanger;
	private StateExplorer stateExplorer;
	private Position focus;
	private HandlerRegistration[][] handlers;
	private HandlerRegistration[] promotionHandlers;
	private Position promotionPosition;
	BohouChessServiceAsync chessService;
	private UserInformation userInfo;
	private VerticalPanel loginPanel;
	private Label loginLabel;
	private Anchor signIn;
	private Color myColor;
	private String matchKey;
	private BohouChessConstants constants;
	private BohouChessMessages messages;
	private boolean ai = false;

	public Presenter() {
		this.state = new State();
		this.stateChanger = new StateChangerImpl();
		this.stateExplorer = new StateExplorerImpl();
		this.focus = null;
		this.handlers = new HandlerRegistration[8][8];
		this.promotionHandlers = new HandlerRegistration[4];
		this.promotionPosition = null;
		chessService = (BohouChessServiceAsync) GWT.create(BohouChessService.class);
		userInfo = null;
		loginPanel = new VerticalPanel();
		loginLabel = new Label("Please sign in with Google Account!");
		signIn = new Anchor("Sign In");
		myColor = null;
		matchKey = null;
		constants = GWT.create(BohouChessConstants.class);
		messages = GWT.create(BohouChessMessages.class);	
	}

	public void playAudio(AUDIO_KIND type) {
		Audio audio = Audio.createIfSupported();
		if (audio == null) {
			return;
		}
		switch (type) {
		case DRAG:
			audio.addSource("bohouli_audio/drag.ogg", AudioElement.TYPE_OGG);
			audio.addSource("bohouli_audio/drag.wav", AudioElement.TYPE_WAV);
			break;
		case CLICK:
			audio.addSource("bohouli_audio/click.ogg", AudioElement.TYPE_OGG);
			audio.addSource("bohouli_audio/click.wav", AudioElement.TYPE_WAV);
			break;
		}

		audio.setControls(true);
		audio.setVolume(1.0);
		audio.play();
	}

	public void setView(View view) {
		this.view = view;
	}

	public void bindHandlers() {
		this.initialHandler();

		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					String token = event.getValue();
					state = BohouStateSerializer.deserialize(token);
					setState(state);
				} catch (Exception e) {
					state = new State();
					setState(state);
				}
			}
		});
		
		this.view.getLoadMatchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loadMatches();
			}
		});

		this.view.getRestartButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				restart();
			}
		});

		this.view.getSaveButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Storage storage = Storage.getLocalStorageIfSupported();
				if (storage != null) {
					storage.setItem("1", BohouStateSerializer.serialize(state));
				} else {
					String message = "Audio is not supported. "
							+ "Try upgrading to a newer browser.";
					Window.alert(message);
				}
			}
		});

		this.view.getLoadButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Storage storage = Storage.getLocalStorageIfSupported();
				if (storage != null) {
					String value = storage.getItem("1");
					if (value != null) {
						state = BohouStateSerializer.deserialize(value);
						setState(state);
						History.newItem(value);
					}
				} else {
					String message = "Local Storage is not supported. "
							+ "Try upgrading to a newer browser.";
					Window.alert(message);
				}
			}
		});

		setDragHandler();

		this.view.getAutoButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {
					public void onSuccess(Void result) {
						// Window.alert("success");
					}

					public void onFailure(Throwable caught) {
						// Window.alert("fail");
					}
				};
				
				myColor = null;
				view.setOpponent(messages.opponentName(""));
				setEmptyBoard();
				chessService.makeAutoMatch(BohouStateSerializer.serialize( new State()), callback);
			}
		});
		
		this.view.getAIButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(ai) {
					ai = false;
					view.setAIMatchString(constants.aiMatchBegin());
					setEmptyBoard();
				} else {
					ai = true;
					restart();
					view.setAIMatchString(constants.aiMatchEnd());
				}
			}
		});
		
		this.view.getSelectMatchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {
					public void onSuccess(Void result) {
						 //Window.alert("success");
					}

					public void onFailure(Throwable caught) {
						 //Window.alert("fail");
					}
				};
				
				String otherEmail = view.getOpponentBox();
				if(otherEmail != null && !otherEmail.isEmpty()) {
					myColor = null;
					view.setOpponent(messages.opponentName(""));
					setEmptyBoard();
					chessService.makeSelectMatch(otherEmail, 
							BohouStateSerializer.serialize(new State()),
							callback);
				} else
					Window.alert("it's null or empty");
			}
		});
		
		this.view.getMatchBox().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String matchItem = view.getSelectedMatch();
				if(!matchItem.isEmpty()) {
					String[] items = matchItem.split(" ");
					matchKey = items[0];
					//Window.alert("get match " + items[0]);
					loadSelectedMatch(items[0]);
				}
			}
		});
		
		this.view.getDeleteButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {
					public void onSuccess(Void result) {
					//	 Window.alert("success");
					}

					public void onFailure(Throwable caught) {
					//	 Window.alert("fail");
					}
				};
				chessService.deleteMatch(matchKey, callback);
				setEmptyBoard();
				myColor = null;
				matchKey = null;
				loadMatches();
			}
		});
	}

	public void restart() {
		state = new State();
		this.setState(state);
		History.newItem(BohouStateSerializer.serialize(state));
	}

	public void setState(State state) {
		this.state = state;
		view.setWhoseTurn(state.getTurn());
		view.setGameResult(state.getGameResult());
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				view.setPiece(7 - r, c, state.getPiece(r, c));
			}
		}
	}
	
	public void setEmptyBoard() {
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 8; c++) {
				view.setPiece(7 - r, c, null);
			}
		}
	}

	public void initialHandler() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				final int final_row = 7 - row;
				final int final_col = col;
				handlers[row][col] = this.view.getImage(row, col)
						.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								clickCheck(final_row, final_col);
								playAudio(AUDIO_KIND.CLICK);
							}
						});
			}
		}
	}

	public void setDragHandler() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Image image = this.view.getImage(row, col);
				final int final_row = 7 - row;
				final int final_col = col;

				image.addDragStartHandler(new DragStartHandler() {
					@Override
					public void onDragStart(DragStartEvent event) {
						if(state == null)
							return;
						
						if (myColor != state.getTurn())
							return;
						event.setData("row", Integer.toString(final_row));
						event.setData("col", Integer.toString(final_col));
						playAudio(AUDIO_KIND.DRAG);
					}
				});

				image.addDragOverHandler(new DragOverHandler() {
					@Override
					public void onDragOver(DragOverEvent event) {

					}
				});

				image.addDropHandler(new DropHandler() {
					@Override
					public void onDrop(DropEvent event) {
						int source_row = Integer.parseInt(event.getData("row"));
						int source_col = Integer.parseInt(event.getData("col"));

						Position src = new Position(source_row, source_col);
						Position tar = new Position(final_row, final_col);
						PieceKind kind = state.getPiece(src).getKind();
						Set<Move> moves = stateExplorer
								.getPossibleMovesFromPosition(state, src);
						if (kind == PieceKind.PAWN
								&& ((final_row == 7 && state.getTurn() == Color.WHITE) || 
										(final_row == 0 && state.getTurn() == Color.BLACK))) {
							Move move = new Move(src, tar, PieceKind.QUEEN);
							if (moves.contains(move)) {
								promotionPosition = tar;
								removeHandler();
								view.setPromotionPieces();
								focus = src;
								addPromotionHandler();
							}
						} else {
							Move move = new Move(src, tar, null);
							if (moves.contains(move)) {
								stateChanger.makeMove(state, move);
								setState(state);
								if(ai == false)
									sendState();
								else
									aiMove();
								History.newItem(BohouStateSerializer.serialize(state));
							}
						}
					}
				});
			}
		}
	}

	public void removeHandler() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				handlers[row][col].removeHandler();
			}
		}
	}

	public void addPromotionHandler() {
		int row = 3;
		for (int col = 2; col < 6; col++) {
			final int final_col = col - 2;
			promotionHandlers[final_col] = this.view.getImage(row, col)
					.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							setPromotion(final_col);
						}
					});
		}
	}

	public void removePromotionHandler() {
		for (int index = 0; index < 4; index++)
			promotionHandlers[index].removeHandler();
	}

	private void setPromotion(int col) {
		PieceKind promotionPiece = PieceKind.PAWN;
		switch (col) {
		case 0:
			promotionPiece = PieceKind.ROOK;
			break;
		case 1:
			promotionPiece = PieceKind.KNIGHT;
			break;
		case 2:
			promotionPiece = PieceKind.BISHOP;
			break;
		case 3:
			promotionPiece = PieceKind.QUEEN;
			break;
		}
		removePromotionHandler();
		initialHandler();
		Move move = new Move(focus, promotionPosition, promotionPiece);
		stateChanger.makeMove(state, move);
		unfocusPiece();
		
		setState(state);
		if(ai == false)
			sendState();
		else
			aiMove();
	
		History.newItem(BohouStateSerializer.serialize(state));
	}

	public void focusPiece(Position cur) {
		focus = cur;
		Set<Move> moves = stateExplorer.getPossibleMovesFromPosition(state, focus);
		for (Move move : moves) {
			view.setHighlighted(7 - move.getTo().getRow(), move.getTo().getCol(), true);
		}
	}

	public void unfocusPiece() {
		focus = null;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				view.setHighlighted(row, col, false);
			}
		}
	}

	public void clickCheck(int row, int col) {
		if(state == null)
			return;
		
		Position cur = new Position(row, col);
		Piece piece = state.getPiece(cur);

		if(ai == false) 
			if (myColor != state.getTurn())
				return;

		if (focus == null) {
			if (piece == null || piece.getColor() != state.getTurn())
				return;
			focusPiece(cur);
		} else {
			if (piece != null && piece.getColor() == state.getTurn()) {
				unfocusPiece();
				focusPiece(cur);
			} else {
				// Piece focus_piece = state.getPiece(focus);
				PieceKind kind = state.getPiece(focus).getKind();
				Set<Move> moves = stateExplorer.getPossibleMovesFromPosition(state, focus);
				if (kind == PieceKind.PAWN
						&& ((row == 7 && state.getTurn() == Color.WHITE) || (row == 0 && state
								.getTurn() == Color.BLACK))) {
					Move move = new Move(focus, cur, PieceKind.QUEEN);
					if (moves.contains(move)) {
						promotionPosition = cur;
						removeHandler();
						this.view.setPromotionPieces();
						addPromotionHandler();
					}
				} else {
					Move move = new Move(focus, cur, null);
					int src_row = focus.getRow();
					int src_col = focus.getCol();
					Image src_image = view.getImage(7 - src_row, src_col);
					Image tar_image = view.getImage(7 - row, col);
					unfocusPiece();
					if (moves.contains(move)) {
						stateChanger.makeMove(state, move);
						ClickAnimation clickAnimation = new ClickAnimation(
								src_image, tar_image);
						clickAnimation.run(1000);
					}
				}
			}
		}
	}
	
	public void aiMove() {
		AlphaBetaPruning prune = new AlphaBetaPruning(new BohouHeuristic());
		Move move = prune.findBestMove(state, 10, new Timer(5000));
		stateChanger.makeMove(state, move);
		setState(state);
	}

	public void sendState() {
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onSuccess(Void result) {
				// Window.alert("success");
			}

			public void onFailure(Throwable caught) {
				// Window.alert("fail");
			}
		};
		if(state.getGameResult() == null)
			chessService.sendState(matchKey, BohouStateSerializer.serialize(state), null, callback);
		else {
			Color color = state.getGameResult().getWinner();
			String winner;
			if(color == null)
				winner = "D";
			else
				winner = color.toString();
			chessService.sendState(matchKey, BohouStateSerializer.serialize(state),
					winner, callback);
		}
	}

	public void createClientChannel(String token) {
		ChannelFactory channelFactory = new ChannelFactoryImpl();
		Channel channel = channelFactory.createChannel(token);

		Socket socket = channel.open(new SocketListener() {
			@Override
			public void onOpen() {
				//Window.alert("My Channel opened!");
			}

			@Override
			public void onMessage(String message) {
				//Window.alert(message);
				String[] messes = message.split("#");
				int messType = Integer.parseInt(messes[0]);
				switch (messType) {
				case 1:
					if(messes[1].equals(matchKey))
						setState(BohouStateSerializer.deserialize(messes[2]));
					break;
				case 2:
					if (messes[1].equals("B")) {
						myColor = Color.BLACK;
					} else {
						myColor = Color.WHITE;
					}
					view.setOpponent(messages.opponentName(messes[2]));
					matchKey = messes[3];
					view.addMatch(messes[3] + " " + messes[2]);
					loadMatchTime(messes[3]);
					restart();
					break;
				case 3:
					if (messes[1].equals("B")) {
						myColor = Color.BLACK;
					} else {
						myColor = Color.WHITE;
					}
					view.setOpponent(messages.opponentName(messes[2]));
					matchKey = messes[3];
					if(messes[4] == null || messes[4].isEmpty())
						restart();
					else
						setState(BohouStateSerializer.deserialize(messes[4]));
					break;
				case 4:
					{
						for(int i = 1; i < messes.length; i++) {
							view.addMatch(messes[i]);
						}
						break;
					}
				case 5:
					if(messes[1].equals(matchKey))
						setState(BohouStateSerializer.deserialize(messes[2]));
					view.setUserRank(messages.userRank(messes[3]));
					break;
				}
			}

			@Override
			public void onError(ChannelError error) {
				Window.alert("Channel error: " + error.getCode() + " : "
						+ error.getDescription());
			}

			@Override
			public void onClose() {
				Window.alert("Channel closed!");
			}
		});
	}

	public void loadChessService() {
		//Load buttons and texts
		{
			view.setSignInName(messages.userName(""));
			view.setUserRank(messages.userRank(""));
			view.setSignInEmail(messages.emailAddress(""));
			view.setSignOutText(constants.signOut());
			view.setOpponent(messages.opponentName(""));
			view.setAutoMatchString(constants.autoMatch());
			view.setAIMatchString(constants.aiMatchBegin());
			view.setSelectMatchString(constants.selectMatch());
			view.setLoadMatchString(constants.loadMatch());
			view.setSaveString(constants.save());
			view.setLoadString(constants.load());
			view.setRestartString(constants.restart());
			view.setDeleteString(constants.delete());
		}
		
		chessService.login(GWT.getHostPageBaseURL() + "bohouli.html",
				new AsyncCallback<UserInformation>() {
					public void onFailure(Throwable error) {
						Window.alert("Sign in Error");
					}

					public void onSuccess(UserInformation result) {
						userInfo = result;
						if (userInfo.isLoggedIn()) {
							view.setSignInName(messages.userName(userInfo.getNickname()));
							view.setSignInEmail(messages.emailAddress(userInfo.getEmailAddress()));
							view.setSignOutLink(userInfo.getLogoutUrl());
							view.setUserRank(messages.userRank(
									Double.toString(userInfo.getRanking())));

							State state;
							if (History.getToken().isEmpty()) {
								state = new State();
							} else {
								state = BohouStateSerializer.deserialize(History.getToken());
							}

							createClientChannel(userInfo.getToken());
							setEmptyBoard();
							bindHandlers();
						} else {
							signIn.setHref(userInfo.getLoginUrl());
							loginPanel.add(loginLabel);
							loginPanel.add(signIn);
							RootPanel.get().add(loginPanel);
						}
					}
				});
	}
	
	public void loadMatches() {
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onSuccess(Void result) {
				Window.alert("success");
			}

			public void onFailure(Throwable caught) {
				Window.alert("fail");
			}
		};
		view.removeMatch();
		chessService.loadMatches(callback);
	}
	
	private void loadSelectedMatch(String matchKey) {	
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onSuccess(Void result) {
				// Window.alert("success");
			}

			public void onFailure(Throwable caught) {
				// Window.alert("fail");
			}
		};
		
        chessService.getState(matchKey, callback);
        loadMatchTime(matchKey);
	}
	
	private void loadMatchTime(String matchKey) {
		chessService.getDate(matchKey, new AsyncCallback<Date>() {
			public void onSuccess(Date date) {
				view.setMatchTime(messages.matchTime(date));
			}

			public void onFailure(Throwable caught) {
				Window.alert("fail");
			}
		});
	}

	public class ClickAnimation extends Animation {

		private final Image src_image;
		private int src_x;
		private int src_y;
		private int tar_x;
		private int tar_y;

		public ClickAnimation(Image src_image, Image tar_image) {
			this.src_image = src_image;

			this.src_y = src_image.getElement().getAbsoluteTop();
			this.src_x = src_image.getElement().getAbsoluteLeft();
			this.tar_y = tar_image.getElement().getAbsoluteTop();
			this.tar_x = tar_image.getElement().getAbsoluteLeft();
		}

		@Override
		protected void onUpdate(double progress) {
			// TODO Auto-generated method stub
			double y = src_y + (tar_y - src_y) * progress;
			double x = src_x + (tar_x - src_x) * progress;
			this.src_image.getElement().getStyle()
					.setProperty("position", "absolute");
			this.src_image.getElement().getStyle()
					.setProperty("left", x + "px");
			this.src_image.getElement().getStyle().setProperty("top", y + "px");
		}

		@Override
		protected void onComplete() {
			this.src_image.getElement().getStyle()
					.setProperty("position", "absolute");
			this.src_image.getElement().getStyle()
					.setProperty("left", src_x + "px");
			this.src_image.getElement().getStyle()
					.setProperty("top", src_y + "px");
			setState(state);
			if(ai == false)
				sendState();
			else
				aiMove();
			History.newItem(BohouStateSerializer.serialize(state));
		}
	}
}
