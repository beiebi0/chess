package org.markanderson.hw3;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.markanderson.hw2.StateChangerImpl;
import org.markanderson.hw2_5.StateExplorerImpl;
import org.markanderson.hw5.AudioHandler;
import org.markanderson.hw6.client.ManderChessService;
import org.markanderson.hw6.client.ManderChessServiceAsync;
import org.markanderson.hw6.helper.ManderChessGameSessionInfo;
import org.markanderson.hw6.helper.ManderChessUserSessionInfo;
import org.markanderson.hw8.ManderLocalizedStrings;
import org.markanderson.hw9.AlphaBetaPruning;
import org.markanderson.hw9.DateTimer;
import org.markanderson.hw9.ManderHeuristic;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

import com.google.common.collect.Sets;
import com.google.gwt.animation.client.Animation;
import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.ChannelError;
import com.google.gwt.appengine.channel.client.ChannelFactory;
import com.google.gwt.appengine.channel.client.ChannelFactoryImpl;
import com.google.gwt.appengine.channel.client.Socket;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;

public class Presenter {

	public interface View {
		/**
		 * Renders the piece at this position. If piece is null then the
		 * position is empty.
		 */
		void setPiece(int row, int col, Piece piece);

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
		 * Set a widget for one of the buttons in the view
		 */
		String getGameStatusString();

		/**
		 * Add drag/drop listeners
		 */
		void handleDragDropEvents();

		/**
		 * handle the promotion box visible
		 */
		void setPromotionGridVisible(Color color);

		/**
		 * handle the promotion box hidden
		 */
		void setPromotionGridHidden();

		/**
		 * get images from view
		 */
		Image getImageFromPosition(Position pos);

		/**
		 * open the channel api
		 */
		// void openChannelWithSocketListener(String token, SocketListener
		// listener);

		// /**
		// * join the game
		// */
		// void joinThisGame();

		/**
		 * send the state to the server
		 */
		void sendAsyncStateUpdateToServer(String clientID, String state);

		/**
		 * populate the user's games into the dropdown
		 */
		void populateCurrentGamesDropDown();

		/**
		 * add a game into the dropdown
		 */
		void addGamesToDropDown(ManderChessGameSessionInfo gInfo);

		String getGameIDFromDropDown();

		void updateCurrentTime(String timeStr);

	}

	public View view;
	public State state;
	public Position selected;
	public Set<Move> highlightedPositions = Sets.newHashSet();
	public PieceKind promotionPK;
	public String userID1;
	public String userID2;
	public Color userColor;
	public String clientID;
	public String matchID;
	public String clientEmail;
	public boolean isSinglePlayer;
	public Socket socket;

	ManderLocalizedStrings messages = (ManderLocalizedStrings) GWT
			.create(ManderLocalizedStrings.class);

	Logger logger = Logger.getLogger(Presenter.class.toString());
	AudioHandler audioHandler = new AudioHandler();
	public ManderChessServiceAsync chessService = GWT
			.create(ManderChessService.class);

	public void setView(View view) {
		this.view = view;
	}

	public class GameOverDialog extends DialogBox {
		// dialog box that shows when the game has ended
		// allows user to restart the game.
		public GameOverDialog() {
			setTitle(messages.gameOver());
			setText(view.getGameStatusString());
			setAnimationEnabled(true);
			setGlassEnabled(true);

			Button restart = new Button(messages.restartGame());
			restart.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					initState(new State());
					GameOverDialog.this.hide();
				}
			});
			setWidget(restart);
		}
	}

	public void initHistoryEventHandler() {
		if (History.getToken().isEmpty()) {
			this.state = new State();
			initState(this.state);
		} else {
			System.out.println(HistorySupport.deserializeHistoryToken(History
					.getToken().toString()));
			this.state = HistorySupport.deserializeHistoryToken(History
					.getToken());
			setState(this.state);
			checkGameResultNotNull();
		}
	}

	public void initState(State state) {
		// call the method that joins the game asynchronously as a user
		// navigates
		// to the page.
		joinThisGame();

		// setup the audio
		initAudio();
		this.state = state;
		setState(this.state);
		checkGameResultNotNull();
		beginGame();
	}

	public void joinThisGame() {
		chessService
				.createUserChannel(new AsyncCallback<ManderChessUserSessionInfo>() {

					@Override
					public void onFailure(Throwable caught) {
						logger.log(Level.SEVERE,
								"Failure to create User Channel");
					}

					@Override
					public void onSuccess(ManderChessUserSessionInfo result) {
						// method to handle setting the clientID and color
						handleChannelOpeningAndSetClientWhenSuccessful(result);
					}
				});
	}

	public void loadGameFromDropDown(String textStr) {
		// view.joinThisGame();
	}

	public void openChannelWithSocketListener(String token,
			SocketListener listener) {
		ChannelFactory factory = new ChannelFactoryImpl();
		Channel channel = factory.createChannel(token);
		socket = channel.open(listener);
	}

	public void handleChannelOpeningAndSetClientWhenSuccessful(
			ManderChessUserSessionInfo result) {
		// here we set the clientID and the user color so that only this user
		// can make moves.
		// we then open the channel and implement the interface channel methods
		// channel API can't handle the "#" character for some reason...
		// String [] splitter = result.getClientID().split(">");
		// clientID = splitter[0];
		clientID = result.getClientID();

		clientEmail = result.getEmailAddress();
		userColor = result.getColor();
		matchID = view.getGameIDFromDropDown();

		openChannelWithSocketListener(result.getChannelID(),
				new SocketListener() {

					@Override
					public void onOpen() {
						logger.log(Level.INFO, "opened socket!");
						System.out.println("Opened Channel");

						view.populateCurrentGamesDropDown();
						// if (userColor.equals(Color.BLACK)) {
						// // view.populateCurrentGamesDropDown();
						// // if we have started a new game, add the new game
						// // to the drop down menu for user to choose from
						// later.
						// // we know that game has begun when player that is
						// black
						// // joins the channel
						// }
					}

					@Override
					public void onMessage(String message) {
						logger.log(Level.INFO, "sent message: " + message);
						System.out.println("onMessage: " + message);
						// we want to set the state when the message goes
						// through
						if (message.contains("addNewGame")) {
							view.addGamesToDropDown(HistorySupport
									.deserializeChessGameInfo(message));
						} else if (message.contains("setGameTime")) {
							String[] newStateStrSplit = message.split(":");
							String gameTimeStr = newStateStrSplit[1];

							view.updateCurrentTime(gameTimeStr);

						} else {
							// we have the matchID attached to the end of the
							// message (after stateString
							// so we need to parse it out and only update the
							// state for that particular match
							String[] newStateStrSplit = message.split("#");
							String stStr = newStateStrSplit[0];
							String gameIDStr = newStateStrSplit[1];

							// weird javascript thing where accessing the
							// dropdown menu also puts
							// a newline character at the end of the matchID
							// string
							String replaced = gameIDStr.replace("\n", "");
							if (replaced.equalsIgnoreCase(matchID)) {
								State newState = HistorySupport
										.deserializeHistoryToken(stStr);
								setState(newState);
								checkGameResultNotNull();
								History.newItem(HistorySupport
										.serializeHistory(Presenter.this.state));
							}
						}
					}

					@Override
					public void onError(ChannelError error) {
						logger.log(Level.SEVERE,
								"error sending message through channel api");
					}

					@Override
					public void onClose() {
						logger.log(Level.INFO, "closed channel");
					}
				});
	}

	public void initAudio() {
		// add the audio
		audioHandler.createAudioWithID("move");
		audioHandler.createAudioWithID("scream");
	}

	public boolean cannotMove() {
		return state.getGameResult() != null;
	}

	public void playSoundEffectForMove(State newState, Position toPos, Move m) {
		// play the sound effect
		for (Audio a : audioHandler.audioArray) {
			// look in the audio array and if we are capturing,
			// play the capture sound. We have to check for
			// enpassant capture as well.
			if (newState.getPiece(toPos) != null
					|| (newState.getPiece(m.getFrom()).getKind()
							.equals(PieceKind.PAWN)
							&& state.getEnpassantPosition() != null
							&& (state.getEnpassantPosition().getCol() - 1 == m
									.getFrom().getCol() || state
									.getEnpassantPosition().getCol() + 1 == m
									.getFrom().getCol()) && (state
							.getEnpassantPosition().equals(
									new Position(m.getTo().getRow() - 1, m
											.getTo().getCol())) || state
							.getEnpassantPosition().equals(
									new Position(m.getTo().getRow() + 1, m
											.getTo().getCol()))))) {
				if (a.getSrc().contains("scream")) {
					System.out.println(a.getSrc());
					a.play();
				}
			} else {
				// otherwise, just play the move sound
				if (a.getSrc().contains("move")) {
					System.out.println(a.getSrc());
					a.play();
				}
			}
		}
	}

	public void clickedOn(int row, int col) {
		if (cannotMove()) {
			return;
		}
		if (!state.getTurn().equals(userColor)) {
			// don't allow clicks if it is not your turn!
			return;
		}
		if (selected != null && highlightedPositions.size() != 0) {
			System.out.println("selected TO position: " + selected);
			Position toPos = new Position(row, col);

			for (Move m : highlightedPositions) {
				if (m.getTo().equals(toPos)) {

					State newState = this.state.copy();
					StateChangerImpl changer = new StateChangerImpl();

					playSoundEffectForMove(newState, toPos, m);

					if (this.promotionPK != null) {
						changer.makeMove(newState, new Move(selected,
								m.getTo(), this.promotionPK));
						setPromotionPiece(null);
						view.setPromotionGridHidden();
					} else {
						changer.makeMove(newState, new Move(selected,
								m.getTo(), m.getPromoteToPiece()));
					}
					saveHandler("moveToProcess");
					// animation for the pieces
					MoveAnimation mAnim = new MoveAnimation(
							view.getImageFromPosition(m.getFrom()),
							view.getImageFromPosition(m.getTo()));

					this.state = newState;
					mAnim.run(900);
				}
			}
			unselectPiece();
			return;
		}
		Position piecePos = new Position(row, col);
		Piece piece = state.getPiece(piecePos);

		if (piece != null && piece.getColor().equals(state.getTurn())) {
			unselectPiece();
			selectPiece(piecePos);
		} else {
			unselectPiece();
		}

		selectPiece(new Position(row, col));
		// we are in promotion situation
		if (piece.getKind().equals(PieceKind.PAWN)
				&& selected.getRow() == (piece.getColor().isWhite() ? 6 : 1)) {
			// set the grid visible at the bottom, depending on what color is
			// being promoted
			view.setPromotionGridVisible(piece.getColor());
		}
	}

	public void setPromotionPiece(PieceKind pk) {
		this.promotionPK = pk;
	}

	public void selectPiece(Position pos) {
		// set the selected piece
		selected = pos;

		// get the possible moves from explorerImpl
		highlightedPositions = new StateExplorerImpl()
				.getPossibleMovesFromPosition(state, selected);
		// highlight all the possible moves
		for (Move m : highlightedPositions) {
			// Enable highlight
			view.setHighlighted(m.getTo().getRow(), m.getTo().getCol(), true);
		}
	}

	public void unselectPiece() {
		// don't care. just unhighlight everything
		selected = null;
		// Disable highlight
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				view.setHighlighted(i, j, false);
			}
		}
	}

	public void saveHandler(String whichState) {
		// convenience handler to save the state
		saveStateToLocalStorage(whichState);
	}

	public void saveStateToLocalStorage(String whichState) {
		// method for saving state to local storage for future loading
		Storage storage = Storage.getLocalStorageIfSupported();
		if (storage != null) {
			StorageMap map = new StorageMap(storage);
			if (whichState.equalsIgnoreCase("saved")) {
				map.put("saved", HistorySupport.serializeHistory(this.state));
			} else if (whichState.equalsIgnoreCase("moveToProcess")) {
				map.put("moveToProcess",
						HistorySupport.serializeHistory(this.state));
			}
		}
	}

	public void loadHandler(String whichState) {
		// loads the game from the saved state
		State loadedState = HistorySupport
				.deserializeHistoryToken(loadStateFromLocalStorage(whichState));

		initState(loadedState);
	}

	public String loadStateFromLocalStorage(String whichState) {
		// gets the storage from the storage map and returns state to the
		Storage storage = Storage.getLocalStorageIfSupported();
		String mapStr = null;
		if (storage != null) {
			StorageMap map = new StorageMap(storage);
			if (whichState.equalsIgnoreCase("saved")
					&& map.containsKey("saved")) {
				mapStr = map.get("saved");
			} else if (whichState.equalsIgnoreCase("moveToProcess")
					&& map.containsKey("moveToProcess")) {
				mapStr = map.get("moveToProcess");
			}
		}
		return mapStr;
	}

	public void removeStateFromLocalStorage(String whichState) {
		// remove the local move from the storage if our rpc request was
		// successful
		Storage storage = Storage.getLocalStorageIfSupported();
		if (storage != null) {
			StorageMap map = new StorageMap(storage);
			if (whichState.equalsIgnoreCase("moveToProcess")
					&& map.containsKey("moveToProcess")) {
				map.remove("moveToProcess");
			}
		}
	}

	public void beginGame() {
		// once the game begins, populate the drop down menu with the user's
		// current games.
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String token = event.getValue();
				if (token.isEmpty()) {
					setState(new State());
					initAudio();
				} else {
					setState(HistorySupport.deserializeHistoryToken(token));
				}
				unselectPiece();
				if (!isSinglePlayer) {
					// call the sendAsync method, which handles the state
					// updating
					// for both players
					String stateToUpdate = HistorySupport
							.serializeHistory(state);
					String gameID = view.getGameIDFromDropDown();
					if (gameID == null) {
						Window.alert("No game started!");
						// gameID = "0";
					} else {
						view.sendAsyncStateUpdateToServer(clientID,
								stateToUpdate + "#" + gameID);
					}
				}
			}
		});
	}

	public void setState(State state) {
		// setting whos turn, set the game result, set pieces
		// and call up the dialog box if the game has ended
		view.setWhoseTurn(state.getTurn());
		view.setGameResult(state.getGameResult());
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				view.setPiece(r, c, state.getPiece(r, c));
			}
		}
		this.state = state;
	}

	public void checkGameResultNotNull() {
		if (state.getGameResult() != null) {
			// show the game over dialog if the game has ended
			new GameOverDialog().show();
		}
	}

	class MoveAnimation extends Animation {
		// this is the animation subclass class used to animate the pieces
		// it makes most sense to have the class in the Presenter so that it can
		// call the necessary methods (setState()) for the onComplete callback
		private Image fromPiece;
		private Image toPiece;
		private int deltaX;
		private int deltaY;
		private int offsetX = 0;
		private int offsetY = 0;

		public MoveAnimation(Image fromPiece, Image toPiece) {
			// we basically get the origin x and y (which is actually
			// absolute left/top) and move the piece from the from position
			// to the 'to' position, by a rate of 4 pixels per update
			this.fromPiece = fromPiece;
			this.toPiece = toPiece;

			if (this.fromPiece.getAbsoluteLeft() == this.toPiece
					.getAbsoluteLeft()) {
				this.offsetX = 0;
				this.deltaX = 0;
			} else {
				this.offsetX = (this.fromPiece.getAbsoluteLeft() < this.toPiece
						.getAbsoluteLeft() ? -8 : 8);
			}
			if (this.fromPiece.getAbsoluteTop() == this.toPiece
					.getAbsoluteTop()) {
				this.offsetY = 0;
				this.deltaY = 0;
			} else {
				this.offsetY = (this.fromPiece.getAbsoluteTop() < this.toPiece
						.getAbsoluteTop() ? -8 : 8);
			}
		}

		@Override
		protected void onUpdate(double progress) {

			if (deltaX > toPiece.getAbsoluteLeft()) {
				// if the delta has gone beyond the point where the piece
				// should stop, stop the piece
				deltaX = toPiece.getAbsoluteLeft();
			} else {
				// otherwise, keep updating
				this.deltaX += offsetX;
			}
			if (deltaY > toPiece.getAbsoluteTop()) {
				// if the delta Y has gone beyond the point where the piece
				// should stop, stop the piece
				deltaY = toPiece.getAbsoluteTop();
			} else {
				// otherwise, keep updating
				this.deltaY += offsetY;
			}
			// debugging purposes:
			// System.out.println("x= " + deltaX + "           y= " + deltaY
			// + "             fromAbsLeft= "
			// + fromPiece.getAbsoluteLeft() + "         fromAbsTop= "
			// + fromPiece.getAbsoluteTop() + "     toAbsLeft= "
			// + toPiece.getAbsoluteLeft() + "     toAbsTop= "
			// + toPiece.getAbsoluteTop());

			// set the rect for the image using the new deltaX and deltaY values
			this.fromPiece.setVisibleRect((int) deltaX, (int) deltaY,
					fromPiece.getWidth(), fromPiece.getHeight());
		}

		@Override
		protected void onComplete() {
			// callback used to update the state and add the history item
			super.onComplete();
			Presenter.this.setState(state);
			checkGameResultNotNull();
			History.newItem(HistorySupport.serializeHistory(state));
			if (isSinglePlayer && state.getTurn() != Color.WHITE) {
				System.out.println("Black Player Moving");
				playMoveForBlackComputer();
			}
		}
	}

	public void playMoveForBlackComputer() {
		// single player mode...we basically do a similar thing for
		// onClick() only here we get the best possible move for the heuristic,
		// and then make the move

		ManderHeuristic heuristic = new ManderHeuristic();
		AlphaBetaPruning abp = new AlphaBetaPruning(heuristic);

		State newState = state.copy();

		// get the best possible move for the computer
		Move bestMove = abp.findBestMove(newState, 1, new DateTimer(2000));

		Position toPos = bestMove.getTo();
		StateChangerImpl changer = new StateChangerImpl();
		playSoundEffectForMove(newState, toPos, bestMove);

		if (promotionPK != null) {
			changer.makeMove(state, new Move(bestMove.getFrom(), toPos,
					promotionPK));
			setPromotionPiece(null);
			view.setPromotionGridHidden();
		} else {
			changer.makeMove(state, new Move(bestMove.getFrom(), toPos,
					bestMove.getPromoteToPiece()));
		}
		setState(state);
	}

	public void restartGame() {
		// method that restarts the game when the user clicks it.
//		if (userColor.equals(this.state.getTurn())) {
			this.state = new State();
			setState(this.state);

			if (!isSinglePlayer) {
				view.sendAsyncStateUpdateToServer(clientID,
						HistorySupport.serializeHistory(this.state));
			}
//		} else {
//			Window.alert(messages.restartGameNotYourTurnAlert());
//		}
	}

	public String getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = matchID;
	}

	public void setSinglePlayerMode(boolean isSinglePlayer) {
		this.isSinglePlayer = isSinglePlayer;
	}
}