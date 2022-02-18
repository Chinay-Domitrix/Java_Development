package dataStructures.queue.cars

import java.io.File
import java.util.*
import java.util.LinkedList as LinList
import java.util.PriorityQueue as PQueue
import kotlin.io.println as ln

fun main() {
	val scanner = Scanner(File("Classwork/dataStructures/src/queue/cars/CarData.tsv")).also { it.nextLine() }
	val carsLinkedList = LinList<Car>().apply {
		while (scanner.hasNextLine()) with(scanner.nextLine().split("\t")) {
			this@apply += Car(
				this[0].toInt(),
				this[1].toInt(),
				this[2].toInt(),
				this[3].toInt(),
				this[4].toInt(),
				this[5].toInt(),
				this[6].toInt(),
				this[7].toInt()
			)
		}
	}
	ln(
		"LinkedList:%n%-24s%-24s%-24s%-24s%-24s%-24s%-24s%-24s".format(
			"ID",
			"MPG",
			"Engine Size",
			"Horsepower",
			"Weight",
			"Acceleration",
			"Country of Origin",
			"Cylinders"
		)
	)
	val carsStack = Stack<Car>().apply {
		while (!carsLinkedList.isEmpty()) with(carsLinkedList.poll()) {
			ln(this)
			this@apply += this
		}
	}
	ln(
		"%nStack:%n%-24s%-24s%-24s%-24s%-24s%-24s%-24s%-24s".format(
			"ID",
			"MPG",
			"Engine Size",
			"Horsepower",
			"Weight",
			"Acceleration",
			"Country of Origin",
			"Cylinders"
		)
	)
	val carsPriorityQueue = PQueue<Car>().apply {
		while (!carsStack.isEmpty()) with(carsStack.pop()) {
			ln(this)
			this@apply += this
		}
	}
	ln(
		"%nPriorityQueue:%n%-24s%-24s%-24s%-24s%-24s%-24s%-24s%-24s".format(
			"ID",
			"MPG",
			"Engine Size",
			"Horsepower",
			"Weight",
			"Acceleration",
			"Country of Origin",
			"Cylinders"
		)
	)
	while (!carsPriorityQueue.isEmpty()) ln(carsPriorityQueue.poll())
}

data class Car(
	val carID: Int,
	val mpg: Int,
	val engineSize: Int,
	val horsepower: Int,
	val weight: Int,
	val acceleration: Int,
	val countryOfOrigin: Int,
	val cylinders: Int
) : Comparable<Car> {
	override fun compareTo(other: Car) = when {
		this.acceleration != other.acceleration -> this.acceleration.compareTo(other.acceleration)
		this.mpg != other.mpg -> this.mpg.compareTo(other.mpg)
		this.horsepower != other.horsepower -> this.horsepower.compareTo(other.horsepower)
		this.engineSize != other.engineSize -> this.engineSize.compareTo(other.engineSize)
		this.weight != other.weight -> this.weight.compareTo(other.weight)
		this.cylinders != other.cylinders -> this.cylinders.compareTo(other.cylinders)
		else -> this.carID.compareTo(other.carID)
	}

	override fun toString() = "%-24s%-24s%-24s%-24s%-24s%-24s%-24s%-24s".format(
		carID,
		mpg,
		engineSize,
		horsepower,
		weight,
		acceleration,
		countryOfOrigin,
		cylinders
	)
}
