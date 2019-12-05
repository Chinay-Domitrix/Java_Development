package objectOriented.rooms;

class HouseDesigner {
	private HouseDesigner() {
		System.out.println("Java House Layout");
		for (var i = 0; i < 20; i++) System.out.println('\u2015');
	}

	public static void main(String[] args) {
		new HouseDesigner();
	}
}
