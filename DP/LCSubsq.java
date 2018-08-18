import java.util.*;
import java.io.*;

public class LCSubsq {

	/*
	Given two sequences, find the length of longest subsequence
	present in both of them. Both the strings are of uppercase.
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
			int n1 = sc.ni(), n2 = sc.ni();
			sc.ns();
			String s1 = sc.ns(), s2 = sc.ns();
			//po(s1+", "+s2);

			int[][] lcs = new int[n1+1][n2+1];
			po(lcs_bu(s1, s2, lcs));
			//show2d(lcs);
		}
	}

	/*
	1) Consider the input strings “AGGTAB” and “GXTXAYB”. Last characters match for the strings. So length of LCS can be written as:
			L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)

	2) Consider the input strings “ABCDGH” and “AEDFHR. Last characters do not match for the strings. So length of LCS can be written as:
			L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) )
	*/

	public static int lcs_bu(String s1, String s2, int[][] lcs){
		for(int i=0; i<=s1.length(); i++){
			lcs[i][0] = 0;
		}
		for(int i=0; i<=s2.length(); i++){
			lcs[0][i] = 0;
		}

		for(int i=1; i<=s1.length(); i++){
			for(int j=1; j<=s2.length(); j++){
				if(s1.charAt(i-1) == s2.charAt(j-1)){
					lcs[i][j] = lcs[i-1][j-1]+1;
				} else {
					lcs[i][j] = Math.max(lcs[i][j-1], lcs[i-1][j]);
				}
			}
		}

		return lcs[s1.length()][s2.length()];

	}

/*
1
10
9
cabebxrhen
goubbruvn

*/

//**********************************************************************//	

	public static void po(Object o){
		System.out.println(o);
	}

	public static void p(Object o){
		System.out.print(o);
	}

	public static void show2d(int[][] arr){
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[0].length; j++){
				p(arr[i][j]+" ");
			}
			po("");
		}
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
}