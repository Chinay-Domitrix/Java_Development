package objectOriented.rooms;

import static java.lang.String.format;

class BathRoom extends Room {
	private boolean hasShower;

	BathRoom(String name, int sqFeet, boolean hasShower) {
		super(name, sqFeet);
		this.hasShower = hasShower;
	}

	/*public static void main(String[] args) {
		var br = new BathRoom("Powder Room", 150, false);
		System.out.println(br);
		br.setSqFeet(7);
		System.out.println(br.getSqFeet());
	}*/

	public boolean getHasShower() {
		return hasShower;
	}

	public String toString() {
		if (hasShower) return format("%s and has a shower.", super.toString().replace(".", ""));
		return format("%s and doesn't have a shower.", super.toString().replace(".", ""));
	}

	public boolean setSqFeet(int sqFeet) {
		if (sqFeet >= 10) return super.setSqFeet(sqFeet);
		System.err.println("Only values greater than ten are accepted.");
		return false;
	}
}