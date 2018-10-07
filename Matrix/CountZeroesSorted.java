/*
Given a N x N binary matrix (elements in matrix can be either 1 or 0) where each row and
column of the matrix is sorted in ascending order, count number of 0s present in it.
Expected time complexity is O(N).

Examples:

Input: 
[0, 0, 0, 0, 1]
[0, 0, 0, 1, 1]
[0, 1, 1, 1, 1]
[1, 1, 1, 1, 1]
[1, 1, 1, 1, 1]
Output: 8

Input: 
[0, 0]
[0, 0]
Output: 4

Input: 
[1, 1, 1, 1]
[1, 1, 1, 1]
[1, 1, 1, 1]
[1, 1, 1, 1]
Output: 0
*/

int countZeros(int arr[][], int n)
    {
        /*
        If the current element is 1, then it means all the elements in the
        column below it are also 1. Hence no need to go down. Go left to
        check the previous columns.
        
        If the current element is 0, then all the elements to the left of it
        are 0. So no need to go left and check, add the column number to 
        the total count, that much zeroes are present in that row.
        And move down to the next row.
        */
        
        int i=0, j=n-1;
        int count = 0;
        while(i>=0 && i<n && j>=0 && j<n){
            if(arr[i][j] == 0){
                count += j+1;
                i++;
            } else {
                j--;
            }
        }
        
        return count;
        
    }
