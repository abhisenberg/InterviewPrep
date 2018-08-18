import java.util.*;
import java.lang.*;

public class q1
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		LinkedList<Integer> l = new LinkedList<>();
		for(int i=0; i<n; i++)
		{
			l.addLast(sc.nextInt());
		}
		
		while(!l.isEmpty())
		{	
			if(l.get(0) <= k)
			{
				if(l.get(l.size()-1) <= k)
				{
					if(l.get(0) < l.get(l.size()-1))
					{	
						l.removeFirst();
					}
					else
					{
						l.removeLast();
					}
				}
				else
				{
					l.removeFirst();
				}
			}
			else
			{
				if(l.get(l.size()-1) <= k)
				{
					l.removeLast();
				}
				else
				{
					break;
				}
			}
		}
		po(n-l.size());
	}
	
	public static void po(Object o)
	{
		System.out.println(o);
	}	
}
