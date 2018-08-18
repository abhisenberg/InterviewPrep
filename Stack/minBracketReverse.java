/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

/*
Given an expression with only ‘}’ and ‘{‘.
The expression may not be balanced.
Find minimum number of bracket reversals to make the
expression balanced.
*/

class minBracketReverse {
	public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        

        /*
        We can solve this problem in O(n) time.
        first remove all balanced part of expression.
        For example, convert “}{{}}{{{” to “}{{{” by 
        removing balanced parts. After removing 
        balanced part, we always end up with an 
        expression of the form }}…}{{…{, an 
        expression that contains 0 or more number 
        of closing brackets followed by 0 or more 
        numbers of opening brackets.

         Let m be the total number of closing 
         brackets and n be the number of opening 
         brackets. We need ⌈m/2⌉ + ⌈n/2⌉ reversals. 
         For example }}}}{{ requires 2+1 reversals.
        */

        while(t-- > 0){
            String s = sc.nextLine();
            Stack<Character> st = new Stack<>();
            double ob = 0, cb = 0;
            
            for(int i=0; i<s.length(); i++){
                char c = s.charAt(i);
                
                if(!st.isEmpty()){
                    if(c == '}' && st.peek()=='{'){
                        ob--;
                        st.pop();
                        continue;
                    }
                }
                
                if(c == '}'){
                    cb++;        
                } else if(c == '{'){
                    ob++;
                }
                
                st.push(c);
            }
        
            if((ob+cb)%2 != 0){
                System.out.println(-1);
            }
            else 
                System.out.println((int)(Math.ceil(ob/2)+Math.ceil(cb/2)));
        }
	}
}