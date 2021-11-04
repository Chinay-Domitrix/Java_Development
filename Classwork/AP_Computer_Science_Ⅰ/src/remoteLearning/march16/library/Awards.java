package remoteLearning.march16.library;

public interface Awards {
	interface Fiction {
		boolean getBooker();

		boolean getNobel();

		boolean getFaulkner();
	}

	interface ScienceFiction {
		boolean getHugo();

		boolean getBradbury();

		boolean getNebula();
	}

	interface NonFiction {
		boolean getNationalBook();

		boolean getCarnegie();

		boolean getHeartland();
	}
}
