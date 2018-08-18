import java.util.*;
import java.io.*;

public class q3 {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int[] arr = sc.narr(n);
		
		/*
		Striver's solution
		*/
		HashMap<Long, Integer> hm = new HashMap<>();
		long suf = 0;
		for(int i=n-1; i>=0; i--){
			suf += arr[i];
			hm.put(suf, i);
		}

		long pref = 0, maxsum = -1;
		for(int i=0; i<n; i++){
			pref += arr[i];
			if(hm.containsKey(pref) && hm.get(pref) > i){
				if(pref > maxsum)
					maxsum = pref;
			}
		}

		/*
		MY CHUTIA SOLUTION, GIVING WRONG ANSWER, CANNOT 
		FIGURE OUT WHY
		int[] s = new int[n];
		int i = 1, j = arr.length-2;
		s[0] = arr[0];
		s[n-1] = arr[n-1];

		while(i < j){
			s[i] = s[i-1] + arr[i];
			s[j] = s[j+1] + arr[j];
			i++;
			j--;
		}

		if(i == j){
			if(s[i-1]+arr[i] == s[j+1]){
				s[i] = s[j+1];
			} else {
				s[i] = arr[j]+s[j+1];
			}
		}

		i=0; j=arr.length-1; 
		int sum = -1;
		while(i< j){
			if(s[i] == s[j]){
				sum = Math.max(sum, s[i]);
			}

			if(i+1 < j && s[i+1] == s[j]){
				sum = Math.max(sum, s[j]);
			}

			if(i < j-1 && s[i] == s[j-1]){
				sum = Math.max(sum, s[i]);
			}

			i++;
			j--;
		}
*/

		if(maxsum == -1)
			po(0);
		else
			po(maxsum);
	}

	static int sm, l, r;

	/*
	5
3 5 4 3 5

	*/

	

	public static void rec(int[] arr, int i, int j, int s1, int s3){
		if(i >= j) {
			return;
		}

		//po(i+", "+j+" -> "+s1+", "+s3);

		if(s1 == s3){
			l = i;
			r = j;
			//po("*"+i+", "+j+" -> "+s1);
			sm = s1;
		}

		if(i < arr.length-1)
			rec(arr, i+1, j, s1+arr[i+1], s3);
		if(i > 0)
			rec(arr, i, j-1, s1, s3+arr[j-1]);
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

			public int[] narr(int n){
				int[] arr = new int[n];
				for(int i=0; i<arr.length; i++){
					arr[i] = sc.nextInt();
				}
				return arr;
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