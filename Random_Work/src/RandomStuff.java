import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomStuff {
	public static void main(String[] args) {
		String s = "a1, a2, a3, \"a4,a5\", a6";
		Pattern pattern = Pattern.compile("\s*(\"[^\"]*\"|[^,]*)\s*");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			System.out.println(matcher.group(1));
		}
	}
}
