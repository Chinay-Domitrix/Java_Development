package objectOriented.investments;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.Double.parseDouble;

final class InterestRate {
	private double interestRate;

	InterestRate(String interestRate) {
		this.interestRate = parseDouble(new StringBuilder(interestRate).deleteCharAt(interestRate.indexOf("%")).toString()) / 100;
	}

	@Contract(pure = true)
	static double doubleValue(@NotNull InterestRate a) {
		return a.doubleValue();
	}

	@Contract(pure = true)
	double doubleValue() {
		return interestRate;
	}

	@Override
	public String toString() {
		return Double.toString(interestRate);
	}
}