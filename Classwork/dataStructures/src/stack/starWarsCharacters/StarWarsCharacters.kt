package stack.starWarsCharacters

import java.io.File
import java.io.FileNotFoundException
import java.lang.System.`in`
import java.util.*

fun main() {
	try {
		val maleCharacters = Stack<Character>()
		val femaleCharacters = Stack<Character>()
		val droids = Stack<Character>()
		val validBirthYears = Stack<Character>()
		val scanner = Scanner(File("Classwork/dataStructures/src/stack/starWarsCharacters/StarWarsCharacters.csv"))
		scanner.nextLine()
		ArrayList<Character>().also {
			while (scanner.hasNextLine()) {
				var list = scanner.nextLine().split(Regex("\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)\""))
				list = list.map { it.replace("\"", "") }
				println(list)
				it += Character(list[0], list[5], list[6], list[7], list[8])
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

 class Character(
	val name: String,
	val birthYear: String,
	val gender: String,
	val homeWorld: String,
	val species: String
)