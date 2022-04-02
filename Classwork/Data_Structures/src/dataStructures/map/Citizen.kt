package dataStructures.map

import java.io.File
import java.lang.System.err
import java.util.*

class Citizen(
	val firstName: String,
	val lastName: String,
	val street: String,
	streetNumber: String,
	val relation: String,
	val rentOrOwn: String,
	propertyValue: String,
	val sex: String,
	age: String,
	val maritalStatus: String,
	ageAtFirstMarriage: String,
	attendSchool: String,
	canRead: String,
	val birthplace: String,
	val fatherBirthplace: String,
	val motherBirthplace: String,
	val motherTongue: String,
	yearImmigrated: String,
	val occupation: String,
	val industry: String,
	val transcriberRemarks: String
) : Comparable<Citizen> {
	private var streetNumber = try {
		streetNumber.toInt()
	} catch (e: NumberFormatException) {
		-1
	}
	var propertyValue = 0.0
	var age = try {
		age.toDouble()
	} catch (e: NumberFormatException) {
		when {
			(age == "") || (age[0] == '.') || (age == "un") -> -1.0
			(age[1] == ' ') && ("/" in age) -> age.substring(0 until age.indexOf(" ")).toDouble() + when {
				"*" in age.substring((age.indexOf(" ") + 1) until age.indexOf("/")) -> 0.5
				else -> try {
					age.substring(age.indexOf(" ") + 1 until age.indexOf("/"))
						.toDouble() / age.substring(age.indexOf("/") + 1).toDouble()
				} catch (e: NumberFormatException) {
					-1.0
				}
			}
			"*" in age -> age.substring(0, age.indexOf("*")).toDouble()
			else -> age.substring(0, age.indexOf("/")).toDouble() / age.substring(age.indexOf("/") + 1).toDouble()
		}
	}
	private var ageAtFirstMarriage = try {
		ageAtFirstMarriage.toInt()
	} catch (e: NumberFormatException) {
		-1
	}
	private var attendSchool = attendSchool == "Yes"
	private var canRead = canRead == "Yes"
	private var yearImmigrated = try {
		yearImmigrated.toInt()
	} catch (e: NumberFormatException) {
		-1
	}

	init {
		var tempPropertyValue = propertyValue
		if ('$' in tempPropertyValue) tempPropertyValue = tempPropertyValue.substring(1)
		try {
			this.propertyValue = tempPropertyValue.toDouble()
		} catch (e: NumberFormatException) {
			if ('/' in tempPropertyValue) this.propertyValue = with(tempPropertyValue) {
				substring(0 until indexOf(' ')).toDouble() + (substring(
					indexOf(' ') + 1 until indexOf('/')
				).toDouble() / substring(indexOf('/') + 1).toDouble())
			}
		}
	}

	override fun compareTo(other: Citizen) = when {
		firstName != other.firstName -> firstName.compareTo(other.firstName)
		lastName != other.lastName -> lastName.compareTo(other.lastName)
		street != other.street -> street.compareTo(other.street)
		streetNumber != other.streetNumber -> streetNumber.compareTo(other.streetNumber)
		relation != other.relation -> relation.compareTo(other.relation)
		rentOrOwn != other.rentOrOwn -> rentOrOwn.compareTo(other.rentOrOwn)
		propertyValue != other.propertyValue -> propertyValue.compareTo(other.propertyValue)
		sex != other.sex -> sex.compareTo(other.sex)
		age != other.age -> age.compareTo(other.age)
		maritalStatus != other.maritalStatus -> maritalStatus.compareTo(other.maritalStatus)
		ageAtFirstMarriage != other.ageAtFirstMarriage -> ageAtFirstMarriage.compareTo(other.ageAtFirstMarriage)
		attendSchool != other.attendSchool -> attendSchool.compareTo(other.attendSchool)
		canRead != other.canRead -> canRead.compareTo(other.canRead)
		birthplace != other.birthplace -> birthplace.compareTo(other.birthplace)
		fatherBirthplace != other.fatherBirthplace -> fatherBirthplace.compareTo(other.fatherBirthplace)
		motherBirthplace != other.motherBirthplace -> motherBirthplace.compareTo(other.motherBirthplace)
		motherTongue != other.motherTongue -> motherTongue.compareTo(other.motherTongue)
		yearImmigrated != other.yearImmigrated -> yearImmigrated.compareTo(other.yearImmigrated)
		occupation != other.occupation -> occupation.compareTo(other.occupation)
		industry != other.industry -> industry.compareTo(other.industry)
		transcriberRemarks != other.transcriberRemarks -> transcriberRemarks.compareTo(other.transcriberRemarks)
		else -> 0
	}

	override fun equals(other: Any?) =
		if (this === other) true else if (javaClass != other?.javaClass) false else with(other as Citizen) { !(((firstName != other.firstName) || (lastName != other.lastName) || (street != other.street) || (streetNumber != other.streetNumber) || (relation != other.relation) || (rentOrOwn != other.rentOrOwn) || (propertyValue != other.propertyValue) || (sex != other.sex) || (age != other.age) || (maritalStatus != other.maritalStatus) || (ageAtFirstMarriage != other.ageAtFirstMarriage) || (canRead != other.canRead) || (birthplace != other.birthplace) || (fatherBirthplace != other.fatherBirthplace) || (motherBirthplace != other.motherBirthplace) || (motherTongue != other.motherTongue) || (yearImmigrated != other.yearImmigrated) || (occupation != other.occupation) || (industry != other.industry) || (transcriberRemarks != other.transcriberRemarks))) }

	override fun hashCode() =
		((((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * firstName.hashCode()) + lastName.hashCode())) + street.hashCode())) + streetNumber.hashCode())) + relation.hashCode())) + rentOrOwn.hashCode())) + propertyValue.hashCode())) + sex.hashCode())) + age.hashCode())) + maritalStatus.hashCode())) + ageAtFirstMarriage.hashCode())) + attendSchool.hashCode())) + canRead.hashCode())) + birthplace.hashCode())) + fatherBirthplace.hashCode())) + motherBirthplace.hashCode())) + motherTongue.hashCode())) + yearImmigrated.hashCode())) + occupation.hashCode())) + industry.hashCode())) + transcriberRemarks.hashCode())))

	override fun toString() =
		"Citizen(firstName='$firstName', lastName='$lastName', street='$street', relation='$relation', rentOrOwn='$rentOrOwn', sex='$sex', maritalStatus='$maritalStatus', birthplace='$birthplace', fatherBirthplace='$fatherBirthplace', motherBirthplace='$motherBirthplace', motherTongue='$motherTongue', industry='$industry', transcriberRemarks='$transcriberRemarks', streetNumber=$streetNumber, propertyValue=$propertyValue, age=$age, ageAtFirstMarriage=$ageAtFirstMarriage, attendSchool=$attendSchool, canRead=$canRead, yearImmigrated=$yearImmigrated, occupation='$occupation')"
}

val citizens by lazy { ArrayList<Citizen>() }
val citizensByStreet by lazy { TreeMap<String, TreeSet<Citizen>>() }
val citizensByBirthplace by lazy { TreeMap<String, PriorityQueue<Double>>() }
val citizensByMotherTongue by lazy { TreeMap<String, ArrayList<String>>() }
val citizensByOccupation by lazy { TreeMap<String, HashSet<String>>() }
val citizensBySex by lazy { TreeMap<String, HashSet<String>>() }
val citizensByRentOrOwn by lazy { TreeMap<String, TreeSet<Double>>() }
val citizensInsights by lazy { TreeMap<String, TreeMap<String, String>>() }
val reader by lazy { Scanner(File("C:\\Users\\chira\\OneDrive\\Documents\\Programming\\Java_Development\\Classwork\\Data_Structures\\src\\dataStructures\\map\\Data.tsv")).also { it.nextLine() } }

fun main() {
	mutableListOf<MutableList<String>>().apply {
		var x = 0
		while (reader.hasNextLine()) this += reader.nextLine().split('\t').toMutableList().apply {
			(0 until this.size).asSequence().filter { this[it] == "\".\"" }.forEach { this[it] = "" }
				.also { (0 until this.size).asSequence().filter { this[it] == "\"\"" }.forEach { this[it] = "" } }
		}.also { i -> x++.also { if (i.size != 34) err.println("Error: Row $it has ${i.size} columns") } }
		for (params in this) {
			for (i in listOf(5, 4, 0, 1, 6, 7, 8, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 25, 26, 33)) params[i] =
				params[i].replace("\"", "")
			citizens += Citizen(
				params[5],
				params[4],
				params[0],
				params[1],
				params[6],
				params[7],
				params[8],
				params[11],
				params[13],
				params[14],
				params[15],
				params[16],
				params[17],
				params[18],
				params[19],
				params[20],
				params[21],
				params[22],
				params[25],
				params[26],
				params[33]
			)
		}
	}.apply {
		for (citizen in citizens) {
			citizensByStreet.getOrPut(citizen.street, ::TreeSet) += citizen
			citizensByBirthplace.getOrPut(citizen.birthplace, ::PriorityQueue) += citizen.age
			citizensByMotherTongue.getOrPut(
				citizen.motherTongue, ::ArrayList
			) += "${citizen.lastName}, ${citizen.firstName}"
			citizensByOccupation.getOrPut(citizen.occupation, ::HashSet) += citizen.fatherBirthplace
			citizensBySex.getOrPut(citizen.sex, ::HashSet) += citizen.transcriberRemarks
			citizensByRentOrOwn.getOrPut(citizen.rentOrOwn, ::TreeSet) += citizen.propertyValue
			citizensInsights.getOrPut(citizen.sex, ::TreeMap) += citizen.industry to citizen.occupation
		}
		println("By Street")
		for ((_, citizens) in citizensByStreet) for (citizen in citizens) println(
			"\t%-32s%s".format(
				"${citizen.lastName}, ${citizen.firstName}", citizen.street
			)
		)
		println("By Birthplace")
		for ((birthplace, citizenAges) in citizensByBirthplace) if (birthplace == "Pennsylvania") "\t%-32s%d".format(
			birthplace, citizenAges.size
		) else for (citizenAge in citizenAges) println("\t%-32s%f".format(birthplace, citizenAge.toFloat()))
		println("\nBy Mother Tongue")
		for ((language, citizens) in citizensByMotherTongue) println("\t%-32s%d".format(language, citizens.size))
		println("\nBy Occupation")
		for ((occupation, fatherBirthplaces) in citizensByOccupation) for (fatherBirthplace in fatherBirthplaces) println(
			"\t%-32s%s".format(occupation, fatherBirthplace)
		)
		println("\nBy Sex")
		for ((sex, remarks) in citizensBySex) for (remark in remarks) println("\t%-32s%s".format(sex, remark))
		println("\nBy Rent or Own")
		for ((rentOrOwn, propertyValues) in citizensByRentOrOwn) for (propertyValue in propertyValues) println(
			"\t%-32s%s".format(
				rentOrOwn, propertyValue
			)
		)
		println("\nCitizens Insights")
		for ((sex, treeMap) in citizensInsights) for ((industry, occupation) in treeMap) println(
			"\t%-32s%-32s%s".format(
				sex, industry, occupation
			)
		)
	}
}
