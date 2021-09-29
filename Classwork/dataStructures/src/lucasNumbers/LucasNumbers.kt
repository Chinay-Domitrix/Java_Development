package lucasNumbers

import java.io.File
import java.io.FileNotFoundException
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.TWO
import java.util.*
import java.util.Comparator.naturalOrder

fun main() = try {
	Scanner(File("Classwork/dataStructures/src/lucasNumbers/LucasNumbers.txt")).use {
		val indices = ArrayList<Int>()
		val sequence = ArrayList<BigInteger>()
		sequence += TWO
		sequence += ONE
		while (it.hasNextLine()) indices += it.nextLine().toInt()
		indices.sortWith(naturalOrder())
		(2..indices[indices.size - 1]).forEach { _ -> sequence += sequence[sequence.size - 2].add(sequence[sequence.size - 1]) }
		indices.forEach { index -> println(sequence[index]) }
	}
} catch (e: FileNotFoundException) {
	e.printStackTrace()
}
