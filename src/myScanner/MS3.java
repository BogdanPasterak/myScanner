package myScanner;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.lang.reflect.Field;

/**
 * Wrapper Class to Scanner.
 * 
 * - Opens the channel once
 * - Class supports int, double, char and String type
 * - It displays information about the data needed
 * - The class has predefined STRINGS and CHARS types to facilitate random data
 * - Int and double data can be limited by the range
 * - Class does not require creating an object (we use like Math)
 * - When the data is bad, he asks for the data to be re-entered
 * - CHARS class allows you to add your own set of characters
 * - The STRINGS class allows you to add your own set of words
 * - You can add your own predefined values to the STRINGS class (info in the comment)
 *
 * @author Bogdan Pasterak
 * @version 2.0
 * @since 21 Sep 2018
 */

// do double dodac zaokraglenie !!!!


public class MS3 {

	private static Random rand;
	private static Scanner sc;

	private static String takenS;
	private static int takenI;
	private static double takenD;
	private static char takenC;

	// boolean
	
	public static final boolean ALLOW_FILL = true;
	public static final boolean DONT_FILL = false;
	
	public static final boolean NOT_EMPTY = true;
	public static final boolean CAN_BE_EMPTY = false;
	public static final boolean CAN_NOT_BE_ZERO = false;
	public static final boolean NOT_AUTO_FILL = false;
	public static final boolean CAN_BE_ZERO = true;
	
	// if scanner object does not exist yet
	private static void isScanner() {
		if (sc == null) {
			sc = new Scanner(System.in);
			rand = new Random();
		}
	}

	//--------------------------------------------------------------
	// input line full parameters
	public static String getString(String info, boolean fill, String... examples) {
		
		String answer;

		// if first time use create new Scanner and Random
		isScanner();

		// validate info
		if (info == null || info.length() == 0)
			info = "Type line: ";
		else if (info.endsWith(":"))
			info += " ";
		else if (!info.endsWith(": "))
			info += ": ";

		// repeat until you get the correct answer
		do {
			System.out.print(info);
			answer = sc.nextLine();
			
			// user type something
			if (answer.length() > 0 ) {
				// correct Yes / No / Cancel answer
				if ( examples.length == 1)
					answer = STRINGS.validateYesNoCancel(answer, examples[0]);
				// if can fill or no example to validate just answer
				if ( fill || examples.length == 0 )
					return answer;
				// if don't fill must match patterns of category
				if (examples.length == 1)
					for (String example : STRINGS.getCategoryArray(examples[0]))
						if (example.equals(answer))
							return answer;
				// or examples
				for (String example : examples) 
					if (example.equals(answer))
						return answer;
				// if not match, try again
			// user type Enter, draws the answer
			} else 
				// if can draw
				if ( fill )
					// no example , draw from all
					if (examples == null || examples.length == 0)
						return STRINGS.getStringFromAll();
					// category or singel example
					else if (examples.length == 1)
						return STRINGS.getStringFromCategory(examples[0]);
					// draws one from examples
					else
						return STRINGS.getStringCustom(examples);
				// if can't draw, try again
		
			System.out.println("Improper text, try again.");				

		} while( true );
		
	}
	
	public static String getString(String info, boolean fill, String category) {
		
		return getString(info, fill, new String[] {category} );
	}
	
	public static String getString(boolean fill, String... examples) {
		
		return getString(null, fill, examples);
	}

	public static String getString(boolean fill, String category) {
		
		return getString(null, fill, new String[] {category} );
	}
	
	public static String getString(String info, boolean fill) {
		
		return getString(info, fill, new String[] {} );
	}
	
	public static String getString(String info, String category) {
		
		return getString(info, ALLOW_FILL, new String[] {category} );
	}
	
	public static String getString(String... infoAndExamples) {
		
		return getString(infoAndExamples[0], ALLOW_FILL, Arrays.copyOfRange(infoAndExamples, 1, infoAndExamples.length));
	}
	
	public static String getString(String info) {
		
		return getString(info, ALLOW_FILL, new String[] {} );
	}

	public static String getString(boolean fill) {
		
		return getString(null, fill, new String[] {} );
	}
	
	public static String getString() {
		
		return getString(null, ALLOW_FILL, new String[] {} );
	}
	

	//--------------------------------------------------------------
	// input character full parameters
	
	public static char getChar(String info, boolean fill, String... restricts) {
		boolean ok = false;
		String allowed = "";

		// if first time use create new Scanner and Random
		isScanner();

		// validate info
		if (info == null || info.length() == 0)
			info = "Type character: ";
		else if (info.endsWith(":"))
			info += " ";
		else if (!info.endsWith(": "))
			info += ": ";

		// collect the restrictions together
//		if (restricts.length == 0)
//			allowed = CHARS.collectRestricts(new String[] {"" , CHARS.ALL_CHAR});
//		else if (restricts.length == 1)
//			allowed = CHARS.collectRestricts(new String[] {"" , restricts[0]});
//		else 
			allowed = CHARS.collectRestricts(restricts);


		// check whether it is allowed
//		for (int i = 0; i < restricts.length; i++) {
//			if (CHARS.isRestrict(restricts[i])) {
//				// System.out.println("RESTRICT ! " + restricts[i]);
//				allowed = CHARS.addAllowedRestrict(allowed, restricts[i]);
//			} else {
//				// System.out.println("CUSTOM !! " + restricts[i]);
//				allowed = CHARS.addAllowed(allowed, restricts[i]);
//			}
//			//System.out.println(allowed);
//		}

		do {
			System.out.print(info);
			takenS = sc.nextLine();

			if (takenS.length() == 0) {
				// if ENTER
				if (fill) {
					System.out.println("Try again, type some character");
				} else {
					// random of allowed
					takenC = allowed.charAt(rand.nextInt(allowed.length()));
					ok = true;
				}
			} else if ( allowed.contains(takenS.substring(0, 1)) ) {
				// matched
				takenC = takenS.charAt(0);
				ok = true;
			} else {
				// not matched
					System.out.println("Out of range, acceptable characters :");
					// writing out permissible characters in rows after 16
					for (int i = 0; i < allowed.length(); i++) {
						System.out.print(allowed.substring(i, i+1) + 
								((i == allowed.length() - 1) ? ".\n" : 
									((i % 16 == 15) ? "  and\n" : ", ")));
					}
			} 

		} while (!ok);

		return takenC;
	}

	
	// nowa wersja

	//--------------------------------------------------------------
	// input int (if enter set 0)
	public static int getInt() {

		return getInt("", CAN_BE_ZERO);
	}

	public static int getInt(boolean canBeZero) {

		return getInt("", canBeZero);
	}

	public static int getInt(String info) {

		return getInt(info, CAN_BE_ZERO);
	}

	public static int getInt(String info, boolean canBeZero) {
		boolean ok = true;

		isScanner();

		if (info.length() == 0)
			info = "Type \"int\" : ";
		else if (info.endsWith(":"))
			info += " ";
		else if (!info.endsWith(": "))
			info += " : ";

		// until the format is OK
		do {
			// do not write the first time
			if (!ok)
				System.out.println("Try again, it was not \"int\" type");

			System.out.print(info);
			takenS = sc.nextLine();
			try {
				takenI = Integer.parseInt(takenS);
				ok = true;
			} catch (Exception e) {
				if (canBeZero && takenS.equals("")) {
					takenI = rand.nextInt(100);
					//System.out.println(takenI);
					ok = true;
				} else
					ok = false;
			}
		} while (!ok);

		return takenI;
	}

	// range to int
	public static int getInt(int from, int to) {

		return getInt(from, to, CAN_BE_ZERO);
	}

	public static int getInt(int from, int to, boolean canBeZero) {
		String info = "Type \"int\" from " + from + " to " + to + " : ";

		return getInt(info, from, to, canBeZero);
	}

	public static int getInt(String info, int from, int to, boolean canBeZero) {
		boolean repeat = false;
		
		if (from > to)
			throw new IllegalArgumentException("FROM it is bigger than TO");

		do {
			if (repeat)
				System.out.println("Out of range, try again");
			else
				repeat = true;
			getInt(info, canBeZero);
			// if tekenS is empty random number from range!
			if (takenS.equals("")) {
				takenI = rand.nextInt(to - from + 1) + from;
				//System.out.println(takenI);
			}
		} while (takenI < from || takenI > to);

		return takenI;
	}

	public static int getInt(int from_0_to) {

		return getInt(0, from_0_to, CAN_BE_ZERO);
	}

	public static int getInt(int from_0_to, boolean canBeZero) {

		return getInt(0, from_0_to, canBeZero);
	}

	public static int getInt(String info, int from_0_to) {

		return getInt(info, 0, from_0_to, CAN_BE_ZERO);
	}

	public static int getInt(String info, int from_0_to, boolean canBeZero) {

		return getInt(info, 0, from_0_to, canBeZero);
	}

	public static int getInt(String info, int from, int to) {

		return getInt(info, from, to, CAN_BE_ZERO);
	}

	//--------------------------------------------------------------
	// input double
	public static double getDouble() {

		return getDouble("Type \"double\" : ", CAN_BE_ZERO);
	}

	public static double getDouble(boolean canBeZero) {

		return getDouble("Type \"double\" : ", canBeZero);
	}

	public static double getDouble(String info) {

		return getDouble(info, CAN_BE_ZERO);
	}

	public static double getDouble(String info, boolean canBeZero) {
		boolean ok = true;

		isScanner();

		if (info.length() == 0)
			info = "Type \"double\" : ";
		else if (info.endsWith(":"))
			info += " ";
		else if (!info.endsWith(": "))
			info += " : ";

		// until the format is OK
		do {
			// do not write the first time
			if (!ok)
				System.out.println("Try again, it was not \"double\" type");

			System.out.print(info);
			takenS = sc.nextLine();
			try {
				takenD = Double.parseDouble(takenS);
				ok = true;
			} catch (Exception e) {
				if (canBeZero && takenS.equals("")) {
					takenD = rand.nextDouble() * 100;
					//System.out.println(takenD);
					ok = true;
				} else
					ok = false;
			}
		} while (!ok);

		return takenD;
	}

	public static double getDouble(double from_0_to) {

		return getDouble( 0.0, from_0_to, CAN_BE_ZERO);
	}

	public static double getDouble(double from_0_to, boolean canBeZero) {

		return getDouble( 0.0, from_0_to, canBeZero);
	}

	public static double getDouble(String info, double from_0_to) {

		return getDouble(info, 0.0, from_0_to, CAN_BE_ZERO);
	}

	public static double getDouble(String info, double from_0_to, boolean canBeZero) {

		return getDouble(info, 0.0, from_0_to, canBeZero);
	}

	public static double getDouble(double from, double to) {

		return getDouble( from, to, CAN_BE_ZERO);
	}

	public static double getDouble(String info, double from, double to) {

		return getDouble(info, from, to, CAN_BE_ZERO);
	}

	public static double getDouble(double from, double to, boolean canBeZero) {
		String info = "Type \"double\" from " + from + " to " + to + " : ";

		return getDouble(info, from, to, canBeZero);
	}

	public static double getDouble(String info, double from, double to, boolean canBeZero) {
		boolean repeat = false;

		do {
			if (repeat)
				System.out.println("Out of range, try again");
			else
				repeat = true;
			getDouble(info, canBeZero);
			// random number
			if (takenS.equals("")) {
				takenD = rand.nextDouble() * (to - from) + from;
				//System.out.println(takenD);
			}
		} while (takenD < from || takenD > to);

		return takenD;
	}

	//--------------------------------------------------------------
	// input character
	public static char getChar() {

		return getChar("", CAN_BE_EMPTY);
	}

	public static char getChar(String info) {

		return getChar(info, CAN_BE_EMPTY);
	}

	public static char getChar(boolean notEmpty) {

		return getChar("", notEmpty);
	}

	public static char getChar(boolean notEmpty, String...restricts ) {

		return getChar("", notEmpty, restricts);
	}

	public static char getChar(String info, boolean notEmpty) {

		return getChar("", notEmpty, CHARS.ALL_CHAR);
	}

	public static char getChar(String... infoAndRestricts) {

		String[] restricts = new String[infoAndRestricts.length - 1];

		for (int i = 0; i < restricts.length; i++) {
			restricts[i] = infoAndRestricts[i + 1];
		}

		return getChar(infoAndRestricts[0], CAN_BE_EMPTY, restricts);
	}

	
	public static final class STRINGS {
		// the class draws (with empty string) a string from the selected category
		// *** YOU OWN VALUES ***
		// to add categories, create a public string with the category name
		// and private string array with this name

		// First name
		public static final String FIRST_NAME = "first_name";
	    @SuppressWarnings("unused")
		private static final String[] first_name = {"Bogdan", "Jon", "Lise", "Max", "Niels", "Patty", "Richard"};

	    // Surname
	    public static final String SURNAME = "surname";
		@SuppressWarnings("unused")
		private static final String[] surname = {"Pasterak", "Doe", "Curie", "Planck", "Bohr", "Wotson"};
		
		// Full name
		public static final String NAME = "name";
		@SuppressWarnings("unused")
		private static final String[] name = {"Bogdan Pasterak", "Jon Doe", "Jacqueline K. Barton", "Gertrude B. Elion", "Enrico Fermi", "Frieda Robscheit-Robbins"};
		
		// Department
		public static final String DEPARTMENT = "department";
		@SuppressWarnings("unused")
		private static final String[] department = {"Architecture", "Economics", "Geosciences", "IT", "Transportation", "Music"};

		// Item
		public static final String ITEM = "item";
		@SuppressWarnings("unused")
		private static final String[] item = {"Milk", "Eggs", "Bread", "Butter", "Sugar", "Sweets"};

		// Yes / No
		public static final String YES_NO = "yes_no";
		private static final String[] yes_no = {"Yes", "No", "yes", "no", "YES", "NO", "y", "n", "Y", "N"};

		// Yes / No / Cancel
		public static final String YES_NO_CANCEL = "yes_no_cancel";
		private static final String[] yes_no_cancel = {"Yes", "No", "Cancel", "yes", "no", "cancel", "YES", "NO", "CANCEL", "y", "n", "c", "Y", "N", "C"};

		
	    private static String[] fieldNames;
		private static String[][] strings;
		
	    private static void init() {
	        // rewrites all arrays to 2dim array strings and names to fieldNames
	    	if (strings == null) {
				Field[] fields = STRINGS.class.getFields();
				Field[] allFields = STRINGS.class.getDeclaredFields();

				strings = new String[fields.length][];
				fieldNames = new String[fields.length];

				for (int i = 0; i < fields.length; i++) {
					String fieldName;
					if (fields[i].getType() == String.class) {
						try {
							boolean is = false;
							fieldName = (String) fields[i].get(String.class);
							fieldNames[i] = fieldName;

							for (Field field : allFields) {
								if (field.getName().equals(fieldName)) {
									strings[i] = (String[]) field.get(String[].class);
									is = true;
									break;
								}
							}
							if (!is) {
								strings[i] = new String[] { "no data" };
							}
						} catch (IllegalAccessException e) {
							strings[i] = new String[] { "no data" };
							fieldNames[i] = "no name";
						}
					} else {
						strings[i] = new String[] { "no data" };
						fieldNames[i] = "not String";
					}
				}
	    	}
	    }
	    
	    public static String validateYesNoCancel(String answer, String category) {
			
	    	if (category == "yes_no_cancel" || category == "yes_no") {
	    		for (String s : yes_no_cancel)
	    			if (answer.equals(s))
	    				switch ( answer.toUpperCase().charAt(0) ) {
						case 'Y':
							return "Yes";
						case 'N':
							return "No";
						case 'C':
							if (category == "yes_no_cancel")
								return "Cancel";
						}
	    	}
			return answer;
		}

		private static String getStringFromAll() {
	    	init();
	    	
	    	return getStringCustom(strings[rand.nextInt(fieldNames.length)]);
	    	////// 
	    }

	    private static String[] getCategoryArray (String category) {
	    	
			init();
			//System.out.println(category);
			// if category exist draws one from category
			for (int i = 0; i < fieldNames.length; i++) {
				if (fieldNames[i].equals(category)) {
					return strings[i];
				}
			}
	    	// new array with one string
	    	return new String[] {category};
	    }

		private static String getStringFromCategory(String category) {
			// Draws a String from the selected category
			if (category == "yes_no")
				return yes_no[rand.nextInt(2)];
			if (category == "yes_no_cancel")
				return yes_no_cancel[rand.nextInt(3)];
			return getStringCustom( getCategoryArray(category));
		}

		protected static String getStringCustom(String... examples) {
			// Draws from custom examples
			return examples[rand.nextInt(examples.length)];
		}
		
	}

	// char restricts
	public static final class CHARS {
		public static final String ALL_CHAR = "all_char";
		public static final String FROM_A_TO_Z = "A-Z";
		public static final String FROM_a_TO_z = "a-z";
		public static final String ALL_LETTERS = "a-zA-Z";
		public static final String ALL_DIGITS = "0-9";
		public static final String ALL_LETTERS_DIGITS = "a-zA-Z0-9";
		public static final String WITH_SPACE = "space";
		public static final String WITH_UNDERSCORE = "underscore";
		public static final String WITH_WHITE_SIGNS = "white-signs";
		public static final String ALL_LETTERS_DIGITS_WHITE_SIGNS = "a-zA-Z0-9 _";
		private static final String[] restrictes = { "all_char", "A-Z", "a-z", "a-zA-Z", "0-9", "a-zA-Z0-9", "space",
				"underscore", "white-signs", "a-zA-Z0-9 _" };
		
		private static String getRestrict(String restrict) {
			switch (restrict) {
			case "all_char":
				return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 _\\/|?<>,.~#@':;}]`{[+=-)(*&^%$£\"!€";
			case "A-Z":
				return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			case "a-z":
				return "abcdefghijklmnopqrstuvwxyz";
			case "a-zA-Z":
				return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			case "0-9":
				return "0123456789";
			case "a-zA-Z0-9":
				return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			case "a-zA-Z0-9 _":
				return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 _";
			case "space":
				return " ";
			case "underscore":
				return "_";
			case "white-signs":
				return " _";
			}
			return "";
		}

		private static String addAllowedRestrict(String allowed, String restrict) {
			String addAllowed;

			switch (restrict) {
			case "A-Z":
				addAllowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				break;
			case "a-z":
				addAllowed = "abcdefghijklmnopqrstuvwxyz";
				break;
			case "a-zA-Z":
				addAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				break;
			case "0-9":
				addAllowed = "0123456789";
				break;
			case "a-zA-Z0-9":
				addAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				break;
			case "a-zA-Z0-9 _":
				addAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 _";
				break;
			case "space":
				addAllowed = " ";
				break;
			case "underscore":
				addAllowed = "_";
				break;
			case "white-signs":
				addAllowed = " _";
				break;

			default: // all_char
				addAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 _\\/|?<>,.~#@':;}]`{[+=-)(*&^%$£\"!€";
				break;
			}
			
			return addAllowed(allowed, addAllowed);
		}

		private static String collectRestricts(String[] restricts) {
			String allRestrict = "";
			for (String restrict : restricts) {
				if ( Arrays.stream(restrictes).anyMatch(restrict::equals) )
					allRestrict = addDistinct(allRestrict, getRestrict(restrict));
				else
					allRestrict = addDistinct(allRestrict, restrict);
			}
			return allRestrict;
		}

		private static String addDistinct(String s1, String s2) {
			
			return IntStream.concat(s1.chars(), s2.chars())
					.distinct()
		            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		            .toString();
		}

		private static boolean isRestrict(String restrict) {
			return Arrays.stream(restrictes).anyMatch(restrict::equals);
//			for (int i = 0; i < restrictes.length; i++) {
//				if (restrictes[i].equals(restrict))
//					return true;
//			}
//			return false;
		}

		private static String addAllowed(String allowed, String addAllowed) {
			// adding
			for (int i = 0; i < addAllowed.length(); i++) {
				if (!allowed.contains(addAllowed.substring(i, i + 1))) {
					allowed += addAllowed.substring(i, i + 1);
				}
			}

			return allowed;
		}
	}


	
}