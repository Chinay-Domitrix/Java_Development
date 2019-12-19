package objectOriented.investments;

import objectOriented.Name;

import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class CertificateOfDeposit extends Investment {
	private final InterestRate interestRate;

	CertificateOfDeposit() {
		this(new Name("Certificate of Deposit"), new InterestRate("2.2%"));
	}

	CertificateOfDeposit(Name name, InterestRate interestRate) {
		super(name);
		this.interestRate = interestRate;
	}

	CertificateOfDeposit(String name, double interestRate) {
		this(new Name(name), new InterestRate((interestRate * 100) + "%"));
	}

	@Override
	double invest1Year(double amount) {
		var yield = amount * interestRate.doubleValue();
		addToYield(yield);
		amount *= doubleValue(interestRate) + 1;
		out.printf("%s returned a %s yield of %s for a total of %s.%n", getName(), interestRate, format(yield), format(amount));
		return amount;
	}
}