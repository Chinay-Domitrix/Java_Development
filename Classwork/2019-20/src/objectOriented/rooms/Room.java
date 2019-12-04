package objectOriented.rooms;

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
		if (sqFeet > 0) {
			this.sqFeet = sqFeet;
			return true;
		} else return false;
	}

	public String toString() {
		return format("This room is the %s, which has a size of %d ft².", name, sqFeet);
	}

	/*public static void main(String[] args) {
		var mb = new Room("Master Bedroom", 420);
		System.out.println(mb);
		mb.setName("Nursery");
		mb.setSqFeet(210);
		System.out.println(mb.getName() + " - " + mb.getSqFeet());
	}*/
}