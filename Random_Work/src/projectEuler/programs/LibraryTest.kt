package projectEuler.programs

import org.jetbrains.annotations.Contract
import org.junit.Assert.*
import org.junit.Test
import projectEuler.programs.Library.binomial
import projectEuler.programs.Library.factorial
import projectEuler.programs.Library.gcd
import projectEuler.programs.Library.isPrime
import projectEuler.programs.Library.isSquare
import projectEuler.programs.Library.listPrimality
import projectEuler.programs.Library.listPrimes
import projectEuler.programs.Library.listTotients
import projectEuler.programs.Library.nextPermutation
import projectEuler.programs.Library.powMod
import projectEuler.programs.Library.reciprocalMod
import projectEuler.programs.Library.reverse
import projectEuler.programs.Library.sqrt
import projectEuler.programs.Library.totient
import java.math.BigInteger
import java.util.*
import java.util.Arrays.binarySearch
import java.util.function.Consumer

class LibraryTest {
	@Test
	fun testReverse() {
		assertEquals("", reverse(""))
		assertEquals("a", reverse("a"))
		assertEquals("ba", reverse("ab"))
		assertEquals("001", reverse("100"))
		assertEquals("a0a", reverse("a0a"))
	}

	@Test
	fun testIsPalindromeString() {
		assertTrue(isPalindrome(""))
		assertTrue(isPalindrome("a"))
		assertTrue(isPalindrome("aa"))
		assertTrue(isPalindrome("aaa"))
		assertTrue(isPalindrome("aaaa"))
		assertTrue(isPalindrome("aba"))
		assertTrue(isPalindrome("abba"))
		assertTrue(isPalindrome("abbba"))
		assertTrue(isPalindrome("acbca"))
		assertFalse(isPalindrome("ab"))
		assertFalse(isPalindrome("ba"))
		assertFalse(isPalindrome("aaba"))
		assertFalse(isPalindrome("abcd"))
	}

	private fun isPalindrome(s: String): Boolean {
		return Library.isPalindrome(s.toInt())
	}

	private fun isPalindrome(i: Int): Boolean {
		return Library.isPalindrome(i)
	}

	@Test
	fun testIsPalindromeInt() {
		assertTrue(isPalindrome(0))
		assertTrue(isPalindrome(1))
		assertTrue(isPalindrome(5))
		assertTrue(isPalindrome(11))
		assertTrue(isPalindrome(33))
		assertTrue(isPalindrome(101))
		assertTrue(isPalindrome(151))
		assertTrue(isPalindrome(737))
		assertTrue(isPalindrome(2222))
		assertTrue(isPalindrome(5665))
		assertTrue(isPalindrome(2147447412))
		assertFalse(isPalindrome(12))
		assertFalse(isPalindrome(43))
		assertFalse(isPalindrome(220))
		assertFalse(isPalindrome(1010))
		assertFalse(isPalindrome(2147483647))
	}

	@Test
	fun testSqrtInt() {
		assertEquals(0, sqrt(BigInteger.valueOf(0)).toInt().toLong())
		assertEquals(1, sqrt(BigInteger.valueOf(1)).toInt().toLong())
		assertEquals(1, sqrt(BigInteger.valueOf(2)).toInt().toLong())
		assertEquals(1, sqrt(BigInteger.valueOf(3)).toInt().toLong())
		assertEquals(2, sqrt(BigInteger.valueOf(4)).toInt().toLong())
		assertEquals(2, sqrt(BigInteger.valueOf(5)).toInt().toLong())
		assertEquals(2, sqrt(BigInteger.valueOf(8)).toInt().toLong())
		assertEquals(3, sqrt(BigInteger.valueOf(9)).toInt().toLong())
		assertEquals(3, sqrt(BigInteger.valueOf(10)).toInt().toLong())
		assertEquals(18, sqrt(BigInteger.valueOf(360)).toInt().toLong())
		assertEquals(19, sqrt(BigInteger.valueOf(361)).toInt().toLong())
		assertEquals(19, sqrt(BigInteger.valueOf(362)).toInt().toLong())
		assertEquals(256, sqrt(BigInteger.valueOf(65536)).toInt().toLong())
		assertEquals(32768, sqrt(BigInteger.valueOf(1073741824)).toInt().toLong())
		assertEquals(46340, sqrt(BigInteger.valueOf(2147483647)).toInt().toLong())
	}

	@Test
	fun testSqrtIntRandomly() {
		val trials = 1000000
		for (i in 0 until trials) {
			val x = rand.nextInt() ushr 1 // uint31
			val y = sqrt(BigInteger.valueOf(x.toLong())).toInt()
			assertTrue(y in 0..x)
			assertTrue(y.toLong() * y <= x && x < (y + 1L) * (y + 1L))
		}
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testSqrtIntInvalid0() {
		sqrt(BigInteger.valueOf(-1))
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testSqrtIntInvalid1() {
		sqrt(BigInteger.valueOf(-300000))
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testSqrtIntInvalid2() {
		sqrt(BigInteger.valueOf(Int.MIN_VALUE.toLong()))
	}

	@Test
	fun testSqrtLong() {
		assertEquals(0L, sqrt(BigInteger.valueOf(0L)).toLong())
		assertEquals(1L, sqrt(BigInteger.valueOf(1L)).toLong())
		assertEquals(1L, sqrt(BigInteger.valueOf(2L)).toLong())
		assertEquals(1L, sqrt(BigInteger.valueOf(3L)).toLong())
		assertEquals(2L, sqrt(BigInteger.valueOf(4L)).toLong())
		assertEquals(2L, sqrt(BigInteger.valueOf(5L)).toLong())
		assertEquals(2L, sqrt(BigInteger.valueOf(8L)).toLong())
		assertEquals(3L, sqrt(BigInteger.valueOf(9L)).toLong())
		assertEquals(3L, sqrt(BigInteger.valueOf(10L)).toLong())
		assertEquals(18L, sqrt(BigInteger.valueOf(360L)).toLong())
		assertEquals(19L, sqrt(BigInteger.valueOf(361L)).toLong())
		assertEquals(19L, sqrt(BigInteger.valueOf(362L)).toLong())
		assertEquals(256L, sqrt(BigInteger.valueOf(65536L)).toLong())
		assertEquals(32768L, sqrt(BigInteger.valueOf(1073741824L)).toLong())
		assertEquals(46340L, sqrt(BigInteger.valueOf(2147483648L)).toLong())
		assertEquals(2645751L, sqrt(BigInteger.valueOf(7000000000000L)).toLong())
		assertEquals(3037000499L, sqrt(BigInteger.valueOf(9223372036854775807L)).toLong())
	}

	@Test
	fun testSqrtLongRandomly() {
		val trials = 1000000
		for (i in 0 until trials) {
			val x = rand.nextLong() ushr 1 // uint63
			val y = sqrt(BigInteger.valueOf(x)).toLong()
			assertTrue(y in 0..x)
		}
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testSqrtLongInvalid0() {
		sqrt(BigInteger.valueOf(-1L))
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testSqrtLongInvalid1() {
		sqrt(BigInteger.valueOf(-3000000000L))
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testSqrtLongInvalid2() {
		sqrt(BigInteger.valueOf(Long.MIN_VALUE))
	}

	@Test
	fun testIsSquare() {
		assertTrue(isSquare(0))
		assertTrue(isSquare(1))
		assertTrue(isSquare(4))
		assertTrue(isSquare(9))
		assertTrue(isSquare(16))
		assertTrue(isSquare(25))
		assertTrue(isSquare(36))
		assertTrue(isSquare(100))
		assertTrue(isSquare(65536))
		assertTrue(isSquare(2147302921))
		assertTrue(isSquare(2147395600))
		assertFalse(isSquare(Int.MIN_VALUE))
		assertFalse(isSquare(Int.MIN_VALUE + 1))
		assertFalse(isSquare(-8654038))
		assertFalse(isSquare(-300))
		assertFalse(isSquare(-4))
		assertFalse(isSquare(-1))
		assertFalse(isSquare(2))
		assertFalse(isSquare(3))
		assertFalse(isSquare(5))
		assertFalse(isSquare(6))
		assertFalse(isSquare(7))
		assertFalse(isSquare(8))
		assertFalse(isSquare(120))
		assertFalse(isSquare(9999))
		assertFalse(isSquare(Int.MAX_VALUE - 1))
		assertFalse(isSquare(Int.MAX_VALUE))
	}

	@Test
	fun testPowMod() {
		assertEquals(0, powMod(0, 0, 1).toLong())
		assertEquals(0, powMod(1, 0, 1).toLong())
		assertEquals(0, powMod(0, 1, 1).toLong())
		assertEquals(0, powMod(1, 1, 1).toLong())
		assertEquals(0, powMod(2, 3, 1).toLong())
		assertEquals(1, powMod(0, 0, 2).toLong())
		assertEquals(1, powMod(1, 0, 2).toLong())
		assertEquals(1, powMod(2, 0, 2).toLong())
		assertEquals(0, powMod(0, 1, 2).toLong())
		assertEquals(1, powMod(1, 1, 2).toLong())
		assertEquals(0, powMod(2, 1, 2).toLong())
		assertEquals(0, powMod(0, 2, 2).toLong())
		assertEquals(1, powMod(1, 2, 2).toLong())
		assertEquals(0, powMod(2, 2, 2).toLong())
		assertEquals(1, powMod(2, 2, 3).toLong())
		assertEquals(4, powMod(4, 3, 5).toLong())
		assertEquals(3, powMod(7, 7, 10).toLong())
		assertEquals(326216098, powMod(78051657, 234602, 456087413).toLong())
		assertEquals(1488576545, powMod(2147480000, 2147483645, 2147483647).toLong())
	}

	@Test
	fun testReciprocalMod() {
		listOf(intArrayOf(1, 2, 1), intArrayOf(1, 3, 1), intArrayOf(2, 3, 2), intArrayOf(1, 4, 1), intArrayOf(3, 4, 3), intArrayOf(1, 5, 1), intArrayOf(2, 5, 3), intArrayOf(3, 5, 2), intArrayOf(4, 5, 4), intArrayOf(2, 7, 4), intArrayOf(3, 7, 5), intArrayOf(4, 7, 2), intArrayOf(5, 7, 3), intArrayOf(6, 7, 6), intArrayOf(18585, 26128, 5705), intArrayOf(4352341, 7559949, 3054661), intArrayOf(290514683, 936234758, 903930729), intArrayOf(735803087, 1384775511, 1321131185), intArrayOf(1, 2147483647, 1), intArrayOf(2, 2147483647, 1073741824), intArrayOf(188080773, 2147483647, 1201032874), intArrayOf(527995520, 2147483647, 1215591224), intArrayOf(1154582780, 2147483647, 193267031), intArrayOf(1321286464, 2147483647, 95844396), intArrayOf(2147483645, 2147483647, 1073741823), intArrayOf(2147483646, 2147483647, 2147483646)).forEach(Consumer { ints: IntArray -> assertEquals(ints[2], reciprocalMod(ints[0], ints[1]).toLong()) })
		listOf(intArrayOf(Int.MIN_VALUE, Int.MIN_VALUE), intArrayOf(-1, -1), intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(0, 0), intArrayOf(1, 1), intArrayOf(3, 2), intArrayOf(Int.MAX_VALUE, 1), intArrayOf(2, 4), intArrayOf(2, 6), intArrayOf(3, 6), intArrayOf(44100, 48000), intArrayOf(77, 2147483646), intArrayOf(30783, 2147483646)).forEach(Consumer {
			try {
				fail()
			} catch (ignored: IllegalArgumentException) {
			}/*Pass*/
		})
	}

	@Test
	fun testReciprocalModRandomly() {
		for (i in 0..99999) {
			val mod = rand.nextInt() ushr 1 // uint31
			if (mod < 2) continue
			val x = rand.nextInt(mod)
			if (gcd(x, mod) == 1) {
				val y = reciprocalMod(x, mod)
				assertTrue(y in 1 until mod)
				assertEquals(1, x.toLong() * y % mod)
				assertEquals(x.toLong(), reciprocalMod(y, mod).toLong())
			} else {
				try {
					fail()
				} catch (ignored: IllegalArgumentException) {
				} // Pass
			}
		}
	}

	@Test
	fun testFactorial() {
		assertEquals(BigInteger("1"), factorial(0))
		assertEquals(BigInteger("1"), factorial(1))
		assertEquals(BigInteger("2"), factorial(2))
		assertEquals(BigInteger("6"), factorial(3))
		assertEquals(BigInteger("24"), factorial(4))
		assertEquals(BigInteger("120"), factorial(5))
		assertEquals(BigInteger("720"), factorial(6))
		assertEquals(BigInteger("6227020800"), factorial(13))
		assertEquals(BigInteger("51090942171709440000"), factorial(21))
		assertEquals(BigInteger("265252859812191058636308480000000"), factorial(30))
	}

	@Test(expected = IllegalArgumentException::class)
	fun testFactorialInvalid0() {
		factorial(-1)
	}

	@Test(expected = IllegalArgumentException::class)
	fun testFactorialInvalid1() {
		factorial(-563)
	}

	@Test(expected = IllegalArgumentException::class)
	fun testFactorialInvalid2() {
		factorial(Int.MIN_VALUE)
	}

	@Test
	fun testBinomial() {
		assertEquals(BigInteger("1"), binomial(0, 0))
		assertEquals(BigInteger("1"), binomial(1, 0))
		assertEquals(BigInteger("1"), binomial(1, 1))
		assertEquals(BigInteger("1"), binomial(2, 0))
		assertEquals(BigInteger("2"), binomial(2, 1))
		assertEquals(BigInteger("1"), binomial(2, 2))
		assertEquals(BigInteger("1"), binomial(3, 0))
		assertEquals(BigInteger("3"), binomial(3, 1))
		assertEquals(BigInteger("3"), binomial(3, 2))
		assertEquals(BigInteger("1"), binomial(3, 3))
		assertEquals(BigInteger("35"), binomial(7, 4))
		assertEquals(BigInteger("120"), binomial(10, 7))
		assertEquals(BigInteger("21"), binomial(21, 20))
		assertEquals(BigInteger("88749815264600"), binomial(50, 28))
	}

	@Test
	fun testGcd() {
		assertEquals(0, gcd(0, 0).toLong())
		assertEquals(1, gcd(0, 1).toLong())
		assertEquals(1, gcd(1, 0).toLong())
		assertEquals(6, gcd(0, 6).toLong())
		assertEquals(6, gcd(6, 0).toLong())
		assertEquals(1, gcd(1, 1).toLong())
		assertEquals(2, gcd(2, 2).toLong())
		assertEquals(1, gcd(2, 3).toLong())
		assertEquals(1, gcd(10, 3).toLong())
		assertEquals(3, gcd(9, 3).toLong())
		assertEquals(2, gcd(6, 4).toLong())
		assertEquals(2, gcd(18, 14).toLong())
		assertEquals(300, gcd(44100, 48000).toLong())
		assertEquals(2147483647, gcd(0, 2147483647).toLong())
		assertEquals(2147483647, gcd(2147483647, 2147483647).toLong())
		assertEquals(1, gcd(2147483646, 2147483647).toLong())
	}

	@Test
	fun testGcdRandomly() {
		val trials = 1000000
		for (i in 0 until trials) {
			val x = rand.nextInt() ushr 1 // uint31
			val y = rand.nextInt() ushr 1 // uint31
			val z = gcd(x, y)
			if (x == 0) assertEquals(y.toLong(), z.toLong()) else if (y == 0) assertEquals(x.toLong(), z.toLong()) else assertTrue(z in 1..x && z <= y && x % z == 0 && y % z == 0) // x, y > 0
		}
	}

	@Test
	fun testIsPrime() {
		assertFalse(isPrime(0))
		assertFalse(isPrime(1))
		assertTrue(isPrime(2))
		assertTrue(isPrime(3))
		assertFalse(isPrime(4))
		assertTrue(isPrime(5))
		assertFalse(isPrime(6))
		assertTrue(isPrime(7))
		assertFalse(isPrime(8))
		assertFalse(isPrime(9))
		assertFalse(isPrime(10))
		assertTrue(isPrime(11))
		assertFalse(isPrime(12))
		assertTrue(isPrime(13))
		assertFalse(isPrime(14))
		assertFalse(isPrime(15))
		assertFalse(isPrime(16))
		assertTrue(isPrime(17))
		assertFalse(isPrime(18))
		assertTrue(isPrime(19))
		assertFalse(isPrime(20))
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testIsPrimeInvalid0() {
		println(isPrime(-1))
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testIsPrimeInvalid1() {
		println(isPrime(-3000))
	}

	@Test
	fun testListPrimality() {
		val isPrime = listPrimality(1000)
		for (i in isPrime.indices) assertEquals(isPrime(i), isPrime[i])
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testListPrimalityInvalid0() {
		println(listPrimality(-1).contentToString())
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testListPrimalityInvalid1() {
		println(listPrimality(-3000).contentToString())
	}

	@Test
	fun testListPrimes() {
		val limit = 1000
		val primes = listPrimes(limit)
		for (i in 0 until primes.size - 1) assertTrue(primes[i] < primes[i + 1])
		for (i in 0..limit) assertEquals(isPrime(i), binarySearch(primes, i) >= 0)
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testListPrimesInvalid0() {
		println(listPrimes(-1).contentToString())
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testListPrimesInvalid1() {
		println(listPrimes(-3000).contentToString())
	}

	@Test
	fun testTotient() {
		assertEquals(1, totient(1).toLong())
		assertEquals(1, totient(2).toLong())
		assertEquals(2, totient(3).toLong())
		assertEquals(2, totient(4).toLong())
		assertEquals(4, totient(5).toLong())
		assertEquals(2, totient(6).toLong())
		assertEquals(6, totient(7).toLong())
		assertEquals(4, totient(8).toLong())
		assertEquals(6, totient(9).toLong())
		assertEquals(4, totient(10).toLong())
		assertEquals(10, totient(11).toLong())
		assertEquals(4, totient(12).toLong())
		assertEquals(12, totient(13).toLong())
		assertEquals(6, totient(14).toLong())
		assertEquals(8, totient(15).toLong())
		assertEquals(8, totient(16).toLong())
		assertEquals(16, totient(17).toLong())
		assertEquals(6, totient(18).toLong())
		assertEquals(18, totient(19).toLong())
		assertEquals(8, totient(20).toLong())
	}

	@Test
	fun testTotientRandomly() {
		val trials = 100
		for (i in 0 until trials) {
			val n = rand.nextInt(100000) + 1
			var tot = 0
			for (j in 1..n) if (gcd(j, n) == 1) tot++
			assertEquals(tot.toLong(), totient(n).toLong())
		}
	}

	@Test(expected = IllegalArgumentException::class)
	fun testTotientInvalid0() {
		println(totient(-1))
	}

	@Test(expected = IllegalArgumentException::class)
	fun testTotientInvalid1() {
		println(totient(-3000))
	}

	@Test
	fun testListTotients() {
		val totients = listTotients(1000)
		for (i in 1 until totients.size) assertEquals(totient(i).toLong(), totients[i])
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testListTotientsInvalid0() {
		println(listTotients(-1).contentToString())
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException::class)
	fun testListTotientsInvalid1() {
		println(listTotients(-3000).contentToString())
	}

	@Test
	fun testNextPermutation() {
		assertFalse(nextPermutation(IntArray(0)))
		var arr = intArrayOf(0, 0, 1)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(0, 1, 0), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 0, 0), arr)
		assertFalse(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 0, 0), arr)
		arr = intArrayOf(1, 2, 3, 5, 9)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 2, 3, 9, 5), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 2, 5, 3, 9), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 2, 5, 9, 3), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 2, 9, 3, 5), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 2, 9, 5, 3), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 3, 2, 5, 9), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 3, 2, 9, 5), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 3, 5, 2, 9), arr)
		assertTrue(nextPermutation(arr))
		assertArrayEquals(intArrayOf(1, 3, 5, 9, 2), arr)
		for (i in 0..109) assertTrue(nextPermutation(arr))
		assertFalse(nextPermutation(arr))
		assertArrayEquals(intArrayOf(9, 5, 3, 2, 1), arr)
	}

	companion object {
		private val rand = Random()
	}
}