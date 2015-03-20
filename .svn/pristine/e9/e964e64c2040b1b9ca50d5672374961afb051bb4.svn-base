package org.longjuntan.hw9;

import java.util.Date;

public class GameTimer {
	private long start;
	  private int milliseconds;

	  public GameTimer(int milliseconds) {
	    this.milliseconds = milliseconds;
	    if (milliseconds > 0) {
	      start = now();
	    }
	  }

	  public long now() {
	    return new Date().getTime();
	  }

	  public boolean didTimeout() {
	    return milliseconds <= 0 ? false : now() > start + milliseconds;
	  }
}
