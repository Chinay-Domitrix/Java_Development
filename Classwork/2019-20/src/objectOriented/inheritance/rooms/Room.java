package objectOriented.inheritance.rooms;

import static java.lang.String.format;

class Room {
	private String name;
	private int sqFeet;

	public Room(String name, int sqFeet) {
		this.sqFeet = sqFeet;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSqFeet() {
		return sqFeet;
	}

	public boolean setSqFeet(int sqFeet) {
		var result = false;
		if (sqFeet > 0) {
			this.sqFeet = sqFeet;
			result = true;
		}
		return result;
	}

	public String toString() {
		return format("This room is the %s, which has a size of %d ft².", name, getSqFeet());
	}
}