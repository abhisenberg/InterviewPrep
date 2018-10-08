/*
There is a staircase consisting of 'n' stairs. The objective is to find the number of ways
one can reach the top (n'th stair) if he can make jumps of only 1, 2 or 3 stairs at a time.
*/

/*
SIMPLE RECURSIVE APPROACH: For any n'th stair, the way of reaching it is equal to the sum 
of the ways of reaching n-1, n-2 and n-3'th stair. Why? Because from the n-1'th stair, it will take
one jump of 1 stair to reach n'th. From n-2'th stair, it will take one jump of 2 stairs to reach n'th.
And from n-3'th stair, it will take one of 3 stairs of reach n'th. Hence the total number of ways of reaching
n'th stair is the sum of the result of previous 3 stairs.
*/
 static int jumps(int n){
        if(n == 0)
            return 1;
        
        if(n < 0)
            return 0;
            
        return jumps(n-1)+jumps(n-2)+jumps(n-3);
        
  }
  
/*
BOTTOM UP APPROACH: Since the number of ways of reaching the n'th stair is the sum of the result of the
previous 3 stairs, we can use an iterative approach to store the result of the past stairs in an array and just use
it to find the ans of the current stair.
*/

static int jumps_bu(int n){
        int[] jumps = new int[n+1];
        /*
        We can find out the number of ways of reaching 1st, 2nd and 3rd stairs
        manually.
        */
        jumps[1] = 1;
        jumps[2] = 2;
        jumps[3] = 4;
        
        for(int i=4; i<=n; i++){
            jumps[i] = jumps[i-1]+jumps[i-2]+jumps[i-3];
        }
        
        return jumps[n];
    }
