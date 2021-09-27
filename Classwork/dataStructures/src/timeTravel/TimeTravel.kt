package timeTravel;

import static java.time.ZonedDateTime.now;
import static java.util.Arrays.stream;
import static java.util.TimeZone.getTimeZone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TimeTravel {
	public static void main(String[] args) {
		try (var fileReader = new Scanner(new File("Classwork/dataStructures/src/timeTravel/TimeTravel.txt"))) {
			var timeZoneID = getTimeZone("America/New_York").toZoneId();
			for (var i = 1; fileReader.hasNextLine(); i++) {
				var data = stream(fileReader.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				var currentTime = now(timeZoneID);
				var futureTime = currentTime.plusDays(data[0]).plusHours(data[1]).plusMinutes(data[2]);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
