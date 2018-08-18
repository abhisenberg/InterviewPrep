import java.util.*;
import java.lang.*;

class Graph_allPaths {

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
			//edges[des].add(new Node(src, 0));
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

	public static void allPaths(Graph g, int src, int des, ArrayList<Integer> ans){
		if(src == des){
			ans.add(des);
			po(ans);
			ans.remove(ans.size()-1);

			return;
		}

		ans.add(src);

		for(int i=0; i<g.edges[src].size(); i++){
			int nbr = g.edges[src].get(i).name;
			allPaths(g, nbr, des, ans);
		}

		ans.remove(ans.size()-1);
	}
	
/*-------------------------------------------------------------------------------------*/

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
		//po(g);
		allPaths(g, 0, 4, new ArrayList<>());
	}

	public static void po(Object o) {
		System.out.println(o);
	}

	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	// 5 7 0 1 0 2 1 3 3 2 0 4 1 4 2 4
	// 4 4 0 1 0 2 2 1 1 3
}
