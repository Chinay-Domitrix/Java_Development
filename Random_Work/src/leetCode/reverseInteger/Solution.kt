package leetCode.reverseInteger

class Solution {
	fun reverse(x: Int) = try {
		(if (x < 0) "-" + x.toString().substring(1).reversed() else x.toString().reversed()).toInt()
	} catch (e: Exception) {
		0
	}
}
