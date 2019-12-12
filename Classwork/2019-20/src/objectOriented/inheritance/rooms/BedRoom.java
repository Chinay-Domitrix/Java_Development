package objectOriented.inheritance.rooms;

import static java.lang.String.format;
import static java.lang.System.err;

final class BedRoom extends Room {
	public static final int MIN_SIZE = 70;
	private final String bedSize;

	public BedRoom(String name, int sqFeet, String bedSize) {
		super(name, sqFeet);
		this.bedSize = bedSize;
	}

	@Override
	public String toString() {
		return format("%s and has a %s sized bed.", super.toString().replace(".", ""), getBedSize());
	}

	@Override
	public boolean setSqFeet(int sqFeet) {
		if (sqFeet >= MIN_SIZE) return super.setSqFeet(sqFeet);
		err.println("Only values greater than or equal to seventy are accepted.");
		return false;
	}

	public String getBedSize() {
		return bedSize;
	}
}
