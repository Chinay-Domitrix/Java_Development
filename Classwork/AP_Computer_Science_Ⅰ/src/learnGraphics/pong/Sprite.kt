package learnGraphics.pong

import java.awt.Rectangle

open class Sprite internal constructor(
	var x: Int,
	var y: Int,
	var xA: Int,
	var yA: Int,
	val width: Int,
	val height: Int
) {

	val bounds: Rectangle
		get() = Rectangle(x, y, width, height)
}
