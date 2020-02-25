package arrays.binarySearching;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.util.Arrays.fill;
import static java.util.Arrays.setAll;

public class Day1Practice3 {
	private static boolean found = false;
	private static int count = 0;

	private static void binarySearch(final int[] array, final int target) {
		int low = 0, high = array.length - 1;
		while (high >= low) {
			count++;
			final int middle = (low + high) / 2;
			if (array[middle] == target) {
				found = true;
				break;
			} else if (array[middle] < target) {
				low = middle + 1;
			} else if (array[middle] > target) {
				high = middle - 1;
			}
		}
	}

	private static int linearSearch(final int[] arr, final int x) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == x)
				return i;
		return -1;
	}

	public static void main(final String[] args) {
		final int[] counter = new int[2];
		fill(counter, 0);
		for (int a = 0; a < 100; a++) {
			final int[] arr = new int[1000];
			setAll(arr, i -> (int) (random() * 10000 + 1));
			final int target = (int) (random() * 10000 + 1), targetIndex = linearSearch(arr, target);
			if (targetIndex != -1) {
				out.println("The number " + target + " was found at index " + targetIndex + " after " + targetIndex
						+ " tries.");
				counter[0] += targetIndex;
			} else {
				out.println(target + " was not in the array. The search took 1000 tries.");
				counter[0] += arr.length;
			}
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
			binarySearch(arr, target);
			if (found)
				out.println(target + " is in the array. It took " + count + " tries.");
			else
				out.println(target + " is not in the array. It took " + count + " tries.");
			counter[1] += count;
		}
		out.println("Linear Search Run Average: " + counter[0] / 100.0 + " tries\nBinary Search Run Average: "
				+ counter[1] / 100.0 + " tries");
	}
}