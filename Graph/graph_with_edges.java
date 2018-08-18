   import java.util.*;
   import java.lang.*;

   /*
   Kruskal algorithm is used to find the MST in an undirected graph.
   It works on negative weight graphs.
   */


   public class graph_with_edges
      {
      	class Edge implements Comparable<Edge>
      	{
      	   int src, des, weight;
      	   
      	   @Override
      	   public int compareTo(Edge other)
      	   {
      	      return this.weight - other.weight;
      	   }
      	   
      	   public String toString()
      	   {
      	      return src+"----"+des+"  => "+weight;
      	   }
      	};
      	
      	int v, e;
      	Edge[] all_edges;  /* The array all_edges[] stors all the edges but
   	 not in any order (you cannot access the edge of a node with
   	 the node number/name, the edges are just stored in the order
   	 they are entered.*/
   	

   	//Material for disjoint sets: Parent and rank array for each vertex
      	int[] parent;
      	int[] ranks;
      	
   	public graph_with_edges(int v, int e)
      	{
      	   this.v = v;
      	   this.e = e;
      	   all_edges = new Edge[e];
      	   for(int i=0; i<e; i++)
      	      all_edges[i] = new Edge();
      	   
      	   parent = new int[v];
      	   ranks = new int[v];
      	}
      	
      	
   	void addEdge(int v1, int v2, int weight, int index)
   	{
   	   all_edges[index].src = v1;
   	   all_edges[index].des = v2;
   	   all_edges[index].weight = weight;
   	}
      	
   	//Find the representative of the set that contains i
      	int findSet(int i)
      	{
      	   //If i is it's own parent, then return it
      	   if(parent[i] == i) return i;
      	   
      	   //If i is not the representative, then recursively call
      	   //findSet() on its parent
      	   int result = findSet(parent[i]);
      	   parent[i] = result;
      	   return result;
      	}
      	
      //This function finds the representatives of both the elements
      	//and then puts one of them as the child of the other, according
      	//the ranks.
      	void union(int i, int j)
      	{
      	
    	   //po("calling union on "+i+" and "+j);
      	   int i_rep = this.findSet(i);
      	   int j_rep = this.findSet(j);
      	   
      	   //Both the elements are in same set, no need to
      	   //change anything
      	   if(i_rep == j_rep)
      	   {
      	      return;
      	   }
      	   
   	   //Make the lesser-ranked-vertex child of the higher-ranked-vertex
      	   if(ranks[i_rep] < ranks[j_rep])
      	   {
      	      parent[i_rep] = j_rep;
      	   }
      	   else if(ranks[j_rep] > ranks[i_rep])
      	   {
      	      parent[j_rep] = i_rep;
      	   }
      	   else
      	   {
      	      //if the ranks are equal, doesn't matter which becomes child 
      	      //and which becomes parent, just increase the rank of the node
      	      //which becomes parent
      	      parent[j_rep] = i_rep;
      	      ranks[i_rep]++;
      	   }
      	   
      	   //show(parent);
      	   //show(ranks);
      	}
      	
      	void kruskal_MST()
      	{
      	   //The number of edges in MST = no. of vertices - 1
      	   Edge[] result = new Edge[this.v - 1];
         	   for(int i=0; i<this.v-1; i++)
      	      result[i] = new Edge();
      	   
      	   //Sort the edges in order of lower-to-higher weights
   	   Arrays.sort(this.all_edges);
   	   
   	   for(int i=0; i<this.v; i++)
   	   {
   	      parent[i] = i;
   	      ranks[i] = 0;
   	   }
   	   
   	   int res_ind=0;
   	   for(int cetc=0; cetc<all_edges.length; cetc++)
   	   {
   	      
   	      int v1_rep = findSet(all_edges[cetc].src);
   	      int v2_rep = findSet(all_edges[cetc].des);
   	      
   	      //If the representatives of both sets are not equal
   	      //then include this edge in result
   	      //and call union function on them
   	      if(v1_rep != v2_rep)
   	      {
   	         //po("adding this edge to the result: "+all_edges[cetc]);
   	         result[res_ind] = all_edges[cetc];
   	         union(v1_rep, v2_rep);
   	         res_ind++;
   	      }
   	   }
   	   
   	   /*
   	   po("The resulting edges of MST are: ");
   	   for(int i=0; i<result.length; i++)
   	      po(result[i]);
   	   */
   	   
   	      
   	   /*
   	   to show sum of weights of mst*/
   	   int sum=0;
   	   for(int i=0; i<result.length; i++)
   	      sum += result[i].weight;
   	        po(sum);
      	}
      	
      	public static void show(int[] arr)
      	{
   	   for(int i=0; i<arr.length; i++)
   	   {
   	      System.out.print(i+":"+arr[i]+", ");
   	   }
   	   po("");
      	}
      	
      	public static void main(String[] args)
      	{
      	   Scanner sc = new Scanner(System.in);
      	   
      	   /*
      	   po("Enter the number of vertices: ");
      	   int v = sc.nextInt();
              po("Enter the number of edges: ");
      	   int e = sc.nextInt();
      	   graph_with_edges g = new graph_with_edges(v, e);
      	   for(int i=0; i<e; i++)
      	   {
      	      po("Enter v1, v2 & weight of the "+i+"th edge: ");
      	      g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt(), i);
      	   }
      	   g.kruskal_MST();
      	   */
      	   
      	   HE_ques(sc);
      	}
      	
      	public static void HE_ques(Scanner sc)
      	{
      	   int v = sc.nextInt();
      	   int e = sc.nextInt();
      	   graph_with_edges g = new graph_with_edges(v,e);
      	   for(int i=0; i<e; i++)
      	   {
      	     g.addEdge(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), i); 
      	   }
      	   g.kruskal_MST();
      	}
      	
      	public static void po(Object o)
      	{
      	   System.out.println(o);
      	}
   	//4 5 0 1 10 0 2 6 2 3 4 0 3 5 1 3 15
      }
