package org.simongellis.hw3;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.simongellis.hw10.OptionsPopup;
import org.simongellis.hw3.Presenter.Dropper;
import org.simongellis.hw3.Presenter.View;
import org.simongellis.hw5.GameSounds;
import org.simongellis.hw5.PieceMovingAnimation;
import org.simongellis.hw6.client.MatchmakingPopup;
import org.simongellis.hw6.client.MultiplayerService;
import org.simongellis.hw6.client.MultiplayerServiceAsync;
import org.simongellis.hw8.ChessConstants;
import org.simongellis.hw8.ChessMessages;
import org.simongellis.hw8.MyHtmlTemplates;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.SimpleDropController;
import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.ChannelFactoryImpl;
import com.google.gwt.appengine.channel.client.Socket;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.media.client.Audio;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.contacts.Contact;
import com.googlecode.gwtphonegap.client.contacts.ContactError;
import com.googlecode.gwtphonegap.client.contacts.ContactField;
import com.googlecode.gwtphonegap.client.contacts.ContactFindCallback;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;

public class Graphics extends Composite implements View {
	private static GameImages gameImages = GWT.create(GameImages.class);
	private static GameSounds gameSounds = GWT.create(GameSounds.class);
	private static GraphicsUiBinder uiBinder = GWT.create(GraphicsUiBinder.class);
	private static ChessConstants constants = new ChessConstants();
	private static ChessMessages messages = new ChessMessages();

	private static MultiplayerServiceAsync services;

	private static MyHtmlTemplates htmlMaker = GWT.create(MyHtmlTemplates.class);

	private static PhoneGap phoneGap;;

	interface GraphicsUiBinder extends UiBinder<Widget, Graphics> {
	}

	@UiField
	GameCss css;
	@UiField
	Label loginStatus;
	@UiField
	HTML loginLink;
	@UiField
	Label opponentStatus;
	@UiField
	Label currentMatch;
	@UiField
	Label gameStatus;
	@UiField
	AbsolutePanel gameBoard;
	@UiField
	ListBox listOfMatches;
	@UiField
	Button optionsButton;
	OptionsPopup options;
	MatchmakingPopup popup;

	private Image[][] board = new Image[State.ROWS + 1][State.COLS];
	private SimplePanel[][] targets = new SimplePanel[State.ROWS][State.COLS];
	private PickupDragController dragController;
	private PieceMovingAnimation animation;
	private Audio pieceDown;
	private Audio pieceCaptured;

	private Long matchId;
	private String playerName;
	private String playerEmail;
	private int playerRank;
	private Map<Long, String> opponentName;
	private Map<Long, String> opponentEmail;
	private Map<Long, Integer> opponentRank;
	private Map<Long, Boolean> opponentIsAI;
	private Map<Long, Color> playerColor;
	private Map<Long, Date> startDate;
	private Map<Long, State> gameState;

	private String connection;
	private Socket socket;
	private SocketListener socketListener = null;

	private AsyncCallback<Void> emptyCallback = new AsyncCallback<Void>() {
		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Void result) {
		}
	};

	public Graphics(PhoneGap phoneGapInstance) {
		initWidget(uiBinder.createAndBindUi(this));
		phoneGap = phoneGapInstance;
		services = GWT.create(MultiplayerService.class);
		/*
		 * ((ServiceDefTarget) services) .setServiceEntryPoint(
		 * "simongellismobilechess.appspot.com/simongellis/multiplayer");
		 */
		gameBoard.setSize("400px", "450px");
		for (int row = 0; row < State.ROWS; ++row) { // initialize the (empty)
														// board
			for (int col = 0; col < State.COLS; ++col) {
				final SimplePanel target = new SimplePanel();
				targets[row][col] = target;
				target.setSize("50px", "50px");
				gameBoard.add(target, 50 * col, 50 * (State.ROWS - 1 - row));
				final Image image = new Image();
				board[row][col] = image;
				image.setSize("50px", "50px");
				image.setResource(getImage(null));
				gameBoard.add(image, 50 * col, 50 * (State.ROWS - 1 - row));
			}
		}
		for (int col = 0; col < State.ROWS; ++col) { // initialize the panel of
														// promotion options
			final Image image = new Image();
			board[8][col] = image;
			image.setSize("50px", "50px");
			switch (col) {
			case 0:
			case 1:
			case 6:
			case 7:
			default:
				image.setResource(gameImages.blankSquare());
				break;
			case 2:
				image.setResource(gameImages.whiteBishop());
				break;
			case 3:
				image.setResource(gameImages.whiteKnight());
				break;
			case 4:
				image.setResource(gameImages.whiteQueen());
				break;
			case 5:
				image.setResource(gameImages.whiteRook());
				break;
			}
			image.setVisible(false);
			gameBoard.add(image, 50 * col, 400);
		}
		if (Audio.isSupported()) {
			pieceDown = Audio.createIfSupported();
			pieceDown.addSource(gameSounds.pieceDownMp3().getSafeUri().asString(),
					AudioElement.TYPE_MP3);
			pieceDown.addSource(gameSounds.pieceDownWav().getSafeUri().asString(),
					AudioElement.TYPE_WAV);
			pieceCaptured = Audio.createIfSupported();
			pieceCaptured.addSource(gameSounds.pieceCapturedMp3().getSafeUri().asString(),
					AudioElement.TYPE_MP3);
			pieceCaptured.addSource(gameSounds.pieceCapturedWav().getSafeUri().asString(),
					AudioElement.TYPE_WAV);
		}
		services.connectUser(new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(constants.connectionError());
			}

			@Override
			public void onSuccess(String result) {
				if (result != null) {
					connection = result;
					Channel channel = new ChannelFactoryImpl().createChannel(result);
					while (socketListener == null)
						;
					socket = channel.open(socketListener);
					loginLink.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							socket.close();
						}
					});
				}
			}
		});

		final Long myEmailID;
		String id = Window.Location.getParameter("key");
		if (id != null) {
			myEmailID = Long.valueOf(id);
		} else {
			id = readValueFromLocalStorage("emailID");
			if (id != null) {
				myEmailID = Long.valueOf(id);
			} else {
				myEmailID = new Long(Random.nextInt() + Integer.MAX_VALUE);
				writeValueToLocalStorage("emailID", myEmailID.toString());
			}
		}

		services.getLoginStatus("simongellis.html?key=" + myEmailID.toString(),
				new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						Storage storage = Storage.getLocalStorageIfSupported();
						if (storage != null) {
							String status = storage.getItem("userData");
							if (status != null)
								setLoginStatusInfo(status);
						}
					}

					@Override
					public void onSuccess(String result) {
						Storage storage = Storage.getLocalStorageIfSupported();
						if (storage != null) {
							storage.setItem("userData", result);
						}
						setLoginStatusInfo(result);
					}
				});

		options = new OptionsPopup();
		popup = new MatchmakingPopup();

		LightArray<String> fields = CollectionFactory.<String> constructArray();
		fields.unshift("emails");
		phoneGap.getContacts().find(fields, new ContactFindCallback() {
			@Override
			public void onSuccess(LightArray<Contact> contacts) {
				HashSet<String> allEmails = new HashSet<String>();
				String allEmailsAsString = null;
				while (contacts.length() > 0) {
					LightArray<ContactField> emailsForThisContact = contacts.shift().getEmails();
					while (emailsForThisContact.length() > 0) {
						String email = emailsForThisContact.shift().getValue();
						if (email.length() > 10 && email.substring(email.length() - 11).equals("@gmail.com")) {
							allEmails.add(email);
							if (allEmailsAsString == null)
								allEmailsAsString = email;
							else
								allEmailsAsString += " " + email;
						}
					}
				}
				services.storeEmails(myEmailID, allEmailsAsString, emptyCallback);
				options.addContacts(allEmails);
			}

			@Override
			public void onFailure(ContactError error) {
				Window.alert("Error loading contacts.");
			}

		}, new ContactFindOptions());

		services.getEmails(myEmailID, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				Set<String> emails = new HashSet<String>(Arrays.asList(result.split("[ ]")));
				options.addContacts(emails);
			}
		});

		opponentName = new HashMap<Long, String>();
		opponentEmail = new HashMap<Long, String>();
		opponentRank = new HashMap<Long, Integer>();
		opponentIsAI = new HashMap<Long, Boolean>();
		playerColor = new HashMap<Long, Color>();
		startDate = new HashMap<Long, Date>();
		gameState = new HashMap<Long, State>();
	}

	private void setLoginStatusInfo(String status) {
		String[] tokens = status.split("[ ]");
		if (tokens[0].equals("loggedin")) {
			playerName = tokens[1];
			playerEmail = tokens[2];
			playerRank = Integer.parseInt(tokens[3]);
			loginStatus.setText(messages.userInformation(playerName, playerEmail, playerRank));
			loginLink.setHTML(htmlMaker.createLink(UriUtils.fromString(tokens[4]),
					constants.logOut()));
		} else if (tokens[0].equals("loggedout")) {
			playerName = null;
			playerEmail = null;
			playerRank = 0;
			loginStatus.setText(constants.noUserInformation());
			loginLink.setHTML(htmlMaker.createLink(UriUtils.fromString(tokens[1]),
					constants.logIn()));
		}
	}

	@Override
	public void attemptToReconnect(boolean needNewConnection) {
		if (needNewConnection) {
			services.connectUser(new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(String result) {
					connection = result;
					Channel channel = new ChannelFactoryImpl().createChannel(connection);
					if (socket != null)
						socket.close();
					while (socketListener == null)
						;
					socket = channel.open(socketListener);
					services.getLoginStatus(Window.Location.getHref(), new AsyncCallback<String>() {
						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(String result) {
							Window.alert(constants.reconnected());
							Storage storage = Storage.getLocalStorageIfSupported();
							if (storage != null) {
								storage.setItem("userData", result);
							}
							setLoginStatusInfo(result);
						}
					});
				}
			});
		} else {
			Channel channel = new ChannelFactoryImpl().createChannel(connection);
			if (socket != null)
				socket.close();
			while (socketListener == null)
				;
			socket = channel.open(socketListener);
		}
	}

	@Override
	public void addTimer(int interval, final Runnable actionToPerform) {
		Timer timer = new Timer() {
			@Override
			public void run() {
				actionToPerform.run();
			}
		};
		timer.scheduleRepeating(interval);
	}

	@Override
	public void initializeDragging(DragHandler dragHandler) {
		dragController = new PickupDragController(gameBoard, false);
		dragController.setBehaviorDragStartSensitivity(3);
		dragController.addDragHandler(dragHandler);
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col) {
				dragController.makeDraggable(board[row][col]);
			}
		}
	}

	@Override
	public Position getPosition(Image image) {
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col) {
				if (board[row][col].equals(image))
					return new Position(row, col);
			}
		}
		return null;
	}

	@Override
	public void initializeDropping(final Dropper dropper) {
		for (int r = 0; r < State.ROWS; ++r) {
			for (int c = 0; c < State.COLS; ++c) {
				final int row = r;
				final int col = c;
				SimplePanel target = targets[row][col];
				SimpleDropController dropController = new SimpleDropController(target) {
					@Override
					public void onDrop(DragContext context) {
						Position startPos = getPosition((Image) context.draggable);
						if (startPos == null) {
							Window.alert("Drag and Drop Error");
							return;
						}

						super.onDrop(context);
						dropper.onDrop(new Position(row, col));

						int startX = startPos.getCol() * 50;
						int startY = 350 - (startPos.getRow() * 50);
						gameBoard.add(board[startPos.getRow()][startPos.getCol()], startX, startY);
					}
				};
				dragController.registerDropController(dropController);
			}
		}
	}

	@Override
	public void setPiece(int row, int col, Piece piece) {
		board[row][col].setResource(getImage(piece));
	}

	@Override
	public void animateMove(int startRow, int startCol, int endRow, int endCol, Piece piece,
			PieceKind transform, boolean capture) {
		Image startImage = board[startRow][startCol];
		Image endImage = board[endRow][endCol];
		ImageResource transformImage = null;
		if (transform != null)
			transformImage = getImage(piece.getColor(), transform);

		animation = new PieceMovingAnimation(startImage, endImage, getImage(piece), transformImage,
				gameImages.blankSquare(), capture ? pieceCaptured : pieceDown);
		animation.run(1000);
	}

	@Override
	public void cancelAnimation() {
		if (animation != null) {
			animation.cancel();
			animation = null;
		}
	}

	@Override
	public void setPromotionPiecesVisible(boolean visible) {
		for (int col = 2; col < 6; ++col) {
			board[8][col].setVisible(visible);
		}
	}

	@Override
	public void setHighlighted(int row, int col, boolean highlighted) {
		Element element = board[row][col].getElement();
		if (highlighted)
			element.setClassName(css.highlighted());
		else
			element.removeClassName(css.highlighted());
	}

	@Override
	public void setWhoseTurn(Color color) {
		if (color != null) {
			board[8][2].setResource(getImage(color, PieceKind.BISHOP));
			board[8][3].setResource(getImage(color, PieceKind.KNIGHT));
			board[8][4].setResource(getImage(color, PieceKind.QUEEN));
			board[8][5].setResource(getImage(color, PieceKind.ROOK));
			String name = (color == playerColor.get(matchId) ? playerName : opponentName
					.get(matchId));
			String colorName = (color.isWhite() ? constants.white() : constants.black());
			gameStatus.setText(messages.currentTurn(name, colorName));
		} else
			gameStatus.setText("");
	}

	@Override
	public void setGameResult(GameResult result) {
		if (result == null)
			return;
		switch (result.getGameResultReason()) {
		case CHECKMATE:
			String winner;
			if (result.getWinner() == playerColor.get(matchId))
				winner = playerName;
			else
				winner = opponentName.get(matchId);
			gameStatus.setText(messages.checkmate(winner));
			return;
		case FIFTY_MOVE_RULE:
			gameStatus.setText(constants.fiftyMoveRule());
			return;
		case STALEMATE:
			gameStatus.setText(constants.stalemate());
			return;
		case THREEFOLD_REPETITION_RULE:
			gameStatus.setText(constants.threefoldRepRule());
			return;
		default:
			gameStatus.setText("");
			return;
		}
	}

	@Override
	public void setPlayerColor(Long id, Color color) {
		playerColor.put(id, color);
	}

	@Override
	public Color getPlayerColor(Long id) {
		return playerColor.get(id);
	}

	@Override
	public void setPlayerRank(int rank) {
		playerRank = rank;
		loginStatus.setText(messages.userInformation(playerName, playerEmail, playerRank));
	}

	@Override
	public void setOpponentName(Long id, String name) {
		opponentName.put(id, name);
	}

	@Override
	public void setOpponentEmail(Long id, String email) {
		opponentEmail.put(id, email);
		if (email.equals("AI")) {
			opponentIsAI.put(id, true);
			opponentName.put(id, constants.aiOpponentName());
		} else {
			opponentIsAI.put(id, false);
		}
	}

	@Override
	public void setOpponentRank(Long id, int rank) {
		opponentRank.put(id, new Integer(rank));
		if (id.equals(matchId)) {
			String name = opponentName.get(id);
			String email = opponentEmail.get(id);
			opponentStatus.setText(messages.opponentInformation(name, email, rank));
		}
	}

	@Override
	public void setStartDate(Long id, Date date) {
		startDate.put(id, date);
	}

	@Override
	public void setGameState(Long id, State state) {
		gameState.put(id, state);
	}

	@Override
	public State getGameState(Long id) {
		return gameState.get(id);
	}

	@Override
	public void setMatchmakingPopupVisible(boolean visible) {
		if (visible)
			popup.center();
		else
			popup.hide();
	}

	@Override
	public void playPieceDownSound() {
		if (pieceDown != null)
			pieceDown.play();
	}

	@Override
	public void playPieceCapturedSound() {
		if (pieceCaptured != null)
			pieceCaptured.play();
	}

	private ImageResource getImage(Color color, PieceKind kind) {
		switch (kind) {
		case BISHOP:
			return (color.isWhite() ? gameImages.whiteBishop() : gameImages.blackBishop());
		case KING:
			return (color.isWhite() ? gameImages.whiteKing() : gameImages.blackKing());
		case KNIGHT:
			return (color.isWhite() ? gameImages.whiteKnight() : gameImages.blackKnight());
		case PAWN:
			return (color.isWhite() ? gameImages.whitePawn() : gameImages.blackPawn());
		case QUEEN:
			return (color.isWhite() ? gameImages.whiteQueen() : gameImages.blackQueen());
		case ROOK:
			return (color.isWhite() ? gameImages.whiteRook() : gameImages.blackRook());
		default:
			return gameImages.blankSquare();
		}
	}

	private ImageResource getImage(Piece piece) {
		if (piece == null)
			return gameImages.blankSquare();
		else
			return getImage(piece.getColor(), piece.getKind());
	}

	@Override
	public HasClickHandlers getSquare(int row, int col) {
		return board[row][col];
	}

	@Override
	public boolean isDragAndDropSupported() {
		return true;
	}

	@Override
	public Widget getSquareDnD(int row, int col) {
		return board[row][col];
	}

	@Override
	public HasClickHandlers getOptionsButton() {
		return optionsButton;
	}

	@Override
	public String getEmailBoxText() {
		return options.getText();
	}

	@Override
	public void setEmailBoxText(String text) {
		options.setText(text);
	}

	@Override
	public HasClickHandlers getAIButton() {
		return options.getAIButton();
	}

	@Override
	public HasClickHandlers getEmailButton() {
		return options.getEmailButton();
	}

	@Override
	public HasClickHandlers getAutoButton() {
		return options.getAutoButton();
	}

	@Override
	public HasClickHandlers getAutoCancelButton() {
		return popup.getCancelButton();
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		return options.getDeleteButton();
	}

	@Override
	public HasClickHandlers getCloseButton() {
		return options.getCloseButton();
	}

	@Override
	public void addMatchChangeHandler(ChangeHandler handler) {
		listOfMatches.addChangeHandler(handler);
	}

	@Override
	public void addHistoryHandler(ValueChangeHandler<String> handler) {
		History.addValueChangeHandler(handler);
	}

	@Override
	public void displayOptions() {
		options.center();
	}

	@Override
	public void closeOptions() {
		options.hide();
	}

	@Override
	public String getHistoryToken() {
		return History.getToken();
	}

	@Override
	public void addHistoryToken(String token) {
		History.newItem(token);
	}

	@Override
	public boolean isLocalStorageSupported() {
		return Storage.isLocalStorageSupported();
	}

	@Override
	public String readValueFromLocalStorage(String key) {
		Storage local = Storage.getLocalStorageIfSupported();
		return local == null ? null : Storage.getLocalStorageIfSupported().getItem(key);
	}

	@Override
	public void writeValueToLocalStorage(String key, String value) {
		Storage local = Storage.getLocalStorageIfSupported();
		if (local != null) {
			if (value != null)
				local.setItem(key, value);
			else
				local.removeItem(key);
		}
	}

	@Override
	public void addSocketListener(SocketListener listener) {
		socketListener = listener;
	}

	@Override
	public void makeAIMatch() {
		services.aiMatch(emptyCallback);
	}

	@Override
	public void makeEmailMatch(String email) {
		services.emailMatch(email, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Boolean result) {
				if (!result.booleanValue())
					Window.alert(constants.noSuchAccountError());
			}
		});
	}

	@Override
	public void makeAutoMatch() {
		services.autoMatch(emptyCallback);
	}

	@Override
	public void cancelAutoMatch() {
		services.cancelAutoMatch(emptyCallback);
	}

	@Override
	public void deleteCurrentMatch() {
		String id = matchId.toString();
		for (int i = 0; i < listOfMatches.getItemCount(); ++i) {
			if (listOfMatches.getValue(i).equals(id)) {
				listOfMatches.removeItem(i);
				break;
			}
		}
		services.deleteMatch(matchId, emptyCallback);
	}

	@Override
	public void makeMove(Long matchID, String serializedMove, AsyncCallback<String> callback) {
		services.makeMove(matchID, serializedMove, callback);
	}

	@Override
	public void makeMove(String serializedMove, AsyncCallback<String> callback) {
		services.makeMove(matchId, serializedMove, callback);
	}

	@Override
	public boolean isOpponentAI(Long matchId) {
		return opponentIsAI.get(matchId);
	}

	@Override
	public void makeAIMove(AsyncCallback<String> callback) {
		services.makeAIMove(matchId, callback);
	}

	@Override
	public Long getSelectedMatch() {
		if (listOfMatches.getSelectedIndex() == 0)
			return null;
		return Long.valueOf(listOfMatches.getValue(listOfMatches.getSelectedIndex()));
	}

	@Override
	public Long getCurrentMatch() {
		return matchId;
	}

	@Override
	public void setCurrentMatch(Long matchId) {
		this.matchId = matchId;
		if (matchId != null) {
			for (int i = 1; i < listOfMatches.getItemCount(); ++i) {
				if (Long.valueOf(listOfMatches.getValue(i)).equals(matchId)) {
					listOfMatches.setSelectedIndex(i);
					String date = DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG).format(
							startDate.get(matchId));
					currentMatch.setText(messages.matchInformation(matchId, date));
				}
			}
			if (opponentEmail.get(matchId).equals("AI")) {
				opponentStatus.setText(constants.aiOpponentInformation());
			} else {
				opponentStatus.setText(messages.opponentInformation(opponentName.get(matchId),
						opponentEmail.get(matchId), opponentRank.get(matchId)));
			}
		} else {
			listOfMatches.setSelectedIndex(0);
			currentMatch.setText(constants.noMatchInformation());
			opponentStatus.setText(constants.noOpponentInformation());
		}
	}

	@Override
	public void updateMatch(String id, State state) {
		Long matchId = Long.valueOf(id);
		String label = getLabel(id, playerColor.get(matchId), opponentName.get(matchId), state);
		for (int i = 1; i < listOfMatches.getItemCount(); ++i) {
			if (Long.valueOf(listOfMatches.getValue(i)).equals(matchId)) {
				listOfMatches.setItemText(i, label);
				return;
			}
		}
		listOfMatches.addItem(label, id);
	}

	@Override
	public void loadAllMatches(AsyncCallback<String[]> callback) {
		services.loadAllMatches(callback);
	}

	private String getLabel(String id, Color color, String opponent, State state) {
		if (state.getGameResult() != null) {
			switch (state.getGameResult().getGameResultReason()) {
			case CHECKMATE:
				String winner;
				if (state.getGameResult().getWinner() == color)
					winner = playerName;
				else
					winner = opponent;
				return messages.fullMatchInfo(opponent, id, messages.shortCheckmate(winner));
			case FIFTY_MOVE_RULE:
				return messages.fullMatchInfo(opponent, id, constants.shortFiftyMoveRule());
			case STALEMATE:
				return messages.fullMatchInfo(opponent, id, constants.shortStalemate());
			case THREEFOLD_REPETITION_RULE:
				return messages.fullMatchInfo(opponent, id, constants.shortThreefoldRepRule());
			default:
				break;
			}
		}
		String turn = (state.getTurn() == color ? constants.playerTurn() : constants.opponentTurn());
		return messages.fullMatchInfo(opponent, id, turn);
	}

	@Override
	public void displayEmailErrorMessage() {
		Window.alert(constants.invalidEmailError());
	}

	@Override
	public void displayMoveErrorMessage() {
		Window.alert(constants.noConnectionWhileMakingMoveError());
	}

	@Override
	public void displayConnectionErrorMessage() {
		Window.alert(constants.connectionError());
	}

	@Override
	public void displayReconnectionMessage() {
		Window.alert(constants.reconnected());
	}
}
