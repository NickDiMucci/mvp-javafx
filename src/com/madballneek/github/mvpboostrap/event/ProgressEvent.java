package com.madballneek.github.mvpboostrap.event;

public class ProgressEvent {
	public double i;
	public double max;

	public ProgressEvent(double i, double max) {
		this.i = i;
		this.max = max;
	}
}
