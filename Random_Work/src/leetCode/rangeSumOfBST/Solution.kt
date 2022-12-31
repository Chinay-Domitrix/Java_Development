package leetCode.rangeSumOfBST

class Solution {
	fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int = if (root == null) 0 else when {
			root.`val` < low -> rangeSumBST(root.right, low, high)
			root.`val` > high -> rangeSumBST(root.left, low, high)
			else -> root.`val` + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high)
		}
}

class TreeNode(var `val`: Int) {
	var left: TreeNode? = null
	var right: TreeNode? = null
}
