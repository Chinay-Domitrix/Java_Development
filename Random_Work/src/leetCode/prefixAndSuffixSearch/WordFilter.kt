package leetCode.prefixAndSuffixSearch

class WordFilter(val words: Array<String>) {
	fun f(prefix: String, suffix: String): Int {
		val validIndicies = ArrayList<Int>()
		words.indices.filterTo(validIndicies) { words[it].startsWith(prefix) && words[it].endsWith(suffix) }
		validIndicies.sort()
		return if (validIndicies.isEmpty()) -1 else validIndicies.last()
	}
}
