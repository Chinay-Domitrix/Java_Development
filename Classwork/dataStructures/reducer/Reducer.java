package Classwork.dataStructures.reducer;

import static java.lang.Integer.parseInt;
import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reducer {
	public static void main(String... args) {
		try (Scanner fileReader = new Scanner(new File("Classwork/Data_Structures/reducer/Reducer.txt"))) {
			while (fileReader.hasNextLine()) {
				String[] nums = fileReader.next().split("/");
				int numerator = parseInt(nums[0]), denominator = parseInt(nums[1]);
				nums = ((numerator / gcd(numerator, denominator)) + "/" + (denominator / gcd(numerator, denominator)))
						.split("/");
				numerator = parseInt(nums[0]);
				denominator = parseInt(nums[1]);
				out.println(((numerator / denominator == 0) ? "" : (numerator / denominator) + " ")
						+ ((numerator % denominator == 0) ? "" : numerator % denominator) + '/' + denominator);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}
}
