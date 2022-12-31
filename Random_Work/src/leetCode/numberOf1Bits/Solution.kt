package leetCode.numberOf1Bits

import java.lang.Integer.toBinaryString

class Solution {
	// you need treat n as an unsigned value
	fun hammingWeight(n: Int) = toBinaryString(n).count { it == '1' }
}
