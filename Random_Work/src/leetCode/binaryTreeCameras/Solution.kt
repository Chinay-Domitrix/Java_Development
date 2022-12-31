package leetCode.binaryTreeCameras

class Solution {
	fun minCameraCover(root: TreeNode?): Int {
		val dp = arrayOfNulls<Int>(2)
		dp[0] = 0
		dp[1] = 1
		return dp[dfs(root, dp)]!!
	}

	private fun dfs(root: TreeNode?, dp: Array<Int?>): Int {
		if (root == null) return 0
		val left = dfs(root.left, dp)
		val right = dfs(root.right, dp)
		if (left == 0 && right == 0) {
			dp[0] = 1
			return 1
		}
		if (left == 0) {
			dp[0] = 1
			return 1
		}
		if (right == 0) {
			dp[0] = 1
			return 1
		}
		dp[0] = 0
		return 0
	}
}

class TreeNode(var `val`: Int) {
	var left: TreeNode? = null
	var right: TreeNode? = null
}
