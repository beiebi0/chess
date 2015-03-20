package org.haoxiangzuo.hw8;

public class EloRating {
	public static int computeElo(int rA, int rB, double sA)
	{
		double rankA = rA;
		double rankB = rB;
		double eA = 1/(1+Math.pow(10, (rankB-rankA)/400));
		double rankANew = rA + 15*(sA-eA);
		int result = (int) rankANew;
		return result;
	}
}
