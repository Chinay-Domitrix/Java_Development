package Classwork.AP_Computer_Science_Ⅰ.src.remoteLearning.march16.vehicles;

import static java.lang.System.out;

public class EBike extends Bike implements ElectricVehicle {
	private static final int maxCharge = 100;
	private static int powerLevel = maxCharge;

	private static int getPowerLevel() {
		return powerLevel;
	}

	@Override
	public void usePower(int powerUsed) {
		assert powerUsed >= 0 && powerUsed <= 100;
		powerLevel -= powerUsed;
	}

	@Override
	public void fullRecharge() {
		powerLevel = maxCharge;
	}

	@Override
	public void printStates() {
		out.printf("speed: %d gear: %d power level: %d%n", speed, gear, getPowerLevel());
	}
}
