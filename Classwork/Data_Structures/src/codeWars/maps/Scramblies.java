package codeWars.maps;

/*
Complete the function scramble(str1, str2) that returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false.
Notes:
    Only lower case letters will be used (a-z). No punctuation or digits will be included.
    Performance needs to be considered.

Examples:
scramble('rkqodlw', 'world') ==> True
scramble('cedewaraaossoqqyt', 'codewars') ==> True
scramble('katas', 'steak') ==> False
*/

import static java.util.stream.IntStream.range;

public class Scramblies {
	public static boolean scramble(String str1, String str2) {
		if (str1.length() < str2.length()) return false;
		int[] alpha1 = new int[26], alpha2 = new int[26];
		for (int i = 0; i < str1.length(); i++) alpha1[str1.charAt(i) - 97]++;
		for (int i = 0; i < str2.length(); i++) alpha2[str2.charAt(i) - 97]++;
		return range(0, 26).noneMatch(i -> alpha1[i] < alpha2[i]);
	}
}
