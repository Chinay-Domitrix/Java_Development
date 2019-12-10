package objectOriented.inheritance.foot;

/**
 * Footwear is a lightweight abstract class intended to illustrate
 * Javadoc and abstract classes for educational purposes
 *
 * @author Chris Haver
 * @version 1.0
 */
abstract public class Footwear {
	private String name;
	private boolean leftOn, rightOn;

	/**
	 * Constructor creates a specific footwear item with a default of not on
	 *
	 * @param name    identifier for specific footwear
	 * @param leftOn  indicates left footwear item currently on foot
	 * @param rightOn indicates right footwear item currently on foot
	 */
	public Footwear(String name, boolean leftOn, boolean rightOn) {
		this.name = name;
		this.leftOn = leftOn;
		this.leftOn = rightOn;
	}

	/**
	 * putOn puts on the appropriate footwear (left or right) depending on String parameter
	 *
	 * @param foot a String that is either "left" or "right" indicated the boolean set to on
	 * @return boolean status of success or failure of operation (for example, it might fail if neither left or right are understood)
	 */
	abstract public boolean putOn(String foot);

	/**
	 * toString overrides the generic method to print in specific format for Footwear
	 *
	 * @return String representation of Footwear Object
	 */
	public String toString() {
		return name + ", " + (rightOn ? "right is on" : "right is off") + ", " + (leftOn ? "left is on" : "left is off");
	}

	/**
	 * getName returns name of Footwear
	 *
	 * @return the {@code name}
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
	 * @param leftOn status of left footwear on
	 */
	public void setLeftOn(boolean leftOn) {
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
	 * @param rightOn status of right footwear on
	 */
	public void setRightOn(boolean rightOn) {
		this.rightOn = rightOn;
	}
}