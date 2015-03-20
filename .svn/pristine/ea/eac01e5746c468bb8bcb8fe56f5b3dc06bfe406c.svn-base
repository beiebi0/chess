package org.ashishmanral.hw9;

import org.shared.chess.Move;

/**
 * This is a wrapper class that wraps move and score together.
 * @author Ashish
 */
class MoveScore implements Comparable<MoveScore> {

	private Move move;
    private int score;

    /**
     * Constructor
     * @param move
     * @param score
     */
    MoveScore(Move move, int score){
    	this.move = move;
    	this.score = score;
    }
    
    /**
     * Gets the move.
     * @return
     */
    public Move getMove(){
    	return move;
    }
    
    /**
     * Gets the score.
     * @return
     */
    public int getScore(){
    	return score;
    }
    
    /**
     * Sets the move.
     * @param move
     */
    public void setMove(Move move){
    	this.move = move;
    }
    
    /**
     * Sets the score.
     * @param score
     */
    public void setScore(int score){
    	this.score = score;
    }
    
    /**
     * Overrrides the toCompare method of Comparable interface.
     */
    @Override
    public int compareTo(MoveScore o) {
      return o.score - score; // sort DESC (best score first)
    }
    
  }
