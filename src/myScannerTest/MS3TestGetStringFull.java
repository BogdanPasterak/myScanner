package myScannerTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import myScanner.MS3;

public class MS3TestGetStringFull {

	public static void main(String[] args) throws IOException {

			InputStream stdin = System.in;
			boolean allTest = true;

			String testS = "";
			String[] answers = {
				// getSrting( all params)
					// t001  , t002    , t003  , t004                       ,
					"yes\r\n", "no\r\n", "\r\n", "\r\n", "ye\r\n", "yes\r\n",
				// getString( Str, bollean, Str )
					// t005  , t006  , t007 , t008               , t009  ,
					"\r\n", "yes\r\n", "no\r\n", "no\r\n", "yes\r\n", "\r\n",
					//t010                  , t011    , t012       , t013
					"eggs\r\n", "Eggs\r\n", "Rice\r\n","Bodzio\r\n", "\r\n",
				// getString( boolean, Str... )
					//t014  , t015               , t016
					"ye\r\n", "ye\r\n", "yes\r\n", "\r\n",
				// getString( boolean, Str )
					// t017, t018       , t019                              , t020
					"\r\n", "Bodzio\r\n", "\r\n", "Bodzio\r\n", "Bogdan\r\n", "b\r\n", "B\r\n"
				// getString( Str, boolean )
					// t020
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
			testS = MS3.getString("info:", true, "yes", "no");
			if (allTest = oneOf(testS, allTest, "yes", "no"))
				System.out.println("no\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t003 one entry");
			// t003 Type only Enter ( autocomplete ) getSrting( all params)
			testS = MS3.getString("", (2 < 3), "yes", "no");
			if (allTest = oneOf(testS, allTest, "yes", "no"))
				System.out.println("yes or no\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t004 tree entry");
			// t004 Type Enter (wrong) retype ye (wrong) retype yes getSrting( all params)
			// DONT_FILL - required proper answer
			testS = MS3.getString(null, MS3.DONT_FILL, "yes", "no");
			if (allTest = oneOf(testS, allTest, "yes", "no"))
				System.out.println("yes\nPass");
			else
				System.out.println("\nFail   output = " + testS);
			
			// ----------- String, boolean, String
			
			System.out.println("Test t005 one entry");
			// t005 Type only Enter ( autocomplete ), one in array getSrting( Str, boll, Str)
			testS = MS3.getString("info: ", MS3.ALLOW_FILL, "yes");
			if (allTest = oneOf(testS, allTest, "yes", "no"))
				System.out.println("\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t006 one entry");
			// t006 Type yes Enter, one in array getSrting( Str, boll, Str)
			testS = MS3.getString("info", MS3.ALLOW_FILL, "yes");
			if (allTest = oneOf(testS, allTest, "yes"))
				System.out.println("\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t007 one entry");
			// t007 Type no Enter , one in array, allow different getSrting( Str, boll, Str)
			testS = MS3.getString("info", MS3.ALLOW_FILL, "yes");
			if (allTest = oneOf(testS, allTest, "no"))
				System.out.println("\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t008 two entry");
			// t008 Type no (wrong) retype yes Enter, one in array getSrting( Str, boll, Str)
			testS = MS3.getString("info", MS3.DONT_FILL, "yes");
			if (allTest = oneOf(testS, allTest, "yes"))
				System.out.println("\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t009 one entry");
			// t009 Type Enter, category getSrting( Str, boll, Str)
			testS = MS3.getString("info", MS3.ALLOW_FILL, MS3.STRINGS.ITEM);
			if (allTest = oneOf(testS, allTest, "Milk", "Eggs", "Bread", "Butter", "Sugar", "Sweets"))
				System.out.println("\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t010 two entry");
			// t010 Type eggs (wrong) retype Eggs Enter, category getSrting( Str, boll, Str)
			testS = MS3.getString("info", MS3.DONT_FILL, MS3.STRINGS.ITEM);
			if (allTest = oneOf(testS, allTest, "Milk", "Eggs", "Bread", "Butter", "Sugar", "Sweets"))
				System.out.println("\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t011 one entry");
			// t011 Type Rise Enter, category, can different getSrting( Str, boll, Str)
			testS = MS3.getString("info", MS3.ALLOW_FILL, MS3.STRINGS.ITEM);
			if (allTest = oneOf(testS, allTest, "Rice"))
				System.out.println("\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t012 one entry");
			// t012 Type Bodzio Enter, category getSrting( Str, boll, Str)
			testS = MS3.getString("info", MS3.ALLOW_FILL, MS3.STRINGS.FIRST_NAME);
			if (allTest = oneOf(testS, allTest, "Bodzio"))
				System.out.println("\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t013 one entry");
			// t013 Type Enter, category getSrting( Str, boll, Str)
			testS = MS3.getString("info", MS3.ALLOW_FILL, MS3.STRINGS.FIRST_NAME);
			if (allTest = oneOf(testS, allTest, "Bogdan", "Jon", "Lise", "Max", "Niels", "Patty", "Richard"))
				System.out.println("some name\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			// ----------- boolean, String...
			
			System.out.println("Test t014 one entry");
			// t014 Type ye Enter, examples getSrting( boll, Str... )
			testS = MS3.getString( MS3.ALLOW_FILL,  "yes", "no");
			if (allTest = oneOf(testS, allTest, "ye"))
				System.out.println("ye\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t015 two entry");
			// t015 Type ye(wrong) retype yes Enter, examples getSrting( boll, Str... )
			testS = MS3.getString( MS3.DONT_FILL,  "yes", "no");
			if (allTest = oneOf(testS, allTest, "yes"))
				System.out.println("yes\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t016 one entry");
			// t016 Type Enter, examples getSrting( boll, Str... )
			testS = MS3.getString( MS3.ALLOW_FILL,  "yes", "no");
			if (allTest = oneOf(testS, allTest, "yes", "no"))
				System.out.println("yes or no\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			// ----------- boolean, String
			
			System.out.println("Test t017 one entry");
			// t017 Type Enter, category getSrting( boll, Str)
			testS = MS3.getString(MS3.ALLOW_FILL, MS3.STRINGS.FIRST_NAME);
			if (allTest = oneOf(testS, allTest, "Bogdan", "Jon", "Lise", "Max", "Niels", "Patty", "Richard"))
				System.out.println("some name\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t018 one entry");
			// t018 Type Bodzio Enter, category getSrting( boll, Str)
			testS = MS3.getString(MS3.ALLOW_FILL, MS3.STRINGS.FIRST_NAME);
			if (allTest = oneOf(testS, allTest, "Bodzio"))
				System.out.println("Bodzio\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t019 tree entry");
			// t019 Type Enter(wrong) retype Bodzio, category getSrting( boll, Str)
			testS = MS3.getString(MS3.DONT_FILL, MS3.STRINGS.FIRST_NAME);
			if (allTest = oneOf(testS, allTest, "Bogdan"))
				System.out.println("Bogdan\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			System.out.println("Test t020 two entry");
			// t020 Type b(wrong) retype B Enter from B getSrting( boll, Str)
			testS = MS3.getString(MS3.DONT_FILL, "B");
			if (allTest = oneOf(testS, allTest, "B"))
				System.out.println("B\nPass");
			else
				System.out.println("\nFail   output = " + testS);

			// -----------  String, boolean


			
			
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
