package arrays.binarySearching;

import java.util.Scanner;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.util.Arrays.setAll;

public class Day1Practice4 {
	private static boolean found = false;

	private static void binarySearch(final int[] array, final int target) {
		int low = 0, high = array.length - 1;
		while (high >= low) {
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

	public static void main(final String[] args) {
		final int[] arr = new int[10];
		final Scanner in = new Scanner(System.in);
		boolean loop;
		do {
			setAll(arr, i -> (int) ((random() * 100) + 1));
			out.print("Please enter a number from one to a hundred: ");
			final int target = in.nextInt();
			in.nextLine();
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
			out.println(
					found ? target + " is in the array. You win!." : target + " is not in the array. Sorry, you lose.");
			out.print("Do you want to play again? ");
			final String looper = in.nextLine();
			if (looper.equalsIgnoreCase("yes"))
				loop = false;
			else if (looper.equalsIgnoreCase("no"))
				loop = true;
			else {
				out.println("Error while reading. Please input again");
				loop = false;
			}
		} while (!loop);
		in.close();
	}
}
