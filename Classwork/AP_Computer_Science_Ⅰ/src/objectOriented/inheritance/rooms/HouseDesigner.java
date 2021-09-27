package objectOriented.inheritance.rooms;

import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

final class HouseDesigner {
	private HouseDesigner() {
		out.println("Java House Layout");
		range(0, 20).mapToObj(i -> "―").forEachOrdered(out::println);
		var x = new Room[] { new Room("laundry room", 120), new Room("dining room", 300),
				new Kitchen("kitchen", 350, "gas", true), new BathRoom("powder room", 100, false),
				new BathRoom("master bathroom", 150, true), new BedRoom("master bedroom", 350, "king"),
				new BedRoom("guest bedroom", 200, "queen"), new Room("basement", 500), new Room("pantry", 50),
				new Room("living room", 350) };
		stream(x).forEachOrdered(i -> out.printf("%s\n%n", i));
		out.println(stream(x).mapToInt(Room::getSqFeet).sum());
	}

	public static void main(String[] args) {
		new HouseDesigner();
	}
}
