package leetCode.`minimumMovesToEqualArrayElementsⅡ`

class Solution {
	fun minMoves2(nums: IntArray): Int {
		val sorted = nums.sorted()
		var ans = 0
		nums.forEach { ans += sorted[nums.size / 2] - it }
		return ans
	}
}
