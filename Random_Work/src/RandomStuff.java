import java.util.ArrayList;

public class RandomStuff {
	public static void main(String... args) {
	}
}

class UGraph {
	private static class Node {
		int data;
		Node next;

		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}

		public Node() {
			this(0, null);
		}
	}

	private final int numVertices;
	private final Node[] adjList;

	/*Write the constructor for the UGraph class, which takes in the number of vertices, and 2 int arrays of the same size.
For any index i, edge1[i] and edge2[i] represent an edge of the graph.
The constructor should set the number of vertices, and also fill in the adjacency list (implemented with linked lists here).
	*/
	public UGraph(int n, int[] edges1, int[] edges2) {
		numVertices = n;
		adjList = new Node[n];
		for (int i = 0; i < n; i++) adjList[i] = new Node();
		for (int i = 0; i < edges1.length; i++) {
			adjList[edges1[i]].next = new Node(edges2[i], adjList[edges1[i]].next);
			adjList[edges2[i]].next = new Node(edges1[i], adjList[edges2[i]].next);
		}
	}

	public void printAdjList() {
		for (int i = 0; i < numVertices; i++) {
			System.out.print("[" + i + "]");
			for (Node ptr = adjList[i]; ptr != null; ptr = ptr.next) {
				System.out.print("->" + ptr.data);
			}
			System.out.println();
		}
	}
}

class Digraph {
	private final int numVertices;
	private final ArrayList<ArrayList<Integer>> adjList;

	ArrayList<Integer> sources;
	ArrayList<Integer> sinks;

	public Digraph(int n, int[] edges1, int[] edges2) {
		numVertices = n;
		adjList = new ArrayList<>();
		for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
		for (int i = 0; i < edges1.length; i++) adjList.get(edges1[i]).add(edges2[i]);
		sources = new ArrayList<>();
		sinks = new ArrayList<>();
	}

	private void dfs(int current, boolean[] visited) {
		visited[current] = true;
		System.out.print(current);
		for (int i : adjList.get(current)) if (!visited[i]) dfs(i, visited);
	}

	public void dfs(int start) {
		boolean[] visited = new boolean[numVertices];
		dfs(start, visited);
	}

	public void bfs(int start) {
		boolean[] visited = new boolean[numVertices];
		ArrayList<Integer> queue = new ArrayList<>();
		queue.add(start);
		visited[start] = true;
		while (!queue.isEmpty()) {
			int current = queue.remove(0);
			System.out.print(current);
			for (int i : adjList.get(current))
				if (!visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
		}
	}

	/*The indegree of a vertex is the number of edges going into it, and the outdegree is the number of edges going out from it.

A vertex is considered to be a source if its indegree is 0, and a sink if its outdegree is 0.

Fill in the degrees method to identify all the sinks and sources in the graph, and add them to the instance ArrayLists.
*/
	public void degrees() {
		int[] indegrees = new int[numVertices];
		int[] outdegrees = new int[numVertices];
		for (int i = 0; i < numVertices; i++)
			for (int j : adjList.get(i)) {
				indegrees[j]++;
				outdegrees[i]++;
			}
		for (int i = 0; i < numVertices; i++) {
			if (indegrees[i] == 0) sources.add(i);
			if (outdegrees[i] == 0) sinks.add(i);
		}
	}
}
