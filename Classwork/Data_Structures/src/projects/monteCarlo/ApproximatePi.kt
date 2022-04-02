package projects.monteCarlo

import kotlin.Long.Companion.MAX_VALUE
import kotlin.random.Random.Default.nextDouble

fun main() {
	var x: Double
	var y: Double
	val iterations = MAX_VALUE
	var count = 0.0
	for (i in 1..iterations) {
		x = nextDouble()
		y = nextDouble()
		if (((x * x) + (y * y)) < 1.0) count++
	}
	println((4.0 * count) / iterations)
}
