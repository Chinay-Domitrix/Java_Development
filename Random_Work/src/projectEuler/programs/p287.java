package projectEuler.programs;

import java.math.BigInteger;

import static java.lang.Math.*;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;

public final class p287 extends EulerSolution {
	private static final int N = 24;

	/*
	 * Let R = 2^(N-1) denote the radius of the circle (filled disk) being drawn.
	 *
	 * First, we can simplify the problem by translating (shifting) the coordinate system.
	 * Instead of x and y each in [0, 2^N) for the formula [x - 2^(N-1)]^2 + [y - 2^(N-1)]^2 <= R^2,
	 * we shall consider x and y each in [-(2^(N-1)), 2^(N-1)) for the formula x^2 + y^2 <= R^2.
	 *
	 * Suppose we are given a square 2D region with endpoints [xStart, xEnd) and [yStart, yEnd).
	 * If the region is entirely white or entirely black, then it takes 2 bits to encode the region.
	 * Otherwise the region must have both white and black pixels, so we use 1 bit
	 * to encode the split, recurse on the 4 sub-squares, and sum their code lengths.
	 *
	 * Within the region, what are the possible values of the left side of the formula, x^2 + y^2?
	 * To minimize or maximize x^2 + y^2, we can min/maximize each of x^2 and y^2 independently.
	 * - To minimize x^2, we minimize |x|. If 0 is in [xStart, xEnd),
	 * then the minimum |x| is 0, and thus the minimum x^2 is 0.
	 * Otherwise, either all possible x values are negative or all
	 * are positive, so the minimum |x| is min(|xStart|, |xEnd-1|).
	 * - To maximize x^2, we maximize |x|. This simply equals max(|xStart|, |xEnd-1|).
	 * - The same arguments apply to minimizing/maximizing y^2.
	 *
	 * Now evaluate minR^2 = minX^2 + minY^2, and maxR^2 = maxX^2 + maxY^2.
	 * - If maxR^2 <= R^2, then all points in the region satisfy
	 * x^2 + y^2 <= R^2, hence the entire region is black.
	 * - Similarly, if minR^2 > R^2, then all points in the region
	 * satisfy x^2 + y^2 > R^2, hence the entire region is white.
	 * - Otherwise, the region must contain both black and white points,
	 * so we split into 4 sub-regions and recurse.
	 */
	private static final long RADIUS_SQUARED = 70368744177664L;

	public static void main(String[] args) {
		System.out.println(new p287().run());
	}

	String run() {
		int temp = 8388608;
		return compressedLength(N, -temp, temp, -temp, temp).toString();
	}

	// Returns the exact minimum number of bits required to encode
	// the circle image's region of [xStart, end) * [yStart, yEnd).
	private BigInteger compressedLength(int n, int xStart, int xEnd, int yStart, int yEnd) {
		assert n >= 0;
		assert (xStart < xEnd) && ((xEnd - xStart) == (1 << n));
		assert (yStart < yEnd) && ((yEnd - yStart) == (1 << n));
		int minAbsX = ((xStart <= 0) && (0 < xEnd)) ? 0 : min(abs(xStart), abs(xEnd - 1));
		int minAbsY = ((yStart <= 0) && (0 < yEnd)) ? 0 : min(abs(yStart), abs(yEnd - 1));
		int maxAbsX = max(abs(xStart), abs(xEnd - 1));
		int maxAbsY = max(abs(yStart), abs(yEnd - 1));
		long minRadius = (long) minAbsX * minAbsX + (long) minAbsY * minAbsY;
		long maxRadius = (long) maxAbsX * maxAbsX + (long) maxAbsY * maxAbsY;
		// All black or all white, respectively
		if (maxRadius <= RADIUS_SQUARED || minRadius > RADIUS_SQUARED) return valueOf(2);
		else {
			int xMid = (xStart + xEnd) >> 1;
			int yMid = (yStart + yEnd) >> 1;
			// The order goes top left, top right, bottom left, and finally bottom right
			return ONE.add(compressedLength(n - 1, xStart, xMid, yMid, yEnd)).add(compressedLength(n - 1, xMid, xEnd, yMid, yEnd)).add(compressedLength(n - 1, xStart, xMid, yStart, yMid)).add(compressedLength(n - 1, xMid, xEnd, yStart, yMid));
		}
	}
}