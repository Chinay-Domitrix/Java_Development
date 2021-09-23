package Classwork.AP_Computer_Science_Ⅰ.src.remoteLearning.march18.quiz;

interface TipHelper {
	int tip(double percentage);
}

public class TipCalculator implements TipHelper {
	private double amount;

	public TipCalculator(double amount) {
		this.amount = amount;
	}

	@Override
	public int tip(double percentage) {
		var x = amount * percentage;
		for (var i = x; i > 0; i--)
			if (i > 0 && i < 1) {
				x -= i - 1;
				break;
			}
		return (int) x;
	}
}
