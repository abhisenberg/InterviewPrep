import java.util.*;
import java.io.*;

public class rodcut {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-- > 0){
			int n = sc.ni();
			int[] cuts = new int[3];
			for(int i=0; i<cuts.length; i++){
				cuts[i] = sc.ni();
			}
			po(rodcuts_bu(n, cuts));
		}
	}

	public static int rodcuts_bu(int n, int[] cuts){
		int[] dp = new int[n+1];
		Arrays.fill(dp, -1);
		dp[0] = 0;

		for(int i=0; i<cuts.length; i++){
			for(int m=1; m<=n; m++){
				if(m >= cuts[i] && dp[m-cuts[i]] != -1) {
					dp[m] = Math.max(dp[m], 1 + dp[m - cuts[i]]);
				}
			}
		}
		return dp[n];

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