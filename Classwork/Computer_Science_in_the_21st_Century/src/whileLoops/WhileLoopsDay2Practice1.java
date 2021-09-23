package Classwork.Computer_Science_in_the_21st_Century.src.whileLoops;

import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

class WhileLoopsDay2Practice1 {
	public static void main(String[] args) throws InterruptedException {
		try (Scanner in = new Scanner(System.in)) {
			out.println("Welcome to the multiplication tutor!");
			sleep(1000);
			out.print("What is your name? ");
			String name = in.nextLine();
			sleep(750);
			out.println("Let's see how good you are at multiplication, " + name + ".");
			sleep(1000);
			out.println("Type -1 at any time to exit and see your score.");
			sleep(1000);
			int input;
			double counter = 0;
			double grade = 0;
			//noinspection ConstantConditions
			do {
				sleep(500);
				int num1 = (int) (Math.random() * 101) + 1;
				int num2 = (int) (Math.random() * 101) + 1;
				out.print("What is " + num1 + " times " + num2 + "? ");
				input = in.nextInt();
				sleep(500);
				if ((input != -1) && (input == (num1 * num2))) {
					out.println("Correct!");
					grade++;
					counter++;
				} else if ((input != -1) && (input != (num1 * num2))) {
					out.println("Incorrect. The answer is " + (num1 * num2));
					counter++;
				} else {
					out.println("You have terminated your lesson.");
					break;
				}
			} while (input != -1);
			double avg = grade / counter;
			out.println("Let's see, " + name + "! You got " + (int) grade + " out of " + (int) counter + " correct.");
			sleep(1000);
			if (avg <= (counter * .5)) out.println("You DEFINITELY need to keep on practicing!");
			else if ((avg > (counter * .5)) && (avg <= (counter * .75))) out.println("You ALMOST got it!");
			else if (avg == counter) out.println("You've MASTERED multiplication!");
		}
	}
}
