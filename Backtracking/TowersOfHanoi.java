import java.io.*;

public class Template {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-- > 0){
		    int n = sc.ni();
		    toh(n, 1, 2, 3);
		    po((int)Math.pow(2, n)-1);
		}
	}
  
  /*
  There are only 3 major things to do to solve any TOH problems with any no. of disks.
  -> Move n-1 disks from source to aux peg
  -> Move nth disk from source to des
  -> Move the n-1 disks from aux to des
  Every other sub case will be handled by recursion.
  */
	
	static void toh(int n, int source, int aux, int des){
	    if(n == 1){
	        po("move disk 1 from rod "+source+" to rod "+des);
	        return;
	    }
	    
	    
	    toh(n-1, source, des, aux);
	    po("move disk "+n+" from rod "+source+" to rod "+des);
	    toh(n-1, aux, source, des);
	    
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

			public long nl(){
				return sc.nextLong();
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

	public static <T> void showArr(T[] arr){
		for(T x: arr){
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
