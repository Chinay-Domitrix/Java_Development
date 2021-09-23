package Classwork.Computer_Science_in_the_21st_Century.src.forLoops;

import java.util.Scanner;

class Practice1Mod2 {
	public static void main(String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		int o = 0;
		int s = 0;
		int u = 0;
		System.out.print("How many scores would you like to input? ");
		int sA = in.nextInt();
		int counter = 0;
		int reLeft = 3;
		for (int i = 0; i < sA; i++) {
			counter++;
			System.out.print("Please enter score #" + counter + ": ");
			double score = in.nextDouble();
			in.nextLine();
			System.out.println();
			if (score >= 90 && score <= 100) {
				o++;
			} else if (score >= 75 && score <= 89) {
				s++;
			} else if (score < 75 && reLeft > 0) {
				System.out.print("Do you want to take a retake? ");
				String re = in.nextLine();
				if (re.equals("yes") || re.equals("Yes")) {
					i--;
					counter--;
					reLeft--;
					System.out.println("You have " + reLeft + " retakes left.");
				} else if (re.equals("no") || re.equals("No")) {
					u++;
				} else {
					System.out.println("Error.");
					break;
				}
			} else if (score < 75 && reLeft == 0) {
				System.out.println("You have failed. You shall not be allowed to continue.");
				Thread.sleep(10000);
				System.out.println("Well, what are you waiting for? You can leave now...");
				Thread.sleep(10000);
				System.out.println("Umm... Please leave... You're making me uncomfortable...");
				Thread.sleep(10000);
				System.out.println("LEAVE!!!");
				System.exit(0);
				break;
			}
		}
		System.out.println("Number of outstanding scores: " + o);
		System.out.println("Number of satisfactory scores: " + s);
		System.out.println("Number of unsatisfactory scores: " + u);
		in.close();
	}
}
