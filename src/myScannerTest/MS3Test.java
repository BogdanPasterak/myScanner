package myScannerTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import myScanner.MS3;

public class MS3Test {

	public static void main(String[] args) throws IOException {

		InputStream stdin = System.in;
		boolean allTest = true;

		String testS = "";
		String[] answers = {
				// getSrting( all params)
				"yes\r\n", "no\r\n", "\r\n"
		};
		
		// set answers for tests
		for (String ans : answers) {
			testS += ans;
		}
		
		System.setIn(new ByteArrayInputStream(testS.getBytes()));

		// Type yes and Enter getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes", "no");
		// result
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("Pass");
		else
			System.out.println("\nFail   output = " + testS);

		
		// Type no and Enter getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("Pass");
		else
			System.out.println("\nFail   output = " + testS);

		// Type only Enter ( autocomplete ) getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("Pass");
		else
			System.out.println("\nFail   output = " + testS);


		
		// back original setting
		System.setIn(stdin);
		System.out.println("All test = " + allTest);

	}

	
	public static boolean oneOf(String toTest, boolean before, String... strings ) {
		boolean answer;
		
		if (!before)
			return false;
		
		for (String s : strings) {
			if (toTest.equals(s))
				return true;
		}
		
		return false;
	}

}
