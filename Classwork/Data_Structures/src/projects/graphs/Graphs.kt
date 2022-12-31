package projects.graphs

import java.io.File

import kotlin.Int.Companion.MAX_VALUE

data class City(val name: String) {
	private var id = name.hashCode()
	override fun equals(other: Any?) = if (this === other) true else hashCode() == (other as City).hashCode()
	override fun hashCode() = id
	override fun toString() = name
}

data class Edge(val start: City, val destination: City, val distance: Int) {
	private val id = start.hashCode() + destination.hashCode()
	override fun equals(other: Any?) = if (this === other) true else hashCode() == (other as Edge).hashCode()
	override fun hashCode() = id
	override fun toString() = "$start to $destination: $distance"
}

data class Graph(val cities: HashSet<City>, val edges: HashSet<Edge>)

class DijkstrasAlgorithm(graph: Graph) {
	val cities: ArrayList<City> = ArrayList(graph.cities)
	private val edges: ArrayList<Edge> = ArrayList(graph.edges)
	private lateinit var visitedCities: HashSet<City>
	private lateinit var unvisitedCities: HashSet<City>
	private lateinit var predecessors: HashMap<City, City>
	private lateinit var distance: HashMap<City, Int>

	fun createTravelPaths(source: City) {
		visitedCities = HashSet()
		unvisitedCities = HashSet()
		predecessors = HashMap()
		distance = HashMap()
		distance += source to 0
		unvisitedCities += source
		while (unvisitedCities.size > 0) {
			val city = getMinimum(unvisitedCities)
			visitedCities.add(city)
			unvisitedCities.remove(city)
			findMinimalDistances(city)
		}
	}

	private fun findMinimalDistances(tempCity: City?) = getNeighbors(tempCity).forEach {
		if (getShortestDistance(it) > (getShortestDistance(tempCity) + getDistance(tempCity, it))) {
			distance[it] = getShortestDistance(tempCity) + getDistance(tempCity, it)
			predecessors[it] = tempCity ?: throw NullPointerException()
			unvisitedCities.add(it)
		}
	}

	private fun getDistance(tempCity: City?, targetCity: City?): Int {
		edges.forEach { (start, destination, distance) -> if (((start == tempCity) && (destination == targetCity)) || ((start == targetCity) && (destination == tempCity))) return distance }
		throw RuntimeException()
	}

	private fun getNeighbors(tempCity: City?): ArrayList<City> {
		val neighbors = ArrayList<City>()
		edges.forEach { (start, destination) ->
			if (start == tempCity && !wasVisited(destination)) neighbors += destination
			if (destination == tempCity && !wasVisited(start)) neighbors += start
		}
		return neighbors
	}

	private fun getMinimum(cities: HashSet<City>): City? {
		var minimum: City? = null
		cities.asSequence().filter { getShortestDistance(it) < getShortestDistance(minimum) }.forEach { minimum = it }
		return minimum
	}

	private fun wasVisited(city: City) = city in visitedCities
	private fun getShortestDistance(destination: City?) = distance[destination] ?: MAX_VALUE
	fun getShortestPath(target: City): ArrayList<City>? {
		val connectingCities = ArrayList<City>()
		var step = target
		if (predecessors[step] == null) return null
		connectingCities += step
		while (predecessors[step] != null) {
			step = predecessors[step]!!
			connectingCities += step
		}
		connectingCities.reverse()
		return connectingCities
	}
}

private fun <E> HashSet<E>.add(element: E?) = add(element ?: throw NullPointerException())

class Runner {
	private var cityMap: HashMap<City, HashSet<Edge>>
	private var cities: HashSet<City> = HashSet()
	private var edges: HashSet<Edge>
	private lateinit var start: City
	private lateinit var end: City

	init {
		val cityList = ArrayList<String>()
		edges = HashSet()
		cityMap = HashMap()
		for (line in File("/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/graphs/data/CityDistances.csv").readLines()) {
			val info = line.split(",")
			val c1 = City(info[0])
			val c2 = City(info[1])
			val distance = info[2].toInt()
			if (c1.name !in cityList) cityList += c1.name
			if (c2.name !in cityList) cityList += c2.name
			cities += listOf(c1, c2)
			edges += listOf(Edge(c1, c2, distance), Edge(c2, c1, distance))
			if (c1 !in cityMap) cityMap[c1] = HashSet()
			if (c2 !in cityMap) cityMap[c2] = HashSet()
			cityMap[c1]?.add(Edge(c1, c2, distance))
			cityMap[c2]?.add(Edge(c2, c1, distance))
		}
		println("Vertices - Cities")
		cities.forEach { println("\t$it") }
		println("Edges - Connecting cities and distances between")
		edges.forEach { println("\t$it") }
		cityList.indices.forEach { i ->
			(((i + 1) until cityList.size)).forEach { j ->
				cities.forEach {
					if (it.name == cityList[i]) start = it
					if (it.name == cityList[j]) end = it
				}
				val dijkstra = DijkstrasAlgorithm(Graph(cities, edges))
				dijkstra.createTravelPaths(start)
				val shortestPath = dijkstra.getShortestPath(end)
				var distance = 0
				println("Shortest path between ${start.name} to ${end.name}.")
				repeat((0 until shortestPath!!.size - 1).count()) {
					val c1 = shortestPath[it]
					val c2 = shortestPath[it + 1]
					println("\t${c1.name} to ${c2.name}")
					cityMap[c1]!!.forEach { (start1, destination, distance1) ->
						if ((start1 == c1) && (destination == c2)) distance += distance1
						if ((start1 == c2) && (destination == c1)) distance += distance1
					}
				}
				println("Distance between: $distance miles\n\n")
			}
		}
	}
}

fun main() {
	Runner()
}
