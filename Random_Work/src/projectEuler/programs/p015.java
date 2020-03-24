package projectEuler.programs;

import static projectEuler.programs.Library.binomial;

public final class p015 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p015().run());
	}

	/*
	 * This is a classic combinatorics problem. To get from the top left corner to the bottom right corner of an N*N grid,
	 * it involves making exactly N moves right and N moves down in some order. Because each individual down or right move
	 * is indistinguishable, there are exactly 2N choose N (binomial coefficient) ways of arranging these moves.
	 */
	String run() {
		return binomial(40, 20).toString();
	}
}