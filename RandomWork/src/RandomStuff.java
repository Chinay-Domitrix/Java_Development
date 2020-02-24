public class RandomStuff {
	public static void main(String[] args) {

	}

	public boolean xyzMiddle(String str) {
		if (str.length() < 3) return false;
		int start1 = str.length() / 2 - 2, start2 = str.length() / 2 - 1;
		boolean xyz = str.substring(start2, start2 + 3).equals("xyz");
		return ((str.length() % 2) == 0) ? (str.substring(start1, start1 + 3).equals("xyz") || xyz) : xyz;
	}
}
