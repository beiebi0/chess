package org.haoxiangzuo.hw7;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

public class StateChanger {
	public static String stateToString(State state) {
		String result = "";
		result += state.getTurn() + ",";
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++) {
				result += state.getPiece(row, col) + ",";
			}
		result += state.isCanCastleKingSide(Color.WHITE) + ","
				+ state.isCanCastleKingSide(Color.BLACK) + ","
				+ state.isCanCastleQueenSide(Color.WHITE) + ","
				+ state.isCanCastleQueenSide(Color.BLACK) + ",";
		result += (state.getEnpassantPosition() == null ? "null" : state
				.getEnpassantPosition().getRow()
				+ " "
				+ state.getEnpassantPosition().getCol()) + ",";
		result += (state.getGameResult() == null ? "null," : state
				.getGameResult().getWinner()
				+ " "
				+ state.getGameResult().getGameResultReason() + ",");
		result += (state.getNumberOfMovesWithoutCaptureNorPawnMoved());
		return result;
	}

	public static State stringToState(String x) {
		State state = new State();
		int counter = 0;
		String[] sets = x.split(",");
		if (sets[counter].charAt(0) == 'W')
			state.setTurn(Color.WHITE);
		else
			state.setTurn(Color.BLACK);
		counter++;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				String sub = sets[counter];
				if (sub.equals("null"))
					state.setPiece(row, col, null);
				else {
					if (sub.charAt(1) == 'W') {
						String piece = sub.substring(3, 5);
						if (piece.equals("RO"))
							state.setPiece(row, col, new Piece(Color.WHITE,
									PieceKind.ROOK));
						else if (piece.equals("KI"))
							state.setPiece(row, col, new Piece(Color.WHITE,
									PieceKind.KING));
						else if (piece.equals("KN"))
							state.setPiece(row, col, new Piece(Color.WHITE,
									PieceKind.KNIGHT));
						else if (piece.equals("QU"))
							state.setPiece(row, col, new Piece(Color.WHITE,
									PieceKind.QUEEN));
						else if (piece.equals("BI"))
							state.setPiece(row, col, new Piece(Color.WHITE,
									PieceKind.BISHOP));
						else if (piece.equals("PA"))
							state.setPiece(row, col, new Piece(Color.WHITE,
									PieceKind.PAWN));
					} else if (sub.charAt(1) == 'B') {
						String piece = sub.substring(3, 5);
						if (piece.equals("RO"))
							state.setPiece(row, col, new Piece(Color.BLACK,
									PieceKind.ROOK));
						else if (piece.equals("KI"))
							state.setPiece(row, col, new Piece(Color.BLACK,
									PieceKind.KING));
						else if (piece.equals("KN"))
							state.setPiece(row, col, new Piece(Color.BLACK,
									PieceKind.KNIGHT));
						else if (piece.equals("QU"))
							state.setPiece(row, col, new Piece(Color.BLACK,
									PieceKind.QUEEN));
						else if (piece.equals("BI"))
							state.setPiece(row, col, new Piece(Color.BLACK,
									PieceKind.BISHOP));
						else if (piece.equals("PA"))
							state.setPiece(row, col, new Piece(Color.BLACK,
									PieceKind.PAWN));
					}
				}
				counter++;
				if (counter > 64)
					break;
			}
		}
		if (sets[counter].charAt(0) == 't')
			state.setCanCastleKingSide(Color.WHITE, true);
		else
			state.setCanCastleKingSide(Color.WHITE, false);
		counter++;
		if (sets[counter].charAt(0) == 't')
			state.setCanCastleKingSide(Color.BLACK, true);
		else
			state.setCanCastleKingSide(Color.BLACK, false);
		counter++;
		if (sets[counter].charAt(0) == 't')
			state.setCanCastleQueenSide(Color.WHITE, true);
		else
			state.setCanCastleQueenSide(Color.WHITE, false);
		counter++;
		if (sets[counter].charAt(0) == 't')
			state.setCanCastleQueenSide(Color.BLACK, true);
		else
			state.setCanCastleQueenSide(Color.BLACK, false);
		counter++;
		if (!sets[counter].equals("null"))
			state.setEnpassantPosition(new Position(Integer
					.valueOf(sets[counter].substring(0, 1)), Integer
					.valueOf(sets[counter].substring(2, 3))));
		counter++;
		Color color = null;
		if (sets[counter].charAt(0) == 'W')
			color = Color.WHITE;
		else if (sets[counter].charAt(0) == 'B')
			color = Color.BLACK;
		else
			color = null;
		if (sets[counter].length() > 5) {
			if (sets[counter].charAt(5) == 'F')
				state.setGameResult(new GameResult(color,
						GameResultReason.FIFTY_MOVE_RULE));
			else if (sets[counter].charAt(5) == 'S')
				state.setGameResult(new GameResult(color,
						GameResultReason.STALEMATE));
			else if (sets[counter].charAt(5) == 'C')
				state.setGameResult(new GameResult(color,
						GameResultReason.CHECKMATE));
		}
		counter++;
		state.setNumberOfMovesWithoutCaptureNorPawnMoved(Integer
				.valueOf(sets[counter]));
		return state;
	}
}
