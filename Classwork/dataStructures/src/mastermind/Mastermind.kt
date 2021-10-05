package mastermind

import java.io.File
import java.io.FileNotFoundException
import java.util.*

fun main() = try {
	Scanner(File("Classwork/dataStructures/src/mastermind/Mastermind.txt")).use { fileReader ->
		val dataSet = ArrayList<CharArray>()
		while (fileReader.hasNextLine()) dataSet.add(fileReader.nextLine().toCharArray())
		val key = arrayOf(dataSet[0], dataSet[0].contentToString())
		for (i in 1 until dataSet.size) {
			val current = dataSet[i]
			var matchT1 = 0
			val failT1 = ArrayList<Int>()
			var matchT2 = 0
			var failT2 = 0
			current.indices.forEach { if ((key[0] as CharArray)[it] == current[it]) matchT1++ else failT1 += it }
			val key2 = ArrayList<Char>()
			val current2 = ArrayList<Char>()
			for (j in failT1) {
				key2.add((key[0] as CharArray)[j])
				current2.add(current[j])
			}
			current2.forEach {
				if (key2.contains(it)) {
					matchT2++
					key2.remove(it)
				} else failT2++
			}
			println(
				"Code:\t${
					(key[1] as String).replace(", ".toRegex(), "").replace("[", "").replace("]", "")
				}\nGuess:\t${
					current.contentToString().replace(", ".toRegex(), "").replace("[", "").replace("]", "")
				}\n\tCorrect Guess - Placement Correct:\t$matchT1\n\tCorrect Guess - Incorrectly Placed:\t$matchT2\n\tIncorrect Guesses:\t\t\t$failT2"
			)
		}
	}
} catch (e: FileNotFoundException) {
	e.printStackTrace()
}
