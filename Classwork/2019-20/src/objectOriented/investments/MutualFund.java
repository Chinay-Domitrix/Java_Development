package objectOriented.investments;

import objectOriented.Name;

import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

public class MutualFund extends Investment {
	private InterestRate interestRate;

	public MutualFund() {
		this(new Name("Mutual Fund"));
	}

	public MutualFund(Name name) {
		super(name);
		interestRate = new InterestRate(((random() * 12) - 3) + "%");
	}

	@Override
	public final double invest1Year(double amount) {
		addToYield((amount * interestRate.doubleValue()) - 20);
		amount *= doubleValue(interestRate) + 1;
		amount -= 20;
		out.printf("%s returned a yield of %s for a total of %s with the fee included.%n", getName(), format((amount * interestRate.doubleValue()) - 20), format(amount));
		return amount;
	}
}