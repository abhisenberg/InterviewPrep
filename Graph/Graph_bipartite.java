import java.util.*;
import java.lang.*;

class Graph_bipartite {

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

	public static void minColor(Graph g) {
		int c = 0;
		int[] colors = new int[g.v];
		boolean[] vis = new boolean[g.v];

		Queue<Integer> q = new LinkedList<>();
		vis[0] = true;
		q.add(0);

		while(!q.isEmpty()) {
			int cv = q.poll();

			po("*"+cv);

			int clr_not = 0;			
			for(int i=0; i<g.edges[cv].size(); i++) {
				int nbr = g.edges[cv].get(i).name;

				po("nbr is "+nbr+" clr is "+colors[nbr]);

				clr_not = clr_not | (1<<colors[nbr]);

				po("clr not is "+Integer.toBinaryString(clr_not));

				if(!vis[nbr]){
					q.add(nbr);
					vis[nbr] = true;
				}
			}

			int i;
			for(i=0; i<32; i++){
				if((clr_not & (1<<i)) == 0){
					break;
				}
			}

			if(i==0) i++;
			po("Assigning to "+cv+": colr "+i);
			colors[cv] = i;
		}

		for(int i=0; i<g.v; i++) {
			po(i+" -> "+colors[i]);
		}
	}

	public static boolean isBipartite(Graph g, int src) {

		//vertex i is 'b' if blue, 'r' if red
		char[] color = new char[g.v];
		Arrays.fill(color,'N');	

		Queue<Integer> q = new LinkedList<>();
		color[src] = 'r';
		q.add(src);

		boolean poss = true;
		while(!q.isEmpty()){
			int cv = q.poll();
			
			for(int i=0; i<g.edges[cv].size(); i++) {
				int nbr = g.edges[cv].get(i).name;

				char legitColor = (color[cv] == 'r')? 'b' : 'r';
				if(color[nbr] != 'N') {
					if(color[nbr] != legitColor){
						poss = false;
						break;
					}
				} else {
					color[nbr] = legitColor;
					q.add(nbr);
				}

			}
		}


		for(int i=0; i<g.v; i++) {
			po(i+" -> "+color[i]);
		}

		return poss;
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
		//po(isBipartite(g,0));
		minColor(g);
	}

	public static void po(Object o) {
		System.out.println(o);
	}

	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	// 4 4 0 1 0 2 1 2 1 3
	// 4 3 0 1 0 2 1 3
	// 4 4 0 1 0 2 1 3 3 0
	// 5 6 0 1 0 2 1 2 1 3 2 3 3 4

}
