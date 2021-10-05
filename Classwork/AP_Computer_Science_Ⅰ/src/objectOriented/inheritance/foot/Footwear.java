package objectOriented.inheritance.foot;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;

import static java.lang.String.format;

/**
 * Footwear is a lightweight abstract class intended to illustrate Javadoc and
 * abstract classes for educational purposes
 *
 * @author Chris Haver
 * @author Chirag Baviskar
 * @version 1.0
 */
abstract class Footwear {
	private final String name;
	private boolean leftOn, rightOn;

	/**
	 * Default constructor with {@code leftOn} and {@code rightOn} defaulting to
	 * false
	 *
	 * @param name identifier for specific footwear
	 */
	@Contract(pure = true)
	Footwear(String name) {
		this(name, false, false);
	}

	/**
	 * Constructor creates a specific footwear item
	 *
	 * @param name    identifier for specific footwear
	 * @param leftOn  indicates left footwear item currently on foot
	 * @param rightOn indicates right footwear item currently on foot
	 */
	@Contract(pure = true)
	Footwear(String name, boolean leftOn, boolean rightOn) {
		this.name = name;
		this.leftOn = leftOn;
		this.leftOn = rightOn;
	}

	/**
	 * putOn puts on the appropriate footwear (left or right) depending on String
	 * parameter
	 *
	 * @param foot a String that is either "left" or "right" indicated the boolean
	 *             set to on
	 * @return boolean status of success or failure of operation (for example, it
	 * might fail if neither left or right are understood)
	 */
	abstract public boolean putOn(String foot);

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * <p>
	 * The {@code equals} method implements an equivalence relation on non-null
	 * object references:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value {@code x},
	 * {@code x.equals(x)} should return {@code true}.
	 * <li>It is <i>symmetric</i>: for any non-null reference values {@code x} and
	 * {@code y}, {@code x.equals(y)} should return {@code true} if and only if
	 * {@code y.equals(x)} returns {@code true}.
	 * <li>It is <i>transitive</i>: for any non-null reference values {@code x},
	 * {@code y}, and {@code z}, if {@code x.equals(y)} returns {@code true} and
	 * {@code y.equals(z)} returns {@code true}, then {@code x.equals(z)} should
	 * return {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values {@code x} and
	 * {@code y}, multiple invocations of {@code x.equals(y)} consistently return
	 * {@code true} or consistently return {@code false}, provided no information
	 * used in {@code equals} comparisons on the objects is modified.
	 * <li>For any non-null reference value {@code x}, {@code x.equals(null)} should
	 * return {@code false}.
	 * </ul>
	 * <p>
	 * The {@code equals} method for class {@code Object} implements the most
	 * discriminating possible equivalence relation on objects; that is, for any
	 * non-null reference values {@code x} and {@code y}, this method returns
	 * {@code true} if and only if {@code x} and {@code y} refer to the same object
	 * ({@code x == y} has the value {@code true}).
	 * <p>
	 * Note that it is generally necessary to override the {@code hashCode} method
	 * whenever this method is overridden, so as to maintain the general contract
	 * for the {@code hashCode} method, which states that equal objects must have
	 * equal hash codes.
	 *
	 * @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj argument;
	 * {@code false} otherwise.
	 * @see #hashCode()
	 * @see HashMap
	 */
	@Contract(value = "null -> false", pure = true)
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * toString overrides the generic method to print in specific format for
	 * Footwear
	 *
	 * @return String representation of Footwear
	 */
	public String toString() {
		return format("%s, %s, %s", name, rightOn ? "right is on" : "right is off",
				leftOn ? "left is on" : "left is off");
	}

	/**
	 * getName returns name of Footwear
	 *
	 * @return the {@code name} of the {@code Footwear} object
	 */
	public String getName() {
		return name;
	}

	/**
	 * getLeftOn returns status if left Footwear is on foot
	 *
	 * @return the status of {@code leftOn}
	 */
	public boolean getLeftOn() {
		return leftOn;
	}

	/**
	 * setLeftOn sets the status if left Footwear is on foot
	 *
	 * @param leftOn the new status of {@code leftOn}
	 */
	@SuppressWarnings("SameParameterValue")
	void setLeftOn(boolean leftOn) {
		this.leftOn = leftOn;
	}

	/**
	 * getRightOn returns status if left Footwear is on foot
	 *
	 * @return the status of {@code rightOn}
	 */
	public boolean getRightOn() {
		return rightOn;
	}

	/**
	 * setRightOn sets the status if right Footwear is on foot
	 *
	 * @param rightOn the new status of {@code rightOn}
	 */
	@SuppressWarnings("SameParameterValue")
	void setRightOn(boolean rightOn) {
		this.rightOn = rightOn;
	}
}
