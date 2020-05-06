package remoteLearning.april1.peopleSorter;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.String.valueOf;
import static java.lang.System.out;

public class PeopleSorter {
	private ArrayList<Person> people;

	private PeopleSorter() {
		people = new ArrayList<>();
	}

	public PeopleSorter(ArrayList<Person> people) {
		assert people.size() >= 1;
		this.people = people;
	}

	public static void main(String[] args) {
		var peopleSorter = new PeopleSorter();
	}

	public void add(Person p) {
		people.add(p);
	}

	private int linearSearch(Person p) {
		for (var count = 0; count < people.size(); count++) if (people.get(count).equals(p)) return count;
		return -1;
	}

	private int binarySearch(Person p) {
		this.selectionSort();
		int count = 0, low = 0, high = people.size() - 1;
		while (high >= low) {
			count++;
			if (people.get((low + high) / 2).equals(p)) break;
			else if (people.get((low + high) / 2).compareTo(p) > 0) low = ((low + high) / 2) + 1;
			else if (people.get((low + high) / 2).compareTo(p) < 0) high = ((low + high) / 2) - 1;
		}
		return count;
	}

	private int selectionSort() {
		int count = 0;
		for (int i = 0; i < people.size() - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < people.size(); j++) {
				count++;
				if (people.get(j).compareTo(people.get(minIndex)) < 0) minIndex = j;
			}
			var pH = people.get(minIndex);
			people.set(minIndex, people.get(i));
			people.set(i, pH);
		}
		return count;
	}

	private int insertionSort() {
		int count = 0;
		for (int i = 1; i < people.size(); i++) {
			var pH = people.get(i);
			var j = i;
			while (j > 0 && people.get(j - 1).compareTo(pH) > 0) {
				people.set(j, people.get(j - 1));
				j--;
			}
			people.set(j, pH);
		}
		return count;
	}

	private void print() {
		var x = new StringBuilder();
		for (var person : people) x.append(person).append("\n");
		if (valueOf(x.charAt(x.length() - 1)).equalsIgnoreCase("\n")) x.deleteCharAt(x.length() - 1);
		out.println(x.toString().trim());
	}

	private void shuffle() {
		for (int i = 0; i < people.size(); i++) {
			var currentElement = people.get(i);
			var randomIndex = i + new Random().nextInt(people.size() - i);
			people.set(i, people.get(randomIndex));
			people.set(randomIndex, currentElement);
		}
	}
}