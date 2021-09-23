package Classwork.AP_Computer_Science_Ⅰ.src.randomWork;

import static java.lang.Integer.toBinaryString;
import static java.lang.Math.abs;
import static java.lang.String.format;
import static java.lang.System.out;

/**
 * This class illustrates the concept of Overflow with the int primitive.
 * <p>
 * Remember, an int like any other primitive represents a fixed number of binary digits
 * to hold a value up to Integer.MAX_VALUE=2147483647.
 * <p>
 * Once it goes over that, some of the binary digits are displaced and you will get
 * unexpected behavior, like the int suddenly registering as negative.
 * <p>
 * For each iteration this will print the binary representation along with the integer.
 */
public class Overflow {
	public static void main(String[] args) {
		var i = 4;
		int lastI;
		var overflow = false;
		for (int x = 0; x < 24; x++) {
			lastI = i;
			i *= 3;
			if ((lastI > 0) && (i <= 0) && !overflow) {
				out.println("**** OVERFLOW => Exceeded 2147483647 ****\n");
				out.println("SEE INTS AFTER OVERFLOW BELOW:");
				overflow = true;
			}
			out.printf("[%d] ", i);
			if (abs(i) < 10000) out.print('\t'); // For formatting tabs
			out.print("\tBin=>");
			out.printf("%s Bin Digits [%d]%n", format("%32s", toBinaryString(i)).replace(" ", "0"), toBinaryString(i).length());
		}
	}
}
