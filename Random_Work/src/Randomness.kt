import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
	val a = goldenFib()
	println("The number is $a")
	var b: Int = 0
	while (a != goldenFib(b)) b++
	println("The index is ${b + 1}")
}

fun goldenFib(): Int {
	val random = Random().nextInt(100)
	return ((((1 + sqrt(5.0)) / 2).pow(random) - ((1 + sqrt(5.0)) / 2).pow(-random)) / sqrt(5.0)).toInt()
}

fun goldenFib(index: Int) = ((((1 + sqrt(5.0)) / 2).pow(index) - ((1 + sqrt(5.0)) / 2).pow(-index)) / sqrt(5.0)).toInt()
