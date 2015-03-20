package org.longjuntan.hw8;

public class Ranking {
	
	public static int getDefaultRanking(){
		return 1500;
	}
	static final int K = 15;

	public enum Result {
		WIN(1), DRAW(0.5), LOSE(0);
		double value;

		Result(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}
	};

	public static int getNewRank(int oldRank, int opponentRank, Result result) {

		double e = 1.0 / (1 + Math.pow(10, (opponentRank - oldRank) / 400));

		return (int) Math.round(Math.floor(oldRank
				+ (K * (result.getValue() - e))));
	}
}
