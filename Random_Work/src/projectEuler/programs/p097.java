package projectEuler.programs;

import static java.math.BigInteger.*;

public final class p097 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p097().run());
	}

	String run() {
		return String.format("%010d", valueOf(2).modPow(valueOf(7830457), TEN.pow(10)).multiply(valueOf(28433)).mod(TEN.pow(10)).add(ONE).mod(TEN.pow(10)));
	}
}