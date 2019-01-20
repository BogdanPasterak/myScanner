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
				"a\r\n", "\r\n", "ala\r\n", "z\r\n", "\r\n", "z\r\n", "a\r\n",
				// getSrting( string, boolean, string)
				// t06, t07    , t08    , t09
				"\r\n", "a\r\n", "z\r\n", "\r\n", "z\r\n", "b\r\n",
				// getSrting( string, string) and getSrting( string )
				// t10, t11    , t12    , t13   , t14
				"\r\n", "a\r\n", "z\r\n", "\r\n", "a\r\n",
				// getSrting( string... ) over 2 and getSrting()
				// t15, t16    , t17    , t18   , t19
				"\r\n", "a\r\n", "z\r\n", "\r\n", "a\r\n",
				// getSrting(boolean) over 2 and getSrting(string , boolean)
				// t20, t21    , t22            , t23   , t24    , t25
				"\r\n", "a\r\n", "\r\n", "a\r\n", "\r\n", "a\r\n", "\r\n", "a\r\n",
				// getSrting( boolean, string... ) and getSrting( boolean, string ) 
				// t26, t27    , t28                     , t29   , t30    , t31
				"\r\n", "a\r\n", "\r\n", "a\r\n", "A\r\n", "\r\n", "a\r\n", "\r\n", "a\r\n", "A\r\n"
		};
		
		// set answers for tests
		for (String ans : answers) {
			testS += ans;
		}
		
		System.setIn(new ByteArrayInputStream(testS.getBytes()));
		
		// getSrting( all params)

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
		
		// getSrting( string, boolean, string)
		
		System.out.println("Test t06 one entry");
		// Type Enter
		test = MS3.getChar("Type a or b", MS3.ALLOW_FILL, "ab");
		// result
		if (allTest = oneOf(test, allTest, "ab"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t07 one entry");
		// Type a Enter
		test = MS3.getChar("Type a or b", MS3.ALLOW_FILL, "ab");
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t08 one entry");
		// Type z Enter
		test = MS3.getChar("Type a or b", MS3.ALLOW_FILL, "ab");
		// result
		if (allTest = oneOf(test, allTest, "z"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t09 three entry");
		// Type Enter retype z retype b 
		test = MS3.getChar("Type a or b", MS3.DONT_FILL, "ab");
		// result
		if (allTest = oneOf(test, allTest, "b"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		// getSrting( string, string) and getSrting( string) default boolean allow_fill and getSrting( string)

		System.out.println("Test t10 one entry");
		// Type Enter
		test = MS3.getChar("Type a or b", "ab");
		// result
		if (allTest = oneOf(test, allTest, "ab"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t11 one entry");
		// Type a Enter
		test = MS3.getChar("Type a or b", "ab");
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t12 one entry");
		// Type z Enter
		test = MS3.getChar("Type a or b", "ab");
		// result
		if (allTest = oneOf(test, allTest, "z"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t13 one entry");
		// Type Enter one of all
		test = MS3.getChar("Type a or b");
		// result
		if (allTest = oneOf(test, allTest, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 _\\/|?<>,.~#@':;}]`{[+=-)(*&^%$£\"!€"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t14 one entry");
		// Type a Enter
		test = MS3.getChar("Type a or b");
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		// getSrting( string... ) and getSrting() default boolean allow_fill and getSrting( string)

		System.out.println("Test t15 one entry");
		// Type Enter
		test = MS3.getChar("Type a or b", "ab", MS3.CHARS.ALL_DIGITS);
		// result
		if (allTest = oneOf(test, allTest, "ab0123456789"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t16 one entry");
		// Type a Enter
		test = MS3.getChar("Type a or b", "ab", MS3.CHARS.ALL_DIGITS);
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t17 one entry");
		// Type z Enter
		test = MS3.getChar("Type a or b", "ab", MS3.CHARS.ALL_DIGITS);
		// result
		if (allTest = oneOf(test, allTest, "z"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t18 one entry");
		// Type Enter one of all
		test = MS3.getChar();
		// result
		if (allTest = oneOf(test, allTest, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 _\\/|?<>,.~#@':;}]`{[+=-)(*&^%$£\"!€"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t19 one entry");
		// Type a Enter
		test = MS3.getChar();
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		// getSrting( boolean ) and getSrting() default boolean allow_fill 
		
		System.out.println("Test t20 one entry");
		// Type Enter one of all
		test = MS3.getChar(MS3.ALLOW_FILL);
		// result
		if (allTest = oneOf(test, allTest, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 _\\/|?<>,.~#@':;}]`{[+=-)(*&^%$£\"!€"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t21 one entry");
		// Type a Enter
		test = MS3.getChar(MS3.ALLOW_FILL);
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t22 two entry");
		// Type  Enter retype a
		test = MS3.getChar(MS3.DONT_FILL);
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t23 one entry");
		// Type Enter one of all
		test = MS3.getChar("info", MS3.ALLOW_FILL);
		// result
		if (allTest = oneOf(test, allTest, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 _\\/|?<>,.~#@':;}]`{[+=-)(*&^%$£\"!€"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t24 one entry");
		// Type a Enter
		test = MS3.getChar("" , MS3.ALLOW_FILL);
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t25 two entry");
		// Type  Enter retype a
		test = MS3.getChar("info: ", MS3.DONT_FILL);
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		// getSrting( boolean, string... ) and getSrting( boolean, string ) 
		
		System.out.println("Test t26 one entry");
		// Type Enter one of all
		test = MS3.getChar(MS3.ALLOW_FILL, MS3.CHARS.ALL_DIGITS, "ABC" );
		// result
		if (allTest = oneOf(test, allTest, "0123456789ABC"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t27 one entry");
		// Type a Enter
		test = MS3.getChar(MS3.ALLOW_FILL, MS3.CHARS.ALL_DIGITS, "ABC" );
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t28 three entry");
		// Type  Enter retype a retype A
		test = MS3.getChar(MS3.DONT_FILL, MS3.CHARS.ALL_DIGITS, "ABC" );
		// result
		if (allTest = oneOf(test, allTest, "A"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t29 one entry");
		// Type Enter one of all
		test = MS3.getChar(MS3.ALLOW_FILL, "ABC" );
		// result
		if (allTest = oneOf(test, allTest, "ABC"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t30 one entry");
		// Type a Enter
		test = MS3.getChar(MS3.ALLOW_FILL, "ABC" );
		// result
		if (allTest = oneOf(test, allTest, "a"))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t31 three entry");
		// Type  Enter retype a retype A
		test = MS3.getChar(MS3.DONT_FILL, "ABC" );
		// result
		if (allTest = oneOf(test, allTest, "A"))
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
