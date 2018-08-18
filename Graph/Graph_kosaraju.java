import java.util.*;
import java.lang.*;

class Graph_kosaraju {

	static class Node {
		int name;
		int w;

		public Node(int name, int w) {
			this.name = name;
			this.w = w;
		}
	}

	static class Graph {
		int v; // no. of vertices
		int e; // no. of edges

		LinkedList<Node>[] edges;

		public Graph(int v) {
			this.v = v;
			edges = new LinkedList[v];
			for (int i = 0; i < v; i++) {
				edges[i] = new LinkedList<>();
			}
		}

		public void addEdge(int src, int des, int w) {
			edges[src].add(new Node(des, w));
		}

		//For unweighted graph
		public void addEdge(int src, int des){
			edges[src].add(new Node(des, 0));
		}

		@Override
		public String toString() {
			String s = "";
			for (int i = 0; i < v; i++) {
				s += i;
				for (int j = 0; j < edges[i].size(); j++) {
					s += " -> " + edges[i].get(j).name + ":" + edges[i].get(j).w + "    ";
				}
				s += '\n';
			}
			return s;
		}
	}

	/*
	Algorithm:

	1 ----> 0 ---> 3	
	^     /       |
 	|    /        |
	|   /         V
	2             4

	1) Create an empty stack ‘S’ and do DFS traversal of a graph.
		In DFS traversal, after calling recursive DFS for adjacent vertices of a vertex,
		push the vertex to stack.
		In the above graph, if we start DFS from vertex 0,
		we get vertices in stack as 1, 2, 4, 3, 0.
	2) Reverse directions of all arcs to obtain the transpose graph.
	3) One by one pop a vertex from S while S is not empty.
		Let the popped vertex be ‘v’. Take v as source and do DFS (call DFSUtil(v)).
		The DFS starting from v prints strongly connected component of v. In the above example,
		we process vertices in order 0, 3, 4, 2, 1 (One by one popped from stack).

	In brief:
	1. Make an empty stack s and do dfs on vertices, keep adding the
		vertices which complete their neighbours.
	2. Reverse the graph edge directions.
	3. Pop the vertices and do dfs on popped vertex, and print the
		dfs order. The printed vertices will all be in SCC.
	*/

	public static void kosaraju(Graph g){
		
		Stack<Integer> s = new Stack<>();
		boolean[] vis = new boolean[g.v];

		for(int i=0; i<g.v; i++){
			if(!vis[i]){
				dfs(g, i, vis, s);
			}
		}
		
		//Reversing the graph
		LinkedList<Node> oldEdges[] = g.edges;
		LinkedList<Node> newEdges[] = new LinkedList[g.v];
		for(int i=0; i<g.v; i++){
			newEdges[i] = new LinkedList<>();
		}

		for(int i=0; i<g.v; i++){
			for(int j=0; j<g.edges[i].size(); j++){
				newEdges[g.edges[i].get(j).name]
					.add(new Node(i, 0));
			}
		}
		g.edges = newEdges;

		//Pop vertces and do DFS
		boolean[] visited = new boolean[g.v];
		while(!s.isEmpty()){
			int src = s.pop();
			if(!visited[src]){
				dfs_after_rev(g, src, visited);
				po("");
			}
		}
	}

	public static void dfs(Graph g, int src, boolean[] vis, Stack<Integer> s){
		vis[src] = true;

		for(int i=0; i<g.edges[src].size(); i++){
			int nbr = g.edges[src].get(i).name;
			
			if(!vis[nbr]){
				dfs(g, nbr, vis, s);
			}
		}
		s.push(src);
	}

	public static void dfs_after_rev(Graph g, int src, boolean[] vis){
		vis[src] = true;
		System.out.print(src+" ");

		for(int i=0; i<g.edges[src].size(); i++){
			int nbr = g.edges[src].get(i).name;
			
			if(!vis[nbr]){
				dfs_after_rev(g, nbr, vis);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		po("Enter no. of vertices: ");
		int v = sc.nextInt();
		po("Enter no. of edges: ");
		int e = sc.nextInt();

		Graph g = new Graph(v);
		for (int i = 0; i < e; i++) {
			po("enter " + i + "th edge");
			g.addEdge(sc.nextInt()-1, sc.nextInt()-1);
		}
		po(g);
		kosaraju(g);
	}

	public static void po(Object o) {
		System.out.println(o);
	}

	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	// 5 5 1 0 0 2 2 1 0 3 3 4
}
