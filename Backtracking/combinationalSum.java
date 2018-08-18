import java.util.*;
import java.io.*;

public class combinationalSum {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		
		while(t-->0){
			int n = sc.ni();
			
			Set<Integer> delDup = new HashSet<>();
			for(int i=0; i<n; i++){
				int temp = sc.ni();
				delDup.add(temp);				
			}

			int[] arr = new int[delDup.size()];
			int i = 0;
			Iterator it = delDup.iterator();
			while(it.hasNext()){
				arr[i] = (int)it.next();
				i++;
			}
			Arrays.sort(arr);
			int sum = sc.ni();
			solFound = false;

			cosum(arr, sum, 0, new ArrayList<>());
			if(!solFound)
				po("Empty");
			else
				po("");
		}
	}

	static boolean solFound;

	public static boolean cosum(int[] arr, int remSum, int pos, ArrayList<Integer> ans){
		
		if(remSum < 0 || pos == arr.length){
			return false;
		}

		if(remSum == 0){
			print(ans);
			solFound = true;
			return true;
		}

		for(int i=pos; i<arr.length; i++)	{
			ans.add(arr[i]);
			cosum(arr, remSum-arr[i], pos, ans);
			ans.remove(ans.size()-1);

			pos++;
		}

		return false;
	}

	public static void print(ArrayList<Integer> list){
		p("(");
		for(int i=0; i<list.size()-1; i++){
			p(list.get(i)+" ");
		}
		p(list.get(list.size()-1)+")");
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

/*
1
4
2 4 6 8
8

*/

/*

(1 1 1 1 1 1 1 1 1 1 1 1)(1 1 1 1 1 1 6)(1 1 1 1 8)(6 6)
(2 2 2 2 2 2 2 2)(2 2 2 2 2 6)(2 2 2 5 5)(2 2 5 7)(2 2 6 6)(2 7 7)(5 5 6)



*/