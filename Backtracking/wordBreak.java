import java.util.*;
import java.io.*;

public class wordBreak {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
			int n = sc.ni();
			dic = new HashSet<String>();
			for(int i=0; i<n; i++){
				dic.add(sc.n());
			}
			String s = sc.n();
			/*
			int ans = wbreak(s, 0) ? 1 : 0;
			po(ans);
			*/
			wbreak2(s, 0, new ArrayList<String>());
			po("");
		}
	}

	/*
	1
	3
	abc abcd ef
	abcdef
	*/

	static Set<String> dic;

	public static boolean wbreak(String str, int pos){

		if(pos == str.length())
			return true;

		for(int i=pos; i<=str.length(); i++){
			String word = str.substring(pos, i);
			if(dic.contains(word)){
				if(wbreak(str, i))
					return true;
			}
		}

		return false;
	}

	public static void wbreak2(String str, int pos, ArrayList<String> al){
		
		if(pos == str.length()){
			printList(al);
			return;
		}

		for(int i=pos; i<=str.length(); i++){
			String word = str.substring(pos, i);
			if(dic.contains(word)){
				al.add(word);
				//po("adding word "+word);
				wbreak2(str, i, al);
				al.remove(al.size()-1);
			}
		}

	}

	public static void printList(ArrayList<String> list){
		p("(");
		for(int i=0; i<list.size()-1; i++){
			p(list.get(i)+" ");
		}
		p(list.get(list.size()-1));
		p(")");
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
