Given an array of integers, find the max sum in that array such that no two adjacent elements should be in the sum.

  Input:
  100 5 5 100
  5 5 10 100 10 5

  Output:
  200
  110
  
/*
Recursive approach (top down):    O(2^n)
If the prev element is selected, then the current cannot be selected and there is only one possibity i.e.
to exclude the current element in the sum.
If the prev element is not selected, then there are 2 options, we can either include or exclude the current
element. Hence we maintain a boolean which keeps track of whether the prev ele was selected or not.
*/

	static int tmax(int[] arr, int i, boolean isInc, int sum){
	    if(i == arr.length-1){
	        return sum;
	    }
	    
	    int a = 0, b = 0;
	    if(!isInc)
	        a = tmax(arr, i+1, true, sum+arr[i+1]);
	    b = tmax(arr, i+1, false, sum);
	    
	    return Math.max(a, b);
	}
  
/*
Bottom up iterative approach:     O(n)
Maintain two variables, inc and exc.
Inc: The max sum we can get if we try to include this element (does not necessarily mean we must include it).
Exc: The max sum we can get if we exclude this element.

During ith iteration,
inc = max(exc+arr[i], inc)
exc = old_inc
*/

	static int tmax_bu(int[] arr){
	    int inc = 0, exc = 0;
	    
	    for(int i=0; i<arr.length; i++){
	        int oldInc = inc;
	        inc = Math.max(exc+arr[i], inc);
	        exc = oldInc;
	    }
	    
	    return Math.max(inc, exc);
	}
