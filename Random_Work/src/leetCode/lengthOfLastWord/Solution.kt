package leetCode.lengthOfLastWord

class Solution {
	fun lengthOfLastWord(s: String) = s.trimEnd().substringAfterLast(' ').length
}
