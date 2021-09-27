package timeTravel

import java.io.File
import java.io.FileNotFoundException
import java.time.ZonedDateTime.now
import java.util.*
import java.util.Arrays.stream
import java.util.TimeZone.getTimeZone

class TimeTravel {
	fun main() = try {
		Scanner(File("Classwork/dataStructures/src/timeTravel/TimeTravel.txt")).use { fileReader ->
			val timeZoneID = getTimeZone("America/New_York").toZoneId()
			var i = 1
			while (fileReader.hasNextLine()) {
				val data = stream(fileReader.nextLine().split(" ").toTypedArray()).mapToInt(Integer::parseInt).toArray()
				val currentTime = now(timeZoneID)
				val futureTime = currentTime.plusDays(data[0].toLong()).plusHours(data[1].toLong()).plusMinutes(data[2].toLong())
				println("Trip $i:\n\tDeparture Date and Time: $currentTime\n\tArrival Date and Time: $futureTime")
				i++
			}
		}
	} catch (e: FileNotFoundException) {
		e.printStackTrace()
	}
}