package leetCode.mergeSortedArray;

/*
public class Solution {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		*/
/*arraycopy(nums2, 0, nums1, m, nums2.length);
		sort(nums1);*//*

//		Merge the pre-sorted arrays in sorted order using O(1) space
		int i = m - 1, j = n - 1, k = m + n - 1;
		while (i >= 0 && j >= 0) nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
		while (j >= 0) nums1[k--] = nums2[j--];
	}
}
*/
