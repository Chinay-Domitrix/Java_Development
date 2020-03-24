import java.util.Arrays;

import static java.lang.System.out;

public class RandomStuff {
	public static void main(String[] args) {
		int[] arr = {5, 3, 4, 1, 6, 2};
		out.println("Before:");
		out.println(Arrays.toString(arr));
		new RandomStuff().selectionSort(arr);
		out.println("After:");
		out.println(Arrays.toString(arr));
		int[] arr2 = {6, 3, 2, 10, 15, 8, 1, 90, 7};
		out.println("===========");
		out.println("Before:");
		out.println(Arrays.toString(arr2));
		new RandomStuff().selectionSort(arr2);
		out.println("After:");
		out.println(Arrays.toString(arr2));
	}

	private void selectionSort(int[] arr) {
		for (int curIndex = 0; curIndex < arr.length - 1; curIndex++) {
			// Show current state of array at each pass
			out.println(Arrays.toString(arr));
			// Find minimum in the rest of the list
			int minIndex = findMin(arr, curIndex);
			// Swap the minimum into the correct position
			swap(arr, curIndex, minIndex);
		}
	}

	private int findMin(int[] arr, int startingIndex) {
		int minIndex = startingIndex;
		for (int i = minIndex + 1; i < arr.length; i++) if (arr[i] < arr[minIndex]) minIndex = i;
		return minIndex;
	}

	private void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	/*
	 * Selection sort takes in an array of integers and
	 * returns a sorted array of the same integers.
	 */
	private int[] selectionSort2(int[] arr) {
		int currentMinIndex;
		for (int i = 0; i < arr.length - 1; i++) {
			out.println(Arrays.toString(arr));
			currentMinIndex = i;
			for (int j = i + 1; j < arr.length; j++) if (arr[j] < arr[currentMinIndex]) currentMinIndex = j;
			// swap numbers if needed
			if (i != currentMinIndex) {
				int temp = arr[currentMinIndex];
				arr[currentMinIndex] = arr[i];
				arr[i] = temp;
			}
		}
		return arr;
	}
}