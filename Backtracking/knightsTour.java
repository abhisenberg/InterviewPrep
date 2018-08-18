import java.util.*;
import java.io.*;

public class knightsTour {

	/*
	The knight is placed on the first block of an empty board and,
	moving according to the rules of chess,
	must visit each square exactly once.
	*/
	
	public static void kt(int[][] cd, int x, int y, int vis){
		if(x < 0 || y < 0 || x >= cd.length || y >= cd.length) {
			return;
		}

		if(vis == 63)
			return;

		if(cd[x][y] != -1)
			return;

		cd[x][y] = vis;

		kt(cd, x+2, y-1, vis+1);
		kt(cd, x+1, y-2, vis+1);
		kt(cd, x+2, y+1, vis+1);
		kt(cd, x+1, y+2, vis+1);
		kt(cd, x-2, y+1, vis+1);
		kt(cd, x-1, y+2, vis+1);
		kt(cd, x-2, y-1, vis+1);
		kt(cd, x-2, y-2, vis+1);

	}

	public static void print(int[][] cd){
		for(int i=0; i<cd.length; i++){
			for(int j=0; j<cd.length; j++){
				System.out.print(cd[i][j]+"		");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args){
		int[][] cb = new int[8][8];
		for(int i=0; i<8; i++){
			Arrays.fill(cb[i], -1);
		}
		kt(cb,0,0,0);
		print(cb);
	}

}