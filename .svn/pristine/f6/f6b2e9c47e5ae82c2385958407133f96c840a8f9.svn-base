package org.zhaohuizhang.hw8;

public class RankCaculator {
	private final static int K = 16;
	private static RankCaculator instance;

	private RankCaculator() {
	}

	public static RankCaculator getInstance() {
		if (instance == null) {
			synchronized (RankCaculator.class) {
				if (instance == null) {
					return new RankCaculator();
				}
			}
		}
		return instance;
	}

	public double calculateRanking(double rankOfPlayer, double rankOfOpponent,
			ResultOfPlayer result) {
		double expectedScoreOfPalyer = 1 / (1 + Math.pow(10,
				(rankOfOpponent - rankOfPlayer) / 400));
		double actualScore = 0;
		switch (result) {
		case WIN:
			actualScore = 1.0;
			break;
		case LOSE:
			actualScore = 0;
			break;
		case DRAW:
			actualScore = 0.5;
			break;
		default:
			break;
		}

		double actualRankOfPlayer = rankOfPlayer + K
				* (actualScore - expectedScoreOfPalyer);
		return Math.round(actualRankOfPlayer);
	}


}
