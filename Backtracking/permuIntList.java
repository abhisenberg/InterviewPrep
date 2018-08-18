import java.util.*;
import java.io.*;

public class permuIntList {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int[] arr = {1,2,3};
		ans = new ArrayList<>();
		permu(arr, new int[arr.length], 0, new HashSet<Integer>());

		po(ans);
	}

	static ArrayList<ArrayList<Integer>> ans;

	public static void permu(int[] arr, int[] permu, int pos, Set<Integer> vis){
		if(pos == arr.length){
			ans.add(arrToAL(permu));
			return;
		}

		for(int i=0; i<arr.length; i++){
			if(vis.contains(i))
				continue;

			permu[pos] = arr[i];
			
			vis.add(i);
			permu(arr, permu, pos+1, vis);
			vis.remove(i);
		}
	}

	public static ArrayList<Integer> arrToAL(int[] arr){
		ArrayList<Integer> toRet = new ArrayList<>();
		for(int x: arr){
			toRet.add(x);
		}
		return toRet;
	}

	/*
	public static void permu(char[] os, char[] perm, int pos, Set<Integer> vis){
		//pos is the current position to be filled
		if(pos == os.length){
			p(String.valueOf(perm)+" ");
			return;
		}

		for(int i=0; i<os.length; i++){
			if(vis.contains(i))
				continue;

			perm[pos] = os[i];

			vis.add(i);
			permu(os, perm, pos+1, vis);
			vis.remove(i);
		}

		return;
	}
	*/

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
}