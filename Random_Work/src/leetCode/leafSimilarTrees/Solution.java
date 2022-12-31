package leetCode.leafSimilarTrees;

import java.util.ArrayList;

class Solution {
	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		var leaf1 = new ArrayList<Integer>();
		var leaf2 = new ArrayList<Integer>();
		mapTree(root1, leaf1);
		mapTree(root2, leaf2);
		return leaf1.equals(leaf2);
	}

	private void mapTree(TreeNode root, ArrayList<Integer> leaf) {
		if (root == null) return;
		if ((root.left == null) && (root.right == null)) {
			leaf.add(root.val);
			return;
		}
		mapTree(root.left, leaf);
		mapTree(root.right, leaf);
	}
}
