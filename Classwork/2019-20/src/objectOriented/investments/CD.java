package objectOriented.investments;

import static java.lang.System.out;

public class CD extends Investment {
	private double interestRate;

	public CD(String name, double interestRate) {
		super(name);
		this.interestRate = interestRate;
	}

	public static void main(String[] args) {
		out.println(format(new CD("CD with 2% yield", 0.02).invest1Year(100)));
	}

	public double invest1Year(double amt) {
//		Must use this step each time to keep track of overall yield from this investment object
		addToYield(amt * interestRate);
		amt += amt * interestRate;
		out.printf("%s returned a yield of %s for a total of %s%n", getName(), format(amt * interestRate), format(amt));
		return amt; // returns new total
	}

}