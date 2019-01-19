package myScannerTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import myScanner.MS3;

public class MS3TestGetChar {

	public static void main(String[] args) throws IOException {

		InputStream stdin = System.in;
		boolean allTest = true;

		String testS = "";
		char test;
		String[] answers = {
			// getSrting( all params)
				// t01 , t02   , t03      , t04    , t05
				"a\r\n", "\r\n", "ala\r\n", "z\r\n", "\r\n", "z\r\n", "a\r\n"
				
				
		};
		
		// set answers for tests
		for (String ans : answers) {
			testS += ans;
		}
		
		System.setIn(new ByteArrayInputStream(testS.getBytes()));

		System.out.println("Test t01 one entry");
		// t01 Type a and Enter getSrting( all params)
		test = MS3.getChar("info", MS3.ALLOW_FILL, MS3.CHARS.ALL_DIGITS, MS3.CHARS.WITH_WHITE_SIGNS, "abc12345");
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t02 one entry");
		// t02 Type Enter getSrting( all params)
		test = MS3.getChar("info", MS3.ALLOW_FILL, MS3.CHARS.ALL_DIGITS, MS3.CHARS.WITH_WHITE_SIGNS, "abc12345");
		// result
		if (allTest = oneOf(test, allTest, "0123456789 _abc"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t03 one entry");
		// t03 Type ala and Enter change to a getSrting( all params)
		test = MS3.getChar("info", MS3.ALLOW_FILL, MS3.CHARS.ALL_DIGITS, MS3.CHARS.WITH_WHITE_SIGNS, "abc12345");
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println("ala" + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t04 one entry");
		// t04 Type z and Enter getSrting( all params)
		test = MS3.getChar("info", MS3.ALLOW_FILL, MS3.CHARS.ALL_DIGITS, MS3.CHARS.WITH_WHITE_SIGNS, "abc12345");
		// result
		if (allTest = oneOf(test, allTest, "z"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t05 three entry");
		// t05 Type Enter retype z and retype a getSrting( all params)
		test = MS3.getChar("", MS3.DONT_FILL, MS3.CHARS.ALL_DIGITS, MS3.CHARS.WITH_WHITE_SIGNS, "abc12345");
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		

		
		
		
		
		
		// back original setting
		System.setIn(stdin);
		System.out.println("All test = " + allTest);

	}

	
	public static boolean oneOf(char toTest, boolean before, String string ) {
		
		if (!before)
			return false;
		
		for (char ch : string.toCharArray()) {
			if (toTest == ch)
				return true;
		}
		
		return false;
	}

}
