/*
 * Solution to Project Euler problem 35
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

public final class p035 implements EulerSolution {
	private static final int LIMIT = Library.pow(10, 6);
	private boolean[] isPrime = Library.listPrimality(LIMIT - 1);

	public static void main(String[] args) {
		System.out.println(new p035().run());
	}

	public String run() {
		int count = 0;
		for (int i = 0; i < isPrime.length; i++) {
			if (isCircularPrime(i))
				count++;
		}
		return Integer.toString(count);
	}

	private boolean isCircularPrime(int n) {
		String s = Integer.toString(n);
		for (int i = 0; i < s.length(); i++) {
			if (!isPrime[Integer.parseInt(s.substring(i) + s.substring(0, i))])
				return false;
		}
		return true;
	}

}
