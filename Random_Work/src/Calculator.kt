import java.lang.System.`in`
import java.lang.System.out
import java.lang.Thread.sleep
import java.util.*


private fun addition(num1: Double, num2: Double) {
	out.printf("The answer is: %s%n", num1 + num2)
}

private fun division(num1: Double, num2: Double) {
	out.printf("The answer is: %s%n", num1 / num2)
}

@Throws(InterruptedException::class, IllegalStateException::class)
fun main() {
	var counter = 1
	Scanner(`in`).use { calc ->
		println("Welcome to Calculator!")
		out.printf("Please enter your %dst number%n", counter)
		val one = calc.nextDouble()
		counter++
		out.printf("Please enter your %dnd number%n", counter)
		val two = calc.nextDouble()
		calc.nextLine()
		print("What is your operation? (Please type 'A' for Addition, 'S' for Subtraction, 'M' for Multiplication and 'D' for Division) ")
		val operation = calc.nextLine()[0]
		if (operation.toString().equals("A", true)) {
			sleep(1050)
			addition(one, two)
		} else if (operation.toString().equals("S", true)) {
			sleep(1050)
			subtraction(one, two)
		} else if (operation.toString().equals("M", true)) {
			sleep(1050)
			multiplication(one, two)
		} else if (operation.toString().equals("D", true)) {
			sleep(1050)
			division(one, two)
		} else throw IllegalStateException("Unexpected value: $operation")
	}
}

private fun multiplication(num1: Double, num2: Double) {
	out.printf("The answer is: %s%n", num1 * num2)
}

private fun subtraction(num1: Double, num2: Double) {
	out.printf("The answer is: %s%n", num1 - num2)
}
