import java.util.*;
import java.io.*;

public class KMP {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		String pattern = sc.ns(); //PATTERN
		int[] lps = new int[pattern.length()+1];
		makeLps(pattern, lps);
		showArr(lps);

		String text = sc.ns(); //TEXT
		po("found at index "+search(text, pattern, lps));
	}

	static void makeLps(String p, int[] lps){
		/*
		i traverses to check the prefix and j traverses to check
		the suffix.
		*/
		int i=0, j=1;
		while(j < p.length()){
			if(p.charAt(i) == p.charAt(j)){
				lps[j+1] = lps[j]+1;
				i++;
				j++;
			} else {
				if(i != 0){
					i = 0;
				} else {
					lps[j+1] = 0;
					j++;
				}
			}
		}
	}

	//ababcabcabababd
	static int search(String text, String pattern, int[] lps){
		int i=0, j=0;
		while(j < pattern.length() && i < text.length()){
			po("checking i="+i+", j="+j);
			if(text.charAt(i) == pattern.charAt(j)){
				i++;
				j++;
			} else {
				if(j != 0){
					if(lps[j] == 0){
						j = 0;
					} else {
						j = lps[j];
					}
				}
				else {
					i++;
				}
			}
		}
		 
		if( j == pattern.length()){
			return i-pattern.length();
		} else{
			return -1;
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