package objectOriented.rooms;

import static java.lang.String.format;

class BedRoom extends Room {
	private String bedSize;

	public BedRoom(String name, int sqFeet, String bedSize) {
		super(name, sqFeet);
		this.bedSize = bedSize;
	}

	public String toString() {
		return format("%s and has a %s sized bed.", super.toString().replace(".", ""), bedSize);
	}

	@Override
	public boolean setSqFeet(int sqFeet) {
		if (sqFeet >= 70) return super.setSqFeet(sqFeet);
		System.err.println("Only values greater than or equal to seventy are accepted.");
		return false;
	}
}
