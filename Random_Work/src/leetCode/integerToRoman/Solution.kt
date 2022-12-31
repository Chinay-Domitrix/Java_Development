package leetCode.integerToRoman

/*class Solution {
	fun intToRoman(num: Int) = buildString {
		val intRomanPairs = mapOf(
			1 to "I",
			4 to "IV",
			5 to "V",
			9 to "IX",
			10 to "X",
			40 to "XL",
			50 to "L",
			90 to "XC",
			100 to "C",
			400 to "CD",
			500 to "D",
			900 to "CM",
			1000 to "M"
		)
		var n = num
		intRomanPairs.keys.indices.forEach {
			val i = intRomanPairs.keys.size - 1 - it
			val key = intRomanPairs.keys.elementAt(i)
			val value = intRomanPairs.values.elementAt(i)
			while (n >= key) {
				n -= key
				append(value)
			}
		}
	}
}*/

class Solution {
	fun intToRoman(num: Int): String = buildString {
		var remain = num
		listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1).withIndex().forEach { (index, value) ->
			repeat(remain / value) {
				append(
					arrayOf(
						"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
					)[index]
				)
			}
			remain %= value
		}
	}
}
