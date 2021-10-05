package amicableNumbers

import java.io.File
import java.io.FileNotFoundException
import java.util.*
import java.util.Arrays.stream

fun main() = try {
	Scanner(File("Classwork/dataStructures/src/amicableNumbers/Amicable_Numbers.txt")).use { fileReader ->
		while (fileReader.hasNextLine()) {
			val nums = stream(fileReader.nextLine().split(" ".toRegex()).toTypedArray()).mapToInt(String::toInt)
				.toArray()
			val sum1 = stream(factors(nums[0])).sum()
			val sum2 = stream(factors(nums[1])).sum()
			println(
				"The numbers ${nums[0]} and ${nums[1]} are ${if (sum1 == nums[1] && sum2 == nums[0]) "" else "not "}amicable.\n\tThe factors of ${nums[0]} are ${
					formatFactorString(
						factors(nums[0])
					)
				}. The sum is $sum1.\n\tThe divisors of ${nums[1]} are ${formatFactorString(factors(nums[1]))}. The sum is $sum2."
			)
		}
	}
} catch (e: FileNotFoundException) {
	e.printStackTrace()
}

private fun factors(input: Int) =
	(1 until input).filter { (input % it) == 0 }.stream().mapToInt { i: Int? -> i!! }.toArray()

private fun formatFactorString(input: IntArray): String {
	var factorStringBuilder = StringBuilder()
	input.forEach { factorStringBuilder.append(it).append(", ") }
	factorStringBuilder = StringBuilder(
		factorStringBuilder.toString().trim { it <= ' ' }).deleteCharAt(factorStringBuilder.lastIndexOf(","))
	return factorStringBuilder.insert(factorStringBuilder.lastIndexOf(",") + 1, " and").toString()
}
