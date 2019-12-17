package objectOriented.investments;

import objectOriented.Name;

import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class MutualFund extends Investment {

	MutualFund() {
		this(new Name("Mutual Fund"));
	}

	MutualFund(Name name) {
		super(name);
	}

	@Override
	double invest1Year(double amount) {
		var interestRate = new InterestRate(((random() * 11) - 3) + "%");
		var yield = amount * interestRate.doubleValue() - 20;
		addToYield(yield);
		amount *= (doubleValue(interestRate) + 1);
		amount -= 20;
		out.printf("%s returned a yield of %s for a total of %s with the fee included.%n", getName(), format(yield), format(amount));
		return amount;
	}
}