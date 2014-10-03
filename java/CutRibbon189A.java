package com.nicholasmoreles.cpsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Nicholas Moreles
 */
public class CutRibbon189A {
	private static final int NUM_COINS = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int number = Integer.parseInt(st.nextToken());
		int[] coins = new int[NUM_COINS];
		for (int i = 0; i < NUM_COINS; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
		}
		int[] maxCoins = new int[number + 1];
		Arrays.fill(maxCoins, -1);
		maxCoins[0] = 0;
		for (int i = 1; i <= number; i++) {
			for (int coin : coins) {
				final int prev = i - coin;
				if (prev >= 0 && maxCoins[prev] >= maxCoins[i] && maxCoins[prev] != -1) {
					maxCoins[i] = maxCoins[prev] + 1;
				}
			}
		}
		System.out.println(maxCoins[number]);
	}
}
