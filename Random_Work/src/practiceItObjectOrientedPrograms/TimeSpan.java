package practiceItObjectOrientedPrograms;

import org.jetbrains.annotations.Contract;

import static java.lang.String.format;

public class TimeSpan {
	private int hours;
	private int minutes;

	// Constructs a time span with the given interval.
	// pre: hours >= 0 && minutes >= 0
	public TimeSpan(int hours, int minutes) {
		this.hours = 0;
		this.minutes = 0;
		add(hours, minutes);
	}

	// Adds the given interval to this time span.
	// pre: hours >= 0 && minutes >= 0
	public void add(int hours, int minutes) {
		this.hours += hours;
		this.minutes += minutes;
		// convert each 60 minutes into one hour
		this.hours += getMinutes() / 60;
		this.minutes = getMinutes() % 60;
	}

	// Returns the number of minutes stored in this practiceItObjectOrientedPrograms.TimeSpan object.
	public int getHours() {
		return hours;
	}

	// Returns the number of minutes stored in this practiceItObjectOrientedPrograms.TimeSpan object.
	@Contract(pure = true)
	private int getMinutes() {
		return minutes;
	}

	// Returns a String for this time span such as "6h15m".
	public String toString() {
		return format("%dh%dm", hours, minutes);
	}

	// YOUR CODE GOES HERE
}