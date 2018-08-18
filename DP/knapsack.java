import java.util.*;
import java.io.*;

public class knapsack {

	/*
	There are two kind of knapsack problems, 0/1 and Fractional knapsack.

	1)Fractional knapsack: This type of problem is where it is allowed
	to take some part of the item, i.e. not necessary to take the whole
	item. This can be solved by greedy method, which is as follows:

		-> Find out the profit-by-weight ratio of each item, and arrange
		them in decreasing order of pbw ratio. Keep taking each item one
		by one in decreasing order and in the end, if the capacity does
		not allow the taking of a full item, take the fraction of it which
		can be taken into the bag.

	2)0/1 knapsack: This problem cannot be solved with greedy method.
	It is solved by DP, by using table method.
	We make a table of with Weights of objects on rows side and
	the capacity remaining in the bag on the columns side.

	Weights: 1 3 4 5
	Value:   1 4 5 7
	Capacity of bag: 7

	The table woulde be like:
	Cpcty:  0 1 2 3 4 5 6 7
	Wt.  	
	1
	3
	4
	5

	table[weights.length][capacity+1]

	Each cell (i, j) represents the max. value we can attain if we are 
	allowed to select the item i and all items before it i.e. 1,2,3.. i

	The formula to fill this table is: 
		table[i][j] = max(table[i-1][j], table[i-1][j-w[i]] + value[i]);
	
	*/

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int val[] = {10, 40, 30, 50};
		int wt[] = {5, 4, 6, 3};
		int c = 10;
		//po(ks_rec(wt, val, c, 0, 0, ""));
		po(ks_bu(wt, val, c));
	}

	/*
	0/1 knapsack 
	SIMPLE RECURSION
	*/
	public static int ks_rec(int[] w, int[] v, int ccty, int i, int total, String s){
		if(ccty < 0){
			return 0;
		}

		if(i == w.length){
			return total;
		}

		return Math.max(ks_rec(w, v, ccty, i+1, total, s),
			ks_rec(w, v, ccty-w[i], i+1, total+v[i], s+" "+v[i]));
	}

	/*
	0/1 knapsack
	BOTTOM UP ITERATIVE
	*/
	public static int ks_bu(int[] w, int[] v, int ccty){
		int[][] dp = new int[w.length+1][ccty+1];

		//No items present, fill the 0th row with 0s
		for(int i=0; i<=ccty; i++){
			dp[0][i] = 0;
		}

		//No ccty left
		for(int i=0; i<=w.length; i++){
			dp[i][0] = 0;
		}

		//Start filling with 1st item and ccyt = 1
		for(int item = 1; item<=w.length; item++){
			for(int ca=1; ca<=ccty; ca++){
				if(w[item-1] <= ca){
					dp[item][ca] = Math.max(
						dp[item-1][ca],
						v[item-1] + dp[item-1][ca-w[item-1]] );
				}
			}
		}

		/*
		In the above loop we are taking 'item-1' with w[] and v[] array
		because in dp array, the 1st item is denoted by index 1
		(bcz index 0 denotes no item being present), but in the v[], w[]
		array, the 1st item is denoted by 0th index.
		Hence we use 'item-1' to get the value and weight of the item'th
		item, while we use just 'item' to store result of item'th
		item in dp[][].
		*/

		for(int i=0; i<dp.length; i++){
			for(int j=0; j<dp[0].length; j++){
				p(dp[i][j]+" ");
			}
			po("");
		}

		return dp[w.length][ccty];
	}


//**********************************************************************//	

	public static void po(Object o){
		System.out.println(o);
	}

	public static void p(Object o){
		System.out.print(o);
	}

	static class ShortScan {
			Scanner sc;

			public ShortScan(){
				sc = new Scanner (System.in);
			}

			public int ni(){
				return sc.nextInt();
			}

			public String n(){
				return sc.next();
			}

			public String ns(){
				return sc.nextLine();
			}
	}

	public static void showArr(int[] arr){
		for(int x: arr){
			p(x+" ");
		}
		po("");
	}
}