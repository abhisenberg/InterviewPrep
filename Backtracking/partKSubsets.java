import java.util.*;
import java.io.*;

public class partKSubsets {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
			int n = sc.ni();
			int[] arr = new int[n];
			int sum = 0;
			for(int i=0; i<arr.length; i++){
				arr[i] = sc.ni();
				sum += arr[i];
			}
			Arrays.sort(arr);

			int k = sc.ni();
			if(sum %k != 0){
				po(0);
			} else {
				if(arr[arr.length-1] > (sum/k)){
					po(0);
				} else {
					boolean[] inset = new boolean[arr.length];
					ksubs(arr, sum/k, -1, inset);
					//showArr(inset);
					int ans = checkArr(inset)? 1 : 0;
					po(ans);
				}
			}
		}
	}

	/*
	1
5
2 1 4 5 6
3

	*/

	/*
	We return int,
	-1 -> The solution can and will not be found on this path
	0 -> The solution isn't found including this number but can
		be found if further traveled
	1 -> The solution is found
	2 -> Do nothing
	*/

	public static boolean checkArr(boolean[] arr){
		boolean ans = true;
		for(boolean x: arr){
			ans = ans && x;
		}
		return ans;
	}

	public static int ksubs(int[] arr, int sum, int pos, boolean[] vis){

		if(sum < 0){
			return -1;
		}

		if(sum == 0){
			//po("ans found at "+arr[pos]);
			return 1;
		}

		int i = pos + 1;
		for(; i<arr.length; i++){
			if(vis[i])
				continue;

			vis[i] = true;
			//po("Sending "+arr[i]);

			int ans = ksubs(arr, sum-arr[i], i, vis);
			if(ans == -1){
				vis[i] = false;
				//po("Removing "+arr[i]);
				return 0;
			}
			if(ans == 0){
				//po("Removing "+arr[i]);
				vis[i] = false;
				continue;
			}
			if(ans == 1){
				break;
			}
		}

		return 2;
	}

	/*
	public static boolean ksubs(int[] arr, int sum, int pos, boolean[] vis){

		if(sum < 0){
			return false;
		}

		if(sum == 0){
			return true;
		}

		int i = pos + 1;
		for(; i<arr.length; i++){
			if(vis[i])
				continue;

			vis[i] = true;
				
			if(sum - arr[i] < 0)
				return false;

			if(!ksubs(arr, sum-arr[i], i, vis)){
				po("removing "+arr[i]);
				vis[i] = false;
			}
		}

		return false;
	}*/

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

	public static void showArr(boolean[] arr){
		for(boolean x: arr){
			p(x+" ");
		}
		po("");
	}
}