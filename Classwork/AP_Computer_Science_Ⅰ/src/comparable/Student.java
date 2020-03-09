import static java.lang.Integer.compare;
import static java.lang.String.format;
import static java.lang.System.out;

public class Student implements Comparable<Student> {
	private String[] name;
	private int idNumber;

	private Student(String name, int idNumber) {
		assert (name != null) && (name.split("").length == 2);
		assert (idNumber > 0);
		this.name = name.split(" ");
		this.idNumber = idNumber;
	}

	public static void main(String[] args) {
		Student x = new Student("Chirag Baviskar", 30000054);
		out.println(x.compareTo(new Student("Amit Ramasubramanian", 30000259)));
		out.println(new Student("Amit Ramasubramanian", 30000259).compareTo(x));
		out.println(x.compareTo(new Student("Tanay Baviskar", 30000049)));
		out.println(new Student("Tanay Baviskar", 30000049).compareTo(x));
		out.println(x.compareTo(new Student("Chirag Baviskar", 30000049)));
		out.println(new Student("Chirag Baviskar", 30000049).compareTo(x));
		out.println(x.compareTo(new Student("Chirag Baviskar", 30000054)));
		out.println(new Student("Chirag Baviskar", 30000054).compareTo(x));
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
	public int compareTo(Student other) {
		return (name[1].compareTo(other.name[1]) == 0) ? ((name[0].compareTo(other.name[0]) == 0) ? compare(idNumber, other.idNumber) : name[0].compareTo(other.name[0])) : name[1].compareTo(other.name[1]);
	}

	@Override
	public String toString() {
		return format("The student's name is %s %s, and their ID number is %d.", name[0], name[1], idNumber);
	}
}