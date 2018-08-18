import java.util.*;
import java.io.*;

public class subsets {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		/*
		int[] arr = {1,2};
		subsets(arr, 0, new ArrayList<>());
		*/

		char[] arr = {'a', 'b', 'c'};
		subsets_lex(arr, -1, new ArrayList<>());
	}

	public static void subsets(int[] arr, int pos, ArrayList<Integer> ans){
		if(pos == arr.length){
			po(ans);
			return;
		}

		ans.add(arr[pos]);
		subsets(arr, pos+1,ans);

		ans.remove(ans.size()-1);
		subsets(arr, pos+1, ans);

	}

	public static void subsets_lex(char[] arr, int pos, ArrayList<Character> ans){
		if(pos == arr.length){
			return;
		}

		//Print the string made till now.
		po(ans);

		int i = pos+1; //Selecting the next element
		for(; i<arr.length; i++){
			ans.add(arr[i]);
			subsets_lex(arr, i, ans);
			ans.remove(ans.size()-1);
		}
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