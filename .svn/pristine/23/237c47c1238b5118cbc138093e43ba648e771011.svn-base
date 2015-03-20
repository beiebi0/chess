package org.bohouli.hw3;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

public class BohouStateSerializer {
	
	public static String serialize(State state) {
		String stateString = "";
		
		String isCastle = "";
		isCastle += state.isCanCastleKingSide(Color.WHITE) ? "Y" : "N";
		isCastle += state.isCanCastleQueenSide(Color.WHITE) ? "Y" : "N";
		isCastle += state.isCanCastleKingSide(Color.BLACK) ? "Y" : "N";
		isCastle += state.isCanCastleQueenSide(Color.BLACK) ? "Y" : "N";
		stateString += "castling=" + isCastle;

		if (state.getEnpassantPosition() != null) {
			stateString += "&enpassant=" + state.getEnpassantPosition().getRow()
					+ state.getEnpassantPosition().getCol();
		}
		stateString += "&turn=" + state.getTurn();

		if (state.getGameResult() != null) {
			stateString += "&winner=" + state.getGameResult().getWinner();
			stateString += "&reason=" + state.getGameResult().getGameResultReason();
		}

		stateString += "&numberOfMovesWithoutCaptureNorPawnMoved="
				+ state.getNumberOfMovesWithoutCaptureNorPawnMoved();

		String boardState = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = state.getPiece(i, j);

				if (piece != null) {
					String charType;
					switch (piece.getKind()) {
					case PAWN:
						charType = "P"; break;
					case ROOK:
						charType = "R"; break;
					case KNIGHT:
						charType = "N"; break;
					case BISHOP:
						charType = "B"; break;
					case QUEEN:
						charType = "Q"; break;
					case KING:
						charType = "K"; break;
					default:
						charType = "";
					}
					boardState += i + "" + j + ":" + piece.getColor() + charType + ",";
				}
			}
		}
//		boardState = boardState.substring(0, boardState.length() - 1);
		stateString += "&board=" + boardState;

		return stateString;
	}
	
	public static State deserialize(String stateString) {
		Color color = null;
		Piece[][] board = new Piece[8][8];
		boolean whiteKingSide = false;
		boolean whiteQueenSide = false;
		boolean blackKingSide = false;
		boolean blackQueenSide = false;
		Position enpassantPosition = null;
		int numberOfMovesWithoutCaptureNorPawnMoved = 0;
		Color winner = null;
		GameResult gameResult = null;
		GameResultReason gameReason = null;

		for (String keyValue : stateString.split("&")) {
			String[] keyValueArr = keyValue.split("=");
			String key = keyValueArr[0];
			String value = keyValueArr[1];

			if (key.equals("board")) {
				for (String piece : value.split(",")) {
					String[] data = piece.split(":");

					String posString = data[0];
					String colorKind = data[1];

					int row = Integer.parseInt(String.valueOf(posString.charAt(0)));
					int col = Integer.parseInt(String.valueOf(posString.charAt(1)));
					Color pieceColor = colorKind.charAt(0) == 'W' ? Color.WHITE : Color.BLACK;

					char type = colorKind.charAt(1);

					PieceKind kind = null;
					switch(type) {
					case 'P':
						kind = PieceKind.PAWN;
						break;
					case 'R':
						kind = PieceKind.ROOK;
						break;
					case 'N':
						kind = PieceKind.KNIGHT;
						break;
					case 'B':
						kind = PieceKind.BISHOP;
						break;
					case 'Q':
						kind = PieceKind.QUEEN;
						break;
					case 'K':
						kind = PieceKind.KING;
						break;
					}

					board[row][col] = new Piece(pieceColor, kind);
				}
			} else if (key.equals("castling")) {
				whiteKingSide = value.charAt(0) == 'Y';
				whiteQueenSide = value.charAt(1) == 'Y';
				blackKingSide = value.charAt(2) == 'Y';
				blackQueenSide = value.charAt(3) == 'Y';
			} else if (key.equals("enpassant")) {
				int row = Integer.parseInt(String.valueOf(value.charAt(0)));
				int col = Integer.parseInt(String.valueOf(value.charAt(1)));

				enpassantPosition = new Position(row, col);
			} else if (key.equals("turn")) {
				color = value.equals("W") ? Color.WHITE : Color.BLACK;
			} else if (key.equals("numberOfMovesWithoutCaptureNorPawnMoved")) {
				numberOfMovesWithoutCaptureNorPawnMoved = Integer
						.parseInt(value);
			} else if (key.equals("winner")) {
				winner = value.equals("W") ? Color.WHITE : Color.BLACK;
			} else if (key.equals("reason")) {
				if (value.equals("CHECKMATE")) {
					gameReason = GameResultReason.CHECKMATE;
				} else if (value.equals("FIFTY_MOVE_RULE")) {
					gameReason = GameResultReason.FIFTY_MOVE_RULE;
				} else if (value.equals("THREEFOLD_REPETITION_RULE")) {
					gameReason = GameResultReason.THREEFOLD_REPETITION_RULE;
				} else if (value.equals("STALEMATE")) {
					gameReason = GameResultReason.STALEMATE;
				}
			}
		}

		if (gameReason != null) {
			gameResult = new GameResult(winner, gameReason);
		}

		State state = new State(color, board, new boolean[] { whiteKingSide, blackKingSide },
				new boolean[] { whiteQueenSide, blackQueenSide }, enpassantPosition,
				numberOfMovesWithoutCaptureNorPawnMoved, gameResult);
		return state;
	}
}
