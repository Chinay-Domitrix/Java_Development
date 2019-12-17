package objectOriented.investments;

import objectOriented.Name;

import java.util.Random;

import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class BlueChipStock extends Investment {

	BlueChipStock() {
		this(new Name("Blue Chip Stock"));
	}

	BlueChipStock(Name name) {
		super(name);

	}

	@Override
	double invest1Year(double amount) {
		var interestRate = new InterestRate(((random() * 23) - 8) + "%");
		var chance = new Random().nextInt(100) + 1;
		double yield;
		if (chance == 1) {
			amount = 0;
			yield = amount;
		} else {
			yield = amount * interestRate.doubleValue();
			addToYield(yield);
			amount *= doubleValue(interestRate) + 1;
		}
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(yield), format(amount));
		return amount;
	}
}