package org.bohouli.hw9;

import java.util.Date;

public class BohouTimer {
	private long start;
	private int milliseconds;

	public BohouTimer(int milliseconds) {
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
