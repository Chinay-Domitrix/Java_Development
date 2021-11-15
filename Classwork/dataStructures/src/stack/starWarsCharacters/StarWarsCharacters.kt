package stack.starWarsCharacters

import java.io.FileNotFoundException
import java.lang.System.`in`
import java.util.*

fun main() {
	try {
		val maleCharacters = Stack<Character>()
		val femaleCharacters = Stack<Character>()
		val droids = Stack<Character>()
		val validBirthYears = Stack<Character>()
		val scanner = Scanner(`in`)
		scanner.nextLine()
		ArrayList<Character>().also {
			while (scanner.hasNextLine()) {
				var mutableList = scanner.nextLine().split(",").toMutableList()
				mutableList.indices.forEach {
					if (mutableList[it].toCharArray()[0] == '\"') {
						mutableList[it] = mutableList[it].substring(
							1,
							mutableList[it].length - 1
						) + "," + mutableList[it + 1].substring(
							0,
							mutableList[it + 1].length - 2
						)
						mutableList.removeAt(it + 1)
					}
				}
				it += Character(mutableList[0], mutableList[5], mutableList[6], mutableList[7], mutableList[8])
			}
		}.forEach {
			if (it.gender.equals("Male", ignoreCase = true)) maleCharacters.push(it)
			if (it.gender.equals("Female", ignoreCase = true)) femaleCharacters.push(it)
			if (it.species.equals("Droid", ignoreCase = true)) droids.push(it)
			if (!it.birthYear.equals("NA", ignoreCase = true)) validBirthYears.push(it)
		}
		println("Male Characters\nName\t\t\t\t\tHomeworld")
		while (!maleCharacters.empty()) {
			val temp = maleCharacters.pop()
			println("${temp.name}\t\t\t${temp.homeWorld}")
		}
		println("Female Characters\nName\t\t\t\t\tHomeworld")
		while (!femaleCharacters.empty()) {
			val temp = femaleCharacters.pop()
			println("${temp.name}\t\t\t${temp.homeWorld}")
		}
		println("Droids\nName\t\t\t\t\tHomeworld")
		while (!droids.empty()) {
			val temp = droids.pop()
			println("${temp.name}\t\t\t${temp.homeWorld}")
		}
		println("Ages\nName\t\t\t\t\tHomeworld\t\t\t\t\tBirth Year")
		while (!validBirthYears.empty()) {
			val temp = validBirthYears.pop()
			println("${temp.name}\t\t\t${temp.homeWorld}\t\t\t\t\t${temp.birthYear}")
		}
	} catch (e: FileNotFoundException) {
		e.printStackTrace()
	}
}

data class Character(
	val name: String,
	val birthYear: String,
	val gender: String,
	val homeWorld: String,
	val species: String
)