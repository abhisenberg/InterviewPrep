import java.util.*;
import java.io.*;

public class RatInMaze {

	/*
	Consider a rat placed at (0, 0) in a square matrix m[][] of order
	n and has to reach the destination at (n-1, n-1).
	Your task is to complete the function which returns a sorted
	array of strings denoting all the possible directions which the
	rat can take to reach the destination at (n-1, n-1).
	The directions in which the rat can move are
	'U'(up), 'D'(down), 'L' (left), 'R' (right).
	*/

	/*
	1
4
1 0 0 0
1 1 0 1
1 1 0 0 
0 1 1 1

1
3
1 1 1
1 0 1
1 1 1

1
3
1 1 1
0 1 1
1 1 1

	*/

	public static void main(String[] args){
		
		ShortScan sc = new ShortScan();

		/*
		int t = sc.ni();
		while(t-->0){
			int n = sc.ni();
			boolean[][] maze = new boolean[n][n];
			int input = 0;

			ans = new ArrayList<>();
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					input = sc.ni();
					maze[i][j] = (input == 1)? true : false;
				}
			}

			rim(maze, 0, 0, new StringBuilder());

			Collections.sort(ans);
			for(int i=0; i<ans.size(); i++){
				po(ans.get(i));
			}

			//po("size of ans "+ans.size());
		}*/

		//For "rat in maze with jump algorithm"
		int[][] maze = { {2, 1, 0, 0},
         {3, 0, 0, 1},
         {0, 1, 0, 1},
          {0, 0, 0, 1}
        };
		rim_jumps(maze, 0, 0, new StringBuilder());

	}

	static int[] px = {-1, 1, 0, 0};
	static int[] py = {0, 0, -1, 1};
	static char[] pc = {'U', 'D', 'L', 'R'};
	static  ArrayList<String> ans;

	public static void rim(boolean[][] maze, int i, int j, StringBuilder path){
		
		if(i < 0 || j < 0 || i >= maze.length || j >= maze.length){
			return;
		}

		if(!maze[i][j]) return;

		if(i == maze.length-1 && j == maze.length-1){
			ans.add(path.toString());
			return;
		}
		
		maze[i][j] = false;
		//po("marked "+i+","+j+" as visited");

		for(int k=0; k<4; k++){
			path.append(pc[k]);
			rim(maze, i+px[k], j+py[k], path);
			path.deleteCharAt(path.length()-1);
		}

		maze[i][j] = true;

	}

	public static void rim_jumps(int[][] maze, int i, int j, StringBuilder sb){
		if(i<0 || j<0 || i>=maze.length || j>=maze.length){
			return;
		}

		//If we reach last step, then return true
		if(i == maze.length-1 && j == maze.length-1){
			po(sb);
			return;
		}

		//if visited, or not allowed to go
		if(maze[i][j] == 0) return;

		//Else, count the number of steps
		int steps = maze[i][j];
		//Mark the step as visited
		maze[i][j] = 0;

		//Try jumping right
		for(int k=1; k<=steps; k++){
			for(int l=1; l<=k; l++)
				sb.append('R');

			rim_jumps(maze, i, j+k, sb);

			for(int l=1; l<=k; l++)
				sb.deleteCharAt(sb.length()-1);
		}

		//Try jumping down
		for(int k=1; k<=steps; k++){
			for(int l=1; l<=k; l++)
				sb.append('D');

			rim_jumps(maze, i+k, j, sb);
			
			for(int l=1; l<=k; l++)
				sb.deleteCharAt(sb.length()-1);
		}


		maze[i][j] = steps;

	}

	public static ArrayList<Character> addPath(ArrayList<Character> p){
		ArrayList<Character> np = new ArrayList<>();
		for(int i=0; i<p.size(); i++){
			np.add(p.get(i));
		}
		return np;
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

	public static void po(Object o){
		System.out.println(o);
	}

	
}