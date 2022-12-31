package avengers

import java.util.*

/**
 * Given a Set of Edges representing Vision's Neural Network, identify all the
 * vertices that connect to the Mind Stone.
 * List the names of these neurons in the output file.
 *
 *
 *
 *
 * Steps to implement this class main method:
 *
 *
 * Step 1:
 * MindStoneNeighborNeuronsInputFile name is passed through the command line as args[0]
 * Read from the MindStoneNeighborNeuronsInputFile with the format:
 * 1. v (int): number of neurons (vertices in the graph)
 * 2. v lines, each with a String referring to a neuron's name (vertex name)
 * 3. e (int): number of synapses (edges in the graph)
 * 4. e lines, each line refers to an edge, each line has 2 (two) Strings: from to
 *
 *
 * Step 2:
 * MindStoneNeighborNeuronsOutputFile name is passed through the command line as args[1]
 * Identify the vertices that connect to the Mind Stone vertex.
 * Output these vertices, one per line, to the output file.
 *
 *
 * Note 1: The Mind Stone vertex has out degree 0 (zero), meaning that there are
 * no edges leaving the vertex.
 *
 *
 * Note 2: If a vertex v connects to the Mind Stone vertex m then the graph has
 * an edge v -> m
 *
 *
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 *
 *
 * To read from a file use StdIn:
 * StdIn.setFile(inputfilename);
 * StdIn.readInt();
 * StdIn.readDouble();
 *
 *
 * To write to a file use StdOut:
 * StdOut.setFile(outputfilename);
 * //Call StdOut.print() for EVERY neuron (vertex) neighboring the Mind Stone neuron (vertex)
 *
 *
 * Compiling and executing:
 * 1. Make sure you are in the ../InfinityWar directory
 * 2. javac -d bin src/avengers/ *.java
 * 3. java -cp bin avengers/MindStoneNeighborNeurons mindstoneneighborneurons.in mindstoneneighborneurons.out
 *
 * @author Yashas Ravi
 */

fun main(args: Array<String>) {
	if (args.size < 2) {
		StdOut.println("Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>")
		return
	}
	StdIn.setFile(args[0])
	val n = StdIn.readInt()
	val list = ArrayList<String>()
	repeat((0 until n).count()) { list += StdIn.readString() }
	list.toTypedArray()
	val m = StdIn.readInt()
	val edges = arrayOfNulls<Edge>(m)
	val verticesList = ArrayList<Vertex>()
	(0 until m).forEach {
		val from = StdIn.readString()
		val to = StdIn.readString()
		var found = Vertex(from)
		for (vertex in verticesList) if (vertex.name == from) {
			found = vertex
			break
		}
		val out = found.incrementOutDegree()
		var result = Vertex(to)
		for (v in verticesList) if (v.name == to) {
			result = v
			break
		}
		val `in` = result.incrementInDegree()
		verticesList += out
		verticesList += `in`
		edges[it] = Edge(out, `in`)
	}
	StdOut.setFile(args[1])
	var found = Optional.empty<Vertex>()
	for (v in verticesList.toTypedArray()) {
		if (v.outDegree == 0 && v.inDegree > 0) {
			found = Optional.of(v)
			break
		}
	}
	found.get().getNeighbours(edges).forEach { StdOut.println(it.name) }
}

class Edge(val from: Vertex, val to: Vertex)
class Vertex(val name: String) {
	var outDegree = 0
	var inDegree = 0
	fun incrementOutDegree(): Vertex {
		outDegree++
		return this
	}

	fun incrementInDegree(): Vertex {
		inDegree++
		return this
	}

	fun getNeighbours(edges: Array<Edge?>): ArrayList<Vertex> {
		val neighbours = ArrayList<Vertex>()
		edges.forEach {
			if (it!!.from == this) neighbours.add(it.to)
			if (it.to == this) neighbours.add(it.from)
		}
		return neighbours
	}
}
