package objectOriented.inheritance.foot;

import java.util.HashMap;

/**
 * Footwear is a lightweight abstract class intended to illustrate
 * Javadoc and abstract classes for educational purposes
 *
 * @author Chris Haver
 * @author Chirag Baviskar
 * @version 1.0
 */
abstract class Footwear {
	private String name;
	private boolean leftOn, rightOn;

	/**
	 * Default constructor with {@code leftOn} and {@code rightOn} defaulting to false
	 *
	 * @param name identifier for specific footwear
	 */
	public Footwear(String name) {
		this(name, false, false);
	}

	/**
	 * Constructor creates a specific footwear item
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
	 * Returns a hash code value for the object. This method is
	 * supported for the benefit of hash tables such as those provided by
	 * {@link HashMap}.
	 * <p>
	 * The general contract of {@code hashCode} is:
	 * <ul>
	 * <li>Whenever it is invoked on the same object more than once during
	 *     an execution of a Java application, the {@code hashCode} method
	 *     must consistently return the same integer, provided no information
	 *     used in {@code equals} comparisons on the object is modified.
	 *     This integer need not remain consistent from one execution of an
	 *     application to another execution of the same application.
	 * <li>If two objects are equal according to the {@code equals(Object)}
	 *     method, then calling the {@code hashCode} method on each of
	 *     the two objects must produce the same integer result.
	 * <li>It is <em>not</em> required that if two objects are unequal
	 *     according to the {@link Object#equals(Object)}
	 *     method, then calling the {@code hashCode} method on each of the
	 *     two objects must produce distinct integer results.  However, the
	 *     programmer should be aware that producing distinct integer results
	 *     for unequal objects may improve the performance of hash tables.
	 * </ul>
	 *
	 * @return a hash code value for this object.
	 * @implSpec As far as is reasonably practical, the {@code hashCode} method defined
	 * by class {@code Object} returns distinct integers for distinct objects.
	 * @see Object#equals(Object)
	 * @see System#identityHashCode
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * <p>
	 * The {@code equals} method implements an equivalence relation
	 * on non-null object references:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value
	 *     {@code x}, {@code x.equals(x)} should return
	 *     {@code true}.
	 * <li>It is <i>symmetric</i>: for any non-null reference values
	 *     {@code x} and {@code y}, {@code x.equals(y)}
	 *     should return {@code true} if and only if
	 *     {@code y.equals(x)} returns {@code true}.
	 * <li>It is <i>transitive</i>: for any non-null reference values
	 *     {@code x}, {@code y}, and {@code z}, if
	 *     {@code x.equals(y)} returns {@code true} and
	 *     {@code y.equals(z)} returns {@code true}, then
	 *     {@code x.equals(z)} should return {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values
	 *     {@code x} and {@code y}, multiple invocations of
	 *     {@code x.equals(y)} consistently return {@code true}
	 *     or consistently return {@code false}, provided no
	 *     information used in {@code equals} comparisons on the
	 *     objects is modified.
	 * <li>For any non-null reference value {@code x},
	 *     {@code x.equals(null)} should return {@code false}.
	 * </ul>
	 * <p>
	 * The {@code equals} method for class {@code Object} implements
	 * the most discriminating possible equivalence relation on objects;
	 * that is, for any non-null reference values {@code x} and
	 * {@code y}, this method returns {@code true} if and only
	 * if {@code x} and {@code y} refer to the same object
	 * ({@code x == y} has the value {@code true}).
	 * <p>
	 * Note that it is generally necessary to override the {@code hashCode}
	 * method whenever this method is overridden, so as to maintain the
	 * general contract for the {@code hashCode} method, which states
	 * that equal objects must have equal hash codes.
	 *
	 * @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj
	 * argument; {@code false} otherwise.
	 * @see #hashCode()
	 * @see HashMap
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * Creates and returns a copy of this object.  The precise meaning
	 * of "copy" may depend on the class of the object. The general
	 * intent is that, for any object {@code x}, the expression:
	 * <blockquote>
	 * <pre>
	 * x.clone() != x</pre></blockquote>
	 * will be true, and that the expression:
	 * <blockquote>
	 * <pre>
	 * x.clone().getClass() == x.getClass()</pre></blockquote>
	 * will be {@code true}, but these are not absolute requirements.
	 * While it is typically the case that:
	 * <blockquote>
	 * <pre>
	 * x.clone().equals(x)</pre></blockquote>
	 * will be {@code true}, this is not an absolute requirement.
	 * <p>
	 * By convention, the returned object should be obtained by calling
	 * {@code super.clone}.  If a class and all of its superclasses (except
	 * {@code Object}) obey this convention, it will be the case that
	 * {@code x.clone().getClass() == x.getClass()}.
	 * <p>
	 * By convention, the object returned by this method should be independent
	 * of this object (which is being cloned).  To achieve this independence,
	 * it may be necessary to modify one or more fields of the object returned
	 * by {@code super.clone} before returning it.  Typically, this means
	 * copying any mutable objects that comprise the internal "deep structure"
	 * of the object being cloned and replacing the references to these
	 * objects with references to the copies.  If a class contains only
	 * primitive fields or references to immutable objects, then it is usually
	 * the case that no fields in the object returned by {@code super.clone}
	 * need to be modified.
	 * <p>
	 * The method {@code clone} for class {@code Object} performs a
	 * specific cloning operation. First, if the class of this object does
	 * not implement the interface {@code Cloneable}, then a
	 * {@code CloneNotSupportedException} is thrown. Note that all arrays
	 * are considered to implement the interface {@code Cloneable} and that
	 * the return type of the {@code clone} method of an array type {@code T[]}
	 * is {@code T[]} where T is any reference or primitive type.
	 * Otherwise, this method creates a new instance of the class of this
	 * object and initializes all its fields with exactly the contents of
	 * the corresponding fields of this object, as if by assignment; the
	 * contents of the fields are not themselves cloned. Thus, this method
	 * performs a "shallow copy" of this object, not a "deep copy" operation.
	 * <p>
	 * The class {@code Object} does not itself implement the interface
	 * {@code Cloneable}, so calling the {@code clone} method on an object
	 * whose class is {@code Object} will result in throwing an
	 * exception at run time.
	 *
	 * @return a clone of this instance.
	 * @throws CloneNotSupportedException if the object's class does not
	 *                                    support the {@code Cloneable} interface. Subclasses
	 *                                    that override the {@code clone} method can also
	 *                                    throw this exception to indicate that an instance cannot
	 *                                    be cloned.
	 * @see Cloneable
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * toString overrides the generic method to print in specific format for Footwear
	 *
	 * @return String representation of Footwear
	 */
	public String toString() {
		return name + ", " + (rightOn ? "right is on" : "right is off") + ", " + (leftOn ? "left is on" : "left is off");
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
	 * @param rightOn the new status of {@code rightOn}
	 */
	public void setRightOn(boolean rightOn) {
		this.rightOn = rightOn;
	}
}