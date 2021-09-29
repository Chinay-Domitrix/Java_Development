package switchStatements;

import java.util.Scanner;

import static java.lang.System.out;

class Calc {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			out.print("Would you like to add, average, multiply, or subtract? ");
			String choose = in.nextLine();
			out.println("Please enter a your values.");
			double num1 = in.nextDouble(), num2 = in.nextDouble(), num3 = in.nextDouble();
			switch (choose) {
				case "add", "Add" -> {
					double sum = num1 + num2 + num3;
					out.println(sum);
				}
				case "average", "Average" -> {
					double average = (num1 + num2 + num3) / 3;
					out.println(average);
				}
				case "multiply", "Multiply" -> {
					if (num1 < num2) {
						double ph = num1;
						num1 = num2;
						num2 = ph;
					}
					if (num1 < num3) {
						double ph = num1;
						num1 = num3;
						num3 = ph;
					}
					if (num2 < num3) {
						num3 = num2;
					}
					double product = num1 * num3;
					out.println(product);
				}
				case "subtract", "Subtract" -> {
					if (num1 < num2) {
						double ph = num1;
						num1 = num2;
						num2 = ph;
					}
					if (num1 < num3) {
						double ph = num1;
						num1 = num3;
						num3 = ph;
					}
					if (num2 < num3) {
						num3 = num2;
					}
					double difference = num1 - num3;
					out.println(difference);
				}
				default -> out.println("Error");
			}
		}
	}
}
