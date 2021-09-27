package remoteLearning.march26;

import java.util.ArrayList;

import static java.lang.Math.random;
import static java.lang.String.valueOf;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

/**
 * This class has a private field that will hold an ArrayList of "words" which
 * are randomly generated Strings of a given length. The constructor will fill
 * the ArrayList as specified and print its initial state. You will write a
 * selectionSort method to sort the words ArrayList and return the number of
 * COMPARISONS required to perform the sort.
 * <p>
 * The main method should run immediately, and you don't necessarily need to
 * change it to unit test.
 */
public class ArrayListSortDemo {
	private ArrayList<String> words;

	/**
	 * Constructor initializes the ArrayList words with a given number of words of a
	 * given length and prints them
	 *
	 * @param arrayListSize number of elements to add to ArrayList
	 * @param wordLength    size of the random Strings that make up individual words
	 */
	private ArrayListSortDemo(int arrayListSize, int wordLength) {
		words = new ArrayList<>();
		range(0, arrayListSize).forEachOrdered(i -> words
				.add(range(0, wordLength).mapToObj(j -> valueOf((char) ((random() * 26) + 65))).collect(joining())));
		out.println(words);
	}

	public static void main(String[] args) {
		var arrayListSortDemo = new ArrayListSortDemo(10, 5);
		out.println("Performed a Selection sort with " + arrayListSortDemo.selectionSort() + " comparisons");
		out.println(arrayListSortDemo.words);
	}

	private int selectionSort() {
		int counter = 0;
		for (int i = 0; i < words.size() - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < words.size(); j++)
				if (words.get(j).compareTo(words.get(minIndex)) < 0) {
					minIndex = j;
					counter++;
				}
			var pH = words.get(minIndex);
			words.set(minIndex, words.get(i));
			words.set(i, pH);
		}
		return counter;
	}
}
