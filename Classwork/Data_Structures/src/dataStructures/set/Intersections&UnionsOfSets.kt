package dataStructures.set

import kotlin.random.Random.Default.nextInt

fun main() {
	var currentIntersection = HashSet<Int>()
	var currentIteration = 0
	while (currentIntersection.isEmpty()) {
		if (currentIntersection.isEmpty() && (currentIteration > 0)) println()
		println("Attempt ${currentIteration + 1}")
		val sets = ArrayList<HashSet<Int>>()
		repeat((0 until nextInt(2, 13)).count()) {
			sets += HashSet<Int>().apply {
				repeat((1..10).count()) {
					this += nextInt(
						1, 30
					)
				}
			}
		}
		sets.withIndex().forEach { (index, set) -> println("Set ${index + 1} => $set") }
		fun intersection(sets: ArrayList<HashSet<Int>>, intersection: HashSet<Int>) =
			intersection.apply { sets.forEach(::retainAll) }
		println("Intersection => ${
			intersection(
				sets.subList(1, sets.size) as ArrayList<HashSet<Int>>, sets[0]
			).also { currentIntersection = it }
		}")
		fun union(sets: ArrayList<HashSet<Int>>) = HashSet<Int>().apply { sets.forEach(::addAll) }
		println("Union => ${union(sets)}")
		if (currentIntersection.isEmpty()) println("No intersection found!")
		currentIteration++
	}
}
