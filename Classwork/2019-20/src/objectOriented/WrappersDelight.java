package objectOriented;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.lang.Math.*;

final class WrappersDelight {
	/**
	 * The constructor is where the output is printed for this program.
	 */
	private WrappersDelight() {
//		The variable type Object is used to permit the usage of all data types, and the toString is used in order to be able to generate a random number.
		for (var i : wrapSampler(Double.toString((random() * 201) - 100))) System.out.println(i);
	}

	public static void main(String[] args) {
		new WrappersDelight();
	}

	/**
	 * The primary logic of the program. Converts the provided string into an int, double, boolean, and a char.
	 *
	 * @param s the String to be converted
	 */
	@NotNull
	private static ArrayList<Object> wrapSampler(String s) {
//		This is a utilization of ArrayList's default constructor. Since it is a constructor, it is an instance method.
		ArrayList<Object> arrayList = new ArrayList<>();
//		The method Double.parseDouble(String s) is a static method which extracts the double value of a number from a String.
		var a = Double.parseDouble(s);
		var b = toIntExact(round(a));
//		The add(Object e) method from ArrayList is an instance method which inserts the parameter Object at the end of the ArrayList.
		arrayList.add(a);
		arrayList.add(b);
		if (a > 0) arrayList.add(true);
		else if (a < 0) arrayList.add(false);
//		This constructor is just to throw an exception in case a zero is passed to this portion of the code.
		else throw new IllegalStateException("Unexpected value: " + 0);
		if (b <= 0) arrayList.add((char) 63);
		else arrayList.add((char) ((b % 26) + 64));
		return arrayList;
	}
}
