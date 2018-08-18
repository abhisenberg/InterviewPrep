import java.util.*;
import java.io.*;

public class q2 {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		sc.ns();
		char[] s1 = (sc.ns()).toCharArray();
		char[] s2 = (sc.ns()).toCharArray();

		int[] fr = new int[26];
		for(int i=0; i<s1.length; i++){
			fr[(int)s1[i]-97]++;
			fr[(int)s2[i]-97]--;
		}
		
		for(int i=0; i<s1.length; i++){
			if(fr[i] != 0){
				po(-1);
				return;
			}
		}

		int c = 0;
		String ans = "";
				 

		/*
		int c=0;
		String ans = "";
		for(int i=0; i<s1.length-1; i++){
			int j=i;

			po("checking for ind "+i+", s1-> "+s1[i]+", s2-> "+s2[i]);

			if(s2[j] == ' ')
				continue;

			while(s1[j] != s2[j] && j<s1.length-1){
				char temp = s1[j];
				s1[j] = s1[j+1];
				s1[j+1] = temp;
				
				ans += (j)+" ";

				c++;
				j++;
				
				showArr(s2);
				showArr(s1);
				po(" ");
				
			}	

			s2[j] = ' ';
		}

		showArr(s2);
		showArr(s1);
		*/

		po(c);
		po(ans);
	}

	public static void verify(char[] s1, char[] s2, int[] sw){

	}
/*
6
abcdef
abdfec

6
cacdfe
adfcce

*/

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

	public static void showArr(char[] arr){
		for(char x: arr){
			p(x+" ");
		}
		po("");
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