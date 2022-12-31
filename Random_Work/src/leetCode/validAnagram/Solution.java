package leetCode.validAnagram;

import java.util.Arrays;

class Solution {
	public boolean isAnagram(String s, String t) {
		return (s.length() == t.length()) && Arrays.stream(s.split("")).sorted().toList().toString().equals(Arrays.stream(t.split("")).sorted().toList().toString());
	}
}
