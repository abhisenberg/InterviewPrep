import java.util.*;
import java.lang.*;

/*
	Given an undirected graph and an integer M,
	determine if the graph can be colored with at most M colors
	such that no two adjacent vertices of the graph are colored
	with the same color. Here coloring of a graph means
	assignment of colors to all vertices. Print 1 if it is
	possible to colour vertices and 0 otherwise.
	*/


class graphMcolor {

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
	
	public static boolean minclr(Graph g, int m){
		int[] clrs = new int[g.v];
		Arrays.fill(clrs, -1);

		boolean[] not_ava;
		
		for(int i=0; i<g.v; i++){
			not_ava = new boolean[m];
			
			for(int j=0; j<g.edges[i].size(); j++){
				int nbr = g.edges[i].get(j).name;
				if(clrs[nbr] != -1){
					not_ava[clrs[nbr]] = true;
				}
			}

			int a = -1;
			for(int j=0; j<m; j++){
				if(!not_ava[j]){
					a = j;
					break;
				}
			}

			if(a == -1){
				return false;
			}
			clrs[i] = a;
		}
		//showArr(clrs);
		return true;
	}

	public static void showArr(int[] clr){
		for(int i=0; i<clr.length; i++){
			po("clr of v: "+i+" is "+clr[i]);
		}
	}

	public static void main(String[] args) {
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
			int v = sc.ni();
			int m = sc.ni();
			int e = sc.ni();
			
			Graph g = new Graph(v);
			for(int i=0; i<e; i++){
				g.addEdge(sc.ni()-1, sc.ni()-1);
			}
			int ans = minclr(g, m) == true ? 1 : 0;
			po(ans);
		}
	}

	static class ShortScan {
			Scanner sc;

			public ShortScan(){
				sc = new Scanner (System.in);
			}

			public int ni(){
				return sc.nextInt();
			}
		}

	public static void po(Object o) {
		System.out.println(o);
	}

	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	/*
	1
4
3
5
1 2 2 3 3 4 4 1 1 3

	*/
}
