package org.mengyanhuang.hw8;

import org.mengyanhuang.hw7.Player;

import com.google.gwt.user.client.Window;

public class PlayerRankCalculator {
	
		final static double q = 0.0057565;
		final static double c = 18.131937;
		
		public static double g(double RD){
			double temp = 1 + 3*q*q*(RD*RD)/(Math.PI*Math.PI);
			return 1/Math.sqrt(temp);
		}
		
		public static double E(int RDj,int r,int rj){
			double temp = Math.pow(10, (-g(RDj)*(r-rj)/400.0));
			return 1/(1+temp);
		}
		
		public static double dSquare(double g, double E){
			double temp = g*g*q*q*E*(1-E);
			return 1/(temp);		
		}
		
		/*
		public static double dSquare(double r,Player op){
			double g = g(op.getRD());
			double square=g*g;
			double e = E(op.getRD(),r,op.getRank());
			double m=(sqr*e*(1-e));		
		}*/
		
		public static int DatePassed(long d1,long d2){
			int t = (int)( (d1 - d2) / (60*60*24*1000) );
			return t;
		}
		/*
		public static double interRD(double RD,int t){
			return Math.min(Math.sqrt(RD*RD+c*c*t), 350.0);
		}*/
	
		public static int newRD(Player pl, Player op, int t){
			double RD = pl.getRD();
			//Window.alert("pl.getRd:" + RD);
			double temp=dSquare(g(op.getRD()),E(op.getRD(),pl.getRank(),op.getRank()));
			double temp1=Math.min(Math.sqrt(RD*RD+c*c*t), 350.0);
			//Window.alert("new 1:"+ temp1);
			double temp2=Math.pow(temp1,2);
			//Window.alert("sqrt:"+temp2);
			double temp3 = 1/(1/temp2+1/temp);
			//Window.alert("1/rd+1/d2:"+temp3);
			
			return (int)(Math.sqrt(temp3));
		}
		
		public static int newRank(Player pl,Player op, double sj, int t){
			int r = pl.getRank();
			double e = E(op.getRD(),r,op.getRank());
			double sigma=g(op.getRD())*(sj-e);
			double temp = 1/(pl.getRank()*pl.getRank()) + 1/dSquare(g(op.getRD()),E(op.getRD(),pl.getRank(),op.getRank()));
			double temp2 = q*(1/temp);
			int addition=(int)(sigma*temp2);
			
			return r+addition;
		}

}
