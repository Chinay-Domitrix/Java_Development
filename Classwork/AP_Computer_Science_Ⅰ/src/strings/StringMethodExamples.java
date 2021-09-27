package strings;

/*
 * Use the Java documentation of Strings to complete the description for all of the methods below (those tested on the AP exam)
 *
 * DOCS: https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
 *
 * Then, write code in the main method to demonstrate the function of each
 *
 * _________Method Signature_________   _______  Description ___________________
 * String(String str)                   Initializes a newly created String object so that it represents the same sequence of characters as the argument; in other words, the newly created string is a copy of the argument string.
 * int length()                         Returns the length of this string.
 * String substring(int from,int to)    Returns a string that is a substring of this string.
 * String substring(int from)           Returns a string that is a substring of this string.
 * int indexOf(String str)              Returns the index within this string of the first occurrence of the specified substring.
 * boolean equals(Object other)         Compares this string to the specified object.
 * int compareTo(String other)          Compares two strings lexicographically.
 */

import static java.lang.System.out;

public class StringMethodExamples {
	public static void main(String[] args) {
		String s = "Sample String";
		String s2 = s + " 2"; // Example of constructor
		out.printf("s = %s, s2 = %s%n", s, s2);
		out.printf("s.length = %d, s2.length = %d%n", s.length(), s2.length()); // Example of length() method
		// Give examples for the methods below:
		// String substring(int from,int to)
		out.println();
		// String substring(int from)

		// int indexOf(String str)

		// boolean equals(String other)

		// int compareTo(String other)

	}
}
