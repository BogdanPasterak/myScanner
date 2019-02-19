package myScannerTest;

import myScanner.MS;

public class QuickTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (String string : MS.STRINGS.getStringsFromAllCategories()) {
			System.out.println(string);
		}
	}

}