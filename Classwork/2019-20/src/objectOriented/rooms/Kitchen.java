package objectOriented.rooms;

import static java.lang.System.err;

class Kitchen extends Room {
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
		if (sqFeet >= 70) return super.setSqFeet(sqFeet);
		err.println("Only values greater than or equal to seventy are accepted.");
		return false;
	}
}
