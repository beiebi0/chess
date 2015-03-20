package org.simongellis.hw3;

import java.util.Date;
import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import org.shared.chess.StateExplorer;
import org.simongellis.hw2_5.StateExplorerImpl;
import org.simongellis.hw7.client.Serializer;

import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.DragHandlerAdapter;
import com.allen_sauer.gwt.dnd.client.DragStartEvent;
import com.google.gwt.appengine.channel.client.ChannelError;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class Presenter {
	public interface Dropper {
		public abstract void onDrop(Position pos);
	}

	public interface View {
		// Return a square on the board (used for TapHandlers)
		HasClickHandlers getSquare(int row, int col);

		// Return the button to open the options panel (used for ClickHandlers)
		HasClickHandlers getOptionsButton();

		// Display the options panel
		void displayOptions();

		// Return the text currently in the email textbox
		String getEmailBoxText();

		// Set the text to display in the email textbox
		void setEmailBoxText(String text);

		// Return the AI match button (used for TapHandlers)
		HasClickHandlers getAIButton();

		// Return the email match button (used for TapHandlers)
		HasClickHandlers getEmailButton();

		// Return the automatch button (used for TapHandlers)
		HasClickHandlers getAutoButton();

		// Return the automatch canceling button (used for TapHandlers)
		HasClickHandlers getAutoCancelButton();

		// Return the delete match button (used for TapHandlers)
		HasClickHandlers getDeleteButton();

		// Return the button to close the options panel (used for TapHandlers)
		HasClickHandlers getCloseButton();

		// Close the options panel
		void closeOptions();

		// Add a ChangeHandler to the view's list of matches
		void addMatchChangeHandler(ChangeHandler handler);

		// Returns whether or not the View supports drag and drop
		boolean isDragAndDropSupported();

		// Initialize the behavior of a widget being dragged
		void initializeDragging(DragHandler dragHandler);

		// Initialize the behavior of a widget bieng dropped
		void initializeDropping(final Dropper dropHandler);

		// Find the board position associated with an image
		Position getPosition(Image image);

		// Return a square on the board (used for drag+drop)
		Widget getSquareDnD(int row, int col);

		// Add a ValueChangeHandler to History
		void addHistoryHandler(ValueChangeHandler<String> handler);

		// Get the current history token
		String getHistoryToken();

		// Add a token to history
		void addHistoryToken(String token);

		// Set a piece on the grid to have the right image
		void setPiece(int row, int col, Piece piece);

		// Animate a piece moving from one square to another
		void animateMove(int startRow, int startCol, int endRow, int endCol, Piece start,
				PieceKind end, boolean capture);

		// Cancel an animation (for if a game is loaded in the middle of an
		// animation)
		void cancelAnimation();

		// Set whether or not the panel of promotion options is visible
		void setPromotionPiecesVisible(boolean visible);

		// Highlight or unhighlight a specified square
		void setHighlighted(int row, int col, boolean highlighted);

		// Set the color of the pieces on the panel of promotion options
		void setWhoseTurn(Color color);

		// Set the game result
		void setGameResult(GameResult result);

		// Set which color this player is playing as for a particular match
		void setPlayerColor(Long id, Color color);

		// Get the player's color
		Color getPlayerColor(Long id);

		// Set the ELO rank of the player
		void setPlayerRank(int rank);

		// Set the name of the opponent in the display for a particular match
		void setOpponentName(Long id, String name);

		// Set the email of the opponent for a particular match
		void setOpponentEmail(Long id, String email);

		// Set the ELO rank of the opponent in a specific match
		void setOpponentRank(Long matchId, int rank);

		// Set the start date for a particular match
		void setStartDate(Long id, Date date);

		// Set the game state for a particular match
		void setGameState(Long id, State state);

		// Get the game state for a particular match
		State getGameState(Long id);

		// Show or hide the matchmaking popup
		void setMatchmakingPopupVisible(boolean visible);

		// Play the sound effect associated with moving a piece to an empty
		// square
		void playPieceDownSound();

		// Play the sound effect associated with moving a piece to an occupied
		// square
		void playPieceCapturedSound();

		// See if local storage is supported
		boolean isLocalStorageSupported();

		// Read a string from local storage
		String readValueFromLocalStorage(String key);

		// Write a value to local storage
		void writeValueToLocalStorage(String key, String value);

		// Sets the SocketListener that interfaces with the server
		void addSocketListener(SocketListener listener);

		// Make a match against the AI
		void makeAIMatch();

		// Match the player to an opponent specified in email
		void makeEmailMatch(String email);

		// Automatically match the player to an opponent
		void makeAutoMatch();

		// Cancel the matchmaking
		void cancelAutoMatch();

		// Delete the currently active match
		void deleteCurrentMatch();

		// Make a move and return the current state of the board
		void makeMove(Long matchID, String serializedMove, AsyncCallback<String> callback);

		// Make a move in the current match and return the current state of the
		// board
		void makeMove(String serializedMove, AsyncCallback<String> callback);

		// Get whether the current opponent is an AI
		boolean isOpponentAI(Long currentMatch);

		// Find the move made by the AI
		void makeAIMove(AsyncCallback<String> callback);

		// Get the ID of the currently visible match
		Long getCurrentMatch();

		// Get the match currently selected in the list of matches
		Long getSelectedMatch();

		// Set the match ID
		void setCurrentMatch(Long matchId);

		// Update the representation of a match
		void updateMatch(String matchId, State state);

		// Load all matches from the server
		void loadAllMatches(AsyncCallback<String[]> callback);

		// Display an error (in response to an invalid email address)
		void displayEmailErrorMessage();

		// Display an error (in response to an attempted move made while the
		// connection was lost)
		void displayMoveErrorMessage();

		// Display an error (in response to a lost connection
		void displayConnectionErrorMessage();

		// Add a timer to repeat at a set interval
		void addTimer(int interval, final Runnable actionToPerform);

		// Attempt to reconnect on a channel
		void attemptToReconnect(boolean needNewConnection);

		// Display a message saying the connection was successfully
		// reestablished
		void displayReconnectionMessage();

	}

	private View view;

	private State state;
	private Color playerColor;
	private StateExplorer stateExplorer;
	private Serializer serializer;
	private Set<Move> possibleMoves;
	private Position moveFrom;
	private Position moveTo;

	private boolean shouldAttemptToReconnect;
	private boolean needNewConnection;

	public Presenter() {
		state = new State();
		playerColor = null;
		stateExplorer = new StateExplorerImpl();
		serializer = new Serializer();
		possibleMoves = null;
		moveFrom = null;
		moveTo = null;
		shouldAttemptToReconnect = false;
		needNewConnection = false;
	}

	public void setView(View view) {
		this.view = view;
		final View finalVersionOfView = view;

		// Add ClickHandlers to the grid and to the button
		for (int r = 0; r < State.ROWS + 1; ++r) {
			for (int c = 0; c < State.COLS; ++c) {
				final int row = r;
				final int col = c;
				view.getSquare(row, col).addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent e) {
						selectPiece(row, col);
					}
				});
			}
		}

		if (view.isDragAndDropSupported()) {
			initializeDragAndDrop();
		}

		/*
		 * view.addHistoryHandler(new ValueChangeHandler<String>() {
		 * 
		 * @Override public void onValueChange(ValueChangeEvent<String> event) {
		 * String historyToken = event.getValue(); String currentState =
		 * serializeState(state); if (!historyToken.equals(currentState)) {
		 * setState(unserializeState(historyToken));
		 * finalVersionOfView.sendState(historyToken); } } });
		 */

		view.addSocketListener(new SocketListener() {
			@Override
			public void onOpen() {
				finalVersionOfView.loadAllMatches(new AsyncCallback<String[]>() {
					@Override
					public void onFailure(Throwable caught) {
						String matchList = finalVersionOfView.readValueFromLocalStorage("matches");
						if (matchList != null) {
							String[] matchIDs = matchList.split("[ ]");
							for (String matchID : matchIDs) {
								String matchInfo = finalVersionOfView
										.readValueFromLocalStorage(matchID);
								if (matchInfo != null) {
									loadMatch(matchInfo);
								}
							}
						}
					}

					@Override
					public void onSuccess(String[] result) {
						shouldAttemptToReconnect = false;
						needNewConnection = false;
						String matchList = null;
						for (String matchInfo : result) {
							String matchID = loadMatch(matchInfo);
							if (matchList == null)
								matchList = matchID;
							else
								matchList += " " + matchID;
							finalVersionOfView.writeValueToLocalStorage(matchID, matchInfo);
						}
						finalVersionOfView.writeValueToLocalStorage("matches", matchList);
					}
				});
			}

			@Override
			public void onMessage(String message) {
				String[] tokens = message.split("[ ]");

				if (tokens[0].equals("aimatch")) {

					Long id = Long.valueOf(tokens[1]);
					playerColor = Color.WHITE;
					State state = serializer.unserializeState(tokens[3]);

					finalVersionOfView.setPlayerColor(id, playerColor);
					finalVersionOfView.setOpponentEmail(id, "AI");
					finalVersionOfView.setStartDate(id, new Date(Long.parseLong(tokens[2])));
					finalVersionOfView.updateMatch(tokens[1], state);
					finalVersionOfView.setCurrentMatch(id);
					setState(state);

					if (!finalVersionOfView.isLocalStorageSupported())
						return;
					String matches = finalVersionOfView.readValueFromLocalStorage("matches");
					matches += " " + tokens[1];
					finalVersionOfView.writeValueToLocalStorage("matches", matches);
					String matchData = tokens[1];
					matchData += " W noname AI 1500 ";
					matchData += tokens[2] + " ";
					matchData += tokens[3];
					finalVersionOfView.writeValueToLocalStorage(tokens[1], matchData);

				} else if (tokens[0].equals("newmatch")) {

					Long id = Long.valueOf(tokens[1]);
					playerColor = (tokens[2].equals("W") ? Color.WHITE : Color.BLACK);
					State state = serializer.unserializeState(tokens[7]);

					finalVersionOfView.setPlayerColor(id, playerColor);
					finalVersionOfView.setOpponentEmail(id, tokens[4]);
					if (!tokens[4].equals("AI")) {
						finalVersionOfView.setOpponentName(id, tokens[3]);
						finalVersionOfView.setOpponentRank(id, Integer.parseInt(tokens[5]));
					}
					finalVersionOfView.setStartDate(id, new Date(Long.parseLong(tokens[6])));
					finalVersionOfView.updateMatch(tokens[1], state);
					finalVersionOfView.setCurrentMatch(id);
					setState(state);

					if (!finalVersionOfView.isLocalStorageSupported())
						return;
					String matches = finalVersionOfView.readValueFromLocalStorage("matches");
					matches += " " + tokens[1];
					finalVersionOfView.writeValueToLocalStorage("matches", matches);
					String matchData = message.substring(9);
					finalVersionOfView.writeValueToLocalStorage(tokens[1], matchData);

					finalVersionOfView.setMatchmakingPopupVisible(false);

				} else if (tokens[0].equals("move")) {

					State newState = serializer.unserializeState(tokens[2]);
					Long id = Long.valueOf(tokens[1]);
					finalVersionOfView.setGameState(id, newState);

					if (id.equals(finalVersionOfView.getCurrentMatch())) {
						State oldState = state.copy();
						state = newState;
						animateMove(oldState, serializer.unserializeMove(tokens[3]));
					} else
						finalVersionOfView.updateMatch(tokens[1], newState);

					if (!finalVersionOfView.isLocalStorageSupported())
						return;
					String matchData = finalVersionOfView.readValueFromLocalStorage(tokens[1]);
					matchData = matchData.substring(0, matchData.lastIndexOf(' '));
					matchData += " " + tokens[2];
					finalVersionOfView.writeValueToLocalStorage(tokens[1], matchData);

				} else if (tokens[0].equals("state")) {

					State sentState = serializer.unserializeState(tokens[2]);
					Long id = Long.valueOf(tokens[1]);
					finalVersionOfView.setGameState(id, sentState);

					if (Long.valueOf(tokens[1]).equals(finalVersionOfView.getCurrentMatch()))
						setState(sentState);
					else
						finalVersionOfView.updateMatch(tokens[1], sentState);

					if (!finalVersionOfView.isLocalStorageSupported())
						return;
					String matchData = finalVersionOfView.readValueFromLocalStorage(tokens[1]);
					matchData = matchData.substring(0, matchData.lastIndexOf(' '));
					matchData = " " + tokens[2];
					finalVersionOfView.writeValueToLocalStorage(tokens[1], matchData);

				} else if (tokens[0].equals("rank")) {

					tokens[2] = tokens[2].substring(0, tokens[2].length() - 2);
					int rank = Integer.parseInt(tokens[2]);
					if (tokens[1].equals("me"))
						finalVersionOfView.setPlayerRank(rank);
					else {
						finalVersionOfView.setOpponentRank(Long.valueOf(tokens[1]), rank);
						if (!finalVersionOfView.isLocalStorageSupported())
							return;
						String matchData = finalVersionOfView.readValueFromLocalStorage(tokens[1]);
						int rankStartIndex = 0;
						for (int i = 0; i < 4; ++i)
							rankStartIndex = matchData.indexOf(' ', rankStartIndex);
						rankStartIndex++;
						int rankEndIndex = matchData.indexOf(' ', rankStartIndex);
						matchData = matchData.substring(0, rankStartIndex) + tokens[2]
								+ matchData.substring(rankEndIndex);
						finalVersionOfView.writeValueToLocalStorage(tokens[1], matchData);
					}

				}
			}

			@Override
			public void onError(ChannelError error) {
				finalVersionOfView.displayConnectionErrorMessage();
				needNewConnection = error.getCode().equals("0") || error.getCode().equals("-1");
				shouldAttemptToReconnect = true;
			}

			@Override
			public void onClose() {
			}
		});

		view.getOptionsButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				finalVersionOfView.displayOptions();
			}
		});

		view.getAIButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				finalVersionOfView.makeAIMatch();
				finalVersionOfView.closeOptions();
			}
		});

		view.getEmailButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String text = finalVersionOfView.getEmailBoxText();
				// Email validation regular expression found on...
				// http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
				if (text.matches("^[_A-Za-z0-9-\\+]+" + "(\\.[_A-Za-z0-9-]+)*" + "@[A-Za-z0-9-]+"
						+ "(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$")) {
					finalVersionOfView.setEmailBoxText("");
					finalVersionOfView.makeEmailMatch(text);
				} else {
					finalVersionOfView.displayEmailErrorMessage();
				}
			}
		});

		view.getAutoButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				finalVersionOfView.setCurrentMatch(null);
				clearBoard();
				finalVersionOfView.setMatchmakingPopupVisible(true);
				finalVersionOfView.makeAutoMatch();
			}
		});

		view.getAutoCancelButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				finalVersionOfView.setMatchmakingPopupVisible(false);
				finalVersionOfView.cancelAutoMatch();
			}
		});

		view.getDeleteButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (finalVersionOfView.getCurrentMatch() != null) {
					finalVersionOfView.deleteCurrentMatch();
					clearBoard();
				}
			}
		});

		view.getCloseButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				finalVersionOfView.closeOptions();
			}
		});

		view.addMatchChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Long matchId = finalVersionOfView.getSelectedMatch();
				if (matchId != null) {
					finalVersionOfView.setCurrentMatch(matchId);
					playerColor = finalVersionOfView.getPlayerColor(matchId);
					setState(finalVersionOfView.getGameState(matchId));
				} else {
					clearBoard();
				}
			}
		});

		view.addTimer(10000, new Runnable() {
			@Override
			public void run() {
				if (shouldAttemptToReconnect) {
					finalVersionOfView.attemptToReconnect(needNewConnection);
				}
				else
					makeMovesFromLocalStorage();
			}
		});
	}

	private void makeMovesFromLocalStorage() {
		if (!view.isLocalStorageSupported())
			return;
		String matchesWithMoves = view.readValueFromLocalStorage("moves");
		if (matchesWithMoves == null)
			return;
		String[] matchIDs = matchesWithMoves.split("[ ]");
		final String serializedMove = view.readValueFromLocalStorage(matchIDs[0]);
		final Long matchID = Long.valueOf(matchIDs[0]);
		view.makeMove(matchID, serializedMove, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				if (view.getCurrentMatch().equals(matchID)) {
					State oldState = state;
					state = serializer.unserializeState(result);
					animateMove(oldState, serializer.unserializeMove(serializedMove));
				} else {
					view.updateMatch(matchID.toString(), serializer.unserializeState(result));
					if (view.isOpponentAI(matchID) && !state.getTurn().equals(playerColor)) {
						final State oldState = state;
						view.makeAIMove(new AsyncCallback<String>() {
							@Override
							public void onFailure(Throwable caught) {
							}

							public void onSuccess(String result) {
								String[] tokens = result.split("[ ]");
								state = serializer.unserializeState(tokens[0]);
								animateMove(oldState, serializer.unserializeMove(tokens[1]));
							}
						});
					}

				}
				deleteMove(matchID.toString());
			}
		});
	}

	private void initializeDragAndDrop() {
		view.initializeDragging(new DragHandlerAdapter() {
			@Override
			public void onDragStart(DragStartEvent event) {
				Position startPos = view.getPosition((Image) event.getContext().draggable);
				event.getContext().desiredDraggableX = startPos.getCol() * 50;
				event.getContext().desiredDraggableY = 350 - (startPos.getRow() * 50);
				if (playerColor != state.getTurn())
					return;
				if (moveTo == null) {
					moveFrom = startPos;
					if (possibleMoves != null) {
						for (Move move : possibleMoves) {
							Position to = move.getTo();
							view.setHighlighted(to.getRow(), to.getCol(), false);
						}
					}
					possibleMoves = stateExplorer.getPossibleMovesFromPosition(state, moveFrom);
					for (Move move : possibleMoves) {
						Position to = move.getTo();
						view.setHighlighted(to.getRow(), to.getCol(), true);
					}
				}
			}
		});

		view.initializeDropping(new Dropper() {
			@Override
			public void onDrop(Position pos) {
				if (playerColor != state.getTurn())
					return;
				if (moveTo == null) {
					moveTo = pos;
					if (moveTo.getRow() == (state.getTurn().isWhite() ? 7 : 0)
							&& state.getPiece(moveFrom).getKind() == PieceKind.PAWN) {
						view.setPromotionPiecesVisible(true);
						return;
					}
					Move move = new Move(moveFrom, moveTo, null);
					if (possibleMoves.contains(move)) {
						final String serializedMove = serializer.serializeMove(move);
						saveMove(view.getCurrentMatch().toString(), serializedMove);
						view.makeMove(serializedMove, new AsyncCallback<String>() {
							@Override
							public void onFailure(Throwable caught) {
								if (possibleMoves != null) {
									for (Move m : possibleMoves) {
										Position to = m.getTo();
										view.setHighlighted(to.getRow(), to.getCol(), false);
									}
								}
								moveFrom = null;
								moveTo = null;
								possibleMoves = null;
								view.displayMoveErrorMessage();
							}

							@Override
							public void onSuccess(String result) {
								if (state.getPiece(moveTo) != null)
									view.playPieceCapturedSound();
								else if (state.getEnpassantPosition() != null
										&& state.getPiece(moveFrom).getKind() == PieceKind.PAWN
										&& moveTo.getCol() == state.getEnpassantPosition().getCol()
										&& moveTo.getRow() == (state.getTurn().isWhite() ? 5 : 2))
									view.playPieceCapturedSound();
								else
									view.playPieceDownSound();
								if (possibleMoves != null) {
									for (Move m : possibleMoves) {
										Position to = m.getTo();
										view.setHighlighted(to.getRow(), to.getCol(), false);
									}
								}
								moveFrom = null;
								moveTo = null;
								possibleMoves = null;
								setState(serializer.unserializeState(result));
								deleteMove(view.getCurrentMatch().toString());
							}
						});
					} else {
						if (possibleMoves != null) {
							for (Move m : possibleMoves) {
								Position to = m.getTo();
								view.setHighlighted(to.getRow(), to.getCol(), false);
							}
						}
						moveFrom = null;
						moveTo = null;
						possibleMoves = null;
					}
				}
			}
		});
	}

	// Load a match from the provided information, return the match ID
	public String loadMatch(String matchInfo) {
		String[] tokens = matchInfo.split("[ ]", 7);
		Long id = Long.valueOf(tokens[0]);
		Color color = (tokens[1].equals("W") ? Color.WHITE : Color.BLACK);

		view.setPlayerColor(id, color);
		view.setOpponentName(id, tokens[2]);
		view.setOpponentEmail(id, tokens[3]);
		view.setOpponentRank(id, Integer.parseInt(tokens[4]));
		view.setStartDate(id, new Date(Long.parseLong(tokens[5])));
		State newState = serializer.unserializeState(tokens[6]);
		view.setGameState(id, newState);
		view.updateMatch(tokens[0], newState);

		return tokens[0];
	}

	// Save the current move in local storage in case it needs to be executed
	// later
	public void saveMove(String matchID, String serializedMove) {
		if (!view.isLocalStorageSupported())
			return;
		String moves = view.readValueFromLocalStorage("moves");
		if (moves == null)
			moves = matchID;
		else
			moves += " " + matchID;
		view.writeValueToLocalStorage("moves", moves);
		view.writeValueToLocalStorage(matchID, serializedMove);
	}

	// Delete a successfully-executed move from local storage
	public void deleteMove(String matchID) {
		if (!view.isLocalStorageSupported())
			return;
		String moves = view.readValueFromLocalStorage("moves");
		if (moves == null)
			return;
		if (moves.equals(matchID)) {
			view.writeValueToLocalStorage("moves", null);
			view.writeValueToLocalStorage(matchID, null);
			return;
		}
		int indexOfMove = moves.indexOf(matchID);
		if (indexOfMove == -1)
			return;
		if (indexOfMove == 0)
			moves = moves.substring(matchID.length() + 1);
		else
			moves = moves.replace(" " + matchID, "");
		view.writeValueToLocalStorage("moves", moves);
		view.writeValueToLocalStorage(matchID, null);
	}

	// Animate a move
	public void animateMove(State oldState, Move move) {
		int fromRow = move.getFrom().getRow();
		int fromCol = move.getFrom().getCol();
		int toRow = move.getTo().getRow();
		int toCol = move.getTo().getCol();
		boolean capture = (oldState.getPiece(move.getTo()) != null && !oldState
				.getPiece(move.getTo()).getColor().equals(oldState.getTurn()));
		capture |= (oldState.getEnpassantPosition() != null
				&& oldState.getPiece(fromRow, fromCol).getKind() == PieceKind.PAWN
				&& toCol == oldState.getEnpassantPosition().getCol() && toRow == (oldState
				.getTurn().isWhite() ? 5 : 2));
		view.animateMove(fromRow, fromCol, toRow, toCol, oldState.getPiece(move.getFrom()),
				move.getPromoteToPiece(), capture);
		view.setGameState(view.getCurrentMatch(), state);
		view.updateMatch(view.getCurrentMatch().toString(), state);
		view.setWhoseTurn(state.getTurn());
		view.setGameResult(state.getGameResult());
		view.setPromotionPiecesVisible(false);
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col) {
				if ((row != fromRow || col != fromCol) && (row != toRow || col != toCol))
					view.setPiece(row, col, state.getPiece(row, col));
			}
		}
		if (view.isOpponentAI(view.getCurrentMatch()) && !state.getTurn().equals(playerColor)) {
			final State stateBeforeAIMove = state;
			view.makeAIMove(new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				public void onSuccess(String result) {
					String[] tokens = result.split("[ ]");
					state = serializer.unserializeState(tokens[0]);
					animateMove(stateBeforeAIMove, serializer.unserializeMove(tokens[1]));
				}
			});
		}
		// view.addHistoryToken(serializeState(state));
	}

	// Set the state of the board, redraw it
	public void setState(State newState) {
		state = newState;
		view.cancelAnimation();
		view.setGameState(view.getCurrentMatch(), state);
		view.updateMatch(view.getCurrentMatch().toString(), state);
		view.setWhoseTurn(state.getTurn());
		view.setGameResult(state.getGameResult());
		view.setPromotionPiecesVisible(false);
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col)
				view.setPiece(row, col, state.getPiece(row, col));
		}
		if (view.isOpponentAI(view.getCurrentMatch()) && !state.getTurn().equals(playerColor)) {
			final State oldState = state;
			view.makeAIMove(new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				public void onSuccess(String result) {
					String[] tokens = result.split("[ ]");
					state = serializer.unserializeState(tokens[0]);
					animateMove(oldState, serializer.unserializeMove(tokens[1]));
				}
			});
		}
		// String stateString = serializeState(state);
		// view.addHistoryToken(stateString);
	}

	// Clears the board currently being displayed
	public void clearBoard() {
		playerColor = null;
		view.setCurrentMatch(null);
		state = new State(null, new Piece[State.ROWS][State.COLS], new boolean[2], new boolean[2],
				null, 0, null);
		view.cancelAnimation();
		view.setWhoseTurn(null);
		view.setGameResult(null);
		view.setPromotionPiecesVisible(false);
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col) {
				view.setPiece(row, col, null);
			}
		}
		if (possibleMoves != null) {
			for (Move move : possibleMoves) {
				Position pos = move.getTo();
				view.setHighlighted(pos.getRow(), pos.getCol(), false);
			}
			possibleMoves = null;
		}
		moveFrom = null;
		moveTo = null;
	}

	// Logic for when a piece of the board is clicked on
	public void selectPiece(int row, int col) {
		if (playerColor != state.getTurn())
			return;
		Position pos = new Position(row, col);

		// Clicking on the board itself
		if (row < State.ROWS && moveTo == null) {

			// If a position was already selected, see if a move has been chosen
			if (moveFrom != null) {
				for (Move move : possibleMoves) {
					if (move.getTo().equals(pos)) {
						moveTo = move.getTo();
					}
					Position to = move.getTo();
					view.setHighlighted(to.getRow(), to.getCol(), false);
				}
				possibleMoves = null;
			}

			// If no move was chosen, highlight the possible moves
			if (moveTo == null) {
				moveFrom = pos;
				possibleMoves = stateExplorer.getPossibleMovesFromPosition(state, pos);
				for (Move move : possibleMoves) {
					Position to = move.getTo();
					view.setHighlighted(to.getRow(), to.getCol(), true);
				}
				if (possibleMoves.isEmpty()) {
					moveFrom = null;
					possibleMoves = null;
				}
			} else { // Make a move (or enable the panel of promotion options)
				final Move move;
				if (moveTo.getRow() == (state.getTurn().isWhite() ? 7 : 0)
						&& state.getPiece(moveFrom).getKind() == PieceKind.PAWN) {
					view.setPromotionPiecesVisible(true);
					return;
				}
				move = new Move(moveFrom, moveTo, null);
				moveFrom = null;
				moveTo = null;
				final State oldState = state.copy();
				final String serializedMove = serializer.serializeMove(move);
				saveMove(view.getCurrentMatch().toString(), serializedMove);
				view.makeMove(serializedMove, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(String result) {
						state = serializer.unserializeState(result);
						animateMove(oldState, move);
						deleteMove(view.getCurrentMatch().toString());
					}
				});
			}
		}

		// Clicking on the panel of promotion options (while it's visible)
		if (row == State.ROWS && moveTo != null) {
			final Move move;
			switch (col) {
			case 0:
			case 1:
			case 6:
			case 7:
			default:
				return;
			case 2:
				move = new Move(moveFrom, moveTo, PieceKind.BISHOP);
				break;
			case 3:
				move = new Move(moveFrom, moveTo, PieceKind.KNIGHT);
				break;
			case 4:
				move = new Move(moveFrom, moveTo, PieceKind.QUEEN);
				break;
			case 5:
				move = new Move(moveFrom, moveTo, PieceKind.ROOK);
				break;
			}
			moveFrom = null;
			moveTo = null;
			final State oldState = state.copy();
			final String serializedMove = serializer.serializeMove(move);
			saveMove(view.getCurrentMatch().toString(), serializedMove);
			view.makeMove(serializedMove, new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(String result) {
					state = serializer.unserializeState(result);
					animateMove(oldState, move);
					deleteMove(view.getCurrentMatch().toString());
				}
			});
		}
	}

	// For the unit tester
	public void setPlayerColor(Color color) {
		playerColor = color;
	}
}
