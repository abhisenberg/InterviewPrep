import java.util.*;
import java.io.*;

public class matAllPath {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int[][] mat = {{1,2}, {3, 4}};
		map(mat, 0, 0, new StringBuilder());
	}

	public static boolean map(int[][] mat, int i, int j, StringBuilder sb){
		if(i <0 || j<0 || i== mat.length || j== mat[0].length){
			return false;
		}

		if(mat[i][j] == -1)
			return false;

		int boxnum = mat[i][j];
		mat[i][j] = -1; //Mark visited
		sb.append(boxnum);

		if(i==mat.length-1 && j==mat[0].length-1){
			po(sb);
			sb.deleteCharAt(sb.length()-1);
			mat[i][j] = boxnum;
			return true;
		}

		//Try going right
		map(mat, i, j+1, sb);

		//Try going down
		map(mat, i+1, j, sb);

		sb.deleteCharAt(sb.length()-1);
		mat[i][j] = boxnum;
		return false;		
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
	}
}