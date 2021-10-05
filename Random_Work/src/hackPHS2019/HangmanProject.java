package hackPHS2019;

import static java.lang.Math.*;
import static java.lang.System.*;
import static java.lang.Thread.sleep;
import static java.util.Arrays.fill;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.iterate;

import java.util.Arrays;
import java.util.Scanner;

final class HangmanProject {
	/**
	 * This is where most of the variables which are significant to the game are
	 * declared, so the methods, excluding {@code main}, require no parameters.
	 */
	private static char[] hangman, guessList;
	private static String main, redo = null, inputCopy = null;
	private static int wins = 0, losses = 0;
	private Scanner scanner = new Scanner(in);

	/**
	 * This is where the game actually starts. Here, the method {@code game} is
	 * invoked and the Scanner is passed on to "game", and eventually "logic."
	 */
	HangmanProject() throws InterruptedException {
		game();
	}

	/**
	 * This method runs all of the initial processes required for the game to run,
	 * then passes logic flow to the method {@code logic} to run the game itself,
	 * and then takes over again for the (potential) home stretch.
	 */
	private void game() throws InterruptedException {
		// This is the loop allowing the user to play another round.
		do {
			/*
			 * This String declaration is the declaration of the String used for the user's
			 * input.
			 */
			String input;
			/*
			 * This if statement is for every round besides the first. It provides options
			 * for the user input.
			 */
			if (wins != 0 || losses != 0) {
				var recycle = false;
				if ((redo != null) && (redo.equalsIgnoreCase("yes") || redo.equalsIgnoreCase("y"))) {
					/*
					 * These following two lines and if/else contains the code which provides the
					 * user the opportunity to choose whether or not they want to reuse their
					 * previous list of words.
					 */
					out.print("Do want to reuse your words? ");
					var reuse = scanner.nextLine();
					if (reuse.equalsIgnoreCase("yes") || reuse.equalsIgnoreCase("y"))
						recycle = true;
					else if (reuse.equalsIgnoreCase("no") || reuse.equalsIgnoreCase("n"))
						continue;
					else
						throw new IllegalStateException("Unexpected value: " + reuse);
				}
				/*
				 * This if/else is what actually recycles the words, if the user wants to.
				 * Otherwise, the user has to input more words.
				 */
				if (recycle)
					input = inputCopy;
				else {
					out.print("Please enter your words and separate them with only spaces. ");
					input = scanner.nextLine();
					inputCopy = input;
				}
			} else {
				out.print("Please enter your words and separate them with only spaces. ");
				input = scanner.nextLine();
				inputCopy = input;
			}
			/*
			 * This is just in case the user added any leading or trailing spaces, since
			 * they hold the potential to throw off the random word algorithm.
			 */
			input = input.trim();
			/*
			 * This is the part where the code generates an array using the user's input and
			 * then picks a random word.
			 */
			var list = input.split(" ");
			var random = toIntExact(round(random() * list.length));
			main = list[random];
			/*
			 * This is the array which is displayed with the letter and underscores when the
			 * game is played.
			 */
			hangman = new char[main.length()];
			fill(hangman, '_');
			/*
			 * This is the array keeping track of the letters the user has already guessed,
			 * and then displays it along with the rest of the game's UI.
			 */
			guessList = new char[52];
			fill(guessList, ' ');
			/* This is just to squash potential bugs. */
			if (Arrays.toString(guessList).equals("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"))
				err.println("Overload Error.");
			/*
			 * This passes control to the method {@link #logic}, which will be pass back at
			 * the end of the invocation of the {@link #logic} method
			 */
			logic(scanner);
			/*
			 * These lines right before the end of the loop are what prompt the user on if
			 * they want to play again. If the user says yes, the loop just reiterates.
			 * Otherwise, the while loop is terminated and the code moves on to the win/lose
			 * counter.
			 */
			out.print("Do you want to play again? ");
			redo = scanner.nextLine();
			if (redo.equalsIgnoreCase("no") || redo.equalsIgnoreCase("n")) {
				out.println("You have quit the game.");
				break;
			}
		} while (true);
		/*
		 * This displays the user's win and loss counter, after the game has terminated
		 * for the last time.
		 */
		out.printf("\nYou had %d wins and %d losses.%n", wins, losses);
	}

	/*
	 * This method contains the primary logic for the game and the ASCII art
	 * messages for both a winning instance and a losing instance.
	 */
	private void logic(Scanner in) throws InterruptedException {
		/*
		 * This is the ASCII art for the win and lose messages. For the sake of a
		 * surprise, it is not declared with the rest of the variables, as the formatter
		 * in Eclipse places class level variables before methods, ruining the surprise.
		 * For the same reason, the Strings are split so the user cannot visually
		 * identify what they say without modifying the code.
		 */
		var winner = new String[] {
				"YYYYYYY       YYYYYYY                                         "
						+ "WWWWWWWW                           WWWWWWWW  iiii                     ",
				"Y:::::Y       Y:::::Y                                         "
						+ "W::::::W                           W::::::W i::::i                    ",
				"Y:::::Y       Y:::::Y                                         "
						+ "W::::::W                           W::::::W  iiii                     ",
				"Y::::::Y     Y::::::Y                                         "
						+ "W::::::W                           W::::::W                           ",
				"YYY:::::Y   Y:::::YYY   ooooooooooo    uuuuuu    uuuuuu       "
						+ " W:::::W           WWWWW           W:::::W iiiiiii  nnnn  nnnnnnnn    ",
				"   Y:::::Y Y:::::Y    oo:::::::::::oo  u::::u    u::::u       "
						+ "  W:::::W         W:::::W         W:::::W  i:::::i  n:::nn::::::::nn  ",
				"    Y:::::Y:::::Y    o:::::::::::::::o u::::u    u::::u       "
						+ "   W:::::W       W:::::::W       W:::::W    i::::i  n::::::::::::::nn ",
				"     Y:::::::::Y     o:::::ooooo:::::o u::::u    u::::u       "
						+ "    W:::::W     W:::::::::W     W:::::W     i::::i  nn:::::::::::::::n",
				"      Y:::::::Y      o::::o     o::::o u::::u    u::::u       "
						+ "     W:::::W   W:::::W:::::W   W:::::W      i::::i    n:::::nnnn:::::n",
				"       Y:::::Y       o::::o     o::::o u::::u    u::::u       "
						+ "      W:::::W W:::::W W:::::W W:::::W       i::::i    n::::n    n::::n",
				"       Y:::::Y       o::::o     o::::o u::::u    u::::u       "
						+ "       W:::::W:::::W   W:::::W:::::W        i::::i    n::::n    n::::n",
				"       Y:::::Y       o::::o     o::::o u:::::uuuu:::::u       "
						+ "        W:::::::::W     W:::::::::W         i::::i    n::::n    n::::n",
				"       Y:::::Y       o:::::ooooo:::::o u:::::::::::::::uu     "
						+ "         W:::::::W       W:::::::W         i::::::i   n::::n    n::::n",
				"    YYYY:::::YYYY    o:::::::::::::::o  u:::::::::::::::u     "
						+ "          W:::::W         W:::::W          i::::::i   n::::n    n::::n",
				"    Y:::::::::::Y     oo:::::::::::oo    uu::::::::uu:::u     "
						+ "           W:::W           W:::W           i::::::i   n::::n    n::::n",
				"    YYYYYYYYYYYYY       ooooooooooo        uuuuuuuu  uuuu     "
						+ "            WWW             WWW            iiiiiiii   nnnnnn    nnnnnn" };
		var loser = new String[] {
				"YYYYYYY       YYYYYYY                                         "
						+ "LLLLLLLLLLL                                                                      ",
				"Y:::::Y       Y:::::Y                                         "
						+ "L:::::::::L                                                                      ",
				"Y:::::Y       Y:::::Y                                         "
						+ "L:::::::::L                                                                      ",
				"Y::::::Y     Y::::::Y                                         "
						+ "LL:::::::LL                                                                      ",
				"YYY:::::Y   Y:::::YYY   ooooooooooo    uuuuuu    uuuuuu       "
						+ "  L:::::L                   ooooooooooo       ssssssssss        eeeeeeeeeeee     ",
				"   Y:::::Y Y:::::Y    oo:::::::::::oo  u::::u    u::::u       "
						+ "  L:::::L                 oo:::::::::::oo   ss::::::::::s     ee::::::::::::ee   ",
				"    Y:::::Y:::::Y    o:::::::::::::::o u::::u    u::::u       "
						+ "  L:::::L                o:::::::::::::::o ss:::::::::::::s   e::::::eeeee:::::ee",
				"     Y:::::::::Y     o:::::ooooo:::::o u::::u    u::::u       "
						+ "  L:::::L                o:::::ooooo:::::o s::::::ssss:::::s e::::::e     e:::::e",
				"      Y:::::::Y      o::::o     o::::o u::::u    u::::u       "
						+ "  L:::::L                o::::o     o::::o  s:::::s  ssssss  e:::::::eeeee::::::e",
				"       Y:::::Y       o::::o     o::::o u::::u    u::::u       "
						+ "  L:::::L                o::::o     o::::o    s::::::s       e:::::::::::::::::e ",
				"       Y:::::Y       o::::o     o::::o u::::u    u::::u       "
						+ "  L:::::L                o::::o     o::::o       s::::::s    e::::::eeeeeeeeeee  ",
				"       Y:::::Y       o::::o     o::::o u:::::uuuu:::::u       "
						+ "  L:::::L         LLLLLL o::::o     o::::o ssssss   s:::::s  e:::::::e           ",
				"       Y:::::Y       o:::::ooooo:::::o u:::::::::::::::uu     "
						+ "LL:::::::LLLLLLLLL:::::L o:::::ooooo:::::o s:::::ssss::::::s e::::::::e          ",
				"    YYYY:::::YYYY    o:::::::::::::::o  u:::::::::::::::u     "
						+ "L::::::::::::::::::::::L o:::::::::::::::o s::::::::::::::s   e::::::::eeeeeeee  ",
				"    Y:::::::::::Y     oo:::::::::::oo    uu::::::::uu:::u     "
						+ "L::::::::::::::::::::::L  oo:::::::::::oo   s:::::::::::ss     ee:::::::::::::e  ",
				"    YYYYYYYYYYYYY       ooooooooooo        uuuuuuuu  uuuu     "
						+ "LLLLLLLLLLLLLLLLLLLLLLLL    ooooooooooo      sssssssssss         eeeeeeeeeeeeee  " };
		/*
		 * This determines whether or not the user has won the round after every
		 * sub-round of the game.
		 */
		var breaker = false;
		/*
		 * This counter counts down the amount of misses left, starting from six, the
		 * highest number of misses allowed. This and guessListIndexes are both declared
		 * here so they can reset once the game reboots for another round.
		 */
		var counter = 6;
		/*
		 * This is the variable in charge of keeping track of the indexes of the list of
		 * used letters.
		 */
		var guessListIndexes = 0;
		/*
		 * This is the do while loop which runs the bulk of the game's underlying logic.
		 */
		do {
			/*
			 * This loop prints out the letters guessed so far. As seen in the method
			 * "game," the blanks are represented as underscores.
			 */
			for (var x : hangman)
				out.print(x + " ");
			out.println();
			/*
			 * The following four lines make it possible to print out the letters which have
			 * been guessed as a StringBuilder, which is a companion class to String.
			 */
			var guessed = new StringBuilder();
			for (var x : guessList)
				guessed.append(x).append(" ");
			guessed = new StringBuilder(guessed.toString().trim());
			/*
			 * This is where I print out the remaining number of misses, the letters guessed
			 * so far, and the prompt for the user to input their guess.
			 */
			out.printf("Misses left: %d\nLetters guessed so far: %s\nGuess Letter: ", counter, guessed);
			/* This is where the user inputs their guess. */
			var guess = in.nextLine().charAt(0);
			/*
			 * This if/else is what determines whether or not the the user's letter input is
			 * in the word.
			 */
			if (main.indexOf(guess) >= 0) {
				out.printf("Found %s!%n", guess);
				/*
				 * This for loop is what adds the letter to the array which is printed as the
				 * letters and underscores.
				 */
				iterate(main.indexOf(guess), i -> i >= 0, i -> main.indexOf(guess, i + 1))
						.forEachOrdered(i -> hangman[i] = guess);
			} else {
				out.printf("No %s!%n", guess);
				counter--;
			}
			/*
			 * This if statement adds the character guessed to guessList only if the
			 * character is not in the array.
			 */
			if (Arrays.toString(guessList).indexOf(guess) < 0) {
				guessList[guessListIndexes] = guess;
				guessListIndexes++;
			}
			/*
			 * This loop structure sorts the list of letters which are were already guessed.
			 */
			for (var i = 1; i < guessList.length; i++) {
				var j = i;
				while ((j > 0) && (guessList[j] < guessList[j - 1])) {
					var ph = guessList[j];
					guessList[j] = guessList[j - 1];
					guessList[j - 1] = ph;
					j--;
				}
			}
			out.println();
			/* This causes a break between the rounds. */
			sleep(750);
			/* The following five lines determine whether or not the round is complete. */
			var y = new StringBuilder();
			for (var x : hangman)
				y.append(x);
			if (y.indexOf("_") < 0)
				breaker = true;
			/*
			 * This if statement prints win ASCII art if the user wins the round, and then
			 * breaks the loop in order to go back to the method "game."
			 */
			if (breaker) {
				out.println();
				wins++;
				stream(winner).forEachOrdered(out::println);
				break;
			}
		} while (counter > 0);
		/*
		 * This if statement prints the word and the loss ASCII art if the user loses
		 * the round.
		 */
		if (counter == 0) {
			out.printf("You were hung! The word was %s.%n", main);
			stream(loser).forEachOrdered(out::println);
			losses++;
		}
	}
}
