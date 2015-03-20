package org.markanderson.hw3;

import org.markanderson.hw6.helper.ManderChessGameSessionInfo;
import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

public class HistorySupport {
	public static State deserializeHistoryToken(String token) {
		State state = new State();

		for (String kvp : token.split(":")) {
			String[] kv = kvp.split("=");

			if (kv[0].equalsIgnoreCase("turn")) {
				state.setTurn(kv[1].equalsIgnoreCase("W") ? Color.WHITE
						: Color.BLACK);
			} else if (kv[0].equalsIgnoreCase("castling")) {
				for (int i = 0; i <= 2; i += 2) {
					int qSide = (int) kv[1].charAt(i) % 48;
					int kSide = (int) kv[1].charAt(i + 1) % 48;

					state.setCanCastleQueenSide(i == 0 ? Color.WHITE
							: Color.BLACK, qSide == 0 ? false : true);
					state.setCanCastleKingSide(i == 0 ? Color.WHITE
							: Color.BLACK, kSide == 0 ? false : true);
				}

			} else if (kv[0].equalsIgnoreCase("enposition")) {
				int i = (int) kv[1].charAt(0) % 48;
				int j = (int) kv[1].charAt(1) % 48;

				state.setEnpassantPosition(new Position(i, j));
			} else if (kv[0].equalsIgnoreCase("moveswithout")) {
				state.setNumberOfMovesWithoutCaptureNorPawnMoved((int) kv[1]
						.charAt(0) % 48);
			} else if (kv[0].equalsIgnoreCase("gameresult")) {
				char type = kv[1].charAt(0);
				if (type == 'c') {
					char color = kv[1].charAt(1);
					state.setGameResult(new GameResult(
							color == 'W' ? Color.WHITE : Color.BLACK,
							GameResultReason.CHECKMATE));
				} else if (type == 's') {
					state.setGameResult(new GameResult(null,
							GameResultReason.STALEMATE));
				} else if (type == 'd') {
					state.setGameResult(new GameResult(null,
							GameResultReason.FIFTY_MOVE_RULE));
				} else if (type == 't') {
					state.setGameResult(new GameResult(null,
							GameResultReason.THREEFOLD_REPETITION_RULE));
				} else {
					state.setGameResult(null);
				}
			}

			else if (kv[0].equalsIgnoreCase("pospiece")) {
				int row = (int) kv[1].charAt(0) % 48;
				int col = (int) kv[1].charAt(1) % 48;

				// get "piece="
				Color pieceColor;
				PieceKind pk;
				Piece piece;
				if (kv[1].charAt(2) == 'Z') {
					piece = null;
				} else {
					// piece is not null, get the color and kind
					pieceColor = kv[1].charAt(2) == 'W' ? Color.WHITE
							: Color.BLACK;
					char pieceCh = kv[1].charAt(3);
					pk = getSerializedPieceKind(pieceCh);
					piece = new Piece(pieceColor, pk);
				}

				state.setPiece(new Position(row, col), piece);
			}
		}
		return state;
	}

	public static PieceKind getSerializedPieceKind(char kind) {
		PieceKind pk;

		if (kind == 'P') {
			pk = PieceKind.PAWN;
		} else if (kind == 'R') {
			pk = PieceKind.ROOK;
		} else if (kind == 'B') {
			pk = PieceKind.BISHOP;
		} else if (kind == 'Q') {
			pk = PieceKind.QUEEN;
		} else if (kind == 'C') {
			pk = PieceKind.KING;
		} else if (kind == 'K') {
			pk = PieceKind.KNIGHT;
		} else {
			pk = null;
		}
		return pk;
	}

	
	public static ManderChessGameSessionInfo deserializeChessGameInfo(String string) {
		ManderChessGameSessionInfo gInfo = new ManderChessGameSessionInfo();
		
		for (String kvp : string.split(":")) {
			String[] kv = kvp.split("=");

			if (kv[0].equalsIgnoreCase("bClientID")) {
				gInfo.setbClientID(kv[1]);
			} else if (kv[0].equalsIgnoreCase("wClientID")) {
				gInfo.setwClientID(kv[1]);
			} else if (kv[0].equalsIgnoreCase("bEmail")) {
				gInfo.setbPlayerEmail(kv[1]);
			} else if (kv[0].equalsIgnoreCase("wEmail")) {
				gInfo.setwPlayerEmail(kv[1]);
			}  else if (kv[0].equalsIgnoreCase("gameID")) {
				gInfo.setCurrentGameID(kv[1]);
			} else if (kv[0].equalsIgnoreCase("turn")) {
				gInfo.setCurrentTurn(kv[1]);
			}
		}
		return gInfo;
	}
	
	public static String serializeHistory(State state) {
		StringBuilder sb = new StringBuilder();

		// turn
		sb.append("turn=" + state.getTurn() + ":");

		// castling
		sb.append("castling=");
		sb.append(state.isCanCastleQueenSide(Color.WHITE) ? '1' : '0');
		sb.append(state.isCanCastleKingSide(Color.WHITE) ? '1' : '0');
		sb.append(state.isCanCastleQueenSide(Color.BLACK) ? '1' : '0');
		sb.append(state.isCanCastleKingSide(Color.BLACK) ? '1' : '0');

		sb.append(":");
		// enpassant
		if (state.getEnpassantPosition() != null) {
			int i = state.getEnpassantPosition().getRow();
			int j = state.getEnpassantPosition().getCol();

			sb.append("enposition=");
			sb.append(i % 48);
			sb.append(j % 48);
			sb.append(":");
		}

		// capture nor pawn moved
		sb.append("moveswithout=");
		sb.append(state.getNumberOfMovesWithoutCaptureNorPawnMoved() % 48);
		sb.append(":");

		// game result
		if (state.getGameResult() != null) {
			sb.append("gameresult=");
			switch (state.getGameResult().getGameResultReason()) {
			case CHECKMATE:
				sb.append('c');
				sb.append(state.getGameResult().getWinner().toString());
				break;
			case STALEMATE:
				sb.append('s');
				sb.append('0');
				break;
			case FIFTY_MOVE_RULE:
				sb.append('d');
				sb.append('0');
				break;
			case THREEFOLD_REPETITION_RULE:
				sb.append('t');
				sb.append(state.getGameResult().getWinner().toString());
				break;
			}
			sb.append(":");
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = state.getPiece(new Position(i, j));

				sb.append("pospiece=");
				sb.append(i);
				sb.append(j);

				if (piece != null) {
					sb.append(piece.getColor().equals(Color.WHITE) ? 'W' : 'B');
					char pk = setSerializedPieceKind(piece.getKind());
					sb.append(pk);
				} else {
					// we check for this when deserializing...it will mean that
					// the piece is null
					sb.append('Z');
				}
				sb.append(":");
			}
		}
		return sb.toString();
	}

	public static char setSerializedPieceKind(PieceKind pk) {
		char pieceCh;
		if (pk.equals(PieceKind.PAWN)) {
			pieceCh = 'P';
		} else if (pk.equals(PieceKind.ROOK)) {
			pieceCh = 'R';
		} else if (pk.equals(PieceKind.BISHOP)) {
			pieceCh = 'B';
		} else if (pk.equals(PieceKind.KNIGHT)) {
			pieceCh = 'K';
		} else if (pk.equals(PieceKind.QUEEN)) {
			pieceCh = 'Q';
		} else if (pk.equals(PieceKind.KING)) {
			pieceCh = 'C';
		} else {
			pieceCh = 'Z';
		}
		return pieceCh;
	}
}
