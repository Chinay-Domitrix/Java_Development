package leetCode.rangeSumOfBST;

import java.util.ArrayList;

public class Solution {
	public int rangeSumBST(TreeNode root, int low, int high) {
		var mappedTree = new ArrayList<Integer>();
		mapTree(root, mappedTree);
		return mappedTree.stream().filter(i -> i >= low && i <= high).mapToInt(i -> i).sum();
	}

	private void mapTree(TreeNode root, ArrayList<Integer> mappedTree) {
		if (root == null) return;
		mappedTree.add(root.val);
		mapTree(root.left, mappedTree);
		mapTree(root.right, mappedTree);
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
