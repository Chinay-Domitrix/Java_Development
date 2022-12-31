package leetCode.romanToInteger

class Solution {
	fun romanToInt(s: String): Int {
		var result = 0
		var i = 0
		while (i < s.length) {
			val c = s[i]
			val next = if ((i + 1) < s.length) s[i + 1] else ' '
			when (c) {
				'I' -> when (next) {
					'V' -> {
						result += 4
						i++
					}

					'X' -> {
						result += 9
						i++
					}

					else -> result++
				}

				'V' -> result += 5
				'X' -> when (next) {
					'L' -> {
						result += 40
						i++
					}

					'C' -> {
						result += 90
						i++
					}

					else -> result += 10
				}

				'L' -> result += 50
				'C' -> when (next) {
					'D' -> {
						result += 400
						i++
					}

					'M' -> {
						result += 900
						i++
					}

					else -> result += 100
				}

				'D' -> result += 500
				'M' -> result += 1000
			}
			i++
		}
		return result
	}
}
