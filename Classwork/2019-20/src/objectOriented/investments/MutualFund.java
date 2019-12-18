package objectOriented.investments;

import objectOriented.Name;

import static java.lang.Double.MIN_VALUE;
import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class MutualFund extends Investment {
	private double minimumInterest = 100;
	private double maximumInterest = 100;
	private final double fee;

	MutualFund() {
		this(new Name("Mutual Fund"));
	}

	MutualFund(Name name) {
		super(name);
		minimumInterest = -3;
		maximumInterest = 8;
		fee = 20;
	}

	MutualFund(String name, double minimumInterest, double maximumInterest, double fee) {
		super(new Name(name));
		this.minimumInterest *= minimumInterest;
		this.maximumInterest *= maximumInterest;
		this.fee = fee;
	}


	@Override
	double invest1Year(double amount) {
		var interestRate = new InterestRate(((random() * ((maximumInterest - minimumInterest) + MIN_VALUE)) + (minimumInterest)) + "%");
		var yield = (amount * interestRate.doubleValue()) - fee;
		addToYield(yield);
		amount *= (doubleValue(interestRate) + 1);
		amount -= 20;
		out.printf("%s returned a yield of %s for a total of %s with the fee included.%n", getName(), format(yield), format(amount));
		return amount;
	}
}