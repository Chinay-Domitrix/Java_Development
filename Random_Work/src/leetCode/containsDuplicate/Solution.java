package leetCode.containsDuplicate;

public class Solution {
	public boolean containsDuplicate(int[] nums) {
		return nums.length != java.util.Arrays.stream(nums).distinct().count();
	}
}
