package Classwork.AP_Computer_Science_Ⅰ.src.comparable;

import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.compare;
import static java.lang.String.format;

public class Student implements Comparable<Student> {
	private String[] name;
	private int idNumber;

	private Student(@NotNull String name, int idNumber) {
		assert name.split("").length == 2;
		assert (idNumber > 0);
		this.name = name.split(" ");
		this.idNumber = idNumber;
	}

	/**
	 * Initially runs a {@link String#compareTo(String)} on the last name. If it is zero, the method runs a
	 * {@linkplain String#compareTo(String)} on the first name. If <i>that</i> is zero, the method returns the result of
	 * {@link Integer#compare(int, int)}.
	 *
	 * @param other the {@code Student} object to be compared to
	 * @return the result of either a {@linkplain String#compareTo(String)} or a {@linkplain Integer#compare(int, int)},
	 * depending on how similar the {@code Student} objects are
	 */
	@Override
	public int compareTo(@NotNull Student other) {
		return (name[1].compareTo(other.name[1]) == 0) ? ((name[0].compareTo(other.name[0]) == 0) ? compare(idNumber, other.idNumber) : name[0].compareTo(other.name[0])) : name[1].compareTo(other.name[1]);
	}

	@Override
	public String toString() {
		return format("The student's name is %s %s, and their ID number is %d.", name[0], name[1], idNumber);
	}
}
