import java.util.*;
import java.lang.*;

public class Graph_CCC
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
      
      //For undirected, only enter the names of src and des, default
      //weight is 0.
      public void addEdge(int src, int des)
      {
		 edges[src].add(new Node(des, 0));
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
   
   public static boolean[] dfs(Graph g, int src, boolean vis[])
   {
   		Stack<Integer> s = new Stack<>();
   		s.push(src);
   		
   		//boolean vis[] = new boolean[g.v];
   		
   		while(!s.isEmpty())
   		{
   			int cv = s.pop();
   			vis[cv] = true;
   			
   			po(cv);
   			
   			for(int i=0; i<g.edges[cv].size(); i++)
   			{
   				int nbr = g.edges[cv].get(i).name;
   				//po("*"+nbr);
   				if(!vis[nbr])
   				{
   					s.push(nbr);
   				}
   			}
   		}
   		
   		return vis;
   }
   
   public static void CCC(Graph g)
   {
   		boolean vis[] = new boolean[g.v];
		
		int cc = 0;		//no. of connected components
		for(int i=0; i<g.v; i++)
		{
			if(!vis[i])
			{	
				cc++;
				vis = dfs(g, i, vis);
			}
		}
		po("No. of cc: "+cc);
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
        //g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
        g.addEdge(sc.nextInt(), sc.nextInt());
        
     }
     po(g);
     //dfs(g, 0);
     CCC(g);
   }
  
   public static void po(Object o)
   {
      System.out.println(o);
   }
   
   //6 8 0 3 6 0 1 4 0 2 5 3 4 2 4 5 2 5 4 1 2 5 4 1 2 -3
   //10 6 0 1 1 2 0 2 3 4 3 5 8 9
}
