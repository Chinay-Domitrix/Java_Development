package objectOriented.inheritance.rooms;

import static java.lang.String.format;
import static java.lang.System.err;

class BathRoom extends Room {
	private static final int MIN_SIZE = 40;
	public final boolean hasShower;

	BathRoom(String name, int sqFeet, boolean hasShower) {
		super(name, sqFeet);
		this.hasShower = hasShower;
	}

	public boolean getHasShower() {
		return hasShower;
	}

	@Override
	public String toString() {
		return getHasShower() ? format("%s and has a shower.", super.toString().replace(".", "")) : format("%s and doesn't have a shower.", super.toString().replace(".", ""));
	}

	@Override
	public boolean setSqFeet(int sqFeet) {
		if (sqFeet >= MIN_SIZE) return super.setSqFeet(sqFeet);
		err.println("Only values greater than or equal to forty are accepted.");
		return false;
	}
}