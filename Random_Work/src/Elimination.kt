fun main() {
	val x = ArrayList<String?>()
	x += null
	(1..2000).forEach { x[it] = it.toString() }
	out@ while (true) {
		var i = 2
		while (i <= 2000) {
			x.removeAt(i)
			if (x.size == 2) break@out
			i += 2
		}
		i = 1
		while (i <= 2000) {
			x.removeAt(i)
			if (x.size == 2) break@out
			i += 2
		}
	}
	println(x[1])
}
