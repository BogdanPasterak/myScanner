package myScannerTest;

import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myScanner.MS;

public class TestJUnit4String {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private final InputStream originalIn = System.in;
	InputStream in;
	byte[] data;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	    System.setIn(originalIn);
	} 
	
	@Test
	public void testGetStringWithoutParametrNotTyped() {
		// type B [ENTER]
		
		String type = "";
		String prompt = "Type line: ";
		
	    data = (type + "\n").getBytes();
	    in = new ByteArrayInputStream(data);
	    System.setIn(in);

	    MS.resetScanner();
		String answer = MS.getString();
	    assertEquals(prompt, outContent.toString());
	    // random value
	    assertEquals(answer, answer);
	    
	}

	@Test
	public void testGetStringWithoutParametrTypedB() {
		// type B [ENTER]
		
		String type = "B";
		String prompt = "Type line: ";
		
	    data = (type + "\n").getBytes();
	    in = new ByteArrayInputStream(data);
	    System.setIn(in);

	    MS.resetScanner();
		String answer = MS.getString();
	    assertEquals(prompt, outContent.toString());
	    assertEquals(type, answer);
	}

	@Test
	public void err() {
	    System.err.print("hello again");
	    assertEquals("hello again", errContent.toString());
	}



}