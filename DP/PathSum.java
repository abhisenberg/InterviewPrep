import java.util.*;
import java.io.*;

public class PathSum {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-- > 0){
			int n = sc.ni();
			int[][] arr = new int[n][n]; int[][] dp = new int[n][n];
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					arr[i][j] = sc.ni();
					dp[i][j] = -1;
				}
			}

			/*
			max = 0;

			for(int i=0; i<arr.length; i++){
				int curr = pathmax_td(arr, 0, i, dp);
				max = curr > max ? curr : max;
			}

			po(max);*/

			po(pathmax_bu(arr));
		}
	}

	/*
	RECURSIVE TOP DOWN
	*/
	static int max;
	static int[] my = {-1, 0, 1};
	public static int pathmax_td(int[][] arr, int i, int j, int[][] dp){
		
		if(i < 0 || i >= arr.length || j < 0 || j >= arr.length)
			return 0;

		if(dp[i][j] != -1)
			return dp[i][j];

		if(i == arr.length-1){
			return arr[i][j];
		}


		for(int k=0; k<my.length; k++){
			int curr = pathmax_td(arr, i+1, j+my[k], dp);
			dp[i][j] = (curr + arr[i][j]) > dp[i][j] ? curr + arr[i][j] : dp[i][j];
		}

		//po("for "+i+","+j+" -> "+dp[i][j]);
		return dp[i][j];

	}

	/*
	ITERATIVE BOTTOM UP
	*/
	public static int pathmax_bu(int[][] arr){
		int[][] dp = new int[arr.length][arr.length];

		for(int i=0; i<arr.length; i++){
			dp[0][i] = arr[0][i];
		}

		for(int i=1; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				int upleft = -1, upright = -1;
				if(j>0)
					upleft = dp[i-1][j-1];
				if(j<arr.length-1)
					upright = dp[i-1][j+1];

				dp[i][j] = arr[i][j] + max(dp[i-1][j],
					upleft, upright);
			}
		}

		int max = -1;
		for(int i=0; i<arr.length; i++){
			max = dp[arr.length-1][i] > max ? dp[arr.length-1][i] : max ;
		}
		return max;
	}

	public static int max(int a, int b, int c){
		if(a > b){
			return a > c ? a : c;
		} else {
			return b > c ? b : c;
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
}