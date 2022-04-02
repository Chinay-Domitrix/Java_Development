package codeWars.maps

fun decomp(m: Int): String {
	var n = m
	val primePower = IntArray(n + 1)
	while (n > 1) {
		var x = n--
		var i = 2
		while (i * i <= x.toDouble()) {
			if (x % i == 0) {
				x /= i
				primePower[i]++
				i = 1
			}
			i++
		}
		primePower[x]++
	}
	var result = ""
	for (i in 2 until primePower.size) with(primePower[i]) { if (this != 0) result += if (this == 1) "$i * " else "$i^$this * " }
	return result.dropLast(3)
}
