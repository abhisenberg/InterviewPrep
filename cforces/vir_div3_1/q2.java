import java.util.*;
import java.io.*;

public class q2 {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int d = sc.ni();
		Node[] arr = new Node[n];
		for(int i=0; i<n; i++){
			arr[i] = new Node();
			arr[i].d = sc.ni();
			arr[i].i = i;
		}

		Node[] sarr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(sarr, new sbd());
		Node[] sarr2 = Arrays.copyOf(sarr, d);
		Arrays.sort(sarr2, new sbi());

		//showArr(sarr2);

		int prev = 0, sum=0; String ans = "";
		for(int i=0; i<sarr2.length-1; i++){
			sum += sarr2[i].d;
			ans += (sarr2[i].i - prev + 1) +" ";
			prev = sarr2[i].i+1;
			//po("*"+ans);
		}
		sum += sarr2[d-1].d;
		ans += ""+(sarr.length-prev);

		po(sum);
		po(ans);
	}

	static class Node {
		int d;
		int i;

		public String toString(){
			return "("+d+", "+i+")";
		}
	}

	static class sbd implements Comparator<Node>{
		public int compare(Node a, Node b){
			return b.d - a.d;
		}
	}

	static class sbi implements Comparator<Node>{
		public int compare(Node a, Node b){
			return a.i - b.i;
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