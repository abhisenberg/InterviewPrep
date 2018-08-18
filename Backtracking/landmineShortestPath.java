import java.util.*;
import java.io.*;

public class landmineShortestPath {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int[][] pathint = {
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
        { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }
    	};
    	boolean[][] path = new boolean[path.length][path[0].length];
    	for(int i=0; i<path.length; i++){
    		for(int j=0; j<path[i].length; j++){
    			path[i][j] = (pathint[i][j] == 1) true : false;
    		}
    	}

    	for(int i=0; i<path.length; i++){
    		for(int j=0; j<path[i].length; j++){
    			if(!path[i][j]){
    				markUnsafe(path, i, j);
    			}
    		}
    	}

	}

	public static void safePath (boolean[][] path, int col){
		for(int i=0; i<path.length; i++){
			path[i][col];
		}
	}

	public static void markUnsafe(boolean[][] path, int i, int j){
		boolean safe = true;
		
		//Check left vala
		if(j-1 >= 0){
			path[i][j-1] = false;
		}

		//Check right vala
		if(j+1 < path[0].length){
			path[i][j+1] = false;
		}

		//Check above
		if(i-1 >= 0){
			path[i-1][j] = false;
		}

		//Check below
		if(i+1 < path.length){
			path[i+1][j] = false;
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

	public static void showArr(boolean[] arr){
		for(boolean x: arr){
			p(x+" ");
		}
		po("");
	}
}