package quickTest;

import myScanner.MyScanner2;

public class QuickTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		String s = MyScanner2.getString("", MyScanner2.STRINGS.SURNAME);
		System.out.println("Answer = _" + s + "_");
		s= MyScanner2.getString(true, "this", "or this", "also this");
		System.out.println("Answer = _" + s + "_");
		
		*/
		System.out.println(MyScanner2.getDouble(1,2.2));
		
	}
	/*
	"this", "or this", "also this",
	MyScanner.CAN_BE_EMPTY,
	"Abecadlo",
	MyScanner.CHARS.ALL_DIGITS,
	MyScanner.CHARS.WITH_WHITE_SIGNS,
	MyScanner.CHARS.FROM_a_TO_z);
	*/

}
