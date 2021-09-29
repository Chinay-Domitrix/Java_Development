package parallelArrays;

import java.util.Scanner;

import static java.lang.System.out;

class Day2Practice1 {

	public static void main(final String[] args) {
		@SuppressWarnings("unused")
		final String[] n = { "Clayton Kershaw", "Jake Arrieta", "David Price", "Max Scherzer", "Chris Sale",
				"Dallas Keuchel", "Zack Greinke", "Corey Kluber", "Gerrit Cole" },
				t = { "Dodgers", "Cubs", "Red Sox", "Nationals", "White Sox", "Astros", "Dodgers", "Indians",
						"Pirates" };
		final int[] w = { 16, 22, 18, 14, 13, 20, 19, 9, 19 }, l = { 7, 6, 5, 12, 11, 8, 3, 16, 8 },
				g = { 33, 33, 32, 33, 31, 33, 32, 32, 32 };
		final double[] i = { 232.2, 229, 220.1, 228.2, 208.2, 232, 222.2, 222, 208 },
				s = { 11.64, 9.28, 9.19, 10.86, 11.82, 8.38, 8.08, 9.93, 8.74 },
				b = { 1.62, 1.89, 1.92, 1.34, 1.81, 1.98, 1.62, 1.82, 1.9 },
				era = { 2.13, 1.77, 2.45, 2.79, 3.41, 2.48, 1.66, 3.49, 2.6 };
		final Scanner in = new Scanner(System.in);
		while (true) {
			print("Which player�s stats would you like to research? ");
			final String nI = in.nextLine();
			if (nI.equals("quit") || nI.equals("Quit"))
				break;
			println(statReturn(in, nI, n, w, l, g, i, s, b, era));
		}
		in.close();
	}

	private static void println(final String a) {
		print(a + "\n");
	}

	private static void print(final String a) {
		out.print(a);
	}

	private static String statReturn(final Scanner in, final String z, final String[] n, final int[] w, final int[] l,
			final int[] g, final double[] i, final double[] s, final double[] b, final double[] era) {
		int a;
		String r = null;
		print("Which stat would you like? ");
		final String sI = in.nextLine();
		switch (z) {
			case "Clayton Kershaw" -> {
				a = 0;
				switch (sI) {
					case "teams", "Teams", "wins", "Wins" -> r = n[a] + " has " + w[a] + " wins.";
					case "losses", "Losses" -> r = n[a] + " has " + l[a] + " losses.";
					case "games", "Games" -> r = n[a] + " has played " + g[a] + " games.";
					case "ip", "IP" -> r = n[a] + " has " + i[a] + " innings pitched.";
					case "k/9", "K/9" -> r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
					case "bb/9", "BB/9", "Bb/9" -> r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
					case "era", "Era", "ERA" -> r = n[a] + " has a " + era[a] + " run average.";
				}
			}
			case "Jake Arrieta" -> {
				a = 1;
				switch (sI) {
					case "teams":
					case "Teams":
					case "wins":
					case "Wins":
						r = n[a] + " has " + w[a] + " wins.";
						break;
					case "losses":
					case "Losses":
						r = n[a] + " has " + l[a] + " losses.";
						break;
					case "games":
					case "Games":
						r = n[a] + " has played " + g[a] + " games.";
						break;
					case "ip":
					case "IP":
						r = n[a] + " has " + i[a] + " innings pitched.";
						break;
					case "k/9":
					case "K/9":
						r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
						break;
					case "bb/9":
					case "BB/9":
					case "Bb/9":
						r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
						break;
					case "era":
					case "Era":
					case "ERA":
						r = n[a] + " has a " + era[a] + " run average.";
						break;
				}
			}
			case "David Price" -> {
				a = 2;
				switch (sI) {
					case "teams":
					case "Teams":
					case "wins":
					case "Wins":
						r = n[a] + " has " + w[a] + " wins.";
						break;
					case "losses":
					case "Losses":
						r = n[a] + " has " + l[a] + " losses.";
						break;
					case "games":
					case "Games":
						r = n[a] + " has played " + g[a] + " games.";
						break;
					case "ip":
					case "IP":
						r = n[a] + " has " + i[a] + " innings pitched.";
						break;
					case "k/9":
					case "K/9":
						r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
						break;
					case "bb/9":
					case "BB/9":
					case "Bb/9":
						r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
						break;
					case "era":
					case "Era":
					case "ERA":
						r = n[a] + " has a " + era[a] + " run average.";
						break;
				}
			}
			case "Max Scherzer" -> {
				a = 3;
				switch (sI) {
					case "teams":
					case "Teams":
					case "wins":
					case "Wins":
						r = n[a] + " has " + w[a] + " wins.";
						break;
					case "losses":
					case "Losses":
						r = n[a] + " has " + l[a] + " losses.";
						break;
					case "games":
					case "Games":
						r = n[a] + " has played " + g[a] + " games.";
						break;
					case "ip":
					case "IP":
						r = n[a] + " has " + i[a] + " innings pitched.";
						break;
					case "k/9":
					case "K/9":
						r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
						break;
					case "bb/9":
					case "BB/9":
					case "Bb/9":
						r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
						break;
					case "era":
					case "Era":
					case "ERA":
						r = n[a] + " has a " + era[a] + " run average.";
						break;
				}
			}
			case "Chris Sale" -> {
				a = 4;
				switch (sI) {
					case "teams":
					case "Teams":
					case "wins":
					case "Wins":
						r = n[a] + " has " + w[a] + " wins.";
						break;
					case "losses":
					case "Losses":
						r = n[a] + " has " + l[a] + " losses.";
						break;
					case "games":
					case "Games":
						r = n[a] + " has played " + g[a] + " games.";
						break;
					case "ip":
					case "IP":
						r = n[a] + " has " + i[a] + " innings pitched.";
						break;
					case "k/9":
					case "K/9":
						r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
						break;
					case "bb/9":
					case "BB/9":
					case "Bb/9":
						r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
						break;
					case "era":
					case "Era":
					case "ERA":
						r = n[a] + " has a " + era[a] + " run average.";
						break;
				}
			}
			case "Dallas Keuchel" -> {
				a = 5;
				switch (sI) {
					case "teams":
					case "Teams":
					case "wins":
					case "Wins":
						r = n[a] + " has " + w[a] + " wins.";
						break;
					case "losses":
					case "Losses":
						r = n[a] + " has " + l[a] + " losses.";
						break;
					case "games":
					case "Games":
						r = n[a] + " has played " + g[a] + " games.";
						break;
					case "ip":
					case "IP":
						r = n[a] + " has " + i[a] + " innings pitched.";
						break;
					case "k/9":
					case "K/9":
						r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
						break;
					case "bb/9":
					case "BB/9":
					case "Bb/9":
						r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
						break;
					case "era":
					case "Era":
					case "ERA":
						r = n[a] + " has a " + era[a] + " run average.";
						break;
				}
			}
			case "Zack Greinke" -> {
				a = 6;
				switch (sI) {
					case "teams":
					case "Teams":
					case "wins":
					case "Wins":
						r = n[a] + " has " + w[a] + " wins.";
						break;
					case "losses":
					case "Losses":
						r = n[a] + " has " + l[a] + " losses.";
						break;
					case "games":
					case "Games":
						r = n[a] + " has played " + g[a] + " games.";
						break;
					case "ip":
					case "IP":
						r = n[a] + " has " + i[a] + " innings pitched.";
						break;
					case "k/9":
					case "K/9":
						r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
						break;
					case "bb/9":
					case "BB/9":
					case "Bb/9":
						r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
						break;
					case "era":
					case "Era":
					case "ERA":
						r = n[a] + " has a " + era[a] + " run average.";
						break;
				}
			}
			case "Corey Kluber" -> {
				a = 7;
				switch (sI) {
					case "teams":
					case "Teams":
					case "wins":
					case "Wins":
						r = n[a] + " has " + w[a] + " wins.";
						break;
					case "losses":
					case "Losses":
						r = n[a] + " has " + l[a] + " losses.";
						break;
					case "games":
					case "Games":
						r = n[a] + " has played " + g[a] + " games.";
						break;
					case "ip":
					case "IP":
						r = n[a] + " has " + i[a] + " innings pitched.";
						break;
					case "k/9":
					case "K/9":
						r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
						break;
					case "bb/9":
					case "BB/9":
					case "Bb/9":
						r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
						break;
					case "era":
					case "Era":
					case "ERA":
						r = n[a] + " has a " + era[a] + " run average.";
						break;
				}
			}
			case "Gerrit Cole" -> {
				a = 8;
				switch (sI) {
					case "teams":
					case "Teams":
					case "wins":
					case "Wins":
						r = n[a] + " has " + w[a] + " wins.";
						break;
					case "losses":
					case "Losses":
						r = n[a] + " has " + l[a] + " losses.";
						break;
					case "games":
					case "Games":
						r = n[a] + " has played " + g[a] + " games.";
						break;
					case "ip":
					case "IP":
						r = n[a] + " has " + i[a] + " innings pitched.";
						break;
					case "k/9":
					case "K/9":
						r = n[a] + " has " + s[a] + " strikeouts per nine innings.";
						break;
					case "bb/9":
					case "BB/9":
					case "Bb/9":
						r = n[a] + " has " + b[a] + " balls on bases per nine innings.";
						break;
					case "era":
					case "Era":
					case "ERA":
						r = n[a] + " has a " + era[a] + " run average.";
						break;
				}
			}
		}
		return r;
	}
}
