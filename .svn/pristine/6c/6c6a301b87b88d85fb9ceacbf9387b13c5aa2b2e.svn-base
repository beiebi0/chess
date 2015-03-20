package org.shihweihuang.hw8.server;

public class ELOCalculator {
	final static private int K = 15;
	
	public static double[] newRank(double first, ResultType result, double second){
		double E1 = calculateE(first, second);
		double E2 = calculateE(second, first);
		double[] newRankFirstSecond = new double[2];
		if (result == ResultType.WIN){
			newRankFirstSecond[0] = first + K * (1 - E1);
			newRankFirstSecond[1] = second + K * (0 - E2);
		} else if (result == ResultType.LOSE) {
			newRankFirstSecond[0] = first + K * (0 - E1);
			newRankFirstSecond[1] = second + K * (1 - E2);
		} else {
			newRankFirstSecond[0] = first + K * (0.5 - E1);
			newRankFirstSecond[1] = second + K * (0.5 - E2);
		}
		return newRankFirstSecond;
	}
	
	private static double calculateE(double r1, double r2){
		return 1/(1 + Math.pow(10, (r2-r1)/400));
	}
}
