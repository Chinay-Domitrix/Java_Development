package reducer

import java.io.File
import java.io.FileNotFoundException
import java.util.*

fun main() = try {
	Scanner(File("Classwork/dataStructures/src/reducer/Reducer.txt")).use {
		while (it.hasNextLine()) {
			var nums = it.next().split("/".toRegex()).toTypedArray()
			var numerator = nums[0].toInt()
			var denominator = nums[1].toInt()
			nums = ((numerator / gcd(numerator, denominator)).toString() + "/" + denominator / gcd(
				numerator,
				denominator
			)).split("/".toRegex()).toTypedArray()
			numerator = nums[0].toInt()
			denominator = nums[1].toInt()
			println((if (numerator / denominator == 0) "" else (numerator / denominator).toString() + " ") + (if (numerator % denominator == 0) "" else numerator % denominator) + '/' + denominator)
		}
	}
} catch (e: FileNotFoundException) {
	e.printStackTrace()
}

private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
