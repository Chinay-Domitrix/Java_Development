package Classwork.AP_Computer_Science_Ⅰ.src.remoteLearning.march18.quiz;

import java.util.Random;

import static java.util.stream.IntStream.range;

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
		StringBuilder s = new StringBuilder();
		range(shiftAmount, word.length()).forEachOrdered(i -> s.append(word, i, i + 1));
		range(0, shiftAmount).forEachOrdered(i -> s.append(word, i, i + 1));
		return s.toString();
	}
}
