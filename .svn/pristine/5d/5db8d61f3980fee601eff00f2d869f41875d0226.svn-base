package org.longjuntan.hw3;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.GameResultReason;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.PieceKind;
import org.shared.chess.Position;
import org.shared.chess.State;

public class Utils {
	public static String getHistory(State state) {
		StringBuilder history = new StringBuilder();
		history.append("STATE");
		history.append("turn=" + state.getTurn().toString() + "&");
		history.append("board=");

		for (int r = 0; r < State.ROWS; r++) {
			for (int c = 0; c < State.COLS; c++) {
				history.append(r).append(c);
				Piece p = state.getPiece(r, c);
				if (p != null) {
					history.append(p.getColor());
					switch (p.getKind()) {
					case PAWN:
						history.append("P");
						break;
					case ROOK:
						history.append("R");
						break;
					case KNIGHT:
						history.append("N");
						break;
					case BISHOP:
						history.append("B");
						break;
					case QUEEN:
						history.append("Q");
						break;
					case KING:
						history.append("K");
						break;
					default:
						break;
					}
				} else {
					history.append("N");
				}
				history.append(",");
			}
		}

		history.append("&canCastle=");
		for (boolean b : new boolean[] {
				state.isCanCastleKingSide(Color.WHITE),
				state.isCanCastleKingSide(Color.BLACK),
				state.isCanCastleQueenSide(Color.WHITE),
				state.isCanCastleQueenSide(Color.BLACK) }) {
			if (b) {
				history.append("T");
			} else {
				history.append("F");
			}
		}
		if (state.getEnpassantPosition() != null) {
			Position p = state.getEnpassantPosition();
			history.append("&ep=").append(p.getRow()).append(p.getCol());
		}

		history.append("&movesWithoutCaptureNorPawnMoved=").append(
				state.getNumberOfMovesWithoutCaptureNorPawnMoved());

		if (state.getGameResult() != null) {
			GameResult r = state.getGameResult();
			history.append("&result=").append("winner:");
			if (r.getWinner() != null) {
				history.append(r.getWinner().toString());
			} else{
				history.append("N");
			}
			history.append(",reason:").append(r.getGameResultReason());
		}

		return history.toString();
	}

	public static State setStateByHistory(String history) {
		Color turn = null;
		Piece[][] board = new Piece[State.ROWS][State.COLS];
		boolean[] canCastleKingSide = new boolean[] { true, true };
		boolean[] canCastleQueenSide = new boolean[] { true, true };
		Position enpassantPosition = null;
		int numberOfMovesWithoutCaptureNorPawnMoved = 0;
		GameResult gameResult = null;
		
		history = history.substring(5);

		for (String item : history.split("&")) {
			String key = item.split("=")[0];
			String value = item.split("=")[1];

			if (key.equals("turn")) {
				turn = (value.equals("B") ? Color.BLACK : Color.WHITE);
			} else if (key.equals("board")) {
				for (String piece : value.split(",")) {
//					System.out.print(piece);
					int row = Integer.parseInt(piece.substring(0, 1));
					int col = Integer.parseInt(piece.substring(1, 2));
//					System.out.println(key.substring(2));
					if (piece.substring(2).equals("N")) {
						board[row][col] = null;
					} else {
						board[row][col] = new Piece(
								piece.charAt(2) == 'B' ? Color.BLACK
										: Color.WHITE, getKind(piece.charAt(3)));
					}

				}
			} else if (key.equals("canCastle")) {
				canCastleKingSide[0] = getBoolean(value.charAt(0));
				canCastleKingSide[1] = getBoolean(value.charAt(1));
				canCastleQueenSide[0] = getBoolean(value.charAt(2));
				canCastleQueenSide[1] = getBoolean(value.charAt(3));
			} else if (key.equals("ep")) {
				enpassantPosition = new Position(Integer.parseInt(value
						.substring(0, 1)), Integer.parseInt(value.substring(1,2)));
			} else if(key.equals("movesWithoutCaptureNorPawnMoved")){
				numberOfMovesWithoutCaptureNorPawnMoved = Integer.parseInt(value);
			} else if(key.equals("result")){
				Color color = null;
				GameResultReason r = null;
				for(String sub:value.split(",")){		
					if(sub.contains("winner")){
						char winner = sub.charAt(sub.length()-1);
						if(winner=='B'){
							color = Color.BLACK;
						}else if(winner == 'W'){
							color = Color.WHITE;
						}else{
							color = null;
						}
					}else{
						String reason = sub.split(":")[1];
						if (reason.equals("STALEMATE")) {
							r = GameResultReason.STALEMATE;
						} else if (reason.equals("CHECKMATE")) {
							r = GameResultReason.CHECKMATE;
						} else if (reason.equals("FIFTY_MOVE_RULE")) {
							r = GameResultReason.FIFTY_MOVE_RULE;
						}
					}
				}
				gameResult = new GameResult(color,r);
			}
		}
		
		return new State(turn, board, 
			      canCastleKingSide, canCastleQueenSide, enpassantPosition,
			      numberOfMovesWithoutCaptureNorPawnMoved, gameResult);
	}

	private static PieceKind getKind(char ch) {
		PieceKind kind = null;
		switch (ch) {
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
		default:
			break;
		}
		return kind;
	}

	private static boolean getBoolean(char ch) {
		return ch == 'T' ? true : false;
	}
	
	public static Move getMoveFromString(String string) {
		string = string.trim();
		PieceKind pk = null;
		if (string.contains("promoting to")) {
			// string.replaceAll(")", "");
			// String substring[] = string.split(" (promoting to ");
			pk = PieceKind.valueOf(string.substring(27, string.length() - 1));
		}
		String[] positions = string.split("->");
		Position from = getPositionFromString(positions[0]);
		Position to = getPositionFromString(positions[1]);
		return new Move(from, to, pk);

	}

	public static Position getPositionFromString(String string) {
		int row = Integer.parseInt(string.substring(1, 2));
		int col = Integer.parseInt(string.substring(3, 4));
		return new Position(row, col);
	} 
	
}
