package leetCode.runningSumOf1DArray

class Solution {
	fun runningSum(nums: IntArray): IntArray = with(IntArray(nums.size)) {
		nums.indices.forEach { this[it] = if (it == 0) nums[it] else this[it - 1] + nums[it] }
		this
	}
}
