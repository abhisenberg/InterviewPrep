import java.util.*;
import java.io.*;

public class Minim {
	/*
	Segment tree construction to find minimum number in a range 
	in an array.
	*/
	public static int[] createSegTree(int[] input){
		int np2 = nextPowOf2(input.length);
		int size = 2*np2 - 1;

		int[] segTree = new int[size];
		construct(segTree, input, 0, input.length-1, 0);
		return segTree;
	}

	public static void construct(int[] segTree, int[] input,
		int low, int high, int pos){

		if(low == high){
			segTree[pos] = input[low];
			return;
		}

		int mid = (low+high)/2;
		construct(segTree, input, low, mid, 2*pos+1);
		construct(segTree, input, mid+1, high, 2*pos+2);

		segTree[pos] = Math.min(segTree[2*pos+1],
			segTree[2*pos+2]);
	}

	/*
	ls -> left of search
	rs -> right of search

	lt -> left of tree
	rt -> right of tree
	*/
	public static int query(int[] segTree, int lt, int rt, int pos,
		int ls, int rs){

		//Total overlap of lt-rt by ls-rs
		if(lt >= ls  && rt <= rs){
			return segTree[pos];
		}

		//No overlap
		if(lt > rs || rt < ls){
			return Integer.MAX_VALUE;
		}
		
		int mid = (lt+rt)/2;
		//Partial overlap
		return Math.min(
			query(segTree, lt, mid, 2*pos+1, ls, rs),
			query(segTree, mid+1, rt, 2*pos+2, ls, rs));
	}

	public static int query(int[] segTree, int[] arr, int ls, int rs){
		return query(segTree, 0, arr.length-1, 0, ls, rs);
	}

	public static void update(int[] segTree, int[] arr, int indx, int val){
		update(segTree, indx, val, 0, arr.length-1, 0);
	}

	public static void update(int[] segTree, int indx, int val,
		int lt, int rt, int pos){

		if(indx < lt || indx > rt || lt  > rt){
			return;
		}

		if(lt == rt && rt == indx){
			segTree[pos] = val;
			return;
		}

		int mid = (lt+rt)/2;
		update(segTree, indx, val, lt, mid, 2*pos+1);
		update(segTree, indx, val, mid+1, rt, 2*pos+2);

		segTree[pos] = Math.min(segTree[2*pos+1], segTree[2*pos+2]);

	}
	

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int[] arr = {2,3,-1,0,4,6};
		int[] segTree = createSegTree(arr);
		po(query(segTree, arr, 0, 1));
		update(segTree, arr, 0, -2);
		po(query(segTree, arr, 0, 1));
		po(query(segTree, arr, 0, arr.length-1));
	}

	public static int nextPowOf2(int n){
		if(n == 0)
			return 1;

		if((n & (n-1))==0) 
			return n;

		int ans = 1;
		while(ans < n){
			ans <<= 1;
		}
		return ans;
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

	public static void show2d(int[][] arr){
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				p(arr[i][j]+" ");
			}
			po("");
		}
	}
}