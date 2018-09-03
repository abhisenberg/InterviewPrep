import java.util.*;
import java.io.*;

public class rings {
/*
	Make rings like matrix like, with input n
	Input : N = 4
	Output : 4 4 4 4 4 4 4
	         4 3 3 3 3 3 4
	         4 3 2 2 2 3 4
	         4 3 2 1 2 3 4
	         4 3 2 2 2 3 4
	         4 3 3 3 3 3 4
	         4 4 4 4 4 4 4

	Input : N = 2
	Output : 2 2 2
	         2 1 2
	         2 2 2

*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		for(int i=(1-n); i<n; i++){
			for(int j=(1-n); j<n; j++){
				int val = 1 + Math.max(Math.abs(i), Math.abs(j));
				p(val+" ");
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