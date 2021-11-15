package stack.starWarsCharacters

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvToBeanBuilder
import java.io.FileNotFoundException
import java.io.FileReader
import java.util.*

fun main() {
	try {
		val maleCharacters = Stack<Character>()
		val femaleCharacters = Stack<Character>()
		val droids = Stack<Character>()
		val validBirthYears = Stack<Character>()
		CsvToBeanBuilder<Character>(FileReader("Classwork/dataStructures/src/stack/starWarsCharacters/StarWarsCharacters.csv")).withType(
			Character::class.java
		).build().parse().forEach {
			if (it.gender.equals("Male", ignoreCase = true)) maleCharacters.push(it)
			if (it.gender.equals("Female", ignoreCase = true)) femaleCharacters.push(it)
			if (it.species.equals("Droid", ignoreCase = true)) droids.push(it)
			if (!it.birthYear.equals("NA", ignoreCase = true)) validBirthYears.push(it)
		}
		println("Male Characters\nName\t\t\t\t\tHomeworld")
		maleCharacters.forEach { println("${it.name}\t\t\t${it.homeWorld}") }
		println("Female Characters\nName\t\t\t\t\tHomeworld")
		femaleCharacters.forEach { println("${it.name}\t\t\t${it.homeWorld}") }
		println("Droids\nName\t\t\t\t\tHomeworld")
		droids.forEach { println(it.name + "\t\t\t" + it.homeWorld) }
		println("Ages\nName\t\t\t\t\tHomeworld\t\t\t\t\tBirth Year")
		validBirthYears.forEach { println("${it.name}\t\t\t${it.homeWorld}\t\t\t${it.birthYear}") }
	} catch (e: FileNotFoundException) {
		e.printStackTrace()
	}
}

data class Character(
	@field:CsvBindByName(column = "name") val name: String,
	@field:CsvBindByName(column = "birth_year") val birthYear: String,
	@field:CsvBindByName(column = "gender") val gender: String,
	@field:CsvBindByName(column = "homeworld") val homeWorld: String,
	@field:CsvBindByName(column = "species") val species: String
)