package arrayLists.survivor;

import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.String.format;
import static java.lang.System.out;

final class SurvivorLogic {
	private final ArrayList<Integer> specialSauceCounter = new ArrayList<>();
	private final int runs = toIntExact(round(random() * 50) + 1);

	SurvivorLogic() {
		looper();
		out.println(specialSauce());
	}

	private void looper() {
		for (var i = 0; i < runs; i++) {
			survivorLogic();
			out.println('\n');
		}
	}

	private void survivorLogic() {
		var survivorLogic = new ArrayList<Integer>();
		var counter = 2;
		var bounds = 10;
		for (var i = 0; i < bounds; i++) survivorLogic.add(toIntExact(round((random() * 99) + 2)));
		out.printf("The starting numbers are:\n%s%n", survivorLogic);
		do {
			var random = toIntExact(round((random() * 99) + 2));
			for (var i = 0; i < survivorLogic.size(); i++) {
				var a = survivorLogic.get(i);
				if (((a % random) == 0) && (a != 0)) {
					out.printf("Round #%d removed %d because (%d %% %d==0)%n", counter, a, a, random);
					survivorLogic.remove(i);
					i--;
					bounds--;
				}
			}
			counter++;
			out.println(survivorLogic);
			if (bounds == 1) out.printf("The winner after %d rounds is %d.%n", counter, survivorLogic.get(0));
			else if (bounds < 1) out.println("There is no winner this round.");
		} while (bounds > 1);
		specialSauceCounter.add(counter);
	}

	private String specialSauce() {
		var x = 0;
		for (var integer : specialSauceCounter) x += integer;
		return format("The average number of tries it took to finish the %d%s was %s tries.", runs, runs > 1 ? "rounds" : "round", x / (double) runs);
	}
}
