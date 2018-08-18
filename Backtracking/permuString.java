import java.util.*;
import java.io.*;

public class permuString {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
			String temp = sc.n();
			char[] s = temp.toCharArray();
			Arrays.sort(s);
			
			permu(s, new char[s.length], 0, new HashSet<Integer>());
			po("");
		}
	}

	public static void permu(char[] os, char[] perm, int pos, Set<Integer> vis){
		//pos is the current position to be filled
		if(pos == os.length){
			p(String.valueOf(perm)+" ");
			return;
		}

		for(int i=0; i<os.length; i++){
			if(vis.contains(i))
				continue;

			perm[pos] = os[i];

			vis.add(i);
			permu(os, perm, pos+1, vis);
			vis.remove(i);
		}

		return;
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
}