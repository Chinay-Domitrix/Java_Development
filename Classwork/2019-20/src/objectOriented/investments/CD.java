package objectOriented.investments;

import objectOriented.Name;

import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

class CD extends Investment {
	private final InterestRate interestRate;

	CD() {
		this(new Name("Certificate of Deposit"), new InterestRate("2.2%"));
	}

	CD(Name name, InterestRate interestRate) {
		super(name);
		this.interestRate = interestRate;
	}

	@Override
	final double invest1Year(double amount) {
		var x = amount * interestRate.doubleValue();
		addToYield(x);
		amount *= doubleValue(interestRate) + 1;
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(x), format(amount));
		return amount;
	}
}