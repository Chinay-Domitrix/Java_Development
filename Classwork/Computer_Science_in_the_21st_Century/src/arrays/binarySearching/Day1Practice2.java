package arrays.binarySearching;

import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.util.Arrays.setAll;

public class Day1Practice2 {
	private static boolean found = false;
	private static int counter = 0;

	private static void binarySearch(final int[] array, final int target) {
		int low = 0, high = array.length - 1;
		while (high >= low) {
			counter++;
			final int middle = (low + high) / 2;
			if (array[middle] == target) {
				found = true;
				break;
			} else if (array[middle] < target)
				low = middle + 1;
			else if (array[middle] > target)
				high = middle - 1;
		}
	}

	private static int linearSearch(final int @NotNull [] arr, final int x) {
		return IntStream.range(0, arr.length).filter(i -> arr[i] == x).findFirst().orElse(-1);
	}

	public static void main(final String[] args) {
		final int[] arr = new int[1000];
		setAll(arr, i -> (int) ((random() * 10000) + 1));
		for (int i = 0; i < arr.length; i++) {
			out.print(arr[i] + "\t");
			if (((i + 1) % 10) == 0)
				out.println();
		}
		final int target = (int) (random() * 10000 + 1), targetIndex = linearSearch(arr, target);
		if (targetIndex != -1)
			out.printf("The number %d was found at index %d after %d tries.%n", target, targetIndex, targetIndex);
		else
			out.println(target + " was not in the array. The search took 1000 tries.");
		out.println();
		for (int i = 1; i < arr.length; i++) {
			int j = i;
			while (j > 0 && arr[j] < arr[j - 1]) {
				final int ph = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = ph;
				j--;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			out.print(arr[i] + "\t");
			if (((i + 1) % 10) == 0)
				out.println();
		}
		binarySearch(arr, target);
		out.println(found ? (target + " is in the array. It took " + counter + " tries.")
				: (target + " is not in the array. It took 1000 tries."));
	}
}
