package Classwork.Computer_Science_in_the_21st_Century.src.whileLoops;

import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

class Bank {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int pin = (int) (Math.random() * 90000) + 10000;
		int counter = 0;
		out.println("Welcome to the bank.");
		while (counter <= 3) {
			counter++;
			out.print("Please enter your temporary PIN: ");
			int input = in.nextInt();
			if (pin == input) {
				out.println("\nPIN accepted. You now have access to your account.");
				break;
			} else if (counter <= 3) out.println("\nIncorrect PIN. Try again.");
			else err.println("You have run out of tries. Account locked.");
		}
		in.close();
	}
}
