package org.karthikmahadevan.hw8;

public class GlickoRating {
	private final static double c = 34.641; //from the same assumptions as that found in GLicko website
	private final static double q = 0.00575646273; //from Glicko website
	private final static int rd_max = 350;
	private int ratings_deviation;
	private int ratings;
	
	public GlickoRating(int rd, int r) {
		ratings_deviation = rd;
		ratings = r;
	}
	
	public int getRatingsDeviation() {
		return ratings_deviation;
	}
	
	public void setRatingsDeviation(int rd) {
		ratings_deviation = rd;
	}
	
	public int getRatings() {
		return ratings;
	}
	
	public void setRatings(int r) {
		ratings = r;
	}
	
	public static int updateRDDueToInactivity(int ratings_deviation, int ratings, int ratingPeriods) {
		int new_rd =  (int) Math.sqrt((double)(ratings_deviation*ratings_deviation) + c*c*(double)ratingPeriods);
		if (new_rd > rd_max) {
			return rd_max;
		}
		else {
			return new_rd;
		}
	}
	
	public static Integer [] getUpdatedRatings(int ratings_deviation, int ratings, 
			int opp_ratings_deviation, int opp_ratings, double s_i) {
		Integer arrRatings [] = new Integer[2];
		double g_of_opp_rd = 1/
				(Math.sqrt(1 + 3*q*q*opp_ratings_deviation*opp_ratings_deviation/(Math.PI*Math.PI)));
		double raised_to = g_of_opp_rd*(ratings - opp_ratings)/(-400); 
		double estimation = 1/(1 + Math.pow(10,raised_to));
		double d_square = 1/(q*q*g_of_opp_rd*g_of_opp_rd*estimation*(1-estimation));
		int new_ratings = (int) (ratings + (q/(1/(ratings_deviation*ratings_deviation) + 1/d_square))*g_of_opp_rd*(s_i - estimation));
		arrRatings[0] = new_ratings;
		arrRatings[1] = (int)Math.sqrt(1/(1/(double)(ratings_deviation*ratings_deviation) + 1/d_square));
		return arrRatings;
	}
};
