package jollySorting

import java.io.File
import java.io.FileNotFoundException
import java.util.*
import java.util.Arrays.*

fun main() = try {
	Scanner(File("Classwork/dataStructures/src/jollySorting/JollySorting.txt")).use {
		while (it.hasNextLine()) {
			val parse =
				stream(it.nextLine().split(" ".toRegex()).toTypedArray()).mapToInt(String::toInt).toArray()
			sort(parse)
			val a = copyOfRange(parse, 0, (parse.size + 1) / 2)
			val b = copyOfRange(parse, (parse.size + 1) / 2, parse.size)
			val c = IntArray(parse.size)
			run {
				var i = 0
				var j = 0
				while (i < c.size) {
					c[i] = a[j]
					i += 2
					j++
				}
			}
			var i = 1
			var j = 0
			while (i < c.size) {
				c[i] = b[j]
				i += 2
				j++
			}
			println(c.contentToString())
		}
	}
} catch (e: FileNotFoundException) {
	e.printStackTrace()
}
