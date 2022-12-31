package kindergarten;

/**
 * This class represents a Classroom, with:
 * - an SNode instance variable for students in line,
 * - an SNode instance variable for musical chairs, pointing to the last student in the list,
 * - a boolean array for seating availability (e.g. can a student sit in a given seat), and
 * - a Student array parallel to seatingAvailability to show students filed into seats
 * --- (more formally, seatingAvailability[i][j] also refers to the same seat in studentsSitting[i][j])
 *
 * @author Ethan Chou
 * @author Kal Pandit
 * @author Maksims Kurjanovics Kravcenko
 */
public class Classroom {
	private SNode studentsInLine;             // when students are in line: references the FIRST student in the LL
	private SNode musicalChairs;              // when students are in musical chairs: references the LAST student in the CLL
	private boolean[][] seatingAvailability;  // represents the classroom seats that are available to students
	private Student[][] studentsSitting;      // when students are sitting in the classroom: contains the students

	/**
	 * Constructor for classrooms. Do not edit.
	 *
	 * @param l passes in students in line
	 * @param m passes in musical chairs
	 * @param a passes in availability
	 * @param s passes in students sitting
	 */
	public Classroom(SNode l, SNode m, boolean[][] a, Student[][] s) {
		studentsInLine = l;
		musicalChairs = m;
		seatingAvailability = a;
		studentsSitting = s;
	}

	/**
	 * Default constructor starts an empty classroom. Do not edit.
	 */
	public Classroom() {
		this(null, null, null, null);
	}

	/**
	 * This method simulates students coming into the classroom and standing in line.
	 * <p>
	 * Reads students from input file and inserts these students in alphabetical order to studentsInLine singly linked list.
	 * <p>
	 * Input file has:
	 * <ol>
	 *     <li>one line containing an integer representing the number of students in the file, say x</li>
	 *     <li>x lines containing one student per line. Each line has the following student information separated by spaces: FirstName LastName Height</li>
	 * </ol>
	 *
	 * @param filename the student information input file
	 */
	public void makeClassroom(String filename) {
		StdIn.setFile(filename);
		var numStudents = StdIn.readInt();
		for (var i = 0; i < numStudents; i++) {
			var s = new Student(StdIn.readString(), StdIn.readString(), StdIn.readInt());
			if (studentsInLine == null) studentsInLine = new SNode(s, null);
			else {
				var current = studentsInLine;
				SNode previous = null;
				while ((current != null) && (s.compareNameTo(current.getStudent()) > 0)) {
					previous = current;
					current = current.getNext();
				}
				if (previous == null) studentsInLine = new SNode(s, current);
				else previous.setNext(new SNode(s, current));
			}
		}
	}

	/**
	 * This method creates and initializes the seatingAvailability (2D array) of
	 * available seats inside the classroom. Imagine that unavailable seats are broken and cannot be used.
	 * <p>
	 * Reads seating chart input file with the format:<br>
	 * An integer representing the number of rows in the classroom, say r<br>
	 * An integer representing the number of columns in the classroom, say c<br>
	 * Number of r lines, each containing c true or false values (true denotes an available seat)
	 * <p>
	 * This method also creates the studentsSitting array with the same number of rows and columns as the seatingAvailability array
	 * <p>
	 * This method does not seat students on the seats.
	 *
	 * @param seatingChart the seating chart input file
	 */
	public void setupSeats(String seatingChart) {
		StdIn.setFile(seatingChart);
		var rows = StdIn.readInt();
		var cols = StdIn.readInt();
		seatingAvailability = new boolean[rows][cols];
		studentsSitting = new Student[rows][cols];
		for (var i = 0; i < rows; i++) for (var j = 0; j < cols; j++) seatingAvailability[i][j] = StdIn.readBoolean();
	}

	/**
	 * This method simulates students taking their seats in the classroom.
	 * <ol>
	 *     <li>seats any remaining students from the musicalChairs starting from the front of the list</li>
	 *     <li>starting from the front of the studentsInLine singly linked list</li>
	 *     <li>removes one student at a time from the list and inserts them into studentsSitting according to seatingAvailability</li>
	 * </ol>
	 * studentsInLine will then be empty
	 */
	public void seatStudents() {
		var current = musicalChairs;
		while (current != null) {
			seatStudent(current.getStudent());
			if ((current == musicalChairs) || (current == current.getNext())) break;
			else current = current.getNext();
		}
		musicalChairs = null;
		current = studentsInLine;
		while (current != null) {
			seatStudent(current.getStudent());
			current = current.getNext();
		}
		studentsInLine = null;
	}

	private void seatStudent(Student s) {
		for (var i = 0; i < seatingAvailability.length; i++)
			for (var j = 0; j < seatingAvailability[i].length; j++)
				if (seatingAvailability[i][j]) {
					seatingAvailability[i][j] = false;
					studentsSitting[i][j] = s;
					return;
				}
	}

	/**
	 * Traverses studentsSitting row-wise (starting at row 0) removing a seated
	 * student and adding that student to the end of the musicalChairs list.
	 * <p>
	 * row-wise: starts at index [0][0] traverses the entire first row and then moves
	 * into second row.
	 */
	public void insertMusicalChairs() {
		for (var i = 0; i < studentsSitting.length; i++)
			for (var j = 0; j < studentsSitting[i].length; j++) {
				var s = studentsSitting[i][j];
				if (s != null) {
					SNode temp;
					if (musicalChairs == null) {
						temp = new SNode(s, null);
						temp.setNext(temp);
					} else {
						temp = new SNode(s, musicalChairs.getNext());
						musicalChairs.setNext(temp);
					}
					musicalChairs = temp;
					studentsSitting[i][j] = null;
					seatingAvailability[i][j] = true;
				}
			}
	}

	/**
	 * This method repeatedly removes students from the musicalChairs until there is only one
	 * student (the winner).
	 * <p>
	 * Choose a student to be eliminated from the musicalChairs using StdRandom.uniform(int b),
	 * where b is the number of students in the musicalChairs. 0 is the first student in the
	 * list, b-1 is the last.
	 * <p>
	 * Removes eliminated student from the list and inserts students back in studentsInLine
	 * in ascending height order (shortest to tallest).
	 * <p>
	 * The last line of this method calls the seatStudents() method so that students can be seated.
	 */
	public void playMusicalChairs() {
		var counter = 0;
		for (var current = musicalChairs; current.getNext() != musicalChairs; current = current.getNext()) counter++;
		var t = counter;
		for (var i = 0; i < (counter - 1); i++) {
			removeFromMusicalChairs(StdRandom.uniform(t), t);
			t--;
			sort();
		}
		seatStudents();
	}

	private void removeFromMusicalChairs(int x, int size) {
		if (size == 1) return;
		var previous = new SNode();
		var current = new SNode();
		int counter = 0;
		current = musicalChairs.getNext();
		previous = musicalChairs;
		if (x == 0) {
			musicalChairs.setNext(current.getNext());
			var student = current.getStudent();
			current = (studentsInLine == null) ? new SNode(student, null) : new SNode(student, studentsInLine);
			studentsInLine = current;
			return;
		} else if (x == (size - 1)) {
			while (current.getNext() != musicalChairs) {
				previous = current;
				current = current.getNext();
			}
			previous.setNext(musicalChairs);
			musicalChairs = previous;
			var student = current.getStudent();
			current = (studentsInLine == null) ? new SNode(student, null) : new SNode(student, studentsInLine);
			studentsInLine = current;
			return;
		}
		while (counter < x) {
			current = current.getNext();
			previous = previous.getNext();
			counter++;
		}
		if (x == size - 1) musicalChairs = previous;
		else previous.setNext(current.getNext());
		var student = current.getStudent();
		current = (studentsInLine == null) ? new SNode(student, null) : new SNode(student, studentsInLine);
		studentsInLine = current;
	}

	private void sort() {
		var current = studentsInLine;
		SNode index;
		Student temp;
		if (studentsInLine == null) return;
		else {
			while (current != null) {
				index = current.getNext();
				while (index != null) {
					if (current.getStudent().getHeight() > index.getStudent().getHeight()) {
						temp = current.getStudent();
						current.setStudent(index.getStudent());
						index.setStudent(temp);
					}
					index = index.getNext();
				}
				current = current.getNext();
			}
		}
	}

	/**
	 * Insert a student to wherever the students are at (i.e. whatever activity is not empty)
	 * <p>
	 * Note: adds to the end of either linked list or the next available empty seat
	 *
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param height    the height of the student
	 */
	public void addLateStudent(String firstName, String lastName, int height) {
		var student = new Student(firstName, lastName, height);
		if (studentsInLine != null) {
			var current = studentsInLine;
			while (current.getNext() != null) current = current.getNext();
			current.setNext(new SNode(student, null));
		} else if (musicalChairs != null) {
			musicalChairs.setNext(new SNode(student, musicalChairs.getNext()));
			musicalChairs = musicalChairs.getNext();
		} else seatStudent(student);
	}

	/**
	 * A student decides to leave early
	 * <p>
	 * This method deletes an early-leaving student from wherever the students
	 * are at (i.e. whatever activity is not empty)
	 * <p>
	 * Assume the student's name is unique
	 *
	 * @param firstName the student's first name
	 * @param lastName  the student's last name
	 */
	public void deleteLeavingStudent(String firstName, String lastName) {
		if (studentsInLine != null) {
			boolean exists = false;
			var previous = new SNode();
			var current = new SNode();
			if (studentsInLine.getStudent().compareNameTo(new Student(firstName, lastName, 0)) == 0) {
				exists = true;
				studentsInLine = studentsInLine.getNext();
				return;
			}
			for (previous = studentsInLine; previous.getNext() != null; previous = previous.getNext())
				if (previous.getNext().getStudent().compareNameTo(new Student(firstName, lastName, 0)) == 0) {
					exists = true;
					current = previous.getNext();
					break;
				}
			if (exists) previous.setNext(current.getNext());
		} else if (musicalChairs != null) {
			boolean exists = false;
			var current = musicalChairs.getNext();
			var previous = musicalChairs;
			if (musicalChairs.getStudent().compareNameTo(new Student(firstName, lastName, 0)) == 0) {
				exists = true;
				while (current != musicalChairs) {
					current = current.getNext();
					previous = previous.getNext();
				}
				previous.setNext(current.getNext());
				musicalChairs = previous;
				return;
			}
			while (current != musicalChairs) {
				if (current.getStudent().compareNameTo(new Student(firstName, lastName, 0)) == 0) {
					exists = true;
					break;
				}
				current = current.getNext();
				previous = previous.getNext();
			}
			if (exists) previous.setNext(current.getNext());
		} else for (var i = 0; i < studentsSitting.length; i++)
			for (var j = 0; j < studentsSitting[i].length; j++)
				if ((studentsSitting[i][j] != null) && (studentsSitting[i][j].compareNameTo(new Student(firstName, lastName, 0)) == 0)) {
					studentsSitting[i][j] = null;
					return;
				}
	}

	/**
	 * Used by driver to display students in line.
	 * <p>
	 * <em><b>DO NOT EDIT</b></em>
	 */
	public void printStudentsInLine() {
		//Print studentsInLine
		StdOut.println("Students in Line:");
		if (studentsInLine == null) StdOut.println("EMPTY");
		for (var ptr = studentsInLine; ptr != null; ptr = ptr.getNext()) {
			StdOut.print(ptr.getStudent().print());
			if (ptr.getNext() != null) StdOut.print(" -> ");
		}
		StdOut.println();
		StdOut.println();
	}

	/**
	 * Prints the seated students; can use this method to debug.
	 * <p>
	 * <em><b>DO NOT EDIT</b></em>
	 */
	public void printSeatedStudents() {
		StdOut.println("Sitting Students:");
		if (studentsSitting != null) for (var i = 0; i < studentsSitting.length; i++) {
			for (var j = 0; j < studentsSitting[i].length; j++) {
				var stringToPrint = (studentsSitting[i][j] == null) ? (!seatingAvailability[i][j] ? "X" : "EMPTY") : studentsSitting[i][j].print();
				StdOut.print(stringToPrint);
				for (int k = 0; k < (10 - stringToPrint.length()); k++) StdOut.print(" ");
			}
			StdOut.println();
		}
		else StdOut.println("EMPTY");
		StdOut.println();
	}

	/**
	 * Prints the musical chairs; can use this method to debug.
	 * <p>
	 * <em><b>DO NOT EDIT</b></em>
	 */
	public void printMusicalChairs() {
		StdOut.println("Students in Musical Chairs:");
		if (musicalChairs == null) {
			StdOut.println("EMPTY");
			StdOut.println();
			return;
		}
		for (var ptr = musicalChairs.getNext(); (ptr != musicalChairs) && (ptr != null); ptr = ptr.getNext())
			StdOut.print(ptr.getStudent().print() + " -> ");
		StdOut.print(musicalChairs.getStudent().print() + " - POINTS TO FRONT");
		StdOut.println();
	}

	/**
	 * Prints the state of the classroom; can use this method to debug.
	 * <p>
	 * <em><b>DO NOT EDIT</b></em>
	 */
	public void printClassroom() {
		printStudentsInLine();
		printSeatedStudents();
		printMusicalChairs();
	}

	/**
	 * Used to get and set objects.
	 * <p>
	 * <em><b>DO NOT EDIT</b></em>
	 */

	public SNode getStudentsInLine() {
		return studentsInLine;
	}

	public void setStudentsInLine(SNode studentsInLine) {
		this.studentsInLine = studentsInLine;
	}

	public SNode getMusicalChairs() {
		return musicalChairs;
	}

	public void setMusicalChairs(SNode musicalChairs) {
		this.musicalChairs = musicalChairs;
	}

	public boolean[][] getSeatingAvailability() {
		return seatingAvailability;
	}

	public void setSeatingAvailability(boolean[][] seatingAvailability) {
		this.seatingAvailability = seatingAvailability;
	}

	public Student[][] getStudentsSitting() {
		return studentsSitting;
	}

	public void setStudentsSitting(Student[][] studentsSitting) {
		this.studentsSitting = studentsSitting;
	}
}
