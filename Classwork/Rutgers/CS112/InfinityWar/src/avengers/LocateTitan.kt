package avengers

/**
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0),
 * modify the edge weights using the functionality values of the vertices that each edge
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 *
 *
 * Steps to implement this class main method:
 *
 *
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 * 1. g (int): number of generators (vertices in the graph)
 * 2. g lines, each with 2 values, (int) generator number, (double) functionality value
 * 3. g lines, each with g (int) edge values, referring to the energy cost to travel from
 * one generator to another
 * Create an adjacency matrix for g generators.
 *
 *
 * Populate the adjacency matrix with edge values (the energy cost to travel from one
 * generator to another).
 *
 *
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it
 * by the functionality of BOTH vertices (generators) that the edge points to. Then,
 * typecast this number to an integer (this is done to avoid precision errors). The result
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 *
 *
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan.
 * Output this number into your output file!
 *
 *
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 *
 *
 * To read from a file use StdIn:
 * StdIn.setFile(inputfilename);
 * StdIn.readInt();
 * StdIn.readDouble();
 *
 *
 * To write to a file use StdOut (here, minCost represents the minimum cost to
 * travel from Earth to Titan):
 * StdOut.setFile(outputfilename);
 * StdOut.print(minCost);
 *
 *
 * Compiling and executing:
 * 1. Make sure you are in the ../InfinityWar directory
 * 2. javac -d bin src/avengers/ *.java
 * 3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 *
 * @author Yashas Ravi
 */
fun main(args: Array<String>) {
	if (args.size < 2) {
		StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>")
		return
	}
	StdIn.setFile(args[0])
	val g = StdIn.readInt()
	val functionality = DoubleArray(g)
	repeat((0 until g).count()) { functionality[StdIn.readInt()] = StdIn.readDouble() }
	val matrix = Array(g) { IntArray(g) }
	matrix.forEach { it.indices.forEach { i -> it[i] = StdIn.readInt() } }
	matrix.indices.forEach { matrix[it].indices.forEach { j -> matrix[it][j] /= (functionality[it] * functionality[j]).toInt() } }
	StdOut.setFile(args[1])
	StdOut.print(Dijkstra(matrix).getDistance(g - 1))
}

class Dijkstra(private val matrix: Array<IntArray>) {
	private val distance = IntArray(matrix.size)
	private val visited = BooleanArray(matrix.size)

	init {
		matrix.indices.forEach {
			distance[it] = Int.MAX_VALUE
			visited[it] = false
		}
		distance[0] = 0
		repeat(matrix.indices.count()) {
			val min = minDistance()
			visited[min] = true
			matrix.indices.filter { !visited[it] && (matrix[min][it] > 0) && (distance[min] != Int.MAX_VALUE) && ((distance[min] + matrix[min][it]) < distance[it]) }
				.forEach { distance[it] = distance[min] + matrix[min][it] }
		}
	}

	private fun minDistance(): Int {
		var min = Int.MAX_VALUE
		var minIndex = -1
		matrix.indices.forEach {
			if (!visited[it] && distance[it] <= min) {
				min = distance[it]
				minIndex = it
			}
		}
		return minIndex
	}

	fun getDistance(index: Int) = distance[index]
}
