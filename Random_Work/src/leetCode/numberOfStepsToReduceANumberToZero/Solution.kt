package leetCode.numberOfStepsToReduceANumberToZero

class Solution {
	fun numberOfSteps(num: Int): Int {
		var count = 0
		var num = num
		while (num > 0) count++.also { if ((num % 2) == 0) num /= 2 else num-- }
		return count
	}
}
