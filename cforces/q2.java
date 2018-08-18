import java.util.*;
import java.lang.*;

public class q2
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		String str = sc.next();
		char s[] = str.toCharArray();
		LinkedList<Integer> l = new LinkedList<>();
		
		int i=1;
		for(; i*i<n; i++)
		{
			if(n%i == 0)
			{
				l.add(i);
				l.add(n/i);
			}
		}
		if(i*i == n) l.add(i);
		Collections.sort(l);
		
		for(int x: l)
		{
			s = rev(s, x-1);
		}
		po(String.copyValueOf(s));
	}
	
	public static char[] rev(char[] s, int x)
	{
		int i=0;
		while(i <= x)
		{
			char temp = s[i];
			s[i] = s[x];
			s[x] = temp;
			
			i++;
			x--;
		}
		return s;
	}
	
	public static void po(Object o)
	{
		System.out.println(o);
	}	
}
