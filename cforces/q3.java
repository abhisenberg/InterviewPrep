import java.util.*;
import java.lang.*;

public class q3
{	//97 to 122
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String s = sc.next();
		
		int c[] = new int[26];
		for(int i=0; i<n; i++)
		{
			c[(int)s.charAt(i)-97]++;
		}
		
		for(int i=0; i<26; i++)
		{
			while(c[i] > 0 && k > 0)
			{
				c[i]--;
				k--;
			}			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=n-1; i>=0; i--)
		{
			if(c[(int)s.charAt(i)-97] > 0)
			{
				sb.append(s.charAt(i));
				c[(int)s.charAt(i)-97]--;
			}
		}
		sb.reverse();
		po(sb);
		
		/*
		boolean not[] = new boolean[n];
		int pp[] = new int[26];
		
		LinkedList<Integer>[] l = new LinkedList[26];
		for(int i=0; i<l.length; i++)
		{
			l[i] = new LinkedList<>();
		}
		
		for(int i=0; i<n; i++)
		{
			l[(int)s.charAt(i)-97].add(i);
		}
		
		while(k-- > 0)
		{
			for(int i=0; i<=25; i++)
			{
				if(pp[i] < l[i].size())
				{
					//po("removing "+((char)(i+97))+" at "+l[i].get(pp[i]));
					not[l[i].get(pp[i])] = true;
					pp[i]++;
					break;
				}
			}
		}
		
		for(int i=0; i<not.length; i++)
		{
			if(!not[i])
				System.out.print(s.charAt(i));
		}
		*/
		
		/*
		int c[] = new int[26];		
		for(int i=0; i<n; i++)
		{
			c[(int)s.charAt(i)-97]++;			
		}
		
		int l = 0;
		while(k-- > 0)
		{
			for(int i=97; i<=122; i++)
			{
				if(c[i-97] > 0)
				{
					c[(int)s.charAt(l)-97]--;
					l++;
					break;
				}
			}
		}
		for(; l<n; l++)
		{
			System.out.print(s.charAt(l));
		}
		*/
	}
	
	public static void po(Object o)
	{
		System.out.println(o);
	}	
}
