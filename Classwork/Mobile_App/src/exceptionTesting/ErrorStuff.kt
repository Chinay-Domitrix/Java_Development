package exceptionTesting

fun main() {
	method()
}

@Throws(ArithmeticException::class)
fun method() {
	throw ArithmeticException()
}
