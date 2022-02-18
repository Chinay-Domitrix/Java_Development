package dataStructures.stack

import java.lang.System.`in`
import java.util.*

fun main() {
	print("Enter a number in decimal: ")
	val stack = Stack<Int>()
	var n = Scanner(`in`).nextLine().toInt()
	print("The binary representation of $n is ")
	while (n != 0) {
		stack.push(n % 2)
		n /= 2
	}
	while (!stack.isEmpty()) print(stack.pop())
	println()
}
