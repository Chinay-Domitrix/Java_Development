package objectOriented.rooms;

import static java.lang.String.format;

public class BedRoom extends Room {
	private String bedSize;

	public BedRoom(String name, int sqFeet, String bedSize) {
		super(name, sqFeet);
		this.bedSize = bedSize;
	}

	public String toString() {
		return format("%s and has a %s sized bed.", super.toString().replace(".", ""), bedSize);
	}
}
