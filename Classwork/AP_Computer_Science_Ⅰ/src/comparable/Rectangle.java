package comparable;

import org.jetbrains.annotations.NotNull;

import static java.lang.Double.compare;

public class Rectangle implements Comparable<Rectangle> {
	private double length, width;

	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	public static void main(String[] args) {

	}

	/**
	 * Compares rectangles by area. Returns:
	 * <ul>
	 * <li>-1 if this has less area then {@code other}</li>
	 * <li>0 if both have the same area</li>
	 * <li>1 if this has more area than {@code other}</li>
	 * </ul>
	 */
	@Override
	public int compareTo(@NotNull Rectangle other) {
		return compare(length * width, other.length * other.width);
	}
}
