package objectOriented.investments;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static java.math.RoundingMode.HALF_UP;

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

	@NotNull
	@Contract(pure = true)
	@Override
	public String toString() {
		return format("%s%%", (Double.toString(interestRate).length() <= 3) ? interestRate : new BigDecimal(interestRate * 100).setScale(2, HALF_UP));
	}
}