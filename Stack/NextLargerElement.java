Given an array A [ ] having distinct elements, the task is to find the next greater
element for each element of the array in order of their appearance in the array.
If no such element exists, output -1.

Input:
13 7 6 12
4 5 2 25

Output:
-1 12 12 -1
5 25 25 -1

Algo:
-> Take an array ans[n] which will store the next larger element of the arr[i] at ans[i].
-> Take a stack which will store the indices of the elements whose NLE is not yet found
-> When a new element comes, keep popping elements from the stack which are less than this new element.
  And store this element at index i at in the ans array.

Code:
            Stack<Integer> maxin = new Stack<>(); //Stores indices
            for(int i=0; i<n; i++){
                while(!maxin.isEmpty() && arr[i] > arr[maxin.peek()]){
                    ans[maxin.pop()] = arr[i];
                } 
                maxin.push(i);
            }
            while(!maxin.isEmpty()){
                ans[maxin.pop()] = -1;
            }
            showArr(ans);
