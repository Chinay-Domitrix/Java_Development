package objectOriented.investments;

import objectOriented.Name;

import java.util.Random;

import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final public class PennyStock extends Investment {

	PennyStock() {
		this(new Name("Penny Stock"));
	}

	PennyStock(Name name) {
		super(name);
	}

	@Override
	double invest1Year(double amount) {
		var interestRate = new InterestRate(((random() * 130) - 50) + "%");
		var chance = new Random().nextInt(100) + 1;
		double yield;
		if (chance >= 1 && chance <= 5) {
			amount = 0;
			yield = amount;
		} else if (chance >= 6 && chance <= 10) {
			yield = amount * 50;
			addToYield(yield);
			amount *= 51;
		} else {
			yield = amount * interestRate.doubleValue();
			addToYield(yield);
			amount *= doubleValue(interestRate) + 1;
		}
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(yield), format(amount));
		return amount;
	}
}