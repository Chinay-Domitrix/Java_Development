package objectOriented.investments;

import objectOriented.Name;

import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class CD extends Investment {
	private InterestRate interestRate;

	public CD() {
		this(new Name("Certificate of Deposit"), new InterestRate("2.2%"));
	}

	public CD(Name name, InterestRate interestRate) {
		super(name);
		this.interestRate = interestRate;
	}

	public final double invest1Year(double amount) {
		addToYield(amount * interestRate.doubleValue());
		amount *= doubleValue(interestRate) + 1;
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(amount * interestRate.doubleValue()), format(amount));
		return amount;
	}

}