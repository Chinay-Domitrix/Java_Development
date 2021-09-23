package Classwork.AP_Computer_Science_Ⅰ.src.strings;

import java.util.ArrayList;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.*;

public class PalindromePal {
	//	Main string to test for palindromes.  Default or set in Constructor.
	private String str;

	//	Constructor
	private PalindromePal(String s) {
		str = s;
	}

	//	static method to test if string in the same forward and back
	private static boolean isPalindrome(String s) {
		return s.equals(reverse2(s));
	}

	private static boolean isPalindrome2(String s) {
		return range(0, s.length() >> 1).allMatch(i -> s.substring(i, i + 1).equals(s.substring(s.length() - i - 1, s.length() - i)));
	}

	private static String reverse2(String s) {
		return iterate(s.length(), i -> i > 0, i -> i - 1).mapToObj(i -> s.substring(i - 1, i)).collect(joining());
	}

	private static String reverse(String s) {
		out.println(s);
		return (s.length() <= 0) ? "" : (s.substring(s.length() - 1) + reverse(s.substring(0, s.length() - 1)));
	}

	//	Use this for Unit Testing methods
	public static void main(String[] args) {
		out.println(isPalindrome2("amanaplanacanalpanama"));
		PalindromePal pal = new PalindromePal("120044078789");
		out.println(pal.countPalindromes(4));
		out.println(pal.getPalindromes(3));
		out.println(pal.countAllPalindromes(8));
	}

	public boolean isPalindrome() {
		return isPalindrome(str);
	}

	//	counts all palindrome substrings of a given length
	private int countPalindromes(int length) {
		return (length >= str.length()) ? 0 : (int) range(0, str.length() - length).filter(i -> isPalindrome(str.substring(i, i + length))).count(); // cannot check a length greater than the string
	}

	//	returns an ArrayList of all palindrome substrings of a given length
	private ArrayList<String> getPalindromes(int length) {
		return range(0, str.length() - length).filter(i -> isPalindrome(str.substring(i, i + length))).mapToObj(i -> str.substring(i, i + length)).collect(toCollection(ArrayList::new));
	}

	//	counts all palindrome substrings of length 2 - maxLength
	private int countAllPalindromes(int maxLength) {
		return rangeClosed(2, maxLength).map(this::countPalindromes).sum();
	}
}
