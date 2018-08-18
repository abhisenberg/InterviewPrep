import java.util.*;
import java.io.*;

public class qsort {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int[] arr = new int[n];
		for(int i =0; i<arr.length; i++){
			arr[i] = sc.ni();
		}
		quicksort(arr, 0 , arr.length-1);
		show(arr);
	}

	public static void quicksort(int[] arr, int l, int h){
		if(l > h) return;

		int part = partition(arr, l, h);
		quicksort(arr, l, part-1);
		quicksort(arr, part+1, h);
	}

	public static int partition(int[] arr, int l, int h){
		int ele = arr[(l+h)/2];
		
		while(l < h){
			
			while(arr[l] < ele)
				l++;

			while(arr[h] > ele)
				h--;


			if(l < h)
			{
				if(arr[l] == arr[h])
					l++;
				else {
					int temp = arr[l];
					arr[l] = arr[h];
					arr[h] = temp;
				}
			}

		}

		return h;
	}

//*********************************************************************//	

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