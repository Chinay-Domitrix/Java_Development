import java.util.Arrays;

38

public final class p038 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p038().run());
	}

	private static boolean isPandigital(String s) {
		if (s.length() != 9)
			return false;
		char[] temp = s.toCharArray();
		Arrays.sort(temp);
		return new String(temp).equals("123456789");
	}

	String run() {
		int max = -1;
		for (int n = 2; n <= 9; n++) {
			for (int i = 1; i < Library.pow(10, 9 / n); i++) {
				StringBuilder concat = new StringBuilder();
				for (int j = 1; j <= n; j++)
					concat.append(i * j);
				if (isPandigital(concat.toString()))
					max = Math.max(Integer.parseInt(concat.toString()), max);
			}
		}
		return Integer.toString(max);
	}
}