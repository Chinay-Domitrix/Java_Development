package arrays.binarySearching;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.util.Arrays.setAll;

public class Day1DoNow {
	public static void main(final String[] args) {
		final int[] arr = new int[100];
		setAll(arr, i -> (int) (random() * 1000) + 1);
		for (int i = 0; i < arr.length; i++) {
			out.print(arr[i] + "\t");
			if ((i + 1) % 5 == 0)
				out.println();
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
		if (found)
			out.println("100 was in the array.");
		else
			out.println("100 was NOT in the array.");
		out.println("It took " + count + " times.");
		for (int i = 0; i < arr.length - 1; i++) {
			int mindex = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j] < arr[mindex])
					mindex = j;
			final int ph = arr[i];
			arr[i] = arr[mindex];
			arr[mindex] = ph;
		} // Another way to do this would be Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			out.print(arr[i] + "\t");
			if ((i + 1) % 5 == 0)
				out.println();
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
			} else if (arr[mid] < key)
				low = mid + 1;
			else if (arr[mid] > key)
				high = mid - 1;
		}
		if (found2)
			out.println("100 was in the array.");
		else
			out.println("100 was NOT in the array.");
		out.println("It took " + count2 + " times.");
	}
}