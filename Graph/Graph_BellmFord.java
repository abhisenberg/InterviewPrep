import java.util.*;
import java.lang.*;

	/*
	Given a graph and a source vertex src in graph, find shortest paths from SRC TO ALL VERTICES in the given graph.
	The graph may contain negative weight edges.
	Dijkstra doesn’t work for Graphs with negative weight edges, Bellman-Ford works for such graphs.
	Dijksra’s algorithm is a Greedy algorithm and time complexity is O(VLogV) (with the use of Fibonacci heap).
	But time complexity of Bellman-Ford is O(VE), which is more than Dijkstra.
	Bellman-Ford is also simpler than Dijkstra and suites well for distributed systems.
	*/

class Graph_BellmFord
{
      static class Graph
   {
      int v;	//no. of vertices
      int e;	//no. of edges
      
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
	     return s;         
      }
	      
/* ************************************************************************************************************************/

	/*
	Input: Graph and a source vertex src
	Output: Shortest distance to all vertices from src. If there is a negative weight cycle,
	then shortest distances are not calculated, negative weight cycle is reported.

	1) This step initializes distances from source to all vertices as infinite and distance to
	source itself as 0. Create an array dist[] of size |V| with all values as infinite except
	dist[src] where src is source vertex.

	2) This step calculates shortest distances. Do following |V|-1 times where |V| is the
	number of vertices in given graph.
	…..a) Do following for each edge u-v
	………………If dist[v] > dist[u] + weight of edge uv, then update dist[v]
	………………….dist[v] = dist[u] + weight of edge uv

	3) This step reports if there is a negative weight cycle in graph. Do following for each edge u-v
	……If dist[v] > dist[u] + weight of edge uv, then “Graph contains negative weight cycle”
	The idea of step 3 is, step 2 guarantees shortest distances if graph doesn’t contain negative
	weight cycle. If we iterate through all edges one more time and get a shorter path for any vertex,
	then there is a negative weight cycle
	*/
	      
      
      public void bellmanFord(int src)
      {
      	 //Initialize the default dis. from src as infinity
         int[] prevDis = new int[v];
         for(int i=0; i<v; i++)
         {
         	if(i == src) prevDis[i] = 0;
         	else prevDis[i] = Integer.MAX_VALUE;
         }
         
         for(int i=0; i<v; i++)
          {
           for(int j=0; j<edges[i].size(); j++)
           {
            if(prevDis[edges[i].get(j).name] >
                prevDis[i] + edges[i].get(j).w)
            {
              prevDis[edges[i].get(j).name] =
                prevDis[i] + edges[i].get(j).w;
            }
           }
          }
         
         po("Min. dis of each vertex from src is: ");
         for(int i=0; i<prevDis.length; i++)
         {
         	System.out.print(i+" : "+prevDis[i]+'\n');
         }
         
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
        g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
     }
     po(g);
     g.bellmanFord(0);
   }
  
   public static void po(Object o)
   {
      System.out.println(o);
   }
   
   //6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
}
