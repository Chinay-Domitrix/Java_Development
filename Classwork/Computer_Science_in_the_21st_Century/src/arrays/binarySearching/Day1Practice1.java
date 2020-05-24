package arrays.binarySearching;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.util.Arrays.setAll;

public class Day1Practice1 {
	public static void main(final String[] args) {
		final int[] arr = new int[1000];
		setAll(arr, i -> (int) ((random() * 10000) + 1));
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
			if (((i + 1) % 10) == 0) out.println();
		}
		int low = 0, high = arr.length - 1;
		final int key = (int) ((random() * 10000) + 1);
		int count = 0;
		boolean found2 = false;
		while (true) {
			if (high < low) break;
			count++;
			final int mid = (low + high) / 2;
			if (arr[mid] == key) {
				found2 = true;
				break;
			} else if (arr[mid] < key) low = mid + 1;
			else if (arr[mid] > key) high = mid - 1;
		}
		out.printf("100 was%s in the array%n", found2 ? "" : " NOT");
		out.println("It took " + count + " times.");
	}
}