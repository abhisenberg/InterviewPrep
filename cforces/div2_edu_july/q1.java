import java.util.*;
import java.io.*;

public class q1 {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int m = sc.ni();
		int[] arr = new int[n];
		for(int i=0; i<arr.length; i++){
			arr[i] = sc.ni();
		}

		int rem = 0;
		for(int i=0; i<arr.length; i++){
			p(((arr[i]+rem)/m)+" ");
			rem = (arr[i]+rem)%m;
		}
		po("");
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

			public String n(){
				return sc.next();
			}

			public String ns(){
				return sc.nextLine();
			}
	}

	public static void showArr(int[] arr){
		for(int x: arr){
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