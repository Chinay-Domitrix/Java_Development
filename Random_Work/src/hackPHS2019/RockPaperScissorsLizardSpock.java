package hackPHS2019;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

import static java.lang.Math.random;
import static java.lang.System.in;
import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static java.util.Objects.requireNonNull;

final class RockPaperScissorsLizardSpock {
	private static final Scanner scanner = new Scanner(in);
	private int playerWins = 0;
	private int compWins = 0;
	private int counter = 0;
	private int rounds;
	private String compPicks = null, name, RPSLS;

	RockPaperScissorsLizardSpock() throws Exception {
		out.print("Welcome to Rock, Paper, Scissors, Lizard, Spock! Please enter your first name, contestant. ");
		name = scanner.nextLine();
		out.printf("Hello %s! How many rounds do you want? (Please enter an odd number from one to nine in numerical form.) ", name);
		rounds = scanner.nextInt();
		assert (rounds != 2) && (rounds != 4) && (rounds != 6) && (rounds != 8) && (rounds >= 1) && (rounds <= 9) : "Unexpected value: " + rounds;
		out.print("Please enter a difficulty level from one to nine in numerical form: ");
		var diff = scanner.nextInt();
		assert (diff >= 1) && (diff <= 9) : "Unexpected value: " + diff;
		scanner.nextLine();
		if (diff == 1) l1();
		else if (diff == 2) l2();
		else if (diff == 3) l3();
		else if (diff == 4) l4();
		else if (diff == 5) l5();
		else if (diff == 6) l6();
		else if (diff == 7) l7();
		else if (diff == 8) l8();
		else l9();
		out.printf((compWins > playerWins) ? "\nYou lost the match, %s. Too bad... You can try again though!%n" : ((playerWins > compWins) ? "\nYou won the match, %s! Congratulations!%n" : "It's a tie, %s.%n"), name);
	}

	private void l1() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.printf("\nRound %d%n", counter);
			out.printf("Please enter an RPSLS choice %s: ", name);
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 6);
			if ((playerWins == 1) || (compWins == 1)) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if (r == 1) compPicks = "Computer picks rock";
			else if (r == 2) compPicks = "Computer picks paper";
			else if (r == 3) compPicks = "Computer picks scissors";
			else if (r == 4) compPicks = "Computer picks lizard";
			else if (r == 5) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void l2() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.println("\nRound " + counter);
			out.print("Please enter an RPSLS choice " + name + ": ");
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 26);
			if ((playerWins == 1) || (compWins == 1)) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if ((r > 0) && (r < 6)) compPicks = "Computer picks rock";
			else if ((r > 5) && (r < 11)) compPicks = "Computer picks paper";
			else if ((r > 10) && (r < 16)) compPicks = "Computer picks scissors";
			else if ((r > 15) && (r < 21)) compPicks = "Computer picks lizard";
			else if ((r > 20) && (r < 26)) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void l3() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.println("\nRound " + counter);
			out.print("Please enter an RPSLS choice " + name + ": ");
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 126);
			if ((playerWins == 1) || (compWins == 1)) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if ((r > 0) && (r < 26)) compPicks = "Computer picks rock";
			else if ((r > 25) && (r < 51)) compPicks = "Computer picks paper";
			else if ((r > 50) && (r < 76)) compPicks = "Computer picks scissors";
			else if ((r > 75) && (r < 101)) compPicks = "Computer picks lizard";
			else if ((r > 100) && (r < 126)) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void l4() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.println("\nRound " + counter);
			out.print("Please enter an RPSLS choice " + name + ": ");
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 626);
			if ((playerWins == 1) || (compWins == 1)) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if ((r > 0) && (r < 126)) compPicks = "Computer picks rock";
			else if ((r > 125) && (r < 151)) compPicks = "Computer picks paper";
			else if ((r > 150) && (r < 176)) compPicks = "Computer picks scissors";
			else if ((r > 175) && (r < 201)) compPicks = "Computer picks lizard";
			else if ((r > 200) && (r < 226)) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void l5() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.println("\nRound " + counter);
			out.print("Please enter an RPSLS choice " + name + ": ");
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 3126);
			if (playerWins == 1 || compWins == 1) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if ((r > 0) && (r < 626)) compPicks = "Computer picks rock";
			else if ((r > 625) && (r < 1251)) compPicks = "Computer picks paper";
			else if ((r > 1250) && (r < 1876)) compPicks = "Computer picks scissors";
			else if ((r > 1875) && (r < 2501)) compPicks = "Computer picks lizard";
			else if ((r > 2500) && (r < 3126)) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void l6() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.println("\nRound " + counter);
			out.print("Please enter an RPSLS choice " + name + ": ");
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 15626);
			if ((playerWins == 1) || (compWins == 1)) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if ((r > 0) && (r < 3126)) compPicks = "Computer picks rock";
			else if ((r > 3125) && (r < 6251)) compPicks = "Computer picks paper";
			else if ((r > 6250) && (r < 9376)) compPicks = "Computer picks scissors";
			else if ((r > 9375) && (r < 12501)) compPicks = "Computer picks lizard";
			else if ((r > 12500) && (r < 15626)) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void l7() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.println("\nRound " + counter);
			out.print("Please enter an RPSLS choice " + name + ": ");
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 78126);
			if ((playerWins == 1) || (compWins == 1)) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if ((r > 0) && (r < 15626)) compPicks = "Computer picks rock";
			else if ((r > 15625) && (r < 31251)) compPicks = "Computer picks paper";
			else if ((r > 31250) && (r < 46876)) compPicks = "Computer picks scissors";
			else if ((r > 46875) && (r < 62501)) compPicks = "Computer picks lizard";
			else if ((r > 62500) && (r < 78126)) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void l8() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.println("\nRound " + counter);
			out.print("Please enter an RPSLS choice " + name + ": ");
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 390626);
			if ((playerWins == 1) || (compWins == 1)) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if ((r > 0) && (r < 78126)) compPicks = "Computer picks rock";
			else if ((r > 78125) && (r < 156251)) compPicks = "Computer picks paper";
			else if ((r > 156250) && (r < 234376)) compPicks = "Computer picks scissors";
			else if ((r > 234375) && (r < 312501)) compPicks = "Computer picks lizard";
			else if ((r > 312500) && (r < 390626)) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void l9() throws Exception {
		for (var i = 0; i < rounds; i++) {
			counter++;
			out.println("\nRound " + counter);
			out.printf("Please enter an RPSLS choice %s: ", name);
			RPSLS = scanner.nextLine();
			var r = (int) (random() * 1953126);
			if ((playerWins == 1) || (compWins == 1)) break;
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("rock") || RPSLS.equals("Rock")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("paper") || RPSLS.equals("Paper")))
				out.println("Computer picks lizard");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("scissors") || RPSLS.equals("Scissors")))
				out.println("Computer picks Spock");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("lizard") || RPSLS.equals("Lizard")))
				out.println("Computer picks scissors");
			else if ((playerWins == compWins) && ((playerWins > 0) | (compWins > 0)) && (RPSLS.equals("spock") || RPSLS.equals("Spock")))
				out.println("Computer picks lizard");
			else if ((r > 0) && (r < 390626)) compPicks = "Computer picks rock";
			else if ((r > 78125) && (r < 781251)) compPicks = "Computer picks paper";
			else if ((r > 781250) && (r < 1171876)) compPicks = "Computer picks scissors";
			else if ((r > 1171875) && (r < 1562501)) compPicks = "Computer picks lizard";
			else if ((r > 1562500) && (r < 1953126)) compPicks = "Computer picks Spock";
			switcher(RPSLS);
			sleep(1250);
		}
	}

	private void switcher(@NotNull String RPSLS) {
		this.RPSLS = requireNonNull(RPSLS);
		if (RPSLS.equalsIgnoreCase("rock")) {
			out.println(compPicks);
			if (compPicks.equals("Computer picks lizard") | compPicks.equals("Computer picks scissors")) {
				out.println("You Win!");
				playerWins++;
			} else if (compPicks.equals("Computer picks rock")) out.println("It's a tie!");
			else {
				out.println("You Lose!");
				compWins++;
			}
		} else if (RPSLS.equalsIgnoreCase("paper")) {
			out.println(compPicks);
			if (compPicks.equals("Computer picks rock") | compPicks.equals("Computer picks Spock")) {
				out.println("You Win!");
				playerWins++;
			} else if (compPicks.equals("Computer picks paper")) out.println("It's a tie!");
			else {
				out.println("You Lose!");
				compWins++;
			}
		} else if (RPSLS.equalsIgnoreCase("scissors")) {
			out.println(compPicks);
			if (compPicks.equals("Computer picks paper") | compPicks.equals("Computer picks lizard")) {
				out.println("You Win!");
				playerWins++;
			} else if (compPicks.equals("Computer picks scissors")) out.println("It's a tie!");
			else {
				out.println("You Lose!");
				compWins++;
			}
		} else if (RPSLS.equalsIgnoreCase("lizard")) {
			out.println(compPicks);
			if (compPicks.equals("Computer picks Spock") | compPicks.equals("Computer picks paper")) {
				out.println("You Win!");
				playerWins++;
			} else if (compPicks.equals("Computer picks lizard")) out.println("It's a tie!");
			else {
				out.println("You Lose!");
				compWins++;
			}
		} else if (RPSLS.equalsIgnoreCase("spock")) {
			out.println(compPicks);
			if (compPicks.equals("Computer picks scissors") | compPicks.equals("Computer picks rock")) {
				out.println("You Win!");
				playerWins++;
			} else if (compPicks.equals("Computer picks Spock")) out.println("It's a tie!");
			else {
				out.println("You Lose!");
				compWins++;
			}
		} else throw new IllegalStateException("Unexpected value: " + RPSLS);
	}
}