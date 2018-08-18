import java.util.*;
import java.io.*;

public class BinarySearch {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int[] arr = {1,2,4,6,7,9};
		int target = sc.ni();
		po(bs_eqless(arr, target));
		po(bs_eqgreat(arr, target));
	}

	/*
	EQUAL OR LESS:
	Returns the the index of element equal to OR less than the target
	(if the target is not present).
	*/
	public static int bs_eqless(int[] arr, int target){
		int low = 0, high = arr.length-1, mid = -1, less = -1;
		
		while(low <= high){
			mid = (low+high)/2;

			/*If mid is less than the number, store it somewhere
			in case the target is not in the array, this number would
			be needed to be returned, and search in the right half.
			*/
			if(arr[mid] < target){
				low = mid+1;
				less = mid;
			}
			else if(arr[mid] > target){
				high = mid-1;
			}
			else 
			break;
		}

		/*Check if arr[mid] is target, if it is not, then it means that
		the elementt is not found in the array and less number needs
		to be returned */
		if(arr[mid] != target)
			mid = less;

		return mid;

	}

	/*
	EQUAL OR GREATER:
	Returns the the index of element equal to OR greater than the target
	(if the target is not present).
	*/
	public static int bs_eqgreat(int[] arr, int target){
		int low = 0, high = arr.length-1, mid = -1, big = -1;
		
		while(low <= high){
			mid = (low+high)/2;

			/*If mid is less than the number, store it somewhere
			in case the target is not in the array, this number would
			be needed to be returned, and search in the right half.
			*/
			if(arr[mid] < target){
				low = mid+1;
			}
			else if(arr[mid] > target){
				high = mid-1;
				big = mid;
			}
			else 
				break;
		}

		/*Check if arr[mid] is target, if it is not, then it means that
		the elementt is not found in the array and less number needs
		to be returned */
		if(arr[mid] != target)
			mid = big;

		return mid;

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