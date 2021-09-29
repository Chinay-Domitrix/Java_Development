package mastermind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Mastermind {
	public static void main(String[] args) {
		try (Scanner fileReader = new Scanner(new File("Classwork/dataStructures/src/mastermind/Mastermind.txt"))) {
			var dataSet = new ArrayList<char[]>();
			while (fileReader.hasNextLine())
				dataSet.add(fileReader.nextLine().toCharArray());
			var key = new Object[] { dataSet.get(0), Arrays.toString(dataSet.get(0)) };
			for (var i = 1; i < dataSet.size(); i++) {
				var current = dataSet.get(i);
				var match = 0;
				var fail = 0;
				for (var j = 0; j < current.length; j++)
					if (((char[]) (key[0]))[j] == current[j])
						match++;
					else
						fail++;
				System.out.println("Code: " + ((String) key[1]) + "\nGuess: " + Arrays.toString(current)
						+ "\nCorrect Guesses: " + match + "\nIncorrect Guesses: " + fail);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
