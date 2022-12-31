package leetCode.missingNumber

class Solution {
	fun missingNumber(nums: IntArray): Int {
		nums.sort();
		return if (nums[0] != 0) 0
		else (0 until nums.size - 1).firstOrNull { nums[it + 1] != (nums[it] + 1) }?.let { it + 1 } ?: nums.size
	}
}
