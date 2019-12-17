package objectOriented.investments;

import objectOriented.Name;

import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class CD extends Investment {
	private final InterestRate interestRate;

	CD() {
		this(new Name("Certificate of Deposit"), new InterestRate("2.2%"));
	}

	CD(Name name, InterestRate interestRate) {
		super(name);
		this.interestRate = interestRate;
	}

	@Override
	double invest1Year(double amount) {
		var yield = amount * interestRate.doubleValue();
		addToYield(yield);
		amount *= doubleValue(interestRate) + 1;
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(yield), format(amount));
		return amount;
	}
}