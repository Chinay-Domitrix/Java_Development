package bonusWork;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

import static java.lang.Math.random;
import static java.lang.String.format;
import static java.lang.System.out;

class NameAndDescriptor {
	private NameAndDescriptor() {
		final var NamesAndDescriptors = new String[][] {
				new String[] { "Pranav", "Sashi", "Nick", "Arsh", "Kush", "Veer", "Dylan", "Yathin", "Omkar", "Tridib",
						"Joe", "Aditya", "Ishan", "Jill", "Abhinaya", "Chirag", "Harshitha", "Eshita", "Jeslin", "Amit",
						"Sridula" },
				new String[] { "Confused", "Memorable", "Robotics", "Interesting", "Procrastinator", "Happy", "Crazy",
						"Table Tennis", "Basketball", "Hardworking", "Zombie", "Video Games", "Smart", "Athletic",
						"Outgoing", "Curious", "Friendly", "Creative", "Badminton", "Artistic" } };
		out.printf("%nYour score was %d.%n", logic(NamesAndDescriptors));
	}

	private static int logic(@NotNull final String[]... a) throws IllegalStateException {
		var score = 0;
		final var c = a[1];
		String answer;
		do {
			var b = pickName(a);
			switch (b) {
				case "Pranav M":
					answer = c[0];
					break;
				case "Sashi":
					answer = c[1];
					break;
				case "Nick":
					answer = c[2];
					break;
				case "Arsh":
					answer = c[3];
					break;
				case "Pranav K":
					answer = c[4];
					break;
				case "Kush":
					answer = c[5];
					break;
				case "Veer":
					answer = c[6];
					break;
				case "Dylan":
					answer = c[7];
					break;
				case "Yathin":
				case "Aditya":
					answer = c[8];
					break;
				case "Omkar":
					answer = c[9];
					break;
				case "Tridib":
					answer = c[10];
					break;
				case "Joe":
					answer = c[11];
					break;
				case "Ishan":
					answer = c[12];
					break;
				case "Jill":
					answer = c[13];
					break;
				case "Abhinaya":
					answer = c[14];
					break;
				case "Chirag":
					answer = c[15];
					break;
				case "Harshitha":
					answer = c[16];
					break;
				case "Eshita":
				case "Jeslin":
					answer = c[17];
					break;
				case "Amit":
					answer = c[18];
					break;
				case "Sridula":
					answer = c[19];
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + b);
			}
			out.printf("The name is %s.%n%n", b);
			int counter = 0;
			for (final var x : c) {
				counter++;
				out.print(x + "\t\t");
				if (counter % 5 == 0)
					out.println();
			}
			out.print("\nPick a descriptor word from the preceding list for the name listed. ");
			final var choice = new Scanner(System.in).nextLine();
			if (choice.equalsIgnoreCase("quit"))
				return score;
			else if (choice.equalsIgnoreCase(answer)) {
				score++;
				out.print("You got it right! Do you want to guess again? ");
				final var redo = new Scanner(System.in).nextLine();
				if (redo.equalsIgnoreCase("no"))
					return score;
				else if (redo.equalsIgnoreCase("yes"))
					out.println();
				else
					throw new IllegalStateException("Unexpected value: " + redo);
			} else if (!choice.equalsIgnoreCase(answer)) {
				if (score != 0)
					score--;
				out.print("You got it wrong. Do you want to guess again? ");
				final var redo = new Scanner(System.in).nextLine();
				if (redo.equalsIgnoreCase("no"))
					return score;
				else if (redo.equalsIgnoreCase("yes"))
					out.println();
				else
					throw new IllegalStateException("Unexpected value: " + redo);
			}
		} while (true);
	}

	public static void main(final String... args) {
		new NameAndDescriptor();
	}

	@NotNull
	private static String pickName(@NotNull final String[]... a) {
		final var b = a[0];
		var name = b[(int) (random() * b.length - 1)];
		final var c = new char[] { 'M', 'K' };
		if (name.equals("Pranav"))
			name += format(" %s", c[(int) (random() * c.length - 1)]);
		return name;
	}
}
