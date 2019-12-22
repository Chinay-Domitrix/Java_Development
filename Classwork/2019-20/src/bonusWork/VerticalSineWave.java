package bonusWork;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.Objects.requireNonNull;

class VerticalSineWave {
	private static String startingSide;

	private VerticalSineWave() {
		out.println("This is a program that \"infinitely\" prints out a vertical sine wave.");
		out.print("Do you want to start on the left or right? I want to start on the ");
		startingSide = new Scanner(System.in).nextLine() + '.';
	}

	private VerticalSineWave(String startingSide) throws IllegalStateException {
		VerticalSineWave.startingSide = startingSide;
		if (condition(startingSide, "right.")) toLeft(4);
		else if (condition(startingSide, "left.")) toRight(0);
		else throw new IllegalStateException(format("Unexpected value: %s", startingSide));
	}

	private VerticalSineWave(int min) {
		var startingSide = VerticalSineWave.startingSide;
		for (int a = min; a < Integer.MAX_VALUE; a++)
			for (int b = min; b < Integer.MAX_VALUE; b++)
				for (int c = min; c < Integer.MAX_VALUE; c++)
					for (int d = min; d < Integer.MAX_VALUE; d++)
						for (int e = min; e < Integer.MAX_VALUE; e++)
							for (int f = min; f < Integer.MAX_VALUE; f++)
								for (int g = min; g < Integer.MAX_VALUE; g++)
									for (int h = min; h < Integer.MAX_VALUE; h++)
										for (int i = min; i < Integer.MAX_VALUE; i++)
											for (int j = min; j < Integer.MAX_VALUE; j++)
												for (int k = min; k < Integer.MAX_VALUE; k++)
													for (int l = min; l < Integer.MAX_VALUE; l++)
														for (int m = min; m < Integer.MAX_VALUE; m++)
															for (int n = min; n < Integer.MAX_VALUE; n++)
																for (int o = min; o < Integer.MAX_VALUE; o++)
																	for (int p = min; p < Integer.MAX_VALUE; p++)
																		for (int q = min; q < Integer.MAX_VALUE; q++)
																			for (int r = min; r < Integer.MAX_VALUE; r++)
																				for (int s = min; s < Integer.MAX_VALUE; s++)
																					for (int t = min; t < Integer.MAX_VALUE; t++)
																						for (int u = min; u < Integer.MAX_VALUE; u++)
																							for (int v = min; v < Integer.MAX_VALUE; v++)
																								for (int w = min; w < Integer.MAX_VALUE; w++)
																									for (int x = min; x < Integer.MAX_VALUE; x++)
																										for (int y = min; y < Integer.MAX_VALUE; y++)
																											for (int z = min; z < Integer.MAX_VALUE; z++)
																												new VerticalSineWave(startingSide);
	}

	public static void main(String[] args) {
		new VerticalSineWave();
		for (int a = MIN_VALUE; a < MAX_VALUE; a++)
			for (int b = MIN_VALUE; b < MAX_VALUE; b++)
				for (int c = MIN_VALUE; c < MAX_VALUE; c++)
					for (int d = MIN_VALUE; d < MAX_VALUE; d++)
						for (int e = MIN_VALUE; e < MAX_VALUE; e++)
							for (int f = MIN_VALUE; f < MAX_VALUE; f++)
								for (int g = MIN_VALUE; g < MAX_VALUE; g++)
									for (int h = MIN_VALUE; h < MAX_VALUE; h++)
										for (int i = MIN_VALUE; i < MAX_VALUE; i++)
											for (int j = MIN_VALUE; j < MAX_VALUE; j++)
												for (int k = MIN_VALUE; k < MAX_VALUE; k++)
													for (int l = MIN_VALUE; l < MAX_VALUE; l++)
														for (int m = MIN_VALUE; m < MAX_VALUE; m++)
															for (int n = MIN_VALUE; n < MAX_VALUE; n++)
																for (int o = MIN_VALUE; o < MAX_VALUE; o++)
																	for (int p = MIN_VALUE; p < MAX_VALUE; p++)
																		for (int q = MIN_VALUE; q < MAX_VALUE; q++)
																			for (int r = MIN_VALUE; r < MAX_VALUE; r++)
																				for (int s = MIN_VALUE; s < MAX_VALUE; s++)
																					for (int t = MIN_VALUE; t < MAX_VALUE; t++)
																						for (int u = MIN_VALUE; u < MAX_VALUE; u++)
																							for (int v = MIN_VALUE; v < MAX_VALUE; v++)
																								for (int w = MIN_VALUE; w < MAX_VALUE; w++)
																									for (int x = MIN_VALUE; x < MAX_VALUE; x++)
																										for (int y = MIN_VALUE; y < MAX_VALUE; y++)
																											for (int z = MIN_VALUE; z < MAX_VALUE; z++)
																												new VerticalSineWave(MIN_VALUE);
	}

	private void toLeft(int i) {
		try {
			if (i == 0) {
				out.println("\t\t\t\t\t" + asterisks(i));
				toRight(i);
				return;
			}
			out.println("\t\t\t\t\t" + asterisks(i));
			toLeft(i - 1);
		} catch (StackOverflowError e) {
			new StackOverflowError().addSuppressed(e);
		}
	}

	private void toRight(int i) {
		try {
			if (i == 5) {
				toLeft(i - 1);
				return;
			}
			out.println("\t\t\t\t\t" + asterisks(i));
			toRight(i + 1);
		} catch (StackOverflowError e) {
			new StackOverflowError().addSuppressed(e);
		}
	}

	@NotNull
	@Contract(pure = true)
	private String asterisks(int i) {
		return (i == 0) ? "*" : (" " + asterisks(i - 1));
	}

	@Contract(value = "_, null -> false", pure = true)
	private boolean condition(@NotNull String checked, String condition) {
		return requireNonNull(checked).equalsIgnoreCase(condition);
	}
}