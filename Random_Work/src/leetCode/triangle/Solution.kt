package leetCode.triangle

class Solution {
	//	Given a triangle array, return the minimum path sum from top to bottom. For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or i + 1 on the next row.
	fun minimumTotal(triangle: List<List<Int>>): Int {
		return if (triangle.flatten().size > 1) {
			val n = triangle.size
			val dp = Array(n) { IntArray(n) }
			(0 until n).forEach { dp[it][it] = triangle[it][it] }
			((0 until (n - 1))).forEach { i ->
				(0 until (n - i - 1)).forEach { j ->
					dp[i][j] = triangle[i][j] + dp[i][j].coerceAtMost(dp[i][j + 1])
				}
			}
			dp[0][0]
		} else triangle[0][0]
	}
}
