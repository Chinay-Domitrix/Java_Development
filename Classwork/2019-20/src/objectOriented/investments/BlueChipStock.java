package objectOriented.investments;

import objectOriented.Name;

import java.util.Random;

import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class BlueChipStock extends Investment {
	private InterestRate interestRate;

	BlueChipStock() {
		this(new Name("Blue Chip Stock"));
	}

	BlueChipStock(Name name) {
		super(name);
		interestRate = new InterestRate(((random() * 24) - 8) + "%");
	}

	@Override
	final double invest1Year(double amount) {
		var chance = new Random().nextInt(100) + 1;
		double x;
		if (chance == 1) {
			amount = 0;
			x = amount;
		} else {
			x = amount * interestRate.doubleValue();
			addToYield(x);
			amount *= doubleValue(interestRate) + 1;
		}
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(x), format(amount));
		return amount;
	}
}