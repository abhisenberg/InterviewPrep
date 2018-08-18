import java.util.*;
import java.lang.*;

public class Graph_Djikstra {
	
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

			// To make the graph directed,comment the below line
			// edges[des].add(new Node(src, w));
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
			po(s);
			return s;
		}

		public void dijkstra(int src) {
			boolean[] inSPT = new boolean[v];
			int[] dist = new int[v];

			// Initialize the dist of every node to infinity
			for (int i = 0; i < v; i++) {
				dist[i] = 1000000000;
			}

			// Set the dis of src to be 0
			dist[src] = 0;

			String path = "";

			for (int i = 0; i < v; i++) {
				int cv = nextVtx(dist, inSPT);

				// Mark current vertex visited
				inSPT[cv] = true;

				// Add the current vertex (since it is selected) to the path
				path += cv + " ";

				// Unlock its neighbors
				for (int j = 0; j < edges[cv].size(); j++) {
					int nbr = edges[cv].get(j).name;
					int nbr_w = edges[cv].get(j).w;

					if (!inSPT[nbr] && dist[nbr] > dist[cv] + nbr_w) {
						dist[nbr] = dist[cv] + nbr_w;
					}
				}
			}

			po("The path covered is: " + path);
			po("The min distances from src are: ");
			for (int i = 0; i < dist.length; i++) {
				po("For vertex " + i + " " + dist[i]);
			}

		}

		public int nextVtx(int[] dist, boolean[] inSPT) {
			int min = Integer.MAX_VALUE, retIndx = -1;
			for (int i = 0; i < dist.length; i++) {
				if (!inSPT[i] && dist[i] < min) {
					min = dist[i];
					retIndx = i;
				}
			}
			return retIndx;
		}
	}

	public static void main(String[] args) {
		/*
		 * Scanner sc = new Scanner(System.in); po("Enter v: "); int v =
		 * sc.nextInt(); po("Enter e: "); int e = sc.nextInt();
		 * 
		 * Graph g = new Graph(v); for(int i=0; i<e; i++) {
		 * g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt()); } po(g);
		 * g.dijkstra(0);
		 */

		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		int e = sc.nextInt();

		Graph g = new Graph(v);
		for (int i = 0; i < e; i++) {
			g.addEdge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
		}
		po(g);
		g.dijkstra(0);
	}

	public static void po(Object o) {
		System.out.println(o);
	}
	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	// 4 5 0 1 1 0 2 0 1 2 1 0 3 99 3 1 -300
	// 3 3 0 1 5 1 2 -10 0 2 2
	// 5 5 1 2 5 1 3 2 3 4 1 1 4 6 3 5 5
}
