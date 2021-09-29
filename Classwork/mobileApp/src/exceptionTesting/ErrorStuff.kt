package exceptionTesting

object ErrorStuff {
	@JvmStatic
	fun main(args: Array<String>) {
		method()
	}

	@Throws(ArithmeticException::class)
	fun method() {
		throw ArithmeticException()
	}
}