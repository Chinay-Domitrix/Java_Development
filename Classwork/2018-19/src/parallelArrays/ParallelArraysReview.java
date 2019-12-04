package parallelArrays;

import static java.lang.System.out;

public class ParallelArraysReview {
	private static final String[] name = {"Kevin Durant", "LeBron James", "Magic Johnson", "Michael Jordan",
			"Stephen Curry"};
	private static final int[] games = {843, 1198, 906, 1072, 688}, points = {22841, 32543, 17707, 32292, 16194},
			assists = {3442, 8662, 10141, 5633, 4558}, rebounds = {5972, 8880, 6559, 6672, 3101},
			steals = {945, 1937, 1724, 2514, 1193}, blocks = {937, 921, 347, 893, 151};

	public static void main(final String[] args) throws Throwable {
		println("Name\t\tPPG\tAPG\tRPG\tSPG\tBPG");
		Thread.sleep(1000);
//		This prints the player statistics.
		for (int i = 0; i < name.length; i++) {
			println(name[i] + "\t" + (int) Math.round(points[i] / (double) games[i] * 10) / 10.0 + "\t"
					+ (int) Math.round(assists[i] / (double) games[i] * 10) / 10.0 + "\t"
					+ (int) Math.round(rebounds[i] / (double) games[i] * 10) / 10.0 + "\t"
					+ (int) Math.round(steals[i] / (double) games[i] * 10) / 10.0 + "\t"
					+ (int) Math.round(blocks[i] / (double) games[i] * 10) / 10.0);
			Thread.sleep(1000);
		}
	}

	private static void println(final String a) {
		out.println(a);
	}
}