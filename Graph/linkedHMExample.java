import java.util.*;
import java.lang.*;

public class linkedHMExample
{
   public static void main(String[] args)
   {
        LinkedHashMap<Integer, Integer> lhm = new LinkedHashMap<>(4, 0.5f, true);
   	lhm.put(1,1);
   	lhm.put(2,2);
   	System.out.println(lhm);
   	lhm.put(1,1);
   	System.out.println(lhm);
   	lhm.put(3,3);
	System.out.println(lhm);
	lhm.get(2);
	System.out.println(lhm);	
   }
}
