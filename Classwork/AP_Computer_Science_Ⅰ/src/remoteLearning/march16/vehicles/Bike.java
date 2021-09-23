package Classwork.AP_Computer_Science_Ⅰ.src.remoteLearning.march16.vehicles;

import static java.lang.System.out;

class Bike implements Vehicle {
	int speed;
	int gear;

	@Override
	public void changeGear(int newGear) {
		gear = newGear;
	}

	@Override
	public void speedUp(int increment) {
		speed += increment;
	}

	@Override
	public void applyBrakes(int decrement) {
		speed -= decrement;
	}

	public void printStates() {
		out.printf("speed: %d gear: %d%n", speed, gear);
	}
}
