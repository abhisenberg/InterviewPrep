import java.util.*;
import java.lang.*;

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
