package codeWars.sets;

public class DetectPangram {
	public static boolean isPangram(String string) {
		string = string.toLowerCase();
		for (char c = 'a'; c <= 'z'; c++) if (string.indexOf(c) == -1) return false;
		return true;
	}
}