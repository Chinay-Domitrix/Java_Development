package leetCode.removePalindromicSubsequences

class Solution {
	fun removePalindromeSub(s: String) = if (s.isEmpty()) 0 else if (s == s.reversed()) 1 else 2
}
