package remoteLearning.march18.quiz;

import java.util.Random;

public class Shifter {
	private String word;
	private int shiftAmount;

	private Shifter(String word) {
		this.word = word;
	}

	private Shifter(String word, int shiftAmount) {
		this(word);
		this.shiftAmount = shiftAmount;
	}

	private String shift() {
		shiftAmount = new Random().nextInt(word.length() + 1) + 1;
		var s = "";
		for (int i = shiftAmount; i < word.length(); i++) s += word.substring(i, i + 1);
		for (int i = 0; i < shiftAmount; i++) s += word.substring(i, i + 1);
		return s;
	}
}