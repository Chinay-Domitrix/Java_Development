package learnGraphics.blockBreak.blockBreak2;

/*  This is an extenstion of Block which adds functionality for the block to
    'wiggleSize' or move back and forth in a set pattern ehwn you call the move method
*/

public class WiggleBlock extends Block {
	private final int wiggleSize;  //how many spaces it will go in one direction before turning 180
	private int count;         // keep track of how much of its pattern is complete
	private int wiggleDirection;// Direction of wiggle in degrees 0-North, 90-East, 180-South, 270 - West

	public WiggleBlock(int x, int y, int w, int h, boolean goal, int wiggleSize, int dir) {
		super(x, y, w, h, false);
		this.wiggleSize = wiggleSize;
		this.wiggleDirection = dir;
	}

	public void move() {
		int xInc = 0;
		int yInc = 0;
		if (count == wiggleSize) {
			count = 0;
			wiggleDirection = (wiggleDirection + 180) % 360;
		}
		if (((wiggleDirection >= 0) && (wiggleDirection < 90)) || ((wiggleDirection >= 270) && (wiggleDirection <= 360)))
			yInc++;
		if ((wiggleDirection > 90) && (wiggleDirection < 270)) yInc--;
		if ((wiggleDirection > 0) && (wiggleDirection < 180)) xInc++;
		if ((wiggleDirection > 180) && (wiggleDirection < 360)) xInc--;
		super.setPos(getX() + xInc, getY() + yInc);
		count++;
	}
}