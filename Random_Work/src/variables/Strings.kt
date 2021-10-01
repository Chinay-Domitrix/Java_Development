package variables

internal object Strings {
	@JvmStatic
	fun main(args: Array<String>) {
//		Create a string with a constructor
		val s1 = "Who let the dogs out? "
//		Just using "" creates a string, so no need to write it the previous way.
		val s2 = "Who who who who!"
//		Java defined the operator + on strings to concatenate:
		val s3 = s1 + s2
		println(s3)
	}
}