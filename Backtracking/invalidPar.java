import java.util.*;
import java.io.*;

public class invalidPar {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		String s = sc.ns();
		/*
		int ob = 0, cb = 0;
		
		//By default rc is true, meaning closing brackets
		//are to be removed.
		boolean rc = true;	
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i) == '(') ob++;
			else if(s.charAt(i) == ')') cb++;
		}

		if(ob > cb) rc = false; //means remove opening brackets
		int tbr = (rc)? cb-ob : ob-cb; //to-be-removed, tells how many are tbr
		*/

		po(rip(s));

	}

	public static boolean isValid(String s){
		int extraBrackets = 0;
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i)=='(') extraBrackets++;
			else if (s.charAt(i)==')') extraBrackets--;
			if(extraBrackets < 0)
				return false;
		}
		//po("extra brackets "+extraBrackets);	
		return (extraBrackets == 0);
	}

	public static boolean isBracket(char c){
		return c=='(' || c ==')';
	}

	public static ArrayList<String> rip(String str){
		ArrayList<String> ans = new ArrayList<>();
		Queue<String> q = new LinkedList<>();
		Set<String> vis = new HashSet<>();

		boolean level = false;
		q.add(str);
		while(!q.isEmpty()){
			str = q.remove();
			
			if(isValid(str)){
				ans.add(str);
				level = true;
			}

			if(level)
				continue;

			for(int i=0; i<str.length(); i++){
				
				String temp = str.substring(0,i)+
					str.substring(i+1, str.length());

				if(!isValid(str) && !vis.contains(temp)){
					vis.add(temp);
					q.add(temp);
				}

			}

		}
		return ans;
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

			public String ns(){
				return sc.next();
			}

			public String nli(){
				return sc.nextLine();
			}
	}
}