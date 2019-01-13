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
				// t001  , t002    , t003  , t004                       , t005  , t006
				"yes\r\n", "no\r\n", "\r\n", "\r\n", "ye\r\n", "yes\r\n", "\r\n", "yes\r\n",
				// t007 , t008               , t009  , t010                  , t011
				"no\r\n", "no\r\n", "yes\r\n", "\r\n", "eggs\r\n", "Eggs\r\n", "Rice\r\n"
		};
		
		// set answers for tests
		for (String ans : answers) {
			testS += ans;
		}
		
		System.setIn(new ByteArrayInputStream(testS.getBytes()));

		System.out.println("Test t001 one entry");
		// t001 Type yes and Enter getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes", "no");
		// result
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("yes\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t002 one entry");
		// t002 Type no and Enter getSrting( all params) 
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("no\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t003 one entry");
		// t003 Type only Enter ( autocomplete ) getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t004 tree entry");
		// t004 Type Enter (wrong) retype ye (wrong) retype yes getSrting( all params)
		// DONT_FILL - required proper answer
		testS = MS3.getString("info", MS3.DONT_FILL, "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("yes\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		System.out.println("Test t005 one entry");
		// t005 Type only Enter ( autocomplete ), one in array getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t006 one entry");
		// t006 Type yes Enter, one in array getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes");
		if (allTest = oneOf(testS, allTest, "yes"))
			System.out.println("\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t007 one entry");
		// t007 Type no Enter , one in array, allow different getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, "yes");
		if (allTest = oneOf(testS, allTest, "no"))
			System.out.println("\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t008 two entry");
		// t008 Type no (wrong) retype yes Enter, one in array getSrting( all params)
		testS = MS3.getString("info", MS3.DONT_FILL, "yes");
		if (allTest = oneOf(testS, allTest, "yes"))
			System.out.println("\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t009 one entry");
		// t009 Type Enter, category getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, MS3.STRINGS.ITEM);
		if (allTest = oneOf(testS, allTest, "Milk", "Eggs", "Bread", "Butter", "Sugar", "Sweets"))
			System.out.println("\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t010 two entry");
		// t010 Type eggs (wrong) retype Eggs Enter, category getSrting( all params)
		testS = MS3.getString("info", MS3.DONT_FILL, MS3.STRINGS.ITEM);
		if (allTest = oneOf(testS, allTest, "Milk", "Eggs", "Bread", "Butter", "Sugar", "Sweets"))
			System.out.println("\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t011 one entry");
		// t010 Type Rise Enter, category, can different getSrting( all params)
		testS = MS3.getString("info", MS3.ALLOW_FILL, MS3.STRINGS.ITEM);
		if (allTest = oneOf(testS, allTest, "Rice"))
			System.out.println("\nPass");
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
