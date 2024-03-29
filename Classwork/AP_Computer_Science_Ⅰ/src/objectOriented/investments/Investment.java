package objectOriented.investments;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import static java.lang.String.valueOf;
import static java.math.RoundingMode.HALF_UP;

abstract class Investment {
	private double totalYield;
	private Name name;

	/**
	 * Constructor sets {@link #name} to given value and {@link #totalYield} to zero
	 *
	 * @param name an identifier for investment such as "Money Market Account"
	 */
	Investment(@NotNull Name name) {
		this.name = new Name(name.toString());
		totalYield = 0;
	}

	/**
	 * @param amount to be rounded to nearest cent/hundredth
	 * @return {@code amount} after rounding to monetary notation
	 */
	private static double toDollarNotation(double amount) {
		return new BigDecimal(valueOf(amount)).setScale(2, HALF_UP).doubleValue();
	}

	/**
	 * @param amount the value to be formatted as a value in dollars and cents
	 * @return {@code amount} after reformatting
	 */
	@NotNull
	static String format(double amount) {
		return String.format("$%s", String.format("%.2f", toDollarNotation(amount)));
	}

	abstract double invest1Year(double amount);

	/**
	 * @param amount the value to be added to {@link #totalYield}
	 */
	void addToYield(double amount) {
		totalYield += amount;
	}

	/**
	 * @return the total value of profit/loss generated by a particular
	 * {@link Investment} object
	 */
	double getTotalYield() {
		return totalYield;
	}

	/**
	 * @return identifier for investment such as "Money Market Account"
	 */
	Name getName() {
		return name;
	}

	/**
	 * @param name an identifier for an investment
	 */
	@SuppressWarnings("unused")
	final void setName(String name) {
		this.name = new Name(name);
	}

	/**
	 * @return {@code String} representing name and {@link #totalYield} amount
	 */
	@Override
	public final String toString() {
		return String.format("%s totalYield = %s", getName(), format(getTotalYield()));
	}
}
