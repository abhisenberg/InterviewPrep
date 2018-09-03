import java.util.*;
import java.io.*;

public class minNumberJumps {

	/*
	Given an array of integers where each element represents the max
	number of steps that can be made forward from that element.
	Write a function to return the minimum number of jumps to reach
	the end of the array (starting from the first element).
	If an element is 0, then we cannot move through that element.

		Examples:

		Input :  arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
		Output :  3 (1-> 3 -> 8 -> 9)	
	*/

	/*
	DP BOTTOM UP APPROACH
	O(N^2) TIME, O(N) SPACE
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
		    int n = sc.ni();
		    int[] arr = new int[n];
		    int[] dp  = new int[n];

		    for(int i=0; i<n; i++){
		        arr[i] = sc.ni();
		        dp[i] = Integer.MAX_VALUE;
		    }
		    
		    /*
		    Create an array dp of size n, which stores
		    the min number of jumps required to reach a particular
		    index i. Initially its infinite.

		    For each index i, we go to the indices reachable by it and 
		    whether it can be reached from this index at a lesser no. of
		    jumps that it already can.
		    */

		    dp[0]=0;
		    for(int i=0; i<n; i++){

		    	//It means the element is not reachable at all
		    	//as a result, further elements are also not reachable.
		        if(dp[i] == Integer.MAX_VALUE)
		            break;
		            
		        for(int j=1; j+i<n && j<=arr[i]; j++){
		               if(dp[i+j] > dp[i]+1){
		                   dp[i+j] = dp[i]+1;
		                }
		        }
		    }
		    
		    if(dp[n-1] == Integer.MAX_VALUE)
		        po("-1");
		    else 
		        po(dp[n-1]);
		}
	}



	/*BFS APPROACH
		O(N) TIME, O(N) SPACE

		The node class stores the index of the current element and the
		number of jumps it took to reach this element (NOT the no. of jumps
		it can make). 
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
		    int n = sc.ni();
		    int[] arr = new int[n];
		    for(int i=0; i<n; i++){
		        arr[i] = sc.ni();
		    }
		    
		    Queue<Node> q = new LinkedList<>();
		    boolean[] vis = new boolean[n];
		    
		    Node cn = new Node(0, 0);
		    q.add(cn);
		    vis[0] = true;
		   
		   int ans = -1;
		   
		    while(!q.isEmpty()){
		        cn = q.poll();
		        
		        if(cn.index == n-1){
		            ans = cn.jumps;
		            break;
		        }
		        
		        for(int i=1; i<=arr[cn.index]; i++){
		            int nbr_indx = i+cn.index;
		            
		            if(nbr_indx < n && !vis[nbr_indx]){
		                q.add(new Node(nbr_indx, cn.jumps+1));
		                vis[nbr_indx] = true;
		            }
		        }
		   }
		   
		   po(ans);
		}
	}

	static class Node {
	    int index;
	    int jumps;
	    
	    public Node(int index, int jumps){
	        this.index = index;
	        this.jumps = jumps;
	    }
	}


	/*
	TRICKY APPROACH
	O(N) TIME, O(1) SPACE
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
		    int n = sc.ni();
		    int[] arr = new int[n];
		    for(int i=0; i<n; i++){
		        arr[i] = sc.ni();
		    }
		    
		    boolean isPoss = true;
		    int maxReach = arr[0], steps = arr[0], jumps = 1;
		    
		    for(int i=1; i<arr.length; i++){
		        if(i > maxReach){
		            isPoss = false;
		            break;
		        }
		        
		        if(i == arr.length-1){
		            break;
		        }
                
		        maxReach = Math.max(maxReach, arr[i]+i);
		        
		        steps--;
		        
		        if(steps < 0){
		            isPoss = false;
		            break;
		        }
		        
		        if(steps == 0){
		            steps = maxReach - i;
		            jumps++;
		        }
		    }
		    if(!isPoss) po(-1);
		    else po(jumps);
		    
		}
	}

//**********************************************************************//	

	public static void po(Object o){
		System.out.println(o);
	}

	public static void p(Object o){
		System.out.print(o);
	}

	static class ShortScan {
			Scanner sc;

			public ShortScan(){
				sc = new Scanner (System.in);
			}

			public int ni(){
				return sc.nextInt();
			}

			public long nl(){
				return sc.nextLong();
			}

			public String n(){
				return sc.next();
			}

			public String ns(){
				return sc.nextLine();
			}

			public int[] narr(int n){
				int[] arr = new int[n];
				for(int i=0; i<arr.length; i++){
					arr[i] = sc.nextInt();
				}
				return arr;
			}
	}

	public static <T> void showArr(T[] arr){
		for(T x: arr){
			p(x+" ");
		}
		po("");
	}

	public static void show2d(int[][] arr){
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				p(arr[i][j]+" ");
			}
			po("");
		}
	}
}	