import java.math.BigInteger;

97

public final class p097 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p097().run());
	}

	String run() {
		BigInteger modulus = BigInteger.TEN.pow(10);
		BigInteger n = BigInteger.valueOf(2).modPow(BigInteger.valueOf(7830457), modulus);
		n = n.multiply(BigInteger.valueOf(28433)).mod(modulus);
		n = n.add(BigInteger.ONE).mod(modulus);
		return String.format("%010d", n);
	}
}