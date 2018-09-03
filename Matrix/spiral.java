import java.util.*;
import java.io.*;

public class spiral {

/*
Input:
        1    2   3   4
        5    6   7   8
        9   10  11  12
        13  14  15  16
Output: 
1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10 
    
    ---------->
    ^         |
    |         |
    |         |
    |         V
     <---------
     In this order.

*/
	public static void main(String[] args){
		ShortScan sc = new ShortScan();
	   int T = sc.ni();

	   while(T-- > 0){
	       int m = sc.ni(), n = sc.ni();
	       int[][] arr = new int[m][n];
	       for(int i=0; i<m; i++){
	           for(int j=0; j<n; j++){
	               arr[i][j] = sc.ni();
	           }
	       }

	       /*
	       Here t is top row of the unvisited array
	       		b is bottom row of the unvisited array
	       		l is lefmost column of the unvisited array
	       		r is rightmost column of the unvisited array
	       		dir is the direction of the present traversal.
	       */

	       int t = 0, b = m-1, l = 0, r = n-1, dir = 0;
	       while(t <=b && l <= r){
	           switch(dir){
	               case 0:
	                   for(int i=l; i<=r; i++){
	                       p(arr[t][i]+" ");
	                   }
	                   t++; dir = 1;
	                   break;

	               case 1:
	                   for(int i=t; i<=b; i++){
	                       p(arr[i][r]+" ");
	                   }
	                   r--; dir = 2;
	                   break;

	               case 2:
	                   for(int i=r; i>=l; i--){
	                       p(arr[b][i]+" ");
	                   }
	                   b--; dir = 3;
	                   break;

	               case 3:
	                   for(int i=b; i>=t; i--){
	                       p(arr[i][l]+" ");
	                   }
	                   l++; dir = 0;
	                   break;
	           }
	       }
	       po("");
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