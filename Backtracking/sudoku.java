import java.util.*;
import java.io.*;

public class sudoku {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		
		int t = sc.ni();
		while(t-->0){
			int[][] su = new int[9][9];
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					su[i][j] = sc.ni();
				}
			}
			sus(su, 0, 0);
			showArr(su);
		}
	}

	public static boolean sus(int[][] su, int i, int j){
		if(i == 9 && j == 0)
			return true;

		int ni, nj;
		if(j == 8) {
			ni = i+1;
			nj = 0;
		} else {
			ni = i;
			nj = j+1;
		}

		if(su[i][j] == 0){
			boolean[] occ = new boolean[10];

			boolean isdone;
			for(int k=1; k<10; k++){
				
				//Check for occurence in rows and colums 				
				boolean f = true;
				for(int l=0; l<9; l++){
					if(su[i][l] == k || su[l][j] == k){
						f = false;
						break;
					}
				}
				//check for submatrix 3x3
				int si = i - i%3;
				int sj = j - j%3;
				for(int l=si; l<si+3; l++){
					for(int m =sj; m<sj+3; m++){
						if(su[l][m] == k){
							f =false;
							break;
						}
					}
				}

				if(!f) continue;

				su[i][j] = k;

				if(sus(su, ni, nj))
					return true;
				
				/*
				I missed this statement, and it was creting havoc in the 
				program as the problem was not getting solved.
				This is necessary as it is a very important concept
				to un-assign the value so that the recursion can find 
				the new solution again. Otherwise the recursion would not
				visit the block again to give the proper solution.
				*/				
				su[i][j] = 0;
			}
			return false;
		}
		else
			return sus(su, ni, nj);
	}

	public static void showArr(int[][] su){
		for(int i=0; i<su.length; i++){
			for(int j=0; j<su.length; j++){
				System.out.print(su[i][j]+" ");
			}
			po("");
		}
	}

	public static void p(Object o){
		System.out.print(o);
	}

	public static void po(Object o){
		System.out.println(o);
	}

	static class ShortScan {
			Scanner sc;

			public ShortScan(){
				sc = new Scanner (System.in);
			}

			public int ni(){
				return sc.nextInt();
			}
	}

	/*
	3 0 0
	0 0 1
	0 0 0
	*/
}