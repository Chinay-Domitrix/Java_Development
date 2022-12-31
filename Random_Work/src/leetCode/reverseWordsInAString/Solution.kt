package leetCode.reverseWordsInAString

class Solution {
	fun reverseWords(s: String) = s.trim().replace(Regex(" +"), " ").split(" ").reversed().joinToString(" ")
}
