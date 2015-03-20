package org.kuangchelee.hw9;

import java.util.Date;

public class DateTimer implements Timer {
  private long start;
  private int milliseconds;

  public DateTimer(int milliseconds) {
    this.milliseconds = milliseconds;
    if (milliseconds > 0) {
      start = now();
    }
  }

  public long now() {
    return new Date().getTime();
  }

  @Override
  public boolean didTimeout() {
    return milliseconds <= 0 ? false : now() > start + milliseconds;
  }

}