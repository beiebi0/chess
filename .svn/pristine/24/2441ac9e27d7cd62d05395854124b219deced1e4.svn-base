package org.bohouli.hw6.server;

public class BohouRank {
    static final double C = 20;
    static final double Q = 0.00575646273;
    
    public static double getRD(int t,double RD){      
            return Math.min(Math.sqrt((RD * RD + C * C * t)), 350);
    }
    
    public static double getg(double RD){
            return 1 / Math.sqrt(1 + (3 * Q * Q * RD * RD / Math.PI * Math.PI));
    }
    
    public static double getE(double gRD, double r, double ri){
            return 1 / (1 + Math.pow(10, gRD * (r - ri)/-400));        
    }
    
    public static double getd2(double E, double g){
            return 1 / (Q * Q * g * g * E * (1 - E));
    }
    
    public static double Calculate(double myRank, double opponentRank, 
    		double oldRD, int t, double win) {          
            double RD = getRD(t, oldRD);
            double g = getg(RD);
            double E = getE(g, myRank, opponentRank);
            double d2 = getd2(E,g);
            
            return myRank + Q * g * (win - E) / (1 / Math.pow(RD, 2) + 1 / Math.pow(d2, 2));          
    }
}
