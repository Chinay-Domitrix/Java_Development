package remoteLearning.march12;

/**
 * The following Comparable class represents an ordered triple for x, y, z
 * coordinates on a 3D coordinate plane.
 * <p>
 * <ol>
 * <li>Write a method called distanceToOrigin that takes no explicit parameters
 * and returns a double representing the distance to the origin for that point
 * based on class fields x, y, z</li>
 * <li>Write a compareTo that compares two 3D points based on distance to
 * origin</li>
 * </ol>
 * <p>
 * Not sure how to find distance from origin? Try this link:
 * https://www.varsitytutors.com/hotmath/hotmath_help/topics/distance-formula-in-3d
 */
public class Point3D implements Comparable<Point3D> {
	private final double x;
	private final double y;
	private final double z;

	private Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void main(String[] args) { // Unit Test: Uncomment and run various scenarios to test
		var a = new Point3D(1, 0.5, 1);
		var b = new Point3D(1, 0.5, 1);
		System.out.printf("a = %s, a.distanceToOrigin() = %s%n", a, a.distanceToOrigin());
		System.out.printf("b = %s, b.distanceToOrigin() = %s%n", b, b.distanceToOrigin());
		System.out.printf("a.compareTo(b) = %d%n", a.compareTo(b));
	}

	private double distanceToOrigin() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}

	public String toString() {
		return "(" + x + ',' + y + ',' + z + ')';
	}

	@Override
	public int compareTo(Point3D other) {
		return Double.compare(this.distanceToOrigin(), other.distanceToOrigin());
	}
}
