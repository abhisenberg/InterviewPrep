import java.util.*;
import java.io.*;

public class LIncSubsq {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int[] arr = {3,8,4,3,5,2,6,9};
		lis(arr);
	}

	public static int lis(int[] arr){
		int[] lis_len = new int[arr.length];
		int[] lis_indx = new int[arr.length];

		Arrays.fill(lis_len, 1);
		Arrays.fill(lis_indx, -1);

		for(int i=1; i<arr.length; i++){
			for(int j=0; j<i; j++){
				if(arr[j] < arr[i] && lis_len[j]+1 > lis_len[i]){
					lis_len[i]	= lis_len[j]+1;
					lis_indx[i] = j;
				}
			}
		}

		int max = 0, maxi = -1;
		for(int i=0; i<lis_len.length; i++){
			if(max < lis_len[i]){
				max = lis_len[i];
				maxi = i;
			}
		}

		po(max);


		//Prints the LIS in reverse order
		do{
			p(arr[maxi]+" ");
			maxi = lis_indx[maxi];
		} while (maxi != -1);

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