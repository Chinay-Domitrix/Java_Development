package arrayLists.survivor;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.System.out;

class SurvivorLogic {
	/*These declarations are just so all the methods have the ability to */
	private ArrayList<Integer> specialSauceCounter = new ArrayList<>();
	private int runs = toIntExact(round(random() * 50) + 1);

	SurvivorLogic() {
		looper();
		out.println(specialSauce());
	}

	private void looper() {
		for (int i = 0; i < runs; i++) {
			survivorLogic();
			out.println('\n');
		}
	}

	private void survivorLogic() {
		ArrayList<Integer> survivorLogic = new ArrayList<>();
		int counter = 2;
		int bounds = 10;
		for (int i = 0; i < bounds; i++)
			survivorLogic.add(toIntExact(round((random() * 99) + 2)));
		out.println("The starting numbers are:\n" + survivorLogic);
		do {
			int random = toIntExact(round((random() * 99) + 2));
			for (int i = 0; i < survivorLogic.size(); i++) {
				int a = survivorLogic.get(i);
				if (((a % random) == 0) && (a != 0)) {
					out.println("Round #" + counter + " removed " + a + " because (" + a + " % " + random + "==0)");
					survivorLogic.remove(i);
					i--;
					bounds--;
				}
			}
			counter++;
			out.println(survivorLogic);
			if (bounds == 1) out.println("The winner after " + counter + " rounds is " + survivorLogic.get(0) + '.');
			else if (bounds < 1) out.println("There is no winner this round.");
		} while (bounds > 1);
		specialSauceCounter.add(counter);
	}

	@NotNull
	@Contract(pure = true)
	private String specialSauce() {
		int x = 0;
		for (Integer integer : specialSauceCounter) x += integer;
		return "The average number of tries it took to finish the " + runs + (runs > 1 ? " rounds " : " round ") + "was " + (x / (double) runs) + " tries.";
	}
}
