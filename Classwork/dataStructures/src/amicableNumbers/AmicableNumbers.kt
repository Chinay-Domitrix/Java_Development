package amicableNumbers;

import static java.lang.System.out;
import static java.util.Arrays.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class AmicableNumbers {
	public static void main(String[] args) {
		try (var fileReader = new Scanner(new File("Classwork/Data_Structures/amicableNumbers/Amicable_Numbers.txt"))) {
			while (fileReader.hasNextLine()) {
				var nums = stream(fileReader.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				var sum1 = stream(factors(nums[0])).sum();
				var sum2 = stream(factors(nums[1])).sum();
				out.println("The numbers " + nums[0] + " and " + nums[1] + " are "
						+ (((sum1 == nums[1]) && (sum2 == nums[0])) ? "" : "not ") + "amicable.\n\tThe factors of "
						+ nums[0] + " are " + formatFactorString(factors(nums[0])) + ". The sum is " + sum1
						+ ".\n\tThe divisors of " + nums[1] + " are " + formatFactorString(factors(nums[1]))
						+ ". The sum is " + sum2 + ".\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static int[] factors(int input) {
		var factorList = new ArrayList<Integer>();
		for (var i = 1; i < input; i++)
			if (input % i == 0)
				factorList.add(i);
		return factorList.stream().mapToInt(i -> i).toArray();
	}

	private static String formatFactorString(int[] input) {
		var factorStringBuilder = new StringBuilder();
		for (var i : input)
			factorStringBuilder.append(i).append(", ");
		factorStringBuilder = new StringBuilder(factorStringBuilder.toString().trim())
				.deleteCharAt(factorStringBuilder.lastIndexOf(","));
		return factorStringBuilder.insert(factorStringBuilder.lastIndexOf(",") + 1, " and").toString();
	}
}
