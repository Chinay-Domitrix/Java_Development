package dataStructures.stack.starWarsCharacters

import java.io.File
import java.io.FileNotFoundException
import java.lang.System.out
import java.util.*

fun main() {
	try {
		val maleCharacters = Stack<Character>()
		val femaleCharacters = Stack<Character>()
		val droids = Stack<Character>()
		val validBirthYears = Stack<Character>()
		val scanner = Scanner(File("Classwork/dataStructures/src/stack/starWarsCharacters/StarWarsCharacters.csv"))
		scanner.nextLine()
		ArrayList<Character>().apply {
			while (scanner.hasNextLine()) with(
				scanner.nextLine().split(Regex(",(?=(?:(?:[^\"]*\"){2})*[^\"]*\$)"))
					.apply { forEach { it.replace("\"", "") } }) {
				this@apply += Character(
					this[0],
					this[5],
					this[6],
					this[7],
					this[8]
				)
			}
		}.forEach {
			if (it.gender.equals("Male", true)) maleCharacters.push(it)
			if (it.gender.equals("Female", true)) femaleCharacters.push(it)
			if (it.species.equals("Droid", true)) droids.push(it)
			if (!it.birthYear.equals("NA", true)) validBirthYears.push(it)
		}
		out.printf("Male Characters%n%-32s%-32s%n", "Name", "Homeworld")
		while (!maleCharacters.empty()) with(maleCharacters.pop()) {
			if (!homeWorld.equals(
					"NA",
					true
				)
			) out.printf("%-32s%-32s%n", name, homeWorld) else out.printf("%-32s%-32s%n", name, "N/A")
		}
		out.printf("%nFemale Characters%n%-32s%-32s%n", "Name", "Homeworld")
		while (!femaleCharacters.empty()) with(femaleCharacters.pop()) {
			if (!homeWorld.equals(
					"NA",
					true
				)
			) out.printf("%-32s%-32s%n", name, homeWorld) else out.printf("%-32s%-32s%n", name, "N/A")
		}
		out.printf("%nDroids%n%-32s%-32s%n", "Name", "Homeworld")
		while (!droids.empty()) with(droids.pop()) {
			if (!homeWorld.equals("NA", true)) out.printf(
				"%-32s%-32s%n",
				name,
				homeWorld
			) else out.printf("%-32s%-32s%n", name, "N/A")
		}
		out.printf("%nAges%n%-32s%-32s%-32s%n", "Name", "Homeworld", "Birth Year")
		while (!validBirthYears.empty()) with(validBirthYears.pop()) {
			if (!homeWorld.equals(
					"NA",
					true
				)
			) out.printf("%-32s%-32s%-32s%n", name, homeWorld, birthYear) else out.printf(
				"%-32s%-32s%-32s%n",
				name,
				"N/A",
				birthYear
			)
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
