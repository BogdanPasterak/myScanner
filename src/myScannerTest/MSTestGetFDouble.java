package myScannerTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import myScanner.MS;

public class MSTestGetFDouble {

	public static void main(String[] args) throws IOException {

		InputStream stdin = System.in;
		boolean allTest = true;

		String testS = "";
		double test;
		String[] answers = {
			// getFDouble( all params )
				// t01     , t02   	    , t03        , t04       , t05
				"7.12357\n", "7.12357\n", "7.12357\n", "7123.4\n", "7163.4\n",
				// t06,t07, t08 , t09 , t10            , t11     , t12
				"\n", "\n", "\n", "\n", "\n100\n1050\n", "1049\n", "1043\n",
			// getFDouble( info, fill, topLimit ) 
				// t09 , t10    , t11   , t12
				"1\r\n", "3\r\n", "\r\n", "3\r\n-1\r\n0\r\n"
		};
		
		// set answers for tests
		for (String ans : answers) {
			testS += ans;
		}
		
		System.setIn(new ByteArrayInputStream(testS.getBytes()));
		
		// getFDouble( all params )

		System.out.println("Test t01 one entry");
		// Type 7.12357 and Enter precysion full namber
		test = MS.getFDouble("Enter some number", 0, MS.ALLOW_FILL, 0 , 100);
		// result
		if (allTest = oneOf(test, allTest, 7))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t02 one entry");
		// Type 7.12357 and Enter precysion 2
		test = MS.getFDouble("Enter some number : ", 2, MS.ALLOW_FILL, 0 , 100);
		// result
		if (allTest = oneOf(test, allTest, 7.12))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t03 one entry");
		// Type 7.12357 and Enter precysion 3
		test = MS.getFDouble("Enter some number : ", 3, MS.ALLOW_FILL, 0 , 100);
		// result
		if (allTest = oneOf(test, allTest, 7.124))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t04 one entry");
		// Type 7123.4 and Enter precysion -2 round to handreds
		test = MS.getFDouble("Enter some number : ", -2, MS.ALLOW_FILL, 0 , 10000);
		// result
		if (allTest = oneOf(test, allTest, 7100))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t05 one entry");
		// Type 7163.4 and Enter precysion -2 round to handreds up
		test = MS.getFDouble("Enter some number : ", -2, MS.ALLOW_FILL, 0 , 10000);
		// result
		if (allTest = oneOf(test, allTest, 7200))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t06 one entry");
		// Type Enter (random) precysion  -2 round to handreds
		test = MS.getFDouble("Enter some number : ", -2, MS.ALLOW_FILL, 1000 , 10000);
		// result
		if (allTest = oneOf(test, allTest, 1000, 10000, -2))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t07 one entry");
		// Type Enter (random) precysion  over range
		test = MS.getFDouble("Enter some number : ", -10, MS.ALLOW_FILL, 1000 , 10000);
		// result
		if (allTest = oneOf(test, allTest, 1000, 10000, 0))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t08 one entry");
		// Type Enter (random) precysion  over to , ignore precision
		test = MS.getFDouble("Enter some number : ", -6, MS.ALLOW_FILL, 1000 , 10000);
		// result
		if (allTest = oneOf(test, allTest, 1000, 10000))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t09 one entry");
		// Type Enter (random) precysion  -1 , wrong range
		test = MS.getFDouble("Enter some number : ", -1, MS.ALLOW_FILL , 10000, 1000);
		// result
		if (allTest = oneOf(test, allTest, 1000, 10000, -1))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t10 three entry");
		// Type Enter (random) reentry 100 reentry 1050 precysion  -1 
		test = MS.getFDouble("Enter some number : ", -1, MS.DONT_FILL , 1000, 10000);
		// result
		if (allTest = oneOf(test, allTest, 1000, 10000, -1))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t11 one entry");
		// Type  1049 precysion  -1 
		test = MS.getFDouble("Enter some number : ", -1, MS.DONT_FILL , 1000, 10000);
		// result
		if (allTest = oneOf(test, allTest, 1050))
			System.out.println(test + "\nPass");
		else
			System.out.println("\nFail   output = " + test);

		System.out.println("Test t12 one entry");
		// Type  1043 precysion  -1 
		test = MS.getFDouble("Enter some number : ", -1, MS.DONT_FILL , 1000, 10000);
		// result
		if (allTest = oneOf(test, allTest, 1040))
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

	public static boolean oneOf(double toTest, boolean before, double from, double to, int accuracy ) {
		
		if (!before)
			return false;
		double pow = Math.pow(10, accuracy);
		double toTestRound = Math.round((toTest * pow)) / pow;
		if(toTest >= from && toTest <= to && toTest - toTestRound == 0)
			return true;
		
		return false;
	}

	
}
