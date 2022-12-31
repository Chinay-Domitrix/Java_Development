package leetCode.maximumProductOfWordLengths

class Solution {
	fun maxProduct(words: Array<String>): Int {
		var wordsChars = ArrayList<HashSet<Char>>()
		words.forEach { wordsChars += it.toCharArray().toHashSet() }
		return 0
	}
}
