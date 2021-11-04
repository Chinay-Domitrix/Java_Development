import java.lang.System.`in`
import java.lang.System.out
import java.util.*

internal object QBRatingProgramScanner {
	@JvmStatic
	fun main(args: Array<String>) {
		Scanner(`in`).use {
			println("What is your QB's name?")
			val name = it.nextLine()
			println("How many yards does your QB have?")
			val yds = it.nextInt()
			println("How many completions does your QB have?")
			val comp = it.nextInt()
			println("How many TDs does your QB have?")
			val td = it.nextInt()
			println("How many interceptions does your QB have?")
			val inter = it.nextInt()
			println("How many attempts does your QB have?")
			val att = it.nextInt()
			val a = (((comp / att.toDouble()) * 100) - 30) / 20
			val b = ((td / att.toDouble()) * 100) / 5
			val c = (9.5 - ((inter / att.toDouble()) * 100)) / 4
			val d = ((yds / att.toDouble()) - 3) / 4
			val rating = (a + b + c + d) / 0.06
			out.printf("%s's Rating: %s%n", name, rating)
		}
	}
}