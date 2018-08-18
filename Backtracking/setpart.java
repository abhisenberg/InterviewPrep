import java.util.*;
import java.io.*;

public class setpart {

	/*
	Given a set of numbers, check whether it can be partitioned
	into two subsets such that the sum of elements in both 
	subsets is same or not.
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
			int n = sc.ni();
			int[] arr = new int[n];
			Done[] done = new Done[n];

			int sum = 0;
			for(int i=0; i<n; i++){
				arr[i] = sc.ni();
				done[i] = new Done();
				sum += arr[i];
			}

			if(sum%2 != 0) po("NO");
			else {
				if (sp(sum/2, arr, 0, done)) po("YES");
				else po("NO");
			}
		}
	}

	static class Done {
		boolean vis, ans;

		public Done(){
			this.vis = false;
			this.ans = false;
		}		

		public Done(boolean vis, boolean ans){
			this.vis = vis;
			this.ans = ans;
		}
	}

	public static boolean sp(int remSum, int[] arr, int curr, Done[] done){
		if(curr >= arr.length || remSum < 0){
			return false;
		}

		if(remSum == 0){
			return true;
		}

		if(done[curr].vis)
			return done[curr].ans;

		done[curr].vis = true;

		done[curr].ans = (sp(remSum - arr[curr], arr, curr+1, done)
			|| sp(remSum, arr, curr+1, done));

		return done[curr].ans;
	}

	public static void po(Object o){
		System.out.println(o);
	}

	static class ShortScan {
			Scanner sc;

			public ShortScan(){
				sc = new Scanner (System.in);
			}

			public int ni(){
				return sc.nextInt();
			}
	}
}