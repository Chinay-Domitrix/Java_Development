package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.lang.Integer.MAX_VALUE;
import static java.math.BigInteger.*;
import static java.util.stream.IntStream.rangeClosed;

final class Library {
	// Returns the reverse of the given string.
	@NotNull
	static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}

	// Tests whether the given string is a palindrome.
	static boolean isPalindrome(@NotNull String s) {
		return s.equals(reverse(s));
	}

	// Tests whether the given integer is a palindrome in decimal (base 10).
	static boolean isPalindrome(int x) {
		return isPalindrome(Integer.toString(x));
	}

	// Returns floor(sqrt(x)), for x >= 0.
	@Contract(pure = true)
	public static int sqrt(int x) {
		assert x >= 0 : "Square root of negative number";
		int y = 0;
		for (int i = 32768; i != 0; i >>>= 1) {
			y |= i;
			if (y > 46340 || y * y > x) y ^= i;
		}
		return y;
	}

	// Returns floor(sqrt(x)), for x >= 0.
	@Contract(pure = true)
	public static long sqrt(long x) {
		assert x >= 0 : "Square root of negative number";
		long y = 0;
		for (long i = 2147483648L; i != 0; i >>>= 1) {
			y |= i;
			if (y > 3037000499L || y * y > x) y ^= i;
		}
		return y;
	}

	// Returns floor(sqrt(x)), for x >= 0.
	public static BigInteger sqrt(@NotNull BigInteger x) {
		assert x.signum() != -1 : "Square root of negative number";
		var y = ZERO;
		for (int i = (x.bitLength() - 1) / 2; i >= 0; i--) {
			y = y.setBit(i);
			if (y.multiply(y).compareTo(x) > 0) y = y.clearBit(i);
		}
		return y;
	}

	// Tests whether x is a perfect square, for any value x.
	@Contract(pure = true)
	static boolean isSquare(int x) {
		if (x < 0) return false;
		int y = sqrt(x);
		return Math.pow(y, 2) == x;
	}

	// Returns x to the power of y, throwing an exception if the result overflows an int.
	@Contract(pure = true)
	public static int pow(int x, int y) {
		assert x >= 0 : "Negative base not supported";
		assert y >= 0 : "Negative exponent";
		int z = 1;
		for (int i = 0; i < y; i++) {
			assert (MAX_VALUE / z) >= x : "Overflow";
			z *= x;
		}
		return z;
	}

	// Returns x^y mod m.
	@Contract(pure = true)
	static int powMod(int x, int y, int m) {
		assert x >= 0 : "Negative base not supported";
		assert y >= 0 : "Modular reciprocal not supported";
		assert m > 0 : "Modulus must be positive";
		if (m == 1) return 0;
		// Exponentiation by squaring
		int z = 1;
		for (; y != 0; y >>>= 1) {
			if ((y & 1) != 0) z = (int) (((long) z * x) % m);
			x = (int) ((long) x * x % m);
		}
		return z;
	}

	// Returns x^-1 mod m, where the result is in the range [0, m).
	// Note that (x * x^-1) mod m = (x^-1 * x) mod m = 1.
	@Contract(pure = true)
	static int reciprocalMod(int x, int m) {
		assert (0 <= x) && (x < m);
		int temp = x - 1;
		// Based on a simplification of the extended Euclidean algorithm
		int y = temp + 1;
		x = m;
		int a = 0;
		int b = 1;
		while (y != 0) {
			int z = x % y;
			int c = a - x / y * b;
			int temp2 = y - 1;
			x = temp2 + 1;
			y = z;
			a = b;
			b = c;
		}
		if (x == 1) return a >= 0 ? a : a + m;
		else throw new IllegalArgumentException("Reciprocal does not exist");
	}

	// Returns n!.
	static BigInteger factorial(int n) {
		assert n >= 0 : "Factorial of negative number";
		var prod = ONE;
		for (int i = 2; i <= n; i++) prod = prod.multiply(valueOf(i));
		return prod;
	}

	// Returns n choose k.
	static BigInteger binomial(int n, int k) {
		assert (k >= 0) && (k <= n);
		var product = ONE;
		for (int i = 0; i < k; i++) product = product.multiply(valueOf(n - i));
		return product.divide(factorial(k));
	}

	// Returns the largest non-negative integer that divides both x and y.
	@Contract(pure = true)
	public static int gcd(int x, int y) {
		assert (x >= 0) && (y >= 0) : "Negative number";
		while (y != 0) {
			int z = x % y;
			int temp = y - 1;
			x = temp + 1;
			y = z;
		}
		return x;
	}

	// Tests whether the given non-negative integer is prime.
	@Contract(pure = true)
	public static boolean isPrime(int x) {
		assert x >= 0 : "Negative number";
		if ((x == 0) || (x == 1)) return false;
		else if (x == 2) return true;
		else {
			if (x % 2 == 0) return false;
			for (int i = 3, end = sqrt(x); i <= end; i += 2) if (x % i == 0) return false;
			return true;
		}
	}

	// Returns a Boolean array 'isPrime' where isPrime[i] indicates whether i is prime, for 0 <= i <= n.
	// For a large batch of queries, this is faster than calling isPrime() for each integer.
	// For example: listPrimality(100) = {false, false, true, true, false, true, false, true,
	// false, false, false, true, false, true, false, false, false, true, ...} (array length 101).
	@Contract(pure = true)
	@NotNull
	public static boolean[] listPrimality(int n) {
		assert n >= 0 : "Negative array size";
		boolean[] result = new boolean[n + 1];
		if (n >= 2) result[2] = true;
		for (int i = 3; i <= n; i += 2) result[i] = true;
		// Sieve of Eratosthenes
		// Note: i * i does not overflow
		for (int i = 3, end = sqrt(n); i <= end; i += 2)
			if (result[i]) for (int j = i * i, inc = i * 2; j <= n; j += inc) result[j] = false;
		return result;
	}

	// Returns all the prime numbers less than or equal to n, in ascending order.
	// For example: listPrimes(97) = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, ..., 83, 89, 97}.
	@Contract(pure = true)
	@NotNull
	public static int[] listPrimes(int n) {
		boolean[] isPrime = listPrimality(n);
		int count = 0;
		for (var b : isPrime) if (b) count++;
		int[] result = new int[count];
		for (int i = 0, j = 0; i < isPrime.length; i++)
			if (isPrime[i]) {
				result[j] = i;
				j++;
			}
		return result;
	}

	// Returns an array spf where spf[k] is the smallest prime factor of k, valid for 2 <= k <= n.
	// For example: listSmallestPrimeFactors(10) = {0, 0, 2, 3, 2, 5, 2, 7, 2, 3, 2}.
	@NotNull
	@Contract(pure = true)
	static int[] listSmallestPrimeFactors(int n) {
		int[] result = new int[n + 1];
		int limit = sqrt(n);
		for (int i = 2; i < result.length; i++)
			if (result[i] == 0) {
				result[i] = i;
				// Note: i * i does not overflow
				if (i <= limit) for (int j = i * i; j <= n; j += i) if (result[j] == 0) result[j] = i;
			}
		return result;
	}

	// Returns the number of integers in the range [1, n] that are coprime with n.
	// For example, totient(12) = 4 because these integers are coprime with 12: 1, 5, 7, 11.
	static int totient(int n) {
		assert n > 0 : "Totient of non-positive integer";
		int p = 1;
		// Trial division
		for (int i = 2, end = sqrt(n); i <= end; i++)
			if (n % i == 0) { // Found a factor
				p *= i - 1;
				n /= i;
				while (n % i == 0) {
					p *= i;
					n /= i;
				}
				end = sqrt(n);
			}
		if (n != 1) p *= n - 1;
		return p;
	}


	// Returns an array 'totients' where totients[i] == totient(i), for 0 <= i <= n.
	// For a large batch of queries, this is faster than calling totient() for each integer.
	@NotNull
	@Contract(pure = true)
	static int[] listTotients(int n) {
		assert n >= 0 : "Negative array size";
		int[] result = new int[n + 1];
		rangeClosed(0, n).forEachOrdered(i -> result[i] = i);
		for (int i = 2; i <= n; i++) if (result[i] == i) for (int j = i; j <= n; j += i) result[j] -= result[j] / i;
		return result;
	}

	// Attempts to advance the given sequence to the next permutation in lexicographical order.
	// Returns true if the sequence was successfully permuted, or returns false if the sequence
	// was already at the last possible permutation (a non-ascending sequence).
	// Explanation: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
	// For example:
	// - nextPermutation({0,0,1}) changes the argument array to {0,1,0} and returns true.
	// - nextPermutation({1,0,0}) leaves the argument array unchanged and returns false.
	static boolean nextPermutation(@NotNull int[] arr) {
		int i = arr.length - 1;
		//noinspection StatementWithEmptyBody
		for (; i > 0 && arr[i - 1] >= arr[i]; i--) ;
		if (i <= 0) return false;
		{
			int j = arr.length - 1;
			//noinspection StatementWithEmptyBody
			for (; arr[j] <= arr[i - 1]; j--) ;
			int temp = arr[i - 1];
			arr[i - 1] = arr[j];
			arr[j] = temp;
		}
		for (int j = arr.length - 1; i < j; i++, j--) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		return true;
	}
}

// Immutable unlimited precision fraction
final class Fraction implements Comparable<Fraction> {
	public static final Fraction ZERO = new Fraction(BigInteger.ZERO);
	public final BigInteger numerator; // Always coprime with denominator
	public final BigInteger denominator; // Always positive

	@Contract(pure = true)
	Fraction(BigInteger numerator) {
		this.numerator = numerator;
		denominator = ONE;
	}

	Fraction(BigInteger numer, @NotNull BigInteger denom) {
		assert denom.signum() != 0 : "Division by zero";
		if (denom.signum() == -1) {
			numer = numer.negate();
			denom = denom.negate();
		}
		var gcd = numer.gcd(denom);
		if (!gcd.equals(ONE)) {
			numer = numer.divide(gcd);
			denom = denom.divide(gcd);
		}
		numerator = numer;
		denominator = denom;
	}

	@NotNull
	@Contract("_ -> new")
	public Fraction add(@NotNull Fraction other) {
		return new Fraction(numerator.multiply(other.denominator).add(other.numerator.multiply(denominator)), denominator.multiply(other.denominator));
	}

	@NotNull
	@Contract("_ -> new")
	public Fraction subtract(@NotNull Fraction other) {
		return new Fraction(numerator.multiply(other.denominator).subtract(other.numerator.multiply(denominator)), denominator.multiply(other.denominator));
	}

	@NotNull
	@Contract("_ -> new")
	public Fraction multiply(@NotNull Fraction other) {
		return new Fraction(numerator.multiply(other.numerator), denominator.multiply(other.denominator));
	}

	@NotNull
	@Contract("_ -> new")
	public Fraction divide(@NotNull Fraction other) {
		return new Fraction(numerator.multiply(other.denominator), denominator.multiply(other.numerator));
	}

	@Contract(value = "null -> false", pure = true)
	public boolean equals(Fraction obj) {
		return (obj != null) && (numerator.equals(obj.numerator) && denominator.equals(obj.denominator));
	}

	public int compareTo(@NotNull Fraction other) {
		return numerator.multiply(other.denominator).compareTo(other.numerator.multiply(denominator));
	}

	public int hashCode() {
		return numerator.hashCode() + denominator.hashCode();
	}

	@NotNull
	@Contract(pure = true)
	public String toString() {
		return numerator + "/" + denominator;
	}
}