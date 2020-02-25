package randomWork;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.System.out;

/**
 * This variable calculates the sum of 1/x for natural numbers x
 * <p>
 * Please inspect to understand how it works.  Then change the limit
 * variable from 10 to 20, 23, and 24.  NOTE: this may take a while for
 * higher numbers, be patient!
 * <p>
 * Observe the behavior.  What do you think is happening?
 * Be prepared to discuss.
 */
public class Sum1_X {
	public static void main(String[] args) {
		var x = 0;
		var y = 1.0;
		var limit = 24.0; // change this variable to 10, 20, 23, then 24
		while ((y < limit) && (x < MAX_VALUE)) {
			x++;
			y += 1.0 / x;
			out.printf("Iteration #%d, and the decimal is at %s%n", x + 1, 1.0 / x);
		}
		out.printf("After %d loops the sum is %s%n", x, y);
	}
}