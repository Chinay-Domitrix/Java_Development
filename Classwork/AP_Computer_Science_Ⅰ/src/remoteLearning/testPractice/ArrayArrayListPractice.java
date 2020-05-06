package remoteLearning.testPractice;

/*   PRACTICE, PRACTICE, PRACTICE WITH ARRAYS AND ARRAYLIST
 *
 *	 We ill use this file to practice manipulating Array & ArrayLists
 *
 *   Day 1 Assignment:  Write toString and reversedAL methods as described below
 *						Then, uncomment the lines in the main method to test
 *	 Day 2 Assignment:  Write swap method as described below
 *						Then, uncomment the lines in the main method to test
 *	 Day 3 Assignment:  Write a setArrayList mutator method to allow you to reset the arrayList to match the values of an array parameter
 *						Write a removeAFromAL method meeting the conditions outlined below
 *						Then, uncomment the lines in the main method to test
 */

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayArrayListPractice {
	private final ArrayList<Integer> arrayList;
	// Declare private fields, note not initialized here so no defined size
	private int[] array;

	// Constructor, set both Array and ArrayList to same int array
	private ArrayArrayListPractice(int[] arr) {
		array = new int[arr.length];
		arrayList = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			array[i] = arr[i];
			arrayList.add(arr[i]);
		}
	}

	public static void main(String[] args) {
		// Create a new object with Array and ArrayList initialized to array a
		int[] a = {15, 17, 19, 22};
		var practice = new ArrayArrayListPractice(a);
		System.out.println(practice);
		practice.array = practice.reverersedAL();
		System.out.println(practice);

		// Uncomment the lines below to test
		practice.swap();
		System.out.println("After swap");
		System.out.println(practice);

		practice.arrayList.add(33); // Add an extra element to arrayList so no longer same size
		practice.swap();
		System.out.println("After uneven swap");
		System.out.println(practice);

		// Uncomment the lines below to test Day 3
		int[] b = new int[]{20, 22, 24, 27};
		practice.setArrayList(b);
		System.out.println(practice);
		System.out.println(practice.removeAFromAL()); // Should return 1, because 22 eliminated from the ArrayList
		System.out.println(practice); // print again after removal
		int[] c = new int[]{7, 9, 11, 13, 15, 17};
		var practice2 = new ArrayArrayListPractice(c);
		int[] d = new int[]{1, 3, 9, 9, 9, 7, 7, 14, 18, 15, 22};
		practice2.setArrayList(d);
		System.out.println(practice2);
		System.out.println(practice2.removeAFromAL()); // Should return 6, because 9,7,15 eliminated from the ArrayList
		System.out.println(practice2); // print again after removal

	}

	// Write a toString() method that has the following sample output
	// NOTE: You will need a loop to print Array but not necessarily for ArrayList
	//
	// array (length 4) = [15, 17, 19, 22], arrayList (size 4) = [15, 17, 19, 22]
	public String toString() {
		StringBuilder tempStr = new StringBuilder("array (length " + array.length + ") = [");
		// append Strings to output array
		for (int i = 0; i < array.length - 1; i++) tempStr.append(array[i]).append(", ");
		tempStr.append(array[array.length - 1]).append("], ");
		tempStr.append("arrayList (size ").append(arrayList.size()).append(") = ").append(arrayList);
		return tempStr.toString();
	}


	/*  int[] swap - This method swaps all the elements in array with the elements at equivalent indeces in arrayList
		             If array and arrayList are not the same size, ALL the Elements in both will be set to zero
			Precondition: array and arrayList have been initialized
			Postcondition: elements are swapped (if same size) or all elements are set to zero (if different size)

	*/

	/*  int[] reverersedAL - This method returns an int array which is the private
	                         ArrayList object in reverse order without changing private variables
		Precondition: arrayList has been initialized
		Postcondition: neither private field array or arrayList is modified
		@return an Array of arrayList elements in reversed order

	*/
	public int[] reverersedAL() {
		int[] arr = new int[arrayList.size()];
		for (int i = 0; i < arr.length; i++) arr[i] = arrayList.get(arrayList.size() - i - 1);
		return arr;
	}

	public void swap() {
		if (array.length != arrayList.size()) {
			Arrays.fill(array, 0);
			for (int i = 0; i < arrayList.size(); i++) arrayList.set(i, 0);
		} else {  // equal size so swap
			for (int i = 0; i < array.length; i++) {
				int temp = arrayList.set(i, array[i]);  // Previous value of arrayList element at index i will be set to temp
				array[i] = temp;
			}
		}
	}

	public void setArrayList(int[] al) {
		arrayList.clear();
		for (int i : al) arrayList.add(i);
	}

	/*
		int removeAFromAL - This element removes anything which is an element of the array from the arrayList and returns the total
							number of things that have been removed from the arrayList
		Precondition: array and arrayList have been initialized
		Postcondition: array is unchanged, but ALL COPIES of elements from the array have been removed from the arrayList
		@return int total number of items deleted from the arrayList

	*/
	public int removeAFromAL() {
		/* Write code here */
	}
}