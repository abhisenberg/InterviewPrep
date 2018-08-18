import java.util.*;
import java.io.*;

/*
Given an array, the task is to divide it into two sets S1 and S2
such that the absolute difference between their sums is minimum.
*/

public class minsumpart {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni()	;
		while(t-- > 0){
			int n = sc.ni();
			int sum = 0;
			int[] arr = new int[n];
			for(int i=0; i<arr.length; i++){
				arr[i] = sc.ni();
				sum += arr[i];
			}

			po(md_rec(arr, 0, 0, 0));
			/*
			int[] dp = new int[n];
			Arrays.fill(dp, -1);
			po(md_td(arr, 0, 0, 0, dp));
			*/
		}
	}

	/*
	SIMPLE RECURSIVE SOLUTION
	md stands for min diff
	*/
	public static int md_rec(int[] arr, int sum1, int sum2, int i){
		if(i == arr.length)
			return (sum1 - sum2) < 0 ? -1*(sum1-sum2) : sum1-sum2;

		po(arr[i]+" "+sum1);

		return Math.min(md_rec(arr, sum1+arr[i], sum2, i+1),
					md_rec(arr, sum1, sum2+arr[i], i+1));
	}

	/*
	TOP DOWN RECURSIVE
	*/
	public static int md_td(int[] arr, int sum1, int sum2, int i, int[] dp){
		if(i == arr.length)
			return (sum1 - sum2) < 0 ? -1*(sum1-sum2) : sum1-sum2;

		if(dp[i] != -1)
			return dp[i];

		dp[i] = Math.min(md_td(arr, sum1+arr[i], sum2, i+1, dp),
					md_td(arr, sum1, sum2+arr[i], i+1, dp) );
		return dp[i];
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
}