/*
Given an n x n matrix, where every row and column is sorted in increasing order. 
Given a number k, search for this number in the matrix.
The expected complexity is O(n)
*/

/*
Algo:
We will start our search by looking at Right most element of first row.
If Current Element =  Search element, Return True, element found.
If Current Element <  Search element, It means all element on left of current
    element are smaller and no need to look on left. Check element on next column.
If Current Element >  Search element, It means all element on below current
    element are higher and no need to look down. Check element in left side on same row.
    
We can either start from the top-right or from the bottom-left becz only with these two starting points,
we get increasing values in one direction and decreasing values in the other.
For ex, if we start from top left then in all possible traversable directions, the values increase, so
we cannot determine which way to go, and we'll end up traversing the whole array.
If we start from bottom right then in all possible traversable directions, the values decrease, hence
again the same problem.
*/

	static boolean present(int[][] arr, int x, int m, int n){
	    int i=0, j=n-1;
	    while(i >= 0 && i<m && j >= 0 && j<n){
	        if(arr[i][j] == x){
	            return true;
	        }
	        
	        if(x > arr[i][j]){
	            i++;
	        }
	        
	        else {
	            j--;
	        }
	    }
	    return false;
	}
