package bonusWork;

import org.jetbrains.annotations.Contract;

import static java.lang.System.out;

class SpecialSum {
	public static void main(String... args) {
		var sum = 0;
		for (var i = 1; i <= 1000000; i++) {
			var localSum = sumDigits(i);
			if ((i % localSum) == 0) sum += i;
		}
		out.println(sum);
	}

	@Contract(pure = true)
	public static int sumDigits(int input) {
		var result = 0;
		while (input > 0) {
			result += input % 10;
			input /= 10;
		}
		return result;
	}
}