package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.Arrays.binarySearch;
import static org.junit.Assert.*;
import static projectEuler.programs.Library.*;

public final class LibraryTest {
	private static final Random rand = new Random();

	@Test
	public void testReverse() {
		assertEquals("", reverse(""));
		assertEquals("a", reverse("a"));
		assertEquals("ba", reverse("ab"));
		assertEquals("001", reverse("100"));
		assertEquals("a0a", reverse("a0a"));
	}

	@Test
	public void testIsPalindromeString() {
		assertTrue(isPalindrome(""));
		assertTrue(isPalindrome("a"));
		assertTrue(isPalindrome("aa"));
		assertTrue(isPalindrome("aaa"));
		assertTrue(isPalindrome("aaaa"));
		assertTrue(isPalindrome("aba"));
		assertTrue(isPalindrome("abba"));
		assertTrue(isPalindrome("abbba"));
		assertTrue(isPalindrome("acbca"));
		assertFalse(isPalindrome("ab"));
		assertFalse(isPalindrome("ba"));
		assertFalse(isPalindrome("aaba"));
		assertFalse(isPalindrome("abcd"));
	}

	@Test
	public void testIsPalindromeInt() {
		assertTrue(isPalindrome(0));
		assertTrue(isPalindrome(1));
		assertTrue(isPalindrome(5));
		assertTrue(isPalindrome(11));
		assertTrue(isPalindrome(33));
		assertTrue(isPalindrome(101));
		assertTrue(isPalindrome(151));
		assertTrue(isPalindrome(737));
		assertTrue(isPalindrome(2222));
		assertTrue(isPalindrome(5665));
		assertTrue(isPalindrome(2147447412));
		assertFalse(isPalindrome(12));
		assertFalse(isPalindrome(43));
		assertFalse(isPalindrome(220));
		assertFalse(isPalindrome(1010));
		assertFalse(isPalindrome(2147483647));
	}

	@Test
	public void testSqrtInt() {
		assertEquals(0, sqrt(0));
		assertEquals(1, sqrt(1));
		assertEquals(1, sqrt(2));
		assertEquals(1, sqrt(3));
		assertEquals(2, sqrt(4));
		assertEquals(2, sqrt(5));
		assertEquals(2, sqrt(8));
		assertEquals(3, sqrt(9));
		assertEquals(3, sqrt(10));
		assertEquals(18, sqrt(360));
		assertEquals(19, sqrt(361));
		assertEquals(19, sqrt(362));
		assertEquals(256, sqrt(65536));
		assertEquals(32768, sqrt(1073741824));
		assertEquals(46340, sqrt(2147483647));
	}

	@Test
	public void testSqrtIntRandomly() {
		final int trials = 1000000;
		for (int i = 0; i < trials; i++) {
			int x = rand.nextInt() >>> 1; // uint31
			int y = sqrt(x);
			assertTrue(0 <= y && y <= x);
			assertTrue((((long) y * y) <= x) && (x < ((y + 1L) * (y + 1L))));
		}
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testSqrtIntInvalid0() {
		sqrt(-1);
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testSqrtIntInvalid1() {
		sqrt(-300000);
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testSqrtIntInvalid2() {
		sqrt(MIN_VALUE);
	}

	@Test
	public void testSqrtLong() {
		assertEquals(0L, sqrt(0L));
		assertEquals(1L, sqrt(1L));
		assertEquals(1L, sqrt(2L));
		assertEquals(1L, sqrt(3L));
		assertEquals(2L, sqrt(4L));
		assertEquals(2L, sqrt(5L));
		assertEquals(2L, sqrt(8L));
		assertEquals(3L, sqrt(9L));
		assertEquals(3L, sqrt(10L));
		assertEquals(18L, sqrt(360L));
		assertEquals(19L, sqrt(361L));
		assertEquals(19L, sqrt(362L));
		assertEquals(256L, sqrt(65536L));
		assertEquals(32768L, sqrt(1073741824L));
		assertEquals(46340L, sqrt(2147483648L));
		assertEquals(2645751L, sqrt(7000000000000L));
		assertEquals(3037000499L, sqrt(9223372036854775807L));
	}

	@Test
	public void testSqrtLongRandomly() {
		final int trials = 1000000;
		for (int i = 0; i < trials; i++) {
			long x = rand.nextLong() >>> 1; // uint63
			long y = sqrt(x);
			assertTrue(0 <= y && y <= x);
			if (x > 0) assertTrue(y <= x / y && x / (y + 1) < y + 1);
		}
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testSqrtLongInvalid0() {
		sqrt(-1L);
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testSqrtLongInvalid1() {
		sqrt(-3000000000L);
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testSqrtLongInvalid2() {
		sqrt(Long.MIN_VALUE);
	}

	@Test
	public void testIsSquare() {
		assertTrue(isSquare(0));
		assertTrue(isSquare(1));
		assertTrue(isSquare(4));
		assertTrue(isSquare(9));
		assertTrue(isSquare(16));
		assertTrue(isSquare(25));
		assertTrue(isSquare(36));
		assertTrue(isSquare(100));
		assertTrue(isSquare(65536));
		assertTrue(isSquare(2147302921));
		assertTrue(isSquare(2147395600));
		assertFalse(isSquare(MIN_VALUE));
		assertFalse(isSquare(MIN_VALUE + 1));
		assertFalse(isSquare(-8654038));
		assertFalse(isSquare(-300));
		assertFalse(isSquare(-4));
		assertFalse(isSquare(-1));
		assertFalse(isSquare(2));
		assertFalse(isSquare(3));
		assertFalse(isSquare(5));
		assertFalse(isSquare(6));
		assertFalse(isSquare(7));
		assertFalse(isSquare(8));
		assertFalse(isSquare(120));
		assertFalse(isSquare(9999));
		assertFalse(isSquare(MAX_VALUE - 1));
		assertFalse(isSquare(MAX_VALUE));
	}

	@Test
	public void testPowMod() {
		assertEquals(0, powMod(0, 0, 1));
		assertEquals(0, powMod(1, 0, 1));
		assertEquals(0, powMod(0, 1, 1));
		assertEquals(0, powMod(1, 1, 1));
		assertEquals(0, powMod(2, 3, 1));
		assertEquals(1, powMod(0, 0, 2));
		assertEquals(1, powMod(1, 0, 2));
		assertEquals(1, powMod(2, 0, 2));
		assertEquals(0, powMod(0, 1, 2));
		assertEquals(1, powMod(1, 1, 2));
		assertEquals(0, powMod(2, 1, 2));
		assertEquals(0, powMod(0, 2, 2));
		assertEquals(1, powMod(1, 2, 2));
		assertEquals(0, powMod(2, 2, 2));
		assertEquals(1, powMod(2, 2, 3));
		assertEquals(4, powMod(4, 3, 5));
		assertEquals(3, powMod(7, 7, 10));
		assertEquals(326216098, powMod(78051657, 234602, 456087413));
		assertEquals(1488576545, powMod(2147480000, 2147483645, 2147483647));
	}

	@Test
	public void testReciprocalMod() {
		asList(new int[]{1, 2, 1}, new int[]{1, 3, 1}, new int[]{2, 3, 2}, new int[]{1, 4, 1}, new int[]{3, 4, 3}, new int[]{1, 5, 1}, new int[]{2, 5, 3}, new int[]{3, 5, 2}, new int[]{4, 5, 4}, new int[]{2, 7, 4}, new int[]{3, 7, 5}, new int[]{4, 7, 2}, new int[]{5, 7, 3}, new int[]{6, 7, 6}, new int[]{18585, 26128, 5705}, new int[]{4352341, 7559949, 3054661}, new int[]{290514683, 936234758, 903930729}, new int[]{735803087, 1384775511, 1321131185}, new int[]{1, 2147483647, 1}, new int[]{2, 2147483647, 1073741824}, new int[]{188080773, 2147483647, 1201032874}, new int[]{527995520, 2147483647, 1215591224}, new int[]{1154582780, 2147483647, 193267031}, new int[]{1321286464, 2147483647, 95844396}, new int[]{2147483645, 2147483647, 1073741823}, new int[]{2147483646, 2147483647, 2147483646}).forEach(ints -> assertEquals(ints[2], reciprocalMod(ints[0], ints[1])));
		asList(new int[]{MIN_VALUE, MIN_VALUE},
				new int[]{-1, -1},
				new int[]{0, -1},
				new int[]{-1, 0},
				new int[]{0, 0},
				new int[]{1, 1},
				new int[]{3, 2},
				new int[]{MAX_VALUE, 1},
				new int[]{2, 4},
				new int[]{2, 6},
				new int[]{3, 6},
				new int[]{44100, 48000},
				new int[]{77, 2147483646},
				new int[]{30783, 2147483646}).forEach(
						i -> {
			try {
				var x = reciprocalMod(i[0], i[1]);
				fail();
			} catch (IllegalArgumentException ignored) {
			} // Pass
		});
	}

	@Test
	public void testReciprocalModRandomly() {
		for (int i = 0; i < 100000; i++) {
			int mod = rand.nextInt() >>> 1; // uint31
			if (mod < 2) continue;
			int x = rand.nextInt(mod);
			if (gcd(x, mod) == 1) {
				int y = reciprocalMod(x, mod);
				assertTrue(1 <= y && y < mod);
				assertEquals(1, (long) x * y % mod);
				assertEquals(x, reciprocalMod(y, mod));
			} else {
				try {
					reciprocalMod(x, mod);
					fail();
				} catch (IllegalArgumentException ignored) {
				} // Pass
			}
		}
	}

	@Test
	public void testFactorial() {
		assertEquals(new BigInteger("1"), factorial(0));
		assertEquals(new BigInteger("1"), factorial(1));
		assertEquals(new BigInteger("2"), factorial(2));
		assertEquals(new BigInteger("6"), factorial(3));
		assertEquals(new BigInteger("24"), factorial(4));
		assertEquals(new BigInteger("120"), factorial(5));
		assertEquals(new BigInteger("720"), factorial(6));
		assertEquals(new BigInteger("6227020800"), factorial(13));
		assertEquals(new BigInteger("51090942171709440000"), factorial(21));
		assertEquals(new BigInteger("265252859812191058636308480000000"), factorial(30));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFactorialInvalid0() {
		factorial(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFactorialInvalid1() {
		factorial(-563);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFactorialInvalid2() {
		factorial(MIN_VALUE);
	}

	@Test
	public void testBinomial() {
		assertEquals(new BigInteger("1"), binomial(0, 0));
		assertEquals(new BigInteger("1"), binomial(1, 0));
		assertEquals(new BigInteger("1"), binomial(1, 1));
		assertEquals(new BigInteger("1"), binomial(2, 0));
		assertEquals(new BigInteger("2"), binomial(2, 1));
		assertEquals(new BigInteger("1"), binomial(2, 2));
		assertEquals(new BigInteger("1"), binomial(3, 0));
		assertEquals(new BigInteger("3"), binomial(3, 1));
		assertEquals(new BigInteger("3"), binomial(3, 2));
		assertEquals(new BigInteger("1"), binomial(3, 3));
		assertEquals(new BigInteger("35"), binomial(7, 4));
		assertEquals(new BigInteger("120"), binomial(10, 7));
		assertEquals(new BigInteger("21"), binomial(21, 20));
		assertEquals(new BigInteger("88749815264600"), binomial(50, 28));
	}

	@Test
	public void testGcd() {
		assertEquals(0, gcd(0, 0));
		assertEquals(1, gcd(0, 1));
		assertEquals(1, gcd(1, 0));
		assertEquals(6, gcd(0, 6));
		assertEquals(6, gcd(6, 0));
		assertEquals(1, gcd(1, 1));
		assertEquals(2, gcd(2, 2));
		assertEquals(1, gcd(2, 3));
		assertEquals(1, gcd(10, 3));
		assertEquals(3, gcd(9, 3));
		assertEquals(2, gcd(6, 4));
		assertEquals(2, gcd(18, 14));
		assertEquals(300, gcd(44100, 48000));
		assertEquals(2147483647, gcd(0, 2147483647));
		assertEquals(2147483647, gcd(2147483647, 2147483647));
		assertEquals(1, gcd(2147483646, 2147483647));
	}

	@Test
	public void testGcdRandomly() {
		final int trials = 1000000;
		for (int i = 0; i < trials; i++) {
			int x = rand.nextInt() >>> 1; // uint31
			int y = rand.nextInt() >>> 1; // uint31
			int z = gcd(x, y);
			if (x == 0) assertEquals(y, z);
			else if (y == 0) assertEquals(x, z);
			else assertTrue((0 < z) && (z <= x) && (z <= y) && ((x % z) == 0) && ((y % z) == 0)); // x, y > 0
		}
	}

	@Test
	public void testIsPrime() {
		assertFalse(isPrime(0));
		assertFalse(isPrime(1));
		assertTrue(isPrime(2));
		assertTrue(isPrime(3));
		assertFalse(isPrime(4));
		assertTrue(isPrime(5));
		assertFalse(isPrime(6));
		assertTrue(isPrime(7));
		assertFalse(isPrime(8));
		assertFalse(isPrime(9));
		assertFalse(isPrime(10));
		assertTrue(isPrime(11));
		assertFalse(isPrime(12));
		assertTrue(isPrime(13));
		assertFalse(isPrime(14));
		assertFalse(isPrime(15));
		assertFalse(isPrime(16));
		assertTrue(isPrime(17));
		assertFalse(isPrime(18));
		assertTrue(isPrime(19));
		assertFalse(isPrime(20));
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testIsPrimeInvalid0() {
		out.println(isPrime(-1));
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testIsPrimeInvalid1() {
		out.println(isPrime(-3000));
	}

	@Test
	public void testListPrimality() {
		boolean[] isPrime = listPrimality(1000);
		for (int i = 0; i < isPrime.length; i++) assertEquals(isPrime(i), isPrime[i]);
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testListPrimalityInvalid0() {
		out.println(Arrays.toString(listPrimality(-1)));
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testListPrimalityInvalid1() {
		out.println(Arrays.toString(listPrimality(-3000)));
	}

	@Test
	public void testListPrimes() {
		int limit = 1000;
		int[] primes = listPrimes(limit);
		for (int i = 0; i < primes.length - 1; i++) assertTrue(primes[i] < primes[i + 1]);
		for (int i = 0; i <= limit; i++) assertEquals(isPrime(i), binarySearch(primes, i) >= 0);
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testListPrimesInvalid0() {
		out.println(Arrays.toString(listPrimes(-1)));
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testListPrimesInvalid1() {
		out.println(Arrays.toString(listPrimes(-3000)));
	}

	@Test
	public void testTotient() {
		assertEquals(1, totient(1));
		assertEquals(1, totient(2));
		assertEquals(2, totient(3));
		assertEquals(2, totient(4));
		assertEquals(4, totient(5));
		assertEquals(2, totient(6));
		assertEquals(6, totient(7));
		assertEquals(4, totient(8));
		assertEquals(6, totient(9));
		assertEquals(4, totient(10));
		assertEquals(10, totient(11));
		assertEquals(4, totient(12));
		assertEquals(12, totient(13));
		assertEquals(6, totient(14));
		assertEquals(8, totient(15));
		assertEquals(8, totient(16));
		assertEquals(16, totient(17));
		assertEquals(6, totient(18));
		assertEquals(18, totient(19));
		assertEquals(8, totient(20));
	}

	@Test
	public void testTotientRandomly() {
		final int trials = 100;
		for (int i = 0; i < trials; i++) {
			int n = rand.nextInt(100000) + 1;
			int tot = 0;
			for (int j = 1; j <= n; j++) if (gcd(j, n) == 1) tot++;
			assertEquals(tot, totient(n));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTotientInvalid0() {
		out.println(totient(-1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTotientInvalid1() {
		out.println(totient(-3000));
	}

	@Test
	public void testListTotients() {
		int[] totients = listTotients(1000);
		for (int i = 1; i < totients.length; i++) assertEquals(totient(i), totients[i]);
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testListTotientsInvalid0() {
		out.println(Arrays.toString(listTotients(-1)));
	}

	@Contract(pure = true)
	@Test(expected = IllegalArgumentException.class)
	public void testListTotientsInvalid1() {
		out.println(Arrays.toString(listTotients(-3000)));
	}

	@Test
	public void testNextPermutation() {
		int[] arr;
		assertFalse(nextPermutation(new int[0]));
		arr = new int[]{0, 0, 1};
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{0, 1, 0}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 0, 0}, arr);
		assertFalse(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 0, 0}, arr);
		arr = new int[]{1, 2, 3, 5, 9};
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 2, 3, 9, 5}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 2, 5, 3, 9}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 2, 5, 9, 3}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 2, 9, 3, 5}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 2, 9, 5, 3}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 3, 2, 5, 9}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 3, 2, 9, 5}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 3, 5, 2, 9}, arr);
		assertTrue(nextPermutation(arr));
		assertArrayEquals(new int[]{1, 3, 5, 9, 2}, arr);
		for (int i = 0; i < 110; i++) assertTrue(nextPermutation(arr));
		assertFalse(nextPermutation(arr));
		assertArrayEquals(new int[]{9, 5, 3, 2, 1}, arr);
	}
}