package arrays;

class Day2Practice2 {
	public static void main(final String[] args) {
		final int[] array = new int[10];
		int sum = 0;
		double counter = 0;
		double avg;
		for (int i = 0; i < array.length; i++) {
			array[i] = i + 4;
			sum += array[i];
			counter++;
			System.out.print(array[i] + " ");
		}
		avg = sum / counter;
		System.out.println(avg);
	}
}