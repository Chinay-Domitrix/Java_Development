package timeTravel

import java.io.File
import java.io.FileNotFoundException
import java.time.ZonedDateTime.now
import java.util.*
import java.util.Arrays.stream
import java.util.TimeZone.getTimeZone
import java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

class TimeTravel {
	fun main() = try {
		Scanner(File("Classwork/dataStructures/src/timeTravel/TimeTravel.txt")).use { fileReader ->
			val timeZoneID = getTimeZone("America/New_York").toZoneId()
			var i = 1
			while (fileReader.hasNextLine()) {
				val dataSet = stream(fileReader.nextLine().split(" ").toTypedArray()).mapToInt(Integer::parseInt).toArray()
				val currentTime = now(timeZoneID)
				val futureTime = currentTime.plusDays(dataSet[0].toLong()).plusHours(dataSet[1].toLong()).plusMinutes(dataSet[2].toLong())
				println("Trip $i:\n\tDeparture Date and Time: ${currentTime.format(RFC_1123_DATE_TIME)}\n\tArrival Date and Time: ${futureTime.format(RFC_1123_DATE_TIME)}")
				i++
			}
		}
	} catch (e: FileNotFoundException) {
		e.printStackTrace()
	}
}
