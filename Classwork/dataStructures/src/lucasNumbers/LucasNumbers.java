package lucasNumbers;

import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;
import static java.util.Comparator.naturalOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

class LucasNumbers {
	public static void main(String[] args) {
		try (Scanner fileReader = new Scanner(new File("Classwork/dataStructures/src/lucasNumbers/LucasNumbers.txt"))) {
			var indices = new ArrayList<Integer>();
			var sequence = new ArrayList<BigInteger>();
			sequence.add(TWO);
			sequence.add(ONE);
			while (fileReader.hasNextLine())
				indices.add(parseInt(fileReader.nextLine()));
			indices.sort(naturalOrder());
			for (int i = 2; i <= indices.get(indices.size() - 1); i++)
				sequence.add(sequence.get(sequence.size() - 2).add(sequence.get(sequence.size() - 1)));
			for (Integer index : indices)
				out.println(sequence.get(index));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
