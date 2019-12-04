package objectOriented.rooms;

public class BedRoom extends Room {
	private int numBeds;
	private String[] bedSizes;

	public BedRoom(String name, int sqFeet, int numBeds, String[] bedSizes) {
		super(name, sqFeet);
		this.numBeds = numBeds;
		this.bedSizes = bedSizes;
	}
	
}
