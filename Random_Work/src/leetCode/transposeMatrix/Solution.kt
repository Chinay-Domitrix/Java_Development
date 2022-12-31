package leetCode.transposeMatrix

class Solution {
	fun transpose(matrix: Array<IntArray>) = with(Array(matrix[0].size) { IntArray(matrix.size) }) {
		matrix.indices.forEach { i -> matrix[i].indices.forEach { j -> this[j][i] = matrix[i][j] } }
		this
	}
}
