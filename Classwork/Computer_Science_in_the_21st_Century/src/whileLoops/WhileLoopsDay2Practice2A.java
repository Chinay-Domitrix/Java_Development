package Classwork.Computer_Science_in_the_21st_Century.src.whileLoops;

import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

class WhileLoopsDay2Practice2A {
	public static void main(String[] args) throws InterruptedException {
		try (Scanner in = new Scanner(System.in)) {
			int randNum = (int) (Math.random() * 11) + 1;
			int score = 5;
			while (score > 0) {
				out.print("Please enter a number between one and ten, with those two included. ");
				int input = in.nextInt();
				if (input == randNum) {
					out.println("Correct! The number was " + randNum + ". Your score is " + score + ".");
					break;
				} else {
					score--;
					out.println("Incorrect. Your score is now " + score + ".");
					sleep(500);
				}

			}
			if (score == 0) out.println("You lose. The answer was " + randNum + ".");
		}
	}
}
