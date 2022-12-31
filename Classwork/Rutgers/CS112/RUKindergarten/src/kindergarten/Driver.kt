package kindergarten

import kindergarten.StdIn.*
import kindergarten.StdOut.print
import kindergarten.StdOut.println
import kindergarten.StdRandom.setSeed

/**
 * This class is designed to test each method in the Classroom file interactively
 *
 * @author Ethan Chou
 * @author Maksims Kurjanovics Kravcenko
 * @author Kal Pandit
 */
fun main() {
	var controlChoice: Int
	var studentClassroom = Classroom()
	do {
		print("Enter a student info input file => ")
		val inputFile = readLine()
		do {
			println("\nWhat method would you like to test?")
			methods.indices.forEach { println("${(it + 1)}. ${methods[it]}") }
			print("Enter a number => ")
			when (readLine().toInt()) {
				1 -> {
					studentClassroom = testMakeClassroom(inputFile)
					studentClassroom.printClassroom()
				}

				2 -> {
					testSetupSeats(studentClassroom)
					studentClassroom.printClassroom()
				}

				3 -> {
					testSeatStudents(studentClassroom)
					studentClassroom.printClassroom()
				}

				4 -> {
					testInsertMusicalChairs(studentClassroom)
					studentClassroom.printClassroom()
				}

				5 -> {
					testPlayMusicalChairs(studentClassroom)
					studentClassroom.printClassroom()
				}

				6 -> {
					testAddStudent(studentClassroom)
					studentClassroom.printClassroom()
				}

				7 -> {
					testDeleteStudent(studentClassroom)
					studentClassroom.printClassroom()
				}

				else -> println("Not a valid option!")
			}
			resetFile()
			println("What would you like to do now?")
			(0..2).forEach {
				println(
					"${(it + 1)}. ${
						arrayOf(
							"Test a new input file", "Test another method on the same file", "Quit"
						)[it]
					}"
				)
			}
			print("Enter a number => ")
			controlChoice = readLine().toInt()
		} while (controlChoice == 2)
	} while (controlChoice == 1)
}

private fun testMakeClassroom(filename: String): Classroom {
	val studentClassroom = Classroom()
	studentClassroom.makeClassroom(filename)
	return studentClassroom
}

private fun testSetupSeats(studentClassroom: Classroom) {
	print("Enter a seating availability input file => ")
	studentClassroom.setupSeats(readLine())
}

private fun testSeatStudents(studentClassroom: Classroom) = studentClassroom.seatStudents()

private fun testInsertMusicalChairs(studentClassroom: Classroom) = studentClassroom.insertMusicalChairs()

private fun testPlayMusicalChairs(studentClassroom: Classroom) {
	setSeed(2022)
	println("Here is the classroom after a long game of musical chairs: ")
	studentClassroom.playMusicalChairs()
	println()
}

private fun testAddStudent(studentClassroom: Classroom) {
	print("\nWrite the student's first name -> ")
	val studentName = readString()
	print("\nWrite the student's last name -> ")
	val lastName = readString()
	print("\nWrite the student's height as a number -> ")
	val height = readInt()
	studentClassroom.addLateStudent(studentName, lastName, height)
}

private fun testDeleteStudent(studentClassroom: Classroom) {
	print("\nWrite the student's first name -> ")
	val firstName = readString()
	print("\nWrite the student's last name -> ")
	val lastName = readString()
	studentClassroom.deleteLeavingStudent(firstName, lastName)
}

private val methods = arrayOf(
	"makeClassroom",
	"setupSeats",
	"seatStudents",
	"insertMusicalChairs",
	"playMusicalChairs",
	"addLateStudent",
	"deleteLeavingStudent"
)
