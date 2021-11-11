package mazeAttempts.maze4

class Location(var r: Int, var c: Int) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		other as Location
		if (r != other.r) return false
		if (c != other.c) return false
		return true
	}

	override fun hashCode() = 31 * r + c
}