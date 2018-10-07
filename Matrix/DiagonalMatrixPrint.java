/*
Given a matrix of n*n size, the task is to print its elements in diagonally pattern.

Input : mat[3][3] = {{1, 2, 3},
                     {4, 5, 6},
                     {7, 8, 9}}
Output : 1 2 4 7 5 3 6 8 9.
Explanation: We start from 1 
Then from upward to downward diagonally i.e. 2 and 4
Then from downward to upward diagonally i.e 7,5,3 
Then from up to down diagonally i.e  6, 8 
Then down to up i.e. end at 9.

Input : mat[4][4] =  {{1,  2,  3,  10},
                      {4,  5,  6,  11},
                      {7,  8,  9,  12},
                      {13, 14, 15, 16}}
Output:  1 2 4 7 5 3 10 6 8 13 14 9 11 12 15 16 
*/

void printMatrixDiagonal(int arr[][], int n)
    {   
        /*
        For printing diagonals, we try out all possible sums (since the sum of i+j in diagonal is constant over
        a diagonal).
        */
        for(int s=0; s<=2*(n-1); s++){
            for(int i=0; i<=s; i++){
                int j = s-i;
                
                if(i>=0 && i<n && j>=0 && j<n){
                    if(s%2 != 0)
                        System.out.print(arr[i][j]+" ");
                    else
                        System.out.print(arr[j][i]+" ");
                }
            }
        }
    }
