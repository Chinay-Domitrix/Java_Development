package leetCode.leafSimilarTrees

class Solution {
	fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
		val leaves1 = ArrayList<Int>()
		val leaves2 = ArrayList<Int>()
		fun dfs(node: TreeNode?, leaves: MutableList<Int>) {
			if (node == null) return
			if ((node.left == null) && (node.right == null)) {
				leaves += node.`val`
				return
			}
			dfs(node.left, leaves)
			dfs(node.right, leaves)
		}
		dfs(root1, leaves1)
		dfs(root2, leaves2)
		return leaves1 == leaves2
	}
}

class TreeNode(@JvmField var `val`: Int) {
	@JvmField
	var left: TreeNode? = null

	@JvmField
	var right: TreeNode? = null
}
