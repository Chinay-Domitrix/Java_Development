package leetCode.twoSum

class Solution {
	/*
	Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
	You may assume that each input would have exactly one solution, and you may not use the same element twice.
	You can return the answer in any order.
	Do in less than O(n^2) time and O(1) space.
	*/
	fun twoSum(nums: IntArray, target: Int): IntArray {
		nums.indices.forEach {
			(((it + 1) until nums.size)).forEach { j ->
				if ((nums[it] + nums[j]) == target) return intArrayOf(
					it, j
				)
			}
		}
		return intArrayOf()
	}
}
