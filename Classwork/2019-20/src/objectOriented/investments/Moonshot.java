package objectOriented.investments;

import objectOriented.Name;

import java.util.Random;

import static java.lang.System.out;

final class Moonshot extends Investment {
	Moonshot() {
		this(new Name("Moonshot"));
	}

	Moonshot(Name name) {
		super(name);
	}

	@Override
	double invest1Year(double amount) {
		var chance = new Random().nextInt(100) + 1;
		double yield;
		if (chance >= 1 && chance <= 80) {
			amount = 0;
			yield = amount;
		} else if (chance == 81) {
			yield = amount * 100001;
			addToYield(yield);
			amount *= 100001;
		} else {
			yield = 0;
		}
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(yield), format(amount));
		return amount;
	}
}