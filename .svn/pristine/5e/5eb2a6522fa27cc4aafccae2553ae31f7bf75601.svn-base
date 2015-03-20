package org.wenjiechen.hw3;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;
import static org.shared.chess.Color.WHITE;
import static org.shared.chess.Color.BLACK;

public class HistoryCoder {
	
	/**
	 * get a game state, code it into a string
	 * @param state game's state
	 * @return string of state.
	 */
	public static String codingStateToHistory(State state){

		String codingState = "castling_";
		codingState += state.isCanCastleKingSide(WHITE) ? "T" : "F";
		codingState += state.isCanCastleQueenSide(WHITE) ? "T" : "F";
		codingState += state.isCanCastleKingSide(BLACK) ? "T" : "F";
		codingState += state.isCanCastleQueenSide(BLACK) ? "T" : "F";

		if (state.getEnpassantPosition() != null) {
			codingState += "&enpassant_" + state.getEnpassantPosition().getRow()
					+ state.getEnpassantPosition().getCol();
		}
		codingState += "&turn_" + state.getTurn();

		if (state.getGameResult() != null) {
			codingState += "&winner_" + state.getGameResult().getWinner();
			codingState += "&reason_" + state.getGameResult().getGameResultReason();
		}

		codingState += "&numberOfMovesWithoutCaptureNorPawnMoved_"
				+ state.getNumberOfMovesWithoutCaptureNorPawnMoved();

		String codingBoard = codingBoard(state);
		codingState += "&board_" + codingBoard;
		
		return codingState;
	}
	
	/**
	 * code game state's board into string
	 * @param state game state
	 * @return
	 */
	private static String codingBoard(State state){
		String codingBoard = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = state.getPiece(i, j);

				if (piece != null) {
					codingBoard += i + "" + j + ":" + piece.getColor()
							+ codePieceKind(piece.getKind()) + ",";
				}
			}
		}
		codingBoard = codingBoard.substring(0, codingBoard.length() - 1);
		return codingBoard;
	}
			
	public static State decodingHistoryString(String historyStr){
		Piece[][] board = null;
		boolean castleWKS = false;
		boolean castleWQS = false;
		boolean castleBKS = false;
		boolean castleBQS = false;
		Position enpassantPos = null;
		Color turnColor = WHITE;
		int numberOfMovesWithoutCaptureNorPawnMoved = 0;
		Color winner = null;
		GameResultReason reason = null;
		GameResult result = null;

		for (String subValue : historyStr.split("&")) {
			String[] ValueArr = subValue.split("_");
			String header = ValueArr[0];
			String value = ValueArr[1];

			if (header.equals("board")) {
				board = decodeBoard(value);
			} else if (header.equals("castling")) {
				castleWKS = value.charAt(0) == 'T';
				castleWQS = value.charAt(1) == 'T';
				castleBKS = value.charAt(2) == 'T';
				castleBQS = value.charAt(3) == 'T';
			} else if (header.equals("enpassant")) {
				int row = Integer.parseInt(String.valueOf(value.charAt(0)));
				int col = Integer.parseInt(String.valueOf(value.charAt(1)));

				enpassantPos = new Position(row, col);
			} else if (header.equals("turn")) {
				turnColor = value.equals("W") ? WHITE : BLACK;
			} else if (header.equals("numberOfMovesWithoutCaptureNorPawnMoved")) {
				numberOfMovesWithoutCaptureNorPawnMoved = Integer.parseInt(value);
			} else if (header.equals("winner")) {
				winner = value.equals("W") ? WHITE : BLACK;
			} else if (header.equals("reason")) {
				if (value.equals("CHECKMATE")) {
					reason = GameResultReason.CHECKMATE;
				} else if (value.equals("FIFTY_MOVE_RULE")) {
					reason = GameResultReason.FIFTY_MOVE_RULE;
				} else if (value.equals("THREEFOLD_REPETITION_RULE")) {
					reason = GameResultReason.THREEFOLD_REPETITION_RULE;
				} else if (value.equals("STALEMATE")) {
					reason = GameResultReason.STALEMATE;
				}
			}
		}
		
		if(reason != null) {
			result = new GameResult(winner, reason);
		}

		return new State(turnColor, board, new boolean[] { castleWKS, castleBKS },
				new boolean[] { castleWQS, castleBQS }, enpassantPos,
				numberOfMovesWithoutCaptureNorPawnMoved, result);
	}

	private static Piece[][] decodeBoard(String value) {
		Piece[][] board = new Piece[8][8];

		for (String piece : value.split(",")) {
			String[] info = piece.split(":");

			String position = info[0];
			String colorAndKind = info[1];

			int row = Integer.parseInt(String.valueOf(position.charAt(0)));
			int col = Integer.parseInt(String.valueOf(position.charAt(1)));

			Color color = colorAndKind.charAt(0) == 'W' ? WHITE : BLACK;

			char kindCode = colorAndKind.charAt(1);

			PieceKind kind = null;
			if (kindCode == 'K') {
				kind = PieceKind.KING;
			} else if (kindCode == 'N') {
				kind = PieceKind.KNIGHT;
			} else if (kindCode == 'B') {
				kind = PieceKind.BISHOP;
			} else if (kindCode == 'R') {
				kind = PieceKind.ROOK;
			} else if (kindCode == 'Q') {
				kind = PieceKind.QUEEN;
			} else {
				kind = PieceKind.PAWN;
			}

			board[row][col] = new Piece(color, kind);
		}

		return board;
	}

	private static String codePieceKind(PieceKind kind) {
		switch (kind) {
		case BISHOP:
			return "B";
		case KING:
			return "K";
		case KNIGHT:
			return "N";
		case PAWN:
			return "P";
		case QUEEN:
			return "Q";
		case ROOK:
			return "R";
		}

		return "";
	}
}
