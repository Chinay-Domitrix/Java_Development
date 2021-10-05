package remoteLearning.march12.numberComputationAndGrouping;

import static java.lang.System.out;

public class NumberGrouping implements NumberComputation {
	public int[] array;

	public NumberGrouping(int[] array) {
		this.array = array;
	}

	public static void main(String[] args) {
		out.println(new NumberGrouping(new int[]{1, 2, 3, 4}).product());
		out.println(new NumberGrouping(new int[]{}).product());
	}

	@Override
	public int product() {
		if (array.length > 0) {
			var product = 1;
			for (int i : array)
				product *= i;
			return product;
		}
		throw new ArrayIndexOutOfBoundsException("Array cannot be of size 0");
	}
}
