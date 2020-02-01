package arrayLists.survivor;

import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.range;

final class SurvivorLogic {
	private final ArrayList<Integer> specialSauceCounter = new ArrayList<>();
	private final int runs = toIntExact(round(random() * 50) + 1);

	SurvivorLogic() {
		looper();
		out.println(specialSauce());
	}

	private void looper() {
		range(0, runs).forEachOrdered(i -> {
			survivorLogic();
			out.println('\n');
		});
	}

	private void survivorLogic() {
		ArrayList<Integer> survivorLogic;
		var counter = 2;
		var bounds = 10;
		survivorLogic = range(0, bounds).mapToObj(i -> toIntExact(round((random() * 99) + 2))).collect(toCollection(ArrayList::new));
		out.printf("The starting numbers are:\n%s%n", survivorLogic);
		do {
			var random = toIntExact(round((random() * 99) + 2));
			for (var i = 0; i < survivorLogic.size(); i++)
				if (((survivorLogic.get(i) % random) == 0) && (survivorLogic.get(i) != 0)) {
					out.printf("Round #%d removed %d because (%d %% %d==0)%n", counter, survivorLogic.get(i), survivorLogic.get(i), random);
					survivorLogic.remove(i);
					i--;
					bounds--;
				}
			counter++;
			out.println(survivorLogic);
			if (bounds == 1) out.printf("The winner after %d rounds is %d.%n", counter, survivorLogic.get(0));
			else if (bounds < 1) out.println("There is no winner this round.");
		} while (bounds > 1);
		specialSauceCounter.add(counter);
	}

	private String specialSauce() {
		var x = specialSauceCounter.stream().mapToInt(integer -> integer).sum();
		return format("The average number of tries it took to finish the %d%s was %s tries.", runs, (runs == 1) ? "round" : "rounds", x / (double) runs);
	}
}
