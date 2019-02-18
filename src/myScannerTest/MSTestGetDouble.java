
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import myScanner.MS;

public class MSTestGetDouble {

	public static void main(String[] args) throws IOException {

		InputStream stdin = System.in;
		boolean allTest = true;

		String testS = "";
		double test;
		String[] answers = {
			// getDouble( all params )
				// t01   , t02   	  , t03    , t04     , t05             , t06           , t07         , t08
				"1.1\r\n", "a\r\n\r\n", "\r\n", "6.1\r\n", "a\r\n\r\n5\r\n", "-9\r\n-8\r\n", "9\r\n8\r\n", "5\r\n",
			// getDouble( info, fill, topLimit ) 
				// t09 , t10    , t11   , t12
				"1\r\n", "3\r\n", "\r\n", "3\r\n-1\r\n0\r\n",
			// getDouble( info, fill ) and               getDouble( info ) and  getDouble()
				// t13   , t14   , t15                , t16      , t17   , t16      , t19
				"101\r\n", "\r\n", "\r\n101\r\n50\r\n", "101\r\n", "\r\n", "101\r\n", "\r\n",
			// getDouble( fill, from, to ) and               getDouble( info ) and  getDouble()
				// t20, t21     , t22               , t23   , t24     , t25
				"\r\n", "88\r\n", "\r\n88\r\n77\r\n", "\r\n", "88\r\n", "\r\n-1\r\n88\r\n77\r\n",
			// getDouble( fill ) and                           getDouble()
				// t26, t27      , t28                      , t29   , t30
				"\r\n", "120\r\n", "\r\n-1\r\n101\r\n77\r\n", "\r\n", "120\r\n"
		};
		
		// set answers for tests
		for (String ans : answers) {
			testS += ans;
		}
		
		System.setIn(new ByteArrayInputStream(testS.getBytes()));
		
		// getDouble( all params )

		System.out.println("Test t01 one entry");
		// Type 1.1 and Enter getDouble( all params )
		test = MS.getDouble("Enter some number : ", MS.ALLOW_FILL, 0 , 100);
		// result
		if (allTest = oneOf(test, allTest, 1.1))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t02 two entry");
		// Type a retype Enter getDouble( all params )
		test = MS.getDouble("Enter some number : ", MS.ALLOW_FILL, 0 , 100);
		// result
		if (allTest = oneOf(test, allTest, 0, 100))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);
		
		System.out.println("Test t03 one entry");
		// Type Enter getDouble( all params ) random only 5
		test = MS.getDouble("Enter number 5.3", MS.ALLOW_FILL, 5.3 , 5.3);
		// result
		if (allTest = oneOf(test, allTest, 5.3))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t04 one entry");
		// Type 6.1 Enter getDouble( all params ) allows other
		test = MS.getDouble("", MS.ALLOW_FILL, 5 , 5);
		// result
		if (allTest = oneOf(test, allTest, 6.1))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t05 three entry");
		// Type a retype Enter retype 5 getDouble( all params ) 
		test = MS.getDouble("", MS.DONT_FILL, 2 , 8);
		// result
		if (allTest = oneOf(test, allTest, 5))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t06 two entry");
		// Type -9 retype -8 getDouble( all params ) 
		test = MS.getDouble("Lower range", MS.DONT_FILL, -8 , -2);
		// result
		if (allTest = oneOf(test, allTest, -8))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t07 two entry");
		// Type 9 retype 8 getDouble( all params ) 
		test = MS.getDouble("Upper range:", MS.DONT_FILL, 2 , 8);
		// result
		if (allTest = oneOf(test, allTest, 8))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t08 one entry");
		// Type 5 getDouble( all params ) bad range
		test = MS.getDouble("Bad range:", MS.DONT_FILL, 8 , 2);
		// result
		if (allTest = oneOf(test, allTest, 5))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		// getDouble( info, fill, topLimit )

		System.out.println("Test t09 one entry");
		// Type 1  
		test = MS.getDouble("Type 0 or 1 or 2:", MS.ALLOW_FILL, 2);
		// result
		if (allTest = oneOf(test, allTest, 1))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t10 one entry");
		// Type 3  
		test = MS.getDouble("Type 0 or 1 or 2", MS.ALLOW_FILL, 2);
		// result
		if (allTest = oneOf(test, allTest, 3))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t11 one entry");
		// Type Enter  
		test = MS.getDouble("Type 0 or 1 or 2", MS.ALLOW_FILL, 2);
		// result
		if (allTest = oneOf(test, allTest, 0, 2))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t12 three entry");
		// Type 3  retype -1 retype 0
		test = MS.getDouble("Type 0 or 1 or 2: ", MS.DONT_FILL, 2);
		// result
		if (allTest = oneOf(test, allTest, 0))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		// getDouble( info, fill )

		System.out.println("Test t13 one entry");
		// Type 101
		test = MS.getDouble("Type Number: ", MS.ALLOW_FILL);
		// result
		if (allTest = oneOf(test, allTest, 101))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t14 one entry");
		// Type Enter
		test = MS.getDouble("Type Enter: ", MS.ALLOW_FILL);
		// result
		if (allTest = oneOf(test, allTest, 0, 100))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t15 three entry");
		// Type Enter, 101, 50
		test = MS.getDouble("Type Enter: ", MS.DONT_FILL);
		// result
		if (allTest = oneOf(test, allTest, 50))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		// getDouble( info )

		System.out.println("Test t16 one entry");
		// Type 101
		test = MS.getDouble("Type Number: ");
		// result
		if (allTest = oneOf(test, allTest, 101))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t17 one entry");
		// Type Enter
		test = MS.getDouble("Type Enter: ");
		// result
		if (allTest = oneOf(test, allTest, 0, 100))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		// getDouble( info )

		System.out.println("Test t18 one entry");
		// Type 101
		test = MS.getDouble("Type Number: ");
		// result
		if (allTest = oneOf(test, allTest, 101))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t19 one entry");
		// Type Enter
		test = MS.getDouble("Type Enter: ");
		// result
		if (allTest = oneOf(test, allTest, 0, 100))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		// getDouble( fill, from, to )
		
		System.out.println("Test t20 one entry");
		// Type Enter
		test = MS.getDouble( MS.ALLOW_FILL, 77, 77);
		// result
		if (allTest = oneOf(test, allTest, 77))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t21 one entry");
		// Type 88
		test = MS.getDouble( MS.ALLOW_FILL, 77, 77);
		// result
		if (allTest = oneOf(test, allTest, 88))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t22 three entry");
		// Type Enter , 88, 77
		test = MS.getDouble( MS.DONT_FILL, 77, 77);
		// result
		if (allTest = oneOf(test, allTest, 77))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		// getDouble( fill, to )

		System.out.println("Test t23 one entry");
		// Type Enter
		test = MS.getDouble( MS.ALLOW_FILL, 77);
		// result
		if (allTest = oneOf(test, allTest, 0, 77))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t24 one entry");
		// Type 88
		test = MS.getDouble( MS.ALLOW_FILL, 77);
		// result
		if (allTest = oneOf(test, allTest, 88))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t25 for entry");
		// Type Enter -1 , 88, 77
		test = MS.getDouble( MS.DONT_FILL, 77);
		// result
		if (allTest = oneOf(test, allTest, 77))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		// getDouble( fill )

		System.out.println("Test t26 one entry");
		// Type Enter
		test = MS.getDouble( MS.ALLOW_FILL);
		// result
		if (allTest = oneOf(test, allTest, 0, 100))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t27 one entry");
		// Type 88
		test = MS.getDouble( MS.ALLOW_FILL);
		// result
		if (allTest = oneOf(test, allTest, 120))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t28 for entry");
		// Type Enter , -1, 101, 77
		test = MS.getDouble( MS.DONT_FILL);
		// result
		if (allTest = oneOf(test, allTest, 77))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		// getDouble( )

		System.out.println("Test t29 one entry");
		// Type Enter
		test = MS.getDouble();
		// result
		if (allTest = oneOf(test, allTest, 0, 100))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t30 one entry");
		// Type 88
		test = MS.getDouble();
		// result
		if (allTest = oneOf(test, allTest, 120))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);
		
		// back original setting
		System.setIn(stdin);
		System.out.println("All test = " + allTest);

	}

	
	public static boolean oneOf(double toTest, boolean before, double expect ) {
		
		if (!before)
			return false;
		
		return expect == toTest;
	}
	public static boolean oneOf(double toTest, boolean before, double from, double to ) {
		
		if (!before)
			return false;
		else if(toTest >= from && toTest <= to)
			return true;
		
		return false;
	}

}
