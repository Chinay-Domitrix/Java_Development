package queue.planes

import java.io.File
import java.util.*

/*
Create Passenger object class with following methods:
	- getLastName() returns last name of passenger
	- getFirstName() returns first name of passenger
	- flightCity() returns city passenger is flying to
	- flightTime() returns time passengers plane is scheduled to depart
	- etdCalc() returns number of hours and minutes until flight departure
	- compareTo() – priority is based on smallest etdCalc() values
		- However, if etdCalc() is more than 1 hour, passengers are added to list as a normal queue (there is no priority)
*/
data class Passenger(val lastName: String, val firstName: String, val flightCity: String, val flightTime: String) :
	Comparable<Passenger> {
	override fun compareTo(other: Passenger) = etdCalc().compareTo(other.etdCalc())
	override fun toString() = "%-32s%-32s%-32s%-32s".format("$lastName, $firstName", flightCity, flightTime, etdCalc())
}

fun Passenger.etdCalc() = parseFlightTime() - with(
	currentTime.substring(0, currentTime.indexOf(' ')).split(':')
) { (this[0].toInt() + (if (currentTime.contains("PM") && !currentTime.contains("12")) 12 else 0)) * 60 + this[1].toInt() }

fun Passenger.parseFlightTime() = with(
	flightTime.substring(0, flightTime.indexOf(' ')).split(':')
) { (this[0].toInt() + (if (flightTime.contains("PM") && !flightTime.contains("12")) 12 else 0)) * 60 + this[1].toInt() }

const val currentTime = "9:03 AM"
fun parseName(name: String) = name.split(' ')
fun main() {
//	val hold = mutableListOf<Passenger>()
	val scanner = Scanner(File("Classwork/dataStructures/src/queue/planes/PassengerInfo.tsv"))
	println(
		"Queue:%n%-32s%-32s%-32s%-32s".format(
			"Last Name, First Name",
			"Flight City",
			"Flight Time",
			"ETD (minutes)"
		)
	)
	val passengerQueue = LinkedList<Passenger>().apply {
		while (scanner.hasNextLine()) {
			val line = scanner.nextLine().split('\t')
			val (firstName, lastName) = parseName(line[0])
			add(Passenger(lastName, firstName, line[1], line[2]))
		}
	}.also { it.forEach(::println) }
	println(
		"%nPriorityQueue:%n%-32s%-32s%-32s%-32s".format(
			"Last Name, First Name",
			"Flight City",
			"Flight Time",
			"ETD (minutes)"
		)
	)
	PriorityQueue<Passenger>().apply {
		while (!passengerQueue.isEmpty()) if (passengerQueue.peek()
				.etdCalc() / 60 < 1
		) add(passengerQueue.poll()) else passengerQueue.poll()
	}.forEach(::println)
}
