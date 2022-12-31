package leetCode.nQueens

import java.util.*
import kotlin.math.abs

class Solution {
	fun solveNQueens(n: Int) = with(LinkedList<List<String>>()) {
		dfs(IntArray(n), 0, this, n)
		this
	}

	private fun dfs(columns: IntArray, row: Int, res: MutableList<List<String>>, n: Int) {
		if (row == n) {
			res += drawGrids(columns)
			return
		}
		(0 until n).forEach {
			if (isValid(columns, row, it)) {
				columns[row] = it
				dfs(columns, row + 1, res, n)
			}
		}
	}

	private fun isValid(columns: IntArray, row: Int, col: Int) =
		(0 until row).none { (columns[it] == col) || ((row - it) == abs(col - columns[it])) }

	private fun drawGrids(columns: IntArray) = with(ArrayList<String>()) {
		columns.forEach {
			with(CharArray(columns.size)) {
				fill('.')
				this[it] = 'Q'
				add(String(this))
			}
		}
		this
	}
}
