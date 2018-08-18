import java.util.*;
import java.lang.*;

public class QOne
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		HashMap<Integer, Boolean> map = new HashMap<>();
		int c = 0;
		while(n-->0)
		{	
			int w = sc.nextInt();
			if(!map.containsKey(w) && w != 0)
			{
				c++;
				map.put(w,true);
			} 
		}
		System.out.println(c);
	}
}
