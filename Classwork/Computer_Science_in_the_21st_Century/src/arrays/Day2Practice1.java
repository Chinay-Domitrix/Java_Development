package arrays;

class Day2Practice1 {
	public static void main(final String[] args) {
		final int[] array = new int[6];
		int ph;
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 20) + 1;
			System.out.print(array[i] + " ");
		}
		System.out.println();
		ph = array[2];
		array[2] = array[3];
		array[3] = ph;
		for (int value : array) {
			System.out.print(value + " ");
		}
	}
}
