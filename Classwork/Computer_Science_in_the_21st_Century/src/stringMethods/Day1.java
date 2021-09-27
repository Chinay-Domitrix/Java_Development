package stringMethods;

import java.util.Scanner;

import static java.lang.Math.round;
import static java.lang.System.err;
import static java.lang.System.out;

public class Day1 {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			out.print("What program do you want to run? ");
			final String main = "The Five Boxing Wizards Jump Quickly";
			switch (in.nextInt()) {
				default:
					err.println("That program does not exist");
					break;
				case 1:
					for (int i = main.length() - 1; i >= 0; i--)
						out.print(main.charAt(i));
					out.println();
					break;
				case 2:
					StringBuilder mainInverted = new StringBuilder();
					for (int i = main.length() - 1; i >= 0; i--)
						mainInverted.append(main.charAt(i));
					out.println(mainInverted);
					break;
				case 3:
					out.println(main.toUpperCase());
					break;
				case 4:
					String mod = main;
					mod = mod.replace('a', 'W');
					mod = mod.replace('e', 'W');
					mod = mod.replace('i', 'W');
					mod = mod.replace('o', 'W');
					mod = mod.replace('u', 'W');
					out.println(mod);
					break;
				case 5:
					StringBuilder s = new StringBuilder();
					for (int i = 0; i < main.length(); i += 2)
						s.append(main.charAt(i));
					out.println(s);
					break;
				case 6:
					String[] c6 = main.split(" ");
					out.print("There are " + c6.length + " words.");
					break;
				case 7:
					String[] c7 = main.split(" ");
					int average = 0;
					for (String x : c7)
						average += x.length();
					average /= (double) c7.length;
					out.print("The average word length is approximately" + round(average) + " words.");
					break;
				case 8:
					String[] c8 = main.split(" ");
					StringBuilder y = new StringBuilder();
					for (int i = c8.length - 1; i >= 0; i--)
						y.append(c8[i]).append(' ');
					out.print(y.toString().trim());
					break;
			}
		}
	}
}
