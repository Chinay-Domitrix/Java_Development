import java.util.ArrayList;

import static java.lang.System.out;

public class Elimination {
	public static void main(String[] args) {
		var x = new ArrayList<String>();
		x.add(null);
		for (var i = 1; i <= 2000; i++) x.set(i, Integer.toString(i));
		out:
		while (true) {
			for (int i = 2; i <= 2000; i += 2) {
				x.remove(i);
				if (x.size() == 2) break out;
			}
			for (int i = 1; i <= 2000; i += 2) {
				x.remove(i);
				if (x.size() == 2) break out;
			}
		}
		out.println(x.get(1));

	}
}
