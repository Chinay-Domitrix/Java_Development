import static java.lang.Math.random;
import static java.lang.System.out;

class SecondsConverted {
	public static void main(final String[] args) {
		final int randomSeconds = (int) (random() * 1000001) + 500, days = randomSeconds / 86400, hours, minutes, seconds;
		int remainingSeconds = randomSeconds % 86400;
		hours = remainingSeconds / 3600;
		remainingSeconds %= 3600;
		minutes = remainingSeconds / 60;
		seconds = remainingSeconds % 60;
		out.println("Total Seconds: " + randomSeconds + "\n" + days + " days : " + hours + " hours : " + minutes + " minutes : " + seconds + " seconds");
	}
}