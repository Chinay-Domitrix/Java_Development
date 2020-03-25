import static java.lang.System.out;

public class RandomStuff {
	private static int x = 0, y = 0;

	public static void main(String[] args) {
		int[] arr = new int[]{5, 10, 2, 1, 12};
		selectionSort(arr);
		out.println(x);
		arr = new int[]{4, 12, 4, 7, 19, 6};
		insertionSort(arr);
		out.println(y);
	}

	public static void selectionSort(int[] elements) {
		for (int j = 0; j < elements.length - 1; j++) {
			int minIndex = j;
			for (int k = j + 1; k < elements.length; k++)
				if (elements[k] < elements[minIndex]) {
					minIndex = k;   // Line 11
					x++;
				}
			if (j != minIndex) {
				int temp = elements[j];
				elements[j] = elements[minIndex];
				elements[minIndex] = temp;
			}
		}
	}

	private static void insertionSort(int[] elements) {
		for (int j = 1; j < elements.length; j++) {
			int temp = elements[j];
			int possibleIndex = j;
			while (possibleIndex > 0 && temp < elements[possibleIndex - 1]) {
				elements[possibleIndex] = elements[possibleIndex - 1];
				possibleIndex--;    // Line 10
				y++;
			}
			elements[possibleIndex] = temp;
		}
	}
}