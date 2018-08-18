import java.util.*;
import java.lang.*;

/*
Hamiltonian Path in an undirected graph is a path that
visits each vertex exactly once.
A Hamiltonian cycle (or Hamiltonian circuit) is a
Hamiltonian Path such that there is an edge (in graph) from
the last vertex to the first vertex of the Hamiltonian Path.
Determine whether a given graph contains Hamiltonian Cycle or not. 
*/

class hamilton {

	static class Graph {
		int v; // no. of vertices
		int e; // no. of edges

		static class Node {
			int name;
			int w;

			public Node(int name, int w) {
				this.name = name;
				this.w = w;
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
			edges[des].add(new Node(src, 0));
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
	
/*-------------------------------------------------------------------------------------*/

	public static boolean hampath(Graph g, int cv, boolean[] vis, int tvis){
		if(tvis == g.v-1){
			return true;
		}

		vis[cv] = true;

		for(int i=0; i<g.edges[cv].size(); i++){
			int nbr = g.edges[cv].get(i).name;

			if(!vis[nbr]){
				if(hampath(g, nbr, vis, tvis+1))
					return true;
			}
		}

		vis[cv] = false;

		return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-->0){
			int v = sc.nextInt();
			int e = sc.nextInt();

			Graph g = new Graph(v);
			for(int i = 0; i < e; i++) {
				g.addEdge(sc.nextInt()-1, sc.nextInt()-1);
			}
			boolean f = false;
			for(int i=0; i<v; i++){
				if(hampath(g, i, new boolean[g.v], 0)){
					po(1);
					f = true;
					break;
				}
			}
			if(!f){
				po(0);
			}
		}
	}

	public static void po(Object o) {
		System.out.println(o);
	}

	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	// 5 7 0 1 1 2 0 3 2 4 1 3 1 4 3 4
	// 5 6 0 1 1 2 0 3 2 4 1 3 1 4
	// 4 4 0 1 1 2 2 3 3 0
	/*
	1
10 14
8 1 8 2 1 3 5 4 1 5 8 6 1 7 2 9 5 10 7 8 6 3 3 5 7 9 6 10
	*/
}
