package de.htwg.kalaha;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class KalahaTest extends TestCase { 

	
	public void setUp() throws Exception {
		
	}
	
	public void testNew() {
		InputStream stdin = System.in;

		String data = "q\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));

		Kalaha k = new Kalaha();
		
		assertNotNull(k);
			
		System.setIn(stdin);
	}
	
	public void testMain()
	{
		InputStream stdin = System.in;
		PrintStream stdout = System.out;

		String data = "q\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		
		Kalaha.main(new String[] {});
		
		assertNotNull(output.toString());
		
		System.setIn(stdin);
		System.setOut(stdout);
	}
}
