package objectOriented.investments;

import org.jetbrains.annotations.NotNull;

import static java.lang.Double.parseDouble;

class InterestRate {
	private final double interestRate;

	InterestRate(String interestRate) {
		this.interestRate = parseDouble(new StringBuilder(interestRate).deleteCharAt(interestRate.indexOf("%")).toString()) / 100;
	}

	static double doubleValue(@NotNull InterestRate a) {
		return a.doubleValue();
	}

	static double doubleValue(double a) {
		return a;
	}

	final double doubleValue() {
		return interestRate;
	}

	@Override
	public String toString() {
		return Double.toString(interestRate);
	}
}