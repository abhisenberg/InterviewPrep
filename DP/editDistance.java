import java.util.*;
import java.io.*;

public class Template {

	/*
	Minimum number of deletions and insertions to transform one
	string into another
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		String s1 = "geek";
		String s2 = "gesek";
		po(convert(s1, s2));
	}

	/*
	If only removal and addition of characters is allowed
	(Replace operation is not allowed)

	The idea is to find the LCS (longest common subsequence) length
	-->str1 and str2 be the given strings.
	-->m and n be their lengths respectively.
	-->len be the length of the longest 
	   common subsequence of str1 and str2
	-->// minimum number of deletions 
	   minDel = m - len
	-->// minimum number of Insertions 
	   minInsert = n - len
	*/

	public static int convert(String s1, String s2){
	    int[][] dp = new int[s1.length()+1][s2.length()+1];
	    
	    for(int i=0; i<s1.length(); i++){
	        dp[i][0] = 0;
	    }
	    
	    for(int i=0; i<s2.length(); i++){
	        dp[0][i] = 0;
	    }
	    
	    //Just finding LCS
	    for(int i=1; i<=s1.length(); i++){
	        for(int j=1; j<=s2.length(); j++){
	            if(s1.charAt(i-1) == s2.charAt(j-1)){
	                dp[i][j] = 1+dp[i-1][j-1];
	            } else {
	                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
	            }
	        }
	    }
	    
	    //LCS length
	    int lcs = dp[s1.length()][s2.length()];
	    
	    /*
	    calculate (m - len) + (n - len)
	    */
	    return Math.max((s1.length()-lcs), (s2.length()-lcs));
	}



	/*
	If removal, addition and replace are available
	*/
	public static int convertWithReplace(String s1, String s2){
	    int[][] dp = new int[s1.length()+1][s2.length()+1];
	    
	    //Calulcate the cost of constructing the strings from a null string
	    for(int i=0; i<=s1.length(); i++){
	        dp[i][0] = i;
	    }
	    
	    for(int i=0; i<=s2.length(); i++){
	        dp[0][i] = i;
	    }
	    
	    /*
	    If the current character is same, it means no cost is involved
	    otherwise we caluclate 1 + min(left, top, topleft)
	    */
	    for(int i=1; i<=s1.length(); i++){
	        for(int j=1; j<=s2.length(); j++){
	            if(s1.charAt(i-1) == s2.charAt(j-1)){
	                dp[i][j] = dp[i-1][j-1];            //No operation for current char
	            } else {
	                dp[i][j] = 1 + min(dp[i-1][j-1],    //Replace
	                                dp[i-1][j],         //Add
	                                dp[i][j-1]);        //Remove
	            }
	        }
	    }
	    
	    return dp[s1.length()][s2.length()];
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