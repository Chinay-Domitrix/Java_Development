package arrays;

class Day1DTOPractice1 {
	public static void main(String[] args) {
		int[] array = new int[6];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 11) + 5;
			System.out.print(array[i] + " ");
		}
	}
}
