package kindergarten;
/* *****************************************************************************
 *  Compilation:  javac StdRandom.java
 *  Execution:    java StdRandom
 *  Dependencies: StdOut.java
 *
 *  A library of static methods to generate pseudo-random numbers from
 *  different distributions (bernoulli, uniform, gaussian, discrete,
 *  and exponential). Also includes a method for shuffling an array.
 *
 *
 *  %  java StdRandom 5
 *  seed = 1316600602069
 *  59 16.81826  true 8.83954  0
 *  32 91.32098  true 9.11026  0
 *  35 10.11874  true 8.95396  3
 *  92 32.88401  true 8.87089  0
 *  72 92.55791  true 9.46241  0
 *
 *  % java StdRandom 5
 *  seed = 1316600616575
 *  96 60.17070  true 8.72821  0
 *  79 32.01607  true 8.58159  0
 *  81 59.49065  true 9.10423  1
 *  96 51.65818  true 9.02102  0
 *  99 17.55771  true 8.99762  0
 *
 *  % java StdRandom 5 1316600616575
 *  seed = 1316600616575
 *  96 60.17070  true 8.72821  0
 *  79 32.01607  true 8.58159  0
 *  81 59.49065  true 9.10423  1
 *  96 51.65818  true 9.02102  0
 *  99 17.55771  true 8.99762  0
 *
 *
 *  Remark
 *  ------
 *    - Relies on randomness of nextDouble() method in java.util.Random
 *      to generate pseudorandom numbers in [0, 1).
 *
 *    - This library allows you to set and get the pseudorandom number seed.
 *
 *    - See http://www.honeylocust.com/RngPack/ for an industrial
 *      strength random number generator in Java.
 *
 ******************************************************************************/

import java.util.Random;

import static java.lang.Double.isInfinite;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.lang.Long.*;
import static java.lang.Math.*;
import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static kindergarten.StdOut.printf;
import static kindergarten.StdOut.println;

/**
 * The {@code StdRandom} class provides static methods for generating
 * random number from various discrete and continuous distributions,
 * including Bernoulli, uniform, Gaussian, exponential, pareto,
 * Poisson, and Cauchy. It also provides method for shuffling an
 * array or subarray.
 * <p>
 * For additional documentation,
 * see <a href="http://introcs.cs.princeton.edu/22library">Section 2.2</a> of
 * <i>Computer Science: An Interdisciplinary Approach</i>
 * by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public final class StdRandom {

	private static Random random;    // pseudo-random number generator
	private static long seed;        // pseudo-random number generator seed

	// static initializer
	static {
		// this is how the seed was set in Java 1.4
		seed = currentTimeMillis();
		random = new Random(seed);
	}

	// don't instantiate
	private StdRandom() {
	}

	/**
	 * Sets the seed of the pseudorandom number generator.
	 * This method enables you to produce the same sequence of "random"
	 * number for each execution of the program.
	 * Ordinarily, you should call this method at most once per program.
	 *
	 * @param s the seed
	 */
	public static void setSeed(long s) {
		seed = s;
		random = new Random(seed);
	}

	/**
	 * Returns the seed of the pseudorandom number generator.
	 *
	 * @return the seed
	 */
	public static long getSeed() {
		return seed;
	}

	/**
	 * Returns a random real number uniformly in [0, 1).
	 *
	 * @return a random real number uniformly in [0, 1)
	 */
	public static double uniform() {
		return random.nextDouble();
	}

	/**
	 * Returns a random integer uniformly in [0, n).
	 *
	 * @param n number of possible integers
	 * @return a random integer uniformly between 0 (inclusive) and {@code n} (exclusive)
	 * @throws IllegalArgumentException if {@code n <= 0}
	 */
	public static int uniform(int n) {
		if (n <= 0) throw new IllegalArgumentException("argument must be positive: " + n);
		return random.nextInt(n);
	}


	/**
	 * Returns a random long integer uniformly in [0, n).
	 *
	 * @param n number of possible long integers
	 * @return a random long integer uniformly between 0 (inclusive) and {@code n} (exclusive)
	 * @throws IllegalArgumentException if {@code n <= 0}
	 */
	public static long uniform(long n) {
		if (n <= 0L) throw new IllegalArgumentException("argument must be positive: " + n);
		// https://docs.oracle.com/javase/8/docs/api/java/util/Random.html#longs-long-long-long-
		var r = random.nextLong();
		var m = n - 1;
		// power of two
		if ((n & m) == 0L) return r & m;
		// reject over-represented candidates
		long u = r >>> 1;
		while (((u + m) - ((r = u % n))) < 0L) u = random.nextLong() >>> 1;
		return r;
	}

	///////////////////////////////////////////////////////////////////////////
	//  STATIC METHODS BELOW RELY ON JAVA.UTIL.RANDOM ONLY INDIRECTLY VIA
	//  THE STATIC METHODS ABOVE.
	///////////////////////////////////////////////////////////////////////////

	/**
	 * Returns a random real number uniformly in [0, 1).
	 *
	 * @return a random real number uniformly in [0, 1)
	 * @deprecated Replaced by {@link #uniform()}.
	 */
	@Deprecated
	public static double random() {
		return uniform();
	}

	/**
	 * Returns a random integer uniformly in [a, b).
	 *
	 * @param a the left endpoint
	 * @param b the right endpoint
	 * @return a random integer uniformly in [a, b)
	 * @throws IllegalArgumentException if {@code b <= a}
	 * @throws IllegalArgumentException if {@code b - a >= Integer.MAX_VALUE}
	 */
	public static int uniform(int a, int b) {
		assert (b > a) && ((b - a) < MAX_VALUE) : "invalid range: [" + a + ", " + b + ")";
		return a + uniform(b - a);
	}

	/**
	 * Returns a random real number uniformly in [a, b).
	 *
	 * @param a the left endpoint
	 * @param b the right endpoint
	 * @return a random real number uniformly in [a, b)
	 * @throws IllegalArgumentException unless {@code a < b}
	 */
	public static double uniform(double a, double b) {
		assert a < b : "invalid range: [" + a + ", " + b + ")";
		return a + uniform() * (b - a);
	}

	/**
	 * Returns a random boolean from a Bernoulli distribution with success
	 * probability <em>p</em>.
	 *
	 * @param p the probability of returning {@code true}
	 * @return {@code true} with probability {@code p} and
	 * {@code false} with probability {@code p}
	 * @throws IllegalArgumentException unless {@code 0} &le; {@code p} &le; {@code 1.0}
	 */
	public static boolean bernoulli(double p) {
		assert (p >= 0.0) && (p <= 1.0) : "probability p must be between 0.0 and 1.0: " + p;
		return uniform() < p;
	}

	/**
	 * Returns a random boolean from a Bernoulli distribution with success
	 * probability 1/2.
	 *
	 * @return {@code true} with probability 1/2 and
	 * {@code false} with probability 1/2
	 */
	public static boolean bernoulli() {
		return bernoulli(0.5);
	}

	/**
	 * Returns a random real number from a standard Gaussian distribution.
	 *
	 * @return a random real number from a standard Gaussian distribution
	 * (mean 0 and standard deviation 1).
	 */
	public static double gaussian() {
		// use the polar form of the Box-Muller transform
		double r, x, y;
		do {
			x = uniform(-1.0, 1.0);
			y = uniform(-1.0, 1.0);
			r = pow(x, 2) + pow(y, 2);
		} while ((r >= 1) || (r == 0));
		return x * sqrt((-2 * log(r)) / r);
		// Remark:  y * Math.sqrt(-2 * Math.log(r) / r)
		// is an independent random gaussian
	}

	/**
	 * Returns a random real number from a Gaussian distribution with mean &mu;
	 * and standard deviation &sigma;.
	 *
	 * @param mu    the mean
	 * @param sigma the standard deviation
	 * @return a real number distributed according to the Gaussian distribution
	 * with mean {@code mu} and standard deviation {@code sigma}
	 */
	public static double gaussian(double mu, double sigma) {
		return mu + sigma * gaussian();
	}

	/**
	 * Returns a random integer from a geometric distribution with success
	 * probability <em>p</em>.
	 *
	 * @param p the parameter of the geometric distribution
	 * @return a random integer from a geometric distribution with success
	 * probability {@code p}; or {@code Integer.MAX_VALUE} if
	 * {@code p} is (nearly) equal to {@code 1.0}.
	 * @throws IllegalArgumentException unless {@code p >= 0.0} and {@code p <= 1.0}
	 */
	public static int geometric(double p) {
		assert (p >= 0.0) && (p <= 1.0) : "probability p must be between 0.0 and 1.0: " + p;
		// using algorithm given by Knuth
		return (int) ceil(log(uniform()) / log(1.0 - p));
	}

	/**
	 * Returns a random integer from a Poisson distribution with mean &lambda;.
	 *
	 * @param lambda the mean of the Poisson distribution
	 * @return a random integer from a Poisson distribution with mean {@code lambda}
	 * @throws IllegalArgumentException unless {@code lambda > 0.0} and not infinite
	 */
	public static int poisson(double lambda) {
		assert lambda > 0.0 : "lambda must be positive: " + lambda;
		assert !isInfinite(lambda) : "lambda must not be infinite: " + lambda;
		// using algorithm given by Knuth
		// see http://en.wikipedia.org/wiki/Poisson_distribution
		var k = 0;
		var p = 1.0;
		var expLambda = Math.exp(-lambda);
		do {
			k++;
			p *= uniform();
		} while (p >= expLambda);
		return k - 1;
	}

	/**
	 * Returns a random real number from the standard Pareto distribution.
	 *
	 * @return a random real number from the standard Pareto distribution
	 */
	public static double pareto() {
		return pareto(1.0);
	}

	/**
	 * Returns a random real number from a Pareto distribution with
	 * shape parameter &alpha;.
	 *
	 * @param alpha shape parameter
	 * @return a random real number from a Pareto distribution with shape
	 * parameter {@code alpha}
	 * @throws IllegalArgumentException unless {@code alpha > 0.0}
	 */
	public static double pareto(double alpha) {
		assert alpha > 0 : "alpha must be positive: " + alpha;
		return pow(1 - uniform(), -1 / alpha) - 1.0;
	}

	/**
	 * Returns a random real number from the Cauchy distribution.
	 *
	 * @return a random real number from the Cauchy distribution.
	 */
	public static double cauchy() {
		return tan(PI * (uniform() - 0.5));
	}

	/**
	 * Returns a random integer from the specified discrete distribution.
	 *
	 * @param probabilities the probability of occurrence of each integer
	 * @return a random integer from a discrete distribution:
	 * {@code i} with probability {@code probabilities[i]}
	 * @throws IllegalArgumentException if {@code probabilities} is {@code null}
	 * @throws IllegalArgumentException if sum of array entries is not (very nearly) equal to {@code 1.0}
	 * @throws IllegalArgumentException unless {@code probabilities[i] >= 0.0} for each index {@code i}
	 */
	public static int discrete(double[] probabilities) {
		assert probabilities != null : "argument array is null";
		var EPSILON = 1E-14;
		var sum = 0.0;
		for (var i = 0; i < probabilities.length; i++) {
			assert probabilities[i] >= 0.0 : "array entry " + i + " must be nonnegative: " + probabilities[i];
			sum += probabilities[i];
		}
		assert !(sum > (1.0 + EPSILON)) && !(sum < (1.0 - EPSILON)) : "sum of array entries does not approximately equal 1.0: " + sum;

		// the for loop may not return a value when both r is (nearly) 1.0 and when the
		// cumulative sum is less than 1.0 (as a result of floating-point roundoff error)
		while (true) {
			var r = uniform();
			sum = 0.0;
			for (var i = 0; i < probabilities.length; i++) {
				sum = sum + probabilities[i];
				if (sum > r) return i;
			}
		}
	}

	/**
	 * Returns a random integer from the specified discrete distribution.
	 *
	 * @param frequencies the frequency of occurrence of each integer
	 * @return a random integer from a discrete distribution:
	 * {@code i} with probability proportional to {@code frequencies[i]}
	 * @throws IllegalArgumentException if {@code frequencies} is {@code null}
	 * @throws IllegalArgumentException if all array entries are {@code 0}
	 * @throws IllegalArgumentException if {@code frequencies[i]} is negative for any index {@code i}
	 * @throws IllegalArgumentException if sum of frequencies exceeds {@code Integer.MAX_VALUE} (2<sup>31</sup> - 1)
	 */
	public static int discrete(int[] frequencies) {
		if (frequencies == null) throw new IllegalArgumentException("argument array is null");
		long sum = 0;
		for (var i = 0; i < frequencies.length; i++) {
			assert frequencies[i] >= 0 : "array entry " + i + " must be nonnegative: " + frequencies[i];
			sum += frequencies[i];
		}
		assert sum != 0 : "at least one array entry must be positive";
		assert sum < MAX_VALUE : "sum of frequencies overflows an int";
		// pick index i with probability proportional to frequency
		var r = (double) uniform((int) sum);
		sum = 0;
		for (var i = 0; i < frequencies.length; i++) {
			sum += frequencies[i];
			if (sum > r) return i;
		}
		// can't reach here
		assert false;
		return -1;
	}

	/**
	 * Returns a random real number from an exponential distribution
	 * with rate &lambda;.
	 *
	 * @param lambda the rate of the exponential distribution
	 * @return a random real number from an exponential distribution with
	 * rate {@code lambda}
	 * @throws IllegalArgumentException unless {@code lambda > 0.0}
	 */
	public static double exp(double lambda) {
		assert lambda > 0.0 : "lambda must be positive: " + lambda;
		return -log(1 - uniform()) / lambda;
	}

	/**
	 * Rearranges the elements of the specified array in uniformly random order.
	 *
	 * @param a the array to shuffle
	 * @throws IllegalArgumentException if {@code a} is {@code null}
	 */
	public static void shuffle(Object[] a) {
		validateNotNull(a);
		var n = a.length;
		range(0, n).forEach(i -> {
			var r = i + uniform(n - i);     // between i and n-1
			var temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		});
	}

	/**
	 * Rearranges the elements of the specified array in uniformly random order.
	 *
	 * @param a the array to shuffle
	 * @throws IllegalArgumentException if {@code a} is {@code null}
	 */
	public static void shuffle(double[] a) {
		validateNotNull(a);
		var n = a.length;
		range(0, n).forEach(i -> {
			var r = i + uniform(n - i);     // between i and n-1
			var temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		});
	}

	/**
	 * Rearranges the elements of the specified array in uniformly random order.
	 *
	 * @param a the array to shuffle
	 * @throws IllegalArgumentException if {@code a} is {@code null}
	 */
	public static void shuffle(int[] a) {
		validateNotNull(a);
		var n = a.length;
		range(0, n).forEach(i -> {
			var r = i + uniform(n - i);     // between i and n-1
			var temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		});
	}

	/**
	 * Rearranges the elements of the specified array in uniformly random order.
	 *
	 * @param a the array to shuffle
	 * @throws IllegalArgumentException if {@code a} is {@code null}
	 */
	public static void shuffle(char[] a) {
		validateNotNull(a);
		var n = a.length;
		range(0, n).forEach(i -> {
			var r = i + uniform(n - i);     // between i and n-1
			var temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		});
	}

	/**
	 * Rearranges the elements of the specified subarray in uniformly random order.
	 *
	 * @param a  the array to shuffle
	 * @param lo the left endpoint (inclusive)
	 * @param hi the right endpoint (exclusive)
	 * @throws IllegalArgumentException if {@code a} is {@code null}
	 * @throws IllegalArgumentException unless {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
	 */
	public static void shuffle(Object[] a, int lo, int hi) {
		validateNotNull(a);
		validateSubarrayIndices(lo, hi, a.length);
		range(lo, hi).forEach(i -> {
			var r = i + uniform(hi - i);     // between i and hi-1
			var temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		});
	}

	/**
	 * Rearranges the elements of the specified subarray in uniformly random order.
	 *
	 * @param a  the array to shuffle
	 * @param lo the left endpoint (inclusive)
	 * @param hi the right endpoint (exclusive)
	 * @throws IllegalArgumentException if {@code a} is {@code null}
	 * @throws IllegalArgumentException unless {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
	 */
	public static void shuffle(double[] a, int lo, int hi) {
		validateNotNull(a);
		validateSubarrayIndices(lo, hi, a.length);
		range(lo, hi).forEach(i -> {
			var r = i + uniform(hi - i);     // between i and hi-1
			var temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		});
	}

	/**
	 * Rearranges the elements of the specified subarray in uniformly random order.
	 *
	 * @param a  the array to shuffle
	 * @param lo the left endpoint (inclusive)
	 * @param hi the right endpoint (exclusive)
	 * @throws IllegalArgumentException if {@code a} is {@code null}
	 * @throws IllegalArgumentException unless {@code (0 <= lo) && (lo < hi) && (hi <= a.length)}
	 */
	public static void shuffle(int[] a, int lo, int hi) {
		validateNotNull(a);
		validateSubarrayIndices(lo, hi, a.length);
		range(lo, hi).forEach(i -> {
			var r = i + uniform(hi - i);     // between i and hi-1
			var temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		});
	}

	/**
	 * Returns a uniformly random permutation of <em>n</em> elements
	 *
	 * @param n number of elements
	 * @return an array of length {@code n} that is a uniformly random permutation
	 * of {@code 0}, {@code 1}, ..., {@code n-1}
	 * @throws IllegalArgumentException if {@code n} is negative
	 */
	public static int[] permutation(int n) {
		assert n >= 0 : "argument is negative";
		var perm = range(0, n).toArray();
		shuffle(perm);
		return perm;
	}

	/**
	 * Returns a uniformly random permutation of <em>k</em> of <em>n</em> elements
	 *
	 * @param n number of elements
	 * @param k number of elements to select
	 * @return an array of length {@code k} that is a uniformly random permutation
	 * of {@code k} of the elements from {@code 0}, {@code 1}, ..., {@code n-1}
	 * @throws IllegalArgumentException if {@code n} is negative
	 * @throws IllegalArgumentException unless {@code 0 <= k <= n}
	 */
	public static int[] permutation(int n, int k) {
		assert n >= 0 : "argument is negative";
		assert (k >= 0) && (k <= n) : "k must be between 0 and n";
		var perm = new int[k];
		range(0, k).forEach(i -> {
			var r = uniform(i + 1);    // between 0 and i
			perm[i] = perm[r];
			perm[r] = i;
		});
		range(k, n).forEach(i -> {
			var r = uniform(i + 1);    // between 0 and i
			if (r < k) perm[r] = i;
		});
		return perm;
	}

	// throw an IllegalArgumentException if x is null
	// (x can be of type Object[], double[], int[], ...)
	private static void validateNotNull(Object x) {
		assert x != null : "argument is null";
	}

	// throw an exception unless 0 <= lo <= hi <= length
	private static void validateSubarrayIndices(int lo, int hi, int length) {
		assert (lo >= 0) && (hi <= length) && (lo <= hi) : "subarray indices out of bounds: [" + lo + ", " + hi + ")";
	}

	/**
	 * Unit test.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		var n = parseInt(args[0]);
		if (args.length == 2) setSeed(parseLong(args[1]));
		var probabilities = new double[]{0.5, 0.3, 0.1, 0.1};
		var frequencies = new int[]{5, 3, 1, 1};
		var a = "A B C D E F G".split(" ");
		println("seed = " + getSeed());
		range(0, n).forEach(i -> {
			printf("%2d ", uniform(100));
			printf("%8.5f ", uniform(10.0, 99.0));
			printf("%5b ", bernoulli(0.5));
			printf("%7.5f ", gaussian(9.0, 0.2));
			printf("%1d ", discrete(probabilities));
			printf("%1d ", discrete(frequencies));
			printf("%11d ", uniform(100000000000L));
			shuffle(a);
			stream(a).forEach(StdOut::print);
			println();
		});
	}

}

/* *****************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
