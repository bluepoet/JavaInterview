package net.bluepoet.chap6;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

public class PatternTest {
	@Test
	public void decoratorPattern() throws IOException {
		File f = new File("out.bin");
		FileOutputStream fos = new FileOutputStream(f);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeBoolean(true);
		oos.writeInt(42);
		oos.writeObject(new ArrayList<Integer>());

		oos.flush();
		oos.close();
		bos.close();
		fos.close();

		assertTrue(f.exists());
	}
	
	@Test
	public void sameIntegerInstances() {
		Integer a = Integer.valueOf(56);
		Integer b = Integer.valueOf(56);
		
		assertSame(a, b);
		
		Integer c = Integer.valueOf(472);
		Integer d = Integer.valueOf(472);
		
		assertNotSame(c, d);
	}
}
