package Classwork.Computer_Science_in_the_21st_Century.src.arrays.binarySearching;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.util.Arrays.setAll;

public class Day1DoNow {
	public static void main(final String[] args) {
		final int[] arr = new int[100];
		setAll(arr, i -> (int) (random() * 1000) + 1);
		for (int i = 0; i < arr.length; i++) {
			out.print(arr[i] + "\t");
			if (((i + 1) % 5) == 0) out.println();
		}
		int count = 0;
		boolean found = false;
		for (final int value : arr) {
			count++;
			if (value == 100) {
				found = true;
				break;
			}
		}
		out.printf("100 was%s in the array%n", found ? "" : " NOT");
		out.printf("It took %d times.%n", count);
		for (int i = 0; i < arr.length - 1; i++) {
			int mindex = i;
			for (int j = i + 1; j < arr.length; j++) if (arr[j] < arr[mindex]) mindex = j;
			final int ph = arr[i];
			arr[i] = arr[mindex];
			arr[mindex] = ph;
		} // Another way to do this would be Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			out.print(arr[i] + "\t");
			if (((i + 1) % 5) == 0) out.println();
		}
		int low = 0, high = arr.length - 1, count2 = 0;
		final int key = 100;
		boolean found2 = false;
		while (high >= low) {
			count2++;
			final int mid = (low + high) / 2;
			if (arr[mid] == key) {
				found2 = true;
				break;
			} else if (arr[mid] < key) low = mid + 1;
			else if (arr[mid] > key) high = mid - 1;
		}
		out.printf("100 was%s in the array%n", found2 ? "" : " NOT");
		out.println("It took " + count2 + " times.");
	}
}
