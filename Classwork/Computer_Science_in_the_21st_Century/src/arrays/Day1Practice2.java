package Classwork.Computer_Science_in_the_21st_Century.src.arrays;

import static java.lang.System.out;

class Day1Practice2 {
	public static void main(String[] args) {
		int[] array = new int[5];
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 11) + 5;
			sum += array[i];
		}
		out.println(sum);
	}
}
