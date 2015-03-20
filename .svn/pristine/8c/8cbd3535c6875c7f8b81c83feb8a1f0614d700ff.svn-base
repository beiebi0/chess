package org.simongellis.hw7.client;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

// I moved the serialization code to this class so I could access it serverside
public class Serializer {
	final String pieces = "KQRBNP";

	// Create a string representing all the information in a state.
	// I chose to write my own method instead of using toString for shorter URLs and easy parsing.
	public String serializeState(State state) {
		String serialized = state.getTurn().toString() + "_";
		for (int row = 0; row < State.ROWS; ++row) {
			for (int col = 0; col < State.COLS; ++col) {
				Piece piece = state.getPiece(row, col);
				if (piece == null)
					serialized += '-';
				else if (piece.getColor().isWhite())
					serialized += pieces.charAt(piece.getKind().ordinal());
				else
					serialized += Character.toLowerCase(pieces.charAt(piece.getKind().ordinal()));
			}
		}
		serialized += '_';

		serialized += (state.isCanCastleKingSide(Color.WHITE) ? 'T' : 'F');
		serialized += (state.isCanCastleKingSide(Color.BLACK) ? 't' : 'f');
		serialized += '_';
		serialized += (state.isCanCastleQueenSide(Color.WHITE) ? 'T' : 'F');
		serialized += (state.isCanCastleQueenSide(Color.BLACK) ? 't' : 'f');

		if (state.getEnpassantPosition() != null) {
			serialized += '_';
			serialized += state.getEnpassantPosition().getRow() + "-";
			serialized += state.getEnpassantPosition().getCol() + "_";
		} else
			serialized += "_X_";

		serialized += state.getNumberOfMovesWithoutCaptureNorPawnMoved() + "_";

		if (state.getGameResult() != null) {
			switch (state.getGameResult().getGameResultReason()) {
			case CHECKMATE:
				serialized += state.getGameResult().getWinner().toString();
				break;
			case FIFTY_MOVE_RULE:
				serialized += 'F';
				break;
			case STALEMATE:
				serialized += 'S';
				break;
			case THREEFOLD_REPETITION_RULE:
				serialized += 'T';
				break;
			default:
				break;
			}
		} else
			serialized += 'X';
		return serialized;
	}

	// Decode a State encoded by serializeString.
	public State unserializeState(String serialized) {
		try {
			Color turn;
			Piece[][] board;
			boolean[] castleKingSide;
			boolean[] castleQueenSide;
			Position enpassantPosition;
			int numberOfMoves;
			GameResult gameResult;

			String[] tokens = serialized.split("[_]", 7);

			turn = (tokens[0].charAt(0) == 'B' ? Color.BLACK : Color.WHITE);

			board = new Piece[State.ROWS][State.COLS];
			for (int row = 0, index = 0; row < State.ROWS; ++row) {
				for (int col = 0; col < State.COLS; ++col, ++index) {
					char currentChar = tokens[1].charAt(index);
					if (currentChar != '-') {
						Color pieceColor = (Character.isUpperCase(currentChar) ? Color.WHITE
								: Color.BLACK);
						PieceKind pieceKind = PieceKind.values()[pieces.indexOf(Character
								.toUpperCase(currentChar))];
						board[row][col] = new Piece(pieceColor, pieceKind);
					}
				}
			}

			castleKingSide = new boolean[2];
			castleKingSide[0] = (tokens[2].charAt(0) == 'T');
			castleKingSide[1] = (tokens[2].charAt(1) == 't');
			castleQueenSide = new boolean[2];
			castleQueenSide[0] = (tokens[3].charAt(0) == 'T');
			castleQueenSide[1] = (tokens[3].charAt(1) == 't');

			if (tokens[4].equals("X"))
				enpassantPosition = null;
			else {
				String[] rowAndCol = tokens[4].split("[-]", 2);
				enpassantPosition = new Position(Integer.parseInt(rowAndCol[0]),
						Integer.parseInt(rowAndCol[1]));
			}

			numberOfMoves = Integer.parseInt(tokens[5]);

			switch (tokens[6].charAt(0)) {
			case 'W':
				gameResult = new GameResult(Color.WHITE, GameResultReason.CHECKMATE);
				break;
			case 'B':
				gameResult = new GameResult(Color.BLACK, GameResultReason.CHECKMATE);
				break;
			case 'F':
				gameResult = new GameResult(null, GameResultReason.FIFTY_MOVE_RULE);
				break;
			case 'S':
				gameResult = new GameResult(null, GameResultReason.STALEMATE);
				break;
			case 'T':
				gameResult = new GameResult(null, GameResultReason.THREEFOLD_REPETITION_RULE);
			case 'X':
			default:
				gameResult = null;
				break;
			}
			return new State(turn, board, castleKingSide, castleQueenSide, enpassantPosition,
					numberOfMoves, gameResult);
		} catch (Exception e) {
			return new State();
		}
	}

	public String serializeMove(Move move) {
		String serialized = "";
		serialized += move.getFrom().getRow();
		serialized += move.getFrom().getCol();
		serialized += move.getTo().getRow();
		serialized += move.getTo().getCol();
		if (move.getPromoteToPiece() == null)
			serialized += "X";
		else
			serialized += pieces.charAt(move.getPromoteToPiece().ordinal());
		return serialized;
	}

	public Move unserializeMove(String serialized) {
		int fromRow, fromCol, toRow, toCol;
		PieceKind promoteToPiece;

		fromRow = Integer.parseInt(serialized.substring(0, 1));
		fromCol = Integer.parseInt(serialized.substring(1, 2));
		toRow = Integer.parseInt(serialized.substring(2, 3));
		toCol = Integer.parseInt(serialized.substring(3, 4));

		char currentChar = serialized.charAt(4);
		if (currentChar == 'X') {
			promoteToPiece = null;
		} else {
			promoteToPiece = PieceKind.values()[pieces.indexOf(currentChar)];
		}

		return new Move(new Position(fromRow, fromCol), new Position(toRow, toCol), promoteToPiece);
	}

}
