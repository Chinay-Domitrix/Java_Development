package arrays;

import static java.lang.System.out;

class Day1DTOPractice3 {
	public static void main(String[] args) {
		int[] array = new int[5];
		int sum = 0;
		int max = 0;
		int min = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 100) + 1;
			sum += array[i];
		}
		for (int i = 0; i < 100; i++)
			if (array[i] > array[i + 1])
				max = array[i];
		for (int i = 0; i < 100; i++)
			if (array[i] < array[i + 1])
				min = array[i];
		out.println("The sum is " + sum + ". The max is " + max + ". The min is " + min + ".");
	}
}
