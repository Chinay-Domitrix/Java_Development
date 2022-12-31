package avengers

/**
 * Given a starting event and an Adjacency Matrix representing a graph of all possible
 * events once Thanos arrives onto Titan, determine the total possible number of timelines
 * that could occur AND the number of timelines with a total Expected Utility (EU) at
 * least the threshold value.
 *
 *
 *
 *
 * Steps to implement this class main method:
 *
 *
 * Step 1:
 * UseTimeStoneInputFile name is passed through the command line as args[0]
 * Read from UseTimeStoneInputFile with the format:
 * 1. t (int): expected utility (EU) threshold
 * 2. v (int): number of events (vertices in the graph)
 * 3. v lines, each with 2 values: (int) event number and (int) EU value
 * 4. v lines, each with v (int) edges: 1 means there is a direct edge between two vertices, 0 no edge
 *
 *
 * Note 1: the last v lines of the UseTimeStoneInputFile is an adjacency matrix for a directed
 * graph.
 * The rows represent the "from" vertex and the columns represent the "to" vertex.
 *
 *
 * The matrix below has only two edges: (1) from vertex 1 to vertex 3 and, (2) from vertex 2 to vertex 0
 * 0 0 0 0
 * 0 0 0 1
 * 1 0 0 0
 * 0 0 0 0
 *
 *
 * Step 2:
 * UseTimeStoneOutputFile name is passed through the command line as args[1]
 * Assume the starting event is vertex 0 (zero)
 * Compute all the possible timelines, output this number to the output file.
 * Compute all the possible timelines with Expected Utility higher than the EU threshold,
 * output this number to the output file.
 *
 *
 * Note 2: output these number the in above order, one per line.
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
 * //Call StdOut.print() for total number of timelines
 * //Call StdOut.print() for number of timelines with EU >= threshold EU
 *
 *
 * Compiling and executing:
 * 1. Make sure you are in the ../InfinityWar directory
 * 2. javac -d bin src/avengers/ *.java
 * 3. java -cp bin avengers/UseTimeStone usetimestone.in usetimestone.out
 *
 * @author Yashas Ravi
 */

fun main(args: Array<String>) {
	if (args.size < 2) {
		StdOut.println("Execute: java UseTimeStone <INput file> <OUTput file>")
		return
	}
	StdIn.setFile(args[0])
	val threshold = StdIn.readInt()
	val numEvents = StdIn.readInt()
	val eventEUs = IntArray(numEvents)
	repeat(eventEUs.indices.count()) { eventEUs[StdIn.readInt()] = StdIn.readInt() }
	val adjMatrix = Array(numEvents) { IntArray(numEvents) }
	adjMatrix.indices.forEach { i -> adjMatrix[i].indices.forEach { adjMatrix[i][it] = StdIn.readInt() } }
	val timelines = ArrayList<Timeline>()
	var timeline = Timeline(0, eventEUs[0])
	timelines += timeline
	var numTimelines = 0
	var numTimelinesWithEU = 0
	while (timelines.isNotEmpty()) {
		timeline = timelines - 0
		numTimelines++
		if (timeline.expectedUtility >= threshold) numTimelinesWithEU++
		adjMatrix[timeline.lastEvent].indices.filter { adjMatrix[timeline.lastEvent][it] == 1 }
			.mapTo(timelines) { Timeline(timeline, it, eventEUs[it]) }
	}
	StdOut.setFile(args[1])
	StdOut.println(numTimelines)
	StdOut.println(numTimelinesWithEU)
}

class Timeline {
	private val events: ArrayList<Int>
	var expectedUtility = 0
		private set

	private constructor() {
		events = ArrayList()
	}

	constructor(event: Int, expectedUtility: Int) : this() {
		add(event, expectedUtility)
	}

	constructor(timeline: Timeline, event: Int, expectedUtility: Int) : this(timeline) {
		add(event, expectedUtility)
	}

	private constructor(timeline: Timeline) {
		events = ArrayList(timeline.events)
		expectedUtility = timeline.expectedUtility
	}

	private fun add(event: Int, expectedUtility: Int) {
		events.add(event)
		this.expectedUtility += expectedUtility
	}

	val lastEvent: Int
		get() = events[events.size - 1]
}

private operator fun <T> MutableList<T>.minus(index: Int): T {
	return removeAt(index)
}

private operator fun <T> MutableList<T>.minus(element: T): Boolean {
	return remove(element)
}
