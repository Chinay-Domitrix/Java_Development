package leetCode.longestValidParentheses

class Solution {
	fun longestValidParentheses(s: String): Int {
//		Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
		var max = 0
		var count = 0
		val stack = mutableListOf<Int>()
		for (i in s.indices) {
			if (s[i] == '(') stack.add(i) else if (s[i] == ')') if (stack.isNotEmpty()) {
				stack.removeAt(stack.size - 1)
				count += 2
			} else count = 0
			max = max.coerceAtLeast(count)
		}
		return max
	}
}
