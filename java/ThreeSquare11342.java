package com.nicholasmoreles.cpsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Nicholas Moreles
 * 
 * Details: http://nicholasmoreles.com/cpsolutions/three-square-uva-11342/
 */
public class ThreeSquare11342 {
	private static final int MAX_NUM = 50000;
	private static final int MAX_SQUARE = 224; // sqrt(MAX_NUM)
	private static int[][] cache = generateCache(); // eager cache
	
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final String noAnswer = "-1";
		final String newLine = "\n";
		final String space = " ";
		final int numTrials = Integer.parseInt(br.readLine());
		for (int i = 1; i <= numTrials; i++) {
			final int total = Integer.parseInt(br.readLine());
			if (cache[total][0] == 0) {
				sb.append(noAnswer);
			} else {
				sb.append(cache[total][1]).append(space);
				sb.append(cache[total][2]).append(space);
				sb.append(cache[total][3]);
			}
			sb.append(newLine);
		}
		br.close();
		System.out.print(sb.toString());
	}
	
	private static int[][] generateCache() {
		int[][] cache = new int[MAX_NUM + 1][4];
		for (int i = 0; i <= MAX_SQUARE; i++) {
			for (int j = i; j <= MAX_SQUARE; j++) {
				for (int k = j; k <= MAX_SQUARE; k++) {
					final int total = i*i + j*j + k*k;
					if (total > MAX_NUM) {
						break;
					}
					if (cache[total][0] == 0) {
						cache[total][0] = 1;
						cache[total][1] = i;
						cache[total][2] = j;
						cache[total][3] = k;
					}
				}
			}
		}
		return cache;
	}
}