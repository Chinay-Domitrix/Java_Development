package leetCode.countCompleteTreeNodes

class Solution {
	//	Given the root of a complete binary tree, return the number of the nodes in the tree.
	/*fun countNodes(root: TreeNode?): Int {
		if (root == null) return 0
		var left = 0
		var right = 0
		var node = root
		while (node != null) {
			left++
			node = node.left
		}
		node = root
		while (node != null) {
			right++
			node = node.right
		}
		if (left == right) return (1 shl left) - 1
		return countNodes(root.left) + countNodes(root.right) + 1
	}*/
}
