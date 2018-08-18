import java.util.*;
import java.lang.*;

public class Graph_FloydWarshall
{
	
	public static int INF = 99999;
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
		 
		 //To make the graph directed,comment the below line
		 //edges[des].add(new Node(src, w));
      }
      
      @Override
      public String toString()
      {
         String s = "";
         for(int i=0; i<v; i++)
         {  s += i;
            for(int j=0; j<edges[i].size(); j++)
            {
	       //po("askaf");
               s += " -> "+edges[i].get(j).name+":"+edges[i].get(j).w+"    ";
            }
            s += '\n';
         }
	 po(s);
	 return s;         
      }
      
      public void floydWarshall()
      {
      	int dist[][] = new int[v][v];
      	
      	//initialize the array with infinity
      	for(int i=0; i<v; i++)
      	{
      		Arrays.fill(dist[i], INF);
      	}
      	
      	//Assign the correct distance b/w the vertices whose
      	//edges exist
      	for(int i=0; i<v; i++)
      	{	
      		//the dist from a node to itself is 0
      		dist[i][i] = 0;
      	
      		for(int j=0; j<edges[i].size(); j++)
      		{
      			int nbr = edges[i].get(j).name;
      			int nbr_w = edges[i].get(j).w;
      			
      			dist[i][nbr] = nbr_w;
      		}
      	}
      	
      	/*
      	The outer most loop ("k" vala loop) denotes the intermediate vertex
      	("k" is the intermediate vertex). The inner loops denote the src and
      	destnation (i is src, j is des)
      	*/
      	for(int k=0; k<v; k++)
      	{
      		for(int i=0; i<v; i++)
      		{
      			for(int j=0; j<v; j++)
      			{
      				if(dist[i][k] != INF &&
      					dist[k][j] != INF &&
      					dist[i][j] > dist[i][k] + dist[k][j])
      				{
      					dist[i][j] = dist[i][k] + dist[k][j];
      				}
      			}
      		}
      	}
      	
      	printDist(dist);
      }
      
      public void printDist(int[][] dist)
      {
      	for(int i=0; i<v; i++)
      	{
      		System.out.print(i+": ");
      		for(int j=0; j<v; j++)
      		{
      			if(dist[i][j] == INF)
      				System.out.print("INF ");
      			else
      				System.out.print(dist[i][j]+" ");
      		}
      		po("");
      	}
      }
      
   }
   
   public static void main(String[] args)
   {	
   		Scanner sc = new Scanner(System.in);
   		po("Enter v: ");
   		int v = sc.nextInt();
   		po("Enter e: ");
   		int e = sc.nextInt();
   		
   		Graph g = new Graph(v);
   		for(int i=0; i<e; i++)
   		{
   			g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
   		}
   		po(g);
   		g.floydWarshall();
   		
   }
   
   public static void po(Object o)
   {
   		System.out.println(o);
   }
   //6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
   //4 5 0 1 1 0 2 0 1 2 1 0 3 99 3 1 -300
   //3 3 0 1 5 1 2 -10 0 2 2
}
