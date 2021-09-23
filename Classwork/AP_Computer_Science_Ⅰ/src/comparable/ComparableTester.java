package Classwork.AP_Computer_Science_Ⅰ.src.comparable;

import static java.lang.System.out;

/**
 * Many basic java classes implement the Comparable Interface which forces them to
 * implement the compareTo method which compares an implicit parameter (the one before '.')
 * to a parameter of the same type in the explicit parameter in parenthesis.
 * <ul>
 *  <li>If the first (implicit) is deemed less than the second (explicit) a negative value will be returned.</li>
 *  <li>If the first (implicit) is deemed greater than the second (explicit) a positive value will be returned.</li>
 *  <li>If they are equal a zero will be returned</li>
 * </ul>
 */
public class ComparableTester {
	public static void main(String[] args) {
		var s1 = "XYZ";
		var s2 = "abd";
		out.println(s1.compareTo(s2));
		out.println(s2.compareTo(s1));
		/* Use demonstrations of the following Wrapper classes that also implement Comparable*/
		// Integer
		Integer i1 = -3;
		Integer i2 = 3;
		out.println(i1.compareTo(i2));
		out.println(i2.compareTo(i1));
		// Double
		// Character
		// Boolean
	}
}
