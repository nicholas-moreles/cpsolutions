package com.nicholasmoreles.cpsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheWedding10662 {
	
	public static void main(String[] args) throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = br.readLine()) != null && line.length() != 0) {
			final StringTokenizer st = new StringTokenizer(line);
			final int numTravelAgencies = Integer.parseInt(st.nextToken());
			final int numRestaurants = Integer.parseInt(st.nextToken());
			final int numHotels = Integer.parseInt(st.nextToken());
			
			int[][] travelAgencies = new int[numTravelAgencies][numRestaurants + 1];
			int[][] restaurants = new int[numRestaurants][numHotels + 1];
			int[][] hotels = new int[numHotels][numTravelAgencies + 1];
			
			populateGraph(travelAgencies, br);
			populateGraph(restaurants, br);
			populateGraph(hotels, br);
			
			printLowestCostBruteForce(travelAgencies, restaurants, hotels);
		}
		br.close();
	}
	
	// N is small (less than 20), so N^3 is more than fast enough
	private static void printLowestCostBruteForce(int[][] travelAgencies,
			int[][] restaurants, int[][] hotels) {
		int lowestCost = Integer.MAX_VALUE;
		int bestTravelAgency = -1;
		int bestRestaurant = -1;
		int bestHotel = -1;
		
		for (int i = 0; i < travelAgencies.length; i++) {
			for (int j = 0; j < restaurants.length; j++) {
				for (int k = 0; k < hotels.length; k++) {
					if (travelAgencies[i][j+1] == 1 && restaurants[j][k+1] == 1 && hotels[k][i+1] == 1) {
						final int cost = travelAgencies[i][0] + restaurants[j][0] + hotels[k][0];
						if (cost < lowestCost) {
							lowestCost = cost;
							bestTravelAgency = i;
							bestRestaurant = j;
							bestHotel = k;
						}
					}
				}
			}
		}
		if (lowestCost == Integer.MAX_VALUE) {
			System.out.println("Don't get married!");
		} else {
			System.out.println(bestTravelAgency + " " + bestRestaurant + " " + bestHotel + ":"
					+ lowestCost);
		}
	}

	private static void populateGraph(int[][] graph, BufferedReader br) throws IOException {
		for (int i = 0; i < graph.length; i++) {
			final StringTokenizer st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken()); // first column is cost
			for (int j = 1; j < graph[0].length; j++) {
				final int val = Integer.parseInt(st.nextToken());
				graph[i][j] = val == 1 ? 0 : 1; // want 1 to be an edge exists, not 0
			}
		}
	}
}
