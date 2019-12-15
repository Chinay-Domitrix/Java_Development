package objectOriented.investments;

import objectOriented.Name;

import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

public class BlueChipStock extends Investment {
	private InterestRate interestRate;

	public BlueChipStock() {
		this(new Name("Blue Chip Stock"));
	}

	public BlueChipStock(Name name) {
		super(name);
		interestRate = new InterestRate(((random() * 24) - 8) + "%");
	}

	@Override
	public double invest1Year(double amount) {
		var chance = (int) ((random() * 100) + 1);
		if (chance == 1) amount = 0;
		else {
			addToYield(amount * interestRate.doubleValue());
			amount *= doubleValue(interestRate) + 1;
		}
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format((amount * interestRate.doubleValue()) - 20), format(amount));
		return amount;
	}
}