package jollySorting;

import static java.util.Arrays.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class JollySorting {
	public static void main(String... args) {
		try (Scanner fileReader = new Scanner(new File("Classwork/dataStructures/src/jollySorting/JollySorting.txt"))) {
			while (fileReader.hasNextLine()) {
				var parse = stream(fileReader.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				sort(parse);
				var a = copyOfRange(parse, 0, (parse.length + 1) / 2);
				var b = copyOfRange(parse, (parse.length + 1) / 2, parse.length);
				var c = new int[parse.length];
				for (int i = 0, j = 0; i < c.length; i += 2, j++)
					c[i] = a[j];
				for (int i = 1, j = 0; i < c.length; i += 2, j++)
					c[i] = b[j];
				System.out.println(Arrays.toString(c));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
