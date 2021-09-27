package midYearSGOReview;

import static java.util.stream.IntStream.rangeClosed;

class CriticalQuestionReviewDay2Program2 {
	public static void main(String[] args) {
		rangeClosed(123, 456).filter(i -> ((i % 3) == 0) && ((i % 5) == 0))
				.forEachOrdered(i -> System.out.printf("%d ", i));
	}
}
