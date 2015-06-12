package net.bluepoet.common;

public abstract class TimerJob {
	public Stopwatch execute() {
		Stopwatch timer = new Stopwatch();
		timer.start();
		executeJob();
		timer.stop();
		return timer;
	}

	protected abstract void executeJob();
}
