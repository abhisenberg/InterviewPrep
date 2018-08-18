import java.util.*;
import java.io.*;

public class q1 {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int m = sc.ni();
		int[][] cc = new int[n][2];
		for(int i=0; i<cc.length; i++){
			cc[i][0] = sc.ni();
			cc[i][1] = sc.ni();
		}

		boolean f = true;
		ArrayList<Integer> ans = new ArrayList<>();
		for(int i=1; i<=m; i++){
			f = true;
			for(int j=0; j<cc.length; j++){
				if(i >= cc[j][0] && i <= cc[j][1]){
					f = false;
					break;
				}
			}
			if(f){
				ans.add(i);
			}
		}

		po(ans.size());
		for(int x: ans){
			p(x+" ");
		}
		po("");
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