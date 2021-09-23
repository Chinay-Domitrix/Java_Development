package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.coords;

import org.jetbrains.annotations.Contract;

import static java.lang.Math.*;
import static java.lang.String.format;

class OrderedPair {
	private static double x;
	private static double y;
	private static int counter = 0;

	OrderedPair() {
		this(0, 0);
	}

	OrderedPair(double x, double y) {
		OrderedPair.x = x;
		OrderedPair.y = y;
		counter++;
	}

	static double distanceFromOrigin() {
		return sqrt(pow(x, 2) + pow(y, 2));
	}

	@Contract(pure = true)
	static int getCounter() {
		return counter;
	}

	public String toString() {
		return format("Your location is (%s, %s).", x, y);
	}

	double getX() {
		return x;
	}

	void setX(double x) {
		OrderedPair.x = x;
	}

	int getXInt() {
		return toIntExact(round(x));
	}

	double getY() {
		return y;
	}

	void setY(double y) {
		OrderedPair.y = y;
	}

	int getYInt() {
		return toIntExact(round(y));
	}
}
