package leetCode.maximumErasureValue

class Solution {
	/*
	Use sliding window to erase a subarray of unique elements from nums, ensuring the largest possible sum from the remained of nums, and then return that sum.
	*/
	fun maximumUniqueSubarray(nums: IntArray): Int {
		val window = IntArray(nums.size)
		var max = 0
		for (i in nums.indices) {
			window[i] = nums[i]
			if (i >= 1) window[i] += window[i - 1]
			max = maxOf(max, window[i])
		}
		var left = 0
		var right = 0
		var sum = 0
		var maxSum = 0
		while (right < nums.size) {
			sum += window[right]
			while ((left <= right) && (sum > maxSum)) {
				maxSum = maxSum.coerceAtLeast(sum - window[left])
				left++
			}
			right++
		}
		return maxSum
	}
}
