package org.zhihanli.hw9;

import org.shared.chess.PieceKind;

public class PieceValue {
	public final static int pawnValue = 100;
	public final static int knightValue = 300;
	public final static int bishopValue = 300;
	public final static int rookValue = 500;
	public final static int queenValue = 900;
	public final static int kingValue = 2000;

	public static int getPieceSquareValue(int row, int col, PieceKind kind) {
		switch (kind) {
		case PAWN:
			return pawnPieceSquareTable[7 - row][col];
		case KNIGHT:
			return knightPieceSquareTable[7 - row][col];
		case BISHOP:
			return bishopPieceSquareTable[7 - row][col];
		case ROOK:
			return rookPieceSquareTable[7 - row][col];
		case QUEEN:
			return queenPieceSquareTable[7 - row][col];
		case KING:
			return kingPieceSquareTable[7 - row][col];
		default:
			return 0;
		}
	}

	public static int[][] pawnPieceSquareTable = { { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 50, 50, 50, 50, 50, 50, 50, 50 },
			{ 10, 10, 20, 30, 30, 20, 10, 10 }, { 5, 5, 10, 25, 25, 10, 5, 5 },
			{ 0, 0, 0, 20, 20, 0, 0, 0 }, { 5, -5, -10, 0, 0, -10, -5, 5 },
			{ 5, 10, 10, -20, -20, 10, 10, 5 }, { 0, 0, 0, 0, 0, 0, 0, 0 } };

	public static int[][] knightPieceSquareTable = {
			{ -50, -40, -30, -30, -30, -30, -40, -50 },
			{ -40, -20, 0, 0, 0, 0, -20, -40 },
			{ -30, 0, 10, 15, 15, 10, 0, -30 },
			{ -30, 5, 15, 20, 20, 15, 5, -30 },
			{ -30, 0, 15, 20, 20, 15, 0, -30 },
			{ -30, 5, 10, 15, 15, 10, 5, -30 },
			{ -40, -20, 0, 5, 5, 0, -20, -40 },
			{ -50, -40, -30, -30, -30, -30, -40, -50 } };

	public static int[][] bishopPieceSquareTable = {
			{ -20, -10, -10, -10, -10, -10, -10, -20 },
			{ -10, 0, 0, 0, 0, 0, 0, -10 }, { -10, 0, 5, 10, 10, 5, 0, -10 },
			{ -10, 5, 5, 10, 10, 5, 5, -10 },
			{ -10, 0, 10, 10, 10, 10, 0, -10 },
			{ -10, 10, 10, 10, 10, 10, 10, -10 },
			{ -10, 5, 0, 0, 0, 0, 5, -10 },
			{ -20, -10, -10, -10, -10, -10, -10, -20 } };

	public static int[][] rookPieceSquareTable = { { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 5, 10, 10, 10, 10, 10, 10, 5 }, { -5, 0, 0, 0, 0, 0, 0, -5 },
			{ -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 },
			{ -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 },
			{ 0, 0, 0, 5, 5, 0, 0, 0 } };

	public static int[][] queenPieceSquareTable = {
			{ -20, -10, -10, -5, -5, -10, -10, -20 },
			{ -10, 0, 0, 0, 0, 0, 0, -10 }, { -10, 0, 5, 5, 5, 5, 0, -10 },
			{ -5, 0, 5, 5, 5, 5, 0, -5 }, { 0, 0, 5, 5, 5, 5, 0, -5 },
			{ -10, 5, 5, 5, 5, 5, 0, -10 }, { -10, 0, 5, 0, 0, 0, 0, -10 },
			{ -20, -10, -10, -5, -5, -10, -10, -20 } };

	public static int[][] kingPieceSquareTable = {
			{ -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -20, -30, -30, -40, -40, -30, -30, -20 },
			{ -10, -20, -20, -20, -20, -20, -20, -10 },
			{ 20, 20, 0, 0, 0, 0, 20, 20 }, { 20, 30, 10, 0, 0, 10, 30, 20 } };
}
