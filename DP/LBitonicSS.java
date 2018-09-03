import java.util.*;
import java.io.*;

public class LBitonicSS {

	/*
	A subsequence of arr[] is called Bitonic if it is first increasing,
	then decreasing.
	Input arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
	Output: 6 (A Longest Bitonic Subsequence of length 6 is 1, 2, 10, 4, 2, 1)

	This problem is a variation of standard
	Longest Increasing Subsequence (LIS) problem.
	Let the input array be arr[] of length n.
	We need to construct two arrays lis[] and lds[] using
	Dynamic Programming solution of LIS problem.
	lis[i] stores the length of the
	Longest Increasing subsequence ending with arr[i].
	lds[i] stores the length of the
	longest Decreasing subsequence starting from arr[i].
	Finally, we need to return the
	max value of lis[i] + lds[i] – 1 where i is from 0 to n-1.
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int[] arr = {12, 11, 40, 5, 3, 1};	
		po(bito(arr));
	}

	/*
	{1, 11, 2, 10, 4, 5, 2, 1};	
		ans is 6

	{12, 11, 40, 5, 3, 1};	
	ans is 5
	*/

	public static int bito(int[] arr){
		int[] lis = new int[arr.length];
		int[] lds = new int[arr.length];

		for(int i=0; i<arr.length; i++){
			lis[i] = 1;
			lds[i] = 1;
		}

		for(int i=1; i<arr.length; i++){
			for(int j=0; j<i; j++){
				if(arr[j] < arr[i] && lis[i] < lis[j]+1){
					lis[i] = lis[j]+1;
				}
			}
		}

		//Building up LDS array by comparing from the back
		for(int i=arr.length-2; i>=0; i--){
			for(int j=arr.length-1; j>i; j--){
				if(arr[j] < arr[i] && lds[i] < lds[j]+1){
					lds[i] = lds[j]+1;
				}
			}
		}

		/*
		Finding the max value of lis[i]+lds[i]-1
		we subtract 1 bcz the number at ith pos is repeated in both.
		*/

		int max = 0;
		for(int i=0; i<arr.length; i++){
			if(lis[i]+lds[i]-1 > max){
				max = lis[i]+lds[i]-1;
			}
		}

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