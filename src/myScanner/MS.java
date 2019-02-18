package myScanner;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.lang.reflect.Field;

/**
 * Wrapper Class to Scanner.
 * 
 * - Opens the channel once - Class supports int, double, char and String type
 * - It displays information about the data needed
 * - The class has predefined STRINGS and CHARS types to facilitate random data
 * - Int and double data can be limited by the range
 * - Class does not require creating an object (we use like Math)
 * - When the data is bad, he asks for the data to be re-entered
 * - The CHARS class allows you to add your own set of characters
 * - The STRINGS class allows you to add your own set of words
 * - You can add your own predefined values to the STRINGS class (info in the comment)
 *
 * Changes 
 * - Order of parametrs in getInt ( info, fill, from, to )
 * - Same in getDouble ( info, fill, from, to ) like getString and getChar
 * - STRINGS predefined Yes_No and Yes_No_Cancel with validation
 * - Primitive testers for all method
 * - Allowing the option of entering an empty string
 * - getFDouble ( info, rounded, fill, from, to )
 * - getRandom[][Double][FDouble] ( from, to [, rounded] ) for int, double and Fdouble
 *
 * @author Bogdan Pasterak
 * @version 4.0
 * @since 22 Jan 2019
 */


public class MS {

	private static Random rand;
	private static Scanner sc;

	// boolean

	public static final boolean ALLOW_FILL = true;
	public static final boolean DONT_FILL = false;

//	public static final boolean NOT_EMPTY = true;
//	public static final boolean CAN_BE_EMPTY = false;
//	public static final boolean CAN_NOT_BE_ZERO = false;
//	public static final boolean NOT_AUTO_FILL = false;
//	public static final boolean CAN_BE_ZERO = true;

	// if scanner object does not exist yet
	private static void isScanner() {
		if (sc == null) {
			sc = new Scanner(System.in);
			rand = new Random();
		}
	}

	public static void resetScanner() {
		sc = new Scanner(System.in);
		rand = new Random();
	}

	// --------------------------------------------------------------
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
			if (answer.length() > 0) {
				// correct Yes / No / Cancel answer
				if (examples.length == 1)
					answer = STRINGS.validateYesNoCancel(answer, examples[0]);
				// if can fill or no example to validate just answer
				if (fill || examples.length == 0)
					return answer;
				// if don't fill must match patterns of category
				if (examples.length == 1)
					// exeption for empty string
					if (examples[0].equals(""))
						return answer;
					else
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
			if (fill) {
				// no example , draw from all
				if (examples == null || examples.length == 0)
					return STRINGS.getStringFromAll();
				// category or singel example
				else if (examples.length == 1)
					return STRINGS.getStringFromCategory(examples[0]);
				// draws one from examples
				else
					return STRINGS.getStringCustom(examples);
			// accept empty String
			} else if ( examples.length > 0 && examples[0] == "")
				return "";
			// if can't draw, try again

			System.out.println("Improper text, try again.");

		} while (true);
	}

	public static String getString(String info, boolean fill, String category) {

		return getString(info, fill, new String[] { category });
	}

	public static String getString(boolean fill, String... examples) {

		return getString(null, fill, examples);
	}

	public static String getString(boolean fill, String category) {

		return getString(null, fill, new String[] { category });
	}

	public static String getString(String info, boolean fill) {

		return getString(info, fill, new String[] {});
	}

	public static String getString(String info, String category) {

		return getString(info, ALLOW_FILL, new String[] { category });
	}

	public static String getString(String... infoAndExamples) {

		return getString(infoAndExamples[0], ALLOW_FILL,
				Arrays.copyOfRange(infoAndExamples, 1, infoAndExamples.length));
	}

	public static String getString(String info) {

		return getString(info, ALLOW_FILL, new String[] {});
	}

	public static String getString(boolean fill) {

		return getString(null, fill, new String[] {});
	}

	public static String getString() {

		return getString(null, ALLOW_FILL, new String[] {});
	}

	// --------------------------------------------------------------
	// input character full parameters

	public static char getChar(String info, boolean fill, String... restricts) {
		String allowed = "";
		String answer;

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
		allowed = CHARS.collectRestricts(restricts);

		// repeat until you get the correct answer
		do {
			System.out.print(info);
			answer = sc.nextLine();

			// user type Enter
			if (answer.length() == 0) {
				// return random char from allowed
				if (fill)
					return allowed.charAt(rand.nextInt(allowed.length()));
				else
					System.out.println("You must enter some character");
				// user type something
			} else if (fill || allowed.contains(answer.substring(0, 1))) {
				// if any one or matched
				return answer.charAt(0);
			} else {
				// not matched
				System.out.println("Out of range, acceptable characters :");
				// writing out permissible characters in rows after 16
				for (int i = 0; i < allowed.length(); i++) {
					System.out.print(allowed.substring(i, i + 1)
							+ ((i == allowed.length() - 1) ? ".\n" : ((i % 16 == 15) ? "  and\n" : ", ")));
				}
			}
		} while (true);

	}

	public static char getChar(String info, boolean fill, String restricts) {

		return getChar(info, fill, new String[] { restricts, "" });
	}

	public static char getChar(String info, String restricts) {

		return getChar(info, ALLOW_FILL, new String[] { restricts, "" });
	}

	public static char getChar(String info) {

		return getChar(info, ALLOW_FILL, new String[] { CHARS.ALL_CHAR, "" });
	}

	public static char getChar(String... infoAndRestricts) {

		return getChar(infoAndRestricts[0], ALLOW_FILL,
				Arrays.copyOfRange(infoAndRestricts, 1, infoAndRestricts.length));
	}

	public static char getChar() {

		return getChar("", ALLOW_FILL, new String[] { CHARS.ALL_CHAR, "" });
	}

	public static char getChar(boolean fill) {

		return getChar("", fill, new String[] { CHARS.ALL_CHAR, "" });
	}

	public static char getChar(String info, boolean fill) {

		return getChar("", fill, new String[] { CHARS.ALL_CHAR, "" });
	}

	public static char getChar(boolean fill, String... restricts) {

		return getChar("", fill, restricts);
	}

	public static char getChar(boolean fill, String restricts) {

		return getChar("", fill, new String[] { restricts, "" });
	}

	// --------------------------------------------------------------
	// input int (if enter set 0)

	public static int getInt(String info, boolean fill, int from, int to) {
		String answer;
		
		// if first time use create new Scanner and Random
		isScanner();

		// validate range
		if (from > to) {
			// swap range and send info !
			// throw new IllegalArgumentException("FROM it is bigger than TO");
			System.err.println("\nWrong range, swap FROM=" + from + " and TO=" + to + "\n");
			int temp = from;
			from = to;
			to = temp;
		}
		// validate info
		if (info == null || info.length() == 0)
			info = "Type number: ";
		else if (info.endsWith(":"))
			info += " ";
		else if (!info.endsWith(": "))
			info += ": ";

		// repeat until you get the correct answer
		do {
			System.out.print(info);
			answer = sc.nextLine();
			
			// user type Enter
			if (answer.length() == 0)
				// return random int from range
				if (fill)
					return rand.nextInt(to - from + 1) + from;
				else
					System.out.println("You must enter some number");
			// user type something
			else
				try {
					int i = Integer.parseInt(answer);
					if ( ! fill && (i < from || i > to))
						System.out.println("Out of range( " + from + " - " + to + " )");
					else
						return i;
				} catch (NumberFormatException e) {
					System.out.println("Wrong format for Integer, tray again");
				}
		} while (true);

	}

	public static int getInt(String info, boolean fill, int from_0_to) {
		// from zero
		return getInt(info, fill, 0, from_0_to);
	}
	
	public static int getInt(String info, boolean fill ) {
		// from zero to 100
		return getInt(info, fill, 0, 100);
	}
	
	public static int getInt(String info ) {
		// can random from zero to 100
		return getInt(info, ALLOW_FILL, 0, 100);
	}
	
	public static int getInt(String info, int from_0_to ) {
		// can random from zero 
		return getInt(info, ALLOW_FILL, 0, from_0_to);
	}
	
	public static int getInt(String info, int from, int to ) {
		// can random 
		return getInt(info, ALLOW_FILL, from, to);
	}
	
	public static int getInt() {
		// auto message, cant random from zero to 100
		return getInt("", ALLOW_FILL, 0, 100);
	}
	
	public static int getInt(boolean fill, int from, int to) {
		// escape info
		return getInt("", fill, from, to);
	}
	
	public static int getInt(boolean fill, int from_0_to) {
		// escape info, from 0
		return getInt("", fill, 0, from_0_to);
	}
	
	public static int getInt(boolean fill ) {
		// escape info, from 0 to 100
		return getInt("", fill, 0, 100);
	}
	
	public static int getInt(int from, int to ) {
		// escape info, allow random 
		return getInt("", ALLOW_FILL, from, to);
	}
	
	public static int getInt( int from_0_to ) {
		// escape info, allow random, from 0
		return getInt("", ALLOW_FILL, 0, from_0_to);
	}
	
	// --------------------------------------------------------------
	// input double
	public static double getDouble(String info, boolean fill, double from, double to) {
		String answer;
		// if first time use create new Scanner and Random
		isScanner();

		// validate range
		if (from > to) {
			// swap range and send info !
			// throw new IllegalArgumentException("FROM it is bigger than TO");
			System.err.println("\nWrong range, swap FROM=" + from + " and TO=" + to + "\n");
			double temp = from;
			from = to;
			to = temp;
		}
		// validate info
		if (info == null || info.length() == 0)
			info = "Type double: ";
		else if (info.endsWith(":"))
			info += " ";
		else if (!info.endsWith(": "))
			info += ": ";

		// repeat until you get the correct answer
		do {
			System.out.print(info);
			answer = sc.nextLine();
			
			// user type Enter
			if (answer.length() == 0)
				// return random int from range
				if (fill)
					return rand.nextDouble() * (to - from) + from;
				else
					System.out.println("You must enter some number");
			// user type something
			else
				try {
					double i = Double.parseDouble(answer);
					if ( ! fill && (i < from || i > to))
						System.out.println("Out of range( " + from + " - " + to + " )");
					else
						return i;
				} catch (NumberFormatException e) {
					System.out.println("Wrong format for Double, tray again");
				}
		} while (true);
	}
	
	public static double getDouble(String info, boolean fill, double from_0_to) {
		// start from zero
		return getDouble(info, fill, 0, from_0_to);
	}
	
	public static double getDouble(String info, boolean fill) {
		// from zero to 100
		return getDouble(info, fill, 0, 100);
	}
	
	public static double getDouble(String info, double from, double to) {
		// can random 
		return getDouble(info, ALLOW_FILL, from, to);
	}
	
	public static double getDouble(String info, double from_0_to) {
		// can random, start from zero
		return getDouble(info, ALLOW_FILL, 0, from_0_to);
	}
	
	public static double getDouble(String info) {
		// can random, from zero to 100
		return getDouble(info, ALLOW_FILL, 0, 100);
	}
	
	public static double getDouble(boolean fill, double from, double to) {
		// escape info
		return getDouble("", fill, from, to);
	}
	
	public static double getDouble(boolean fill, double from_0_to) {
		// escape info, start from zero
		return getDouble("", fill, 0, from_0_to);
	}
	
	public static double getDouble(boolean fill) {
		// escape from zero to 100
		return getDouble("", fill, 0, 100);
	}
	
	public static double getDouble(double from, double to) {
		// escape info,  can random
		return getDouble("", ALLOW_FILL, from, to);
	}
	
	public static double getDouble(double from_0_to) {
		// escape info,  can random, start from zero
		return getDouble("", ALLOW_FILL, 0, from_0_to);
	}
	
	public static double getDouble() {
		// escape info,  can random, from zero to 100
		return getDouble("", ALLOW_FILL, 0, 100);
	}
	
	// --------------------------------------------------------------
	// input fixed accuracy double ( > 0 after decimal, < 0 before decimal )
	public static double getFDouble(String info, int accuracy, boolean fill, double from, double to) {
		String answer;
		double power;
		// if first time use create new Scanner and Random
		isScanner();

		// validate accuracy
		if (accuracy > 9 || accuracy < -9) {
			// throw new IllegalArgumentException("Accuracy is over the range");
			System.err.println("\nWrong accuracy, allowed range from 9 to -9, was " + accuracy + "\n");
			accuracy = 0;
		}
		power = Math.pow(10, accuracy);
		// validate range
		if (from > to) {
			// swap range and send info !
			// throw new IllegalArgumentException("FROM it is bigger than TO");
			System.err.println("\nWrong range, swap FROM=" + from + " and TO=" + to + "\n");
			double temp = from;
			from = to;
			to = temp;
		}
		// precision bigger than range
		if (to < 1 / power) {
			// throw new IllegalArgumentException("To low range from or to");
			System.err.println("\nWrong accuracy to range = " + accuracy + " and to = " + to + "\n");
			return getDouble(info, fill, from, to);
		}
		// validate info
		if (info == null || info.length() == 0)
			info = "Type double: ";
		else if (info.endsWith(":"))
			info += " ";
		else if (!info.endsWith(": "))
			info += ": ";

		// repeat until you get the correct answer
		do {
			System.out.print(info);
			answer = sc.nextLine();
			
			// user type Enter
			if (answer.length() == 0)
				// return random int from range
				if (fill)
					return getRnd((int)(from * power), (int)(to * power)) / power;
				else
					System.out.println("You must enter some number");
			// user type something
			else
				try {
					double i = Double.parseDouble(answer);
					if ( ! fill && (i < from || i > to))
						System.out.println("Out of range( " + from + " - " + to + " )");
					else
						// set precision
						return Math.round(i * power) / power;
				} catch (NumberFormatException e) {
					System.out.println("Wrong format for Double, tray again");
				}
		} while (true);
	}
	
	public static double getFDouble(String info, int accuracy, boolean fill, double from_0_to) {
		// start from zero
		return getFDouble(info, accuracy, fill, 0, from_0_to);
	}
	
	public static double getFDouble(String info, int accuracy, boolean fill) {
		// from zero to 100
		return getFDouble(info, accuracy, fill, 0, 100);
	}
	
	public static double getFDouble(String info, int accuracy, double from, double to) {
		// can random 
		return getFDouble(info, accuracy, ALLOW_FILL, from, to);
	}
	
	public static double getFDouble(String info, int accuracy, double from_0_to) {
		// can random, start from zero
		return getFDouble(info, accuracy, ALLOW_FILL, 0, from_0_to);
	}
	
	public static double getFDouble(String info, int accuracy) {
		// can random, from zero to 100
		return getFDouble(info, accuracy, ALLOW_FILL, 0, 100);
	}
	
	public static double getFDouble(int accuracy, boolean fill, double from, double to) {
		// escape info
		return getFDouble("", accuracy, fill, from, to);
	}
	
	public static double getFDouble(int accuracy, boolean fill, double from_0_to) {
		// escape info, start from zero
		return getFDouble("", accuracy, fill, 0, from_0_to);
	}
	
	public static double getFDouble(int accuracy, boolean fill) {
		// escape from zero to 100
		return getFDouble("", accuracy, fill, 0, 100);
	}
	
	public static double getFDouble(int accuracy, double from, double to) {
		// escape info,  can random
		return getFDouble("", accuracy, ALLOW_FILL, from, to);
	}
	
	public static double getFDouble(int accuracy, double from_0_to) {
		// escape info,  can random, start from zero
		return getFDouble("", accuracy, ALLOW_FILL, 0, from_0_to);
	}
	
	public static double getFDouble(int accuracy) {
		// escape info,  can random, from zero to 100
		return getFDouble("", accuracy, ALLOW_FILL, 0, 100);
	}
	
	
	// get random int 
	private static int getRnd(int from, int to) {
		return rand.nextInt(to - from + 1) + from;
	}
	
	public static int getRandom(int from, int to) {
		isScanner();
		return getRnd(from, to);
	}

	private static double getRndD(int from, int to) {
		return rand.nextDouble() * (to - from) + from;
	}
	
	public static double getRandomDouble(int from, int to) {
		isScanner();
		return getRndD(from, to);
	}

	private static double getRndFD(int from, int to, int accuracy) {
		double power = Math.pow(10, accuracy);
		return getRnd((int)(from * power), (int)(to * power)) / power;
	}
	
	public static double getRandomFDouble(int from, int to, int accuracy ) {
		isScanner();
		if (accuracy > 9 || accuracy < -9 || from > to || to < Math.pow(10, -accuracy)) {
			System.err.println("\nInvalid data for random value\n");
			return 0;
		}
		return getRndFD(from, to, accuracy);
	}

	// nowa wersja
	
	public static final class STRINGS {
		// the class draws (with empty string) a string from the selected category
		// *** YOU OWN VALUES ***
		// to add categories, create a public string with the category name
		// and private string array with this name

		// First name
		public static final String FIRST_NAME = "first_name";
		@SuppressWarnings("unused")
		private static final String[] first_name = { "Bogdan", "Jon", "Lise", "Max", "Niels", "Patty", "Richard" };

		// Surname
		public static final String SURNAME = "surname";
		@SuppressWarnings("unused")
		private static final String[] surname = { "Pasterak", "Doe", "Curie", "Planck", "Bohr", "Wotson" };

		// Full name
		public static final String NAME = "name";
		@SuppressWarnings("unused")
		private static final String[] name = { "Bogdan Pasterak", "Jon Doe", "Jacqueline K. Barton",
				"Gertrude B. Elion", "Enrico Fermi", "Frieda Robscheit-Robbins" };

		// Department
		public static final String DEPARTMENT = "department";
		@SuppressWarnings("unused")
		private static final String[] department = { "Architecture", "Economics", "Geosciences", "IT", "Transportation",
				"Music" };

		// Item
		public static final String ITEM = "item";
		@SuppressWarnings("unused")
		private static final String[] item = { "Milk", "Eggs", "Bread", "Butter", "Sugar", "Sweets" };

		// Yes / No - always returns a validated value (Yes, No), allow some freedom
		// when typing
		public static final String YES_NO = "yes_no";
		private static final String[] yes_no = { "Yes", "No", "yes", "no", "YES", "NO", "y", "n", "Y", "N" };

		// Yes / No / Cancel - always returns a validated value (Yes, No, Cancel), allow
		// some freedom when typing
		public static final String YES_NO_CANCEL = "yes_no_cancel";
		private static final String[] yes_no_cancel = { "Yes", "No", "Cancel", "yes", "no", "cancel", "YES", "NO",
				"CANCEL", "y", "n", "c", "Y", "N", "C" };

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
						switch (answer.toUpperCase().charAt(0)) {
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
		}

		private static String[] getCategoryArray(String category) {

			init();
			// System.out.println(category);
			// if category exist draws one from category
			for (int i = 0; i < fieldNames.length; i++) {
				if (fieldNames[i].equals(category)) {
					return strings[i];
				}
			}
			// new array with one string
			return new String[] { category };
		}

		private static String getStringFromCategory(String category) {
			// Draws a String from the selected category
			if (category == "yes_no")
				return yes_no[rand.nextInt(2)];
			if (category == "yes_no_cancel")
				return yes_no_cancel[rand.nextInt(3)];
			return getStringCustom(getCategoryArray(category));
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

		private static String collectRestricts(String[] restricts) {
			String allRestrict = "";

			// if there are no restrictions
			if (restricts.length == 0)
				allRestrict = getRestrict("all_char");
			// if only one
			else if (restricts.length == 1)
				if (Arrays.stream(restrictes).anyMatch(restricts[0]::equals))
					allRestrict = getRestrict(restricts[0]);
				else
					allRestrict = restricts[0];
			// more than one
			else
				for (String restrict : restricts) {
					if (Arrays.stream(restrictes).anyMatch(restrict::equals))
						allRestrict = addDistinct(allRestrict, getRestrict(restrict));
					else
						allRestrict = addDistinct(allRestrict, restrict);
				}
			return allRestrict;
		}

		private static String addDistinct(String s1, String s2) {

			return IntStream.concat(s1.chars(), s2.chars()).distinct()
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		}

	}

}