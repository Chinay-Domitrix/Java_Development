package arrays;

class Day2Practice3 {
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
		int[] ph = new int[4];
		for (int item : array) System.out.print(item + " ");
		System.out.println();
		for (int i = 0; i < ph.length; i++) {
			ph[i] = array[i];
			array[i] = array[(array.length - (1 + i))];
			array[(array.length - (1 + i))] = ph[i];
		}
		for (int value : array) System.out.print(value + " ");
	}
}