import java.util.*;
import java.lang.*;

class Graph_cycledetection {

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
/***********************************************************************************************************************/
	/*
	FOR UNDIRECTED GRAPHS: DFS METHOD : If a node is visited again,
	and it is not called by it's parent, then it means we have encountered
	a loop.
	*/
	static boolean dfs_cd(Graph g, int src){
		Stack<Integer> s = new Stack<>();
		boolean[] vis = new boolean[g.v];
		int[] parent = new int[g.v];

		boolean cycdet = false;

		s.push(src);
		vis[src] = true;
			
		while(!s.isEmpty()) {
			int cv = s.pop();
			vis[cv] = true;
			po(cv);

			for(int i=0; i<g.edges[cv].size(); i++) {
				int nbr = g.edges[cv].get(i).name;
				if(!vis[nbr]){
					s.push(nbr);
					parent[nbr] = cv;
				} else if(nbr != parent[cv]) {
					po("Cycle detected! At "+nbr+" and "+cv);
					cycdet = true;
				}
			}
		}

		return cycdet;
	}

	/*
	FOR UNDIRECTED GRAPHS: DISJOINT SETS:
	For each edge, make subsets using both the vertices of the edge,
	If both the vertices are in the same subset, a cycle is found.
	If the vertices of an edge are in different subsets,
	we take the union of them. For taking the union,
	either make node 0 as parent of node 1,	or vice-versa.
	*/

	//Material for disjoint sets
	static int[] parent;
	static int[] ranks;

	public static int findSet(int vtx){
		if(parent[vtx] == vtx) {
			return vtx;
		}

		int new_parent = findSet(parent[vtx]);
		parent[vtx] = new_parent;
		return new_parent;
	}

	//If it returns false, it means their parents are same, and their
	//is a loop present.
	public static boolean union(int i, int j){
		int i_rep = findSet(i);
		int j_rep = findSet(j);

		if(i_rep == j_rep) {
			return false;	
		}

		if(ranks[i_rep] < ranks[j_rep]){
			parent[i_rep] = j_rep;
		} else if (ranks[j_rep] < ranks[i_rep]){
			parent[j_rep] = i_rep;
		} else {
			parent[j_rep] = i_rep;
			ranks[i_rep]++;
		}
		return true;
	}

	public static boolean disSet_cd(Graph g){
		parent = new int[g.v];
		ranks = new int[g.v];

		for(int i=0; i<g.v; i++){
			parent[i] = i;
		}

		boolean cycdet = false;

		for(int i=0; i<g.v; i++){
			for(int k=0; k<g.edges[i].size(); k++){
				int j = g.edges[i].get(k).name;
				if(!union(i,j)){
					po("Cycle found b/w "+i+" and "+j);
					cycdet = true;
				}
			}
		}

		return cycdet;
	}

	/*
	FOR DIRECTED GRAPHS: REC_STACK[] METHOD:
	Returns true if cycle is found, else false.
	rec_stack[] -> it is true when the vertex is present in the recursion stack
	
	We check if the nbr of a vtx is already present in the rec stack,
	if it is, then it would mean that a cycle is present b/w all the vtx
	present in the rec stack.
	*/
	static boolean[] cycle;
	public static boolean isCyclicRec(Graph g, int cv, boolean[] vis, boolean[] rec_stack) {
		
		po("currently visiting "+cv);

		if(rec_stack[cv])
			return true;

		if(vis[cv])
			return false;

		vis[cv] = true;
		rec_stack[cv] = true;

		for(int i=0; i<g.edges[cv].size(); i++){
			int nbr = g.edges[cv].get(i).name;

			if(isCyclicRec(g, nbr, vis, rec_stack)){

				//If cycle nodes need to be printed, this can be achieved
				//by printing the contents of rec_stack which are true.
				cycle = rec_stack;
				return true;
			}
		}

		rec_stack[cv] = false;
		return false;
	}

	public static void printCycle(){
		for(int k=0; k<cycle.length; k++){
				if(cycle[k]){
					System.out.print(k+" ");
			}
		}
	}

/* -----------------------------------------------------------------------------*/

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
		//po(dfs_cd(g, 3));
		//po(disSet_cd(g));
		po(isCyclicRec(g, 0, new boolean[g.v], new boolean[g.v]));
	}

	public static void po(Object o) {
		System.out.println(o);
	}

	//For undirected:
	// 6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
	// 6 6 0 1 0 2 1 3 2 3 2 5 3 4
	// 6 5 0 1 0 2 1 3 2 5 3 4
	// 5 5 0 1 1 2 3 2 4 3 4 0

	//For directed:
	// 6 6 1 0 0 2 3 1 2 3 2 5 3 4
 } 
