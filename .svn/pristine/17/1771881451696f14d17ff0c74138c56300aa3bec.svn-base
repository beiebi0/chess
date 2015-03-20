package org.yuanjia.hw3;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Move;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.Piece;
import org.shared.chess.State;
import org.yuanjia.hw2.StateChangerImpl;
import org.yuanjia.hw2_5.StateExplorerImpl;
import org.yuanjia.hw6.client.ChessServices;
import org.yuanjia.hw6.client.ChessServicesAsync;
import org.yuanjia.hw6.client.LoginInfo;
import org.yuanjia.hw9.AI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

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
		void setDotHighlighted(int row, int col, boolean highlighted);

		/**
		 * Indicate whose turn it is.
		 */
		void setWhoseTurn(Color color);

		/**
		 * Indicate whether the game is in progress or over.
		 */
		void setGameResult(GameResult gameResult);

		void setButtonsVisible(boolean B, Color C);

		void setNewHistory(State state);

		void makeAnimation(int fromRow, int fromCol, int toRow, int toCol,
				PieceKind promoteTo);

		void setStorageButtonsVisible(boolean B);

	}

	private static final int moveDelay = 1500;

	private View view;
	private static State state = new State();
	private Position selected = null;
	public static StateExplorerImpl stateexplorer = new StateExplorerImpl();
	public static StateChangerImpl statechanger = new StateChangerImpl();
	private PieceKind promoteTo;
	private Position promoteToPos;
	private Position promoteFromPos;
	public static String matchId;
	public static List<String> matchIdList = new ArrayList<String>();
	public static Color myTurn;
	private ChessServicesAsync stateDisplaySvc = GWT
			.create(ChessServices.class);

	public Presenter(View view, State state) {
		setView(view);
		setState(state);
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setState(State newstate) {
		view.setWhoseTurn(newstate.getTurn());

		view.setGameResult(newstate.getGameResult());

		// view.setGameResult(new
		// GameResult(Color.BLACK,GameResultReason.CHECKMATE));

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				view.setPiece(r, c, newstate.getPiece(r, c));
			}
		}
		state = newstate;
	}

	public void selectCell(int row, int col, String type) {
		if (isFinished())
			return;
		Position clickedOn = new Position(row, col);
		Move playerMove = null;

		if (selected == null) {
			if (isEmpty(row, col)) {
				return;
			}
			if (!state.getPiece(clickedOn).getColor().equals(state.getTurn())) {
				return;
			}
			// If the player selects a piece
			selectPiece(clickedOn);
		} else if (selected != null
				&& !isEmpty(row, col)
				&& state.getPiece(new Position(row, col)).getColor()
						.equals(state.getTurn())) {
			// If the player change pieces
			unselectPiece(selected);
			selectPiece(new Position(row, col));
		} else {
			// If the player makes a move
			MoveBasedOnPromote(playerMove, row, col, type);
			unselectPiece(selected);
		}
	}

	private boolean isEmpty(int row, int col) {

		if (state.getPiece(row, col) == null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isFinished() {

		if (state.getGameResult() != null) {
			return true;
		} else {
			return false;
		}
	}

	private void selectPiece(Position pos) {
		selected = pos;

		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				view.setDotHighlighted(i, j, false);

			}
		}
		Set<Move> startMoves = stateexplorer.getPossibleMovesFromPosition(
				state, selected);
		for (Move move : startMoves) {
			// Enable highlight
			view.setDotHighlighted(move.getTo().getRow(),
					move.getTo().getCol(), true);
		}
	}

	public State getState() {
		return state;
	}

	private void unselectPiece(Position pos) {
		selected = null;
		// Disable highlight
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				view.setDotHighlighted(i, j, false);
			}
		}
	}

	public void setPromote(PieceKind kind) {
		Set<Move> startMoves = stateexplorer.getPossibleMovesFromPosition(
				state, promoteFromPos);
		switch (kind) {
		case QUEEN:
			promoteTo = PieceKind.QUEEN;
			break;
		case KNIGHT:
			promoteTo = PieceKind.KNIGHT;
			break;
		case BISHOP:
			promoteTo = PieceKind.BISHOP;
			break;
		case ROOK:
			promoteTo = PieceKind.ROOK;
			break;
		default:
			break;
		}
		Move move2 = new Move(promoteFromPos, promoteToPos, promoteTo);
		if (startMoves.contains(move2) && state.getTurn().equals(myTurn)) {
			statechanger.makeMove(state, move2);
			// view.setNewHistory(state);
			refreshBoard(state);
		}
		promoteFromPos = null;
		promoteToPos = null;
		promoteTo = null;

		view.setButtonsVisible(false, null);
	}

	private void MoveBasedOnPromote(Move move, int row, int col, String type) {
		Set<Move> startMoves = stateexplorer.getPossibleMovesFromPosition(
				state, selected);
		Set<Position> toPos = new HashSet<Position>();
		Piece blackpawn = new Piece(Color.BLACK, PieceKind.PAWN);
		Piece whitepawn = new Piece(Color.WHITE, PieceKind.PAWN);
		for (Move testMove : startMoves) {
			toPos.add(testMove.getTo());
		}

		if (selected.getRow() == 1 && toPos.contains(new Position(row, col))
				&& state.getPiece(selected).equals(blackpawn)) {
			promoteFromPos = selected;
			promoteToPos = new Position(row, col);
			view.setButtonsVisible(true, Color.BLACK);
			return;

		} else if (selected.getRow() == 6
				&& toPos.contains(new Position(row, col))
				&& state.getPiece(selected).equals(whitepawn) && row == 7) {
			promoteFromPos = selected;
			promoteToPos = new Position(row, col);
			view.setButtonsVisible(true, Color.WHITE);
			return;
		} else {

			move = new Move(selected, new Position(row, col), null);
			if (startMoves.contains(move) && state.getTurn().equals(myTurn)) {
				statechanger.makeMove(state, move);
				if (type.equals("Click")) {
					view.makeAnimation(selected.getRow(), selected.getCol(),
							row, col, null);
				}
				if(matchId.equals("AI_GAME")){
					setState(state);
					Timer timer = new Timer(){
						@Override
			            public void run()
			            {
							final Move moveAI = AI.get_move(state);
							statechanger.makeMove(state, moveAI);
							setState(state);
							view.makeAnimation(moveAI.getFrom().getRow(), moveAI.getFrom().getCol(), moveAI.getTo().getRow(), moveAI.getTo().getCol(), null);
			            }
					};
					timer.schedule(moveDelay);

				}else{
					refreshBoard(state);
				}
				view.setButtonsVisible(false, null);
			}
		}
	}

	private void refreshBoard(State newstate) {
		// Set up the callback object.
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				Window.alert("refreshBoard failed.");
			}

			public void onSuccess(Void result) {
				// Window.alert(result);
			}
		};
		// Make the call to the DisplayState.
		stateDisplaySvc.displayState(StateSerializer.serialize(newstate),
				ChessEntryPoint.loginInfo.getClientId(), matchId, callback);

	}

	public void setMyTurn(String turn) {
		if (turn.equals("white")) {
			myTurn = Color.WHITE;
		} else if (turn.equals("black")) {
			myTurn = Color.BLACK;
		}
	}

}