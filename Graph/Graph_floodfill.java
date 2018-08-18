import java.util.*;
import java.lang.*;

public class Graph_floodfill
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int arr[][] = new int[n][m];
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				arr[i][j] = sc.nextInt();
			}
		}
		if(find(arr, 0, 0, new boolean[n][m]))
			System.out.println("Yes");
		else
			System.out.println("No");		
	}
	
	public static boolean find(int[][] arr, int x, int y, boolean vis[][])
	{		
		if(x == arr.length || y == arr.length
					|| x < 0 || y < 0)
		{
			return false;
		}
		
		if(vis[x][y])
			return false;
		
		vis[x][y] = true;
		
		if(arr[x][y] == 0)
			return false;
			
		if(x == arr.length-1 && y == arr.length-1)
		{
			return true;
		}
		
		//move up
		if( find(arr, x-1, y, vis) == true )
			return true;
		
		//move down
		if( find(arr, x+1, y, vis) == true )
			return true;
			
		//move right
		if( find(arr, x, y+1, vis) == true )
			return true;
		
		//move left
		if( find(arr, x, y-1, vis) == true )
			return true;
		
		return false;
	}
}
