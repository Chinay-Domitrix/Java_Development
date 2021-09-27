package objectOriented.coords;

import static java.lang.Math.random;
import static java.lang.System.out;

class OrderedPairDriver {
	private OrderedPairDriver() {
		var x = new OrderedPair[2];
		x[0] = new OrderedPair((int) (random() * 11), (int) (random() * 11));
		out.println(x[0]);
		out.println(OrderedPair.distanceFromOrigin());
		x[1] = new OrderedPair();
		out.println(x[1]);
		out.println(OrderedPair.distanceFromOrigin());
	}

	public static void main(String[] args) {
		new OrderedPairDriver();
	}
}
