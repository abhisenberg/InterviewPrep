import java.util.*;
import java.lang.*;

class Graph_topo_sort {
	
	static class Graph {
		int v; // no. of vertices
		int e; // no. of edges

		static class Node implements Comparable<Node> {
			int name;
			int w;

			public Node(int name, int w) {
				this.name = name;
				this.w = w;
			}

			/*
			Arrange the list in descending order to attain
			lexicographic order while popping from stack.
			If the list is made in ascending order, then while
			popping, the result will be in reverse lexicograhic 
			order.
			*/
			public int compareTo(Node other) {
				return other.name - this.name;
			}
		}

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

		public void addEdge(int src, int des) {
			edges[src].add(new Node(des, 0));
		}

		/*
		Sorting is done so that the edges are visited in a 
		lexicographic order.
		*/
		public void sortEdges() {
			for(int i=0; i<this.v; i++) {
				Collections.sort(this.edges[i]);
			}
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

/*****************************************************************************************************/

	public static void topo_sort(Graph g) {
		Stack<Integer> s = new Stack<>();
		boolean[] vis = new boolean[g.v];

		for(int i= g.v - 1; i>=0; i--) {
			if(!vis[i]){
				dfs(g, i, vis, s);
			}
		}

		while(!s.isEmpty()) {
			System.out.print(s.pop()+" ");
		}
		/*
		The vertices which are popped earlier than others mean
		they come earlier than others in topo sort.
		*/
	}

	/*
	The items are pushed in the stack in order of their
	time taken to visit the neighbours. The vertices which
	finish earlier are moved earlier into the stack.

	The vertex which finishes visiting all it's neighbours first
	is pushed first, meaning it has explored all it's children
	and no other vertex "depends" on it, therefore it'll come
	last in the order of popping of stack.
	*/
	public static void dfs(Graph g, int src, boolean[] vis, Stack<Integer> s) {
		
		vis[src] = true;
		for(int i=0; i<g.edges[src].size(); i++) {
			int nbr = g.edges[src].get(i).name;

			if(!vis[nbr]){
				dfs(g, nbr, vis, s);
			}
		}
		s.push(src);
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
			g.addEdge(sc.nextInt(), sc.nextInt());
		}
		g.sortEdges();
		po(g);

		topo_sort(g);
	}

	public static void po(Object o) {
		System.out.println(o);
	}

	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	// 5 6 3 2 3 4 2 4 2 0 4 0 4 1
	// 5 6 0 1 0 2 1 2 1 3 2 3 2 4
}
