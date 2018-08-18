import java.util.*;
import java.io.*;

public class mergesort {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int[] arr = new int[n];
		for(int i=0; i<n; i++){
			arr[i] = sc.ni();
		}
		arr = mergesort(arr, 0 , arr.length-1);
		show(arr);
	}

	public static int[] mergesort(int[] arr, int l, int h){
		if(l == h){
			return new int[] {arr[l]};
		}

		int mid = (l+h)/2;
		int[] left_sorted = mergesort(arr, l, mid);
		int[] right_sorted = mergesort(arr, mid+1, h);
		return merge_lists(left_sorted, right_sorted);
	}

	public static int[] merge_lists(int[] a, int[] b){
		int[] c = new int[a.length + b.length];
		int i = 0, j = 0, k = 0;

		while(i<a.length && j<b.length){

			if(a[i] <= b[j]){
				c[k] = a[i];
				i++;
			} else {
				c[k] = b[j];
				j++;
			}

			k++;
		}

		while(i < a.length){
			c[k] = a[i];
			k++;
			i++;
		}

		while(j < b.length){
			c[k] = b[j];
			k++;
			j++;
		}

		return c;
	}

//***************************************************************************//

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