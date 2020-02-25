package parallelArrays;

import java.util.Scanner;

import static java.lang.System.out;

class Day1Practice2 {
	private static final double[] att = new double[5];
	private static final double[] poss = new double[5];
	private static final String[] id = new String[5];
	private static final String[] f = new String[5];
	private static final char[] l = new char[5];
	private static final Scanner in = new Scanner(System.in);

	public static void main(final String[] args) {
		for (int i = 0; i < 6; i++) {
			out.print("Please enter the student's first name. ");
			f[i] = in.nextLine();
			out.print("Please enter the student's last initial. ");
			l[i] = in.nextLine().charAt(0);
			out.print("Please enter the student's ID number. ");
			id[i] = in.nextLine();
			in.nextLine();
			out.print("Please enter the student's attempted points. ");
			att[i] = in.nextInt();
			in.nextLine();
			out.print("Please enter the possible points. ");
			poss[i] = in.nextInt();
			in.nextLine();
			out.println();
		}
		out.println("First\tLast\tID\t\tAtt.\tPoss.\tAvg.");
		for (int i = 0; i < 6; i++)
			out.println(f[i] + "\t" + l[i] + "\t" + id[i] + "\t" + round(att[i]) + "\t" + round(poss[i]) + "\t"
					+ round(100 * (att[i] / poss[i])) + "%");
		in.close();
	}

	private static long round(final double a) {
		return Math.round(a);
	}
}