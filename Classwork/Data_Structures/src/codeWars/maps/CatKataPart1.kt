package codeWars.maps

import kotlin.math.hypot

fun peacefulYard(yard: Array<String>, minDistance: Int): Boolean {
	val cats = ArrayList<IntArray>()
	for (row in yard.indices) (0 until yard[row].length).asSequence().filter { yard[row][it] != '-' }
		.mapTo(cats) { intArrayOf(row, it) }
	for (i in 0 until (cats.size - 1)) for (j in (i + 1) until cats.size) if (hypot(
			(cats[i][0] - cats[j][0]).toDouble(), (cats[i][1] - cats[j][1]).toDouble()
		) < minDistance
	) return false
	return true
}
