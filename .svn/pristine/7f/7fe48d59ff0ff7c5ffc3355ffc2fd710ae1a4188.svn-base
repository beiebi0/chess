package org.wenjiechen.hw3;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateChanger;
import org.shared.chess.StateExplorer;
import org.wenjiechen.hw2.StateChangerImpl;
import org.wenjiechen.hw2_5.StateExplorerImpl;
import org.wenjiechen.hw6.client.ChessGameService;
import org.wenjiechen.hw6.client.ChessGameServiceAsync;
import org.wenjiechen.hw8.LanguageChinese;
import org.wenjiechen.hw9.AiPlayer;

import com.google.common.collect.Sets;
import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.ChannelError;
import com.google.gwt.appengine.channel.client.ChannelFactoryImpl;
import com.google.gwt.appengine.channel.client.SocketListener;
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
import com.google.gwt.event.dom.client.HasAllDragAndDropHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.contacts.Contact;
import com.googlecode.gwtphonegap.client.contacts.ContactError;
import com.googlecode.gwtphonegap.client.contacts.ContactField;
import com.googlecode.gwtphonegap.client.contacts.ContactFindCallback;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;

//import com.googlecode.gwtphonegap.

public class Presenter {
	public interface View {
		/**
		 * Renders the piece at this position. If piece is null then the
		 * position is empty.
		 */
		void setPiece(int row, int col, Piece piece);

		void setHighlighted(int row, int col, boolean highlighted);

		void setWhoseTurn(Color color);

		void setGameResult(GameResult gameResult);

		void setLastMove(int row, int col, boolean lastmove);

		void setPromotionChoice(Color color);

		void setPromotionPromt(String str);

		void cleanPromotionPanel();

		void saveHistory(String token);

		void audioPlay();

		void newGridBoard();

		// HasClickHandlers getSaveButton();
		//
		// HasClickHandlers getLoadButton();
		//
		// HasClickHandlers getRestartButton();

		Widget getImage(int r, int c);

		Grid getGameBoard();

		void playFadeAnimation(Move move);

		void playSlowMoveAnimation(Move move);

		void playMoveSound();

		void addSocketListener(SocketListener listener);

		void autoMatch();

		void sendMoveAndState(String move);

		public void setPlayer(String name, String color, int rank);

		void setOpponentPlayer(String name, String color, String rank);

		void setLogoutURL(String logoutLink);

		HasAllDragAndDropHandlers addDnDListener(int row, int col);

		public void addItemToMatchList(String str);

		public HasChangeHandlers getMatchList();

		public int getIndexofSelectionFromMatchList();

		public void cleanMatchList();

		public void fetchMatchList(String email,AsyncCallback<String> matchListCallback);

		public HasClickHandlers getLoadMatchButton();

		public void calculateRank(String matchid, String gameResult,
				AsyncCallback<Void> callback);

		public HasClickHandlers getAutomatchButton();

		public HasClickHandlers getDeleteMatchButton();

		public void deleteMatch(String matchid);

		public HasClickHandlers getPlayWithAI();

		public void addToOracle(String email);
		
		public void getPlayerEmail(String email);
	}

	private static LanguageChinese LC = GWT.create(LanguageChinese.class);
	final PhoneGap phoneGap = GWT.create(PhoneGap.class);

	public View view;
	public State state;
	private StateChanger stateChanger;
	private StateExplorer stateExplorer;
	private Position firstClickPiecePos;
	private Set<Position> possibleMoveToPos;
	private PieceKind promotionKind;
	private boolean canPromotion;
	private Move moveFromMes = null;
	private Color playerColor;
	private State nullState = new State(null, new Piece[8][8], new boolean[2],
			new boolean[2], null, 0, null);
	private boolean[][] didSetDragHandler = new boolean[8][8];
	private String playerName;
	private String playerEmail;
	private int playerRank;
	private Long matchId = new Long(0);
	private Long[] matchIdList;
	private String[] stateList;
	private String[] opponentNames;
	private String[] opponentColors;
	private String[] opponentRanks;
	private boolean autoMatch = false; // only unmatched player can click
										// automatch
	private int selectedIdx; // idx of selected matchlist
	private boolean playWithCom = false;
	private AiPlayer computer = null;
	private Color computerColor = Color.WHITE;
	Timer timer = null;

	public Presenter() {
		stateChanger = new StateChangerImpl();
		stateExplorer = new StateExplorerImpl();
		promotionKind = null;
		possibleMoveToPos = Sets.newHashSet();
	}

	public void setView(View view) {
		this.view = view;
		final View finalView = view;

		// add drag and drop handlers
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				if (didSetDragHandler[row][col] == false) {
					didSetDragHandler[row][col] = true;
					final int r = row;
					final int c = col;
					HasAllDragAndDropHandlers image = view.addDnDListener(row,
							col);

					image.addDragStartHandler(new DragStartHandler() {
						@Override
						public void onDragStart(DragStartEvent event) {
							// Required: set data for the event.
							// event.setData("text", "Hello World");
							// Optional: show a copy of the widget under cursor.
							event.getDataTransfer()
									.setDragImage(
											finalView.getImage(r, c)
													.getElement(), 0, 0);
							System.out
									.println("presenter() onDragStart(): dragPiece");
							dragPiece(r, c);
						}
					});

					image.addDragOverHandler(new DragOverHandler() {
						@Override
						public void onDragOver(DragOverEvent event) {
							finalView.getImage(r, c).getElement().getStyle()
									.clearBackgroundColor();
						}
					});

					image.addDropHandler(new DropHandler() {
						@Override
						public void onDrop(DropEvent event) {
							event.preventDefault();
							dropPiece(r, c);
						}
					});
				}
			}
		}

		view.addSocketListener(new SocketListener() {

			@Override
			public void onOpen() {
				Window.alert(LC.ifNotMatched());
				updateMatchList();
			}

			@Override
			public void onMessage(String message) {
				// Window.alert(message);
				if (message.contains("emailMatch")) {
					parseMessageFromEmailMatchAndMatch(message);
				} else if (message.contains("sendMove")) {
					parseMessageFromSendMoveAndMove(message);
				} else if (message.contains("autoMatch")) {
					parseMessageFromAutoMatch(message);
				} else if (message.contains("deleteMatch")) {
					parseMessageFromDeleteMatch(message);
				} else {
					Window.alert("undefined message: " + message);
				}
			}

			@Override
			public void onError(ChannelError error) {
				Window.alert(error.getCode() + ": " + error.getDescription());
			}

			@Override
			public void onClose() {
				// Window.alert("Channel closed!");
			}

		});

		view.getMatchList().addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				selectedIdx = finalView.getIndexofSelectionFromMatchList();
				Window.alert("press load or delete match ");
			}
		});

		view.getLoadMatchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (selectedIdx != -1) {
					loadStateWithMatchId(selectedIdx);
					selectedIdx = -1;
				}
			}
		});

		view.getDeleteMatchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (selectedIdx != -1) {
					deleteMatchWithMatchId(selectedIdx);
					selectedIdx = -1;
				}
			}
		});

		view.getAutomatchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// call server autoMatch()
				if (matchId == 0) {
					finalView.autoMatch();
					autoMatch = true;
				} else {
					Window.alert("you are playing a game. If want automatch, please delete match first.");
				}
			}
		});

		view.getPlayWithAI().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (playWithCom == false) {
					Window.alert("press \"play with computer\" button to star a new game");
					// Window.alert("timer is called 3000");
					initComputer();
					computerMove();
				} else {
					Window.alert("a new round play with computer");
					setState(new State());
					computerMove();
				}
			}
		});
	}

	private void initComputer() {
		computer = new AiPlayer();
		playerColor = Color.BLACK;
		computerColor = Color.WHITE;
		playWithCom = true;
		timer = new Timer() {
			@Override
			public void run() {
				Move aiMove = computer.chooseMove(state);
				makeMoveUpdateState(aiMove);
			}
		};
		view.setPlayer(playerName, "Black", playerRank);
		view.setOpponentPlayer("Computer", "White", "4-depths");
		setState(new State());
	}

	private void computerMove() {
		timer.schedule(1500);
	}

	private void deleteMatchWithMatchId(int selectedIdx) {
		Long matchid = matchIdList[selectedIdx];
		view.deleteMatch(matchid.toString());
	}

	// "deleteMatch>" + matchid;
	private void parseMessageFromDeleteMatch(String message) {
		updateMatchList();
		String mes[] = message.split(">");
		Long id = Long.parseLong(mes[1]);
		if (matchId.equals(id)) {
			matchId = new Long(0);
			view.setPlayer(playerName, "unmatched", playerRank);
			view.setOpponentPlayer("", "", "");
			setState(nullState);
		}
	}

	private void parseMessageFromAutoMatch(String message) {
		// not this client send automatch
		if (matchId != 0 || autoMatch == false) {
			return;
		}

		String[] mes1 = message.split(">");

		// match faild
		if (mes1[1].contains("noOtherPlayer")) {
			Window.alert("No other players, please wait for a moment");
			return;
		}

		// match success
		Long id = new Long(0);
		Color selfcolor = null;
		String selfcolorstr = "";
		String oppColor = "";
		String oppName = "";
		String oppRank = "";

		String tmp = mes1[1];
		String[] mes = tmp.split("=");
		id = Long.parseLong(mes[1]);
		selfcolorstr = mes[3];
		selfcolor = Color.valueOf(selfcolorstr.toUpperCase());
		oppColor = (selfcolorstr.equals("white") ? "black" : "white");
		oppName = mes[5];
		oppRank = mes[7];

		// load new
		matchId = id;
		playerColor = selfcolor;
		setState(new State());
		view.setOpponentPlayer(oppName, oppColor, oppRank);
		view.setPlayer(playerName, selfcolorstr, playerRank);
		autoMatch = false;// can't automatch again
		updateMatchList();
	}

	/**
	 * 1 check matchId
	 * 
	 * @param message
	 *            : sendMove>"matchId="+matchId+"=move="+move.toString()
	 */
	public void parseMessageFromSendMoveAndMove(String message) {
		// window.alert("parseMessageFromSendMoveAndMove(): 1" + message);
		String mes[] = message.split("<");
		String tmp = mes[1];
		String[] info = tmp.split("=");
		Long id = Long.parseLong(info[1]);
		Move move = parseMove(info[3]);

		if (matchId.equals(id)) {
			// match success
			makeMoveUpdateState(move);
		}
	}

	private void makeMoveUpdateState(Move move) {
		cleanHighlight();
		stateChanger.makeMove(state, move);
		setState(state);
		// view.saveHistory(HistoryCoder.codingStateToHistory(state));
		view.playFadeAnimation(move);
		view.playMoveSound();
		firstClickPiecePos = null;
		possibleMoveToPos.clear();
	}

	public void calculateRank() {
		// game over calculate Rank
		if (state.getGameResult() != null) {
			String gameResult = "";
			if (state.getGameResult().isDraw() == true) {
				gameResult = "draw";
			} else if (state.getGameResult().getWinner() == Color.WHITE) {
				gameResult = "white";
			} else {
				gameResult = "black";
			}

			view.calculateRank(matchId.toString(), gameResult,
					new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Void result) {
						}
					});
		}
	}

	public void parseMessageForLoginPlayer(String message) {
		// "name=" + player.getName() + "=email=" +
		// player.getEmail()+"=rank="+player.getRank()
		String[] info = message.split("=");
		playerName = info[1];
		playerEmail = info[3];
		playerRank = Integer.parseInt(info[5]);
		view.setPlayer(playerName, LC.unset(), playerRank);
	}

	public void loadStateWithMatchId(int selectedIdx) {
		matchId = matchIdList[selectedIdx];
		playerColor = opponentColors[selectedIdx].equals("white") ? Color.BLACK
				: Color.WHITE;
		String playerColorStr = playerColor == Color.WHITE ? "white" : "black";
		view.setPlayer(playerName, playerColorStr, playerRank);
		view.setOpponentPlayer(opponentNames[selectedIdx],
				opponentColors[selectedIdx], opponentRanks[selectedIdx]);
		String statestr = stateList[selectedIdx];
		State matchState;
		if (statestr.equals("newgame") == true) {
			matchState = new State();
		} else {
			matchState = HistoryCoder.decodingHistoryString(statestr);
		}
		setState(matchState);
	}

	/**
	 * fetch matchList from server and update matchlist box
	 */
	public void updateMatchList() {
		view.cleanMatchList();
		final View finalView = view;

		AsyncCallback<String> matchlistCallback = new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(LC.errorCannotFetchMatchList());
			}

			@Override
			public void onSuccess(String result) {
				String matchlist = result;
				if (!matchlist.equals("")) {
					String[] matchlines = parseMsgFromFetchMatchList(matchlist);
					for (String match : matchlines) {
						finalView.addItemToMatchList(match);
					}
				}// if
			}
		};
		view.fetchMatchList(playerEmail,matchlistCallback);
	}

	public String[] parseMsgFromFetchMatchList(String matchlist) {
		if (matchlist.equals("")) {
			return null;
		}
		// Window.alert("matchlist 1 " + matchlist);
		String[] matchlines = matchlist.split("#");
		int arrayLength = matchlines.length;
		String[] showlines = new String[arrayLength];
		for (int i = 0; i < arrayLength; ++i) {
			showlines[i] = "";
		}

		matchIdList = new Long[arrayLength];
		stateList = new String[arrayLength];
		opponentNames = new String[arrayLength];
		opponentColors = new String[arrayLength];
		opponentRanks = new String[arrayLength];

		Long matchId;
		String state;
		String selfColorStr;
		String oppName;
		String oppRank;
		String oppColor;
		String date;

		int i = 0; // have to define out of loop
		for (String line : matchlines) {
			// line = "matchid=1=color=3=opponentname=5=opponentemail=7=state=9"
			String[] tuples = line.split("=");
			matchId = Long.parseLong(tuples[1]);
			selfColorStr = tuples[3];
			oppColor = selfColorStr.equals("white") ? "black" : "white";
			oppName = tuples[5];
			oppRank = tuples[7];
			state = tuples[9];
			date = tuples[11];

			matchIdList[i] = matchId;
			stateList[i] = state;
			opponentNames[i] = oppName;
			opponentColors[i] = oppColor;
			opponentRanks[i] = oppRank;

			showlines[i] += LC.showlineColor() + selfColorStr;
			showlines[i] += LC.showlineOppNmae() + oppName;
			showlines[i] += LC.showlineRank() + oppRank;
			showlines[i] += LC.showlinedate() + date;
			++i;
		}
		return showlines;
	}

	public void parseMessageFromEmailMatchAndMatch(String message) {
		Long id = new Long(0);
		Color selfcolor = null;
		String selfcolorstr = "";
		String oppColor = "";
		String oppName = "";
		String oppRank = "";

		String[] mes1 = message.split(">");
		String tmp = mes1[1];
		if (mes1[1].contains("haveMatchedBefore")) {
			Window.alert(LC.youHaveAoOnGoingGame());
			return;
		}
		String[] mes = tmp.split("=");
		id = Long.parseLong(mes[1]);
		selfcolorstr = mes[3];
		selfcolor = Color.valueOf(selfcolorstr.toUpperCase());
		oppColor = (selfcolorstr.equals("white") ? "black" : "white");
		oppName = mes[5];
		oppRank = mes[7];

		// load new
		matchId = id;
		playerColor = selfcolor;
		setState(new State());
		view.setOpponentPlayer(oppName, oppColor, oppRank);
		view.setPlayer(playerName, selfcolorstr, playerRank);
		updateMatchList();
	}

	/**
	 * call this method when click 1. highlight the cells that the piece can
	 * move to, and store these position in Set<Position> possibleMoveToPos 2.
	 * set position firstClickPiecePos = clickPos
	 * 
	 * @param clickPos
	 */
	public void dealWithClick(int row, int col) {
		if (playerColor != null && playerColor.equals(state.getTurn()) == false) {
			return;
		}
		if (state.getGameResult() != null) {
			return;
		}
		Position clickPos = new Position(row, col);
		if (!possibleMoveToPos.isEmpty()
				&& !possibleMoveToPos.contains(clickPos)) {
			cleanHighlight();
			view.cleanPromotionPanel();
			highlightPossibleStartPos();
			possibleMoveToPos.clear();
			return;
		}
		// first click
		if (state.getPiece(clickPos) != null
				&& state.getPiece(clickPos).getColor() == state.getTurn()
				&& stateExplorer.getPossibleStartPositions(state).contains(
						clickPos) == true) {
			System.out.println("first click");
			view.cleanPromotionPanel();
			canPromotion = ((state.getPiece(clickPos).getKind() == PieceKind.PAWN) == true && (state
					.getTurn() == Color.WHITE ? clickPos.getRow() == 6
					: clickPos.getRow() == 1) == true);
			if (canPromotion == true) {
				view.setPromotionChoice(state.getTurn());
			}
			cleanHighlight();
			firstClickPiecePos = clickPos;
			highlightPossibleMoveToPos(clickPos);
		}
		// capture opponent's pieces or move to a space
		else if (firstClickPiecePos != null
				&& possibleMoveToPos.contains(clickPos) == true
				&& (state.getPiece(clickPos) == null || state
						.getPiece(clickPos).getColor() != state.getTurn())) {

			// Window.alert("second click");
			Move move;
			if (canPromotion == true) {
				if (promotionKind == null) {
					view.setPromotionPromt(LC.promotionPromt());
					return;
				}
				move = new Move(firstClickPiecePos, clickPos, promotionKind);
				promotionKind = null;
				canPromotion = false;
				view.cleanPromotionPanel();
			} else {
				move = new Move(firstClickPiecePos, clickPos, null);
			}

			makeMoveUpdateState(move);
			if (playWithCom == false) {
				calculateRank();
				String stateStr = HistoryCoder.codingStateToHistory(state);
				String moveAndStateMes = "matchId=" + matchId + "=move="
						+ move.toString() + "=state=" + stateStr;
				view.sendMoveAndState(moveAndStateMes);
			} else {
				computerMove();
			}
		}
	}

	/**
	 * clean highlight, makeMove, setState, save history and clean private
	 * variables.
	 * 
	 * @param move
	 */
	public void refreshState(Move move) {
		// cleanHighlight();
		// dropAudio.play();
		// stateChanger.makeMove(state, move);
		// setState(state);
		// view.saveHistory(HistoryCoder.codingStateToHistory(state));
		// firstClickPiecePos = null;
		// possibleMoveToPos.clear();
	}

	private Move parseMove(String message) {
		PieceKind proKind = null;
		if (message.contains("QUEEN")) {
			proKind = PieceKind.QUEEN;
		} else if (message.contains("ROOK")) {
			proKind = PieceKind.ROOK;
		} else if (message.contains("BISHOP")) {
			proKind = PieceKind.BISHOP;
		} else if (message.contains("KNIGHT")) {
			proKind = PieceKind.KNIGHT;
		}
		message = message.substring(0, 12);
		Position from = new Position(Integer.parseInt(message.substring(1, 2)),
				Integer.parseInt(message.substring(3, 4)));
		Position to = new Position(Integer.parseInt(message.substring(8, 9)),
				Integer.parseInt(message.substring(10, 11)));
		Move move = new Move(from, to, proKind);

		return move;
	}

	public void afterSlowMove(Move move) {
		cleanHighlight();
		view.playMoveSound();
		stateChanger.makeMove(state, move);
		setState(state);
		view.saveHistory(HistoryCoder.codingStateToHistory(state));
		firstClickPiecePos = null;
		possibleMoveToPos.clear();
	}

	private void highlightPossibleMoveToPos(Position clickPos) {
		if (playerColor != null && playerColor.equals(state.getTurn()) == false) {
			return;
		}
		Set<Move> moves = stateExplorer.getPossibleMovesFromPosition(state,
				clickPos);
		possibleMoveToPos.clear();
		if (moves != null) {
			for (Move m : moves) {
				if (canPromotion == false) {
					view.setHighlighted(m.getTo().getRow(), m.getTo().getCol(),
							true);
				}
				possibleMoveToPos.add(m.getTo());
			}
		}
	}

	public void getPromotionKind(int pos) {
		if (firstClickPiecePos != null && canPromotion == true) {
			switch (pos) {
			case 0:
				promotionKind = PieceKind.BISHOP;
				break;
			case 1:
				promotionKind = PieceKind.KNIGHT;
				break;
			case 2:
				promotionKind = PieceKind.ROOK;
				break;
			case 3:
				promotionKind = PieceKind.QUEEN;
				break;
			}
			// highlighted Pawn's possible move to positions
			for (Position m : possibleMoveToPos) {
				view.setHighlighted(m.getRow(), m.getCol(), true);
			}
		}// if
	}

	/**
	 * highlight possible start positions
	 */
	private void highlightPossibleStartPos() {
		if (playerColor != null && playerColor.equals(state.getTurn()) == false) {
			return;
		}
		Set<Position> startPos = stateExplorer.getPossibleStartPositions(state);
		for (Position s : startPos) {
			view.setHighlighted(s.getRow(), s.getCol(), true);
		}
	}

	/**
	 * clean highlighted cells and highlighted last move cell
	 */
	public void cleanHighlight() {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				view.setHighlighted(r, c, false);
				view.setLastMove(r, c, false);
			}
		}
	}

	public void setState(State state) {
		this.state = state;
		view.setWhoseTurn(state.getTurn());
		view.setGameResult(state.getGameResult());
		cleanHighlight();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				view.setPiece(r, c, state.getPiece(r, c));
			}
		}
		if (playerColor != null && playerColor.equals(state.getTurn())) {
			highlightPossibleStartPos();
		}
	}

	public State getState() {
		return this.state;
	}

	public void loadStorage(String token) {
		cleanHighlight();
		setState(HistoryCoder.decodingHistoryString(token));
		highlightPossibleStartPos();
	}

	public void saveStorage(Storage storage) {
		if (storage != null) {
			storage.setItem("savedState",
					HistoryCoder.codingStateToHistory(state));
		}
	}

	public void viewBinder() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String historyStr = event.getValue();
				if (historyStr.isEmpty()) {
					setState(new State());
				} else {
					System.out
							.println("History.addValueChangeHandler(): setState");
					setState(HistoryCoder.decodingHistoryString(historyStr));
				}
			}
		});

		// view.getSaveButton().addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// Storage storage = Storage.getLocalStorageIfSupported();
		// saveStorage(storage);
		// }
		// });
		//
		// view.getLoadButton().addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// Storage storage = Storage.getLocalStorageIfSupported();
		// String token = storage.getItem("savedState");
		// loadStorage(token);
		// }
		// });
		//
		// view.getRestartButton().addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// System.out.println("restart*********************");
		// setState(new State());
		// }
		// });
	}

	/**
	 * 
	 * @param row
	 * @param col
	 */
	public void dragPiece(int row, int col) {
		if (playerColor != null && playerColor.equals(state.getTurn()) == false) {
			return;
		}
		if (state.getGameResult() != null) {
			return;
		}
		Position dragPos = new Position(row, col);
		if (!possibleMoveToPos.isEmpty()
				&& !possibleMoveToPos.contains(dragPos)) {
			cleanHighlight();
			view.cleanPromotionPanel();
			highlightPossibleStartPos();
			possibleMoveToPos.clear();
			return;
		}
		if (state.getPiece(dragPos) != null
				&& state.getPiece(dragPos).getColor() == state.getTurn()
				&& stateExplorer.getPossibleStartPositions(state).contains(
						dragPos) == true) {
			System.out.println("drag piece");
			view.cleanPromotionPanel();
			canPromotion = ((state.getPiece(dragPos).getKind() == PieceKind.PAWN) == true && (state
					.getTurn() == Color.WHITE ? dragPos.getRow() == 6 : dragPos
					.getRow() == 1) == true);
			if (canPromotion == true) {
				view.setPromotionChoice(state.getTurn());
			}
			cleanHighlight();
			firstClickPiecePos = dragPos;
			highlightPossibleMoveToPos(dragPos);
		}
	}

	/**
	 * 
	 * @param row
	 * @param col
	 */
	public void dropPiece(int row, int col) {
		Position dropPos = new Position(row, col);
		if (firstClickPiecePos != null
				&& possibleMoveToPos.contains(dropPos) == true
				&& (state.getPiece(dropPos) == null || state.getPiece(dropPos)
						.getColor() != state.getTurn())) {
			System.out.println("drop piece");
			Move move;
			if (canPromotion == true) {
				if (promotionKind == null) {
					view.setPromotionPromt(LC.promotionPromt());
					return;
				}
				move = new Move(firstClickPiecePos, dropPos, promotionKind);
				promotionKind = null;
				canPromotion = false;
				view.cleanPromotionPanel();
			} else {
				move = new Move(firstClickPiecePos, dropPos, null);
			}
			makeMoveUpdateState(move);
			if (playWithCom == false) {
				calculateRank();
				String stateStr = HistoryCoder.codingStateToHistory(state);
				String moveAndStateMes = "matchId=" + matchId + "=move="
						+ move.toString() + "=state=" + stateStr;
				view.sendMoveAndState(moveAndStateMes);
			} else {
				computerMove();
			}
		}
	}

	// hw11 find contact

//	public void getContacts() {
//		LightArray<String> fields = CollectionFactory.<String> constructArray();
//		fields.push("emails");
//		ContactFindOptions findOptions = new ContactFindOptions("", true);
//		phoneGap.getContacts().find(fields, new ContactFindCallback() {
//			@Override
//			public void onSuccess(LightArray<Contact> contacts) {
////				Window.alert("1 get contacts onSuccess lenght = " + contacts.length());
//				for (int i = 0; i < contacts.length(); i++) {
//					LightArray<ContactField> emails = contacts.get(i)
//							.getEmails();
//					for (int j = 0; j < emails.length(); j++) {
//						ContactField email = emails.get(j);
//						view.addToOracle(email.getValue());
//					}
//				}
////				Window.alert("2 get contacts onSuccess finish");
//			}
//
//			@Override
//			public void onFailure(ContactError error) {
//				// something went wrong, doh!
//				Window.alert("phonegap get contacts faild");
//			}
//		}, findOptions);
//	}
		
	public void getPlayerEmail(String email){
		playerEmail = email;
		playerName = email.substring(0, email.indexOf("@"));
		view.setPlayer(playerName, "unMatched", 1500);
	}
}