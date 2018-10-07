Rotate an NxN matrix by 90 degrees without using extra space.

	static void rotate(int[][] arr){
	    int n = arr.length;
	    
	    for(int i=0; i< n/2; i++){
	        for(int j=i; j<n-i-1; j++){
	            
	            int p1 = arr[i][j];
	            int p2 = arr[j][n-i-1];
	            int p3 = arr[n-i-1][n-j-1];
	            int p4 = arr[n-j-1][i];
	            
	            arr[j][n-i-1] = p3;
	            arr[n-i-1][n-j-1] = p4;
	            arr[n-j-1][i] = p1;
	            arr[i][j] = p2;
	        }
	    }
	}

http://javabypatel.blogspot.com/2016/11/rotate-matrix-by-90-degrees-inplace.html
