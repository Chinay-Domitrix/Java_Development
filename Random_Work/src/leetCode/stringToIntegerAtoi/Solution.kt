package leetCode.stringToIntegerAtoi

import kotlin.Int.Companion.MAX_VALUE
import kotlin.Int.Companion.MIN_VALUE

class Solution {
	fun myAtoi(s: String): Int {
		var i = 0
		var sign = 1
		var result = 0
		while ((i < s.length) && (s[i] == ' ')) i++
		if ((i < s.length) && ((s[i] == '+') || (s[i] == '-'))) {
			sign = if (s[i] == '+') 1 else -1
			i++
		}
		while (i < s.length && s[i].isDigit()) {
			if ((result > (MAX_VALUE / 10)) || ((result == (MAX_VALUE / 10)) && ((s[i] - '0') > 7))) return if (sign == 1) MAX_VALUE else MIN_VALUE
			else {
				result = (result * 10) + (s[i] - '0')
				i++
			}
		}
		return result * sign
	}
}
