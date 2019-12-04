package midYearSGOReview;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.System.out;

class CriticalQuestionReviewDay2Program1 {
	public static void main(final String[] args) {
		int[] a = new int[8];
		int min = MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 19) + 1;
			sum += a[i];
		}
		for (int value : a) if (value < min) min = value;
		out.printf("The minimum is %d and the sum is %d.%n", min, sum);
	}
}