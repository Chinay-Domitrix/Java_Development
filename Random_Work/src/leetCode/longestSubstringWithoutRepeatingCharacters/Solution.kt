package leetCode.longestSubstringWithoutRepeatingCharacters

import kotlin.math.max

class Solution {
	fun lengthOfLongestSubstring(s: String): Int {
		val set = mutableSetOf<Char>()
		var max = 0
		var start = 0
		for (i in s.indices) if (s[i] in set) {
			max = max(max, i - start)
			while (s[start] != s[i]) set.remove(s[start]).also { start++ }
			start++
		} else set += s[i]
		return max(max, s.length - start)
	}
}
