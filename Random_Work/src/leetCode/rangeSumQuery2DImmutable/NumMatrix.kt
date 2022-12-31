package leetCode.rangeSumQuery2DImmutable

class NumMatrix(var matrix: Array<Array<Int>>) {
	fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
		matrix = matrix.sliceArray(row1..row2)
		matrix.forEach { it.sliceArray(col1..col2) }
		return matrix.flatten().sum()
	}
}

// [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]]
fun main() = with(
	NumMatrix(
		arrayOf(
			arrayOf(3, 0, 1, 4, 2),
			arrayOf(5, 6, 3, 2, 1),
			arrayOf(1, 2, 0, 1, 5),
			arrayOf(4, 1, 0, 1, 7),
			arrayOf(1, 0, 3, 0, 5)
		)
	).sumRegion(2, 1, 4, 3), ::println
)
