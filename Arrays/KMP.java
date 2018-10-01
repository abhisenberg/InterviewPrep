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
		We make LPS array one size bigger so that the initial element is always zero,
		for ex: for pattern "ababd", the LPS array will be:
		
		LPS index: 0 1 2 3 4 5
			     a b a b d
		LPS value: 0 0 0 1 2 0
			   ^
		Pattern index will be started from here while finding the pattern in the string.
		i traverses to check the prefix and j traverses to check
		the suffix.
		*/
		int i=0, j=1;
		while(j < p.length()){
			/*
			Values are updated at j+1 because the first element of the array is kept empty.
			*/
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
		
		/*
		i traverses the big text, j traverses the pattern.
		If pattern matches, increment both i and j, to check the next alphabets, otherwise
		take j to the index where the previously mathced alphabet was earlier present in the string.
		It should have been lps[j-1] (since j-1 is the last matched index, j is not matched)
		but LPS starts with a blank element so j-1'th element's lps is basically at j index.
		Hence we take j to lps[j] which is the index of the last-mathced character's earlier
		occurence in the pattern.

		If j is already 0, then it means we are checking the beginning of the string, which means
		we cannot go any farther than this, so we increment i, to check for the next alphabet in text.
		*/
		
		while(j < pattern.length() && i < text.length()){
			if(text.charAt(i) == pattern.charAt(j)){
				i++;
				j++;
			} else {
				if(j != 0){
					j = lps[j];
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
