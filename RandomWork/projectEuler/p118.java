118

public final class p118 extends EulerSolution {
	private boolean[] isPrime; // Cache for small numbers
	private int count;

	public static void main(String[] args) {
		System.out.println(new p118().run());
	}

	private static int toInteger(int[] digits, int start, int end) {
		int result = 0;
		for (int i = start; i < end; i++)
			result = result * 10 + digits[i];
		return result;
	}

	String run() {
		isPrime = Library.listPrimality(10000);
		count = 0;
		int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		do countPrimeSets(digits, 0, 0); // Try all permutations, and try all splits within each permutation
		while (Library.nextPermutation(digits));
		return Integer.toString(count);
	}

	private void countPrimeSets(int[] digits, int startIndex, int prevNum) {
		if (startIndex == digits.length)
			count++;
		else {
			for (int split = startIndex + 1; split <= digits.length; split++) {
				int num = toInteger(digits, startIndex, split);
				if (num > prevNum && isPrime(num))
					countPrimeSets(digits, split, num);
			}
		}
	}

	private boolean isPrime(int n) {
		if (n < isPrime.length)
			return isPrime[n];
		else
			return Library.isPrime(n);
	}
}