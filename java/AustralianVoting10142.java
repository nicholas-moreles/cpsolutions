package com.nicholasmoreles.uva_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class AustralianVoting10142 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//final BufferedReader br = new BufferedReader(new FileReader("aussievoting.txt"));
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int numTests = Integer.parseInt(br.readLine());
		br.readLine(); // blank line
		
		for (int i = 0; i < numTests; i++) {
			
			// candidate number is key, candidate name is value
			final Map<Integer, String> candidateEncoding = new HashMap<>();
			
			// candidate number is key, list of votes is value where each vote is a Queue with the 
			// voter's preferences as integer values in order
			final Map<Integer, List<Queue<Integer>>> candidates= new HashMap<>();
			
			final int numBallots =  getElectionData(br, candidateEncoding, candidates);
			
			final List<Integer> winners = findWinners(candidates, numBallots);
			
			for (Integer winner : winners) {
				System.out.println(candidateEncoding.get(winner));
			}
			
			if (i < numTests - 1) {
				System.out.println();
			}
		}
		
		br.close();
	}
	
	private static int getElectionData(BufferedReader br, Map<Integer, String> candidates, 
			Map<Integer, List<Queue<Integer>>> votes) throws IOException {
		
		// get candidates
		final int numCandidates = Integer.parseInt(br.readLine());
		for (int i = 1; i <= numCandidates; i++) {
			candidates.put(i, br.readLine());
			votes.put(i, new LinkedList<Queue<Integer>>());
		}
		
		// get ballots
		String line = br.readLine();
		int numBallots = 0;
		while (line != null && line.length() != 0) {  // guaranteed a blank line after votes complete
			numBallots++;
			Queue<Integer> voter = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()) {
				voter.add(Integer.parseInt(st.nextToken()));
			}
			votes.get(voter.peek()).add(voter);
			line = br.readLine();
		}
		
		return numBallots;
	}
	
	public static List<Integer> findWinners(Map<Integer, List<Queue<Integer>>> candidates, int numBallots) {
		final List<Integer> winners = new ArrayList<>();
		
		while (true) {
			
			final List<Integer> losers = new ArrayList<>();
			final int votesNeededForTie = numBallots / candidates.size();
			
			// determine if there is any winner this round
			for (Integer candidate : candidates.keySet()) {
				final int numVotes = candidates.get(candidate).size();
				if (numVotes > (numBallots + 1 ) / 2) {
					winners.clear();
					winners.add(candidate);
					return winners;
				}
				if (numVotes == votesNeededForTie) {
					winners.add(candidate);
				}
			}
			
			if (winners.size() == candidates.size()) { // tie
				return winners;
			} else { // no tie
				winners.clear();
			}
			
				
			// no winner, find the losers
			int minVotes = Integer.MAX_VALUE;
			
			for (Integer candidate : candidates.keySet()) {
				final int votesForCandidate = candidates.get(candidate).size();
				if (votesForCandidate < minVotes) {
					losers.clear();
					minVotes = votesForCandidate;
				}
				if (votesForCandidate == minVotes) {
					losers.add(candidate);
				}
			}
			
			reallocateBallots(candidates, losers);
		}
	}
	
	private static void reallocateBallots(Map<Integer, List<Queue<Integer>>> candidates, List<Integer> losers){
		for (Integer candidate : losers) {
			final List<Queue<Integer>> losersBallots = candidates.get(candidate);
			for (Queue<Integer> voter : losersBallots) {
				while (losers.contains(voter.peek()) || !candidates.keySet().contains(voter.peek())) {
					voter.remove();
				}
				candidates.get(voter.peek()).add(voter); // add voter to next preferred candidate
			}
		candidates.remove(candidate);
		}
	}
}
