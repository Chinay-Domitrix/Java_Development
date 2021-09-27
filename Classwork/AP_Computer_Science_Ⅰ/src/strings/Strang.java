package strings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.System.out;
import static java.util.stream.IntStream.range;

/*   A large Albanian conglomerate wants to use "String" in its coding projects,
     but they cannot afford the licensing fees. Instead they have hired you to
     create a knock-off "Strang" class that behaves like a String.

     However, you **  MAY NOT USE ANY STRING METHODS ***
     the only string method used in the toString() method included

     Do Now Directions:
     - Write a length and substrang method without using String methods
     - Uncomment the method calls in the main method to test


*/
public class Strang implements Comparable {

	private final char[] chars;

	@Contract(pure = true)
	private Strang(char[] chars) {
		this.chars = chars;
	}

	public static void main(String[] args) {
		var c = new char[] { 'T', 'i', 'r', 'a', 'n', 'a', ' ', 'T', 'e', 'c', 'h' };
		var strang1 = new Strang(c);
		// Example: charAt method
		out.println("strang1.charAt(3) = " + strang1.charAt(3));
		// #1 Create a method for length and uncomment below to run
		out.println("stb.length() = " + strang1.length());
		// #2 Create a method for substrang and uncomment to run
		out.printf("strang1.substrang(2) = %s%n", strang1.substrang(2));
	}

	public int length() {
		return chars.length;
	}

	public Strang substrang(int beginIndex) {
		var returned = new char[chars.length - beginIndex];
		range(beginIndex, length()).forEachOrdered(i -> returned[i - beginIndex] = chars[i]);
		return new Strang(returned);
	}

	public char charAt(int index) {
		return chars[index];
	}

	public String toString() {
		return new String(chars);
	}

	/**
	 * This is a place holder to allow the method to compile need to rewrite to
	 * perform actual comparison function just leave it for now, we will return to
	 * it later
	 */
	public int compareTo(@NotNull Object other) {
		return 0; /* Leave untouched for now */
	}
}
