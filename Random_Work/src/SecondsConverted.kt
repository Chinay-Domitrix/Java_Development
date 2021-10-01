import java.lang.Math.random
import java.lang.System.out

internal object SecondsConverted {
	@JvmStatic
	fun main(args: Array<String>) {
		val randomSeconds = (random() * 1000001).toInt() + 500
		val days = randomSeconds / 86400
		var remainingSeconds = randomSeconds % 86400
		val hours = remainingSeconds / 3600
		remainingSeconds %= 3600
		val minutes = remainingSeconds / 60
		val seconds = remainingSeconds % 60
		out.printf(
			"Total Seconds:\t%d, which is, when simplified,%n%t%t%t%t%d days, %d hours, %d minutes, and %d seconds%n",
			randomSeconds,
			days,
			hours,
			minutes,
			seconds
		)
	}
}