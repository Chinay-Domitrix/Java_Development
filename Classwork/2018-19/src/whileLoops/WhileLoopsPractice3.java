package whileLoops;

import java.util.Scanner;

import static java.lang.System.out;

class WhileLoopsPractice3 {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			out.println("Enter your grades. When you are done, type \"-1\".");
			int counter = 0;
			double grade = 0;
			double gradeIn;
			do {
				gradeIn = in.nextDouble();
				if (gradeIn != -1) {
					grade += gradeIn;
					counter++;
				} else break;
			} while (gradeIn != -1);
			out.println("Your average, when rounded, is " + (Math.round((grade / counter))) + ".");
		}
	}
}
