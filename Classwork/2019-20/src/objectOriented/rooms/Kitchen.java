package objectOriented.rooms;

import static java.lang.String.format;
import static java.lang.System.err;

class Kitchen extends Room {
	public static final int MIN_SIZE = 70;
	private String stoveType;
	private boolean hasOven;

	public Kitchen(String name, int sqFeet, String stoveType, boolean hasOven) {
		super(name, sqFeet);
		this.stoveType = stoveType;
		this.hasOven = hasOven;
	}

	public boolean getHasOven() {
		return hasOven;
	}

	public String getStoveType() {
		return stoveType;
	}

	@Override
	public boolean setSqFeet(int sqFeet) {
		if (sqFeet >= MIN_SIZE) return super.setSqFeet(sqFeet);
		err.println("Only values greater than or equal to seventy are accepted.");
		return false;
	}

	@Override
	public String toString() {
		return getHasOven() ? format("%s and has a(n) " + getStoveType() + " stove with an oven.", super.toString().replace(".", "")) : format("%s and has a(n) " + getStoveType() + " stove without an oven.", super.toString().replace(".", ""));
	}
}
