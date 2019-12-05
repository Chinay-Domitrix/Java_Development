package objectOriented.rooms;

import static java.lang.String.format;
import static java.lang.System.err;

class BathRoom extends Room {
	private boolean hasShower;

	BathRoom(String name, int sqFeet, boolean hasShower) {
		super(name, sqFeet);
		this.hasShower = hasShower;
	}

	public boolean getHasShower() {
		return hasShower;
	}

	public String toString() {
		if (getHasShower()) return format("%s and has a shower.", super.toString().replace(".", ""));
		return format("%s and doesn't have a shower.", super.toString().replace(".", ""));
	}

	@Override
	public boolean setSqFeet(int sqFeet) {
		if (sqFeet >= 40) return super.setSqFeet(sqFeet);
		err.println("Only values greater than or equal to forty are accepted.");
		return false;
	}
}