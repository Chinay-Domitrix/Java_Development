package leetCode.majorityElement

class Solution {
	fun majorityElement(nums: IntArray): Int {
		var cou nt = 0
		var candidate = 0
		nums.forEach {
			if (count == 0) candidate = it
			count += if (it == candidate) 1 else -1
		}
		return candidate
	}
}
