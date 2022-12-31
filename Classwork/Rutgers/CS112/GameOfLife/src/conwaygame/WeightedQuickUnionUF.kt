package conwaygame

/*
 * Weighted Quick Union
 */
class WeightedQuickUnionUF(rows: Int, private val cols: Int) {
	private val parent = Array(rows) { IntArray(cols) }
	private val size = Array(rows) { IntArray(cols) }

	// Each parent[i][j] will contain i*numOfColumns + j
	// Going from i,j to this value is common, so convert and invert do this
	init {
		for (i in 0 until rows) for (j in 0 until cols) {
			parent[i][j] = convert(i, j)
			size[i][j] = 1
		}
	}

	fun find(i: Int, j: Int): Int {
		var dims = intArrayOf(i, j)
		var curParent = parent[i][j]
		while (convert(dims[0], dims[1]) != curParent) {
			dims = invert(curParent)
			curParent = parent[dims[0]][dims[1]]
		}
		return curParent
	}

	fun union(r1: Int, c1: Int, r2: Int, c2: Int) {
		var root1 = find(r1, c1)
		var root2 = find(r2, c2)
		if (root1 == root2) return
		// If root1 is root of larger tree, this makes it root of smaller tree
		if (size(root1) >= size(root2)) {
			val temp = root1
			root1 = root2
			root2 = temp
		}
		val invRoot1 = invert(root1)
		val invRoot2 = invert(root2)
		parent[invRoot1[0]][invRoot1[1]] = root2
		size[invRoot2[0]][invRoot1[1]] += size(root1)
	}

	private fun convert(a: Int, b: Int) = (a * cols) + b

	private fun invert(`val`: Int) = intArrayOf((`val` - (`val` % cols)) / cols, `val` % cols)

	private fun size(`val`: Int): Int {
		val result = invert(`val`)
		return size[result[0]][result[1]]
	}
}
