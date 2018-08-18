import java.util.*;
import java.lang.*;

public class QTwo
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int r = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int n = x*y;
		if(n < x || n < y) po(0);
		else
		{	
			//po("jkgckhgc");
			int c = 0;
			for(int i=l; i*i<=n && i<=r; i++)
			{
				if(n%i==0)
				{	int a = i;
					int b = (n/i);
					//po("a and b "+a+" "+b);
					if(b <= r)
					{	
						int gcd = gcd(Math.max(a,b), Math.min(a,b));
						if(gcd == x)
						{	
							//po("gcd of "+a+" and "+b+" is "+gcd);
						 	c++;
						 	if(a != b) c++;
						}
					}
				}
			}
			po(c);
		}
		
	}
	
	public static int gcd(int a, int b)
	{
		//a > b
		if(b==0) return a;
		return gcd(b, a%b);
	}
	
	public static void po(Object o)
	{
		System.out.println(o);
	}
}
