import java.util.*;
import java.io.*;

public class nqeen {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
			int n = sc.ni();
			ans = new ArrayList<>();

			for(int i=0; i<n; i++){
				nq(0, i, new int[n], n);
			}
			
			for(int i=0; i<ans.size(); i++){
				showArr(ans.get(i));
			}
			if(ans.size() == 0) po("-1");
		}
	}

	static ArrayList<int[]> ans;

	public static void nq(int row, int col, int[] pcol, int n){
		if(row < 0 || col < 0 || row >= n || col>=n){
			return;
		}
		
		if(row != 0){

			for(int prow=0; prow<row; prow++){
				//check for same row
				if(col == pcol[prow]){
					return;
				}

				//check for left diagonal
				if(col+row == prow+pcol[prow]){
					return;
				}

				//check for right diagonal
				if(col-row == pcol[prow]-prow){
					return;
				}
			}
		}

		pcol[row] = col;

		if(row == n-1){
			ans.add(Arrays.copyOf(pcol, pcol.length));
			return;
		}

		for(int i=0; i<n; i++){
			nq(row+1, i, pcol, n);
		}
	}

	public static void showArr(int[] arr){
		System.out.print("[");
		for(int i=0; i<arr.length; i++){
			System.out.print((arr[i]+1)+" ");
		}
        System.out.print("] ");
		//System.out.print((arr[arr.length-1]+1)+"]");
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