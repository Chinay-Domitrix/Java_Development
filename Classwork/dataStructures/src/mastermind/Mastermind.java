package mastermind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Mastermind {
	public static void main(String[] args) {
		try (var fileReader = new Scanner(new File("Classwork/dataStructures/src/mastermind/Mastermind.txt"))) {
			var dataSet = new ArrayList<char[]>();
			while (fileReader.hasNextLine())
				dataSet.add(fileReader.nextLine().toCharArray());
			var key = new Object[]{dataSet.get(0), Arrays.toString(dataSet.get(0))};
			for (var i = 1; i < dataSet.size(); i++) {
				var current = dataSet.get(i);
				var matchT1 = 0;
				var failT1 = new ArrayList<Integer>();
				var matchT2 = 0;
				var failT2 = 0;
				for (var j = 0; j < current.length; j++)
					if (((char[]) key[0])[j] == current[j])
						matchT1++;
					else
						failT1.add(j);
				var key2 = new ArrayList<Character>();
				var current2 = new ArrayList<Character>();
				for (int j : failT1) {
					key2.add(((char[]) key[0])[j]);
					current2.add(current[j]);
				}
				for (Character character : current2)
					if (key2.contains(character)) {
						matchT2++;
						key2.remove(character);
					} else
						failT2++;
				out.println("Code:\t" + ((String) key[1]).replaceAll(", ", "").replace("[", "").replace("]", "")
						+ "\nGuess:\t" + Arrays.toString(current).replaceAll(", ", "").replace("[", "").replace("]", "")
						+ "\n\tCorrect Guess - Placement Correct:\t" + matchT1
						+ "\n\tCorrect Guess - Incorrectly Placed:\t" + matchT2 + "\n\tIncorrect Guesses:\t\t\t" + failT2);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
