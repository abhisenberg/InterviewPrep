import java.util.*;
import java.io.*;

public class radixsort {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int[] arr = new int[n];
		for(int i=0; i<n; i++){
			arr[i]  = sc.ni();
		}
		radixsort(arr);
		show(arr);
	}

	public static void radixsort(int[] arr){
		int max = Integer.MIN_VALUE;
		for(int x: arr){
			max = x > max ? x : max;
		}

		for(int tenpow = 1; (max/tenpow) > 0; tenpow *= 10){
			countsort(arr, tenpow);
		}
	}

	public static void countsort(int[] arr, int tenpow){
		int[] count = new int[10];
		int[] output = new int[arr.length];

		for(int i=0; i<arr.length; i++){
			count[(arr[i]/tenpow)%10]++;
		}

		for(int i=1; i<count.length; i++){
			count[i] += count[i-1];
		}

		/* To keep the order, start from back side */
		for(int i=arr.length-1; i>=0; i--){
			output[count[(arr[i]/tenpow)%10] - 1] = arr[i];
			count[(arr[i]/tenpow)%10]--;
		}

		/*
		Copy the output array to arr[], so that arr[] now
        contains sorted numbers according to curent digit
		*/
		for(int i=0; i<arr.length; i++){
			arr[i] = output[i];
		}

	}

//**********************************************************************//	

	public static void show(int[] arr){
		for(int x: arr){
			p(x+" ");
		}
		po("");
	}

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