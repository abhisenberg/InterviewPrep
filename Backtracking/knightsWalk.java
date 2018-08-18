import java.util.*;
import java.io.*;

public class knightsWalk {
	
	static int min  = 99999;
	static int[] cx = {2,1,1,2,-1,-2,-2,-1};
	static int[] cy = {-1,-2,2,1,2,1,-1,-2};

	static class Point{
		int x;
		int y;
		int s;

		public Point(int x, int y, int s){
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}

	public static int kt(int n, int m, int sx, int sy, int dx, int dy){

		Queue<Point> q = new LinkedList<>();
		boolean[][] inq = new boolean[n][m];

		q.add(new Point(sx,sy,0));
		inq[sx][sy] = true;
		
		int min = -1;
		while(!q.isEmpty()){
			Point cp = q.poll();
			
			if(cp.x == dx && cp.y == dy){
				min = cp.s;
				break;				
			}

			for(int i=0; i<cx.length; i++){
				int newx = cp.x+cx[i];
				int newy = cp.y+cy[i];
				if(newx <= n-1 && newy <= m-1 && newx >= 0 && newy >=0){
					// po(newx+" "+newy);
					if(!inq[newx][newy]){
						q.add(new Point(newx, newy, cp.s+1));
						inq[newx][newy] = true;
					}
				}
			}
		}

		return min;
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-->0){
			int n = sc.nextInt();
			int m = sc.nextInt();
			int sx = sc.nextInt();
			int sy = sc.nextInt();
			int dx = sc.nextInt();
			int dy = sc.nextInt();

			po(kt(n, m, sx, sy, dx, dy));
		}
	}

	public static void po(Object o){
		System.out.println(o);
	}

}