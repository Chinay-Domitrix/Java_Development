package leetCode.minimumNumberOfSwapsToMakeTheStringBalanced

class Solution {
	fun minSwaps(s: String) = s.substring(0, s.length / 2).count { it == ']' }
}
