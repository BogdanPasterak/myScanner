package myScannerTest;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import myScanner.MS;

public class TestJUnit4String {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private final InputStream originalIn = System.in;
	private String typeData;
	private String setPrompt;
	private String expectPrompt;
	private String receivedPrompt;
	private String expectAnswer;
	private String receivedAnswer;
	
	
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
	public void testGetStringAllParamType01() {
		// type Bodzio [ENTER] allow

	    typeData = "Bodzio\n";
		expectAnswer = "Bodzio";
		setPrompt = "Enter first name: ";
		expectPrompt = "Enter first name: ";
		
	    System.setIn( new ByteArrayInputStream(typeData.getBytes()) );
	    MS.resetScanner();
	    
	    receivedAnswer = MS.getString(setPrompt, MS.ALLOW_FILL, "Bogdan", "Ryki");
	    receivedPrompt = outContent.toString();
	    
	    assertEquals(expectPrompt, receivedPrompt);
	    assertEquals(expectAnswer, receivedAnswer);
	}

	@Test
	public void testGetStringAllParamType02() {
		// type Bodzio [ENTER] allow, update prompt

	    typeData = "Bodzio\n";
		expectAnswer = "Bodzio";
		setPrompt = "Enter first name:";
		expectPrompt = "Enter first name: ";
		
	    System.setIn( new ByteArrayInputStream(typeData.getBytes()) );
	    MS.resetScanner();
	    
	    receivedAnswer = MS.getString(setPrompt, MS.ALLOW_FILL, "Bogdan", "Ryki");
	    receivedPrompt = outContent.toString();
	    
	    assertEquals(expectPrompt, receivedPrompt);
	    assertEquals(expectAnswer, receivedAnswer);
	}

	@Test
	public void testGetStringAllParamType03() {
		// type Bodzio [ENTER], not allow, Bogdan [Enter], update prompt

	    typeData = "Bodzio\nBogdan\n";
		expectAnswer = "Bogdan";
		setPrompt = "Enter first name";
		expectPrompt = "Enter first name: " + "Improper text, try again." + System.getProperty("line.separator") + "Enter first name: ";
		// windows end line
//		expectPrompt = "Enter first name: " + "Improper text, try again.\r\n" + "Enter first name: ";
		
	    System.setIn( new ByteArrayInputStream(typeData.getBytes()) );
	    MS.resetScanner();
	    
	    receivedAnswer = MS.getString(setPrompt, MS.DONT_FILL, "Bogdan", "Ryki");
	    receivedPrompt = outContent.toString();
	    
	    assertEquals(expectPrompt, receivedPrompt);
	    assertEquals(expectAnswer, receivedAnswer);
	}

	@Test
	public void testGetStringAllParamNotTyped01() {
		// type [ENTER], update prompt

	    typeData = "\n";
		expectAnswer = "";	// random value from added Strings
		setPrompt = "";
		expectPrompt = "Type line: ";
		
	    System.setIn( new ByteArrayInputStream(typeData.getBytes()) );
	    MS.resetScanner();
	    
	    receivedAnswer = MS.getString(setPrompt, MS.ALLOW_FILL, "Bogdan", "Ryki");
	    receivedPrompt = outContent.toString();
	    
	    assertEquals(expectPrompt, receivedPrompt);
	    assertThat( Arrays.asList("Bogdan", "Ryki"), hasItem(receivedAnswer) );
	    //assertThat( Arrays.asList(MS.STRINGS.getStringsFromAllCategories()), hasItem(receivedAnswer) );
	}

	@Test
	public void testGetStringAllParamNotTyped02() {
		// type [ENTER], not allow, B [ENTER], not allow, Bogdan [Enter], update prompt

	    typeData = "\nB\nBogdan\n";
		expectAnswer = "Bogdan";
		setPrompt = null;
		expectPrompt = "Type line: " + "Improper text, try again." + System.getProperty("line.separator") + "Type line: " +
				"Improper text, try again." + System.getProperty("line.separator") + "Type line: ";
		// windows end line
//		expectPrompt = "Type line: " + "Improper text, try again.\r\n" + "Type line: " +
//				"Improper text, try again.\r\n" + "Type line: ";
		
	    System.setIn( new ByteArrayInputStream(typeData.getBytes()) );
	    MS.resetScanner();
	    
	    receivedAnswer = MS.getString(setPrompt, MS.DONT_FILL, "Bogdan", "Ryki");
	    receivedPrompt = outContent.toString();
	    
	    assertEquals(expectPrompt, receivedPrompt);
	    assertEquals(expectAnswer, receivedAnswer);
	}

	
	
	@Test
	public void err() {
	    System.err.print("hello again");
	    assertEquals("hello again", errContent.toString());
	}

}