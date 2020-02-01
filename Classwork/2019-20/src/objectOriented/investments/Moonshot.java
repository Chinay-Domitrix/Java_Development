package objectOriented.investments;

import java.util.Random;

import static java.lang.System.out;

final class Moonshot extends Investment {
	private double collapseChance = 100;
	private double multiplierChance = 100;
	private final int multiplier;

	Moonshot() {
		this(new Name("Moonshot"));
	}

	private Moonshot(Name name) {
		super(name);
		collapseChance = 80;
		multiplierChance = 1;
		multiplier = 100000;
	}

	@SuppressWarnings("SameParameterValue")
	Moonshot(String name, double collapseChance, double multiplierChance, int multiplier) {
		super(new Name(name));
		this.collapseChance *= collapseChance;
		this.multiplierChance *= multiplierChance;
		this.multiplier = multiplier;
	}

	@Override
	double invest1Year(double amount) {
		var chance = new Random().nextInt(100) + 1;
		double yield;
		if (chance >= 1 && chance <= 80) {
			yield = -amount;
			addToYield(yield);
			amount += yield;
		} else if (chance == collapseChance + multiplierChance) {
			yield = amount * multiplier;
			addToYield(yield);
			amount *= multiplier;
		} else yield = 0;
		out.printf("%s returned a yield of %s for a total of %s.%n", getName(), format(yield), format(amount));
		return amount;
	}
}