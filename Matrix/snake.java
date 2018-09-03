import java.util.*;
import java.io.*;

public class snake {

	/*
	Input:
		1  2  3  4
		8  7  6  5
		9  10 11 12
		16 15 14 13

	Output:
		1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16

		---------->
		          |
		          V
		<----------
		|
		V
		---------->
		          |
		          V
		<----------

		
		We traverse all rows. For every row,
		we check if it is even or odd. If even,
		we print from left to right else print from right to left.
	*/
	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-- > 0){
		    int n = sc.ni();
		    int[][] arr = new int[n][n];
		    for(int i=0; i<n; i++){
		        for(int j=0; j<n; j++){
		            arr[i][j] = sc.ni();
		        }
		    }
		    
		    for(int i=0; i<n; i++){
		        if(i%2 == 0){
		            for(int j=0; j<n; j++){
		                p(arr[i][j]+" ");
		            }
		        } else {
		             for(int j=n-1; j>=0; j--){
		                p(arr[i][j]+" ");
		            }
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