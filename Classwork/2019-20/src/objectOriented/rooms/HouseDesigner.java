package objectOriented.rooms;

import static java.lang.System.out;

class HouseDesigner {
	private HouseDesigner() {
		out.println("Java House Layout");
		for (var i = 0; i < 20; i++) out.println("―");
	}

	public static void main(String[] args) {
		new HouseDesigner();
	}
}
