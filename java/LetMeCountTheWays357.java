package com.nicholasmoreles.cpsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * UVa #357 - Let Me Count The Ways 
 * @author Nicholas Moreles
 * 
 * Time limit: 	 3.000s
 * Running time: 1.952s
 * 
 * Approach: Create an eager cache filled with all possible input values. Lookup value when asked.
 */
public class LetMeCountTheWays357 {
	private static final int[] COINS = {1, 5, 10, 25, 50};
	private static final int MAX_INPUT = 30000;
	
	public static void main(String[] args) throws IOException {
		final long[] cache = generateCache(); // long needed to prevent integer overflow
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = br.readLine()) != null) {
			final int value = Integer.parseInt(line);
			final long ways = cache[value];
			if (ways == 1) {
				System.out.printf("There is only 1 way to produce %d cents change.\n", value);
			} else {
				System.out.printf("There are %d ways to produce %d cents change.\n", ways, value);
			}
		}
		br.close();
	}
	
	/**
	 * Approach: For each possible coin, for each possible amount, add the cache at the amount
	 * with the value of the coin subtracted. Coins must be in the outer loop to prevent double
	 * counting of the same combination.
	 * 
	 * @return the populated eager cache
	 */
	private static long[] generateCache() {
		final long[] cache = new long[MAX_INPUT + 1];
		cache[0] = 1;
		for (int coin : COINS) {
			for (int amount = coin; amount <= MAX_INPUT; amount++) {
				cache[amount] += cache[amount - coin];
			}
		}
		return cache;
	}
}
