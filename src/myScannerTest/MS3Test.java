package myScannerTest;

import java.awt.AWTException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import myScanner.MS3;

public class MS3Test {

	public static void main(String[] args) {

		InputStream stdin = System.in;
		boolean allTest = true;

		String testS;

		// Type Enter getSrting( all params)
		// set answer for test
		System.setIn(new ByteArrayInputStream("yes\r\n".getBytes()));
		// test
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes", "no");
		// result
		if (allTest = allTest && (testS.equals("yes") || testS.equals("no")))
			System.out.println("Pass");
		else
			System.out.println("\nFail   output = " + testS);

/*
		// Type no getSrting( all params)
		System.setIn(new ByteArrayInputStream("\r\n".getBytes()));
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes", "no");
		if (allTest = allTest && (testS == "yes" || testS == "no"))
			System.out.println("Pass");
		else
			System.out.println("\nFail   output = " + testS);
*/
		// back original setting
		System.setIn(stdin);
		System.out.println("All test = " + allTest);

	}

	public static void setTest() {

	}

}
