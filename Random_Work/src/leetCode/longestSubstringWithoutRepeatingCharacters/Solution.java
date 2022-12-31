package leetCode.longestSubstringWithoutRepeatingCharacters;

/*class Solution {
	public int lengthOfLongestSubstring(String s) {
		var set = new HashSet<Character>();
		var max = 0;
		var start = 0;
		for (var i = 0; i < s.length(); i++)
			if (set.contains(s.charAt(i))) {
				max = max(max, i - start);
				while (s.charAt(start) != s.charAt(i)) {
					set.remove(s.charAt(start));
					start++;
				}
			} else set.add(s.charAt(i));
		return max(max, s.length() - start);
	}
}*/
