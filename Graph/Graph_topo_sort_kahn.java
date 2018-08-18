import java.util.*;
import java.lang.*;

class Graph_topo_sort_kahn {
	
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

	public static void topo_kahn(Graph g) {

		/* 
		Finding the number of incoming edges for each vertex,
		i.e. finding the in-degree[] array
		*/
		int[] indegree = new int[g.v];
		for(int i=0; i<g.v; i++) {
			for(int j=0; j<g.edges[i].size(); j++) {
				int into = g.edges[i].get(j).name;
				indegree[into]++;
			}
		}

		/*
		Finding the vertices with 0 indegree and inserting them
		into a queue.
		*/
		Queue<Integer> q = new LinkedList<>();
		for(int i=0; i<indegree.length; i++) {
			if(indegree[i] == 0) {
				q.add(i);
			}
		}

		LinkedList<Integer> sorted = new LinkedList<>();		
		int vis = 0;
		while(!q.isEmpty()) {
			int cv = q.poll();
			sorted.add(cv);
			vis++;

			for(int i=0; i<g.edges[cv].size(); i++) {
				int nbr = g.edges[cv].get(i).name;
				indegree[nbr]--;

				if(indegree[nbr] == 0)
					q.add(nbr);
			}
		}

		po(sorted);
	}

	public static void allTopoSorts() {
		
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
		po(g);
		topo_kahn(g);
	}

	public static void po(Object o) {
		System.out.println(o);
	}

	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	// 5 6 3 2 3 4 2 4 2 0 4 0 4 1
	// 5 6 0 1 0 2 1 2 1 3 2 3 2 4
	// 6 8 0 1 0 2 1 3 0 3 2 5 3 5 1 4 3 4 
}
