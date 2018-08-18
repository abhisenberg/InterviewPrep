import java.util.*;
import java.io.*;

public class LPalind {

	/*
	There are two problems regarding longest palindromes:

	1) Longest Palindromic Subsequence - Which means non-contiguous
	palindromes in a string. For ex: in AGBDBA, the longest pal. subsequence
	is ABDBA.

	2) Longest Palindromic Substring - Which means conitguous palindrome
	in the string. For ex: in AGBDBA, the longest pal. substring is BDB.
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		String s = sc.ns();
		//po(lpalSqnce_bu(s));
		lpalSstring_bu(s);
	}

	/*
	~~LP Subsequence~~: The idea is to construct a matrix of size N*N, in which
	the index (i,j) will store "The length of the longest palindrome present
	in the subsequence contained by (i,j).", i.e. the index (1,5) will store
	the length of the longest palindrome that is present b/w index 1 and 5
	in the string. Index (3,3) denotes the length of palindrome present
	in the subsequence starting from 3 and ending at 3, which is just a
	single character, hence it will be 1, and so on.

	The idea is to search for all palindromes of length 1, then length 2,
	then 3, 4... upto the length of string. And for every (len+1) we will
	make use of the data of (len).

	Suppose two characters at indices i and j in the string do not match,
	then the value of dp[i][j] will be the max of dp[i-1][j] & dp[i][j-1],
	meaning, the max of "the result excluding the character at i while at
	the same time including the character at j" and "the result including
	the character at i while excluding the character at j".

	And in the case when the two chars at indices i and j match, the answer
	dp[i][j] is 2 + dp[i-1][j-1]. The "+2" is because we have added the 
	two chars to the palindromic subsequence upto that point, denoted by
	dp[i-1][j-1];

	There is another approach: reverse the string and apply LCS on both of
	these strings.
	*/

	public static int lpalSqnce_bu(String s){
		
		int[][] dp = new int[s.length()][s.length()];

		/*First we will find and set the length of palindrome as 1
		for all the single characters.
		*/
		for(int i=0; i<dp.length; i++){
			dp[i][i] = 1;
		}

		/*
		Now finding the palindromes for length 2 and above.
		*/
		for(int l=2; l<=s.length(); l++){
			for(int start = 0; start <= s.length()-l; start++){
				int end = start+l-1;

				if(s.charAt(start) == s.charAt(end)){
					dp[start][end] = 2 + dp[start+1][end-1];
				} else 
					dp[start][end] = Math.max(
						dp[start+1][end], dp[start][end-1]);
			}
		}

		return dp[0][s.length()-1];
	}

	/*
	~~LP Substring~~: 
	*/
	public static int lpalSstring_bu(String s){
		int[][] dp = new int[s.length()][s.length()];

		for(int i=0; i<dp.length; i++){
			dp[i][i] = 1;
		}

		for(int l = 2; l<=s.length(); l++){
			for(int be = 0; be <= s.length()-l; be++){
				int en = be+l-1;

				if(s.charAt(be) == s.charAt(en)){
					if(en-be == 1){
						dp[be][en] = 2;
					} else {
						if(dp[be+1][en-1] != 0)
							dp[be][en] = dp[be+1][en-1] + 2;
						else
							dp[be][en] = 0;
					}
				} else {
					dp[be][en] = 0;
				}
			}
		}

		/*
		Find the longest length from the dp[][] array by traversing it,
		and note down the indices of the palindrome.
		*/
		int pi = -1, pj = -1, max = -1;
		
		for(int i=0; i<dp.length; i++){
			for(int j=0; j<dp.length; j++){
				if(dp[i][j] > max){
					max = dp[i][j];
					pi = i;
					pj = j;
				}
			}
		}

		po(s.substring(pi,pj+1));
		return max;

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

	public static void show2d(int[][] arr){
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				p(arr[i][j]+" ");
			}
			po("");
		}
	}
	public static void showArr(int[] arr){
		for(int x: arr){
			p(x+" ");
		}
		po("");
	}
}