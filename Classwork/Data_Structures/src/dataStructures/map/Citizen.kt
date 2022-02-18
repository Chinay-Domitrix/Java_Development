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
		e.printStackTrace()
		-1
	}
	var propertyValue = 0.0
	var age = 0.0
	private var ageAtFirstMarriage = 0
	private var attendSchool = false
	private var canRead: Boolean
	private var yearImmigrated = 0

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
		/*this.age = if ('/' !in age) age.trim().toDouble() else with(age.trim()) {
			if (this.isNotEmpty()) {
				try {
					if (' ' in this) with(this.split(' ')) {
						((this[0].toDouble() + with(
							this[1].split(
								'/'
							)
						) {
							this[0].toDouble() / this[1].toDouble()
						}) * 100).roundToInt() / 100.0
					}
					else with(this.split('/')) { (this[0].toDouble() / this[1].toDouble() * 100).roundToInt() / 100.0 }
				} catch (e: NumberFormatException) {
					e.printStackTrace()
					-1.0
				}
			} else -1.0
		}*/
		try {
			this.age = age.toDouble()
		} catch (e: NumberFormatException) {
			this.age = when {
				(age[0] == '.') || (age == "un") -> -1.0
				(age[1] == ' ') && ("/" in age) -> age.substring(0, age.indexOf(" ")).toDouble() + when {
					"*" in age.substring(age.indexOf(" ") + 1 until age.indexOf("/")) -> 0.5
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
		this.ageAtFirstMarriage = try {
			ageAtFirstMarriage.toInt()
		} catch (e: NumberFormatException) {
			-1
		}
		this.attendSchool = attendSchool == "Yes"
		this.canRead = canRead == "Yes"
		this.yearImmigrated = try {
			yearImmigrated.toInt()
		} catch (e: NumberFormatException) {
			-1
		}
		println(this)
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

val citizens = ArrayList<Citizen>()
val citizensByStreet = TreeMap<String, TreeSet<Citizen>>()
val citizensByBirthplace = TreeMap<String, PriorityQueue<Double>>()
val citizensByMotherTongue = TreeMap<String, ArrayList<String>>()
val citizensByOccupation = TreeMap<String, HashSet<String>>()
val citizensBySex = TreeMap<String, HashSet<String>>()
val citizensByRentOrOwn = TreeMap<String, TreeSet<Double>>()
val reader =
	Scanner(File("C:\\Users\\chira\\OneDrive\\Documents\\Programming\\Java_Development\\Classwork\\Data_Structures\\src\\dataStructures\\map\\Data.tsv"))

fun main() = mutableListOf<MutableList<String>>().apply {
	var x = 0
	while (reader.hasNextLine()) this += reader.nextLine().split("\t".toRegex()).toMutableList().apply {
		(0 until this.size).asSequence().filter { this[it] == "\".\"" }.forEach { this[it] = "" }
		(0 until this.size).asSequence().filter { this[it] == "\"\"" }.forEach { this[it] = "" }
	}.also {
		x++
		if (it.size != 34) err.println("Error: Row $x has ${it.size} columns")
	}
	forEach {
		citizens += Citizen(
			it[5].replace("\"", "").replace("*", ""),
			it[4].replace("\"", "").replace("*", ""),
			it[0].replace("\"", "").replace("*", ""),
			it[1].replace("\"", "").replace("*", ""),
			it[6].replace("\"", "").replace("*", ""),
			it[7].replace("\"", "").replace("*", ""),
			it[8].replace("\"", "").replace("*", ""),
			it[11].replace("\"", "").replace("*", ""),
			it[13].replace("\"", "").replace("*", ""),
			it[14].replace("\"", "").replace("*", ""),
			it[15].replace("\"", "").replace("*", ""),
			it[16].replace("\"", "").replace("*", ""),
			it[17].replace("\"", "").replace("*", ""),
			it[18].replace("\"", "").replace("*", ""),
			it[19].replace("\"", "").replace("*", ""),
			it[20].replace("\"", "").replace("*", ""),
			it[21].replace("\"", "").replace("*", ""),
			it[22].replace("\"", "").replace("*", ""),
			it[25].replace("\"", "").replace("*", ""),
			it[26].replace("\"", "").replace("*", ""),
			it[33].replace("\"", "").replace("*", "")
		)
	}
}.forEach(::println)
	/*
	 * Using the file Data.csv:
	 *
	 * Fill a TreeMap (using street name as the key) with a TreeSet of Citizens. Use an iterator to display all the citizens who lived on each street.
	 *
	 * Fill a TreeMap (using birthplace as the key) with a PriorityQueue of Citizens ages (a double) from that birthplace. Use an iterator to display the ages for each birthplace (except for Pennsylvania – just display a count of citizens who were born in Pennsylvania).
	 *
	 * Fill a new TreeMap (using mother tongue as the key) with an ArrayList of Citizens names (last name, first name as a String) who speak that mother tongue. Use an iterator to display the counts of all Citizens names who speak each of the mother tongues.
	 *
	 * Fill a new TreeMap (using occupation as the key) with a HashSet of father’s birthplaces (a String) who work that occupation. Use an iterator to display all the Citizens father’s birthplaces who work the respective occupations.
	 *
	 * Fill a new TreeMap (using sex as the key) with a HashSet of transcriber’s remarks (as a String) as the values. Display all the transcriber’s remarks for each sex.
	 *
	 * Fill a new TreeMap (using rent or own as the key) with an TreeSet of values of properties (or rent amounts) (a double – keep in mind that values like 23 1⁄2 need to be converted into a double). Display all the values of the properties (or rent amounts) as doubles based on rent or own status.
	 */.apply {
		citizens.forEach {
			citizensByStreet.getOrPut(it.street, ::TreeSet) += it
			citizensByBirthplace.getOrPut(it.birthplace, ::PriorityQueue) += it.age
			citizensByMotherTongue.getOrPut(it.motherTongue, ::ArrayList) += "${it.lastName}, ${it.firstName}"
			citizensByOccupation.getOrPut(it.occupation, ::HashSet) += it.fatherBirthplace
			citizensBySex.getOrPut(it.sex, ::HashSet) += it.transcriberRemarks
			citizensByRentOrOwn.getOrPut(it.rentOrOwn, ::TreeSet) += it.propertyValue
		}
		println("By Street")
		citizensByStreet.forEach { (_, value) ->
			value.forEach {
				println(
					String.format(
						"\t%-20s%-20s", "${it.lastName}, ${it.firstName}", it.street
					)
				)
			}
		}
	}
