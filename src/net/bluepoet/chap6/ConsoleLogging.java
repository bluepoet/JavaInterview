package net.bluepoet.chap6;

public class ConsoleLogging implements Logging {

	@Override
	public void write(String message) {
		System.out.println(message);
	}
}
