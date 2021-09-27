package jollySorting

import java.io.File
import java.io.FileNotFoundException
import java.util.*
import java.util.Arrays.stream

class JollySorting {
	fun main() = try {
		Scanner(File("Classwork/dataStructures/src/jollySorting/JollySorting.txt")).use {
			while (it.hasNextLine()) {
				val parse = stream(it.nextLine().split(" ").toTypedArray()).mapToInt(Integer::parseInt).toArray()
//				val arr1 = \
			}
		}
	} catch (e: FileNotFoundException) {
		e.printStackTrace()
	}
}