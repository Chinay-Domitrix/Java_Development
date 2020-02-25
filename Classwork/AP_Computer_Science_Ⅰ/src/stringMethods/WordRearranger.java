package stringMethods;

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
		String returned = "";
		for (int i = reversible.length() - 1; i >= 0; i--) returned += reversible.substring(i, i + 1);
		return returned;
	}

	public static String task2(@NotNull String input) {
		var stringArrayList = new ArrayList<String>();
		for (int i = 0; i < input.length(); i++)
			if (input.substring(i, i + 1).equals(" ")) {
				stringArrayList.add(input.substring(0, i));
				input = input.substring(i + 1);
				i = 0;
			}
		stringArrayList.add(input);
		var returned = "";
		for (int i = stringArrayList.size() - 1; i >= 0; i--) returned += stringArrayList.get(i) + " ";
		return returned;
	}
}
