package leetCode.possibleBipartition

class Solution {
	fun possibleBipartition(n: Int, dislikes: Array<IntArray>): Boolean {
		val graph = Array(n + 1) { mutableListOf<Int>() }
		for (dislike in dislikes) {
			graph[dislike[0]].add(dislike[1])
			graph[dislike[1]].add(dislike[0])
		}
		val colors = IntArray(n + 1) { 0 }
		return (1..n).none { (colors[it] == 0) && !dfs(graph, colors, it, 1) }
	}

	private fun dfs(graph: Array<MutableList<Int>>, colors: IntArray, i: Int, color: Int): Boolean {
		if (colors[i] != 0) return colors[i] == color
		colors[i] = color
		return graph[i].all { dfs(graph, colors, it, -color) }
	}
}
