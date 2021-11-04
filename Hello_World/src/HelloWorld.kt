import java.lang.Thread.sleep

internal object HelloWorld {
	@Throws(InterruptedException::class)
	@JvmStatic
	fun main(args: Array<String>) {
		val var1 = "Hello world!"
		val var2 = "My name is Chirag."
		val var3 = "This just happens to be my first Java project."
		println(var1)
		sleep(1250)
		println(var2)
		sleep(1250)
		println(var3)
		sleep(1250)
		for (a in var3.indices) {
			print(var1[a])
			sleep(150)
		}
		sleep(450)
		println()
		for (a in var3.indices) {
			print(var2[a])
			sleep(150)
		}
		sleep(450)
		println()
		for (element in var3) {
			print(element)
			sleep(150)
		}
	}
}