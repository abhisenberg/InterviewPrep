import java.util.*;
import java.lang.*;

class graph_gfg
{
   static class Graph
   {
 	int v;
	LinkedList<Integer> adj[];
	
	Graph(int v)
	{
	   this.v = v;
	   adj = new LinkedList[v];

	   for(int i=0; i<v; i++)
	   {
		adj[i] = new LinkedList<>();
	   }
	}

	public String toString()
	{  
	   String ans = "";
	   for(int i=0; i<this.v; i++)
	   {
		ans += i+" -> ";
		for(int nbr: this.adj[i])
		{
		  ans += nbr+", ";
		}
		ans += '\n';
	   }
	   return ans;
	}
	
	void show()
	{
	   System.out.println(this);
	}

	void addEdge(int src, int des)
   	   {
		this.adj[src].addFirst(des);
		this.adj[des].addFirst(src);
   	   }
   }
   
    static class Graph_Weighted
   {
   	static class Node
   	{
   	   int name;
   	   int weight;
   	   
   	   public Node(int name, int weight)
   	   {
   	   	this.name = name;
   	   	this.weight = weight;
   	   }
   	}
   	
 	int v;
	LinkedList<Node> adj[];
	
	Graph_Weighted(int v)
	{
	   this.v = v;
	   adj = new LinkedList[v];

	   for(int i=0; i<v; i++)
	   {
		adj[i] = new LinkedList<>();
	   }
	}

	public String toString()
	{  
	   String ans = "";
	   for(int i=0; i<this.v; i++)
	   {
		ans += i+" -> ";
		for(Node nbr: this.adj[i])
		{
		  ans += nbr.name+":"+nbr.weight+", ";
		}
		ans += '\n';
	   }
	   return ans;
	}
	
	void show()
	{
	   System.out.println(this);
	}

	void addEdge(int src, int des, int w)
   	   {	
		this.adj[src].addFirst(new Node(des, w));
		this.adj[des].addFirst(new Node(src, w));
   	   }
   }
	
   static void bfs(Graph g, int s)
   {
	Queue<Integer> q = new LinkedList<>();
	
	//0 means not visited, 1 means visited
	int vis[]= new int[g.v];
	
	q.add(s);
	vis[s]++;
	while(!q.isEmpty())
	{
	   int parent = q.poll();
	   po(parent);
	   
	   for(int x: g.adj[parent])
	   {
	   	if(vis[x] == 0)
	   	{
    	   	   q.add(x);

    	   	   //increase the count means visited
    	   	   vis[x]++;
	   	}
	   }
	}
   }
   
   static void bfs_0_1(Graph_Weighted g, int src)
   {
   	Deque<Integer> q = new LinkedList<>();
   	
   	//dis[i] array is the distance of ith node from
   	int dis[] = new int[g.v];
   	Arrays.fill(dis,Integer.MAX_VALUE);
   	
   	q.add(src);
   	dis[src] = 0;
   	
   	while(!q.isEmpty())
   	{
   	   int v = q.pop();
   	   for(int i=0; i<g.adj[v].size(); i++)
   	   {
	      /* If the current distance from the source is more than
	      the distance of parent node from source + dist of nbr from 
	      parent, change it.
	      */
   	      if(dis[g.adj[v].get(i).name]
   	      		> dis[v] + g.adj[v].get(i).weight)
   	      {
   	         dis[g.adj[v].get(i).name]
   	                = dis[v] + g.adj[v].get(i).weight;

   	         if(g.adj[v].get(i).weight == 0)
   	         {
   	            q.addFirst(g.adj[v].get(i).name);
   	         }
   	         else
   	            q.addLast(g.adj[v].get(i).name);
   	      }
   	   }
   	}
   	
   	po("Min. dis of nodes from the source node is: ");
   	for(int i=0; i<dis.length; i++)
   	{
   	   po(i+"th node -> "+dis[i]);
   	}
   }
   
   static void dfs (Graph g, int src)
   {
   	Stack<Integer> s = new Stack<>();
   	
   	int vis[] = new int[g.v];
   	
   	s.push(src);
   	vis[src]++;
   	
   	while(!s.isEmpty())
   	{
   	   int v = s.pop();
   	   po(v);
   	   for(int nbr: g.adj[v])
   	   {	
   	        if(vis[nbr] == 0)
   	   	{
	       s.push(nbr);
   	   	   vis[nbr]++;
   	   	}
   	   }
   	}
   }
   
   static void dfs_rec(Graph g, int i, boolean vis[])
   {	
   		vis[i] = true;
   		po(i);
   		for(int j=0; j<g.adj[i].size(); j++)
   		{
   			int nbr = g.adj[i].get(j);
   			if(!vis[nbr])
	   			dfs_rec(g, nbr, vis);
   		}
   }

   static void po(Object o)
   {
	System.out.println(o);
   }

   public static void main(String[] args)
   {
	Scanner sc = new Scanner(System.in);
	
	
	po("No. of vertices? ");	
	int v = sc.nextInt();
	po("No. of Edges? ");	
	int e = sc.nextInt();
	Graph g = new Graph(v);
	for(int i=0; i<e; i++)
	{
	   po("Enter src and des of "+(i+1)+"th edge: ");
	   g.addEdge(sc.nextInt(), sc.nextInt());
	}
	g.show();
	//bfs(g, 0); 
	//dfs(g, 0);
	dfs_rec(g, 0, new boolean[g.v]);
	
	/*
	po("No. of vertices? ");
	int v = sc.nextInt();
	po("No. of edges? ");
	int e = sc.nextInt();
	Graph_Weighted g = new Graph_Weighted(v);
	for(int i=0; i<e; i++)
	{
	   po("Enter src, des and weight of "+(i+1)+"th edge: ");
	   g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
	}
	g.show();
	bfs_0_1(g, 0);
	*/
	
	//HE_dfs_ques(sc);
   }
   
   public static void HE_dfs_ques(Scanner sc)
   {
   	int n = sc.nextInt();
   	int e = sc.nextInt();
   	Graph g = new Graph(n);
    	for(int i=0; i<e; i++)
    	{
    	   g.addEdge(sc.nextInt()-1, sc.nextInt()-1);
    	}
    	int src = sc.nextInt()-1;
    	
    	int[] vis = new int[n];
    	Stack<Integer> s = new Stack<>();
    	s.push(src);
    	vis[src]++;
    	
    	while(!s.isEmpty())
    	{
    	   int v = s.pop();
    	   po("visited "+v);
    	   for(int x: g.adj[v])
    	   {
    	      if(vis[x] == 0)
    	      {
    	         s.push(x);
    	         vis[x]++;
    	      }
    	   }
    	}
    	
    	int c = 0;
    	for(int i=0; i<n; i++)
    	{
    	   if(vis[i] == 0) c++;
    	}
    	po(c);
   }
   /*
	6 6 0 1 0 2 1 3 2 3 2 4 3 5
	5 6 0 1 1 0 3 0 0 2 1 1 4 0 3 4 1 2 3 0
	12 6 0 1 0 2 1 2 5 6 5 7 10 11
   */
}
