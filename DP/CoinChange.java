import java.util.*;
import java.io.*;

/*
 find the number of ways to make change for N cents
*/

public class CoinChange {

	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int t = sc.ni();
		while(t-->0){
			int n = sc.ni();
			int[] arr = new int[n];
			for(int i=0; i<n; i++){
				arr[i] = sc.ni();
			}

			int amount = sc.ni();
			int[] dp = new int[amount+1];
			//po(coin_bu(arr, amount, dp));
			po(min_coins(arr, amount, dp));
		}
	}

	/*
	SIMPLE RECURSIVE APPROACH	
	*/
	public static int coin_rec(int[] coins, int amount, String s, int c){
		if(amount == 0){
			return 1;
		}

		if(amount < 0){
			return 0;
		}

		int ways = 0;
		for(int i=c; i>=0; i--){
			if(amount >= coins[i]){
				// po("amount = "+amount+", coin = "+coins[i]);
				ways += coin_rec(coins, amount-coins[i], s+" "+coins[i], i);
			}
		}

		return ways;
	}

	/*
	TOP DOWN APPROACH: DOES NOT WORK
	*/
	public static int coin_td(int[] coins, int amount, String s, int[] dp, int c){
		if(dp[amount] != -1){
			return dp[amount];
		}

		if(amount == 0){
			return 1;
		}

		if(amount < 0){
			return 0;
		}

		int ways = 0;
		for(int i=c; i>=0; i--){
			if(amount >= coins[i]){
				po("amount = "+amount+", "+s+", c = "+c);
				ways += coin_td(coins, amount-coins[i], s+" "+coins[i], dp, i);
			}
		}

		dp[amount] = ways;
		po("amount = "+amount+", ways found = "+ways);
		return ways;
	}

	/*
	BOTTOM UP APPROACH
	*/
	public static int coin_bu(int[] coins, int amount, int[] dp){
		Arrays.sort(coins);
		
		/*
		We set dp[0] to 1 because if a number reaches remainder as 0,
		it means that we have found another way to make the change.
		*/	
		dp[0] = 1;

		for(int c=0; c<coins.length; c++){
			/*
			Run loop for each coin and check if any number from 1 to amount
			can be made with infinte supply of the coin coin[c] and 
			infinte supply of coins which come before c'th coin.
			*/
			for(int curr_am=0; curr_am <= amount; curr_am++){

				if(curr_am >= coins[c])
					dp[curr_am] += dp[curr_am - coins[c]];
			}

		}

		return dp[amount];
	}

	/*
	To find the min. number of coins requried to form the total,
	this method is to be used:
	*/
	public static int min_coins(int[] coins, int amount, int[] dp){
		Arrays.fill(dp, 99999);

		/*
		Here dp[0] = 0 indicates that 0 number of coins are needed
		to make a sum of 0.
		*/
		dp[0] = 0;
		for(int c=0; c<coins.length; c++){
			
			for(int curr_am=1; curr_am <= amount; curr_am++){
				
				if(curr_am >= coins[c]){
					dp[curr_am] = Math.min(dp[curr_am],
										1+dp[curr_am - coins[c]]);
				}
			}
		}

		return dp[amount];
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