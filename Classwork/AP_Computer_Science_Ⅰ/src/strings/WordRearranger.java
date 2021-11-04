package strings;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.lang.System.out;

public class WordRearranger {
	public static void main(String[] args) {
		out.println(task1("The quick brown fox jumped over the lazy dog"));
		out.println(task2("The quick brown fox jumped over the lazy dog"));
		out.println();
	}

	private static String task1(@NotNull String reversible) {
		StringBuilder returned = new StringBuilder();
		for (int i = reversible.length() - 1; i >= 0; i--)
			returned.append(reversible.charAt(i));
		return returned.toString();
	}

	public static String task2(@NotNull String input) {
		var stringArrayList = new ArrayList<String>();
		for (int i = 0; i < input.length(); i++)
			if (input.charAt(i) == ' ') {
				stringArrayList.add(input.substring(0, i));
				input = input.substring(i + 1);
				i = 0;
			}
		stringArrayList.add(input);
		StringBuilder returned = new StringBuilder();
		for (int i = stringArrayList.size() - 1; i >= 0; i--)
			returned.append(stringArrayList.get(i)).append(" ");
		return returned.toString();
	}
}
