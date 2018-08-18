import java.util.*;
import java.lang.*;

class Graph_Djikstra_Heap
{

	static final int INF = 999999;

	static class Node
    {
      	int name;
      	int w;

      	public Node(int name, int w)
      	{
      		this.name = name;
      		this.w = w;
      	}
    }

   static class Graph
	{
      int v;	//no. of vertices
	  int e;	//no. of edges
      
      LinkedList<Node>[] edges;
      
      public Graph(int v)
      {  this.v = v;
      	edges = new LinkedList[v];
      	for(int i=0; i<v; i++)
      	{
      		edges[i] = new LinkedList<>();
      	}
      }

      public void addEdge(int src, int des, int w)
      {
      	edges[src].add(new Node(des, w));
      }
      
      @Override
      public String toString()
      {
      	String s = "";
      	for(int i=0; i<v; i++)
      		{  s += i;
      			for(int j=0; j<edges[i].size(); j++)
      			{
      				po("askaf");
      				s += " -> "+edges[i].get(j).name+":"+edges[i].get(j).w+"    ";
      			}
      			s += '\n';
      		}
      		po(s);
      		return s;         
      }
	}
   
   static class Heap
	{
		private ArrayList<Node> data;
		private HashMap<Integer, Integer> pos;
		
		public Heap()
		{
			data = new ArrayList<>();
			pos = new HashMap<>();
		}
		
		public Heap(Node[] arr)
		{	
			this();
			
			for(Node x: arr)
			{
				data.add(x);
				pos.put(x.name, data.size()-1);
			}
			
			//Arranging the heap for the first time
			for(int i= (data.size()/2)-1; i>=0; i--)
			{
				downheapify(i);
			}
		}
		
		//used in creation and deletion of heap
		public void downheapify(int pi)
		{
			int lci = 2*pi + 1;
			int rci = 2*pi + 2;
			
			int mini = pi;
			
			if(lci < data.size() && data.get(lci).w < data.get(pi).w)
				mini = lci;
			else if(rci < data.size() && data.get(rci).w < data.get(pi).w)
				mini = rci;
			
			if(mini != pi)
			{
				swap(mini, pi);
				downheapify(mini);
			}
		}
		
		//generally only used in addition of new element
		public void upheapify(int ci)
		{
			int pi = (ci-1)/2;

			if(pi >= 0 && data.get(ci).w < data.get(pi).w)
			{
				swap(ci, pi);
				upheapify(pi);
			}
		}
		
		public Node get()
		{
			return data.get(0);
		}
		
		public Node pop()
		{
			Node rv = data.get(0);
			swap(0, data.size()-1);
			data.remove(data.size()-1);
			downheapify(0);
			return rv;
		}
		
		public void add(Node ele)
		{
			data.add(ele);
			pos.put(ele.name, data.size()-1);
			upheapify(data.size()-1);
		}
		
		public boolean isHigherPr(int i, int j)
		{
			//for min-heap
			if(data.get(i).w < data.get(j).w)
				return true;
			else
				return false;
		}
		
		public void swap(int i, int j)
		{
			Node ith_data = data.get(i);
			Node jth_data = data.get(j);			
			
			pos.put(ith_data.name, j);
			pos.put(jth_data.name, i);
			
			data.set(i, jth_data);
			data.set(j, ith_data);
		}
		
		public void show()
		{
			po(this.data);
		}
	}

	public static void djikstra(Graph g, int src)
	{
		boolean vis[] = new boolean[g.v];
		int dis[] = new int[g.v];

		for(int i=0; i<g.v; i++)
		{
			dis[i] = INF;
		}

		dis[src] = 0;

		Heap min = new Heap();
		min.add(new Node(src, dis[src]));

		for(int i=0; i<g.v; i++)
		{
			int cv = min.pop().name;
			vis[cv] = true;

			for(int j=0; j<g.edges[cv].size(); j++)
			{
				int nbr = g.edges[cv].get(j).name;
				int nbr_w = g.edges[cv].get(j).w;

				if(!vis[nbr] && dis[nbr] > dis[cv] + nbr_w)
				{	
					dis[nbr] = dis[cv] + nbr_w;
					min.add(new Node(nbr, nbr_w));
				}
			}
		}

		po("The min distances from src are: ");
		for (int i = 0; i < dis.length; i++)
		{
			po("For vertex " + i + " " + dis[i]);
		}
	}
   
	public static void main(String[] args)
   {
     Scanner sc = new Scanner(System.in);
     
     po("Enter no. of vertices: ");
     int v = sc.nextInt();
     po("Enter no. of edges: ");
     int e = sc.nextInt();
     
     Graph g = new Graph(v);
     for(int i=0; i<e; i++)
     {
		po("enter "+i+"th edge");
        g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
     }
     po(g);

     djikstra(g, 0);
   }
  
   public static void po(Object o)
   {
      System.out.println(o);
   }
   
   //6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
}
