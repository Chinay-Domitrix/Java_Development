import static java.lang.Math.random;
import static java.lang.System.out;

class SecondsConverted {
	public static void main(final String[] args) {
		var randomSeconds = (int) (random() * 1000001) + 500;
		var days = randomSeconds / 86400;
		var remainingSeconds = randomSeconds % 86400;
		var hours = remainingSeconds / 3600;
		remainingSeconds %= 3600;
		var minutes = remainingSeconds / 60;
		var seconds = remainingSeconds % 60;
		out.printf("Total Seconds:\t%d, which is, when simplified,%n%t%t%t%t%d days, %d hours, %d minutes, and %d seconds%n", randomSeconds, days, hours, minutes, seconds);
	}
}