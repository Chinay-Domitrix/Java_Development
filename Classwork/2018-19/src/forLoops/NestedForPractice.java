package forLoops;

import java.util.Scanner;

class NestedForPractice {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Please enter whichever program do you want to run as a number.");
			int progNum = in.nextInt();
			switch (progNum) {
				default:
					System.out.println("Error.");
					break;
				case 1:
					for (int i = 6; i > 1; i--) {
						for (int j = i; j < 6; j++) {
							System.out.print("*");
						}
						System.out.println();
					}
					break;
				case 2:
					for (int i = 0; i < 5; i++) {
						for (int j = i; j < 5; j++)
							System.out.print("*");
						System.out.println();
					}
					break;
				case 3:
					for (int i = 0; i < 3; i++) {
						for (int j = i; j < 3; j++)
							System.out.print("**");
						System.out.println();
					}
					break;
				case 4:
					for (int i = 1; i < 10; i++) {
						for (int j = 1; j < 11; j++) {
							System.out.print(i * j + "\t");
						}
						System.out.println();
					}
			}
		}
	}
}