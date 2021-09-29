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
			answer = switch (b) {
				case "Pranav M" -> c[0];
				case "Sashi" -> c[1];
				case "Nick" -> c[2];
				case "Arsh" -> c[3];
				case "Pranav K" -> c[4];
				case "Kush" -> c[5];
				case "Veer" -> c[6];
				case "Dylan" -> c[7];
				case "Yathin", "Aditya" -> c[8];
				case "Omkar" -> c[9];
				case "Tridib" -> c[10];
				case "Joe" -> c[11];
				case "Ishan" -> c[12];
				case "Jill" -> c[13];
				case "Abhinaya" -> c[14];
				case "Chirag" -> c[15];
				case "Harshitha" -> c[16];
				case "Eshita", "Jeslin" -> c[17];
				case "Amit" -> c[18];
				case "Sridula" -> c[19];
				default -> throw new IllegalStateException("Unexpected value: " + b);
			};
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
