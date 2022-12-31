package leetCode.checkIfNumbersAreAscendingInASentence

class Solution {
	fun areNumbersAscending(s: String) = with(
		s.split(" ")
			.filter { !it.contains(Regex("[a-zA-Z]+")) }) { (1 until size).none { this[it - 1].toInt() >= this[it].toInt() } }
}
