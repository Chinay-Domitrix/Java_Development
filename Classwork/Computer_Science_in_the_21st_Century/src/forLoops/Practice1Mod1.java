package Classwork.Computer_Science_in_the_21st_Century.src.forLoops;

import java.util.Scanner;

class Practice1Mod1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int o = 0;
		int s = 0;
		int u = 0;
		System.out.print("How many scores would you like to input? ");
		int sA = in.nextInt();
		int counter = 0;
		for (int i = 0; i < sA; i++) {
			counter++;
			System.out.print("Score #" + counter + ": ");
			int score = (int) (Math.random() * 101);
			System.out.print(score + "\n");
			if (score >= 90 && score <= 100) {
				o++;
			} else if (score >= 70 && score <= 89) {
				s++;
			} else if (score >= 1 && score <= 69) {
				u++;
			}
		}
		System.out.println("Number of outstanding scores: " + o);
		System.out.println("Number of satisfactory scores: " + s);
		System.out.println("Number of outstanding scores: " + u);
		in.close();
	}
}
