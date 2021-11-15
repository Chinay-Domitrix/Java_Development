package stack

import java.lang.System.`in`
import java.util.*

fun main() {
	val stack = Stack<Char>()
	print("Enter a string: ")
	val str = Scanner(`in`).nextLine()
	str.forEach { stack.push(it) }
	var reversed = ""
	while (!stack.isEmpty()) reversed += stack.pop()
	println("Reversed string: $reversed")
}