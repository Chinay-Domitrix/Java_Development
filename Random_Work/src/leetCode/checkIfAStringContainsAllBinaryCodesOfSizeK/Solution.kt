package leetCode.checkIfAStringContainsAllBinaryCodesOfSizeK

import kotlin.math.pow

class Solution

fun Solution.hasAllCodes(s: String, k: Int) =
	((0 until ((s.length - k) + 1))).map { s.substring(it, it + k) }.toSet().size == 2.0.pow(k).toInt()
