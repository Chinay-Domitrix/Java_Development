package objectOriented.investments;

import objectOriented.Name;

import java.util.Random;

import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final public class PennyStock extends Investment {

	private double minimumInterest = 100;
	private double maximumInterest = 100;
	private double collapseChance = 100;
	private double multiplierChance = 100;
	private int multiplier;

	PennyStock() {
		this(new Name("Penny Stock"));
	}

	PennyStock(Name name) {
		super(name);
	}

	public PennyStock(String name, double minimumInterest, double maximumInterest, double collapseChance, double multiplierChance, int multiplier) {
		super(new Name(name));
		this.minimumInterest *= minimumInterest;
		this.maximumInterest *= maximumInterest;
		this.collapseChance *= collapseChance;
		this.multiplierChance *= multiplierChance;
		this.multiplier = multiplier;
	}

	@Override
	double invest1Year(double amount) {
		var interestRate = new InterestRate(((random() * ((maximumInterest - minimumInterest) + 1)) + minimumInterest) + "%");
		var chance = new Random().nextInt(100) + 1;
		double yield;
		if (chance >= 1 && chance <= collapseChance) {
			amount = 0;
			yield = amount;
		} else if (chance >= collapseChance + 1 && chance <= multiplierChance) {
			yield = amount * multiplier;
			addToYield(yield);
			amount *= multiplier;
		} else {
			yield = amount * interestRate.doubleValue();
			addToYield(yield);
			amount *= doubleValue(interestRate) + 1;
		}
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(yield), format(amount));
		return amount;
	}
}