package objectOriented.investments;

import org.jetbrains.annotations.NotNull;

import static java.lang.Double.parseDouble;

public final class InterestRate {
	public double interestRate;

	public InterestRate(String interestRate) {
		this.interestRate = parseDouble(new StringBuilder(interestRate).deleteCharAt(interestRate.indexOf("%")).toString());
	}

	public static double doubleValue(@NotNull InterestRate a) {
		return a.doubleValue();
	}

	public static double doubleValue(double a) {
		return a;
	}

	public final double doubleValue() {
		return interestRate;
	}

	@Override
	public String toString() {
		return Double.toString(interestRate);
	}
}