package com.nicholasmoreles.cpsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author Nicholas Moreles
 */
public class Fence363B {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int numPlanks = Integer.parseInt(st.nextToken());
		final int pianoWidth = Integer.parseInt(st.nextToken());
		final int[] planks = new int[numPlanks + 1]; // index 0 is empty
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= numPlanks; ++i) {
			planks[i] =  Integer.parseInt(st.nextToken());
		}
		
		long lastWidthPlanks = 0;
		int bestIndex = -1;
		long bestPlanks = Long.MAX_VALUE;
		for (int i = 1; i <= numPlanks; ++i) {
			lastWidthPlanks += i > pianoWidth ? planks[i] - planks[i - pianoWidth] : planks[i];
			if (i >= pianoWidth && lastWidthPlanks < bestPlanks) {
				bestPlanks = lastWidthPlanks;
				bestIndex = i - (pianoWidth - 1);
			}
		}
		System.out.println(bestIndex);
	}
}
