package net.bluepoet.chap6;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
	@Mock
	private Logging logging;

	@Test
	public void useConsoleLogging() {
		Client c = new Client(new ConsoleLogging());
		c.doWork(32);
	}

	@Test
	public void useFileLogging() throws IOException {
		File tempFile = File.createTempFile("test", "log");
		Client c = new Client(new FileLogging(tempFile));
		c.doWork(41);
		c.doWork(42);
		c.doWork(43);

		BufferedReader br = new BufferedReader(new FileReader(tempFile));

		assertThat("Even number : 42", is(br.readLine()));
		assertEquals(-1, br.read());
	}

	@Test
	public void useMockLogging() {
		Client c = new Client(logging);

		c.doWork(1);
		c.doWork(2);

		verify(logging).write("Even number : 2");
	}
}
