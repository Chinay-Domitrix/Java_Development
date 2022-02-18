package codeWars.sets

/*
Given an array of positive or negative integers I= [i1,..,in] you have to produce a sorted array P of the form [ [p, sum of all ij of I for which p is a prime factor (p positive) of ij] ...]

P will be sorted by increasing order of the prime numbers. The final result has to be given as a string in Java, C#, C, C++ and as an array of arrays in other languages.

Example:
I = (/12, 15/); // result = "(2 12)(3 27)(5 15)"
[2, 3, 5] is the list of all prime factors of the elements of I, hence the result.

Note: It can happen that a sum is 0 if some numbers are negative!

Example: I = [15, 30, -45] 5 divides 15, 30 and (-45) so 5 appears in the result, the sum of the numbers for which 5 is a factor is 0, so we have [5, 0] in the result amongst others.
*/
fun sumOfDivided(l: IntArray) = l.filter { it > 0 }.map { it.primeFactors() }.flatten().distinct().sorted()
	.map { i -> i to l.filter { it.primeFactors().contains(it) }.sum() }.joinToString("") { "[$it]" }

fun Int.primeFactors(): List<Int> {
	val primes = mutableListOf<Int>()
	var i = 2
	var temp = this
	while (i <= temp) if (temp % i == 0) primes.add(i.also { temp /= i }) else i++
	return primes
}

fun main() {
	println(sumOfDivided(intArrayOf(12, 15)))
	println(sumOfDivided(intArrayOf(15, 21, 24, 30, 45)))
	println(sumOfDivided(intArrayOf(107, 158, 204, 100, 118, 123, 126, 110, 116, 100)))
	println(sumOfDivided(intArrayOf()))
	println(sumOfDivided(intArrayOf(1070, 1580, 2040, 1000, 1180, 1230, 1260, 1100, 1160, 1000)))
}
