import java.util.*;
import java.io.*;

public class q3 {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		/*
		int n = sc.ni();
		int m = sc.ni();
		int[] diff = new int[n];
		int sum = 0, csum = 0;
		for(int i=0; i<n; i++){
			int a = sc.ni();
			sum += a;
			int b = sc.ni();
			csum += b;

			diff[i] = a-b;
		}

		if(m < csum){
			po(-1);
			return;
		}
		po(sum-csum);
		*/

		int sum = 45, csum=33;
		int[] diff = {2,2,2,3,4,6};


		Arrays.sort(diff);
		int[][] dp = new int[diff.length+1][sum-csum+1];
		
		for(int i=1; i<dp.length; i++){
			for(int j=1; j<=sum-csum; j++){
				//po("checking for "+diff[i-1]+" -> "+j);
				dp[i][j] = dp[i-1][j];

				if(j < diff[i-1]){
					continue;
				}

				//po(j+"-"+diff[i-1]+" -> "+dp[i-1][j-diff[i-1]]);
				po(diff[i-1]+" "+j+" => "+dp[i][j]);
				if(diff[i-1] == j){
					po("/");
					dp[i][j] = 1;
				}
				else if(dp[i][j] ==0 && dp[i-1][j-diff[i-1]] != 0){
					po("//");
					dp[i][j] = dp[i-1][j-diff[i-1]]+1;
				} else if(dp[i-1][j-diff[i-1]]+1 < dp[i][j]
					&& dp[i-1][j-diff[i-1]] != 0){
					po("///");
					dp[i][j] = dp[i-1][j-diff[i-1]]+1;
				}
				po(diff[i-1]+" "+j+" => "+dp[i][j]);
			}
		}

		for(int i=0; i<=sum-csum; i++){
			p(i+"  ");
		}
		po("");
		show2d(dp);

	}

	static int cmin;
	public static void f(int[] arr, int sum, int i, int c){
		
		if(i == arr.length){
			cmin = (c < cmin) ? c : cmin;
			return;
		}

		if(sum < 0){
			return;
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
			for(int j=0; j<arr[i].length; j++){
				p(arr[i][j]+"  ");
			}
			po(" ");
		}
	}
}