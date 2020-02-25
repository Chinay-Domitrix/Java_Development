package arrays.binarySearching;

import java.util.Scanner;

import static java.lang.System.out;
import static java.util.Arrays.setAll;

public class Review {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 0;
		String redo;
		main:
		//noinspection ConstantConditions
		do {
			out.print("How many values do you want in the array? Be warned, the smaller your pool of numbers, the lower the chance you have of winning. ");
			int[] arr = new int[in.nextInt()];
			setAll(arr, i -> (int) (Math.random() * 10000) + 1);
			out.print("What value do you want to search for? ");
			int target = in.nextInt();
			in.nextLine();
			int low = 0, high = arr.length - 1;
			for (int i = 1; i < arr.length; i++) {
				int j = i;
				while (j > 0 && arr[j] < arr[j - 1]) {
					final int ph = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = ph;
					j--;
				}
			}
			boolean found = false;
			while (high >= low) {
				count++;
				int middle = (low + high) / 2;
				if (arr[middle] == target) {
					found = true;
					break;
				} else if (arr[middle] < target)
					low = middle + 1;
				else if (arr[middle] > target)
					high = middle - 1;
			}
			if (found) {
				out.println(target + " is in the array. You win! It took " + count + " attempts.");
				out.print("Do you want to play again? ");
				boolean error;
				do {
					redo = in.nextLine();
					if (redo.equalsIgnoreCase("yes") || redo.equalsIgnoreCase("y")) {
						redo = "y";
						error = false;
					} else if (redo.equalsIgnoreCase("no") || redo.equalsIgnoreCase("n"))
						break main;
					else {
						out.println("Error. Please try again. ");
						error = true;
					}
				} while (error);
			} else {
				out.println(target + " is not in the array. Sorry, you lose.");
				break;
			}
		} while (redo.equalsIgnoreCase("y"));
	}
}