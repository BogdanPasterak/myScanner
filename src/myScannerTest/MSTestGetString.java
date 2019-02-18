
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import myScanner.MS;

public class MSTestGetString {

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
				//t014  , t015               , t016  , t017 ,
				"ye\r\n", "ye\r\n", "yes\r\n", "\r\n", "\r\n", 
			// getString( boolean, Str )
				// t018     , t019                              , t020
				"Bodzio\r\n", "\r\n", "Bodzio\r\n", "Bogdan\r\n", "b\r\n", "B\r\n",
				// getString( Str, boolean )
				// t021, t022 , t023   , t024           ,
				"\r\n", "\r\n", "Z\r\n", "\r\n", "Z\r\n",
			// getString( Str, Str )
				// t025 , t026   , t027   , t028                    , t029
				"No\r\n", "c\r\n", "z\r\n", "\r\n", "a\r\n", "y\r\n", "c\r\n", "y\r\n",
			// getString( Str... ) / getString( Str )
				// t030, t031  , t032  , t033
				"\r\n", "C\r\n", "\r\n", "C\r\n",
			// getString( boolean ) / getString( )
				// t034        , t035  , t036   , t037  , t038 
				"\r\n", "C\r\n", "\r\n", "C\r\n", "\r\n", "C\r\n",
			// getString accepted empty string (exeption)
				// t039, t040       , t041  , t042
				"\r\n", "Bogdan\r\n", "\r\n", "Bogdan\r\n"
		};
		
		// set answers for tests
		for (String ans : answers) {
			testS += ans;
		}
		
		System.setIn(new ByteArrayInputStream(testS.getBytes()));

		System.out.println("Test t001 one entry");
		// t001 Type yes and Enter getSrting( all params)
		testS = MS.getString("info", MS.ALLOW_FILL, "yes", "no");
		// result
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("yes\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t002 one entry");
		// t002 Type no and Enter getSrting( all params) 
		testS = MS.getString("info:", true, "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("no\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t003 one entry");
		// t003 Type only Enter ( autocomplete ) getSrting( all params)
		testS = MS.getString("", (2 < 3), "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t004 three entry");
		// t004 Type Enter (wrong) retype ye (wrong) retype yes getSrting( all params)
		// DONT_FILL - required proper answer
		testS = MS.getString(null, MS.DONT_FILL, "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println("yes\nPass");
		else
			System.out.println("\nFail   output = " + testS);
		
		// ----------- String, boolean, String
		
		System.out.println("Test t005 one entry");
		// t005 Type only Enter ( autocomplete ), one in array getSrting( Str, boll, Str)
		testS = MS.getString("info: ", MS.ALLOW_FILL, "yes");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t006 one entry");
		// t006 Type yes Enter, one in array getSrting( Str, boll, Str)
		testS = MS.getString("info", MS.ALLOW_FILL, "yes");
		if (allTest = oneOf(testS, allTest, "yes"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t007 one entry");
		// t007 Type no Enter , one in array, allow different getSrting( Str, boll, Str)
		testS = MS.getString("info", MS.ALLOW_FILL, "yes");
		if (allTest = oneOf(testS, allTest, "no"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t008 two entry");
		// t008 Type no (wrong) retype yes Enter, one in array getSrting( Str, boll, Str)
		testS = MS.getString("info", MS.DONT_FILL, "yes");
		if (allTest = oneOf(testS, allTest, "yes"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t009 one entry");
		// t009 Type Enter, category getSrting( Str, boll, Str)
		testS = MS.getString("info", MS.ALLOW_FILL, MS.STRINGS.ITEM);
		if (allTest = oneOf(testS, allTest, "Milk", "Eggs", "Bread", "Butter", "Sugar", "Sweets"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t010 two entry");
		// t010 Type eggs (wrong) retype Eggs Enter, category getSrting( Str, boll, Str)
		testS = MS.getString("info", MS.DONT_FILL, MS.STRINGS.ITEM);
		if (allTest = oneOf(testS, allTest, "Milk", "Eggs", "Bread", "Butter", "Sugar", "Sweets"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t011 one entry");
		// t011 Type Rise Enter, category, can different getSrting( Str, boll, Str)
		testS = MS.getString("info", MS.ALLOW_FILL, MS.STRINGS.ITEM);
		if (allTest = oneOf(testS, allTest, "Rice"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t012 one entry");
		// t012 Type Bodzio Enter, category getSrting( Str, boll, Str)
		testS = MS.getString("info", MS.ALLOW_FILL, MS.STRINGS.FIRST_NAME);
		if (allTest = oneOf(testS, allTest, "Bodzio"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t013 one entry");
		// t013 Type Enter, category getSrting( Str, boll, Str)
		testS = MS.getString("info", MS.ALLOW_FILL, MS.STRINGS.FIRST_NAME);
		if (allTest = oneOf(testS, allTest, "Bogdan", "Jon", "Lise", "Max", "Niels", "Patty", "Richard"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		// ----------- boolean, String...
		
		System.out.println("Test t014 one entry");
		// t014 Type ye Enter, examples getSrting( boll, Str... )
		testS = MS.getString( MS.ALLOW_FILL,  "yes", "no");
		if (allTest = oneOf(testS, allTest, "ye"))
			System.out.println("ye\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t015 two entry");
		// t015 Type ye(wrong) retype yes Enter, examples getSrting( boll, Str... )
		testS = MS.getString( MS.DONT_FILL,  "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes"))
			System.out.println("yes\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t016 one entry");
		// t016 Type Enter, examples getSrting( boll, Str... )
		testS = MS.getString( MS.ALLOW_FILL,  "yes", "no");
		if (allTest = oneOf(testS, allTest, "yes", "no"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		// ----------- boolean, String
		
		System.out.println("Test t017 one entry");
		// t017 Type Enter, category getSrting( boll, Str)
		testS = MS.getString(MS.ALLOW_FILL, MS.STRINGS.FIRST_NAME);
		if (allTest = oneOf(testS, allTest, "Bogdan", "Jon", "Lise", "Max", "Niels", "Patty", "Richard"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t018 one entry");
		// t018 Type Bodzio Enter, category getSrting( boll, Str)
		testS = MS.getString(MS.ALLOW_FILL, MS.STRINGS.FIRST_NAME);
		if (allTest = oneOf(testS, allTest, "Bodzio"))
			System.out.println("Bodzio\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t019 three entry");
		// t019 Type Enter(wrong) retype Bodzio, category getSrting( boll, Str)
		testS = MS.getString(MS.DONT_FILL, MS.STRINGS.FIRST_NAME);
		if (allTest = oneOf(testS, allTest, "Bogdan"))
			System.out.println("Bogdan\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t020 two entry");
		// t020 Type b(wrong) retype B Enter from B getSrting( boll, Str)
		testS = MS.getString(MS.DONT_FILL, "B");
		if (allTest = oneOf(testS, allTest, "B"))
			System.out.println("B\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		// -----------  String, boolean

		System.out.println("Test t021 one entry");
		// t021 Type Enter random form all getSrting( Str, boll)
		testS = MS.getString("", MS.ALLOW_FILL);
		if (allTest = oneOf(testS, allTest, testS))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t022 one entry");
		// t022 Type Enter random form all getSrting( Str, boll)
		testS = MS.getString("Type something", MS.ALLOW_FILL);
		if (allTest = oneOf(testS, allTest, testS))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t023 one entry");
		// t023 Type Z Enter random form all getSrting( Str, boll)
		testS = MS.getString("Type something", MS.ALLOW_FILL);
		if (allTest = oneOf(testS, allTest, "Z"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t024 two entry");
		// t024 Type Enter (wrong) retype Z Enter random form all getSrting( Str, boll)
		testS = MS.getString("Type something", MS.DONT_FILL);
		if (allTest = oneOf(testS, allTest, "Z"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		// -----------  String, String
		
		System.out.println("Test t025 one entry");
		// t025 Type No Enter form category  getSrting( Str, Str)
		testS = MS.getString("Type Yes, No or Cancel", MS.STRINGS.YES_NO_CANCEL);
		if (allTest = oneOf(testS, allTest, "No"))
			System.out.println(testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t026 one entry");
		// t026 Type c Enter form category  getSrting( Str, Str)
		testS = MS.getString("Type Yes, No or Cancel", MS.STRINGS.YES_NO_CANCEL);
		if (allTest = oneOf(testS, allTest, "Cancel"))
			System.out.println("c  !! change to " + testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t027 one entry");
		// t027 Type z Enter form category  getSrting( Str, Str)
		testS = MS.getString("Type Yes, No or Cancel", MS.STRINGS.YES_NO_CANCEL);
		if (allTest = oneOf(testS, allTest, "z"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t028 three entry");
		// t028 Type Enter, a, y  form category  getSrting( Str, Str)
		testS = MS.getString("Type Yes, No or Cancel", MS.DONT_FILL, MS.STRINGS.YES_NO_CANCEL);
		if (allTest = oneOf(testS, allTest, "Yes"))
			System.out.println( "y  !! change to " + testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t029 two entry");
		// t029 Type c, y  form category  getSrting( Str, Str)
		testS = MS.getString("Type Yes or No", MS.DONT_FILL, MS.STRINGS.YES_NO);
		if (allTest = oneOf(testS, allTest, "Yes"))
			System.out.println( "y  !! change to " + testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		// -----------  String... and String

		System.out.println("Test t030 two entry");
		// t030 Type Enter form category  getSrting( Str...)
		testS = MS.getString("Type A or B", "A", "B");
		if (allTest = oneOf(testS, allTest, "A", "B"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t031 one entry");
		// t031 Type C Enter form category  getSrting( Str...)
		testS = MS.getString("Type A or B", "A", "B");
		if (allTest = oneOf(testS, allTest, "C"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t032 one entry");
		// t032 Type Enter getSrting( Str)
		testS = MS.getString("Type A or B");
		if (allTest = oneOf(testS, allTest, testS))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t033 one entry");
		// t033 Type C Enter getSrting( Str)
		testS = MS.getString("Type A or B");
		if (allTest = oneOf(testS, allTest, "C"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		// -----------  bollean and empty
		
		System.out.println("Test t034 two entry");
		// t034 Type Enter, retype C Enter getSrting( boolean)
		testS = MS.getString(MS.DONT_FILL);
		if (allTest = oneOf(testS, allTest, "C"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t035 one entry");
		// t035 Type Enter getSrting( boolean)
		testS = MS.getString(MS.ALLOW_FILL);
		if (allTest = oneOf(testS, allTest, testS))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t036 one entry");
		// t036 Type C Enter getSrting( boolean)
		testS = MS.getString(MS.ALLOW_FILL);
		if (allTest = oneOf(testS, allTest, "C"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t037 one entry");
		// t037 Type Enter getSrting( )
		testS = MS.getString( );
		if (allTest = oneOf(testS, allTest, testS))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t038 one entry");
		// t038 Type C Enter getSrting( )
		testS = MS.getString( );
		if (allTest = oneOf(testS, allTest, "C"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		// ------------ accepted empty String (exception) paramiters (false and "")

		System.out.println("Test t039 one entry");
		// t039 Type Enter getSrting( )
		testS = MS.getString("Type Enter", MS.DONT_FILL , "" );
		if (allTest = oneOf(testS, allTest, ""))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t040 one entry");
		// t040 Type Bogdan Enter getSrting( )
		testS = MS.getString("Type Enter", MS.DONT_FILL , "" );
		if (allTest = oneOf(testS, allTest, "Bogdan"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t041 one entry");
		// t041 Type Enter getSrting( )
		testS = MS.getString( MS.DONT_FILL , "" );
		if (allTest = oneOf(testS, allTest, ""))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		System.out.println("Test t042 one entry");
		// t042 Type Bogdan Enter getSrting( )
		testS = MS.getString( MS.DONT_FILL , "" );
		if (allTest = oneOf(testS, allTest, "Bogdan"))
			System.out.println( testS + "\nPass");
		else
			System.out.println("\nFail   output = " + testS);

		
		
		// back original setting
		System.setIn(stdin);
		System.out.println("All test = " + allTest);

	}

	
	public static boolean oneOf(String toTest, boolean before, String... strings ) {
		
		if (!before)
			return false;
		
		for (String s : strings) {
			if (toTest.equals(s))
				return true;
		}
		
		return false;
	}

}