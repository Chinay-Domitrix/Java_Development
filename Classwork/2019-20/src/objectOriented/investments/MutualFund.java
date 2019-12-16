package objectOriented.investments;

import objectOriented.Name;

import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

class MutualFund extends Investment {
	private final InterestRate interestRate;

	MutualFund() {
		this(new Name("Mutual Fund"));
	}

	MutualFund(Name name) {
		super(name);
		interestRate = new InterestRate(((random() * 0b1100) - 0b11) + "%");
	}

	@Override
	final double invest1Year(double amount) {
		var x = amount * interestRate.doubleValue() - 20;
		addToYield(x);
		amount *= doubleValue(interestRate) + 1;
		amount -= 20;
		out.printf("%s returned a yield of %s for a total of %s with the fee included.%n", getName(), format(x), format(amount));
		return amount;
	}
}