import java.util.*;
import java.io.*;

public class q2 {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int m = sc.ni();
		int q = sc.ni();
		sc.ns();
		String s = sc.ns();
		String t = sc.ns();
		
		int[] last = new int[s.length()];
		Arrays.fill(last, -1);

		for(int i=0; i<=s.length()-t.length(); i++){
			if(s.substring(i, i+t.length()).equals(t)){
				last[i] = i+t.length()-1;
				//i = last[i]+1;
			}
		}

		while(q-->0){
			int c = 0;
			int start = sc.ni()-1;
			int end = sc.ni()-1;
			for(int i=start; i<=end; i++){
				if(last[i] != -1 && last[i] <= end){
					c++;
				}
			}
			po(c);
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