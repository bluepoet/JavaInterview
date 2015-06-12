package net.bluepoet.chap6;

public class Client {
	private Logging logging;
	
	public Client(Logging logging) {
		this.logging = logging;
	}
	
	public void doWork(int count) {
		if(count % 2 == 0) {
			logging.write("Even number : " + count);
		}
	}
}
