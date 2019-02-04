package testMyScanner;

import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;

//import myScanner.MyScanner;
import myScanner.MyScanner2;

class TestJunit1String {
	InputStream in;
	byte[] data;
	
	
	@Test
	void WithoutParametrNotTyped() {
		
		// type [ENTER]
	    data = "\n".getBytes();
	    in = new ByteArrayInputStream(data);
	    System.out.println();
	    System.setIn(in);
	    try {
	    	assertEquals("", MyScanner2.getString() );
		} catch (Exception e) {
			System.out.println("\nWithoutParametrNotTyped Exception : " + e.getMessage());
		}
	}
	
	@Test
	void WithoutParametr() {
				
		// type Bogdan + [ENTER]
	    data = "Bogdan".getBytes();
		in = new ByteArrayInputStream(data);
	    System.setIn(in);
	    try {
	    	assertEquals("Bogdan", MyScanner2.getString() );
		} catch (Exception e) {
			System.out.println("\nException : " + e.getMessage());
		}
	}
	
	@Test
	void WithParametrInfo() {
				
		// type Bogdan + [ENTER]
	    data = "Bogdan".getBytes();
		in = new ByteArrayInputStream(data);
	    System.setIn(in);
	    try {
	    	assertEquals("Bogdan", MyScanner2.getString("info") );
		} catch (Exception e) {
			System.out.println("\nException : " + e.getMessage());
		}
	}
	

}
