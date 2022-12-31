package avengers

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes.
 * Then, write into the output file a boolean (true or false) indicating if
 * the graph is still connected.
 *
 *
 * Steps to implement this class main method:
 *
 *
 * Step 1:
 *
 *
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 *
 *
 * Read from PredictThanosSnapInputFile with the format:
 *
 *  1. seed (long): seed for the random number generator
 *  1. p (int): number of people (vertices in the graph)
 *  1. p lines, each with p (int) edges: 1 means there is a direct edge between two vertices, 0 no edge
 *
 *
 *
 * Note: the last p lines of the PredictThanosSnapInputFile is an adjacency matrix for
 * an undirected graph.
 *
 *
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 *
 *
 * 0 1 1 0<br></br>
 * 1 0 0 0<br></br>
 * 1 0 0 0<br></br>
 * 0 0 0 0<br></br>
 *
 *
 * Step 2:
 *
 *
 * Delete random vertices from the graph. You can use the following pseudocode.
 *
 *
 * `
 * StdRandom.setSeed(seed);<br></br>
 * for (int i = 0; i < p; i++) {<br></br>
 * if (StdRandom.bernoulli(0.5)) {<br></br>
 * // delete vertex i<br></br>
 * }<br></br>
 * }<br></br>
` *
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) {
 * if (StdRandom.uniform() <= 0.5) {
 * delete vertex;
 * }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 *
 *
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 *
 *
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 *
 *
 * To read from a file use StdIn:
 * StdIn.setFile(inputfilename);
 * StdIn.readInt();
 * StdIn.readDouble();
 *
 *
 * To write to a file use StdOut (here, isConnected is true if the graph is connected,
 * false otherwise):
 * StdOut.setFile(outputfilename);
 * StdOut.print(isConnected);
 *
 *
 * Compiling and executing:
 *
 *  1. Make sure you are in the ../InfinityWar directory
 *  1. javac -d bin src/avengers/ *.java
 *  1. java -cp bin avengers/PredictThanosSnap predictthanosnap.in predictthanosnap.out
 *
 *
 * @author Yashas Ravi
 */
fun main(args: Array<String>) {
	if (args.size < 2) {
		StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>")
		return
	}
	StdIn.setFile(args[0])
	val seed = StdIn.readLong()
	val numNodes = StdIn.readInt()
	val adjMatrix = Array<IntArray?>(numNodes) { IntArray(numNodes) }
	adjMatrix.forEach { it?.indices?.forEach { i -> it[i] = StdIn.readInt() } }
	StdRandom.setSeed(seed)
	(0 until numNodes).forEach { if (StdRandom.uniform() <= 0.5) adjMatrix[it] = null }
	StdOut.setFile(args[1])
	StdOut.print(adjMatrix.isConnected())
}

private fun Array<IntArray?>.isConnected(): Boolean {
	val visited = BooleanArray(size)
	dfs(this, 0, visited)
	return visited.all { it }
}

private fun isConnected(adjMatrix: Array<IntArray?>): Boolean {
	val visited = BooleanArray(adjMatrix.size)
	dfs(adjMatrix, 0, visited)
	return visited.all { it }
}

private fun dfs(adjMatrix: Array<IntArray?>, node: Int, visited: BooleanArray) {
	visited[node] = true
	adjMatrix.indices.filter { (adjMatrix[node] != null) && (adjMatrix[node]!![it] == 1) && !visited[it] }
		.forEach { dfs(adjMatrix, it, visited) }
}
