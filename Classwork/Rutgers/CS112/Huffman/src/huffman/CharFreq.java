package huffman;

import org.jetbrains.annotations.NotNull;

/**
 * This class contains a character object, and a double representing
 * its probability of occurrence
 *
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class CharFreq implements Comparable<CharFreq> {
	private Character character;
	private double probOcc;

	// We can set both the Character and double at once
	public CharFreq(Character c, double p) {
		character = c;
		probOcc = p;
	}

	// No arguments make a null character and prob 0
	public CharFreq() {
		this(null, 0);
	}

	// Allows us to use Collections.sort() to sort by probOcc
	public int compareTo(@NotNull CharFreq cf) {
		Double d1 = probOcc;
		Double d2 = cf.probOcc;
		return d1.compareTo(d2) != 0 ? d1.compareTo(d2) : character.compareTo(cf.character);
	}

	// Getters and setters
	public Character getCharacter() {
		return character;
	}

	public double getProbOcc() {
		return probOcc;
	}

	public void setCharacter(Character c) {
		character = c;
	}

	public void setProbOcc(double p) {
		probOcc = p;
	}
}
