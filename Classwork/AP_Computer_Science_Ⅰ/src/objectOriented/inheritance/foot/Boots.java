package objectOriented.inheritance.foot;

import org.jetbrains.annotations.NotNull;

import static java.lang.System.out;

public class Boots extends Footwear {
	@SuppressWarnings("SameParameterValue")
	private Boots(String name) {
		super(name);
	}

	public Boots(String name, boolean leftOn, boolean rightOn) {
		super(name, leftOn, rightOn);
	}

	public static void main(String[] args) {
		Boots boots = new Boots("Winter Boots");
		if (boots.putOn("both")) out.println("Both boots are on!");
		else if (boots.putOn("left")) out.println("The left boot is on!");
		else if (boots.putOn("right")) out.println("The right boot is on!");
		else out.println("Input is invalid!");
	}

	public boolean putOn(@NotNull String foot) {
		if (foot.equalsIgnoreCase("left")) this.setLeftOn(true);
		else if (foot.equalsIgnoreCase("right")) this.setRightOn(true);
		else if (foot.equalsIgnoreCase("both")) {
			this.setRightOn(true);
			this.setLeftOn(true);
		} else return false;
		return true;
	}
}