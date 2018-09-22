import java.util.*;
import java.lang.*;

/*
Prims is an algorithm for finding the minimum spanning tree.
It works on undirected graph.
It works by adding vertices into a growing MST. Greedy approach.
*/

class Graph_Prims
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
		 edges[des].add(new Node(src, w));
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

/************************************************************************************************************************/
		 /*
		 The difference between Dijkstra and Prims is only in the relaxation step:
		 
		 In Prims
		 If node_in_mst(v)=false and graph[u][v] < dist[v] :
			dist[v] = graph[u][v]
		 Note here , We are just updating the distance of v with the minimum neighbor edge weight.
		 
		 
		 In Dijkstra
		 if node_in_shortest_path_set(v)=false and dist[u]+ graph[u][v] < dist[v]:
			dist[v] = dist[u] + graph[u][v]
		 Note here , We are updating the distance of v
		 with the minimum neighbor edge weight + distance of source.
		 */
      
      public void prims(int src)
      {
      	/*
      	There are 3 arrays:
      	inMST[] : to store the nodes that are included in MST
      	notIn[] : to store the "key" of the nodes that are not included yet,
      				the key is infinity for all nodes (except source) at begining
      				and then as the nodes get unlocked one by one, the key is 
      				updated to their weight.
      				When we search for the next node (with min weight), the one
      				with lowest key is selected.
      	parent[] : it stores the parent of the node that is just unlocked,
      				i.e. it store the node which unlocked the current node.
      	*/
		boolean inMST[] = new boolean[v];
		int notIn[] = new int[v];
		int parent[] = new int[v];
		
		/*
		Apart from the source node, set the key of all the nodes to infinity
		*/
		for(int i=0; i<v; i++)
		{
			if(i != src) notIn[i] = Integer.MAX_VALUE;
		}
		
		/*
		The outermost loop runs for 'v' number of times, since the MST contains 
		'v' vertices.
		*/
		for(int k=0; k<v; k++)
		{
			int ni = prims_nextVrtx(notIn, inMST);  //ni = next vertex index
			inMST[ni] = true;
			
			for(int i=0; i<edges[ni].size(); i++)
			{	
				int pe = edges[ni].get(i).name; //pe = potential edge
				int pew = edges[ni].get(i).w;   //pew = potential edge weight
				
				/*
				If the current edge weight is less than the prevsly stored 
				weight in the notIn array, then update it (since a vertex can
				have several paths to reach to it, and we need to only store 
				the path with least weight. 
				*/
				if(pew < notIn[pe]) 
				{
					parent[pe] = ni;
					notIn[edges[ni].get(i).name] = edges[ni].get(i).w;
				}
			}
		}
		
		/*
		For printing the vertices number and the weight of the edge
		between them.
		*/
		for(int i=0; i<parent.length; i++)
		{
			int from = i;
			for(int j=0; j<edges[from].size(); j++)
			{
				if(edges[from].get(j).name == parent[i])
				{
					po(from+" --- "+parent[i]+" : "+edges[from].get(j).w);
				}
			}
		}
		
      }
      
      /*
		Here we find the next node (one with min weight), to do this we search
		the whole notIn[] array for the least key value and return the index.
	  */
      public int prims_nextVrtx(int[] notIn, boolean[] inMST)
      {
      	int min = Integer.MAX_VALUE;
      	for(int i=0; i<notIn.length; i++)
      	{
      		if(!inMST[i] && notIn[i] < min)
      		{	
      			return i;
      		}
      	}
      	return -1;
      }
   }
   
   public static void main(String[] args)
   {
   		Scanner sc = new Scanner(System.in);
   		po("Enter the no. of vertices: ");
   		int v =  sc.nextInt();
   		po("Enter the no. of edges: ");
   		int e = sc.nextInt();
   		Graph g = new Graph(v);
   		for(int i=0; i<e; i++)
   		{
   			g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
   		}
   		po(g);
   		g.prims(0);
   }
   
   public static void po(Object o)
   {
   		System.out.println(o);
   }
   
   // 4 5 0 2 1 0 1 6 2 1 7 2 3 14 1 3 8
   // 4 5 0 2 1 0 1 6 2 1 7 2 3 4 1 3 8
}
}
