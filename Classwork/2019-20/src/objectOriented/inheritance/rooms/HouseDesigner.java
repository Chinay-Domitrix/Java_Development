package objectOriented.inheritance.rooms;

import static java.lang.System.out;

final class HouseDesigner {
	private HouseDesigner() {
		out.println("Java House Layout");
		for (var i = 0; i < 20; i++) out.println("―");
		Room[] x = new Room[]{new Room("laundry room", 120), new Room("dining room", 300), new Kitchen("kitchen", 350, "gas", true), new BathRoom("powder room", 100, false), new BathRoom("master bathroom", 150, true), new BedRoom("master bedroom", 350, "king"), new BedRoom("guest bedroom", 200, "queen"), new Room("basement", 500), new Room("pantry", 50), new Room("living room", 350)};
		for (Room i : x) out.printf("%s\n%n", i);
		int totalSqFeet = 0;
		for (Room i : x) {
			totalSqFeet += i.getSqFeet();
		}
		out.println(totalSqFeet);
	}

	public static void main(String[] args) {
		new HouseDesigner();
	}
}
