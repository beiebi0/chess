package org.chenji.hw8;

import java.util.Date;

import org.chenji.hw7.client.Player;
import org.shared.chess.State;

public class Glicko_Eval {
  private static double c2 = 336;
  private final static double q = 0.00575646273;
  private static void rate(Player player, int opponentRating, double s) {
    int t = (int) ((new Date().getTime() - player.getLastGame().getTime()) / (1000 * 60 * 60 * 24));
    double RD0 = Math.sqrt(player.getRD() * player.getRD() + c2 * t);
    double RD = (RD0 < 350 ? RD0 : 350);
    double g = 1 / Math.sqrt(1 + (3 * q * q * RD * RD / (Math.PI * Math.PI)));
    double E = 1 / (1 + Math.pow(10, g * (player.getRating() - opponentRating) / -400)); 
    double d2 = 1 / (q * q * g * g * E * (1 - E));
    double RD2 = RD * RD;
    player.setRating((int) (player.getRating() + (q / (1 / RD2 + 1 / d2)) * g * (s - E)));
    player.setRD(Math.sqrt(1 / (1 / RD2 + 1 / d2)));
  }
  public static void rate(Player player1, Player player2, State state) {
    double s0;
    if (state.getGameResult().getWinner() == null) {
      s0 = 0.5;
    }
    else if (state.getGameResult().getWinner().isWhite()) {
      s0 = 1;
    }
    else {
      s0 = 0;
    }
    double s1 = 1 - s0;
    int rating2 = player2.getRating();
    int rating1 = player1.getRating();
    rate(player1, rating2, s0);
    rate(player2, rating1, s1);
  }
} 
