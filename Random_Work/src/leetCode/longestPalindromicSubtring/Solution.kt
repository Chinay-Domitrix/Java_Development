package leetCode.longestPalindromicSubtring

class Solution {
	fun longestPalindrome(s: String): String {
		return if (s.length <= 1) s else {
			var max = 0
			var start = 0
			var end = 0
			s.indices.forEach {
				with(expandAroundCenter(s, it, it).coerceAtLeast(expandAroundCenter(s, it, it + 1))) {
					if (this > max) {
						max = this
						start = it - ((this - 1) / 2)
						end = it + (this / 2)
					}
				}
			}
			s.substring(start, end + 1)
		}
	}

	private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
		var l = left
		var r = right
		while ((l >= 0) && (r < s.length) && (s[l] == s[r])) {
			l--
			r++
		}
		return r - l - 1
	}
}
