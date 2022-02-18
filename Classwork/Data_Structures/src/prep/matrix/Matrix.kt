package prep.matrix

import java.io.File
import java.io.FileNotFoundException
import java.lang.System.err
import java.util.*
import java.util.Arrays.stream

fun main() = try {
	Scanner(File("Classwork/dataStructures/src/prep/matrix/MatrixInput.txt")).use { fileReader ->
		while (fileReader.hasNextLine()) {
			val matrixStrings = fileReader.nextLine().split("\t").toTypedArray()
			val array1 = parseArray(matrixStrings[0])
			val array2 = parseArray(matrixStrings[1])
			println("Array 1:")
			array1.forEach { println(stringifyMatrix(it)) }
			println("Array 2:")
			array2.forEach { println(stringifyMatrix(it)) }
			if (array1.size != array2.size || array1[0].size != array2[0].size) err.println("The sum and difference are not possible") else {
				println("Sum:")
				add(array1, array2).forEach { println(stringifyMatrix(it)) }
				println("Difference:")
				subtract(array1, array2).forEach { println(stringifyMatrix(it)) }
			}
			if (array1[0].size != array2.size) err.println("Multiplication is not possible.") else {
				println("Product:")
				multiply(array1, array2).forEach { println(stringifyMatrix(it)) }
			}
		}
	}
} catch (e: FileNotFoundException) {
	e.printStackTrace()
}

private fun parseArray(input: String): Array<IntArray> {
	val rows =
		StringBuilder(input).deleteCharAt(input.length - 1).deleteCharAt(0).toString().replace("},".toRegex(), "}/")
			.split("/".toRegex()).toTypedArray()
	val intArrayList = ArrayList<IntArray>()
	rows.forEach {
		intArrayList += stream(
			StringBuilder(it).deleteCharAt(it.length - 1).deleteCharAt(0).toString().split(",".toRegex()).toTypedArray()
		).mapToInt(String::toInt).toArray()
	}
	return intArrayList.toArray(Array(0) { IntArray(0) })
}

private fun add(array1: Array<IntArray>, array2: Array<IntArray>): Array<IntArray> {
	val temp = Array(array1.size) { IntArray(array1[0].size) }
	array1.indices.forEach { i -> (0 until array1[0].size).forEach { temp[i][it] = array1[i][it] + array2[i][it] } }
	return temp
}

private fun subtract(array1: Array<IntArray>, array2: Array<IntArray>): Array<IntArray> {
	val temp = Array(array1.size) { IntArray(array1[0].size) }
	array1.indices.forEach { i -> (0 until array1[0].size).forEach { temp[i][it] = array1[i][it] - array2[i][it] } }
	return temp
}

private fun multiply(array1: Array<IntArray>, array2: Array<IntArray>): Array<IntArray> {
	val temp = Array(array1.size) { IntArray(array2[0].size) }
	temp.indices.forEach { i -> (0 until temp[0].size).forEach { j -> array2.indices.forEach { temp[i][j] += array1[i][it] * array2[it][j] } } }
	return temp
}

private fun stringifyMatrix(array: IntArray) =
	array.contentToString().replace(",".toRegex(), "").replace("[", "").replace("]", "")
